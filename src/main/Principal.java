package main;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import controlador.Controlador_Login;
import vista.Login;

public class Principal {

	public static void main(String[] args) {
		
		try {
			ObjectOutputStream salida;
			ObjectInputStream entrada;
			@SuppressWarnings("resource")
			Socket socket = new Socket("127.0.0.1",44444);
			entrada = new ObjectInputStream(socket.getInputStream());
			salida = new ObjectOutputStream(socket.getOutputStream());
			Login cliente = new Login();
			@SuppressWarnings("unused")
			Controlador_Login  controlador = new Controlador_Login(cliente, entrada, salida);
//			System.err.println(entrada.readObject());
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
		
		
	}

}
