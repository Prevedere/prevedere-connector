package org.mule.modules.prevedere.model;

import com.google.gson.annotations.SerializedName;

public class ApiResult {

	@SerializedName("JsonResult")
	public String jsonResult;

	@SerializedName("Success")
    public boolean success;

	@SerializedName("ErrorMessage")
    public String errorMessage;    
}
