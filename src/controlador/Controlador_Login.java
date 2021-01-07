package controlador;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.io.ObjectOutputStream;

import javax.swing.JFrame;

import modelo.Usuario;
import vista.Login;

public class Controlador_Login {
	
	private Login frame;
	
	public Controlador_Login(JFrame frame,ObjectOutputStream salida)
	{
		this.frame = (Login) frame;
		botones(salida);
	}
	
	public void botones(ObjectOutputStream salida)
	{
		frame.getBotonAcceptar().addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(!frame.getNombre().getText().equals(""))
				{
					frame.getSuper().setVisible(true);
					frame.dispose();
					Usuario nuevo = new Usuario();
					nuevo.setUsuario(frame.getNombre().getText());
					nuevo.setContrasena(frame.getContrasena().getText());
					try {
						salida.writeObject(1);
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
