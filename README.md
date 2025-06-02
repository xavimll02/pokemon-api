# Pokemon API

A Spring Boot application that provides information about Pokemon using the [PokeAPI](https://pokeapi.co/). The application offers endpoints to retrieve the top 5 Pokemon based on different attributes (weight, height, and base experience).

## Technical Decisions

### Framework & Language
- **Java 21**: Latest LTS version offering improved performance and modern language features.

### Caching Strategy
- **Redis**: Selected as the caching solution for its:
  - High performance in-memory data storage
  - Easy scalability if needed
  - Excellent Spring Boot integration through Spring Cache

### Architecture Decisions
- **DTO Pattern**: Used to:
  - Map requests and responses

- **Service Layer**: Implements the core business logic:
  - Uses priority queue for efficient top-5 selection
  - Handles pagination of PokeAPI responses
  - Manages caching through Spring annotations

- **Global Exception Handler**: Provides:
  - Consistent error responses across the API
  - Proper HTTP status codes for different error scenarios
  - Detailed error messages for debugging

- **Configuration Properties**: Externalized configuration for:
  - PokeAPI base URL and pagination limits
  - Redis connection settings
  - Easy environment-specific configuration

### Testing Strategy
- **Unit Tests**: Testing individual components in isolation
- **Integration Tests**: End-to-end testing with actual HTTP calls
- **Test Profiles**: Separate configuration for testing environment

### Containerization
- **Multi-stage Docker Build**: 
  - Optimizes image size
  - Separates build and runtime environments
  - Improves build performance through caching

## Features

- Get the top 5 heaviest Pokemon
- Get the top 5 highest Pokemon
- Get the top 5 Pokemon with the most base experience
- Redis caching for improved performance
- Docker support for easy deployment
- Comprehensive error handling
- Unit and integration tests

## Project Structure

```
src/
├── main/
│   ├── java/
│   │   └── com/aleatest/pokemonapi/
│   │       ├── config/          # Configuration classes
│   │       ├── controller/      # REST endpoints
│   │       ├── dto/            # Data transfer objects
│   │       ├── exception/       # Global exception handling
│   │       └── service/        # Business logic
│   └── resources/
│       └── application.properties  # Application configuration
└── test/
    └── java/
        └── com/aleatest/pokemonapi/
            ├── controller/      # Controller tests
            ├── exception/       # Exception handling tests
            └── service/         # Service tests
```

## API Endpoints

- `GET /api/v1/heaviest` - Returns the top 5 heaviest Pokemon
- `GET /api/v1/highest` - Returns the top 5 highest Pokemon
- `GET /api/v1/most-experience` - Returns the top 5 Pokemon with the most base experience

## Running Locally

### Prerequisites

- Java 21 or higher
- Maven
- Redis (optional if running with Docker)

### Without Docker

1. Start Redis locally on port 6379
2. Build the application:
   ```bash
   ./mvnw clean package
   ```
3. Run the application:
   ```bash
   ./mvnw spring-boot:run
   ```

### With Docker

1. Build and start the containers:
   ```bash
   docker-compose up --build
   ```

The application will be available at `http://localhost:8080`

## Testing

Run the tests using Maven:
```bash
./mvnw test
```

## Configuration

The application can be configured through `application.properties`:

- `spring.application.name`: Application name
- `spring.cache.type`: Cache type (redis)
- `spring.data.redis.host`: Redis host
- `spring.data.redis.port`: Redis port
- `pokeapi.limit`: Maximum number of Pokemon entries to fetch per request
- `pokeapi.base-url`: PokeAPI base URL

## Documentation

In the `docs` directory you can find:
- **javadoc**: Complete API documentation with detailed explanations of each class and method
- **openapi.yaml**: OpenAPI specification that you can import into Swagger UI to test the API endpoints interactively

## Potential Improvements

Here are some improvements that could enhance the solution:

1. Enhanced Pokemon Information
   - Add more Pokemon attributes (types, abilities, etc.)

2. Security Implementation
   - Add HTTPS support with SSL/TLS certificates
   - Implement API key authentication

3. Deployment
   - Deploy the service to a cloud provider (AWS, Azure, GCP)
   - Set up a CI/CD pipeline for automated deployments
   - Set up domain name and SSL certificates

4. Testing Improvements
   - Implement chaos testing
   - Add load testing scenarios
   - Improve logging for debugging

5. Developer Experience
   - Improve logging for debugging
   - Add more comprehensive API documentation
