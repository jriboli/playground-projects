
from fastapi import APIRouter, Depends
from sqlalchemy.orm import Session
from backend.api.models.alert import Alert
from backend.api.utils.db import get_db_session

router = APIRouter()

@router.post("/alerts")
def create_alert(alert: Alert, db: Session = Depends(get_db_session)):
    """
    Creates a new user alert.
    """
    db.add(alert)
    db.commit()
    return {"message": "Alert created successfully"}