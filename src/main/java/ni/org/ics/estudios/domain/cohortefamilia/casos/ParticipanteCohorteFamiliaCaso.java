package ni.org.ics.estudios.domain.cohortefamilia.casos;

import java.util.Date;

import ni.org.ics.estudios.domain.BaseMetaData;
import ni.org.ics.estudios.domain.audit.Auditable;
import ni.org.ics.estudios.domain.cohortefamilia.ParticipanteCohorteFamilia;

import javax.persistence.*;

import org.hibernate.annotations.ForeignKey;

/**
 * Created by William Aviles on 6/07/2017.
 * V1.0
 */
@Entity
@Table(name = "chf_participantes_casos", catalog = "estudios_ics", uniqueConstraints={@UniqueConstraint(columnNames = {"CODIGO_CASO","CODIGO_PARTICIPANTE"})})
public class ParticipanteCohorteFamiliaCaso extends BaseMetaData implements Auditable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String codigoCasoParticipante;
	private CasaCohorteFamiliaCaso codigoCaso;
	private ParticipanteCohorteFamilia participante;
	private String enfermo;
	private Date fechaEnfermedad;
    private Date fis;
    private String positivoPor;
    
	@Id
    @Column(name = "CODIGO_PARTICIPANTE_CASO", length = 50, nullable = false)
	public String getCodigoCasoParticipante() {
		return codigoCasoParticipante;
	}

	public void setCodigoCasoParticipante(String codigoCasoParticipante) {
		this.codigoCasoParticipante = codigoCasoParticipante;
	}

	@ManyToOne
    @JoinColumn(name = "CODIGO_CASO", nullable = false)
    @ForeignKey(name = "FK_PARTICIPANTE_CASA_CASO")
	public CasaCohorteFamiliaCaso getCodigoCaso() {
		return codigoCaso;
	}

	public void setCodigoCaso(CasaCohorteFamiliaCaso codigoCaso) {
		this.codigoCaso = codigoCaso;
	}
	

	@ManyToOne
    @JoinColumn(name = "CODIGO_PARTICIPANTE", referencedColumnName = "CODIGO_PARTICIPANTE", nullable = false)
    @ForeignKey(name = "FK_PARTICIPANTE_CASO")
	public ParticipanteCohorteFamilia getParticipante() {
		return participante;
	}

	public void setParticipante(ParticipanteCohorteFamilia participante) {
		this.participante = participante;
	}

	@Column(name = "ENFERMO", length = 1, nullable = false)
	public String getEnfermo() {
		return enfermo;
	}

	public void setEnfermo(String enfermo) {
		this.enfermo = enfermo;
	}

	@Column(name = "FECHA_ENFERMEDAD", nullable = true)
	public Date getFechaEnfermedad() {
		return fechaEnfermedad;
	}

	public void setFechaEnfermedad(Date fechaEnfermedad) {
		this.fechaEnfermedad = fechaEnfermedad;
	}

    @Column(name = "FIS", nullable = true)
    public Date getFis() {
        return fis;
    }

    public void setFis(Date fis) {
        this.fis = fis;
    }

    @Column(name = "POSITIVO_POR", length = 2, nullable = true)
    public String getPositivoPor() {
        return positivoPor;
    }

    public void setPositivoPor(String positivoPor) {
        this.positivoPor = positivoPor;
    }

	@Override
	public String toString(){
		return codigoCaso.getCasa().getCodigoCHF() + "-" + participante.getParticipante().getCodigo() + "-" + codigoCaso.getFechaInicio();
	}
	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ParticipanteCohorteFamiliaCaso)) return false;

        ParticipanteCohorteFamiliaCaso that = (ParticipanteCohorteFamiliaCaso) o;

        if (!codigoCasoParticipante.equals(that.codigoCasoParticipante)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = codigoCasoParticipante.hashCode();
        result = 31 * result + participante.hashCode();
        return result;
    }

	@Override
	public boolean isFieldAuditable(String fieldname) {
		// TODO Auto-generated method stub
		return true;
	}
}
