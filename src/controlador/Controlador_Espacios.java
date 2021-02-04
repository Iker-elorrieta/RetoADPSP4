package controlador;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import javax.swing.table.DefaultTableModel;

import modelo.Entornos;
import modelo.Horario;
import vista.Logeado;
import vista.VentanaEspacios;
import vista.VentanaEstaciones;

public class Controlador_Espacios implements MouseListener {
	
	private VentanaEspacios ventana;
	private ObjectInputStream entrada;
	private ObjectOutputStream salida;
	private Logeado ventanaAnterior;
	private boolean pruebaClase = false;
	
	public Controlador_Espacios(Logeado ventanaAnterior, VentanaEspacios ventana, ObjectInputStream entrada, ObjectOutputStream salida){
		this.ventana = ventana;
		this.entrada = entrada;
		this.salida = salida;
		this.ventanaAnterior = ventanaAnterior;
		iniciarControlador();
	}
	
	public void iniciarControlador() {
		this.ventana.setVisible(true);
		this.ventana.getVolver().addMouseListener(this);
		this.ventana.getVolver().setName("volver");
		this.ventana.getEspacios().addMouseListener(this);
		this.ventana.getEspacios().setName("row");
		
		ArrayList<Entornos> lista = new ArrayList<Entornos>();
		try {
			salida.writeObject(8);
			lista = (ArrayList<Entornos>) entrada.readObject();
			
		} catch (IOException | ClassNotFoundException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		DefaultTableModel model = (DefaultTableModel) ventana.getEspacios().getModel();
		model.setRowCount(0);
		
		for (int i = 0; i < lista.size(); i++) {
			String temp[] = { lista.get(i).getNombre(), lista.get(i).getDescripcion(), lista.get(i).getTipo() };
			model.addRow(temp);
		}
		ventana.getEspacios().setModel(model);
		pruebaClase = true;
		this.ventana.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				try {
					salida.writeObject(999);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}

		});
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		switch (e.getComponent().getName()) {
		
		case "volver":
			ventanaAnterior.getFrame().setVisible(true);
			ventana.dispose();
			pruebaClase = true;
			break;
		case "row":
			VentanaEstaciones VE = new VentanaEstaciones();
			try {
				salida.writeObject(9);
				DefaultTableModel model = (DefaultTableModel) ventana.getEspacios().getModel();
				try {
					salida.writeObject(model.getValueAt(ventana.getEspacios().getSelectedRow(), 0).toString());
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				ArrayList<Horario> horarios = (ArrayList<Horario>) entrada.readObject();
				ControladorEstaciones ce = new ControladorEstaciones(VE, entrada, salida, null,horarios,"espacios");
				VE.getComboBox().setVisible(false);
				VE.setVisible(true);
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			
			pruebaClase = true;
			
			break;
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	public boolean metodoPruebas()
	{
		return pruebaClase;
	}
}
