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

	private static String DefaultApiUrlBase = "https://api.prevedere.com";
	protected static String ApiUrlBase = "https://api.prevedere.com";

	private static String DefaultApiKey = "071cf3ed952041a6a0673755988b0b6f";
	protected static String ApiKey;

	protected static Context Context;

	public static void setApiKey(String value) {
		ApiClient.Context = null;
		ApiClient.ApiKey = value;
	}

	public ApiClient() {
		ApiUrlBase = DefaultApiUrlBase;
		ApiKey = DefaultApiKey;
	}
	
	public ApiClient(String baseUrl) {
		ApiUrlBase = baseUrl;
		ApiKey = DefaultApiKey;
	}
	
	public ApiClient(String baseUrl, String apiKey) {
		ApiUrlBase = baseUrl;
		ApiKey = apiKey;
	}

	public String test(String echo) throws Exception {
		this.assertConnected();

		URI url = ApiUrls.echoUrl(echo);

		Echo result = executeRequest(url, Echo.class);

		return result.echo;
	}

	public static boolean isConnected() {
		return ApiClient.Context != null;
	}

	public static void disconnect() {
		ApiClient.Context = null;
	}

	public SearchResults<Indicator> searchIndicators(String query) throws Exception {
		return this.searchIndicators(query, null, null, false);
	}

	public SearchResults<Indicator> searchIndicators(String query, Frequency frequency, Seasonality seasonality,
			Boolean internal) throws Exception {
		return this.searchIndicators(query, frequency, seasonality, internal, null, null, null);
	}

	public SearchResults<Indicator> searchIndicators(String query, Frequency frequency, Seasonality seasonality,
			Boolean internalOnly, Boolean includeForecasted, Integer page, Integer pageSize) throws Exception {
		this.assertConnected();

		boolean searchInternalOnly = internalOnly != null ? internalOnly : false;
		boolean searchIncludeForecasted = includeForecasted != null ? includeForecasted : false;
		int searchPage = page != null ? page : 0;
		int searchPageSize = pageSize != null ? pageSize : 50;

		URI url = ApiUrls.searchUrl(query, frequency, seasonality, searchInternalOnly, searchIncludeForecasted, searchPage, searchPageSize);

		return executeRequest(url, new TypeToken<SearchResults<Indicator>>() {}.getType());
	}

	public List<ForecastModel> getForecastModels() throws Exception {
		this.assertConnected();

		URI url = ApiUrls.forcastModelsUrl();

		return executeRequest(url, new TypeToken<List<ForecastModel>>() {}.getType());
	}

	public List<Point> getModelForecast(ForecastModel model, Date cutoffDate) throws Exception {
		return this.getModelForecast(model.id, cutoffDate);
	}

	public List<Point> getModelForecast(UUID modelId, Date cutoffDate) throws Exception {
		this.assertConnected();

		URI url = ApiUrls.forecastModelDataUrl(modelId, cutoffDate);

		return executeRequest(url, new TypeToken<List<Point>>() {}.getType());	
	}

	public ForecastResult getForecastSummary(ForecastModel model) throws Exception {
		return getForecastSummary(model.id);
	}

	public ForecastResult getForecastSummary(UUID modelId) throws Exception {
		this.assertConnected();

		URI url = ApiUrls.forecastSummaryUrl(modelId);

		return executeRequest(url, ForecastResult.class);
	}

	public RawModel getRawModel(UUID modelId, boolean useForecastFrequency, Date cutoffDate) throws Exception {
		this.assertConnected();

		URI url = ApiUrls.rawModelDataUrl(modelId, useForecastFrequency, cutoffDate);

		return executeRequest(url, RawModel.class);
	}

	public List<Point> getSeriesData(Indicator indicator) throws Exception {
		return this.getSeriesData(indicator.provider.id, indicator.providerId, null);
	}

	public List<Point> getSeriesData(UUID provider, String providerId, Frequency frequency) throws Exception {
		this.assertConnected();

		URI url = ApiUrls.seriesUrl(provider, providerId, frequency);

		return executeRequest(url, new TypeToken<List<Point>>() {}.getType());	
	}

	public List<Point> getSeriesData(UUID provider, String providerId) throws Exception {
		return getSeriesData(provider, providerId, null, null, null, 0);
	}

	public List<Point> getSeriesData(UUID provider, String providerId, Date startTime, Frequency frequency,
			Calculation calculation, int offset) throws Exception {
		this.assertConnected();

		URI url = ApiUrls.seriesUrl(provider, providerId, startTime, frequency, calculation, offset);

		return executeRequest(url, new TypeToken<List<Point>>() {}.getType());
	}

	private void assertConnected() throws Exception {
		if (ApiClient.Context != null) {
			return;
		}

		if (connect()) {
			return;
		}

		throw new Exception("Could not connect with API Key " + ApiClient.ApiKey);
	}

	private boolean connect() throws Exception {
		URI url = ApiUrls.contextUrl();

		ApiClient.Context = executeRequest(url, Context.class);

		return ApiClient.Context != null;
	}

	private <TResponse> TResponse executeRequest(URI url, Type responseClass) throws IOException {
		Request request = new Request.Builder().url(url.toASCIIString()).build();

		Response response = Client.newCall(request).execute();

		String content = response.body().string();

		Gson gson = new GsonBuilder().registerTypeAdapter(Date.class, new DateTypeAdapter())
				.registerTypeAdapter(UUID.class, new UuidTypeAdapter()).create();

		return gson.fromJson(content, responseClass);
	}

	private static class ApiUrls {
		private ApiUrls() {
			
		}
		
		public static URI contextUrl() throws MalformedURLException, URISyntaxException {
			return ApiUrls.getURI(ApiUrlBase + "/context?apikey=" + ApiClient.ApiKey);	
		}
		
		public static URI echoUrl(String echo) throws MalformedURLException, URISyntaxException {
			return ApiUrls.getURI(ApiUrlBase + "/test/" + echo + "?apikey=" + ApiClient.ApiKey);
		}

		public static URI forcastModelsUrl() throws MalformedURLException, URISyntaxException {
			return ApiUrls.getURI(ApiUrlBase + "/forecastmodels?apikey=" + ApiClient.ApiKey);
		}

		public static URI rawModelDataUrl(UUID modelId, boolean useForecastFrequency, Date cutoffDate)
				throws MalformedURLException, URISyntaxException {
			String url = ApiUrlBase + "/rawModel/" + modelId.toString().replace("-", "") + "/" + useForecastFrequency
					+ "?apikey=" + ApiClient.ApiKey;

			if (cutoffDate != null) {
				url += "&AsOfDate=" + new SimpleDateFormat("yyyy-MM-dd").format(cutoffDate);
			}

			return ApiUrls.getURI(url);
		}

		@SuppressWarnings("unused")
		public static URI forecastModelDataUrl(ForecastModel model, Date cutoffDate)
				throws MalformedURLException, URISyntaxException {
			return ApiUrls.forecastModelDataUrl(model.id, cutoffDate);
		}

		public static URI forecastModelDataUrl(UUID modelId, Date cutoffDate)
				throws MalformedURLException, URISyntaxException {
			String url = ApiUrlBase + "/forecast/" + modelId.toString().replace("-", "") + "?apikey="
					+ ApiClient.ApiKey;

			if (cutoffDate != null) {
				url += "&asofdate=" + new SimpleDateFormat("yyyy-MM-dd").format(cutoffDate);
			}

			return ApiUrls.getURI(url);
		}

		@SuppressWarnings("unused")
		public static URI forecastSummaryUrl(ForecastModel model) throws MalformedURLException, URISyntaxException {
			return ApiUrls.forecastSummaryUrl(model.id);
		}

		public static URI forecastSummaryUrl(UUID modelId) throws MalformedURLException, URISyntaxException {
			String url = ApiUrlBase + "/forecast/summary/" + modelId.toString().replace("-", "") + "?apikey="
					+ ApiClient.ApiKey;

			return ApiUrls.getURI(url);
		}

		@SuppressWarnings("unused")
		public static URI seriesUrl(Indicator indicator) throws MalformedURLException, URISyntaxException {
			return seriesUrl(indicator, null);
		}

		public static URI seriesUrl(Indicator indicator, Frequency frequency)
				throws MalformedURLException, URISyntaxException {
			return seriesUrl(indicator.provider.id, indicator.providerId, frequency);
		}

		public static URI seriesUrl(UUID provider, String providerId, Frequency frequency)
				throws MalformedURLException, URISyntaxException {
			String url = ApiUrlBase + "/indicator/series/" + provider.toString().replace("-", "") + "/" + providerId
					+ "?apikey=" + ApiClient.ApiKey;

			if (frequency != null) {
				url += "&Frequency=" + frequency;
			}

			return ApiUrls.getURI(url);
		}

		public static URI seriesUrl(UUID provider, String providerId, Date startTime, Frequency frequency,
				Calculation calculation, int offset) throws MalformedURLException, URISyntaxException {
			String url = ApiUrlBase + "/indicator/series/" + provider.toString().replace("-", "") + "/" + providerId
					+ "?apikey=" + ApiClient.ApiKey;

			if (startTime != null) {
				url += "&StartDate=" + new SimpleDateFormat("yyyy-MM-dd").format(startTime);
			}
			if (frequency != null && frequency != Frequency.Default) {
				url += "&Frequency=" + frequency;
			}
			if (calculation != null && calculation != Calculation.Default) {
				url += "&Calculation=" + calculation;
			}
			if (offset != 0) {
				url += "&offset=" + offset;
			}

			return ApiUrls.getURI(url);
		}

		public static URI searchUrl(String query, Frequency frequency, Seasonality seasonality, boolean internalOnly,
				boolean includeForecasted, int page, int pageSize) throws MalformedURLException, URISyntaxException {
			String url = ApiUrlBase + "/search?query=" + query + "&apikey=" + ApiClient.ApiKey + "&internal="
					+ internalOnly + "&includeforecasted=" + includeForecasted + "&page=" + page + "&pageSize="
					+ pageSize;

			if (frequency != null || frequency != Frequency.Default) {
				url += "&Frequency=" + frequency;
			}
			if (seasonality != null || seasonality != Seasonality.Default) {
				url += "&Seasonality=" + seasonality;
			}

			return ApiUrls.getURI(url);
		}

		private static URI getURI(String urlString) throws MalformedURLException, URISyntaxException {
			URL url = new URL(urlString);

			return new URI(url.getProtocol(), url.getUserInfo(), url.getHost(), url.getPort(), url.getPath(),
					url.getQuery(), url.getRef());
		}
	}
}
