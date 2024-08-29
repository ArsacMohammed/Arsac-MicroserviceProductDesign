# Use an official OpenJDK runtime as a parent image
FROM openjdk:21-jdk

# Set the working directory in the container
WORKDIR /app

# Copy the JAR file into the container
COPY target/EurekaServer-0.0.1-SNAPSHOT.jar /app/EurekaServer.jar

# Expose the port the Eureka Server runs on
EXPOSE 8761

# Run the application
ENTRYPOINT ["java", "-jar", "EurekaServer.jar"]

