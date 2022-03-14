package ni.org.ics.estudios.domain.Pbmc;

import ni.org.ics.estudios.domain.BaseMetaData;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by ICS on 03/02/2022.
 */

@Entity
@Table(name = "pbmc_recepcion", catalog = "estudios_ics")
public class Pbmc extends BaseMetaData implements Serializable{

    private Integer pbcm_id;
    private Integer codigo_participante;
    private Date fecha_pbmc;
    private double volumen;
    private String observacion;
    private Character pbmc_tiene_serologia ='0';
    private Character enviado ='0';
    private String estudios;
    private double edadMeses;
    private String notas;
    private String casaCHF;
    private Integer casaPDCS;
    private Integer volumen_pbmc_desde_bd;

    /* getter setter */

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID_PBMC", nullable = false)
    public Integer getPbcm_id() {
        return pbcm_id;
    }

    public void setPbcm_id(Integer pbcm_id) {
        this.pbcm_id = pbcm_id;
    }

    @Column(name = "CODIGO_PARTICIPANTE", nullable = false)
    public Integer getCodigo_participante() {
        return codigo_participante;
    }

    public void setCodigo_participante(Integer codigo_participante) {
        this.codigo_participante = codigo_participante;
    }

    @Column(name = "FECHA", nullable = true)
    public Date getFecha_pbmc() {
        return fecha_pbmc;
    }

    public void setFecha_pbmc(Date fecha_pbmc) {
        this.fecha_pbmc = fecha_pbmc;
    }

    @Column(name = "VOLUMEN", nullable = false)
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


    @Column(name = "TIENE_SEROLOGIA", nullable = true)
    public Character getPbmc_tiene_serologia() {
        return pbmc_tiene_serologia;
    }

    public void setPbmc_tiene_serologia(Character pbmc_tiene_serologia) {
        this.pbmc_tiene_serologia = pbmc_tiene_serologia;
    }

    @Column(name = "ENVIADO", nullable = true)
    public Character getEnviado() {
        return enviado;
    }

    public void setEnviado(Character enviado) {
        this.enviado = enviado;
    }

    @Column(name = "ESTUDIO", nullable = true)
    public String getEstudios() {
        return estudios;
    }

    public void setEstudios(String estudios) {
        this.estudios = estudios;
    }

    @Column(name = "EDAD_MESES", nullable = true)
    public double getEdadMeses() {
        return edadMeses;
    }

    public void setEdadMeses(double edadMeses) {
        this.edadMeses = edadMeses;
    }

    @Column(name = "NOTAS", nullable = true)
    public String getNotas() {
        return notas;
    }

    public void setNotas(String notas) {
        this.notas = notas;
    }

    @Column(name = "CASA_PDCS", nullable = true)
    public Integer getCasaPDCS() {
        return casaPDCS;
    }

    public void setCasaPDCS(Integer casaPDCS) {
        this.casaPDCS = casaPDCS;
    }

    @Column(name = "CASA_CHF", nullable = true)
    public String getCasaCHF() {
        return casaCHF;
    }

    public void setCasaCHF(String casaCHF) {
        this.casaCHF = casaCHF;
    }

    @Column(name = "VOLUMEN_SUGERIDO_PBMC", nullable = true)
    public Integer getVolumen_pbmc_desde_bd() {
        return volumen_pbmc_desde_bd;
    }

    public void setVolumen_pbmc_desde_bd(Integer volumen_pbmc_desde_bd) {
        this.volumen_pbmc_desde_bd = volumen_pbmc_desde_bd;
    }

    @Transient
    public boolean isEsPositivo() {
        return this.enviado == '1';
    }

}
