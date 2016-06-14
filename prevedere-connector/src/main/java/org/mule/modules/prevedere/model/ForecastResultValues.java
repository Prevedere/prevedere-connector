package org.mule.modules.prevedere.model;

import java.util.ArrayList;
import java.util.List;

public class ForecastResultValues {
	public ForecastResultValues()
    {
        Values = new ArrayList<Point>();
    }

    public List<Point> Values;

    public int LeadTime;

    public double DirectionalAccuracy;

    public double Accuracy;

    public double Sum;

    public double Mape;
}
