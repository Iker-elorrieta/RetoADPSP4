package comunes;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
/**
 * Metodo para crear el hash de cualquier string que recibe.
 * @author Grupo 4
 */
public class CrearHash {

	@SuppressWarnings("unused")
	public static String crearHash (String texto){
	    Byte [] hash = null;
	    String resumenAString = "";
	    MessageDigest md;
	    try {
	
	        md = MessageDigest.getInstance("SHA");
	
	        byte dataBytes[] = texto.getBytes();
	        md.update(dataBytes);
	        byte resumen[] = md.digest();
	        resumenAString = new String(resumen);
	
	    } catch (NoSuchAlgorithmException e) {
	        System.out.println("Error hash");
	    }
	    return resumenAString;
	
	}

}
