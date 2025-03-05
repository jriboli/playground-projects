
from sqlalchemy import create_engine
from sqlalchemy.ext.declarative import declarative_base
from sqlalchemy.orm import sessionmaker
import os

DATABASE_URL = os.getenv("DATABASE_URL", "mysql+pymysql://root:pass@localhost/clinical_trials")

engine = create_engine(DATABASE_URL, echo=True)
SessionLocal = sessionmaker(autocommit=False, autoflush=False, bind=engine)
Base = declarative_base()

def init_db():
    from backend.api.models import trial, alert, user
    Base.metadata.create_all(bind=engine)

def get_db_session():
    """
    Dependency to get the DB session.
    """
    db = SessionLocal()
    try:
        yield db
    finally:
        db.close()