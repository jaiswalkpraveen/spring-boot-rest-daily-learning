# 3-Day SDET → SDE Intensive Roadmap

## 📊 Current Skills Assessment

### ✅ What You Already Know
- **Layered Architecture**: Controller → Service → Repository
- **Entity Modeling**: JPA entities with `@Entity`, `@Table`, `@Column`
- **One-to-One Mapping**: Bidirectional relationship with cascade
- **DTO Pattern**: Request/Response DTOs with MapStruct
- **Validation**: Bean Validation with shared constraints interface
- **Dependency Injection**: Constructor-based DI
- **Basic CRUD**: GET, POST, PUT, DELETE endpoints

### ⚠️ Gaps in Current Repo
1. **No exception handling** (returns generic `RuntimeException`)
2. **No tests** (test directory is empty)
3. **Hardcoded database config** (credentials in `application.yml`)
4. **No logging** (no SLF4J usage)
5. **Missing relationship types** (One-to-Many, Many-to-Many)
6. **No pagination/sorting** for `getAllUsers()`
7. **No API documentation** (Swagger/OpenAPI)
8. **No security** (authentication/authorization)

---

## 🗓️ 3-Day Learning Plan

### **Day 1: Database Relationships & Advanced JPA**
**Goal**: Master all JPA relationship types and query optimization

#### Morning (4 hours)
**1. One-to-Many Relationship** (2 hours)
- **Concept**: One user can have multiple orders
- **Implementation**:
  - Create `Order` entity with `@ManyToOne` to `User`
  - Add `@OneToMany` in `User` for `List<Order>`
  - Learn `fetch = FetchType.LAZY` vs `EAGER`
  - Handle `@JsonManagedReference` / `@JsonBackReference` to avoid circular serialization
- **Practice**: Build `/users/{id}/orders` endpoint

**2. Many-to-Many Relationship** (2 hours)
- **Concept**: Users can have multiple roles, roles can belong to multiple users
- **Implementation**:
  - Create `Role` entity
  - Use `@ManyToMany` with `@JoinTable`
  - Understand owning vs inverse side
  - Add/remove roles from user
- **Practice**: Build `/users/{id}/roles` endpoints

#### Afternoon (4 hours)
**3. Custom Queries & JPQL** (2 hours)
- **Learn**:
  - `@Query` annotation with JPQL
  - Named parameters (`:name`) vs positional (`?1`)
  - Native SQL queries with `nativeQuery = true`
  - Projections (returning DTOs directly from queries)
- **Practice**:
  - Find users by age range
  - Search users by name (case-insensitive)
  - Get user count by role

**4. Pagination & Sorting** (2 hours)
- **Learn**:
  - `Pageable` and `PageRequest`
  - `Sort` with multiple fields
  - Return `Page<UserResponse>` instead of `List<UserResponse>`
- **Practice**: Refactor `getAllUsers()` to support pagination

---

### **Day 2: Production-Ready Features**
**Goal**: Add error handling, logging, testing, and API docs

#### Morning (4 hours)
**1. Global Exception Handling** (1.5 hours)
- **Learn**:
  - `@ControllerAdvice` and `@ExceptionHandler`
  - Custom exception classes (`UserNotFoundException`, `ValidationException`)
  - Standardized error response DTO
  - HTTP status codes (404, 400, 500, 409)
- **Practice**: Replace all `RuntimeException` with proper exceptions

**2. Logging** (1 hour)
- **Learn**:
  - SLF4J with Logback (comes with Spring Boot)
  - Log levels (TRACE, DEBUG, INFO, WARN, ERROR)
  - Structured logging (log request IDs, user IDs)
- **Practice**: Add logging to service methods

**3. Unit Testing** (1.5 hours)
- **Learn**:
  - JUnit 5 basics
  - Mockito for mocking dependencies
  - `@Mock`, `@InjectMocks`, `@ExtendWith(MockitoExtension.class)`
  - Test service layer (mock repository)
- **Practice**: Write tests for `UserServiceImpl.createUser()`

#### Afternoon (4 hours)
**4. Integration Testing** (2 hours)
- **Learn**:
  - `@SpringBootTest` for full context
  - `@WebMvcTest` for controller-only tests
  - `MockMvc` for testing endpoints
  - `@DataJpaTest` for repository tests
  - H2 in-memory database for tests
- **Practice**: Write integration test for `POST /users/create`

**5. API Documentation (Swagger)** (2 hours)
- **Learn**:
  - Add `springdoc-openapi-starter-webmvc-ui` dependency
  - `@Operation`, `@ApiResponse` annotations
  - Customize Swagger UI
- **Practice**: Document all user endpoints
- **Access**: `http://localhost:8080/swagger-ui.html`

---

### **Day 3: Advanced Topics & Best Practices**
**Goal**: Security, profiles, caching, and deployment readiness

