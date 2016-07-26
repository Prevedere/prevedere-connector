package org.mule.modules.prevedere.model;

import java.util.UUID;

import com.google.gson.annotations.SerializedName;

public class Provider {
	
	@SerializedName("Name")
	public String name;

	@SerializedName("Description")
    public String description;

	@SerializedName("Id")
    public UUID id;

	@SerializedName("Source")
    public String source;
}
