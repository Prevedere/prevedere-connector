package org.mule.modules.prevedere.model;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.annotations.SerializedName;

public class IndicatorEntry {
	
	public IndicatorEntry() {
		this.values = new ArrayList<Point>();
	}
	
	@SerializedName("Indicator")
	public Indicator indicator;

	@SerializedName("Offset")
    public int offset;

	@SerializedName("Values")
    public List<Point> values; 
}
