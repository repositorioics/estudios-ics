package ni.org.ics.estudios.domain.muestreoanual;

import org.codehaus.jackson.annotate.JsonIgnore;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

/**
 * Simple objeto de dominio que representa los datos de la encuesta de los
 * participantes en el estudio
 * 
 * @author William Aviles
 **/

@Entity
@Table(name = "encuestas_participantes", catalog = "estudios_ics")
public class EncuestaParticipanteMA {

	/**
	 * 
	 */
	private EncuestaParticipanteId epId;
	private Integer fiebre;
	private Integer tiemFieb;
	private Integer lugarCons;
	private Integer noCs;
	private String automed;
	private Integer escuela;
	private Integer grado;
	private Integer turno;
	private Integer nEscuela;
	private String otraEscuela;
	private Integer cuidan;
	private Integer cuantosCuidan;
	private Integer cqVive;
	private Integer lugarPart;
	private Integer papaAlf;
	private Integer papaNivel;
	private Integer papaTra;
	private Integer papaTipoTra;
	private Integer mamaAlf;
	private Integer mamaNivel;
	private Integer mamaTra;
	private Integer mamaTipoTra;
	private Integer comparteHab;
	private Integer hab1;
	private Integer hab2;
	private Integer hab3;
	private Integer hab4;
	private Integer hab5;
	private Integer hab6;
	private Integer comparteCama;
	private Integer cama1;
	private Integer cama2;
	private Integer cama3;
	private Integer cama4;
	private Integer cama5;
	private Integer cama6;
	private Integer asma;
	private Integer silb12m;
	private Integer sitResf;
	private Integer sitEjer;
	private Integer silbMesPas;
	private Integer difHablar;
	private Integer vecHablar;
	private Integer difDormir;
	private Integer suenoPer;
	private Integer tos12m;
	private Integer vecesTos;
	private Integer tos3Dias;
	private Integer hosp12m;
	private Integer med12m;
	private Integer dep12m;
	private Integer crisis;
	private Integer frecAsma;
	private Integer FumaSN;
	private String quienFuma;
	private Integer cantCigarrosMadre;
	private Integer cantCigarrosPadre;
	private Integer cantCigarrosOtros;
	
	
	private Integer rash;
	private Integer mesActual;
	private Integer yearActual;
	private Integer rash_year;
	private Integer rash_mes;
	private Integer rash_mesact;
	private Integer rashCara;
	private Integer rashMiembrosSup;
	private Integer rashTorax;
	private Integer rashAbdomen;
	private Integer rashMiembrosInf;
	private Integer rashDias;
	private Integer ojoRojo;
	private Integer ojoRojo_year;
	private Integer ojoRojo_mes;
	private Integer ojoRojo_mesact;
	private Integer ojoRojo_Dias;
	private Integer hormigueo;
	private Integer hormigueo_year;
	private Integer hormigueo_mes;
	private Integer hormigueo_mesact;
	private Integer hormigueo_Dias;
	private Integer consultaRashHormigueo;
	private Integer uSaludRashHormigueo;
	private Integer cSaludRashHormigueo;
	private String oCSRashHormigueo;
	private Integer pSRashHormigueo;
	private String oPSRashHormigueo;
	private String diagRashHormigueo;
	private String chPapaMama;
	private Date fechana_papa;
	private Integer cal_edad_papa;
	private Integer cal_edad2_papa;
	private String nombpapa1;
	private String nombpapa2;
	private String apellipapa1;
	private String apellipapa2;
	private Date fechana_mama;
	private Integer cal_edad_mama;
	private Integer cal_edad2_mama;
	private String nombmama1;
	private String nombmama2;
	private String apellimama1;
	private String apellimama2;
	
	private Integer otrorecurso1;
	private Integer otrorecurso2;

    //CHF + NUEVAS PREGUNTAS MA2018
    private String emancipado;
    private String descEnmancipado;
    private String otraDescEmanc;
    private String embarazada;
    private Integer semanasEmbarazo;
    private String alfabeto;
    private String nivelEducacion;
    private String trabaja;
    private String tipoTrabajo;
    private String ocupacionActual;
    private String ninoAsisteEscuela;
    private String gradoEstudiaNino;
    private String ninoTrabaja;
    private String ocupacionActualNino;
    private String padreEstudio;
    private String codigoPadreEstudio;
    private String padreAlfabeto;
    private String trabajaPadre;
    private String madreEstudio;
    private String codigoMadreEstudio;
    private String madreAlfabeta;
    private String trabajaMadre;
    private String fuma;
    private String periodicidadFuna;
    private Integer cantidadCigarrillos;
    private String fumaDentroCasa;
    private String tuberculosisPulmonarActual;
    private String fechaDiagTubpulActual;
    private String TratamientoTubpulActual;
    private String CompletoTratamientoTubpulActual;
    private String TuberculosisPulmonarPasado;
    private String FechaDiagTubpulPasadoDes;
    private String fechaDiagTubpulPasado;
    private String tratamientoTubpulPasado;
    private String completoTratamientoTubpulPas;
    private String alergiaRespiratoria;
    private String cardiopatia;
    private String enfermPulmonarObstCronica;
    private String diabetes;
    private String presionAlta;
    private String tosSinFiebreResfriado;
    private Integer cantEnfermCuadrosResp;
    private String otrasEnfermedades;
    private String descOtrasEnfermedades;
    private String vacunaInfluenza;
    private Integer anioVacunaInfluenza;
    private String rash6m;
    private String ojoRojo6m;
    private String estudiosAct; // estudios actuales al momento de llenar la encuesta
    //MA 2019
    private Integer vacunaInfluenzaMes;
    private String vacunaInfluenzaCSSF;
    private String vacunaInfluenzaOtro;
    private String nombreCDI;
    private String direccionCDI;
	private MovilInfo movilInfo;
	//MA2020
	private String otroLugarCuidan;
	private String enfermedadCronica;
	private String tenidoDengue;
	private String unidadSaludDengue;
	private String centroSaludDengue;
	private String otroCentroSaludDengue;
	private String puestoSaludDengue;
	private String otroPuestoSaludDengue;
	private String hospitalDengue;
	private String otroHospitalDengue;
	private String hospitalizadoDengue;
	private String ambulatorioDengue;
	private String diagMedicoDengue;
	private String rashUA; //sustitución de rash6m
	private String consultaRashUA; //sustitución de consultaRashHormigueo
    private Boolean encuestaValida; //18-01-2024

