package ni.org.ics.estudios.domain.covid19;

import ni.org.ics.estudios.domain.BaseMetaData;
import ni.org.ics.estudios.domain.Participante;
import ni.org.ics.estudios.domain.audit.Auditable;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.hibernate.annotations.ForeignKey;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by miguel on 10/9/2020.
 */
@Entity
@Table(name = "covid_cuestionario", catalog = "estudios_ics")
public class CuestionarioCovid19 extends BaseMetaData implements Auditable {

    /*1.¿Desde febrero de 2020 ha tenido usted o su niño/a alguno de los siguientes síntomas?*/
    private String codigo;
    private Participante participante;
    private String feb20Febricula;
    private String feb20Fiebre;
    private String feb20Escalofrio;
    private String feb20TemblorEscalofrio;
    private String feb20DolorMuscular;
    private String feb20DolorArticular;
    private String feb20SecresionNasal;
    private String feb20DolorGarganta;
    private String feb20Tos;
    private String feb20DificultadResp;
    private String feb20DolorPecho;
    private String feb20NauseasVomito;
    private String feb20DolorCabeza;
    private String feb20DolorAbdominal;
    private String feb20Diarrea;
    private String feb20DificultadDormir;
    private String feb20Debilidad;
    private String feb20PerdidaOlfatoGusto;
    private String feb20Mareo;
    private String feb20Sarpullido;
    private String feb20Desmayo;
    private String feb20QuedoCama;
    /*2.	[Si uno o mas de los síntomas en la sección] Que fecha exacta o aproximada empezaron estos síntomas*/
    private String sabeFIS;
    private Date fis;
    private String mesInicioSintoma;
    private String anioInicioSintoma;
    /*3.	¿Usted cree que en el año 2020 que usted o su niño/a pudo haber padecido de COVID-19 o su medico le informo que padeció de COVID-19?*/
    private String padecidoCovid19;
    /*4.	¿Conoce en donde usted o su niño/a estuvieron expuestos a COVID-19?*/
    private String conoceLugarExposicion;
    private String lugarExposicion;
    /*5.	¿Busco ayuda medica?*/
    private String buscoAyuda;
    /*6.	[Si P5==”Si”] Donde busco ayuda médica?*/
    private String dondeBuscoAyuda;
    private String nombreCentroSalud;
    private String nombreHospital;
    private String recibioSeguimiento;
    private String tipoSeguimiento;
    /*7.	[Si P5==”Si”] Cuanto tiempo después de que iniciaron los síntomas de COVID-19 busco ayuda medica? */
    private String tmpDespuesBuscoAyuda;
    /*8.	[Si P5==”Si”] Paso al menos una noche en el hospital por COVID-19?*/
    private String unaNocheHospital;
    /*Si P7==”Si”] A que hospital acudió?  */
    private String queHospital;
    /*10.	[Si P7 == “Si”] Cuantas noches estuvo en el hospital? */
    private String sabeCuantasNoches;
    private Integer cuantasNochesHosp;
    /*  11.	[Si P7== “Si”] Cual fue su fecha de admisión al hospital? */
    private String sabeFechaAdmision;
    private Date fechaAdmisionHosp;
    /*12.	 [Si P7 == “Si”] Cual fue su fecha de alta medica? */
    private String sabeFechaAlta;
    private Date fechaAltaHosp;
    /*13.	[S    i P7==”Si”] Mientras estuvo en el hospital, requirió oxigeno?*/
    private String utilizoOxigeno;
    /*14.	[Si P7==”Si”] Mientras usted o su niño/a estuvo en el hospital, en algún momento requirió de Unidad de Cuidados Intensivos?*/
    private String estuvoUCI;
    private String fueIntubado;
    /*  15.	[Si la P3 == “Si”] Se ha recuperado del COVID-19 y posee ahora un estado de salud normal o igual al que tenia antes de enfermedad?*/
    private String recuperadoCovid19;
    /*16.	[Si P15 == “No” o “No Sabe/No esta seguro”/no contesto] ¿Usted o su niño/a que síntomas presenta aun?*/
    private String febricula;
    private String cansancio;
    private String dolorCabeza;
    private String perdidaOlfato;
    private String perdidaGusto;
    private String tos;
    private String dificultadRespirar;
    private String dolorPecho;
    private String palpitaciones;
    private String dolorArticulaciones;
    private String paralisis;
    private String mareos;
    private String pensamientoNublado;
    private String problemasDormir;
    private String depresion;
    private String otrosSintomas;
    private String cualesSintomas;
    /*  17.	[Si P3 == “Si”] ¿ A usted o su niño/a cuanto tiempo le tomo en recuperarse de los síntomas secundarios a COVID-19 y recobrar su salud normal?*/
    private String sabeTiempoRecuperacion;
    private String tiempoRecuperacion;
    private String tiempoRecuperacionEn;
    /*18.	[Si P3==”Si”] En el momento más severo de su enfermedad, diría que su enfermedad o la de su niño/a fue?*/
    private String severidadEnfermedad;
    /*  19.	[Si P3==”Si”] Que medicamentos tomo para COVID-19*/
    private String tomoMedicamento;
    private String queMedicamento;
    private String otroMedicamento;
    /*20.	Algún medico u otro profesional de la salud le ha dicho alguna vez que usted o su niño/a padece alguna de estas condiciones?*/
    private String padeceEnfisema;
    private String padeceAsma;
    private String padeceDiabetes;
    private String padeceEnfCoronaria;
    private String padecePresionAlta;
    private String padeceEnfHigado;
    private String padeceEnfRenal;
    private String padeceInfartoDerrameCer;
    private String padeceCancer;
    private String padeceCondicionInmuno;
    private String padeceEnfAutoinmune;
    private String padeceDiscapacidadFis;
    private String padeceCondPsicPsiq;
    private String padeceOtraCondicion;
    private String queOtraCondicion;
    /*  21.	¿Ha fumado alguna vez en su vida? . para mayores 14 anios*/
    private String fumado;
    /*  22.	¿Ha fumado al menos 100 cigarrillos en su vida?*/
    private String fumadoCienCigarrillos;
    /*  23.	[Si P3==”Si”] ¿En los días previos a que se enfermara, usted fumaba cigarrillos todos los días o algunos días previo a que se enfermara? */
    private String fumadoPrevioEnfermedad;
    /*24.	¿Usted fumaba cigarrillos todos los días o algunos días ahora? */
    private String fumaActualmente;
    /*25	[Si P3==Si] Y si es mujer ¿Usted estaba embarazada al momento de ser diagnosticada o sospechosa de COVID19?*/
    private String embarazada;
    /*Si es Si, Recuerda las semanas de embarazo que tenia*/
    private String recuerdaSemanasEmb;
    private Integer semanasEmbarazo;
    /*Si es Si, como finalizo el embarzo*/
    private String finalEmbarazo;
    private String otroFinalEmbarazo;
    /*26.  [Si P3==Si] Si estaba embarazada recientemente al momento de ser diagnosticada con COVID19, ¿Usted estaba dando pecho materno?*/
    private String dabaPecho;
    /*27. ¿Usted estuvo empleado como trabajador de la salud desde el 1 de febrero de 2020?*/
    private String trabajadorSalud;
    private String periodoSintomas;//Almacena desde que perido se estan preguntando los sintomas de la pregunta 1

