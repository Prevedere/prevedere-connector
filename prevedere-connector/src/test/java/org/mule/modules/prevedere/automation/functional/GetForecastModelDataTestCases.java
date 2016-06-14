package org.mule.modules.prevedere.automation.functional;

import static org.junit.Assert.*;

import java.util.Date;
import java.util.List;

import org.joda.time.format.DateTimeFormat;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mule.modules.prevedere.PrevedereConnector;
import org.mule.modules.prevedere.model.Point;
import org.mule.tools.devkit.ctf.junit.AbstractTestCase;

public class GetForecastModelDataTestCases extends AbstractTestCase<PrevedereConnector> {

	public GetForecastModelDataTestCases() {
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
	public void verifyModelDataIsReturned() throws Exception {
		java.lang.String modelId = PrevedereTestUtils.modelId;
		java.util.Date cutoff = null;
		
		List<Point> result = getConnector().getForecastModelData(modelId, cutoff);
		
		assertTrue(result.size() > 0);
	}
	
	@Test
	public void verifyModelDataCutoffDate() throws Exception {
		String modelId = PrevedereTestUtils.modelId;
		Date cutoff = DateTimeFormat.forPattern("dd/mm/yyyy").parseDateTime("01/01/2015").toDate();
		
		List<Point> result = getConnector().getForecastModelData(modelId, cutoff);
		
		assertTrue(result.size() > 0);
	}

}