from fastapi import APIRouter, HTTPException, Depends
from sqlalchemy.orm import Session

# Local Stuff
from app.services.flashcard_service import *
from app.db.database import get_db
from app.models import Flashcard
from app.responses import *

router = APIRouter()

@router.get("/random")
def get_random_flashcard(db: Session = Depends(get_db)):
    flashcard = get_flashcard_from_db(db)
    if not flashcard:
        raise HTTPException(status_code=404, detail="No flashcards found")
    return flashcard

@router.post("/")
def create_flashcard(card = Flashcard, db: Session = Depends(get_db)):
    flashcard = create_flashcard_in_db(card)
    return flashcard
