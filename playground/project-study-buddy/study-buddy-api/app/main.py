from fastapi import FastAPI, Depends, HTTPException, status
from fastapi.security import OAuth2PasswordRequestForm
from sqlalchemy.orm import Session
from sqlalchemy.sql.expression import func
from fastapi.exceptions import RequestValidationError
from sqlalchemy.exc import SQLAlchemyError

# Local Stuff
from app.auth import authenticate_user, create_access_token, hash_password, check_role
from app.models import User
from app.enums.user_role import UserRole
from app.api import users, cheatsheets, flashcards, admins
from app.errors.exception_handlers import validation_exception_handler, sqlalchemy_exception_handler, general_exception_handler
from app.db.database import SessionLocal

from datetime import timedelta

app = FastAPI()

# Register the custom exception handlers
app.add_exception_handler(RequestValidationError, validation_exception_handler)
app.add_exception_handler(SQLAlchemyError, sqlalchemy_exception_handler)
app.add_exception_handler(Exception, general_exception_handler)

# Dependency to get the DB session
def get_db():
    db = SessionLocal()
    try:
        yield db
    finally:
        db.close()

# Include the routers from the different route modules
app.include_router(users.router, prefix="/users", tags=["users"])
app.include_router(cheatsheets.router, prefix="/cheatsheets", tags=["cheatsheets"])
app.include_router(flashcards.router, prefix="/flashcards", tags=["flashcards"])
app.include_router(admins.router, prefix="/admin", tags=["admins"])
# ----------------- Potentially Add QUIZ -----------------

# Register new user
@app.post("/register")
def register_user(username: str, email: str, password: str, role: UserRole = UserRole.student, db: Session = Depends(get_db)):
    existing_user = db.query(User).filter(User.username == username).first()
    if existing_user:
        raise HTTPException(status_code=400, detail="Username already exists")
    
    new_user = User(
        username=username,
        email=email,
        hashed_password=hash_password(password),
        role=role
    )
    db.add(new_user)
    db.commit()
    return {"message": "User created successfully", "role": role}

# Login and get token
@app.post("/token")
def login(form_data: OAuth2PasswordRequestForm = Depends(), db: Session = Depends(get_db)):
    user = authenticate_user(db, form_data.username, form_data.password)
    if not user:
        raise HTTPException(status_code=status.HTTP_401_UNAUTHORIZED, detail="Invalid credentials")
    
    access_token = create_access_token(data={"sub": user.username}, expires_delta=timedelta(minutes=30))
    return {"access_token": access_token, "token_type": "bearer"}

# Added for Role Base Protected Routes
@app.get("/instructor/materials", dependencies=[Depends(check_role(UserRole.instructor))])
def instructor_materials():
    return {"message": "Instructor-only materials"}

@app.get("/students/homework", dependencies=[Depends(check_role(UserRole.student))])
def student_homework():
    return {"message": "Student-only homework"}

