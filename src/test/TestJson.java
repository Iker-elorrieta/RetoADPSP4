package test;

import static org.junit.Assert.*;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Calendar;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import com.fasterxml.jackson.databind.ObjectMapper;

import modelo.Json;
import modelo.Entornos;
import modelo.Estaciones;
import modelo.HibernateUtil;
import modelo.Informes;
import modelo.Municipios;

/**
 * Clase para comprobar que los Jsones funcionan correctamente.
 */
public class TestJson {

	SessionFactory sesion = HibernateUtil.getSessionFactory();
	Session session = sesion.openSession();
	
	@org.junit.Test
	@SuppressWarnings({ "unchecked" })
	public void testJsonTotal()
	{
		//Tiempo 6min ~
		Calendar tiempo1 = Calendar.getInstance();
		//Hay que borrar todos los datos de la BDD para hacer esta prueba 

		Json hijoDeJ = new Json();
		ArrayList<Object> lista = hijoDeJ.cargarJsons();
		assertEquals(true,hijoDeJ.cargarTodosLosDatos((ArrayList<Informes>) lista.get(0),(ObjectMapper) lista.get(1),(Municipios[]) lista.get(2),(Entornos[]) lista.get(3),(Estaciones[]) lista.get(4), (Informes[]) lista.get(5), sesion, session));
		Calendar tiempo2 = Calendar.getInstance();
		System.out.println(Duration.between(tiempo1.toInstant(), tiempo2.toInstant()));
	}
}