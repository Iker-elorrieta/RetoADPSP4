package vista;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;

public class Login {

	private JFrame frame;
	private JTextField nombre;
	private JTextField contrasena;
	private JButton botonIniciar;
	private JButton botonRegistrar;
	private JButton botonRecuperar;
	
	/**
	 * {@summary Constructor de la clase. Carga los distintos componentes de la ventana} 
	 */
	
	public Login() {
		
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
		frame.setTitle("Euskalmet");
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
		labelNombre.setBounds(197, 126, 81, 22);
		labelNombre.setHorizontalAlignment(SwingConstants.CENTER);
		frame.getContentPane().add(labelNombre);
		
		JLabel labelContrasena = new JLabel("Contraseña");
		labelContrasena.setBounds(192, 204, 86, 22);
		labelContrasena.setHorizontalAlignment(SwingConstants.CENTER);
		frame.getContentPane().add(labelContrasena);
		
		JLabel labelBienvenida = new JLabel("Bienvenido a Euskal Weather");
		labelBienvenida.setHorizontalAlignment(SwingConstants.CENTER);
		labelBienvenida.setBounds(200, 32, 254, 14);
		frame.getContentPane().add(labelBienvenida);
	
	}
	
	/**
	 * {@summary Método que carga los campos de texto de la ventana}
	 */
	
	public void cargarCamposTexto() {
		
		nombre = new JTextField();
		nombre.setBounds(133, 159, 194, 40);
		nombre.setColumns(10);
		frame.getContentPane().add(nombre);
		
		contrasena = new JTextField();
		contrasena.setBounds(133, 236, 194, 40);
		contrasena.setColumns(10);
		frame.getContentPane().add(contrasena);
		
	}
	
	/**
	 *{@summary Método que carga los botones de la ventana}
	 */
	
	public void cargarBotones() {
		
		botonIniciar = new JButton("Iniciar sesión");
		botonIniciar.setFocusPainted(false);
		botonIniciar.setBounds(133, 297, 194, 40);
		frame.getContentPane().add(botonIniciar);
		
		botonRegistrar = new JButton("Registrar usuario");
		botonRegistrar.setFocusPainted(false);
		botonRegistrar.setBounds(386, 204, 194, 40);
		frame.getContentPane().add(botonRegistrar);
		
		botonRecuperar = new JButton("Recuperar contraseña");
		botonRecuperar.setFocusPainted(false);
		botonRecuperar.setBounds(386, 262, 194, 40);
		frame.getContentPane().add(botonRecuperar);
			
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

	public JButton getBotonIniciar() {
		return botonIniciar;
	}

	public JButton getBotonRegistrar() {
		return botonRegistrar;
	}

	public JButton getBotonRecuperar() {
		return botonRecuperar;
	}

}
