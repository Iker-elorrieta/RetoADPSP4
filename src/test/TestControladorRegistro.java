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
import modelo.Usuario;
import vista.Logeado;
import vista.Login;
import vista.Registrar;

/**
 * Clase para comprobar que el programa funciona correctamente.
 */
public class TestControladorRegistro {
	
	private Registrar registrar = new Registrar();
	private Controlador_Registro controladorRegistro;
	SessionFactory sesion = HibernateUtil.getSessionFactory();
	Session session = sesion.openSession();
	
	//Valores de prueba
	String nombrePruebas = "prueba";
	
	//Para hacer esta prueba el servido debe estar en funcionamiento.
	
	@org.junit.Test
	public void testControladorRegistro() {
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
			user.setNombre(nombrePruebas);
			user.setContrasena(nombrePruebas);
			InsertarBorrar.borrar(user, sesion, session);
			socket.close();
		} catch (IOException e) {
			
		}
		assertEquals(true,controladorRegistro.iniciarControlador());
		
	}
    
    
}