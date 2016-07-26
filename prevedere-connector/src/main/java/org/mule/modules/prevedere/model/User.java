package org.mule.modules.prevedere.model;

import java.util.Date;
import java.util.UUID;

import com.google.gson.annotations.SerializedName;

public class User {
	
	@SerializedName("Id")
	public UUID id;

	@SerializedName("Username")
	public String username;

	@SerializedName("FirstName")
	public String firstName;

	@SerializedName("LastName")
	public String lastName;

	@SerializedName("Created")
    public Date created;

	@SerializedName("Modified")
	public Date modified;

	@SerializedName("LastLogin")
	public Date lastLogin;

	@SerializedName("IsLocked")
	public boolean isLocked;
}