	@EmbeddedId
	public EncuestaParticipanteId getEpId() {
		return epId;
	}

	public void setEpId(EncuestaParticipanteId epId) {
		this.epId = epId;
	}

	@Column(name = "fiebre", nullable = true, length = 1)
	public Integer getFiebre() {
		return fiebre;
	}

	public void setFiebre(Integer fiebre) {
		this.fiebre = fiebre;
	}

	@Column(name = "tiemfieb", nullable = true, length = 1)
	public Integer getTiemFieb() {
		return tiemFieb;
	}

	public void setTiemFieb(Integer tiemFieb) {
		this.tiemFieb = tiemFieb;
	}

	@Column(name = "lugarcons", nullable = true, length = 1)
	public Integer getLugarCons() {
		return lugarCons;
	}

	public void setLugarCons(Integer lugarCons) {
		this.lugarCons = lugarCons;
	}

	@Column(name = "nocs", nullable = true)
	public Integer getNoCs() {
		return noCs;
	}

	public void setNoCs(Integer noCs) {
		this.noCs = noCs;
	}

	@Column(name = "automed", nullable = true)
	public String getAutomed() {
		return automed;
	}

	public void setAutomed(String automed) {
		this.automed = automed;
	}

	@Column(name = "escuela", nullable = true)
	public Integer getEscuela() {
		return escuela;
	}

	public void setEscuela(Integer escuela) {
		this.escuela = escuela;
	}

	@Column(name = "grado", nullable = true)
	public Integer getGrado() {
		return grado;
	}

	public void setGrado(Integer grado) {
		this.grado = grado;
	}

	@Column(name = "turno", nullable = true)
	public Integer getTurno() {
		return turno;
	}

	public void setTurno(Integer turno) {
		this.turno = turno;
	}

	@Column(name = "nescuela", nullable = true)
	public Integer getnEscuela() {
		return nEscuela;
	}

	public void setnEscuela(Integer nEscuela) {
		this.nEscuela = nEscuela;
	}

	@Column(name = "otraescuela", nullable = true, length = 50)
	public String getOtraEscuela() {
		return otraEscuela;
	}

	public void setOtraEscuela(String otraEscuela) {
		this.otraEscuela = otraEscuela;
	}

	@Column(name = "cuidan", nullable = true)
	public Integer getCuidan() {
		return cuidan;
	}

	public void setCuidan(Integer cuidan) {
		this.cuidan = cuidan;
	}

	@Column(name = "cuantoscuidan", nullable = true)
	public Integer getCuantosCuidan() {
		return cuantosCuidan;
	}

	public void setCuantosCuidan(Integer cuantosCuidan) {
		this.cuantosCuidan = cuantosCuidan;
	}

	@Column(name = "cqvive", nullable = true)
	public Integer getCqVive() {
		return cqVive;
	}

	public void setCqVive(Integer cqVive) {
		this.cqVive = cqVive;
	}

	@Column(name = "num_parto", nullable = true)
	public Integer getLugarPart() {
		return lugarPart;
	}

	public void setLugarPart(Integer lugarPart) {
		this.lugarPart = lugarPart;
	}

	@Column(name = "papaalf", nullable = true)
	public Integer getPapaAlf() {
		return papaAlf;
	}

	public void setPapaAlf(Integer papaAlf) {
		this.papaAlf = papaAlf;
	}

	@Column(name = "papanivel", nullable = true)
	public Integer getPapaNivel() {
		return papaNivel;
	}

	public void setPapaNivel(Integer papaNivel) {
		this.papaNivel = papaNivel;
	}

	@Column(name = "papatra", nullable = true)
	public Integer getPapaTra() {
		return papaTra;
	}

	public void setPapaTra(Integer papaTra) {
		this.papaTra = papaTra;
	}

	@Column(name = "papatipotra", nullable = true)
	public Integer getPapaTipoTra() {
		return papaTipoTra;
	}

	public void setPapaTipoTra(Integer papaTipoTra) {
		this.papaTipoTra = papaTipoTra;
	}

	@Column(name = "mamaalf", nullable = true)
	public Integer getMamaAlf() {
		return mamaAlf;
	}

	public void setMamaAlf(Integer mamaAlf) {
		this.mamaAlf = mamaAlf;
	}

	@Column(name = "mamanivel", nullable = true)
	public Integer getMamaNivel() {
		return mamaNivel;
	}

	public void setMamaNivel(Integer mamaNivel) {
		this.mamaNivel = mamaNivel;
	}

	@Column(name = "mamatra", nullable = true)
	public Integer getMamaTra() {
		return mamaTra;
	}

	public void setMamaTra(Integer mamaTra) {
		this.mamaTra = mamaTra;
	}

	@Column(name = "mamatipotra", nullable = true)
	public Integer getMamaTipoTra() {
		return mamaTipoTra;
	}

	public void setMamaTipoTra(Integer mamaTipoTra) {
		this.mamaTipoTra = mamaTipoTra;
	}

	@Column(name = "compartehab", nullable = true)
	public Integer getComparteHab() {
		return comparteHab;
	}

	public void setComparteHab(Integer comparteHab) {
		this.comparteHab = comparteHab;
	}

	@Column(name = "hab1", nullable = true, length = 2)
	public Integer getHab1() {
		return hab1;
	}

	public void setHab1(Integer hab1) {
		this.hab1 = hab1;
	}

	@Column(name = "hab2", nullable = true, length = 2)
	public Integer getHab2() {
		return hab2;
	}

	public void setHab2(Integer hab2) {
		this.hab2 = hab2;
	}

	@Column(name = "hab3", nullable = true, length = 2)
	public Integer getHab3() {
		return hab3;
	}

	public void setHab3(Integer hab3) {
		this.hab3 = hab3;
	}

	@Column(name = "hab4", nullable = true, length = 2)
	public Integer getHab4() {
		return hab4;
	}

	public void setHab4(Integer hab4) {
		this.hab4 = hab4;
	}

	@Column(name = "hab5", nullable = true, length = 2)
	public Integer getHab5() {
		return hab5;
	}

	public void setHab5(Integer hab5) {
		this.hab5 = hab5;
	}

	@Column(name = "hab6", nullable = true, length = 2)
	public Integer getHab6() {
		return hab6;
	}

	public void setHab6(Integer hab6) {
		this.hab6 = hab6;
	}

	@Column(name = "compartecama", nullable = true)
	public Integer getComparteCama() {
		return comparteCama;
	}

	public void setComparteCama(Integer comparteCama) {
		this.comparteCama = comparteCama;
	}

