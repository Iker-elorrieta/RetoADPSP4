package test;

import static org.junit.Assert.*;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import hiberclass.Entornos;
import hiberclass.Entornosmuni;
import hiberclass.EntornosmuniId;
import hiberclass.Estaciones;
import hiberclass.Horario;
import hiberclass.Informes;
import hiberclass.Municipios;
import main.HibernateUtil;
import main.InsertarBorrar;
import main.Principal;

/**
 * Clase para comprobar que el programa funciona correctamente.
 */
public class Test {
	
	private Principal principal = new Principal();
	private InsertarBorrar insertado = new InsertarBorrar();
	private Entornos entorno = new Entornos();
	private Municipios municipio = new Municipios();
	private Horario horario = new Horario();
	private Informes informe = new Informes();
	private Estaciones estacion = new Estaciones();
	private Entornosmuni entornosmuni = new Entornosmuni();
	private EntornosmuniId entornosmuniId = new EntornosmuniId();
	private HibernateUtil hibernate = new HibernateUtil();
	SessionFactory sesion = HibernateUtil.getSessionFactory();
	Session session = sesion.openSession();
	
	//Valores de prueba
	String nombrePruebas = "prueba";
	Double valor = (double) 1;
	Calendar fecha = Calendar.getInstance(); // para pasar a date llamar al metodo toDate(Calendar)
	
	@SuppressWarnings("rawtypes")
	Set set = new HashSet(0);
	
	@org.junit.Test
	public void testInsercionMunicipio() {
		municipio = new Municipios(nombrePruebas);
		municipio = new Municipios(nombrePruebas, nombrePruebas, valor, valor, "1", set, set);
		assertEquals(true, InsertarBorrar.insertar(municipio, sesion, session));
		InsertarBorrar.borrar(municipio, sesion, session);
	}
	
	@org.junit.Test
	public void testInsercionEntorno() {
		entorno = new Entornos(nombrePruebas);
		entorno = new Entornos(nombrePruebas, nombrePruebas, nombrePruebas, nombrePruebas, valor, valor, set);
		assertEquals(true, InsertarBorrar.insertar(entorno, sesion, session));
		InsertarBorrar.borrar(entorno, sesion, session);
	}
	
	@org.junit.Test
	public void testInsercionEstacion() {
		municipio = new Municipios(nombrePruebas, nombrePruebas, valor, valor, "1", set, set);
		InsertarBorrar.insertar(municipio, sesion, session);
		estacion = new Estaciones(municipio, nombrePruebas, nombrePruebas);
		estacion = new Estaciones(municipio, nombrePruebas, nombrePruebas, nombrePruebas, valor, valor, valor, valor, set);
		assertEquals(true, InsertarBorrar.insertar(estacion, sesion, session));
		InsertarBorrar.borrar(municipio, sesion, session);
	}
	
	@org.junit.Test
	public void testInsercionInformes() {
		municipio = new Municipios(nombrePruebas, nombrePruebas, valor, valor, "1", set, set);
		InsertarBorrar.insertar(municipio, sesion, session);
		estacion = new Estaciones(municipio, nombrePruebas, nombrePruebas, nombrePruebas, valor, valor, valor, valor, set);
		InsertarBorrar.insertar(estacion, sesion, session);
		informe = new Informes(estacion, nombrePruebas);
		informe = new Informes(estacion, nombrePruebas, nombrePruebas, set);
		assertEquals(true, InsertarBorrar.insertar(informe, sesion, session));
		InsertarBorrar.borrar(municipio, sesion, session);
	}
	
	@org.junit.Test
    public void testInsercionHorarios() {
		municipio = new Municipios(nombrePruebas, nombrePruebas, valor, valor, "1", set, set);
		estacion = new Estaciones(municipio, nombrePruebas, nombrePruebas, nombrePruebas, valor, valor, valor, valor, set);
		informe = new Informes(estacion, nombrePruebas, nombrePruebas, set);
		InsertarBorrar.insertar(municipio, sesion, session);
		InsertarBorrar.insertar(estacion, sesion, session);
		InsertarBorrar.insertar(informe, sesion, session);
		
        horario.setFecha(toDate(fecha));
        horario.setHora("10:00");
        horario.setInformes(informe);
        horario.setComgm3(valor);
        horario.setNogm3(0.22);
        horario.setNoxgm3(0.55);
        horario.setNo2(valor);
        horario.setNo2ica(nombrePruebas);
        horario.setPm10(valor);
        horario.setPm10ica(nombrePruebas);
        horario.setPm25(valor);
        horario.setPm25ica(nombrePruebas);
        horario.setSo2(valor);
        horario.setSo2ica(nombrePruebas);
        horario.setIcaestacion(nombrePruebas);

        horario = new Horario(informe,toDate(fecha),"10:00",valor, valor, valor, nombrePruebas, valor, valor, nombrePruebas, valor, nombrePruebas, valor, nombrePruebas, nombrePruebas);
        
        assertEquals(true, InsertarBorrar.insertar(horario, sesion, session));
        InsertarBorrar.borrar(municipio, sesion, session);
    }
	
	@org.junit.Test
    public void testInsercionEntornosMuni() {
		municipio = new Municipios(nombrePruebas, nombrePruebas, valor, valor, "1", set, set);
		entorno = new Entornos(nombrePruebas, nombrePruebas, nombrePruebas, nombrePruebas, valor, valor, set);
		InsertarBorrar.insertar(municipio, sesion, session);
		InsertarBorrar.insertar(entorno, sesion, session);
		
		entorno.setId(InsertarBorrar.obtenerEntornoId(entorno, sesion, session));
		municipio.setId(InsertarBorrar.obtenerMunicipioId(municipio, sesion, session));
		entornosmuniId = new EntornosmuniId(entorno.getId(), municipio.getId());
		entornosmuni.setEntornos(entorno);
		entornosmuni.setMunicipios(municipio);
		entornosmuni = new Entornosmuni(entornosmuniId, entorno, municipio);
		assertEquals(true, InsertarBorrar.insertar(entornosmuni, sesion, session));
		InsertarBorrar.borrar(entorno, sesion, session);
		InsertarBorrar.borrar(municipio, sesion, session);
		
		session.close();
		sesion.close();
	}

    public Date toDate(Calendar calendar)
    {
        Date result;
        result = calendar.getTime();
        return result;
    }
}