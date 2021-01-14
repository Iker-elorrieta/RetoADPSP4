package modelo;
// Generated Jan 3, 2021, 9:45:27 PM by Hibernate Tools 5.4.18.Final

import java.util.HashSet;
import java.util.Set;

/**
 * Municipios generated by hbm2java
 */
@SuppressWarnings("serial")
public class Municipios implements java.io.Serializable {

	private Integer id;
	private String nombre;
	private String descripcion;
	private Double latitud;
	private Double longitud;
	private String codigo;
	@SuppressWarnings("rawtypes")
	private Set estacioneses = new HashSet(0);
	@SuppressWarnings("rawtypes")
	private Set entornosmunis = new HashSet(0);

	public Municipios() {
		
	}

	public Municipios(String nombre) {
		this.nombre = nombre;
	}

	public Municipios(String nombre, String descripcion, Double latitud, Double longitud, String codigo,
			Set estacioneses, Set entornosmunis) {
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.latitud = latitud;
		this.longitud = longitud;
		this.codigo = codigo;
		this.estacioneses = estacioneses;
		this.entornosmunis = entornosmunis;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public String getCodigo() {
		return this.codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	@SuppressWarnings("rawtypes")
	public Set getEstacioneses() {
		return this.estacioneses;
	}

	public void setEstacioneses(@SuppressWarnings("rawtypes") Set estacioneses) {
		this.estacioneses = estacioneses;
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
		return "Municipios [id=" + id + ", nombre=" + nombre + ", descripcion=" + descripcion + ", latitud=" + latitud
				+ ", longitud=" + longitud + ", codigo=" + codigo + ", estacioneses=" + estacioneses
				+ ", entornosmunis=" + entornosmunis + "]";
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
		if(latitud != null)
			return false;
		if(longitud != null)
			return false;
		if(codigo != null)
			return false;
		return true;
	}
}
