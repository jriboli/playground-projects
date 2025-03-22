from sqlalchemy import Column, Integer, String, ForeignKey

# Local Stuff
from app.models.base import Base

class Flashcard(Base):
    __tablename__ = "flashcards"
    
    id = Column(Integer, primary_key=True, index=True)
    topic = Column(String(255), index=True)
    package = Column(String(255))
    method = Column(String(255))
    question = Column(String(1024))
    answer = Column(String(1024))