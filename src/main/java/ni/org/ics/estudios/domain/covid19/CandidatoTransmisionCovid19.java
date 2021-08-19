package ni.org.ics.estudios.domain.covid19;

import ni.org.ics.estudios.domain.BaseMetaData;
import ni.org.ics.estudios.domain.Participante;
import ni.org.ics.estudios.domain.audit.Auditable;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.hibernate.annotations.ForeignKey;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Miguel Salinas on 01/06/2020.
 * V1.0
 */
@Entity
@Table(name = "covid_candidato_transmision", catalog = "estudios_ics")
public class CandidatoTransmisionCovid19 extends BaseMetaData implements Auditable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String codigo;
	private Participante participante;
    private String casaCHF;
	private Date fis;
	private Date fif;
	private String positivoPor;
	private String consentimiento;
    private String estActuales;
    private Date fechaIngreso;
    private String indice; //el primero de la casa en el dia

	@Id
    @Column(name = "CODIGO", length = 36, nullable = false)
	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	@ManyToOne
    @JoinColumn(name = "CODIGO_PARTICIPANTE", referencedColumnName = "CODIGO", nullable = false)
    @ForeignKey(name = "FK_PARTICIPANTE_CANDIDATO_TCOVID")
	public Participante getParticipante() {
		return participante;
	}

	public void setParticipante(Participante participante) {
		this.participante = participante;
	}

    @Column(name = "CASA_CHF", nullable = true, length = 10)
    public String getCasaCHF() {
        return casaCHF;
    }

    public void setCasaCHF(String casaCHF) {
        this.casaCHF = casaCHF;
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

	@Column(name = "POSITIVO_POR", length = 2, nullable = true)
	public String getPositivoPor() {
		return positivoPor;
	}

	public void setPositivoPor(String positivoPor) {
		this.positivoPor = positivoPor;
	}

    @Column(name = "CONSENTIMIENTO", length = 30, nullable = false)
    public String getConsentimiento() {
        return consentimiento;
    }

    public void setConsentimiento(String consentimiento) {
        this.consentimiento = consentimiento;
    }

    @Column(name = "ESTUDIOS_ACTUALES", length = 255, nullable = false)
    public String getEstActuales() {
        return estActuales;
    }

    public void setEstActuales(String estActuales) {
        this.estActuales = estActuales;
    }

    @Column(name = "FECHA_INGRESO", nullable = false)
    public Date getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(Date fechaInicio) {
        this.fechaIngreso = fechaInicio;
    }

    @Column(name = "INDICE", length = 1, nullable = false)
    @JsonIgnore
    public String getIndice() {
        return indice;
    }

    public void setIndice(String indice) {
        this.indice = indice;
    }

    @Override
	public String toString(){
		return participante.getCodigo().toString();
	}
	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CandidatoTransmisionCovid19)) return false;

        CandidatoTransmisionCovid19 that = (CandidatoTransmisionCovid19) o;

        if (!codigo.equals(that.codigo)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = codigo.hashCode();
        result = 31 * result + participante.hashCode();
        return result;
    }

	@Override
	public boolean isFieldAuditable(String fieldname) {
		// TODO Auto-generated method stub
		return true;
	}
}
