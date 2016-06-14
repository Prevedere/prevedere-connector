package org.mule.modules.prevedere.automation.functional;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mule.modules.prevedere.PrevedereConnector;
import org.mule.modules.prevedere.model.ForecastModel;
import org.mule.tools.devkit.ctf.junit.AbstractTestCase;

public class GetForecastModelsTestCases extends AbstractTestCase<PrevedereConnector> {

	public GetForecastModelsTestCases() {
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
		List<ForecastModel> result=getConnector().getForecastModels();
		
		assertTrue(result.size() > 0);
	}

}