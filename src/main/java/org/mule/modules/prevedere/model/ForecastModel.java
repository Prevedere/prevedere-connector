package org.mule.modules.prevedere.model;

import java.util.Date;
import java.util.UUID;

import com.google.gson.annotations.SerializedName;

public class ForecastModel {
	
	@SerializedName("Id")
	public UUID id;

	@SerializedName("ForecastMonths")
    public int forecastMonths;

	@SerializedName("Name")
    public String name;

	@SerializedName("Owner")
    public String owner;

	@SerializedName("Created")
    public Date created;

	@SerializedName("LastModified")
    public Date lastModified;

	@SerializedName("Type")
    public ForecastModelType type;

	@SerializedName("Frequency")
    public Frequency frequency;

	@SerializedName("OutputFrequency")
    public Frequency outputFrequency;

	@SerializedName("Indicator")
    public Indicator indicator;
}
