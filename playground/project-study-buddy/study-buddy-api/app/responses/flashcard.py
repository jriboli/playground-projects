from pydantic import BaseModel

class FlashcardResponse(BaseModel):
    id: int
    topic: str
    package: str
    method: str
    question: str
    answer: str

    class Config:
        from_attributes = True