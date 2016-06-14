package org.mule.modules.prevedere.model;

import java.util.Date;
import java.util.UUID;

public class ForecastModel {
	public UUID Id;

    public int ForecastMonths;

    public String Name;

    public String Owner;

    public Date Created;

    public Date LastModified;

    public ForecastModelType Type;

    public Frequency Frequency;

    public Frequency OutputFrequency;

    public Indicator Indicator;
}
