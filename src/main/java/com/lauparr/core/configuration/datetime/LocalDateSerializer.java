package com.lauparr.core.configuration.datetime;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.lauparr.core.configuration.DateTimeConfig;

import java.io.IOException;
import java.time.LocalDate;

/**
 * Created by lauparr on 18/11/2016.
 */
public class LocalDateSerializer extends JsonSerializer<LocalDate> {

    @Override
    public void serialize(LocalDate value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        gen.writeString(value.format(DateTimeConfig.DATE_FORMATTER));
    }

}
