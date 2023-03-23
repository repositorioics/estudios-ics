package ni.org.ics.estudios.domain.covid19;


import ni.org.ics.estudios.domain.BaseMetaData;
import ni.org.ics.estudios.domain.Participante;
import ni.org.ics.estudios.domain.audit.Auditable;
import ni.org.ics.estudios.domain.cohortefamilia.CasaCohorteFamilia;
import org.hibernate.annotations.ForeignKey;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Simple objeto de dominio que representa un participante del estudio Covid19
 * 
 * @author Miguel Salinas
 **/

@Entity
@Table(name = "covid_participantes", catalog = "estudios_ics")
public class ParticipanteCovid19 extends BaseMetaData implements Auditable, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
	private Participante participante;
    @Id
    @OneToOne
    @JoinColumn(name = "CODIGO_PARTICIPANTE", referencedColumnName = "CODIGO", nullable = false)
    @ForeignKey(name = "FK_PARTICIPANTE_PARTICIPANTECHF")
	public Participante getParticipante() {
		return participante;
	}

	public void setParticipante(Participante participante) {
		this.participante = participante;
	}

	@Override
    public String toString() {
        return "'" + this.participante.getCodigo() + "'";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ParticipanteCovid19)) return false;

        ParticipanteCovid19 participante = (ParticipanteCovid19) o;

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
