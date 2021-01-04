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
	
	Principal principal = new Principal();
	InsertarBorrar insertado = new InsertarBorrar();
	Entornos entorno = new Entornos();
	Municipios municipio = new Municipios();
	Horario horario = new Horario();
	Informes informe = new Informes();
	Estaciones estacion = new Estaciones();
	Entornosmuni entornosmuni = new Entornosmuni();
	EntornosmuniId entornosmuniId = new EntornosmuniId();
	HibernateUtil hibernate = new HibernateUtil();
	SessionFactory sesion = HibernateUtil.getSessionFactory();
	Session session = sesion.openSession();
	
	//Valores de prueba
	String nombrePruebas = "prueba";
	Double valor = (double) 1;
	Calendar fecha = Calendar.getInstance(); // para pasar a date llamar al metodo toDate(Calendar)
	
	@SuppressWarnings("rawtypes")
	Set set = new HashSet(0);
	
	@org.junit.Test
	public void testInsercionEstacion() {
		
		municipio = new Municipios(nombrePruebas, nombrePruebas, valor, valor, "1", set, set);
		estacion = new Estaciones(municipio, nombrePruebas, nombrePruebas);
		estacion = new Estaciones(municipio, nombrePruebas, nombrePruebas, nombrePruebas, valor, valor, valor, valor, set);
		estacion.toString();
		InsertarBorrar.insertar(municipio, sesion, session);
		assertEquals(true, InsertarBorrar.insertar(estacion, sesion, session));
		InsertarBorrar.borrar(municipio);
	}
	
	@org.junit.Test
	public void testInsercionMunicipio() {
		municipio = new Municipios(nombrePruebas);
		municipio = new Municipios(nombrePruebas, nombrePruebas, valor, valor, "1", set, set);
		assertEquals(true, InsertarBorrar.insertar(municipio, sesion, session));
		InsertarBorrar.borrar(municipio);
	}
	
	@org.junit.Test
	public void testInsercionInformes() {
		municipio = new Municipios(nombrePruebas, nombrePruebas, valor, valor, "1", set, set);
		estacion = new Estaciones(municipio, nombrePruebas, nombrePruebas, nombrePruebas, valor, valor, valor, valor, set);
		informe = new Informes(estacion, nombrePruebas);
		informe = new Informes(estacion, nombrePruebas, nombrePruebas, set);
		InsertarBorrar.insertar(municipio, sesion, session);
		InsertarBorrar.insertar(estacion, sesion, session);
		assertEquals(true, InsertarBorrar.insertar(informe, sesion, session));
		InsertarBorrar.borrar(informe);
		InsertarBorrar.borrar(estacion);
		InsertarBorrar.borrar(municipio);
	}
	
	@org.junit.Test
	public void testInsercionEntorno() {
		
		entorno = new Entornos(nombrePruebas);
		entorno = new Entornos(nombrePruebas, nombrePruebas, nombrePruebas, nombrePruebas, valor, valor, set);
		assertEquals(true, InsertarBorrar.insertar(entorno, sesion, session));
		InsertarBorrar.borrar(entorno);
	}
	
	@org.junit.Test
    public void testInsercionHorarios() {
		municipio = new Municipios(nombrePruebas, nombrePruebas, valor, valor, "1", set, set);
		estacion = new Estaciones(municipio, nombrePruebas, nombrePruebas, nombrePruebas, valor, valor, valor, valor, set);
        informe = new Informes(estacion, nombrePruebas, nombrePruebas, set);

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
        InsertarBorrar.insertar(municipio, sesion, session);
        InsertarBorrar.insertar(estacion, sesion, session);
        InsertarBorrar.insertar(informe, sesion, session);
        assertEquals(true, InsertarBorrar.insertar(horario, sesion, session));
        InsertarBorrar.borrar(municipio);
    }
	
	@org.junit.Test
    public void testInsercionEntornosMuni() {
		entorno = new Entornos(nombrePruebas, nombrePruebas, nombrePruebas, nombrePruebas, valor, valor, set);	
		municipio = new Municipios(nombrePruebas, nombrePruebas, valor, valor, "1", set, set);		
		InsertarBorrar.insertar(entorno, sesion, session);
		InsertarBorrar.insertar(municipio, sesion, session);
		entorno.setId(InsertarBorrar.obtenerEntornoId(entorno));
		municipio.setId(InsertarBorrar.obtenerMunicipioId(municipio));
		entornosmuniId = new EntornosmuniId(entorno.getId(), municipio.getId());
		entornosmuni.setEntornos(entorno);
		entornosmuni.setMunicipios(municipio);
		entornosmuni = new Entornosmuni(entornosmuniId, entorno, municipio);
		InsertarBorrar.insertar(entornosmuni, sesion, session);
		InsertarBorrar.borrar(municipio);
		InsertarBorrar.borrar(entorno);
		
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