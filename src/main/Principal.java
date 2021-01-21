package main;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.HashSet;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import controlador.Controlador_Login;
import modelo.InsertarBorrar;
import modelo.Municipios;
import modelo.Provincias;
import vista.Login;

/**
 * Esta clase es la del cliente.
 * @author Grupo 4
 *
 */
public class Principal {

	public static void main(String[] args) {
		start();
	}
	
	/**
	 * Main de la clase
	 */
	public static boolean start()
	{
		Login cliente = new Login();
		cliente.getFrame().setVisible(true);
		SessionFactory sesion = modelo.HibernateUtil.getSessionFactory();
		Session session = sesion.openSession();
		Set set = new HashSet(0);
		Provincias provincia = new Provincias("Inicio"); 
		Municipios municipio = new Municipios(provincia,"Inicio", "Inicio",(double) 1,(double) 1, "1", set, set);
		
		try{
			InsertarBorrar.insertar(provincia, sesion, session);
			InsertarBorrar.insertar(municipio, sesion, session);
		}catch(Exception a){/*a.printStackTrace();*/}
		try {
			@SuppressWarnings("resource")
			Socket socket = new Socket("127.0.0.1",44444);
			ObjectInputStream entrada = new ObjectInputStream(socket.getInputStream());
			ObjectOutputStream salida = new ObjectOutputStream(socket.getOutputStream());
			@SuppressWarnings("unused")
			Controlador_Login  controlador = new Controlador_Login(cliente, entrada, salida);
//			System.err.println(entrada.readObject());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		return true;
	}

}
