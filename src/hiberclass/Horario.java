package hiberclass;
// Generated 21 dic. 2020 22:57:37 by Hibernate Tools 5.4.21.Final

import java.util.Date;

/**
 * Horario generated by hbm2java
 */
public class Horario implements java.io.Serializable {

	private Integer id;
	private Informes informes;
	private Date fecha;
	private String hora;
	private double nogm3;
	private double no2gm3;
	private double noxgm3;
	private double pm10gm3;

	public Horario() {
	}

	public Horario(Informes informes, Date fecha, String hora, double nogm3, double no2gm3, double noxgm3,
			double pm10gm3) {
		this.informes = informes;
		this.fecha = fecha;
		this.hora = hora;
		this.nogm3 = nogm3;
		this.no2gm3 = no2gm3;
		this.noxgm3 = noxgm3;
		this.pm10gm3 = pm10gm3;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Informes getInformes() {
		return this.informes;
	}

	public void setInformes(Informes informes) {
		this.informes = informes;
	}

	public Date getFecha() {
		return this.fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public String getHora() {
		return this.hora;
	}

	public void setHora(String hora) {
		this.hora = hora;
	}

	public double getNogm3() {
		return this.nogm3;
	}

	public void setNogm3(double nogm3) {
		this.nogm3 = nogm3;
	}

	public double getNo2gm3() {
		return this.no2gm3;
	}

	public void setNo2gm3(double no2gm3) {
		this.no2gm3 = no2gm3;
	}

	public double getNoxgm3() {
		return this.noxgm3;
	}

	public void setNoxgm3(double noxgm3) {
		this.noxgm3 = noxgm3;
	}

	public double getPm10gm3() {
		return this.pm10gm3;
	}

	public void setPm10gm3(double pm10gm3) {
		this.pm10gm3 = pm10gm3;
	}
	
	public boolean isNull()
	{
		if(informes != null)
			return false;
		if(nogm3 != 0)
			return false;
		if(no2gm3 != 0)
			return false;
		if(noxgm3 != 0)
			return false;
		if(pm10gm3 != 0)
			return false;
		return true;
	}
}
