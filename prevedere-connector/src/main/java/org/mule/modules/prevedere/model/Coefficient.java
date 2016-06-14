package org.mule.modules.prevedere.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Coefficient {

	public Coefficient()
    {
        Confidence95 = "";
        Type = "";
        IndicatorName = "";
        Dates = new ArrayList<Date>();
    }

    public Coefficient(String type, double value, double error, double tValue, String confidence)
    {
        Value = value;
        StandardError = error;
        TValue = tValue;
        Confidence95 = confidence;
        Type = type;
        IndicatorName = "";
        Dates = new ArrayList<Date>();
    }

    public double Value;

    public double StandardError;

    public double TValue;

    public String Confidence95;

    public String Type;

    public String IndicatorName;

    public List<Date> Dates;

    public String ToString()
    {
        return "Value: " + Value + " Std Err:" + StandardError + " T:" + TValue + " 95%:" + Confidence95;
    }
}
