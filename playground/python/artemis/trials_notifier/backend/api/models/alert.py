
from sqlalchemy import Column, Integer, String, ForeignKey
from backend.api.utils.db import Base


class Alert(Base):
    __tablename__ = "alerts"

    id = Column(Integer, primary_key=True, index=True)
    auth0_id = Column(String, index=True)  # Auth0 user ID
    condition = Column(String, nullable=True)
    phase = Column(String, nullable=True)
    min_enrollment = Column(Integer, nullable=True)
    notify_via = Column(String, default="email")  # "email" or "sms"
