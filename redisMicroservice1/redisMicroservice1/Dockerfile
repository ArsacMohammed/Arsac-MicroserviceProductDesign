# Use an official OpenJDK runtime as a parent image
FROM openjdk:21-jdk-bullseye
# Install Maven
RUN apt-get update && apt-get install -y maven

# Set the working directory in the container
WORKDIR /app

# Copy the Maven project files
COPY pom.xml /app/

# Download the dependencies
RUN mvn dependency:go-offline

# Copy the source code
COPY src /app/src

# Build your Spring Boot application
RUN mvn clean package -DskipTests

# Copy the JAR file into the container
RUN mv target/*.jar /app/ApiGatewayServer.jar

# Expose the port the application runs on
EXPOSE 9080

# Run the application
ENTRYPOINT ["java", "-jar", "/app/ApiGatewayServer.jar"]
