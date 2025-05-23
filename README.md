
# ClickHouse database operations using Spring Boot JdbcTemplate

Example application that executes a ClickHouse query with a custom query ID. `DatabaseOperations` class shows how ClickHouse database operations are executed using Spring JdbcTemplate.

It's worth noting that ClickHouse java classes are not used there. Only references to ClickHouse java classes are:
* application.properties (`spring.datasource.driverClassName`), 
* `DatabaseConfiguration` that initializes a `ClickHouseDataSource` 
* `CustomClickHouseRequestManager` extending `ClickHouseRequestManager`

`DatabaseOperationsTest` starts a ClickHouse container and has a test verifying that a query is executed with a custom query Id.

# Run tests

Build the application
```
$> ./mvnw clean package
```
