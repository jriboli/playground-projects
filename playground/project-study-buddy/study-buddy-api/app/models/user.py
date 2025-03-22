from sqlalchemy import Column, Integer, String, Boolean, Enum

# Local Stuff
#from app.db.database import Base
from app.models.base import Base
from app.enums.user_role import UserRole

class User(Base):
    __tablename__ = "users"

    id = Column(Integer, primary_key=True, index=True)
    username = Column(String(255), unique=True, index=True)
    email = Column(String(255), unique=True, index=True)
    hashed_password = Column(String(255))
    is_active = Column(Boolean, default=True)
    role = Column(Enum(UserRole), default=UserRole.student)