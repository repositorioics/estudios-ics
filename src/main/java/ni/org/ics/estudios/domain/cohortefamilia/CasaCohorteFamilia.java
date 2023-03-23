package ni.org.ics.estudios.domain.cohortefamilia;

import ni.org.ics.estudios.domain.BaseMetaData;
import ni.org.ics.estudios.domain.Casa;
import ni.org.ics.estudios.domain.audit.Auditable;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.hibernate.annotations.ForeignKey;

import javax.persistence.*;

/**
 * Created by Miguel Salinas on 5/11/2017.
 * V1.0
 */
@Entity
@Table(name = "chf_casas_cohorte_familia", catalog = "estudios_ics")
public class CasaCohorteFamilia extends BaseMetaData implements Auditable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String codigoCHF;
    private Casa casa;
    private String nombre1JefeFamilia;
    private String nombre2JefeFamilia;
    private String apellido1JefeFamilia;
    private String apellido2JefeFamilia;
    private Double latitud;
    private Double longitud;

    @Id
    @Column(name = "CODIGO_CHF", nullable = false, length = 50)
    public String getCodigoCHF() {
        return codigoCHF;
    }

    public void setCodigoCHF(String codigoCHF) {
        this.codigoCHF = codigoCHF;
    }

    @ManyToOne
    @JoinColumn(name = "CODIGO_CASA", nullable = false)
    @ForeignKey(name = "FK_CASA_CHF")
    public Casa getCasa() {
        return casa;
    }

    public void setCasa(Casa casa) {
        this.casa = casa;
    }

    @Column(name = "NOMBRE1_JEFE", nullable = false)
    public String getNombre1JefeFamilia() {
        return nombre1JefeFamilia;
    }

    public void setNombre1JefeFamilia(String nombre1JefeFamilia) {
        this.nombre1JefeFamilia = nombre1JefeFamilia;
    }

    @Column(name = "NOMBRE2_JEFE", nullable = true)
    public String getNombre2JefeFamilia() {
        return nombre2JefeFamilia;
    }

    public void setNombre2JefeFamilia(String nombre2JefeFamilia) {
        this.nombre2JefeFamilia = nombre2JefeFamilia;
    }

    @Column(name = "APELLIDO1_JEFE", nullable = false)
    public String getApellido1JefeFamilia() {
        return apellido1JefeFamilia;
    }

    public void setApellido1JefeFamilia(String apellido1JefeFamilia) {
        this.apellido1JefeFamilia = apellido1JefeFamilia;
    }

    @Column(name = "APELLIDO2_JEFE", nullable = true)
    public String getApellido2JefeFamilia() {
        return apellido2JefeFamilia;
    }

    public void setApellido2JefeFamilia(String apellido2JefeFamilia) {
        this.apellido2JefeFamilia = apellido2JefeFamilia;
    }

    @Column(name = "LATITUD", nullable = true)
    public Double getLatitud() {
        return latitud;
    }

    public void setLatitud(Double latitud) {
        this.latitud = latitud;
    }

    @Column(name = "LONGITUD", nullable = true)
    public Double getLongitud() {
        return longitud;
    }

    public void setLongitud(Double longitud) {
        this.longitud = longitud;
    }
    
    @Transient
    @JsonIgnore
    public String getNombreJefeFamiliaCompleto(){
        String nombreJefeFamiliaCompleto = this.getNombre1JefeFamilia().toUpperCase();
        if (this.getNombre2JefeFamilia()!=null) nombreJefeFamiliaCompleto = nombreJefeFamiliaCompleto + " "+  this.getNombre2JefeFamilia().toUpperCase();
        nombreJefeFamiliaCompleto = nombreJefeFamiliaCompleto +" "+ this.getApellido1JefeFamilia().toUpperCase();
        if (this.getApellido2JefeFamilia()!=null) nombreJefeFamiliaCompleto = nombreJefeFamiliaCompleto + " "+  this.getApellido2JefeFamilia().toUpperCase();

        return nombreJefeFamiliaCompleto;
    }

    @Override
    public boolean isFieldAuditable(String fieldname) {
        return true;
    }

    @Override
    public String toString() {
        return "CasaCohorteFamilia{" + casa.getCodigo() +
                ", " + codigoCHF + "}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CasaCohorteFamilia)) return false;

        CasaCohorteFamilia that = (CasaCohorteFamilia) o;

        if (!casa.equals(that.casa)) return false;
        if (!codigoCHF.equals(that.codigoCHF)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = codigoCHF.hashCode();
        result = 31 * result + casa.hashCode();
        return result;
    }
}
