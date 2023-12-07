package com.caklipakli.catalog.tasks;

import com.caklipakli.catalog.model.*;
import com.fasterxml.jackson.core.*;
import jakarta.validation.*;
import lombok.extern.log4j.*;
import org.springframework.stereotype.*;

import java.util.*;
import java.util.stream.*;

@Log4j2
@Component
public class ValidateObjects {

    public static ValidatedObject getValidetedListingsWithErrors(List<Listing> originalList) throws JsonProcessingException {

        log.info("Validation started...");
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        StringBuilder errors = new StringBuilder();
        ValidatedObject validatedObjects = new ValidatedObject();

        List<Listing> validatedListings = originalList.stream()
                .filter(listing -> {
                    Set<ConstraintViolation<Listing>> violations = validator.validate(listing);
                    violations.forEach(violation -> {
                        errors.append(listing.getId()).append("; ")
                                .append(listing.getMarketplaceId() == 1 ? "EBAY" : "AMAZON")
                                .append("; ").append(violation.getMessage()).append(" \n");

                    });
                    return violations.isEmpty();
                })
                .collect(Collectors.toList());

        validatedObjects.setErrors(errors);
        validatedObjects.setListings(validatedListings);
        log.info("Valid listings: " + validatedListings.size());

        return validatedObjects;
    }
}
