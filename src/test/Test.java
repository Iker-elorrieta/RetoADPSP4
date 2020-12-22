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
		estacion = new Estaciones("prueba", "prueba", "prueba", Double.valueOf("1"), Double.valueOf("1"), Double.valueOf("1"), Double.valueOf("1"), set);
		estacion = new Estaciones("prueba", "prueba");
		estacion.setNombre("Prueba");
		estacion.setDireccion("Dire de prueba");
		estacion.setProvincia("prueba");
		estacion.setCoordenadaX(Double.valueOf("1"));
		estacion.setCoordenadaY(Double.valueOf("1"));
		estacion.setLatitud(Double.valueOf("1"));
		estacion.setLongitud(Double.valueOf("1"));
		assertEquals(true, InsertarBorrar.insertar((Object)estacion));
		InsertarBorrar.borrar(estacion);
	}
	
	@org.junit.Test
	public void testInsercionMunicipio() {
		Set set = new HashSet(0);
		municipio = new Municipios("prueba", "prueba", Double.valueOf("1"), Double.valueOf("1"), 1, set);
		municipio = new Municipios();
		municipio = new Municipios("prueba");
		municipio.setNombre("prueba");
		municipio.setDescripcion("prueba");
		municipio.setLatitud(Double.valueOf("1"));
		municipio.setLongitud(Double.valueOf("1"));
		municipio.setCodigo(1);
		assertEquals(true, InsertarBorrar.insertar((Object)municipio));
		InsertarBorrar.borrar(municipio);
	}
	
	@org.junit.Test
	public void testInsercionInformes() {
		
		estacion = new Estaciones();
		estacion.setNombre("prueba");
		estacion.setDireccion("prueba");
		informe = new Informes(estacion, "prueba");
		informe = new Informes(estacion, "prueba", "prueba", set);
		informe.setEstaciones(estacion);
		informe.setFormato("prueba");
		informe.setUrl("prueba");
		informe.setHorarios(set);
		InsertarBorrar.insertar((Object)estacion);
		assertEquals(true, InsertarBorrar.insertar((Object)informe));
		InsertarBorrar.borrar(informe);
		InsertarBorrar.borrar(estacion);
	}

}
