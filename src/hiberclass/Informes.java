package hiberclass;
// Generated 22 dic. 2020 17:17:54 by Hibernate Tools 5.4.18.Final

import java.util.HashSet;
import java.util.Set;

/**
 * Informes generated by hbm2java
 */
public class Informes implements java.io.Serializable {

	private Integer id;
	private Estaciones estaciones;
	private String formato;
	private String url;
	private Set horarios = new HashSet(0);

	public Informes() {
	}

	public Informes(Estaciones estaciones, String url) {
		this.estaciones = estaciones;
		this.url = url;
	}

	public Informes(Estaciones estaciones, String formato, String url, Set horarios) {
		this.estaciones = estaciones;
		this.formato = formato;
		this.url = url;
		this.horarios = horarios;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Estaciones getEstaciones() {
		return this.estaciones;
	}

	public void setEstaciones(Estaciones estaciones) {
		this.estaciones = estaciones;
	}

	public String getFormato() {
		return this.formato;
	}

	public void setFormato(String formato) {
		this.formato = formato;
	}

	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Set getHorarios() {
		return this.horarios;
	}

	public void setHorarios(Set horarios) {
		this.horarios = horarios;
	}

}
