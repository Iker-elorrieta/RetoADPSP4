package hiberclass;
// Generated 23 dic. 2020 23:03:33 by Hibernate Tools 5.4.21.Final

import java.util.HashSet;
import java.util.Set;

/**
 * Entornos generated by hbm2java
 */
public class Entornos implements java.io.Serializable {

	private Integer id;
	private String nombre;
	private String descripcion;
	private String tipo;
	private String territorio;
	private Double latitud;
	private Double longitud;
	private Set entornosmunis = new HashSet(0);

	public Entornos() {
	}

	public Entornos(String nombre) {
		this.nombre = nombre;
	}

	public Entornos(String nombre, String descripcion, String tipo, String territorio, Double latitud, Double longitud,
			Set entornosmunis) {
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.tipo = tipo;
		this.territorio = territorio;
		this.latitud = latitud;
		this.longitud = longitud;
		this.entornosmunis = entornosmunis;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public void setLatitud(Double latitud) {
		this.latitud = latitud;
	}

	public Double getLongitud() {
		return this.longitud;
	}

	public void setLongitud(Double longitud) {
		this.longitud = longitud;
	}

	public Set getEntornosmunis() {
		return this.entornosmunis;
	}

	public void setEntornosmunis(Set entornosmunis) {
		this.entornosmunis = entornosmunis;
	}

	public boolean isNull() {
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
		if(entornosmunis != null)
			return false;
		return true;
	}
	
	
}
