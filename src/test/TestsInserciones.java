package test;

import static org.junit.Assert.*;

import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import comunes.InsertarBorrar;
import comunes.Json;
import controlador.Controlador_Login;
import controlador.Controlador_Registro;
import modelo.Entornos;
import modelo.Entornosmuni;
import modelo.EntornosmuniId;
import modelo.Estaciones;
import modelo.HibernateUtil;
import modelo.Horario;
import modelo.Informes;
import modelo.Municipios;
import modelo.Provincias;
import modelo.Usuario;
import vista.Logeado;
import vista.Login;
import vista.Registrar;


/**
 * Clase para comprobar que el programa inserta objectos correctamente.
 */
public class TestsInserciones {
																	@SuppressWarnings("unused")
	private Json principal = new Json();							@SuppressWarnings("unused")
	private HibernateUtil hibernate = new HibernateUtil();			@SuppressWarnings("unused")
	private Login login = new Login();								@SuppressWarnings("unused")
	private Registrar registrar = new Registrar();					@SuppressWarnings("unused")
	private Logeado logeado = new Logeado();						@SuppressWarnings("unused")
	private Controlador_Login controladorLogin;						@SuppressWarnings("unused")
	private Controlador_Registro controladorRegistro;				@SuppressWarnings("unused")
	private InsertarBorrar insertado = new InsertarBorrar();
	private Provincias provincia = new Provincias();
	private EntornosmuniId entornosmuniId = new EntornosmuniId();
	private Entornos entorno = new Entornos();						
	private Municipios municipio = new Municipios();				
	private Horario horario = new Horario();
	private Informes informe = new Informes();						
	private Estaciones estacion = new Estaciones();					
	private Entornosmuni entornosmuni = new Entornosmuni();			
	SessionFactory sesion = HibernateUtil.getSessionFactory();
	Session session = sesion.openSession();
	
	//Valores de prueba
	String nombrePruebas = "prueba";
	Double valor = (double) 1;
	String valorString = "0,1";
	Calendar fecha = Calendar.getInstance(); // para pasar a date llamar al metodo toDate(Calendar)
	String email = "a@b.c";
	String contrasena = "preuba";
	
	@SuppressWarnings("rawtypes")
	Set set = new HashSet(0);
	
	@org.junit.Test
	public void testInsercionProvincia(){
		provincia = new Provincias(nombrePruebas);
		assertEquals(true, InsertarBorrar.insertar(provincia, sesion, session));
		InsertarBorrar.borrar(provincia, sesion, session);
	}
	
	@org.junit.Test
	public void testInsercionMunicipio(){
		municipio.setLatitud(valorString);
		municipio.setLongitud(valorString);
		municipio.setLatitud("a");
		municipio.setLongitud("b");
		provincia = new Provincias(nombrePruebas);
		municipio = new Municipios(provincia,nombrePruebas);
		InsertarBorrar.insertar(provincia, sesion, session);
		municipio = new Municipios(provincia, nombrePruebas, nombrePruebas, valor, valor, "1", set, set);
		municipio.toString();
		municipio.isNull();
		assertEquals(true, InsertarBorrar.insertar(municipio, sesion, session));
		InsertarBorrar.borrar(municipio, sesion, session);
		InsertarBorrar.borrar(provincia, sesion, session);
	}
	
	@org.junit.Test
	public void testInsercionEntorno() {
		entorno.getMunicipio();
		entorno.setMunicipio("algo");
		entorno.setLatitud(valorString);
		entorno.setLongitud(valorString);
		entorno.setLatitud("a");
		entorno.setLongitud("b");
		entorno = new Entornos(nombrePruebas);
		entorno = new Entornos(nombrePruebas, nombrePruebas, nombrePruebas, nombrePruebas, valor, valor, set);
		entorno.toString();
		entorno.isNull();
		assertEquals(true, InsertarBorrar.insertar(entorno, sesion, session));
		InsertarBorrar.borrar(entorno, sesion, session);
	}
	
	@org.junit.Test
    public void testInsercionHorarios() {
		provincia = new Provincias(nombrePruebas);
		municipio = new Municipios(provincia, nombrePruebas, nombrePruebas, valor, valor, "1", set, set);
		estacion = new Estaciones(municipio, nombrePruebas, nombrePruebas, valor, valor, valor, valor, set);
		informe = new Informes(estacion, nombrePruebas, nombrePruebas, set);
		InsertarBorrar.insertar(provincia, sesion, session);
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
        horario.setValor1(valorString);
        horario.setValor2(valorString);
        horario.setValor3(valorString);
        horario.setValor4(valorString);
        horario.setValor5(valorString);
        horario.setValor6(valorString);
        horario.setValor7(valorString);
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
        InsertarBorrar.borrar(provincia, sesion, session);
    }
	
	@SuppressWarnings("unlikely-arg-type")
	@org.junit.Test
    public void testInsercionEntornosMuni() {
		provincia = new Provincias(nombrePruebas);
		municipio = new Municipios(provincia, nombrePruebas, nombrePruebas, valor, valor, "1", set, set);
		entorno = new Entornos(nombrePruebas, nombrePruebas, nombrePruebas, nombrePruebas, valor, valor, set);
		InsertarBorrar.insertar(provincia, sesion, session);
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
		InsertarBorrar.borrar(provincia, sesion, session);
	}
	
	@org.junit.Test
	public void testInsercionInformes() {
		provincia = new Provincias(nombrePruebas);
		municipio = new Municipios(provincia, nombrePruebas, nombrePruebas, valor, valor, "1", set, set);
		InsertarBorrar.insertar(provincia, sesion, session);
		InsertarBorrar.insertar(municipio, sesion, session);
		estacion = new Estaciones(municipio, nombrePruebas, nombrePruebas, valor, valor, valor, valor, set);
		InsertarBorrar.insertar(estacion, sesion, session);
		informe = new Informes(estacion, nombrePruebas);
		informe = new Informes(estacion, nombrePruebas, nombrePruebas, set);
		informe.toString();
		assertEquals(true, InsertarBorrar.insertar(informe, sesion, session));
		InsertarBorrar.borrar(municipio, sesion, session);
		InsertarBorrar.borrar(provincia, sesion, session);
	}
	
	@org.junit.Test
	public void testInsercionEstacion() {
		provincia = new Provincias(nombrePruebas);
		municipio = new Municipios(provincia, nombrePruebas, nombrePruebas, valor, valor, "1", set, set);
		InsertarBorrar.insertar(provincia, sesion, session);
		InsertarBorrar.insertar(municipio, sesion, session);
		estacion.setCoordenadaX(valorString);
		estacion.setCoordenadaY(valorString);
		estacion.setLatitud(valorString);
		estacion.setLongitud(valorString);
		estacion.setMunicipio("algo");
		estacion.getMunicipio();
		estacion = new Estaciones(municipio, nombrePruebas, nombrePruebas);
		estacion = new Estaciones(municipio, nombrePruebas, nombrePruebas, valor, valor, valor, valor, set);
		assertEquals(true, InsertarBorrar.insertar(estacion, sesion, session));
		InsertarBorrar.borrar(municipio, sesion, session);
		InsertarBorrar.borrar(provincia, sesion, session);
		estacion.toString();
		estacion.isNull();
	}
	
	@org.junit.Test
	public void testInsercionUsuario() {
		Usuario user = new Usuario();
		
		user.setNombre(nombrePruebas);
		user.setContrasena(contrasena);
		
		user = new Usuario(nombrePruebas,email,contrasena, contrasena);
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