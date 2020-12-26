package main;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import hiberclass.Entornos;
import hiberclass.Municipios;

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
		
		SessionFactory sesion = HibernateUtil.getSessionFactory();
		Session session = sesion.getCurrentSession();
		
		Transaction tx = null;
		tx = session.beginTransaction();

		// Insertar nuevo objeto

		session.save(object);
		tx.commit();
		System.out.println();
		System.out.println("Estación insertada");
		System.out.println();

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
	
	public static int obtenerEntornoId(Entornos entorno) {
		
		String hql = new String();
		Transaction tx = null;
		SessionFactory sesion = HibernateUtil.getSessionFactory();
		Session session = sesion.openSession();
		tx = session.beginTransaction();		
		hql = "from Entornos where Nombre = '" + entorno.getNombre() + "'";
		Query q = session.createQuery(hql);
		
		entorno = (Entornos) q.uniqueResult();
		
		return entorno.getId();
		
	}
	
	public static int obtenerMunicipioId(Municipios municipio) {
		
		String hql = new String();
		Transaction tx = null;
		SessionFactory sesion = HibernateUtil.getSessionFactory();
		Session session = sesion.openSession();
		tx = session.beginTransaction();		
		hql = "from Municipios where Nombre = '" + municipio.getNombre() + "'";
		Query q = session.createQuery(hql);
		
		municipio = (Municipios) q.uniqueResult();
		
		return municipio.getId();
		
	}

}
