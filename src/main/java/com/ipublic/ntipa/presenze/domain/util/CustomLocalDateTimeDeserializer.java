package com.ipublic.ntipa.presenze.domain.util;

import java.io.IOException;
import java.util.Locale;

import org.joda.time.LocalDateTime;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.ISODateTimeFormat;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

public class CustomLocalDateTimeDeserializer extends
		JsonDeserializer<LocalDateTime> {

	private DateTimeFormatter formatter = ISODateTimeFormat.dateTime();

	@Override
	public LocalDateTime deserialize(JsonParser jp, DeserializationContext ctxt)
			throws IOException, JsonProcessingException {

		String s = jp.getText();
		if(!s.endsWith("Z"))
			s= s+"Z";
		return LocalDateTime.parse( s , formatter.withLocale(Locale.getDefault()  ) );

	}
}