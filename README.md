
# ClickHouse database operations using Spring Boot JdbcTemplate

Example application that prints table names from ClickHouse database. `DatabaseOperations` class shows how ClickHouse database operations (only a simple select query there) are executed using Spring JdbcTemplate.

It's worth noting that ClickHouse java classes are not used there. Only reference to ClickHouse java classes is in application.properties (`spring.datasource.driverClassName`) and DatabaseConfiguration that initializes a `ClickHouseDataSource`. `DatabaseOperations` contains
some example database operations.

# How to run

Build the application
```
$> ./mvnw clean package
```

Assuming you have ClickHouse database running locally in port `8123` you can run

```
SPRING_DATASOURCE_USERNAME=user SPRING_DATASOURCE_PASSWORD=password java -jar target/spring-clickhouse-db-0.0.1-SNAPSHOT.jar
```
where "user" and "password" are your credentials to your local ClickHouse database.

The application prints the table names from the database:
```

  .   ____          _            __ _ _
 /\\ / ___'_ __ _ _(_)_ __  __ _ \ \ \ \
( ( )\___ | '_ | '_| | '_ \/ _` | \ \ \ \
 \\/  ___)| |_)| | | | | || (_| |  ) ) ) )
  '  |____| .__|_| |_|_| |_\__, | / / / /
 =========|_|==============|___/=/_/_/_/

 :: Spring Boot ::                (v3.5.0)

