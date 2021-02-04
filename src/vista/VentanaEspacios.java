package vista;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;

@SuppressWarnings("serial")
public class VentanaEspacios extends JFrame {

	private JPanel contentPane;
	private DefaultTableModel model;
	private String columnas[] = {"Nombre","Descripcion","Tipo"};
	private JTable Espacios;
	private JButton Volver;
	private DefaultTableModel modelo;
	private boolean probarVentana = false;
	
	public VentanaEspacios() {
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setBounds(100, 100, 734, 502);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(105, 5, 608, 453);
		contentPane.add(scrollPane);
		
		Espacios = new JTable();
		scrollPane.setViewportView(Espacios);
		modelo = new DefaultTableModel();
		for(int i=0; i<columnas.length;i++) {
			this.modelo.addColumn(columnas[i]);
		}
		this.Espacios.setModel(modelo);
		
		Volver = new JButton("Volver");
		Volver.setBounds(10, 180, 89, 23);
		contentPane.add(Volver);
		
		
		model = new DefaultTableModel();
		for(int i=0; i<columnas.length;i++) {
			model.addColumn(columnas[i]);
			
		}
		probarVentana = true;
	}


	public DefaultTableModel getModel() {
		return model;
	}
	public JTable getEspacios() {
		return Espacios;
	}
	public void setEspacios(JTable espacios) {
		Espacios = espacios;
	}
	public JButton getVolver() {
		return Volver;
	}
	public void setVolver(JButton volver) {
		Volver = volver;
	}
	
	public boolean metodoPrueba()
	{
		return probarVentana;
	}
	
}
