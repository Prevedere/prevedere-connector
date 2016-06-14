package org.mule.modules.prevedere.model;

import java.util.ArrayList;
import java.util.List;

public class SearchResults<TResult> {
	
	public SearchResults()
    {
        Results = new ArrayList<TResult>();
    }

    public int Page;

    public int PageSize;

    public long TotalResults;

    public List<TResult> Results;
}
