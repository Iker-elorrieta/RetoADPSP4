package Server;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JLabel;


public class VentanaServidor extends JFrame implements ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	static JTextField mensaje = new JTextField();

	private JScrollPane scrollpane1;
	static JTextArea textarea1;
	JButton botonEnviar = new JButton("Enviar");
	JButton botonSalir = new JButton("Salir");
	Servidor s = null;
	JLabel labelHora= null;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaServidor frame = new VentanaServidor();
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
	public VentanaServidor() {
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
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		textarea1 = new JTextArea();
		textarea1.setBounds(226, 51, 398, 298);
		getContentPane().add(textarea1);
		textarea1.setText("Esperando conexiones... \n");
		labelHora = new JLabel("");
		labelHora.setBounds(578, 11, 46, 14);
		getContentPane().add(labelHora);
		
		
				textarea1.setEditable(false);
				
						s = new Servidor(textarea1,mensaje,labelHora);
						
						
		s.start();
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == botonSalir) { // SE PULSA BOTON SALIR
			s.desconectar();
		}
	}
}
