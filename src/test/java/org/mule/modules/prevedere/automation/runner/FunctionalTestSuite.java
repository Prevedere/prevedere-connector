/**
 *     PREVEDERE CONFIDENTIAL
 *
 *     2012 Prevedere Incorporated
 *     All Rights Reserved.
 *
 *     NOTICE:  All information contained herein is, and remains the property of Prevedere Incorporated and its suppliers, if any.  The intellectual and technical concepts contained herein are proprietary to Prevedere Incorporated and its suppliers and may be covered by U.S. and Foreign Patents, patents in process, and are protected by trade secret or copyright law. Dissemination of this information or reproduction of this material is strictly forbidden unless prior written permission is obtained from Prevedere Incorporated.
 */
package org.mule.modules.prevedere.automation.runner;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;
import org.mule.modules.prevedere.automation.functional.ConnectionTestCases;
import org.mule.modules.prevedere.automation.functional.GetForecastModelDataTestCases;
import org.mule.modules.prevedere.automation.functional.GetForecastModelsTestCases;
import org.mule.modules.prevedere.automation.functional.GetForecastSummaryDataTestCases;
import org.mule.modules.prevedere.automation.functional.GetIndicatorDataTestCases;
import org.mule.modules.prevedere.automation.functional.GetRawModelDataTestCases;
import org.mule.modules.prevedere.automation.functional.SearchIndicatorsTestCases;
import org.mule.modules.prevedere.PrevedereConnector;
import org.mule.tools.devkit.ctf.exceptions.ConfigurationLoadingFailedException;
import org.mule.tools.devkit.ctf.mockup.ConnectorTestContext;
import org.mule.tools.devkit.ctf.platform.PlatformManager;

@RunWith(Suite.class)
@SuiteClasses({ 
	GetForecastModelDataTestCases.class, 
	GetForecastModelsTestCases.class,
	GetForecastSummaryDataTestCases.class, 
	GetIndicatorDataTestCases.class, 
	GetRawModelDataTestCases.class,
	SearchIndicatorsTestCases.class, 
	ConnectionTestCases.class })
public class FunctionalTestSuite {

	@BeforeClass
	public static void initialiseSuite() throws ConfigurationLoadingFailedException {
		ConnectorTestContext.initialize(PrevedereConnector.class);	
	}

	@AfterClass
	public static void shutdownSuite() {
		@SuppressWarnings("deprecation")
		ConnectorTestContext<PrevedereConnector> context = ConnectorTestContext.getInstance(PrevedereConnector.class);
        PlatformManager platform =  context.getPlatformManager();
        platform.shutdown();
	}

}