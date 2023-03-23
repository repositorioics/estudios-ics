package ni.org.ics.estudios.domain.muestreoanual;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Simple objeto de dominio que representa los datos de la toma de muestra
 * 
 * @author Brenda Lopez
 **/

@Entity
@Table(name = "temp_pbmc", catalog = "estudios_ics")
public class TempPbmc {

	/**
	 * 
	 */
	
	private TempPbmcId tempPbmcId;
	private Double temperatura;
	private String lugar;
	private String observacion;
	private String username;
	private String estado;
	private Date fecreg;

	@EmbeddedId
	public TempPbmcId getTempPbmcId() {
		return tempPbmcId;
	}

	public void setTempPbmcId(TempPbmcId tempPbmcId) {
		this.tempPbmcId = tempPbmcId;
	}

	@Column(name="observacion", nullable = true)
	public String getObservacion() {
		return observacion;
	}

	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}

	@Column(name="lugar", nullable = true)
	public String getLugar() {
		return lugar;
	}

	public void setLugar(String lugar) {
		this.lugar = lugar;
	}
	
	@Column(name="username", nullable = true)
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Column(name="fecha_registro", nullable = true)
	public Date getFecreg() {
		return fecreg;
	}

	public void setFecreg(Date fecreg) {
		this.fecreg = fecreg;
	}

	@Column(name="estado", nullable = true)
	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	@Column(name="temperatura", nullable = true)
	public Double getTemperatura() {
		return temperatura;
	}

	public void setTemperatura(Double temperatura) {
		this.temperatura = temperatura;
	}

}
