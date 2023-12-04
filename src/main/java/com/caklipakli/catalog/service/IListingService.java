package com.caklipakli.catalog.service;

import com.caklipakli.catalog.model.Listing;

import java.util.List;

public interface IListingService {

    void saveListings(List<Listing> listings);

    void saveListing(Listing listing);

    List<Listing> getAllListings();
}
