# Prevedere Anypoint Connector Demo Project

The Prevedere Connector Demo Application creates a number of HTTP endpoints to demonstrate every function of the Prevedere Connector.  All required parameters will appear as uri parameters, whereas all optional parameters will be query params.  To deploy the demo project, follow the instructions below:

# Mule supported versions

Mule 3.8.x

#Service or application supported modules

Prevedere API v1.0.0

# Installation 

1.	Download and extract the source code for the connector.
2.	Open Anypoint Studio.
3.	Select File -> Import.  On the Import screen, open the Anypoint Studio folder and select ‘Anypoint Studio Project from External Location’.  Click ‘Next’.
4.	In the Mule Import dialog, select the browse button next to the ‘Project Root:’ input in the External Project section.  Browse to the directory where you extracted the Prevedere Connector source and then navigate to ‘\demo\prevedere-connector-examples’.  Click ‘OK’.  Click ‘Finish’.
5.	Right click on the imported project and select ‘Run As’ -> ‘Mule Application’.  The project should build successfully and deploy automatically.

#Usage

All available endpoints are located at http://localhost:8081 by default.

##Prevedere Global Indicator Repository
Prevedere collects over 2 million economic indicators that can be used to generate economic models and forecasts. The indicator repository is available to search against and retrieve data for analysis.

**Search**
Search the global repository for indicator information.  Use this information to retrieve associated indicator data points.

Endpoint: /indicators/{query}/

* Query: The text to perform a search for against.
* Frequency: _<optional>_ Filter results to specified frequency.
* Seasonality: _<optional>_ Filter results to specified seasonality.
* Internal Only: _<optional>_ Flag to determine if only internal indicators should be returned.

**Retrieve Data**
Retrieve data points for a specific indicator.

Endpoint: /indicator/{provider}/{providerid}

* Provider: The UUID of the provider associated with the desired indicator.
* ProviderId: The ID associated with the desired indicator.
* Start: _<optional>_ Date to start retrieving data.
* Offset: _<optional>_ Number of periods to shift the data.
* Calculation: _<optional>_ Calculation to apply to the data.
* Frequency: _<optional>_ Filter results to specified frequency.

##Prevedere Forecast Modeling
Forecast models are built using Prevedere's economic indicator repository and can also include a company's internal data. Depending on the strength of the relationships between the forecasted values and the indicators, these models can be extremely accurate at predicting future performance.

**List Available Models**
Retrieve a list of all available forecast models for the given configuration.

Endpoint: /models

**Retrieve Model Data**
Retrieve a list of forecasted data points.

Endpoint: /model/{model id}

* Model Id: The UUID of the model to retrieve data for.
* Cutoff: _<optional>_ The last date to use actual values as inputs to the forecast.  Useful for back testing a model.

**Retrieve Component Informtion**
Retrieve a list of actual values for a forecast models components.  Also includes relevent statistical information.

Endpoint: /raw/{model id}

* Model Id: The UUID of the model to retrieve data for.
* Cutoff: _<optional>_ The end date for the forecast.  Useful for back testing a model.
* UseForecastFrequency: _<optional>_ Flag indicating whether to convert all component output to the frequency of the forecast model.

**Retrieve Summary Information**
Retrieve High, Average and Low forecasts for a forecast model.

Endpoint: /summary/{model id}

* Model Id: The UUID of the model to retrieve data for.
* Cutoff: _<optional>_ The end date for the forecast.  Useful for back testing a model.

For more information about usage please read our documentation in /prevedere-connector/doc/user-manual.adoc

# Reporting Issues

We use GitHub:Issues for tracking issues with this connector. You can report new issues at this link https://github.com/Prevedere/Prevedere-Connector/issues.

