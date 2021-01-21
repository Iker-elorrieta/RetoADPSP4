package modelo;
// Generated Jan 21, 2021, 3:38:32 AM by Hibernate Tools 5.4.18.Final

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Horario generated by hbm2java
 */
public class Horario implements java.io.Serializable {

	private Integer id;
	private Informes informes;
	private Date fecha;
	private String hora;
	private Double comgm3;
	private Double nogm3;
	private Double no2;
	private String no2ica;
	private Double noxgm3;
	private Double pm10;
	private String pm10ica;
	private Double pm25;
	private String pm25ica;
	private Double so2;
	private String so2ica;
	private String icaestacion;

	public Horario() {
	}

	public Horario(Informes informes) {
		this.informes = informes;
	}

	public Horario(Informes informes, Date fecha, String hora, Double comgm3, Double nogm3, Double no2, String no2ica,
			Double noxgm3, Double pm10, String pm10ica, Double pm25, String pm25ica, Double so2, String so2ica,
			String icaestacion) {
		this.informes = informes;
		this.fecha = fecha;
		this.hora = hora;
		this.comgm3 = comgm3;
		this.nogm3 = nogm3;
		this.no2 = no2;
		this.no2ica = no2ica;
		this.noxgm3 = noxgm3;
		this.pm10 = pm10;
		this.pm10ica = pm10ica;
		this.pm25 = pm25;
		this.pm25ica = pm25ica;
		this.so2 = so2;
		this.so2ica = so2ica;
		this.icaestacion = icaestacion;
	}
	
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	public void setFecha(String fecha) {
		try {
			this.fecha = new SimpleDateFormat("dd/MM/yyyy").parse(fecha);
		} catch (ParseException e) {
			this.fecha = null;
		}
	}
	
	public void setValor1(String comgm3) {
		try
		{
			this.comgm3 = Double.parseDouble(comgm3.replace(",", "."));
		}
		catch(NumberFormatException a)
		{
			this.comgm3 = (double) 0;
		}
	}
	
	public void setValor2(String nogm3) {
		try
		{
			this.nogm3 = Double.parseDouble(nogm3.replace(",", "."));
		}
		catch(NumberFormatException a)
		{
			this.nogm3 = (double) 0;
		}
	}
	
	public void setValor3(String no2) {
		try
		{
			this.no2 = Double.parseDouble(no2.replace(",", "."));
		}
		catch(NumberFormatException a)
		{
			this.no2 = (double) 0;
		}
	}
	
	public void setValor4(String noxgm3) {
		try
		{
			this.noxgm3 = Double.parseDouble(noxgm3.replace(",", "."));
		}
		catch(NumberFormatException a)
		{
			this.noxgm3 = (double) 0;
		}
	}

	public void setValor5(String pm10) {
		try
		{
			this.pm10 = Double.parseDouble(pm10.replace(",", "."));
		}
		catch(NumberFormatException a)
		{
			this.pm10 = (double) 0;
		}
	}
	
	public void setValor6(String pm25) {
		try
		{
			this.pm25 = Double.parseDouble(pm25.replace(",", "."));
		}
		catch(NumberFormatException a)
		{
			this.pm25 = (double) 0;
		}
	}
	
	public void setValor7(String so2) {
		try
		{
			this.so2 = Double.parseDouble(so2.replace(",", "."));
		}
		catch(NumberFormatException a)
		{
			this.so2 = (double) 0;
		}
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

	public Double getComgm3() {
		return this.comgm3;
	}

	public void setComgm3(Double comgm3) {
		this.comgm3 = comgm3;
	}

	public Double getNogm3() {
		return this.nogm3;
	}

	public void setNogm3(Double nogm3) {
		this.nogm3 = nogm3;
	}

	public Double getNo2() {
		return this.no2;
	}

	public void setNo2(Double no2) {
		this.no2 = no2;
	}

	public String getNo2ica() {
		return this.no2ica;
	}

	public void setNo2ica(String no2ica) {
		this.no2ica = no2ica;
	}

	public Double getNoxgm3() {
		return this.noxgm3;
	}

	public void setNoxgm3(Double noxgm3) {
		this.noxgm3 = noxgm3;
	}

	public Double getPm10() {
		return this.pm10;
	}

	public void setPm10(Double pm10) {
		this.pm10 = pm10;
	}

	public String getPm10ica() {
		return this.pm10ica;
	}

	public void setPm10ica(String pm10ica) {
		this.pm10ica = pm10ica;
	}

	public Double getPm25() {
		return this.pm25;
	}

	public void setPm25(Double pm25) {
		this.pm25 = pm25;
	}

	public String getPm25ica() {
		return this.pm25ica;
	}

	public void setPm25ica(String pm25ica) {
		this.pm25ica = pm25ica;
	}

	public Double getSo2() {
		return this.so2;
	}

	public void setSo2(Double so2) {
		this.so2 = so2;
	}

	public String getSo2ica() {
		return this.so2ica;
	}

	public void setSo2ica(String so2ica) {
		this.so2ica = so2ica;
	}

	public String getIcaestacion() {
		return this.icaestacion;
	}

	public void setIcaestacion(String icaestacion) {
		this.icaestacion = icaestacion;
	}

	@Override
	public String toString() {
		return "Horario [id=" + id + ", fecha=" + fecha + ", hora=" + hora + ", comgm3="
				+ comgm3 + ", nogm3=" + nogm3 + ", no2=" + no2 + ", no2ica=" + no2ica + ", noxgm3=" + noxgm3 + ", pm10="
				+ pm10 + ", pm10ica=" + pm10ica + ", pm25=" + pm25 + ", pm25ica=" + pm25ica + ", so2=" + so2
				+ ", so2ica=" + so2ica + ", icaestacion=" + icaestacion + "]";
	}
	
	public boolean isNull()
	{
		if(comgm3 != null)
			return false;
		if(nogm3 != null)
			return false;
		if(no2 != null)
			return false;
		if(no2ica != null)
			return false;
		if(pm10 != null)
			return false;
		if(pm10ica != null)
			return false;
		if(pm25 != null)
			return false;
		if(pm25ica != null)
			return false;
		if(so2 != null)
			return false;
		if(so2ica != null)
			return false;
		if(icaestacion != null)
			return false;
		return true;
	}
}
