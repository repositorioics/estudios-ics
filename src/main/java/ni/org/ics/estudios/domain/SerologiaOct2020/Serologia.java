package ni.org.ics.estudios.domain.SerologiaOct2020;

import ni.org.ics.estudios.domain.BaseMetaData;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by ICS on 14/10/2020.
 */

@Entity
@Table(name = "serologia_recepcion", catalog = "estudios_ics")
public class Serologia extends BaseMetaData implements Serializable  {

    private static final long serialVersionUID = 1L;

    private Integer idSerologia;
    private Integer participante;
    private Date fecha;
    private double volumen;
    private String observacion;
    private String descripcion;
    private char enviado = '0';
    private String estudio;
    private String casaCHF;
    private Integer casaPDCS;
    private double edadMeses;
    private Integer codigoPbmc;
    private Integer volumen_serologia_desde_bd;

    //getter and setter
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID_SEROLOGIA", nullable = false)
    public Integer getIdSerologia() {
        return idSerologia;
    }

    public void setIdSerologia(Integer idSerologia) {
        this.idSerologia = idSerologia;
    }


    @Column(name = "CODIGO_PARTICIPANTE", nullable = false)
    public Integer getParticipante() {
        return participante;
    }

    public void setParticipante(Integer participante) {
        this.participante = participante;
    }

    @Column(name = "FECHA", nullable = true)
    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }


    @Column(name = "VOLUMEN", nullable = true)
    public double getVolumen() {
        return volumen;
    }

    public void setVolumen(double volumen) {
        this.volumen = volumen;
    }

    @Column(name = "OBSERVACION", nullable = true)
    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    @Column(name = "ENVIADO", nullable = true)
    public char getEnviado() {
        return enviado;
    }

    public void setEnviado(char enviado) {
        this.enviado = enviado;
    }

    @Column(name = "ESTUDIO", nullable = true)
    public String getEstudio() {
        return estudio;
    }

    public void setEstudio(String estudio) {
        this.estudio = estudio;
    }

    @Column(name = "CASA_CHF", nullable = true)
    public String getCasaCHF() {
        return casaCHF;
    }

    public void setCasaCHF(String casaCHF) {
        this.casaCHF = casaCHF;
    }


    @Column(name = "EDAD_MESES", nullable = true)
    public double getEdadMeses() {
        return edadMeses;
    }

    public void setEdadMeses(double edadMeses) {
        this.edadMeses = edadMeses;
    }

    @Column(name = "CASA_PDCS", nullable = true)
    public Integer getCasaPDCS() {
        return casaPDCS;
    }

    public void setCasaPDCS(Integer casaPDCS) {
        this.casaPDCS = casaPDCS;
    }

    @Column(name = "DESCRIPCION", nullable = true, length = 100)
    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }


    @Column(name = "ID_PBMC", nullable = true)
    public Integer getCodigoPbmc() {
        return codigoPbmc;
    }

    public void setCodigoPbmc(Integer codigoPbmc) {
        this.codigoPbmc = codigoPbmc;
    }

    @Column(name = "VOLUMEN_SUGERIDO", nullable = true)
    public Integer getVolumen_serologia_desde_bd() {
        return volumen_serologia_desde_bd;
    }

    public void setVolumen_serologia_desde_bd(Integer volumen_serologia_desde_bd) {
        this.volumen_serologia_desde_bd = volumen_serologia_desde_bd;
    }


//fin setter

}
