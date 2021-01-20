package controlador;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import modelo.Municipios;
import modelo.Usuario;
import vista.Logeado;

public class ControladorLogeado {
	
	private Logeado ventanaLogeado;
	private Usuario usuario;
	public static boolean booleanTest = false;
	
	/**
	 * {@summary Constructor de la clase que inicia el controlador}
	 * @param ventanaLogeado
	 * @param usuario
	 */
	
	public ControladorLogeado(Logeado ventanaLogeado, Usuario usuario) {
		
		this.ventanaLogeado = ventanaLogeado;
		this.usuario = usuario;
		iniciarControlador();
		
		booleanTest = true;
		
	}
	
	/**
	 * {@summary Método que hace visible el frame de la ventana y que cambia las etiquetas en función de los datos del usuario que ha iniciado sesión}
	 */
	
	public void iniciarControlador() {
		
		SessionFactory sesion = modelo.HibernateUtil.getSessionFactory();
		Session session = sesion.openSession();
		Transaction tx = session.beginTransaction();
		
		String hql = "from Municipios where Nombre = 'Inicio'";
		Query q = session.createQuery(hql);
		
		Municipios municipio = (Municipios) q.uniqueResult();
		
		this.ventanaLogeado.getFrame().setVisible(true);
		String contenido = "Municipio de prueba: " + municipio.toString();
		this.ventanaLogeado.getlabelHola().setText("Municipio de prueba: " + municipio.toString());
		this.ventanaLogeado.getlabelCorreo().setText("");
		
		
		
	}

}
