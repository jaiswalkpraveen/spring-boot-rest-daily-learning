# Spring Boot REST API - Daily Learning Project

A production-ready REST API demonstrating core Spring Boot concepts including JPA relationships, validation, exception handling, and MapStruct integration.

## 📋 Table of Contents
- [Overview](#overview)
- [Features](#features)
- [Tech Stack](#tech-stack)
- [Project Structure](#project-structure)
- [Database Schema](#database-schema)
- [API Endpoints](#api-endpoints)
- [Getting Started](#getting-started)
- [Learning Resources](#learning-resources)

## 🎯 Overview

This project is a comprehensive learning experience covering Spring Boot REST API development from basics to production-level features. It implements a User management system with Orders, demonstrating:
- **One-to-One Relationship**: User ↔ UserDetails
- **One-to-Many Relationship**: User → Orders
- **DTO Pattern** with MapStruct for clean separation
- **Bean Validation** for request validation
- **Transaction Management** with `@Transactional`

## ✨ Features

### Implemented
- ✅ **CRUD Operations** for Users and Orders
- ✅ **JPA Relationships** (One-to-One, One-to-Many)
- ✅ **DTO/Entity Mapping** using MapStruct
- ✅ **Validation** with Bean Validation API
- ✅ **Custom JPQL Queries** (search, filter, pagination support)
- ✅ **Transaction Management**

### In Progress
- 🚧 **Global Exception Handler** (theory documented)
- 🚧 **Many-to-Many Relationship** (User ↔ Roles)

### Planned
- 📝 Logging with SLF4J
- 📝 Unit & Integration Tests
- 📝 Spring Security + JWT
- 📝 API Documentation (Swagger/OpenAPI)
- 📝 Caching
- 📝 Docker containerization

## 🛠️ Tech Stack

| Category | Technology |
|----------|-----------|
| **Framework** | Spring Boot 3.5.7 |
| **Language** | Java 21 |
| **Database** | MySQL 8.0 |
| **ORM** | Spring Data JPA (Hibernate) |
| **Mapper** | MapStruct 1.5.5 |
| **Validation** | Jakarta Bean Validation |
| **Build Tool** | Maven |
| **Utilities** | Lombok |

## 📁 Project Structure

```
src/main/java/com/example/demo/
├── AppConfig.java                 # Main Spring Boot application
├── controller/
│   ├── UserController.java        # User REST endpoints
│   └── OrderController.java       # Order REST endpoints
├── dto/
│   ├── request/
│   │   ├── UserRequest.java       # User creation/update DTO
│   │   └── OrderRequest.java      # Order creation DTO
│   └── response/
│       ├── UserResponse.java      # User API response
│       └── OrderResponse.java     # Order API response
├── entity/
│   ├── User.java                  # User JPA entity
│   ├── UserDetails.java           # UserDetails JPA entity (1:1)
│   ├── Order.java                 # Order JPA entity (N:1)
│   └── Role.java                  # Role JPA entity (N:M - in progress)
├── exception/
│   ├── ErrorResponse.java         # Standard error response DTO
│   └── ResourceNotFoundException.java # Custom 404 exception
├── mapper/
│   └── UserMapper.java            # MapStruct interface
├── repository/
│   ├── UserRepository.java        # User data access
│   ├── UserDetailsRepository.java # UserDetails data access
│   ├── OrderRepository.java       # Order data access
│   └── RoleRepository.java        # Role data access
├── service/
│   ├── UserService.java           # User business logic interface
│   ├── OrderService.java          # Order business logic interface
│   └── impl/
│       ├── UserServiceImpl.java   # User service implementation
│       └── OrderServiceImpl.java  # Order service implementation
└── validation/
    ├── UserConstraints.java       # Shared validation interface
    └── OrderConstraint.java       # Order validation interface
```

## 🗄️ Database Schema

```sql
┌─────────────┐       ┌──────────────┐       ┌─────────────┐
│   users     │       │ user_details │       │   orders    │
├─────────────┤       ├──────────────┤       ├─────────────┤
│ id (PK)     │◄─────►│ id (PK)      │   ┌───│ id (PK)     │
│ name        │ 1:1   │ user_id (FK) │   │   │ user_id (FK)│
│ age         │       │ address      │   │   │ product     │
└─────────────┘       │ phone        │   │   │ amount      │
                      └──────────────┘   │   │ order_date  │
                                         │   └─────────────┘
                                         │        N:1
                                         └────────┘
```

### Relationships
- **User** ↔ **UserDetails**: One-to-One (bidirectional, `cascade = ALL`)
- **User** → **Orders**: One-to-Many (bidirectional, `cascade = ALL`, `orphanRemoval = true`)
- **Order** → **User**: Many-to-One (LAZY fetch)

## 🔌 API Endpoints

### User Management

| Method | Endpoint | Description | Request Body | Response |
|--------|----------|-------------|--------------|----------|
| GET | `/users/all` | Get all users | - | `List<UserResponse>` |
| GET | `/users/{id}` | Get user by ID | - | `UserResponse` |
| POST | `/users/create` | Create new user | `UserRequest` | `UserResponse` |
| PUT | `/users/update/{id}` | Update user | `UserRequest` | `UserResponse` |
| DELETE | `/users/delete/{id}` | Delete user | - | - |
| GET | `/users/age-range/{minAge}/{maxAge}` | Get users by age range | - | `List<UserResponse>` |

**UserRequest Example:**
```json
{
  "name": "John Doe",
  "age": 30,
  "address": "123 Main St",
  "phone": "555-1234"
}
```

### Order Management

| Method | Endpoint | Description | Request Body | Response |
|--------|----------|-------------|--------------|----------|
| GET | `/users/{userId}/orders` | Get all orders for a user | - | `List<OrderResponse>` |
| GET | `/users/{userId}/orders/{orderId}` | Get specific order | - | `OrderResponse` |
| POST | `/users/{userId}/orders` | Create order for user | `OrderRequest` | `OrderResponse` |
| DELETE | `/users/{userId}/orders/{orderId}` | Delete order | - | - |

**OrderRequest Example:**
```json
{
  "product": "Laptop",
  "amount": 1200.50
}
```

## 🚀 Getting Started

### Prerequisites
- Java 21+
- Maven 3.6+
- MySQL 8.0+

### Database Setup
```sql
CREATE DATABASE spring_boot_learning;
```

Update `src/main/resources/application.yml`:
```yaml
spring:
  datasource:
    url: jdbc:mysql://localhost:3306/spring_boot_learning
    username: your_username
    password: your_password
```

### Run Application
```bash
# Clone repository
git clone https://github.com/jaiswalkpraveen/spring-boot-rest-daily-learning.git
cd spring-boot-rest-daily-learning

# Build project
mvn clean install

# Run application
mvn spring-boot:run
```

The API will be available at `http://localhost:8080`

### Test Endpoints
```bash
# Create a user
curl -X POST http://localhost:8080/users/create \
-H "Content-Type: application/json" \
-d '{"name":"Alice","age":25,"address":"NYC","phone":"555-0001"}'

# Get all users
curl http://localhost:8080/users/all

# Create an order
curl -X POST http://localhost:8080/users/1/orders \
-H "Content-Type: application/json" \
-d '{"product":"Laptop","amount":1200.50}'
```

## 📚 Learning Resources

This repository includes comprehensive learning materials:

- **[3_DAY_INTENSIVE_ROADMAP.md](./3_DAY_INTENSIVE_ROADMAP.md)** - 3-day plan for SDET → SDE transition
- **[LEARNING_PATH.md](./LEARNING_PATH.md)** - 4-6 week comprehensive learning path
- **[SPRING_BOOT_ANNOTATIONS.md](./SPRING_BOOT_ANNOTATIONS.md)** - Categorized list of all annotations used
- **[ORDER_CREATION_FLOW.md](./ORDER_CREATION_FLOW.md)** - Visual diagram of order creation flow
- **[GLOBAL_EXCEPTION_HANDLER_THEORY.md](./GLOBAL_EXCEPTION_HANDLER_THEORY.md)** - Exception handling guide

## 🎓 Key Concepts Demonstrated

### 1. Dependency Injection & IoC
Spring Boot's IoC container manages beans and injects dependencies automatically.

### 2. MapStruct Integration
Compile-time, type-safe object mapping:
```java
@Mapper(componentModel = "spring")
public interface UserMapper {
    UserResponse toUserResponse(User user);
    User toEntity(UserRequest request);
}
```

### 3. Validation with Shared Interfaces
```java
public interface UserConstraints {
    @NotBlank(message = "Name is required")
    String getName();
    
    @NotNull @Positive
    Integer getAge();
}
```

### 4. JPA Relationships
- **One-to-One**: Bidirectional with `mappedBy`
- **One-to-Many**: Parent-child with cascade operations
- Helper methods to maintain bidirectional consistency

### 5. Transaction Management
```java
@Transactional
public class OrderServiceImpl {
    // All operations within a single transaction
}
```

## 📝 Annotations Reference

### Core Annotations
- `@SpringBootApplication` - Main application entry point
- `@RestController` - REST API controller
- `@Service` - Business logic layer
- `@Repository` - Data access layer
- `@Transactional` - Transaction boundary

### JPA Annotations
- `@Entity`, `@Table` - Entity mapping
- `@Id`, `@GeneratedValue` - Primary key
- `@OneToOne`, `@OneToMany`, `@ManyToOne` - Relationships
- `@JoinColumn`, `@JoinTable` - Foreign keys

### Validation
- `@Valid` - Trigger validation
- `@NotNull`, `@NotBlank` - Required fields
- `@Positive` - Numeric constraints

### MapStruct
- `@Mapper` - Define mapper interface
- `@Mapping` - Configure field mappings
- `@MappingTarget` - Update existing objects

## 🤝 Contributing

This is a learning project. Feel free to fork and experiment!

## 📄 License

This project is for educational purposes.

## 👤 Author

**Praveen Jaiswal**  
GitHub: [@jaiswalkpraveen](https://github.com/jaiswalkpraveen)

---

*Built with ❤️ while learning Spring Boot*
