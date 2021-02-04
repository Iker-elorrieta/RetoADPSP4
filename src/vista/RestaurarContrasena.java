package vista;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;

public class RestaurarContrasena {
	
	private JFrame frame;
	private JTextField textFieldUsuario;
	private JLabel lblPregunta;
	private JButton btnEnviarRespuesta;
	private JButton btnEnviarUsuario;
	private JButton btnVolver;
	private JLabel lblResponda;
	private JTextField textFieldRespuesta;
	private JLabel lblIntroduzcaUsuario;
	private JButton btnEnviarContrasena;
	private JTextField textFieldContrasena;
	private JTextField textFieldRepetirContrasena;
	private JLabel lblContrasena;
	private JLabel lblRepetirContrasena;
	private boolean comprobacionPruebas = false;
	
	public RestaurarContrasena() {
		
		cargarVentana();
		cargarEtiquetas();
		cargarBotones();
		cargarCamposTexto();
		comprobacionPruebas = true;
	}
	
	public void cargarVentana() {
		
		frame = new JFrame();
		frame.setResizable(false);
		frame.setBounds(100, 100, 700, 525);
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.getContentPane().setLayout(null);	
		
	}
	
	public void cargarEtiquetas() {
		
		lblIntroduzcaUsuario = new JLabel("Introduzca su nombre de usuario");
		lblIntroduzcaUsuario.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblIntroduzcaUsuario.setHorizontalAlignment(SwingConstants.CENTER);
		lblIntroduzcaUsuario.setBounds(0, 164, 694, 29);
		frame.getContentPane().add(lblIntroduzcaUsuario);
		
		lblPregunta = new JLabel("");
		lblPregunta.setVisible(false);
		lblPregunta.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblPregunta.setHorizontalAlignment(SwingConstants.CENTER);
		lblPregunta.setBounds(0, 159, 694, 42);
		frame.getContentPane().add(lblPregunta);
		
		lblResponda = new JLabel("Responda a la pregunta de seguridad");
		lblResponda.setVisible(false);
		lblResponda.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblResponda.setHorizontalAlignment(SwingConstants.CENTER);
		lblResponda.setBounds(0, 23, 694, 35);
		frame.getContentPane().add(lblResponda);
		
		lblContrasena = new JLabel("Escriba la nueva contraseña");
		lblContrasena.setHorizontalAlignment(SwingConstants.CENTER);
		lblContrasena.setVisible(false);
		lblContrasena.setBounds(85, 211, 236, 14);
		frame.getContentPane().add(lblContrasena);
		
		lblRepetirContrasena = new JLabel("Repita la nueva contraseña");
		lblRepetirContrasena.setVisible(false);
		lblRepetirContrasena.setHorizontalAlignment(SwingConstants.CENTER);
		lblRepetirContrasena.setBounds(355, 212, 246, 14);
		frame.getContentPane().add(lblRepetirContrasena);
		
		lblContrasena = new JLabel("Escriba la nueva contraseña");
		lblContrasena.setHorizontalAlignment(SwingConstants.CENTER);
		lblContrasena.setVisible(false);
		lblContrasena.setBounds(85, 211, 236, 14);
		frame.getContentPane().add(lblContrasena);
		
		lblRepetirContrasena = new JLabel("Repita la nueva contraseña");
		lblRepetirContrasena.setVisible(false);
		lblRepetirContrasena.setHorizontalAlignment(SwingConstants.CENTER);
		lblRepetirContrasena.setBounds(355, 212, 246, 14);
		frame.getContentPane().add(lblRepetirContrasena);
		
	}
	
	public void cargarBotones() {
		
		btnEnviarRespuesta = new JButton("Enviar");
		btnEnviarRespuesta.setVisible(false);
		btnEnviarRespuesta.setFocusPainted(false);
		btnEnviarRespuesta.setEnabled(false);
		btnEnviarRespuesta.setBounds(306, 320, 89, 23);
		frame.getContentPane().add(btnEnviarRespuesta);
			
		btnEnviarUsuario = new JButton("Enviar");
		btnEnviarUsuario.setFocusPainted(false);
		btnEnviarUsuario.setBounds(306, 320, 89, 23);
		frame.getContentPane().add(btnEnviarUsuario);
		
		btnVolver = new JButton("Volver");
		btnVolver.setBounds(10, 430, 122, 35);
		frame.getContentPane().add(btnVolver);
		
		btnEnviarContrasena = new JButton("Enviar");
		btnEnviarContrasena.setEnabled(false);
		btnEnviarContrasena.setVisible(false);
		btnEnviarContrasena.setBounds(306, 320, 89, 23);
		frame.getContentPane().add(btnEnviarContrasena);
		
	}
	
	public void cargarCamposTexto() {
		
		textFieldUsuario = new JTextField();
		textFieldUsuario.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textFieldUsuario.setBounds(212, 236, 267, 42);
		frame.getContentPane().add(textFieldUsuario);
		textFieldUsuario.setColumns(10);
			
		textFieldRespuesta = new JTextField();
		textFieldRespuesta.setVisible(false);
		textFieldRespuesta.setEnabled(false);
		textFieldRespuesta.setBounds(212, 235, 267, 43);
		frame.getContentPane().add(textFieldRespuesta);
		textFieldRespuesta.setColumns(10);
	
		textFieldContrasena = new JTextField();
		textFieldContrasena.setEnabled(false);
		textFieldContrasena.setVisible(false);
		textFieldContrasena.setBounds(85, 236, 236, 42);
		frame.getContentPane().add(textFieldContrasena);
		textFieldContrasena.setColumns(10);
		
		textFieldRepetirContrasena = new JTextField();
		textFieldRepetirContrasena.setEnabled(false);
		textFieldRepetirContrasena.setVisible(false);
		textFieldRepetirContrasena.setBounds(355, 236, 246, 42);
		frame.getContentPane().add(textFieldRepetirContrasena);
		textFieldRepetirContrasena.setColumns(10);
			
	}

	public JFrame getFrame() {
		return frame;
	}

	public JTextField getTextFieldUsuario() {
		return textFieldUsuario;
	}

	public JLabel getLblPregunta() {
		return lblPregunta;
	}

	public JButton getBtnEnviarRespuesta() {
		return btnEnviarRespuesta;
	}

	public JButton getBtnEnviarUsuario() {
		return btnEnviarUsuario;
	}

	public JButton getBtnVolver() {
		return btnVolver;
	}

	public JLabel getLblIntroduzcaUsuario() {
		return lblIntroduzcaUsuario;
	}

	public JLabel getLblResponda() {
		return lblResponda;
	}

	public JTextField getTextFieldRespuesta() {
		return textFieldRespuesta;
	}

	public JButton getBtnEnviarContrasena() {
		return btnEnviarContrasena;
	}

	public JTextField getTextFieldContrasena() {
		return textFieldContrasena;
	}

	public JTextField getTextFieldRepetirContrasena() {
		return textFieldRepetirContrasena;
	}

	public JLabel getLblContrasena() {
		return lblContrasena;
	}

	public JLabel getLblRepetirContrasena() {
		return lblRepetirContrasena;
	}
	
	public boolean metodoPrueba()
	{
		return comprobacionPruebas;
	}
	
}
