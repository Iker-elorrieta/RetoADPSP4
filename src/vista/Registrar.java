package vista;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;

public class Registrar extends JFrame {

	private JPanel contentPane;
	private JTextField nombre;
	private JTextField contrasena;
	private JTextField repetirContrasena;
	private JTextField email;
	private VentanaCliente frame;
	private JButton botonAcceptar;
	
	public Registrar(VentanaCliente frame) {
		this.frame = frame;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 334);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel labelNombre = new JLabel("Nombre");
		labelNombre.setHorizontalAlignment(SwingConstants.CENTER);
		labelNombre.setBounds(165, 11, 81, 22);
		contentPane.add(labelNombre);
		
		JLabel labelContrasena = new JLabel("Contraseña");
		labelContrasena.setHorizontalAlignment(SwingConstants.CENTER);
		labelContrasena.setBounds(165, 72, 86, 22);
		contentPane.add(labelContrasena);
		
		JLabel labelRepetirContrasena = new JLabel("Repetir Contraseña");
		labelRepetirContrasena.setHorizontalAlignment(SwingConstants.CENTER);
		labelRepetirContrasena.setBounds(141, 132, 139, 22);
		contentPane.add(labelRepetirContrasena);
		
		JLabel labelEmail = new JLabel("Email");
		labelEmail.setHorizontalAlignment(SwingConstants.CENTER);
		labelEmail.setBounds(165, 191, 81, 22);
		contentPane.add(labelEmail);
		
		nombre = new JTextField();
		nombre.setColumns(10);
		nombre.setBounds(151, 41, 116, 20);
		contentPane.add(nombre);
		
		contrasena = new JTextField();
		contrasena.setColumns(10);
		contrasena.setBounds(151, 104, 116, 20);
		contentPane.add(contrasena);
		
		repetirContrasena = new JTextField();
		repetirContrasena.setColumns(10);
		repetirContrasena.setBounds(151, 159, 116, 20);
		contentPane.add(repetirContrasena);
		
		email = new JTextField();
		email.setColumns(10);
		email.setBounds(152, 216, 115, 20);
		contentPane.add(email);
		
		botonAcceptar = new JButton("Acceptar");
		botonAcceptar.setBounds(162, 261, 89, 23);
		contentPane.add(botonAcceptar);
	}
	
	public JFrame getSuper()
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

	
}
