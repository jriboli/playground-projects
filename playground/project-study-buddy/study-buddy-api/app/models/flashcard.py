from sqlalchemy import Column, Integer, String, ForeignKey
from sqlalchemy.orm import relationship

# Local Stuff
from app.db.database import Base

class Flashcard(Base):
    __tablename__ = "flashcards"
    
    id = Column(Integer, primary_key=True, index=True)
    cheat_sheet_id = Column(Integer, ForeignKey("cheatsheets.id"))
    question = Column(String(1024))
    answer = Column(String(1024))
    
    # Define the reverse relationship to CheatSheet (Many-to-One)
    cheat_sheet = relationship("CheatSheet", back_populates="flashcards")