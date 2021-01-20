package Server;

import java.io.IOException;
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
import modelo.Municipios;
import modelo.Usuario;

public class HiloServidor extends Thread {

	JTextArea textArea = null;
	JTextField textoF = null;
	ObjectOutputStream osalida = null;
	ObjectInputStream oentrada = null;
	ArrayList<ObjectOutputStream> lista = null;
	final String[] nombreArray = {"bizkaia","araba/álava","gipuzkoa","nada"};
	int ventana; 
	// 1-Login 
	//2-Registro 
	// 3 - RestaurarContraseña enviar usuario 
	// 4 - RestaurarContraseña enviar nueva contraseña
	//apartir de 500 son consulas SQL
	//501 arrayList Bizkaia
	//502 arraylist Araba
	//503 arrayList Gipuzkoa
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
					
					if (comunes.InsertarBorrar.insertar(usuario, sesion, session)) {
						
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
					
				case 5:		
					recibirArrayMunicipios(nombreArray[3]);	
					
				break;	
				
				case 501:
					
					recibirArrayMunicipios(nombreArray[0]);	
					
				break;
				
				
				case 502:
					
					recibirArrayMunicipios(nombreArray[1]);	
					
				break;
				
				
				case 503:
					
					recibirArrayMunicipios(nombreArray[2]);		
					
				break;
					
					
				}	
		

			} catch (Exception e) {
				// TODO Auto-generated catch block
//				e.printStackTrace();
			}

		}

		
		
		
		
	}
	private ArrayList<Municipios> recibirArrayMunicipios(String nombre) {
		ArrayList<Municipios> arrayMunicipio = null;
		
		try {
		tx = null;	
		sesion = modelo.HibernateUtil.getSessionFactory();
		session = sesion.openSession();	
		tx = session.beginTransaction();
		
		if(!nombre.equals("nada")) {
		hql = "from Municipios where Provincia = (Select Id from Provincias where nombre='"+nombre+"'";
		}else {
		hql = "from Municipios";	
		}
		q = session.createQuery(hql);
		
		arrayMunicipio = (ArrayList<Municipios>) q.list();
		
		osalida.writeObject(arrayMunicipio);
		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		
		return arrayMunicipio;
		
	}
}
