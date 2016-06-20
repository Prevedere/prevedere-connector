package org.mule.modules.prevedere.utils;

import java.lang.reflect.Type;
import java.util.Date;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.format.ISODateTimeFormat;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;

public class DateTypeAdapter implements JsonDeserializer<Date> {

	static final org.joda.time.format.DateTimeFormatter DATE_TIME_WITH_ZONE_FORMATTER = ISODateTimeFormat.dateTime().withZone(DateTimeZone.UTC);
	static final org.joda.time.format.DateTimeFormatter DATE_TIME_FORMATTER = ISODateTimeFormat.dateTime();

	@Override
	public Date deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) {
		String jsonElement = json.getAsString();
		
		if(jsonElement.length() == 0) {
			return null;
		}
		
		return new DateTime(jsonElement).toDate();
	}

}
