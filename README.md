# Dependency Injection (DI) and Inversion of Control (IoC) in Spring Boot

## What is Dependency Injection (DI)?

Dependency Injection (DI) is a design pattern used to implement IoC, where the control of creating and managing dependencies is transferred from the object itself to an external container or framework. Instead of an object creating its dependencies, they are injected by an external entity, promoting loose coupling and easier testing.

### Key points:
- Objects do not create dependencies themselves.
- Dependencies are provided (injected) by an external source.
- Improves modularity and testability.

## What is Inversion of Control (IoC)?

Inversion of Control (IoC) is a broader principle where the control flow of a program is inverted compared to traditional programming. Instead of the programmer controlling the flow, the framework or container controls it.

In the context of Spring:
- The Spring container manages the lifecycle and dependencies of objects.
- Objects receive their dependencies from the container rather than constructing them.

## How Spring Boot Performs DI

Spring Boot uses the Spring Framework's IoC container to manage beans (objects). It automatically scans for components, creates instances, and injects dependencies where needed.

### Steps:
1. Spring scans the classpath for classes annotated with stereotype annotations like `@Component`, `@Service`, `@Repository`, etc.
2. It creates instances of those classes and registers them as beans in the IoC container.
3. Dependencies are injected into beans via constructor injection, setter injection, or field injection using `@Autowired`.
4. When a bean is requested, Spring provides the fully initialized object with all dependencies injected.

## What Happens When `SpringApplication.run()` Executes?

1. **Bootstrap**: Spring Boot starts the application by bootstrapping the Spring context.
2. **Component Scan**: It scans the base package and sub-packages for annotated classes.
3. **Bean Creation**: Spring creates beans for all detected components.
4. **Dependency Injection**: It injects dependencies into beans based on annotations like `@Autowired`.
5. **Application Context Ready**: The application context (IoC container) is fully initialized.
6. **Application Starts**: The main application runs, and beans are ready to be used.

## Example Code: Car and Engine

```java
@Component
public class Engine {
    public void start() {
        System.out.println("Engine started");
    }
}

@Component
public class Car {
    private final Engine engine;

    @Autowired
    public Car(Engine engine) {
        this.engine = engine;
    }

    public void drive() {
        engine.start();
        System.out.println("Car is driving");
    }
}
```

In this example:
- `Engine` and `Car` are Spring-managed beans.
- `Car` depends on `Engine`, which is injected via constructor injection.
- When `car.drive()` is called, the engine starts first.

## Key Annotations

| Annotation     | Description                                                                                 |
|----------------|---------------------------------------------------------------------------------------------|
| `@Component`   | Marks a class as a Spring-managed component (bean).                                        |
| `@Autowired`   | Marks a constructor, setter, or field to be injected with a matching bean from the context.|
| `@Primary`     | Marks a bean as the primary candidate when multiple beans of the same type exist.          |
| `@Qualifier`   | Specifies which bean to inject when multiple candidates are available.                      |

## Summary Table of DI and IoC Concepts

| Concept                  | Description                                                                                   |
|--------------------------|-----------------------------------------------------------------------------------------------|
| Dependency Injection (DI)| Pattern where dependencies are provided to objects rather than created by them.               |
| Inversion of Control (IoC)| Principle where the framework controls the flow and creation of objects instead of the program.|
| Spring IoC Container      | Manages beans and their lifecycle, performs DI automatically.                                 |
| Bean                      | An object managed by the Spring container.                                                   |
| Component Scan            | Process of detecting beans via annotations in specified packages.                            |
| Injection Types           | Constructor, Setter, Field injection using `@Autowired`.                                    |

---

Keep these notes handy for quick revision of how Spring Boot manages dependencies and the underlying principles of IoC and DI.

