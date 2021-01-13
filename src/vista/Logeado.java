package vista;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class Logeado {
	
	private JFrame frame;
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_1;
	
	
	public Logeado() {
		
		cargarVentana();
		
	}
	
	public void cargarVentana() {
		
		frame = new JFrame();
		frame.setResizable(false);
		frame.setBounds(100, 100, 700, 525);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.getContentPane().setLayout(null);
		
		lblNewLabel = new JLabel("Hola, ");
		lblNewLabel.setBounds(183, 183, 312, 14);
		frame.getContentPane().add(lblNewLabel);
		
		lblNewLabel_1 = new JLabel("Tu correo es");
		lblNewLabel_1.setBounds(183, 224, 312, 19);
		frame.getContentPane().add(lblNewLabel_1);
	
	}

	public JFrame getFrame() {
		return frame;
	}

	public void setFrame(JFrame frame) {
		this.frame = frame;
	}

	public JLabel getLblNewLabel() {
		return lblNewLabel;
	}

	public void setLblNewLabel(JLabel lblNewLabel) {
		this.lblNewLabel = lblNewLabel;
	}

	public JLabel getLblNewLabel_1() {
		return lblNewLabel_1;
	}

	public void setLblNewLabel_1(JLabel lblNewLabel_1) {
		this.lblNewLabel_1 = lblNewLabel_1;
	}
}
