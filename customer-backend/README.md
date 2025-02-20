# Customer Management Service

A Spring Boot application for managing customer information with RESTful APIs.

## Features

- Create new customers
- Retrieve customer details
- List all customers
- Input validation
- Error handling
- CORS configuration
- API documentation with OpenAPI/Swagger
- JaCoCo code coverage reporting

## Tech Stack

- Java 8
- Spring Boot 2.7.18
- Spring Data JPA
- H2 Database
- Maven
- JaCoCo
- SpringDoc OpenAPI
- Lombok
- Spring Validation

## Getting Started

### Prerequisites

- JDK 1.8
- Maven 3.x
- Git

### Installation

1. Clone the repository:
```bash
git clone <repository-url>
```

2. Navigate to the project directory:
```bash
cd customer
```

3. Build the project:
```bash
mvn clean install
```

4. Run the application:
```bash
mvn spring-boot:run
```

The application will start on `http://localhost:8080`

## API Documentation

Once the application is running, you can access the API documentation at:
- Swagger UI: `http://localhost:8080/swagger-ui.html`
- OpenAPI Spec: `http://localhost:8080/v3/api-docs`

### Available Endpoints

- POST `/api/v1/customers` - Create a new customer
- GET `/api/v1/customers` - List all customers
- GET `/api/v1/customers/{id}` - Get customer by ID

## Testing

Run the tests with:
```bash
mvn test
```

### Code Coverage

JaCoCo coverage reports can be found at:
`target/site/jacoco/index.html`

The project maintains a minimum of 80% code coverage requirement.

## CORS Configuration

The application is configured to accept requests from `http://localhost:3000` for development purposes.

## Error Handling

The application includes global exception handling for:
- Validation errors
- Resource not found
- Invalid input
- General server errors

## Response Format

All API responses follow a consistent format:
```json
{
  "success": boolean,
  "message": "string",
  "status": "string",
  "data": object
}
```

## Contributing

1. Fork the repository
2. Create your feature branch
3. Commit your changes
4. Push to the branch
5. Create a new Pull Request

## License

This project is licensed under the MIT License - see the LICENSE file for details.
