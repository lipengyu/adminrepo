package com.lauparr.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.lauparr.configuration.datetime.LocalDateDeserializer;
import com.lauparr.configuration.datetime.LocalDateSerializer;
import com.lauparr.configuration.datetime.LocalDateTimeDeserializer;
import com.lauparr.configuration.datetime.LocalDateTimeSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static java.time.format.DateTimeFormatter.ofPattern;

/**
 * Created by lauparr on 18/11/2016.
 */
@Configuration
public class DateTimeConfig {

    public static final DateTimeFormatter DATE_FORMATTER = ofPattern("dd/MM/yyyy");

    public static final DateTimeFormatter DATE_TIME_FORMATTER = ofPattern("dd/MM/yyyy HH:mm:ss");

    @Bean
    @Primary
    public ObjectMapper serializingObjectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        JavaTimeModule javaTimeModule = new JavaTimeModule();
        javaTimeModule.addSerializer(LocalDate.class, new LocalDateSerializer());
        javaTimeModule.addDeserializer(LocalDate.class, new LocalDateDeserializer());
        javaTimeModule.addSerializer(LocalDateTime.class, new LocalDateTimeSerializer());
        javaTimeModule.addDeserializer(LocalDateTime.class, new LocalDateTimeDeserializer());
        objectMapper.registerModule(javaTimeModule);
        return objectMapper;
    }

}