	@Column(name = "cama1", nullable = true, length = 2)
	public Integer getCama1() {
		return cama1;
	}

	public void setCama1(Integer cama1) {
		this.cama1 = cama1;
	}

	@Column(name = "cama2", nullable = true, length = 2)
	public Integer getCama2() {
		return cama2;
	}

	public void setCama2(Integer cama2) {
		this.cama2 = cama2;
	}

	@Column(name = "cama3", nullable = true, length = 2)
	public Integer getCama3() {
		return cama3;
	}

	public void setCama3(Integer cama3) {
		this.cama3 = cama3;
	}

	@Column(name = "cama4", nullable = true, length = 2)
	public Integer getCama4() {
		return cama4;
	}

	public void setCama4(Integer cama4) {
		this.cama4 = cama4;
	}

	@Column(name = "cama5", nullable = true, length = 2)
	public Integer getCama5() {
		return cama5;
	}

	public void setCama5(Integer cama5) {
		this.cama5 = cama5;
	}

	@Column(name = "cama6", nullable = true, length = 2)
	public Integer getCama6() {
		return cama6;
	}

	public void setCama6(Integer cama6) {
		this.cama6 = cama6;
	}

	@Column(name = "asma", nullable = true)
	public Integer getAsma() {
		return asma;
	}

	public void setAsma(Integer asma) {
		this.asma = asma;
	}

	@Column(name = "silb12m", nullable = true)
	public Integer getSilb12m() {
		return silb12m;
	}

	public void setSilb12m(Integer silb12m) {
		this.silb12m = silb12m;
	}

	@Column(name = "sitresf", nullable = true)
	public Integer getSitResf() {
		return sitResf;
	}

	public void setSitResf(Integer sitResf) {
		this.sitResf = sitResf;
	}

	@Column(name = "sitejer", nullable = true)
	public Integer getSitEjer() {
		return sitEjer;
	}

	public void setSitEjer(Integer sitEjer) {
		this.sitEjer = sitEjer;
	}

	@Column(name = "silbmespas", nullable = true)
	public Integer getSilbMesPas() {
		return silbMesPas;
	}

	public void setSilbMesPas(Integer silbMesPas) {
		this.silbMesPas = silbMesPas;
	}

	@Column(name = "difhablar", nullable = true)
	public Integer getDifHablar() {
		return difHablar;
	}

	public void setDifHablar(Integer difHablar) {
		this.difHablar = difHablar;
	}

	@Column(name = "vechablar", nullable = true)
	public Integer getVecHablar() {
		return vecHablar;
	}

	public void setVecHablar(Integer vecHablar) {
		this.vecHablar = vecHablar;
	}

	@Column(name = "difdormir", nullable = true)
	public Integer getDifDormir() {
		return difDormir;
	}

	public void setDifDormir(Integer difDormir) {
		this.difDormir = difDormir;
	}

	@Column(name = "suenoper", nullable = true)
	public Integer getSuenoPer() {
		return suenoPer;
	}

	public void setSuenoPer(Integer suenoPer) {
		this.suenoPer = suenoPer;
	}

	@Column(name = "tos12m", nullable = true)
	public Integer getTos12m() {
		return tos12m;
	}

	public void setTos12m(Integer tos12m) {
		this.tos12m = tos12m;
	}

	@Column(name = "vecestos", nullable = true)
	public Integer getVecesTos() {
		return vecesTos;
	}

	public void setVecesTos(Integer vecesTos) {
		this.vecesTos = vecesTos;
	}

	@Column(name = "tos3dias", nullable = true)
	public Integer getTos3Dias() {
		return tos3Dias;
	}

	public void setTos3Dias(Integer tos3Dias) {
		this.tos3Dias = tos3Dias;
	}

	@Column(name = "hosp12m", nullable = true)
	public Integer getHosp12m() {
		return hosp12m;
	}

	public void setHosp12m(Integer hosp12m) {
		this.hosp12m = hosp12m;
	}

	@Column(name = "med12m", nullable = true)
	public Integer getMed12m() {
		return med12m;
	}

	public void setMed12m(Integer med12m) {
		this.med12m = med12m;
	}

	@Column(name = "dep12m", nullable = true)
	public Integer getDep12m() {
		return dep12m;
	}

	public void setDep12m(Integer dep12m) {
		this.dep12m = dep12m;
	}

	@Column(name = "crisis", nullable = true)
	public Integer getCrisis() {
		return crisis;
	}

	public void setCrisis(Integer crisis) {
		this.crisis = crisis;
	}

	@Column(name = "frecasma", nullable = true)
	public Integer getFrecAsma() {
		return frecAsma;
	}

	public void setFrecAsma(Integer frecAsma) {
		this.frecAsma = frecAsma;
	}

	@Column(name = "Fumasn", nullable = true)
	public Integer getFumaSN() {
		return FumaSN;
	}

	public void setFumaSN(Integer fumaSN) {
		FumaSN = fumaSN;
	}

	@Column(name = "quienfuma", nullable = true)
	public String getQuienFuma() {
		return quienFuma;
	}

	public void setQuienFuma(String quienFuma) {
		this.quienFuma = quienFuma;
	}
	
	@Column(name = "cant_cigarros_madre", nullable = true)
	public Integer getCantCigarrosMadre() {
		return cantCigarrosMadre;
	}

	public void setCantCigarrosMadre(Integer cantCigarrosMadre) {
		this.cantCigarrosMadre = cantCigarrosMadre;
	}

	@Column(name = "cant_cigarros_padre", nullable = true)
	public Integer getCantCigarrosPadre() {
		return cantCigarrosPadre;
	}

	public void setCantCigarrosPadre(Integer cantCigarrosPadre) {
		this.cantCigarrosPadre = cantCigarrosPadre;
	}

	@Column(name = "cant_cigarros_otros", nullable = true)
	public Integer getCantCigarrosOtros() {
		return cantCigarrosOtros;
	}

	public void setCantCigarrosOtros(Integer cantCigarrosOtros) {
		this.cantCigarrosOtros = cantCigarrosOtros;
	}

	public MovilInfo getMovilInfo() {
		return movilInfo;
	}

	public void setMovilInfo(MovilInfo movilInfo) {
		this.movilInfo = movilInfo;
	}

	@Column(name = "rash", nullable = true)
	public Integer getRash() {
		return rash;
	}

	public void setRash(Integer rash) {
		this.rash = rash;
	}

	@Column(name = "mesActual", nullable = true)
	public Integer getMesActual() {
		return mesActual;
	}

