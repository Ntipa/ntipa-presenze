package com.ipublic.ntipa.presenze.domain.util;

import java.io.IOException;
import java.util.Locale;

import org.joda.time.LocalDateTime;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.ISODateTimeFormat;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

/**
 * Custom Jackson serializer for displaying Joda Time dates.
 */
public class CustomLocalDateTimeSerializer extends JsonSerializer<LocalDateTime> {

	DateTimeFormatter formatter = ISODateTimeFormat.dateTime();

    @Override
    public void serialize(LocalDateTime value, JsonGenerator generator,
                          SerializerProvider serializerProvider)
            throws IOException {
		String s = formatter.withLocale(Locale.getDefault() ).print(value); 
        generator.writeString(s);
    }
}