    private Date fechaRecibido; //poner fecha en que se recibe el registro en el server

    @Id
    @Column(name = "CODIGO", length = 36, nullable = false)
    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    @ManyToOne
    @JoinColumn(name = "CODIGO_PARTICIPANTE", nullable = false)
    @ForeignKey(name = "FK_CUESTIONARIO_COVID_PARTICIPANTE")
    public Participante getParticipante() {
        return participante;
    }

    public void setParticipante(Participante participante) {
        this.participante = participante;
    }

    @Column(name = "FEB20_FEBRICULA", length = 4, nullable = true)
    public String getFeb20Febricula() {
        return feb20Febricula;
    }

    public void setFeb20Febricula(String feb20Febricula) {
        this.feb20Febricula = feb20Febricula;
    }

    @Column(name = "FEB20_FIEBRE", length = 4, nullable = true)
    public String getFeb20Fiebre() {
        return feb20Fiebre;
    }

    public void setFeb20Fiebre(String feb20Fiebre) {
        this.feb20Fiebre = feb20Fiebre;
    }

    @Column(name = "FEB20_ESCALOFRIO", length = 4, nullable = true)
    public String getFeb20Escalofrio() {
        return feb20Escalofrio;
    }

    public void setFeb20Escalofrio(String feb20Escalofrio) {
        this.feb20Escalofrio = feb20Escalofrio;
    }

    @Column(name = "FEB20_TEMBLOR_ESCALOFRIO", length = 4, nullable = true)
    public String getFeb20TemblorEscalofrio() {
        return feb20TemblorEscalofrio;
    }

    public void setFeb20TemblorEscalofrio(String feb20TemblorEscalofrio) {
        this.feb20TemblorEscalofrio = feb20TemblorEscalofrio;
    }

    @Column(name = "FEB20_DOLOR_MUSCULAR", length = 4, nullable = true)
    public String getFeb20DolorMuscular() {
        return feb20DolorMuscular;
    }

    public void setFeb20DolorMuscular(String feb20DolorMuscular) {
        this.feb20DolorMuscular = feb20DolorMuscular;
    }

