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

@SuppressWarnings("serial")
public class VentanaEspacios extends JFrame {

	private JPanel contentPane;
	private DefaultTableModel model;
	private JTable table;
	private String columnas[] = {"Nombre","Descripcion","Tipo"};

	
	public VentanaEspacios() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		
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
		
		
		
		
		
	}

}
