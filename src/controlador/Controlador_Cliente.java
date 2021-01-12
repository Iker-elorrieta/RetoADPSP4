//package controlador;
//
//import java.awt.event.MouseAdapter;
//import java.awt.event.MouseEvent;
//import java.io.ObjectOutputStream;
//
//import javax.swing.JFrame;
//
//import vista.Login;
//import vista.Registrar;
//import vista.VentanaCliente;
//
//public class Controlador_Cliente {
//	
//	private VentanaCliente frame;
	
//	public Controlador_Cliente(JFrame frame,ObjectOutputStream salida)
//	{
//		this.frame = (VentanaCliente) frame;
//		botones(salida);
//	}
//	
//	public void botones(ObjectOutputStream salida)
//	{
//		frame.getBotonLogin().addMouseListener(new MouseAdapter() {
//			@Override
//			public void mouseClicked(MouseEvent e) {
//				Login ventana = new Login();
//				ventana.setVisible(true);
//				frame.setVisible(false);
//				@SuppressWarnings("unused")
//				Controlador_Login controlador = new Controlador_Login(ventana,salida);
//			}
//		});
//		
//		frame.getBotonRegistrar().addMouseListener(new MouseAdapter() {
//			@Override
//			public void mouseClicked(MouseEvent e) {
//				Registrar ventana = new Registrar();
//				ventana.setVisible(true);
//				frame.setVisible(false);
//				@SuppressWarnings("unused")
//				Controlador_Registro controlador = new Controlador_Registro(ventana,salida);
//			}
//		});
//	}
//}
