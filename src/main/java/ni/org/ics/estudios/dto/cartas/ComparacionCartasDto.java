package ni.org.ics.estudios.dto.cartas;

import java.math.BigInteger;

/**
 * Created by ICS on 28/2/2022.
 */
public class ComparacionCartasDto {

    private Integer codigoParticipante;
    private String fechaFirma;
    private BigInteger edadActual;
    private String usuarioRegistro;
    private String contactoFuturo;
    private String asentimiento;
    private String parteA;
    private String parteB;
    private String parteC;
    private String quienFirma;
    private String relacionFamiliar;
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

    public BigInteger getEdadActual() {
        return edadActual;
    }

    public void setEdadActual(BigInteger edadActual) {
        this.edadActual = edadActual;
    }

    public String getUsuarioRegistro() {
        return usuarioRegistro;
    }

    public void setUsuarioRegistro(String usuarioRegistro) {
        this.usuarioRegistro = usuarioRegistro;
    }

    public String getContactoFuturo() {
        return contactoFuturo;
    }

    public void setContactoFuturo(String contactoFuturo) {
        this.contactoFuturo = contactoFuturo;
    }

    public String getAsentimiento() {
        return asentimiento;
    }

    public void setAsentimiento(String asentimiento) {
        this.asentimiento = asentimiento;
    }

    public String getParteA() {
        return parteA;
    }

    public void setParteA(String parteA) {
        this.parteA = parteA;
    }

    public String getParteB() {
        return parteB;
    }

    public void setParteB(String parteB) {
        this.parteB = parteB;
    }

    public String getParteC() {
        return parteC;
    }

    public void setParteC(String parteC) {
        this.parteC = parteC;
    }

    public String getQuienFirma() {
        return quienFirma;
    }

    public void setQuienFirma(String quienFirma) {
        this.quienFirma = quienFirma;
    }

    public String getRelacionFamiliar() {
        return relacionFamiliar;
    }

    public void setRelacionFamiliar(String relacionFamiliar) {
        this.relacionFamiliar = relacionFamiliar;
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