    @Column(name = "FEB20_DOLOR_ARTICULAR", length = 4, nullable = true)
    public String getFeb20DolorArticular() {
        return feb20DolorArticular;
    }

    public void setFeb20DolorArticular(String feb20DolorArticular) {
        this.feb20DolorArticular = feb20DolorArticular;
    }

    @Column(name = "FEB20_SECRECION_NASAL", length = 4, nullable = true)
    public String getFeb20SecresionNasal() {
        return feb20SecresionNasal;
    }

    public void setFeb20SecresionNasal(String feb20SecresionNasal) {
        this.feb20SecresionNasal = feb20SecresionNasal;
    }

    @Column(name = "FEB20_DOLOR_GARGANTA", length = 4, nullable = true)
    public String getFeb20DolorGarganta() {
        return feb20DolorGarganta;
    }

    public void setFeb20DolorGarganta(String feb20DolorGarganta) {
        this.feb20DolorGarganta = feb20DolorGarganta;
    }

    @Column(name = "FEB20_TOS", length = 4, nullable = true)
    public String getFeb20Tos() {
        return feb20Tos;
    }

    public void setFeb20Tos(String feb20Tos) {
        this.feb20Tos = feb20Tos;
    }

    @Column(name = "FEB20_DIFICULTAD_RESPIRATORIA", length = 4, nullable = true)
    public String getFeb20DificultadResp() {
        return feb20DificultadResp;
    }

    public void setFeb20DificultadResp(String feb20DificultadResp) {
        this.feb20DificultadResp = feb20DificultadResp;
    }

    @Column(name = "FEB20_DOLOR_PECHO", length = 4, nullable = true)
    public String getFeb20DolorPecho() {
        return feb20DolorPecho;
    }

    public void setFeb20DolorPecho(String feb20DolorPecho) {
        this.feb20DolorPecho = feb20DolorPecho;
    }

    @Column(name = "FEB20_NAUSEAS_VOMITO", length = 4, nullable = true)
    public String getFeb20NauseasVomito() {
        return feb20NauseasVomito;
    }

    public void setFeb20NauseasVomito(String feb20NauseasVomito) {
        this.feb20NauseasVomito = feb20NauseasVomito;
    }

    @Column(name = "FEB20_DOLOR_CABEZA", length = 4, nullable = true)
    public String getFeb20DolorCabeza() {
        return feb20DolorCabeza;
    }

    public void setFeb20DolorCabeza(String feb20DolorCabeza) {
        this.feb20DolorCabeza = feb20DolorCabeza;
    }

    @Column(name = "FEB20_DOLOR_ADDOMINAL", length = 4, nullable = true)
    public String getFeb20DolorAbdominal() {
        return feb20DolorAbdominal;
    }

    public void setFeb20DolorAbdominal(String feb20DolorAbdominal) {
        this.feb20DolorAbdominal = feb20DolorAbdominal;
    }

    @Column(name = "FEB20_DIARREA", length = 4, nullable = true)
    public String getFeb20Diarrea() {
        return feb20Diarrea;
    }

    public void setFeb20Diarrea(String feb20Diarrea) {
        this.feb20Diarrea = feb20Diarrea;
    }

    @Column(name = "FEB20_DIFICULTAD_DORMIR", length = 4, nullable = true)
    public String getFeb20DificultadDormir() {
        return feb20DificultadDormir;
    }

    public void setFeb20DificultadDormir(String feb20DificultadDormir) {
        this.feb20DificultadDormir = feb20DificultadDormir;
    }

    @Column(name = "FEB20_DEBILIDAD", length = 4, nullable = true)
    public String getFeb20Debilidad() {
        return feb20Debilidad;
    }

    public void setFeb20Debilidad(String feb20Debilidad) {
        this.feb20Debilidad = feb20Debilidad;
    }

    @Column(name = "FEB20_PERDIDA_OLFATO_GUSTO", length = 4, nullable = true)
    public String getFeb20PerdidaOlfatoGusto() {
        return feb20PerdidaOlfatoGusto;
    }

    public void setFeb20PerdidaOlfatoGusto(String feb20PerdidaOlfatoGusto) {
        this.feb20PerdidaOlfatoGusto = feb20PerdidaOlfatoGusto;
    }

    @Column(name = "FEB20_MAREO", length = 4, nullable = true)
    public String getFeb20Mareo() {
        return feb20Mareo;
    }

    public void setFeb20Mareo(String feb20Mareo) {
        this.feb20Mareo = feb20Mareo;
    }

    @Column(name = "FEB20_SARPULLIDO", length = 4, nullable = true)
    public String getFeb20Sarpullido() {
        return feb20Sarpullido;
    }

    public void setFeb20Sarpullido(String feb20Sarpullido) {
        this.feb20Sarpullido = feb20Sarpullido;
    }

