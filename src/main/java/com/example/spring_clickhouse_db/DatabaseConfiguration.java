package com.example.spring_clickhouse_db;

import java.sql.SQLException;
import java.util.Properties;
import javax.sql.DataSource;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.clickhouse.jdbc.ClickHouseDataSource;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

@Configuration
public class DatabaseConfiguration {

  public static ClickHouseDataSource createClickHouseDataSource(String url, String username,
      String password) throws SQLException {

    Properties properties = new Properties();
    properties.setProperty("user", username);
    properties.setProperty("password", password);
    properties.setProperty("http_connection_provider", "HTTP_URL_CONNECTION");

    return new ClickHouseDataSource(url, properties);
  }

  @Bean
  public DataSource createDataSource(DataSourceProperties properties, HikariConfig hikariConfig)
      throws SQLException {

    System.out.println("USER=" + properties.getUsername());
    System.out.println("PASS=" + properties.getPassword());

    ClickHouseDataSource dataSource = createClickHouseDataSource(properties.getUrl(),
        properties.getUsername(), properties.getPassword());

    hikariConfig.setDataSource(dataSource);

    return new HikariDataSource(hikariConfig);
  }

  @Bean
  @ConfigurationProperties(prefix = "spring.datasource.hikari")
  public HikariConfig hikariConfig() {
    return new HikariConfig();
  }


}
