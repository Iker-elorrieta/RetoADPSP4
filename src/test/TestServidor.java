package test;

import static org.junit.Assert.*;

import java.awt.event.ActionEvent;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;

import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import Server.Servidor;
import Server.VentanaServidor;
import controlador.Controlador_Login;
import controlador.Controlador_Registro;
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
public class TestServidor {
	
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
	
	//Valores de prueba
	String nombrePruebas = "prueba";
	Double valor = (double) 1;
	Calendar fecha = Calendar.getInstance(); // para pasar a date llamar al metodo toDate(Calendar)
	
	@SuppressWarnings("rawtypes")
	Set set = new HashSet(0);
	
	//Antes de probar esta clase se debe comprobar que el puerto no esta ocupado por otro servidor o proceso
	//en otras palabras que no haya otro servidor arrancado.
	
	@org.junit.Test
	public void testServidor()
	{
		Servidor server = new Servidor(new JTextArea(), new JTextField(), new JLabel());
		server.start();
		try {
			Socket socket = new Socket("127.0.0.1",44444);@SuppressWarnings("unused")
			ObjectInputStream entrada = new ObjectInputStream(socket.getInputStream()); 
			ObjectOutputStream salida = new ObjectOutputStream(socket.getOutputStream());
			Usuario nuevo = new Usuario();
			nuevo.setUsuario("prueba");
			nuevo.setContrasena("prueba");
			nuevo.setEMail("a@b.c");
			salida.writeObject(2);
			salida.writeObject(nuevo);
			salida.writeObject(1);
			salida.writeObject(nuevo);
			InsertarBorrar.borrar(nuevo, sesion, session);
			socket.close();
		} catch (IOException e) {
			
		}
		
		assertEquals(true,server.desconectar());
	}
	
	@org.junit.Test
	public void testVentanaServidor()
	{
		VentanaServidor frame = new VentanaServidor();
		Socket socket;
		try {
			socket = new Socket("127.0.0.1",44444);
			@SuppressWarnings("unused")
			ObjectInputStream entrada = new ObjectInputStream(socket.getInputStream());
			@SuppressWarnings("unused")
			ObjectOutputStream salida = new ObjectOutputStream(socket.getOutputStream());
			ActionEvent a = new ActionEvent(frame.getBotonSalir(), 0, nombrePruebas);
			frame.actionPerformed(a);
		} catch (Exception a) {
		
		}
		
		assertEquals(true,frame.prueba());
	}
}