package main;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import hiberclass.Estaciones;

public class Insertado {

	public static boolean insertarEstacion(Estaciones estacion, boolean test) {
		
		
		Transaction tx = null;
		SessionFactory sesion = HibernateUtil.getSessionFactory();
		Session session = sesion.openSession();
		tx = session.beginTransaction();

		// Insertar nueva estaci�n

		session.save(estacion);
		tx.commit();
		System.out.println();
		System.out.println("Estaci�n insertada");
		System.out.println();

		// borrado (para tests)

		if (test) {

			tx = session.beginTransaction();
			session.delete(estacion);
			tx.commit();

			System.out.println();
			System.out.println("Estaci�n borrada");
			System.out.println();

		}
		
		session.close();
		sesion.close();

		return true;
	}

}
