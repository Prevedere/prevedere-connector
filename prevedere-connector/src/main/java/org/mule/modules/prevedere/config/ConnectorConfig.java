package org.mule.modules.prevedere.config;

import org.mule.api.annotations.components.Configuration;
import org.mule.api.annotations.display.FriendlyName;
import org.mule.api.annotations.param.Default;
import org.mule.modules.prevedere.client.ApiClient;
import org.mule.api.annotations.Configurable;

@Configuration(configElementName="config", friendlyName = "Configuration")
public class ConnectorConfig {
    
	/**
     * API Key
     */
    @Configurable
    @FriendlyName("API Key")
    @Default("071cf3ed952041a6a0673755988b0b6f")
    private String apiKey;

    private static final ApiClient apiClient = new ApiClient();
    
    /**
     * Set API Key
     *
     * @param apiKey An API Key
     */
    public void setApiKey(String apiKey) {
    	ApiClient.setApiKey(apiKey);
        this.apiKey = apiKey;
    }

    /**
     * Get API Key
     */
    public String getApiKey() {
        return this.apiKey;
    }    
    
    public ApiClient getClient() {
    	return apiClient;
    }
}