package com.caklipakli.catalog.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Marketplace {

    @Id
    private int id;
    private String marketplace;
}
