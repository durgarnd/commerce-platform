## Commerce Platform – Microservices Reference Application

This repository contains a production-style **Spring Boot microservices architecture** demonstrating service discovery, centralized configuration, synchronous and asynchronous communication, and API Gateway routing.

---

#  Architecture Overview

```
Client (Postman / UI)
        |
        v
    API Gateway (Spring Cloud Gateway)
        |
-------------------------------------------------
| Order Service | Inventory Service | Payment Service |
-------------------------------------------------
                        |
                     Kafka
                        |
                Notification Service
```

### Core Components
| Component | Purpose |
|-----------|---------|
| Config Server | Centralized configuration (Git-backed) |
| Eureka Server | Service discovery and registration |
| API Gateway | Single entry point and routing |
| Order Service | Orchestrates order flow |
| Inventory Service | Manages stock (H2 + JPA) |
| Payment Service | Simulates payment processing |
| Notification Service | Kafka consumer for order events |
| Kafka | Async messaging backbone |

---

# 🛠️ Tech Stack

- Java 17
- Spring Boot 3.5.x
- Spring Cloud 2024.x
- Spring Cloud Config Server
- Netflix Eureka
- Spring Cloud Gateway
- Spring Kafka
- Resilience4j
- H2 Database
- Maven 3.9+
- Docker (for Kafka)

---

# 📂 Repository Structure

```
commerce-platform/
 ├── config-server
 ├── eureka-server
 ├── api-gateway
 ├── order-service
 ├── inventory-service
 ├── payment-service
 ├── notification-service
 ├── docker (Kafka + Zookeeper)
 └── config-repo (external Git repo)
```

---

# 🚀 How to Run the Application

## ✅ 1. Start Config Server

```bash
cd config-server
mvn spring-boot:run
```

Config repo must be available and configured in `application.properties`.

---

## ✅ 2. Start Eureka Server

```bash
cd eureka-server
mvn spring-boot:run
```

Verify:
```
http://localhost:8761
```

---

## ✅ 3. Start Kafka (Docker)

```bash
cd docker
docker-compose up -d
```

Verify containers:
```bash
docker ps
```

---

## ✅ 4. Start Microservices (in order)

```bash
cd inventory-service
mvn spring-boot:run

cd payment-service
mvn spring-boot:run

cd order-service
mvn spring-boot:run

cd notification-service
mvn spring-boot:run
```

---

## ✅ 5. Start API Gateway

```bash
cd api-gateway
mvn spring-boot:run
```

---

# 🧪 How to Test the System

## 🔹 Test Inventory Service Directly

```bash
curl "http://localhost:8083/inventory/check?productCode=P100&quantity=1"
```

Expected:
```
true / false
```

---

## 🔹 Place Order (Direct Order Service)

```bash
curl -X POST http://localhost:8081/orders \
  -H "Content-Type: application/json" \
  -d '{
        "productCode": "P100",
        "quantity": 1,
        "amount": 500
      }'
```

Expected:
```
CONFIRMED | OUT_OF_STOCK | PAYMENT_FAILED
```

---

## 🔹 Place Order via API Gateway (Recommended)

```bash
curl -X POST http://localhost:8080/api/orders \
  -H "Content-Type: application/json" \
  -d '{
        "productCode": "P100",
        "quantity": 1,
        "amount": 500
      }'
```

---

## 🔹 Verify Notification Service

Check logs:

```
EMAIL SENT: Order confirmed → ORD-xxxx
EMAIL SENT: Order failed → ORD-xxxx
```

---

# 🧾 Inventory Data (H2 Database)

Inventory is stored in H2 using JPA.

### H2 Console
```
http://localhost:8083/h2-console
JDBC URL: jdbc:h2:mem:inventorydb
```

Sample data is loaded via `data.sql`:

```sql
INSERT INTO product_inventory (product_code, quantity) VALUES ('P100', 10);
INSERT INTO product_inventory (product_code, quantity) VALUES ('P200', 5);
```

---

# 🔍 Service Discovery

All services register with Eureka.

Verify:
```
http://localhost:8761
```

You should see:
- API-GATEWAY
- ORDER-SERVICE
- INVENTORY-SERVICE
- PAYMENT-SERVICE
- NOTIFICATION-SERVICE

---

# 📡 Kafka Topics Used

| Topic | Producer | Consumer |
|--------|-----------|-----------|
| order-confirmed | Order Service | Notification Service |
| order-failed | Order Service | Notification Service |

---

# 🧠 Key Features Demonstrated

- Centralized configuration (Spring Cloud Config)
- Service discovery (Eureka)
- Synchronous communication (Feign)
- Asynchronous messaging (Kafka)
- API Gateway routing
- JPA + H2 persistence
- Resilience4j retry and timeout
- Event-driven notification service

---

# ⚠️ Known Limitations

- Saga compensation (inventory rollback on payment failure) is not implemented
- Distributed tracing and rate limiting are not enabled
- Security (JWT / OAuth2) is not implemented
- In-memory Kafka & H2 used for local development

---

# 📈 Future Enhancements

- Saga pattern for distributed transactions
- JWT authentication at API Gateway
- OpenTelemetry distributed tracing
- Centralized logging (ELK / Grafana)
- Kubernetes deployment
- MySQL/PostgreSQL persistence

---

# 👨‍💻 Author Notes

This project is designed as a **reference microservices architecture** for learning and understand the basic configuration . It demonstrates real-world enterprise patterns using modern Spring Boot and Spring Cloud.

---

# 🏁 Quick Start Summary

```bash
# Start infra
config-server
eureka-server
kafka (docker)

# Start services
inventory-service
payment-service
order-service
notification-service
api-gateway

# Test via Gateway
curl -X POST http://localhost:8080/api/orders
```

---

If you have issues, check:
- Eureka registration
- Config Server logs
- Kafka container status
- H2 inventory data

---

