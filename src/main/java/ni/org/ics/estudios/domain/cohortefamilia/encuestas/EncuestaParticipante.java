package ni.org.ics.estudios.domain.cohortefamilia.encuestas;

import ni.org.ics.estudios.domain.BaseMetaData;
import ni.org.ics.estudios.domain.audit.Auditable;
import ni.org.ics.estudios.domain.cohortefamilia.ParticipanteCohorteFamilia;
import org.hibernate.annotations.ForeignKey;

import javax.persistence.*;

/**
 * Created by Miguel Salinas on 5/3/2017.
 * V1.0
 */

@Entity
@Table(name = "chf_encuestas_participante", catalog = "estudios_ics")
public class EncuestaParticipante extends BaseMetaData implements Auditable {

    private ParticipanteCohorteFamilia participante;
    //Adulto   (Mayores o igual a 18 años; Mujeres menores de 18 años con niños o embarazadas)
    //private String emancipado;
    //private String motivoEmacipacion;
    //private String otroMotivoEmancipacion;
    private String estaEmbarazada;
    private Integer semanasEmbarazo;
    private String esAlfabeto;
    private String nivelEducacion;
    private String trabaja;
    private String tipoTrabajo;
    private String ocupacionActual;
    //Niño
    private String vaNinoEscuela;
    private String gradoCursa;
    private String turno;
    private String centroEstudio;
    private String nombreCentroEstudio;
    private String dondeCuidanNino;
    private String ninoTrabaja;
    private String ocupacionActualNino;
    private Integer cantNinosLugarCuidan; //Cuántos niños aproximadamente hay en el lugar que cuidan al niño
    //Datos generales de los padres y factores de hacinamiento
    private String conQuienViveNino;
    private String descOtroViveNino;
    private String padreEnEstudio;
    private String codigoPadreEstudio;
    private String padreAlfabeto;
    private String nivelEducacionPadre;
    private String trabajaPadre;
    private String tipoTrabajoPadre;
    private String madreEnEstudio;
    private String codigoMadreEstudio;
    private String madreAlfabeto;
    private String nivelEducacionMadre;
    private String trabajaMadre;
    private String tipoTrabajoMadre;
    // Para todos los participantes
    private String fuma;
    private String periodicidadFuma;
    private Integer cantidadCigarrillos;
    private String fumaDentroCasa;
    //Antecedentes de Salud
    private String tuberculosisPulmonarActual;
    private String fechaDiagnosticoTubPulActual;
    private String tomaTratamientoTubPulActual;
    private String completoTratamientoTubPulActual;
    private String tuberculosisPulmonarPasado;
    private String fechaDiagnosticoTubPulPasado;
    private String fechaDiagnosticoTubPulPasadoDes;//fecha de diagnóstico desconocida
    private String tomaTratamientoTubPulPasado;
    private String completoTratamientoTubPulPasado;
    private String alergiaRespiratoria;
    private String cardiopatia;
    private String enfermedadPulmonarOC; // enfermedad pulmonar obstructiva crónica
    private String diabetes;
    private String presionAlta;
    private String asma;
    private String silbidoRespirarPechoApretado;
    private String tosSinFiebreResfriado;
    private String usaInhaladoresSpray;
    private String crisisAsma;
    private Integer cantidadCrisisAsma;
    private Integer vecesEnfermoEnfermedadesRes; //veces enfermo en el último año por cuadros o enfermedades respiratorias
    private String otrasEnfermedades;
    private String descOtrasEnfermedades;
    private String vacunaInfluenza;
    private Integer anioVacunaInfluenza;
    private String recurso1;
    private String otrorecurso1;

    @Id
    @ManyToOne
    @JoinColumn(name = "CODIGO_PARTICIPANTE", referencedColumnName = "CODIGO_PARTICIPANTE", nullable = false)
    @ForeignKey(name = "FK_PARTICIPANTECHF_ENCUESTAPARTICIPANTE")
    public ParticipanteCohorteFamilia getParticipante() {
        return participante;
    }

    public void setParticipante(ParticipanteCohorteFamilia participante) {
        this.participante = participante;
    }

    @Column(name = "EMBARAZADA", length = 1)
    public String getEstaEmbarazada() {
        return estaEmbarazada;
    }

    public void setEstaEmbarazada(String estaEmbarazada) {
        this.estaEmbarazada = estaEmbarazada;
    }

    @Column(name = "SEMANAS_EMBARAZO", length = 2)
    public Integer getSemanasEmbarazo() {
        return semanasEmbarazo;
    }

