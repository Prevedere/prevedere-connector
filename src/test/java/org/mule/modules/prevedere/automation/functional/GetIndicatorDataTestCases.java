/**
 *     PREVEDERE CONFIDENTIAL
 *
 *     2012 Prevedere Incorporated
 *     All Rights Reserved.
 *
 *     NOTICE:  All information contained herein is, and remains the property of Prevedere Incorporated and its suppliers, if any.  The intellectual and technical concepts contained herein are proprietary to Prevedere Incorporated and its suppliers and may be covered by U.S. and Foreign Patents, patents in process, and are protected by trade secret or copyright law. Dissemination of this information or reproduction of this material is strictly forbidden unless prior written permission is obtained from Prevedere Incorporated.
 */
package org.mule.modules.prevedere.automation.functional;

import static org.junit.Assert.*;

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
		
		org.joda.time.LocalDate firstDate = new org.joda.time.LocalDate(result.get(0).date);
		org.joda.time.LocalDate nextDate = new org.joda.time.LocalDate(result.get(1).date);
		
		assertTrue((nextDate.getYear() - firstDate.getYear()) == 1);
	}

}