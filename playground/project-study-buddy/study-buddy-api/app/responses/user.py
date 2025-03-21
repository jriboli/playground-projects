from pydantic import BaseModel

class UserResponse(BaseModel):
    id: int
    username: str
    email: str
    hashed_password: str
    is_active: str
    role: str

    class Config:
        from_attributes = True