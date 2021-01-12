package controlador;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import modelo.Usuario;
import vista.Login;
import vista.Registrar;

public class Controlador_Login implements MouseListener {
	
	private Login frame;
	private ObjectOutputStream salida;
	private ObjectInputStream entrada;
	
	public Controlador_Login(Login frame, ObjectInputStream entrada, ObjectOutputStream salida) {
		
		this.frame = frame;
		this.entrada = entrada;
		this.salida = salida;
		iniciarcontrolador();		
		
	}
	
	public void iniciarcontrolador() {
		
		this.frame.getBotonIniciar().addMouseListener(this);
		this.frame.getBotonIniciar().setName("entrar");
		this.frame.getBotonRegistrar().addMouseListener(this);
		this.frame.getBotonRegistrar().setName("registrar");
	
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
			nuevo.setUsuario(frame.getNombre().getText());
			nuevo.setContrasena(frame.getContrasena().getText());
			try {
				salida.writeObject(1);
				salida.writeObject(nuevo);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			break;
			
		case "registrar":
			
			Registrar registrar = new Registrar();
			@SuppressWarnings("unused") Controlador_Registro controlador = new Controlador_Registro(registrar, this.entrada, this.salida);
			registrar.setVisible(true);
			this.frame.dispose();

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
