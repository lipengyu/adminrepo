package com.lauparr.configuration.datetime;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.lauparr.configuration.DateTimeConfig;

import java.io.IOException;
import java.time.LocalDateTime;

/**
 * Created by lauparr on 18/11/2016.
 */
public class LocalDateTimeSerializer extends JsonSerializer<LocalDateTime> {

    @Override
    public void serialize(LocalDateTime value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        gen.writeString(value.format(DateTimeConfig.DATE_TIME_FORMATTER));
    }

}
