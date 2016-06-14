package org.mule.modules.prevedere.config;

import org.mule.api.annotations.components.Configuration;
import org.mule.api.annotations.display.FriendlyName;
import org.mule.api.annotations.Configurable;

@Configuration(configElementName="config", friendlyName = "Configuration")
public class ConnectorConfig {
    
	/**
     * API Key
     */
    @Configurable
    @FriendlyName("API Key")
    private String apiKey;

    public org.mule.modules.prevedere.client.ApiClient ApiClient;
    
    public ConnectorConfig() {
    	this.ApiClient = new org.mule.modules.prevedere.client.ApiClient();
    }    

    /**
     * Set API Key
     *
     * @param apiKey An API Key
     */
    public void setApiKey(String apiKey) {
    	org.mule.modules.prevedere.client.ApiClient.setApiKey(apiKey);
        this.apiKey = apiKey;
    }

    /**
     * Get API Key
     */
    public String getApiKey() {
        return this.apiKey;
    }    
    
}