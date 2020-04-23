package ni.org.ics.estudios.domain.scancarta;

import ni.org.ics.estudios.domain.BaseMetaData;
import ni.org.ics.estudios.domain.audit.Auditable;
import ni.org.ics.estudios.domain.catalogs.Parte;
import org.hibernate.annotations.ForeignKey;

import javax.persistence.*;

/**
 * Created by ICS on 14/01/2020.
 */

@Entity
@Table(name = "detalleparte", catalog = "estudios_ics", uniqueConstraints = { @UniqueConstraint(columnNames = "iddetalle") })
public class  DetalleParte extends BaseMetaData implements Auditable {

    private static final long serialVersionUID = 1L;

    private Integer iddetalle;
    ParticipanteCarta participantecarta;
    Parte parte;
    private Boolean acepta;

    @Id
    @Column(name = "iddetalle", nullable = false, length = 6)
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Integer getIddetalle() {
        return iddetalle;
    }

    public void setIddetalle(Integer iddetalle) {
        this.iddetalle = iddetalle;
    }


    @ManyToOne
    @JoinColumn(name = "idparticipantecarta", nullable = false)
    @ForeignKey(name = "FK_IDPARTICIPANTECARTA")
    public ParticipanteCarta getParticipantecarta() {
        return participantecarta;
    }

    public void setParticipantecarta(ParticipanteCarta participantecarta) {
        this.participantecarta = participantecarta;
    }

    @ManyToOne
    @JoinColumn(name = "idparte", nullable = false)
    @ForeignKey(name = "FK_IDPARTE")
    public Parte getParte() {
        return parte;
    }

    public void setParte(Parte parte) {
        this.parte = parte;
    }
    @Column(name = "acepta")
    public Boolean getAcepta() {
        return acepta;
    }

    public void setAcepta(Boolean acepta) {
        this.acepta = acepta;
    }

    @Override
    public boolean isFieldAuditable(String fieldname) {
        return true;
    }

}
