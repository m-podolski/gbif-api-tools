package com.github.mpodolski.gbifapitools.backbonetree;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.EnabledIfEnvironmentVariable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;

import static org.assertj.core.api.BDDAssertions.then;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@EnabledIfEnvironmentVariable(
  named = "SPRING_PROFILES_ACTIVE",
  matches = ".*integration.*")
public class TaxonRepositoryIntegrationTest {

  @Autowired
  private TaxonRepository taxonRepository;

  @Autowired
  private TestEntityManager testEntityManager;

  @Test void createReadTaxon() {

    Taxon taxon = Taxon.builder()
      .path(new ArrayList<>(Arrays.asList("path", "to", "taxon")))
      .nameCanonical("Cocos nucifera")
      .authorship("Me")
      .extinct(false)
      .numDescendants(1l)
      .numOccurrences(1l)
      .build();

    Taxon savedTaxon = testEntityManager.persistFlushFind(taxon);

    Optional<Taxon> foundTaxon = taxonRepository.findById(savedTaxon.getId());

    then(foundTaxon.get()
      .getNameCanonical()).isEqualTo(savedTaxon.getNameCanonical());
  }
}
