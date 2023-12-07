package com.caklipakli.catalog.model;

import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

@Entity
@Table(name = "location")
@Getter
@Setter
public class Location {

    @Id
    @Pattern(regexp = "^[0-9a-f]{8}-[0-9a-f]{4}-[1-5][0-9a-f]{3}-[89ab][0-9a-f]{3}-[0-9a-f]{12}$", message = "Not a valid UUID")
    @NotNull(message = "Location ID is required")
    private String id;

    @JsonProperty("manager_name")
    private String manager_name;

    private String phone;

    @JsonProperty("address_primary")
    private String addressPrimary;

    @JsonProperty("address_secondary")
    private String addressSecondary;

    String country;
    private String town;

    @JsonProperty("postal_code")
    private String postalCode;
}
