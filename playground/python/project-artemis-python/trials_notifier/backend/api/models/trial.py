
from sqlalchemy import Column, Integer, String
from backend.api.utils.db import Base

class Trial(Base):
    __tablename__ = "trials"

    id = Column(Integer, primary_key=True, index=True)
    nct_id = Column(String, unique=True, index=True)
    title = Column(String)
    condition = Column(String)
    phase = Column(String)
    enrollment = Column(Integer)
    start_date = Column(String)
    country = Column(String)
