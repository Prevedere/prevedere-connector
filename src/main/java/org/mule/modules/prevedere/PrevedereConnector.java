/**
 *     PREVEDERE CONFIDENTIAL
 *
 *     2012 Prevedere Incorporated
 *     All Rights Reserved.
 *
 *     NOTICE:  All information contained herein is, and remains the property of Prevedere Incorporated and its suppliers, if any.  The intellectual and technical concepts contained herein are proprietary to Prevedere Incorporated and its suppliers and may be covered by U.S. and Foreign Patents, patents in process, and are protected by trade secret or copyright law. Dissemination of this information or reproduction of this material is strictly forbidden unless prior written permission is obtained from Prevedere Incorporated.
 */
package org.mule.modules.prevedere;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.mule.api.annotations.Config;
import org.mule.api.annotations.Connector;
import org.mule.api.annotations.Processor;
import org.mule.api.annotations.licensing.RequiresEnterpriseLicense;
import org.mule.api.annotations.param.Optional;
import org.mule.modules.prevedere.client.ApiClient;
import org.mule.modules.prevedere.config.ConnectorConfig;
import org.mule.modules.prevedere.model.Calculation;
import org.mule.modules.prevedere.model.ForecastModel;
import org.mule.modules.prevedere.model.ForecastResult;
import org.mule.modules.prevedere.model.Frequency;
import org.mule.modules.prevedere.model.Indicator;
import org.mule.modules.prevedere.model.Point;
import org.mule.modules.prevedere.model.RawModel;
import org.mule.modules.prevedere.model.SearchResults;
import org.mule.modules.prevedere.model.Seasonality;

/**
 * Prevedere AnyPoint Connector
 * 
 * @author Prevedere
 *
 */
@RequiresEnterpriseLicense(allowEval = true)
@Connector(name="prevedere", friendlyName="Prevedere", minMuleVersion = "3.8")
public class PrevedereConnector {

	@Config
	ConnectorConfig config;

	/**
	 * Test the current configuration and API Authorization
	 * 
	 * @param echo
	 * 			a test value to be echoed back to the client
	 * @return Echo: {echo} 
	 * 
	 * @throws Exception
	 * 			if API key is invalid
	 */
    @Processor(friendlyName="Test Connection")
    public String testConnection(String echo) throws Exception {
    	String result = this.config.getClient().test(echo);
		
    	if(!this.config.getClient().isConnected()) {
			throw new Exception("Could not connect to API.");
		}
		
		return result;
    }
    
    /**
     * Retrieve all Forecast Models available to the API Key's associated company
     * 
     * @return a list of all Forecast Models
     * 
     * @throws Exception
     * 			if API key is invalid
	 */
    @Processor(friendlyName="Get Forecast Models")
    public List<ForecastModel> getForecastModels() throws Exception {
    	return this.config.getClient().getForecastModels();
    }
    
    /**
     * Search Prevedere's global economic indicator database and locally imported company indicators
     * 
     * @param query
     * 			the text to search for
     * @param internalOnly
     * 			optional parameter indicating whether to perform a search on company imported indicators (internal), otherwise the search will be performed on Prevedere's global repository
     * @param frequency
     * 			optional parameter to filter search results by frequency
     * @param seasonality
     * 			optional parameter to filter search results seasonality
     * 
     * @return a list a indicators matching the search criteria
     * 
     * @throws Exception
     * 			if API key is invalid
	 */
    @Processor(friendlyName="Search Indicators")
    public List<Indicator> searchIndicators(String query, @Optional Boolean internalOnly, @Optional Frequency frequency, @Optional Seasonality seasonality) throws Exception {
		Frequency searchFrequency = frequency != null ? frequency : Frequency.Default;
		Seasonality searchseasonality = seasonality != null ? seasonality : Seasonality.Default;
    	
    	SearchResults<Indicator> result = this.config.getClient().searchIndicators(query, searchFrequency, searchseasonality, internalOnly);
    	
    	if(result == null || result.totalResults == 0) {
    		return Collections.<Indicator>emptyList();
    	}
    	
    	return result.results;
    }

    /**
     * Get indicator time series data
     * 
     * @param provider 
     * 			the provider UUID for this indicator
     * @param providerId
     * 			the provider id for this indicator
     * @param start
     * 			optional parameter to start the data at a given date
     * @param frequency
     * 			optional parameter to convert the time series data to a compatible frequency
     * @param calculation
     * 			optional parameter to convert the time series data to a compatible calculation
     * @param offset
     * 			optional parameter to shift time series data by integer value
     * @return a list of date and double pairs
     * 
     * @throws Exception
     * 			if API key is invalid
	 */    
    @Processor(friendlyName="Get Indicator Data")
    public List<Point> getIndicatorData(String provider, String providerId, @Optional Date start, @Optional Frequency frequency, @Optional Calculation calculation, @Optional Integer offset) throws Exception {
    	//some stupid java stuff here
    	int indicatorOffset = offset != null ? offset : new Integer(0);
		
    	return this.config.getClient().getSeriesData(getUUID(provider), providerId, start, frequency, calculation, indicatorOffset);
    }
    
    /**
     * Get forecast model time series data
     * 
     * @param modelId
     * 			the UUID of the forecast model
     * @param cutoff
     * 			optional parameter to start the forecast, useful for back-testing a model
     * @return a list of date and double pairs
     * 
     * @throws Exception
     * 			if API key is invalid
	 */
    @Processor(friendlyName="Get Forecast Model Data")
    public List<Point> getForecastModelData(String modelId, @Optional Date cutoff) throws Exception {
    	return this.config.getClient().getModelForecast(getUUID(modelId), cutoff);
    }
    
    /**
     * Get forecast model component information
     * 
     * @param modelId
     * 			the UUID of the forecast model
     * @param useForecastFrequency
     * 			optional parameter to use the forecast frequency when retrieving component time series data
     * @param cutoff
     * 			optional parameter to start the forecast
     * @return a RawModel object containing a list of component indicator information and list of segmented statistical information
     * 
     * @throws Exception
     * 			if API key is invalid
	 */
    @Processor(friendlyName="Get Raw Model Data")
    public RawModel getRawModelData(String modelId, @Optional Boolean useForecastFrequency, @Optional Date cutoff) throws Exception {
    	boolean shouldUseForecastFrequency = useForecastFrequency != null ? useForecastFrequency : false;
    	
    	return this.config.getClient().getRawModel(getUUID(modelId), shouldUseForecastFrequency, cutoff);
    }
    
    /**
     * Get forecast model summary information
     * 
     * @param modelId
     * 			the UUID of the forecast model
     * @return forecast result values for high, average and low forecasts
     * @throws Exception
     * 			if API key is invalid
	 */
    @Processor(friendlyName="Get Forecast Summary Data")
    public ForecastResult getForecastSummaryData(String modelId) throws Exception {
    	return this.config.getClient().getForecastSummary(getUUID(modelId));
    }
    
    public ConnectorConfig getConfig() {
        return config;
    }

    public void setConfig(ConnectorConfig config) {
        this.config = config;
    }

    private static UUID getUUID(String id) {
    	return UUID.fromString(id.replaceFirst( "(\\p{XDigit}{8})(\\p{XDigit}{4})(\\p{XDigit}{4})(\\p{XDigit}{4})(\\p{XDigit}+)", "$1-$2-$3-$4-$5" ));
    }
    
}