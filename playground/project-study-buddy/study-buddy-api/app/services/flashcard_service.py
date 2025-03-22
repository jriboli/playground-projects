from sqlalchemy import func
from sqlalchemy.orm import Session
from fastapi import HTTPException

# Local Stuff
from app.models import Flashcard

def get_flashcard_from_db(db: Session):
    return db.query(Flashcard).order_by(func.rand()).first()

def create_flashcard_in_db(data: dict, db: Session):
    # Validate the input 
    if "topic" not in data or "package" not in data:
        raise HTTPException(status_code=400, detail="Missing required fields")
    
    new_cheat_sheet = Flashcard(**data)
    db.add(new_cheat_sheet)
    db.commit()
    db.refresh(new_cheat_sheet)
    return new_cheat_sheet
