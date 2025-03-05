from fastapi import FastAPI, HTTPException
from pydantic import BaseModel

app = FastAPI()


class Item(BaseModel):
    text: str  #required - no default value
    is_done: bool = False


items = []


@app.get("/")
def root():
    return {"Hello": "World"}


@app.get("/items")
def list_items(limit: int = 10):
    return items[0:limit]


@app.post("/items", response_model=list[Item])
def create_item(item: Item):
    if items.__contains__(item):
        raise HTTPException(status_code=404, detail="Item already exists")
    else:
        items.append(item)
        return items


@app.get("/items/{item_id}", response_model=Item)
def get_item(item_id: int) -> Item:
    if item_id < len(items):
        return items[item_id]
    else:
        raise HTTPException(status_code=404, detail="Item not found")
