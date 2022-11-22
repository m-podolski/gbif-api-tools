package com.github.mpodolski.gbifapitools;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.EnabledIfEnvironmentVariable;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@EnabledIfEnvironmentVariable(
  named = "SPRING_PROFILES_ACTIVE",
  matches = ".*integration.*")
class GbifToolsApplicationIntegrationTest {

  @Test
  void contextLoads() {
  }
}
