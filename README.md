# RideOrCry - Running the Application

This document provides instructions to download, build, and run the RideOrCry application using Docker and Docker Compose.

## Table of Contents
1. [Running with Docker](#rideorcry---running-with-docker)
2. [Running with Docker Compose](#rideorcry---running-with-docker-compose)
3. [Troubleshooting](#troubleshooting)

## References
| Method | Description |
|--------|-------------|
| **Docker** | Build and run the application as a standalone container. |
| **Docker Compose** | Run the application with a PostgreSQL database using `docker-compose`. |



# RideOrCry - Running with Docker

This guide provides instructions to download, build, and run the containerized RideOrCry application.

## Prerequisites

- [Docker](https://docs.docker.com/get-docker/) installed on your machine.

## Download the Repository

Clone the repository to your local machine:

```sh
git clone https://github.com/ihopewecanchangeitlater/RideOrCry-Backend
cd RideOrCry-Backend
```

## Build the Docker Image

To build the Docker image, run:

```sh
docker build -t ride-or-cry-server:latest .
```

## Run the Container

To start the application in a Docker container:

```sh
docker run -e DATABSE_URL=<jdbc_database_url> -e DATABASE_USER=<database_user> -e DATABASE_PASSWORD=<database_password> -p 8080:8080 ride-or-cry-server
```

### Optional Parameters
You can also use:
- SPRING_SECURITY_SECRET: Secret string word for JWT generation
- SPRING_SECURITY_EXPIRATION: Expiration time of JWT token (in miliseconds)

## Access the Application

Once the container is running, access the application at:

```
http://localhost:8080
```

## Stop the Container

To stop the running container, first find the container ID:

```sh
docker ps
```

Then stop it using:

```sh
docker stop <container_id>
```

## Clean Up

To remove the container and image after stopping it:

```sh
docker rm <container_id>
docker rmi ride-or-cry-server
```

# RideOrCry - Running with Docker Compose

This guide provides instructions to set up and run the RideOrCry application using Docker Compose.

## Prerequisites
- [Docker](https://docs.docker.com/get-docker/) installed on your machine.
- [Docker Compose](https://docs.docker.com/compose/install/) installed.

## Download the Repository
Clone the repository to your local machine:
```sh
git clone https://github.com/ihopewecanchangeitlater/RideOrCry-Backend
cd RideOrCry-Backend
```

## Start the Application
To start the application using Docker Compose, run:
```sh
docker-compose up --build -d
```
This will start two containers:
1. **PostgreSQL Database** (RideOrCry-DB)
2. **Spring Boot Application** (RideOrCry-SERVER)

## Access the Application
Once the containers are running, access the application at:
```
http://localhost:8080
```
The PostgreSQL database will be accessible on:
```
localhost:5432
```

## Stop the Application
To stop the running containers, use:
```sh
docker-compose down
```

## Clean Up
To remove the containers and associated volumes:
```sh
docker-compose down -v
```

## Troubleshooting
- Ensure ports 8080 and 5432 are available on your machine.
- Run `docker compose logs` to check logs if the application fails to start.

---
Developed with ❤️ by RideOrCry Team

