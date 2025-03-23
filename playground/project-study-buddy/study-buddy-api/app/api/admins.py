from fastapi import APIRouter, Depends, HTTPException
from sqlalchemy.orm import Session

# Local Stuff
#from app.services.user_service import get_user, create_user
from app.db.database import get_db
from app.auth import check_role, hash_password
from app.models import User, CheatSheet, Flashcard
from app.enums.user_role import UserRole
from app.responses import CheatSheetResponse
from app.services.cheat_sheet_service import create_cheat_sheet_in_db
from app.services.flashcard_service import get_all_flashcards_from_db

router = APIRouter()


@router.get("/dashboard", dependencies=[Depends(check_role(UserRole.admin))])
def admin_dashboard():
    return {"message": "Welcome, Admin!"}

## ------- USERS -----------------------
@router.get("/users")
def get_all_users(db: Session = Depends(get_db), current_user: User = Depends(check_role(UserRole.admin))):
    users = db.query(User).all()
    return users

@router.post("/users")
def create_user(username: str, email: str, password: str, role: UserRole = UserRole.student, db: Session = Depends(get_db), current_user: User = Depends(check_role(UserRole.admin))):
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

@router.put("/users/{user_id}")
def update_user(user_id: int, role: UserRole, db: Session = Depends(get_db), current_user: User = Depends(check_role(UserRole.admin))):
    user = db.query(User).filter(User.id == user_id).first()
    if not user:
        raise HTTPException(status_code=404, detail="User not found")
    
    user.role = role
    db.commit()
    db.refresh(user)
    return {"message": "User role updated", "role": user.role}

@router.delete("/users/{user_id}")
def delete_user(user_id: int, db: Session = Depends(get_db), current_user: User = Depends(check_role(UserRole.admin))):
    user = db.query(User).filter(User.id == user_id).first()
    if not user:
        raise HTTPException(status_code=404, detail="User not found")
    
    db.delete(user)
    db.commit()
    return {"message": "User deleted successfully"}
