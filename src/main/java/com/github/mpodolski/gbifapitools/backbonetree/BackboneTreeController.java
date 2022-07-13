package com.github.mpodolski.gbifapitools.backbonetree;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping(path = "api/backbonetree")
@AllArgsConstructor
public class BackboneTreeController {

  private final TaxonService taxonService;

  @GetMapping
  public List<Taxon> getTaxa() {
    return taxonService.getTaxa();
  }

  @PostMapping
  public List<Taxon> postTaxa(@RequestBody Taxon taxon) {
    return Collections.singletonList(taxonService.postTaxa(taxon));
  }
}
