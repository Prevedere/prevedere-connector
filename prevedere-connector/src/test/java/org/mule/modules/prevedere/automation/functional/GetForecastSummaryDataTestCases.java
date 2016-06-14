package org.mule.modules.prevedere.automation.functional;

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mule.modules.prevedere.PrevedereConnector;
import org.mule.modules.prevedere.model.ForecastResult;
import org.mule.tools.devkit.ctf.junit.AbstractTestCase;

public class GetForecastSummaryDataTestCases extends AbstractTestCase<PrevedereConnector> {

	public GetForecastSummaryDataTestCases() {
		super(PrevedereConnector.class);
	}

	@Before
	public void setup() {
	}

	@After
	public void tearDown() {
		// TODO
	}

	@Test
	public void verify() throws Exception {
		String modelId = PrevedereTestUtils.modelId;
		
		ForecastResult result = getConnector().getForecastSummaryData(modelId);
		
		assertTrue(result != null);
	}

}