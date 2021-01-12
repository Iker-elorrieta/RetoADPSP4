package Server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import javax.swing.JTextArea;
import javax.swing.JTextField;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.exception.ConstraintViolationException;

import modelo.Usuario;

public class HiloServidor extends Thread {

	JTextArea textArea = null;
	JTextField textoF = null;
	ObjectOutputStream osalida = null;
	ObjectInputStream oentrada = null;
	ArrayList<ObjectOutputStream> lista = null;
	int ventana; // 1-Login //2-Registro

	public HiloServidor(JTextArea textArea, JTextField texto, ObjectOutputStream osalida, ObjectInputStream oentrada,
			ArrayList<ObjectOutputStream> lista) {
		this.textArea = textArea;
		this.textoF = texto;
		this.osalida = osalida;
		this.oentrada = oentrada;
		this.lista = lista;

	}

	public void run() {

		while (true) {

			try {

				Usuario user = new Usuario();

				ventana = (int) oentrada.readObject();

				switch (ventana) {

				case 1:

					osalida.writeObject("Has entrado al servidor desde Login");
					Usuario usuario = (Usuario) oentrada.readObject();
					System.out.println(usuario.getUsuario());

					break;

				case 2:
					osalida.writeObject("Has entrado al servidor desde Registro");
					String hql = new String();
					SessionFactory sesion = modelo.HibernateUtil.getSessionFactory();
					Session session = sesion.openSession();
					
					usuario = (Usuario) oentrada.readObject();
					if (modelo.InsertarBorrar.insertar(usuario, sesion, session)) {
						
						osalida.writeObject("bien");
						
					} else {
						
						osalida.writeObject("mal");
					}
					
				

					break;

				}

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

	}

}
