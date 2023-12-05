package com.caklipakli.catalog.model;

import lombok.*;

import java.util.*;

@Getter
@Setter
public class ValidatedObject {

    private List<Listing> listings;
    private StringBuilder errors;
}
