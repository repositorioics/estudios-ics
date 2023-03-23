package ni.org.ics.estudios.domain.muestreoanual;

import java.util.Date;

import javax.persistence.*;

/**
 * Simple objeto de dominio que representa los datos de la toma de muestra
 * 
 * @author Brenda Lopez
 **/

@Entity
@Table(name = "recepcionsero", catalog = "estudios_ics")
public class RecepcionSero {

	/**
	 * 
	 */
	private String id;
    private Integer codigo;
    private Date fechaRecSero;
	private Double volumen;
	private String lugar;
	private String observacion;
	private String username;
	private String estado;
	private Date fecreg;

    @Id
    @Column(name = "id", length = 50, nullable = false)
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Column(name = "codigo", nullable = false, length = 5)
    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    @Column(name = "fecha_sero", nullable = false)
    public Date getFechaRecSero() {
        return fechaRecSero;
    }

    public void setFechaRecSero(Date fechaRecSero) {
        this.fechaRecSero = fechaRecSero;
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
