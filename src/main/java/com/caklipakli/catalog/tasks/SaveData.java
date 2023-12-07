package com.caklipakli.catalog.tasks;

import com.caklipakli.catalog.model.*;
import com.caklipakli.catalog.report.*;
import com.caklipakli.catalog.service.*;
import com.fasterxml.jackson.core.*;
import lombok.extern.log4j.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

import java.util.*;

import static com.caklipakli.catalog.AppCommand.*;
import static com.caklipakli.catalog.tasks.GetData.*;
import static com.caklipakli.catalog.tasks.ValidateObjects.*;

@Log4j2
@Component
public class SaveData {

    @Autowired
    private ListingServiceImp listingService;

    public void saveData() {
        try {
            ValidatedObject validatedObject = getValidetedListingsWithErrors(getListingDataFromAPI());
            saveListings(validatedObject.getListings());
            log.info("Write import log file");
            WriteReport.writeImportLogReport(ERROR_LOG_FILE, validatedObject.getErrors());
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    private void saveListings(List<Listing> listingList) {
        log.info("Save listing data to database. ");
        try {
            listingService.saveListings(listingList);
            log.info("Listings saved successfully. ");
        } catch (Exception e) {
            log.error("Error saving listings. " + e.getMessage());
        }
    }

}