    public void setSemanasEmbarazo(Integer semanasEmbarazo) {
        this.semanasEmbarazo = semanasEmbarazo;
    }

    @Column(name = "ALFABETO", length = 1)
    public String getEsAlfabeto() {
        return esAlfabeto;
    }

    public void setEsAlfabeto(String esAlfabeto) {
        this.esAlfabeto = esAlfabeto;
    }

    @Column(name = "NIVEL_EDUCACION", length = 50)
    public String getNivelEducacion() {
        return nivelEducacion;
    }

    public void setNivelEducacion(String nivelEducacion) {
        this.nivelEducacion = nivelEducacion;
    }

    @Column(name = "TRABAJA", length = 1)
    public String getTrabaja() {
        return trabaja;
    }

    public void setTrabaja(String trabaja) {
        this.trabaja = trabaja;
    }

    @Column(name = "TIPO_TRABAJO", length = 50)
    public String getTipoTrabajo() {
        return tipoTrabajo;
    }

    public void setTipoTrabajo(String tipoTrabajo) {
        this.tipoTrabajo = tipoTrabajo;
    }

    @Column(name = "OCUPACION_ACTUAL")
    public String getOcupacionActual() {
        return ocupacionActual;
    }

    public void setOcupacionActual(String ocupacionActual) {
        this.ocupacionActual = ocupacionActual;
    }

    @Column(name = "NINO_ASISTE_ESCUELA", length = 1)
    public String getVaNinoEscuela() {
        return vaNinoEscuela;
    }

    public void setVaNinoEscuela(String vaNinoEscuela) {
        this.vaNinoEscuela = vaNinoEscuela;
    }

    @Column(name = "GRADO_ESTUDIA_NINO", length = 50)
    public String getGradoCursa() {
        return gradoCursa;
    }

    public void setGradoCursa(String gradoCursa) {
        this.gradoCursa = gradoCursa;
    }

    @Column(name = "TURNO_ESTUDIA_NINO", length = 50)
    public String getTurno() {
        return turno;
    }

    public void setTurno(String turno) {
        this.turno = turno;
    }

    @Column(name = "CENTRO_ESTUDIO", length = 5)
    public String getCentroEstudio() {
        return centroEstudio;
    }

    public void setCentroEstudio(String centroEstudio) {
        this.centroEstudio = centroEstudio;
    }

    @Column(name = "NOMBRE_CENTRO_ESTUDIO")
    public String getNombreCentroEstudio() {
        return nombreCentroEstudio;
    }

    public void setNombreCentroEstudio(String nombreCentroEstudio) {
        this.nombreCentroEstudio = nombreCentroEstudio;
    }

    @Column(name = "DONDE_CUIDAN_NINO")
    public String getDondeCuidanNino() {
        return dondeCuidanNino;
    }

    public void setDondeCuidanNino(String dondeCuidanNino) {
        this.dondeCuidanNino = dondeCuidanNino;
    }

    @Column(name = "NINO_TRABAJA", length = 1)
    public String getNinoTrabaja() {
        return ninoTrabaja;
    }

    public void setNinoTrabaja(String ninoTrabaja) {
        this.ninoTrabaja = ninoTrabaja;
    }

    @Column(name = "OCUPACION_ACTUAL_NINO")
    public String getOcupacionActualNino() {
        return ocupacionActualNino;
    }

    public void setOcupacionActualNino(String ocupacionActualNino) {
        this.ocupacionActualNino = ocupacionActualNino;
    }

    @Column(name = "CANT_NINOS_LUGAR_CUIDAN_NINO", length = 4)
    public Integer getCantNinosLugarCuidan() {
        return cantNinosLugarCuidan;
    }

    public void setCantNinosLugarCuidan(Integer cantNinosLugarCuidan) {
        this.cantNinosLugarCuidan = cantNinosLugarCuidan;
    }

    @Column(name = "CON_QUIEN_VIVE_NINO", length = 50)
    public String getConQuienViveNino() {
        return conQuienViveNino;
    }

    public void setConQuienViveNino(String conQuienViveNino) {
        this.conQuienViveNino = conQuienViveNino;
    }

    @Column(name = "DESC_OTRO_VIVE_NINO")
    public String getDescOtroViveNino() {
        return descOtroViveNino;
    }

    public void setDescOtroViveNino(String descOtroViveNino) {
        this.descOtroViveNino = descOtroViveNino;
    }

    @Column(name = "PADRE_ESTUDIO", length = 1)
    public String getPadreEnEstudio() {
        return padreEnEstudio;
    }

    public void setPadreEnEstudio(String padreEnEstudio) {
        this.padreEnEstudio = padreEnEstudio;
    }

