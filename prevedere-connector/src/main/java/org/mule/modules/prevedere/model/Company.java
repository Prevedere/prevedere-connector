package org.mule.modules.prevedere.model;

import java.util.Date;
import java.util.UUID;

import com.google.gson.annotations.SerializedName;

public class Company {

	@SerializedName("Id")
	public UUID id;

	@SerializedName("Name")
	public String name;
	
	@SerializedName("Prefix")
	public String prefix;

	@SerializedName("Description")
	public String description;
	
	@SerializedName("CreateDate")
    public Date created;
    
}
