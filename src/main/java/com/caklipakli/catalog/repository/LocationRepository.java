package com.caklipakli.catalog.repository;

import com.caklipakli.catalog.model.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LocationRepository  extends JpaRepository<Location, String> {

}
