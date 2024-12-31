

# Modern Spring Boot Application Starter

This repository provides a modern and professional starter template for building Spring Boot applications with **user authentication**, **2FA**, **JWT**, **phone verification**, and more. It includes essential configurations for **user authentication**, **caching**, **data persistence**, and **input validation**, which are commonly required for modern web applications.

## Key Features:
- **Spring Security**: Secure user authentication and authorization with JWT.
- **Redis Integration**: Caching and session management using Redis.
- **MySQL Integration**: Persistent data storage with MySQL.
- **Spring Data JPA**: Simplified database operations with JPA repositories.
- **Spring Boot Starter Validation**: Input validation for DTOs using Java Bean Validation.
- **2FA (Two-Factor Authentication)**: Adds an additional layer of security.
- **Phone Verification**: SMS-based verification to ensure user identity.

## Prerequisites

Before starting with the application, ensure you have the following prerequisites:

- **JDK 17** or higher
- **Maven** for dependency management
- **MySQL** server for database persistence
- **Redis** server for caching (can be local or using a cloud service like RedisLabs)

## Setting up the Project

### 1. Clone the Repository

Clone this repository to your local machine:

```bash
git clone https://github.com/your-repository/modern-spring-boot-app.git
cd modern-spring-boot-app
```

### 2. Configure `application.properties`

In the `src/main/resources/application.properties` file, you need to set the following configurations:

#### MySQL Configuration:
Ensure your MySQL server is running and accessible, then add the following configuration:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/your_database_name
spring.datasource.username=your_mysql_user
spring.datasource.password=your_mysql_password
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.jpa.hibernate.ddl-auto=update
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL8Dialect
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true
```

#### Redis Configuration:
If you're using a Redis instance locally, add:

```properties
spring.redis.host=localhost
spring.redis.port=6379
spring.redis.password=your_redis_password (if applicable)
```

If you are using a cloud Redis service, replace with your provided connection details.

#### Spring Security Configuration:
This template uses Spring Security for authentication. Add the following properties to configure the security behavior:

```properties
# Basic configuration for Spring Security
spring.security.user.name=admin
spring.security.user.password=admin_password
```

### 3. Add Dependencies (Optional)

Ensure you have the required dependencies in your `pom.xml` for MySQL, Redis, Spring Security, and validation:

```xml
<dependencies>
    <!-- Spring Boot Starter Web -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-web</artifactId>
    </dependency>
    
    <!-- Spring Boot Starter Data JPA -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-data-jpa</artifactId>
    </dependency>

    <!-- MySQL Driver -->
    <dependency>
        <groupId>mysql</groupId>
        <artifactId>mysql-connector-java</artifactId>
    </dependency>

    <!-- Spring Boot Starter Redis -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-data-redis</artifactId>
    </dependency>

    <!-- Spring Boot Starter Security -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-security</artifactId>
    </dependency>

    <!-- Spring Boot Starter Validation -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-validation</artifactId>
    </dependency>
</dependencies>
```

### 4. Build and Run the Application

Once all configurations are complete, you can build and run the application using Maven:

```bash
mvn clean install
mvn spring-boot:run
```

The application will start, and the server will be available at `http://localhost:8080` (or another port if configured).

## Key Application Components

### Security

The application uses **Spring Security** for user authentication with **JWT**. The default user credentials are:

- **Username**: `admin`
- **Password**: `admin_password`

### MySQL Database

This application uses MySQL as the relational database for persistence. The database schema will be created automatically using JPA, based on the entities defined in the application.

### Redis Caching

Redis is integrated to provide caching for various operations. This improves performance by caching frequently accessed data.

### User Management

The application includes **user registration**, **login**, and **management features**, all backed by Spring Security and MySQL. Users can be created, updated, and deleted.

### Validation

Input validation is handled using Spring's `@Valid` annotation in conjunction with the `spring-boot-starter-validation` dependency, ensuring that user inputs are properly validated.

### 2FA and Phone Verification

This project also incorporates **two-factor authentication (2FA)** and **phone verification** using SMS, enhancing the security of user accounts.

## Guides

The following guides illustrate how to use some features concretely:

- [Building a RESTful Web Service](https://spring.io/guides/gs/rest-service/)
- [Serving Web Content with Spring MVC](https://spring.io/guides/gs/serving-web-content/)
- [Building REST services with Spring](https://spring.io/guides/tutorials/rest/)
- [Accessing Data with JPA](https://spring.io/guides/gs/accessing-data-jpa/)
- [Accessing data with MySQL](https://spring.io/guides/gs/accessing-data-mysql/)
- [Messaging with Redis](https://spring.io/guides/gs/messaging-redis/)
- [Validation](https://spring.io/guides/gs/validating-form-input/)

## Additional Resources

For more detailed Spring Boot documentation, please visit the official [Spring Boot Documentation](https://spring.io/projects/spring-boot).

## License

This project is licensed under the **MIT License** - see the [LICENSE](LICENSE) file for details.

