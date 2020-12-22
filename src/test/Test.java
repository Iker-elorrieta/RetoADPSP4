package test;

import static org.junit.Assert.*;

import java.util.HashSet;
import java.util.Set;

import hiberclass.Entornos;
import hiberclass.Estaciones;
import hiberclass.Horario;
import hiberclass.Informes;
import hiberclass.Municipios;
import main.InsertarBorrar;
import main.Principal;

public class Test {
	
	Principal principal = new Principal();
	InsertarBorrar insertado = new InsertarBorrar();
	Entornos entorno = new Entornos();
	Municipios municipio = new Municipios();
	Horario horario = new Horario();
	Informes informe = new Informes();
	Estaciones estacion = new Estaciones();
	@SuppressWarnings("rawtypes")
	Set set = new HashSet(0);
	
	@org.junit.Test
	public void testInsercionEstacion() {
		estacion = new Estaciones("prueba", "prueba");
		estacion = new Estaciones("prueba", "prueba", "prueba", Double.valueOf("1"), Double.valueOf("1"), Double.valueOf("1"), Double.valueOf("1"), set);
		assertEquals(true, InsertarBorrar.insertar((Object)estacion));
		InsertarBorrar.borrar(estacion);
	}
	
	@org.junit.Test
	public void testInsercionMunicipio() {
		municipio = new Municipios("prueba");
		municipio = new Municipios("prueba", "prueba", Double.valueOf("1"), Double.valueOf("1"), 1, set);
		assertEquals(true, InsertarBorrar.insertar((Object)municipio));
		InsertarBorrar.borrar(municipio);
	}
	
	@org.junit.Test
	public void testInsercionInformes() {
		
		estacion.setNombre("prueba");
		estacion.setDireccion("prueba");
		informe = new Informes(estacion, "prueba");
		informe = new Informes(estacion, "prueba", "prueba", set);
		InsertarBorrar.insertar((Object)estacion);
		assertEquals(true, InsertarBorrar.insertar((Object)informe));
		InsertarBorrar.borrar(informe);
		InsertarBorrar.borrar(estacion);
	}
	
	@org.junit.Test
	public void testInsercionEntorno() {
		
		entorno = new Entornos("prueba");
		entorno = new Entornos("prueba", "prueba", "prueba", "prueba", Double.valueOf("1"), Double.valueOf("1"), set);
		assertEquals(true, InsertarBorrar.insertar((Object)entorno));
		InsertarBorrar.borrar(entorno);
	}

}
