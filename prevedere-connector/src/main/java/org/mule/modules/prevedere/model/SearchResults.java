package org.mule.modules.prevedere.model;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.annotations.SerializedName;

public class SearchResults<TResult> {
	
	public SearchResults()
    {
        this.results = new ArrayList<TResult>();
    }

	@SerializedName("Page")
    public int page;

	@SerializedName("PageSize")
    public int pageSize;

	@SerializedName("TotalResults")
    public long totalResults;

	@SerializedName("Results")
    public List<TResult> results;
}
