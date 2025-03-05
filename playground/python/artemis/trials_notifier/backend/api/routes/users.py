
from fastapi import APIRouter, Depends
from sqlalchemy.orm import Session
from backend.api.models.user import User
from backend.api.utils.db import get_db_session

router = APIRouter()

@router.post("/users")
def register_user(user: User, db: Session = Depends(get_db_session)):
    """
    Registers a new Auth0 user.
    """
    db.add(user)
    db.commit()
    return {"message": "User registered successfully"}