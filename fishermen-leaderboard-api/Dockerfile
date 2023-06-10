FROM eclipse-temurin:11-jdk-alpine

RUN addgroup -S spring && adduser -S spring -G spring
USER spring:spring

ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} /app/app.jar
WORKDIR /app

ENTRYPOINT ["java","-jar","app.jar"]