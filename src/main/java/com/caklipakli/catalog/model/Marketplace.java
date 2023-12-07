package com.caklipakli.catalog.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
public class Marketplace {

    @Id
    private int id;
    private String marketplace;
}
