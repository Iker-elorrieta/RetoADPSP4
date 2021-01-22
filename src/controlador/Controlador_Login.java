package controlador;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import javax.swing.JOptionPane;
import modelo.Usuario;
import vista.Logeado;
import vista.Login;
import vista.Registrar;
import vista.RestaurarContrasena;

public class Controlador_Login implements MouseListener {
	
	private Login ventanaLogin;
	private ObjectOutputStream salida;
	private ObjectInputStream entrada;
	private boolean booleanPrueba = false;
	private String tipo = "normal";
	
	/**
	 * {@summary Constructor de la clase que da inicio al controlador}
	 * @param frame
	 * @param entrada
	 * @param salida
	 */
	
	public Controlador_Login(Login frame, ObjectInputStream entrada, ObjectOutputStream salida) {
		
		this.ventanaLogin = frame;
		this.entrada = entrada;
		this.salida = salida;
		iniciarControlador();
		
	}
	
	/**
	 * {@summary Método que hace visible el frame de la ventana, añade listeners a los componentes y les da nombre}
	 */
	
	public void iniciarControlador() {
		this.ventanaLogin.getFrame().setVisible(true);
		this.ventanaLogin.getBotonIniciar().addMouseListener(this);
		this.ventanaLogin.getBotonIniciar().setName("entrar");
		this.ventanaLogin.getBotonRegistrar().addMouseListener(this);
		this.ventanaLogin.getBotonRegistrar().setName("registrar");
		this.ventanaLogin.getBotonRecuperar().addMouseListener(this);
		this.ventanaLogin.getBotonRecuperar().setName("restaurar");
		
		booleanPrueba = true;
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	/**
	 * {@summary Método que contiene las acciones a realizar al pulsar un botón}
	 */
	
	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
		switch (e.getComponent().getName()) {
		case "entrar":
			
//			// Acción de iniciar sesión. Comprueba que los campos no están vacíos antes de continuar
			
			if (!this.ventanaLogin.getNombre().getText().isBlank() && !this.ventanaLogin.getNombre().getText().isBlank()) {
				
				// Si los campos están rellenados se crea un objeto Usuario con ellos
				
				Usuario nuevo = new Usuario();
				nuevo.setNombre(ventanaLogin.getNombre().getText());
				nuevo.setContrasena(ventanaLogin.getContrasena().getText());
				try {
					
					// Se manda al servidor la instrucción de iniciar sesión junto al objeto Usuario
					
					salida.writeObject(1);
					salida.writeObject(nuevo);
					
					// Se recibe el objeto Usuario que el servidor ha obtenido de la BDD con los datos que se enviaron previamente
					
					nuevo = (Usuario) entrada.readObject();
						
					if (nuevo != null) {
						
						// Si el usuario recibido no es nulo significa que los datos eran correctos. Se informa de ello al usuario y se da paso a la siguiente ventana
						if(tipo.equals("normal"))
							JOptionPane.showMessageDialog(this.ventanaLogin.getFrame(),"Ha iniciado sesión", "Información", JOptionPane.INFORMATION_MESSAGE);
						Logeado ventanaLogeado = new Logeado();
						@SuppressWarnings("unused")
						ControladorLogeado controladorLogeado = new ControladorLogeado(ventanaLogeado, nuevo, entrada, salida);
						
					} else {
						
						// Si el usuario recibido es nulo significa que los datos enviados no eran correctos. Se informa de ello al usuario
						if(tipo.equals("normal"))
							JOptionPane.showMessageDialog(null,"Los datos introducidos no son correctos", "Información", JOptionPane.ERROR_MESSAGE);
						
					}
					
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			} else {
				
				// Si no se han rellenado los campos no se puede iniciar sesión. Se avisa al usuario de ello
				if(tipo.equals("normal"))
					JOptionPane.showMessageDialog(null,"Debe escribir un nombre y una contraseña para iniciar sesión", "Información", JOptionPane.ERROR_MESSAGE);
				
			}
			
			break;
			
		case "registrar":
			
//			Acción de realizar registro. Se da paso a la ventana de registro y se cierra la actual
			
			Registrar registrar = new Registrar();
			@SuppressWarnings("unused") Controlador_Registro controlador = new Controlador_Registro(registrar, this.entrada, this.salida);
			this.ventanaLogin.getFrame().dispose();

			break;
			
		case "restaurar":
			
//			Acción de restaurar contraseña. Se da paso a la ventana de restaurar contraseña y se cierra la actual
			
			RestaurarContrasena restaurar = new RestaurarContrasena();
			@SuppressWarnings("unused") ControladorRestaurarContrasena controladorRestaurar = new ControladorRestaurarContrasena(restaurar, this.entrada, this.salida);
			this.ventanaLogin.getFrame().dispose();
			
			break;
		default:
			break;
		}
		
		booleanPrueba = true;
		
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

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public boolean isBooleanPrueba() {
		return booleanPrueba;
	}

	public void setBooleanPrueba(boolean booleanPrueba) {
		this.booleanPrueba = booleanPrueba;
	}
	
}
