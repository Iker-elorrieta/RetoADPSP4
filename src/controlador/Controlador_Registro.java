package controlador;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.swing.JOptionPane;

import modelo.Usuario;
import vista.Login;
import vista.Registrar;

public class Controlador_Registro implements MouseListener {
	
	private Registrar ventanaRegistro;
	private ObjectInputStream entrada;
	private ObjectOutputStream salida;
	private boolean prueba = false;
	
	/**
	 * {@summary Constructor de la clase que da inicio al controlador }
	 * @param registrar
	 * @param entrada
	 * @param salida
	 */
	
	public Controlador_Registro(Registrar registrar, ObjectInputStream entrada, ObjectOutputStream salida) {
		
		this.ventanaRegistro = registrar;
		this.entrada = entrada;
		this.salida = salida;
		iniciarControlador();
		
	}
	
	/**
	 * {@summary Método que hace visible el frame de la ventana, añade listeners a los componentes y les da nombre}
	 */
	
	public boolean iniciarControlador() {
		
		this.ventanaRegistro.getFrame().setVisible(true);
		this.ventanaRegistro.getBotonAcceptar().addMouseListener(this);
		this.ventanaRegistro.getBotonAcceptar().setName("registrar");
		this.ventanaRegistro.getBotonVolver().addMouseListener(this);
		this.ventanaRegistro.getBotonVolver().setName("volver");
		
		return prueba;
	}
	
	/**
	 * {@summary Método que contiene las acciones a realizar al pulsar un botón}
	 */
	
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
		switch (e.getComponent().getName()) {
		
		case "registrar":
			
//			Acción de registrar. 
//			Crea un objeto usuario con los datos de los campos de texto
			
			try {
				Usuario usuario = new Usuario();
				usuario.setUsuario(this.ventanaRegistro.getNombre().getText());
				usuario.setContrasena(this.ventanaRegistro.getContrasena().getText());
				usuario.setEMail(this.ventanaRegistro.getEmail().getText());
				
//				Envía el usuario al servidor con la orden de registrar
				
				this.salida.writeObject(2);
				this.salida.writeObject(usuario);
				
//				Recibe la respuesta del servidor.
				
				String respuesta = this.entrada.readObject().toString();
				if (respuesta.equals("bien")){
					
//					Si la respuesta es bien significa que el registro se ha realizado correctamente. Se informa de ello al usuario
					
					JOptionPane.showMessageDialog(null,"Registro realizado", "Información", JOptionPane.INFORMATION_MESSAGE);
					prueba = true;
					
				} else if (respuesta.equals("mal")) {
					
//					Si la respuesta es mal significa que el registro no se ha realizado. Se informa de ello al usuario
					
					JOptionPane.showMessageDialog(null,"Ocurrió algún error. Registro no realizado", "Información", JOptionPane.ERROR_MESSAGE);
					prueba = true;
				}
				
						
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			break;
			
		case "volver":
			
//			Acción de volver a la ventana anterior. Da paso a la ventana de Login y cierra la actual
			
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
}