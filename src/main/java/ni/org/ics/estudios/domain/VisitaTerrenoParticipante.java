package ni.org.ics.estudios.domain;

import org.hibernate.annotations.ForeignKey;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by FIRSTICT on 08/09/2018.
 * V1.0
 */
@Entity
@Table(name = "visitas_terreno_participante", catalog = "estudios_ics")
public class VisitaTerrenoParticipante extends BaseMetaData {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String codigoVisita;
	private Participante participante;
	private Date fechaVisita;
	private String visitaExitosa;
	private String razonVisitaNoExitosa;
    private String otraRazonVisitaNoExitosa;
    private String dejoCarta;
    private String personaDejoCarta;
    private String relFamPersonaDejoCarta;
    private String personaCasa;
    private String relacionFamPersonaCasa;
    private String otraRelacionPersonaCasa;
    private String telefonoPersonaCasa;
    private String estudio;

    @Id
    @Column(name = "CODIGO", nullable = false, length = 50)
    public String getCodigoVisita() {
        return codigoVisita;
    }

    public void setCodigoVisita(String codigoVisita) {
        this.codigoVisita = codigoVisita;
    }

    @ManyToOne(optional = true)
    @JoinColumn(name="CODIGO_PARTICIPANTE", nullable = false)
    @ForeignKey(name = "FK_PARTICIPANTE_VT")
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

    @Column(name = "VISITA_EXITOSA", nullable = false, length = 5)
    public String getVisitaExitosa() {
        return visitaExitosa;
    }

    public void setVisitaExitosa(String visitaExitosa) {
        this.visitaExitosa = visitaExitosa;
    }

    @Column(name = "RAZON_VISITA_NO_EXITOSA", nullable = true, length = 5)
    public String getRazonVisitaNoExitosa() {
        return razonVisitaNoExitosa;
    }

    public void setRazonVisitaNoExitosa(String razonVisitaNoExitosa) {
        this.razonVisitaNoExitosa = razonVisitaNoExitosa;
    }

    @Column(name = "OTRA_RAZON_VISITA_NO_EXITOSA", nullable = true)
    public String getOtraRazonVisitaNoExitosa() {
        return otraRazonVisitaNoExitosa;
    }

    public void setOtraRazonVisitaNoExitosa(String otraRazonVisitaNoExitosa) {
        this.otraRazonVisitaNoExitosa = otraRazonVisitaNoExitosa;
    }

    @Column(name = "DEJO_CARTA", nullable = true, length = 2)
    public String getDejoCarta() {
        return dejoCarta;
    }

    public void setDejoCarta(String dejoCarta) {
        this.dejoCarta = dejoCarta;
    }

    @Column(name = "PERSONA_DEJO_CARTA", nullable = true, length = 100)
    public String getPersonaDejoCarta() {
        return personaDejoCarta;
    }

    public void setPersonaDejoCarta(String personaDejoCarta) {
        this.personaDejoCarta = personaDejoCarta;
    }

    @Column(name = "RELFAM_PERS_DEJO_CARTA", nullable = true, length = 2)
    public String getRelFamPersonaDejoCarta() {
        return relFamPersonaDejoCarta;
    }

    public void setRelFamPersonaDejoCarta(String relFamPersonaDejoCarta) {
        this.relFamPersonaDejoCarta = relFamPersonaDejoCarta;
    }

    @Column(name = "PERSONA_CASA", nullable = true, length = 100)
    public String getPersonaCasa() {
        return personaCasa;
    }

    public void setPersonaCasa(String personaCasa) {
        this.personaCasa = personaCasa;
    }

    @Column(name = "RELFAM_PERS_CASA", nullable = true, length = 2)
    public String getRelacionFamPersonaCasa() {
        return relacionFamPersonaCasa;
    }

    public void setRelacionFamPersonaCasa(String relacionFamPersonaCasa) {
        this.relacionFamPersonaCasa = relacionFamPersonaCasa;
    }

    @Column(name = "OTRA_RELFAM_PERS_CASA", nullable = true, length = 100)
    public String getOtraRelacionPersonaCasa() {
        return otraRelacionPersonaCasa;
    }

    public void setOtraRelacionPersonaCasa(String otraRelacionPersonaCasa) {
        this.otraRelacionPersonaCasa = otraRelacionPersonaCasa;
    }

    @Column(name = "TELEF_PERSONA_CASA", nullable = true, length = 32)
    public String getTelefonoPersonaCasa() {
        return telefonoPersonaCasa;
    }

    public void setTelefonoPersonaCasa(String telefonoPersonaCasa) {
        this.telefonoPersonaCasa = telefonoPersonaCasa;
    }

    @Column(name = "ESTUDIO", nullable = true, length = 50)
    public String getEstudio() {
        return estudio;
    }

    public void setEstudio(String estudio) {
        this.estudio = estudio;
    }

    @Override
    public String toString() {
        return "'" + codigoVisita + "'";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof VisitaTerrenoParticipante)) return false;

        VisitaTerrenoParticipante visita = (VisitaTerrenoParticipante) o;

        return (!codigoVisita.equals(visita.codigoVisita));
    }

    @Override
    public int hashCode() {
        return codigoVisita.hashCode();
    }
}