	public void setMesActual(Integer mesActual) {
		this.mesActual = mesActual;
	}

	@Column(name = "yearActual", nullable = true)
	public Integer getYearActual() {
		return yearActual;
	}

	public void setYearActual(Integer yearActual) {
		this.yearActual = yearActual;
	}

	@Column(name = "rash_year", nullable = true)
	public Integer getRash_year() {
		return rash_year;
	}

	public void setRash_year(Integer rash_year) {
		this.rash_year = rash_year;
	}

	@Column(name = "rash_mes", nullable = true)
	public Integer getRash_mes() {
		return rash_mes;
	}

	public void setRash_mes(Integer rash_mes) {
		this.rash_mes = rash_mes;
	}

	@Column(name = "rash_mesact", nullable = true)
	public Integer getRash_mesact() {
		return rash_mesact;
	}

	public void setRash_mesact(Integer rash_mesact) {
		this.rash_mesact = rash_mesact;
	}

	@Column(name = "rashCara", nullable = true)
	public Integer getRashCara() {
		return rashCara;
	}

	public void setRashCara(Integer rashCara) {
		this.rashCara = rashCara;
	}

	@Column(name = "rashMiembrosSup", nullable = true)
	public Integer getRashMiembrosSup() {
		return rashMiembrosSup;
	}

	public void setRashMiembrosSup(Integer rashMiembrosSup) {
		this.rashMiembrosSup = rashMiembrosSup;
	}

	@Column(name = "rashTorax", nullable = true)
	public Integer getRashTorax() {
		return rashTorax;
	}

	public void setRashTorax(Integer rashTorax) {
		this.rashTorax = rashTorax;
	}

	@Column(name = "rashAbdomen", nullable = true)
	public Integer getRashAbdomen() {
		return rashAbdomen;
	}

	public void setRashAbdomen(Integer rashAbdomen) {
		this.rashAbdomen = rashAbdomen;
	}

	@Column(name = "rashMiembrosInf", nullable = true)
	public Integer getRashMiembrosInf() {
		return rashMiembrosInf;
	}

	public void setRashMiembrosInf(Integer rashMiembrosInf) {
		this.rashMiembrosInf = rashMiembrosInf;
	}

	@Column(name = "rashDias", nullable = true)
	public Integer getRashDias() {
		return rashDias;
	}

	public void setRashDias(Integer rashDias) {
		this.rashDias = rashDias;
	}

	@Column(name = "ojoRojo", nullable = true)
	public Integer getOjoRojo() {
		return ojoRojo;
	}

	public void setOjoRojo(Integer ojoRojo) {
		this.ojoRojo = ojoRojo;
	}

	@Column(name = "ojoRojo_year", nullable = true)
	public Integer getOjoRojo_year() {
		return ojoRojo_year;
	}

	public void setOjoRojo_year(Integer ojoRojo_year) {
		this.ojoRojo_year = ojoRojo_year;
	}

	@Column(name = "ojoRojo_mes", nullable = true)
	public Integer getOjoRojo_mes() {
		return ojoRojo_mes;
	}

	public void setOjoRojo_mes(Integer ojoRojo_mes) {
		this.ojoRojo_mes = ojoRojo_mes;
	}

	@Column(name = "ojoRojo_mesact", nullable = true)
	public Integer getOjoRojo_mesact() {
		return ojoRojo_mesact;
	}

	public void setOjoRojo_mesact(Integer ojoRojo_mesact) {
		this.ojoRojo_mesact = ojoRojo_mesact;
	}

	@Column(name = "ojoRojo_Dias", nullable = true)
	public Integer getOjoRojo_Dias() {
		return ojoRojo_Dias;
	}

	public void setOjoRojo_Dias(Integer ojoRojo_Dias) {
		this.ojoRojo_Dias = ojoRojo_Dias;
	}

	@Column(name = "hormigueo", nullable = true)
	public Integer getHormigueo() {
		return hormigueo;
	}

	public void setHormigueo(Integer hormigueo) {
		this.hormigueo = hormigueo;
	}

	@Column(name = "hormigueo_year", nullable = true)
	public Integer getHormigueo_year() {
		return hormigueo_year;
	}

	public void setHormigueo_year(Integer hormigueo_year) {
		this.hormigueo_year = hormigueo_year;
	}

	@Column(name = "hormigueo_mes", nullable = true)
	public Integer getHormigueo_mes() {
		return hormigueo_mes;
	}

	public void setHormigueo_mes(Integer hormigueo_mes) {
		this.hormigueo_mes = hormigueo_mes;
	}

	@Column(name = "hormigueo_mesact", nullable = true)
	public Integer getHormigueo_mesact() {
		return hormigueo_mesact;
	}

	public void setHormigueo_mesact(Integer hormigueo_mesact) {
		this.hormigueo_mesact = hormigueo_mesact;
	}

	@Column(name = "hormigueo_Dias", nullable = true)
	public Integer getHormigueo_Dias() {
		return hormigueo_Dias;
	}

	public void setHormigueo_Dias(Integer hormigueo_Dias) {
		this.hormigueo_Dias = hormigueo_Dias;
	}

	@Column(name = "consultaRashHormigueo", nullable = true)
	public Integer getConsultaRashHormigueo() {
		return consultaRashHormigueo;
	}

	public void setConsultaRashHormigueo(Integer consultaRashHormigueo) {
		this.consultaRashHormigueo = consultaRashHormigueo;
	}

	@Column(name = "uSaludRashHormigueo", nullable = true)
	public Integer getuSaludRashHormigueo() {
		return uSaludRashHormigueo;
	}

	public void setuSaludRashHormigueo(Integer uSaludRashHormigueo) {
		this.uSaludRashHormigueo = uSaludRashHormigueo;
	}

	@Column(name = "cSaludRashHormigueo", nullable = true)
	public Integer getcSaludRashHormigueo() {
		return cSaludRashHormigueo;
	}

	public void setcSaludRashHormigueo(Integer cSaludRashHormigueo) {
		this.cSaludRashHormigueo = cSaludRashHormigueo;
	}

	@Column(name = "oCSRashHormigueo", nullable = true)
	public String getoCSRashHormigueo() {
		return oCSRashHormigueo;
	}

	public void setoCSRashHormigueo(String oCSRashHormigueo) {
		this.oCSRashHormigueo = oCSRashHormigueo;
	}

	@Column(name = "pSRashHormigueo", nullable = true)
	public Integer getpSRashHormigueo() {
		return pSRashHormigueo;
	}

