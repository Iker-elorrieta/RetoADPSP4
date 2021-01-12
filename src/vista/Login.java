package vista;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;

public class Login extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5803146381028577298L;
	private JPanel contentPane;
	private JTextField nombre;
	private JTextField contrasena;
	private JFrame frame;
	private JButton botonIniciar;
	private JButton botonRegistrar;
	private JButton botonRecuperar;
	
	public Login() {
		
		frame = new JFrame();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 720, 480);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel labelNombre = new JLabel("Nombre");
		labelNombre.setBounds(197, 126, 81, 22);
		labelNombre.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(labelNombre);
		
		nombre = new JTextField();
		nombre.setBounds(133, 159, 194, 40);
		nombre.setColumns(10);
		contentPane.add(nombre);
		
		contrasena = new JTextField();
		contrasena.setBounds(133, 236, 194, 40);
		contrasena.setColumns(10);
		contentPane.add(contrasena);
		
		JLabel labelContrasena = new JLabel("Contraseña");
		labelContrasena.setBounds(192, 204, 86, 22);
		labelContrasena.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(labelContrasena);
		
		botonIniciar = new JButton("Iniciar sesión");
		botonIniciar.setFocusPainted(false);
		botonIniciar.setBounds(133, 297, 194, 40);
		contentPane.add(botonIniciar);
		
		botonRegistrar = new JButton("Registrar usuario");
		botonRegistrar.setFocusPainted(false);
		botonRegistrar.setBounds(386, 204, 194, 40);
		contentPane.add(botonRegistrar);
		
		JLabel lblNewLabel = new JLabel("Bienvenido a Euskal Weather");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(200, 32, 254, 14);
		contentPane.add(lblNewLabel);
		
		botonRecuperar = new JButton("Recuperar contraseña");
		botonRecuperar.setFocusPainted(false);
		botonRecuperar.setBounds(386, 262, 194, 40);
		contentPane.add(botonRecuperar);
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

	public void setBotonAcceptar(JButton botonAcceptar) {
		this.botonIniciar = botonAcceptar;
	}

	public void setNombre(JTextField nombre) {
		this.nombre = nombre;
	}

	public void setContrasena(JTextField contrasena) {
		this.contrasena = contrasena;
	}

	public JButton getBotonIniciar() {
		return botonIniciar;
	}

	public void setBotonIniciar(JButton botonIniciar) {
		this.botonIniciar = botonIniciar;
	}

	public JButton getBotonRegistrar() {
		return botonRegistrar;
	}

	public void setBotonRegistrar(JButton botonRegistrar) {
		this.botonRegistrar = botonRegistrar;
	}

	public JButton getBotonRecuperar() {
		return botonRecuperar;
	}

	public void setBotonRecuperar(JButton botonRecuperar) {
		this.botonRecuperar = botonRecuperar;
	}
}
