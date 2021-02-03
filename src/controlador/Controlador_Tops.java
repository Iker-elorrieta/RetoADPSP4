package controlador;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import javax.swing.table.DefaultTableModel;

import modelo.Entornos;
import modelo.Municipios;
import modelo.Usuario;
import vista.Logeado;
import vista.VentanaTop;

public class Controlador_Tops {
	private VentanaTop VentanaTop;
	private ObjectInputStream entrada;
	private ObjectOutputStream salida;
	private ArrayList<Municipios> araMuniTop;
	private ArrayList<Municipios> araMuniBizkaia;
	private ArrayList<Municipios> araMuniGipu;
	private ArrayList<Municipios> araMuniAraba;
	private ArrayList<Entornos> araEntornosTop;
	
	public static boolean booleanTest = false;

	/**
	 * {@summary Constructor de la clase que inicia el controlador}
	 * 
	 * @param ventanaLogeado
	 * @param usuario
	 */
	public Controlador_Tops(VentanaTop vT, ObjectInputStream entrada,
			ObjectOutputStream salida) {
		this.VentanaTop = vT;
		this.entrada = entrada;
		this.salida = salida;
		iniciarControlador();
		booleanTest = true;

	}
	
	
	public void iniciarControlador(){
		
		
		try {
			salida.writeObject(7);
			
			araMuniTop = (ArrayList<Municipios>) entrada.readObject();
			araMuniBizkaia = (ArrayList<Municipios>) entrada.readObject();
			araMuniAraba = (ArrayList<Municipios>) entrada.readObject();
			araMuniGipu = (ArrayList<Municipios>) entrada.readObject();
			
			//araEntornosTop = (ArrayList<Entornos>) entrada.readObject();
			
			
			System.out.println(araMuniTop.size());
			
			DefaultTableModel model = (DefaultTableModel) VentanaTop.getModelMunicipios();
			model.setRowCount(0);
			for (int i = 0; i < araMuniTop.size(); i++) {
				String temp[] = { araMuniTop.get(i).getNombre(), araMuniTop.get(i).getDescripcion() };
				VentanaTop.getModelMunicipios().addRow(temp);
			}
			
			DefaultTableModel modelBizka = (DefaultTableModel) VentanaTop.getModelBizkaia();
			modelBizka.setRowCount(0);
			for (int i = 0; i < araMuniBizkaia.size(); i++) {
				String temp[] = { araMuniBizkaia.get(i).getNombre(), araMuniBizkaia.get(i).getDescripcion() };
				VentanaTop.getModelBizkaia().addRow(temp);
			}
			
			DefaultTableModel modelAraba = (DefaultTableModel) VentanaTop.getModelAraba();
			modelAraba.setRowCount(0);
			
			for (int i = 0; i < araMuniAraba.size(); i++) {
				String temp[] = { araMuniAraba.get(i).getNombre(), araMuniAraba.get(i).getDescripcion() };
				VentanaTop.getModelAraba().addRow(temp);
			}
			
			DefaultTableModel modelGipu = (DefaultTableModel) VentanaTop.getModelGuipu();
			modelGipu.setRowCount(0);
			for (int i = 0; i < araMuniGipu.size(); i++) {
				String temp[] = { araMuniGipu.get(i).getNombre(), araMuniGipu.get(i).getDescripcion() };
				VentanaTop.getModelGuipu().addRow(temp);
			}
			
//			DefaultTableModel modelEtornos = (DefaultTableModel) VentanaTop.getModelEspacios();
//			modelEtornos.setRowCount(0);
//			for (int i = 0; i < araEntornosTop.size(); i++) {
//				String temp[] = { araEntornosTop.get(i).getNombre(), araEntornosTop.get(i).getDescripcion() };
//				VentanaTop.getModelEspacios().addRow(temp);
//			}
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
