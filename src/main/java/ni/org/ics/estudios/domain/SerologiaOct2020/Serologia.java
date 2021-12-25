package ni.org.ics.estudios.domain.SerologiaOct2020;

import ni.org.ics.estudios.domain.BaseMetaData;
import ni.org.ics.estudios.domain.Participante;
import ni.org.ics.estudios.domain.audit.Auditable;
import org.hibernate.annotations.ForeignKey;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Created by ICS on 14/10/2020.
 */

@Entity
@Table(name = "serologia_recepcion_lab", catalog = "estudios_ics")
public class Serologia extends BaseMetaData implements Serializable  {

    private static final long serialVersionUID = 1L;

    private Integer idSerologia;
    private Participante participante;
    private Date fecha;
    private double volumen;
    private String observacion;
    private Integer precepciona;
    private int gradilla;
    private char cerrado = '0';
    private String estudio;
    private String casaCHF;
    private Integer edadMeses;
    private Integer codigonuevoparticipante;


    //getter and setter
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "IDSEROLOGIA", nullable = false)
    public Integer getIdSerologia() {
        return idSerologia;
    }

    public void setIdSerologia(Integer idSerologia) {
        this.idSerologia = idSerologia;
    }

    @ManyToOne(optional = true)
    @JoinColumn(name="CODIGO_PARTICIPANTE", updatable = false)
    @ForeignKey(name = "FK_IDPARTICIPANTE")
    public Participante getParticipante() {
        return participante;
    }

    public void setParticipante(Participante participante) {
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


    @Column(name = "PRECEPCIONA", nullable = true)
    public Integer getPrecepciona() {
        return precepciona;
    }

    public void setPrecepciona(Integer precepciona) {
        this.precepciona = precepciona;
    }

    @Column(name = "GRADILLA", nullable = true)
    public int getGradilla() {
        return gradilla;
    }

    public void setGradilla(int gradilla) {
        this.gradilla = gradilla;
    }

    @Column(name = "CERRADO", nullable = true)
    public char getCerrado() {
        return cerrado;
    }

    public void setCerrado(char cerrado) {
        this.cerrado = cerrado;
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


    @Column(name = "EDADMESES", nullable = true)
    public Integer getEdadMeses() {
        return edadMeses;
    }

    public void setEdadMeses(Integer edadMeses) {
        this.edadMeses = edadMeses;
    }

    @Column(name = "CODIGO_NUEVO_PARTICIPANTE", nullable = true)
    public Integer getCodigonuevoparticipante() {
        return codigonuevoparticipante;
    }

    public void setCodigonuevoparticipante(Integer codigonuevoparticipante) {
        this.codigonuevoparticipante = codigonuevoparticipante;
    }

//fin setter



    @Transient
    public boolean isEsCerrado() {
        return this.cerrado == '1';
    }

}
