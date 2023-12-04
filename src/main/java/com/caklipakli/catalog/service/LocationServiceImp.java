package com.caklipakli.catalog.service;

import com.caklipakli.catalog.model.Location;
import com.caklipakli.catalog.repository.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
