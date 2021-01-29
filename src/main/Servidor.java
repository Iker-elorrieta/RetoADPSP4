package main;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;


import Server.ServidorPeticiones;


import javax.swing.JLabel;


/**
 * Ventana para manejo de conexiones y visualisación de estas.
 * @author Grupo 4
 *
 */
public class Servidor extends JFrame implements ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	static JTextField mensaje = new JTextField();

	private JScrollPane scrollpane1;
	static JTextArea textarea1;
	JButton botonEnviar = new JButton("Enviar");
	JButton botonSalir = new JButton("Salir");
	ServidorPeticiones s = null;
	JLabel labelHora= null;
	boolean prueba = false; 

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Servidor frame = new Servidor();
					frame.setBounds(0, 0, 500, 450);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Servidor() {
		super(" VENTANA SERVIDOR ");
		setTitle("Servidor ");
		getContentPane().setLayout(null);
		mensaje.setBounds(10, 51, 204, 30);
		getContentPane().add(mensaje);
		mensaje.setEditable(false);
	
		scrollpane1 = new JScrollPane();
		scrollpane1.setBounds(224, 51, 400, 300);
		getContentPane().add(scrollpane1);
		botonSalir.setBounds(206, 370, 100, 30);
		getContentPane().add(botonSalir);
		botonEnviar.addActionListener(this);
		botonSalir.addActionListener(this);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		textarea1 = new JTextArea();
		textarea1.setBounds(226, 51, 398, 298);
		getContentPane().add(textarea1);
		textarea1.setText("Esperando conexiones... \n");
		labelHora = new JLabel("");
		labelHora.setBounds(578, 11, 46, 14);
		getContentPane().add(labelHora);
		
		textarea1.setEditable(false);				
		s = new ServidorPeticiones(textarea1,mensaje,labelHora);	
		s.start();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == botonSalir) { // SE PULSA BOTON SALIR
			s.desconectar();
			prueba = true;
		}
	}

	public JButton getBotonSalir() {
		return botonSalir;
	}
	
	public boolean prueba()
	{
		return prueba;
	}
}
