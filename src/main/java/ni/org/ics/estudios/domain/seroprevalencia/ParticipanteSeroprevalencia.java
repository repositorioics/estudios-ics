package ni.org.ics.estudios.domain.seroprevalencia;


import ni.org.ics.estudios.domain.BaseMetaData;
import ni.org.ics.estudios.domain.Participante;
import ni.org.ics.estudios.domain.audit.Auditable;
import ni.org.ics.estudios.domain.cohortefamilia.CasaCohorteFamilia;
import org.hibernate.annotations.ForeignKey;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Simple objeto de dominio que representa un participante del estudio seroprevalencia arbovirus
 * 
 * @author William Aviles
 **/

@Entity
@Table(name = "sa_participante_seroprevalencia", catalog = "estudios_ics")
public class ParticipanteSeroprevalencia extends BaseMetaData implements Auditable, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
	//private String participanteSA;
	private Participante participante;
    private CasaCohorteFamilia casaCHF;
/*
    @Column(name = "CODIGO_SA", nullable = false, length = 50)
    public String getParticipanteSA() {
		return participanteSA;
	}

	public void setParticipanteSA(String participanteSA) {
		this.participanteSA = participanteSA;
	}
*/
    @Id
    @ManyToOne
    @JoinColumn(name = "CODIGO_PARTICIPANTE", referencedColumnName = "CODIGO",  nullable = false)
    @ForeignKey(name = "FK_PARTICIPANTE_PARTICIPANTESA")
	public Participante getParticipante() {
		return participante;
	}

	public void setParticipante(Participante participante) {
		this.participante = participante;
	}

    @ManyToOne
    @JoinColumn(name = "CODIGO_CASA_CHF", nullable = false)
    @ForeignKey(name = "FK_CASACHF_PARTICIPANTESA")
	public CasaCohorteFamilia getCasaCHF() {
		return casaCHF;
	}

	public void setCasaCHF(CasaCohorteFamilia casaCHF) {
		this.casaCHF = casaCHF;
	}

	@Override
    public String toString() {
        return "'" + this.participante.getCodigo() + "'";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ParticipanteSeroprevalencia)) return false;

        ParticipanteSeroprevalencia participante = (ParticipanteSeroprevalencia) o;

        return (!this.participante.getCodigo().equals(participante.getParticipante().getCodigo()));
    }

    @Override
    public int hashCode() {
        return participante.getCodigo().hashCode();
    }

    @Override
    public boolean isFieldAuditable(String fieldname) {
        return true;
    }
}
