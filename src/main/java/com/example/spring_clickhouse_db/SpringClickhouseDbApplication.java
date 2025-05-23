package com.example.spring_clickhouse_db;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringClickhouseDbApplication implements CommandLineRunner {

  public static void main(String[] args) {
    SpringApplication.run(SpringClickhouseDbApplication.class, args);
  }

  @Autowired
  private DatabaseOperations dbOperations;

  @Override
  public void run(String... args) throws Exception {
    final String queryId = "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa";
    dbOperations.heavyDatabaseOperationThatMightTimeout(queryId);
  }

}
