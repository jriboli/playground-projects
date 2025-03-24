from sqlalchemy.orm import Session
from fastapi import HTTPException

# Local Stuff
from app.models import User


def get_all_users_from_db(db: Session):
    users = db.query(User).all()
    return users

def save_user_in_db(user: User, db: Session):
    # Any validation needed?
    db_user = find_or_create_user(user.id)
    
    new_user = set_fields_in_user(db_user, user)
    db.add(new_user)
    db.commit()
    db.refresh(new_user)
    return new_user 

def delete_user_from_db(user_id: int, db: Session):
    db_user = find_or_create_user(user_id)
    db.delete(db_user)
    db.commit()
    
def find_or_create_user(user_id: int, db: Session):
    if(not user_id):
        return None
    else:
        return find_user_by_id_from_db(user_id)

def find_user_by_id_from_db(user_id: int, db: Session):
    return db.query(User).filter_by(id=user_id).first()


def set_fields_in_user(user_from_db: User, user: User):
    user_from_db.id = user.id
    user_from_db.username = user.username
    user_from_db.email = user.email
    user_from_db.hashed_password = user.hashed_password
    user_from_db.is_active = user.is_active
    user_from_db.role = user.role
