import pandas as pd
import numpy as np
import json

from sklearn.metrics.pairwise import cosine_similarity
from sklearn.feature_extraction.text import TfidfVectorizer
from sqlalchemy.orm import Session

from schemas import ProductBase
from models import Product, User


categorys = {}

def get_taste(target, columns, products):
    for question in target[0]:
        if question == -1:
            return []

    product_df = pd.read_sql(products.statement, products.session.bind)
    taste_data = product_df[columns]

    sim_scores = list(enumerate(cosine_similarity(target, taste_data)[0]))
    sim_scores = sorted(sim_scores, key=lambda x: x[1], reverse=True)
    sim_scores = sim_scores[:10]

    sool_indices = [idx[0] for idx in sim_scores]
    result = product_df.iloc[sool_indices].sample(3)

    result_json = result.to_json(orient="records")
    return json.loads(result_json)


def get_today(user, target, columns, products):
    def get_taste_score(target, columns):
        for question in target[0]:
            if question == -1:
                return {}
        taste_data = product_df[columns]

        taste_scores = dict(enumerate(cosine_similarity(target, taste_data)[0]))
        return taste_scores

    product_df = pd.read_sql(products.statement, products.session.bind)
    
    if len(user.products) <= 5:
        return []
        
    keywords = set()
    for product in user.products:
        keywords |= set(product.keyword.split(', '))

    target_data =  pd.DataFrame({'keyword': [', '.join(keywords)]})
    search_data = pd.concat([target_data, product_df], ignore_index = True)
    tfidf = TfidfVectorizer()
    tfidf_matrix = tfidf.fit_transform(search_data['keyword'].fillna(value=''))

    cosine_sim = cosine_similarity(tfidf_matrix, tfidf_matrix)
    taste_score = get_taste_score(target, columns)
    sim_scores = dict(enumerate(cosine_sim[0]))

    for key, value in taste_score.items():
        sim_scores[key] += value

    sim_scores = sorted(sim_scores.items(), key=lambda x: x[1], reverse=True)
    sim_scores = sim_scores[:10]

    sool_indices = [idx[0] for idx in sim_scores]
    result = product_df.iloc[sool_indices].sample(3)

    result_json = result.to_json(orient="records")
    return json.loads(result_json)


def get_usertype(user_type, user_q1, user_q2, user_q3, user_q4, user_q5, user_q6):
    if user_type == '탁주':
        target = [[
            user_q1,
            user_q2,
            user_q3,
            user_q4,
            user_q5,]]
        columns = ['단맛', '바디감', '탄산', '담백함', '신맛']

    elif user_type == '증류주':
        target = [[
            user_q1,
            user_q2,
            user_q3,
            user_q4,
            user_q5,]]
        columns = ['바디감', '고소함', '풍부함', '자극적인 맛', '향긋함']

    elif user_type == '약주, 과실주':
        target = [[
            user_q1,
            user_q2,
            user_q4,
            user_q5,
            user_q6,]]
        columns = ['단맛', '탄산', '신맛', '바디감', '쓴맛']

    result = {
        'type': 0,
        'analysis': {
            'labels': columns,
            'data': target[0],
            'max': '',
            'min': ''
        }
    }

    max_value = -1
    max_cnt = 0
    max_name = ''
    for idx, value in enumerate(target[0]):
        if value > max_value:
            max_cnt = 1
            max_value = value
            max_name = columns[idx]
        elif value == max_value:
            max_cnt += 1
            if max_cnt > 2:
                max_name = '다양한 맛'
            else:
                max_name += ', ' + columns[idx]
    
    min_value = 10
    min_cnt = 0
    min_name = ''
    for idx, value in enumerate(target[0]):
        if value < min_value:
            min_cnt = 1
            min_value = value
            min_name = columns[idx]
        elif value == min_value:
            min_cnt += 1
            if min_cnt > 2:
                min_name = '다양한 맛'
            else:
                min_name += ', ' + columns[idx]

    if user_type == '탁주':
        type1 = (target[0][0]+target[0][1]+target[0][3]) / 3
        type4 = (target[0][2]+target[0][4]) / 2
        if type1 >= type4:
            result['type'] = 1
        else:
            result['type'] = 4

    elif user_type == '약주, 과실주':
        type2 = (target[0][3]+target[0][4]) / 2
        type5 = (target[0][0]+target[0][1]+target[0][2]) / 3
        if type2 >= type5:
            result['type'] = 2
        else:
            result['type'] = 5
    elif user_type == '증류주':
        type3 = (target[0][1]+target[0][2]) / 2
        type6 = (target[0][0]+target[0][3]+target[0][4]) / 3
        if type3 >= type6:
            result['type'] = 3
        else:
            result['type'] = 6

    if (max_cnt == 5 or min_cnt == 5):
        if min_value > 2:
            result['type'] = 7
            result['analysis']['max'] = '당신은 이미 전통주의 달인입니다.'
        
        else:
            result['type'] = 8
            result['analysis']['min'] = '아직까지는 전통주가 어려워요.'
    else:
        result['analysis']['max'] = max_name + '을 즐기고,'
        result['analysis']['min'] = min_name + '을 낯설어 합니다.'
    return result