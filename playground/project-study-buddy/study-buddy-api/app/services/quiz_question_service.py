from sqlalchemy.orm import Session
from app.models.quiz_question import QuizQuestion
from app.responses.quiz_question import QuizQuestionCreate


def get_all_quiz_questions(db: Session):
    return db.query(QuizQuestion).all()

def save_question_in_db(question: QuizQuestion, db: Session):
    # Any validation needed?
    db_question = find_or_create_question(question.id)
    
    new_question = set_fields_in_question(db_question, question)
    db.add(new_question)
    db.commit()
    db.refresh(new_question)
    return new_question

def delete_question_from_db(question_id: int, db: Session):
    question = db.query(QuizQuestion).filter(QuizQuestion.id == question_id).first()
    if question:
        db.delete(question)
        db.commit()
        return True
    return False

def find_or_create_question(question_id: int, db: Session):
    if(not question_id):
        return None
    else:
        return find_question_by_id_from_db(question_id)

def find_question_by_id_from_db(question_id: int, db: Session):
    return db.query(QuizQuestion).filter(QuizQuestion.id == question_id).first()

def set_fields_in_question(question_from_db: QuizQuestion, question: QuizQuestion):
    question_from_db.id = question.id
    question_from_db.topic = question.topic
    question_from_db.package = question.package
    question_from_db.method = question.method
    question_from_db.question = question.question
    question_from_db.options = question.options
    question_from_db.correct_answer = question.correct_answer


