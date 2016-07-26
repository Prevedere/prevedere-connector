package org.mule.modules.prevedere.automation.functional;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mule.modules.prevedere.PrevedereConnector;
import org.mule.modules.prevedere.model.RawModel;
import org.mule.tools.devkit.ctf.junit.AbstractTestCase;

public class GetRawModelDataTestCases extends AbstractTestCase<PrevedereConnector> {

	public GetRawModelDataTestCases() {
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
		boolean useForecastFrequency = false;
		Date cutoff = null;
		
		RawModel result = getConnector().getRawModelData(modelId, useForecastFrequency, cutoff);

		assertTrue(result != null);
	}

}