package test;

import static org.junit.Assert.*;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Calendar;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import com.fasterxml.jackson.databind.ObjectMapper;
import comunes.InsertarBorrar;
import comunes.Json;
import comunes.Xml;
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
	Xml xml;
	
	@org.junit.Test
	public void TestXml() { // Prueba de creación de xml
		xml = new Xml();
		assertEquals(true, xml.convertirJSONaXML());
	}
	
	@org.junit.Test
	@SuppressWarnings({ "unchecked" })
	public void testJsonTotal()
	{
		limbiarBDD();
		Calendar tiempo1 = Calendar.getInstance();
		//Hay que borrar todos los datos de la BDD para hacer esta prueba 

		Json hijoDeJ = new Json();
		ArrayList<Object> lista = hijoDeJ.cargarJsons();
		assertEquals(true,hijoDeJ.cargarTodosLosDatos((ObjectMapper)lista.get(0),(Municipios[])lista.get(1),(Entornos[])lista.get(2),(Estaciones[])lista.get(3),(Informes[])lista.get(4),sesion,session));
		Calendar tiempo2 = Calendar.getInstance();
		System.out.println(Duration.between(tiempo1.toInstant(), tiempo2.toInstant()));
	}
	
	public void limbiarBDD()
	{
		String hql = "From Provincias";
		Query q = session.createQuery(hql);
		for(Object objeto : q.list())
		{
			InsertarBorrar.borrar(objeto, sesion, session);
		}
			
		hql = "From Entornos";
		q = session.createQuery(hql);
		for(Object objeto : q.list())
		{
			InsertarBorrar.borrar(objeto, sesion, session);
		}
	}
}