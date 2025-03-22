from sqlalchemy import Column, Integer, String, ForeignKey, JSON
from sqlalchemy.orm import relationship
from app.models.base import Base  # Import Base

class QuizQuestion(Base):
    __tablename__ = "quiz_questions"

    id = Column(Integer, primary_key=True, index=True)
    #cheat_sheet_id = Column(Integer, ForeignKey("cheatsheets.id"), nullable=False)
    topic = Column(String(255), index=True)
    package = Column(String(255))
    method = Column(String(255))
    question = Column(String(512), nullable=False)
    options = Column(JSON, nullable=False)  # Stores multiple-choice options as a JSON array
    correct_answer = Column(String(255), nullable=False)
    

    # Relationship to CheatSheet
    #cheat_sheet = relationship("CheatSheet", back_populates="quiz_questions")
