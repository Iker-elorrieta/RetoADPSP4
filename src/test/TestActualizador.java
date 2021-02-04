package test;

import static org.junit.Assert.*;

import server.ComprobarHashJson;

public class TestActualizador {
	
	ComprobarHashJson actualizador = new ComprobarHashJson();
	
	@org.junit.Test
	public void hilo() { //
		actualizador = new ComprobarHashJson();
		ComprobarHashJson.setFrecuencia(100);
		ComprobarHashJson.setTest(true);
		actualizador.start();
		assertEquals(true, actualizador.isTestfuncionabien());
		
	}

	@org.junit.Test
	public void comprobarPagina() { //
		
		assertEquals(true, ComprobarHashJson.comprobarPagina("www.google.com"));
		
	}
	
	@org.junit.Test
	public void obtenerInformes() { //
		actualizador = new ComprobarHashJson();
		ComprobarHashJson.obtenerInformes();
		assertEquals(true, actualizador.isTestfuncionabien());
		
	}
	
	@org.junit.Test
	public void borrarHorarios() { //
		assertEquals(true, ComprobarHashJson.borrarHorarios());
		
	}

}
