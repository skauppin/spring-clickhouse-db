package com.example.spring_clickhouse_db;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.testcontainers.clickhouse.ClickHouseContainer;

@SpringBootTest
public class DatabaseOperationsTest {

  private static ClickHouseContainer clickHouseContainer;

  @Autowired
  private DatabaseOperations databaseOperations;

  @BeforeAll
  public static void init() {
    clickHouseContainer = new ClickHouseContainer("clickhouse/clickhouse-server:25.4.5.24");
    clickHouseContainer.withExposedPorts(8123);
    clickHouseContainer.start();

    System.setProperty("CLICKHOUSE_PORT", String.valueOf(clickHouseContainer.getMappedPort(8123)));
    System.setProperty("SPRING_DATASOURCE_USERNAME", clickHouseContainer.getUsername());
    System.setProperty("SPRING_DATASOURCE_PASSWORD", clickHouseContainer.getPassword());
  }

  @AfterAll
  public static void stop() {
    clickHouseContainer.stop();
  }

  @Test
  public void queryExecutedWithCustomQueryId() throws InterruptedException {
    final String queryId = "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa";

    Thread queryRunningThread = new Thread(() -> {
      databaseOperations.heavyDatabaseOperationThatMightTimeout(queryId);
    });

    // there should not be a process yet
    assertFalse(databaseOperations.isQueryRunning(queryId));

    // start thread running a slow query
    queryRunningThread.start();
    Thread.sleep(1000);

    // at this point we should find the process with the query ID
    assertTrue(databaseOperations.isQueryRunning(queryId));

    queryRunningThread.join();
  }

}
