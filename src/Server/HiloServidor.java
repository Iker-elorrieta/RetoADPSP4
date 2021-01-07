package Server;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import javax.swing.JTextArea;
import javax.swing.JTextField;

import modelo.Usuario;

public class HiloServidor extends Thread {

	JTextArea textArea = null;
	JTextField textoF = null;
	ObjectOutputStream osalida = null;
	ObjectInputStream oentrada = null;
	ArrayList<ObjectOutputStream> lista = null;
	int ventana; //1-Login //2-Registro

	public HiloServidor(JTextArea textArea, JTextField texto, ObjectOutputStream osalida, ObjectInputStream oentrada,
			ArrayList<ObjectOutputStream> lista) {
		this.textArea = textArea;
		this.textoF = texto;
		this.osalida = osalida;
		this.oentrada = oentrada;
		this.lista = lista;
		
	}

	public void run() {
		
		while(true) {
	
		
		try {
			
		Usuario user = new Usuario();
		
		ventana = (int) oentrada.readObject();
		
		switch(ventana) {
			
		
		case 1:		
				
				osalida.writeObject("Has entrado al servidor desde Login");
				user = (Usuario) oentrada.readObject();
				System.out.println(user.toString());
				
				
				
		break;
		
		
		
		
		case 2:
			osalida.writeObject("Has entrado al servidor desde Registro");
			user = (Usuario) oentrada.readObject();
			System.out.println(user.toString());
			
		break;
		
		
		
		
		
		
		
		
		
		
		
		}
		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		}
		
		}

	

}

