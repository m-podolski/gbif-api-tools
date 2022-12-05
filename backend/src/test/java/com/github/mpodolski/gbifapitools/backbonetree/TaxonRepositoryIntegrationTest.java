package com.github.mpodolski.gbifapitools.backbonetree;

import com.github.mpodolski.gbifapitools.DatabaseConfiguration;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.EnabledIfEnvironmentVariable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.r2dbc.DataR2dbcTest;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.test.context.ContextConfiguration;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;

import static org.assertj.core.api.BDDAssertions.then;

@DataR2dbcTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@EnabledIfEnvironmentVariable(
  named = "SPRING_PROFILES_ACTIVE",
  matches = ".*integration.*")
public class TaxonRepositoryIntegrationTest {

  @Autowired
  private TaxonRepository taxonRepository;

  @Test
  void createReadTaxon() {

    Taxon taxon = Taxon.builder()
      .path(new ArrayList<>(Arrays.asList("path", "to", "taxon")))
      .nameCanonical("Cocos nucifera")
      .authorship("Me")
      .extinct(false)
      .numDescendants(1l)
      .numOccurrences(1l)
      .build();

    Optional<Taxon> savedTaxon = taxonRepository.save(taxon)
      .blockOptional();
    Optional<Taxon> foundTaxon = taxonRepository.findById(savedTaxon.get()
        .getId())
      .blockOptional();

    then(foundTaxon.get()
      .getNameCanonical()).isEqualTo(savedTaxon.get()
      .getNameCanonical());
  }
}
