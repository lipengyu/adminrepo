package com.lauparr.configuration.datetime;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.lauparr.configuration.DateTimeConfig;

import java.io.IOException;
import java.time.LocalDateTime;

/**
 * Created by lauparr on 18/11/2016.
 */
public class LocalDateTimeDeserializer extends JsonDeserializer<LocalDateTime> {

    @Override
    public LocalDateTime deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        return LocalDateTime.parse(p.getValueAsString(), DateTimeConfig.DATE_TIME_FORMATTER);
    }

}
