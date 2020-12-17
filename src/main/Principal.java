package main;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
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
import java.util.Calendar;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Principal {
	public static void main(String[] args) {
		try {
			int contadorPaginasNoEncontradas = 0;
			comprobarPagina("https://opendata.euskadi.eus/contenidos/ds_informes_estudios/calidad_aire_2020/es_def/adjuntos/datos_horarios/3_DE_MARZO.json");
			ObjectMapper mapper = new ObjectMapper();
			
			Indice usrPost = mapper.readValue(new URL("https://opendata.euskadi.eus/contenidos/ds_informes_estudios/calidad_aire_2020/es_def/adjuntos/index.json"), Indice.class);
			mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
			for(int i = 0 ; i< usrPost.getAggregated().size(); i++)
			{
				try 
				{
					Datos_indice[] json = null;
					if(!usrPost.getAggregated().get(i).getUrl().contains("datos_diarios"))
						json = mapper.readValue(readJsonFromUrl(usrPost.getAggregated().get(i).getUrl(),fechaAnterior(1),null), Datos_indice[].class);
					else
						json = mapper.readValue(readJsonFromUrl(usrPost.getAggregated().get(i).getUrl(),fechaAnterior(1),"diarios"), Datos_indice[].class);
					usrPost.getAggregated().get(i).setHorasRegistradas(json.length);
					for(int y = 0; y < json.length ;y++)
					{
						System.out.println(y + ")  " + json[y]);
					}
				}
				catch (FileNotFoundException a)
				{
					System.out.println("Pagina no encontrada");
					contadorPaginasNoEncontradas++;
				}
			}
			System.out.println("Hay " + usrPost.getAggregated().size() + " elementos en el json de calidad del aire de cuales hay " + contadorPaginasNoEncontradas + " sin encontrar.");
			System.out.println("Cada uno de los elementos tiene la siguiente cantidad de horas registradas.");
			
			for (int i = 0 ; i < usrPost.getAggregated().size() ; i++)
			{
				System.out.println("Nombre: " + usrPost.getAggregated().get(i).getName());
				System.out.println("Contiene " + usrPost.getAggregated().get(i).getHorasRegistradas());
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private static String readAll(Reader rd, String date,String tipo) throws IOException {
		StringBuilder sb = new StringBuilder();
		int cp;
		while ((cp = rd.read()) != -1) {
		sb.append((char) cp);
			
		if (sb.toString().contains("Date"))
			if(tipo == null)
			{
				if(sb.toString().contains(date))
					if(sb.toString().contains("Hour"))
						if(sb.toString().contains("09:00"))
						{
							String result = sb.toString().substring(sb.toString().indexOf("["),sb.toString().lastIndexOf("}")+1) + " ]";
							return result.toLowerCase();
						}
			}
			else
			{
				if(sb.toString().contains(fechaAnterior(2)))
				{
					String result = sb.toString().substring(sb.toString().indexOf("["),sb.toString().lastIndexOf("}")+1) + " ]";
					return result.toLowerCase();
				}
			}
				
		}
		return sb.toString().substring(sb.toString().indexOf("["),sb.toString().lastIndexOf("]")+1).toLowerCase();
	}
	
	public static String readJsonFromUrl(String url, String date, String tipo) throws IOException {
		InputStream is = new URL(url).openStream();
		try {
			BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
			String jsonText = readAll(rd,date,tipo);
			return jsonText;
		} finally {
			is.close();
		}
	}
	
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
}
