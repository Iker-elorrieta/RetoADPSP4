package controlador;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
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
	private String tipo = "normal";

	/**
	 * {@summary Constructor de la clase que da inicio al controlador }
	 * 
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
	 * {@summary Método que hace visible el frame de la ventana, añade listeners a
	 * los componentes y les da nombre}
	 */

	public boolean iniciarControlador() {

		this.ventanaRegistro.getFrame().setVisible(true);
		this.ventanaRegistro.getBotonAcceptar().addMouseListener(this);
		this.ventanaRegistro.getBotonAcceptar().setName("registrar");
		this.ventanaRegistro.getBotonVolver().addMouseListener(this);
		this.ventanaRegistro.getBotonVolver().setName("volver");
		this.ventanaRegistro.getFrame().addWindowListener(new WindowAdapter() {

			@Override
			public void windowClosing(WindowEvent e) {
				try {
					salida.writeObject(999);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}

		});
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
//			Comprobación de que los campos de texto continenen valores correctos antes de realizar el registro

			if (!this.ventanaRegistro.getNombre().getText().isBlank()
					&& !this.ventanaRegistro.getContrasena().getText().isBlank()
					&& !this.ventanaRegistro.getContrasena().getText().isBlank()
					&& !this.ventanaRegistro.getRepetirContrasena().getText().isBlank()
					&& !this.ventanaRegistro.getTextFieldPregunta().getText().isBlank()
					&& !this.ventanaRegistro.getTextFieldRespuesta().getText().isBlank()
					&& this.ventanaRegistro.getNombre().getText().length() <= 20
					&& this.ventanaRegistro.getContrasena().getText().length() <= 30
					&& this.ventanaRegistro.getTextFieldPregunta().getText().length() <= 50
					&& this.ventanaRegistro.getTextFieldRespuesta().getText().length() <= 50 && this.ventanaRegistro
							.getContrasena().getText().equals(this.ventanaRegistro.getRepetirContrasena().getText())) {

//				Si los campos de texto contienen valores correctos se crea un objeto usuario con ellos

				try {
					Usuario usuario = new Usuario();
					usuario.setNombre(this.ventanaRegistro.getNombre().getText());
					usuario.setContrasena(this.ventanaRegistro.getContrasena().getText());
					usuario.setPregunta(this.ventanaRegistro.getTextFieldPregunta().getText());
					usuario.setRespuesta(this.ventanaRegistro.getTextFieldRespuesta().getText());

					// Envía el usuario al servidor con la orden de registrar

					this.salida.writeObject(2);
					this.salida.writeObject(usuario);

					// Recibe la respuesta del servidor.

					String respuesta = this.entrada.readObject().toString();
					if (respuesta.equals("bien")) {

						// Si la respuesta es bien significa que el registro se ha realizado
						// correctamente. Se informa de ello al usuario
						if (tipo.equals("normal"))
							JOptionPane.showMessageDialog(null, "Registro realizado", "Información",
									JOptionPane.INFORMATION_MESSAGE);
						prueba = true;

					} else if (respuesta.equals("mal")) {

						// Si la respuesta es mal significa que el registro no se ha realizado. Se
						// informa de ello al usuario
						if (tipo.equals("normal"))
							JOptionPane.showMessageDialog(null, "Ocurrió algún error. Registro no realizado",
									"Información", JOptionPane.ERROR_MESSAGE);
						prueba = true;
					}

				} catch (Exception e1) {
					e1.printStackTrace();
				}
			} else {

				if (this.ventanaRegistro.getNombre().getText().isBlank()
						|| this.ventanaRegistro.getContrasena().getText().isBlank()
						|| this.ventanaRegistro.getContrasena().getText().isBlank()
						|| this.ventanaRegistro.getRepetirContrasena().getText().isBlank()
						|| this.ventanaRegistro.getTextFieldPregunta().getText().isBlank()
						|| this.ventanaRegistro.getTextFieldRespuesta().getText().isBlank()) {
					if (tipo.equals("normal"))
						JOptionPane.showMessageDialog(null,
								"Debe rellenar todos los campos antes de realizar el registro", "Información",
								JOptionPane.ERROR_MESSAGE);

				}

				if (this.ventanaRegistro.getNombre().getText().length() > 20) {
					if (tipo.equals("normal"))
						JOptionPane.showMessageDialog(null, "El nombre no puede tener más de 20 caracteres",
								"Información", JOptionPane.ERROR_MESSAGE);

				}

				if (this.ventanaRegistro.getContrasena().getText().length() > 30) {

					if (tipo.equals("normal"))
						JOptionPane.showMessageDialog(null, "La contraseña no puede tener más de 30 caracteres",
								"Información", JOptionPane.ERROR_MESSAGE);

				}

				if (this.ventanaRegistro.getTextFieldPregunta().getText().length() > 50) {

					if (tipo.equals("normal"))
						JOptionPane.showMessageDialog(null, "La pregunta no puede tener más de 50 caracteres",
								"Información", JOptionPane.ERROR_MESSAGE);

				}

				if (this.ventanaRegistro.getTextFieldRespuesta().getText().length() > 50) {

					if (tipo.equals("normal"))
						JOptionPane.showMessageDialog(null, "La respuesta no puede tener más de 50 caracteres",
								"Información", JOptionPane.ERROR_MESSAGE);

				}

				if (!this.ventanaRegistro.getContrasena().getText()
						.equals(this.ventanaRegistro.getRepetirContrasena().getText())) {
					if (tipo.equals("normal"))
						JOptionPane.showMessageDialog(null, "Las contraseñas no coinciden", "Información",
								JOptionPane.ERROR_MESSAGE);

				}

			}

			break;

		case "volver":

//			Acción de volver a la ventana anterior. Da paso a la ventana de Login y cierra la actual

			Login ventanaLogin = new Login();
			@SuppressWarnings("unused")
			Controlador_Login controladorLogin = new Controlador_Login(ventanaLogin, this.entrada, this.salida);
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

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
}