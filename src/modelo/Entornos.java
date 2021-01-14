package modelo;
// Generated Jan 3, 2021, 9:45:27 PM by Hibernate Tools 5.4.18.Final

import java.util.HashSet;
import java.util.Set;

/**
 * Entornos generated by hbm2java
 */
@SuppressWarnings("serial")
public class Entornos implements java.io.Serializable {

	private Integer id;
	private String nombre;
	private String municipio;
	private String descripcion;
	private String tipo;
	private String territorio;
	private Double latitud;
	private Double longitud;
	@SuppressWarnings("rawtypes")
	private Set entornosmunis = new HashSet(0);

	public Entornos() {
	}

	public Entornos(String nombre) {
		this.nombre = nombre;
	}

	public Entornos(String nombre, String descripcion, String tipo, String territorio, Double latitud, Double longitud,
			@SuppressWarnings("rawtypes") Set entornosmunis) {
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.tipo = tipo;
		this.territorio = territorio;
		this.latitud = latitud;
		this.longitud = longitud;
		this.entornosmunis = entornosmunis;
	}
	
	public void setLatitud(Double latitud) {
		this.latitud = latitud;
	}

	public void setLongitud(Double longitud) {
		this.longitud = longitud;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getMunicipio() {
		return municipio;
	}

	public void setMunicipio(String municipio) {
		this.municipio = municipio;
	}
	
	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getTipo() {
		return this.tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getTerritorio() {
		return this.territorio;
	}

	public void setTerritorio(String territorio) {
		this.territorio = territorio;
	}

	public Double getLatitud() {
		return this.latitud;
	}
	
	/**
	 * Metodo de set latidud modificado para que funcione con la libreria de JSon jackson.
	 * @param latitud
	 */
	public void setLatitud(String latitud) {
		try
		{
			this.latitud = Double.parseDouble(latitud.replace(",", "."));
		}
		catch(NumberFormatException a)
		{
			this.latitud = null;
		}
	}

	public Double getLongitud() {
		return this.longitud;
	}

	/**
	 * Metodo de set longitud modificado para que funcione con la libreria de JSon jackson.
	 * @param latitud
	 */
	public void setLongitud(String longitud) {
		try
		{
			this.longitud = Double.parseDouble(longitud.replace(",", "."));
		}
		catch(NumberFormatException a)
		{
			this.longitud = null;
		}
	}

	@SuppressWarnings("rawtypes")
	public Set getEntornosmunis() {
		return this.entornosmunis;
	}

	public void setEntornosmunis(@SuppressWarnings("rawtypes") Set entornosmunis) {
		this.entornosmunis = entornosmunis;
	}
	
	@Override
	public String toString() {
		return "Entornos [id=" + id + ", nombre=" + nombre + ", descripcion=" + descripcion + ", tipo=" + tipo
				+ ", territorio=" + territorio + ", latitud=" + latitud + ", longitud=" + longitud + ", entornosmunis="
				+ entornosmunis + "]";
	}
	
	/**
	 * Metodo para comprobar si el objeto tiene datos de interes para guardar en la base de datos.
	 */
	public boolean isNull()
	{
		if(nombre != null)
			return false;
		if(descripcion != null)
			return false;
		if(tipo != null)
			return false;
		if(territorio != null)
			return false;
		if(latitud != null)
			return false;
		if(longitud != null)
			return false;
		return true;
	}
}
