package Server;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import javax.swing.JTextArea;
import javax.swing.JTextField;

public class HiloServidor extends Thread {

	JTextArea textArea = null;
	JTextField textoF = null;
	ObjectOutputStream osalida = null;
	ObjectInputStream oentrada = null;
	ArrayList<ObjectOutputStream> lista = null;

	public HiloServidor(JTextArea textArea, JTextField texto, ObjectOutputStream osalida, ObjectInputStream oentrada,
			ArrayList<ObjectOutputStream> lista) {
		this.textArea = textArea;
		this.textoF = texto;
		this.osalida = osalida;
		this.oentrada = oentrada;
		this.lista = lista;
	}

	public void run() {

		try {
		String usuario  = oentrada.readUTF();
		String contrasena = oentrada.readUTF();
	
		
		
		
		
		
		
		
		
		
		String texto = "";
		while (!texto.equals("*")) {
			
				texto = (String) oentrada.readObject();
				if (!texto.equals("*")) {
				textArea.append(texto);
					for (int i = 0; i < lista.size(); i++) {
						ObjectOutputStream os = lista.get(i);
						os.writeObject(texto);
					}
				}
				else{
					lista.remove(osalida);
					
				}
			
		}
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		
		
		this.textoF.setText("Conexiones actuales: "+lista.size());
		
		
		System.out.println("Termino recibir ser");

	}

}

