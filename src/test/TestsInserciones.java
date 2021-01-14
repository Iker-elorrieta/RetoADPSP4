package test;

import static org.junit.Assert.*;

import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import controlador.Controlador_Login;
import controlador.Controlador_Registro;
import modelo.Entornos;
import modelo.Entornosmuni;
import modelo.EntornosmuniId;
import modelo.Estaciones;
import modelo.HibernateUtil;
import modelo.Horario;
import modelo.Informes;
import modelo.InsertarBorrar;
import modelo.Json;
import modelo.Municipios;
import modelo.Usuario;
import vista.Logeado;
import vista.Login;
import vista.Registrar;


/**
 * Clase para comprobar que el programa funciona correctamente.
 */
public class TestsInserciones {
	
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
	private Login login = new Login();
	private Registrar registrar = new Registrar();
	private Logeado logeado = new Logeado();
	private Controlador_Login controladorLogin;
	private Controlador_Registro controladorRegistro;
	SessionFactory sesion = HibernateUtil.getSessionFactory();
	Session session = sesion.openSession();
	
	//Valores de prueba
	String nombrePruebas = "prueba";
	Double valor = (double) 1;
	Calendar fecha = Calendar.getInstance(); // para pasar a date llamar al metodo toDate(Calendar)
	String email = "a@b.c";
	String contrasena = "preuba";
	
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
	
	@org.junit.Test
    public void testInsercionHorarios() {
		municipio = new Municipios(nombrePruebas, nombrePruebas, valor, valor, "1", set, set);
		estacion = new Estaciones(municipio, nombrePruebas, nombrePruebas, nombrePruebas, valor, valor, valor, valor, set);
		informe = new Informes(estacion, nombrePruebas, nombrePruebas, set);
		InsertarBorrar.insertar(municipio, sesion, session);
		InsertarBorrar.insertar(estacion, sesion, session);
		InsertarBorrar.insertar(informe, sesion, session);
		
        horario.setFecha(toDate(fecha));
        horario.setFecha("10/05/2021");
        horario.setFecha("algo :v");
        horario.setInformes(informe);
        horario.setComgm3(null);
        horario.setNogm3(null);
        horario.setNoxgm3(null);
        horario.setNo2(null);
        horario.setNo2ica(null);
        horario.setPm10(null);
        horario.setPm10ica(null);
        horario.setPm25(null);
        horario.setPm25ica(null);
        horario.setSo2(null);
        horario.setSo2ica(null);
        horario.setIcaestacion(null);
        horario.setValor1("0,1");
        horario.setValor2("0,1");
        horario.setValor3("0,1");
        horario.setValor4("0,1");
        horario.setValor5("0,1");
        horario.setValor6("0,1");
        horario.setValor7("0,1");
        horario.setValor1("a");
        horario.setValor2("b");
        horario.setValor3("c");
        horario.setValor4("d");
        horario.setValor5("e");
        horario.setValor6("f");
        horario.setValor7("g");
        
        horario.setHora("10:00");

        horario.toString();
        horario.isNull();
        
        horario = new Horario(informe,toDate(fecha),"10:00",valor, valor, valor, nombrePruebas, valor, valor, nombrePruebas, valor, nombrePruebas, valor, nombrePruebas, nombrePruebas);
        
        assertEquals(true, InsertarBorrar.insertar(horario, sesion, session));
        InsertarBorrar.borrar(municipio, sesion, session);
    }
	
	@org.junit.Test
    public void testInsercionEntornosMuni() {
		municipio = new Municipios(nombrePruebas, nombrePruebas, valor, valor, "1", set, set);
		entorno = new Entornos(nombrePruebas, nombrePruebas, nombrePruebas, nombrePruebas, valor, valor, set);
		InsertarBorrar.insertar(municipio, sesion, session);
		InsertarBorrar.insertar(entorno, sesion, session);
		
		entorno.setId(InsertarBorrar.obtenerEntornoId(entorno, sesion, session));
		municipio.setId(InsertarBorrar.obtenerMunicipioId(municipio, sesion, session));
		entornosmuniId = new EntornosmuniId(entorno.getId(), municipio.getId());
		entornosmuniId.setEntorno(entorno.getId());
		entornosmuniId.setMunicipio(municipio.getId());
		entornosmuniId.equals("a");
		entornosmuniId.equals(null);
		entornosmuniId.equals(entornosmuniId);
		entornosmuniId.hashCode();
		entornosmuni.setEntornos(entorno);
		entornosmuni.setMunicipios(municipio);
		entornosmuni = new Entornosmuni(entornosmuniId, entorno, municipio);
		assertEquals(true, InsertarBorrar.insertar(entornosmuni, sesion, session));
		InsertarBorrar.borrar(entorno, sesion, session);
		InsertarBorrar.borrar(municipio, sesion, session);
	}
	
	@org.junit.Test
	public void testInsercionInformes() {
		municipio = new Municipios(nombrePruebas, nombrePruebas, valor, valor, "1", set, set);
		InsertarBorrar.insertar(municipio, sesion, session);
		estacion = new Estaciones(municipio, nombrePruebas, nombrePruebas, nombrePruebas, valor, valor, valor, valor, set);
		InsertarBorrar.insertar(estacion, sesion, session);
		informe = new Informes(estacion, nombrePruebas);
		informe = new Informes(estacion, nombrePruebas, nombrePruebas, set);
		informe.toString();
		assertEquals(true, InsertarBorrar.insertar(informe, sesion, session));
		InsertarBorrar.borrar(municipio, sesion, session);
	}
	
	@org.junit.Test
	public void testInsercionEstacion() {
		municipio = new Municipios(nombrePruebas, nombrePruebas, valor, valor, "1", set, set);
		InsertarBorrar.insertar(municipio, sesion, session);
		estacion = new Estaciones(municipio, nombrePruebas, nombrePruebas);
		estacion = new Estaciones(municipio, nombrePruebas, nombrePruebas, nombrePruebas, valor, valor, valor, valor, set);
		assertEquals(true, InsertarBorrar.insertar(estacion, sesion, session));
		InsertarBorrar.borrar(municipio, sesion, session);
		estacion.toString();
		estacion.isNull();
	}
	
	@org.junit.Test
	public void testInsercionUsuario() {
		Usuario user = new Usuario();
		
		user.setUsuario(nombrePruebas);
		user.setEMail(email);
		user.setContrasena(contrasena);
		
		user = new Usuario(nombrePruebas,email,contrasena);
		assertEquals(true, InsertarBorrar.insertar(user, sesion, session));
		InsertarBorrar.borrar(user, sesion, session);
	}
	
    public Date toDate(Calendar calendar)
    {
        Date result;
        result = calendar.getTime();
        return result;
    }
    
    
}