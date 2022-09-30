package com.github.mpodolski.gbifapitools.backbonetree;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.graphql.tester.AutoConfigureHttpGraphQlTester;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.graphql.test.tester.HttpGraphQlTester;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@AutoConfigureHttpGraphQlTester
public class BackboneTreeControllerTest {

  @Autowired
  HttpGraphQlTester tester;

  @Test
  void findAllTaxaTest() {
    this.tester.documentName("queries")
      .operationName("FindAllTaxa")
      .execute()
      .path("findAllTaxa")
      .hasValue();
  }

  @Test
  void findAllTaxonTest() {
    this.tester.documentName("queries")
      .operationName("FindTaxon")
      .variable("taxonId", 1)
      .execute()
      .path("findTaxon.authorship")
      .entity(String.class)
      .isEqualTo("Me");
  }

  @Test
  void createTaxaTest() {

    int listLength = 2;

    List<Map<String, Object>> taxa = new ArrayList<>(listLength);

    for (int i = 0; i < listLength; i++) {
      Map<String, Object> taxon = new HashMap<>();
      taxon.put("path", Arrays.asList("path", "to", "taxon"));
      taxon.put("nameCanonical", "Cocos nucifera");
      taxon.put("authorship", "Me");
      taxon.put("numDescendants", 1l);
      taxon.put("numOccurrences", 1l);
      taxon.put("extinct", false);

      taxa.add(taxon);
    }

    this.tester.documentName("mutations")
      .operationName("CreateTaxa")
      .variable("taxa", taxa)
      .execute()
      .path("createTaxa[*]")
      .entityList(Map.class)
      .hasSize(listLength)
      .satisfies(taxaReturned -> assertThat(taxaReturned.get(listLength - 1)
        .get("nameCanonical")).isEqualTo("Cocos nucifera"));
  }
}