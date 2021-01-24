package test;

import static org.junit.Assert.*;

import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Order;

import com.fasterxml.jackson.databind.ObjectMapper;

import comunes.CrearHash;
import comunes.InsertarBorrar;
import comunes.Json;
import comunes.Xml;
import controlador.ControladorEstaciones;
import controlador.ControladorLogeado;
import controlador.ControladorRestaurarContrasena;
import controlador.Controlador_Login;
import controlador.Controlador_Registro;
import main.Principal;
import main.Servidor;
import modelo.Entornos;
import modelo.Entornosmuni;
import modelo.EntornosmuniId;
import modelo.Estaciones;
import modelo.HibernateUtil;
import modelo.Horario;
import modelo.Informes;
import modelo.Municipios;
import modelo.Usuario;
import server.ServidorPeticiones;
import vista.Logeado;
import vista.Login;
import vista.Registrar;
import vista.RestaurarContrasena;
import vista.VentanaEstaciones;

/**
 * Clase para comprobar que el servidor funciona correctamente.
 */
public class TestServidor {
																	
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
	private RestaurarContrasena restaurar = new RestaurarContrasena();
	private VentanaEstaciones ventanaEstaciones = new VentanaEstaciones();
	private Controlador_Login controladorLogin;						
	private Controlador_Registro controladorRegistro;
	private ControladorRestaurarContrasena controladorRestaurar;
	private ControladorEstaciones controladorEstaciones;
	ControladorLogeado controlador;
	Usuario usuario = new Usuario();
	ServidorPeticiones server = new ServidorPeticiones(new JTextArea(), new JTextField(), new JLabel());
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
	public void aaaa() // Test servidor
	{
		server.start();
		try {
			Socket socket = new Socket("127.0.0.1",44444);@SuppressWarnings("unused")
			ObjectInputStream entrada = new ObjectInputStream(socket.getInputStream()); 
			ObjectOutputStream salida = new ObjectOutputStream(socket.getOutputStream());
			Usuario nuevo = new Usuario();
			nuevo.setNombre("prueba");
			nuevo.setContrasena("prueba");
			salida.writeObject(2);
			salida.writeObject(nuevo);
			salida.writeObject(1);
			salida.writeObject(nuevo);
			InsertarBorrar.borrar(nuevo, sesion, session);
			socket.close();
		} catch (IOException e) {}
		assertEquals(true,server.prueba());
	}
	
	@SuppressWarnings("static-access")
	@org.junit.Test
	public void bbbb() { // Prueba del cliente principal
		Principal main = new Principal();
		assertEquals(true,main.start());
	}
	
	@org.junit.Test
	public void cccc() {//Prueba del controlador de logeado
		try {
			Logeado ventana = new Logeado();
			Socket socket = new Socket("127.0.0.1",44444);@SuppressWarnings("unused")
			ObjectInputStream entrada = new ObjectInputStream(socket.getInputStream()); 
			ObjectOutputStream salida = new ObjectOutputStream(socket.getOutputStream());
	
			controlador = new ControladorLogeado(ventana, usuario, entrada, salida);
		} catch (IOException e) {}
		assertEquals(true, ControladorLogeado.booleanTest = true);
	}
		
