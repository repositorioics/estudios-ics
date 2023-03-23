package ni.org.ics.estudios.domain;

import ni.org.ics.estudios.domain.audit.Auditable;
import ni.org.ics.estudios.domain.catalogs.Estudio;
import org.hibernate.annotations.*;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

/**
 * Created by FIRSTICT on 4/28/2017.
 * V1.0
 */
@Entity
@Table(name = "tamizajes", catalog = "estudios_ics")
public class Tamizaje extends BaseMetaData implements Auditable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
    private String codigo;
    private Estudio estudio;
    private String sexo;
    private Date fechaNacimiento;
    private String aceptaTamizajePersona;
    private String razonNoAceptaTamizajePersona;
    private String otraRazonNoAceptaTamizajePersona;
    private String criteriosInclusion;
    private String enfermedad;
    private String dondeAsisteProblemasSalud;
    private String otroCentroSalud;
    private String puestoSalud;
    private String aceptaAtenderCentro;
    private String esElegible;
    private String aceptaParticipar;
    private String razonNoAceptaParticipar;
    private String otraRazonNoAceptaParticipar;
    private String asentimientoVerbal;
    //nuevo ingreso MA2018
    private String pretermino;
    private String cohorte;
    private String enfermedadInmuno;
    //private String cualEnfermedad;
    private String enfermedadCronica;
    private String tratamiento;
    private String cualTratamiento;
    private String diagDengue;
    private Date fechaDiagDengue;
    private String hospDengue;
    private Date fechaHospDengue;
    private String tiempoResidencia;
    //reconsentimiento Dengue 2018
    private String tipoVivienda;
    //private String otraEnfCronica;
    //private String enfCronicaAnio;
    //private String enfCronicaMes;
    private String otroTx;
    private String autorizaSupervisor;
    private String emancipado;
    private String razonEmancipacion;
    private String otraRazonEmancipacion;
    private Integer codigoParticipanteRecon;
    private String tipoAsentimiento;

    @Id
    @Column(name = "CODIGO", nullable = false, insertable = true, updatable = false, length = 36)
    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    @ManyToOne
    @JoinColumn(name = "CODIGO_ESTUDIO", nullable = false)
    @ForeignKey(name = "FK_ESTUDIO_TAMIZAJE")
    public Estudio getEstudio() {
        return estudio;
    }

    public void setEstudio(Estudio estudio) {
        this.estudio = estudio;
    }

    @Column(name = "SEXO", length = 50)
    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    @Column(name = "FECHA_NACIMIENTO", nullable = true)
    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    @Column(name="ACEPTA_TAMIZAJE", nullable = false, length = 1)
    public String getAceptaTamizajePersona() {
        return aceptaTamizajePersona;
    }

    public void setAceptaTamizajePersona(String aceptaTamizajePersona) {
        this.aceptaTamizajePersona = aceptaTamizajePersona;
    }

    @Column(name="RAZON_NO_ACEPTA_TAMIZAJE", nullable = true, length = 50)
    public String getRazonNoAceptaTamizajePersona() {
        return razonNoAceptaTamizajePersona;
    }

    public void setRazonNoAceptaTamizajePersona(String razonNoAceptaTamizajePersona) {
        this.razonNoAceptaTamizajePersona = razonNoAceptaTamizajePersona;
    }

    @Column(name="CRITERIOS_INCLUSION", nullable = true, length = 50)
    public String getCriteriosInclusion() {
        return criteriosInclusion;
    }

    public void setCriteriosInclusion(String criteriosInclusion) {
        this.criteriosInclusion = criteriosInclusion;
    }

    @Column(name="ENFERMEDAD", nullable = true, length = 50)
    public String getEnfermedad() {
        return enfermedad;
    }

    public void setEnfermedad(String enfermedad) {
        this.enfermedad = enfermedad;
    }

    @Column(name="DONDE_ASISTE_PROBLEMAS_SALUD", nullable = true)
    public String getDondeAsisteProblemasSalud() {
        return dondeAsisteProblemasSalud;
    }

    public void setDondeAsisteProblemasSalud(String dondeAsisteProblemasSalud) {
        this.dondeAsisteProblemasSalud = dondeAsisteProblemasSalud;
    }

    @Column(name="OTRO_CENTRO_SALUD", nullable = true)
    public String getOtroCentroSalud() {
        return otroCentroSalud;
    }

    public void setOtroCentroSalud(String otroCentroSalud) {
        this.otroCentroSalud = otroCentroSalud;
    }

    @Column(name="PUESTO_SALUD", nullable = true)
    public String getPuestoSalud() {
        return puestoSalud;
    }

    public void setPuestoSalud(String puestoSalud) {
        this.puestoSalud = puestoSalud;
    }

    @Column(name="ASISTIR_CSSF", nullable = true, length = 5)
    public String getAceptaAtenderCentro() {
        return aceptaAtenderCentro;
    }

    public void setAceptaAtenderCentro(String aceptaAtenderCentro) {
        this.aceptaAtenderCentro = aceptaAtenderCentro;
    }

    @Column(name="ELEGIBLE", nullable = true, length = 1)
    public String getEsElegible() {
        return esElegible;
    }

    public void setEsElegible(String esElegible) {
        this.esElegible = esElegible;
    }

    @Column(name="ACEPTA_PARTICIPAR", nullable = true, length = 1)
    public String getAceptaParticipar() {
        return aceptaParticipar;
    }

    public void setAceptaParticipar(String aceptaParticipar) {
        this.aceptaParticipar = aceptaParticipar;
    }

    @Column(name="RAZON_NO_ACEPTA_PARTICIPAR", nullable = true, length = 5)
    public String getRazonNoAceptaParticipar() {
        return razonNoAceptaParticipar;
    }

    public void setRazonNoAceptaParticipar(String razonNoAceptaParticipar) {
        this.razonNoAceptaParticipar = razonNoAceptaParticipar;
    }

    @Column(name="ASENTIMIENTO_VERBAL", nullable = true, length = 1)
    public String getAsentimientoVerbal() {
        return asentimientoVerbal;
    }

    public void setAsentimientoVerbal(String asentimientoVerbal) {
        this.asentimientoVerbal = asentimientoVerbal;
    }

    @Column(name="OTRA_RAZON_NO_ACEPTA_TAMIZAJE", nullable = true)
    public String getOtraRazonNoAceptaTamizajePersona() {
        return otraRazonNoAceptaTamizajePersona;
    }

    public void setOtraRazonNoAceptaTamizajePersona(String otraRazonNoAceptaTamizajePersona) {
        this.otraRazonNoAceptaTamizajePersona = otraRazonNoAceptaTamizajePersona;
    }

    @Column(name="OTRA_RAZON_NO_ACEPTA_PARTICIPAR", nullable = true)
    public String getOtraRazonNoAceptaParticipar() {
        return otraRazonNoAceptaParticipar;
    }

    public void setOtraRazonNoAceptaParticipar(String otraRazonNoAceptaParticipar) {
        this.otraRazonNoAceptaParticipar = otraRazonNoAceptaParticipar;
    }

    @Column(name="PRETERMINO", nullable = true, length = 1)
    public String getPretermino() {
        return pretermino;
    }

    public void setPretermino(String pretermino) {
        this.pretermino = pretermino;
    }

    @Column(name="COHORTE", nullable = true, length = 5)
    public String getCohorte() {
        return cohorte;
    }

    public void setCohorte(String cohorte) {
        this.cohorte = cohorte;
    }

    @Column(name="ENFERMEDAD_INMUNO", nullable = true, length = 1)
    public String getEnfermedadInmuno() {
        return enfermedadInmuno;
    }

    public void setEnfermedadInmuno(String enfermedadInmuno) {
        this.enfermedadInmuno = enfermedadInmuno;
    }

    @Column(name="ENFERMEDAD_CRONICA", nullable = true, length = 1)
    public String getEnfermedadCronica() {
        return enfermedadCronica;
    }

    public void setEnfermedadCronica(String enfermedadCronica) {
        this.enfermedadCronica = enfermedadCronica;
    }

    @Column(name="TRATAMIENTO", nullable = true, length = 1)
    public String getTratamiento() {
        return tratamiento;
    }

    public void setTratamiento(String tratamiento) {
        this.tratamiento = tratamiento;
    }

    @Column(name="CUAL_TRATAMIENTO", nullable = true, length = 255)
    public String getCualTratamiento() {
        return cualTratamiento;
    }

    public void setCualTratamiento(String cualTratamiento) {
        this.cualTratamiento = cualTratamiento;
    }

    @Column(name="DIAG_DENGUE", nullable = true, length = 1)
    public String getDiagDengue() {
        return diagDengue;
    }

    public void setDiagDengue(String diagDengue) {
        this.diagDengue = diagDengue;
    }

    @Column(name="FECHA_DIAG_DENGUE", nullable = true)
    public Date getFechaDiagDengue() {
        return fechaDiagDengue;
    }

    public void setFechaDiagDengue(Date fechaDiagDengue) {
        this.fechaDiagDengue = fechaDiagDengue;
    }

    @Column(name="HOSP_DENGUE", nullable = true, length = 1)
    public String getHospDengue() {
        return hospDengue;
    }

    public void setHospDengue(String hospDengue) {
        this.hospDengue = hospDengue;
    }

    @Column(name="FECHA_HOSP_DENGUE", nullable = true)
    public Date getFechaHospDengue() {
        return fechaHospDengue;
    }

    public void setFechaHospDengue(Date fechaHospDengue) {
        this.fechaHospDengue = fechaHospDengue;
    }

    @Column(name="TIEMPO_RESIDENCIA", nullable = true, length = 1)
    public String getTiempoResidencia() {
        return tiempoResidencia;
    }

    public void setTiempoResidencia(String tiempoResidencia) {
        this.tiempoResidencia = tiempoResidencia;
    }

    @Column(name="TIPO_VIVIENDA", nullable = true, length = 1)
    public String getTipoVivienda() {
        return tipoVivienda;
    }

    public void setTipoVivienda(String tipoVivienda) {
        this.tipoVivienda = tipoVivienda;
    }
