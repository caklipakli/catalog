package com.caklipakli.catalog.service;

import com.caklipakli.catalog.model.*;

import java.util.*;

public interface IListingService {

    void saveListings(List<Listing> listings);

    void saveListing(Listing listing);

    List<Listing> getAllListings();
}
