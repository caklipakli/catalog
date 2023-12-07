package com.caklipakli.catalog.repository;

import com.caklipakli.catalog.model.*;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.*;

@Repository
public interface LocationRepository extends JpaRepository<Location, String> {

}
