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
import modelo.Municipios;
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
		Transaction tx = session.beginTransaction();
		try
		{
		Set set = new HashSet(0);
		Municipios municipio = null;

			municipio = new Municipios("Inicio", "Inicio",(double) 1,(double) 1, "1", set, set);
			session.save(municipio);
		
		}
		catch(Exception a)
		{
		}
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
