from typing import List, Union
from datetime import date

from pydantic import BaseModel


class FoodBase(BaseModel):
    id: int

    class Config:
        orm_mode = True


class Food(FoodBase):
    name: str
    img: Union[str, None] = None


class MakerBase(BaseModel):
    id: int

    class Config:
        orm_mode = True


class Maker(MakerBase):
    name: str
    official_name: str
    address: str
    ceo_name: str


class UserBase(BaseModel):
    id: int
    nickname: str


class Shop(BaseModel):
    id: int
    image: str
    mall_name: str
    name: str
    price: int
    url: str

    class Config:
        orm_mode = True


class ProductBase(BaseModel):
    id: int
    name: str
    image: str
    cutted_img: Union[str, None] = None
    type: str
    volume: str
    alcohol: float
    like_num: int
    price: int
    
    class Config:
        orm_mode = True


class User(UserBase):
    email: str
    gender: str
    img: str
    birthdate: date
    question1: int
    question2: int
    question3: int
    question4: int
    question5: int
    question6: int
    type: str
    gosu: bool
    review_count: Union[int, None] = None
    
    class Config:
        orm_mode = True


class ProductDetail(ProductBase):
    subtext: Union[str, None] = None
    award: Union[str, None] = None
    foods: List[Food]
    makers: List[Maker]
    shops: List[Shop]

    acidity: int
    weight: int
    bitter: int
    body: int
    carbonic: int
    flavor: int
    nutty: int
    plain: int
    richness: int
    spicy: int
    sweet: int
    tannin: int
    taste_standard: str

    fruit_category: Union[int, None] = None
    flower_category: Union[int, None] = None
    herb_category: Union[int, None] = None
    nut_category: Union[int, None] = None
    other_category: Union[int, None] = None
    situation_category: Union[int, None] = None

    anju_note: Union[str, None] = None
    other_note: Union[str, None] = None
    storage_note: Union[str, None] = None
    taste_note: Union[str, None] = None

    distinction: Union[str, None] = None
    ingredient: Union[str, None] = None


class UserType(BaseModel):
    type: int
    analysis: object


class Recommendation(BaseModel):
    taste: Union[List[ProductBase], None] = None
    today: Union[List[ProductBase], None] = None
    award: List[ProductBase]
    usertype: UserType
