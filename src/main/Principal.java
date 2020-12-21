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
import java.util.ArrayList;
import java.util.Calendar;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Principal {
	static ArrayList<Integer> lista;
	public static void main(String[] args) {
		try {
			int contadorPaginasNoEncontradas = 0;
			//Comprobar los certificados de la pagina
			comprobarPagina("https://opendata.euskadi.eus/contenidos/ds_informes_estudios/calidad_aire_2020/es_def/adjuntos/index.json");
			
			ObjectMapper mapper = new ObjectMapper();
			
			Indice usrPost = mapper.readValue(new URL("https://opendata.euskadi.eus/contenidos/ds_informes_estudios/calidad_aire_2020/es_def/adjuntos/index.json"), Indice.class);
			//Esta linea sirve para ignorar los elementos que encuentra y que el objeto no tenga
			mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
			//Recorremos la lista para pillar todas las horas
			for(int i = 0 ; i< usrPost.getAggregated().size(); i++)
			{
				try
				{
					Datos_indice[] json = null;
					//Comprobamos si es un dato horario o diario
					if(usrPost.getAggregated().get(i).getUrl().contains("datos_indice"))
					{
						//Si en un dato horario se pillara todo hasta el dia anterior hasta la hora 10:00
						json = mapper.readValue(readJsonFromUrl(usrPost.getAggregated().get(i).getUrl(),fechaAnterior(0),null), Datos_indice[].class);
						
						//Este objeto solo sirve para guardar cuantos elementos tiene cada url
						usrPost.getAggregated().get(i).setHorasRegistradas(json.length);
						System.out.println(usrPost.getAggregated().get(i).getUrl());
						for(int y = 0; y < json.length ;y++)
						{
							lista = new ArrayList<Integer>();
//							indexOcurriencias(json[y].toString(),'=',json[y].toString().indexOf("="));
							for (int x = 0; x < contarCaracteres(json[y].toString(),'=') ; x++)
							{
								System.out.println(json.toString().substring(lista.get(y),lista.get(y)+4));
								if(!json.toString().substring(lista.get(y),lista.get(y)+4).equals("null"))
									System.out.println(y + ") " + "No esta vacio");
							}
						}
					}
					else if(usrPost.getAggregated().get(i).getUrl().contains("datos_diarios"))
					{
						//Si es un dato diario se pillara el objeto hasta el dia anterior
						json = mapper.readValue(readJsonFromUrl(usrPost.getAggregated().get(i).getUrl(),fechaAnterior(1),"diarios"), Datos_indice[].class);

						usrPost.getAggregated().get(i).setHorasRegistradas(json.length);
						//Este objeto solo sirve para guardar cuantos elementos tiene cada url
						usrPost.getAggregated().get(i).setHorasRegistradas(json.length);
						for(int y = 0; y < json.length ;y++)
						{
							lista = new ArrayList<Integer>();
							indexOcurriencias(json[y].toString(),'=',json[y].toString().indexOf("="));
							System.out.println(contarCaracteres(json[0].toString(),'='));
							for (int x = json[y].toString().indexOf("=")+1; x < json[y].toString().length() ; x++)
							{
								System.out.println(json.toString().substring(lista.get(y),lista.get(y)+4));
								if(!json.toString().substring(lista.get(y),lista.get(y)+4).equals("null"))
									System.out.println(y + ") " + "No esta vacio");
							}
						}
					}
					
				}
				catch (FileNotFoundException a)
				{
					//Si la pagina no se encuentra muestra el error
					System.out.println("Pagina no encontrada");
					//Contador de paginas no encotradas.
					contadorPaginasNoEncontradas++;
				}
			}
			//Resumen de datos
			System.out.println("Hay " + usrPost.getAggregated().size() + " elementos en el json de calidad del aire de cuales hay " + contadorPaginasNoEncontradas + " sin encontrar.");
			System.out.println("Cada uno de los elementos tiene la siguiente cantidad de horas registradas.");
			

			for (int i = 0 ; i < usrPost.getAggregated().size() ; i++)
			{
				//Quitar datos_horarios y las paginas que no se encuentran del array
				if(usrPost.getAggregated().get(i).getHorasRegistradas() <= 0)
				{
					usrPost.getAggregated().remove(i);
					i = -1;
				}
			}
			
			//mostrar los contenidos
			for (int i = 0 ; i < usrPost.getAggregated().size() ; i++)
			{
				System.out.print((i+1) + ")");
				System.out.println("Url: " + usrPost.getAggregated().get(i).getUrl());
				System.out.println("Nombre: " + usrPost.getAggregated().get(i).getName());
				System.out.println("Contiene " + usrPost.getAggregated().get(i).getHorasRegistradas());
				System.out.println("-----------------------------------------------------------");
			}
			
			System.out.println("Hay " + usrPost.getAggregated().size() + " elementos en el json de calidad del aire de cuales hay " + contadorPaginasNoEncontradas + " sin encontrar.");
			System.out.println(usrPost.getAggregated().get(0).getUrl());
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Lee la pagina y devuelve el codigo de json adecuado en formato String
	 * @param rd
	 * @param date
	 * @param tipo
	 * @return
	 * @throws IOException
	 */
	private static String readAll(Reader rd, String date,String tipo,String nombreFichero) throws IOException {
		StringBuilder sb = new StringBuilder();
		int cp;
		while ((cp = rd.read()) != -1) {
		sb.append((char) cp);
			
		//Que siga rellenando hasta la fecha anterior y hasta la hora 10 de esa fecha
		if (sb.toString().contains("Date"))
//			if(tipo == null || tipo.equals(""))
			{
				//Recoger el string entero
				String result = sb.toString().substring(sb.toString().indexOf("["));
				
				//Comprobar si la ultima fecha es del mismo mes y año
				if(result.lastIndexOf(date.substring(2)) != -1)
					//comprobar que la ultima fecha que se ha encontrado es menor que la fecha que quieremos
					if(Integer.parseInt(result.substring(result.lastIndexOf(date.substring(2))-2,result.lastIndexOf(date.substring(2)))) < Integer.parseInt(date.substring(0,2)))
						if(sb.toString().contains("Hour"))
						{
							if(sb.toString().contains("09:00"))
							{
								result = sb.toString().substring(sb.toString().indexOf("["),sb.toString().lastIndexOf("}")+1) + " ]";
								escribirJson(result,nombreFichero.substring(nombreFichero.lastIndexOf("/")+1));
								return result.toLowerCase();
							}
						}
						else
						{	
							//en caso de que no tenga el elemento hora(lo mas probable esq sea un diario) recogemos solo con la fecha
							result = sb.toString().substring(sb.toString().indexOf("["));
							//comprobamos que tenga una fecha en el mismo mes y año
							if(result.lastIndexOf(date.substring(2)) != -1)
								//comprobamos que el ultimo dia elegido es menor a la fecha que quieremos
								if(Integer.parseInt(result.substring(result.lastIndexOf(date.substring(2))-2,result.lastIndexOf(date.substring(2)))) < Integer.parseInt(date.substring(0,2)))
								{
									try
									{
										//si tiene algun elemento se guardara en un json y se inserta el json
										result = sb.toString().substring(sb.toString().indexOf("["),sb.toString().lastIndexOf("}")+1) + " ]";
										escribirJson(result,nombreFichero.substring(nombreFichero.lastIndexOf("/")+1));
										return result.toLowerCase();
									}
									catch (StringIndexOutOfBoundsException a)
									{
										//sino se deja vacio
										return "[{}]";
									}
									
								}
						}
			}
			
				
		}
		return sb.toString().substring(sb.toString().indexOf("["),sb.toString().lastIndexOf("]")+1).toLowerCase();
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
	public static String readJsonFromUrl(String url, String date, String tipo) throws IOException {
		//Abrir entrada de la pagina para empezar ha leerlo.
		InputStream is = new URL(url).openStream();
		try {
			BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
			String jsonText = readAll(rd,date,tipo,url);
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

	public static int contarCaracteres(String cadena,char valor)
	{
		int contador = 0;
		for (int i = 0; i < cadena.length() ; i++)
			if(cadena.charAt(i) == valor)
				contador++;
		return contador;
	}
	
	public static int indexOcurriencias(String cadena, char valor,int ultimoIndex)
	{
		if(cadena.indexOf(ultimoIndex) != -1)
		{
			return cadena.indexOf(ultimoIndex);
		}
		return -1;
	}
}
