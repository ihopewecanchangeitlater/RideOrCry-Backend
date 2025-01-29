# Use an official maven OpenJDK runtime as the base image
FROM maven:3.8.4-openjdk-17 AS build

# Set the working directory inside the container
WORKDIR /app

# Copy the project files
COPY . .

# Build the application
RUN mvn -f ./pom.xml clean package -Dmaven.test.skip=true

# Use a smaller runtime image for the final stage
FROM openjdk:17-jdk-slim

# Set the working directory
WORKDIR /app

# Copy the built JAR file from the build stage
COPY --from=build /app/target/*.jar app.jar

# Expose the port the app runs on
EXPOSE 8080

# Command to run the application
CMD ["java", "-jar", "app.jar"]
