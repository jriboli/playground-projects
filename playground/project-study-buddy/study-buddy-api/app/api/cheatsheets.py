from fastapi import APIRouter, Depends, HTTPException
from sqlalchemy.orm import Session

# Local Stuff
from app.services.cheat_sheet_service import get_cheat_sheets_from_db, create_cheat_sheet_in_db
from app.db.database import get_db
from app.models import CheatSheet
from app.responses import CheatSheetResponse

router = APIRouter()

@router.get("/", response_model=list[CheatSheetResponse])
def get_cheat_sheets(db: Session = Depends(get_db)):
    cheat_sheets = get_cheat_sheets_from_db(db)
    if not cheat_sheets:
        raise HTTPException(status_code=404, detail="No cheat sheets found")
    return cheat_sheets

# @router.post("/", response_model=CheatSheetResponse)
# def create_cheat_sheet(sheet: CheatSheet, db: Session = Depends(get_db)):
#     cheat_sheet = create_cheat_sheet_in_db(sheet, db)
#     return cheat_sheet
