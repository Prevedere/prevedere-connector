/**
 *     PREVEDERE CONFIDENTIAL
 *
 *     2012 Prevedere Incorporated
 *     All Rights Reserved.
 *
 *     NOTICE:  All information contained herein is, and remains the property of Prevedere Incorporated and its suppliers, if any.  The intellectual and technical concepts contained herein are proprietary to Prevedere Incorporated and its suppliers and may be covered by U.S. and Foreign Patents, patents in process, and are protected by trade secret or copyright law. Dissemination of this information or reproduction of this material is strictly forbidden unless prior written permission is obtained from Prevedere Incorporated.
 */
package org.mule.modules.prevedere.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.google.gson.annotations.SerializedName;

public class Indicator {
	
	public Indicator(){
		this.tags = new ArrayList<String>();
	}
	
	@SerializedName("Accessibility")
	public ProviderAcces accesssibility;
	
	@SerializedName("Aggregate")
	public String aggregate;

	@SerializedName("Calculation")
	public Calculation calculation;
	
	@SerializedName("Color")
    public String color;

	@SerializedName("Count")
    public int count;

	@SerializedName("Created")
    public Date created;
	
	@SerializedName("Description")
    public String description;

	@SerializedName("EndTime")
    public Date endTime;

	@SerializedName("Frequency")
    public Frequency frequency;

	@SerializedName("Deprecated")
    public boolean deprecated;

	@SerializedName("LastModified")
    public Date lastModified;

	@SerializedName("Name")
    public String name;

	@SerializedName("Notes")
    public String notes;

	@SerializedName("Provider")
    public Provider provider;

	@SerializedName("ProviderId")
    public String providerId;
	
	@SerializedName("Seasonality")
    public Seasonality seasonality;

	@SerializedName("Source")
    public String source;

    @SerializedName("StartTime")
    public Date startTime;

    @SerializedName("Tags")
    public List<String> tags;

    @SerializedName("Type")
    public String type;

    @SerializedName("Units")
    public String units;
}
