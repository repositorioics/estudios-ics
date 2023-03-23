package ni.org.ics.estudios.domain.muestreoanual;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class LabPbmcId implements Serializable {
	/**
	 * Objeto que representa la clave unica
	 * 
	 * @author Brenda Lopez
	 **/

	private static final long serialVersionUID = 1L;
	private Integer codigo;
	private Date fechaRecPbmc;

	public LabPbmcId() {

	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof LabPbmcId))
			return false;
		LabPbmcId castOther = (LabPbmcId) other;
		return (this.getCodigo() == castOther.getCodigo())
				&& (this.getFechaRecPbmc() == castOther.getFechaRecPbmc());
	}

	public int hashCode() {
		int result = 17;
		result = 37 * 3 + this.getCodigo();
		result = 37 * result + Integer.valueOf(this.getFechaRecPbmc().toString());
		return result;
	}

	@Column(name = "codigo", nullable = false, length = 5)
	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	@Column(name = "fecha_pbmc", nullable = false)
	public Date getFechaRecPbmc() {
		return fechaRecPbmc;
	}

	public void setFechaRecPbmc(Date fechaRecPbmc) {
		this.fechaRecPbmc = fechaRecPbmc;
	}

}