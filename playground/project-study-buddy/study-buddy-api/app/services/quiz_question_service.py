from sqlalchemy.orm import Session
from app.models.quiz_question import QuizQuestion
from app.responses.quiz_question import QuizQuestionCreate

def create_quiz_question(db: Session, quiz_data: QuizQuestionCreate):
    new_question = QuizQuestion(**quiz_data.dict())
    db.add(new_question)
    db.commit()
    db.refresh(new_question)
    return new_question

def get_quiz_questions(db: Session):
    return db.query(QuizQuestion).all()

def get_quiz_question_by_id(db: Session, question_id: int):
    return db.query(QuizQuestion).filter(QuizQuestion.id == question_id).first()

def delete_quiz_question(db: Session, question_id: int):
    question = db.query(QuizQuestion).filter(QuizQuestion.id == question_id).first()
    if question:
        db.delete(question)
        db.commit()
        return True
    return False
