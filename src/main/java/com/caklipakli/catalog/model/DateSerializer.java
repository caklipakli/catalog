package com.caklipakli.catalog.model;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import lombok.SneakyThrows;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateSerializer extends JsonDeserializer<Date> {

    @SneakyThrows
    @Override
    public Date deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) {

        String dateString = jsonParser.getText();

        String [] dateParts = dateString.split("/");
        SimpleDateFormat formatter1 = new SimpleDateFormat("MM/dd/yyyy");
        SimpleDateFormat formatter2 = new SimpleDateFormat("M/d/yyyy");
        SimpleDateFormat formatter3 = new SimpleDateFormat("M/dd/yyyy");

        if (dateParts[0].length() == 1 && dateParts[1].length() == 1) {
            return formatter3.parse(dateString);
        }
        else if (dateParts[0].length() == 1) {
            return formatter2.parse(dateString);
        }
        else {
            return formatter1.parse(dateString);
        }
    }
}
