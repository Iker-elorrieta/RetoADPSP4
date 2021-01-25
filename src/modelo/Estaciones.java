package modelo;
// Generated Jan 21, 2021, 3:38:32 AM by Hibernate Tools 5.4.18.Final

import java.util.HashSet;
import java.util.Set;

/**
 * Estaciones generated by hbm2java
 */
@SuppressWarnings("serial")
public class Estaciones implements java.io.Serializable {

	private Integer id;
	private Municipios municipios;
	private String municipio;
	private String nombre;
	private String direccion;
	private Double coordenadaX;
	private Double coordenadaY;
	private Double latitud;
	private Double longitud;
	@SuppressWarnings("rawtypes")
	private Set informeses = new HashSet(0);

	public Estaciones() {
	}

	public Estaciones(Municipios municipios, String nombre, String direccion) {
		this.municipios = municipios;
		this.nombre = nombre;
		this.direccion = direccion;
	}

	@SuppressWarnings("rawtypes")
	public Estaciones(Municipios municipios, String nombre, String direccion, Double coordenadaX, Double coordenadaY,
			Double latitud, Double longitud, Set informeses) {
		this.municipios = municipios;
		this.nombre = nombre;
		this.direccion = direccion;
		this.coordenadaX = coordenadaX;
		this.coordenadaY = coordenadaY;
		this.latitud = latitud;
		this.longitud = longitud;
		this.informeses = informeses;
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
	
	/**
	 * Metodo modificado para poder funcionar con la libreria de json jackson
	 * 
	 */
	public void setCoordenadaX(String coordenadaX) {
		this.coordenadaX = Double.parseDouble(coordenadaX.replace(",", "."));
	}

	/**
	 * Metodo modificado para poder funcionar con la libreria de json jackson
	 * 
	 */
	public void setCoordenadaY(String coordenadaY) {
		this.coordenadaY = Double.parseDouble(coordenadaY.replace(",", "."));
	}

	/**
	 * Metodo modificado para poder funcionar con la libreria de json jackson
	 * 
	 */
	public void setLatitud(String latitud) {
		this.latitud = Double.parseDouble(latitud.replace(",", "."));
	}
	
	/**
	 * Metodo modificado para poder funcionar con la libreria de json jackson
	 * 
	 */
	public void setLongitud(String longitud) {
		this.longitud = Double.parseDouble(longitud.replace(",", "."));
	}
	
	public Municipios getMunicipios() {
		return this.municipios;
	}

	public void setMunicipios(Municipios municipios) {
		this.municipios = municipios;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDireccion() {
		return this.direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public Double getCoordenadaX() {
		return this.coordenadaX;
	}

	public void setCoordenadaX(Double coordenadaX) {
		this.coordenadaX = coordenadaX;
	}

	public Double getCoordenadaY() {
		return this.coordenadaY;
	}

	public void setCoordenadaY(Double coordenadaY) {
		this.coordenadaY = coordenadaY;
	}

	public Double getLatitud() {
		return this.latitud;
	}

	public void setLatitud(Double latitud) {
		this.latitud = latitud;
	}

	public Double getLongitud() {
		return this.longitud;
	}

	public void setLongitud(Double longitud) {
		this.longitud = longitud;
	}

	@SuppressWarnings("rawtypes")
	public Set getInformeses() {
		return this.informeses;
	}

	@SuppressWarnings("rawtypes")
	public void setInformeses(Set informeses) {
		this.informeses = informeses;
	}
	
	@Override
	public String toString() {
		return "Estaciones [id=" + id + ", municipios=" + municipios + ", nombre=" + nombre + ", direccion=" + direccion
				+ ", coordenadaX=" + coordenadaX + ", coordenadaY=" + coordenadaY + ", latitud=" + latitud
				+ ", longitud=" + longitud + ", informeses=" + informeses + "]";
	}

	/**
	 * 
	 * Metodo para comprobar si el objeto tiene datos de interes para guardar en la base de datos.
	 */
	public boolean isNull()
	{
		if(nombre != null)
			return false;
		if(direccion != null)
			return false;
		if(coordenadaX != null)
			return false;
		if(coordenadaY != null)
			return false;
		if(latitud != null)
			return false;
		if(longitud != null)
			return false;
		return true;
	}
}
