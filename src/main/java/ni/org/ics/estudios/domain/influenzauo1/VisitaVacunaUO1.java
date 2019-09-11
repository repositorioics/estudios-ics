package ni.org.ics.estudios.domain.influenzauo1;

import ni.org.ics.estudios.domain.BaseMetaData;
import ni.org.ics.estudios.domain.Participante;
import ni.org.ics.estudios.domain.audit.Auditable;
import org.hibernate.annotations.ForeignKey;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "uo1_visitas_vacunas", catalog = "estudios_ics")
public class VisitaVacunaUO1 extends BaseMetaData implements Auditable {
    private String codigoVisita;
    private Participante participante;
    private Date fechaVisita;
    private String visita;
    private String visitaExitosa;
    private String razonVisitaFallida;
    private String otraRazon;
    private String vacuna;
    private Date fechaVacuna;
    private String tomaMxAntes;
    private String razonNoTomaMx;
    private String segundaDosis;
    private Date fechaSegundaDosis;
    private Date fechaReprogramacion;

    @Id
    @Column(name = "CODIGO_VISITA", length = 36, nullable = false)
    public String getCodigoVisita() {
        return codigoVisita;
    }

    public void setCodigoVisita(String codigoVisita) {
        this.codigoVisita = codigoVisita;
    }

    @ManyToOne
    @JoinColumn(name = "CODIGO_PARTICIPANTE", nullable = false)
    @ForeignKey(name = "FK_VISITAVACUO1_PARTICIPANTE")
    public Participante getParticipante() {
        return participante;
    }

    public void setParticipante(Participante participante) {
        this.participante = participante;
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

    @Column(name = "VACUNA_ADMINISTRADA", length = 2)
    public String getVacuna() {
        return vacuna;
    }

    public void setVacuna(String vacuna) {
        this.vacuna = vacuna;
    }

    @Column(name = "FECHA_VACUNA")
    public Date getFechaVacuna() {
        return fechaVacuna;
    }

    public void setFechaVacuna(Date fechaVacuna) {
        this.fechaVacuna = fechaVacuna;
    }

    @Column(name = "TOMA_MX_ANTES_VACUNA", length = 2)
    public String getTomaMxAntes() {
        return tomaMxAntes;
    }

    public void setTomaMxAntes(String tomaMxAntes) {
        this.tomaMxAntes = tomaMxAntes;
    }

    @Column(name = "RAZON_NO_TOMA_MX", length = 500)
    public String getRazonNoTomaMx() {
        return razonNoTomaMx;
    }

    public void setRazonNoTomaMx(String razonNoTomaMx) {
        this.razonNoTomaMx = razonNoTomaMx;
    }

    @Column(name = "RECIBIO_SEGUNDA_DOSIS", length = 2)
    public String getSegundaDosis() {
        return segundaDosis;
    }

    public void setSegundaDosis(String segundaDosis) {
        this.segundaDosis = segundaDosis;
    }

    @Column(name = "FECHA_SEGUNDA_DOSIS")
    public Date getFechaSegundaDosis() {
        return fechaSegundaDosis;
    }

    public void setFechaSegundaDosis(Date fechaSegundaDosis) {
        this.fechaSegundaDosis = fechaSegundaDosis;
    }

    @Column(name = "FECHA_REPROGRAMACION")
    public Date getFechaReprogramacion() {
        return fechaReprogramacion;
    }

    public void setFechaReprogramacion(Date fechaReprogramacion) {
        this.fechaReprogramacion = fechaReprogramacion;
    }

    @Override
    public String toString() {
        return "VisitaVacunaUO1{" + codigoVisita + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VisitaVacunaUO1 that = (VisitaVacunaUO1) o;
        return (!codigoVisita.equals(that.getCodigoVisita()));
    }

    @Override
    public int hashCode() {
        int result = codigoVisita.hashCode();
        result = 31 * result + codigoVisita.hashCode();
        return result;
    }

    @Override
    public boolean isFieldAuditable(String fieldname) {
        return true;
    }

}
