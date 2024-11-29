# Stage 1: Build the Maven project
FROM maven:3.9.4-eclipse-temurin-21 AS build
WORKDIR /app

# Copy the entire project into the container
COPY . .

# Build the project using Maven
RUN mvn clean package -DskipTests

# Stage 2: Run the application
FROM openjdk:21-slim AS runtime
WORKDIR /app

# Copy the JAR file from the build stage
COPY --from=build /app/target/*.jar app.jar

# Command to run the JAR file
CMD ["java", "-jar", "app.jar"]