package server;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

import javax.swing.JTextArea;
import javax.swing.JTextField;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import comunes.CrearHash;
import modelo.Entornos;
import modelo.Estaciones;
import modelo.Horario;
import modelo.Municipios;
import modelo.Provincias;
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
	ArrayList<Provincias> provi;

	public HiloServidor(JTextArea textArea, JTextField texto, ObjectOutputStream osalida, ObjectInputStream oentrada,
			ArrayList<ObjectOutputStream> lista) {
		this.textArea = textArea;
		this.textoF = texto;
		this.osalida = osalida;
		this.oentrada = oentrada;
		this.lista = lista;

		tx = null;
		sesion = modelo.HibernateUtil.getSessionFactory();
		session = sesion.openSession();
		tx = session.beginTransaction();
		hql = "from Provincias";
		q = session.createQuery(hql);

		provi = (ArrayList<Provincias>) q.list();

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

					arah = (ArrayList<Horario>) q.list();

					osalida.writeObject(arah);

					break;

				case 7:
					ArrayList<ListadoTop> listado = new ArrayList<ListadoTop>();
					ArrayList<Horario> arrayTopHorario = new ArrayList<Horario>();
					ArrayList<Entornos> arrayTopEntorno = new ArrayList<Entornos>();
					ArrayList<Entornos> arrayLimpioEntorno = new ArrayList<Entornos>();
					tx = null;
					sesion = modelo.HibernateUtil.getSessionFactory();
					session = sesion.openSession();
					tx = session.beginTransaction();
					// hql = "from Municipios INNER JOIN Estaciones on Municipios.id =
					// Estaciones.municipio INNER JOIN Informes ON Estaciones.id = Informes.estacion
					// INNER JOIN Horario ON Horario.informe = Informes.id where Horario.nogm3 is
					// not null AND latitud is not null and longitud is not null and CoordenadaX is
					// not null and CoordenadaY is not null and COmgm3 is not null and NO2ICA is not
					// null and NOXgm3 pm10ica is not null and pm25ica is not null and so2 is not
					// null and so2ica is not null ORDER BY Horario.nogm3 DESC";
					hql = "from Horario order by nogm3 desc ";

					q = session.createQuery(hql);

					arrayTopHorario = (ArrayList<Horario>) q.list();
					String nombreMuni = "";

					for (int i = 0; i < arrayTopHorario.size(); i++) {
						if (listado.size() < 5) {

							if (!nombreMuni.contains(arrayTopHorario.get(i).getInformes().getEstaciones().getMunicipios().getNombre())) {
								ListadoTop objectoHaInsertar = new ListadoTop();
								objectoHaInsertar.setParam1(arrayTopHorario.get(i).getInformes().getEstaciones().getMunicipios().getNombre());
								objectoHaInsertar.setParam2(arrayTopHorario.get(i).getNogm3());
								listado.add(objectoHaInsertar);
							}
						}
						nombreMuni += arrayTopHorario.get(i).getInformes().getEstaciones().getMunicipios().getNombre();
					}

					osalida.writeObject(listado);
					listado = new ArrayList<ListadoTop>();
					nombreMuni = "";
					
					for (int i = 0; i < arrayTopHorario.size(); i++) {

						if (listado.size() < 5) {

							if (!nombreMuni.contains(arrayTopHorario.get(i).getInformes().getEstaciones().getMunicipios().getNombre())&& arrayTopHorario.get(i).getInformes().getEstaciones().getMunicipios().getProvincias().getNombre().toLowerCase().contains("bizkaia")) {
								ListadoTop objectoHaInsertar = new ListadoTop();
								objectoHaInsertar.setParam1(arrayTopHorario.get(i).getInformes().getEstaciones().getMunicipios().getNombre());
								objectoHaInsertar.setParam2(arrayTopHorario.get(i).getNogm3());
								listado.add(objectoHaInsertar);
							}
						}
						nombreMuni += arrayTopHorario.get(i).getInformes().getEstaciones().getMunicipios().getNombre();
					}

					osalida.writeObject(listado);
					listado = new ArrayList<ListadoTop>();
					nombreMuni = "";
					
					for (int i = 0; i < arrayTopHorario.size(); i++) {

						if (listado.size() < 5) {
							if (!nombreMuni.contains(arrayTopHorario.get(i).getInformes().getEstaciones().getMunicipios().getNombre())&& arrayTopHorario.get(i).getInformes().getEstaciones().getMunicipios().getProvincias().getNombre().toLowerCase().contains("araba")) {
								ListadoTop objectoHaInsertar = new ListadoTop();
								objectoHaInsertar.setParam1(arrayTopHorario.get(i).getInformes().getEstaciones().getMunicipios().getNombre());
								objectoHaInsertar.setParam2(arrayTopHorario.get(i).getNogm3());
								listado.add(objectoHaInsertar);
							}
						}
						nombreMuni += arrayTopHorario.get(i).getInformes().getEstaciones().getMunicipios().getNombre();
					}

					osalida.writeObject(listado);
					listado = new ArrayList<ListadoTop>();
					nombreMuni = "";
					
					for (int i = 0; i < arrayTopHorario.size(); i++) {

						if (listado.size() < 5) {

							if (!nombreMuni.contains(arrayTopHorario.get(i).getInformes().getEstaciones().getMunicipios().getNombre())&& arrayTopHorario.get(i).getInformes().getEstaciones().getMunicipios().getProvincias().getNombre().toLowerCase().contains("gipu")) {
								ListadoTop objectoHaInsertar = new ListadoTop();
								objectoHaInsertar.setParam1(arrayTopHorario.get(i).getInformes().getEstaciones().getMunicipios().getNombre());
								objectoHaInsertar.setParam2(arrayTopHorario.get(i).getNogm3());
								listado.add(objectoHaInsertar);	
							}
						}
						nombreMuni += arrayTopHorario.get(i).getInformes().getEstaciones().getMunicipios().getNombre();
					}
					osalida.writeObject(listado);
					
					
					listado = new ArrayList<ListadoTop>();
					nombreMuni = "";
					
					for (int i = 0; i < arrayTopHorario.size(); i++) {
						
						hql = "from Entornos where id in (Select entornos.id from Entornosmuni where municipios.id ="+ arrayTopHorario.get(i).getInformes().getEstaciones().getMunicipios().getId()+")";

						q = session.createQuery(hql);
						try {
							ListadoTop objectoHaInsertar;
							for(int y = 0 ; y < q.list().size();y++)
							{
								if(!nombreMuni.contains(((Entornos) q.list().get(y)).getNombre())){
									objectoHaInsertar = new ListadoTop();
									objectoHaInsertar.setParam1(((Entornos) q.list().get(y)).getNombre());
									objectoHaInsertar.setParam2(arrayTopHorario.get(i).getNogm3());
									listado.add(objectoHaInsertar);
								}
							}
						} catch (NullPointerException e) {
//							System.out.println(i+") es nulo");
						}
						catch(IndexOutOfBoundsException a) {
							
						}
						try{
							nombreMuni += listado.get(listado.size()-1).getParam1();
						}catch(Exception b){
							
						}
					}
					osalida.writeObject(listado);
					
					
					
					

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

		System.out.println("Hilo servidor acabado");

	}

	@SuppressWarnings({ "unchecked" })
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

	public static class ListadoTop implements Serializable{
		
		private String param1;
		private Double param2;
		private String param3;
		
		public ListadoTop(){}
		
		public ListadoTop(Municipios muni, Object dato){
			param1 = muni.getNombre();
			if(dato instanceof Double)
			{
				param2 = (Double) dato;
			}
			else if(dato instanceof String)
			{
				param3 = (String) dato;
			}
		}

		public String getParam1() {
			return param1;
		}

		public Double getParam2() {
			return param2;
		}

		public String getParam3() {
			return param3;
		}

		public void setParam3(String param3) {
			this.param3 = param3;
		}

		public void setParam1(String param1) {
			this.param1 = param1;
		}

		public void setParam2(Double param2) {
			this.param2 = param2;
		}	
	}
}
