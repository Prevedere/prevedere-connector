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