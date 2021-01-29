package comunes;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Text;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonIOException;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;

public class Xml {

	/**
	 * {@summary Método que comprueba la carpeta de Json y que realiza xml con los
	 * archivos que encuentra}
	 * 
	 * @return boolean
	 */

	public boolean convertirJSONaXML() {

		// Se crea un objeto file con el directorio de Json y se utiliza para recorrer
		// todos los archivos del mismo

		File directorio = new File("json");
		for (File archivo : directorio.listFiles()) {
			if(!archivo.getName().contains("ignore"))
			{
				try {
					JsonParser parser = new JsonParser();
					FileReader fr = new FileReader(archivo.getAbsolutePath());
					JsonElement datos = parser.parse(fr);
	
					JsonArray array = datos.getAsJsonArray();
					Iterator<JsonElement> iter = array.iterator();
	
					DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
					DocumentBuilder builder = factory.newDocumentBuilder();
					DOMImplementation implementation = builder.getDOMImplementation();
					Document documento = implementation.createDocument(null,
							archivo.getName().substring(0, archivo.getName().indexOf(".json")), null);
					documento.setXmlVersion("1.0");
					
					// Integer que sirve para llevar la cuenta del número de objeto dentro de cada Json y dar nombre con él al elemento en el xml
					
					int numero = 0;
	
					while (iter.hasNext()) {
						
						// Recorre todos los objetos del Json y actualiza su número
						
						numero++;
						datos = iter.next();
	
						JsonObject objeto = datos.getAsJsonObject();
	
						Set<Map.Entry<String, JsonElement>> entradas = objeto.entrySet();
						Iterator<Map.Entry<String, JsonElement>> iter2 = entradas.iterator();
						
						// Da nombre al elemento xml en base al nombre del documento json y el número del objeto
						
						Element elemento = documento
								.createElement(archivo.getName().substring(0, archivo.getName().indexOf(".json")) + "-"
										+ String.valueOf(numero));
						while (iter2.hasNext()) {
							
							// Se va leyendo cada atributo del Json y se añade al objeto
	
							Map.Entry<String, JsonElement> datos2 = iter2.next();
							String atributo = datos2.getKey();
							JsonElement valor = datos2.getValue();
							Element parametro = documento.createElement(atributo);
							Text textParametro = documento.createTextNode(valor.getAsString());
							parametro.appendChild(textParametro);
							elemento.appendChild(parametro);
	
						}
						
						// Se añade el objeto al xml
	
						documento.getDocumentElement().appendChild(elemento);
	
					}
					
					// Se crea el documento xml
	
					Source source = new DOMSource(documento);
					Result result = new StreamResult(new File(
							"xml" + "\\" + archivo.getName().substring(0, archivo.getName().indexOf(".json")) + ".xml"));
					Transformer transformer = TransformerFactory.newInstance().newTransformer();
					transformer.setOutputProperty(OutputKeys.INDENT, "yes");
					transformer.setOutputProperty(OutputKeys.METHOD, "xml");
					transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
					transformer.transform(source, result);
	
				} catch (JsonIOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (JsonSyntaxException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (ParserConfigurationException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (TransformerException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

		return true;

	}

}
