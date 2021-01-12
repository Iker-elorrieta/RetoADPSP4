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
		this.ventanaLogeado.getLblNewLabel().setText(this.ventanaLogeado.getLblNewLabel().getText() + " " + this.usuario.getUsuario());
		this.ventanaLogeado.getLblNewLabel_1().setText(this.ventanaLogeado.getLblNewLabel_1().getText() + " " + this.usuario.getEMail());
		
	}

}
