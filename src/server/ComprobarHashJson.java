package server;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Calendar;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import comunes.Json;
import modelo.Horario;
import modelo.Informes;

public class ComprobarHashJson extends Thread {

	public ComprobarHashJson() {

	}

	public void run() {
		System.out.println("Se ha inciado el hilo de actualizar.");
		while (true) {

			try {
				
				boolean actualizarJson = false;
				String hash = new String();
				BufferedReader reader = null;
				URL url = new URL("https://www.google.com");
				
				ArrayList <Informes> listaInformes = obtenerInformes();
				
				for (Informes informe : listaInformes) {
					
					comprobarPagina(informe.getUrl());
					url = new URL(informe.getUrl());
					reader = new BufferedReader(new InputStreamReader(url.openStream()));
					hash = informe.getHash();
					StringBuffer buffer = new StringBuffer();
					int read;
					char[] chars = new char[1024];
					while ((read = reader.read(chars)) != -1)
						buffer.append(chars, 0, read);

					String hashcomprobado = comunes.CrearHash.crearHash(buffer.toString());
					
					if (!hash.equals(hashcomprobado)) {

						hash = hashcomprobado;
						informe.setHash(hash);
						actualizarHash(informe);
						actualizarJson = true;

					} 
								
				}
				
				if (actualizarJson) {
					
//				Descomentar cuando se llame al método que mete los json en la base de datos
					
				borrarHorarios();
				insertarHorarios();
					System.out.println("Hay que actualizar");
					
				} else {
					
					System.out.println("No hay que actualizar");
					
				}
			
				
				Thread.sleep(600000);
				
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
//				e.printStackTrace();
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
//				e.printStackTrace();
			}catch(FileNotFoundException e) {
				System.out.println("No se pudo comprobar con el json de la url " + e.getLocalizedMessage());
			} catch (IOException e) {
				// TODO Auto-generated catch block
//				e.printStackTrace();
			}

		}

	}
	
	public static void comprobarPagina(String url) {
		try {
			SSLContext ssl_ctx = SSLContext.getInstance("TLS");
			TrustManager[] trust_mgr = new TrustManager[] { (TrustManager) new X509TrustManager() {
				public X509Certificate[] getAcceptedIssuers() {
					return null;
				}

				public void checkClientTrusted(X509Certificate[] certs, String t) {
				}

				public void checkServerTrusted(X509Certificate[] certs, String t) {
				}
			} };
			ssl_ctx.init(null, // key manager
					trust_mgr, // trust manager
					new SecureRandom()); // random number generator
			HttpsURLConnection.setDefaultSSLSocketFactory(ssl_ctx.getSocketFactory());
			HttpsURLConnection huc = null;
			huc = (HttpsURLConnection) (new URL(url).openConnection());
			huc.getResponseCode();
		} catch (Exception e) {
//			e.printStackTrace();
		}
	}
	
	public static ArrayList <Informes> obtenerInformes() {
		
		SessionFactory sesion = modelo.HibernateUtil.getSessionFactory();
		Session session = sesion.openSession();

		String hql = "from Informes";
		Query q = session.createQuery(hql);
		
		ArrayList <Informes> listaInformes = new ArrayList<Informes>();
		
		for(int i = 0; i < q.list().size(); i++) {
			
			Informes informe = (Informes) q.list().get(i);
			listaInformes.add((Informes)q.list().get(i));
				
		}
		
		session.close();
		
		return listaInformes;
		
	}
	
	public static void insertarHorarios()
	{
		SessionFactory sesion = modelo.HibernateUtil.getSessionFactory();
		Session session = sesion.openSession();	
		Json actualizar = new Json();
		actualizar.insertarHorarios(sesion, session);
		session.close();
	}
	
	public static boolean actualizarHash(Informes informe) {
		
		SessionFactory sesion = modelo.HibernateUtil.getSessionFactory();
		Session session = sesion.openSession();
		Transaction tx = null;	
		
		tx = session.beginTransaction();		
		session.update(informe);
		tx.commit();
		session.close();
		
		return true;
		
	}
	
	public static boolean borrarHorarios() {
		
		SessionFactory sesion = modelo.HibernateUtil.getSessionFactory();
		Session session = sesion.openSession();
		Transaction tx = null;	
		
		String hql = "from Horario";
		Query q = session.createQuery(hql);
		
		ArrayList <Horario> listaHorarios = new ArrayList<Horario>();
		
		for(int i = 0; i < q.list().size(); i++) {
			
			Horario horario = (Horario) q.list().get(i);
			
		}
		
		listaHorarios = (ArrayList<Horario>) q.list();
		
		for (Horario horario : listaHorarios) {
			
			session.delete(horario);
			
		}
		
		tx.commit();
		session.close();
		
		return true;
	}
	
}
