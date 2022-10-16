FROM openjdk:11-jdk-slim

VOLUME /tmp

ARG JAR_FILE=build/libs/app.jar

ADD ${JAR_FILE} app.jar
COPY answers.csv /

ENTRYPOINT ["java","-jar","/app.jar"]

