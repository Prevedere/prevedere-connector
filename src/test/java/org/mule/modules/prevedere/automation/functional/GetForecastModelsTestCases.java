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