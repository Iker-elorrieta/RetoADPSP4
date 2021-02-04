package vista;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;

public class Logeado {
	
	private JFrame frame;
	private JTable table;
	private String columnas[] = {"Nombre","Descripcion"};
	private DefaultTableModel model;
	private JButton botonBizkaia;
	private JButton botonAraba;
	private JButton botonGipuzkoa;
    private	JButton botonTops;
	private JButton botonEspaciosNaturales;
	private boolean probarVentana = false;
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
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.getContentPane().setLayout(null);	
		
		this.botonBizkaia = new JButton("Bizkaia");
		this.botonBizkaia.setBounds(10, 167, 117, 42);
		frame.getContentPane().add(this.botonBizkaia);
		
		this.botonAraba = new JButton("Araba");
		this.botonAraba.setBounds(10, 220, 117, 42);
		frame.getContentPane().add(this.botonAraba);
		
		this.botonGipuzkoa = new JButton("Gipuzkoa");
		this.botonGipuzkoa.setBounds(10, 273, 117, 42);
		frame.getContentPane().add(this.botonGipuzkoa);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(161, 26, 523, 434);
		frame.getContentPane().add(scrollPane);
		
		model = new DefaultTableModel(); 
		table = new JTable(model);
		
	//	table.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		scrollPane.setViewportView(table);
		for(int i=0; i<columnas.length;i++) {
			model.addColumn(columnas[i]);
			
		}
		table.setModel(model);
		
		botonTops = new JButton("Tops");
		botonTops.setBounds(10, 373, 141, 23);
		frame.getContentPane().add(botonTops);
		
		botonEspaciosNaturales = new JButton("Espacios Naturales");
		botonEspaciosNaturales.setBounds(10, 407, 141, 23);
		frame.getContentPane().add(botonEspaciosNaturales);
		probarVentana = true;
	}
	
	/**
	 * {@summary Método que carga las etiquetas de la ventana} 
	 */
	
	public void cargarEtiquetas(){		
	}

	public DefaultTableModel getModel() {
		return model;
	}

	public void setModel(DefaultTableModel model) {
		this.model = model;
	}

	public JTable getTable() {
		return table;
	}

	public void setTable(JTable table) {
		this.table = table;
	}

	public JButton getBotonBizkaia() {
		return botonBizkaia;
	}

	public JButton getBotonAraba() {
		return botonAraba;
	}

	public JButton getBotonGipuzkoa() {
		return botonGipuzkoa;
	}

	public JFrame getFrame() {
		return frame;
	}

	public JButton getBotonTops() {
		return botonTops;
	}

	public JButton getBotonEspaciosNaturales() {
		return botonEspaciosNaturales;
	}
	
	public boolean probarClase()
	{
		return probarVentana;
	}
}