	public void setpSRashHormigueo(Integer pSRashHormigueo) {
		this.pSRashHormigueo = pSRashHormigueo;
	}

	@Column(name = "oPSRashHormigueo", nullable = true)
	public String getoPSRashHormigueo() {
		return oPSRashHormigueo;
	}

	public void setoPSRashHormigueo(String oPSRashHormigueo) {
		this.oPSRashHormigueo = oPSRashHormigueo;
	}

	@Column(name = "diagRashHormigueo", nullable = true)
	public String getDiagRashHormigueo() {
		return diagRashHormigueo;
	}

	public void setDiagRashHormigueo(String diagRashHormigueo) {
		this.diagRashHormigueo = diagRashHormigueo;
	}

	@Column(name = "chPapaMama", nullable = true)
	public String getChPapaMama() {
		return chPapaMama;
	}

	public void setChPapaMama(String chPapaMama) {
		this.chPapaMama = chPapaMama;
	}

	@Column(name = "fechana_papa", nullable = true)
	public Date getFechana_papa() {
		return fechana_papa;
	}

	public void setFechana_papa(Date fechana_papa) {
		this.fechana_papa = fechana_papa;
	}

	@Column(name = "cal_edad_papa", nullable = true)
	public Integer getCal_edad_papa() {
		return cal_edad_papa;
	}

	public void setCal_edad_papa(Integer cal_edad_papa) {
		this.cal_edad_papa = cal_edad_papa;
	}

	@Column(name = "cal_edad2_papa", nullable = true)
	public Integer getCal_edad2_papa() {
		return cal_edad2_papa;
	}

	public void setCal_edad2_papa(Integer cal_edad2_papa) {
		this.cal_edad2_papa = cal_edad2_papa;
	}

	@Column(name = "nombpapa1", nullable = true)
	public String getNombpapa1() {
		return nombpapa1;
	}

	public void setNombpapa1(String nombpapa1) {
		this.nombpapa1 = nombpapa1;
	}

	@Column(name = "nombpapa2", nullable = true)
	public String getNombpapa2() {
		return nombpapa2;
	}

	public void setNombpapa2(String nombpapa2) {
		this.nombpapa2 = nombpapa2;
	}

	@Column(name = "apellipapa1", nullable = true)
	public String getApellipapa1() {
		return apellipapa1;
	}

	public void setApellipapa1(String apellipapa1) {
		this.apellipapa1 = apellipapa1;
	}

	@Column(name = "apellipapa2", nullable = true)
	public String getApellipapa2() {
		return apellipapa2;
	}

	public void setApellipapa2(String apellipapa2) {
		this.apellipapa2 = apellipapa2;
	}

	@Column(name = "fechana_mama", nullable = true)
	public Date getFechana_mama() {
		return fechana_mama;
	}

	public void setFechana_mama(Date fechana_mama) {
		this.fechana_mama = fechana_mama;
	}

	@Column(name = "cal_edad_mama", nullable = true)
	public Integer getCal_edad_mama() {
		return cal_edad_mama;
	}

	public void setCal_edad_mama(Integer cal_edad_mama) {
		this.cal_edad_mama = cal_edad_mama;
	}

	@Column(name = "cal_edad2_mama", nullable = true)
	public Integer getCal_edad2_mama() {
		return cal_edad2_mama;
	}

	public void setCal_edad2_mama(Integer cal_edad2_mama) {
		this.cal_edad2_mama = cal_edad2_mama;
	}

	@Column(name = "nombmama1", nullable = true)
	public String getNombmama1() {
		return nombmama1;
	}

	public void setNombmama1(String nombmama1) {
		this.nombmama1 = nombmama1;
	}

	@Column(name = "nombmama2", nullable = true)
	public String getNombmama2() {
		return nombmama2;
	}

	public void setNombmama2(String nombmama2) {
		this.nombmama2 = nombmama2;
	}

	@Column(name = "apellimama1", nullable = true)
	public String getApellimama1() {
		return apellimama1;
	}

	public void setApellimama1(String apellimama1) {
		this.apellimama1 = apellimama1;
	}

	@Column(name = "apellimama2", nullable = true)
	public String getApellimama2() {
		return apellimama2;
	}

	public void setApellimama2(String apellimama2) {
		this.apellimama2 = apellimama2;
	}
	
	@Column(name = "otrorecurso1", nullable = true, length = 10)
	public Integer getOtrorecurso1() {
		return otrorecurso1;
	}

	public void setOtrorecurso1(Integer otrorecurso1) {
		this.otrorecurso1 = otrorecurso1;
	}

	@Column(name = "otrorecurso2", nullable = true, length = 10)
	public Integer getOtrorecurso2() {
		return otrorecurso2;
	}

	public void setOtrorecurso2(Integer otrorecurso2) {
		this.otrorecurso2 = otrorecurso2;
	}

    @Column(name = "EMANCIPADO", nullable = true, length = 2)
    public String getEmancipado() {
        return emancipado;
    }

    public void setEmancipado(String emancipado) {
        this.emancipado = emancipado;
    }

    @Column(name = "descEnmancipado", nullable = true, length = 10)
    public String getDescEnmancipado() {
        return descEnmancipado;
    }

    public void setDescEnmancipado(String descEnmancipado) {
        this.descEnmancipado = descEnmancipado;
    }

    @Column(name = "otraDescEmanc", nullable = true, length = 255)
    public String getOtraDescEmanc() {
        return otraDescEmanc;
    }

    public void setOtraDescEmanc(String otraDescEmanc) {
        this.otraDescEmanc = otraDescEmanc;
    }

    @Column(name = "EMBARAZADA", nullable = true, length = 2)
    public String getEmbarazada() {
        return embarazada;
    }

    public void setEmbarazada(String embarazada) {
        this.embarazada = embarazada;
    }

    @Column(name = "SEMANAS_EMBARAZO", nullable = true, length = 2)
    public Integer getSemanasEmbarazo() {
        return semanasEmbarazo;
    }

    public void setSemanasEmbarazo(Integer semanasEmbarazo) {
        this.semanasEmbarazo = semanasEmbarazo;
    }

    @Column(name = "ALFABETO", nullable = true, length = 2)
    public String getAlfabeto() {
        return alfabeto;
    }

    public void setAlfabeto(String alfabeto) {
        this.alfabeto = alfabeto;
    }

    @Column(name = "NIVEL_EDUCACION", nullable = true, length = 2)
    public String getNivelEducacion() {
        return nivelEducacion;
    }

