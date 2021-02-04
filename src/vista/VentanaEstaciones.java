package vista;

import java.awt.Font;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTable;
import javax.swing.WindowConstants;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;

@SuppressWarnings("serial")
public class VentanaEstaciones extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private DefaultTableModel model;
	private String columnas[] = {"Hora","ICA","NOXGM3","PM10"};
	@SuppressWarnings("rawtypes")
	private JComboBox comboBox;
	private boolean comprobacionPrueba = false;
	
	@SuppressWarnings("rawtypes")
	public VentanaEstaciones() {
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setBounds(100, 100, 583, 423);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnNewButton = new JButton("He visto suficiente");
		btnNewButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnNewButton.setBounds(221, 350, 176, 23);
		contentPane.add(btnNewButton);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 47, 547, 300);
		contentPane.add(scrollPane);
		
		
		model = new DefaultTableModel(); 
		table = new JTable();
		scrollPane.setViewportView(table);
		table = new JTable(model);	
		table.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		scrollPane.setViewportView(table);
		for(int i=0; i<columnas.length;i++) {
			model.addColumn(columnas[i]);
			
		}
		table.setModel(model);
		
		comboBox = new JComboBox();
		comboBox.setBounds(10, 14, 121, 23);
		contentPane.add(comboBox);
		
		comprobacionPrueba = true;
	}

	public JTable getTable() {
		return table;
	}

	public void setTable(JTable table) {
		this.table = table;
	}

	public DefaultTableModel getModel() {
		return model;
	}

	public void setModel(DefaultTableModel model) {
		this.model = model;
	}

	@SuppressWarnings("rawtypes")
	public JComboBox getComboBox() {
		return comboBox;
	}

	@SuppressWarnings("rawtypes")
	public void setComboBox(JComboBox comboBox) {
		this.comboBox = comboBox;
	}
	
	public boolean metodoPrueba()
	{
		return comprobacionPrueba;
	}
}
