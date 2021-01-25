package controlador;

import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import javax.swing.table.DefaultTableModel;

import modelo.Estaciones;
import modelo.Horario;
import modelo.Municipios;
import modelo.Usuario;
import vista.VentanaEstaciones;

public class ControladorEstaciones implements ActionListener {

	private VentanaEstaciones ventanaEstaciones;
	private Usuario usuario;
	private ObjectInputStream entrada;
	private ObjectOutputStream salida;
	private ArrayList<Estaciones> araE;
	public static boolean booleanTest = false;
	final String na = "No disponible";

	/**
	 * {@summary Constructor de la clase que inicia el controlador}
	 * 
	 * @param ventanaLogeado
	 * @param usuario
	 */

	public ControladorEstaciones(VentanaEstaciones ventanaEstaciones, Usuario usuario, ObjectInputStream entrada,
			ObjectOutputStream salida, ArrayList<Estaciones> araE) {
		this.ventanaEstaciones = ventanaEstaciones;
		this.usuario = usuario;
		this.entrada = entrada;
		this.salida = salida;
		booleanTest = true;
		this.araE = araE;

		iniciarControlador();

	}

	/**
	 * {@summary Método que hace visible el frame de la ventana y que cambia las
	 * etiquetas en función de los datos del usuario que ha iniciado sesión}
	 */

	@SuppressWarnings("unchecked")
	public void iniciarControlador() {

		this.ventanaEstaciones.setVisible(true);
		this.ventanaEstaciones.getTable().setVisible(true);
		this.ventanaEstaciones.addWindowListener(new WindowAdapter() {

			public void windowClosing(WindowEvent e) {
				try {
					salida.writeObject(999);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}

		});

		try {

			this.ventanaEstaciones.getComboBox().addActionListener(this);
			this.ventanaEstaciones.getComboBox().setActionCommand("combo");

			DefaultTableModel model = (DefaultTableModel) ventanaEstaciones.getModel();
			model.setRowCount(0);

			for (int i = 0; i < araE.size(); i++) {
				ventanaEstaciones.getComboBox().addItem(araE.get(i).getNombre().toString());
			}

			salida.writeObject(6);

			int wombo = araE.get(ventanaEstaciones.getComboBox().getSelectedIndex()).getId();
			salida.writeObject(wombo);

			ArrayList<Horario> arah = (ArrayList<Horario>) entrada.readObject();
			for (int i = 0; i < arah.size(); i++) {
				String temp[] = { arah.get(i).getHora(), arah.get(i).getIcaestacion(), arah.get(i).getNoxgm3() + "",
						arah.get(i).getPm10() + "" };
				ventanaEstaciones.getModel().addRow(temp);

			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		switch (e.getActionCommand()) {

		case "combo":

			try {

				DefaultTableModel model = (DefaultTableModel) ventanaEstaciones.getModel();
				model.setRowCount(0);
				salida.writeObject(6);
				Integer nbr = araE.get(ventanaEstaciones.getComboBox().getSelectedIndex()).getId();
				salida.writeObject(nbr);
				ArrayList<Horario> ah = (ArrayList<Horario>) entrada.readObject();

				for (int i = 0; i < ah.size(); i++) {
					if (ah.get(i).getHora() == null || ah.get(i).getHora().equals("")) {
						ah.get(i).setHora(na);
					} else if (ah.get(i).getIcaestacion() == null || ah.get(i).getIcaestacion().isEmpty()) {
						ah.get(i).setIcaestacion(na);
					} else if (ah.get(i).getNoxgm3() == null || ah.get(i).getNoxgm3().equals(null)) {
						ah.get(i).setNoxgm3(0.00);
					} else if (ah.get(i).getPm10() == null || ah.get(i).getPm10().equals(null)) {
						ah.get(i).setPm10(0.00);
					}

					String temp[] = { ah.get(i).getHora(), ah.get(i).getIcaestacion(), ah.get(i).getNoxgm3() + "",
							ah.get(i).getPm10() + "" };

					ventanaEstaciones.getModel().addRow(temp);

				}

			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			break;
		}
	}
}
