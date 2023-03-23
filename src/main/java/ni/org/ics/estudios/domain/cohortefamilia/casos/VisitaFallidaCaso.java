package ni.org.ics.estudios.domain.cohortefamilia.casos;

import java.util.Date;

import ni.org.ics.estudios.domain.BaseMetaData;
import ni.org.ics.estudios.domain.audit.Auditable;
import javax.persistence.*;

import org.hibernate.annotations.ForeignKey;

/**
 * Created by William Aviles on 6/07/2017.
 * V1.0
 */
@Entity
@Table(name = "chf_visitas_fallidas_casos", catalog = "estudios_ics")
public class VisitaFallidaCaso extends BaseMetaData implements Auditable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String codigoFallaVisita;
	private ParticipanteCohorteFamiliaCaso codigoParticipanteCaso;
	private Date fechaVisita;
	private String razonVisitaFallida;
	private String otraRazon;
    private String visita;
    
	@Id
    @Column(name = "CODIGO_VISITA_CASO", length = 50, nullable = false)
	public String getCodigoFallaVisita() {
		return codigoFallaVisita;
	}

	public void setCodigoFallaVisita(String codigoFallaVisita) {
		this.codigoFallaVisita = codigoFallaVisita;
	}

	@ManyToOne
    @JoinColumn(name = "CODIGO_PARTICIPANTE_CASO", nullable = false)
    @ForeignKey(name = "FK_VISITAFALL_PARTICIPANTE")
	public ParticipanteCohorteFamiliaCaso getCodigoParticipanteCaso() {
		return codigoParticipanteCaso;
	}

	public void setCodigoParticipanteCaso(ParticipanteCohorteFamiliaCaso codigoParticipanteCaso) {
		this.codigoParticipanteCaso = codigoParticipanteCaso;
	}
	
	@Column(name = "FECHA_VISITA", nullable = false)
	public Date getFechaVisita() {
		return fechaVisita;
	}

	public void setFechaVisita(Date fechaVisita) {
		this.fechaVisita = fechaVisita;
	}

	@Column(name = "RAZON_VISITA_FALLIDA", length = 10, nullable = false)
	public String getRazonVisitaFallida() {
		return razonVisitaFallida;
	}

	public void setRazonVisitaFallida(String razonVisitaFallida) {
		this.razonVisitaFallida = razonVisitaFallida;
	}

	@Column(name = "OTRA_RAZON_VISITA_FALLIDA", length = 255, nullable = true)
	public String getOtraRazon() {
		return otraRazon;
	}

	public void setOtraRazon(String otraRazon) {
		this.otraRazon = otraRazon;
	}

    @Column(name = "VISITA", length = 2, nullable = true)
    public String getVisita() {
        return visita;
    }

    public void setVisita(String visita) {
        this.visita = visita;
    }

	@Override
	public String toString(){
		return codigoParticipanteCaso.getCodigoCaso().getCasa().getCodigoCHF() + "-" + codigoParticipanteCaso.getParticipante().getParticipante().getCodigo() + "-" + codigoParticipanteCaso.getCodigoCaso().getFechaInicio();
	}
	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof VisitaFallidaCaso)) return false;

        VisitaFallidaCaso that = (VisitaFallidaCaso) o;

        if (!codigoFallaVisita.equals(that.codigoFallaVisita)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = codigoFallaVisita.hashCode();
        result = 31 * result + codigoParticipanteCaso.getParticipante().hashCode();
        return result;
    }

	@Override
	public boolean isFieldAuditable(String fieldname) {
		// TODO Auto-generated method stub
		return true;
	}
}
