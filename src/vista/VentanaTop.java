package vista;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;

public class VentanaTop extends JFrame {

	private JPanel contentPane;
	private DefaultTableModel modelEspacios;
	private JTable tableEspacios;
	private DefaultTableModel modelMunicipios;
	private JTable tableMunicipios;
	private String columnasEspacios[] = {"Nombre","Descripcion","Tipo","Direccion"};
	private String columnasMunicipios[] = {"Nombre","NoGm3"};

	private JScrollPane scrollPaneEstaciones;
	private JScrollPane scrollPaneMunicipios;
	private JScrollPane scrollPaneBizkaia;
	private JScrollPane scrollPaneAraba;
	private JScrollPane scrollPaneGuipuzkoa;
	
	private DefaultTableModel modelBizkaia;
	private JTable tableBizkaia;
	
	private DefaultTableModel modelAraba;
	private JTable tableAraba;
	
	private DefaultTableModel modelGuipu;
	private JTable tableGuipu;
	
	private boolean comprobacionPrueba = false;
	
	  public VentanaTop () {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 708, 591);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		this.scrollPaneEstaciones = new JScrollPane();
		this.scrollPaneEstaciones.setBounds(10, 413, 670, 137);
		contentPane.add(this.scrollPaneEstaciones);
		
		
		this.modelEspacios = new DefaultTableModel(); 
		this.tableEspacios = new JTable();
		this.scrollPaneEstaciones.setViewportView(this.tableEspacios);
		this.tableEspacios = new JTable(this.modelEspacios);	
		this.tableEspacios.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		this.scrollPaneEstaciones.setViewportView(this.tableEspacios);
		for(int i=0; i<columnasEspacios.length;i++) {
			this.modelEspacios.addColumn(columnasEspacios[i]);
			
		}
		this.tableEspacios.setModel(modelEspacios);
		
		
		this.scrollPaneMunicipios = new JScrollPane();
		this.scrollPaneMunicipios.setBounds(372, 47, 308, 149);
		contentPane.add(this.scrollPaneMunicipios);
		
		
		this.modelMunicipios = new DefaultTableModel(); 
		this.tableMunicipios = new JTable();
		this.scrollPaneMunicipios.setViewportView(this.tableMunicipios);
		this.tableMunicipios = new JTable(this.modelMunicipios);	
		this.tableMunicipios.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		this.scrollPaneMunicipios.setViewportView(this.tableMunicipios);
		for(int i=0; i<this.columnasMunicipios.length;i++) {
			this.modelMunicipios.addColumn(this.columnasMunicipios[i]);	
		}
		this.tableMunicipios.setModel(modelMunicipios);
		
		this.scrollPaneBizkaia = new JScrollPane();
		this.scrollPaneBizkaia.setBounds(10, 225, 308, 165);
		contentPane.add(this.scrollPaneBizkaia);
		
		this.modelBizkaia = new DefaultTableModel(); 
		this.tableBizkaia = new JTable();
		this.scrollPaneBizkaia.setViewportView(this.tableBizkaia);
		this.tableBizkaia = new JTable(this.modelBizkaia);	
		this.tableBizkaia.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		this.scrollPaneBizkaia.setViewportView(this.tableBizkaia);
		for(int i=0; i<columnasMunicipios.length;i++) {
			this.modelBizkaia.addColumn(this.columnasMunicipios[i]);
		}
		this.tableBizkaia.setModel(this.modelBizkaia);
		
		this.scrollPaneAraba = new JScrollPane();
		this.scrollPaneAraba.setBounds(10, 47, 308, 149);
		contentPane.add(this.scrollPaneAraba);
		
		this.modelAraba = new DefaultTableModel(); 
		this.tableAraba = new JTable();
		this.scrollPaneAraba.setViewportView(this.tableAraba);
		this.tableAraba = new JTable(modelAraba);	
		this.tableAraba.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		this.scrollPaneAraba.setViewportView(this.tableAraba);
		for(int i=0; i<columnasMunicipios.length;i++) {
			this.modelAraba.addColumn(columnasMunicipios[i]);
		}
		this.tableAraba.setModel(this.modelAraba);
		
		this.scrollPaneGuipuzkoa = new JScrollPane();
		this.scrollPaneGuipuzkoa.setBounds(372, 225, 308, 165);
		contentPane.add(this.scrollPaneGuipuzkoa);
		
		this.modelGuipu = new DefaultTableModel(); 
		this.tableGuipu = new JTable();
		this.scrollPaneGuipuzkoa.setViewportView(this.tableGuipu);
		this.tableGuipu = new JTable(modelGuipu);	
		this.tableGuipu.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		this.scrollPaneGuipuzkoa.setViewportView(tableGuipu);
		for(int i=0; i<columnasMunicipios.length;i++) {
			this.modelGuipu.addColumn(columnasMunicipios[i]);
		}
		this.tableGuipu.setModel(this.modelGuipu);
		
