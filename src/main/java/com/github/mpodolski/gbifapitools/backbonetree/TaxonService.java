package com.github.mpodolski.gbifapitools.backbonetree;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class TaxonService {

  private final TaxonRepository taxonRepository;

  public List<Taxon> findAllTaxa() {
    return taxonRepository.findAll();
  }

  public Optional<Taxon> findTaxon(Integer id) {
    return taxonRepository.findById(id);
  }

  public List<Taxon> createTaxa(ArrayList<Taxon> taxon) {
    return taxonRepository.saveAll(taxon);
  }
}
