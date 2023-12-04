package com.caklipakli.catalog.tasks;

import com.caklipakli.catalog.model.Listing;
import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;
import picocli.CommandLine;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
@Log4j2
@CommandLine.Command(name = "validate", description = "Validate objects")
public class ValidateObjects implements Runnable {

    public static List<Listing> validateListings(List<Listing> originalList) throws JsonProcessingException {

        log.info("Validation started...");
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();

        List<Listing> validatedListings = originalList.stream()
                .filter(listing -> {
                    Set<ConstraintViolation<Listing>> violations = validator.validate(listing);
                    violations.forEach(violation -> {
                        Process.errorsSb.append(listing.getId()).append("; ")
                                .append(listing.getMarketplaceId() == 1 ? "EBAY" : "AMAZON")
                                .append("; ").append(violation.getMessage()).append(" \n");

                    });

                    return violations.isEmpty();
                })
                .collect(Collectors.toList());

        log.info("Valid listings: " + validatedListings.size());

        return validatedListings;
    }

    @Override
    public void run() {

    }
}
