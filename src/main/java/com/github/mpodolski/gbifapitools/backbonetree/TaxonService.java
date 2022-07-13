package com.github.mpodolski.gbifapitools.backbonetree;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class TaxonService {

  private final TaxonRepository taxonRepository;

  public List<Taxon> getTaxa() {
    return taxonRepository.findAll();
  }

  public Taxon postTaxa(Taxon taxon) {
    return taxonRepository.save(taxon);
  }
}
