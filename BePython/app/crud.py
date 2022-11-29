from sqlalchemy.orm import Session
from typing import List, Optional
from  sqlalchemy.sql.expression import func

from models import Product, User, Shop
from recommend import get_taste, get_today, get_usertype


def get_products(db: Session, types: Optional[List]=None, alcohol: Optional[float]=None, isAward: Optional[bool]=None, user_id: Optional[int]=None, search: Optional[str]=None):
    products = db.query(Product)

    if user_id != None:
        result = {}
        user = db.query(User).filter(User.id == user_id).first()
        if user.type == '탁주':
            target = [[
                user.question1,
                user.question2,
                user.question3,
                user.question4,
                user.question5,]]
            columns = ['sweet', 'weight', 'carbonic', 'plain', 'acidity']
            products = products \
                .filter((Product.type == '탁주') | (Product.type == '탁주 기타주류'))

        elif user.type == '증류주':
            target = [[
                user.question1,
                user.question2,
                user.question3,
                user.question4,
                user.question5,]]
            columns = ['body', 'nutty', 'richness', 'spicy', 'flavor']
            products = products \
                .filter((Product.type == '리큐르') | (Product.type == '증류식소주') | (Product.type == '일반증류주') | (Product.type == '증류주 기타주류'))
        
        elif user.type == '약주, 과실주':
            target = [[
                user.question1,
                user.question2,
                user.question3,
                user.question4,
                user.question5,
                user.question6,
                user.question6,]]
            columns = ['sweet', 'carbonic', 'plain', 'acidity', 'body', 'tannin', 'bitter']
            products = products \
                .filter((Product.type == '약주') | (Product.type == '약청주 기타주류') | (Product.type == '과실주') | (Product.type == '과실주 기타주류') | (Product.type == '청주'))

        result['taste'] = get_taste(target, columns, products)
        result['today'] = get_today(user, target, columns, products)
        result['award'] = products.filter(Product.award).order_by(func.rand())[:3]
        result['usertype'] = get_usertype(user.type, user.question1, user.question2, user.question3, user.question4, user.question5, user.question6)

    else:
        products = products.filter(Product.alcohol < alcohol)
        if isAward:
            products = products.filter(Product.award)

        if types:
            tmp = set_filter(products, types[0])
            for type in types[1:]:
                tmp = tmp.union(set_filter(products, type))
            products = tmp

        if search:
            products = products.filter(Product.name.like(f"%{search}%"))

        products = products.order_by(Product.like_num.desc())
        result = products.all()
    
    return result


def get_product(db: Session, product_id: int):
    product = db.query(Product).filter(Product.id == product_id).first()
    return product


def search_products(db: Session, keyword: str):
    search = f"%{keyword}%"
    print(search)
    return db.query(Product).filter(Product.name.like(search)).all()


def get_recommendation(db: Session, user: object, keywords: Optional[str]=None):
    products = db.query(Product)
    result = {'taste': [], 'today': [], 'usertype':{'type':0, 'analysis': {}}}
    result['award'] = products.filter(Product.award).order_by(func.rand())[:3]

    if user['type'] == '탁주':
        target = [[
            user['question1'],
            user['question2'],
            user['question3'],
            user['question4'],
            user['question5'],]]
        columns = ['sweet', 'weight', 'carbonic', 'plain', 'acidity']   
        typed_products = products \
            .filter((Product.type == '탁주') | (Product.type == '탁주 기타주류'))
    
    elif user['type'] == '약주, 과실주':
        target = [[
            user['question1'],
            user['question2'],
            user['question3'],
            user['question4'],
            user['question5'],
            user['question6'],
            user['question6']]]
        columns = ['sweet', 'carbonic', 'plain', 'acidity', 'body', 'tannin', 'bitter']
        typed_products = products \
            .filter((Product.type == '약주') | (Product.type == '약청주 기타주류') | (Product.type == '과실주') | (Product.type == '과실주 기타주류') | (Product.type == '청주'))
    
    elif user['type'] == '증류주':
        target = [[
            user['question1'],
            user['question2'],
            user['question3'],
            user['question4'],
            user['question5'],]]
        columns = ['body', 'nutty', 'richness', 'spicy', 'flavor']
        typed_products = products \
            .filter((Product.type == '리큐르') | (Product.type == '증류식소주') | (Product.type == '일반증류주') | (Product.type == '증류주 기타주류'))
    else:
        return result
    
    result['taste'] = get_taste(target, columns, typed_products)
    result['usertype'] = get_usertype(user['type'], user['question1'], user['question2'], user['question3'], user['question4'], user['question5'], user['question6'])

    return result


def set_filter(products: Session, keyword:str):
    if keyword == '탁주':
        typed_products = products \
            .filter((Product.type == '탁주') | (Product.type == '탁주 기타주류'))
    elif keyword == '약주, 과실주':
        typed_products = products \
            .filter((Product.type == '약주') | (Product.type == '약청주 기타주류') | (Product.type == '과실주') | (Product.type == '과실주 기타주류') | (Product.type == '청주'))
    elif keyword == '소주':
        typed_products = products \
            .filter((Product.type == '리큐르') | (Product.type == '증류식소주') | (Product.type == '일반증류주') | (Product.type == '증류주 기타주류'))
    else:
        return None
    
    return typed_products