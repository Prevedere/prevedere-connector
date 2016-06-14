package org.mule.modules.prevedere.client;

import java.io.IOException;
import java.lang.reflect.Type;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.mule.modules.prevedere.model.*;
import org.mule.modules.prevedere.utils.DateTypeAdapter;
import org.mule.modules.prevedere.utils.UuidTypeAdapter;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class ApiClient {

	protected static OkHttpClient Client = new OkHttpClient();

	protected static String ApiUrlBase = "https://api.prevedere.com";

	protected static String ApiKey;

	public static void setApiKey(String value) {
		IsConnected = false;		
		ApiKey = value;
	}
	
	public static boolean IsConnected;

	public ApiClient() {}
	
	public ApiClient(String apiKey) {
		ApiKey = apiKey;
	}
	
	public String Test(String echo) throws Exception {
		this.AssertConnected();
		
		URI url = ApiUrls.echoUrl(echo);

		Echo result = ExecuteRequest(url, Echo.class);

		return result.Echo;
	}

	public void Disconnect() {
		ApiClient.IsConnected = false;
	}

	public SearchResults<Indicator> SearchIndicators(String query) throws Exception {
		return this.SearchIndicators(query, null, null, false);
	}

	public SearchResults<Indicator> SearchIndicators(String query, Frequency frequency, Seasonality seasonality, Boolean internal) throws Exception {
		return this.SearchIndicators(query, frequency, seasonality, internal, null, null, null);
	}

	public SearchResults<Indicator> SearchIndicators(String query, Frequency frequency, Seasonality seasonality, Boolean internalOnly, Boolean includeForecasted, Integer page, Integer pageSize) throws Exception {
		this.AssertConnected();

		internalOnly = internalOnly != null ? internalOnly : false;
		includeForecasted = includeForecasted != null ? includeForecasted : false;
		page = page != null ? page : 0;
		pageSize = pageSize != null ? pageSize : 50;

		URI url = ApiUrls.SearchUrl(query, frequency, seasonality, internalOnly, includeForecasted, page, pageSize);

		SearchResults<Indicator> result = ExecuteRequest(url, new TypeToken<SearchResults<Indicator>>(){}.getType());
		
		return result;
	}

	public List<ForecastModel> GetForecastModels() throws Exception {
		this.AssertConnected();

		URI url = ApiUrls.ForcastModelsUrl();
		
		List<ForecastModel> result = ExecuteRequest(url, new TypeToken<List<ForecastModel>>(){}.getType());
		
		return result;
	}

	public List<Point> GetModelForecast(ForecastModel model, Date cutoffDate) throws Exception {
		return this.GetModelForecast(model.Id, cutoffDate);
	}

	public List<Point> GetModelForecast(UUID modelId, Date cutoffDate) throws Exception {
		this.AssertConnected();

		URI url = ApiUrls.ForecastModelDataUrl(modelId, cutoffDate);

		List<Point> result = ExecuteRequest(url, new TypeToken<List<Point>>(){}.getType());
		
		return result;
	}

	public ForecastResult GetForecastSummary(ForecastModel model) throws Exception {
		return GetForecastSummary(model.Id);
	}
	
	public ForecastResult GetForecastSummary(UUID modelId) throws Exception {
		this.AssertConnected();

		URI url = ApiUrls.ForecastSummaryUrl(modelId);

		return ExecuteRequest(url, ForecastResult.class);
	}

	public RawModel GetRawModel(UUID modelId, boolean useForecastFrequency, Date cutoffDate) throws Exception {
		this.AssertConnected();

		URI url = ApiUrls.RawModelDataUrl(modelId, useForecastFrequency, cutoffDate);

		return ExecuteRequest(url, RawModel.class);
	}

	public List<Point> GetSeriesData(Indicator indicator) throws Exception {
		return this.GetSeriesData(indicator.Provider.Id, indicator.ProviderId, null);
	}

	public List<Point> GetSeriesData(UUID provider, String providerId, Frequency frequency) throws Exception {
		this.AssertConnected();

		URI url = ApiUrls.SeriesUrl(provider, providerId, frequency);

		List<Point> result = ExecuteRequest(url, new TypeToken<List<Point>>(){}.getType());
		
		return result;
	}

	public List<Point> GetSeriesData(UUID provider, String providerId) throws Exception {
		return GetSeriesData(provider, providerId, null, null, null, 0);
	}
	
	public List<Point> GetSeriesData(UUID provider, String providerId, Date startTime, Frequency frequency, Calculation calculation, int offset) throws Exception {
		this.AssertConnected();

		URI url = ApiUrls.SeriesUrl(provider, providerId, startTime, frequency, calculation, offset);

		List<Point> result = ExecuteRequest(url, new TypeToken<List<Point>>(){}.getType());
		
		return result;
	}

	private void AssertConnected() throws Exception {
		if(ApiClient.IsConnected) {
			return;
		}
		
		if(Connect()) {
			ApiClient.IsConnected = true;
			return;
		}
		
		throw new Exception("Could not connect with API Key " + ApiClient.ApiKey);
	}

	private boolean Connect() throws Exception {
		URI url = ApiUrls.echoUrl("connect");

		Echo result = ExecuteRequest(url, Echo.class);
		
		return result != null;
	}
	
	private <TResponse> TResponse ExecuteRequest(URI url, Type responseClass) throws IOException{
		Request request = new Request.Builder().url(url.toASCIIString()).build();

		Response response = Client.newCall(request).execute();
		
		String content = response.body().string();
		
		Gson gson = new GsonBuilder()
				.registerTypeAdapter(Date.class, new DateTypeAdapter())
				.registerTypeAdapter(UUID.class, new UuidTypeAdapter())
				.create();
		
		TResponse result = gson.fromJson(content, responseClass);
		
		return result;
	}

	private static class ApiUrls {
		public static URI echoUrl(String echo) throws MalformedURLException, URISyntaxException {
			return ApiUrls.GetURI(ApiUrlBase + "/test/" + echo + "?apikey=" + ApiClient.ApiKey);
		}

		public static URI ForcastModelsUrl() throws MalformedURLException, URISyntaxException {
			return ApiUrls.GetURI(ApiUrlBase + "/forecastmodels?apikey=" + ApiClient.ApiKey);
		}

		public static URI RawModelDataUrl(UUID modelId, boolean useForecastFrequency, Date cutoffDate) throws MalformedURLException, URISyntaxException {
			String url = ApiUrlBase + "/rawModel/" + modelId.toString().replace("-", "") + "/" + useForecastFrequency + "?apikey=" + ApiClient.ApiKey;

			if (cutoffDate != null) {
				url += "&AsOfDate=" + new SimpleDateFormat("yyyy-MM-dd").format(cutoffDate);
			}

			return ApiUrls.GetURI(url);
		}
		
		@SuppressWarnings("unused")
		public static URI ForecastModelDataUrl(ForecastModel model, Date cutoffDate) throws MalformedURLException, URISyntaxException {
			return ApiUrls.ForecastModelDataUrl(model.Id, cutoffDate);
		}

		public static URI ForecastModelDataUrl(UUID modelId, Date cutoffDate) throws MalformedURLException, URISyntaxException {
			String url = ApiUrlBase + "/forecast/" + modelId.toString().replace("-", "") + "?apikey=" + ApiClient.ApiKey;

			if (cutoffDate != null) {
				url += "&asofdate=" + new SimpleDateFormat("yyyy-MM-dd").format(cutoffDate);
			}

			return ApiUrls.GetURI(url);
		}

		@SuppressWarnings("unused")
		public static URI ForecastSummaryUrl(ForecastModel model) throws MalformedURLException, URISyntaxException {
			return ApiUrls.ForecastSummaryUrl(model.Id);
		}

		public static URI ForecastSummaryUrl(UUID modelId) throws MalformedURLException, URISyntaxException {
			String url = ApiUrlBase + "/forecast/summary/" + modelId.toString().replace("-", "") + "?apikey=" + ApiClient.ApiKey;

			return ApiUrls.GetURI(url);
		}

		@SuppressWarnings("unused")
		public static URI SeriesUrl(Indicator indicator) throws MalformedURLException, URISyntaxException {
			return SeriesUrl(indicator, null);
		}

		public static URI SeriesUrl(Indicator indicator, Frequency frequency) throws MalformedURLException, URISyntaxException {
			return SeriesUrl(indicator.Provider.Id, indicator.ProviderId, frequency);
		}

		public static URI SeriesUrl(UUID provider, String providerId, Frequency frequency) throws MalformedURLException, URISyntaxException {
			String url = ApiUrlBase + "/indicator/series/" + provider.toString().replace("-", "") + "/" + providerId + "?apikey=" + ApiClient.ApiKey;

			if (frequency != null) {
				url += "&Frequency=" + frequency;
			}
			
			return ApiUrls.GetURI(url);
		}

		public static URI SeriesUrl(UUID provider, String providerId, Date startTime, Frequency frequency, Calculation calculation, int offset) throws MalformedURLException, URISyntaxException {
			String url = ApiUrlBase + "/indicator/series/" + provider.toString().replace("-", "") + "/" + providerId + "?apikey=" + ApiClient.ApiKey;

			if(startTime != null) {
				url += "&StartDate=" + new SimpleDateFormat("yyyy-MM-dd").format(startTime);
			}			
			if (frequency != null && frequency != Frequency.Default) {
				url += "&Frequency=" + frequency;
			}
			if (calculation != null && calculation != Calculation.Default) {
				url += "&Calculation=" + calculation;
			}	
			if(offset != 0) {
				url += "&offset=" + offset;
			}

			return ApiUrls.GetURI(url);
		}

		public static URI SearchUrl(String query, Frequency frequency, Seasonality seasonality, boolean internalOnly, boolean includeForecasted, int page, int pageSize) throws MalformedURLException, URISyntaxException {
			String url = ApiUrlBase + "/search?query=" + query + "&apikey=" + ApiClient.ApiKey + "&internal=" + internalOnly + "&includeforecasted=" + includeForecasted + "&page=" + page + "&pageSize=" + pageSize;

			if (frequency != null || frequency != Frequency.Default) {
				url += "&Frequency=" + frequency;
			}
			if (seasonality != null || seasonality != Seasonality.Default) {
				url += "&Seasonality=" + seasonality;
			}

			return ApiUrls.GetURI(url);
		}

		private static URI GetURI(String urlString) throws MalformedURLException, URISyntaxException {
			URL url = new URL(urlString);
			
			return new URI(url.getProtocol(), url.getUserInfo(), url.getHost(), url.getPort(), url.getPath(), url.getQuery(), url.getRef());
		}
	}
}
