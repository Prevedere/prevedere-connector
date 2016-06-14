package org.mule.modules.prevedere;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.mule.api.annotations.Config;
import org.mule.api.annotations.Connector;
import org.mule.api.annotations.Processor;
import org.mule.api.annotations.licensing.RequiresEnterpriseLicense;
import org.mule.api.annotations.param.Optional;
import org.mule.api.annotations.rest.RestQueryParam;
import org.mule.api.annotations.rest.RestUriParam;
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
@SuppressWarnings("deprecation")
@RequiresEnterpriseLicense(allowEval = true)
@Connector(name="prevedere", friendlyName="Prevedere")
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
	 */
    @Processor(friendlyName="Test Connection")
    public String testConnection(@RestUriParam("echo") String echo) throws Exception {
    	String result = this.config.ApiClient.Test(echo);
		
		if(!ApiClient.IsConnected) {
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
     */
    @Processor(friendlyName="Get Forecast Models")
    public List<ForecastModel> getForecastModels() throws Exception {
    	return this.config.ApiClient.GetForecastModels();
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
     * @return a list a indicators matching the search criteria
     * 
     * @throws Exception
     */
    @Processor(friendlyName="Search Indicators")
    public List<Indicator> searchIndicators(@RestUriParam("query")String query, @RestQueryParam("internal")@Optional Boolean internalOnly, @RestQueryParam("frequency")@Optional Frequency frequency, @RestQueryParam("seasonality")@Optional Seasonality seasonality) throws Exception {
		frequency = frequency != null ? frequency : Frequency.Default;
		seasonality = seasonality != null ? seasonality : Seasonality.Default;
    	
    	SearchResults<Indicator> result = this.config.ApiClient.SearchIndicators(query, frequency, seasonality, internalOnly);
    	
    	if(result == null || result.TotalResults == 0) {
    		return null;
    	}
    	
    	return result.Results;
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
     */    
    @Processor(friendlyName="Get Indicator Data")
    public List<Point> getIndicatorData(@RestUriParam("provider") String provider, @RestUriParam("providerId")String providerId, @RestQueryParam("startDate")@Optional Date start, @RestQueryParam("frequency")@Optional Frequency frequency, @RestQueryParam("calculation")@Optional Calculation calculation, @RestQueryParam("offset")@Optional Integer offset) throws Exception {
    	String hyphenatedProvider =  provider.replaceFirst( "(\\p{XDigit}{8})(\\p{XDigit}{4})(\\p{XDigit}{4})(\\p{XDigit}{4})(\\p{XDigit}+)", "$1-$2-$3-$4-$5" );
    	
    	//some stupid java stuff here
    	offset = offset != null ? offset : new Integer(0);
		
    	return this.config.ApiClient.GetSeriesData(UUID.fromString(hyphenatedProvider), providerId, start, frequency, calculation, offset);
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
     */
    @Processor(friendlyName="Get Forecast Model Data")
    public List<Point> getForecastModelData(@RestUriParam("modelId") String modelId, @RestQueryParam("cutoff")@Optional Date cutoff) throws Exception {
    	String hyphenatedModelId =  modelId.replaceFirst( "(\\p{XDigit}{8})(\\p{XDigit}{4})(\\p{XDigit}{4})(\\p{XDigit}{4})(\\p{XDigit}+)", "$1-$2-$3-$4-$5" );
    	
    	return this.config.ApiClient.GetModelForecast(UUID.fromString(hyphenatedModelId), cutoff);
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
     */
    @Processor(friendlyName="Get Raw Model Data")
    public RawModel getRawModelData(@RestUriParam("modelId") String modelId, @RestQueryParam("useForecastFrequency")@Optional Boolean useForecastFrequency, @RestQueryParam("cutoffDate")@Optional Date cutoff) throws Exception {
    	String hyphenatedModelId =  modelId.replaceFirst("(\\p{XDigit}{8})(\\p{XDigit}{4})(\\p{XDigit}{4})(\\p{XDigit}{4})(\\p{XDigit}+)", "$1-$2-$3-$4-$5" );
    	
    	useForecastFrequency = useForecastFrequency != null ? useForecastFrequency : false;
    	
    	return this.config.ApiClient.GetRawModel(UUID.fromString(hyphenatedModelId), useForecastFrequency, cutoff);
    }
    
    /**
     * Get forecast model summary information
     * 
     * @param modelId
     * 			the UUID of the forecast model
     * @return forecast result values for high, average and low forecasts
     * @throws Exception
     */
    @Processor(friendlyName="Get Forecast Summary Data")
    public ForecastResult getForecastSummaryData(@RestUriParam("modelId") String modelId) throws Exception {
    	String hyphenatedModelId =  modelId.replaceFirst( "(\\p{XDigit}{8})(\\p{XDigit}{4})(\\p{XDigit}{4})(\\p{XDigit}{4})(\\p{XDigit}+)", "$1-$2-$3-$4-$5" );
    	
    	return this.config.ApiClient.GetForecastSummary(UUID.fromString(hyphenatedModelId));
    }
    
    public ConnectorConfig getConfig() {
        return config;
    }

    public void setConfig(ConnectorConfig config) {
        this.config = config;
    }

}