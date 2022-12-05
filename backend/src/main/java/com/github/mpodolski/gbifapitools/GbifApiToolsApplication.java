package com.github.mpodolski.gbifapitools;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class GbifApiToolsApplication {

  private static final Logger logger = LoggerFactory.getLogger(GbifApiToolsApplication.class);

  public static void main(String[] args) {
    SpringApplication.run(GbifApiToolsApplication.class, args);
  }

  @Bean
  CommandLineRunner commandLineRunner() {
    return args -> {
      System.out.println("Works still");
      //    R2dbcEntityTemplate template = new R2dbcEntityTemplate(connectionFactory);
//
//    template.getDatabaseClient()
//      .sql("CREATE TABLE person" +
//        "(id VARCHAR(255) PRIMARY KEY," +
//        "name VARCHAR(255)," +
//        "age INT)")
//      .fetch()
//      .rowsUpdated()
//      .as(StepVerifier::create)
//      .expectNextCount(1)
//      .verifyComplete();

//    Flux<Map<String, Object>> first = databaseClient.sql("select * from taxon;")
//      .fetch()
//      .all();
//
//    first.doOnNext(map -> {
//      map.forEach((key, value) -> {
//        System.out.println(key);
//      });
//    });
    };
  }
}
