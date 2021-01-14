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
public class TestControladorLogin {
	
	private Login login = new Login();
	private Registrar registrar = new Registrar();
	private Controlador_Login controladorLogin;
	private Controlador_Registro controladorRegistro;
	
	//Valores de prueba
	String nombrePruebas = "prueba";
	
	@org.junit.Test
	public void testControladorLogin() {
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
    
    
}