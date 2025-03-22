from sqlalchemy.orm import Session
from fastapi import HTTPException

# Local Stuff
from app.models import CheatSheetRecord


def get_cheat_sheets_from_db(db: Session, topic: str, package: str):
    if(not topic) & (not package):
        records = db.query(CheatSheetRecord).all()
    else:
        ## Some Logic to filter DB results
        return None
    
def get_cheat_sheet_by_id(db: Session, record_id: int):
    return db.query(CheatSheetRecord).filter_by(id=record_id)

def create_cheat_sheet_record_in_db(data: dict, db: Session):
    # Validate the input 
    if "topic" not in data or "method" not in data:
        raise HTTPException(status_code=400, detail="Missing required fields")
    
    new_cheat_sheet = CheatSheetRecord(**data)
    db.add(new_cheat_sheet)
    db.commit()
    db.refresh(new_cheat_sheet)
    return new_cheat_sheet


