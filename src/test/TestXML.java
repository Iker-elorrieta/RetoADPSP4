package test;

import static org.junit.Assert.*;

import modelo.Xml;

/**
 * Clase para comprobar la clase xml funciona correctamente.
 * @author Grupo 4
 */
public class TestXML {
	
	Xml xml = new Xml();

	@org.junit.Test
	public void test() {
		assertEquals(true, Xml.convertirJSONaXML());
	}

}
