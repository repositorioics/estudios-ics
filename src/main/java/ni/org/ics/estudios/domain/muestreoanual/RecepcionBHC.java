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
@Table(name = "recepcionbhc", catalog = "estudios_ics")
public class RecepcionBHC {

	/**
	 * 
	 */
	
	private RecepcionBHCId recBhcId;
	private Boolean paxgene;
	private Double volumen;
	private String lugar;
	private String observacion;
	private String username;
	private String estado;
	private Date fecreg;

	@EmbeddedId
	public RecepcionBHCId getRecBhcId() {
		return recBhcId;
	}

	public void setRecBhcId(RecepcionBHCId recBhcId) {
		this.recBhcId = recBhcId;
	}

	@Column(name="paxgene", nullable = true)
	public Boolean getPaxgene() {
		return paxgene;
	}

	public void setPaxgene(Boolean paxgene) {
		this.paxgene = paxgene;
	}

	@Column(name="volbhc", nullable = true)
	public Double getVolumen() {
		return volumen;
	}

	public void setVolumen(Double volumen) {
		this.volumen = volumen;
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


}
