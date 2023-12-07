package com.caklipakli.catalog.service;

import com.caklipakli.catalog.model.*;
import com.caklipakli.catalog.repository.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

import java.util.*;

@Service
public class LocationServiceImp implements ILocationService {

    @Autowired
    private LocationRepository locationRepository;

    @Override
    public void saveLocations(List<Location> locations) {
        locationRepository.saveAll(locations);
    }

    @Override
    public void saveLocation(Location location) {
        locationRepository.save(location);
    }

    @Override
    public List<Location> getAllLocations() {
        return locationRepository.findAll();
    }

}
