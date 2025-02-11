
from fastapi import FastAPI
from backend.api.utils.db import init_db
from backend.api.routes import trials, alerts, users

# Initialize FastAPI app
app = FastAPI(title="Clinical Trials Notification API", version="1.0")

# Initialize the database (creates tables if they don't exist)
init_db()

# Include API routes
app.include_router(trials.router, prefix="/trials", tags=["Trials"])
app.include_router(alerts.router, prefix="/alerts", tags=["Alerts"])
app.include_router(users.router, prefix="/users", tags=["Users"])

# Health check endpoint
@app.get("/")
def health_check():
    return {"status": "OK", "message": "Clinical Trials API is running"}
