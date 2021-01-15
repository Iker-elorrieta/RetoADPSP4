package vista;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import java.awt.Color;
import java.awt.SystemColor;

public class Logeado {
	
	private JFrame frame;
	private JTextArea labelHola;
	private JLabel labelCorreo;
	
	/**
	 *{@summary Constructor de la clase. Carga los distintos componentes de la ventana} 
	 */
	
	public Logeado() {
	
		cargarVentana();
		cargarEtiquetas();
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
	
	public void cargarEtiquetas(){
		
		labelHola = new JTextArea("Hola, ");
		labelHola.setLineWrap(true);
		labelHola.setBackground(SystemColor.controlHighlight);
		labelHola.setEnabled(false);
		labelHola.setEditable(false);
		labelHola.setBounds(170, 135, 312, 141);
		frame.getContentPane().add(labelHola);
		
		labelCorreo = new JLabel("Tu correo es");
		labelCorreo.setBounds(170, 310, 312, 19);
		frame.getContentPane().add(labelCorreo);
		
	}

	public JFrame getFrame() {
		return frame;
	}

	public JTextArea getlabelHola() {
		return labelHola;
	}


	public JLabel getlabelCorreo() {
		return labelCorreo;
	}

}