    @Column(name = "CODIGO_PADRE_ESTUDIO", length = 50, nullable = true)
    public String getCodigoPadreEstudio() {
        return codigoPadreEstudio;
    }

    public void setCodigoPadreEstudio(String codigoPadreEstudio) {
        this.codigoPadreEstudio = codigoPadreEstudio;
    }

    @Column(name = "PADRE_ALFABETO", length = 1)
    public String getPadreAlfabeto() {
        return padreAlfabeto;
    }

    public void setPadreAlfabeto(String padreAlfabeto) {
        this.padreAlfabeto = padreAlfabeto;
    }

    @Column(name = "NIVEL_EDUCACION_PADRE", length = 50)
    public String getNivelEducacionPadre() {
        return nivelEducacionPadre;
    }

    public void setNivelEducacionPadre(String nivelEducacionPadre) {
        this.nivelEducacionPadre = nivelEducacionPadre;
    }

    @Column(name = "TRABAJA_PADRE", length = 1)
    public String getTrabajaPadre() {
        return trabajaPadre;
    }

    public void setTrabajaPadre(String trabajaPadre) {
        this.trabajaPadre = trabajaPadre;
    }

    @Column(name = "TIPO_TRABAJO_PADRE", length = 50)
    public String getTipoTrabajoPadre() {
        return tipoTrabajoPadre;
    }

    public void setTipoTrabajoPadre(String tipoTrabajoPadre) {
        this.tipoTrabajoPadre = tipoTrabajoPadre;
    }

    @Column(name = "MADRE_ESTUDIO", length = 1)
    public String getMadreEnEstudio() {
        return madreEnEstudio;
    }

    public void setMadreEnEstudio(String madreEnEstudio) {
        this.madreEnEstudio = madreEnEstudio;
    }

    @Column(name = "CODIGO_MADRE_ESTUDIO", length = 50, nullable = true)
    public String getCodigoMadreEstudio() {
        return codigoMadreEstudio;
    }

    public void setCodigoMadreEstudio(String codigoMadreEstudio) {
        this.codigoMadreEstudio = codigoMadreEstudio;
    }

    @Column(name = "MADRE_ALFABETA", length = 1)
    public String getMadreAlfabeto() {
        return madreAlfabeto;
    }

    public void setMadreAlfabeto(String madreAlfabeto) {
        this.madreAlfabeto = madreAlfabeto;
    }

    @Column(name = "NIVEL_EDUCACION_MADRE", length = 50)
    public String getNivelEducacionMadre() {
        return nivelEducacionMadre;
    }

    public void setNivelEducacionMadre(String nivelEducacionMadre) {
        this.nivelEducacionMadre = nivelEducacionMadre;
    }

    @Column(name = "TRABAJA_MADRE", length = 1)
    public String getTrabajaMadre() {
        return trabajaMadre;
    }

    public void setTrabajaMadre(String trabajaMadre) {
        this.trabajaMadre = trabajaMadre;
    }

    @Column(name = "TIPO_TRABAJO_MADRE", length = 50)
    public String getTipoTrabajoMadre() {
        return tipoTrabajoMadre;
    }

    public void setTipoTrabajoMadre(String tipoTrabajoMadre) {
        this.tipoTrabajoMadre = tipoTrabajoMadre;
    }

    @Column(name = "FUMA", length = 1)
    public String getFuma() {
        return fuma;
    }

    public void setFuma(String fuma) {
        this.fuma = fuma;
    }

    @Column(name = "PERIODICIDAD_FUNA", length = 50)
    public String getPeriodicidadFuma() {
        return periodicidadFuma;
    }

    public void setPeriodicidadFuma(String periodicidadFuma) {
        this.periodicidadFuma = periodicidadFuma;
    }

    @Column(name = "CANTIDAD_CIGARRILLOS", length = 4)
    public Integer getCantidadCigarrillos() {
        return cantidadCigarrillos;
    }

    public void setCantidadCigarrillos(Integer cantidadCigarrillos) {
        this.cantidadCigarrillos = cantidadCigarrillos;
    }

    @Column(name = "FUMA_DENTRO_CASA", length = 1)
    public String getFumaDentroCasa() {
        return fumaDentroCasa;
    }

    public void setFumaDentroCasa(String fumaDentroCasa) {
        this.fumaDentroCasa = fumaDentroCasa;
    }

    @Column(name = "TUBERCULOSIS_PULMONAR_ACTUAL", length = 1)
    public String getTuberculosisPulmonarActual() {
        return tuberculosisPulmonarActual;
    }

