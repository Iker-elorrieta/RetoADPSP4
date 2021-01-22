package comunes;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.nio.charset.Charset;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.regex.PatternSyntaxException;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.exception.ConstraintViolationException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import modelo.Entornos;
import modelo.Entornosmuni;
import modelo.EntornosmuniId;
import modelo.Estaciones;
import modelo.Horario;
import modelo.Informes;
import modelo.Municipios;
import modelo.Provincias;

public class Json {
	
	/**
	 * Metodo principal de sacar todos los datos de los json
	 * Tambien es la clase donde se crean los ficheros de json de cada url menos los horarios
	 * para sacar lo requierido de los horarios es el lista.get(0) de lo que devuelve este metodo.
	 * @return este metodo devuelve un arraylist con los datos necesarios que se requiere en el metodo cargarTodosLosDatos en el orden correspondiente.
	 */
	public ArrayList<Object> cargarJsons()
	{
		Entornos[] listaEspaciosNaturales = null;
		Municipios[] listaMunicipios = null;
		@SuppressWarnings("unused")
		ArrayList<Informes> paginasNoEncontrada = new ArrayList<Informes>();
		
		//Comprobar los certificados de la pagina
		comprobarPagina("https://opendata.euskadi.eus/contenidos/ds_informes_estudios/calidad_aire_2021/es_def/adjuntos/index.json");
		
		ObjectMapper mapper = new ObjectMapper();
		
		//Esta linea sirve para ignorar los elementos que encuentra y que el objeto no tenga
		mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
		Informes[] horariosEstaciones = null;
		try {
			horariosEstaciones = mapper.readValue(readJsonDesdeUrl("https://opendata.euskadi.eus/contenidos/ds_informes_estudios/calidad_aire_2021/es_def/adjuntos/index.json"), Informes[].class);
		} catch (JsonMappingException e2) {
//				e2.printStackTrace();
		} catch (JsonProcessingException e2) {
//				e2.printStackTrace();
		} catch (IOException e2) {
//				e2.printStackTrace();
		}
		//Cargamos los arrays con todos los datos de los json
		Estaciones[] listaEstaciones = null;
		try {
			listaEstaciones = mapper.readValue(readJsonDesdeUrl("https://opendata.euskadi.eus/contenidos/ds_informes_estudios/calidad_aire_2020/es_def/adjuntos/estaciones.json"), Estaciones[].class);
			listaMunicipios = mapper.readValue(readJsonDesdeUrl("https://opendata.euskadi.eus/contenidos/ds_recursos_turisticos/pueblos_euskadi_turismo/opendata/pueblos.json"), Municipios[].class);
			listaEspaciosNaturales = mapper.readValue(readJsonDesdeUrl("https://opendata.euskadi.eus/contenidos/ds_recursos_turisticos/playas_de_euskadi/opendata/espacios-naturales.json"), Entornos[].class);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		ArrayList<Object> result = new ArrayList<Object>();
		result.add(cargarHorarios(horariosEstaciones, mapper).get(1));
		result.add(mapper);
		result.add(listaMunicipios);
		result.add(listaEspaciosNaturales);
		result.add(listaEstaciones);
		result.add(horariosEstaciones);
		return result;
	}
	/**
	 * En este metodo se inserta dodos los datos en la base de datos menos los horarios.
	 * @param paginasNoEncontrada
	 * @param mapper
	 * @param listaMunicipios
	 * @param listaEspaciosNaturales
	 * @param listaEstaciones
	 * @param horariosEstaciones
	 * @param sesion
	 * @param session
	 */
	public boolean cargarTodosLosDatos(ArrayList<Informes> paginasNoEncontrada,
									  ObjectMapper mapper, Municipios[] listaMunicipios, 
									  Entornos[] listaEspaciosNaturales,Estaciones[] listaEstaciones, 
									  Informes[] horariosEstaciones, SessionFactory sesion,Session session)
	{
		ArrayList<Municipios> comprobarMunicipios = new ArrayList<Municipios>();
		try{
			//Insertartamos todos los municipios
			for(int i = 0 ; i < listaMunicipios.length ; i++)
		{
				String nombreProvincia;
				try {
					nombreProvincia = listaMunicipios[i].getProvincia().substring(0,listaMunicipios[i].getProvincia().indexOf(" "));
				}catch(Exception b) {
					nombreProvincia = listaMunicipios[i].getProvincia();
				}
				Provincias provincia = new Provincias(nombreProvincia);
				String hql1 = "from Provincias where lower(nombre) = '"+nombreProvincia+"'";
				Query q1 = session.createQuery(hql1);
				provincia = (Provincias) q1.uniqueResult();

				if (provincia == null){
					provincia = new Provincias(nombreProvincia); 
					InsertarBorrar.insertar(provincia,sesion,session);
					hql1 = "from Provincias where lower(nombre) = '"+nombreProvincia+"'";
					q1 = session.createQuery(hql1);
					provincia = (Provincias) q1.uniqueResult();
					listaMunicipios[i].setProvincias(provincia);
					InsertarBorrar.insertar(listaMunicipios[i],sesion,session);
					
						String[] municipiosRaros = listaMunicipios[i].getNombreentero().split(" ");
						
						if(municipiosRaros.length > 1 && !municipiosRaros[0].equals(municipiosRaros[1]))
							comprobarMunicipios.add(listaMunicipios[i]);
				}else {
					listaMunicipios[i].setProvincias(provincia);
					InsertarBorrar.insertar(listaMunicipios[i],sesion,session);

						String[] municipiosRaros = listaMunicipios[i].getNombreentero().split(" ");
						
						if(municipiosRaros.length > 1 && !municipiosRaros[0].equals(municipiosRaros[1]))
							comprobarMunicipios.add(listaMunicipios[i]);
				}
			}
			
			//Insertar espacios naturales
			for (int i = 0 ; i < listaEspaciosNaturales.length; i++){
				InsertarBorrar.insertar(listaEspaciosNaturales[i],sesion,session);
			}
			
			//Insertar estaciones
			for(int i = 0 ; i < listaEstaciones.length ; i++)
			{
				try{
					String hql = "from Municipios where lower(nombre) = '"+listaEstaciones[i].getMunicipio()+"'";
					Query q = session.createQuery(hql);
					Municipios muni = (Municipios) q.uniqueResult();
					
					if(muni != null){
						listaEstaciones[i].setMunicipios(muni);
						InsertarBorrar.insertar(listaEstaciones[i],sesion,session);
					}
					else
					{
						int y = 0;
						boolean encontrado = false;
						while(y < comprobarMunicipios.size() && !encontrado)
						{
							if(comprobarMunicipios.get(y).getNombreentero().contains(listaEstaciones[i].getMunicipio()))
							{
								hql = "from Municipios where lower(nombre) = '"+comprobarMunicipios.get(y).getNombre()+"'";
								q = session.createQuery(hql);
								muni = (Municipios) q.uniqueResult();
								
								listaEstaciones[i].setMunicipios(muni);
								InsertarBorrar.insertar(listaEstaciones[i],sesion,session);
								encontrado = true;
							}
							y++;
						}
					}
				}
				catch (Exception f)
				{
					System.out.println(f.getMessage());
					return false;
				}
			}
			
			//Insertar informes y horarios
			for(int i = 0 ; i < horariosEstaciones.length ;i++)
			{
				String url = horariosEstaciones[i].getUrl();
				try{	
					String hql1 = "from Estaciones where lower(nombre) = '"+horariosEstaciones[i].getNombre()+"'";
					Query q1 = session.createQuery(hql1);
					Estaciones estacion = (Estaciones) q1.uniqueResult();
					
					if(estacion != null)
					{
						horariosEstaciones[i].setEstaciones(estacion);
						boolean encontrado = false;
						for(int o = 0 ; o < paginasNoEncontrada.size() ; o++)
						{
							if(horariosEstaciones[i].equals(paginasNoEncontrada.get(o)))
								encontrado = true;
						}
						if(!encontrado)
							InsertarBorrar.insertar(horariosEstaciones[i],sesion,session);
					}
				}
				catch (Exception f)
				{
//					f.printStackTrace();
				}
			}
			
			//Insertar Entornos
			for (int i = 0 ; i < listaEspaciosNaturales.length; i++)
			{
				try{
					String hql = "from Entornos where lower(nombre) = '"+listaEspaciosNaturales[i].getNombre()+"'";
					Query q = session.createQuery(hql);
					Entornos entorno = (Entornos) q.uniqueResult();
					
					String hql1 = "from Municipios where lower(nombre) = '"+listaEspaciosNaturales[i].getMunicipio()+"'";
					Query q1 = session.createQuery(hql1);
					Municipios muni = (Municipios) q1.uniqueResult();
					if(entorno != null && muni != null)
					{
						EntornosmuniId clave = new EntornosmuniId(entorno.getId(),muni.getId());
						Entornosmuni vinculo = new Entornosmuni(clave,entorno,muni);
						InsertarBorrar.insertar(vinculo,sesion,session);
					}
				}
				catch (Exception f){
					System.out.println(f.getMessage());
					return false;
				}
			}
			insertarHorarios(horariosEstaciones, sesion, session, mapper);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return true;
	}
	
	/**
	 * Metodo que crea los Jsones para comprobar con los hashes de la BDD.
	 * @param horariosEstaciones
	 * @param mapper
	 * @return devuelve un arraylist con todas las estaciones y las paginas no encontradas
	 * Las paginas no encontradas sirve para señalar que paginas evitar y resistencia a fallos inesperados del programa y BDD.
	 */
	public ArrayList<Object> cargarHorarios(Informes[] horariosEstaciones, ObjectMapper mapper)
	{
		ArrayList<Informes> paginasNoEncontrada = new ArrayList<Informes>();
		ArrayList<Object> result = new ArrayList<Object>();
		@SuppressWarnings("unused")
		Horario[] horario = null;
		for(int y = 0 ; y < horariosEstaciones.length ;y++)
		{
			try
			{
				String url = horariosEstaciones[y].getUrl();
				if(url.contains("datos_indice") || url.contains("datos_diarios"))
				{
					horario = mapper.readValue(readJsonDesdeUrl(horariosEstaciones[y].getUrl()), Horario[].class);
				}
			}
			catch(FileNotFoundException d)
			{
				paginasNoEncontrada.add(horariosEstaciones[y]);
			}
			catch (Exception a)
			{
				//Seguir al siguiente objeto en caso de fallo
			}
		}
		result.add(horariosEstaciones);
		result.add(paginasNoEncontrada);
		return result;
	}
	
	/**
	 * Metodo de insercion a la base de datos de todos los horarios de cada index que se encuentra en el json.
	 * @param horariosEstaciones
	 * @param sesion
	 * @param session
	 * @param mapper
	 */
	public boolean insertarHorarios(Informes[] horariosEstaciones, 
										   SessionFactory sesion, Session session , ObjectMapper mapper  )
	{
		Horario[] horario = null;
		for(int y = 0 ; y < horariosEstaciones.length ;y++)
		{
			try{
				String url = horariosEstaciones[y].getUrl();
				if(url.contains("datos_indice") || url.contains("datos_diarios"))
				{
					horario = mapper.readValue(readJsonDesdeUrl(horariosEstaciones[y].getUrl()), Horario[].class);
					String hql = "from Informes where url = '"+url+"'";
					Query q = session.createQuery(hql);
					Informes informe = (Informes) q.uniqueResult();
					for(int x = 0 ; x < horario.length; x++)
					{
						horario[x].setInformes(informe);
						if(!horario[x].isNull() && horario[x].getInformes() != null)
						{
							InsertarBorrar.insertar(horario[x],sesion,session);
						}
					}
				}
			}
			catch(Exception b)
			{
				//Seguir al siguiente objeto en caso de fallo
			}
		}
		return true;
	}
	
	/**
	 * Lee la pagina y devuelve el codigo de json adecuado en formato String
	 * @param rd
	 * @param date
	 * @param tipo
	 * @return
	 * @throws IOException
	 */
	private String readAll(Reader rd, String url) throws IOException {
		StringBuilder sb = new StringBuilder();
		int cp;
		int contadorRepetidos = 1;
		String result = "";
		char numeroDescripcion = (char) String.valueOf(contadorRepetidos).charAt(0);
		while ((cp = rd.read()) != -1) {
			sb.append((char) cp);
			numeroDescripcion = (char) String.valueOf(contadorRepetidos).charAt(0);
			result = comprobarHoraDia(sb.toString(),url);
				if(!result.equals("continuar"))
					return result.toLowerCase();
				
			if(sb.toString().indexOf("turismDescription\"") != -1)
			{
				sb.deleteCharAt(sb.length()-1);
				sb.append((char) numeroDescripcion);
				sb.append('"');
				contadorRepetidos++;
				if(contadorRepetidos == 3)
					contadorRepetidos = 1;
				
			}
		}
		
		/**
		 * Como el index es un json mas especial se incluye en un if especifico
		 */
		if(url.contains("index.json"))
		{
			result = sb.toString().substring(sb.toString().indexOf("["),sb.toString().lastIndexOf("]")+1);
			String nombrefichero = url.substring(url.lastIndexOf("/")+1);
			escribirJson(result,nombrefichero);
			result = replace(result, url);
			return result;
		}
		/*
		 * En caso de que no se encuentra la parentesis del principio ni del final se añaden en este caso.
		 */
		else if(sb.toString().indexOf("[") != -1 && sb.toString().lastIndexOf("]") != -1)
		{
			result = sb.toString().substring(sb.toString().indexOf("["),sb.toString().lastIndexOf("]")+1);
			String nombrefichero = url.substring(url.lastIndexOf("/")+1);
			escribirJson(result,nombrefichero);
			result = replace(result, url);
			return result.toLowerCase();
		}
		/**
		 * En caso de que el json no contiene suficientes datos para formar un objeto este json se devolvera vacio.
		 */
		else 
			return "[{}]";
	}
	
	/**
	 * Escribe en String del parametro en un fichero.
	 * @param json
	 * @return
	 */
	public boolean escribirJson(String contenido,String nombre)
	{
		try (BufferedWriter fichero = new BufferedWriter(new FileWriter("."+File.separatorChar+"json"+File.separatorChar+nombre, false));)
		{
				fichero.write(contenido);
				return true;
		}
		catch (FileNotFoundException fn) {
			System.out.println("No se encuentra el fichero");
		} catch (IOException e) {
//			e.printStackTrace();
		}
		return false;
	}
	
	/**
	 * Metodo para abrir una entrada con la pagina para empezar ha leer.
	 * @param url
	 * @param date
	 * @param tipo
	 * @return
	 * @throws IOException
	 */
	public String readJsonDesdeUrl(String url) throws IOException {
		//Abrir entrada de la pagina para empezar ha leerlo.
		InputStream is = new URL(url).openStream();
		try {
			BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
			String jsonText = readAll(rd,url);
			return jsonText;
		} finally {
			is.close();
		}
	}
	
	/**
	 * Metodo para sacar la fecha anterior a diferencia de hoy
	 * @param cantidadDiasAnteriores ; cantidad de dias anteriores
	 * @return fecha adecuada.
	 */
	public String fechaAnterior(int cantidadDiasAnteriores)
	{
		Calendar fecha = Calendar.getInstance();
		fecha.add(Calendar.DAY_OF_YEAR, -cantidadDiasAnteriores);
		int dia = fecha.get(Calendar.DAY_OF_MONTH);
		int mes = fecha.get(Calendar.MONTH);
		int anio = fecha.get(Calendar.YEAR);
		String date = String.format("%02d", dia) + "/" + String.format("%02d", (mes+1)) + "/" + anio;
		return date;
	}
	
	/**
	 * Este metodo sirve para comprobar el certificado de la cierta pagina.
	 * @param url
	 */
	public void comprobarPagina(String url)
	{
		try {
			SSLContext ssl_ctx = SSLContext.getInstance("TLS");
			TrustManager[] trust_mgr = new TrustManager[] { (TrustManager) new X509TrustManager() {
				public X509Certificate[] getAcceptedIssuers() {
					return null;
				}

				public void checkClientTrusted(X509Certificate[] certs, String t) {
				}

				public void checkServerTrusted(X509Certificate[] certs, String t) {
				}
			} };
			ssl_ctx.init(null, // key manager
					trust_mgr, // trust manager
					new SecureRandom()); // random number generator
			HttpsURLConnection.setDefaultSSLSocketFactory(ssl_ctx.getSocketFactory());
			HttpsURLConnection huc = null;
			huc = (HttpsURLConnection) (new URL(url).openConnection());
			huc.getResponseCode();
		} catch (Exception e) {
//			e.printStackTrace();
		}
	}

	/**
	 * Este metodo sirve como filtro de cantidad de objetos que se recoge de los json
	 * @param sb
	 * @param url
	 */
	private String comprobarHoraDia(String sb ,String url)
	{
		String nombreFichero;
		//Que siga rellenando hasta la fecha anterior y hasta la hora 10 de esa fecha
		if (sb.contains("Date"))
			{
				try
				{
					//Recoger el string entero
					String result = sb.substring(sb.indexOf("["));
					String date = fechaAnterior(1);
					//Comprobar si la ultima fecha es del mismo mes y año
					if(result.lastIndexOf(date.substring(2)) != -1)
						//comprobar que la ultima fecha que se ha encontrado es menor que la fecha que quieremos
						if(Integer.parseInt(result.substring(result.lastIndexOf(date.substring(2))-2,result.lastIndexOf(date.substring(2)))) < Integer.parseInt(date.substring(0,2)))
							if(sb.contains("Hour") && url.contains("datos_indice"))
							{
								if(sb.contains("09:00"))
								{
									result = sb.substring(sb.indexOf("["),sb.lastIndexOf("}")+1) + " ]";
									nombreFichero = url.substring(url.lastIndexOf("/",url.lastIndexOf("/")-1)+1).replace("/", "-");
									escribirJson(result,nombreFichero);
									result = replace(result,url);
									return result;
								}
							}
							else if(url.contains("datos_diarios"))
							{
								date = fechaAnterior(2);
								//en caso de que no tenga el elemento hora(lo mas probable esq sea un diario) recogemos solo con la fecha
								result = sb.substring(sb.indexOf("["));
								//comprobamos que tenga una fecha en el mismo mes y año
								if(result.lastIndexOf(date.substring(2)) != -1)
									//comprobamos que el ultimo dia elegido es menor a la fecha que quieremos
									if(Integer.parseInt(result.substring(result.lastIndexOf(date.substring(2))-2,result.lastIndexOf(date.substring(2)))) < Integer.parseInt(date.substring(0,2)))
									{
										try
										{
											//si tiene algun elemento se guardara en un json y se inserta el json
											result = sb.substring(sb.indexOf("["),sb.lastIndexOf("}")+1) + " ]";
											nombreFichero = url.substring(url.lastIndexOf("/",url.lastIndexOf("/")-1)+1).replace("/", "-");
											escribirJson(result,nombreFichero);
											result = replace(result,url);
											return result;
										}
										catch (StringIndexOutOfBoundsException a)
										{
											//sino se deja vacio
											return "[{}]";
										}
										
									}
							}
					
				}
				catch (StringIndexOutOfBoundsException a)
				{
					return "continuar";
				}
			}
			return "continuar";
	}
	
	/**
	 * Este metodo sirve para formatear los datos de los json a los que tiene el programa
	 * Las librerias Jackson solo pilla datos si tiene el mismo nombre que el metodo set de la clase corespondiente
	 * con lo cual es necesario renombrar los campos de los json a los que tenemos en el programa.
	 * @param cadena
	 * @param tipo
	 * @return el json entero formateado.
	 */
	public String replace(String cadena,String tipo)
	{
		if(tipo.toLowerCase().contains("estaciones.json"))
		{
			cadena = cadena.replace("Name", "Nombre");
			cadena = cadena.replace("Province" , "Provincia");
			cadena = cadena.replace("Town", "Municipio");
			cadena = cadena.replace("Address", "Direccion");
			cadena = cadena.replace("CoordenatesXETRS89", "CoordenadaX");
			cadena = cadena.replace("CoordenatesYETRS89", "CoordenadaY");
			cadena = cadena.replace("Latitude", "Latitud");
			cadena = cadena.replace("Longitude", "Longitud");
		}
		else if(tipo.toLowerCase().contains("datos_indice"))
		{
			cadena = cadena.replace("Date", "Fecha");
			cadena = cadena.replace("Hour", "Hora");
			cadena = cadena.replace("COmgm3", "valor1");
			cadena = cadena.replace("NOgm3", "valor2");
			cadena = cadena.replace("NO2", "valor3");
			cadena = cadena.replace("NOXgm3", "valor4");
			cadena = cadena.replace("PM10", "valor5");
			cadena = cadena.replace("PM25", "valor6");
			cadena = cadena.replace("SO2", "valor7");
		}
		else if(tipo.toLowerCase().contains("datos_diarios"))
		{
			cadena = cadena.replace("Date", "Fecha");
			cadena = cadena.replace("COmgm3", "valor1");
			cadena = cadena.replace("NOgm3", "valor2");
			cadena = cadena.replace("NO2", "valor3");
			cadena = cadena.replace("NOXgm3", "valor4");
			cadena = cadena.replace("PM10", "valor5");
			cadena = cadena.replace("PM25", "valor6");
			cadena = cadena.replace("SO2", "valor7");
		}
		else if(tipo.toLowerCase().contains("pueblos.json"))
		{
			cadena = cadena.replace("documentName", "Nombre");
			cadena = cadena.replace("turismDescription1", "Descripcion");
			cadena = cadena.replace("latwgs84", "Latitud");
			cadena = cadena.replace("lonwgs84", "Longitud");
			cadena = cadena.replace("municipalitycode", "Codigo");
			cadena = cadena.replace("territory", "Provincia");
			cadena = cadena.replace("municipality", "nombreEntero");
		}
		else if(tipo.toLowerCase().contains("espacios-naturales.json"))
		{
			cadena = cadena.replace("documentName", "Nombre");
			cadena = cadena.replace("turismDescription1", "Descripcion");
			cadena = cadena.replace("natureType", "Tipo");
			cadena = cadena.replace("territory", "Territorio");
			cadena = cadena.replace("municipality", "municipio");
			cadena = cadena.replace("latwgs84", "Latitud");
			cadena = cadena.replace("lonwgs84", "Longitud");
		}
		else if(tipo.toLowerCase().contains("index.json"))
		{
			cadena = cadena.replace("format", "formato");
			cadena = cadena.replace("name", "nombre");
		}
		return cadena;
	}
}
