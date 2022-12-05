package com.github.mpodolski.gbifapitools.backbonetree;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.util.ArrayList;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Taxon {

  @Id
  private Long id;

  private ArrayList<String> path;

  private String nameCanonical;

  private String authorship;

  private Long numDescendants;

  private Long numOccurrences;

  private Boolean extinct;
}