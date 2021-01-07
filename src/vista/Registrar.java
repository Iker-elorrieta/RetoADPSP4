package vista;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JTextField;

public class Registrar extends JFrame {

	private JPanel contentPane;
	private JTextField nombre;
	private JTextField contrasena;
	private JTextField repetirContrasena;
	private JTextField email;
	
	public Registrar() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
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
	}

}
