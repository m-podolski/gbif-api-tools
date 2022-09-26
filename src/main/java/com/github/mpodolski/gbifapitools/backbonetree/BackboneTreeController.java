package com.github.mpodolski.gbifapitools.backbonetree;

import lombok.AllArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@AllArgsConstructor
public class BackboneTreeController {

  private final TaxonService taxonService;

  @QueryMapping
  public List<Taxon> findAllTaxa() {
    return taxonService.findAllTaxa();
  }

  @QueryMapping
  public Taxon findTaxon(@Argument Integer id) {
    Optional<Taxon> taxon = taxonService.findTaxon(id);
    return taxon.orElse(null);
  }

  @MutationMapping
  public List<Taxon> createTaxa(@Argument ArrayList<Taxon> taxa) {
    return taxonService.createTaxa(taxa);
  }
}
