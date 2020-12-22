package main;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class InsertarBorrar {

	public static boolean insertar(Object object) {
		
		
		Transaction tx = null;
		SessionFactory sesion = HibernateUtil.getSessionFactory();
		Session session = sesion.openSession();
		tx = session.beginTransaction();

		// Insertar nuevo objeto

		session.save(object);
		tx.commit();
		System.out.println();
		System.out.println("Estación insertada");
		System.out.println();

		session.close();


		return true;
	}
	
	public static void borrar(Object object) {
		
		Transaction tx = null;
		SessionFactory sesion = HibernateUtil.getSessionFactory();
		Session session = sesion.openSession();
		tx = session.beginTransaction();

		// Insertar nueva estación

		session.delete(object);
		tx.commit();

		session.close();


	}
	

}
