package ni.org.ics.estudios.domain.cohortefamilia;

import ni.org.ics.estudios.domain.BaseMetaData;
import org.hibernate.annotations.ForeignKey;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Miguel Salinas on 29/07/2019.
 * V1.0
 */
@Entity
@Table(name = "chf_muestras_superficie", catalog = "estudios_ics")
public class MuestraSuperficie extends BaseMetaData implements Serializable {

    private String codigo;
    private String tipoMuestra;
    private String otraSuperficie;
    private String codigoMx;
    private CasaCohorteFamilia casaChf;
    private ParticipanteCohorteFamilia participanteChf;
    private String visita;
    private String caso;

    @Id
    @Column(name = "CODIGO", length = 36, nullable = false)
    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    @Column(name = "TIPO_MUESTRA", length = 10, nullable = false)
    public String getTipoMuestra() {
        return tipoMuestra;
    }

    public void setTipoMuestra(String tipoMuestra) {
        this.tipoMuestra = tipoMuestra;
    }

    @Column(name = "OTRA_SUPERFICIE", length = 250, nullable = true)
    public String getOtraSuperficie() {
        return otraSuperficie;
    }

    public void setOtraSuperficie(String otraSuperficie) {
        this.otraSuperficie = otraSuperficie;
    }

    @Column(name = "CODIGO_MX", length = 20, nullable = true)
    public String getCodigoMx() {
        return codigoMx;
    }

    public void setCodigoMx(String codigoMx) {
        this.codigoMx = codigoMx;
    }

    @ManyToOne
    @JoinColumn(name = "CODIGO_PARTICIPANTE", referencedColumnName = "CODIGO_PARTICIPANTE", nullable = true)
    @ForeignKey(name = "FK_PARTICIPANTECHF_MUESTRA_SUP")
    public ParticipanteCohorteFamilia getParticipanteChf() {
        return participanteChf;
    }

    public void setParticipanteChf(ParticipanteCohorteFamilia participanteChf) {
        this.participanteChf = participanteChf;
    }

    @ManyToOne
    @JoinColumn(name = "CODIGO_CASACHF", nullable = true)
    @ForeignKey(name = "FK_CASACHF_MUESTRA_SUP")
    public CasaCohorteFamilia getCasaChf() {
        return casaChf;
    }

    public void setCasaChf(CasaCohorteFamilia casaChf) {
        this.casaChf = casaChf;
    }

    @Column(name = "VISITA", length = 2, nullable = true)
    public String getVisita() {
        return visita;
    }

    public void setVisita(String visita) {
        this.visita = visita;
    }

    @Column(name = "CODIGO_CASO", length = 36, nullable = true)
    public String getCaso() {
        return caso;
    }

    public void setCaso(String caso) {
        this.caso = caso;
    }
}
