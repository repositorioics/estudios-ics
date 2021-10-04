package ni.org.ics.estudios.domain.covid19;

import ni.org.ics.estudios.domain.BaseMetaData;
import ni.org.ics.estudios.domain.Participante;
import ni.org.ics.estudios.domain.audit.Auditable;
import org.hibernate.annotations.ForeignKey;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Time;
import java.util.Date;
import java.util.Timer;

/**
 * Created by ICS on 23/11/2020.
 */

@Entity
@Table(name = "covid_otros_positivos", catalog = "estudios_ics")
public class OtrosPositivosCovid extends BaseMetaData implements Serializable {

    private static final long serialVersionUID = 1L;


    private Long codigo;
    private CandidatoTransmisionCovid19 candidatoTransmisionCovid19;
    private  Integer codigo_participante;
    private String casaCHF;
    private Date fis;
    private Date fif;
    private String positivoPor;
    private String estActuales;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CODIGO", nullable = false)
    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }

    @ManyToOne
    @JoinColumn(name="CODIGO_CANDIDATO_INDICE", updatable = false)
    @ForeignKey(name = "FK_CODIGO")
    public CandidatoTransmisionCovid19 getCandidatoTransmisionCovid19() {
        return candidatoTransmisionCovid19;
    }

    public void setCandidatoTransmisionCovid19(CandidatoTransmisionCovid19 candidatoTransmisionCovid19) {
        this.candidatoTransmisionCovid19 = candidatoTransmisionCovid19;
    }
    @Column(name = "CODIGO_PARTICIPANTE", nullable = false)
    public Integer getCodigo_participante() {
        return codigo_participante;
    }

    public void setCodigo_participante(Integer codigo_participante) {
        this.codigo_participante = codigo_participante;
    }

    @Column(name = "CASA_CHF", nullable = true, length = 10)
    public String getCasaCHF() {
        return casaCHF;
    }

    public void setCasaCHF(String casaCHF) {
        this.casaCHF = casaCHF;
    }
    @Column(name = "FIS", nullable = true)
    public Date getFis() {
        return fis;
    }

    public void setFis(Date fis) {
        this.fis = fis;
    }
    @Column(name = "FIF")
    public Date getFif() {
        return fif;
    }

    public void setFif(Date fif) {
        this.fif = fif;
    }
    @Column(name = "POSITIVO_POR", length = 2, nullable = true)
    public String getPositivoPor() {
        return positivoPor;
    }

    public void setPositivoPor(String positivoPor) {
        this.positivoPor = positivoPor;
    }

    @Column(name = "ESTUDIOS_ACTUALES", length = 255, nullable = false)
    public String getEstActuales() {
        return estActuales;
    }

    public void setEstActuales(String estActuales) {
        this.estActuales = estActuales;
    }

}
