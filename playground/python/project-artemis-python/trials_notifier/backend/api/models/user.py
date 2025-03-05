
from sqlalchemy import Column, Integer, String
from backend.api.utils.db import Base

class User(Base):
    __tablename__ = "users"

    id = Column(Integer, primary_key=True, index=True)
    auth0_id = Column(String, unique=True, index=True)
    email = Column(String, unique=True)
    phone = Column(String, nullable=True)