package ni.org.ics.estudios.domain.covid19;

import ni.org.ics.estudios.domain.BaseMetaData;
import ni.org.ics.estudios.domain.audit.Auditable;
import ni.org.ics.estudios.domain.cohortefamilia.ParticipanteCohorteFamilia;
import ni.org.ics.estudios.domain.cohortefamilia.casos.CasaCohorteFamiliaCaso;
import org.hibernate.annotations.ForeignKey;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Miguel Salinas on 01/06/2020.
 * V1.0
 */
@Entity
@Table(name = "covid_participantes_casos", catalog = "estudios_ics", uniqueConstraints={@UniqueConstraint(columnNames = {"CODIGO_CASO","CODIGO_PARTICIPANTE"})})
public class ParticipanteCasoCovid19 extends BaseMetaData implements Auditable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String codigoCasoParticipante;
	private CasaCasoCovid19 codigoCaso;
	private ParticipanteCohorteFamilia participante;
	private String enfermo;
	private Date fechaIngreso;
	private Date fis;
	private Date fif;
	private String positivoPor;
	private Date fechaDesactivacion;
	private String consentimiento;
    
	@Id
    @Column(name = "CODIGO_PARTICIPANTE_CASO", length = 50, nullable = false)
	public String getCodigoCasoParticipante() {
		return codigoCasoParticipante;
	}

	public void setCodigoCasoParticipante(String codigoCasoParticipante) {
		this.codigoCasoParticipante = codigoCasoParticipante;
	}

	@ManyToOne
    @JoinColumn(name = "CODIGO_CASO", nullable = false)
    @ForeignKey(name = "FK_PARTICIPANTE_CASA_CASO_COVID")
	public CasaCasoCovid19 getCodigoCaso() {
		return codigoCaso;
	}

	public void setCodigoCaso(CasaCasoCovid19 codigoCaso) {
		this.codigoCaso = codigoCaso;
	}
	

	@ManyToOne
    @JoinColumn(name = "CODIGO_PARTICIPANTE", referencedColumnName = "CODIGO_PARTICIPANTE", nullable = false)
    @ForeignKey(name = "FK_PARTICIPANTE_CASO")
	public ParticipanteCohorteFamilia getParticipante() {
		return participante;
	}

	public void setParticipante(ParticipanteCohorteFamilia participante) {
		this.participante = participante;
	}

	@Column(name = "ENFERMO", length = 1, nullable = false)
	public String getEnfermo() {
		return enfermo;
	}

	public void setEnfermo(String enfermo) {
		this.enfermo = enfermo;
	}

	@Column(name = "FECHA_INGRESO", nullable = false)
	public Date getFechaIngreso() {
		return fechaIngreso;
	}

	public void setFechaIngreso(Date fechaIngreso) {
		this.fechaIngreso = fechaIngreso;
	}

	@Column(name = "FIS", nullable = true)
	public Date getFis() {
		return fis;
	}

	public void setFis(Date fis) {
		this.fis = fis;
	}

	@Column(name = "FIF")
	public Date getFif() {
		return fif;
	}

	public void setFif(Date fif) {
		this.fif = fif;
	}

	@Column(name = "POSITIVO_POR", length = 2, nullable = false)
	public String getPositivoPor() {
		return positivoPor;
	}

	public void setPositivoPor(String positivoPor) {
		this.positivoPor = positivoPor;
	}

	@Column(name = "FECHA_DESACTIVACION")
	public Date getFechaDesactivacion() {
		return fechaDesactivacion;
	}

	public void setFechaDesactivacion(Date fechaDesactivacion) {
		this.fechaDesactivacion = fechaDesactivacion;
	}

	@Column(name = "CONSENTIMIENTO", length = 1, nullable = false)
	public String getConsentimiento() {
		return consentimiento;
	}

	public void setConsentimiento(String consentimiento) {
		this.consentimiento = consentimiento;
	}

	@Override
	public String toString(){
		return codigoCaso.getCasa().getCodigoCHF() + "-" + participante.getParticipante().getCodigo() + "-" + codigoCaso.getFechaInicio();
	}
	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ParticipanteCasoCovid19)) return false;

        ParticipanteCasoCovid19 that = (ParticipanteCasoCovid19) o;

        if (!codigoCasoParticipante.equals(that.codigoCasoParticipante)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = codigoCasoParticipante.hashCode();
        result = 31 * result + participante.hashCode();
        return result;
    }

	@Override
	public boolean isFieldAuditable(String fieldname) {
		// TODO Auto-generated method stub
		return true;
	}
}
