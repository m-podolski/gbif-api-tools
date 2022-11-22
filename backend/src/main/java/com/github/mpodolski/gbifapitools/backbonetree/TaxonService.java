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

  private List<Taxon> removeBracketsFromPath(ArrayList<Taxon> taxa) {
    return taxa.stream()
      .map(taxon -> {
        List<String> path = taxon.getPath();
        Boolean isBracketed = path.get(0)
          .matches("[\\[].+");

        if (isBracketed) {
          path.set(0, path.get(0)
            .toString()
            .substring(1));
          path.set(path.size() - 1, path.get(path.size() - 1)
            .toString()
            .substring(0, path.get(path.size() - 1)
              .length() - 1));
        }

        return taxon;
      })
      .toList();
  }

  public List<Taxon> createTaxa(ArrayList<Taxon> taxa) {
    return taxonRepository.saveAll(removeBracketsFromPath(taxa));
  }
}
