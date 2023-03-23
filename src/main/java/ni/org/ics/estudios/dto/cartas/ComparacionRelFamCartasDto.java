package ni.org.ics.estudios.dto.cartas;

import java.math.BigInteger;

/**
 * Created by ICS on 28/2/2022.
 * Los campos que terminan con C se refieren a la tabla cartas_consentimientos y los que terminan con S a la tabla scan_participante_carta
 */
public class ComparacionRelFamCartasDto {


    private Integer codigoParticipante;
    private String fechaFirma;
    private BigInteger edadFirma;
    private String usuarioRegistro;
    private String nombre1TutorC;
    private String nombre1TutorS;
    private String nombre2TutorC;
    private String nombre2TutorS;
    private String apellido1TutorC;
    private String apellido1TutorS;
    private String apellido2TutorC;
    private String apellido2TutorS;
    private String relacionFamiliarC;
    private String relacionFamiliarS;
    private String quienFirmaC;
    private String quienFirmaS;
    private String estudio;
    private String versionCarta;

    public Integer getCodigoParticipante() {
        return codigoParticipante;
    }

    public void setCodigoParticipante(Integer codigoParticipante) {
        this.codigoParticipante = codigoParticipante;
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

    public String getNombre1TutorC() {
        return nombre1TutorC;
    }

    public void setNombre1TutorC(String nombre1TutorC) {
        this.nombre1TutorC = nombre1TutorC;
    }

    public String getNombre1TutorS() {
        return nombre1TutorS;
    }

    public void setNombre1TutorS(String nombre1TutorS) {
        this.nombre1TutorS = nombre1TutorS;
    }

    public String getNombre2TutorC() {
        return nombre2TutorC;
    }

    public void setNombre2TutorC(String nombre2TutorC) {
        this.nombre2TutorC = nombre2TutorC;
    }

    public String getNombre2TutorS() {
        return nombre2TutorS;
    }

    public void setNombre2TutorS(String nombre2TutorS) {
        this.nombre2TutorS = nombre2TutorS;
    }

    public String getApellido1TutorC() {
        return apellido1TutorC;
    }

    public void setApellido1TutorC(String apellido1TutorC) {
        this.apellido1TutorC = apellido1TutorC;
    }

    public String getApellido1TutorS() {
        return apellido1TutorS;
    }

    public void setApellido1TutorS(String apellido1TutorS) {
        this.apellido1TutorS = apellido1TutorS;
    }

    public String getApellido2TutorC() {
        return apellido2TutorC;
    }

    public void setApellido2TutorC(String apellido2TutorC) {
        this.apellido2TutorC = apellido2TutorC;
    }

    public String getApellido2TutorS() {
        return apellido2TutorS;
    }

    public void setApellido2TutorS(String apellido2TutorS) {
        this.apellido2TutorS = apellido2TutorS;
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
}
