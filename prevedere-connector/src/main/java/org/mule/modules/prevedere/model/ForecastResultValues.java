package org.mule.modules.prevedere.model;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.annotations.SerializedName;

public class ForecastResultValues {
	public ForecastResultValues()
    {
        this.values = new ArrayList<Point>();
    }

	@SerializedName("Values")
    public List<Point> values;

	@SerializedName("LeadTime")
    public int leadTime;

	@SerializedName("DirectionalAccuracy")
    public double directionalAccuracy;

	@SerializedName("Accuracy")
    public double accuracy;

	@SerializedName("Sum")
    public double sum;

	@SerializedName("Mape")
    public double mape;
}
