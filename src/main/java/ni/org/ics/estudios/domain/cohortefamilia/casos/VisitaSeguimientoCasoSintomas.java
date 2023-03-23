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
@Table(name = "chf_sintomas_casos", catalog = "estudios_ics")
public class VisitaSeguimientoCasoSintomas extends BaseMetaData implements Auditable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String codigoCasoSintoma;
	private VisitaSeguimientoCaso codigoVisitaCaso;
	private Date fechaSintomas;
	private String periodo;
	private String fiebre;
	private Date fif;
	private String fiebreCuantificada;
	private Float valorFiebreCuantificada;
	private String dolorCabeza;
	private String dolorArticular;
	private String dolorMuscular;
	private String dificultadRespiratoria;
	private Date fdr;
	private String secrecionNasal;
	private Date fsn;
	private String tos;
	private Date ftos;
	private String pocoApetito;
	private String dolorGarganta;
	private String diarrea;
	private String quedoCama;
	private String respiracionRuidosa;
	private Date frr; //Fecha de dolor de garganta
	private String oseltamivir;
	private String antibiotico;
	private String cualAntibiotico;
	private String prescritoMedico;
    private String respiracionRapida;
    //se agrega intensidad sintomas
	private String fiebreIntensidad;
	private String dolorCabezaIntensidad;
	private String dolorArticularIntensidad;
	private String dolorMuscularIntensidad;
	private String secrecionNasalIntensidad;
	private String tosIntensidad;
	private String dolorGargantaIntensidad;
    
	@Id
    @Column(name = "CODIGO_SINTOMA_CASO", length = 50, nullable = false)
	public String getCodigoCasoSintoma() {
		return codigoCasoSintoma;
	}

	public void setCodigoCasoSintoma(String codigoCasoSintoma) {
		this.codigoCasoSintoma = codigoCasoSintoma;
	}

	@ManyToOne
    @JoinColumn(name = "CODIGO_VISITA_CASO", nullable = false)
    @ForeignKey(name = "FK_SINTOMA_VISITA")
	public VisitaSeguimientoCaso getCodigoVisitaCaso() {
		return codigoVisitaCaso;
	}

	public void setCodigoVisitaCaso(VisitaSeguimientoCaso codigoVisitaCaso) {
		this.codigoVisitaCaso = codigoVisitaCaso;
	}
	
	@Column(name = "FECHA_SINTOMAS", nullable = false)
	public Date getFechaSintomas() {
		return fechaSintomas;
	}

	public void setFechaSintomas(Date fechaSintomas) {
		this.fechaSintomas = fechaSintomas;
	}
	
	@Column(name = "PERIODO", length = 10, nullable = true)
	public String getPeriodo() {
		return periodo;
	}

	public void setPeriodo(String periodo) {
		this.periodo = periodo;
	}

	@Column(name = "FIEBRE", length = 2, nullable = false)
	public String getFiebre() {
		return fiebre;
	}

	public void setFiebre(String fiebre) {
		this.fiebre = fiebre;
	}
	
	@Column(name = "FIF", nullable = true)
	public Date getFif() {
		return fif;
	}

	public void setFif(Date fif) {
		this.fif = fif;
	}

	@Column(name = "FIEBRE_CUANTIFICADA", length = 2, nullable = false)
	public String getFiebreCuantificada() {
		return fiebreCuantificada;
	}

	public void setFiebreCuantificada(String fiebreCuantificada) {
		this.fiebreCuantificada = fiebreCuantificada;
	}
	
	@Column(name = "VALOR_FIEBRE_CUANTIFICADA",  nullable = true)
	public Float getValorFiebreCuantificada() {
		return valorFiebreCuantificada;
	}

	public void setValorFiebreCuantificada(Float valorFiebreCuantificada) {
		this.valorFiebreCuantificada = valorFiebreCuantificada;
	}

	@Column(name = "DOLOR_CABEZA", length = 2, nullable = true)
	public String getDolorCabeza() {
		return dolorCabeza;
	}

	public void setDolorCabeza(String dolorCabeza) {
		this.dolorCabeza = dolorCabeza;
	}

	@Column(name = "DOLOR_ARTICULAR", length = 2, nullable = true)
	public String getDolorArticular() {
		return dolorArticular;
	}

	public void setDolorArticular(String dolorArticular) {
		this.dolorArticular = dolorArticular;
	}

	@Column(name = "DOLOR_MUSCULAR", length = 2, nullable = true)
	public String getDolorMuscular() {
		return dolorMuscular;
	}

	public void setDolorMuscular(String dolorMuscular) {
		this.dolorMuscular = dolorMuscular;
	}

	@Column(name = "DIFICULTAD_RESPIRATORIA", length = 2, nullable = true)
	public String getDificultadRespiratoria() {
		return dificultadRespiratoria;
	}

	public void setDificultadRespiratoria(String dificultadRespiratoria) {
		this.dificultadRespiratoria = dificultadRespiratoria;
	}
	
	@Column(name = "FECHA_DIFICULTAD_RESPIRATORIA", nullable = true)
	public Date getFdr() {
		return fdr;
	}

	public void setFdr(Date fdr) {
		this.fdr = fdr;
	}

	@Column(name = "SECRECION_NASAL", length = 2, nullable = true)
	public String getSecrecionNasal() {
		return secrecionNasal;
	}

	public void setSecrecionNasal(String secrecionNasal) {
		this.secrecionNasal = secrecionNasal;
	}
	
	@Column(name = "FECHA_SECRECION_NASAL", nullable = true)
	public Date getFsn() {
		return fsn;
	}

	public void setFsn(Date fsn) {
		this.fsn = fsn;
	}

	@Column(name = "TOS", length = 2, nullable = true)
	public String getTos() {
		return tos;
	}

	public void setTos(String tos) {
		this.tos = tos;
	}
	
	@Column(name = "FECHA_TOS", nullable = true)
	public Date getFtos() {
		return ftos;
	}

	public void setFtos(Date ftos) {
		this.ftos = ftos;
	}

	@Column(name = "POCO_APETITO", length = 2, nullable = true)
	public String getPocoApetito() {
		return pocoApetito;
	}

	public void setPocoApetito(String pocoApetito) {
		this.pocoApetito = pocoApetito;
	}

	@Column(name = "DOLOR_GARGANTA", length = 2, nullable = true)
	public String getDolorGarganta() {
		return dolorGarganta;
	}

	public void setDolorGarganta(String dolorGarganta) {
		this.dolorGarganta = dolorGarganta;
	}

	@Column(name = "DIARREA", length = 2, nullable = true)
	public String getDiarrea() {
		return diarrea;
	}

	public void setDiarrea(String diarrea) {
		this.diarrea = diarrea;
	}

	@Column(name = "QUEDO_CAMA", length = 2, nullable = true)
	public String getQuedoCama() {
		return quedoCama;
	}

	public void setQuedoCama(String quedoCama) {
		this.quedoCama = quedoCama;
	}

	@Column(name = "RESP_RUIDOSA", length = 2, nullable = true)
	public String getRespiracionRuidosa() {
		return respiracionRuidosa;
	}

	public void setRespiracionRuidosa(String respiracionRuidosa) {
		this.respiracionRuidosa = respiracionRuidosa;
	}
	
	@Column(name = "FECHA_RESP_RUIDOSA", nullable = true)
	public Date getFrr() {
		return frr;
	}

	public void setFrr(Date frr) {
		this.frr = frr;
	}

	@Column(name = "OSELTAMIVIR", length = 2, nullable = true)
	public String getOseltamivir() {
		return oseltamivir;
	}

	public void setOseltamivir(String oseltamivir) {
		this.oseltamivir = oseltamivir;
	}

	@Column(name = "ANTIBIOTICO", length = 2, nullable = true)
	public String getAntibiotico() {
		return antibiotico;
	}

	public void setAntibiotico(String antibiotico) {
		this.antibiotico = antibiotico;
	}

	@Column(name = "CUAL_ANTIBIOTICO", length = 255, nullable = true)
	public String getCualAntibiotico() {
		return cualAntibiotico;
	}

	public void setCualAntibiotico(String cualAntibiotico) {
		this.cualAntibiotico = cualAntibiotico;
	}

	@Column(name = "PRESCRITO_MEDICO", length = 2, nullable = true)
	public String getPrescritoMedico() {
		return prescritoMedico;
	}

	public void setPrescritoMedico(String prescritoMedico) {
		this.prescritoMedico = prescritoMedico;
	}

    @Column(name = "RESP_RAPIDA", length = 2, nullable = true)
    public String getRespiracionRapida() {
        return respiracionRapida;
    }

    public void setRespiracionRapida(String respiracionRapida) {
        this.respiracionRapida = respiracionRapida;
    }

	@Column(name = "FIEBRE_INTENSIDAD", length = 2, nullable = true)
	public String getFiebreIntensidad() {
		return fiebreIntensidad;
	}

	public void setFiebreIntensidad(String fiebreIntensidad) {
		this.fiebreIntensidad = fiebreIntensidad;
	}

	@Column(name = "DOLOR_CABEZA_INTENSIDAD", length = 2, nullable = true)
	public String getDolorCabezaIntensidad() {
		return dolorCabezaIntensidad;
	}

	public void setDolorCabezaIntensidad(String dolorCabezaIntensidad) {
		this.dolorCabezaIntensidad = dolorCabezaIntensidad;
	}

	@Column(name = "DOLOR_ARTICULAR_INTENSIDAD", length = 2, nullable = true)
	public String getDolorArticularIntensidad() {
		return dolorArticularIntensidad;
	}

	public void setDolorArticularIntensidad(String dolorArticularIntensidad) {
		this.dolorArticularIntensidad = dolorArticularIntensidad;
	}

	@Column(name = "DOLOR_MUSCULAR_INTENSIDAD", length = 2, nullable = true)
	public String getDolorMuscularIntensidad() {
		return dolorMuscularIntensidad;
	}

	public void setDolorMuscularIntensidad(String dolorMuscularIntensidad) {
		this.dolorMuscularIntensidad = dolorMuscularIntensidad;
	}

	@Column(name = "SECRECION_NASAL_INTENSIDAD", length = 2, nullable = true)
	public String getSecrecionNasalIntensidad() {
		return secrecionNasalIntensidad;
	}

	public void setSecrecionNasalIntensidad(String secrecionNasalIntensidad) {
		this.secrecionNasalIntensidad = secrecionNasalIntensidad;
	}

	@Column(name = "TOS_INTENSIDAD", length = 2, nullable = true)
	public String getTosIntensidad() {
		return tosIntensidad;
	}

	public void setTosIntensidad(String tosIntensidad) {
		this.tosIntensidad = tosIntensidad;
	}

	@Column(name = "DOLOR_GARGANTA_INTENSIDAD", length = 2, nullable = true)
	public String getDolorGargantaIntensidad() {
		return dolorGargantaIntensidad;
	}

	public void setDolorGargantaIntensidad(String dolorGargantaIntensidad) {
		this.dolorGargantaIntensidad = dolorGargantaIntensidad;
	}

	@Override
	public String toString(){
		return codigoVisitaCaso.getCodigoParticipanteCaso().getCodigoCaso().getCasa().getCodigoCHF() + "-" + codigoVisitaCaso.getCodigoParticipanteCaso().getParticipante().getParticipante().getCodigo() + "-" + codigoVisitaCaso.getCodigoParticipanteCaso().getCodigoCaso().getFechaInicio();
	}
	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof VisitaSeguimientoCasoSintomas)) return false;

        VisitaSeguimientoCasoSintomas that = (VisitaSeguimientoCasoSintomas) o;

        if (!codigoCasoSintoma.equals(that.codigoCasoSintoma)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = codigoCasoSintoma.hashCode();
        result = 31 * result + codigoVisitaCaso.getCodigoParticipanteCaso().getParticipante().hashCode();
        return result;
    }

	@Override
	public boolean isFieldAuditable(String fieldname) {
		// TODO Auto-generated method stub
		return true;
	}
}
