from sqlalchemy import create_engine
from sqlalchemy.ext.declarative import declarative_base
from sqlalchemy.orm import sessionmaker
from dotenv import load_dotenv
import os 

load_dotenv()

DBUSER = os.environ.get('DBUSER')
DBPASSWORD = os.environ.get('DBPASSWORD')
DBPORT = os.environ.get('DBPORT')
DBNAME = os.environ.get('DBNAME')
MYSQL_URL = f'mysql+pymysql://{DBUSER}:{DBPASSWORD}@j7a304.p.ssafy.io:{DBPORT}/{DBNAME}'
engine = create_engine(MYSQL_URL, encoding='utf-8')
SessionLocal = sessionmaker(autocommit=False, autoflush=False, bind=engine)

Base = declarative_base()