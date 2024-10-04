# Spring Boot File Processing with Redis Streams and Cache

This project demonstrates a Spring Boot application designed to process 20,000 files in parallel using Redis Streams for task allocation (queue) and Redis Cache (using Hash) for storing processing data. The application can be run in multiple instances to enable parallel processing across distributed environments.

## Features
- **Redis Streams** for task distribution among multiple instances.
- **Redis Cache** (using Hash data structure) for storing file metadata and intermediate processing data.
- **Parallel Processing**: Multiple instances of the application consume tasks from Redis Streams and process them concurrently.
- **Scalable**: The application can be scaled horizontally by running additional instances.

## Prerequisites
To run this application, you'll need the following:
- Java 11 or higher
- Maven 3.x
- Redis server (running locally )
- Docker (for running multiple instances, optional)

## Technologies Used
- **Spring Boot**: Backend framework.
- **Spring Data Redis**: Redis integration.
- **Redis Streams**: Task allocation/queue.
- **Redis Cache**: For storing metadata using the hash data structure.
- **Lettuce Redis Client**: Redis client for connection management.
- **Docker**: To run multiple instances in parallel.
