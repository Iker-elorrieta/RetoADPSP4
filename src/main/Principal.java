package main;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import controlador.Controlador_Cliente;
import vista.VentanaCliente;

public class Principal {

	public static void main(String[] args) {
		VentanaCliente cliente = new VentanaCliente();
		cliente.setVisible(true);
		try {
			ObjectOutputStream salida;
			ObjectInputStream entrada;
			Socket socket = new Socket("192.168.7.159",44444);
			entrada = new ObjectInputStream(socket.getInputStream());
			salida = new ObjectOutputStream(socket.getOutputStream());
			Controlador_Cliente controlador = new Controlador_Cliente(cliente,salida);
			System.out.println(entrada.readObject().toString());
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}

}