		JLabel lblNewLabel = new JLabel("Guipuzkoa");
		lblNewLabel.setBounds(514, 207, 67, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblBizkaia = new JLabel("Bizkaia");
		lblBizkaia.setBounds(139, 207, 46, 14);
		contentPane.add(lblBizkaia);
		
		JLabel lblAraba = new JLabel("Araba");
		lblAraba.setBounds(139, 22, 46, 14);
		contentPane.add(lblAraba);
		
		JLabel lblGeneral = new JLabel("General");
		lblGeneral.setBounds(514, 22, 46, 14);
		contentPane.add(lblGeneral);
		
		JLabel lblNewLabel_4 = new JLabel("Entornos");
		lblNewLabel_4.setBounds(318, 394, 58, 14);
		contentPane.add(lblNewLabel_4);		
		comprobacionPrueba = true;
	}

	public JScrollPane getScrollPaneBizkaia() {
		return scrollPaneBizkaia;
	}

	public void setScrollPaneBizkaia(JScrollPane scrollPaneBizkaia) {
		this.scrollPaneBizkaia = scrollPaneBizkaia;
	}

	public JScrollPane getScrollPaneAraba() {
		return scrollPaneAraba;
	}

	public void setScrollPaneAraba(JScrollPane scrollPaneAraba) {
		this.scrollPaneAraba = scrollPaneAraba;
	}

	public JScrollPane getScrollPaneGuipuzkoa() {
		return scrollPaneGuipuzkoa;
	}

	public void setScrollPaneGuipuzkoa(JScrollPane scrollPaneGuipuzkoa) {
		this.scrollPaneGuipuzkoa = scrollPaneGuipuzkoa;
	}

	public DefaultTableModel getModelBizkaia() {
		return modelBizkaia;
	}

	public void setModelBizkaia(DefaultTableModel modelBizkaia) {
		this.modelBizkaia = modelBizkaia;
	}

	public JTable getTableBizkaia() {
		return tableBizkaia;
	}

	public void setTableBizkaia(JTable tableBizkaia) {
		this.tableBizkaia = tableBizkaia;
	}

	public DefaultTableModel getModelAraba() {
		return modelAraba;
	}

	public void setModelAraba(DefaultTableModel modelAraba) {
		this.modelAraba = modelAraba;
	}

	public JTable getTableAraba() {
		return tableAraba;
	}

	public void setTableAraba(JTable tableAraba) {
		this.tableAraba = tableAraba;
	}

	public DefaultTableModel getModelGuipu() {
		return modelGuipu;
	}

	public void setModelGuipu(DefaultTableModel modelGuipu) {
		this.modelGuipu = modelGuipu;
	}

	public JTable getTableGuipu() {
		return tableGuipu;
	}

	public void setTableGuipu(JTable tableGuipu) {
		this.tableGuipu = tableGuipu;
	}

	public DefaultTableModel getModelEspacios() {
		return modelEspacios;
	}

	public void setModelEspacios(DefaultTableModel modelEspacios) {
		this.modelEspacios = modelEspacios;
	}

	public JTable getTableEspacios() {
		return tableEspacios;
	}

	public void setTableEspacios(JTable tableEspacios) {
		this.tableEspacios = tableEspacios;
	}

	public DefaultTableModel getModelMunicipios() {
		return modelMunicipios;
	}

	public void setModelMunicipios(DefaultTableModel modelMunicipios) {
		this.modelMunicipios = modelMunicipios;
	}

	public JTable getTableMunicipios() {
		return tableMunicipios;
	}

	public void setTableMunicipios(JTable tableMunicipios) {
		this.tableMunicipios = tableMunicipios;
	}

	public String[] getColumnasEspacios() {
		return columnasEspacios;
	}

	public void setColumnasEspacios(String[] columnasEspacios) {
		this.columnasEspacios = columnasEspacios;
	}

	public String[] getColumnasMunicipios() {
		return columnasMunicipios;
	}

	public void setColumnasMunicipios(String[] columnasMunicipios) {
		this.columnasMunicipios = columnasMunicipios;
	}

	public JScrollPane getScrollPaneEstaciones() {
		return scrollPaneEstaciones;
	}

	public void setScrollPaneEstaciones(JScrollPane scrollPaneEstaciones) {
		this.scrollPaneEstaciones = scrollPaneEstaciones;
	}

	public JScrollPane getScrollPaneMunicipios() {
		return scrollPaneMunicipios;
	}

	public void setScrollPaneMunicipios(JScrollPane scrollPaneMunicipios) {
		this.scrollPaneMunicipios = scrollPaneMunicipios;
	}
	
	public boolean metodoPrueba()
	{
		return comprobacionPrueba;
	}
}
