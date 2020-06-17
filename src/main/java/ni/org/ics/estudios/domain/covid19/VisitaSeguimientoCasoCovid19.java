package ni.org.ics.estudios.domain.covid19;

import ni.org.ics.estudios.domain.BaseMetaData;
import ni.org.ics.estudios.domain.audit.Auditable;
import org.hibernate.annotations.ForeignKey;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Miguel Salinas on 12/06/2020.
 * V1.0
 */
@Entity
@Table(name = "covid_visitas_seguimientos_casos", catalog = "estudios_ics")
public class VisitaSeguimientoCasoCovid19 extends BaseMetaData implements Auditable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String codigoCasoVisita;
	private ParticipanteCasoCovid19 codigoParticipanteCaso;
	private Date fechaVisita;
	private String visita;
	private String horaProbableVisita;
	private String expCS;
	private Float temp;
	private Integer saturacionO2;
	private Integer frecResp;

    @Id
    @Column(name = "CODIGO_VISITA_CASO", length = 36, nullable = false)
	public String getCodigoCasoVisita() {
		return codigoCasoVisita;
	}

	public void setCodigoCasoVisita(String codigoCasoVisita) {
		this.codigoCasoVisita = codigoCasoVisita;
	}

    @ManyToOne
    @JoinColumn(name = "CODIGO_PARTICIPANTE_CASO", nullable = false)
    @ForeignKey(name = "FK_VISITA_PARTICIPANTE_CASO_COVID")
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

    @Column(name = "VISITA", length = 2)
	public String getVisita() {
		return visita;
	}

	public void setVisita(String visita) {
		this.visita = visita;
	}

    @Column(name = "HORA_PROBABLE", length = 25, nullable = false)
	public String getHoraProbableVisita() {
		return horaProbableVisita;
	}

	public void setHoraProbableVisita(String horaProbableVisita) {
		this.horaProbableVisita = horaProbableVisita;
	}

    @Column(name = "EXP_CS", length = 2, nullable = false)
    public String getExpCS() {
		return expCS;
	}

	public void setExpCS(String expCS) {
		this.expCS = expCS;
	}

    @Column(name = "TEMP", nullable = true)
	public Float getTemp() {
		return temp;
	}

	public void setTemp(Float temp) {
		this.temp = temp;
	}

    @Column(name = "SATURACION_O2", nullable = false)
	public Integer getSaturacionO2() {
		return saturacionO2;
	}

	public void setSaturacionO2(Integer saturacionO2) {
		this.saturacionO2 = saturacionO2;
	}

    @Column(name = "FREC_RESP", nullable = false)
	public Integer getFrecResp() {
		return frecResp;
	}

	public void setFrecResp(Integer frecResp) {
		this.frecResp = frecResp;
	}

	@Override
	public String toString(){
		return codigoParticipanteCaso.getCodigoCaso().getCasa().getCodigoCHF() + "-" + codigoParticipanteCaso.getParticipante().getCodigo() + "-" + codigoParticipanteCaso.getCodigoCaso().getFechaIngreso();
	}
	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof VisitaSeguimientoCasoCovid19)) return false;

        VisitaSeguimientoCasoCovid19 that = (VisitaSeguimientoCasoCovid19) o;

        if (!codigoCasoVisita.equals(that.codigoCasoVisita)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = codigoCasoVisita.hashCode();
        result = 31 * result + codigoParticipanteCaso.getParticipante().hashCode();
        return result;
    }

    @Override
    public boolean isFieldAuditable(String fieldname) {
        return true;
    }
}
