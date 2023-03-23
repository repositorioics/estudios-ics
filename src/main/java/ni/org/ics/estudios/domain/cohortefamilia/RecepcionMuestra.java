package ni.org.ics.estudios.domain.cohortefamilia;

import ni.org.ics.estudios.domain.BaseMetaData;
import ni.org.ics.estudios.domain.audit.Auditable;

import javax.persistence.*;
import java.util.Date;

/**
 * Simple objeto de dominio que representa los datos de una recepción de muestra
 * 
 * @author Miguel Salinas
 **/

@Entity
@Table(name = "recepcion_muestras", catalog = "estudios_ics")
public class RecepcionMuestra extends BaseMetaData implements Auditable {

	/**
	 * 
	 */

    private String codigo;
    private String codigoMx;
    private Date fechaRecepcion;
	private String paxgene;
	private Double volumen;
	private String lugar;
	private String observacion;
    private String tubo;
    private String tipoMuestra;
    private String proposito;

    @Id
    @Column(name = "CODIGO", length = 50, nullable = false)
    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    @Column(name = "CODIGO_MX", length = 50, nullable = false)
    public String getCodigoMx() {
        return codigoMx;
    }

    public void setCodigoMx(String codigoMx) {
        this.codigoMx = codigoMx;
    }

    @Column(name = "FECHA_RECEPCION", nullable = false)
    public Date getFechaRecepcion() {
        return fechaRecepcion;
    }

    public void setFechaRecepcion(Date fechaRecepcion) {
        this.fechaRecepcion = fechaRecepcion;
    }

    @Column(name="PAXGENE", nullable = true, length = 1)
	public String getPaxgene() {
		return paxgene;
	}

	public void setPaxgene(String paxgene) {
		this.paxgene = paxgene;
	}

	@Column(name="VOLUMEN", nullable = true)
	public Double getVolumen() {
		return volumen;
	}

	public void setVolumen(Double volumen) {
		this.volumen = volumen;
	}

	@Column(name="OBSERVACION", nullable = true)
	public String getObservacion() {
		return observacion;
	}

	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}

	@Column(name="LUGAR", length = 5, nullable = true)
	public String getLugar() {
		return lugar;
	}

	public void setLugar(String lugar) {
		this.lugar = lugar;
	}

    @Column(name = "TIPO_TUBO", length = 5, nullable = true)
    public String getTubo() {
        return tubo;
    }

    public void setTubo(String tubo) {
        this.tubo = tubo;
    }

    @Column(name = "TIPO_MUESTRA", length = 5, nullable = true)
    public String getTipoMuestra() {
        return tipoMuestra;
    }

    public void setTipoMuestra(String tipoMuestra) {
        this.tipoMuestra = tipoMuestra;
    }

    @Column(name = "PROPOSITO", length = 5, nullable = true)
    public String getProposito() {
        return proposito;
    }

    public void setProposito(String proposito) {
        this.proposito = proposito;
    }


    @Override
    public boolean isFieldAuditable(String fieldname) {
        return true;
    }

    @Override
    public String toString() {
        return "RecepcionMuestra{'" + codigo +'}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RecepcionMuestra)) return false;

        RecepcionMuestra that = (RecepcionMuestra) o;

        if (!codigo.equals(that.codigo)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return codigo.hashCode();
    }
}
