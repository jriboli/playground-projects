# config.py
import os

DATABASE_URL = os.getenv("DATABASE_URL", "sqlite:///:memory:")  # Default to in-memory SQLite for testing

# test_config.py
import os
from config import DATABASE_URL

def test_database_url():
    # Check if DATABASE_URL is set correctly for staging or production
    assert DATABASE_URL == "postgresql://user:password@localhost/staging_db"  # Example for staging
