from sqlalchemy.orm import Session
from fastapi import HTTPException

# Local Stuff
from app.models import CheatSheetRecord


def create_cheat_sheet_by_options_from_db(db: Session, topic: str, package: str):
    if(not topic) & (not package):
        records = db.query(CheatSheetRecord).all()
    else:
        ## Some Logic to filter DB results
        return None

def save_record_in_db(sheet: CheatSheetRecord, db: Session):
    # Validate the input 
    if not sheet.topic or not sheet.method:
        raise HTTPException(status_code=400, detail="Missing required fields")
    db_cheat_sheet = find_or_create_record(CheatSheetRecord.id)
    
    new_cheat_sheet = set_fields_in_record(db_cheat_sheet, sheet)
    db.add(new_cheat_sheet)
    db.commit()
    db.refresh(new_cheat_sheet)
    return new_cheat_sheet

def find_or_create_record(record_id: int, db:Session):
    if(not record_id):
        return None
    else:
        return find_record_by_id_from_db(record_id)
    
def find_record_by_id_from_db(db: Session, record_id: int):
    return db.query(CheatSheetRecord).filter_by(id=record_id) 

# Taking the information from DB, if any - and updating with request retails
# If new, sheet_from_db is empty and we are replacing everything
def set_fields_in_record(sheet_from_db: CheatSheetRecord, sheet: CheatSheetRecord):
    sheet_from_db.id = sheet.id
    sheet_from_db.topic = sheet.topic
    sheet_from_db.package = sheet.package
    sheet_from_db.method = sheet.method
    sheet_from_db.description = sheet.description
    sheet_from_db.code_example = sheet.code_example

