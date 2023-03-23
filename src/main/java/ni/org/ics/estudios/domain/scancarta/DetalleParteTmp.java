package ni.org.ics.estudios.domain.scancarta;

import ni.org.ics.estudios.domain.BaseMetaData;
import ni.org.ics.estudios.domain.audit.Auditable;
import ni.org.ics.estudios.domain.catalogs.Parte;
import org.hibernate.annotations.ForeignKey;

import javax.persistence.*;

/**
 * Created by ICS on 03/08/2021.
 */
@Entity
@Table(name = "scan_detalle_parte_tmp", catalog = "estudios_ics")
public class DetalleParteTmp extends BaseMetaData implements Auditable {


    private Integer iddetalle;
    ParticipanteCartaTmp participantecartatmp;
    Parte parte;
    private boolean acepta;

    public DetalleParteTmp(){}

    @Id
    @Column(name = "IDDETALLE_TMP", nullable = false, length = 6)
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Integer getIddetalle() {
        return iddetalle;
    }

    public void setIddetalle(Integer iddetalle) {
        this.iddetalle = iddetalle;
    }

    @ManyToOne
    @JoinColumn(name = "ID_PARTICIPANTECARTA_TMP", nullable = false)
    @ForeignKey(name = "FK_ID_PARTICIPANTECARTA_TMP")
    public ParticipanteCartaTmp getParticipantecartatmp() {
        return participantecartatmp;
    }

    public void setParticipantecartatmp(ParticipanteCartaTmp participantecartatmp) {
        this.participantecartatmp = participantecartatmp;
    }

    @ManyToOne
    @JoinColumn(name = "IDPARTE", nullable = false)
    @ForeignKey(name = "FK_IDPARTE_TMP")
    public Parte getParte() {
        return parte;
    }

    public void setParte(Parte parte) {
        this.parte = parte;
    }
    @Column(name = "ACEPTA")
    public boolean isAcepta() {
        return acepta;
    }

    public void setAcepta(boolean acepta) {
        this.acepta = acepta;
    }


    @Override
    public boolean isFieldAuditable(String fieldname) {
        return true;
    }

}
