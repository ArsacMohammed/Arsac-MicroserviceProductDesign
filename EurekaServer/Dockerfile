# Use an official OpenJDK runtime as a parent image
FROM openjdk:17-jdk-alpine

# Set the working directory in the container
WORKDIR /app

# Copy the JAR file into the container
COPY EurekaServer/EurekaServer/target/EurekaServer-0.0.1-SNAPSHOT.jar /app/EurekaServer.jar

# Expose the port the Eureka Server runs on
EXPOSE 8761

# Run the application
ENTRYPOINT ["java", "-jar", "EurekaServer.jar"]