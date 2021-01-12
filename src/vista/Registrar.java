package vista;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;

public class Registrar extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2409533816211087023L;
	private JFrame frame;
	private JTextField nombre;
	private JTextField contrasena;
	private JTextField repetirContrasena;
	private JTextField email;
	private JButton botonAcceptar;
	private JButton botonVolver;
	
	public Registrar() {
		
		cargarVentana();
		cargarEtiquetas();
		cargarCamposTexto();
		cargarBotones();
		
	}
	
	public void cargarVentana() {
		
		frame = new JFrame();
		frame.setResizable(false);
		frame.setBounds(100, 100, 700, 525);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.getContentPane().setLayout(null);
	
	}
	
	public void cargarEtiquetas() {
		
		JLabel labelNombre = new JLabel("Nombre");
		labelNombre.setHorizontalAlignment(SwingConstants.CENTER);
		labelNombre.setBounds(49, 91, 269, 42);
		frame.getContentPane().add(labelNombre);
		
		JLabel labelContrasena = new JLabel("Contraseña");
		labelContrasena.setHorizontalAlignment(SwingConstants.CENTER);
		labelContrasena.setBounds(49, 212, 269, 22);
		frame.getContentPane().add(labelContrasena);
		
		JLabel labelRepetirContrasena = new JLabel("Repetir Contraseña");
		labelRepetirContrasena.setHorizontalAlignment(SwingConstants.CENTER);
		labelRepetirContrasena.setBounds(363, 212, 269, 22);
		frame.getContentPane().add(labelRepetirContrasena);
		
		JLabel labelEmail = new JLabel("Email");
		labelEmail.setHorizontalAlignment(SwingConstants.CENTER);
		labelEmail.setBounds(363, 91, 269, 42);
		frame.getContentPane().add(labelEmail);
		
	}
	
	public void cargarCamposTexto() {
		
		nombre = new JTextField();
		nombre.setColumns(10);
		nombre.setBounds(49, 148, 269, 42);
		frame.getContentPane().add(nombre);
		
		contrasena = new JTextField();
		contrasena.setColumns(10);
		contrasena.setBounds(49, 245, 269, 42);
		frame.getContentPane().add(contrasena);
		
		repetirContrasena = new JTextField();
		repetirContrasena.setColumns(10);
		repetirContrasena.setBounds(363, 245, 269, 42);
		frame.getContentPane().add(repetirContrasena);
		
		email = new JTextField();
		email.setColumns(10);
		email.setBounds(363, 148, 269, 42);
		frame.getContentPane().add(email);
		
	}
	
	public void cargarBotones() {
		
		botonAcceptar = new JButton("Registrar");
		botonAcceptar.setBounds(422, 334, 168, 52);
		frame.getContentPane().add(botonAcceptar);
		
		botonVolver = new JButton("Volver");
		botonVolver.setBounds(107, 334, 168, 52);
		frame.getContentPane().add(botonVolver);
		
	}
	
	
	public JFrame getFrame()
	{
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

	public JTextField getEmail() {
		return email;
	}
	
	public JButton getBotonAcceptar() {
		return botonAcceptar;
	}

	public void setBotonAcceptar(JButton botonAcceptar) {
		this.botonAcceptar = botonAcceptar;
	}

	public void setNombre(JTextField nombre) {
		this.nombre = nombre;
	}

	public void setContrasena(JTextField contrasena) {
		this.contrasena = contrasena;
	}

	public void setRepetirContrasena(JTextField repetirContrasena) {
		this.repetirContrasena = repetirContrasena;
	}

	public void setEmail(JTextField email) {
		this.email = email;
	}

	public JButton getBotonVolver() {
		return botonVolver;
	}

	public void setBotonVolver(JButton botonVolver) {
		this.botonVolver = botonVolver;
	}
}
