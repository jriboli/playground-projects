from fastapi import APIRouter, Depends, HTTPException
from sqlalchemy.orm import Session

# Local Stuff
#from app.services.user_service import get_user, create_user
from app.db.database import get_db
from app.auth import check_role, hash_password
from app.models import User, CheatSheet, Flashcard
from app.enums.user_role import UserRole

router = APIRouter()


@router.get("/dashboard", dependencies=[Depends(check_role(UserRole.admin))])
def admin_dashboard():
    return {"message": "Welcome, Admin!"}

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

@router.get("/cheatsheets")
def get_all_cheatsheets(db: Session = Depends(get_db), current_user: User = Depends(check_role(UserRole.admin))):
    cheat_sheets = db.query(CheatSheet).all()
    return cheat_sheets

@router.post("/cheatsheets")
def create_cheatsheet(topic: str, package: str, method: str, description: str, code_example: str, db: Session = Depends(get_db), current_user: User = Depends(check_role(UserRole.admin))):
    new_cheatsheet = CheatSheet(
        topic=topic,
        package=package,
        method=method,
        description=description,
        code_example=code_example
    )
    db.add(new_cheatsheet)
    db.commit()
    return {"message": "Cheat sheet created successfully"}

@router.put("/cheatsheets/{cheatsheet_id}")
def update_cheatsheet(cheatsheet_id: int, topic: str, package: str, method: str, description: str, code_example: str, db: Session = Depends(get_db), current_user: User = Depends(check_role(UserRole.admin))):
    cheatsheet = db.query(CheatSheet).filter(CheatSheet.id == cheatsheet_id).first()
    if not cheatsheet:
        raise HTTPException(status_code=404, detail="Cheat sheet not found")
    
    cheatsheet.topic = topic
    cheatsheet.package = package
    cheatsheet.method = method
    cheatsheet.description = description
    cheatsheet.code_example = code_example
    db.commit()
    db.refresh(cheatsheet)
    return {"message": "Cheat sheet updated successfully"}

@router.delete("/cheatsheets/{cheatsheet_id}")
def delete_cheatsheet(cheatsheet_id: int, db: Session = Depends(get_db), current_user: User = Depends(check_role(UserRole.admin))):
    cheatsheet = db.query(CheatSheet).filter(CheatSheet.id == cheatsheet_id).first()
    if not cheatsheet:
        raise HTTPException(status_code=404, detail="Cheat sheet not found")
    
    db.delete(cheatsheet)
    db.commit()
    return {"message": "Cheat sheet deleted successfully"}

@router.get("/flashcards")
def get_all_flashcards(db: Session = Depends(get_db), current_user: User = Depends(check_role(UserRole.admin))):
    flashcards = db.query(Flashcard).all()
    return flashcards

@router.post("/flashcards")
def create_flashcard(cheat_sheet_id: int, question: str, answer: str, db: Session = Depends(get_db), current_user: User = Depends(check_role(UserRole.admin))):
    new_flashcard = Flashcard(
        cheat_sheet_id=cheat_sheet_id,
        question=question,
        answer=answer
    )
    db.add(new_flashcard)
    db.commit()
    return {"message": "Flashcard created successfully"}

@router.put("/flashcards/{flashcard_id}")
def update_flashcard(flashcard_id: int, question: str, answer: str, db: Session = Depends(get_db), current_user: User = Depends(check_role(UserRole.admin))):
    flashcard = db.query(Flashcard).filter(Flashcard.id == flashcard_id).first()
    if not flashcard:
        raise HTTPException(status_code=404, detail="Flashcard not found")
    
    flashcard.question = question
    flashcard.answer = answer
    db.commit()
    db.refresh(flashcard)
    return {"message": "Flashcard updated successfully"}

@router.delete("/flashcards/{flashcard_id}")
def delete_flashcard(flashcard_id: int, db: Session = Depends(get_db), current_user: User = Depends(check_role(UserRole.admin))):
    flashcard = db.query(Flashcard).filter(Flashcard.id == flashcard_id).first()
    if not flashcard:
        raise HTTPException(status_code=404, detail="Flashcard not found")
    
    db.delete(flashcard)
    db.commit()
    return {"message": "Flashcard deleted successfully"}