package com.example.spring_clickhouse_db.request_manager;

import java.util.UUID;
import com.clickhouse.client.ClickHouseRequestManager;

public class CustomClickHouseRequestManager extends ClickHouseRequestManager {

  private static ThreadLocal<String> clickhouseQueryIdValue = new ThreadLocal<>();

  @Override
  public String createQueryId() {
    String threadLocalValue = clickhouseQueryIdValue.get();
    if (threadLocalValue != null) {
      // user defined query ID
      return threadLocalValue;
    }
    return UUID.randomUUID().toString();
  }

  /**
   * Execute a ClickHouse query using a specific query ID
   *
   * @param queryId
   * @param runnable
   */
  public static void withClickHouseQueryId(String queryId, Runnable runnable) {
    clickhouseQueryIdValue.set(queryId);
    try {
      runnable.run();
    } finally {
      clickhouseQueryIdValue.remove();
    }
  }
}
