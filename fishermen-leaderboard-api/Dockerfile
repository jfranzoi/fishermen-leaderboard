FROM maven:3-eclipse-temurin-11-alpine as builder

WORKDIR /home
COPY pom.xml .
COPY src src

RUN mvn clean package -DskipTests

FROM eclipse-temurin:11-jdk-alpine

RUN addgroup -S spring && adduser -S spring -G spring
USER spring:spring

COPY --from=builder /home/target/*.jar /app/app.jar
WORKDIR /app

ENTRYPOINT ["java","-jar","app.jar"]