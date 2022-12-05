package com.github.mpodolski.gbifapitools.backbonetree;

import lombok.AllArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Controller
@AllArgsConstructor
public class BackboneTreeController {

  private final TaxonService taxonService;

  @QueryMapping
  public Flux<Taxon> findAllTaxa() {
    return taxonService.findAllTaxa();
  }

  @QueryMapping
  public Mono<Taxon> findTaxon(@Argument Long id) {
    return taxonService.findTaxon(id);
  }

  @MutationMapping
  public Flux<Taxon> createTaxa(@Argument ArrayList<Taxon> taxa) {
    return taxonService.createTaxa(taxa);
  }
}
