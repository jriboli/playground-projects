from fastapi import APIRouter, Depends, HTTPException
from sqlalchemy.orm import Session

# Local Stuff
#from app.services.user_service import get_user, create_user
from app.db.database import get_db
from app.auth import check_role, hash_password
from app.models import User
from app.enums.user_role import UserRole
from app.services.user_service import *

router = APIRouter()


@router.get("/dashboard", dependencies=[Depends(check_role(UserRole.admin))])
def admin_dashboard():
    return {"message": "Welcome, Admin!"}

## ------- USERS -----------------------
@router.get("/users")
def get_all_users(db: Session = Depends(get_db), current_user: User = Depends(check_role(UserRole.admin))):
    users = get_all_users(db)
    return users

@router.post("/users")
def create_user(user: User, db: Session = Depends(get_db), current_user: User = Depends(check_role(UserRole.admin))):
    # Setting users to default to "Student"
    user.role = UserRole.student
    
    existing_user = save_user_in_db(user, db)
    if existing_user:
        raise HTTPException(status_code=400, detail="Username already exists")
    return {"message": "User created successfully", "role": role}

@router.put("/users/{user_id}")
def update_user(user: User, db: Session = Depends(get_db), current_user: User = Depends(check_role(UserRole.admin))):
    user = find_user_by_id_from_db(user.id, db)
    if not user:
        raise HTTPException(status_code=404, detail="User not found")
    return {"message": "User role updated", "role": user.role}

@router.delete("/users/{user_id}")
def delete_user(user: User, db: Session = Depends(get_db), current_user: User = Depends(check_role(UserRole.admin))):
    user = delete_user(user.id, db)
    if not user:
        raise HTTPException(status_code=404, detail="User not found")
    return {"message": "User deleted successfully"}
