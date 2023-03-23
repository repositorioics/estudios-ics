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
@Table(name = "covid_participantes_casos", catalog = "estudios_ics", uniqueConstraints={@UniqueConstraint(columnNames = {"CODIGO_CASO","CODIGO_PARTICIPANTE"})})
public class ParticipanteCasoCovid19 extends BaseMetaData implements Auditable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String codigoCasoParticipante;
	private CasoCovid19 codigoCaso;
	private Participante participante;
	private String enfermo;
	private Date fis;
	private Date fif;
	private String positivoPor;
	private String consentimiento;
    private Date fechaRecibido; //poner fecha en que se recibe el registro en el server
    
	@Id
    @Column(name = "CODIGO_PARTICIPANTE_CASO", length = 36, nullable = false)
	public String getCodigoCasoParticipante() {
		return codigoCasoParticipante;
	}

	public void setCodigoCasoParticipante(String codigoCasoParticipante) {
		this.codigoCasoParticipante = codigoCasoParticipante;
	}

	@ManyToOne
    @JoinColumn(name = "CODIGO_CASO", nullable = false)
    @ForeignKey(name = "FK_PARTICIPANTE_CASO_COVID")
	public CasoCovid19 getCodigoCaso() {
		return codigoCaso;
	}

	public void setCodigoCaso(CasoCovid19 codigoCaso) {
		this.codigoCaso = codigoCaso;
	}
	

	@ManyToOne
    @JoinColumn(name = "CODIGO_PARTICIPANTE", referencedColumnName = "CODIGO", nullable = false)
    @ForeignKey(name = "FK_PARTICIPANTE_CASO")
	public Participante getParticipante() {
		return participante;
	}

	public void setParticipante(Participante participante) {
		this.participante = participante;
	}

	@Column(name = "ENFERMO", length = 1, nullable = false)
	public String getEnfermo() {
		return enfermo;
	}

	public void setEnfermo(String enfermo) {
		this.enfermo = enfermo;
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

	@Column(name = "CONSENTIMIENTO", length = 1, nullable = false)
	public String getConsentimiento() {
		return consentimiento;
	}

	public void setConsentimiento(String consentimiento) {
		this.consentimiento = consentimiento;
	}

    @JsonIgnore
    @Column(name = "FECHA_RECIBIDO")
    public Date getFechaRecibido() {
        return fechaRecibido;
    }

    public void setFechaRecibido(Date fechaRecibido) {
        this.fechaRecibido = fechaRecibido;
    }

	@Override
	public String toString(){
		return participante.getCodigo() + "-" + codigoCaso.getFechaIngreso();
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
