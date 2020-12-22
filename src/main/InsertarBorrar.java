package main;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

/**
 * Clase de modificacion BDD
 */
public class InsertarBorrar {

	/**
	 * Metodo para insertar un objeto en la base de datos.
	 * @param object
	 * @return
	 */
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
	
	/**
	 * Metodo para borrar un objeto de la base de datos.
	 * @param object
	 */
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
