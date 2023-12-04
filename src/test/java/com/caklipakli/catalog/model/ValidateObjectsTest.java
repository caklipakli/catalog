package com.caklipakli.catalog.model;

import com.caklipakli.catalog.MockData;
import com.caklipakli.catalog.tasks.ValidateObjects;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.Test;
import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

public class ValidateObjectsTest {

    @Test
    public void testValidateListingsWithValidData() throws Exception {
        List<Listing> listings = MockData.getValidTestListingList();

        List<Listing> validatedListings = ValidateObjects.validateListings(listings);

        assertThat(validatedListings.size()).isEqualTo(1);
    }

    @Test
    public void testValidateListingsWithInvalidData() throws Exception {
        List<Listing> listings = MockData.getInvalidIdTestListingList();

        List<Listing> validatedListings = ValidateObjects.validateListings(listings);

        assertThat(validatedListings.size()).isEqualTo(0);
    }

    @Test
    public void testValidateListingsViolationMessage() throws Exception {

        Listing listing = MockData.getInvalidIdTestListingList().get(0);
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<Listing>> violations = validator.validate(listing);

        assertThat(violations.iterator().next().getMessage()).isEqualTo("Not a valid UUID");
    }

    @Test
    public void testValidateListingsViolationMessageNumber() throws Exception {

        Listing listing = MockData.getInvalidCurrencyEmailDescTestListingList().get(0);
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<Listing>> violations = validator.validate(listing);

        assertThat(violations.size()).isEqualTo(3);
    }
}
