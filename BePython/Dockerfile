# 파이썬 최신 이미지 빌드
FROM tiangolo/uvicorn-gunicorn:python3.9

# root에 app 폴더 생성
# RUN mkdir -p /bepython

# work dir 고정
WORKDIR /BePython

# host pc의 main.py , requirements.txt 를 아래 경로에 복사
COPY ./requirements.txt /BePython/requirements.txt

# # java stuff
# RUN apt-get update 
#     apt-get install -y curl openjdk-8-jdk

# install python packages
RUN pip3 install --upgrade pip
RUN pip3 install --no-cache-dir --upgrade -r /BePython/requirements.txt

# RUN cd /code && \ 
#     curl -s https://raw.githubusercontent.com/konlpy/konlpy/master/scripts/mecab.sh | bash -s
# #
COPY ./app /BePython/app

EXPOSE 8082

# container 실행 시 자동으로 실행 할 command
#  uvicorn을 사용하여 main.py의 app을 실행시킵니다.

CMD ["python", "app/main.py"]