    @Column(name = "FEB20_DESMAYOS", length = 4, nullable = true)
    public String getFeb20Desmayo() {
        return feb20Desmayo;
    }

    public void setFeb20Desmayo(String feb20Desmayo) {
        this.feb20Desmayo = feb20Desmayo;
    }

    @Column(name = "FEB20_QUEDO_CAMA", length = 4, nullable = true)
    public String getFeb20QuedoCama() {
        return feb20QuedoCama;
    }

    public void setFeb20QuedoCama(String feb20QuedoCama) {
        this.feb20QuedoCama = feb20QuedoCama;
    }

    @Column(name = "SABE_FIS", length = 4, nullable = true)
    public String getSabeFIS() {
        return sabeFIS;
    }

    public void setSabeFIS(String sabeFIS) {
        this.sabeFIS = sabeFIS;
    }

    @Column(name = "FIS", nullable = true)
    public Date getFis() {
        return fis;
    }

    public void setFis(Date fis) {
        this.fis = fis;
    }

    @Column(name = "MES_FIS", length = 4, nullable = true)
    public String getMesInicioSintoma() {
        return mesInicioSintoma;
    }

    public void setMesInicioSintoma(String mesInicioSintoma) {
        this.mesInicioSintoma = mesInicioSintoma;
    }

    @Column(name = "ANIO_FIS", length = 4, nullable = true)
    public String getAnioInicioSintoma() {
        return anioInicioSintoma;
    }

    public void setAnioInicioSintoma(String anioInicioSintoma) {
        this.anioInicioSintoma = anioInicioSintoma;
    }

    @Column(name = "PADECIDO_COVID19", length = 4, nullable = true)
    public String getPadecidoCovid19() {
        return padecidoCovid19;
    }

    public void setPadecidoCovid19(String padecidoCovid19) {
        this.padecidoCovid19 = padecidoCovid19;
    }

    @Column(name = "CONOCE_LUGAR_EXPOSICION", length = 4, nullable = true)
    public String getConoceLugarExposicion() {
        return conoceLugarExposicion;
    }

    public void setConoceLugarExposicion(String conoceLugarExposicion) {
        this.conoceLugarExposicion = conoceLugarExposicion;
    }

    @Column(name = "LUGAR_EXPOSICION", length = 255, nullable = true)
    public String getLugarExposicion() {
        return lugarExposicion;
    }

    public void setLugarExposicion(String lugarExposicion) {
        this.lugarExposicion = lugarExposicion;
    }

    @Column(name = "BUSCO_AYUDA", length = 4, nullable = true)
    public String getBuscoAyuda() {
        return buscoAyuda;
    }

    public void setBuscoAyuda(String buscoAyuda) {
        this.buscoAyuda = buscoAyuda;
    }

    @Column(name = "DONDE_BUSCO_AYUDA", length = 4, nullable = true)
    public String getDondeBuscoAyuda() {
        return dondeBuscoAyuda;
    }

    public void setDondeBuscoAyuda(String dondeBuscoAyuda) {
        this.dondeBuscoAyuda = dondeBuscoAyuda;
    }

    @Column(name = "NOMBRE_CS", length = 255, nullable = true)
    public String getNombreCentroSalud() {
        return nombreCentroSalud;
    }

    public void setNombreCentroSalud(String nombreCentroSalud) {
        this.nombreCentroSalud = nombreCentroSalud;
    }

    @Column(name = "NOMBRE_HOSPITAL", length = 255, nullable = true)
    public String getNombreHospital() {
        return nombreHospital;
    }

    public void setNombreHospital(String nombreHospital) {
        this.nombreHospital = nombreHospital;
    }

    @Column(name = "RECIBIO_SEGUIMIENTO", length = 4, nullable = true)
    public String getRecibioSeguimiento() {
        return recibioSeguimiento;
    }

    public void setRecibioSeguimiento(String recibioSeguimiento) {
        this.recibioSeguimiento = recibioSeguimiento;
    }

    @Column(name = "TIPO_SEGUIMIENTO", length = 4, nullable = true)
    public String getTipoSeguimiento() {
        return tipoSeguimiento;
    }

    public void setTipoSeguimiento(String tipoSeguimiento) {
        this.tipoSeguimiento = tipoSeguimiento;
    }

    @Column(name = "CUANTO_TMP_DESP_BUSCO_AYUDA", length = 4, nullable = true)
    public String getTmpDespuesBuscoAyuda() {
        return tmpDespuesBuscoAyuda;
    }

    public void setTmpDespuesBuscoAyuda(String tmpDespuesBuscoAyuda) {
        this.tmpDespuesBuscoAyuda = tmpDespuesBuscoAyuda;
    }

