package controlador;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.swing.JDialog;
import javax.swing.JOptionPane;

import modelo.Usuario;
import vista.Login;
import vista.Registrar;

public class Controlador_Registro implements MouseListener {
	
	private Registrar ventanaRegistro;
	private ObjectInputStream entrada;
	private ObjectOutputStream salida;
	private boolean prueba = false;
	private JDialog op;
	private JOptionPane a;
	
	public Controlador_Registro(Registrar registrar, ObjectInputStream entrada, ObjectOutputStream salida) {
		
		this.ventanaRegistro = registrar;
		this.entrada = entrada;
		this.salida = salida;
		iniciarControlador();
		
	}
	
	public boolean iniciarControlador() {
		
		this.ventanaRegistro.getFrame().setVisible(true);
		this.ventanaRegistro.getBotonAcceptar().addMouseListener(this);
		this.ventanaRegistro.getBotonAcceptar().setName("registrar");
		this.ventanaRegistro.getBotonVolver().addMouseListener(this);
		this.ventanaRegistro.getBotonVolver().setName("volver");
		
		return prueba;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
		switch (e.getComponent().getName()) {
		
		case "registrar":
			try {
				Usuario usuario = new Usuario();
				usuario.setUsuario(this.ventanaRegistro.getNombre().getText());
				usuario.setContrasena(this.ventanaRegistro.getContrasena().getText());
				usuario.setEMail(this.ventanaRegistro.getEmail().getText());
				this.salida.writeObject(2);
				this.salida.writeObject(usuario);
				String respuesta = this.entrada.readObject().toString();
				if (respuesta.equals("bien")){
					
					op = a.createDialog(ventanaRegistro.getFrame(), "Registro realizado");
					prueba = true;
				} else if (respuesta.equals("mal")) {
					op = a.createDialog(ventanaRegistro.getFrame(),"Ocurrió algún error. Registro no realizado");
					prueba = true;
				}
				prueba = true;

			} catch (Exception e1) {
				e1.printStackTrace();
			}
			break;
			
		case "volver":
			
			Login ventanaLogin = new Login();
			@SuppressWarnings("unused") Controlador_Login controladorLogin = new Controlador_Login(ventanaLogin, this.entrada, this.salida);
			this.ventanaRegistro.getFrame().dispose();
			prueba = true;
		default:
			break;
		}
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	public JDialog getOp() {
		return op;
	}
	
	
}
