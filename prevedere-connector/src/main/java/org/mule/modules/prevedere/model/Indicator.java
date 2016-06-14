package org.mule.modules.prevedere.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Indicator {
	
	public Indicator(){
		Tags = new ArrayList<String>();
	}
	
	public String Aggregate;

    public String Color;

    public int Count;

    public Date Created;

    public String Description;

    public Date EndTime;

    public Frequency Frequency;

    public boolean Deprecated;

    public Date LastModified;

    public String Name;

    public String Notes;

    public Provider Provider;

    public String ProviderId;

    public Seasonality Seasonality;

    public String Source;

    public Date StartTime;

    public List<String> Tags;

    public String Type;

    public String Units;
}
