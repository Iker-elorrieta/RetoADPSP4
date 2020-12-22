package test;

import static org.junit.Assert.*;

import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import hiberclass.Entornos;
import hiberclass.Entornosmuni;
import hiberclass.Estaciones;
import hiberclass.Horario;
import hiberclass.Informes;
import hiberclass.Municipios;
import main.InsertarBorrar;
import main.Principal;

public class Test {
	
	Principal principal = new Principal();
	InsertarBorrar insertado = new InsertarBorrar();
	Entornos entorno = new Entornos();
	Municipios municipio = new Municipios();
	Horario horario = new Horario();
	Informes informe = new Informes();
	Estaciones estacion = new Estaciones();
	Entornosmuni lista = new Entornosmuni();

	@SuppressWarnings("rawtypes")
	Set set = new HashSet(0);
	Entornosmuni lista = new Entornosmuni();
	
	@org.junit.Test
	public void testInsercionEstacion() {
		estacion = new Estaciones("prueba", "prueba");
		estacion = new Estaciones("prueba", "prueba", "prueba", Double.valueOf("1"), Double.valueOf("1"), Double.valueOf("1"), Double.valueOf("1"), set);
		assertEquals(true, InsertarBorrar.insertar((Object)estacion));
		InsertarBorrar.borrar(estacion);
	}
	
	@org.junit.Test
	public void testInsercionMunicipio() {
		municipio = new Municipios("prueba");
		municipio = new Municipios("prueba", "prueba", Double.valueOf("1"), Double.valueOf("1"), 1, set);
		assertEquals(true, InsertarBorrar.insertar((Object)municipio));
		InsertarBorrar.borrar(municipio);
	}
	
	@org.junit.Test
	public void testInsercionInformes() {
		
		estacion.setNombre("prueba");
		estacion.setDireccion("prueba");
		informe = new Informes(estacion, "prueba");
		informe = new Informes(estacion, "prueba", "prueba", set);
		InsertarBorrar.insertar((Object)estacion);
		assertEquals(true, InsertarBorrar.insertar((Object)informe));
		InsertarBorrar.borrar(informe);
		InsertarBorrar.borrar(estacion);
	}
	
	@org.junit.Test
	public void testInsercionEntorno() {
		
		entorno = new Entornos("prueba");
		entorno = new Entornos("prueba", "prueba", "prueba", "prueba", Double.valueOf("1"), Double.valueOf("1"), set);
		assertEquals(true, InsertarBorrar.insertar((Object)entorno));
		InsertarBorrar.borrar(entorno);
	}
	
	@org.junit.Test
    public void testInsercionEntornosMuni() {

        Set set = new HashSet(0);
        municipio = new Municipios("prueba", "prueba", Double.valueOf("1"), Double.valueOf("1"), 1, set);
        municipio = new Municipios();
        municipio = new Municipios("prueba");
        municipio.setNombre("prueba");
        municipio.setDescripcion("prueba");
        municipio.setLatitud(Double.valueOf("1"));
        municipio.setLongitud(Double.valueOf("1"));
        municipio.setCodigo(1);

        InsertarBorrar.insertar((Object)municipio);

        entorno = new Entornos();
        entorno.setNombre("prueba");
        entorno.setDescripcion("prueba");
        entorno.setTipo("prueba");
        entorno.setTerritorio("prueba");
        entorno.setLatitud((double) 10);
        entorno.setLongitud((double) 20);
        entorno.setEntornosmunis(set);
        entorno = new Entornos("prueba","prueba","prueba", "prueba", null, null, set);
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

        estacion = new Estaciones("prueba", "prueba", "prueba", Double.valueOf("1"), Double.valueOf("1"), Double.valueOf("1"), Double.valueOf("1"), set);
        informe = new Informes(estacion, "prueba", "prueba", set);

        Calendar fecha = Calendar.getInstance();
        horario.setFecha(toDate(fecha));
        horario.setHora("10:00");
        horario.setInformes(informe);
        horario.setNo2gm3(0.23);
        horario.setNogm3(0.22);
        horario.setNoxgm3(0.55);
        horario.setPm10gm3(1.23);

        horario = new Horario(informe,toDate(fecha),"10:00",0.23,0.22,0.44,1.33);

        InsertarBorrar.insertar((Object)estacion);
        InsertarBorrar.insertar((Object)informe);
        assertEquals(true, InsertarBorrar.insertar(horario));
        InsertarBorrar.borrar(horario);
        InsertarBorrar.borrar(informe);
        InsertarBorrar.borrar(estacion);

    }

//    public Calendar toCalendar(Date date){ 
//          Calendar cal = Calendar.getInstance();
//          cal.setTime(date);
//          return cal;
//    }

    public Date toDate(Calendar calendar)
    {
        Date result;
        result = calendar.getTime();
        return result;
    }

	@org.junit.Test
	public void testInsercionEntornosMuni() {
		
		Set set = new HashSet(0);
		municipio = new Municipios("prueba", "prueba", Double.valueOf("1"), Double.valueOf("1"), 1, set);
		municipio = new Municipios();
		municipio = new Municipios("prueba");
		municipio.setNombre("prueba");
		municipio.setDescripcion("prueba");
		municipio.setLatitud(Double.valueOf("1"));
		municipio.setLongitud(Double.valueOf("1"));
		municipio.setCodigo(1);
		
		InsertarBorrar.insertar((Object)municipio);
		
		entorno = new Entornos();
		entorno.setNombre("prueba");
		entorno.setDescripcion("prueba");
		entorno.setTipo("prueba");
		entorno.setTerritorio("prueba");
		entorno.setLatitud((double) 10);
		entorno.setLongitud((double) 20);
		entorno.setEntornosmunis(set);
		entorno = new Entornos("prueba","prueba","prueba", "prueba", null, null, set);
		InsertarBorrar.insertar((Object)entorno);
		
		lista.setMunicipios(municipio);
		lista.setEntornos(entorno);
		lista.setId(0);
		lista = new Entornosmuni(entorno,municipio);
		
		assertEquals(true, InsertarBorrar.insertar((Object)lista));
		InsertarBorrar.borrar(lista);
		InsertarBorrar.borrar(municipio);
	}
}