    public void setNivelEducacion(String nivelEducacion) {
        this.nivelEducacion = nivelEducacion;
    }

    @Column(name = "TRABAJA", nullable = true, length = 2)
    public String getTrabaja() {
        return trabaja;
    }

    public void setTrabaja(String trabaja) {
        this.trabaja = trabaja;
    }

    @Column(name = "TIPO_TRABAJO", nullable = true, length = 2)
    public String getTipoTrabajo() {
        return tipoTrabajo;
    }

    public void setTipoTrabajo(String tipoTrabajo) {
        this.tipoTrabajo = tipoTrabajo;
    }

    @Column(name = "OCUPACION_ACTUAL", nullable = true, length = 255)
    public String getOcupacionActual() {
        return ocupacionActual;
    }

    public void setOcupacionActual(String ocupacionActual) {
        this.ocupacionActual = ocupacionActual;
    }

    @Column(name = "NINO_ASISTE_ESCUELA", nullable = true, length = 2)
    public String getNinoAsisteEscuela() {
        return ninoAsisteEscuela;
    }

    public void setNinoAsisteEscuela(String ninoAsisteEscuela) {
        this.ninoAsisteEscuela = ninoAsisteEscuela;
    }

    @Column(name = "GRADO_ESTUDIA_NINO", nullable = true, length = 2)
    public String getGradoEstudiaNino() {
        return gradoEstudiaNino;
    }

    public void setGradoEstudiaNino(String gradoEstudiaNino) {
        this.gradoEstudiaNino = gradoEstudiaNino;
    }

    @Column(name = "NINO_TRABAJA", nullable = true, length = 2)
    public String getNinoTrabaja() {
        return ninoTrabaja;
    }

    public void setNinoTrabaja(String ninoTrabaja) {
        this.ninoTrabaja = ninoTrabaja;
    }

    @Column(name = "OCUPACION_ACTUAL_NINO", nullable = true, length = 255)
    public String getOcupacionActualNino() {
        return ocupacionActualNino;
    }

    public void setOcupacionActualNino(String ocupacionActualNino) {
        this.ocupacionActualNino = ocupacionActualNino;
    }

    @Column(name = "PADRE_ESTUDIO", nullable = true, length = 2)
    public String getPadreEstudio() {
        return padreEstudio;
    }

    public void setPadreEstudio(String padreEstudio) {
        this.padreEstudio = padreEstudio;
    }

    @Column(name = "CODIGO_PADRE_ESTUDIO", nullable = true, length = 50)
    public String getCodigoPadreEstudio() {
        return codigoPadreEstudio;
    }

    public void setCodigoPadreEstudio(String codigoPadreEstudio) {
        this.codigoPadreEstudio = codigoPadreEstudio;
    }

    @Column(name = "PADRE_ALFABETO", nullable = true, length = 2)
    public String getPadreAlfabeto() {
        return padreAlfabeto;
    }

    public void setPadreAlfabeto(String padreAlfabeto) {
        this.padreAlfabeto = padreAlfabeto;
    }

    @Column(name = "TRABAJA_PADRE", nullable = true, length = 2)
    public String getTrabajaPadre() {
        return trabajaPadre;
    }

    public void setTrabajaPadre(String trabajaPadre) {
        this.trabajaPadre = trabajaPadre;
    }

    @Column(name = "MADRE_ESTUDIO", nullable = true, length = 3)
    public String getMadreEstudio() {
        return madreEstudio;
    }

    public void setMadreEstudio(String madreEstudio) {
        this.madreEstudio = madreEstudio;
    }

    @Column(name = "CODIGO_MADRE_ESTUDIO", nullable = true, length = 50)
    public String getCodigoMadreEstudio() {
        return codigoMadreEstudio;
    }

    public void setCodigoMadreEstudio(String codigoMadreEstudio) {
        this.codigoMadreEstudio = codigoMadreEstudio;
    }

    @Column(name = "MADRE_ALFABETA", nullable = true, length = 2)
    public String getMadreAlfabeta() {
        return madreAlfabeta;
    }

    public void setMadreAlfabeta(String madreAlfabeta) {
        this.madreAlfabeta = madreAlfabeta;
    }

    @Column(name = "TRABAJA_MADRE", nullable = true, length = 2)
    public String getTrabajaMadre() {
        return trabajaMadre;
    }

    public void setTrabajaMadre(String trabajaMadre) {
        this.trabajaMadre = trabajaMadre;
    }

    @Column(name = "FUMA", nullable = true, length = 2)
    public String getFuma() {
        return fuma;
    }

    public void setFuma(String fuma) {
        this.fuma = fuma;
    }

    @Column(name = "PERIODICIDAD_FUNA", nullable = true, length = 2)
    public String getPeriodicidadFuna() {
        return periodicidadFuna;
    }

    public void setPeriodicidadFuna(String periodicidadFuna) {
        this.periodicidadFuna = periodicidadFuna;
    }

    @Column(name = "CANTIDAD_CIGARRILLOS", nullable = true, length = 2)
    public Integer getCantidadCigarrillos() {
        return cantidadCigarrillos;
    }

    public void setCantidadCigarrillos(Integer cantidadCigarrillos) {
        this.cantidadCigarrillos = cantidadCigarrillos;
    }

    @Column(name = "FUMA_DENTRO_CASA", nullable = true, length = 2)
    public String getFumaDentroCasa() {
        return fumaDentroCasa;
    }

    public void setFumaDentroCasa(String fumaDentroCasa) {
        this.fumaDentroCasa = fumaDentroCasa;
    }

    @Column(name = "TUBERCULOSIS_PULMONAR_ACTUAL", nullable = true, length = 2)
    public String getTuberculosisPulmonarActual() {
        return tuberculosisPulmonarActual;
    }

    public void setTuberculosisPulmonarActual(String tuberculosisPulmonarActual) {
        this.tuberculosisPulmonarActual = tuberculosisPulmonarActual;
    }

    @Column(name = "FECHA_DIAG_TUBPUL_ACTUAL", nullable = true, length = 8)
    public String getFechaDiagTubpulActual() {
        return fechaDiagTubpulActual;
    }

    public void setFechaDiagTubpulActual(String fechaDiagTubpulActual) {
        this.fechaDiagTubpulActual = fechaDiagTubpulActual;
    }

    @Column(name = "TRATAMIENTO_TUBPUL_ACTUAL", nullable = true, length = 2)
    public String getTratamientoTubpulActual() {
        return TratamientoTubpulActual;
    }

