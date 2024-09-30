#!/bin/bash


#!/bin/bash
# Convert this script to Unix-style line endings if necessary
dos2unix "$0"

# Start Zookeeper
echo "Starting Zookeeper..."
${KAFKA_HOME}/bin/zookeeper-server-start.sh ${KAFKA_HOME}/config/zookeeper.properties &

# Wait for Zookeeper to start
sleep 10

# Start Kafka
echo "Starting Kafka..."
${KAFKA_HOME}/bin/kafka-server-start.sh ${KAFKA_HOME}/config/server.properties &

# Wait for Kafka to start
sleep 10

# Start Eureka Server
echo "Starting Order Server..."
java -jar /app/OrderServer.jar