    public void setTuberculosisPulmonarActual(String tuberculosisPulmonarActual) {
        this.tuberculosisPulmonarActual = tuberculosisPulmonarActual;
    }

    @Column(name = "FECHA_DIAG_TUBPUL_ACTUAL", length = 8)
    public String getFechaDiagnosticoTubPulActual() {
        return fechaDiagnosticoTubPulActual;
    }

    public void setFechaDiagnosticoTubPulActual(String fechaDiagnosticoTPActual) {
        this.fechaDiagnosticoTubPulActual = fechaDiagnosticoTPActual;
    }

    @Column(name = "TRATAMIENTO_TUBPUL_ACTUAL", length = 1)
    public String getTomaTratamientoTubPulActual() {
        return tomaTratamientoTubPulActual;
    }

    public void setTomaTratamientoTubPulActual(String tomaTratamientoTPActual) {
        this.tomaTratamientoTubPulActual = tomaTratamientoTPActual;
    }

    @Column(name = "COMPLETO_TRATAMIENTO_TUBPUL_ACTUAL", length = 1)
    public String getCompletoTratamientoTubPulActual() {
        return completoTratamientoTubPulActual;
    }

    public void setCompletoTratamientoTubPulActual(String completoTratamientoTPActual) {
        this.completoTratamientoTubPulActual = completoTratamientoTPActual;
    }

    @Column(name = "TUBERCULOSIS_PULMONAR_PASADO", length = 1)
    public String getTuberculosisPulmonarPasado() {
        return tuberculosisPulmonarPasado;
    }

    public void setTuberculosisPulmonarPasado(String tuberculosisPulmonarPasado) {
        this.tuberculosisPulmonarPasado = tuberculosisPulmonarPasado;
    }

    @Column(name = "FECHA_DIAG_TUBPUL_PASADO", length = 8)
    public String getFechaDiagnosticoTubPulPasado() {
        return fechaDiagnosticoTubPulPasado;
    }

    public void setFechaDiagnosticoTubPulPasado(String fechaDiagnosticoTPPasado) {
        this.fechaDiagnosticoTubPulPasado = fechaDiagnosticoTPPasado;
    }

    @Column(name = "FECHA_DIAG_TUBPUL_PASADO_DES", length = 1, nullable = true)
    public String getFechaDiagnosticoTubPulPasadoDes() {
        return fechaDiagnosticoTubPulPasadoDes;
    }

    public void setFechaDiagnosticoTubPulPasadoDes(String fechaDiagnosticoTubPulPasadoDes) {
        this.fechaDiagnosticoTubPulPasadoDes = fechaDiagnosticoTubPulPasadoDes;
    }

    @Column(name = "TRATAMIENTO_TUBBUL_PASADO", length = 1)
    public String getTomaTratamientoTubPulPasado() {
        return tomaTratamientoTubPulPasado;
    }

    public void setTomaTratamientoTubPulPasado(String tomaTratamientoTPPasado) {
        this.tomaTratamientoTubPulPasado = tomaTratamientoTPPasado;
    }

    @Column(name = "COMPLETO_TRATAMIENTO_TUBPUL_PAS", length = 1)
    public String getCompletoTratamientoTubPulPasado() {
        return completoTratamientoTubPulPasado;
    }

    public void setCompletoTratamientoTubPulPasado(String completoTratamientoTPPasado) {
        this.completoTratamientoTubPulPasado = completoTratamientoTPPasado;
    }

    @Column(name = "ALERGIA_RESPIRATORIA", length = 1)
    public String getAlergiaRespiratoria() {
        return alergiaRespiratoria;
    }

    public void setAlergiaRespiratoria(String alergiaRespiratoria) {
        this.alergiaRespiratoria = alergiaRespiratoria;
    }

    @Column(name = "CARDIOPATIA", length = 1)
    public String getCardiopatia() {
        return cardiopatia;
    }

    public void setCardiopatia(String cardiopatia) {
        this.cardiopatia = cardiopatia;
    }

    @Column(name = "ENFERM_PULMONAR_OBST_CRONICA", length = 1)
    public String getEnfermedadPulmonarOC() {
        return enfermedadPulmonarOC;
    }

    public void setEnfermedadPulmonarOC(String enfermedadPulmonarOC) {
        this.enfermedadPulmonarOC = enfermedadPulmonarOC;
    }

    @Column(name = "DIABETES", length = 1)
    public String getDiabetes() {
        return diabetes;
    }

    public void setDiabetes(String diabetes) {
        this.diabetes = diabetes;
    }

