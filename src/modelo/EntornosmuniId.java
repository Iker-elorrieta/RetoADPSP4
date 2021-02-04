package modelo;
// Generated 1 feb. 2021 20:13:50 by Hibernate Tools 5.4.21.Final

/**
 * EntornosmuniId generated by hbm2java
 */
public class EntornosmuniId implements java.io.Serializable {

	private int entorno;
	private int municipio;

	public EntornosmuniId() {
	}

	public EntornosmuniId(int entorno, int municipio) {
		this.entorno = entorno;
		this.municipio = municipio;
	}

	public int getEntorno() {
		return this.entorno;
	}

	public void setEntorno(int entorno) {
		this.entorno = entorno;
	}

	public int getMunicipio() {
		return this.municipio;
	}

	public void setMunicipio(int municipio) {
		this.municipio = municipio;
	}

	@Override
	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof EntornosmuniId))
			return false;
		EntornosmuniId castOther = (EntornosmuniId) other;

		return (this.getEntorno() == castOther.getEntorno()) && (this.getMunicipio() == castOther.getMunicipio());
	}

	@Override
	public int hashCode() {
		int result = 17;

		result = 37 * result + this.getEntorno();
		result = 37 * result + this.getMunicipio();
		return result;
	}

}
