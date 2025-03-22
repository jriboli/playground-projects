from fastapi import APIRouter, Depends, HTTPException
from sqlalchemy.orm import Session

# Local Stuff
from app.services.cheat_sheet_service import *
from app.db.database import get_db
from app.models import CheatSheetRecord
from app.responses import CheatSheetResponse

router = APIRouter()

@router.get("/", response_model=CheatSheetResponse)
def get_cheat_sheets(topic: str, package: str, db: Session = Depends(get_db)):
    cheat_sheets = get_cheat_sheets_from_db(db, topic, package)
    if not cheat_sheets:
        raise HTTPException(status_code=404, detail="No cheat sheets records found")
    
    response = CheatSheetResponse
    response.records = cheat_sheets
    return response

@router.post("/", response_model=CheatSheetResponse)
def create_cheat_sheet_record(sheet: CheatSheetRecord, db: Session = Depends(get_db)):
    cheat_sheet = create_cheat_sheet_record_in_db(sheet, db)
    return cheat_sheet
