package ni.org.ics.estudios.domain.muestreoanual;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class TempLabId implements Serializable {
	/**
	 * Objeto que representa la clave unica
	 * 
	 * @author Brenda Lopez
	 **/

	private static final long serialVersionUID = 1L;
	private String horaTempLab;
	private Date fechaTempLab;

	public TempLabId() {

	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof TempLabId))
			return false;
		TempLabId castOther = (TempLabId) other;
		return (this.getHoraTempLab() == castOther.getHoraTempLab())
				&& (this.getFechaTempLab() == castOther.getFechaTempLab());
	}

	public int hashCode() {
		int result = 17;
		result = 37 * result + Integer.valueOf(this.getFechaTempLab().toString());
		return result;
	}

	@Column(name = "hora_temp", nullable = false, length = 15)
	public String getHoraTempLab() {
		return horaTempLab;
	}

	public void setHoraTempLab(String horaTempLab) {
		this.horaTempLab = horaTempLab;
	}

	@Column(name = "fecha_temp", nullable = false)
	public Date getFechaTempLab() {
		return fechaTempLab;
	}

	public void setFechaTempLab(Date fechaTempLab) {
		this.fechaTempLab = fechaTempLab;
	}

}