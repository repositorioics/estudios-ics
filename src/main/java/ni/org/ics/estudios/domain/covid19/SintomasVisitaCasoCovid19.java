package ni.org.ics.estudios.domain.covid19;

import ni.org.ics.estudios.domain.BaseMetaData;
import ni.org.ics.estudios.domain.audit.Auditable;
import org.hibernate.annotations.ForeignKey;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Miguel Salinas on 22/06/2020.
 * V1.0
 */
@Entity
@Table(name = "covid_sintomas_visitas_casos", catalog = "estudios_ics")
public class SintomasVisitaCasoCovid19 extends BaseMetaData implements Auditable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String codigoCasoSintoma;
	private VisitaSeguimientoCasoCovid19 codigoCasoVisita;
	private Date fechaSintomas;
	private String fiebre;
	private String fiebreIntensidad;
	//private Date fif;
	private String fiebreCuantificada;
	private Float valorFiebreCuantificada;
	private String dolorCabeza;
	private String dolorCabezaIntensidad;
	private String dolorArticular;
	private String dolorArticularIntensidad;
	private String dolorMuscular;
	private String dolorMuscularIntensidad;
	private String dificultadRespiratoria;
	private String secrecionNasal;
	private String secrecionNasalIntensidad;
	private String tos;
	private String tosIntensidad;
	private String pocoApetito;
	private String dolorGarganta;
	private String dolorGargantaIntensidad;
	private String picorGarganta;
	private String congestionNasal;
	private String rash;
	private String urticaria;
	private String conjuntivitis;
	private String expectoracion;
	private String diarrea;
	private String vomito;
	private String fatiga;
	private String respiracionRuidosa;
	private String respiracionRapida;
	private String perdidaOlfato;
	private String perdidaGusto;
	private String desmayos;
	private String sensacionPechoApretado;
	private String dolorPecho;
	private String sensacionFaltaAire;
	private String quedoCama;
	private String tratamiento;
	private Date fechaInicioTx;
	private String cualMedicamento;
	private String otroMedicamento;
	private String antibiotico;
	private String prescritoMedico;
	private String factorRiesgo;
	private String otroFactorRiesgo;
	private String otraPersonaEnferma;
	private Integer cuantasPersonas;
	private String trasladoHospital;
	private String cualHospital;
	private String hospitalizado;
	private Date fechaAlta;
	//private Date frr;
	//private Date fdr;
	//private Date fsn;
	//private Date ftos;


    @Id
    @Column(name = "CODIGO_SINTOMA_CASO", length = 36, nullable = false)
	public String getCodigoCasoSintoma() {
		return codigoCasoSintoma;
	}

	public void setCodigoCasoSintoma(String codigoCasoSintoma) {
		this.codigoCasoSintoma = codigoCasoSintoma;
	}

    @ManyToOne
    @JoinColumn(name = "CODIGO_VISITA_CASO", nullable = false)
    @ForeignKey(name = "FK_VISITA_SINTOMA_COVCAS")
	public VisitaSeguimientoCasoCovid19 getCodigoCasoVisita() {
		return codigoCasoVisita;
	}

	public void setCodigoCasoVisita(VisitaSeguimientoCasoCovid19 codigoCasoVisita) {
		this.codigoCasoVisita = codigoCasoVisita;
	}

    @Column(name = "FECHA_SINTOMAS", nullable = false)
	public Date getFechaSintomas() {
		return fechaSintomas;
	}

	public void setFechaSintomas(Date fechaSintomas) {
		this.fechaSintomas = fechaSintomas;
	}

    @Column(name = "FIEBRE", length = 2, nullable = false)
	public String getFiebre() {
		return fiebre;
	}

	public void setFiebre(String fiebre) {
		this.fiebre = fiebre;
	}

    @Column(name = "FIEBRE_INTENSIDAD", length = 2, nullable = true)
    public String getFiebreIntensidad() {
		return fiebreIntensidad;
	}

	public void setFiebreIntensidad(String fiebreIntensidad) {
		this.fiebreIntensidad = fiebreIntensidad;
	}

	/*public Date getFif() {
		return fif;
	}

	public void setFif(Date fif) {
		this.fif = fif;
	}*/

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

    @Column(name = "DOLOR_CABEZA", length = 2, nullable = false)
	public String getDolorCabeza() {
		return dolorCabeza;
	}

	public void setDolorCabeza(String dolorCabeza) {
		this.dolorCabeza = dolorCabeza;
	}

    @Column(name = "DOLOR_CABEZA_INTENSIDAD", length = 2, nullable = true)
	public String getDolorCabezaIntensidad() {
		return dolorCabezaIntensidad;
	}

	public void setDolorCabezaIntensidad(String dolorCabezaIntensidad) {
		this.dolorCabezaIntensidad = dolorCabezaIntensidad;
	}

    @Column(name = "DOLOR_ARTICULAR", length = 2, nullable = false)
	public String getDolorArticular() {
		return dolorArticular;
	}

	public void setDolorArticular(String dolorArticular) {
		this.dolorArticular = dolorArticular;
	}

    @Column(name = "DOLOR_ARTICULAR_INTENSIDAD", length = 2, nullable = true)
	public String getDolorArticularIntensidad() {
		return dolorArticularIntensidad;
	}

	public void setDolorArticularIntensidad(String dolorArticularIntensidad) {
		this.dolorArticularIntensidad = dolorArticularIntensidad;
	}

    @Column(name = "DOLOR_MUSCULAR", length = 2, nullable = false)
	public String getDolorMuscular() {
		return dolorMuscular;
	}

	public void setDolorMuscular(String dolorMuscular) {
		this.dolorMuscular = dolorMuscular;
	}

    @Column(name = "DOLOR_MUSCULAR_INTENSIDAD", length = 2, nullable = true)
	public String getDolorMuscularIntensidad() {
		return dolorMuscularIntensidad;
	}

	public void setDolorMuscularIntensidad(String dolorMuscularIntensidad) {
		this.dolorMuscularIntensidad = dolorMuscularIntensidad;
	}

    @Column(name = "DIFICULTAD_RESPIRATORIA", length = 2, nullable = false)
	public String getDificultadRespiratoria() {
		return dificultadRespiratoria;
	}

	public void setDificultadRespiratoria(String dificultadRespiratoria) {
		this.dificultadRespiratoria = dificultadRespiratoria;
	}

    @Column(name = "SECRECION_NASAL", length = 2, nullable = false)
	public String getSecrecionNasal() {
		return secrecionNasal;
	}

	public void setSecrecionNasal(String secrecionNasal) {
		this.secrecionNasal = secrecionNasal;
	}

    @Column(name = "SECRECION_NASAL_INTENSIDAD", length = 2, nullable = true)
	public String getSecrecionNasalIntensidad() {
		return secrecionNasalIntensidad;
	}

	public void setSecrecionNasalIntensidad(String secrecionNasalIntensidad) {
		this.secrecionNasalIntensidad = secrecionNasalIntensidad;
	}

    @Column(name = "TOS", length = 2, nullable = false)
	public String getTos() {
		return tos;
	}

	public void setTos(String tos) {
		this.tos = tos;
	}

    @Column(name = "TOS_INTENSIDAD", length = 2, nullable = true)
	public String getTosIntensidad() {
		return tosIntensidad;
	}

	public void setTosIntensidad(String tosIntensidad) {
		this.tosIntensidad = tosIntensidad;
	}

    @Column(name = "POCO_APETITO", length = 2, nullable = false)
	public String getPocoApetito() {
		return pocoApetito;
	}

	public void setPocoApetito(String pocoApetito) {
		this.pocoApetito = pocoApetito;
	}

    @Column(name = "DOLOR_GARGANTA", length = 2, nullable = false)
	public String getDolorGarganta() {
		return dolorGarganta;
	}

	public void setDolorGarganta(String dolorGarganta) {
		this.dolorGarganta = dolorGarganta;
	}

    @Column(name = "DOLOR_GARGANTA_INTENSIDAD", length = 2, nullable = true)
	public String getDolorGargantaIntensidad() {
		return dolorGargantaIntensidad;
	}

	public void setDolorGargantaIntensidad(String dolorGargantaIntensidad) {
		this.dolorGargantaIntensidad = dolorGargantaIntensidad;
	}

    @Column(name = "PICOR_GARGANTA", length = 2, nullable = false)
	public String getPicorGarganta() {
		return picorGarganta;
	}

	public void setPicorGarganta(String picorGarganta) {
		this.picorGarganta = picorGarganta;
	}

    @Column(name = "CONGESTION_NASAL", length = 2, nullable = false)
	public String getCongestionNasal() {
		return congestionNasal;
	}

	public void setCongestionNasal(String congestionNasal) {
		this.congestionNasal = congestionNasal;
	}

    @Column(name = "RASH", length = 2, nullable = false)
	public String getRash() {
		return rash;
	}

	public void setRash(String rash) {
		this.rash = rash;
	}

    @Column(name = "URTICARIA", length = 2, nullable = false)
	public String getUrticaria() {
		return urticaria;
	}

	public void setUrticaria(String urticaria) {
		this.urticaria = urticaria;
	}

    @Column(name = "CONJUNTIVITIS", length = 2, nullable = false)
	public String getConjuntivitis() {
		return conjuntivitis;
	}

	public void setConjuntivitis(String conjuntivitis) {
		this.conjuntivitis = conjuntivitis;
	}

    @Column(name = "EXPECTORACION", length = 2, nullable = false)
	public String getExpectoracion() {
		return expectoracion;
	}

	public void setExpectoracion(String expectoracion) {
		this.expectoracion = expectoracion;
	}

    @Column(name = "DIARREA", length = 2, nullable = false)
	public String getDiarrea() {
		return diarrea;
	}

	public void setDiarrea(String diarrea) {
		this.diarrea = diarrea;
	}

    @Column(name = "VOMITO", length = 2, nullable = false)
	public String getVomito() {
		return vomito;
	}

	public void setVomito(String vomito) {
		this.vomito = vomito;
	}

    @Column(name = "FATIGA", length = 2, nullable = false)
	public String getFatiga() {
		return fatiga;
	}

	public void setFatiga(String fatiga) {
		this.fatiga = fatiga;
	}

    @Column(name = "RESP_RUIDOSA", length = 2, nullable = false)
	public String getRespiracionRuidosa() {
		return respiracionRuidosa;
	}

	public void setRespiracionRuidosa(String respiracionRuidosa) {
		this.respiracionRuidosa = respiracionRuidosa;
	}

    @Column(name = "RESP_RAPIDA", length = 2, nullable = false)
	public String getRespiracionRapida() {
		return respiracionRapida;
	}

	public void setRespiracionRapida(String respiracionRapida) {
		this.respiracionRapida = respiracionRapida;
	}

    @Column(name = "PERRIDA_OLFATO", length = 2, nullable = false)
	public String getPerdidaOlfato() {
		return perdidaOlfato;
	}

	public void setPerdidaOlfato(String perdidaOlfato) {
		this.perdidaOlfato = perdidaOlfato;
	}

    @Column(name = "PERRIDA_GUSTO", length = 2, nullable = false)
	public String getPerdidaGusto() {
		return perdidaGusto;
	}

	public void setPerdidaGusto(String perdidaGusto) {
		this.perdidaGusto = perdidaGusto;
	}

    @Column(name = "DESMAYOS", length = 2, nullable = false)
	public String getDesmayos() {
		return desmayos;
	}

	public void setDesmayos(String desmayos) {
		this.desmayos = desmayos;
	}

    @Column(name = "SENSACION_PA", length = 2, nullable = false)
	public String getSensacionPechoApretado() {
		return sensacionPechoApretado;
	}

	public void setSensacionPechoApretado(String sensacionPechoApretado) {
		this.sensacionPechoApretado = sensacionPechoApretado;
	}

    @Column(name = "DOLOR_PECHO", length = 2, nullable = false)
	public String getDolorPecho() {
		return dolorPecho;
	}

	public void setDolorPecho(String dolorPecho) {
		this.dolorPecho = dolorPecho;
	}

    @Column(name = "SENSACION_FA", length = 2, nullable = false)
	public String getSensacionFaltaAire() {
		return sensacionFaltaAire;
	}

	public void setSensacionFaltaAire(String sensacionFaltaAire) {
		this.sensacionFaltaAire = sensacionFaltaAire;
	}

    @Column(name = "QUEDO_CAMA", length = 2, nullable = false)
	public String getQuedoCama() {
		return quedoCama;
	}

	public void setQuedoCama(String quedoCama) {
		this.quedoCama = quedoCama;
	}

    @Column(name = "TRATAMIENTO", length = 2, nullable = false)
	public String getTratamiento() {
		return tratamiento;
	}

	public void setTratamiento(String tratamiento) {
		this.tratamiento = tratamiento;
	}

    @Column(name = "FECHA_INICIO_TX", length = 2, nullable = true)
	public Date getFechaInicioTx() {
		return fechaInicioTx;
	}

	public void setFechaInicioTx(Date fechaInicioTx) {
		this.fechaInicioTx = fechaInicioTx;
	}

    @Column(name = "CUAL_MEDICAMENTO", length = 64, nullable = true)
	public String getCualMedicamento() {
		return cualMedicamento;
	}

	public void setCualMedicamento(String cualMedicamento) {
		this.cualMedicamento = cualMedicamento;
	}

    @Column(name = "OTRO_MEDICMANTO", length = 250, nullable = true)
	public String getOtroMedicamento() {
		return otroMedicamento;
	}

	public void setOtroMedicamento(String otroMedicamento) {
		this.otroMedicamento = otroMedicamento;
	}

    @Column(name = "ANTIBIOTICO", length = 2, nullable = false)
	public String getAntibiotico() {
		return antibiotico;
	}

	public void setAntibiotico(String antibiotico) {
		this.antibiotico = antibiotico;
	}

    @Column(name = "PRESCRITO_MEDICO", length = 2, nullable = true)
	public String getPrescritoMedico() {
		return prescritoMedico;
	}

	public void setPrescritoMedico(String prescritoMedico) {
		this.prescritoMedico = prescritoMedico;
	}

    @Column(name = "FACTORES_RIESGO", length = 32, nullable = true)
	public String getFactorRiesgo() {
		return factorRiesgo;
	}

	public void setFactorRiesgo(String factorRiesgo) {
		this.factorRiesgo = factorRiesgo;
	}

    @Column(name = "OTRO_FR", length = 250, nullable = true)
	public String getOtroFactorRiesgo() {
		return otroFactorRiesgo;
	}

	public void setOtroFactorRiesgo(String otroFactorRiesgo) {
		this.otroFactorRiesgo = otroFactorRiesgo;
	}

    @Column(name = "PERSONA_ENFERMA", length = 2, nullable = true)
	public String getOtraPersonaEnferma() {
		return otraPersonaEnferma;
	}

	public void setOtraPersonaEnferma(String otraPersonaEnferma) {
		this.otraPersonaEnferma = otraPersonaEnferma;
	}

    @Column(name = "CUANTAS_PERSONAS", nullable = true)
	public Integer getCuantasPersonas() {
		return cuantasPersonas;
	}

	public void setCuantasPersonas(Integer cuantasPersonas) {
		this.cuantasPersonas = cuantasPersonas;
	}

    @Column(name = "TRASLADO_HOSP", length = 2, nullable = true)
	public String getTrasladoHospital() {
		return trasladoHospital;
	}

	public void setTrasladoHospital(String trasladoHospital) {
		this.trasladoHospital = trasladoHospital;
	}

    @Column(name = "CUAL_HOSPITAL", length = 250, nullable = true)
	public String getCualHospital() {
		return cualHospital;
	}

	public void setCualHospital(String cualHospital) {
		this.cualHospital = cualHospital;
	}

    @Column(name = "HOSPITALIZADO", length = 2, nullable = true)
	public String getHospitalizado() {
		return hospitalizado;
	}

	public void setHospitalizado(String hospitalizado) {
		this.hospitalizado = hospitalizado;
	}

    @Column(name = "FECHA_ALTA", nullable = true)
	public Date getFechaAlta() {
		return fechaAlta;
	}

	public void setFechaAlta(Date fechaAlta) {
		this.fechaAlta = fechaAlta;
	}

	@Override
	public String toString(){
		return codigoCasoVisita.getCodigoParticipanteCaso().getParticipante().getCodigo() + "-" + fechaSintomas;
	}
	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SintomasVisitaCasoCovid19)) return false;

        SintomasVisitaCasoCovid19 that = (SintomasVisitaCasoCovid19) o;

        if (!codigoCasoSintoma.equals(that.codigoCasoSintoma)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = codigoCasoSintoma.hashCode();
        result = 31 * result + codigoCasoVisita.getCodigoParticipanteCaso().getParticipante().hashCode();
        return result;
    }

    @Override
    public boolean isFieldAuditable(String fieldname) {
        return true;
    }
}
