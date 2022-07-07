package com.gmail.malte.podolski.gbiftools.entities;

import javax.persistence.*;

@Entity
@Table(name = "kingdom")
public class Kingdom {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(
    name = "id",
    nullable = false,
    updatable = false)
  private Integer id;

  @Column(
    name = "name_scientific",
    nullable = false,
    length = 30)
  private String nameScientific;

  public Integer getId() {return id;}

  public void setId(Integer id) {this.id = id;}

  public String getNameScientific() {return nameScientific;}

  public void setNameScientific(String nameScientific) {
    this.nameScientific = nameScientific;
  }

}