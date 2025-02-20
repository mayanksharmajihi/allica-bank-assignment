# Allica Bank Customer Management System

This monorepo contains both the frontend and backend applications for the Allica Bank Customer Management System.

## Repository Structure

```
allica-bank-assignment/
├── customer-ui/       # Frontend React application
└── customer-backend/  # Backend Spring Boot application
```

## Frontend (customer-ui)

A React application built with:
- React 18
- Material-UI components
- React Query for data fetching
- Jest and React Testing Library for testing

### Frontend Setup
1. Navigate to the customer-ui directory:
   ```bash
   cd customer-ui
   ```
2. Install dependencies:
   ```bash
   npm install
   ```
3. Start the development server:
   ```bash
   npm start
   ```

## Backend (customer-backend)

A Spring Boot application with:
- Java 8
- Spring Boot 2.x
- H2 in-memory database
- JUnit 5 for testing

### Backend Setup
1. Navigate to the customer-backend directory:
   ```bash
   cd customer-backend
   ```
2. Build the application:
   ```bash
   ./mvnw clean install
   ```
3. Run the application:
   ```bash
   ./mvnw spring-boot:run
   ```
4. The API will be available at `http://localhost:8080`

## Running the Complete System

1. Start the backend server first (follow backend setup)
2. Start the frontend development server (follow frontend setup)
3. Access the application at `http://localhost:3000`

## API Documentation

The backend API documentation can be accessed at:
- Swagger UI: `http://localhost:8080/swagger-ui.html`
- OpenAPI JSON: `http://localhost:8080/v3/api-docs`

## Testing

Both frontend and backend include comprehensive test suites:
- Frontend: Jest and React Testing Library
- Backend: JUnit 5 with Spring Boot Test

## Development Guidelines

- Follow the existing code style and patterns
- Write tests for new features
- Use meaningful commit messages
- Create feature branches for new development

## License

This project is private and confidential to Allica Bank.