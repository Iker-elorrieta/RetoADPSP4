package modelo;
// Generated Jan 21, 2021, 3:38:32 AM by Hibernate Tools 5.4.18.Final

import java.util.HashSet;
import java.util.Set;

/**
 * Provincias generated by hbm2java
 */
@SuppressWarnings("serial")
public class Provincias implements java.io.Serializable {

	private Integer id;
	private String nombre;
	@SuppressWarnings("rawtypes")
	private Set municipioses = new HashSet(0);

	public Provincias() {
	}

	public Provincias(String nombre) {
		this.nombre = nombre;
	}

	@SuppressWarnings("rawtypes")
	public Provincias(String nombre, Set municipioses) {
		this.nombre = nombre;
		this.municipioses = municipioses;
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

	@SuppressWarnings("rawtypes")
	public Set getMunicipioses() {
		return this.municipioses;
	}

	@SuppressWarnings("rawtypes")
	public void setMunicipioses(Set municipioses) {
		this.municipioses = municipioses;
	}

}
