package test;

import static org.junit.Assert.*;

import java.awt.event.MouseEvent;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.fasterxml.jackson.databind.ObjectMapper;

import Server.Servidor;
import Server.VentanaServidor;
import controlador.Controlador_Login;
import main.Principal;
import modelo.Entornos;
import modelo.Entornosmuni;
import modelo.EntornosmuniId;
import modelo.Estaciones;
import modelo.HibernateUtil;
import modelo.Horario;
import modelo.Informes;
import modelo.InsertarBorrar;
import modelo.Municipios;
import modelo.Usuario;
import vista.Logeado;
import vista.Login;
import vista.Registrar;
import modelo.Json;

/**
 * Clase para comprobar que el programa funciona correctamente.
 */
public class Test {
	
	private Json principal = new Json();
	private InsertarBorrar insertado = new InsertarBorrar();
	private Entornos entorno = new Entornos();
	private Municipios municipio = new Municipios();
	private Horario horario = new Horario();
	private Informes informe = new Informes();
	private Estaciones estacion = new Estaciones();
	private Entornosmuni entornosmuni = new Entornosmuni();
	private EntornosmuniId entornosmuniId = new EntornosmuniId();
	private HibernateUtil hibernate = new HibernateUtil();
	SessionFactory sesion = HibernateUtil.getSessionFactory();
	Session session = sesion.openSession();
	private Login login = new Login();
	private Registrar registrar = new Registrar();
	private Logeado logeado = new Logeado();
	
	//Valores de prueba
	String nombrePruebas = "prueba";
	Double valor = (double) 1;
	Calendar fecha = Calendar.getInstance(); // para pasar a date llamar al metodo toDate(Calendar)
	
	@SuppressWarnings("rawtypes")
	Set set = new HashSet(0);
	
	@org.junit.Test
	public void testInsercionMunicipio() {
		municipio = new Municipios(nombrePruebas);
		municipio = new Municipios(nombrePruebas, nombrePruebas, valor, valor, "1", set, set);
		municipio.toString();
		municipio.isNull();
		assertEquals(true, InsertarBorrar.insertar(municipio, sesion, session));
		InsertarBorrar.borrar(municipio, sesion, session);
	}
	
