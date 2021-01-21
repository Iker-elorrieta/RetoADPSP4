package modelo;


import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

/**
 * Clase necesaria para hacer la conexion a la base de datos con hibernate.
 * @author Grupo 4
 *
 */
public class HibernateUtil { 
  private static final SessionFactory sessionFactory = buildSessionFactory();
                                         
  private static SessionFactory buildSessionFactory() {
     try {
     
        return new Configuration().configure().buildSessionFactory(
                new StandardServiceRegistryBuilder().configure().build() );
     }
     catch (Throwable ex) {
       
        System.err.println("Initial SessionFactory creation failed." + ex);
        throw new ExceptionInInitializerError(ex);
     }
  }

  public static SessionFactory getSessionFactory() {
        return sessionFactory;
  }
}//

