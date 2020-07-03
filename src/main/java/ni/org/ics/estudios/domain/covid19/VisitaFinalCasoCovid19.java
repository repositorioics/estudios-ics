package ni.org.ics.estudios.domain.covid19;

import ni.org.ics.estudios.domain.BaseMetaData;
import ni.org.ics.estudios.domain.audit.Auditable;
import org.hibernate.annotations.ForeignKey;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Miguel Salinas on 01/07/2020.
 * V1.0
 */
@Entity
@Table(name = "covid_visitas_finales_casos", catalog = "estudios_ics")
public class VisitaFinalCasoCovid19 extends BaseMetaData implements Auditable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String codigoVisitaFinal;
	private ParticipanteCasoCovid19 codigoParticipanteCaso;
	private Date fechaVisita;
	private String enfermo;
	private String consTerreno;
	private String referidoCS;
	private String tratamiento;
	private String queAntibiotico;
	private String otroMedicamento;
	private String sintResp;
	private String fueHospitalizado;
	private Date fechaIngreso;
	private Date fechaEgreso;
	private Integer diasHospitalizado;
	private String cualHospital;
	private String utilizoOxigeno;
	private String fueIntubado;
	private String estadoSalud;
	private String faltoTrabajoEscuela;
	private Integer diasFaltoTrabajoEscuela;

    @Id
    @Column(name = "CODIGO_VISITA_FINAL", length = 36, nullable = false)
    public String getCodigoVisitaFinal() {
		return codigoVisitaFinal;
	}

	public void setCodigoVisitaFinal(String codigoVisitaFinal) {
		this.codigoVisitaFinal = codigoVisitaFinal;
	}

    @ManyToOne
    @JoinColumn(name = "CODIGO_PARTICIPANTE_CASO", nullable = false)
    @ForeignKey(name = "FK_VISITA_FIN_PARTICIPANTE_CASO_COVID")
    public ParticipanteCasoCovid19 getCodigoParticipanteCaso() {
		return codigoParticipanteCaso;
	}

	public void setCodigoParticipanteCaso(ParticipanteCasoCovid19 codigoParticipanteCaso) {
		this.codigoParticipanteCaso = codigoParticipanteCaso;
	}

    @Column(name = "FECHA_VISITA")
	public Date getFechaVisita() {
		return fechaVisita;
	}

	public void setFechaVisita(Date fechaVisita) {
		this.fechaVisita = fechaVisita;
	}

    @Column(name = "ENFERMO", length = 2, nullable = false)
	public String getEnfermo() {
		return enfermo;
	}

	public void setEnfermo(String enfermo) {
		this.enfermo = enfermo;
	}

    @Column(name = "CONSULTA_TERRENO", length = 2, nullable = true)
	public String getConsTerreno() {
		return consTerreno;
	}

	public void setConsTerreno(String consTerreno) {
		this.consTerreno = consTerreno;
	}

    @Column(name = "REFERIDO_CS", length = 2, nullable = true)
	public String getReferidoCS() {
		return referidoCS;
	}

	public void setReferidoCS(String referidoCS) {
		this.referidoCS = referidoCS;
	}

    @Column(name = "TRATAMIENTO", length = 255, nullable = true)
	public String getTratamiento() {
		return tratamiento;
	}

	public void setTratamiento(String tratamiento) {
		this.tratamiento = tratamiento;
	}

    @Column(name = "CUAL_ANTIBIOTICO", length = 255, nullable = true)
	public String getQueAntibiotico() {
		return queAntibiotico;
	}

	public void setQueAntibiotico(String queAntibiotico) {
		this.queAntibiotico = queAntibiotico;
	}

    @Column(name = "OTRO_MEDICAMENTO", length = 255, nullable = true)
	public String getOtroMedicamento() {
		return otroMedicamento;
	}

	public void setOtroMedicamento(String otroMedicamento) {
		this.otroMedicamento = otroMedicamento;
	}

    @Column(name = "SINTOMAS", length = 2, nullable = false)
	public String getSintResp() {
		return sintResp;
	}

	public void setSintResp(String sintResp) {
		this.sintResp = sintResp;
	}

    @Column(name = "FUE_HOSPITALIZADO", length = 2, nullable = false)
	public String getFueHospitalizado() {
		return fueHospitalizado;
	}

	public void setFueHospitalizado(String fueHospitalizado) {
		this.fueHospitalizado = fueHospitalizado;
	}

    @Column(name = "FECHA_INGRESO", nullable = true)
	public Date getFechaIngreso() {
		return fechaIngreso;
	}

	public void setFechaIngreso(Date fechaIngreso) {
		this.fechaIngreso = fechaIngreso;
	}

    @Column(name = "FECHA_EGRESO", nullable = true)
	public Date getFechaEgreso() {
		return fechaEgreso;
	}

	public void setFechaEgreso(Date fechaEgreso) {
		this.fechaEgreso = fechaEgreso;
	}

    @Column(name = "DIAS_HOSPITALIZADO", nullable = true)
	public Integer getDiasHospitalizado() {
		return diasHospitalizado;
	}

	public void setDiasHospitalizado(Integer diasHospitalizado) {
		this.diasHospitalizado = diasHospitalizado;
	}

    @Column(name = "CUAL_HOSPITAL", length = 255, nullable = true)
	public String getCualHospital() {
		return cualHospital;
	}

	public void setCualHospital(String cualHospital) {
		this.cualHospital = cualHospital;
	}

    @Column(name = "UTILIZO_OXIGENO", length = 2, nullable = true)
	public String getUtilizoOxigeno() {
		return utilizoOxigeno;
	}

	public void setUtilizoOxigeno(String utilizoOxigeno) {
		this.utilizoOxigeno = utilizoOxigeno;
	}

    @Column(name = "FUE_INTUBADO", length = 2, nullable = true)
	public String getFueIntubado() {
		return fueIntubado;
	}

	public void setFueIntubado(String fueIntubado) {
		this.fueIntubado = fueIntubado;
	}

    @Column(name = "ESTADO_SALUD", length = 2, nullable = true)
	public String getEstadoSalud() {
		return estadoSalud;
	}

	public void setEstadoSalud(String estadoSalud) {
		this.estadoSalud = estadoSalud;
	}

    @Column(name = "FALTO_TRABAJO_ESCUELA", length = 2, nullable = false)
	public String getFaltoTrabajoEscuela() {
		return faltoTrabajoEscuela;
	}

	public void setFaltoTrabajoEscuela(String faltoTrabajoEscuela) {
		this.faltoTrabajoEscuela = faltoTrabajoEscuela;
	}

    @Column(name = "DIAS_FALTO_TRABAJO_ESC", nullable = true)
	public Integer getDiasFaltoTrabajoEscuela() {
		return diasFaltoTrabajoEscuela;
	}

	public void setDiasFaltoTrabajoEscuela(Integer diasFaltoTrabajoEscuela) {
		this.diasFaltoTrabajoEscuela = diasFaltoTrabajoEscuela;
	}

	@Override
	public String toString(){
		return codigoParticipanteCaso.getParticipante().getCodigo() + "-" + codigoParticipanteCaso.getCodigoCaso().getFechaIngreso();
	}
	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof VisitaFinalCasoCovid19)) return false;

        VisitaFinalCasoCovid19 that = (VisitaFinalCasoCovid19) o;

        if (!codigoVisitaFinal.equals(that.codigoVisitaFinal)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = codigoVisitaFinal.hashCode();
        result = 31 * result + codigoParticipanteCaso.getParticipante().hashCode();
        return result;
    }

    @Override
    public boolean isFieldAuditable(String fieldname) {
        return true;
    }
}
