package org.mule.modules.prevedere.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class RawModel {

    public RawModel()
    {
        this.Indicators = new ArrayList<IndicatorEntry>();
        this.Coefficients = new ArrayList<List<Coefficient>>();
    }
    
	public List<IndicatorEntry> Indicators;

    public List<List<Coefficient>> Coefficients;

    public Date StartDate;
}
