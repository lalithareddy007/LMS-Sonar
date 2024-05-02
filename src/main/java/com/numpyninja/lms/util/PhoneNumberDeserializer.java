package com.numpyninja.lms.util;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.numpyninja.lms.exception.InvalidDataException;

import java.io.IOException;

public class PhoneNumberDeserializer extends JsonDeserializer<Long> {


    @Override
    public Long deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        String value = p.getValueAsString().trim();
        if (value.isEmpty()) {
            return null;
        }
        if(!value.matches("\\d+$"))
        {
            throw new InvalidDataException("Please enter the phone number in valid format");
        }
        return Long.parseLong(value);
    }

}
