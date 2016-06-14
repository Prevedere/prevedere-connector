package org.mule.modules.prevedere.automation.functional;

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mule.modules.prevedere.PrevedereConnector;
import org.mule.tools.devkit.ctf.junit.AbstractTestCase;

public class TestConnectionTestCases extends AbstractTestCase<PrevedereConnector> {

	public TestConnectionTestCases() {
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
		java.lang.String echo = "test echo";
		java.lang.String expected = "Echo: " + echo;
		
		assertEquals(getConnector().testConnection(echo), expected);
	}

}