package org.mule.modules.prevedere.model;

import com.google.gson.annotations.SerializedName;

public class ForecastResult {
	
	@SerializedName("High")
	public ForecastResultValues high;

	@SerializedName("Average")
    public ForecastResultValues average;

	@SerializedName("Low")
    public ForecastResultValues low;
}
