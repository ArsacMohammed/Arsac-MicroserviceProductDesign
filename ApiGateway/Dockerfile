# Use an official OpenJDK runtime as a parent image
FROM openjdk:21-jdk

# Set the working directory in the container
WORKDIR /app

# Copy the JAR file into the container
COPY target/ApiGateway-0.0.1-SNAPSHOT.jar /app/ApiGatewayServer.jar

# Expose the port the Eureka Server runs on
EXPOSE 9090

# Run the application
ENTRYPOINT ["java", "-jar", "ApiGatewayServer.jar"]

