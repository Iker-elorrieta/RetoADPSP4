package test;

import static org.junit.Assert.*;

import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.hibernate.SessionFactory;

import hiberclass.Entornos;
import hiberclass.Entornosmuni;
import hiberclass.Estaciones;
import hiberclass.Horario;
import hiberclass.Informes;
import hiberclass.Municipios;
import main.HibernateUtil;
import main.InsertarBorrar;
import main.Principal;

/**
 * Clase para comprobar que el programa funciona correctamente.
 */
public class Test {
	
	Principal principal = new Principal();
	InsertarBorrar insertado = new InsertarBorrar();
	Entornos entorno = new Entornos();
	Municipios municipio = new Municipios();
	Horario horario = new Horario();
	Informes informe = new Informes();
	Estaciones estacion = new Estaciones();
	HibernateUtil hibernate = new HibernateUtil();
	
	//Valores de prueba
	String nombrePruebas = "prueba";
	double valor = 1;
	Calendar fecha = Calendar.getInstance(); // para pasar a date llamar al metodo toDate(Calendar)
	
	@SuppressWarnings("rawtypes")
	Set set = new HashSet(0);
	Entornosmuni lista = new Entornosmuni();
	
	@org.junit.Test
	public void testInsercionEstacion() {		
		estacion = new Estaciones(nombrePruebas, nombrePruebas);
		estacion = new Estaciones(nombrePruebas, nombrePruebas, nombrePruebas, valor, valor, valor, valor, set);
		estacion.toString();
		assertEquals(true, InsertarBorrar.insertar((Object)estacion));
		InsertarBorrar.borrar(estacion);
	}
	
	@org.junit.Test
	public void testInsercionMunicipio() {
		municipio = new Municipios(nombrePruebas);
		municipio = new Municipios(nombrePruebas, nombrePruebas, valor, valor, 1, set);
		assertEquals(true, InsertarBorrar.insertar((Object)municipio));
		InsertarBorrar.borrar(municipio);
	}
	
	@org.junit.Test
	public void testInsercionInformes() {
		
		estacion.setNombre(nombrePruebas);
		estacion.setDireccion(nombrePruebas);
		informe = new Informes(estacion, nombrePruebas);
		informe = new Informes(estacion, nombrePruebas, nombrePruebas, set);
		InsertarBorrar.insertar((Object)estacion);
		assertEquals(true, InsertarBorrar.insertar((Object)informe));
		InsertarBorrar.borrar(informe);
		InsertarBorrar.borrar(estacion);
	}
	
	@org.junit.Test
	public void testInsercionEntorno() {
		
		entorno = new Entornos(nombrePruebas);
		entorno = new Entornos(nombrePruebas, nombrePruebas, nombrePruebas, nombrePruebas, valor, valor, set);
		assertEquals(true, InsertarBorrar.insertar((Object)entorno));
		InsertarBorrar.borrar(entorno);
	}
	
	@org.junit.Test
    public void testInsercionEntornosMuni() {

        Set set = new HashSet(0);
        municipio = new Municipios(nombrePruebas, nombrePruebas, valor, valor, 1, set);
        municipio = new Municipios();
        municipio = new Municipios(nombrePruebas);
        municipio.setNombre(nombrePruebas);
        municipio.setDescripcion(nombrePruebas);
        municipio.setLatitud(Double.valueOf("1"));
        municipio.setLongitud(Double.valueOf("1"));
        municipio.setCodigo(1);

        InsertarBorrar.insertar((Object)municipio);

        entorno = new Entornos();
        entorno.setNombre(nombrePruebas);
        entorno.setDescripcion(nombrePruebas);
        entorno.setTipo(nombrePruebas);
        entorno.setTerritorio(nombrePruebas);
        entorno.setLatitud((double) 10);
        entorno.setLongitud((double) 20);
        entorno.setEntornosmunis(set);
        entorno = new Entornos(nombrePruebas,nombrePruebas,nombrePruebas, nombrePruebas, null, null, set);
        InsertarBorrar.insertar((Object)entorno);

        lista.setMunicipios(municipio);
        lista.setEntornos(entorno);
        lista.setId(0);
        lista = new Entornosmuni(entorno,municipio);

        assertEquals(true, InsertarBorrar.insertar((Object)lista));
        InsertarBorrar.borrar(lista);
        InsertarBorrar.borrar(municipio);
        InsertarBorrar.borrar(entorno);
    }
	
	@org.junit.Test
    public void testInsercionHorarios() {

        estacion = new Estaciones(nombrePruebas, nombrePruebas, nombrePruebas, valor, valor, valor, valor, set);
        informe = new Informes(estacion, nombrePruebas, nombrePruebas, set);

        horario.setFecha(toDate(fecha));
        horario.setHora("10:00");
        horario.setInformes(informe);
        horario.setNo2gm3(0.23);
        horario.setNogm3(0.22);
        horario.setNoxgm3(0.55);
        horario.setPm10gm3(1.23);

        horario = new Horario(informe,toDate(fecha),"10:00",valor,valor,valor,valor);

        InsertarBorrar.insertar((Object)estacion);
        InsertarBorrar.insertar((Object)informe);
        assertEquals(true, InsertarBorrar.insertar(horario));
        InsertarBorrar.borrar(horario);
        InsertarBorrar.borrar(informe);
        InsertarBorrar.borrar(estacion);
    }

    public Date toDate(Calendar calendar)
    {
        Date result;
        result = calendar.getTime();
        return result;
    }
}
