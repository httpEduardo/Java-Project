# Java Product & Category Management System

![Java](https://img.shields.io/badge/Java-11%2B-orange?style=flat-square&logo=java)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-2.x-brightgreen?style=flat-square&logo=spring)
![License](https://img.shields.io/badge/License-MIT-blue.svg?style=flat-square)
![Database](https://img.shields.io/badge/Database-MySQL-blue?style=flat-square&logo=mysql)

## Overview

This is a Java-based product and category management system built with Spring Boot framework. The application provides a RESTful API for managing products and categories with built-in authentication and authorization using Spring Security. It includes database operations for hierarchical category structures and product management with search capabilities.

## Features

- **Category Management**: Create and manage hierarchical categories with parent-child relationships
- **Product Management**: Full CRUD operations for products with category associations
- **Product Search**: Advanced search functionality with name filtering and category-based queries
- **Authentication & Authorization**: Secure endpoints with Spring Security using BCrypt password encryption
- **Database Integration**: MySQL database with proper foreign key relationships
- **Pagination Support**: Built-in pagination for large datasets
- **Spring Data JPA**: Repository pattern implementation for database operations

## Prerequisites

Before running this application, ensure you have the following installed:

- **Java JDK 11 or higher** - [Download here](https://www.oracle.com/java/technologies/downloads/)
- **MySQL 8.0 or higher** (or PostgreSQL 10+) - [Download MySQL](https://dev.mysql.com/downloads/)
- **Maven 3.6+** or **Gradle 6.0+** - For dependency management and building
- **Git** - For cloning the repository

## Installation

### 1. Clone the Repository

```bash
git clone https://github.com/httpEduardo/Java-Project.git
cd Java-Project
```

### 2. Set Up the Database

Create a new database in MySQL:

```sql
CREATE DATABASE product_management;
USE product_management;
```

Run the SQL schema file to create the required tables:

```bash
mysql -u your_username -p product_management < CREATE\ TABLE\ category\ \(.sql
```

Or manually execute the SQL commands:

```sql
CREATE TABLE category (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL,
    parent_id BIGINT,
    FOREIGN KEY (parent_id) REFERENCES category(id)
);

CREATE TABLE product (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL,
    description TEXT,
    price DECIMAL(10,2) NOT NULL,
    available BOOLEAN DEFAULT TRUE,
    category_id BIGINT,
    FOREIGN KEY (category_id) REFERENCES category(id)
);
```

### 3. Build the Project

Using Maven:
```bash
mvn clean install
```

Using Gradle:
```bash
gradle build
```

## Project Structure

```
Java-Project/
├── CREATE TABLE category (.sql  # Database schema definition
├── Untitled-2.java             # Spring Security configuration
├── Untitled-3.java             # Product repository with search methods
└── README.md                   # Project documentation
```

### File Descriptions

- **CREATE TABLE category (.sql**: Contains the database schema for creating `category` and `product` tables with proper relationships
- **Untitled-2.java**: Spring Security configuration with in-memory authentication setup (admin user with BCrypt password encryption)
- **Untitled-3.java**: Spring Data JPA repository method for searching products by name and category with pagination support

## Database Setup

### Schema Overview

The application uses two main tables:

#### Category Table
- `id`: Primary key (BIGINT, auto-increment)
- `name`: Category name (VARCHAR 255, NOT NULL)
- `parent_id`: Reference to parent category for hierarchical structure (BIGINT, optional)

#### Product Table
- `id`: Primary key (BIGINT, auto-increment)
- `name`: Product name (VARCHAR 255, NOT NULL)
- `description`: Product description (TEXT, optional)
- `price`: Product price (DECIMAL 10,2, NOT NULL)
- `available`: Availability status (BOOLEAN, default TRUE)
- `category_id`: Reference to category (BIGINT, optional)

### Relationships
- Categories can have parent-child relationships (hierarchical structure)
- Products belong to a specific category (many-to-one relationship)

## Configuration

### Database Connection

Create an `application.properties` file in `src/main/resources/`:

```properties
# Database Configuration
spring.datasource.url=jdbc:mysql://localhost:3306/product_management
spring.datasource.username=your_username
spring.datasource.password=your_password
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

# JPA Configuration
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL8Dialect
spring.jpa.properties.hibernate.format_sql=true

# Server Configuration
server.port=8080
```

### Authentication

The application comes with a pre-configured admin user:

- **Email**: `admin@qima.com`
- **Password**: `admin123`

The password is encrypted using BCrypt before storage.

## Usage

### Running the Application

Using Maven:
```bash
mvn spring-boot:run
```

Using Gradle:
```bash
gradle bootRun
```

Or run the compiled JAR:
```bash
java -jar target/<your-jar-file-name>.jar
```

### API Endpoints

The application provides RESTful endpoints for:

- **Category Management**: Create, read, update, delete categories
- **Product Management**: Create, read, update, delete products
- **Product Search**: Search products by name and category with pagination

Example API usage:

```bash
# Login to get authentication token
curl -X POST http://localhost:8080/api/login \
  -H "Content-Type: application/json" \
  -d '{"email":"admin@qima.com","password":"admin123"}'

# Search products by name and category
curl -X GET "http://localhost:8080/api/products/search?name=laptop&categoryId=1&page=0&size=10" \
  -H "Authorization: Bearer YOUR_TOKEN"
```

## Code Examples

### Product Repository Usage

The `ProductRepository` interface provides advanced search capabilities:

```java
// Search products by name (case-insensitive) and category with pagination
Page<Product> findAllByNameContainingIgnoreCaseAndCategory_Id(
    String name, 
    Long categoryId, 
    Pageable pageable
);
```

### Database Connection Example

```java
import javax.sql.DataSource;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DatabaseConfig {
    
    @Bean
    public DataSource dataSource() {
        return DataSourceBuilder.create()
            .url("jdbc:mysql://localhost:3306/product_management")
            .username("your_username")
            .password("your_password")
            .build();
    }
}
```

## Contributing

Contributions are welcome! Please follow these guidelines:

1. **Fork the repository**
2. **Create a feature branch**: `git checkout -b feature/your-feature-name`
3. **Commit your changes**: `git commit -m 'Add some feature'`
4. **Push to the branch**: `git push origin feature/your-feature-name`
5. **Open a Pull Request**

### Coding Standards

- Follow Java naming conventions
- Write clean, self-documenting code
- Add comments for complex logic
- Include unit tests for new features
- Ensure all tests pass before submitting PR

## License

This project is licensed under the MIT License - see below for details:

```
MIT License

Copyright (c) 2026 Eduardo

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
```

## Support

For issues, questions, or suggestions, please open an issue on the [GitHub repository](https://github.com/httpEduardo/Java-Project/issues).

## Acknowledgments

- Spring Boot Framework
- Spring Security
- Spring Data JPA
- MySQL Database

---

**Built with ❤️ using Java and Spring Boot**
