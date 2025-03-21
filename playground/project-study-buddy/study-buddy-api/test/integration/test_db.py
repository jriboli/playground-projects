import pytest

# Local Stuff
from app.main import app
from app.db.database import SessionLocal, get_db
from app.models import CheatSheet

# Create a new session for testing
@pytest.fixture
def db():
    db = SessionLocal()
    try:
        yield db
    finally:
        db.close()

def test_create_cheatsheet(db):
    new_cheat_sheet = CheatSheet(topic="Test Topic", package="Test Package", method="test_method", description="Test Description", code_example="Test Code")
    db.add(new_cheat_sheet)
    db.commit()
    db.refresh(new_cheat_sheet)
    assert new_cheat_sheet.id is not None
    assert new_cheat_sheet.topic == "Test Topic"
    db.close()
