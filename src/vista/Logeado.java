package vista;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class Logeado {
	
	private JFrame frame;
	private JLabel labelHola;
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
		
		labelHola = new JLabel("Hola, ");
		labelHola.setBounds(183, 183, 312, 14);
		frame.getContentPane().add(labelHola);
		
		labelCorreo = new JLabel("Tu pregunta es ");
		labelCorreo.setBounds(183, 224, 312, 19);
		frame.getContentPane().add(labelCorreo);
		
	}

	public JFrame getFrame() {
		return frame;
	}

	public JLabel getlabelHola() {
		return labelHola;
	}


	public JLabel getlabelCorreo() {
		return labelCorreo;
	}

}