2025-05-23T15:37:46.839+03:00  INFO 140323 --- [spring-clickhouse-db] [           main] c.e.s.SpringClickhouseDbApplication      : Starting SpringClickhouseDbApplication v0.0.1-SNAPSHOT using Java 17.0.12 with PID 140323 (/home/skauppin/work/code/temp/spring-clickhouse-db/target/spring-clickhouse-db-0.0.1-SNAPSHOT.jar started by skauppin in /home/skauppin/work/code/temp/spring-clickhouse-db)
2025-05-23T15:37:46.864+03:00  INFO 140323 --- [spring-clickhouse-db] [           main] c.e.s.SpringClickhouseDbApplication      : No active profile set, falling back to 1 default profile: "default"
2025-05-23T15:37:51.115+03:00  INFO 140323 --- [spring-clickhouse-db] [           main] com.clickhouse.jdbc.ClickHouseDriver     : ClickHouse JDBC driver version: clickhouse-jdbc 0.7.2 (revision: 33b1fad)
2025-05-23T15:37:51.120+03:00  INFO 140323 --- [spring-clickhouse-db] [           main] com.clickhouse.jdbc.ClickHouseDriver     : v1 driver
2025-05-23T15:37:51.258+03:00  INFO 140323 --- [spring-clickhouse-db] [           main] com.clickhouse.jdbc.ClickHouseDriver     : ClickHouse JDBC driver version: clickhouse-jdbc 0.7.2 (revision: 33b1fad)
2025-05-23T15:37:51.258+03:00  INFO 140323 --- [spring-clickhouse-db] [           main] com.clickhouse.jdbc.ClickHouseDriver     : v1 driver
2025-05-23T15:37:51.362+03:00  INFO 140323 --- [spring-clickhouse-db] [           main] com.zaxxer.hikari.HikariDataSource       : HikariPool-1 - Starting...
2025-05-23T15:37:52.365+03:00  INFO 140323 --- [spring-clickhouse-db] [           main] com.zaxxer.hikari.pool.HikariPool        : HikariPool-1 - Added connection com.clickhouse.jdbc.internal.ClickHouseConnectionImpl@4a0df195
2025-05-23T15:37:52.372+03:00  INFO 140323 --- [spring-clickhouse-db] [           main] com.zaxxer.hikari.HikariDataSource       : HikariPool-1 - Start completed.
2025-05-23T15:37:52.806+03:00  INFO 140323 --- [spring-clickhouse-db] [           main] c.e.s.SpringClickhouseDbApplication      : Started SpringClickhouseDbApplication in 9.342 seconds (process running for 11.924)
Row 0: INFORMATION_SCHEMA, CHARACTER_SETS
Row 1: INFORMATION_SCHEMA, COLLATIONS
Row 2: INFORMATION_SCHEMA, COLUMNS
Row 3: INFORMATION_SCHEMA, ENGINES
Row 4: INFORMATION_SCHEMA, KEY_COLUMN_USAGE
Row 5: INFORMATION_SCHEMA, REFERENTIAL_CONSTRAINTS
Row 6: INFORMATION_SCHEMA, SCHEMATA
Row 7: INFORMATION_SCHEMA, STATISTICS
Row 8: INFORMATION_SCHEMA, TABLES
Row 9: INFORMATION_SCHEMA, VIEWS
Row 10: INFORMATION_SCHEMA, character_sets
Row 11: INFORMATION_SCHEMA, collations
Row 12: INFORMATION_SCHEMA, columns
Row 13: INFORMATION_SCHEMA, engines
Row 14: INFORMATION_SCHEMA, key_column_usage
Row 15: INFORMATION_SCHEMA, referential_constraints
Row 16: INFORMATION_SCHEMA, schemata
Row 17: INFORMATION_SCHEMA, statistics
Row 18: INFORMATION_SCHEMA, tables
Row 19: INFORMATION_SCHEMA, views
Row 20: default, backup_log
Row 21: default, backup_metadata
Row 22: default, data
Row 23: default, data_ranges
Row 24: default, data_ranges_view
Row 25: default, data_span
Row 26: default, enabled_stores
Row 27: default, latest_values
Row 28: default, latest_values_view
Row 29: default, maintenance_mode
Row 30: default, maintenance_mode_flag
Row 31: default, metadata
Row 32: default, migration_status
Row 33: information_schema, CHARACTER_SETS
Row 34: information_schema, COLLATIONS
Row 35: information_schema, COLUMNS
Row 36: information_schema, ENGINES
Row 37: information_schema, KEY_COLUMN_USAGE
Row 38: information_schema, REFERENTIAL_CONSTRAINTS
Row 39: information_schema, SCHEMATA
Row 40: information_schema, STATISTICS
Row 41: information_schema, TABLES
Row 42: information_schema, VIEWS
Row 43: information_schema, character_sets
Row 44: information_schema, collations
Row 45: information_schema, columns
Row 46: information_schema, engines
Row 47: information_schema, key_column_usage
Row 48: information_schema, referential_constraints
Row 49: information_schema, schemata
Row 50: information_schema, statistics
Row 51: information_schema, tables
Row 52: information_schema, views
Row 53: system, aggregate_function_combinators
Row 54: system, asynchronous_inserts
Row 55: system, asynchronous_loader
Row 56: system, asynchronous_metrics
Row 57: system, azure_queue
Row 58: system, azure_queue_settings
Row 59: system, backups
Row 60: system, build_options
Row 61: system, certificates
Row 62: system, clusters
Row 63: system, collations
Row 64: system, columns
Row 65: system, contributors
Row 66: system, current_roles
Row 67: system, dashboards
Row 68: system, data_skipping_indices
Row 69: system, data_type_families
Row 70: system, database_engines
Row 71: system, databases
Row 72: system, detached_parts
Row 73: system, detached_tables
Row 74: system, dictionaries
Row 75: system, disks
Row 76: system, distributed_ddl_queue
Row 77: system, distribution_queue
Row 78: system, dns_cache
Row 79: system, dropped_tables
Row 80: system, dropped_tables_parts
Row 81: system, enabled_roles
Row 82: system, errors
Row 83: system, events
Row 84: system, filesystem_cache
Row 85: system, filesystem_cache_settings
Row 86: system, formats
Row 87: system, functions
Row 88: system, grants
Row 89: system, graphite_retentions
Row 90: system, histogram_metrics
Row 91: system, jemalloc_bins
Row 92: system, kafka_consumers
Row 93: system, keywords
Row 94: system, latency_buckets
Row 95: system, licenses
Row 96: system, macros
Row 97: system, merge_tree_settings
Row 98: system, merges
Row 99: system, metrics
Row 100: system, models
Row 101: system, moves
Row 102: system, mutations
Row 103: system, named_collections
Row 104: system, numbers
Row 105: system, numbers_mt
Row 106: system, one
Row 107: system, part_moves_between_shards
Row 108: system, parts
Row 109: system, parts_columns
Row 110: system, privileges
Row 111: system, processes
Row 112: system, projection_parts
Row 113: system, projection_parts_columns
Row 114: system, projections
Row 115: system, query_cache
Row 116: system, query_condition_cache
Row 117: system, query_log
Row 118: system, quota_limits
Row 119: system, quota_usage
Row 120: system, quotas
Row 121: system, quotas_usage
Row 122: system, remote_data_paths
Row 123: system, replicas
Row 124: system, replicated_fetches
Row 125: system, replicated_merge_tree_settings
Row 126: system, replication_queue
Row 127: system, resources
Row 128: system, rocksdb
Row 129: system, role_grants
Row 130: system, roles
Row 131: system, row_policies
Row 132: system, s3_queue_settings
Row 133: system, s3queue
Row 134: system, scheduler
Row 135: system, schema_inference_cache
Row 136: system, server_settings
Row 137: system, settings
Row 138: system, settings_changes
Row 139: system, settings_profile_elements
Row 140: system, settings_profiles
Row 141: system, stack_trace
Row 142: system, storage_policies
Row 143: system, symbols
Row 144: system, table_engines
Row 145: system, table_functions
Row 146: system, tables
Row 147: system, time_zones
Row 148: system, user_directories
Row 149: system, user_processes
Row 150: system, users
Row 151: system, view_refreshes
Row 152: system, warnings
Row 153: system, workloads
Row 154: system, zeros
Row 155: system, zeros_mt
Row 156: system, zookeeper
Row 157: system, zookeeper_connection
2025-05-23T15:37:53.286+03:00  INFO 140323 --- [spring-clickhouse-db] [ionShutdownHook] com.zaxxer.hikari.HikariDataSource       : HikariPool-1 - Shutdown initiated...
2025-05-23T15:37:53.446+03:00  INFO 140323 --- [spring-clickhouse-db] [ionShutdownHook] com.zaxxer.hikari.HikariDataSource       : HikariPool-1 - Shutdown completed.
```

