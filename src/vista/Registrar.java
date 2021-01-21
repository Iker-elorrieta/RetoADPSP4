package vista;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;

public class Registrar {

	private JFrame frame;
	private JTextField nombre;
	private JTextField contrasena;
	private JTextField repetirContrasena;
	private JButton botonAcceptar;
	private JButton botonVolver;
	
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
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.getContentPane().setLayout(null);
	
	}
	
	/**
	 * {@summary Método que carga las etiquetas de la ventana}
	 */
	
	public void cargarEtiquetas() {
		
		JLabel labelNombre = new JLabel("Nombre");
		labelNombre.setHorizontalAlignment(SwingConstants.CENTER);
		labelNombre.setBounds(200, 87, 269, 42);
		frame.getContentPane().add(labelNombre);
		
		JLabel labelContrasena = new JLabel("Contraseña");
		labelContrasena.setHorizontalAlignment(SwingConstants.CENTER);
		labelContrasena.setBounds(49, 212, 269, 22);
		frame.getContentPane().add(labelContrasena);
		
		JLabel labelRepetirContrasena = new JLabel("Repetir Contraseña");
		labelRepetirContrasena.setHorizontalAlignment(SwingConstants.CENTER);
		labelRepetirContrasena.setBounds(363, 212, 269, 22);
		frame.getContentPane().add(labelRepetirContrasena);
		
	}
	
	/**
	 *{@summary Método que carga los campos de texto de la ventana}
	 */
	
	public void cargarCamposTexto() {
		
		nombre = new JTextField();
		nombre.setColumns(10);
		nombre.setBounds(200, 144, 269, 42);
		frame.getContentPane().add(nombre);
		
		contrasena = new JTextField();
		contrasena.setColumns(10);
		contrasena.setBounds(49, 245, 269, 42);
		frame.getContentPane().add(contrasena);
		
		repetirContrasena = new JTextField();
		repetirContrasena.setColumns(10);
		repetirContrasena.setBounds(363, 245, 269, 42);
		frame.getContentPane().add(repetirContrasena);
		
	}
	
	/**
	 *{@summary Método que carga los botones de la ventana} 
	 */
	
	public void cargarBotones() {
		
		botonAcceptar = new JButton("Registrar");
		botonAcceptar.setBounds(422, 334, 168, 52);
		frame.getContentPane().add(botonAcceptar);
		
		botonVolver = new JButton("Volver");
		botonVolver.setBounds(107, 334, 168, 52);
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
	
}