    @Column(name = "NOCHE_HOSPITALIZADO", length = 4, nullable = true)
    public String getUnaNocheHospital() {
        return unaNocheHospital;
    }

    public void setUnaNocheHospital(String unaNocheHospital) {
        this.unaNocheHospital = unaNocheHospital;
    }

    @Column(name = "CUAL_HOSPITAL", length = 255, nullable = true)
    public String getQueHospital() {
        return queHospital;
    }

    public void setQueHospital(String queHospital) {
        this.queHospital = queHospital;
    }

    @Column(name = "SABE_CUANTAS_NOCHES_HOSP", length = 4, nullable = true)
    public String getSabeCuantasNoches() {
        return sabeCuantasNoches;
    }

    public void setSabeCuantasNoches(String sabeCuantasNoches) {
        this.sabeCuantasNoches = sabeCuantasNoches;
    }

    @Column(name = "CUANTAS_NOCHES_HOSP", length = 3, nullable = true)
    public Integer getCuantasNochesHosp() {
        return cuantasNochesHosp;
    }

    public void setCuantasNochesHosp(Integer cuantasNochesHosp) {
        this.cuantasNochesHosp = cuantasNochesHosp;
    }

    @Column(name = "SABE_FECHA_ADMISION_HOSP", length = 4, nullable = true)
    public String getSabeFechaAdmision() {
        return sabeFechaAdmision;
    }

    public void setSabeFechaAdmision(String sabeFechaAdmision) {
        this.sabeFechaAdmision = sabeFechaAdmision;
    }

    @Column(name = "FECHA_ADMISION_HOSP", nullable = true)
    public Date getFechaAdmisionHosp() {
        return fechaAdmisionHosp;
    }

    public void setFechaAdmisionHosp(Date fechaAdmisionHosp) {
        this.fechaAdmisionHosp = fechaAdmisionHosp;
    }

    @Column(name = "SABE_FECHA_ALTA_HOSP", length = 4, nullable = true)
    public String getSabeFechaAlta() {
        return sabeFechaAlta;
    }

    public void setSabeFechaAlta(String sabeFechaAlta) {
        this.sabeFechaAlta = sabeFechaAlta;
    }

    @Column(name = "FECHA_ALTA_HOSP", nullable = true)
    public Date getFechaAltaHosp() {
        return fechaAltaHosp;
    }

    public void setFechaAltaHosp(Date fechaAltaHosp) {
        this.fechaAltaHosp = fechaAltaHosp;
    }

    @Column(name = "UTILIZO_OXIGENO", length = 4, nullable = true)
    public String getUtilizoOxigeno() {
        return utilizoOxigeno;
    }

    public void setUtilizoOxigeno(String utilizoOxigeno) {
        this.utilizoOxigeno = utilizoOxigeno;
    }

    @Column(name = "ESTUVO_UCI", length = 4, nullable = true)
    public String getEstuvoUCI() {
        return estuvoUCI;
    }

    public void setEstuvoUCI(String estuvoUCI) {
        this.estuvoUCI = estuvoUCI;
    }

    @Column(name = "FUE_INTUBADO", length = 4, nullable = true)
    public String getFueIntubado() {
        return fueIntubado;
    }

    public void setFueIntubado(String fueIntubado) {
        this.fueIntubado = fueIntubado;
    }

    @Column(name = "ESTA_RECUPERADO", length = 4, nullable = true)
    public String getRecuperadoCovid19() {
        return recuperadoCovid19;
    }

    public void setRecuperadoCovid19(String recuperadoCovid19) {
        this.recuperadoCovid19 = recuperadoCovid19;
    }

    @Column(name = "TIENE_FEBRICULA", length = 4, nullable = true)
    public String getFebricula() {
        return febricula;
    }

    public void setFebricula(String febricula) {
        this.febricula = febricula;
    }

    @Column(name = "TIENE_CANSANCIO", length = 4, nullable = true)
    public String getCansancio() {
        return cansancio;
    }

    public void setCansancio(String cansancio) {
        this.cansancio = cansancio;
    }

    @Column(name = "TIENE_DOLOR_CABEZA", length = 4, nullable = true)
    public String getDolorCabeza() {
        return dolorCabeza;
    }

    public void setDolorCabeza(String dolorCabeza) {
        this.dolorCabeza = dolorCabeza;
    }

    @Column(name = "TIENE_PERDIDAD_OLFATO", length = 4, nullable = true)
    public String getPerdidaOlfato() {
        return perdidaOlfato;
    }

    public void setPerdidaOlfato(String perdidaOlfato) {
        this.perdidaOlfato = perdidaOlfato;
    }

    @Column(name = "TIENE_PERDIDA_GUSTO", length = 4, nullable = true)
    public String getPerdidaGusto() {
        return perdidaGusto;
    }

