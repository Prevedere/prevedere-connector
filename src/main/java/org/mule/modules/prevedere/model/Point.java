package org.mule.modules.prevedere.model;

import java.util.Date;

import com.google.gson.annotations.SerializedName;

public class Point {
	
	@SerializedName("Value")
	public double value;
	
	@SerializedName("Date")
    public Date date;
}
