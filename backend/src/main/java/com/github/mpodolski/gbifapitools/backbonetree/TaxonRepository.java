package com.github.mpodolski.gbifapitools.backbonetree;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaxonRepository extends ReactiveCrudRepository<Taxon, Long> {

}