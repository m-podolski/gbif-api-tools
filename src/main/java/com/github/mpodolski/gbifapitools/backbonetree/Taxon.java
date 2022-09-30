package com.github.mpodolski.gbifapitools.backbonetree;

import com.vladmihalcea.hibernate.type.array.ListArrayType;
import lombok.*;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.*;
import java.util.List;

@Entity
@Table
@TypeDef(
  name = "list-array",
  typeClass = ListArrayType.class
)
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class Taxon {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @Type(type = "list-array")
  @Column(
    columnDefinition = "varchar(50)[]",
    nullable = false)
  private List<String> path;

  @Column(
    nullable = false,
    length = 50)
  private String nameCanonical;

  @Column(
    length = 50)
  private String authorship;

  @Column(
    nullable = false)
  private Long numDescendants;

  @Column(
    nullable = false)
  private Long numOccurrences;

  @Column(
    nullable = false)
  private Boolean extinct;
}