    public void setPerdidaGusto(String perdidaGusto) {
        this.perdidaGusto = perdidaGusto;
    }

    @Column(name = "TIENE_TOS", length = 4, nullable = true)
    public String getTos() {
        return tos;
    }

    public void setTos(String tos) {
        this.tos = tos;
    }

    @Column(name = "TIENE_DIFICULTAD_RESPIRAR", length = 4, nullable = true)
    public String getDificultadRespirar() {
        return dificultadRespirar;
    }

    public void setDificultadRespirar(String dificultadRespirar) {
        this.dificultadRespirar = dificultadRespirar;
    }

    @Column(name = "TIENE_DOLOR_PECHO", length = 4, nullable = true)
    public String getDolorPecho() {
        return dolorPecho;
    }

    public void setDolorPecho(String dolorPecho) {
        this.dolorPecho = dolorPecho;
    }

    @Column(name = "TIENE_PALPITACIONES", length = 4, nullable = true)
    public String getPalpitaciones() {
        return palpitaciones;
    }

    public void setPalpitaciones(String palpitaciones) {
        this.palpitaciones = palpitaciones;
    }

    @Column(name = "TIENE_DOLOR_ARTICULACIONES", length = 4, nullable = true)
    public String getDolorArticulaciones() {
        return dolorArticulaciones;
    }

    public void setDolorArticulaciones(String dolorArticulaciones) {
        this.dolorArticulaciones = dolorArticulaciones;
    }

    @Column(name = "TIENE_PARALISIS", length = 4, nullable = true)
    public String getParalisis() {
        return paralisis;
    }

    public void setParalisis(String paralisis) {
        this.paralisis = paralisis;
    }

    @Column(name = "TIENE_MAREOS", length = 4, nullable = true)
    public String getMareos() {
        return mareos;
    }

    public void setMareos(String mareos) {
        this.mareos = mareos;
    }

    @Column(name = "TIENE_PENSAMIENTO_NUBLADO", length = 4, nullable = true)
    public String getPensamientoNublado() {
        return pensamientoNublado;
    }

    public void setPensamientoNublado(String pensamientoNublado) {
        this.pensamientoNublado = pensamientoNublado;
    }

    @Column(name = "TIENE_PROBLEMAS_DORMIR", length = 4, nullable = true)
    public String getProblemasDormir() {
        return problemasDormir;
    }

    public void setProblemasDormir(String problemasDormir) {
        this.problemasDormir = problemasDormir;
    }

    @Column(name = "TIENE_DEPRESION", length = 4, nullable = true)
    public String getDepresion() {
        return depresion;
    }

    public void setDepresion(String depresion) {
        this.depresion = depresion;
    }

    @Column(name = "TIENE_OTROS_SINTOMAS", length = 4, nullable = true)
    public String getOtrosSintomas() {
        return otrosSintomas;
    }

    public void setOtrosSintomas(String otrosSintomas) {
        this.otrosSintomas = otrosSintomas;
    }

    @Column(name = "CUALES_OTROS_SINTOMAS", length = 255, nullable = true)
    public String getCualesSintomas() {
        return cualesSintomas;
    }

    public void setCualesSintomas(String cualesSintomas) {
        this.cualesSintomas = cualesSintomas;
    }

    @Column(name = "SABE_TIEMPO_RECUPERACION", length = 4, nullable = true)
    public String getSabeTiempoRecuperacion() {
        return sabeTiempoRecuperacion;
    }

    public void setSabeTiempoRecuperacion(String sabeTiempoRecuperacion) {
        this.sabeTiempoRecuperacion = sabeTiempoRecuperacion;
    }

    @Column(name = "TIEMPO_RECUPERACION", length = 2, nullable = true)
    public String getTiempoRecuperacion() {
        return tiempoRecuperacion;
    }

    public void setTiempoRecuperacion(String tiempoRecuperacion) {
        this.tiempoRecuperacion = tiempoRecuperacion;
    }

    @Column(name = "TIEMPO_RECUPERACION_EN", length = 2, nullable = true)
    public String getTiempoRecuperacionEn() {
        return tiempoRecuperacionEn;
    }

    public void setTiempoRecuperacionEn(String tiempoRecuperacionEn) {
        this.tiempoRecuperacionEn = tiempoRecuperacionEn;
    }

    @Column(name = "SEVERIDAD_ENFERMEDAD", length = 4, nullable = true)
    public String getSeveridadEnfermedad() {
        return severidadEnfermedad;
    }

    public void setSeveridadEnfermedad(String severidadEnfermedad) {
        this.severidadEnfermedad = severidadEnfermedad;
    }

    @Column(name = "TOMO_MEDICAMENTO", length = 4, nullable = true)
    public String getTomoMedicamento() {
        return tomoMedicamento;
    }

