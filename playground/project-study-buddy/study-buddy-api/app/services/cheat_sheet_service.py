from sqlalchemy.orm import Session
from fastapi import HTTPException

# Local Stuff
from app.models import CheatSheet

def get_cheat_sheets_from_db(db: Session):
    return db.query(CheatSheet).all()

def create_cheat_sheet_in_db(data: dict, db: Session):
    # Validate the input 
    if "topic" not in data or "method" not in data:
        raise HTTPException(status_code=400, detail="Missing required fields")
    
    new_cheat_sheet = CheatSheet(**data)
    db.add(new_cheat_sheet)
    db.commit()
    db.refresh(new_cheat_sheet)
    return new_cheat_sheet
