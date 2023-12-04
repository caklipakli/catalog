package com.caklipakli.catalog.service;

import com.caklipakli.catalog.model.Listing;
import com.caklipakli.catalog.tasks.ValidateObjects;
import com.caklipakli.catalog.repository.ListingRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Log4j2
public class ListingServiceImp implements IListingService {

    @Autowired
    private ListingRepository listingRepository;

    @Autowired
    private ValidateObjects validateObjects;

    @Override
    public void saveListings(List<Listing> listings) {
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
    public void saveListingAfterValidation(List<Listing> listings) throws JsonProcessingException {

        List<Listing> validatedListings = validateObjects.validateListings(listings);
        try {
            saveListings(validatedListings);
            log.info("Valid listings saved successfully");
        }
        catch (Exception e)
        {
            log.error("Error saving listing" + e.getMessage());
        }
    }
}
