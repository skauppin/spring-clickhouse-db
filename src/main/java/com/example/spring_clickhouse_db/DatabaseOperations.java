package com.example.spring_clickhouse_db;

import java.sql.Types;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import com.example.spring_clickhouse_db.request_manager.CustomClickHouseRequestManager;

@Component
public class DatabaseOperations {

  @Autowired
  private JdbcTemplate jdbcTemplate;

  /**
   * Check if query already running -- query "system.processes" table
   *
   * @param queryId
   * @return true if query is already running
   */
  boolean isQueryRunning(String queryId) {
    RowMapper<ClickHouseProcess> mapper = (resultSet,
        i) -> new ClickHouseProcess(resultSet.getString("query_id"), resultSet.getString("query"));

    List<ClickHouseProcess> list =
        jdbcTemplate.query("SELECT query_id, query FROM system.processes WHERE query_id = ?",
            new Object[] {queryId}, new int[] {Types.VARCHAR}, mapper);

    System.out.println("Number of processes with query ID " + queryId + ": " + list.size() + " -- "
        + Thread.currentThread().getName());
    for (int i = 0; i < list.size(); i++) {
      System.out.println(i + ": " + list.get(i));
    }

    return list.size() > 0;
  }

  /**
   * Heavy database operation that might timeout -- use query ID to identify the operation and to
   * check if it is already running in the database.
   *
   * @param queryId
   * @return true if the operation was executed, false if it was already running
   */
  public boolean heavyDatabaseOperationThatMightTimeout(String queryId) {

    if (isQueryRunning(queryId)) {
      System.out.println("Query ID already running, not starting a new one.");
      return false;
    }

    // We could do a heavy DB operation here..
    CustomClickHouseRequestManager.withClickHouseQueryId(queryId,
        () -> jdbcTemplate.execute("SELECT sleep(3), count(*) FROM system.tables"));

    return true;
  }

  private record ClickHouseProcess(String queryId, String query) {
  }
}
