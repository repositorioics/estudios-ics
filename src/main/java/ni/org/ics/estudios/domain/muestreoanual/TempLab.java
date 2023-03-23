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
@Table(name = "templab", catalog = "estudios_ics")
public class TempLab {

	/**
	 * 
	 */
	
	private TempLabId tempLabId;
	private Double temperatura;
	private String username;
	private Date fecreg;

	@EmbeddedId
	public TempLabId getTempLabId() {
		return tempLabId;
	}

	public void setTempLabId(TempLabId tempLabId) {
		this.tempLabId = tempLabId;
	}

	@Column(name="temperatura", nullable = true)
	public Double getTemperatura() {
		return temperatura;
	}

	public void setTemperatura(Double temperatura) {
		this.temperatura = temperatura;
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



}
