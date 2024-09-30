# Use OpenJDK slim image as the base
FROM openjdk:21-jdk-slim

# Set environment variables for Kafka and Zookeeper
ENV KAFKA_VERSION=3.8.0 \
    SCALA_VERSION=2.13 \
    KAFKA_HOME=/opt/kafka

# Install necessary tools and clean up the package cache
RUN apt-get update && apt-get install -y wget gnupg \
    && wget https://downloads.apache.org/kafka/${KAFKA_VERSION}/kafka_${SCALA_VERSION}-${KAFKA_VERSION}.tgz \
    && tar -xvzf kafka_${SCALA_VERSION}-${KAFKA_VERSION}.tgz -C /opt \
    && mv /opt/kafka_${SCALA_VERSION}-${KAFKA_VERSION} ${KAFKA_HOME} \
    && rm kafka_${SCALA_VERSION}-${KAFKA_VERSION}.tgz \
    && apt-get clean && rm -rf /var/lib/apt/lists/*
	
	
RUN apt-get update && apt-get install -y dos2unix

# Set working directory for Kafka
WORKDIR $KAFKA_HOME

# Expose ports for Order service, Zookeeper, and Kafka
EXPOSE 8090 2181 9092

# Copy the OrderServer JAR file
COPY target/springKafka-0.0.1-SNAPSHOT.jar /app/OrderServer.jar


WORKDIR /usr/bin

# Copy the start-service.sh file to the container
COPY start-service.sh /usr/bin/start-service.sh


# Make the script executable
RUN chmod +x /usr/bin/start-service.sh

RUN ls -l /usr/bin/start-service.sh

# Set the entrypoint to your script that starts Zookeeper, Kafka, and your Java service
#ENTRYPOINT ["/usr/bin/start-service.sh"]
# Start an interactive shell to inspect the container
CMD ["/bin/bash"]

# Optionally, remove CMD if you have ENTRYPOINT
