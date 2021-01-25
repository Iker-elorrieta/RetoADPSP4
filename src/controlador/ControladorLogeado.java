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
import modelo.Estaciones;
import modelo.Municipios;
import modelo.Usuario;
import vista.Logeado;
import vista.VentanaEstaciones;

public class ControladorLogeado implements MouseListener {

	private Logeado ventanaLogeado;
	private Usuario usuario;
	private ObjectInputStream entrada;
	private ObjectOutputStream salida;
	private ArrayList<Municipios> araMu;
	public static boolean booleanTest = false;

	/**
	 * {@summary Constructor de la clase que inicia el controlador}
	 * 
	 * @param ventanaLogeado
	 * @param usuario
	 */
	public ControladorLogeado(Logeado ventanaLogeado, Usuario usuario, ObjectInputStream entrada,
			ObjectOutputStream salida) {
		this.ventanaLogeado = ventanaLogeado;
		this.usuario = usuario;
		this.entrada = entrada;
		this.salida = salida;
		iniciarControlador();
		booleanTest = true;

	}

	/**
	 * {@summary Método que hace visible el frame de la ventana y que cambia las
	 * etiquetas en función de los datos del usuario que ha iniciado sesión}
	 */
	@SuppressWarnings("unchecked")
	public void iniciarControlador() {

		this.ventanaLogeado.getFrame().setVisible(true);
		this.ventanaLogeado.getTable().setVisible(true);
		this.ventanaLogeado.getBotonBizkaia().setName("Bizkaia");
		this.ventanaLogeado.getBotonBizkaia().addMouseListener(this);
		this.ventanaLogeado.getBotonAraba().setName("Araba");
		this.ventanaLogeado.getBotonAraba().addMouseListener(this);
		this.ventanaLogeado.getBotonGipuzkoa().setName("Gipuzkoa");
		this.ventanaLogeado.getBotonGipuzkoa().addMouseListener(this);
		this.ventanaLogeado.getTable().addMouseListener(this);
		this.ventanaLogeado.getTable().setName("row");
		this.ventanaLogeado.getFrame().addWindowListener(new WindowAdapter() {

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

			DefaultTableModel model = (DefaultTableModel) ventanaLogeado.getModel();
			model.setRowCount(0);

			salida.writeObject(5);

			araMu = (ArrayList<Municipios>) entrada.readObject();

			for (int i = 0; i < araMu.size(); i++) {
				String temp[] = { araMu.get(i).getNombre(), araMu.get(i).getDescripcion() };
				ventanaLogeado.getModel().addRow(temp);
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	/**
	 * Metodo que carga la lista en base a la provincia seleccionada.
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub

		switch (e.getComponent().getName()) {

		case "Bizkaia":

			try {

				DefaultTableModel model = (DefaultTableModel) ventanaLogeado.getModel();
				model.setRowCount(0);

				salida.writeObject(501);

				ArrayList<Municipios> arrayBizkaia = (ArrayList<Municipios>) entrada.readObject();

				arrayATabla(arrayBizkaia);

				System.out.println("hellegado");

			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			break;

		case "Araba":
			try {
				DefaultTableModel model = (DefaultTableModel) ventanaLogeado.getModel();
				model.setRowCount(0);
				salida.writeObject(502);

				ArrayList<Municipios> arrayBizkaia = (ArrayList<Municipios>) entrada.readObject();

				arrayATabla(arrayBizkaia);

			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			break;

		case "Gipuzkoa":
			try {
				DefaultTableModel model = (DefaultTableModel) ventanaLogeado.getModel();
				model.setRowCount(0);
				salida.writeObject(503);

				ArrayList<Municipios> arrayBizkaia = (ArrayList<Municipios>) entrada.readObject();

				arrayATabla(arrayBizkaia);

			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			break;

		}

	}

	/**
	 * Metodo para sacar los horarios de las estaciónes
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		switch (e.getComponent().getName()) {

		case "row":

			int colum = ventanaLogeado.getTable().columnAtPoint(e.getPoint());

			int row = ventanaLogeado.getTable().rowAtPoint(e.getPoint());

			String devuelto = (String) ventanaLogeado.getTable().getModel().getValueAt(row, colum);

			if (!devuelto.equals("")) {
				try {
					salida.writeObject(404);
					salida.writeObject(devuelto);
					ArrayList<Estaciones> araE;
					araE = (ArrayList<Estaciones>) entrada.readObject();

					VentanaEstaciones VE = new VentanaEstaciones();
					@SuppressWarnings("unused")
					ControladorEstaciones ce = new ControladorEstaciones(VE, usuario, entrada, salida, araE);
					VE.setVisible(true);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}

			break;
		}

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

	public boolean arrayATabla(ArrayList<Municipios> arrayMunillo) {

		System.out.println(arrayMunillo.size());
		for (int i = 0; i < arrayMunillo.size(); i++) {
			String temp[] = { arrayMunillo.get(i).getNombre(), arrayMunillo.get(i).getDescripcion() };
			ventanaLogeado.getModel().addRow(temp);
		}

		return true;
	}

}
