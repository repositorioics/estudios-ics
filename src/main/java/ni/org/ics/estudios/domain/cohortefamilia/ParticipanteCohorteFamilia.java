package ni.org.ics.estudios.domain.cohortefamilia;


import ni.org.ics.estudios.domain.BaseMetaData;
import ni.org.ics.estudios.domain.Participante;
import ni.org.ics.estudios.domain.audit.Auditable;
import org.hibernate.annotations.ForeignKey;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Simple objeto de dominio que representa un participante de los estudios de cohorte de familia
 * 
 * @author William Aviles
 **/

@Entity
@Table(name = "chf_participante_cohorte_familia", catalog = "estudios_ics")
public class ParticipanteCohorteFamilia extends BaseMetaData implements Auditable, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
	//private String participanteCHF;
	private Participante participante;
    private CasaCohorteFamilia casaCHF;

    /*@Column(name = "CODIGO_CHF", nullable = false, length = 50)
    public String getParticipanteCHF() {
		return participanteCHF;
	}

	public void setParticipanteCHF(String participanteCHF) {
		this.participanteCHF = participanteCHF;
	}
*/
    @Id
    @ManyToOne
    @JoinColumn(name = "CODIGO_PARTICIPANTE", referencedColumnName = "CODIGO", nullable = false)
    @ForeignKey(name = "FK_PARTICIPANTE_PARTICIPANTECHF")
	public Participante getParticipante() {
		return participante;
	}

	public void setParticipante(Participante participante) {
		this.participante = participante;
	}

    @ManyToOne
    @JoinColumn(name = "CODIGO_CASA_CHF", nullable = false)
    @ForeignKey(name = "FK_CASACHF_PARTICIPANTECHF")
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
        if (!(o instanceof ParticipanteCohorteFamilia)) return false;

        ParticipanteCohorteFamilia participante = (ParticipanteCohorteFamilia) o;

        return (this.participante.getCodigo().equals(participante.participante.getCodigo()));
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