    public void setTomoMedicamento(String tomoMedicamento) {
        this.tomoMedicamento = tomoMedicamento;
    }

    @Column(name = "QUE_MEDICAMENTO", length = 64, nullable = true)
    public String getQueMedicamento() {
        return queMedicamento;
    }

    public void setQueMedicamento(String queMedicamento) {
        this.queMedicamento = queMedicamento;
    }

    @Column(name = "OTRO_MEDICAMENTO", length = 255, nullable = true)
    public String getOtroMedicamento() {
        return otroMedicamento;
    }

    public void setOtroMedicamento(String otroMedicamento) {
        this.otroMedicamento = otroMedicamento;
    }

    @Column(name = "PADECE_ENFISEMA", length = 4, nullable = true)
    public String getPadeceEnfisema() {
        return padeceEnfisema;
    }

    public void setPadeceEnfisema(String padeceEnfisema) {
        this.padeceEnfisema = padeceEnfisema;
    }

    @Column(name = "PADECE_ASMA", length = 4, nullable = true)
    public String getPadeceAsma() {
        return padeceAsma;
    }

    public void setPadeceAsma(String padeceAsma) {
        this.padeceAsma = padeceAsma;
    }

    @Column(name = "PADECE_DIABETES", length = 4, nullable = true)
    public String getPadeceDiabetes() {
        return padeceDiabetes;
    }

    public void setPadeceDiabetes(String padeceDiabetes) {
        this.padeceDiabetes = padeceDiabetes;
    }

    @Column(name = "PADECE_ENF_CORONARIA", length = 4, nullable = true)
    public String getPadeceEnfCoronaria() {
        return padeceEnfCoronaria;
    }

    public void setPadeceEnfCoronaria(String padeceEnfCoronaria) {
        this.padeceEnfCoronaria = padeceEnfCoronaria;
    }

    @Column(name = "PADECE_PRESION_ALTA", length = 4, nullable = true)
    public String getPadecePresionAlta() {
        return padecePresionAlta;
    }

    public void setPadecePresionAlta(String padecePresionAlta) {
        this.padecePresionAlta = padecePresionAlta;
    }

    @Column(name = "PADECE_ENF_HIGADO", length = 4, nullable = true)
    public String getPadeceEnfHigado() {
        return padeceEnfHigado;
    }

    public void setPadeceEnfHigado(String padeceEnfHigado) {
        this.padeceEnfHigado = padeceEnfHigado;
    }

    @Column(name = "PADECE_ENF_RENAL", length = 4, nullable = true)
    public String getPadeceEnfRenal() {
        return padeceEnfRenal;
    }

    public void setPadeceEnfRenal(String padeceEnfRenal) {
        this.padeceEnfRenal = padeceEnfRenal;
    }

    @Column(name = "PADECE_INFARTO_DERRAME_CEREB", length = 4, nullable = true)
    public String getPadeceInfartoDerrameCer() {
        return padeceInfartoDerrameCer;
    }

    public void setPadeceInfartoDerrameCer(String padeceInfartoDerrameCer) {
        this.padeceInfartoDerrameCer = padeceInfartoDerrameCer;
    }

    @Column(name = "PADECE_CANCER", length = 4, nullable = true)
    public String getPadeceCancer() {
        return padeceCancer;
    }

    public void setPadeceCancer(String padeceCancer) {
        this.padeceCancer = padeceCancer;
    }

    @Column(name = "PADECE_CONDICION_INMUNO", length = 4, nullable = true)
    public String getPadeceCondicionInmuno() {
        return padeceCondicionInmuno;
    }

    public void setPadeceCondicionInmuno(String padeceCondicionInmuno) {
        this.padeceCondicionInmuno = padeceCondicionInmuno;
    }

    @Column(name = "PADECE_ENF_AUTOINMUNE", length = 4, nullable = true)
    public String getPadeceEnfAutoinmune() {
        return padeceEnfAutoinmune;
    }

    public void setPadeceEnfAutoinmune(String padeceEnfAutoinmune) {
        this.padeceEnfAutoinmune = padeceEnfAutoinmune;
    }

    @Column(name = "PADECE_DISCAPACIDAD", length = 4, nullable = true)
    public String getPadeceDiscapacidadFis() {
        return padeceDiscapacidadFis;
    }

    public void setPadeceDiscapacidadFis(String padeceDiscapacidadFis) {
        this.padeceDiscapacidadFis = padeceDiscapacidadFis;
    }

    @Column(name = "PADECE_CONDICION_PSIC_PSIQ", length = 4, nullable = true)
    public String getPadeceCondPsicPsiq() {
        return padeceCondPsicPsiq;
    }

