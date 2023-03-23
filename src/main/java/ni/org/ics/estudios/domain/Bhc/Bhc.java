package ni.org.ics.estudios.domain.Bhc;

import ni.org.ics.estudios.domain.BaseMetaData;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by ICS on 16/02/2022.
 */

@Entity
@Table(name = "bhc_recepcion", catalog = "estudios_ics")
public class Bhc extends BaseMetaData implements Serializable {

    private Integer bhc_id;
    private Integer codigo_participante;
    private Date fecha_bhc;
    private double volumen;
    private Character enviado ='0';
    private String estudios;
    private double edadMeses;
    private String notas;
    private String observacion;
    private String casa_Familia;
    private Integer casa_PDCS;
    private Integer volumen_bhc_sugerido;


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "BHC_ID", nullable = false)
    public Integer getBhc_id() {
        return bhc_id;
    }

    public void setBhc_id(Integer bhc_id) {
        this.bhc_id = bhc_id;
    }

    @Column(name = "CODIGO_PARTICIPANTE", nullable = false)
    public Integer getCodigo_participante() {
        return codigo_participante;
    }

    public void setCodigo_participante(Integer codigo_participante) {
        this.codigo_participante = codigo_participante;
    }

    @Column(name = "FECHA_TOMA", nullable = true)
    public Date getFecha_bhc() {
        return fecha_bhc;
    }

    public void setFecha_bhc(Date fecha_bhc) {
        this.fecha_bhc = fecha_bhc;
    }

    @Column(name = "VOLUMEN", nullable = false)
    public double getVolumen() {
        return volumen;
    }

    public void setVolumen(double volumen) {
        this.volumen = volumen;
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

    @Column(name = "OBSERVACION", nullable = true)
    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    @Column(name = "CASA_FAMILIA", nullable = true)
    public String getCasa_Familia() {
        return casa_Familia;
    }

    public void setCasa_Familia(String casa_Familia) {
        this.casa_Familia = casa_Familia;
    }

    @Column(name = "CASA_PDCS", nullable = true)
    public Integer getCasa_PDCS() {
        return casa_PDCS;
    }

    public void setCasa_PDCS(Integer casa_PDCS) {
        this.casa_PDCS = casa_PDCS;
    }

    @Column(name = "VOLUMEN_SUGERIDO_BHC", nullable = true)
    public Integer getVolumen_bhc_sugerido() {
        return volumen_bhc_sugerido;
    }

    public void setVolumen_bhc_sugerido(Integer volumen_bhc_sugerido) {
        this.volumen_bhc_sugerido = volumen_bhc_sugerido;
    }
}
