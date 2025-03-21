from sqlalchemy import Column, Integer, String
from sqlalchemy.orm import relationship

# Local Stuff
from app.db.database import Base

class CheatSheet(Base):
    __tablename__ = "cheatsheets"
    
    id = Column(Integer, primary_key=True, index=True)
    topic = Column(String(255), index=True)
    package = Column(String(255))
    method = Column(String(255))
    description = Column(String(1024))
    code_example = Column(String(2048))
    
    # Define the relationship with Flashcards (One-to-Many)
    flashcards = relationship("Flashcard", back_populates="cheat_sheet")