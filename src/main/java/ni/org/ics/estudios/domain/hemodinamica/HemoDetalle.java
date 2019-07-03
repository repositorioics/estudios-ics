package ni.org.ics.estudios.domain.hemodinamica;

import ni.org.ics.estudios.domain.BaseMetaData;
import org.hibernate.annotations.ForeignKey;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

import java.util.Date;

/**
 * Created by ICS_Inspiron3 on 21/05/2019.
 */

@Entity
@Table(name = "hemodetalle", catalog = "estudios_ics")
public class HemoDetalle extends BaseMetaData {
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    /**
     *
     */
    private String idHemoDetalle;
    private String densidadUrinaria;
    private  String diuresis;
    private String extremidades;
    private String fc;
    private Date fecha;
    private String fr;
    private String hora;
    private  String llenadoCapilar;
    private String nivelConciencia;
    private String pulsoCalidad;
    private  String sa;
    private String signo;
    private String tc;
    private DatosHemodinamica datoshemodinamica;
    private String personaValida;
    private Character impreso;
    private String pa;
    private String pp;
    private String pam;
    private String dx;
/* Getter y Setter */
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid2")
    @Column(name = "idHemoDetalle", nullable = false)
    public String getIdHemoDetalle () {
            return idHemoDetalle;
    }
    public void setIdHemoDetalle (String idHemoDetalle){
        this.idHemoDetalle = idHemoDetalle;
    }

    @Column(name = "densidadUrinaria", nullable = false)
    public String getDensidadUrinaria() {
        return densidadUrinaria;
    }
    public void setDensidadUrinaria(String DensidadUrinaria) {
        this.densidadUrinaria = DensidadUrinaria;
    }

    @Column(name = "diuresis", nullable = false)
    public String getDiuresis() {
        return diuresis;
    }

    public void setDiuresis(String Diuresis) {
        this.diuresis = Diuresis;
    }

    @Column(name = "extremidades", nullable = false)
    public String getExtremidades() {
        return extremidades;
    }

    public void setExtremidades(String Extremidades) {
        this.extremidades = Extremidades;
    }

    @Column(name = "fc", nullable = false)
    public String getFc() {
        return fc;
    }

    public void setFc(String Fc) {
        this.fc = Fc;
    }


    @Column(name = "fecha", nullable = false)
    public Date getFecha(){
        return fecha;
    }
    public  void setFecha(Date Fecha){
        this.fecha = Fecha;
    }

    @Column(name = "fr", nullable = false)
    public String getFr() {
        return fr;
    }

    public void setFr(String Fr) {
        this.fr = Fr;
    }


    @Column(name = "hora", nullable = false)
    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    @Column(name = "llenadoCapilar", nullable = false)
    public String getLlenadoCapilar() {
        return llenadoCapilar;
    }

    public void setLlenadoCapilar(String LlenadoCapilar) {
        this.llenadoCapilar = LlenadoCapilar;
    }

    @Column(name = "nivelConciencia", nullable = false)
    public String getNivelConciencia() {
        return nivelConciencia;
    }

    public void setNivelConciencia(String nivelConciencia) {
        this.nivelConciencia = nivelConciencia;
    }

    @Column(name = "pulsoCalidad", nullable = false)
    public String getPulsoCalidad() {
        return pulsoCalidad;
    }

    public void setPulsoCalidad(String PulsoCalidad) {
        this.pulsoCalidad = PulsoCalidad;
    }

    @Column(name = "sa", nullable = false)
    public String getSa() {
        return sa;
    }

    public void setSa(String Sa) {
        this.sa = Sa;
    }

    @Column(name = "signo", nullable = false)
    public String getSigno() {
        return signo;
    }
    public void setSigno(String Signo) {
        this.signo = Signo;
    }

    @Column(name = "tc", nullable = false)
    public String getTc() {
        return tc;
    }

    public void setTc(String Tc) {
        this.tc = Tc;
    }


    @ManyToOne
    @JoinColumn(name="idDatoHemo", updatable = false)
    @ForeignKey(name = "FK_idDatoHemo")
    public DatosHemodinamica getDatoshemodinamica(){
        return datoshemodinamica;
    }
    public void setDatoshemodinamica(DatosHemodinamica datoshemodinamica) {
        this.datoshemodinamica = datoshemodinamica;
    }


    @Column(name = "personaValida", nullable = false)
    public String getPersonaValida () {
        return personaValida;
    }
    public void setPersonaValida  (String personaValida) {
       this.personaValida = personaValida;
    }

    @Column(name = "impreso", nullable = false)
    public Character getImpreso(){return impreso;}

    public void setImpreso(Character impreso) {
        this.impreso = impreso;
    }

    @Column(name = "pa", nullable = false)
    public String getPa(){return pa;}

    public void setPa(String pa) {
        this.pa = pa;
    }

    @Column(name = "pp", nullable = false)
    public String getPp(){return pp;}

    public void setPp(String pp) {
        this.pp = pp;
    }
    @Column(name = "pam", nullable = false)
    public String getPam(){return pam;}

    public void setPam(String pam) {
        this.pam = pam;
    }

    @Column(name = "dx", nullable = false)
    public String getDx() {
        return dx;
    }

    public void setDx(String dx) {
        this.dx = dx;
    }
}//fin class
