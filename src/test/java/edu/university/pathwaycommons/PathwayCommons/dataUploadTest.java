package edu.university.pathwaycommons.PathwayCommons;

import static org.junit.Assert.*;
import static org.testng.Assert.assertEquals;

import org.junit.Test;
/*
 * Test to test the dataUpload package.
 */
public class dataUploadTest {

	@Test
	public void testUpdateCode() {
		
		dataUpload dUpload = new dataUpload();
		//102 should be equal to reaction type: controls-expression-of
		String codeInt = dUpload.updateCode("controls-expression-of");
		assertEquals("102", codeInt);
		
		//111 should be equal to reaction type: controls-production-of
		String codeInt2 = dUpload.updateCode("controls-production-of");
		assertEquals("111", codeInt2);
		
		//113 should be equal to reaction type: controls-transport-of
		String codeInt3 = dUpload.updateCode("controls-transport-of");
		assertEquals("113",  codeInt3);
		
		//112 should be equal to reaction type: catalysis-precedes
		String codeInt4 = dUpload.updateCode("catalysis-precedes");
		assertEquals("112", codeInt4);
	}

}
