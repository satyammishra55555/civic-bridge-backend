# 🏛️ Civic Bridge Backend

A scalable Java Spring Boot backend application built using Microservices architecture for managing citizen service requests and administrative workflows.

## 🚀 Features

* 🔐 JWT Authentication & Role-Based Authorization
* 👥 User & Role Management
* 📝 Citizen Request Management
* 📄 Application Processing
* 📡 RESTful APIs
* ⚡ Apache Kafka Integration
* 🛡️ Global Exception Handling
* ✅ Request Validation
* 📑 Swagger API Documentation
* 🗄️ MySQL Database Integration
* 🐳 Docker Support
* ☁️ AWS Ready
* 🧪 Unit Testing with JUnit & Mockito

---

## 🛠️ Tech Stack

| Category         | Technologies         |
| ---------------- | -------------------- |
| Language         | Java 17              |
| Framework        | Spring Boot 3        |
| Security         | Spring Security, JWT |
| Architecture     | Microservices        |
| Database         | MySQL                |
| ORM              | Hibernate, JPA       |
| Messaging        | Apache Kafka         |
| Build Tool       | Maven                |
| Documentation    | Swagger / OpenAPI    |
| Containerization | Docker               |
| Cloud            | AWS                  |
| Version Control  | Git & GitHub         |

---

## 📂 Project Structure

```text
src
├── main
│   ├── java
│   │   ├── controller
│   │   ├── service
│   │   ├── repository
│   │   ├── entity
│   │   ├── dto
│   │   ├── security
│   │   ├── config
│   │   ├── exception
│   │   └── util
│   └── resources
│       ├── application.properties
│       └── data.sql
└── test
```

---

## ⚙️ Prerequisites

* Java 17
* Maven 3.9+
* MySQL 8+
* IntelliJ IDEA
* Git

---

## ▶️ Getting Started

### Clone Repository

```bash
git clone https://github.com/satyammishra55555/civic-bridge-backend.git
```

### Navigate to Project

```bash
cd civic-bridge-backend
```

### Configure Database

Update the database configuration in `application.properties`.

### Run the Application

```bash
mvn spring-boot:run
```

Application will start on:

```text
http://localhost:8080
```

---

## 📡 Sample API Endpoints

| Method | Endpoint             | Description      |
| ------ | -------------------- | ---------------- |
| POST   | `/api/auth/login`    | User Login       |
| POST   | `/api/auth/register` | Register User    |
| GET    | `/api/requests`      | Get All Requests |
| POST   | `/api/requests`      | Create Request   |
| PUT    | `/api/requests/{id}` | Update Request   |
| DELETE | `/api/requests/{id}` | Delete Request   |

---

## 🔒 Authentication

The application uses **JWT (JSON Web Token)** for secure authentication and authorization.

Include the JWT token in every protected request:

```text
Authorization: Bearer <your_jwt_token>
```

---

## 📈 Future Enhancements

* Email Notifications
* Redis Caching
* CI/CD Pipeline
* Kubernetes Deployment
* Monitoring with Prometheus & Grafana

---

## 👨‍💻 Author

**Satyam Mishra**

* GitHub: https://github.com/satyammishra55555

---

⭐ If you found this project useful, don't forget to give it a Star!