    @Column(name = "PRESION_ALTA", length = 1)
    public String getPresionAlta() {
        return presionAlta;
    }

    public void setPresionAlta(String presionAlta) {
        this.presionAlta = presionAlta;
    }

    @Column(name = "ASMA", length = 1)
    public String getAsma() {
        return asma;
    }

    public void setAsma(String asma) {
        this.asma = asma;
    }

    @Column(name = "SILBIDO_RESPIRAR_PECHO_APRETADO", length = 1)
    public String getSilbidoRespirarPechoApretado() {
        return silbidoRespirarPechoApretado;
    }

    public void setSilbidoRespirarPechoApretado(String silbidoRespirarPechoApretado) {
        this.silbidoRespirarPechoApretado = silbidoRespirarPechoApretado;
    }

    @Column(name = "TOS_SIN_FIEBRE_RESFRIADO", length = 1)
    public String getTosSinFiebreResfriado() {
        return tosSinFiebreResfriado;
    }

    public void setTosSinFiebreResfriado(String tosSinFiebreResfriado) {
        this.tosSinFiebreResfriado = tosSinFiebreResfriado;
    }

    @Column(name = "INHALADORES_SPRAY", length = 1)
    public String getUsaInhaladoresSpray() {
        return usaInhaladoresSpray;
    }

    public void setUsaInhaladoresSpray(String usaInhaladoresSpray) {
        this.usaInhaladoresSpray = usaInhaladoresSpray;
    }

    @Column(name = "CRISIS_ASMA", length = 1)
    public String getCrisisAsma() {
        return crisisAsma;
    }

    public void setCrisisAsma(String crisisAsma) {
        this.crisisAsma = crisisAsma;
    }

    @Column(name = "CANT_CRISIS_ASMA", length = 4)
    public Integer getCantidadCrisisAsma() {
        return cantidadCrisisAsma;
    }

    public void setCantidadCrisisAsma(Integer cantidadCrisisAsma) {
        this.cantidadCrisisAsma = cantidadCrisisAsma;
    }

    @Column(name = "CANT_ENFERM_CUADROS_RESP")
    public Integer getVecesEnfermoEnfermedadesRes() {
        return vecesEnfermoEnfermedadesRes;
    }

    public void setVecesEnfermoEnfermedadesRes(Integer vecesEnfermoEnfermedadesRes) {
        this.vecesEnfermoEnfermedadesRes = vecesEnfermoEnfermedadesRes;
    }

    @Column(name = "OTRAS_ENFERMEDADES", length = 1)
    public String getOtrasEnfermedades() {
        return otrasEnfermedades;
    }

    public void setOtrasEnfermedades(String otrasEnfermedades) {
        this.otrasEnfermedades = otrasEnfermedades;
    }

    @Column(name = "DESC_OTRAS_ENFERMEDADES", length = 255)
    public String getDescOtrasEnfermedades() {
        return descOtrasEnfermedades;
    }

    public void setDescOtrasEnfermedades(String desOtrasEnfermedades) {
        this.descOtrasEnfermedades = desOtrasEnfermedades;
    }

    @Column(name = "VACUNA_INFLUENZA", length = 1)
    public String getVacunaInfluenza() {
        return vacunaInfluenza;
    }

    public void setVacunaInfluenza(String vacunaInfluenza) {
        this.vacunaInfluenza = vacunaInfluenza;
    }

    @Column(name = "ANIO_VACUNA_INFLUENZA", length = 4)
    public Integer getAnioVacunaInfluenza() {
        return anioVacunaInfluenza;
    }

    public void setAnioVacunaInfluenza(Integer anioVacunaInfluenza) {
        this.anioVacunaInfluenza = anioVacunaInfluenza;
    }

    @Column(name = "RECURSO1", nullable = true, length = 50)
    public String getRecurso1() {
        return recurso1;
    }

    public void setRecurso1(String recurso1) {
        this.recurso1 = recurso1;
    }

    @Column(name = "OTRO_RECURSO1", nullable = true, length = 50)
    public String getOtrorecurso1() {
        return otrorecurso1;
    }

    public void setOtrorecurso1(String otrorecurso1) {
        this.otrorecurso1 = otrorecurso1;
    }

    @Override
    public boolean isFieldAuditable(String fieldname) {
        return true;
    }

    @Override
    public String toString() {
        return "EncuestaParticipante{" + participante.getParticipante().getCodigo() +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof EncuestaParticipante)) return false;

        EncuestaParticipante that = (EncuestaParticipante) o;

        return  (!participante.equals(that.participante));
    }

    @Override
    public int hashCode() {
        return participante.hashCode();
    }
}
