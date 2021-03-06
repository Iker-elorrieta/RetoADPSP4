package modelo;
// Generated Jan 21, 2021, 3:38:32 AM by Hibernate Tools 5.4.18.Final

import java.util.HashSet;
import java.util.Set;

/**
 * Informes generated by hbm2java
 */
public class Informes implements java.io.Serializable {

	private Integer id;
	private Estaciones estaciones;
	private String formato;
	private String nombre;
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
	
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
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



	@Override
	public String toString() {
		return "Informes [id=" + id + ", estaciones=" + estaciones + ", formato=" + formato +
				", url=" + url + ", horarios=" + horarios + "]";
	}
}
