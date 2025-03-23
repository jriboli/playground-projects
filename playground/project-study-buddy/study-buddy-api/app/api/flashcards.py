from fastapi import APIRouter, HTTPException, Depends
from sqlalchemy.orm import Session

# Local Stuff
from app.services.flashcard_service import *
from app.db.database import get_db
from app.auth import check_role
from app.enums.user_role import UserRole
from app.models import Flashcard, User
from app.responses import *

router = APIRouter()

@router.get("/random")
def get_random_flashcard(db: Session = Depends(get_db)):
    flashcard = get_random_flashcard_from_db(db)
    if not flashcard:
        raise HTTPException(status_code=404, detail="No flashcards found")
    return flashcard

@router.post("/")
def create_flashcard(card = Flashcard, db: Session = Depends(get_db)):
    flashcard = save_flashcard_in_db(card, db)
    return {"message": "Flashcard created successfully"}

@router.put("/{flashcard_id}")
def update_flashcard(card = Flashcard, db: Session = Depends(get_db)):
    flashcard = save_flashcard_in_db(card, db)
    if not flashcard:
        raise HTTPException(status_code=404, detail="Flashcard not found")
    return {"message": "Flashcard updated successfully"}

@router.delete("/{flashcard_id}", status_code=204)
def remove_flashcard(
    flashcard_id: int, 
    db: Session = Depends(get_db), 
    current_user: User = Depends(check_role(UserRole.admin))
):
    if not delete_flashcard_from_db(db, flashcard_id):
        raise HTTPException(status_code=404, detail="Flashcard not found")
    
    return {"message": "Flashcard deleted successfully"}

@router.get("/all")
def get_all_flashcards(
    db: Session = Depends(get_db), 
    current_user: User = Depends(check_role(UserRole.admin))
):
    return get_all_flashcards_from_db(db)