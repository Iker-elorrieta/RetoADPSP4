package controlador;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.swing.JOptionPane;

import comunes.CrearHash;
import modelo.Usuario;
import vista.Login;
import vista.RestaurarContrasena;

public class ControladorRestaurarContrasena implements MouseListener {
	
	private RestaurarContrasena ventana;
	private ObjectInputStream entrada;
	private ObjectOutputStream salida;
	Usuario usuario;
	Login login;
	Controlador_Login controladorLogin;
	private boolean prueba = false;
	private boolean probando = false;
	public ControladorRestaurarContrasena(RestaurarContrasena ventana, ObjectInputStream entrada, ObjectOutputStream salida) {
		
		this.ventana = ventana;
		this.entrada = entrada;
		this.salida = salida;
		iniciarControlador();
		
	}
	
	public void iniciarControlador() {
		
		this.ventana.getFrame().setVisible(true);
		this.ventana.getBtnEnviarUsuario().addMouseListener(this);
		this.ventana.getBtnEnviarUsuario().setName("enviarUsuario");
		this.ventana.getBtnVolver().addMouseListener(this);
		this.ventana.getBtnVolver().setName("volver");
		this.ventana.getBtnEnviarRespuesta().addMouseListener(this);
		this.ventana.getBtnEnviarRespuesta().setName("enviarRespuesta");
		this.ventana.getBtnEnviarContrasena().addMouseListener(this);
		this.ventana.getBtnEnviarContrasena().setName("enviarContrasena");
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
		switch (e.getComponent().getName()) {
		case "enviarUsuario":
			
			if (!this.ventana.getTextFieldUsuario().getText().isBlank()) {
				try {
					salida.writeObject(3);
					salida.writeObject(this.ventana.getTextFieldUsuario().getText());
					usuario = (Usuario) entrada.readObject();

					if (usuario != null) {

						this.ventana.getBtnEnviarUsuario().setVisible(false);
						this.ventana.getBtnEnviarUsuario().setEnabled(false);
						this.ventana.getTextFieldUsuario().setVisible(false);
						this.ventana.getTextFieldUsuario().setEnabled(false);
						this.ventana.getLblIntroduzcaUsuario().setVisible(false);

						this.ventana.getLblPregunta().setVisible(true);
						this.ventana.getLblResponda().setVisible(true);
						this.ventana.getBtnEnviarRespuesta().setVisible(true);
						this.ventana.getBtnEnviarRespuesta().setEnabled(true);
						this.ventana.getTextFieldRespuesta().setVisible(true);
						this.ventana.getTextFieldRespuesta().setEnabled(true);

						this.ventana.getLblPregunta().setText(usuario.getPregunta());
						prueba = true;
					} else {

						JOptionPane.showMessageDialog(null, "El usuario introducido no existe", "Información",
								JOptionPane.ERROR_MESSAGE);
					}

				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} 
				
			} else {
				
				JOptionPane.showMessageDialog(null, "Debe introducir un nombre de usuario para continuar", "Información", JOptionPane.ERROR_MESSAGE);
				
			}
			
			break;
			
		case "enviarRespuesta":
			
			if (!this.ventana.getTextFieldRespuesta().getText().isBlank()) {
				
				if (CrearHash.crearHash(this.ventana.getTextFieldRespuesta().getText()).equals(usuario.getRespuesta())) {
					
					this.ventana.getLblPregunta().setVisible(false);
					this.ventana.getLblResponda().setVisible(false);
					this.ventana.getBtnEnviarRespuesta().setVisible(false);
					this.ventana.getBtnEnviarRespuesta().setEnabled(false);
					this.ventana.getTextFieldRespuesta().setVisible(false);
					this.ventana.getTextFieldRespuesta().setEnabled(false);
					this.ventana.getLblContrasena().setVisible(true);
					this.ventana.getLblRepetirContrasena().setVisible(true);
					this.ventana.getTextFieldContrasena().setVisible(true);
					this.ventana.getTextFieldContrasena().setEnabled(true);
					this.ventana.getTextFieldRepetirContrasena().setVisible(true);
					this.ventana.getTextFieldRepetirContrasena().setEnabled(true);
					this.ventana.getBtnEnviarContrasena().setVisible(true);
					this.ventana.getBtnEnviarContrasena().setEnabled(true);
					prueba = true;
				} else {
					
					JOptionPane.showMessageDialog(null, "La respuesta introducida no es correcta", "Información", JOptionPane.ERROR_MESSAGE);
				}
				
			} else {
				
				JOptionPane.showMessageDialog(null, "Debe introducir una respuesta para continuar", "Información", JOptionPane.ERROR_MESSAGE);
				
			}
			break;
			
		case "volver":
			
			login = new Login();
			controladorLogin = new Controlador_Login(login, entrada, salida);
			this.ventana.getFrame().dispose();
			prueba = true;
			break;
			
		case "enviarContrasena":
			
			if ((!this.ventana.getTextFieldContrasena().getText().isBlank() && !this.ventana.getTextFieldRepetirContrasena().getText().isBlank()) &&
				this.ventana.getTextFieldContrasena().getText().equals(this.ventana.getTextFieldRepetirContrasena().getText()) &&
				this.ventana.getTextFieldContrasena().getText().length() <= 30) {
				
				try {
					salida.writeObject(4);
					salida.writeObject(usuario);
					salida.writeObject(this.ventana.getTextFieldContrasena().getText());
					if ((Boolean) entrada.readObject()) {
						if(!probando)
							JOptionPane.showMessageDialog(null, "Contraseña actualizada", "Información", JOptionPane.INFORMATION_MESSAGE);
						login = new Login();
						controladorLogin = new Controlador_Login(login, entrada, salida);
						prueba = true;
					}
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			} else {
				
				if (this.ventana.getTextFieldContrasena().getText().isBlank() || this.ventana.getTextFieldRepetirContrasena().getText().isBlank()) {
					
					JOptionPane.showMessageDialog(null, "Debe rellenar todos los campos para continuar", "Información", JOptionPane.ERROR_MESSAGE);
				}
				
				if (!this.ventana.getTextFieldContrasena().getText().equals(this.ventana.getTextFieldRepetirContrasena().getText())) {
					
					JOptionPane.showMessageDialog(null, "Las contraseñas no coinciden", "Información", JOptionPane.ERROR_MESSAGE);
				}
				
				if (this.ventana.getTextFieldContrasena().getText().length() > 30) {
					
					JOptionPane.showMessageDialog(null, "La contraseña no puede tener más de 30 caracteres", "Información", JOptionPane.ERROR_MESSAGE);
					
				}
			}
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
	
	public boolean metodoPrueba()
	{
		return prueba;
	}
	
	public void Probando()
	{
		probando = true;
	}
	
}
