package test;

import static org.junit.Assert.*;

import controlador.ControladorLogeado;
import modelo.Usuario;
import vista.Logeado;

public class TestControladorLogeado {

	ControladorLogeado controlador;
	Usuario usuario = new Usuario();

	@org.junit.Test
	public void test() {
		Logeado ventana = new Logeado();
		controlador = new ControladorLogeado(ventana, usuario);
		assertEquals(true, ControladorLogeado.booleanTest = true);
	}

}
