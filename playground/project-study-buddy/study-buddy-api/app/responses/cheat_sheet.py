from pydantic import BaseModel, ConfigDict

from typing import List

class CheatSheetRecordSchema(BaseModel):
    id: int
    topic: str
    package: str
    method: str
    description: str
    code_example: str

    model_config = ConfigDict(from_attributes=True)


class CheatSheetResponse(BaseModel):
    records: List[CheatSheetRecordSchema]