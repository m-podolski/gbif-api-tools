package com.github.mpodolski.gbifapitools.backbonetree;

import lombok.AllArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Controller
//@RequestMapping(path = "api/backbonetree")
@AllArgsConstructor
public class BackboneTreeController {

  private final TaxonService taxonService;

  @QueryMapping
  public List<Taxon> allTaxa() {
    return taxonService.findTaxa();
  }

  @QueryMapping
  public Taxon findTaxon(@Argument Integer id) {
    Optional<Taxon> taxon = taxonService.findTaxon(id);
    return taxon.orElse(null);
  }

  @QueryMapping
  public List<Taxon> createTaxa(@Argument Taxon taxon) {
    return Collections.singletonList(taxonService.createTaxa(taxon));
  }
}
