package ni.org.ics.estudios.dto.cartas;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;

/**
 * Created by Lserrano on 14/07/2023.
 * Los campos que terminan con C se refieren a la tabla cartas_consentimientos y los que terminan con S a la tabla scan_participante_carta
 */
public class ComparacionTutorRelFamDto implements Serializable {

    private Integer codigoParticipante;
    private Date fechaNac;
    private String fechaFirma;
    private BigInteger edadFirma;
    private String usuarioRegistro;
    private String nombreTutorC;
    private String nombreTutorS;
    private String relacionFamiliarC;
    private String relacionFamiliarS;
    private String estudio;
    private String versionCarta;
    private String quienFirmaC;
    private String quienFirmaS;

    public ComparacionTutorRelFamDto(){}


    public ComparacionTutorRelFamDto(Integer codigoParticipante, Date fechaNac, String fechaFirma, BigInteger edadFirma, String usuarioRegistro, String nombreTutorC, String nombreTutorS, String relacionFamiliarC, String relacionFamiliarS, String estudio, String versionCarta, String quienFirmaC, String quienFirmaS) {
        this.codigoParticipante = codigoParticipante;
        this.fechaNac = fechaNac;
        this.fechaFirma = fechaFirma;
        this.edadFirma = edadFirma;
        this.usuarioRegistro = usuarioRegistro;
        this.nombreTutorC = nombreTutorC;
        this.nombreTutorS = nombreTutorS;
        this.relacionFamiliarC = relacionFamiliarC;
        this.relacionFamiliarS = relacionFamiliarS;
        this.estudio = estudio;
        this.versionCarta = versionCarta;
        this.quienFirmaC = quienFirmaC;
        this.quienFirmaS = quienFirmaS;
    }

    public Integer getCodigoParticipante() {
        return codigoParticipante;
    }

    public void setCodigoParticipante(Integer codigoParticipante) {
        this.codigoParticipante = codigoParticipante;
    }

    public Date getFechaNac() {
        return fechaNac;
    }

    public void setFechaNac(Date fechaNac) {
        this.fechaNac = fechaNac;
    }

    public String getFechaFirma() {
        return fechaFirma;
    }

    public void setFechaFirma(String fechaFirma) {
        this.fechaFirma = fechaFirma;
    }

    public BigInteger getEdadFirma() {
        return edadFirma;
    }

    public void setEdadFirma(BigInteger edadFirma) {
        this.edadFirma = edadFirma;
    }

    public String getUsuarioRegistro() {
        return usuarioRegistro;
    }

    public void setUsuarioRegistro(String usuarioRegistro) {
        this.usuarioRegistro = usuarioRegistro;
    }

    public String getNombreTutorC() {
        return nombreTutorC;
    }

    public void setNombreTutorC(String nombreTutorC) {
        this.nombreTutorC = nombreTutorC;
    }

    public String getNombreTutorS() {
        return nombreTutorS;
    }

    public void setNombreTutorS(String nombreTutorS) {
        this.nombreTutorS = nombreTutorS;
    }

    public String getRelacionFamiliarC() {
        return relacionFamiliarC;
    }

    public void setRelacionFamiliarC(String relacionFamiliarC) {
        this.relacionFamiliarC = relacionFamiliarC;
    }

    public String getRelacionFamiliarS() {
        return relacionFamiliarS;
    }

    public void setRelacionFamiliarS(String relacionFamiliarS) {
        this.relacionFamiliarS = relacionFamiliarS;
    }

    public String getEstudio() {
        return estudio;
    }

    public void setEstudio(String estudio) {
        this.estudio = estudio;
    }

    public String getVersionCarta() {
        return versionCarta;
    }

    public void setVersionCarta(String versionCarta) {
        this.versionCarta = versionCarta;
    }

    public String getQuienFirmaC() {
        return quienFirmaC;
    }

    public void setQuienFirmaC(String quienFirmaC) {
        this.quienFirmaC = quienFirmaC;
    }

    public String getQuienFirmaS() {
        return quienFirmaS;
    }

    public void setQuienFirmaS(String quienFirmaS) {
        this.quienFirmaS = quienFirmaS;
    }
}
