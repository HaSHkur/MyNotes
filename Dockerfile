# ----------- Build Stage -----------
FROM eclipse-temurin:21-jdk-alpine AS build

# Set working directory inside container
WORKDIR /app

# Copy Gradle wrapper & config files first for caching
COPY gradlew .
COPY gradle gradle
COPY build.gradle settings.gradle ./

# Make wrapper executable
RUN chmod +x ./gradlew

# Copy the source code
COPY src ./src

# Build the fat jar (skip tests for faster build)
RUN ./gradlew bootJar --no-daemon -x test

# ----------- Run Stage -----------
FROM eclipse-temurin:21-jre-alpine

WORKDIR /app

# Copy the built jar from the build stage
COPY --from=build /app/build/libs/*.jar app.jar

# Expose Spring Boot default port
EXPOSE 8080

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]
