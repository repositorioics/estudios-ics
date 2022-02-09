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
    private String fecha;
    private char enviado = '0';
    private Double volumen;
    private String observacion;
    private String namePrecepciona;
    private Integer idpersonal;
    private Integer gradilla;
    private String estudios;
    private Integer casaPDCS;
    private String casaChf;
    private Date recordDate;
    private String descripcion;
    private Integer codigoPbmc;

    public SerologiaDto() {
    }

    public SerologiaDto(Integer idSerologia, Integer idparticipante, String nombreCompleto, String fecha, char enviado, Double volumen, String observacion, String namePrecepciona, Integer idpersonal, Integer gradilla, String estudios, Integer casaPDCS, String casaChf, Date recordDate, String descripcion, Integer codigoPbmc) {
        this.idSerologia = idSerologia;
        this.idparticipante = idparticipante;
        this.nombreCompleto = nombreCompleto;
        this.fecha = fecha;
        this.enviado = enviado;
        this.volumen = volumen;
        this.observacion = observacion;
        this.namePrecepciona = namePrecepciona;
        this.idpersonal = idpersonal;
        this.gradilla = gradilla;
        this.estudios = estudios;
        this.casaPDCS = casaPDCS;
        this.casaChf = casaChf;
        this.recordDate = recordDate;
        this.descripcion = descripcion;
        this.codigoPbmc = codigoPbmc;
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

    public char getEnviado() {
        return enviado;
    }

    public void setEnviado(char enviado) {
        this.enviado = enviado;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public Double getVolumen() {
        return volumen;
    }

    public void setVolumen(Double volumen) {
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

    public Integer getCasaPDCS() {
        return casaPDCS;
    }

    public void setCasaPDCS(Integer casaPDCS) {
        this.casaPDCS = casaPDCS;
    }

    public Date getRecordDate() {
        return recordDate;
    }

    public void setRecordDate(Date recordDate) {
        this.recordDate = recordDate;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Integer getCodigoPbmc() {
        return codigoPbmc;
    }

    public void setCodigoPbmc(Integer codigoPbmc) {
        this.codigoPbmc = codigoPbmc;
    }
}
