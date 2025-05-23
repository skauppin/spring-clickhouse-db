package com.example.spring_clickhouse_db;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class DatabaseOperations {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void printTableNames() {
        String sql = "SELECT database, name FROM system.tables ORDER BY database, name";
        jdbcTemplate.query(sql, (rs, rowNum) -> {
            System.out.println("Row " + rowNum + ": " + rs.getString("database") + ", " + rs.getString("name"));
            return null;
        });
    }

    /**
     * Check if query already running -- query "system.processes" table
     *
     * @param queryId
     * @return true if query is already running
     */
    private boolean checkIfQueryAlreadyRunning(String queryId) {
        String check = "SELECT count(*) FROM clusterAllReplicas('{cluster}', system.processes) WHERE query_id = ?";
        int numberOfProcesses = jdbcTemplate.queryForObject(check, Integer.class);

        System.out.println("Number of processes with query ID " + queryId + ": " + numberOfProcesses);
        return numberOfProcesses > 0;
    }

    /**
     * Heavy database operation that might timeout -- use query ID to identify the operation
     * and to check if it is already running in the database.
     *
     * @param queryId
     */
    public void heavyDatabaseOperationThatMightTimeout(String queryId) {

        if (checkIfQueryAlreadyRunning(queryId)) {
            System.out.println("Query ID already running, not starting a new one.");
            return;
        }

        /*

        // set ClickHouse query ID somehow - so that whatever operation is done with jdbcTemplate instance
        // uses this query ID

        try {
            // do heavy database operation using jdbcTemplate
            ...

        } finally {
            // clear the query ID somehow
        }

        */
    }
}
