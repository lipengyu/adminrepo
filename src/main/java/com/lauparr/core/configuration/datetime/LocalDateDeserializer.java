package com.lauparr.core.configuration.datetime;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.lauparr.core.configuration.DateTimeConfig;

import java.io.IOException;
import java.time.LocalDate;

/**
 * Created by lauparr on 18/11/2016.
 */
public class LocalDateDeserializer extends JsonDeserializer<LocalDate> {

    @Override
    public LocalDate deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        return LocalDate.parse(p.getValueAsString(), DateTimeConfig.DATE_FORMATTER);
    }

}
