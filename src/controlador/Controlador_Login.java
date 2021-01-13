package controlador;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.swing.JOptionPane;

import modelo.Usuario;
import vista.Logeado;
import vista.Login;
import vista.Registrar;

public class Controlador_Login implements MouseListener {
	
	private Login ventanaLogin;
	private ObjectOutputStream salida;
	private ObjectInputStream entrada;
	
	public Controlador_Login(Login frame, ObjectInputStream entrada, ObjectOutputStream salida) {
		
		this.ventanaLogin = frame;
		this.entrada = entrada;
		this.salida = salida;
		iniciarcontrolador();		
		
	}
	
	public boolean iniciarcontrolador() {
		
		this.ventanaLogin.getFrame().setVisible(true);
		this.ventanaLogin.getBotonIniciar().addMouseListener(this);
		this.ventanaLogin.getBotonIniciar().setName("entrar");
		this.ventanaLogin.getBotonRegistrar().addMouseListener(this);
		this.ventanaLogin.getBotonRegistrar().setName("registrar");
		
		return true;
	
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
		switch (e.getComponent().getName()) {
		case "entrar":
			
			Usuario nuevo = new Usuario();
			nuevo.setUsuario(ventanaLogin.getNombre().getText());
			nuevo.setContrasena(ventanaLogin.getContrasena().getText());
			try {
				salida.writeObject(1);
				salida.writeObject(nuevo);
				nuevo = (Usuario) entrada.readObject();
				
				if (nuevo != null) {
					
					JOptionPane.showMessageDialog(null,"Ha iniciado sesión", "Información", JOptionPane.INFORMATION_MESSAGE);
					Logeado ventanaLogeado = new Logeado();
					@SuppressWarnings("unused")
					ControladorLogeado controladorLogeado = new ControladorLogeado(ventanaLogeado, nuevo);
					
					
				} else {
					
					JOptionPane.showMessageDialog(null,"Los datos introducidos no son correctos", "Información", JOptionPane.ERROR_MESSAGE);
					
				}
				
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			break;
			
		case "registrar":
			
			Registrar registrar = new Registrar();
			@SuppressWarnings("unused") Controlador_Registro controlador = new Controlador_Registro(registrar, this.entrada, this.salida);
			this.ventanaLogin.getFrame().dispose();

			break;
			
			
		default:
			break;
		}
		
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
