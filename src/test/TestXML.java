package test;

import static org.junit.Assert.*;

import main.Xml;

/**
 * Clase para comprobar el correcto funcionamiento del los xml.
 * @author Grupo 4
 */
public class TestXML {
	
	Xml xml = new Xml();

	@org.junit.Test
	public void test() {
		assertEquals(true, Xml.convertirJSONaXML());
	}

}
