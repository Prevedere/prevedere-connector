package org.mule.modules.prevedere.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.google.gson.annotations.SerializedName;

public class Coefficient {

	public Coefficient()
    {
        this.confidence95 = "";
        this.type = "";
        this.indicatorName = "";
        this.dates = new ArrayList<Date>();
    }

    public Coefficient(String type, double value, double error, double tValue, String confidence)
    {
        this.value = value;
        this.standardError = error;
        this.tValue = tValue;
        this.confidence95 = confidence;
        this.type = type;
        this.indicatorName = "";
        this.dates = new ArrayList<Date>();
    }

    @SerializedName("Value")
    public double value;

    @SerializedName("StandardError")
    public double standardError;

    @SerializedName("TValue")
    public double tValue;

    @SerializedName("Confidence95")
    public String confidence95;

    @SerializedName("Type")
    public String type;

    @SerializedName("IndicatorName")
    public String indicatorName;

    @SerializedName("Dates")
    public List<Date> dates;

    public String toString()
    {
        return "Value: " + this.value + " Std Err:" + this.standardError + " T:" + this.tValue + " 95%:" + this.confidence95;
    }
}
