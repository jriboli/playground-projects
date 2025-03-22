import os
from sqlalchemy import create_engine
#from sqlalchemy.ext.declarative import declarative_base
from sqlalchemy.orm import sessionmaker
from dotenv import load_dotenv

#Local Stuff
from app.models import *

load_dotenv()

db_user = os.getenv("DB_USER")
db_password = os.getenv("DB_PASSWORD")
db_host = "127.0.0.1"
db_name = "study_buddy"

DATABASE_URL = f"mysql+pymysql://{db_user}:{db_password}@{db_host}/{db_name}"

engine = create_engine(DATABASE_URL)
# The session factory
SessionLocal = sessionmaker(bind=engine, autocommit=False, autoflush=False)
#Base = declarative_base()

# Dependency that provides the DB session
def get_db():
    db = SessionLocal()
    try:
        yield db
    finally:
        db.close()