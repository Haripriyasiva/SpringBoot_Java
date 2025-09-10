🏗️ Microservices Project (Customer, Order, Product) with Eureka & Gateway

This project demonstrates a Spring Boot microservices architecture with service discovery, API gateway, and centralized API documentation.

📌 Microservices

Customer Service → manages customer data (SQLite)

Order Service → manages customer orders

Product Service → manages product catalog

Eureka Server → service discovery

API Gateway → routes client requests to microservices

⚙️ Tech Stack

Java 17

Spring Boot 3.x

Spring Cloud Netflix Eureka (Service Discovery)

Spring Cloud Gateway (API Gateway)

Spring Data JPA (Persistence)

SQLite / H2 (Databases)

Swagger / Springdoc OpenAPI (API Docs)

📂 Project Structure
microservices-project/
│── customer-service/
│── order-service/
│── product-service/
│── gateway-server/   # Eureka Server
│── gateway-client/   # Spring Cloud Gateway
│── README.md

🚀 Setup & Run
1️⃣ Clone the Repository
git clone https://github.com/your-username/microservices-project.git
cd microservices-project

2️⃣ Start Eureka Server
cd gateway-server
mvn spring-boot:run


👉 Available at: http://localhost:8761

3️⃣ Start Customer, Order & Product Services
cd customer-service
mvn spring-boot:run

cd order-service
mvn spring-boot:run

cd product-service
mvn spring-boot:run


Each service registers itself with Eureka.

4️⃣ Start API Gateway
cd gateway-client
mvn spring-boot:run


👉 Gateway available at: http://localhost:8080

🔗 Service Ports (Example)
Service	Port	Eureka Name
Customer Service	8081	CUSTOMER-SERVICE
Order Service	8082	ORDER-SERVICE
Product Service	8083	PRODUCT-SERVICE
API Gateway	8080	GATEWAY
Eureka Server	8761	-
🌍 API Gateway Routes

Example application.yml for Gateway:

spring:
  cloud:
    gateway:
      routes:
        - id: customer-service
          uri: lb://CUSTOMER-SERVICE
          predicates:
            - Path=/api/customers/**
        - id: order-service
          uri: lb://ORDER-SERVICE
          predicates:
            - Path=/api/orders/**
        - id: product-service
          uri: lb://PRODUCT-SERVICE
          predicates:
            - Path=/api/products/**


Now you can call services via Gateway:

http://localhost:8080/api/customers

http://localhost:8080/api/orders

http://localhost:8080/api/products

📖 Swagger Documentation

Each service has Swagger UI:

Customer Service → http://localhost:8081/swagger-ui.html

Order Service → http://localhost:8082/swagger-ui.html

Product Service → http://localhost:8083/swagger-ui.html

(Optional: you can aggregate them behind the Gateway using Springdoc OpenAPI Aggregator.)

🛠️ Testing with Postman

Start Eureka Server & Gateway

Use Gateway URLs (http://localhost:8080/...) instead of direct service ports

Example request:

POST http://localhost:8080/api/customers
Content-Type: application/json

{
  "name": "Alice",
  "email": "alice@example.com"
}

✅ Eureka Dashboard

👉 http://localhost:8761
Shows all registered services:

CUSTOMER-SERVICE (UP)
ORDER-SERVICE    (UP)
PRODUCT-SERVICE  (UP)
