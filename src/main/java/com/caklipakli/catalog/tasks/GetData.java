package com.caklipakli.catalog.tasks;

import com.caklipakli.catalog.model.*;
import com.fasterxml.jackson.core.*;
import com.fasterxml.jackson.core.type.*;
import com.fasterxml.jackson.databind.*;
import lombok.extern.log4j.*;

import java.io.*;
import java.net.*;
import java.net.http.*;
import java.util.*;

@Log4j2
public class GetData {

    private static final String URI_ADDRESS_LOCATION = "https://my.api.mockaroo.com/location";
    private static final String URI_ADDRESS_LISTING = "https://my.api.mockaroo.com/listing";
    private static final String API_NAME = "X-API-Key";
    private static final String API_VALUE = "63304c70";

    public static List<Location> getLocationDataFromAPI() throws JsonProcessingException {

        log.info("Get location data from Mockaroo");

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(URI_ADDRESS_LOCATION))
                .header(API_NAME, API_VALUE)
                .method("GET", HttpRequest.BodyPublishers.noBody())
                .build();

        HttpResponse<String> response = null;
        try {
            response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ObjectMapper objectMapper = new ObjectMapper();
        List<Location> locationList = objectMapper.readValue(response.body(), new TypeReference<>() {
        });
        log.info("Location data mapped from API, number of rows received: " + locationList.size());
        return locationList;
    }

    public static List<Listing> getListingDataFromAPI() throws JsonProcessingException {

        log.info("Get listing data from Mockaroo");

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(URI_ADDRESS_LISTING))
                .header(API_NAME, API_VALUE)
                .method("GET", HttpRequest.BodyPublishers.noBody())
                .build();

        HttpResponse<String> response = null;
        try {
            response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ObjectMapper objectMapper = new ObjectMapper();
        List<Listing> listings = objectMapper.readValue(response.body(), new TypeReference<>() {
        });


        log.info("Listing data mapped from API, number of rows received:: " + listings.size());
        return listings;
    }
}

