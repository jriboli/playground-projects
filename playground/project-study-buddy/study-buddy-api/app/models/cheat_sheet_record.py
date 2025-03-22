from sqlalchemy import Column, Integer, String

# Local Stuff
from app.models.base import Base

class CheatSheetRecord(Base):
    __tablename__ = "cheatsheets"
    
    id = Column(Integer, primary_key=True, index=True)
    topic = Column(String(255), index=True)
    package = Column(String(255))
    method = Column(String(255))
    description = Column(String(1024))
    code_example = Column(String(2048))