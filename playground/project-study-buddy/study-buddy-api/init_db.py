from sqlalchemy.orm import sessionmaker
from app.db.database import engine, Base
from app.models import *  # ✅ Import all models

def initialize_database():
    print("Creating tables in the database...")
    Base.metadata.create_all(engine)  # ✅ Creates tables based on models
    print("Database initialization complete.")