    public void setTratamientoTubpulActual(String tratamientoTubpulActual) {
        TratamientoTubpulActual = tratamientoTubpulActual;
    }

    @Column(name = "COMPLETO_TRATAMIENTO_TUBPUL_ACTUAL", nullable = true, length = 2)
    public String getCompletoTratamientoTubpulActual() {
        return CompletoTratamientoTubpulActual;
    }

    public void setCompletoTratamientoTubpulActual(String completoTratamientoTubpulActual) {
        CompletoTratamientoTubpulActual = completoTratamientoTubpulActual;
    }

    @Column(name = "TUBERCULOSIS_PULMONAR_PASADO", nullable = true, length = 2)
    public String getTuberculosisPulmonarPasado() {
        return TuberculosisPulmonarPasado;
    }

    public void setTuberculosisPulmonarPasado(String tuberculosisPulmonarPasado) {
        TuberculosisPulmonarPasado = tuberculosisPulmonarPasado;
    }

    @Column(name = "FECHA_DIAG_TUBPUL_PASADO_DES", nullable = true, length = 2)
    public String getFechaDiagTubpulPasadoDes() {
        return FechaDiagTubpulPasadoDes;
    }

    public void setFechaDiagTubpulPasadoDes(String fechaDiagTubpulPasadoDes) {
        FechaDiagTubpulPasadoDes = fechaDiagTubpulPasadoDes;
    }

    @Column(name = "FECHA_DIAG_TUBPUL_PASADO", nullable = true, length = 8)
    public String getFechaDiagTubpulPasado() {
        return fechaDiagTubpulPasado;
    }

    public void setFechaDiagTubpulPasado(String fechaDiagTubpulPasado) {
        this.fechaDiagTubpulPasado = fechaDiagTubpulPasado;
    }

    @Column(name = "TRATAMIENTO_TUBPUL_PASADO", nullable = true, length = 2)
    public String getTratamientoTubpulPasado() {
        return tratamientoTubpulPasado;
    }

    public void setTratamientoTubpulPasado(String tratamientoTubpulPasado) {
        this.tratamientoTubpulPasado = tratamientoTubpulPasado;
    }

    @Column(name = "COMPLETO_TRATAMIENTO_TUBPUL_PAS", nullable = true, length = 2)
    public String getCompletoTratamientoTubpulPas() {
        return completoTratamientoTubpulPas;
    }

    public void setCompletoTratamientoTubpulPas(String completoTratamientoTubpulPas) {
        this.completoTratamientoTubpulPas = completoTratamientoTubpulPas;
    }

    @Column(name = "ALERGIA_RESPIRATORIA", nullable = true, length = 2)
    public String getAlergiaRespiratoria() {
        return alergiaRespiratoria;
    }

    public void setAlergiaRespiratoria(String alergiaRespiratoria) {
        this.alergiaRespiratoria = alergiaRespiratoria;
    }

    @Column(name = "CARDIOPATIA", nullable = true, length = 2)
    public String getCardiopatia() {
        return cardiopatia;
    }

    public void setCardiopatia(String cardiopatia) {
        this.cardiopatia = cardiopatia;
    }

    @Column(name = "ENFERM_PULMONAR_OBST_CRONICA", nullable = true, length = 2)
    public String getEnfermPulmonarObstCronica() {
        return enfermPulmonarObstCronica;
    }

    public void setEnfermPulmonarObstCronica(String enfermPulmonarObstCronica) {
        this.enfermPulmonarObstCronica = enfermPulmonarObstCronica;
    }

    @Column(name = "DIABETES", nullable = true, length = 2)
    public String getDiabetes() {
        return diabetes;
    }

    public void setDiabetes(String diabetes) {
        this.diabetes = diabetes;
    }

    @Column(name = "PRESION_ALTA", nullable = true, length = 2)
    public String getPresionAlta() {
        return presionAlta;
    }

    public void setPresionAlta(String presionAlta) {
        this.presionAlta = presionAlta;
    }

    @Column(name = "TOS_SIN_FIEBRE_RESFRIADO", nullable = true, length = 2)
    public String getTosSinFiebreResfriado() {
        return tosSinFiebreResfriado;
    }

    public void setTosSinFiebreResfriado(String tosSinFiebreResfriado) {
        this.tosSinFiebreResfriado = tosSinFiebreResfriado;
    }

    @Column(name = "CANT_ENFERM_CUADROS_RESP", nullable = true, length = 2)
    public Integer getCantEnfermCuadrosResp() {
        return cantEnfermCuadrosResp;
    }

    public void setCantEnfermCuadrosResp(Integer cantEnfermCuadrosResp) {
        this.cantEnfermCuadrosResp = cantEnfermCuadrosResp;
    }

    @Column(name = "OTRAS_ENFERMEDADES", nullable = true, length = 2)
    public String getOtrasEnfermedades() {
        return otrasEnfermedades;
    }

    public void setOtrasEnfermedades(String otrasEnfermedades) {
        this.otrasEnfermedades = otrasEnfermedades;
    }

    @Column(name = "DESC_OTRAS_ENFERMEDADES", nullable = true, length = 255)
    public String getDescOtrasEnfermedades() {
        return descOtrasEnfermedades;
    }

    public void setDescOtrasEnfermedades(String descOtrasEnfermedades) {
        this.descOtrasEnfermedades = descOtrasEnfermedades;
    }

    @Column(name = "VACUNA_INFLUENZA", nullable = true, length = 2)
    public String getVacunaInfluenza() {
        return vacunaInfluenza;
    }

    public void setVacunaInfluenza(String vacunaInfluenza) {
        this.vacunaInfluenza = vacunaInfluenza;
    }

    @Column(name = "ANIO_VACUNA_INFLUENZA", nullable = true, length = 2)
    public Integer getAnioVacunaInfluenza() {
        return anioVacunaInfluenza;
    }

    public void setAnioVacunaInfluenza(Integer anioVacunaInfluenza) {
        this.anioVacunaInfluenza = anioVacunaInfluenza;
    }

    @Column(name = "rash6m", nullable = true, length = 2)
    public String getRash6m() {
        return rash6m;
    }

    public void setRash6m(String rash6m) {
        this.rash6m = rash6m;
    }

    @Column(name = "ojoRojo6m", nullable = true, length = 2)
    public String getOjoRojo6m() {
        return ojoRojo6m;
    }

    public void setOjoRojo6m(String ojoRojo6m) {
        this.ojoRojo6m = ojoRojo6m;
    }

