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
@Table(name = "covid_sintomas_visfin_casos", catalog = "estudios_ics")
public class SintomasVisitaFinalCovid19 extends BaseMetaData implements Auditable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String codigoCasoSintoma;
	private VisitaFinalCasoCovid19 codigoVisitaFinal;
	private String fiebre;
	private String tos;
	private String dolorCabeza;
	private String dolorArticular;
	private String dolorMuscular;
	private String dificultadRespiratoria;
	private String pocoApetito;
	private String dolorGarganta;
	private String secrecionNasal;
	private String picorGarganta;
	private String expectoracion;
	private String rash;
	private String urticaria;
	private String conjuntivitis;
	private String diarrea;
	private String vomito;
	private String quedoCama;
	private String respiracionRuidosa;
	private String respiracionRapida;
	private String perdidaOlfato;
	private String congestionNasal;
	private String perdidaGusto;
	private String desmayos;
	private String sensacionPechoApretado;
	private String dolorPecho;
	private String sensacionFaltaAire;
	private String fatiga;
	private Date fiebreFecIni;
	private Date tosFecIni;
	private Date dolorCabezaFecIni;
	private Date dolorArticularFecIni;
	private Date dolorMuscularFecIni;
	private Date dificultadRespiratoriaFecIni;
	private Date pocoApetitoFecIni;
	private Date dolorGargantaFecIni;
	private Date secrecionNasalFecIni;
	private Date picorGargantaFecIni;
	private Date expectoracionFecIni;
	private Date rashFecIni;
	private Date urticariaFecIni;
	private Date conjuntivitisFecIni;
	private Date diarreaFecIni;
	private Date vomitoFecIni;
	private Date quedoCamaFecIni;
	private Date respiracionRuidosaFecIni;
	private Date respiracionRapidaFecIni;
	private Date perdidaOlfatoFecIni;
	private Date congestionNasalFecIni;
	private Date perdidaGustoFecIni;
	private Date desmayosFecIni;
	private Date sensacionPechoApretadoFecIni;
	private Date dolorPechoFecIni;
	private Date sensacionFaltaAireFecIni;
	private Date fatigaFecIni;
	private Date fiebreFecFin;
	private Date tosFecFin;
	private Date dolorCabezaFecFin;
	private Date dolorArticularFecFin;
	private Date dolorMuscularFecFin;
	private Date dificultadRespiratoriaFecFin;
	private Date pocoApetitoFecFin;
	private Date dolorGargantaFecFin;
	private Date secrecionNasalFecFin;
	private Date picorGargantaFecFin;
	private Date expectoracionFecFin;
	private Date rashFecFin;
	private Date urticariaFecFin;
	private Date conjuntivitisFecFin;
	private Date diarreaFecFin;
	private Date vomitoFecFin;
	private Date quedoCamaFecFin;
	private Date respiracionRuidosaFecFin;
	private Date respiracionRapidaFecFin;
	private Date perdidaOlfatoFecFin;
	private Date congestionNasalFecFin;
	private Date perdidaGustoFecFin;
	private Date desmayosFecFin;
	private Date sensacionPechoApretadoFecFin;
	private Date dolorPechoFecFin;
	private Date sensacionFaltaAireFecFin;
	private Date fatigaFecFin;

    @Id
    @Column(name = "CODIGO_SINTOMA_CASO", length = 36, nullable = false)
	public String getCodigoCasoSintoma() {
		return codigoCasoSintoma;
	}

	public void setCodigoCasoSintoma(String codigoCasoSintoma) {
		this.codigoCasoSintoma = codigoCasoSintoma;
	}

    @ManyToOne
    @JoinColumn(name = "CODIGO_VISITA_FINAL", nullable = false)
    @ForeignKey(name = "FK_VISITA_FINAL_SINTOMA_COVCAS")
    public VisitaFinalCasoCovid19 getCodigoVisitaFinal() {
		return codigoVisitaFinal;
	}

	public void setCodigoVisitaFinal(VisitaFinalCasoCovid19 codigoVisitaFinal) {
		this.codigoVisitaFinal = codigoVisitaFinal;
	}

    @Column(name = "FIEBRE", length = 2, nullable = false)
	public String getFiebre() {
		return fiebre;
	}

	public void setFiebre(String fiebre) {
		this.fiebre = fiebre;
	}

    @Column(name = "TOS", length = 2, nullable = false)
	public String getTos() {
		return tos;
	}

	public void setTos(String tos) {
		this.tos = tos;
	}

    @Column(name = "DOLOR_CABEZA", length = 2, nullable = false)
    public String getDolorCabeza() {
		return dolorCabeza;
	}

	public void setDolorCabeza(String dolorCabeza) {
		this.dolorCabeza = dolorCabeza;
	}

    @Column(name = "DOLOR_ARTICULAR", length = 2, nullable = false)
	public String getDolorArticular() {
		return dolorArticular;
	}

	public void setDolorArticular(String dolorArticular) {
		this.dolorArticular = dolorArticular;
	}

    @Column(name = "DOLOR_MUSCULAR", length = 2, nullable = false)
	public String getDolorMuscular() {
		return dolorMuscular;
	}

	public void setDolorMuscular(String dolorMuscular) {
		this.dolorMuscular = dolorMuscular;
	}

    @Column(name = "DIFICULTAD_RESPIRATORIA", length = 2, nullable = false)
	public String getDificultadRespiratoria() {
		return dificultadRespiratoria;
	}

	public void setDificultadRespiratoria(String dificultadRespiratoria) {
		this.dificultadRespiratoria = dificultadRespiratoria;
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

    @Column(name = "SECRECION_NASAL", length = 2, nullable = false)
	public String getSecrecionNasal() {
		return secrecionNasal;
	}

	public void setSecrecionNasal(String secrecionNasal) {
		this.secrecionNasal = secrecionNasal;
	}

    @Column(name = "PICOR_GARGANTA", length = 2, nullable = false)
	public String getPicorGarganta() {
		return picorGarganta;
	}

	public void setPicorGarganta(String picorGarganta) {
		this.picorGarganta = picorGarganta;
	}

    @Column(name = "EXPECTORACION", length = 2, nullable = false)
	public String getExpectoracion() {
		return expectoracion;
	}

	public void setExpectoracion(String expectoracion) {
		this.expectoracion = expectoracion;
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

    @Column(name = "QUEDO_CAMA", length = 2, nullable = false)
    public String getQuedoCama() {
		return quedoCama;
	}

	public void setQuedoCama(String quedoCama) {
		this.quedoCama = quedoCama;
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

    @Column(name = "CONGESTION_NASAL", length = 2, nullable = false)
	public String getCongestionNasal() {
		return congestionNasal;
	}

	public void setCongestionNasal(String congestionNasal) {
		this.congestionNasal = congestionNasal;
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

    @Column(name = "FATIGA", length = 2, nullable = false)
	public String getFatiga() {
		return fatiga;
	}

	public void setFatiga(String fatiga) {
		this.fatiga = fatiga;
	}

    @Column(name = "FIEBRE_FI", nullable = true)
    public Date getFiebreFecIni() {
        return fiebreFecIni;
    }

    public void setFiebreFecIni(Date fiebreFecIni) {
        this.fiebreFecIni = fiebreFecIni;
    }

    @Column(name = "TOS_FI", nullable = true)
    public Date getTosFecIni() {
        return tosFecIni;
    }

    public void setTosFecIni(Date tosFecIni) {
        this.tosFecIni = tosFecIni;
    }

    @Column(name = "DOLOR_CABEZA_FI", nullable = true)
    public Date getDolorCabezaFecIni() {
        return dolorCabezaFecIni;
    }

    public void setDolorCabezaFecIni(Date dolorCabezaFecIni) {
        this.dolorCabezaFecIni = dolorCabezaFecIni;
    }

    @Column(name = "DOLOR_ARTICULAR_FI", nullable = true)
    public Date getDolorArticularFecIni() {
        return dolorArticularFecIni;
    }

    public void setDolorArticularFecIni(Date dolorArticularFecIni) {
        this.dolorArticularFecIni = dolorArticularFecIni;
    }

    @Column(name = "DOLOR_MUSCULAR_FI", nullable = true)
    public Date getDolorMuscularFecIni() {
        return dolorMuscularFecIni;
    }

    public void setDolorMuscularFecIni(Date dolorMuscularFecIni) {
        this.dolorMuscularFecIni = dolorMuscularFecIni;
    }

    @Column(name = "DIFICULTAD_RESPIRATORIA_FI", nullable = true)
    public Date getDificultadRespiratoriaFecIni() {
        return dificultadRespiratoriaFecIni;
    }

    public void setDificultadRespiratoriaFecIni(Date dificultadRespiratoriaFecIni) {
        this.dificultadRespiratoriaFecIni = dificultadRespiratoriaFecIni;
    }

    @Column(name = "POCO_APETITO_FI", nullable = true)
    public Date getPocoApetitoFecIni() {
        return pocoApetitoFecIni;
    }

    public void setPocoApetitoFecIni(Date pocoApetitoFecIni) {
        this.pocoApetitoFecIni = pocoApetitoFecIni;
    }

    @Column(name = "DOLOR_GARGANTA_FI", nullable = true)
    public Date getDolorGargantaFecIni() {
        return dolorGargantaFecIni;
    }

    public void setDolorGargantaFecIni(Date dolorGargantaFecIni) {
        this.dolorGargantaFecIni = dolorGargantaFecIni;
    }

    @Column(name = "SECRECION_NASAL_FI", nullable = true)
    public Date getSecrecionNasalFecIni() {
        return secrecionNasalFecIni;
    }

    public void setSecrecionNasalFecIni(Date secrecionNasalFecIni) {
        this.secrecionNasalFecIni = secrecionNasalFecIni;
    }

    @Column(name = "PICOR_GARGANTA_FI", nullable = true)
    public Date getPicorGargantaFecIni() {
        return picorGargantaFecIni;
    }

    public void setPicorGargantaFecIni(Date picorGargantaFecIni) {
        this.picorGargantaFecIni = picorGargantaFecIni;
    }

    @Column(name = "EXPECTORACION_FI", nullable = true)
    public Date getExpectoracionFecIni() {
        return expectoracionFecIni;
    }

    public void setExpectoracionFecIni(Date expectoracionFecIni) {
        this.expectoracionFecIni = expectoracionFecIni;
    }

    @Column(name = "RASH_FI", nullable = true)
    public Date getRashFecIni() {
        return rashFecIni;
    }

    public void setRashFecIni(Date rashFecIni) {
        this.rashFecIni = rashFecIni;
    }

    @Column(name = "URTICARIA_FI", nullable = true)
    public Date getUrticariaFecIni() {
        return urticariaFecIni;
    }

    public void setUrticariaFecIni(Date urticariaFecIni) {
        this.urticariaFecIni = urticariaFecIni;
    }

    @Column(name = "CONJUNTIVITIS_FI", nullable = true)
    public Date getConjuntivitisFecIni() {
        return conjuntivitisFecIni;
    }

    public void setConjuntivitisFecIni(Date conjuntivitisFecIni) {
        this.conjuntivitisFecIni = conjuntivitisFecIni;
    }

    @Column(name = "DIARREA_FI", nullable = true)
    public Date getDiarreaFecIni() {
        return diarreaFecIni;
    }

    public void setDiarreaFecIni(Date diarreaFecIni) {
        this.diarreaFecIni = diarreaFecIni;
    }

    @Column(name = "VOMITO_FI", nullable = true)
    public Date getVomitoFecIni() {
        return vomitoFecIni;
    }

    public void setVomitoFecIni(Date vomitoFecIni) {
        this.vomitoFecIni = vomitoFecIni;
    }

    @Column(name = "QUEDO_CAMA_FI", nullable = true)
    public Date getQuedoCamaFecIni() {
        return quedoCamaFecIni;
    }

    public void setQuedoCamaFecIni(Date quedoCamaFecIni) {
        this.quedoCamaFecIni = quedoCamaFecIni;
    }

    @Column(name = "RESP_RUIDOSA_FI", nullable = true)
    public Date getRespiracionRuidosaFecIni() {
        return respiracionRuidosaFecIni;
    }

    public void setRespiracionRuidosaFecIni(Date respiracionRuidosaFecIni) {
        this.respiracionRuidosaFecIni = respiracionRuidosaFecIni;
    }

    @Column(name = "RESP_RAPIDA_FI", nullable = true)
    public Date getRespiracionRapidaFecIni() {
        return respiracionRapidaFecIni;
    }

    public void setRespiracionRapidaFecIni(Date respiracionRapidaFecIni) {
        this.respiracionRapidaFecIni = respiracionRapidaFecIni;
    }

    @Column(name = "PERRIDA_OLFATO_FI", nullable = true)
    public Date getPerdidaOlfatoFecIni() {
        return perdidaOlfatoFecIni;
    }

    public void setPerdidaOlfatoFecIni(Date perdidaOlfatoFecIni) {
        this.perdidaOlfatoFecIni = perdidaOlfatoFecIni;
    }

    @Column(name = "CONGESTION_NASAL_FI", nullable = true)
    public Date getCongestionNasalFecIni() {
        return congestionNasalFecIni;
    }

    public void setCongestionNasalFecIni(Date congestionNasalFecIni) {
        this.congestionNasalFecIni = congestionNasalFecIni;
    }

    @Column(name = "PERRIDA_GUSTO_FI", nullable = true)
    public Date getPerdidaGustoFecIni() {
        return perdidaGustoFecIni;
    }

    public void setPerdidaGustoFecIni(Date perdidaGustoFecIni) {
        this.perdidaGustoFecIni = perdidaGustoFecIni;
    }

    @Column(name = "DESMAYOS_FI", nullable = true)
    public Date getDesmayosFecIni() {
        return desmayosFecIni;
    }

    public void setDesmayosFecIni(Date desmayosFecIni) {
        this.desmayosFecIni = desmayosFecIni;
    }

    @Column(name = "SENSACION_PA_FI", nullable = true)
    public Date getSensacionPechoApretadoFecIni() {
        return sensacionPechoApretadoFecIni;
    }

    public void setSensacionPechoApretadoFecIni(Date sensacionPechoApretadoFecIni) {
        this.sensacionPechoApretadoFecIni = sensacionPechoApretadoFecIni;
    }

    @Column(name = "DOLOR_PECHO_FI", nullable = true)
    public Date getDolorPechoFecIni() {
        return dolorPechoFecIni;
    }

    public void setDolorPechoFecIni(Date dolorPechoFecIni) {
        this.dolorPechoFecIni = dolorPechoFecIni;
    }

    @Column(name = "SENSACION_FA_FI", nullable = true)
    public Date getSensacionFaltaAireFecIni() {
        return sensacionFaltaAireFecIni;
    }

    public void setSensacionFaltaAireFecIni(Date sensacionFaltaAireFecIni) {
        this.sensacionFaltaAireFecIni = sensacionFaltaAireFecIni;
    }

    @Column(name = "FATIGA_FI", nullable = true)
    public Date getFatigaFecIni() {
        return fatigaFecIni;
    }

    public void setFatigaFecIni(Date fatigaFecIni) {
        this.fatigaFecIni = fatigaFecIni;
    }

    @Column(name = "FIEBRE_FF", nullable = true)
    public Date getFiebreFecFin() {
        return fiebreFecFin;
    }

    public void setFiebreFecFin(Date fiebreFecFin) {
        this.fiebreFecFin = fiebreFecFin;
    }

    @Column(name = "TOS_FF", nullable = true)
    public Date getTosFecFin() {
        return tosFecFin;
    }

    public void setTosFecFin(Date tosFecFin) {
        this.tosFecFin = tosFecFin;
    }

    @Column(name = "DOLOR_CABEZA_FF", nullable = true)
    public Date getDolorCabezaFecFin() {
        return dolorCabezaFecFin;
    }

    public void setDolorCabezaFecFin(Date dolorCabezaFecFin) {
        this.dolorCabezaFecFin = dolorCabezaFecFin;
    }

    @Column(name = "DOLOR_ARTICULAR_FF", nullable = true)
    public Date getDolorArticularFecFin() {
        return dolorArticularFecFin;
    }

    public void setDolorArticularFecFin(Date dolorArticularFecFin) {
        this.dolorArticularFecFin = dolorArticularFecFin;
    }

    @Column(name = "DOLOR_MUSCULAR_FF", nullable = true)
    public Date getDolorMuscularFecFin() {
        return dolorMuscularFecFin;
    }

    public void setDolorMuscularFecFin(Date dolorMuscularFecFin) {
        this.dolorMuscularFecFin = dolorMuscularFecFin;
    }

    @Column(name = "DIFICULTAD_RESPIRATORIA_FF", nullable = true)
    public Date getDificultadRespiratoriaFecFin() {
        return dificultadRespiratoriaFecFin;
    }

    public void setDificultadRespiratoriaFecFin(Date dificultadRespiratoriaFecFin) {
        this.dificultadRespiratoriaFecFin = dificultadRespiratoriaFecFin;
    }

    @Column(name = "POCO_APETITO_FF", nullable = true)
    public Date getPocoApetitoFecFin() {
        return pocoApetitoFecFin;
    }

    public void setPocoApetitoFecFin(Date pocoApetitoFecFin) {
        this.pocoApetitoFecFin = pocoApetitoFecFin;
    }

    @Column(name = "DOLOR_GARGANTA_FF", nullable = true)
    public Date getDolorGargantaFecFin() {
        return dolorGargantaFecFin;
    }

    public void setDolorGargantaFecFin(Date dolorGargantaFecFin) {
        this.dolorGargantaFecFin = dolorGargantaFecFin;
    }

    @Column(name = "SECRECION_NASAL_FF", nullable = true)
    public Date getSecrecionNasalFecFin() {
        return secrecionNasalFecFin;
    }

    public void setSecrecionNasalFecFin(Date secrecionNasalFecFin) {
        this.secrecionNasalFecFin = secrecionNasalFecFin;
    }

    @Column(name = "PICOR_GARGANTA_FF", nullable = true)
    public Date getPicorGargantaFecFin() {
        return picorGargantaFecFin;
    }

    public void setPicorGargantaFecFin(Date picorGargantaFecFin) {
        this.picorGargantaFecFin = picorGargantaFecFin;
    }

    @Column(name = "EXPECTORACION_FF", nullable = true)
    public Date getExpectoracionFecFin() {
        return expectoracionFecFin;
    }

    public void setExpectoracionFecFin(Date expectoracionFecFin) {
        this.expectoracionFecFin = expectoracionFecFin;
    }

    @Column(name = "RASH_FF", nullable = true)
    public Date getRashFecFin() {
        return rashFecFin;
    }

    public void setRashFecFin(Date rashFecFin) {
        this.rashFecFin = rashFecFin;
    }

    @Column(name = "URTICARIA_FF", nullable = true)
    public Date getUrticariaFecFin() {
        return urticariaFecFin;
    }

    public void setUrticariaFecFin(Date urticariaFecFin) {
        this.urticariaFecFin = urticariaFecFin;
    }

    @Column(name = "CONJUNTIVITIS_FF", nullable = true)
    public Date getConjuntivitisFecFin() {
        return conjuntivitisFecFin;
    }

    public void setConjuntivitisFecFin(Date conjuntivitisFecFin) {
        this.conjuntivitisFecFin = conjuntivitisFecFin;
    }

    @Column(name = "DIARREA_FF", nullable = true)
    public Date getDiarreaFecFin() {
        return diarreaFecFin;
    }

    public void setDiarreaFecFin(Date diarreaFecFin) {
        this.diarreaFecFin = diarreaFecFin;
    }

    @Column(name = "VOMITO_FF", nullable = true)
    public Date getVomitoFecFin() {
        return vomitoFecFin;
    }

    public void setVomitoFecFin(Date vomitoFecFin) {
        this.vomitoFecFin = vomitoFecFin;
    }

    @Column(name = "QUEDO_CAMA_FF", nullable = true)
    public Date getQuedoCamaFecFin() {
        return quedoCamaFecFin;
    }

    public void setQuedoCamaFecFin(Date quedoCamaFecFin) {
        this.quedoCamaFecFin = quedoCamaFecFin;
    }

    @Column(name = "RESP_RUIDOSA_FF", nullable = true)
    public Date getRespiracionRuidosaFecFin() {
        return respiracionRuidosaFecFin;
    }

    public void setRespiracionRuidosaFecFin(Date respiracionRuidosaFecFin) {
        this.respiracionRuidosaFecFin = respiracionRuidosaFecFin;
    }

    @Column(name = "RESP_RAPIDA_FF", nullable = true)
    public Date getRespiracionRapidaFecFin() {
        return respiracionRapidaFecFin;
    }

    public void setRespiracionRapidaFecFin(Date respiracionRapidaFecFin) {
        this.respiracionRapidaFecFin = respiracionRapidaFecFin;
    }

    @Column(name = "PERRIDA_OLFATO_FF", nullable = true)
    public Date getPerdidaOlfatoFecFin() {
        return perdidaOlfatoFecFin;
    }

    public void setPerdidaOlfatoFecFin(Date perdidaOlfatoFecFin) {
        this.perdidaOlfatoFecFin = perdidaOlfatoFecFin;
    }

    @Column(name = "CONGESTION_NASAL_FF", nullable = true)
    public Date getCongestionNasalFecFin() {
        return congestionNasalFecFin;
    }

    public void setCongestionNasalFecFin(Date congestionNasalFecFin) {
        this.congestionNasalFecFin = congestionNasalFecFin;
    }

    @Column(name = "PERRIDA_GUSTO_FF", nullable = true)
    public Date getPerdidaGustoFecFin() {
        return perdidaGustoFecFin;
    }

    public void setPerdidaGustoFecFin(Date perdidaGustoFecFin) {
        this.perdidaGustoFecFin = perdidaGustoFecFin;
    }

    @Column(name = "DESMAYOS_FF", nullable = true)
    public Date getDesmayosFecFin() {
        return desmayosFecFin;
    }

    public void setDesmayosFecFin(Date desmayosFecFin) {
        this.desmayosFecFin = desmayosFecFin;
    }

    @Column(name = "SENSACION_PA_FF", nullable = true)
    public Date getSensacionPechoApretadoFecFin() {
        return sensacionPechoApretadoFecFin;
    }

    public void setSensacionPechoApretadoFecFin(Date sensacionPechoApretadoFecFin) {
        this.sensacionPechoApretadoFecFin = sensacionPechoApretadoFecFin;
    }

    @Column(name = "DOLOR_PECHO_FF", nullable = true)
    public Date getDolorPechoFecFin() {
        return dolorPechoFecFin;
    }

    public void setDolorPechoFecFin(Date dolorPechoFecFin) {
        this.dolorPechoFecFin = dolorPechoFecFin;
    }

    @Column(name = "SENSACION_FA_FF", nullable = true)
    public Date getSensacionFaltaAireFecFin() {
        return sensacionFaltaAireFecFin;
    }

    public void setSensacionFaltaAireFecFin(Date sensacionFaltaAireFecFin) {
        this.sensacionFaltaAireFecFin = sensacionFaltaAireFecFin;
    }

    @Column(name = "FATIGA_FF", nullable = true)
    public Date getFatigaFecFin() {
        return fatigaFecFin;
    }

    public void setFatigaFecFin(Date fatigaFecFin) {
        this.fatigaFecFin = fatigaFecFin;
    }

    @Override
	public String toString(){
		return codigoVisitaFinal.getCodigoParticipanteCaso().getParticipante().getCodigo() + "-" + codigoVisitaFinal.getFechaVisita();
	}
	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SintomasVisitaFinalCovid19)) return false;

        SintomasVisitaFinalCovid19 that = (SintomasVisitaFinalCovid19) o;

        if (!codigoCasoSintoma.equals(that.codigoCasoSintoma)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = codigoCasoSintoma.hashCode();
        result = 31 * result + codigoVisitaFinal.getCodigoParticipanteCaso().getParticipante().hashCode();
        return result;
    }

    @Override
    public boolean isFieldAuditable(String fieldname) {
        return true;
    }
}
