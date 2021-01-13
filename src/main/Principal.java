package main;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;


import controlador.Controlador_Login;
import modelo.HibernateUtil;
import modelo.InsertarBorrar;
import modelo.Usuario;
import vista.Login;

public class Principal {

	public void main(String[] args) {
		
		start();
		
	}
	
	public boolean start()
	{
		Login cliente = new Login();
		cliente.setVisible(true);
		try {
			@SuppressWarnings("resource")
			Socket socket = new Socket("127.0.0.1",44444);
			ObjectInputStream entrada = new ObjectInputStream(socket.getInputStream());
			ObjectOutputStream salida = new ObjectOutputStream(socket.getOutputStream());
			@SuppressWarnings("unused")
			Controlador_Login  controlador = new Controlador_Login(cliente, entrada, salida);
//			System.err.println(entrada.readObject());
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		return true;
	}

}