    public void setPadeceCondPsicPsiq(String padeceCondPsicPsiq) {
        this.padeceCondPsicPsiq = padeceCondPsicPsiq;
    }

    @Column(name = "PADECE_OTRA_CONDICION", length = 4, nullable = true)
    public String getPadeceOtraCondicion() {
        return padeceOtraCondicion;
    }

    public void setPadeceOtraCondicion(String padeceOtraCondicion) {
        this.padeceOtraCondicion = padeceOtraCondicion;
    }

    @Column(name = "QUE_OTRA_CONDICION", length = 255, nullable = true)
    public String getQueOtraCondicion() {
        return queOtraCondicion;
    }

    public void setQueOtraCondicion(String queOtraCondicion) {
        this.queOtraCondicion = queOtraCondicion;
    }

    @Column(name = "FUMADO", length = 4, nullable = true)
    public String getFumado() {
        return fumado;
    }

    public void setFumado(String fumado) {
        this.fumado = fumado;
    }

    @Column(name = "FUMADO_CIEN_CIGARRILLOS", length = 4, nullable = true)
    public String getFumadoCienCigarrillos() {
        return fumadoCienCigarrillos;
    }

    public void setFumadoCienCigarrillos(String fumadoCienCigarrillos) {
        this.fumadoCienCigarrillos = fumadoCienCigarrillos;
    }

    @Column(name = "FUMADO_PREVIO_ENFERMEDAD", length = 4, nullable = true)
    public String getFumadoPrevioEnfermedad() {
        return fumadoPrevioEnfermedad;
    }

    public void setFumadoPrevioEnfermedad(String fumadoPrevioEnfermedad) {
        this.fumadoPrevioEnfermedad = fumadoPrevioEnfermedad;
    }

    @Column(name = "FUMA_ACTUALMENTE", length = 4, nullable = true)
    public String getFumaActualmente() {
        return fumaActualmente;
    }

    public void setFumaActualmente(String fumaActualmente) {
        this.fumaActualmente = fumaActualmente;
    }

    @Column(name = "EMBARAZADA", length = 4, nullable = true)
    public String getEmbarazada() {
        return embarazada;
    }

    public void setEmbarazada(String embarazada) {
        this.embarazada = embarazada;
    }

    @Column(name = "RECUERDA_SEMANAS_EMB", length = 4, nullable = true)
    public String getRecuerdaSemanasEmb() {
        return recuerdaSemanasEmb;
    }

    public void setRecuerdaSemanasEmb(String recuerdaSemanasEmb) {
        this.recuerdaSemanasEmb = recuerdaSemanasEmb;
    }

    @Column(name = "SEMANAS_EMBARAZO", nullable = true)
    public Integer getSemanasEmbarazo() {
        return semanasEmbarazo;
    }

    public void setSemanasEmbarazo(Integer semanasEmbarazo) {
        this.semanasEmbarazo = semanasEmbarazo;
    }

    @Column(name = "FINAL_EMBARAZO", length = 4, nullable = true)
    public String getFinalEmbarazo() {
        return finalEmbarazo;
    }

    public void setFinalEmbarazo(String finalEmbarazo) {
        this.finalEmbarazo = finalEmbarazo;
    }

    @Column(name = "OTRO_FINAL_EMBARAZO", nullable = true)
    public String getOtroFinalEmbarazo() {
        return otroFinalEmbarazo;
    }

    public void setOtroFinalEmbarazo(String otroFinalEmbarazo) {
        this.otroFinalEmbarazo = otroFinalEmbarazo;
    }

    @Column(name = "DABA_PECHO", length = 4, nullable = true)
    public String getDabaPecho() {
        return dabaPecho;
    }

    public void setDabaPecho(String dabaPecho) {
        this.dabaPecho = dabaPecho;
    }

    @Column(name = "TRABAJADOR_SALUD", length = 4, nullable = true)
    public String getTrabajadorSalud() {
        return trabajadorSalud;
    }

    public void setTrabajadorSalud(String trabajadorSalud) {
        this.trabajadorSalud = trabajadorSalud;
    }

    @Column(name = "PERIODO_SINTOMAS", length = 16, nullable = true)
    public String getPeriodoSintomas() {
        return periodoSintomas;
    }

    public void setPeriodoSintomas(String periodoSintomas) {
        this.periodoSintomas = periodoSintomas;
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CuestionarioCovid19 that = (CuestionarioCovid19) o;

        if (!codigo.equals(that.codigo)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return codigo.hashCode();
    }

    @Override
    public String toString() {
        return "CuestionarioCovid19{" +
                "codigo='" + codigo + '\'' +
                ", participante=" + participante +
                '}';
    }

    @Override
    public boolean isFieldAuditable(String fieldname) {
        return true;
    }
}
