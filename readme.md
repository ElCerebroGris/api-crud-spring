# CRUD API with Spring 3.3.3 and Java 21

This is a basic REST API for Create, Read, Update, and Delete (CRUD) operations, built using Spring Framework 3.3.3, Java 21, and Maven as the build tool. It can be extended to manage any resource by modifying the entity and repository layers.

## Features

- RESTful endpoints for CRUD operations
- JSON response format
- Exception handling with custom error messages
- Validation for request data
- Maven for build automation and dependency management
- Easy configuration via `application.properties`/`application.yml`
  
## Technologies Used

- Java 21
- Spring Framework 3.3.3 (Spring Boot, Spring Data JPA, Spring Web)
- Hibernate (JPA)
- Maven
- H2/MySQL/PostgreSQL (as Database - configure in `application.properties`)

## Prerequisites

- Java 21
- Maven 3.8+

## Getting Started

### Clone the Repository

```bash
git clone https://github.com/ElCerebroGris/api-crud-spring.git
cd your-repository
```

### Running the Application

1. **Configure the database**: Edit the `src/main/resources/application.properties` to match your database configuration. For example, if using H2, you might have:

   ```properties
   spring.datasource.url=jdbc:h2:mem:testdb
   spring.datasource.driverClassName=org.h2.Driver
   spring.datasource.username=sa
   spring.datasource.password=password
   spring.jpa.database-platform=org.hibernate.dialect.H2Dialect
   ```

2. **Build and Run the Application**:

   ```bash
   mvn clean install
   mvn spring-boot:run
   ```

3. **Access the API**:

   The application will start on `http://localhost:8080`. You can access the CRUD API at `http://localhost:8080/api/v1/{resource}`.

### Example Endpoints

- **Create a resource**: `POST /api/v1/resource`
- **Retrieve all resources**: `GET /api/v1/resource`
- **Retrieve a specific resource by ID**: `GET /api/v1/resource/{id}`
- **Update a resource by ID**: `PUT /api/v1/resource/{id}`
- **Delete a resource by ID**: `DELETE /api/v1/resource/{id}`

### Testing the API

You can use tools like [Postman](https://www.postman.com/) or [curl](https://curl.se/) to test the API endpoints.

Example using `curl`:

```bash
# Create a new resource
curl -X POST http://localhost:8080/api/v1/resource -H "Content-Type: application/json" -d '{"name":"New Item"}'

# Get all resources
curl -X GET http://localhost:8080/api/v1/resource

# Update a resource
curl -X PUT http://localhost:8080/api/v1/resource/1 -H "Content-Type: application/json" -d '{"name":"Updated Item"}'

# Delete a resource
curl -X DELETE http://localhost:8080/api/v1/resource/1
```

### Running Tests

To run the unit tests:

```bash
mvn test
```

## Project Structure

```bash
src
├── main
│   ├── java
│   │   └── com
│   │       └── demo
│   │           ├── Controllers
│   │           ├── DTOs
│   │           ├── Models
│   │           ├── Repositories
│   │           └── DemoApplication.java
│   └── resources
│       ├── application.properties
│       └── templates
└── test
    └── java
```

- `controller`: Contains the REST controllers that define the API endpoints.
- `model`: Contains the entity classes representing the data model.
- `repository`: Contains the repository interfaces for data access (JPA).
- `service`: Contains the business logic.
- `resources`: Contains configuration files and static resources.

## Contributing

1. Fork the repository.
2. Create a new branch: `git checkout -b my-feature-branch`
3. Make your changes and commit them: `git commit -m 'Add some feature'`
4. Push to the branch: `git push origin my-feature-branch`
5. Open a pull request.

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## Contact

For any inquiries, feel free to reach out:

- Name: Osvaldo Calombe
- Email: osvaldo.programacao@gmail.com