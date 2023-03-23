package ni.org.ics.estudios.dto;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by ICS on 03/02/2022.
 */
public class PbmcDto implements Serializable {

    private Integer codigo_pbmc;
    private Integer codigo_participante;
    private String volumen_pbmc;
    private String fecha;
    private String volumen_rojo_adic;
    private boolean tiene_rojo_adic;
    private String observacion;
    private String edadA;
    private String edadM;
    private String estudios;
    private String estado;
    private Date fechaNacimiento;
    private double edadEnMeses;
    private Integer id_serologia;
    private String codigo_casa_familia;
    private Integer codigo_casa_PDCS;
    private String volumen_pbmc_desde_bd;
    private String volumen_adicional_desde_bd;
    private String es_pbmc;

    public PbmcDto() {
    }

    public Integer getCodigo_pbmc() {
        return codigo_pbmc;
    }

    public void setCodigo_pbmc(Integer codigo_pbmc) {
        this.codigo_pbmc = codigo_pbmc;
    }

    public Integer getCodigo_participante() {
        return codigo_participante;
    }

    public void setCodigo_participante(Integer codigo_participante) {
        this.codigo_participante = codigo_participante;
    }

    public String getVolumen_pbmc() {
        return volumen_pbmc;
    }

    public void setVolumen_pbmc(String volumen_pbmc) {
        this.volumen_pbmc = volumen_pbmc;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getVolumen_rojo_adic() {
        return volumen_rojo_adic;
    }

    public void setVolumen_rojo_adic(String volumen_rojo_adic) {
        this.volumen_rojo_adic = volumen_rojo_adic;
    }

    public boolean isTiene_rojo_adic() {
        return tiene_rojo_adic;
    }

    public void setTiene_rojo_adic(boolean tiene_rojo_adic) {
        this.tiene_rojo_adic = tiene_rojo_adic;
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

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public double getEdadEnMeses() {
        return edadEnMeses;
    }

    public void setEdadEnMeses(double edadEnMeses) {
        this.edadEnMeses = edadEnMeses;
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

    public Integer getId_serologia() {
        return id_serologia;
    }

    public void setId_serologia(Integer id_serologia) {
        this.id_serologia = id_serologia;
    }

    public String getVolumen_pbmc_desde_bd() {
        return volumen_pbmc_desde_bd;
    }

    public void setVolumen_pbmc_desde_bd(String volumen_pbmc_desde_bd) {
        this.volumen_pbmc_desde_bd = volumen_pbmc_desde_bd;
    }

    public String getVolumen_adicional_desde_bd() {
        return volumen_adicional_desde_bd;
    }

    public void setVolumen_adicional_desde_bd(String volumen_adicional_desde_bd) {
        this.volumen_adicional_desde_bd = volumen_adicional_desde_bd;
    }

    public String getEs_pbmc() {
        return es_pbmc;
    }

    public void setEs_pbmc(String es_pbmc) {
        this.es_pbmc = es_pbmc;
    }
}
