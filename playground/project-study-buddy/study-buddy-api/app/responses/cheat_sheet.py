from pydantic import BaseModel

from typing import List

class CheatSheetRecord(BaseModel):
    id: int
    topic: str
    package: str
    method: str
    description: str
    code_example: str

    class Config:
        from_attributes = True


class CheatSheetResponse(BaseModel):
    records: List[CheatSheetRecord]