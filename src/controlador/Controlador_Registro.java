package controlador;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.io.ObjectOutputStream;

import javax.swing.JFrame;

import modelo.Usuario;
import vista.Login;
import vista.Registrar;

public class Controlador_Registro {
	
	private Registrar frame;
	
	public Controlador_Registro(JFrame frame,ObjectOutputStream salida)
	{
		this.frame = (Registrar) frame;
		botones(salida);
	}
	
	public void botones(ObjectOutputStream salida)
	{
		frame.getBotonAcceptar().addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(!frame.getNombre().getText().equals("") && (frame.getContrasena().getText().equals(frame.getRepetirContrasena().getText())) && frame.getEmail().getText().contains("@") && frame.getEmail().getText().contains("."))
				{
					frame.getSuper().setVisible(true);
					frame.dispose();
					Usuario nuevo = new Usuario();
					nuevo.setUsuario(frame.getNombre().getText());
					nuevo.setContrasena(frame.getContrasena().getText());
					nuevo.setEMail(frame.getEmail().getText());
					try {
						salida.writeObject(2);
						salida.writeObject(nuevo);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
	}
}
