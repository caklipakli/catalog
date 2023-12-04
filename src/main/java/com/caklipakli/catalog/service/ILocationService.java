package com.caklipakli.catalog.service;

import com.caklipakli.catalog.model.Location;

import java.util.List;

public interface ILocationService {

    void saveLocations(List<Location> locations);

    void saveLocation(Location location);

    List<Location> getAllLocations();

}
