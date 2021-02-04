package vista;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Font;

public class Registrar {

	private JFrame frame;
	private JTextField nombre;
	private JPasswordField contrasena;
	private JPasswordField repetirContrasena;
	private JButton botonAcceptar;
	private JButton botonVolver;
	private JTextField textFieldPregunta;
	private JTextField textFieldRespuesta;
	
	/**
	 * {@summary Constructor de la clase. Carga los distintos componentes de la ventana}
	 */
	
	public Registrar() {
		
		cargarVentana();
		cargarEtiquetas();
		cargarCamposTexto();
		cargarBotones();
		
	}
	
	/**
	 * {@summary Método que carga el frame de la ventana}
	 */
	
	public void cargarVentana() {
		
		frame = new JFrame();
		frame.setResizable(false);
		frame.setBounds(100, 100, 700, 525);
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.getContentPane().setLayout(null);
	
	}
	
	/**
	 * {@summary Método que carga las etiquetas de la ventana}
	 */
	
	public void cargarEtiquetas() {
		
		JLabel labelNombre = new JLabel("Nombre");
		labelNombre.setHorizontalAlignment(SwingConstants.CENTER);
		labelNombre.setBounds(203, 28, 269, 42);
		frame.getContentPane().add(labelNombre);
		
		JLabel labelContrasena = new JLabel("Contraseña");
		labelContrasena.setHorizontalAlignment(SwingConstants.CENTER);
		labelContrasena.setBounds(50, 148, 269, 22);
		frame.getContentPane().add(labelContrasena);
		
		JLabel labelRepetirContrasena = new JLabel("Repetir Contraseña");
		labelRepetirContrasena.setHorizontalAlignment(SwingConstants.CENTER);
		labelRepetirContrasena.setBounds(364, 148, 269, 22);
		frame.getContentPane().add(labelRepetirContrasena);
		
		JLabel lblPregunta = new JLabel("Pregunta de seguridad");
		lblPregunta.setHorizontalAlignment(SwingConstants.CENTER);
		lblPregunta.setBounds(50, 244, 269, 14);
		frame.getContentPane().add(lblPregunta);
		
		JLabel lblRespuesta = new JLabel("Respuesta ");
		lblRespuesta.setHorizontalAlignment(SwingConstants.CENTER);
		lblRespuesta.setBounds(364, 244, 269, 14);
		frame.getContentPane().add(lblRespuesta);
		
	}
	
	/**
	 *{@summary Método que carga los campos de texto de la ventana}
	 */
	
	public void cargarCamposTexto() {
		
		nombre = new JTextField();
		nombre.setFont(new Font("Tahoma", Font.PLAIN, 14));
		nombre.setColumns(10);
		nombre.setBounds(203, 85, 269, 42);
		frame.getContentPane().add(nombre);
		
		contrasena = new JPasswordField();
		contrasena.setFont(new Font("Tahoma", Font.PLAIN, 14));
		contrasena.setColumns(10);
		contrasena.setBounds(50, 181, 269, 42);
		frame.getContentPane().add(contrasena);
		
		repetirContrasena = new JPasswordField();
		repetirContrasena.setFont(new Font("Tahoma", Font.PLAIN, 14));
		repetirContrasena.setColumns(10);
		repetirContrasena.setBounds(364, 181, 269, 42);
		frame.getContentPane().add(repetirContrasena);
		
		textFieldPregunta = new JTextField();
		textFieldPregunta.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textFieldPregunta.setBounds(50, 277, 269, 42);
		frame.getContentPane().add(textFieldPregunta);
		textFieldPregunta.setColumns(10);
			
		textFieldRespuesta = new JTextField();
		textFieldRespuesta.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textFieldRespuesta.setBounds(364, 274, 269, 45);
		frame.getContentPane().add(textFieldRespuesta);
		textFieldRespuesta.setColumns(10);
		
	}
	
	/**
	 *{@summary Método que carga los botones de la ventana} 
	 */
	
	public void cargarBotones() {
		
		botonAcceptar = new JButton("Registrar");
		botonAcceptar.setBounds(422, 376, 168, 52);
		frame.getContentPane().add(botonAcceptar);
		
		botonVolver = new JButton("Volver");
		botonVolver.setBounds(107, 376, 168, 52);
		frame.getContentPane().add(botonVolver);
		
	}
	
	public JFrame getFrame() {
		return frame;
	}

	public JTextField getNombre() {
		return nombre;
	}

	public JTextField getContrasena() {
		return contrasena;
	}

	public JTextField getRepetirContrasena() {
		return repetirContrasena;
	}
	
	public JButton getBotonAcceptar() {
		return botonAcceptar;
	}

	public JButton getBotonVolver() {
		return botonVolver;
	}

	public JTextField getTextFieldPregunta() {
		return textFieldPregunta;
	}

	public JTextField getTextFieldRespuesta() {
		return textFieldRespuesta;
	}
}
