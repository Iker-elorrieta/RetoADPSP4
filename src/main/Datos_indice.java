package main;

public class Datos_indice {

	private String Date;
	private String Hour;
	private String NOgm3;
	private String NO2gm3;
	private String NOXgm3;
	private String PM10gm3;
	
	public String getDate() {
		return Date;
	}
	public String getHour() {
		return Hour;
	}
	public String getNOgm3() {
		return NOgm3;
	}
	public String getNO2gm3() {
		return NO2gm3;
	}
	public String getNOXgm3() {
		return NOXgm3;
	}
	public String getPM10gm3() {
		return PM10gm3;
	}
	public void setDate(String date) {
		Date = date;
	}
	public void setHour(String hour) {
		Hour = hour;
	}
	public void setNOgm3(String nOgm3) {
		NOgm3 = nOgm3;
	}
	public void setNO2gm3(String nO2gm3) {
		NO2gm3 = nO2gm3;
	}
	public void setNOXgm3(String nOXgm3) {
		NOXgm3 = nOXgm3;
	}
	public void setPM10gm3(String pM10gm3) {
		PM10gm3 = pM10gm3;
	}

	@Override
	public String toString() {
		return "Datos_indice [Date=" + Date + ", Hour=" + Hour + ", NOgm3=" + NOgm3 + ", NO2gm3=" + NO2gm3 + ", NOXgm3="
				+ NOXgm3 + ", PM10gm3=" + PM10gm3 + "]";
	}

	
}