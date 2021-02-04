package controlador;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import javax.swing.table.DefaultTableModel;

import modelo.Entornos;
import modelo.Municipios;
import modelo.Usuario;
import server.HiloServidor;
import server.HiloServidor.ListadoTop;
import vista.Logeado;
import vista.VentanaTop;

public class Controlador_Tops {
	private VentanaTop VentanaTop;
	private ObjectInputStream entrada;
	private ObjectOutputStream salida;
	private ArrayList<HiloServidor.ListadoTop> araMuniTop;
	private ArrayList<HiloServidor.ListadoTop> araMuniBizkaia;
	private ArrayList<HiloServidor.ListadoTop> araMuniAraba;
	private ArrayList<HiloServidor.ListadoTop> araMuniGipu;
	private ArrayList<HiloServidor.ListadoTop> araEntornosTop;
	
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
			
			araMuniTop = (ArrayList<HiloServidor.ListadoTop>) entrada.readObject();
			araMuniBizkaia = (ArrayList<HiloServidor.ListadoTop>) entrada.readObject();
			araMuniAraba = (ArrayList<HiloServidor.ListadoTop>) entrada.readObject();
			araMuniGipu = (ArrayList<HiloServidor.ListadoTop>) entrada.readObject();
			araEntornosTop = (ArrayList<HiloServidor.ListadoTop>) entrada.readObject();
						
			DefaultTableModel model = (DefaultTableModel) VentanaTop.getModelMunicipios();
			model.setRowCount(0);
			for (int i = 0; i < araMuniTop.size(); i++) {
				String temp[] = { araMuniTop.get(i).getParam1(), String.valueOf(araMuniTop.get(i).getParam2()) };
				VentanaTop.getModelMunicipios().addRow(temp);
			}
			
			DefaultTableModel modelBizka = (DefaultTableModel) VentanaTop.getModelBizkaia();
			modelBizka.setRowCount(0);
			for (int i = 0; i < araMuniBizkaia.size(); i++) {
				String temp[] = { araMuniBizkaia.get(i).getParam1(), String.valueOf(araMuniBizkaia.get(i).getParam2()) };
				VentanaTop.getModelBizkaia().addRow(temp);
			}
			
			DefaultTableModel modelAraba = (DefaultTableModel) VentanaTop.getModelAraba();
			modelAraba.setRowCount(0);
			for (int i = 0; i < araMuniAraba.size(); i++) {
				String temp[] = { araMuniAraba.get(i).getParam1(), String.valueOf(araMuniAraba.get(i).getParam2()) };
				modelAraba.addRow(temp);
			}
			VentanaTop.setModelAraba(modelAraba);
			
			DefaultTableModel modelGipu = (DefaultTableModel) VentanaTop.getModelGuipu();
			modelGipu.setRowCount(0);
			for (int i = 0; i < araMuniGipu.size(); i++) {
				String temp[] = { araMuniGipu.get(i).getParam1(),  String.valueOf(araMuniGipu.get(i).getParam2()) };
				VentanaTop.getModelGuipu().addRow(temp);
			}
			
			DefaultTableModel modelEtornos = (DefaultTableModel) VentanaTop.getModelEspacios();
			modelEtornos.setRowCount(0);
			for (int i = 0; i < araEntornosTop.size(); i++) {
				String temp[] = { araEntornosTop.get(i).getParam1(), String.valueOf(araEntornosTop.get(i).getParam2()) };
				VentanaTop.getModelEspacios().addRow(temp);
			}
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
