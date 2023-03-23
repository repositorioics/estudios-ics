package ni.org.ics.estudios.domain.cohortefamilia.casos;

import java.util.Date;

import ni.org.ics.estudios.domain.BaseMetaData;
import ni.org.ics.estudios.domain.audit.Auditable;
import javax.persistence.*;

import org.hibernate.annotations.ForeignKey;

/**
 * Created by William Aviles on 6/07/2017.
 * V1.0
 */
@Entity
@Table(name = "chf_visitas_finales_casos", catalog = "estudios_ics")
public class VisitaFinalCaso extends BaseMetaData implements Auditable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ParticipanteCohorteFamiliaCaso codigoParticipanteCaso;
	private Date fechaVisita;
	private String enfermo;
	private String consTerreno;
	private String referidoCs;
	private String tratamiento;
	private String cualAntibiotico;
	private String sintResp;
	private String fiebre;
	private String tos;
	private String dolorGarganta;
	private String secrecionNasal;
	private Date fif;
	private Date fff;
	private Date fitos;
	private Date fftos;
	private Date figg;
	private Date ffgg;
	private Date fisn;
	private Date ffsn;
    
	@Id
	@OneToOne
    @JoinColumn(name = "CODIGO_PARTICIPANTE_CASO", nullable = false)
    @ForeignKey(name = "FK_VISITAFINAL_PARTICIPANTE")
	public ParticipanteCohorteFamiliaCaso getCodigoParticipanteCaso() {
		return codigoParticipanteCaso;
	}

	public void setCodigoParticipanteCaso(ParticipanteCohorteFamiliaCaso codigoParticipanteCaso) {
		this.codigoParticipanteCaso = codigoParticipanteCaso;
	}
	
	@Column(name = "FECHA_VISITA", nullable = false)
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
	public String getReferidoCs() {
		return referidoCs;
	}

	public void setReferidoCs(String referidoCs) {
		this.referidoCs = referidoCs;
	}

	@Column(name = "TRATAMIENTO", length = 255, nullable = true)
	public String getTratamiento() {
		return tratamiento;
	}

	public void setTratamiento(String tratamiento) {
		this.tratamiento = tratamiento;
	}

	@Column(name = "CUAL_ANTIBIOTICO", length = 255, nullable = true)
	public String getCualAntibiotico() {
		return cualAntibiotico;
	}

	public void setCualAntibiotico(String cualAntibiotico) {
		this.cualAntibiotico = cualAntibiotico;
	}

	@Column(name = "SINTOMAS", length = 2, nullable = false)
	public String getSintResp() {
		return sintResp;
	}

	public void setSintResp(String sintResp) {
		this.sintResp = sintResp;
	}

	@Column(name = "FIEBRE", length = 2, nullable = true)
	public String getFiebre() {
		return fiebre;
	}

	public void setFiebre(String fiebre) {
		this.fiebre = fiebre;
	}

	@Column(name = "TOS", length = 2, nullable = true)
	public String getTos() {
		return tos;
	}

	public void setTos(String tos) {
		this.tos = tos;
	}

	@Column(name = "DOLOR_GARGANTA", length = 2, nullable = true)
	public String getDolorGarganta() {
		return dolorGarganta;
	}

	public void setDolorGarganta(String dolorGarganta) {
		this.dolorGarganta = dolorGarganta;
	}

	@Column(name = "SECRECION_NASAL", length = 2, nullable = true)
	public String getSecrecionNasal() {
		return secrecionNasal;
	}

	public void setSecrecionNasal(String secrecionNasal) {
		this.secrecionNasal = secrecionNasal;
	}

	@Column(name = "FECHA_INICIO_FIEBRE", nullable = true)
	public Date getFif() {
		return fif;
	}

	public void setFif(Date fif) {
		this.fif = fif;
	}

	@Column(name = "FECHA_FIN_FIEBRE", nullable = true)
	public Date getFff() {
		return fff;
	}

	public void setFff(Date fff) {
		this.fff = fff;
	}

	@Column(name = "FECHA_INICIO_TOS", nullable = true)
	public Date getFitos() {
		return fitos;
	}

	public void setFitos(Date fitos) {
		this.fitos = fitos;
	}

	@Column(name = "FECHA_FIN_TOS", nullable = true)
	public Date getFftos() {
		return fftos;
	}

	public void setFftos(Date fftos) {
		this.fftos = fftos;
	}

	@Column(name = "FECHA_INICIO_DOLOR_GARGANTA", nullable = true)
	public Date getFigg() {
		return figg;
	}

	public void setFigg(Date figg) {
		this.figg = figg;
	}

	@Column(name = "FECHA_FIN_DOLOR_GARGANTA", nullable = true)
	public Date getFfgg() {
		return ffgg;
	}

	public void setFfgg(Date ffgg) {
		this.ffgg = ffgg;
	}

	@Column(name = "FECHA_INICIO_SECRECION_NASAL", nullable = true)
	public Date getFisn() {
		return fisn;
	}

	public void setFisn(Date fisn) {
		this.fisn = fisn;
	}

	@Column(name = "FECHA_FIN_SECRECION_NASAL", nullable = true)
	public Date getFfsn() {
		return ffsn;
	}

	public void setFfsn(Date ffsn) {
		this.ffsn = ffsn;
	}

	@Override
	public String toString(){
		return codigoParticipanteCaso.getCodigoCaso().getCasa().getCodigoCHF() + "-" + codigoParticipanteCaso.getParticipante().getParticipante().getCodigo() + "-" + codigoParticipanteCaso.getCodigoCaso().getFechaInicio();
	}
	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof VisitaFinalCaso)) return false;

        VisitaFinalCaso that = (VisitaFinalCaso) o;

        if (!codigoParticipanteCaso.equals(that.codigoParticipanteCaso)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = codigoParticipanteCaso.hashCode();
        result = 31 * result + codigoParticipanteCaso.getParticipante().hashCode();
        return result;
    }

	@Override
	public boolean isFieldAuditable(String fieldname) {
		// TODO Auto-generated method stub
		return true;
	}
}
