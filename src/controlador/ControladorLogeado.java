package controlador;

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
		
		this.ventanaLogeado.getFrame().setVisible(true);
		this.ventanaLogeado.getlabelHola().setText(this.ventanaLogeado.getlabelHola().getText() + this.usuario.getUsuario());
		this.ventanaLogeado.getlabelCorreo().setText(this.ventanaLogeado.getlabelCorreo().getText() + this.usuario.getPregunta());
		
	}

}
