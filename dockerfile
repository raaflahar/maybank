# Tahap build
FROM maven:3.9.6-eclipse-temurin-21 AS builder
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

# Tahap runtime
FROM openjdk:21-jdk-slim
WORKDIR /app
COPY --from=builder /app/target/maybank-banking-platform.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]
