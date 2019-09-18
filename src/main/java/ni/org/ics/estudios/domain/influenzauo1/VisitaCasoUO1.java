package ni.org.ics.estudios.domain.influenzauo1;


import ni.org.ics.estudios.domain.BaseMetaData;
import ni.org.ics.estudios.domain.audit.Auditable;
import org.hibernate.annotations.ForeignKey;

import javax.persistence.*;
import java.util.Date;
@Entity
@Table(name = "uo1_visitas_casos", catalog = "estudios_ics")
public class VisitaCasoUO1 extends BaseMetaData implements Auditable {
    private String codigoCasoVisita;
    private ParticipanteCasoUO1 participanteCasoUO1;
    private Date fechaVisita;
    private String visita;
    private String visitaExitosa;
    private String razonVisitaFallida;
    private String otraRazon;
    private String positivoPor;
    private Date fif;
    private String vacunaFlu3Semanas;
    private Date fechaVacuna;
    private String lugar;

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
    @ForeignKey(name = "FK_VISITA_PARTICIPANTEUO1")
    public ParticipanteCasoUO1 getParticipanteCasoUO1() {
        return participanteCasoUO1;
    }

    public void setParticipanteCasoUO1(ParticipanteCasoUO1 participanteCasoUO1) {
        this.participanteCasoUO1 = participanteCasoUO1;
    }

    @Column(name = "FECHA_VISITA", nullable = false)
    public Date getFechaVisita() {
        return fechaVisita;
    }

    public void setFechaVisita(Date fechaVisita) {
        this.fechaVisita = fechaVisita;
    }

    @Column(name = "VISITA", length = 2)
    public String getVisita() {
        return visita;
    }

    public void setVisita(String visita) {
        this.visita = visita;
    }

    @Column(name = "VISITA_EXITOSA", length = 2)
    public String getVisitaExitosa() {
        return visitaExitosa;
    }

    public void setVisitaExitosa(String visitaExitosa) {
        this.visitaExitosa = visitaExitosa;
    }

    @Column(name = "RAZON_VISITA_FALLIDA", length = 10)
    public String getRazonVisitaFallida() {
        return razonVisitaFallida;
    }

    public void setRazonVisitaFallida(String razonVisitaFallida) {
        this.razonVisitaFallida = razonVisitaFallida;
    }

    @Column(name = "OTRA_RAZON_VISITA_FALLIDA")
    public String getOtraRazon() {
        return otraRazon;
    }

    public void setOtraRazon(String otraRazon) {
        this.otraRazon = otraRazon;
    }

    @Column(name = "POSITIVO_POR", length = 2)
    public String getPositivoPor() {
        return positivoPor;
    }

    public void setPositivoPor(String positivoPor) {
        this.positivoPor = positivoPor;
    }

    @Column(name = "FIF")
    public Date getFif() {
        return fif;
    }

    public void setFif(Date fif) {
        this.fif = fif;
    }

    @Column(name = "VACUNA_INFLUENZA", length = 2)
    public String getVacunaFlu3Semanas() {
        return vacunaFlu3Semanas;
    }

    public void setVacunaFlu3Semanas(String vacunaFlu3Semanas) {
        this.vacunaFlu3Semanas = vacunaFlu3Semanas;
    }

    @Column(name = "FECHA_VACUNA")
    public Date getFechaVacuna() {
        return fechaVacuna;
    }

    public void setFechaVacuna(Date fechaVacuna) {
        this.fechaVacuna = fechaVacuna;
    }

    @Column(name = "LUGAR", length = 2)
    public String getLugar() {
        return lugar;
    }

    public void setLugar(String lugar) {
        this.lugar = lugar;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof VisitaCasoUO1)) return false;

        VisitaCasoUO1 that = (VisitaCasoUO1) o;

        if (!codigoCasoVisita.equals(that.getCodigoCasoVisita())) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = codigoCasoVisita.hashCode();
        result = 31 * result + codigoCasoVisita.hashCode();
        return result;
    }

    @Override
    public boolean isFieldAuditable(String fieldname) {
        return true;
    }
}
