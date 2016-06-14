package org.mule.modules.prevedere.model;

import java.util.ArrayList;
import java.util.List;

public class IndicatorEntry {
	
	public IndicatorEntry() {
		Values = new ArrayList<Point>();
	}
	
	public Indicator Indicator;

    public int Offset;

    public List<Point> Values; 
}
