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
@Table(name = "labpax", catalog = "estudios_ics")
public class LabPax {

	/**
	 * 
	 */
	
	private LabPaxId labPaxId;
	private Integer viaje;
	private String observacion;
	private String username;
	private Date fecreg;

	@EmbeddedId
	public LabPaxId getLabPaxId() {
		return labPaxId;
	}

	public void setLabPaxId(LabPaxId labPaxId) {
		this.labPaxId = labPaxId;
	}

	@Column(name="viaje", nullable = true)
	public Integer getViaje() {
		return viaje;
	}

	public void setViaje(Integer viaje) {
		this.viaje = viaje;
	}

	@Column(name="observacion", nullable = true)
	public String getObservacion() {
		return observacion;
	}

	public void setObservacion(String observacion) {
		this.observacion = observacion;
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
