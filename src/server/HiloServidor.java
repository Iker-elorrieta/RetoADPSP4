package server;

import java.awt.Color;
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
import modelo.Estaciones;
import modelo.Horario;
import modelo.Municipios;
import modelo.Usuario;

public class HiloServidor extends Thread {

	JTextArea textArea = null;
	JTextField textoF = null;
	ObjectOutputStream osalida = null;
	ObjectInputStream oentrada = null;
	ArrayList<ObjectOutputStream> lista = null;
	final String[] nombreArray = { "bizkaia", "araba/álava", "gipuzkoa", "nada" };
	int ventana;
	// 1-Login
	// 2-Registro
	// 3 - RestaurarContraseña enviar usuario
	// 4 - RestaurarContraseña enviar nueva contraseña
	// apartir de 500 son consulas SQL
	// 501 arrayList Bizkaia
	// 502 arraylist Araba
	// 503 arrayList Gipuzkoa
	Transaction tx;
	SessionFactory sesion;
	Session session;
	String hql;
	Query q;
	Boolean desconexion = false;
	ArrayList<Estaciones> araE;
	ArrayList<Horario> arah;

	public HiloServidor(JTextArea textArea, JTextField texto, ObjectOutputStream osalida, ObjectInputStream oentrada,
			ArrayList<ObjectOutputStream> lista) {
		this.textArea = textArea;
		this.textoF = texto;
		this.osalida = osalida;
		this.oentrada = oentrada;
		this.lista = lista;

	}

	@SuppressWarnings("unchecked")
	public void run() {

		Usuario usuario = new Usuario();

		while (!desconexion) {

			try {

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
					hql = "from Usuario where nombre = '" + usuario.getNombre() + "' and contrasena = '"
							+ usuario.getContrasena() + "'";
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
					hql = "from Usuario where nombre = '" + nombreUsuario + "'";
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

				case 404:

					String devuelto = (String) oentrada.readObject();
					araE = new ArrayList<Estaciones>();
					try {
						tx = null;
						sesion = modelo.HibernateUtil.getSessionFactory();
						session = sesion.openSession();
						tx = session.beginTransaction();
						hql = "from Estaciones where municipios.nombre='" + devuelto + "'";
						q = session.createQuery(hql);

						araE = (ArrayList<Estaciones>) q.list();

						session.close();
						osalida.writeObject(araE);
					} catch (Exception e2) {
						e2.printStackTrace();
					}

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

				case 6:

					int str = (Integer) oentrada.readObject();

					tx = null;
					sesion = modelo.HibernateUtil.getSessionFactory();
					session = sesion.openSession();
					tx = session.beginTransaction();

					hql = "from Horario where informes in (from Informes where estaciones in (from Estaciones where id="
							+ str + "))";

					q = session.createQuery(hql);

					System.out.println("he salido de la query");

					arah = (ArrayList<Horario>) q.list();

					osalida.writeObject(arah);

					break;

				default:

					desconexion = true;

					break;

				}

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

		lista.remove(lista.size() - 1);
		System.out.println("Hilo servidor acabado");

	}

	@SuppressWarnings({ "unchecked", "null" })
	private ArrayList<Municipios> recibirArrayMunicipios(String nombre) {
		ArrayList<Municipios> arrayMunicipio = null;

		try {
			tx = null;
			sesion = modelo.HibernateUtil.getSessionFactory();
			session = sesion.openSession();
			tx = session.beginTransaction();

			if (!nombre.equals("nada")) {

				hql = "from Municipios where Provincia = (Select id from Provincias where nombre='" + nombre + "')";
			} else {
				hql = "from Municipios";
			}
			q = session.createQuery(hql);

			arrayMunicipio = (ArrayList<Municipios>) q.list();

			ArrayList<Municipios> arrayLimpio = new ArrayList<Municipios>();

			for (int i = 0; i < arrayMunicipio.size(); i++) {

				if (arrayMunicipio.get(i).getEstacioneses().size() > 0) {
					arrayLimpio.add(arrayMunicipio.get(i));
				}

			}

			osalida.writeObject(arrayLimpio);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return arrayMunicipio;

	}
}
