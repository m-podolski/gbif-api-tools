package com.github.mpodolski.gbifapitools;

import io.r2dbc.postgresql.PostgresqlConnectionConfiguration;
import io.r2dbc.postgresql.PostgresqlConnectionFactory;
import io.r2dbc.spi.ConnectionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.r2dbc.config.AbstractR2dbcConfiguration;
import org.springframework.r2dbc.connection.init.ConnectionFactoryInitializer;
import org.springframework.r2dbc.connection.init.ResourceDatabasePopulator;

@Configuration
public class DatabaseConfiguration extends AbstractR2dbcConfiguration {

  @Override
  @Bean
  public ConnectionFactory connectionFactory() {
    return new PostgresqlConnectionFactory(PostgresqlConnectionConfiguration.builder()
      .host("localhost")
      .port(5432)
      .database("gat")
      .username("gat")
      .password("gat")
      .build());
  }

  @Bean
  ConnectionFactoryInitializer initializer(ConnectionFactory connectionFactory) {
    ConnectionFactoryInitializer initializer = new ConnectionFactoryInitializer();
    initializer.setConnectionFactory(connectionFactory);

    initializer.setDatabasePopulator(
      new ResourceDatabasePopulator(new ClassPathResource("database/schema.sql")));
    initializer.setDatabaseCleaner(
      new ResourceDatabasePopulator(new ClassPathResource("database/schema-drop.sql")));
    return initializer;
  }
}
