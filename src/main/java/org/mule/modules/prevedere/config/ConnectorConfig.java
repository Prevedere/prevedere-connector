/**
 *     PREVEDERE CONFIDENTIAL
 *
 *     2012 Prevedere Incorporated
 *     All Rights Reserved.
 *
 *     NOTICE:  All information contained herein is, and remains the property of Prevedere Incorporated and its suppliers, if any.  The intellectual and technical concepts contained herein are proprietary to Prevedere Incorporated and its suppliers and may be covered by U.S. and Foreign Patents, patents in process, and are protected by trade secret or copyright law. Dissemination of this information or reproduction of this material is strictly forbidden unless prior written permission is obtained from Prevedere Incorporated.
 */
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

    private final ApiClient apiClient = new ApiClient();
    
    /**
     * Set API Key
     *
     * @param apiKey An API Key
     */
    public void setApiKey(String apiKey) {
    	this.apiClient.setApiKey(apiKey);
    	this.apiKey = apiKey;
    }

    /**
     * Get API Key
     * 
     * @return the current API Key
     */    
    public String getApiKey() {
        return this.apiKey;
    }    
    
    /**
     * Get Prevedere API Client
     * 
     * @return an instance of the Prevedere API Client
     */
    public ApiClient getClient() {
    	return apiClient;
    }
}