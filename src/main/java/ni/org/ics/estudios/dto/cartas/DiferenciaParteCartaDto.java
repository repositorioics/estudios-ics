package ni.org.ics.estudios.dto.cartas;

import java.io.Serializable;

/**
 * Created by miguel on 7/1/2022.
 *  * Los campos que terminan con Cc se refieren a la tabla cartas_consentimientos y los que terminan con Sc a la tabla scan_participante_carta
 */
public class DiferenciaParteCartaDto implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer codigo;
    private String fechaFirma;
    private String usuarioRegistro;
    private Integer edadActualMeses;
    private Integer edadMeses;
    private String aceptaParteASc;
    private String aceptaParteACc;
    private String aceptaParteBCc;
    private String aceptaParteBSc;
    private String aceptaParteCCc;
    private String aceptaParteCSc;
    private String aceptaParteDCc;
    private String aceptaParteDSc;
    private String aceptaParteECc;
    private String aceptaParteESc;
    private String aceptaParteFCc;
    private String aceptaParteFSc;
    private String aceptaParteGCc;
    private String aceptaParteGSc;
    private String aceptaContactoFuturoCc;
    private String aceptaContactoFuturoSc;
    private String asentimientoVerbalCc;
    private String asentimientoVerbalSc;
    private Integer estudio;
    private String version;

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public String getFechaFirma() {
        return fechaFirma;
    }

    public void setFechaFirma(String fechaFirma) {
        this.fechaFirma = fechaFirma;
    }

    public String getUsuarioRegistro() {
        return usuarioRegistro;
    }

    public void setUsuarioRegistro(String usuarioRegistro) {
        this.usuarioRegistro = usuarioRegistro;
    }

    public Integer getEdadActualMeses() {
        return edadActualMeses;
    }

    public void setEdadActualMeses(Integer edadActualMeses) {
        this.edadActualMeses = edadActualMeses;
    }

    public Integer getEdadMeses() {
        return edadMeses;
    }

    public void setEdadMeses(Integer edadMeses) {
        this.edadMeses = edadMeses;
    }

    public String getAceptaParteASc() {
        return aceptaParteASc;
    }

    public void setAceptaParteASc(String aceptaParteASc) {
        this.aceptaParteASc = aceptaParteASc;
    }

    public String getAceptaParteACc() {
        return aceptaParteACc;
    }

    public void setAceptaParteACc(String aceptaParteACc) {
        this.aceptaParteACc = aceptaParteACc;
    }

    public String getAceptaParteBCc() {
        return aceptaParteBCc;
    }

    public void setAceptaParteBCc(String aceptaParteBCc) {
        this.aceptaParteBCc = aceptaParteBCc;
    }

    public String getAceptaParteBSc() {
        return aceptaParteBSc;
    }

    public void setAceptaParteBSc(String aceptaParteBSc) {
        this.aceptaParteBSc = aceptaParteBSc;
    }

    public String getAceptaParteCCc() {
        return aceptaParteCCc;
    }

    public void setAceptaParteCCc(String aceptaParteCCc) {
        this.aceptaParteCCc = aceptaParteCCc;
    }

    public String getAceptaParteCSc() {
        return aceptaParteCSc;
    }

    public void setAceptaParteCSc(String aceptaParteCSc) {
        this.aceptaParteCSc = aceptaParteCSc;
    }

    public String getAceptaContactoFuturoCc() {
        return aceptaContactoFuturoCc;
    }

    public void setAceptaContactoFuturoCc(String aceptaContactoFuturoCc) {
        this.aceptaContactoFuturoCc = aceptaContactoFuturoCc;
    }

    public String getAceptaContactoFuturoSc() {
        return aceptaContactoFuturoSc;
    }

    public void setAceptaContactoFuturoSc(String aceptaContactoFuturoSc) {
        this.aceptaContactoFuturoSc = aceptaContactoFuturoSc;
    }

    public String getAsentimientoVerbalCc() {
        return asentimientoVerbalCc;
    }

    public void setAsentimientoVerbalCc(String asentimientoVerbalCc) {
        this.asentimientoVerbalCc = asentimientoVerbalCc;
    }

    public String getAsentimientoVerbalSc() {
        return asentimientoVerbalSc;
    }

    public void setAsentimientoVerbalSc(String asentimientoVerbalSc) {
        this.asentimientoVerbalSc = asentimientoVerbalSc;
    }

    public String getAceptaParteDCc() {
        return aceptaParteDCc;
    }

    public void setAceptaParteDCc(String aceptaParteDCc) {
        this.aceptaParteDCc = aceptaParteDCc;
    }

    public String getAceptaParteDSc() {
        return aceptaParteDSc;
    }

    public void setAceptaParteDSc(String aceptaParteDSc) {
        this.aceptaParteDSc = aceptaParteDSc;
    }

    public String getAceptaParteECc() {
        return aceptaParteECc;
    }

    public void setAceptaParteECc(String aceptaParteECc) {
        this.aceptaParteECc = aceptaParteECc;
    }

    public String getAceptaParteESc() {
        return aceptaParteESc;
    }

    public void setAceptaParteESc(String aceptaParteESc) {
        this.aceptaParteESc = aceptaParteESc;
    }

    public String getAceptaParteFCc() {
        return aceptaParteFCc;
    }

    public void setAceptaParteFCc(String aceptaParteFCc) {
        this.aceptaParteFCc = aceptaParteFCc;
    }

    public String getAceptaParteFSc() {
        return aceptaParteFSc;
    }

    public void setAceptaParteFSc(String aceptaParteFSc) {
        this.aceptaParteFSc = aceptaParteFSc;
    }

    public Integer getEstudio() {
        return estudio;
    }

    public void setEstudio(Integer estudio) {
        this.estudio = estudio;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getAceptaParteGCc() {
        return aceptaParteGCc;
    }

    public void setAceptaParteGCc(String aceptaParteGCc) {
        this.aceptaParteGCc = aceptaParteGCc;
    }

    public String getAceptaParteGSc() {
        return aceptaParteGSc;
    }

    public void setAceptaParteGSc(String aceptaParteGSc) {
        this.aceptaParteGSc = aceptaParteGSc;
    }
}
