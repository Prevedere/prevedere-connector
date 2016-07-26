package org.mule.modules.prevedere.automation.runner;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;
import org.mule.modules.prevedere.automation.functional.GetForecastModelDataTestCases;
import org.mule.modules.prevedere.automation.functional.GetForecastModelsTestCases;
import org.mule.modules.prevedere.automation.functional.GetForecastSummaryDataTestCases;
import org.mule.modules.prevedere.automation.functional.GetIndicatorDataTestCases;
import org.mule.modules.prevedere.automation.functional.GetRawModelDataTestCases;
import org.mule.modules.prevedere.automation.functional.SearchIndicatorsTestCases;
import org.mule.modules.prevedere.automation.functional.TestConnectionTestCases;
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
	TestConnectionTestCases.class })
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