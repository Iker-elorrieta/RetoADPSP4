package test;

import static org.junit.Assert.*;

import java.awt.event.MouseEvent;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.fasterxml.jackson.databind.ObjectMapper;

import Server.Servidor;
import Server.VentanaServidor;
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
import modelo.InsertarBorrar;
import modelo.Municipios;
import modelo.Usuario;
import vista.Logeado;
import vista.Login;
import vista.Registrar;
import modelo.Json;

/**
 * Clase para comprobar que el programa funciona correctamente.
 */
public class TestPrincipal {
	
	private Json principal = new Json();
	private InsertarBorrar insertado = new InsertarBorrar();
	private Entornos entorno = new Entornos();
	private Municipios municipio = new Municipios();
	private Horario horario = new Horario();
	private Informes informe = new Informes();
	private Estaciones estacion = new Estaciones();
	private Entornosmuni entornosmuni = new Entornosmuni();
	private EntornosmuniId entornosmuniId = new EntornosmuniId();
	private HibernateUtil hibernate = new HibernateUtil();
	private Login login = new Login();
	private Registrar registrar = new Registrar();
	private Logeado logeado = new Logeado();
	private Controlador_Login controladorLogin;
	private Controlador_Registro controladorRegistro;
	SessionFactory sesion = HibernateUtil.getSessionFactory();
	Session session = sesion.openSession();
	
	//Valores de prueba
	String nombrePruebas = "prueba";
	Double valor = (double) 1;
	Calendar fecha = Calendar.getInstance(); // para pasar a date llamar al metodo toDate(Calendar)
	
	@SuppressWarnings("rawtypes")
	Set set = new HashSet(0);
	
	@org.junit.Test
	public void testPrincipal() {
		Principal main = new Principal();
		main.main(null);
		assertEquals(true,main.start());
	}
	
    
    
}