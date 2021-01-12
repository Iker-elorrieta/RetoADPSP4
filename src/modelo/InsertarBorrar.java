package modelo;

import org.hibernate.Query;
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
	public static boolean insertar(Object object,SessionFactory sesion,Session session) {
		
		Transaction tx = null;
		tx = session.beginTransaction();

		// Insertar nuevo objeto

		session.save(object);
		
		try {
			tx.commit();
		} catch (Exception e) {

			return false;
		}
		
		System.out.println("Objeto insertado");
		
		return true;
	}
	
	/**
	 * Metodo para borrar un objeto de la base de datos.
	 * @param object
	 */
	public static void borrar(Object object,SessionFactory sesion,Session session) {
		
		Transaction tx = null;
		tx = session.beginTransaction();

		// Insertar nueva estación

		session.delete(object);
		tx.commit();
	}
	
	public static int obtenerEntornoId(Entornos entorno,SessionFactory sesion,Session session) {
		
		String hql = new String();
		Transaction tx = null;
		tx = session.beginTransaction();		  
		hql = "from Entornos where Nombre = '" + entorno.getNombre() + "'";
		Query q = session.createQuery(hql);
		
		entorno = (Entornos) q.uniqueResult();
		
		return entorno.getId();
		
	}
	
	public static int obtenerMunicipioId(Municipios municipio,SessionFactory sesion,Session session) {
		
		String hql = new String();
		Transaction tx = null;
		tx = session.beginTransaction();		
		hql = "from Municipios where Nombre = '" + municipio.getNombre() + "'";
		Query q = session.createQuery(hql);
		
		municipio = (Municipios) q.uniqueResult();
		
		return municipio.getId();
		
	}

}
