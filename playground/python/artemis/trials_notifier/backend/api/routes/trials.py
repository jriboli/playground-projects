
from fastapi import APIRouter, Depends
from sqlalchemy.orm import Session
from backend.api.utils.db import get_db_session
from backend.api.models.trial import Trial

router = APIRouter()

@router.get("/trials")
def get_trials(db: Session = Depends(get_db_session)):
    """
    Returns all stored clinical trials.
    """
    return db.query(Trial).all()