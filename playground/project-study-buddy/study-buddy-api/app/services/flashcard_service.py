from sqlalchemy import func
from sqlalchemy.orm import Session
from fastapi import HTTPException

# Local Stuff
from app.models import Flashcard

def get_random_flashcard_from_db(db: Session):
    return db.query(Flashcard).order_by(func.rand()).first()

def get_all_flashcards_from_db(db: Session):
    flashcards = db.query(Flashcard).all()
    return flashcards

def save_flashcard_in_db(flashcard: Flashcard, db: Session):
    # Any validation needed?
    db_flashcard = find_or_create_flashcard(flashcard.id)
    
    new_flashcard = set_fields_in_flashcard(db_flashcard, flashcard)
    db.add(new_flashcard)
    db.commit()
    db.refresh(new_flashcard)
    return new_flashcard 

def delete_flashcard_from_db(flashcard_id: int, db: Session):
    db_flashcard = find_or_create_flashcard(flashcard_id)
    db.delete(db_flashcard)
    db.commit()
    
def find_or_create_flashcard(flashcard_id: int, db: Session):
    if(not flashcard_id):
        return None
    else:
        return find_flashcard_by_id_from_db(flashcard_id)

def find_flashcard_by_id_from_db(flashcard_id: int, db: Session):
    return db.query(Flashcard).filter_by(id=flashcard_id).first()


def set_fields_in_flashcard(flashcard_from_db: Flashcard, flashcard: Flashcard):
    flashcard_from_db.id = flashcard.id
    flashcard_from_db.topic = flashcard.topic
    flashcard_from_db.package = flashcard.package
    flashcard_from_db.method = flashcard.method
    flashcard_from_db.question = flashcard.question
    flashcard_from_db.answer = flashcard.answer
