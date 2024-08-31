# Use an official OpenJDK runtime as a parent image
FROM openjdk:21-jdk

# Set the working directory in the container
WORKDIR /app

# Copy the JAR file into the container
COPY target/configServer-0.0.1-SNAPSHOT.jar /app/configServer.jar

# Expose the port the Eureka Server runs on
EXPOSE 8888

# Run the application
ENTRYPOINT ["java", "-jar", "configServer.jar"]

