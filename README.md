#  ClusteredData Warehouse API Guide

The ClusteredData Warehouse offers a robust API for managing Foreign Exchange (FX) transactions. This guide covers the `/api/v1/FXDeals` endpoint, its request format, validation rules, error handling, and running the application via Docker.

## API Endpoint

### URL:
- **[POST] http://localhost:9999/api/v1/FXDeals**
- **[GET]  http://localhost:9999/api/v1/FXDeals**

### Sample Request JSON:
```shell
{
  "orderingCurrencyIsoCode": "TEST1",
  "toCurrencyIsoCode": "TEST2",
  "dealAmount": 100000.50
}
```

## Project Directory Structure

```javascript

WAREHOUSE
│   docker-compose.yml
│   Dockerfile
│   HELP.md
│   Makefile
│   mvnw
│   mvnw.cmd
│   pom.xml
│
├───.mvn
│   └───wrapper
│           maven-wrapper.properties
│
├───src
│   ├───main
│   │   ├───java
│   │   │   └───com
│   │   │       └───progresssoft
│   │   │           └───warehouse
│   │   │               │   WarehouseApplication.java
│   │   │               │
│   │   │               ├───aop
│   │   │               │   ├───exception
│   │   │               │   │       AlreadyExistException.java
│   │   │               │   │
│   │   │               │   └───logging
│   │   │               │           MethodLoggingAspect.java
│   │   │               │
│   │   │               ├───config
│   │   │               │       MapperConfig.java
│   │   │               │
│   │   │               ├───dto
│   │   │               │   ├───request
│   │   │               │   │       FXDealDtoRequest.java
│   │   │               │   │
│   │   │               │   └───response
│   │   │               │           ErrorResponse.java
│   │   │               │           FXDealDtoResponse.java
│   │   │               │
│   │   │               ├───model
│   │   │               │       FXDeal.java
│   │   │               │
│   │   │               ├───repository
│   │   │               │       FXDealRepository.java
│   │   │               │
│   │   │               ├───service
│   │   │               │   │   FXDealService.java
│   │   │               │   │   IMapper.java
│   │   │               │   │
│   │   │               │   └───impl
│   │   │               │           FXDealServiceImpl.java
│   │   │               │           MapperImpl.java
│   │   │               │
│   │   │               └───web
│   │   │                   └───rest
│   │   │                           FXDealsController.java
│   │   │                           GlobalExceptionHandler.java
│   │   │
│   │   └───resources
│   │       │   application.yml
│   │       │
│   │       ├───db
│   │       │   └───migration
│   │       │           V1__create_fx_deal_table.sql
│   │       │
│   │       ├───static
│   │       └───templates
│   └───test
│       └───java
│           └───com
│               └───progresssoft
│                   └───warehouse
│                       │   WarehouseApplicationTests.java
│                       │
│                       └───service
│                               FXDealServiceImplTest.java
│
└───target
    ├───classes
    │   │   application.yml
    │   │
    │   ├───com
    │   │   └───progresssoft
    │   │       └───warehouse
    │   │           │   WarehouseApplication.class
    │   │           │
    │   │           ├───aop
    │   │           │   ├───exception
    │   │           │   │       AlreadyExistException.class
    │   │           │   │
    │   │           │   └───logging
    │   │           │           MethodLoggingAspect.class
    │   │           │
    │   │           ├───config
    │   │           │       MapperConfig.class
    │   │           │
    │   │           ├───dto
    │   │           │   ├───request
    │   │           │   │       FXDealDtoRequest$FXDealDtoRequestBuilder.class
    │   │           │   │       FXDealDtoRequest.class
    │   │           │   │
    │   │           │   └───response
    │   │           │           ErrorResponse$ErrorResponseBuilder.class
    │   │           │           ErrorResponse.class
    │   │           │           FXDealDtoResponse$FXDealDtoResponseBuilder.class
    │   │           │           FXDealDtoResponse.class
    │   │           │
    │   │           ├───model
    │   │           │       FXDeal.class
    │   │           │
    │   │           ├───repository
    │   │           │       FXDealRepository.class
    │   │           │
    │   │           ├───service
    │   │           │   │   FXDealService.class
    │   │           │   │   IMapper.class
    │   │           │   │
    │   │           │   └───impl
    │   │           │           FXDealServiceImpl.class
    │   │           │           MapperImpl.class
    │   │           │
    │   │           └───web
    │   │               └───rest
    │   │                       FXDealsController.class
    │   │                       GlobalExceptionHandler.class
    │   │
    │   └───db
    │       └───migration
    │               V1__create_fx_deal_table.sql
    │
    ├───generated-sources
    │   └───annotations
    ├───generated-test-sources
    │   └───test-annotations
    └───test-classes
        └───com
            └───progresssoft
                └───warehouse
                    │   WarehouseApplicationTests.class
                    │
                    └───service
                            FXDealServiceImplTest.class
```


## Running the Application with Docker
You can start the application using Docker Compose:
```shell
docker compose up
```

Or via the Makefile:
```shell

make run
make run-detached
```
## Database Setup
Start the database container:
```shell
docker exec -it warehouse-postgres-db bash
```
Connect to PostgreSQL:
```shell
psql -U postgres -d warehouse
```

## Logging with AOP
The application uses AspectJ for Aspect-Oriented Programming (AOP) logging.
It logs service method parameters, execution success, and errors to provide detailed runtime insights.

## Testing Strategy
- JUnit 5: Used for unit testing with extensive coverage to ensure stability.

- Mockito: Employed for mocking dependencies during tests.


## Stopping the Application
```shell
make stop
```
