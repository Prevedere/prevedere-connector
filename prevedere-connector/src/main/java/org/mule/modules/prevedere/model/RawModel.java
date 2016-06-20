package org.mule.modules.prevedere.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.google.gson.annotations.SerializedName;

public class RawModel {

    public RawModel()
    {
        this.indicators = new ArrayList<IndicatorEntry>();
        this.coefficients = new ArrayList<List<Coefficient>>();
    }
    
    @SerializedName("Values")
	public List<IndicatorEntry> indicators;

    @SerializedName("Values")
    public List<List<Coefficient>> coefficients;

    @SerializedName("Values")
    public Date startDate;
}
