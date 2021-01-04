package main;

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
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.regex.PatternSyntaxException;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.hibernate.HibernateException;
import org.hibernate.PropertyValueException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.exception.ConstraintViolationException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import hiberclass.Entornos;
import hiberclass.Entornosmuni;
import hiberclass.EntornosmuniId;
import hiberclass.Estaciones;
import hiberclass.Horario;
import hiberclass.Informes;
import hiberclass.Municipios;

public class Principal {
	static String[] caracteresDeSeparacion = {"-","/","|"};
	
	public static void main(String[] args) {
		Calendar tiempo1 = Calendar.getInstance();
		Horario[] horario = null;
		Entornos[] listaEspaciosNaturales = null;
		Municipios[] listaMunicipios = null;
		ArrayList<Informes> paginasNoEncontrada = new ArrayList<Informes>();
		try (SessionFactory sesion = HibernateUtil.getSessionFactory();
			 Session session = sesion.openSession();)
		{
			int contadorPaginasNoEncontradas = 0;
			//Comprobar los certificados de la pagina
			comprobarPagina("https://opendata.euskadi.eus/contenidos/ds_informes_estudios/calidad_aire_2021/es_def/adjuntos/index.json");
			
			ObjectMapper mapper = new ObjectMapper();
			
			//Esta linea sirve para ignorar los elementos que encuentra y que el objeto no tenga
			mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
			Informes[] horariosEstaciones = null;
			try {
				horariosEstaciones = mapper.readValue(readJsonDesdeUrl("https://opendata.euskadi.eus/contenidos/ds_informes_estudios/calidad_aire_2021/es_def/adjuntos/index.json"), Informes[].class);
			} catch (JsonMappingException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			} catch (JsonProcessingException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			} catch (IOException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
			//Cargamos los arrays con todos los datos de los json
			Estaciones[] listaEstaciones = mapper.readValue(readJsonDesdeUrl("https://opendata.euskadi.eus/contenidos/ds_informes_estudios/calidad_aire_2020/es_def/adjuntos/estaciones.json"), Estaciones[].class);
			listaMunicipios = mapper.readValue(readJsonDesdeUrl("https://opendata.euskadi.eus/contenidos/ds_recursos_turisticos/pueblos_euskadi_turismo/opendata/herriak.json"), Municipios[].class);
			listaEspaciosNaturales = mapper.readValue(readJsonDesdeUrl("https://opendata.euskadi.eus/contenidos/ds_recursos_turisticos/playas_de_euskadi/opendata/espacios-naturales.json"), Entornos[].class);
			String[] nombres;
			
			//Insertartamos todos los municipios
			for(int i = 0 ; i < listaMunicipios.length ; i++)
			{
				InsertarBorrar.insertar(listaMunicipios[i],sesion,session);
			}
			
			//Insertar espacios naturales
			for (int i = 0 ; i < listaEspaciosNaturales.length; i++)
			{
				InsertarBorrar.insertar(listaEspaciosNaturales[i],sesion,session);						
			}
			
			//Insertar estaciones
			for(int i = 0 ; i < listaEstaciones.length ; i++)
			{
				for(int y = 0 ; y < listaMunicipios.length ; y++)
				{
					try
					{
						for(int x = 0 ; x < caracteresDeSeparacion.length ; x++)
						{
							try {
								nombres = listaEstaciones[i].getMunicipio().split(caracteresDeSeparacion[x]);
								for(int valor = 0 ; valor < nombres.length ; valor++)
								{
									if(listaMunicipios[y].getNombre().toLowerCase().contains(nombres[valor]));
									{
										listaEstaciones[i].setMunicipios(listaMunicipios[y]);
										InsertarBorrar.insertar(listaEstaciones[i],sesion,session);
									}
								}
							}
							catch (PatternSyntaxException a)
							{
	//							System.out.println("La estacion no tiene ese municipio");
							}
						}
						if(listaMunicipios[y].getNombre().toLowerCase().equals(listaEstaciones[i].getMunicipio()))
						{
							listaEstaciones[i].setMunicipios(listaMunicipios[y]);
							InsertarBorrar.insertar(listaEstaciones[i],sesion,session);
						}
					}
					catch (ConstraintViolationException e)
					{
						System.out.println("Estacion repetida.");
					}
					catch (HibernateException o)
					{
						System.out.println("Estacion repetida.");
					}
					catch (Exception f)
					{
						System.out.println(f.getMessage());
					}
				}
			}
			
			
			//Insertar informes y horarios
			for(int i = 0 ; i < horariosEstaciones.length ;i++)
			{
				String url = horariosEstaciones[i].getUrl();
				try
				{	
					for(int y = 0 ; y < listaEstaciones.length ; y++)
					{
						nombres = horariosEstaciones[i].getNombre().toLowerCase().split("_");
						for(int x = 0 ; x < nombres.length ;x++)
						{
							try
							{
								if(listaEstaciones[y].getNombre().toLowerCase().contains(nombres[x]) && (url.contains("datos_indice") || url.contains("datos_diarios")))
								{
									horariosEstaciones[i].setEstaciones(listaEstaciones[y]);
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
							catch (ConstraintViolationException e)
							{
								System.out.println("Objeto repetido.");
							}
							catch (HibernateException o)
							{
								System.out.println("Objeto repetido.");
							}
							catch (PatternSyntaxException a)
							{
	//							System.out.println("La estacion no tiene ese municipio");
							}
							catch (Exception f)
							{
//								f.printStackTrace();
								System.out.println(f.getMessage());
							}
						}
					}
				}
				catch (ConstraintViolationException e)
				{
					System.out.println("Objeto repetido.");
				}
				catch (HibernateException o)
				{
					System.out.println("Objeto repetido.");
				}
				catch (Exception f)
				{
					f.printStackTrace();
				}
			}
			
			
			for (int i = 0 ; i < listaEspaciosNaturales.length; i++)
			{
				for(int y = 0 ; y < listaMunicipios.length ; y++)
				{
					try
					{
						//Como un espacio natural puede tener mas de un municipio comprobamos si puede tener mas de un 
						for(int x = 0 ; x < caracteresDeSeparacion.length ; x++)
						{
							try {
								nombres = listaEspaciosNaturales[i].getMunicipio().trim().split(caracteresDeSeparacion[x]);
								for(int valor = 0 ; valor < nombres.length ; valor++)
								{
									String nombreMuni = listaMunicipios[y].getNombre().toLowerCase().trim();
									if(nombres[valor].toLowerCase().trim().equals(nombreMuni))
									{
										Entornos entorno = (Entornos) listaEspaciosNaturales[i];
										Municipios muni = listaMunicipios[y];
					
										String hql = "from Entornos where nombre = '"+entorno.getNombre()+"'";
										Query q = session.createQuery(hql);
										entorno = (Entornos) q.uniqueResult();
										
										String hql1 = "from Municipios where nombre = '"+muni.getNombre()+"'";
										Query q1 = session.createQuery(hql1);
										muni = (Municipios) q1.uniqueResult();

										EntornosmuniId clave = new EntornosmuniId(entorno.getId(),muni.getId());
										Entornosmuni vinculo = new Entornosmuni(clave,entorno,muni);
										InsertarBorrar.insertar(vinculo,sesion,session);
									}
								}
							}
							catch (PatternSyntaxException a)
							{
	//							System.out.println("La estacion no tiene ese municipio");
							}
						}
					}
					catch (ConstraintViolationException e)
					{
						System.out.println("Espacio natural repetido.");
					}
					catch (HibernateException o)
					{
						System.out.println("Espacio natural repetido.");
					}
					catch (Exception f)
					{
						System.out.println(f.getMessage());
					}
				}
			}
			
			for(int y = 0 ; y < horariosEstaciones.length ;y++)
			{
				try
				{
					String url = horariosEstaciones[y].getUrl();
					if(url.contains("datos_indice") || url.contains("datos_diarios"))
					{
						String hql = "from Informes where url = '"+url+"'";
						Query q = session.createQuery(hql);
						Informes informe = (Informes) q.uniqueResult();
						horario = mapper.readValue(readJsonDesdeUrl(horariosEstaciones[y].getUrl()), Horario[].class);
						for(int x = 0 ; x < horario.length; x++)
						{
							horario[x].setInformes(informe);
							if(!horario[x].isNull() && horario[x].getInformes() != null)
								InsertarBorrar.insertar(horario[x],sesion,session);
						}
					}
				}
				catch (IOException a)
				{
					
				}
				catch(PropertyValueException b)
				{
					
				}
				catch (ConstraintViolationException c)
				{
					
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		Calendar tiempo2 = Calendar.getInstance();
		System.out.println(Duration.between(tiempo1.toInstant(), tiempo2.toInstant()));
	}
	
	/**
	 * Lee la pagina y devuelve el codigo de json adecuado en formato String
	 * @param rd
	 * @param date
	 * @param tipo
	 * @return
	 * @throws IOException
	 */
	private static String readAll(Reader rd, String url) throws IOException {
		StringBuilder sb = new StringBuilder();
		int cp;
		String result = "";
		while ((cp = rd.read()) != -1) {
			sb.append((char) cp);

			result = comprobarHoraDia(sb.toString(),url);
				if(!result.equals("continuar"))
					return result.toLowerCase();
		}
		
		if(url.contains("index.json"))
		{
			result = sb.toString().substring(sb.toString().indexOf("["),sb.toString().lastIndexOf("]")+1);
			String nombrefichero = url.substring(url.lastIndexOf("/")+1);
			escribirJson(result,nombrefichero);
			result = replace(result, url);
			return result;
		}
		else if(sb.toString().indexOf("[") != -1 && sb.toString().lastIndexOf("]") != -1)
		{
			result = sb.toString().substring(sb.toString().indexOf("["),sb.toString().lastIndexOf("]")+1);
			String nombrefichero = url.substring(url.lastIndexOf("/")+1);
			escribirJson(result,nombrefichero);
			result = replace(result, url);
			return result.toLowerCase();
		}
		else if(sb.toString().indexOf("[") != -1 && sb.toString().lastIndexOf("]") == -1)
		{
			result = sb.toString().substring(sb.toString().indexOf("["),sb.toString().lastIndexOf("}")) + "]";
			String nombrefichero = url.substring(url.lastIndexOf("/")+1);
			escribirJson(result,nombrefichero);
			result = replace(result, url);
			return result.toLowerCase();
		}
		else
			return "[{}]";
	}
	
	/**
	 * Escribe en String del parametro en un fichero.
	 * @param json
	 * @return
	 */
	public static boolean escribirJson(String contenido,String nombre)
	{
		try (BufferedWriter fichero = new BufferedWriter(new FileWriter("."+File.separatorChar+"json"+File.separatorChar+nombre, false));)
		{
				fichero.write(contenido);
				return true;
		}
		catch (FileNotFoundException fn) {
			System.out.println("No se encuentra el fichero");
		} catch (IOException e) {
			e.printStackTrace();
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
	public static String readJsonDesdeUrl(String url) throws IOException {
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
	public static String fechaAnterior(int cantidadDiasAnteriores)
	{
		Calendar fecha = Calendar.getInstance();
		fecha.add(Calendar.DAY_OF_YEAR, -cantidadDiasAnteriores);
		int dia = fecha.get(Calendar.DAY_OF_MONTH);
		int mes = fecha.get(Calendar.MONTH);
		int anio = fecha.get(Calendar.YEAR);
		String date = dia + "/" + (mes+1) + "/" + anio;
		return date;
	}
	
	/**
	 * Este metodo sirve para comprobar el certificado de la cierta pagina.
	 * @param url
	 */
	public static void comprobarPagina(String url)
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
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (KeyManagementException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private static String comprobarHoraDia(String sb ,String url)
	{
		String nombreFichero;
		//Que siga rellenando hasta la fecha anterior y hasta la hora 10 de esa fecha
		if (sb.contains("Date"))
			{
				try
				{
					//Recoger el string entero
					String result = sb.substring(sb.indexOf("["));
					String date = fechaAnterior(0);
					//Comprobar si la ultima fecha es del mismo mes y año
					if(result.lastIndexOf(date.substring(2)) != -1)
						//comprobar que la ultima fecha que se ha encontrado es menor que la fecha que quieremos
						if(Integer.parseInt(result.substring(result.lastIndexOf(date.substring(2))-2,result.lastIndexOf(date.substring(2))).replace("/", "")) < Integer.parseInt(date.substring(0,2).replace("/","")))
							if(sb.contains("Hour") && url.contains("datos_indice"))
								if(sb.contains("09:00"))
								{
									result = sb.substring(sb.indexOf("["),sb.lastIndexOf("}")+1) + " ]";
									nombreFichero = url.substring(url.lastIndexOf("/",url.lastIndexOf("/")-1)+1).replace("/", "-");
									escribirJson(result,nombreFichero);
									result = replace(result,url);
									return result;
								}
							else if(url.contains("datos_diarios"))
							{
								date = fechaAnterior(2);
								//en caso de que no tenga el elemento hora(lo mas probable esq sea un diario) recogemos solo con la fecha
								result = sb.substring(sb.indexOf("["));
								//comprobamos que tenga una fecha en el mismo mes y año
								if(result.lastIndexOf(date.substring(2)) != -1)
									//comprobamos que el ultimo dia elegido es menor a la fecha que quieremos
									if(Integer.parseInt(result.substring(result.lastIndexOf(date.substring(2))-2,result.lastIndexOf(date.substring(2))).replace("/", "")) < Integer.parseInt(date.substring(0,2).replace("/", "")))
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
	
	public static String replace(String cadena,String tipo)
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
		else if(tipo.toLowerCase().contains("herriak.json"))
		{
			cadena = cadena.replace("documentName", "Nombre");
			cadena = cadena.replace("documentDescription", "Descripcion");
			cadena = cadena.replace("latwgs84", "Latitud");
			cadena = cadena.replace("lonwgs84", "Longitud");
			cadena = cadena.replace("municipalitycode", "Codigo");
		}
		else if(tipo.toLowerCase().contains("espacios-naturales.json"))
		{
			cadena = cadena.replace("documentName", "Nombre");
			cadena = cadena.replace("turismDescription", "Descripcion");
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
