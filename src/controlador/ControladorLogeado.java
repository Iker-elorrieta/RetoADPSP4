package controlador;

import modelo.Usuario;
import vista.Logeado;

public class ControladorLogeado {
	
	private Logeado ventanaLogeado;
	private Usuario usuario;
	public static boolean booleanTest = false;
	
	public ControladorLogeado(Logeado ventanaLogeado, Usuario usuario) {
		
		this.ventanaLogeado = ventanaLogeado;
		this.usuario = usuario;
		iniciarControlador();
		
		booleanTest = true;
		
	}
	
	public void iniciarControlador() {
		
		this.ventanaLogeado.getFrame().setVisible(true);
		this.ventanaLogeado.getlabelHola().setText(this.ventanaLogeado.getlabelHola().getText() + " " + this.usuario.getUsuario());
		this.ventanaLogeado.getlabelCorreo().setText(this.ventanaLogeado.getlabelCorreo().getText() + " " + this.usuario.getEMail());
		
		
		
	}

}
