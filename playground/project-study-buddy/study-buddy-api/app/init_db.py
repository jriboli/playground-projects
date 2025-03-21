from app.db.database import engine, Base

Base.metadata.create_all(engine)