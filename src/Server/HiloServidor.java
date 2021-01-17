package Server;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import javax.swing.JTextArea;
import javax.swing.JTextField;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import comunes.CrearHash;
import modelo.Usuario;

public class HiloServidor extends Thread {

	JTextArea textArea = null;
	JTextField textoF = null;
	ObjectOutputStream osalida = null;
	ObjectInputStream oentrada = null;
	ArrayList<ObjectOutputStream> lista = null;
	int ventana; // 1-Login //2-Registro // 3 - RestaurarContraseña enviar usuario // 4 - RestaurarContraseña enviar nueva contraseña
	Transaction tx;
	SessionFactory sesion;
	Session session;
	String hql;
	Query q;

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

				case 1:
					
					tx = null;	
					sesion = modelo.HibernateUtil.getSessionFactory();
					session = sesion.openSession();	
					tx = session.beginTransaction();
					
					usuario = (Usuario) oentrada.readObject();
					usuario.setContrasena(comunes.CrearHash.crearHash(usuario.getContrasena()));
					tx = session.beginTransaction();		
					hql = "from Usuario where usuario = '" + usuario.getUsuario() + "' and contrasena = '" + usuario.getContrasena() + "'";
					q = session.createQuery(hql);
					
					usuario = (Usuario) q.uniqueResult();
					
					osalida.writeObject(usuario);
					
					break;

				case 2:
					
					sesion = modelo.HibernateUtil.getSessionFactory();
					session = sesion.openSession();
					
					usuario = (Usuario) oentrada.readObject();
					
					usuario.setContrasena(comunes.CrearHash.crearHash(usuario.getContrasena()));
					usuario.setRespuesta(comunes.CrearHash.crearHash(usuario.getRespuesta()));
					
					if (modelo.InsertarBorrar.insertar(usuario, sesion, session)) {
						
						osalida.writeObject("bien");
						
					} else {
						
						osalida.writeObject("mal");
					}

					break;
					
				case 3:
					
					String nombreUsuario = (String) oentrada.readObject();
					
					tx = null;	
					sesion = modelo.HibernateUtil.getSessionFactory();
					session = sesion.openSession();	
					tx = session.beginTransaction();	
					hql = "from Usuario where usuario = '" + nombreUsuario + "'";
					q = session.createQuery(hql);
					
					usuario = (Usuario) q.uniqueResult();
					
					osalida.writeObject(usuario);
							
					break;
					
				case 4:
					
					usuario = (Usuario) oentrada.readObject();
					String nuevaContrasena = (String) oentrada.readObject();
					tx = null;
					sesion = modelo.HibernateUtil.getSessionFactory();
					session = sesion.openSession();	
					tx = session.beginTransaction();
					usuario.setContrasena(CrearHash.crearHash(nuevaContrasena));
					session.update(usuario);
					tx.commit();
					osalida.writeObject(true);
					
					break;

				}	
							

			} catch (Exception e) {
				// TODO Auto-generated catch block
//				e.printStackTrace();
			}

		}

	}

}
