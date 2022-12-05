package com.github.mpodolski.gbifapitools.backbonetree;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import java.util.ArrayList;
import java.util.Arrays;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TaxonServiceTest {

  @Mock
  TaxonRepository taxonRepository;

  @InjectMocks
  private TaxonService taxonService;

  @Test
  void createTaxa() {
    Taxon taxonWithBracketedPath = Taxon.builder()
      .path(new ArrayList<>(Arrays.asList("[path", "to", "taxon]")))
      .nameCanonical("Cocos nucifera")
      .authorship("Me")
      .extinct(false)
      .numDescendants(1L)
      .numOccurrences(1L)
      .build();

    Taxon taxonWithFixedPath = Taxon.builder()
      .path(new ArrayList<>(Arrays.asList("path", "to", "taxon")))
      .nameCanonical("Cocos nucifera")
      .authorship("Me")
      .extinct(false)
      .numDescendants(1L)
      .numOccurrences(1L)
      .build();

    int listLength = 2;
    ArrayList<Taxon> taxa = new ArrayList<>(listLength);

    for (int i = 0; i < listLength; i++) {
      taxa.add(taxonWithBracketedPath);
    }

    when(taxonRepository.saveAll(taxa)).thenReturn(Flux.fromIterable(taxa));

    StepVerifier.create(this.taxonService.createTaxa(taxa))
      .expectNextMatches(taxon -> taxon.getPath()
        .get(0)
        .equals("path"))
      .expectNextCount(1L)
      .verifyComplete();
  }
}