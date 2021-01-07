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

public class Login extends JFrame {

	private JPanel contentPane;
	private JTextField nombre;
	private JTextField contrasena;
	private VentanaCliente frame;
	private JButton botonAcceptar;
	
	public Login(VentanaCliente frame) {
		this.frame = frame;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 507, 226);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel labelNombre = new JLabel("Nombre");
		labelNombre.setHorizontalAlignment(SwingConstants.CENTER);
		labelNombre.setBounds(199, 11, 81, 22);
		contentPane.add(labelNombre);
		
		nombre = new JTextField();
		nombre.setColumns(10);
		nombre.setBounds(185, 41, 116, 20);
		contentPane.add(nombre);
		
		contrasena = new JTextField();
		contrasena.setColumns(10);
		contrasena.setBounds(185, 104, 116, 20);
		contentPane.add(contrasena);
		
		JLabel labelContrasena = new JLabel("Contraseña");
		labelContrasena.setHorizontalAlignment(SwingConstants.CENTER);
		labelContrasena.setBounds(199, 72, 86, 22);
		contentPane.add(labelContrasena);
		
		botonAcceptar = new JButton("Acceptar");
		botonAcceptar.setBounds(199, 153, 89, 23);
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
	
	
}
