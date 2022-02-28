package ni.org.ics.estudios.dto;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by ICS on 16/02/2022.
 */
public class BhcDto implements Serializable {

    private Integer bhc_id;
    private Integer codigo_participante;
    private String volumen_bhc;
    private String nombreCompleto;
    private Date fecha;
    private String observacion;
    private String edadA;
    private String edadM;
    private String edadD;
    private String estudios;
    private String estado;
    private double edadEnMeses;
    private String codigo_casa_familia;
    private Integer codigo_casa_PDCS;
    private String volumen_bhc_desde_bd;

    public BhcDto(){}

    public BhcDto(Integer bhc_id, Integer codigo_participante, String volumen_bhc, String nombreCompleto, Date fecha, String observacion, String edadA, String edadM, String edadD, String estudios, String estado, double edadEnMeses, String codigo_casa_familia, Integer codigo_casa_PDCS, String volumen_bhc_desde_bd) {
        this.bhc_id = bhc_id;
        this.codigo_participante = codigo_participante;
        this.volumen_bhc = volumen_bhc;
        this.nombreCompleto = nombreCompleto;
        this.fecha = fecha;
        this.observacion = observacion;
        this.edadA = edadA;
        this.edadM = edadM;
        this.edadD = edadD;
        this.estudios = estudios;
        this.estado = estado;
        this.edadEnMeses = edadEnMeses;
        this.codigo_casa_familia = codigo_casa_familia;
        this.codigo_casa_PDCS = codigo_casa_PDCS;
        this.volumen_bhc_desde_bd = volumen_bhc_desde_bd;
    }

    public Integer getBhc_id() {
        return bhc_id;
    }

    public void setBhc_id(Integer bhc_id) {
        this.bhc_id = bhc_id;
    }

    public Integer getCodigo_participante() {
        return codigo_participante;
    }

    public void setCodigo_participante(Integer codigo_participante) {
        this.codigo_participante = codigo_participante;
    }


    public String getVolumen_bhc() {
        return volumen_bhc;
    }

    public void setVolumen_bhc(String volumen_bhc) {
        this.volumen_bhc = volumen_bhc;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public String getEdadA() {
        return edadA;
    }

    public void setEdadA(String edadA) {
        this.edadA = edadA;
    }

    public String getEdadM() {
        return edadM;
    }

    public void setEdadM(String edadM) {
        this.edadM = edadM;
    }

    public String getEstudios() {
        return estudios;
    }

    public void setEstudios(String estudios) {
        this.estudios = estudios;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public double getEdadEnMeses() {
        return edadEnMeses;
    }

    public void setEdadEnMeses(double edadEnMeses) {
        this.edadEnMeses = edadEnMeses;
    }

    public String getEdadD() {
        return edadD;
    }

    public void setEdadD(String edadD) {
        this.edadD = edadD;
    }

    public String getCodigo_casa_familia() {
        return codigo_casa_familia;
    }

    public void setCodigo_casa_familia(String codigo_casa_familia) {
        this.codigo_casa_familia = codigo_casa_familia;
    }

    public Integer getCodigo_casa_PDCS() {
        return codigo_casa_PDCS;
    }

    public void setCodigo_casa_PDCS(Integer codigo_casa_PDCS) {
        this.codigo_casa_PDCS = codigo_casa_PDCS;
    }

    public String getVolumen_bhc_desde_bd() {
        return volumen_bhc_desde_bd;
    }

    public void setVolumen_bhc_desde_bd(String volumen_bhc_desde_bd) {
        this.volumen_bhc_desde_bd = volumen_bhc_desde_bd;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

}
