package Server;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import javax.swing.JTextArea;
import javax.swing.JTextField;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import modelo.InsertarBorrar;
import modelo.Municipios;
import modelo.Usuario;

/**
 * Clase que comunica con cada cliente que se crea al acceptar una conexion.
 * @author Grupo 4
 *
 */
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

				Usuario usuario = new Usuario();

				ventana = (int) oentrada.readObject();

				switch (ventana) {
				case 1: //En caso de que nos llega una peticion de la ventana de login comprobamos si el usuario introducido se encuentra en la base de datos.

					@SuppressWarnings("unused") Transaction tx = null;	
					SessionFactory sesion = modelo.HibernateUtil.getSessionFactory();
					Session session = sesion.openSession();
					tx = session.beginTransaction();
					
					usuario = (Usuario) oentrada.readObject();
					usuario.setContrasena(crearHash(usuario.getContrasena()));
					String hql = "from Usuario where nombre = '" + usuario.getNombre() + "' and contrasena = '" + usuario.getContrasena() + "'";
					Query q = session.createQuery(hql);
					
					usuario = (Usuario) q.uniqueResult();
					
					osalida.writeObject(usuario);
					
					break;
				
				case 2: //En caso de que nos llegue una peticion de la ventana de registro debemos crear el usuario en la base de datos comprobando si existe ya o no.
					
					sesion = modelo.HibernateUtil.getSessionFactory();
					session = sesion.openSession();
					
					usuario = (Usuario) oentrada.readObject();
					
					usuario.setContrasena(crearHash(usuario.getContrasena()));
					if (modelo.InsertarBorrar.insertar(usuario, sesion, session)) {
						
						osalida.writeObject("bien");
						
					} else {
						
						osalida.writeObject("mal");
					}
				

					break;

				}

			} catch (Exception e) {
				// TODO Auto-generated catch block
//				e.printStackTrace();
			}

		}

	}
	
	/**
	 * Metodo para sacar el hash de las contraseñas y posiblemente los ficheros mas adelante.
	 * Como practicas tenemos la encryptación SHA de momento que es poco segura mas adelante en el programa cambiaremos este parametro.
	 * @param texto
	 * @return hash
	 */
	public static String crearHash (String texto){
        @SuppressWarnings("unused")
		Byte [] hash = null;
        String resumenAString = "";
        MessageDigest md;
        try {

            md = MessageDigest.getInstance("SHA");

            byte dataBytes[] = texto.getBytes();
            md.update(dataBytes);
            byte resumen[] = md.digest();
            resumenAString = new String(resumen);

        } catch (NoSuchAlgorithmException e) {
            System.out.println("Error hash");
        }
        return resumenAString;

    }

}
