package vista;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JMenuBar;
import javax.swing.JMenu;

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
		
		JButton botonEspaciosNaturales = new JButton("Espacios Naturales");
		botonEspaciosNaturales.setBounds(10, 407, 141, 23);
		frame.getContentPane().add(botonEspaciosNaturales);
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

	public void setFrame(JFrame frame) {
		this.frame = frame;
	}

	public JButton getBotonBizkaia() {
		return botonBizkaia;
	}

	public void setBotonBizkaia(JButton botonBizkaia) {
		this.botonBizkaia = botonBizkaia;
	}

	public JButton getBotonAraba() {
		return botonAraba;
	}

	public void setBotonAraba(JButton botonAraba) {
		this.botonAraba = botonAraba;
	}

	public JButton getBotonGipuzkoa() {
		return botonGipuzkoa;
	}

	public void setBotonGipuzkoa(JButton botonGipuzkoa) {
		this.botonGipuzkoa = botonGipuzkoa;
	}

	public JFrame getFrame() {
		return frame;
	}

	public JButton getBotonTops() {
		return botonTops;
	}

	public void setBotonTops(JButton botonTops) {
		this.botonTops = botonTops;
	}

	public JButton getBotonEspaciosNaturales() {
		return botonEspaciosNaturales;
	}

	public void setBotonEspaciosNaturales(JButton botonEspaciosNaturales) {
		this.botonEspaciosNaturales = botonEspaciosNaturales;
	}
	
}
