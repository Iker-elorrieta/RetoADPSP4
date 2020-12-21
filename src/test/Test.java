package test;

import static org.junit.Assert.*;
import hiberclass.Estaciones;
import main.Insertado;

public class Test {
	
	Insertado insertado = new Insertado();

	@org.junit.Test
	public void testInsercion() {
		Estaciones estacion = new Estaciones();
		estacion.setNombre("Prueba");
		estacion.setDireccion("Dire de prueba");
		assertEquals(true, Insertado.insertarEstacion(estacion, true));
	}

}
