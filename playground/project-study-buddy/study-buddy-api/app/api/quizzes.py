from fastapi import APIRouter, Depends, HTTPException
from sqlalchemy.orm import Session
from app.db.database import get_db

#Local Stuff
from app.responses.quiz_question import QuizQuestionCreate, QuizQuestionResponse
from app.services.quiz_question_service import (
    create_quiz_question, 
    get_quiz_questions, 
    get_quiz_question_by_id, 
    delete_quiz_question
)

router = APIRouter()

@router.post("/", response_model=QuizQuestionResponse)
def create_new_quiz_question(question: QuizQuestionCreate, db: Session = Depends(get_db)):
    return create_quiz_question(db, question)

@router.get("/", response_model=list[QuizQuestionResponse])
def list_quiz_questions(db: Session = Depends(get_db)):
    return get_quiz_questions(db)

@router.get("/{question_id}", response_model=QuizQuestionResponse)
def get_quiz_question(question_id: int, db: Session = Depends(get_db)):
    question = get_quiz_question_by_id(db, question_id)
    if not question:
        raise HTTPException(status_code=404, detail="Quiz question not found")
    return question

@router.delete("/{question_id}", status_code=204)
def remove_quiz_question(question_id: int, db: Session = Depends(get_db)):
    if not delete_quiz_question(db, question_id):
        raise HTTPException(status_code=404, detail="Quiz question not found")
    return {"message": "Quiz question deleted successfully"}
