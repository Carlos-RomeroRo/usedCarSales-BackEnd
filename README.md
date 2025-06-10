🚗 UsedCarSales API

This project is a RESTful API built with Spring Boot for managing the sale of used vehicles. It provides full functionality for handling users, cars, purchase orders, roles, and a shopping cart system.

🌟 General Objective

Develop a complete RESTful API that enables the management of used car sales, including:

Management of users and roles

Administration of vehicles

Processing of purchase orders

Implementation of shopping cart functionality

Filters, advanced search, and sorting

Security with authentication and authorization

Uploading and displaying of images

Report generation



⚙️ Initial Configuration

1. 🔐 application.properties File

You need to create the application.properties file at the following path:

src/main/resources/application.properties

✨ You can use the application.properties.txt file included in the project as a reference.

2. 🐳 docker-compose.yml File

This file is required to run the PostgreSQL database (or other services). Create a docker-compose.yml file in the root directory of the project.

✨ You can use the docker-compose.yml.txt file included in the project as a reference.

🚀 Running the Project

Once everything is configured:

# Start the database and services with Docker
docker-compose up -d

# Run the Spring Boot application
./gradlew bootRun

🧪 Technologies Used

Java 17+

Spring Boot

Spring Data JPA

PostgreSQL

Docker & Docker Compose

Lombok

Gradle

Thymeleaf (if using views)

Security (JWT or another mechanism)

