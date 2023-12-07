package com.caklipakli.catalog;

import com.caklipakli.catalog.model.*;

import java.util.*;

public class MockData {

    public static List<Listing> getValidTestListingList() {
        Listing listing = new Listing();

        listing.setId("b2ea374d-2122-4f0d-9c63-44fcb3c211d9");
        listing.setTitle("Test title");
        listing.setDescription("Test description");
        listing.setLocationId("52ea143e-cb45-43af-981e-92cedb89f7a8");
        listing.setListingPrice(100.00);
        listing.setCurrency("USD");
        listing.setQuantity(1);
        listing.setListingStatus(1);
        listing.setMarketplaceId(1);
        listing.setUploadTime(new Date(2018, 1, 1));
        listing.setOwnerEmailAddress("test@email.com");

        List<Listing> listingList = new ArrayList<>();
        listingList.add(listing);
        return listingList;
    }

    public static List<Listing> getInvalidIdTestListingList() {
        Listing listing = new Listing();

        listing.setId("b2ea374d-2122-4f0d-9c63");
        listing.setTitle("Test title");
        listing.setDescription("Test description");
        listing.setLocationId("52ea143e-cb45-43af-981e-92cedb89f7a8");
        listing.setListingPrice(100.00);
        listing.setCurrency("USD");
        listing.setQuantity(1);
        listing.setListingStatus(1);
        listing.setMarketplaceId(1);
        listing.setUploadTime(new Date(2018, 1, 1));
        listing.setOwnerEmailAddress("testemail@test.com");

        List<Listing> listingList = new ArrayList<>();
        listingList.add(listing);
        return listingList;
    }

    public static List<Listing> getInvalidCurrencyEmailDescTestListingList() {
        Listing listing = new Listing();

        listing.setId("b2ea374d-2122-4f0d-9c63-44fcb3c211d9");
        listing.setTitle("Test title");
        listing.setDescription(null);
        listing.setLocationId("52ea143e-cb45-43af-981e-92cedb89f7a8");
        listing.setListingPrice(100.00);
        listing.setCurrency("USDX");
        listing.setQuantity(1);
        listing.setListingStatus(1);
        listing.setMarketplaceId(1);
        listing.setUploadTime(new Date(2016, 1, 1));
        listing.setOwnerEmailAddress("testemail.com");

        List<Listing> listingList = new ArrayList<>();
        listingList.add(listing);
        return listingList;
    }
}
