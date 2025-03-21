from pydantic import BaseModel

class CheatSheetResponse(BaseModel):
    id: int
    topic: str
    package: str
    method: str
    description: str
    code_example: str

    class Config:
        from_attributes = True