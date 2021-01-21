package Server;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.fasterxml.jackson.databind.ObjectMapper;

import modelo.Entornos;
import modelo.Estaciones;
import modelo.Informes;
import modelo.Json;
import modelo.Municipios;

/**
 * Esta es la clase que accepta conexiones de los clientes
 * siendo limitado a 870 conexiones
 * El puerto de este servidor se encuentra en 44444
 * @author Grupo 4
 *
 */
public class ServidorPeticiones extends Thread {

	ArrayList<ObjectOutputStream> lista;
	int PUERTO = 44444;
	int MAXIMO_CONEXIONES = 870;
	JTextArea textArea = null;
	JTextField texto = null;
	boolean continuar = true;
	ServerSocket servidor = null;
	JLabel hora;
	boolean varPrueba = false;

	/**
	 * Constructor del servidor
	 * @param textArea
	 * @param texto
	 * @param hora
	 */
	public ServidorPeticiones(JTextArea textArea, JTextField texto,JLabel hora) {
		lista = new ArrayList<ObjectOutputStream>();
		this.texto = texto;
		this.textArea = textArea;
		this.hora = hora;
		texto.setText("0");
	}

	public void run() {

		try {
			servidor = new ServerSocket(PUERTO);

			System.out.println("Servidor iniciado...");
			@SuppressWarnings("resource")
			Socket socket = new Socket();
			texto.setText("Conexiones actuales: "+ lista.size());
			
			SessionFactory sesion = modelo.HibernateUtil.getSessionFactory();
			Session session = sesion.openSession();
			
			String hql = "from Provincias";
			Query q = session.createQuery(hql);
			
			if(!(q.list().size() > 1) || q == null)
			{
				Json json = new Json();
				ArrayList<Object> listaJson = json.cargarJsons();
				json.cargarTodosLosDatos((ArrayList<Informes>) listaJson.get(0),(ObjectMapper) listaJson.get(1),(Municipios[]) listaJson.get(2),(Entornos[]) listaJson.get(3),(Estaciones[]) listaJson.get(4), (Informes[]) listaJson.get(5), sesion, session);
			}
			
			while (continuar) {
				socket = servidor.accept();
				if (lista.size() < MAXIMO_CONEXIONES) {
					ObjectOutputStream osalida = new ObjectOutputStream(socket.getOutputStream());
					lista.add(osalida);
					ObjectInputStream oentrada = new ObjectInputStream(socket.getInputStream());
					texto.setText("Conexiones actuales: "+ lista.size());
					HiloServidor hilo = new HiloServidor(textArea, texto, osalida, oentrada, lista);
					hilo.start();
					varPrueba = true;
				}
				else
					socket.close();
			}

			socket.close();
			
			System.out.println("Servidor terminado");
			varPrueba = true;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("Servidor cerrado");
			System.exit(0);
		}
	}

	/**
	 * Metodo para cerrar el servidor.
	 * 
	 */
	public boolean desconectar() {
	continuar = false;
	try {
			for(int i = 0;i<lista.size();i++)
			{
				ObjectOutputStream os = lista.get(i);
				os.writeObject("*");
			}
			if(servidor != null)
				servidor.close();
			else
				continuar = true;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
		
		return true;
	}
	
	public boolean prueba()
	{
		return varPrueba;
	}
}
