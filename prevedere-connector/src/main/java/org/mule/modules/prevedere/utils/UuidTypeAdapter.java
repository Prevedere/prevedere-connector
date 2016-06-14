package org.mule.modules.prevedere.utils;

import java.lang.reflect.Type;
import java.util.UUID;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

public class UuidTypeAdapter implements JsonDeserializer<UUID> {

	@Override
	public UUID deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
		String jsonElement = json.getAsString();

		if (jsonElement.length() == 0) {
			return null;
		}

		String[] components = jsonElement.split("-");
		if (components.length != 5) {
			return UUID.fromString(jsonElement.replaceFirst("(\\p{XDigit}{8})(\\p{XDigit}{4})(\\p{XDigit}{4})(\\p{XDigit}{4})(\\p{XDigit}+)","$1-$2-$3-$4-$5"));
		}

		return UUID.fromString(jsonElement);
	}

}
