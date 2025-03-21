from fastapi import APIRouter, Depends

# Local Stuff
# from app.services.user_service import get_user, create_user
from app.auth import get_current_user
from app.models import User

router = APIRouter()

# Protected route
@router.get("/me")
def get_current_user_data(current_user: User = Depends(get_current_user)):
    return {"username": current_user.username, "email": current_user.email}
