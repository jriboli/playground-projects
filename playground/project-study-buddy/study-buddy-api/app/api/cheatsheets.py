from fastapi import APIRouter, Depends, HTTPException
from sqlalchemy.orm import Session

# Local Stuff
from app.services.cheat_sheet_service import *
from app.db.database import get_db
from app.auth import check_role
from app.enums.user_role import UserRole
from app.models import CheatSheetRecord, User
from app.responses import CheatSheetResponse, CheatSheetRecordSchema

router = APIRouter()

@router.get("/", response_model=CheatSheetResponse)
def get_cheat_sheets(topic: str, package: str, db: Session = Depends(get_db)):
    cheat_sheets = create_cheat_sheet_by_options_from_db(db, topic, package)
    if not cheat_sheets:
        raise HTTPException(status_code=404, detail="No cheat sheets records found")
    
    records = [CheatSheetRecordSchema.model_validate(record) for record in cheat_sheets]
    return CheatSheetResponse(records=records)

# @router.post("/", response_model=CheatSheetResponse)
# def create_cheat_sheet_record(sheet: CheatSheetRecord, db: Session = Depends(get_db)):
#     cheat_sheet = save_record_in_db(sheet, db)
    
#     records = [CheatSheetRecordSchema.model_validate(record) for record in cheat_sheet]
#     return CheatSheetResponse(records=records)

@router.put("/{cheatsheet_id}")
def update_cheatsheet(sheet: CheatSheetRecord, db: Session = Depends(get_db)):
    cheatsheet = save_record_in_db(sheet, db)
    if not cheatsheet:
        raise HTTPException(status_code=404, detail="Cheat sheet not found")
    return {"message": "Cheat sheet updated successfully"}

@router.delete("/{cheatsheet_id}")
def delete_cheatsheet(cheatsheet_id: int, db: Session = Depends(get_db), current_user: User = Depends(check_role(UserRole.admin))):
    cheatsheet = delete_cheatsheet(cheatsheet_id, db)
    if not cheatsheet:
        raise HTTPException(status_code=404, detail="Cheat sheet not found")
    return {"message": "Cheat sheet deleted successfully"}

@router.get("/all")
def get_all_cheatsheets(db: Session = Depends(get_db), current_user: User = Depends(check_role(UserRole.admin))):
    cheat_sheets = get_all_cheatsheets(db)
    return cheat_sheets