#### Morning (4 hours)
**1. Spring Security Basics** (2.5 hours)
- **Learn**:
  - Add `spring-boot-starter-security`
  - Basic authentication (username/password)
  - JWT tokens (stateless authentication)
  - `@PreAuthorize` for role-based access
  - CORS configuration
- **Practice**:
  - Protect endpoints (only admins can delete users)
  - Add `/auth/login` endpoint returning JWT

**2. Application Profiles** (1.5 hours)
- **Learn**:
  - `application-dev.yml`, `application-prod.yml`
  - Environment-specific configs (DB URLs, log levels)
  - `@Profile` annotation for beans
  - Externalize secrets (use environment variables)
- **Practice**: Create dev/prod profiles with different DB configs

#### Afternoon (4 hours)
**3. Caching** (1.5 hours)
- **Learn**:
  - `@EnableCaching` and `@Cacheable`
  - Cache providers (Caffeine, Redis)
  - `@CacheEvict` for invalidation
- **Practice**: Cache `getUserById()` results

**4. Actuator & Monitoring** (1 hour)
- **Learn**:
  - Spring Boot Actuator endpoints (`/actuator/health`, `/metrics`)
  - Custom health indicators
  - Prometheus integration
- **Practice**: Expose metrics for user creation count

**5. Docker & Deployment** (1.5 hours)
- **Learn**:
  - Write `Dockerfile` for Spring Boot app
  - Multi-stage builds (Maven + JRE)
  - `docker-compose.yml` with MySQL
- **Practice**: Run app in Docker container

---

## 📚 Recommended Learning Resources

### Official Docs
- [Spring Data JPA Reference](https://docs.spring.io/spring-data/jpa/docs/current/reference/html/)
- [Spring Boot Testing](https://docs.spring.io/spring-boot/docs/current/reference/html/features.html#features.testing)
- [Spring Security](https://docs.spring.io/spring-security/reference/index.html)

### Hands-On Tutorials
- [Baeldung Spring Boot Series](https://www.baeldung.com/spring-boot)
- [Spring Guides](https://spring.io/guides)

---

## 🎯 Daily Practice Goals

### Day 1 Deliverables
- [ ] `Order` entity with One-to-Many to `User`
- [ ] `Role` entity with Many-to-Many to `User`
- [ ] Custom query: Find users by age range
- [ ] Paginated `GET /users/all` endpoint

### Day 2 Deliverables
- [ ] `@ControllerAdvice` with custom exceptions
- [ ] Logging in all service methods
- [ ] 5+ unit tests for `UserService`
- [ ] 3+ integration tests for `UserController`
- [ ] Swagger UI accessible at `/swagger-ui.html`

### Day 3 Deliverables
- [ ] JWT-based authentication
- [ ] Role-based access control (admin-only delete)
- [ ] Dev/prod profiles with environment variables
- [ ] Cached `getUserById()`
- [ ] Dockerized app running with MySQL

---

## 🚀 Post-3-Day Next Steps

### Week 2 Topics
1. **Microservices**: Spring Cloud, service discovery (Eureka), API Gateway
2. **Message Queues**: RabbitMQ/Kafka integration
3. **Advanced Testing**: TestContainers, contract testing
4. **Performance**: Database indexing, N+1 query problems, connection pooling

### Week 3 Topics
1. **CI/CD**: GitHub Actions, Jenkins pipelines
2. **Observability**: ELK stack (Elasticsearch, Logstash, Kibana)
3. **Advanced Security**: OAuth2, SAML
4. **Event-Driven Architecture**: Event sourcing, CQRS

---

## 💡 Tips for Maximizing Your 3 Days

1. **Code along, don't just read**: Type every example yourself
2. **Break after each topic**: 10-minute breaks prevent burnout
3. **Use Git commits**: Commit after each feature (easy to roll back)
4. **Ask questions**: Use Stack Overflow, ChatGPT, or Spring community forums
5. **Build a mini-project**: By Day 3, create a simple e-commerce API (Products, Orders, Users)
6. **Review daily**: Spend 30 minutes each evening reviewing what you learned

---

## 📝 Assessment Checklist (End of Day 3)

Can you confidently:
- [ ] Explain the difference between `@OneToMany` and `@ManyToOne`?
- [ ] Write a custom JPQL query with pagination?
- [ ] Create a `@ControllerAdvice` exception handler?
- [ ] Mock a repository in a unit test?
- [ ] Secure an endpoint with JWT?
- [ ] Deploy a Spring Boot app in Docker?

If you answer "yes" to all, you're ready for junior SDE interviews! 🎉

---

## 🔗 Next Conversation Starters

After completing this roadmap, ask me:
- "Help me implement One-to-Many relationship for User and Orders"
- "Show me how to write integration tests for my UserController"
- "Guide me through adding JWT authentication"
- "Help me dockerize this application"

Good luck with your SDET → SDE transition! 🚀
