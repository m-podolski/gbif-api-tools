package com.github.mpodolski.gbifapitools.backbonetree;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaxonRepository extends JpaRepository<Taxon, Integer> {

  Taxon getTaxonByNameCanonical(String nameCanonical);
}