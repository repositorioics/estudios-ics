package ni.org.ics.estudios.dto;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by ICS on 17/10/2020.
 */
public class SerologiaDto implements Serializable {

    private Integer idSerologia;
    private Integer idparticipante;
    private String nombreCompleto;
    private Date fecha;
    private String cerrado;
    private Integer volumen;
    private String observacion;
    private String namePrecepciona;
    private Integer idpersonal;
    private Integer gradilla;
    private String estudios;
    private String casaChf;
    private Date recordDate;

    public SerologiaDto() {
    }

    public SerologiaDto(Integer idSerologia, Integer idparticipante, String nombreCompleto, Date fecha, String cerrado, Integer volumen, String observacion, String namePrecepciona, Integer idpersonal, Integer gradilla, String estudios, String casaChf, Date recordDate) {
        this.idSerologia = idSerologia;
        this.idparticipante = idparticipante;
        this.nombreCompleto = nombreCompleto;
        this.fecha = fecha;
        this.cerrado = cerrado;
        this.volumen = volumen;
        this.observacion = observacion;
        this.namePrecepciona = namePrecepciona;
        this.idpersonal = idpersonal;
        this.gradilla = gradilla;
        this.estudios = estudios;
        this.casaChf = casaChf;
        this.recordDate = recordDate;
    }

    public Integer getIdSerologia() {
        return idSerologia;
    }

    public void setIdSerologia(Integer idSerologia) {
        this.idSerologia = idSerologia;
    }

    public Integer getIdparticipante() {
        return idparticipante;
    }

    public void setIdparticipante(Integer idparticipante) {
        this.idparticipante = idparticipante;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getCerrado() {
        return cerrado;
    }

    public void setCerrado(String cerrado) {
        this.cerrado = cerrado;
    }

    public Integer getVolumen() {
        return volumen;
    }

    public void setVolumen(Integer volumen) {
        this.volumen = volumen;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public String getNamePrecepciona() {
        return namePrecepciona;
    }

    public void setNamePrecepciona(String namePrecepciona) {
        this.namePrecepciona = namePrecepciona;
    }

    public Integer getIdpersonal() {
        return idpersonal;
    }

    public void setIdpersonal(Integer idpersonal) {
        this.idpersonal = idpersonal;
    }

    public Integer getGradilla() {
        return gradilla;
    }

    public void setGradilla(Integer gradilla) {
        this.gradilla = gradilla;
    }

    public String getEstudios() {
        return estudios;
    }

    public void setEstudios(String estudios) {
        this.estudios = estudios;
    }

    public String getCasaChf() {
        return casaChf;
    }

    public void setCasaChf(String casaChf) {
        this.casaChf = casaChf;
    }

    public Date getRecordDate() {
        return recordDate;
    }

    public void setRecordDate(Date recordDate) {
        this.recordDate = recordDate;
    }
}