	@org.junit.Test
	public void testInsercionEntorno() {
		entorno = new Entornos(nombrePruebas);
		entorno = new Entornos(nombrePruebas, nombrePruebas, nombrePruebas, nombrePruebas, valor, valor, set);
		entorno.toString();
		entorno.isNull();
		assertEquals(true, InsertarBorrar.insertar(entorno, sesion, session));
		InsertarBorrar.borrar(entorno, sesion, session);
	}
	
//	@org.junit.Test
//    public void testInsercionHorarios() {
//		municipio = new Municipios(nombrePruebas, nombrePruebas, valor, valor, "1", set, set);
//		estacion = new Estaciones(municipio, nombrePruebas, nombrePruebas, nombrePruebas, valor, valor, valor, valor, set);
//		informe = new Informes(estacion, nombrePruebas, nombrePruebas, set);
//		InsertarBorrar.insertar(municipio, sesion, session);
//		InsertarBorrar.insertar(estacion, sesion, session);
//		InsertarBorrar.insertar(informe, sesion, session);
//		
//        horario.setFecha(toDate(fecha));
//        horario.setHora("10:00");
//        horario.setInformes(informe);
//        horario.setComgm3(valor);
//        horario.setNogm3(0.22);
//        horario.setNoxgm3(0.55);
//        horario.setNo2(valor);
//        horario.setNo2ica(nombrePruebas);
//        horario.setPm10(valor);
//        horario.setPm10ica(nombrePruebas);
//        horario.setPm25(valor);
//        horario.setPm25ica(nombrePruebas);
//        horario.setSo2(valor);
//        horario.setSo2ica(nombrePruebas);
//        horario.setIcaestacion(nombrePruebas);
//
//        horario.toString();
//        horario.isNull();
//        
//        horario = new Horario(informe,toDate(fecha),"10:00",valor, valor, valor, nombrePruebas, valor, valor, nombrePruebas, valor, nombrePruebas, valor, nombrePruebas, nombrePruebas);
//        
//        assertEquals(true, InsertarBorrar.insertar(horario, sesion, session));
//        InsertarBorrar.borrar(municipio, sesion, session);
//    }
//	
//	@org.junit.Test
//    public void testInsercionEntornosMuni() {
//		municipio = new Municipios(nombrePruebas, nombrePruebas, valor, valor, "1", set, set);
//		entorno = new Entornos(nombrePruebas, nombrePruebas, nombrePruebas, nombrePruebas, valor, valor, set);
//		InsertarBorrar.insertar(municipio, sesion, session);
//		InsertarBorrar.insertar(entorno, sesion, session);
//		
//		entorno.setId(InsertarBorrar.obtenerEntornoId(entorno, sesion, session));
//		municipio.setId(InsertarBorrar.obtenerMunicipioId(municipio, sesion, session));
//		entornosmuniId = new EntornosmuniId(entorno.getId(), municipio.getId());
//		entornosmuniId.setEntorno(entorno.getId());
//		entornosmuniId.setMunicipio(municipio.getId());
//		entornosmuniId.equals("a");
//		entornosmuniId.equals(null);
//		entornosmuniId.equals(entornosmuniId);
//		entornosmuniId.hashCode();
//		entornosmuni.setEntornos(entorno);
//		entornosmuni.setMunicipios(municipio);
//		entornosmuni = new Entornosmuni(entornosmuniId, entorno, municipio);
//		assertEquals(true, InsertarBorrar.insertar(entornosmuni, sesion, session));
//		InsertarBorrar.borrar(entorno, sesion, session);
//		InsertarBorrar.borrar(municipio, sesion, session);
//	}
//	
//	@org.junit.Test
//	public void testInsercionInformes() {
//		municipio = new Municipios(nombrePruebas, nombrePruebas, valor, valor, "1", set, set);
//		InsertarBorrar.insertar(municipio, sesion, session);
//		estacion = new Estaciones(municipio, nombrePruebas, nombrePruebas, nombrePruebas, valor, valor, valor, valor, set);
//		InsertarBorrar.insertar(estacion, sesion, session);
//		informe = new Informes(estacion, nombrePruebas);
//		informe = new Informes(estacion, nombrePruebas, nombrePruebas, set);
//		informe.toString();
//		assertEquals(true, InsertarBorrar.insertar(informe, sesion, session));
//		InsertarBorrar.borrar(municipio, sesion, session);
//	}
//	
//	@org.junit.Test
//	public void testInsercionEstacion() {
//		municipio = new Municipios(nombrePruebas, nombrePruebas, valor, valor, "1", set, set);
//		InsertarBorrar.insertar(municipio, sesion, session);
//		estacion = new Estaciones(municipio, nombrePruebas, nombrePruebas);
//		estacion = new Estaciones(municipio, nombrePruebas, nombrePruebas, nombrePruebas, valor, valor, valor, valor, set);
//		assertEquals(true, InsertarBorrar.insertar(estacion, sesion, session));
//		InsertarBorrar.borrar(municipio, sesion, session);
//		estacion.toString();
//		estacion.isNull();
//	}
//	
//	//Json
//	
//	@org.junit.Test
//	@SuppressWarnings({ "unchecked" })
//	public void testJsonTotal()
//	{
//		Calendar tiempo1 = Calendar.getInstance();
//		//Hay que borrar todos los datos de la BDD para hacer esta prueba
//
//		Json hijoDeJ = new Json();
//		ArrayList<Object> lista = hijoDeJ.cargarJsons();
//		assertEquals(true,hijoDeJ.cargarTodosLosDatos((ArrayList<Informes>) lista.get(0),(ObjectMapper) lista.get(1),(Municipios[]) lista.get(2),(Entornos[]) lista.get(3),(Estaciones[]) lista.get(4), (Informes[]) lista.get(5), sesion, session));
//		Calendar tiempo2 = Calendar.getInstance();
//		System.out.println(Duration.between(tiempo1.toInstant(), tiempo2.toInstant()));
//	}
	
	//Principal
	
	@org.junit.Test
	public void testPrincipal() {
		Servidor server = new Servidor(new JTextArea(), new JTextField(), new JLabel());
		server.start();
		Principal main = new Principal();
		main.main(null);
		assertEquals(true,main.start());
		server.desconectar();
	}
	
	//Server
	
	@org.junit.Test
	public void testServidor()
	{
		Servidor server = new Servidor(new JTextArea(), new JTextField(), new JLabel());
		server.start();
		try {
			Socket socket = new Socket("127.0.0.1",44444);
			ObjectInputStream entrada = new ObjectInputStream(socket.getInputStream());
			ObjectOutputStream salida = new ObjectOutputStream(socket.getOutputStream());
			Usuario nuevo = new Usuario();
			nuevo.setUsuario("prueba");
			nuevo.setContrasena("prueba");
			nuevo.setEMail("a@b.c");
			salida.writeObject(2);
			salida.writeObject(nuevo);
			salida.writeObject(1);
			salida.writeObject(nuevo);
			InsertarBorrar.borrar(nuevo, sesion, session);
		} catch (IOException e) {
			
		}
		
		assertEquals(true,server.desconectar());
	}
	
	@org.junit.Test
	public void testControlador() {
		VentanaServidor.main(null);
		ObjectOutputStream salida;
		ObjectInputStream entrada;
		try {
			Socket socket = new Socket("127.0.0.1",44444);
			entrada = new ObjectInputStream(socket.getInputStream());
			salida = new ObjectOutputStream(socket.getOutputStream());
			Login login = new Login();
			Controlador_Login controlador = new Controlador_Login(login, entrada, salida);
			MouseEvent e = new MouseEvent(this.login.getBotonIniciar(), 0, 0, 0, 0, 0, 0, false);
			e.getComponent().setName("entrar");
			controlador.mousePressed(e);
		} catch (IOException e) {
		}
	
	}
	
    public Date toDate(Calendar calendar)
    {
        Date result;
        result = calendar.getTime();
        return result;
    }
    
    
}