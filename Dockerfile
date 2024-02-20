FROM openjdk:17-alpine
LABEL maintainer="ahmismail.c@stc.com.sa"
WORKDIR /usr/local/bin/
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} demo-service.jar
EXPOSE 2222
ENTRYPOINT ["java","-jar","-Dspring.profiles.active=prod","demo-service.jar"]
