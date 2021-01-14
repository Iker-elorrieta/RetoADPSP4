package test;

import static org.junit.Assert.*;

import main.Xml;

public class TestXML {
	
	Xml xml = new Xml();

	@org.junit.Test
	public void test() {
		assertEquals(true, Xml.convertirJSONaXML());
	}

}
