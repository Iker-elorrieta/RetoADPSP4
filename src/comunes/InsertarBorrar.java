package comunes;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import modelo.Entornos;
import modelo.Municipios;

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

		try {
		session.save(object);
		
		
			tx.commit();
		} catch (Exception e) {
			e.printStackTrace();
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
	
	/**
	 * Para conseguir meter datos en la tabla entornomuni hace falta los id del entorno que es la funcionalidad de este metodo.
	 * @param entorno
	 * @param sesion
	 * @param session
	 * 
	 */
	public static int obtenerEntornoId(Entornos entorno,SessionFactory sesion,Session session) {
		
		String hql = new String();
		@SuppressWarnings("unused")
		Transaction tx = null;
		tx = session.beginTransaction();		  
		hql = "from Entornos where Nombre = '" + entorno.getNombre() + "'";
		Query q = session.createQuery(hql);
		
		entorno = (Entornos) q.uniqueResult();
		
		return entorno.getId();
		
	}
	
	/**
	 * Para conseguir meter datos en la tabla entornomuni hace falta los id del Municipio que es la funcionalidad de este metodo.
	 * @param entorno
	 * @param sesion
	 * @param session
	 * 
	 */
	public static int obtenerMunicipioId(Municipios municipio,SessionFactory sesion,Session session) {
		
		String hql = new String();
		@SuppressWarnings("unused")
		Transaction tx = null;
		tx = session.beginTransaction();		
		hql = "from Municipios where Nombre = '" + municipio.getNombre() + "'";
		Query q = session.createQuery(hql);
		
		municipio = (Municipios) q.uniqueResult();
		
		return municipio.getId();
		
	}

}
