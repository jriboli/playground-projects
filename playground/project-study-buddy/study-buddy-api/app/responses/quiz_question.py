from pydantic import BaseModel
from typing import List

class QuizQuestionBase(BaseModel):
    #cheat_sheet_id: int
    topic: str
    package: str
    method: str
    question: str
    options: List[str]
    correct_answer: str

class QuizQuestionCreate(QuizQuestionBase):
    pass  # Used for creating new questions

class QuizQuestionResponse(QuizQuestionBase):
    id: int

    class Config:
        from_attributes = True  # ✅ Enables conversion from SQLAlchemy to Pydantic
