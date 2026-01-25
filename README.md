# Bookstore Microservices Platform

![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.x-green) ![Docker](https://img.shields.io/badge/Docker-Enabled-blue) ![Build](https://img.shields.io/badge/Build-Maven-orange)

A cloud-native, event-driven microservices architecture representing a complete E-Commerce Bookstore. This project demonstrates advanced Spring Boot patterns, distributed systems concepts, and a production-grade DevOps setup including Observability and CI/CD readiness.

## 🏗 System Architecture

The system is designed around a **Microservices Architecture** using **Spring Cloud Gateway** as the entry point and **RabbitMQ** for asynchronous communication between services.

![System Architecture](/docs/bookstore-spring-microservices.png)

## 🚀 Key Features & Patterns

* **Event-Driven Architecture:** Decoupled `Order` and `Notification` services using **RabbitMQ** for high-performance async messaging.
* **Infrastructure as Code:** Local development environment fully containerized using **Docker Compose** (Apps, Postgres, Broker, Keycloak, Observability).
* **Reliability & Resiliency:** Implemented **Resilience4j** (Circuit Breakers, Retries) to handle transient failures gracefully.
* **Distributed Locking:** Utilized **ShedLock** to ensure scheduled jobs run exactly once across multiple service instances.
* **Observability Pipeline:** Full monitoring stack with **Prometheus** (Metrics), **Grafana** (Dashboards), **Loki** (Logs), and **Tempo** (Distributed Tracing).
* **Modern Testing Strategy:** "Test Real Dependencies" approach using **Testcontainers** (JUnit 5 + WireMock) to spin up ephemeral infrastructure for integration tests.
* **Security:** OAuth2/OIDC implementation using **Keycloak** as the Identity Provider.

## 🛠 Tech Stack

| Category | Technologies |
| :--- | :--- |
| **Core Framework** | Java 21, Spring Boot 3.x, Spring Cloud |
| **Data & Storage** | PostgreSQL, Spring Data JPA, Flyway (Migrations) |
| **Messaging** | RabbitMQ (Spring AMQP) |
| **Gateway & Net** | Spring Cloud Gateway, RestClient (Declarative Interface) |
| **Security** | Spring Security OAuth2, Keycloak |
| **Frontend** | Thymeleaf, Alpine.js, Bootstrap (Server-Side Rendered) |
| **Observability** | Grafana, Prometheus, Loki, Tempo, OpenTelemetry |
| **Testing** | JUnit 5, Testcontainers, RestAssured, Awaitility, WireMock |

## 📦 Service Breakdown

| Service | Description | Key Tech |
| :--- | :--- | :--- |
| **API Gateway** | Single entry point routing requests to internal services. Aggregates Swagger documentation. | Spring Cloud Gateway |
| **Catalog Service** | Manages book inventory and product data. | REST API, Postgres |
| **Order Service** | Handles order placement and publishes `OrderCreatedEvent` to the broker. | OAuth2, RabbitMQ, Resilience4j |
| **Notification Service** | Consumer service that listens to events and triggers user alerts (Email/SMS simulation). | RabbitMQ Listener |
| **Bookstore WebApp** | Customer-facing UI for browsing books and managing orders. | Thymeleaf, Alpine.js |
| **Auth Server** | Centralized Identity and Access Management. | Keycloak |

## 🧪 Testing Strategy

This project moves away from mocking everything. We use **Testcontainers** to ensure that integration tests run against **real** database and message broker instances.

```java
@Testcontainers
class OrderIntegrationTest {
    
    @Container
    static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:15");
    
    @Container
    static RabbitMQContainer rabbitMQ = new RabbitMQContainer("rabbitmq:3-management");

    @Test
    void shouldCreateOrderSuccessfully() {
        // This runs against a real Dockerized Postgres & RabbitMQ
    }
}
```

## 📍 Development Roadmap
- [x] **Catalog Service:** REST APIs and Postgres integration
- [x] **Service Discovery:** Docker Compose setup
- [ ] **Order Service:** RabbitMQ integration (In Progress)
- [ ] **Security:** Keycloak integration (Pending)
- [ ] **Observability:** Grafana/Prometheus setup (Pending)

## 🔧 Getting Started

### Prerequisites
* Java 21+
* Docker & Docker Compose

### Run the Application
You can spin up the entire infrastructure (Databases, Broker, Tracing, Apps) with a single command:

```bash
# 1. Build the microservices
./mvnw clean package -DskipTests

# 2. Start the infrastructure and services
docker-compose up -d

```
Once running, access the applications:
* **Web App:** [http://localhost:8080](http://localhost:8080)
* **Keycloak Console:** [http://localhost:9090](http://localhost:9090)
* **Grafana:** [http://localhost:3000](http://localhost:3000)
* **RabbitMQ Dashboard:** [http://localhost:15672](http://localhost:15672)
* **MailHog Dashboard:** [http://localhost:8025(http://localhost:8025)

  
## 📝 Credits & Inspiration
This project is based on the Spring Boot Microservices Complete Tutorial by **SivaLabs**.
I am building this project to apply my 9+ years of Java experience to a modern Microservices context, focusing specifically on:
1.  **Infrastructure as Code** with Docker & Testcontainers.
2.  **Asynchronous Messaging** patterns with RabbitMQ.
3.  **Modern CI/CD** practices.

