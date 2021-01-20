package test;

import static org.junit.Assert.*;

import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Order;

import Server.Servidor;
import Server.VentanaServidor;
import comunes.InsertarBorrar;
import comunes.Json;
import controlador.Controlador_Login;
import controlador.Controlador_Registro;
import modelo.Entornos;
import modelo.Entornosmuni;
import modelo.EntornosmuniId;
import modelo.Estaciones;
import modelo.HibernateUtil;
import modelo.Horario;
import modelo.Informes;
import modelo.Municipios;
import modelo.Usuario;
import vista.Logeado;
import vista.Login;
import vista.Registrar;

/**
 * Clase para comprobar que el servidor funciona correctamente.
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
	Servidor server = new Servidor(new JTextArea(), new JTextField(), new JLabel());
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
	public void a() // Test servidor
	{	
		server.start();
		try {
			Socket socket = new Socket("127.0.0.1",44444);@SuppressWarnings("unused")
			ObjectInputStream entrada = new ObjectInputStream(socket.getInputStream()); 
			ObjectOutputStream salida = new ObjectOutputStream(socket.getOutputStream());
			Usuario nuevo = new Usuario();
			nuevo.setUsuario("prueba");
			nuevo.setContrasena("prueba");
			salida.writeObject(2);
			salida.writeObject(nuevo);
			salida.writeObject(1);
			salida.writeObject(nuevo);
			InsertarBorrar.borrar(nuevo, sesion, session);
			socket.close();
		} catch (IOException e) {
			
		}
		assertEquals(true,server.prueba());
	}
	
	//Para hacer esta prueba el servido debe estar en funcionamiento.
	
		@org.junit.Test
		public void b() // Test Controlador Login
		{
			ObjectOutputStream salida;
			ObjectInputStream entrada;
			try {
				Socket socket = new Socket("127.0.0.1",44444);
				entrada = new ObjectInputStream(socket.getInputStream());
				salida = new ObjectOutputStream(socket.getOutputStream());
				MouseEvent e = new MouseEvent(login.getBotonRegistrar(), 0, 0, 0, 0, 0, 0, false);
				login = new Login();
				controladorLogin = new Controlador_Login(login, entrada, salida);
				e.getComponent().setName("registrar");
				controladorLogin.mousePressed(e);
				e.getComponent().setName("volver");
				controladorRegistro = new Controlador_Registro(registrar, entrada, salida);
				controladorRegistro.mousePressed(e);
				e.getComponent().setName("entrar");
				login.getNombre().setText("test");
				login.getContrasena().setText("no");
				controladorLogin.mousePressed(e);
				login.getNombre().setText("test");
				login.getContrasena().setText("test");
				controladorLogin.mousePressed(e);
				socket.close();
			} catch (IOException e) {
			}
			assertEquals(true, controladorLogin.isBooleanPrueba());
			
		}
		
		//Para hacer esta prueba el servido debe estar en funcionamiento.
		
		@org.junit.Test
		public void c() // Test Controlador Registro
		{
			ObjectOutputStream salida;
			ObjectInputStream entrada;
			try {			
				Socket socket = new Socket("127.0.0.1",44444);
				entrada = new ObjectInputStream(socket.getInputStream());
				salida = new ObjectOutputStream(socket.getOutputStream());
				registrar.getNombre().setText(nombrePruebas);
				registrar.getContrasena().setText(nombrePruebas);
				controladorRegistro = new Controlador_Registro(registrar, entrada, salida);
				MouseEvent e = new MouseEvent(registrar.getBotonAcceptar(), 0, 0, 0, 0, 0, 0, false);
				e.getComponent().setName("registrar");
				controladorRegistro.mouseClicked(e);
				controladorRegistro.mouseClicked(e);
				e.getComponent().setName("volver");
				controladorRegistro.mouseClicked(e);
				Usuario user = new Usuario();
				user.setUsuario(nombrePruebas);
				user.setContrasena(nombrePruebas);
				InsertarBorrar.borrar(user, sesion, session);
				socket.close();
			} catch (IOException e) {
				
			}
			assertEquals(true,controladorRegistro.iniciarControlador());
		}
	
	@org.junit.Test
	public void d() // Test Servidor Ventana
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
			a.setSource(frame.getBotonSalir());
			frame.actionPerformed(a);
		} catch (Exception a) {
			a.printStackTrace();
		}
		assertEquals(true,frame.prueba());
	}
	
	
}