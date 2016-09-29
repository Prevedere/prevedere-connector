/**
 *     PREVEDERE CONFIDENTIAL
 *
 *     2012 Prevedere Incorporated
 *     All Rights Reserved.
 *
 *     NOTICE:  All information contained herein is, and remains the property of Prevedere Incorporated and its suppliers, if any.  The intellectual and technical concepts contained herein are proprietary to Prevedere Incorporated and its suppliers and may be covered by U.S. and Foreign Patents, patents in process, and are protected by trade secret or copyright law. Dissemination of this information or reproduction of this material is strictly forbidden unless prior written permission is obtained from Prevedere Incorporated.
 */
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
