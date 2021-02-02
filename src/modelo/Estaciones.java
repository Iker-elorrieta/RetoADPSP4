package modelo;
// Generated 2 feb. 2021 1:38:46 by Hibernate Tools 5.4.21.Final

import java.util.HashSet;
import java.util.Set;

/**
 * Estaciones generated by hbm2java
 */
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
	private Set informeses = new HashSet(0);

	public Estaciones() {
	}

	public Estaciones(Municipios municipios, String nombre, String direccion) {
		this.municipios = municipios;
		this.nombre = nombre;
		this.direccion = direccion;
	}

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

	public Set getInformeses() {
		return this.informeses;
	}

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
