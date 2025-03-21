# app/errors/exception_handlers.py
from fastapi import HTTPException
from fastapi.responses import JSONResponse
from fastapi.exceptions import RequestValidationError
from sqlalchemy.exc import SQLAlchemyError
from fastapi.requests import Request

# Custom handler for RequestValidationError (e.g., failed Pydantic model validation)
async def validation_exception_handler(request: Request, exc: RequestValidationError):
    return JSONResponse(
        status_code=400,
        content={"message": f"Validation error: {exc.errors()}"}
    )

# Custom handler for general SQLAlchemy database errors
async def sqlalchemy_exception_handler(request: Request, exc: SQLAlchemyError):
    return JSONResponse(
        status_code=500,
        content={"message": "Database error", "details": str(exc)}
    )

# Generic exception handler for unexpected errors
async def general_exception_handler(request: Request, exc: Exception):
    return JSONResponse(
        status_code=500,
        content={"message": "Internal Server Error", "details": str(exc)}
    )
