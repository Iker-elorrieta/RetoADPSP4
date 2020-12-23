package hiberclass;
// Generated 23 dic. 2020 5:10:52 by Hibernate Tools 5.4.21.Final

import java.util.HashSet;
import java.util.Set;

/**
 * Municipios generated by hbm2java
 */
public class Municipios implements java.io.Serializable {

	private Integer id;
	private String nombre;
	private String descripcion;
	private Double latitud;
	private Double longitud;
	private Integer codigo;
	private Set estacioneses = new HashSet(0);
	private Set entornoses = new HashSet(0);

	public Municipios() {
	}

	public Municipios(String nombre) {
		this.nombre = nombre;
	}

	public Municipios(String nombre, String descripcion, Double latitud, Double longitud, Integer codigo,
			Set estacioneses, Set entornoses) {
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.latitud = latitud;
		this.longitud = longitud;
		this.codigo = codigo;
		this.estacioneses = estacioneses;
		this.entornoses = entornoses;
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

	public Integer getCodigo() {
		return this.codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public Set getEstacioneses() {
		return this.estacioneses;
	}

	public void setEstacioneses(Set estacioneses) {
		this.estacioneses = estacioneses;
	}

	public Set getEntornoses() {
		return this.entornoses;
	}

	public void setEntornoses(Set entornoses) {
		this.entornoses = entornoses;
	}

}
