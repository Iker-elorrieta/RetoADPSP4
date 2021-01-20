package test;

import static org.junit.Assert.*;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;

import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import Server.Servidor;
import comunes.InsertarBorrar;
import comunes.Json;
import controlador.Controlador_Login;
import controlador.Controlador_Registro;
import main.Principal;
import modelo.Entornos;
import modelo.Entornosmuni;
import modelo.EntornosmuniId;
import modelo.Estaciones;
import modelo.HibernateUtil;
import modelo.Horario;
import modelo.Informes;
import modelo.Municipios;
import vista.Logeado;
import vista.Login;
import vista.Registrar;

/**
 * Clase para comprobar que el la clase Principal funciona correctamente.
 */
public class TestPrincipal {
																	@SuppressWarnings("unused")
	private Json principal = new Json();							@SuppressWarnings("unused")
	private InsertarBorrar insertado = new InsertarBorrar();		@SuppressWarnings("unused")
	private Entornos entorno = new Entornos();						@SuppressWarnings("unused")
	private Municipios municipio = new Municipios();				@SuppressWarnings("unused")
	private Horario horario = new Horario();						@SuppressWarnings("unused")
	private Informes informe = new Informes();						@SuppressWarnings("unused")
	private Estaciones estacion = new Estaciones();					@SuppressWarnings("unused")
	private Entornosmuni entornosmuni = new Entornosmuni();			@SuppressWarnings("unused")
	private EntornosmuniId entornosmuniId = new EntornosmuniId();	@SuppressWarnings("unused")
	private HibernateUtil hibernate = new HibernateUtil();			@SuppressWarnings("unused")
	private Login login = new Login();								@SuppressWarnings("unused")
	private Registrar registrar = new Registrar();					@SuppressWarnings("unused")
	private Logeado logeado = new Logeado();						@SuppressWarnings("unused")
	private Controlador_Login controladorLogin;						@SuppressWarnings("unused")
	private Controlador_Registro controladorRegistro;
	SessionFactory sesion = HibernateUtil.getSessionFactory();
	Session session = sesion.openSession();
	Servidor server = new Servidor(new JTextArea(), new JTextField(), new JLabel());

	//Valores de prueba
	String nombrePruebas = "prueba";
	Double valor = (double) 1;
	Calendar fecha = Calendar.getInstance(); // para pasar a date llamar al metodo toDate(Calendar)
	
	@SuppressWarnings("rawtypes")
	Set set = new HashSet(0);
	
	@SuppressWarnings("static-access")
	@org.junit.Test
	public void testPrincipal() {
		server.start();
		Principal main = new Principal();
		assertEquals(true,main.start());
	}
	
    
    
}