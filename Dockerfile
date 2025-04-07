# ======= Build Stage =======
FROM maven:3.9.4-eclipse-temurin-17 AS builder

# Set working directory inside builder container
WORKDIR /app

# Copy all files and download dependencies
COPY . .

# Build the project and create the JAR
RUN mvn clean install -DskipTests

# ======= Runtime Stage =======
FROM openjdk:17-jdk-slim

WORKDIR /app

# Copy the JAR file from the builder stage
COPY --from=builder /app/target/CJV805-Assignment-2-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]