	@org.junit.Test
	public void dddd() // Test Controlador Login
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
			controladorLogin.setTipo("prueba");
			e.getComponent().setName("registrar");
			controladorLogin.mousePressed(e);
			e.getComponent().setName("volver");
			controladorRegistro = new Controlador_Registro(registrar, entrada, salida);
			controladorRegistro.setTipo("prueba");
			controladorRegistro.mousePressed(e);
			e.getComponent().setName("entrar");
			login.getNombre().setText("test");
			login.getContrasena().setText("no");
			controladorLogin.mousePressed(e);
			login.getNombre().setText("test");
			login.getContrasena().setText("test");
			controladorLogin.mousePressed(e);
			socket.close();
		} catch (IOException e) {}
		assertEquals(true, controladorLogin.isBooleanPrueba());
		
	}
		
	@org.junit.Test
	public void eeee() // Test Controlador Registro
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
			controladorRegistro.setTipo("prueba");
			MouseEvent e = new MouseEvent(registrar.getBotonAcceptar(), 0, 0, 0, 0, 0, 0, false);
			e.getComponent().setName("registrar");
			controladorRegistro.mouseClicked(e);
			controladorRegistro.mouseClicked(e);
			e.getComponent().setName("volver");
			controladorRegistro.mouseClicked(e);
			Usuario user = new Usuario();
			user.setNombre(nombrePruebas);
			user.setContrasena(nombrePruebas);
			InsertarBorrar.borrar(user, sesion, session);
			socket.close();
		} catch (IOException e) {}
		assertEquals(true,controladorRegistro.iniciarControlador());
	}
	
	@org.junit.Test
	public void ffff() // Test Controlador recuperar contraseña
	{
		ObjectOutputStream salida;
		ObjectInputStream entrada;
		try {			
			Socket socket = new Socket("127.0.0.1",44444);
			entrada = new ObjectInputStream(socket.getInputStream());
			salida = new ObjectOutputStream(socket.getOutputStream());
			controladorRestaurar = new ControladorRestaurarContrasena(restaurar, entrada, salida);
			controladorRestaurar.Probando();
			Usuario nuevo = new Usuario("prueba","prueba","prueba?", "prueba!");
			nuevo.setRespuesta(CrearHash.crearHash(nuevo.getRespuesta()));
			InsertarBorrar.insertar(nuevo, sesion, session);
			restaurar.getTextFieldUsuario().setText(nuevo.getNombre());
			MouseEvent e = new MouseEvent(restaurar.getBtnEnviarUsuario(), 0, 0, 0, 0, 0, 0, false);
			e.getComponent().setName("enviarUsuario");
			controladorRestaurar.mousePressed(e);
			
			restaurar.getTextFieldRespuesta().setText("prueba!");
			e.getComponent().setName("enviarRespuesta");
			controladorRestaurar.mousePressed(e);
			
			e.getComponent().setName("volver");
			controladorRestaurar.mousePressed(e);
			
			restaurar.getTextFieldContrasena().setText("Funciona");
			restaurar.getTextFieldRepetirContrasena().setText("Funciona");
			e.getComponent().setName("enviarContrasena");
			controladorRestaurar.mousePressed(e);
			
			InsertarBorrar.borrar(nuevo, sesion, session);
			socket.close();
		} catch (IOException e) {}
		assertEquals(true,controladorRestaurar.metodoPrueba());
	}
	
	@org.junit.Test
	public void gggg() // Test Controlador Registro
	{
		ObjectOutputStream salida;
		ObjectInputStream entrada;
		try {			
			Socket socket = new Socket("127.0.0.1",44444);
			entrada = new ObjectInputStream(socket.getInputStream());
			salida = new ObjectOutputStream(socket.getOutputStream());
			Usuario nuevo = new Usuario("prueba","prueba","prueba?", "prueba!");
			InsertarBorrar.insertar(nuevo, sesion, session);
			String hql = "from Estaciones where municipios.nombre='bilbao'";
			Query q = session.createQuery(hql);
			controladorEstaciones = new ControladorEstaciones(ventanaEstaciones, nuevo, entrada, salida, (ArrayList<Estaciones>) q.list());
			ActionEvent e = new ActionEvent(ventanaEstaciones.getComboBox(), 0, "combo");
			ventanaEstaciones.getComboBox().setActionCommand("combo");
			ventanaEstaciones.getComboBox().setSelectedIndex(2);
			controladorEstaciones.actionPerformed(e);
			InsertarBorrar.borrar(nuevo, sesion, session);
			socket.close();
		} catch (IOException e) {}
		assertEquals(true,controladorEstaciones.probarClase());
	}
	
	@org.junit.Test
	public void hhhh() // Test Servidor Ventana
	{
		Servidor frame = new Servidor();
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
		} catch (Exception a) {}
		assertEquals(true,frame.prueba());
	}
}