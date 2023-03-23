package ni.org.ics.estudios.domain.muestreoanual;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

/**
 * Simple objeto de dominio que representa los datos de la toma de muestra
 * 
 * @author Brenda Lopez
 **/

@Entity
@Table(name = "labbhc", catalog = "estudios_ics")
public class LabBHC implements Serializable {

	/**
	 * 
	 */
    private static final long serialVersionUID = 1L;
    private Integer codigo;
    private Date fechaRecBHC;
	private Double volumen;
	private String observacion;
	private String username;
	private Date fecreg;

    @Id
    @Column(name = "codigo", nullable = false, length = 5)
    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    @Id
    @Column(name = "fecha_bhc", nullable = false)
    public Date getFechaRecBHC() {
        return fechaRecBHC;
    }

    public void setFechaRecBHC(Date fechaRecBHC) {
        this.fechaRecBHC = fechaRecBHC;
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

    public boolean equals(Object other) {
        if ((this == other))
            return true;
        if ((other == null))
            return false;
        if (!(other instanceof LabBHCId))
            return false;
        LabBHCId castOther = (LabBHCId) other;
        return (this.getCodigo() == castOther.getCodigo())
                && (this.getFechaRecBHC() == castOther.getFechaRecBHC());
    }

    public int hashCode() {
        int result = 17;
        result = 37 * 3 + this.getCodigo();
        result = 37 * result + Integer.valueOf(this.getFechaRecBHC().toString());
        return result;
    }
}