/*
    @Column(name="OTRA_ENF_CRONICA", nullable = true)
    public String getOtraEnfCronica() {
        return otraEnfCronica;
    }

    public void setOtraEnfCronica(String otraEnfCronica) {
        this.otraEnfCronica = otraEnfCronica;
    }

    @Column(name="ENF_CRONICA_ANIO", nullable = true, length = 4)
    public String getEnfCronicaAnio() {
        return enfCronicaAnio;
    }

    public void setEnfCronicaAnio(String enfCronicaAnio) {
        this.enfCronicaAnio = enfCronicaAnio;
    }

    @Column(name="ENF_CRONICA_MES", nullable = true, length = 2)
    public String getEnfCronicaMes() {
        return enfCronicaMes;
    }

    public void setEnfCronicaMes(String enfCronicaMes) {
        this.enfCronicaMes = enfCronicaMes;
    }
*/
    @Column(name="OTRO_TX", nullable = true)
    public String getOtroTx() {
        return otroTx;
    }

    public void setOtroTx(String otroTx) {
        this.otroTx = otroTx;
    }

    @Column(name="AUTORIZA_SUPERVISOR", nullable = true, length = 1)
    public String getAutorizaSupervisor() {
        return autorizaSupervisor;
    }

    public void setAutorizaSupervisor(String autorizaSupervisor) {
        this.autorizaSupervisor = autorizaSupervisor;
    }

    @Column(name = "EMANCIPADO", length = 1)
    public String getEmancipado() {
        return emancipado;
    }

    public void setEmancipado(String emancipado) {
        this.emancipado = emancipado;
    }

    @Column(name = "RAZON_EMANCIPADO", length = 1)
    public String getRazonEmancipacion() {
        return razonEmancipacion;
    }

    public void setRazonEmancipacion(String razonEmancipacion) {
        this.razonEmancipacion = razonEmancipacion;
    }

    @Column(name = "OTRA_RAZON_EMANCIPADO", length = 255)
    public String getOtraRazonEmancipacion() {
        return otraRazonEmancipacion;
    }

    public void setOtraRazonEmancipacion(String otraRazonEmancipacion) {
        this.otraRazonEmancipacion = otraRazonEmancipacion;
    }

    @Column(name = "CODIGO_PART_RECON", nullable = true)
    public Integer getCodigoParticipanteRecon() {
        return codigoParticipanteRecon;
    }

    public void setCodigoParticipanteRecon(Integer codigoParticipanteRecon) {
        this.codigoParticipanteRecon = codigoParticipanteRecon;
    }

    @Column(name="TIPO_ASENTIMIENTO", nullable = true, length = 1)
    public String getTipoAsentimiento() {
        return tipoAsentimiento;
    }

    public void setTipoAsentimiento(String asentimientoEscrito) {
        this.tipoAsentimiento = asentimientoEscrito;
    }

    @Override
    public boolean isFieldAuditable(String fieldname) {
        return true;
    }

    @Override
    public String toString() {
        return "'" + codigo + "'";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Tamizaje)) return false;

        Tamizaje tamizaje = (Tamizaje) o;

        return (!codigo.equals(tamizaje.codigo));
    }

    @Override
    public int hashCode() {
        return codigo.hashCode();
    }
}
