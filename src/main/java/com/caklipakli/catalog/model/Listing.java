package com.caklipakli.catalog.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(name = "listing")
@Getter
@Setter
public class Listing {

    @Id
    @Pattern(regexp="^[0-9a-f]{8}-[0-9a-f]{4}-[1-5][0-9a-f]{3}-[89ab][0-9a-f]{3}-[0-9a-f]{12}$", message = "Not a valid UUID")
    @NotNull(message = "Listing ID is required")
    private String id;

    @NotNull(message = "Title is required")
    private String title;

    @NotNull(message = "Description is required")
    private String description;

    @NotNull(message = "Location ID is required")
    @JsonProperty("location_id")
    private String locationId;

    @JsonProperty("listing_price")
    @NotNull(message = "Listing price is required")
    @Positive(message = "Listing price must be positive")
    private Double listingPrice;

    @Column(length = 3)
    @NotNull(message = "Currency is required")
    @Pattern(regexp = "^[A-Z]{3}$", message = "Currency must be 3 characters")
    private String currency;

    @NotNull(message = "Quantity is required")
    @Positive(message = "Quantity must be positive")
    private int quantity;

    @JsonProperty("listing_status")
    @NotNull(message = "Listing status is required")
    @Min(value = 1, message = "Listing status must be between 1 and 3")
    @Max(value = 3, message = "Listing status must be between 1 and 3")
    private int listingStatus;

    @NotNull(message = "Marketplace is required")
    @JsonProperty("marketplace")
    private int marketplaceId;

    @JsonProperty("owner_email_address")
    @NotNull(message = "Owner email address is required")
    @Pattern(regexp = "^[A-Za-z0-9+_.-]+@(.+)$", message = "Owner email address must be a valid email address")
    private String ownerEmailAddress;

    @JsonProperty("upload_time")
    @NotNull(message = "Upload time is required")
    @JsonDeserialize(using = DateSerializer.class)
    private Date uploadTime;

}
