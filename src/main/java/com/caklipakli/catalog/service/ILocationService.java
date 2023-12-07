package com.caklipakli.catalog.service;

import com.caklipakli.catalog.model.*;

import java.util.*;

public interface ILocationService {

    void saveLocations(List<Location> locations);

    void saveLocation(Location location);

    List<Location> getAllLocations();

}
