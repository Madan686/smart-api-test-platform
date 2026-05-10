# Smart API Test Automation Platform

A full-stack Java Spring Boot application for creating, executing, validating, and monitoring API test cases through a web-based dashboard.

The platform allows users to dynamically configure API requests, execute external/internal APIs, validate responses, store execution history, and monitor test analytics from a centralized interface.

---

# Features

## API Test Case Management
- Create API test cases
- Configure HTTP methods (GET, POST, PUT, DELETE)
- Define API URLs
- Add custom request headers
- Add JSON request bodies
- Define expected status codes
- Define expected response keywords

---

## Dynamic API Execution Engine
- Execute APIs dynamically using WebClient
- Support external and internal APIs
- Runtime request generation
- Dynamic header injection
- JSON body handling
- Real HTTP response handling

---

## Validation Engine
- Compare expected vs actual status codes
- Validate response body keywords
- Generate PASS/FAIL execution results
- Exception handling for failed requests

---

## Execution History
- Store all execution results in MySQL
- Save response body and execution metadata
- Track historical API executions

---

## Analytics Dashboard
- Total test cases
- Total executions
- Passed executions
- Failed executions
- Pass percentage
- Recent execution history

---

# Tech Stack

## Backend
- Java 21
- Spring Boot
- Spring MVC
- Spring Data JPA
- Hibernate
- WebClient (Spring WebFlux)

---

## Frontend
- Thymeleaf
- HTML5
- CSS3

---

## Database
- MySQL

---

## Build Tool
- Maven

---

# System Architecture

```text
Browser
   ↓
Controller Layer
   ↓
Service Layer
   ↓
Repository Layer
   ↓
MySQL Database
```

---

# Execution Workflow

```text
User
↓
Create Test Case
↓
Store Test Configuration
↓
Run Test
↓
WebClient Executes API
↓
Response Captured
↓
Validation Engine
↓
PASS / FAIL Decision
↓
Execution History Stored
↓
Dashboard Analytics Updated
```

---

# Project Structure

```text
smart-api-test-platform
│
├── src/main/java/com/smartapi
│
├── controller
│   ├── HomeController.java
│   ├── ApiTestCaseController.java
│   └── DashboardController.java
│
├── service
│   ├── ApiTestCaseService.java
│   ├── TestExecutionService.java
│   └── DashboardService.java
│
├── repository
│   ├── ApiTestCaseRepository.java
│   └── TestExecutionResultRepository.java
│
├── entity
│   ├── ApiTestCase.java
│   └── TestExecutionResult.java
│
├── dto
├── config
├── exception
│
├── resources
│   ├── templates
│   │   ├── home.html
│   │   ├── dashboard.html
│   │   └── test-cases
│   │       ├── form.html
│   │       ├── list.html
│   │       └── result.html
│   │
│   ├── static
│   └── application.properties
│
└── pom.xml
```

---

# Database Schema

## api_test_cases

| Column | Type |
|---|---|
| id | BIGINT |
| name | VARCHAR |
| method | VARCHAR |
| url | VARCHAR |
| headers | TEXT |
| request_body | TEXT |
| expected_status_code | INT |
| expected_response_keyword | VARCHAR |
| created_at | DATETIME |

---

## test_execution_results

| Column | Type |
|---|---|
| id | BIGINT |
| test_case_name | VARCHAR |
| request_url | VARCHAR |
| expected_status_code | INT |
| actual_status_code | INT |
| execution_status | VARCHAR |
| response_body | TEXT |
| error_message | TEXT |
| executed_at | DATETIME |

---

# Installation & Setup

## 1. Clone Repository

```bash
git clone <your-repository-url>
cd smart-api-test-platform
```

---

## 2. Create MySQL Database

Open MySQL Workbench and run:

```sql
CREATE DATABASE smart_api_test_db;
```

---

## 3. Configure application.properties

File:

```text
src/main/resources/application.properties
```

Configuration:

```properties
spring.application.name=smart-api-test-platform

spring.datasource.url=jdbc:mysql://localhost:3306/smart_api_test_db
spring.datasource.username=root
spring.datasource.password=YOUR_MYSQL_PASSWORD

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true

server.port=8080
```

Replace:

```text
YOUR_MYSQL_PASSWORD
```

with your actual MySQL password.

---

## 4. Install Dependencies

```bash
mvn clean install
```

---

## 5. Run Application

```bash
mvn spring-boot:run
```

---

# Access Application

## Home Page

```text
http://localhost:8080/
```

---

## Test Cases

```text
http://localhost:8080/test-cases
```

---

## Create Test Case

```text
http://localhost:8080/test-cases/new
```

---

## Dashboard

```text
http://localhost:8080/dashboard
```

---

# Example API Test Case

## GET Request Example

| Field | Value |
|---|---|
| Name | Get Posts |
| Method | GET |
| URL | https://jsonplaceholder.typicode.com/posts/1 |
| Expected Status | 200 |
| Expected Keyword | userId |

---

## POST Request Example

| Field | Value |
|---|---|
| Name | Create Post |
| Method | POST |
| URL | https://jsonplaceholder.typicode.com/posts |
| Headers | {"Content-Type":"application/json"} |
| Request Body | {"title":"Java Automation","body":"Spring Boot Testing","userId":1} |
| Expected Status | 201 |
| Expected Keyword | id |

---

# Core Concepts Implemented

- MVC Architecture
- Layered Backend Design
- Repository Pattern
- Dependency Injection
- ORM with Hibernate
- Dynamic HTTP Execution
- Runtime Request Building
- API Validation Engine
- Analytics Dashboard
- Exception Handling
- MySQL Persistence

---

# Future Enhancements

- JWT Authentication
- Test Collections
- Scheduled Test Execution
- Email Alerts
- Export PDF Reports
- REST API Version
- JSON Schema Validation
- Docker Deployment
- CI/CD Integration

---

# Learning Outcomes

This project demonstrates practical backend engineering concepts including:

- Spring Boot Application Architecture
- Database Modeling
- HTTP Client Integration
- Dynamic Workflow Design
- API Automation Logic
- Validation Systems
- Service Layer Architecture
- Analytics & Reporting

---

# Author

Madan