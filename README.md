# Java Spring Batch Import data from REST API to Database

This is a Java Spring Batch project that allows you to import data from REST API into a database.

## Technology Stack

The project utilizes the following technologies and frameworks:

- Java
- Spring Boot
- Spring Batch
- Maven (for build and dependency management)
- PostgreSQL
- Docker

## Prerequisites

Before running this project, make sure you have the following prerequisites installed:

- Java JDK (version 17 or higher)
- Maven (for building and managing dependencies)
- Docker

## Setup

1. Clone the repository:

```bash
git clone https://github.com/uberlannunes/spring-batch-rest-api-to-db.git
```

2. Navigate to the project directory:
```bash
cd spring-batch-rest-api-to-db
```

3. Execute docker compose command to setup the Database
```bash
docker compose up -d
```

4. Run the Application
```bash
./mvnw spring-boot:run
```