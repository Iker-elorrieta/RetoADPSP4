package controlador;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.swing.JOptionPane;

import modelo.Usuario;
import vista.Registrar;

public class Controlador_Registro implements MouseListener {
	
	private Registrar registrar;
	private ObjectInputStream entrada;
	private ObjectOutputStream salida;
	
	public Controlador_Registro(Registrar registrar, ObjectInputStream entrada, ObjectOutputStream salida) {
		
		this.registrar = registrar;
		this.entrada = entrada;
		this.salida = salida;
		iniciarControlador();
		
	}
	
	public void iniciarControlador() {
		
		this.registrar.getBotonAcceptar().addMouseListener(this);
		this.registrar.getBotonAcceptar().setName("registrar");
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
		switch (e.getComponent().getName()) {
		case "registrar":
			try {
				Usuario usuario = new Usuario();
				usuario.setUsuario(this.registrar.getNombre().getText());
				usuario.setContrasena(this.registrar.getContrasena().getText());
				usuario.setEMail(this.registrar.getEmail().getText());
				this.salida.writeObject(2);
				System.out.println(this.entrada.readObject());
				this.salida.writeObject(usuario);
				String respuesta = this.entrada.readObject().toString();
				if (respuesta.equals("bien")){
					
					JOptionPane.showMessageDialog(null,"Registro realizado", "Información", JOptionPane.INFORMATION_MESSAGE);
					
				} else if (respuesta.equals("mal")) {
					
					JOptionPane.showMessageDialog(null,"Ocurrió algún error. Registro no realizado", "Información", JOptionPane.ERROR_MESSAGE);
					
				}
				
						
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			break;

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
}
