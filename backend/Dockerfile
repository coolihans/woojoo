# JDK8 기반으로 도커 이미지 생성
FROM openjdk:8-jdk

# JAR_FILE 위치에 있는 jar파일을 app.jar이름의 파일로 복사
# ARG JAR_FILE=build/libs/artSider-0.0.1-SNAPSHOT.jar
ARG JAR_FILE=build/libs/backend-1.0-SNAPSHOT.jar
COPY ${JAR_FILE} app.jar

# 8181 포트 사용
EXPOSE 8181
ENTRYPOINT ["java","-jar","/app.jar"]