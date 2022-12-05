package com.github.mpodolski.gbifapitools.backbonetree;

//import jakarta.persistence.EntityManager;
//import jakarta.persistence.EntityTransaction;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.EnabledIfEnvironmentVariable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.graphql.tester.AutoConfigureHttpGraphQlTester;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.graphql.test.tester.HttpGraphQlTester;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@AutoConfigureHttpGraphQlTester
@AutoConfigureTestEntityManager
//@Transactional
@EnabledIfEnvironmentVariable(
  named = "SPRING_PROFILES_ACTIVE",
  matches = ".*integration.*")
public class BackboneTreeControllerIntegrationTest {

  @Autowired
  HttpGraphQlTester tester;

//  @Autowired
//  private TestEntityManager testEntityManager;

  Taxon taxon = Taxon.builder()
    .path(new ArrayList<>(Arrays.asList("path", "to", "taxon")))
    .nameCanonical("Cocos nucifera")
    .authorship("Me")
    .extinct(false)
    .numDescendants(1L)
    .numOccurrences(1L)
    .build();

  @Test
  void findAllTaxa() {
    this.tester.documentName("queries")
      .operationName("FindAllTaxa")
      .execute()
      .path("findAllTaxa")
      .hasValue();
  }

//  @Test
//  void findTaxon() {
//    Integer savedTaxonId = testEntityManager.persistAndGetId(this.taxon, Integer.class);
//    // Manually committing because HTTPGraphqlTester runs in parallel thread
//    EntityManager entityManager = testEntityManager.getEntityManager();
//    EntityTransaction entityTransaction = entityManager.getTransaction();
//    entityTransaction.commit();
//
//    this.tester.documentName("queries")
//      .operationName("FindTaxon")
//      .variable("taxonId", savedTaxonId)
//      .execute()
//      .path("findTaxon.authorship")
//      .entity(String.class)
//      .isEqualTo(this.taxon.getAuthorship());
//  }

  @Test
  void createTaxa() {
    int listLength = 2;
    List<Map<String, Object>> taxa = new ArrayList<>(listLength);

    for (int i = 0; i < listLength; i++) {
      Map<String, Object> taxon = new HashMap<>();
      taxon.put("path", this.taxon.getPath());
      taxon.put("nameCanonical", this.taxon.getNameCanonical());
      taxon.put("authorship", this.taxon.getAuthorship());
      taxon.put("numDescendants", this.taxon.getNumDescendants());
      taxon.put("numOccurrences", this.taxon.getNumOccurrences());
      taxon.put("extinct", this.taxon.getExtinct());

      taxa.add(taxon);
    }

    this.tester.documentName("mutations")
      .operationName("CreateTaxa")
      .variable("taxa", taxa)
      .execute()
      .path("createTaxa[*]")
      .entityList(Map.class)
      .hasSize(listLength)
      .satisfies(
        taxaReturned -> assertThat(taxaReturned.get(listLength - 1)
          .get("nameCanonical")).isEqualTo(
          this.taxon.getNameCanonical()));
  }
}