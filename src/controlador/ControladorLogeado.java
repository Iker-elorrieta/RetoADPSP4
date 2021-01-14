package controlador;

import modelo.Usuario;
import vista.Logeado;

public class ControladorLogeado {
	
	private Logeado ventanaLogeado;
	private Usuario usuario;
	
	public ControladorLogeado(Logeado ventanaLogeado, Usuario usuario) {
		
		this.ventanaLogeado = ventanaLogeado;
		this.usuario = usuario;
		iniciarControlador();
		
	}
	
	public void iniciarControlador() {
		
		this.ventanaLogeado.getFrame().setVisible(true);
		this.ventanaLogeado.getlabelHola().setText(this.ventanaLogeado.getlabelHola().getText() + " " + this.usuario.getUsuario());
		this.ventanaLogeado.getlabelCorreo().setText(this.ventanaLogeado.getlabelCorreo().getText() + " " + this.usuario.getEMail());
		
	}

}
