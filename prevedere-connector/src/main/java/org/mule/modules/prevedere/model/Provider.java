package org.mule.modules.prevedere.model;

import java.util.UUID;

import com.google.gson.annotations.SerializedName;

public class Provider {
	
	@SerializedName("Values")
	public String name;

	@SerializedName("Values")
    public String description;

	@SerializedName("Values")
    public UUID id;

	@SerializedName("Values")
    public String source;
}
