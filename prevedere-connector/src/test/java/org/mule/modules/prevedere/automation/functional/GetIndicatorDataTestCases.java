package org.mule.modules.prevedere.automation.functional;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

import org.joda.time.format.DateTimeFormat;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mule.modules.prevedere.PrevedereConnector;
import org.mule.modules.prevedere.model.Calculation;
import org.mule.modules.prevedere.model.Frequency;
import org.mule.modules.prevedere.model.Point;
import org.mule.tools.devkit.ctf.junit.AbstractTestCase;

public class GetIndicatorDataTestCases extends AbstractTestCase<PrevedereConnector> {

	public GetIndicatorDataTestCases() {
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
		String provider = PrevedereTestUtils.provider;
		String providerId = PrevedereTestUtils.providerId;
		Date start = null;
		Frequency frequency = null;
		Calculation calculation = null;
		Integer offset = null;
		
		List<Point> result = getConnector().getIndicatorData(provider, providerId, start, frequency, calculation, offset);
		
		assertTrue(result.size() > 0); 
	}
	
	@Test
	public void verifyIndicatorStartDate() throws Exception {
		String provider = PrevedereTestUtils.provider;
		String providerId = PrevedereTestUtils.providerId;
		Date start = DateTimeFormat.forPattern("dd/mm/yyyy").parseDateTime("01/01/2015").toDate();
		Frequency frequency = null;
		Calculation calculation = null;
		Integer offset = null;
		
		List<Point> result = getConnector().getIndicatorData(provider, providerId, start, frequency, calculation, offset);
		
		assertTrue(result.size() > 0); 
	}
	
	@Test
	public void verifyIndicatorFrequency() throws Exception {
		String provider = PrevedereTestUtils.provider;
		String providerId = PrevedereTestUtils.providerId;
		Date start = null;
		Frequency frequency = Frequency.Annual;
		Calculation calculation = null;
		Integer offset = null;
		
		List<Point> result = getConnector().getIndicatorData(provider, providerId, start, frequency, calculation, offset);
		
		assertTrue(result.size() > 1);
		
		LocalDate firstDate = result.get(0).Date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		LocalDate nextDate = result.get(1).Date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		
		assertTrue((nextDate.getYear() - firstDate.getYear()) == 1);
	}

}