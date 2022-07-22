package com.github.mpodolski.gbifapitools.backbonetree;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class TaxonService {

  private final TaxonRepository taxonRepository;

  public List<Taxon> findTaxa() {
    return taxonRepository.findAll();
  }

  public Optional<Taxon> findTaxon(Integer id) {
    return taxonRepository.findById(id);
  }

  public Taxon createTaxa(Taxon taxon) {
    return taxonRepository.save(taxon);
  }
}
