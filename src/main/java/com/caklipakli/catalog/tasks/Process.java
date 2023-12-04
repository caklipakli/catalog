package com.caklipakli.catalog.tasks;

import com.caklipakli.catalog.exception.GlobalExceptionHandler;
import com.caklipakli.catalog.model.Listing;
import com.caklipakli.catalog.model.Location;
import com.caklipakli.catalog.report.WriteReport;
import com.caklipakli.catalog.service.ListingServiceImp;
import com.caklipakli.catalog.service.LocationServiceImp;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import picocli.CommandLine;

import java.util.List;


@CommandLine.Command(name = "process", description = "process the tasks")
@Component
@Log4j2
public class Process extends GlobalExceptionHandler implements Runnable {
    @Autowired
    private LocationServiceImp locationService;
    @Autowired
    private ListingServiceImp listingService;
    public static StringBuilder errorsSb = new StringBuilder();

    public static void main(String[] args) {

        CommandLine commandLine = new CommandLine(new Process());
        commandLine.addSubcommand("report", new GenerateReport());
        commandLine.parseWithHandler(new CommandLine.RunAll(), args);
        commandLine.getCommandSpec().parser().collectErrors(true);

    }

    @SneakyThrows
    @Override
    public void run() {
        log.info("Simulate a listing reporting system");

        List<Location> locationList = null;

        try {
            locationList = GetData.getLocationDataFromAPI();

        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        log.info("Save location data to database");
        try {
            locationService.saveLocations(locationList);
            log.info("Locations saved successfully");
        } catch (Exception e) {
            log.error("Error saving locations" + e.getMessage());
        }

        List<Listing> listingList;

        try {
            listingList = GetData.getListingDataFromAPI();

            log.info("Validation and saving of listings");

            listingService.saveListingAfterValidation(listingList);

        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        log.info("Generate error log report: import_log.csv");

        WriteReport.writeImportLogReport(WriteReport.ERROR_LOG_FILE_NAME);
    }

}
