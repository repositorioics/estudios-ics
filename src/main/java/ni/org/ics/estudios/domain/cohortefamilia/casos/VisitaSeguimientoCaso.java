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
@Table(name = "chf_visitas_casos", catalog = "estudios_ics")
public class VisitaSeguimientoCaso extends BaseMetaData implements Auditable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String codigoCasoVisita;
	private ParticipanteCohorteFamiliaCaso codigoParticipanteCaso;
	private Date fechaVisita;
	private String visita;
	private String horaProbableVisita;
	private String expCS;
	private float temp;
	
    
	@Id
    @Column(name = "CODIGO_VISITA_CASO", length = 50, nullable = false)
	public String getCodigoCasoVisita() {
		return codigoCasoVisita;
	}

	public void setCodigoCasoVisita(String codigoCasoVisita) {
		this.codigoCasoVisita = codigoCasoVisita;
	}

	@ManyToOne
    @JoinColumn(name = "CODIGO_PARTICIPANTE_CASO", nullable = false)
    @ForeignKey(name = "FK_VISITA_PARTICIPANTE")
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

	@Column(name = "VISITA", length = 2, nullable = false)
	public String getVisita() {
		return visita;
	}

	public void setVisita(String visita) {
		this.visita = visita;
	}

	@Column(name = "HORA_PROBABLE", length = 25, nullable = false)
	public String getHoraProbableVisita() {
		return horaProbableVisita;
	}

	public void setHoraProbableVisita(String horaProbableVisita) {
		this.horaProbableVisita = horaProbableVisita;
	}

	@Column(name = "EXP_CS", length = 2, nullable = false)
	public String getExpCS() {
		return expCS;
	}

	public void setExpCS(String expCS) {
		this.expCS = expCS;
	}

	@Column(name = "TEMP", nullable = false)
	public float getTemp() {
		return temp;
	}

	public void setTemp(float temp) {
		this.temp = temp;
	}

	@Override
	public String toString(){
		return codigoParticipanteCaso.getCodigoCaso().getCasa().getCodigoCHF() + "-" + codigoParticipanteCaso.getParticipante().getParticipante().getCodigo() + "-" + codigoParticipanteCaso.getCodigoCaso().getFechaInicio();
	}
	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof VisitaSeguimientoCaso)) return false;

        VisitaSeguimientoCaso that = (VisitaSeguimientoCaso) o;

        if (!codigoCasoVisita.equals(that.codigoCasoVisita)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = codigoCasoVisita.hashCode();
        result = 31 * result + codigoParticipanteCaso.getParticipante().hashCode();
        return result;
    }

	@Override
	public boolean isFieldAuditable(String fieldname) {
		// TODO Auto-generated method stub
		return true;
	}
}
