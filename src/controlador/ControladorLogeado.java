package controlador;

import java.awt.Window;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import modelo.Municipios;
import modelo.Usuario;
import vista.Logeado;

public class ControladorLogeado implements MouseListener {
	
	private Logeado ventanaLogeado;
	private Usuario usuario;
	private ObjectInputStream entrada;
	private ObjectOutputStream salida;
	private ArrayList<Municipios> araMu;
	public static boolean booleanTest = false;
	
	/**
	 * {@summary Constructor de la clase que inicia el controlador}
	 * @param ventanaLogeado
	 * @param usuario
	 */
	
	public ControladorLogeado(Logeado ventanaLogeado, Usuario usuario, ObjectInputStream entrada, ObjectOutputStream salida) {
		this.ventanaLogeado = ventanaLogeado;
		this.usuario = usuario;
		this.entrada = entrada;
		this.salida= salida;
		iniciarControlador();	
		booleanTest = true;
		
	}
	
	/**
	 * {@summary Método que hace visible el frame de la ventana y que cambia las etiquetas en función de los datos del usuario que ha iniciado sesión}
	 */
	
	@SuppressWarnings("unchecked")
	public void iniciarControlador() {
		
		this.ventanaLogeado.getFrame().setVisible(true);
		this.ventanaLogeado.getTable().setVisible(true);
		
		this.ventanaLogeado.getBotonBizkaia().setName("Bizkaia");;
		this.ventanaLogeado.getBotonBizkaia().addMouseListener(this);
		
		this.ventanaLogeado.getBotonAraba().setName("Araba");;
		this.ventanaLogeado.getBotonAraba().addMouseListener(this);
		
		
		this.ventanaLogeado.getBotonGipuzkoa().setName("Gipuzkoa");;
		this.ventanaLogeado.getBotonGipuzkoa().addMouseListener(this);
		
		
		
		
		try {
			
			salida.writeObject(5);
				

		 araMu= (ArrayList<Municipios>) entrada.readObject();
		
		for(int i=0; i<araMu.size();i++) {
			String temp[] = {araMu.get(i).getNombre(),araMu.get(i).getDescripcion()};
			ventanaLogeado.getModel().addRow(temp);
		}
		
		
		
		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		switch (e.getComponent().getName()) {
		
		case "Bizkaia":
			
			 try {
				salida.writeObject("501");
		
				System.out.println("llego aqui");
			 ArrayList<Municipios> arrayBizkaia = (ArrayList<Municipios>) entrada.readObject();
			
	
			 arrayATabla(arrayBizkaia);
			 
			 } catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			
		break;
		
		
		case "Araba":
			try {
				salida.writeObject("502");
			
			
			 ArrayList<Municipios> arrayBizkaia = (ArrayList<Municipios>) entrada.readObject();
			
			
			 arrayATabla(arrayBizkaia);
			 
			 } catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			
			
		break;
			
			
		case "Gipuzkoa":
			try {
				salida.writeObject("503");
			
			
			 ArrayList<Municipios> arrayBizkaia = (ArrayList<Municipios>) entrada.readObject();
			
			
			 arrayATabla(arrayBizkaia);
			 
			 } catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			
			
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

	public boolean arrayATabla(ArrayList<Municipios> arrayMunillo) {
		
		System.out.println(arrayMunillo.size());
		for(int i=0; i<arrayMunillo.size();i++) {
			String temp[] = {arrayMunillo.get(i).getNombre(),arrayMunillo.get(i).getDescripcion()};
			ventanaLogeado.getModel().addRow(temp);
			}
		
	return true;	
	}
}
