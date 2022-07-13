package com.github.mpodolski.gbifapitools;

import com.github.mpodolski.gbifapitools.backbonetree.TaxonRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class GbifApiToolsApplication {

  public static void main(String[] args) {
    SpringApplication.run(GbifApiToolsApplication.class, args);
  }

  @Bean
  CommandLineRunner commandLineRunner(ApplicationContext ctx,
                                      TaxonRepository kingdomRepository) {
    return args -> {
      System.out.println("Works still");
    };
  }

}