    @Column(name = "est_actuales", nullable = true, length = 255)
    public String getEstudiosAct() {
        return estudiosAct;
    }

    public void setEstudiosAct(String estudiosAct) {
        this.estudiosAct = estudiosAct;
    }

    @Column(name = "VACUNA_INFLUENZA_MES", nullable = true)
    public Integer getVacunaInfluenzaMes() {
        return vacunaInfluenzaMes;
    }

    public void setVacunaInfluenzaMes(Integer vacunaInfluenzaMes) {
        this.vacunaInfluenzaMes = vacunaInfluenzaMes;
    }

    @Column(name = "VACUNA_INFLUENZA_CSSF", nullable = true, length = 2)
    public String getVacunaInfluenzaCSSF() {
        return vacunaInfluenzaCSSF;
    }

    public void setVacunaInfluenzaCSSF(String vacunaInfluenzaCSSF) {
        this.vacunaInfluenzaCSSF = vacunaInfluenzaCSSF;
    }

    @Column(name = "VACUNA_INFLUENZA_OTRO", nullable = true, length = 255)
    public String getVacunaInfluenzaOtro() {
        return vacunaInfluenzaOtro;
    }

    public void setVacunaInfluenzaOtro(String vacunaInfluenzaOtro) {
        this.vacunaInfluenzaOtro = vacunaInfluenzaOtro;
    }

    @Column(name = "NOMBRE_CDI", nullable = true, length = 255)
    public String getNombreCDI() {
        return nombreCDI;
    }

    public void setNombreCDI(String nombreCDI) {
        this.nombreCDI = nombreCDI;
    }

    @Column(name = "DIRECCION_CDI", nullable = true, length = 255)
    public String getDireccionCDI() {
        return direccionCDI;
    }

    public void setDireccionCDI(String direccionCDI) {
        this.direccionCDI = direccionCDI;
    }

	@Column(name = "OTRO_LUGAR_CUIDAN")
	public String getOtroLugarCuidan() {
		return otroLugarCuidan;
	}

	public void setOtroLugarCuidan(String otroLugarCuidan) {
		this.otroLugarCuidan = otroLugarCuidan;
	}

	@Column(name = "ENFERMEDAD_CRONICA", nullable = true, length = 2)
	public String getEnfermedadCronica() {
		return enfermedadCronica;
	}

	public void setEnfermedadCronica(String enfermedadCronica) {
		this.enfermedadCronica = enfermedadCronica;
	}

	@Column(name = "TENIDO_DENGUE", nullable = true, length = 2)
	public String getTenidoDengue() {
		return tenidoDengue;
	}

	public void setTenidoDengue(String tenidoDengue) {
		this.tenidoDengue = tenidoDengue;
	}

	@Column(name = "UNIDAD_SALUD_DENGUE", nullable = true, length = 2)
	public String getUnidadSaludDengue() {
		return unidadSaludDengue;
	}

	public void setUnidadSaludDengue(String unidadSaludDengue) {
		this.unidadSaludDengue = unidadSaludDengue;
	}

	@Column(name = "CENTRO_SALUD_DENGUE", nullable = true, length = 2)
	public String getCentroSaludDengue() {
		return centroSaludDengue;
	}

	public void setCentroSaludDengue(String centroSaludDengue) {
		this.centroSaludDengue = centroSaludDengue;
	}

	@Column(name = "OTRO_CENTRO_SALUD_DENGUE")
	public String getOtroCentroSaludDengue() {
		return otroCentroSaludDengue;
	}

	public void setOtroCentroSaludDengue(String otroCentroSaludDengue) {
		this.otroCentroSaludDengue = otroCentroSaludDengue;
	}

	@Column(name = "PUESTO_SALUD_DENGUE", nullable = true, length = 2)
	public String getPuestoSaludDengue() {
		return puestoSaludDengue;
	}

	public void setPuestoSaludDengue(String puestoSaludDengue) {
		this.puestoSaludDengue = puestoSaludDengue;
	}

	@Column(name = "OTRO_PUESTO_SALUD_DENGUE")
	public String getOtroPuestoSaludDengue() {
		return otroPuestoSaludDengue;
	}

	public void setOtroPuestoSaludDengue(String otroPuestoSaludDengue) {
		this.otroPuestoSaludDengue = otroPuestoSaludDengue;
	}

	@Column(name = "HOSPITAL_DENGUE", nullable = true, length = 3)
	public String getHospitalDengue() {
		return hospitalDengue;
	}

	public void setHospitalDengue(String hospitalDengue) {
		this.hospitalDengue = hospitalDengue;
	}

	@Column(name = "OTRO_HOSPITAL_DENGUE")
	public String getOtroHospitalDengue() {
		return otroHospitalDengue;
	}

	public void setOtroHospitalDengue(String otroHospitalDengue) {
		this.otroHospitalDengue = otroHospitalDengue;
	}

	@Column(name = "HOSPITALIZADO_DENGUE", nullable = true, length = 2)
	public String getHospitalizadoDengue() {
		return hospitalizadoDengue;
	}

	public void setHospitalizadoDengue(String hospitalizadoDengue) {
		this.hospitalizadoDengue = hospitalizadoDengue;
	}

	@Column(name = "AMBULATORIO_DENGUE", nullable = true, length = 2)
	public String getAmbulatorioDengue() {
		return ambulatorioDengue;
	}

	public void setAmbulatorioDengue(String ambulatorioDengue) {
		this.ambulatorioDengue = ambulatorioDengue;
	}

	@Column(name = "DX_MEDICO_DENGUE")
	public String getDiagMedicoDengue() {
		return diagMedicoDengue;
	}

	public void setDiagMedicoDengue(String diagMedicoDengue) {
		this.diagMedicoDengue = diagMedicoDengue;
	}

	@Column(name = "RASH_UA", nullable = true, length = 2)
	public String getRashUA() {
		return rashUA;
	}

	public void setRashUA(String rashUA) {
		this.rashUA = rashUA;
	}

	@Column(name = "CONSULTA_RASH_UA", nullable = true, length = 2)
	public String getConsultaRashUA() {
		return consultaRashUA;
	}

	public void setConsultaRashUA(String consultaRashUA) {
		this.consultaRashUA = consultaRashUA;
	}

    @JsonIgnore
    @Column(name = "ENCUESTA_VALIDA")
    public Boolean getEncuestaValida() {
        return encuestaValida;
    }

    public void setEncuestaValida(Boolean encuestaValida) {
        this.encuestaValida = encuestaValida;
    }
}
