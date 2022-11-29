from typing import List, Union
import uvicorn

from fastapi import Depends, FastAPI, HTTPException, responses, Query
from fastapi_pagination import Page, add_pagination, paginate
from sqlalchemy.orm import Session
from starlette.middleware.cors import CORSMiddleware
from models import User

import crud, models, database, schemas

app = FastAPI(title='WooJoo',
            openapi_url='/fastapi/openapi.json',
            docs_url="/fastapi/docs", redoc_url=None)

origins = [
    "*"
]

app.add_middleware(
    CORSMiddleware,
    allow_origins=origins,
    allow_credentials=True,
    allow_methods=[""],
    allow_headers=[""],
)

models.Base.metadata.create_all(bind=database.engine)

origins = [
    "*"
]

app.add_middleware(
    CORSMiddleware,
    allow_origins=origins,
    allow_credentials=True,
    allow_methods=["*"],
    allow_headers=["*"],
)

def get_db():
    db = database.SessionLocal()
    try:
        yield db
    finally:
        db.close()

@app.get("/fastapi/")
def main():
    return responses.RedirectResponse(url="/fastapi/docs/")


@app.get("/fastapi/product/", tags=["data"], response_model=Page[schemas.ProductBase])
async def read_products(
    db: Session = Depends(get_db),
    types: Union[List[str], None] = Query(default=None),
    alcohol: float = 100,
    isAward: bool = False,
    search: Union[str, None] = None
):
    products = crud.get_products(db=db, types=types, alcohol=alcohol, isAward=isAward, search=search)
    return paginate(products)


@app.get("/fastapi/search", tags=["data"], response_model=Page[schemas.ProductBase])
async def search_product(keyword: str, db: Session = Depends(get_db)):
    products = crud.search_products(db, keyword=keyword)
    return paginate(products)


@app.get("/fastapi/product/{product_id}", tags=["data"], response_model=schemas.ProductDetail)
async def read_product(product_id: int, db: Session = Depends(get_db)):
    product = crud.get_product(db, product_id=product_id)
    if product is None:
        raise HTTPException(status_code=404, detail="Product not found")
    return product


@app.get("/fastapi/recommendation/{user_id}", tags=["data"], response_model=schemas.Recommendation)
async def recommend_products(
    db: Session = Depends(get_db),
    user_id: Union[int, None] = None,
):
    products = crud.get_products(db, user_id=user_id)
    return products


@app.get("/fastapi/recommendation", tags=["data"], response_model=schemas.Recommendation)
async def recommend_products_nonmember(
    type: str,
    question1: int,
    question2: int,
    question3: int,
    question4: int,
    question5: int,
    question6: int,
    db: Session = Depends(get_db),
):
    user = {
        'type': type,
        'question1': question1,
        'question2': question2,
        'question3': question3,
        'question4': question4,
        'question5': question5,
        'question6': question6,
    }
    products = crud.get_recommendation(db, user=user)
    return products


@app.get("/fastapi/test2", tags=["data"], response_model=schemas.Recommendation)
async def recommend_products_test2(
    keywords: str,
    db: Session = Depends(get_db),
):
    user = {
        'type': '탁주',
        'question1': 1,
        'question2': 1,
        'question3': 1,
        'question4': 1,
        'question5': 1,
        'question6': 1,
    }
    products = crud.get_recommendation(db, user=user, keywords=keywords)
    return products


add_pagination(app)

if __name__ == "__main__":
    uvicorn.run("main:app", host="0.0.0.0", port=8082)