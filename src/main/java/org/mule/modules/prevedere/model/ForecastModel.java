/**
 *     PREVEDERE CONFIDENTIAL
 *
 *     2012 Prevedere Incorporated
 *     All Rights Reserved.
 *
 *     NOTICE:  All information contained herein is, and remains the property of Prevedere Incorporated and its suppliers, if any.  The intellectual and technical concepts contained herein are proprietary to Prevedere Incorporated and its suppliers and may be covered by U.S. and Foreign Patents, patents in process, and are protected by trade secret or copyright law. Dissemination of this information or reproduction of this material is strictly forbidden unless prior written permission is obtained from Prevedere Incorporated.
 */
package org.mule.modules.prevedere.model;

import java.util.Date;
import java.util.UUID;

import com.google.gson.annotations.SerializedName;

public class ForecastModel {
	
	@SerializedName("Id")
	public UUID id;

	@SerializedName("ForecastMonths")
    public int forecastMonths;

	@SerializedName("Name")
    public String name;

	@SerializedName("Owner")
    public String owner;

	@SerializedName("Created")
    public Date created;

	@SerializedName("LastModified")
    public Date lastModified;

	@SerializedName("Type")
    public ForecastModelType type;

	@SerializedName("Frequency")
    public Frequency frequency;

	@SerializedName("OutputFrequency")
    public Frequency outputFrequency;

	@SerializedName("Indicator")
    public Indicator indicator;
}
