package controlador;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import vista.VentanaEspacios;

public class Controlador_Espacios {
	
	private VentanaEspacios ventana;
	private ObjectInputStream entrada;
	private ObjectOutputStream salida;
	
	public Controlador_Espacios(VentanaEspacios ventana, ObjectInputStream entrada, ObjectOutputStream salida){
		this.ventana = ventana;
		this.entrada = entrada;
		this.salida = salida;
	}
}
