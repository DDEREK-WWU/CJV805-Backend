# Use a slim OpenJDK 17 image
FROM openjdk:17-jdk-slim

# Set working directory inside the container
WORKDIR /app

# Copy the compiled jar file into the image
COPY target/CJV805-Assignment-2-0.0.1-SNAPSHOT.jar app.jar

# Optional: expose the default Spring Boot port
EXPOSE 8080

# Run the JAR
ENTRYPOINT ["java", "-jar", "app.jar"]