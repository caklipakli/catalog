package com.caklipakli.catalog.service;

import com.caklipakli.catalog.model.*;
import com.caklipakli.catalog.repository.*;
import lombok.extern.log4j.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

import java.util.*;

@Service
@Log4j2
public class ListingServiceImp implements IListingService {

    @Autowired
    private ListingRepository listingRepository;

    @Override
    public void saveListings(List<Listing> listings) {
        log.info("Saving listings to database");
        listingRepository.saveAll(listings);
    }

    @Override
    public void saveListing(Listing listing) {
        listingRepository.save(listing);

    }

    @Override
    public List<Listing> getAllListings() {
        return listingRepository.findAll();
    }

}
