package ni.org.ics.estudios.domain.muestreoanual;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by Ing. Santiago Carballo on 01/03/2023.
 */
@Entity
@Table(name = "encuesta_satisfaccion_usuario", catalog = "estudios_ics")
public class EncuestaSatisfaccionUsuario implements Serializable{
    private static final long serialVersionUID = 1L;
    private Long codigo;
    private Integer codigoParticipante;
    private String atencionMedica;
    private String familaAmistades;
    private String desicionPropia;
    private String obsequiosOfreceEstudio;
    private String ayudaRecibeComunidad;
    private String examenesLaboratorio;
    private String interesCientificoPersonalP1;
    private String informacionAyudaOtros;
    private String otraP1;
    private String calidadAtencionMedica;
    private String atencionOportuna;
    private String buenaCoordinacionhosp;
    private String infoEstadoSalud;
    private String enseniaPrevEnfermedades;
    private String infoConsMejoraConocimientos;
    private String interesCientificoPersonalP2;
    private String mejorarTratamientoDengue;
    private String unicaManeraRecibirAtencionMed;
    private String otraP2;
    private String difBuscarAtencionMed;
    private String centroSaludLejos;
    private String costoTrasnporteElevado;
    private String trabajoTiempo;
    private String noQueriapasarConsulta;
    private String horarioClases;
    private String temorTomenMx;
    private String temorInfoPersonal;
    private String otraP3;
    private String recomendariaAmigoFamiliar;
    private String atencionCalidad;
    private String respNecesidadesSaludOportuna;
    private String tiempoEsperaCorto;
    private String mejorAtencionQueSeguro;
    private String examenLabNecesarios;
    private String conocimientosImportantes;
    private String pocosRequisitos;
    private String otraP_4_1;
    private String atencionPersonalMala;
    private String noDanRespNecesidades;
    private String tiempoEsperaLargo;
    private String mejorAtencionOtraUnidadSalud;
    private String solicitaDemasiadaMx;
    private String muchosRequisitos;
    private String noExplicanHacenMx;
    private String noConfianza;
    private String otraP_4_2;
    private String comprendeProcedimientos;
    private String noComodoRealizarPreg;
    private String noRespondieronPreg;
    private String explicacionRapida;
    private String noDejaronHacerPreg;
    private String otraP_5_1;
    private String brindaronConsejosPrevencionEnfer;
    private String entiendoProcedimientosEstudios;
    private String satisfecho;
    private String comodoInfoRecolectada;
    private String noComodoPreguntas;
    private String recomendacionMejorarAtencion;
    private String importanciaRecibirInformacion;
    private String identificadoEquipo;
    private char estado;
    private char pasivo;
    private Date fechaRegistro;
    private String creado;
    private String usuarioRegistro;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CODIGO", nullable = false)
    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }

    @Column(name="CODIGO_PARTICIPANTE", nullable = true)
    public Integer getCodigoParticipante() {
        return codigoParticipante;
    }

    public void setCodigoParticipante(Integer codigoParticipante) {
        this.codigoParticipante = codigoParticipante;
    }

    @Column(name = "ATENCION_MEDICA", length = 1)
    public String getAtencionMedica() {
        return atencionMedica;
    }

    public void setAtencionMedica(String atencionMedica) {
        this.atencionMedica = atencionMedica;
    }

    @Column(name = "FAMILA_AMISTADES", length = 1)
    public String getFamilaAmistades() {
        return familaAmistades;
    }

    public void setFamilaAmistades(String familaAmistades) {
        this.familaAmistades = familaAmistades;
    }

    @Column(name = "DESICION_PROPIA", length = 1)
    public String getDesicionPropia() {
        return desicionPropia;
    }

    public void setDesicionPropia(String desicionPropia) {
        this.desicionPropia = desicionPropia;
    }

    @Column(name = "OBSEQUIOS_OFRECE_ESTUDIO", length = 1)
    public String getObsequiosOfreceEstudio() {
        return obsequiosOfreceEstudio;
    }

    public void setObsequiosOfreceEstudio(String obsequiosOfreceEstudio) {
        this.obsequiosOfreceEstudio = obsequiosOfreceEstudio;
    }

    @Column(name = "AYUDA_RECIBE_COMUNIDAD", length = 1)
    public String getAyudaRecibeComunidad() {
        return ayudaRecibeComunidad;
    }

    public void setAyudaRecibeComunidad(String ayudaRecibeComunidad) {
        this.ayudaRecibeComunidad = ayudaRecibeComunidad;
    }

    @Column(name = "EXAMENES_LABORATORIO", length = 1)
    public String getExamenesLaboratorio() {
        return examenesLaboratorio;
    }

    public void setExamenesLaboratorio(String examenesLaboratorio) {
        this.examenesLaboratorio = examenesLaboratorio;
    }

    @Column(name = "INTERES_CIENTIFICO_PERSONAL_P_1", length = 1)
    public String getInteresCientificoPersonalP1() {
        return interesCientificoPersonalP1;
    }

    public void setInteresCientificoPersonalP1(String interesCientificoPersonalP1) {
        this.interesCientificoPersonalP1 = interesCientificoPersonalP1;
    }

    @Column(name = "INFORMACION_AYUDA_OTROS", length = 1)
    public String getInformacionAyudaOtros() {
        return informacionAyudaOtros;
    }

    public void setInformacionAyudaOtros(String informacionAyudaOtros) {
        this.informacionAyudaOtros = informacionAyudaOtros;
    }

    @Column(name = "OTRA_P_1", length = 255)
    public String getOtraP1() {
        return otraP1;
    }

    public void setOtraP1(String otraP1) {
        this.otraP1 = otraP1;
    }

    @Column(name = "CALIDAD_ATENCION_MEDICA", length = 1)
    public String getCalidadAtencionMedica() {
        return calidadAtencionMedica;
    }

    public void setCalidadAtencionMedica(String calidadAtencionMedica) {
        this.calidadAtencionMedica = calidadAtencionMedica;
    }

    @Column(name = "ATENCION_EMERGENCIA_OPORTUNA", length = 1)
    public String getAtencionOportuna() {
        return atencionOportuna;
    }

    public void setAtencionOportuna(String atencionOportuna) {
        this.atencionOportuna = atencionOportuna;
    }

    @Column(name = "BUENA_COORDINACION_HOSP", length = 1)
    public String getBuenaCoordinacionhosp() {
        return buenaCoordinacionhosp;
    }

    public void setBuenaCoordinacionhosp(String buenaCoordinacionhosp) {
        this.buenaCoordinacionhosp = buenaCoordinacionhosp;
    }

    @Column(name = "INFO_ESTADO_SALUD", length = 1)
    public String getInfoEstadoSalud() {
        return infoEstadoSalud;
    }

    public void setInfoEstadoSalud(String infoEstadoSalud) {
        this.infoEstadoSalud = infoEstadoSalud;
    }

    @Column(name = "ENSENIA_PREV_ENFERMEDADES", length = 1)
    public String getEnseniaPrevEnfermedades() {
        return enseniaPrevEnfermedades;
    }

    public void setEnseniaPrevEnfermedades(String enseniaPrevEnfermedades) {
        this.enseniaPrevEnfermedades = enseniaPrevEnfermedades;
    }

    @Column(name = "INFO_CONS_MEJORA_CONOCIMIENTOS", length = 1)
    public String getInfoConsMejoraConocimientos() {
        return infoConsMejoraConocimientos;
    }

    public void setInfoConsMejoraConocimientos(String infoConsMejoraConocimientos) {
        this.infoConsMejoraConocimientos = infoConsMejoraConocimientos;
    }

    @Column(name = "INTERES_CIENTIFICO_PERSONAL_P_2", length = 1)
    public String getInteresCientificoPersonalP2() {
        return interesCientificoPersonalP2;
    }

    public void setInteresCientificoPersonalP2(String interesCientificoPersonalP2) {
        this.interesCientificoPersonalP2 = interesCientificoPersonalP2;
    }

    @Column(name = "MEJORAR_TRATAMIENTO_DENGUE", length = 1)
    public String getMejorarTratamientoDengue() {
        return mejorarTratamientoDengue;
    }

    public void setMejorarTratamientoDengue(String mejorarTratamientoDengue) {
        this.mejorarTratamientoDengue = mejorarTratamientoDengue;
    }

    @Column(name = "UNICA_MANERA_RECIBIR_ATENCION_MED", length = 1)
    public String getUnicaManeraRecibirAtencionMed() {
        return unicaManeraRecibirAtencionMed;
    }

    public void setUnicaManeraRecibirAtencionMed(String unicaManeraRecibirAtencionMed) {
        this.unicaManeraRecibirAtencionMed = unicaManeraRecibirAtencionMed;
    }

    @Column(name = "OTRA_P_2", length = 255)
    public String getOtraP2() {
        return otraP2;
    }

    public void setOtraP2(String otraP2) {
        this.otraP2 = otraP2;
    }

    @Column(name = "DIF_BUSCAR_ATENCION_MED", length = 1)
    public String getDifBuscarAtencionMed() {
        return difBuscarAtencionMed;
    }

    public void setDifBuscarAtencionMed(String difBuscarAtencionMed) {
        this.difBuscarAtencionMed = difBuscarAtencionMed;
    }

    @Column(name = "CENTRO_SALUD_LEJOS", length = 1)
    public String getCentroSaludLejos() {
        return centroSaludLejos;
    }

    public void setCentroSaludLejos(String centroSaludLejos) {
        this.centroSaludLejos = centroSaludLejos;
    }

    @Column(name = "COSTO_TRASNPORTE_ELEVADO", length = 1)
    public String getCostoTrasnporteElevado() {
        return costoTrasnporteElevado;
    }

    public void setCostoTrasnporteElevado(String costoTrasnporteElevado) {
        this.costoTrasnporteElevado = costoTrasnporteElevado;
    }

    @Column(name = "TRABAJO_TIEMPO", length = 1)
    public String getTrabajoTiempo() {
        return trabajoTiempo;
    }

    public void setTrabajoTiempo(String trabajoTiempo) {
        this.trabajoTiempo = trabajoTiempo;
    }

    @Column(name = "NO_QUERIA_PASAR_CONSULTA", length = 1)
    public String getNoQueriapasarConsulta() {
        return noQueriapasarConsulta;
    }

    public void setNoQueriapasarConsulta(String noQueriapasarConsulta) {
        this.noQueriapasarConsulta = noQueriapasarConsulta;
    }

    @Column(name = "HORARIO_CLASES", length = 1)
    public String getHorarioClases() {
        return horarioClases;
    }

    public void setHorarioClases(String horarioClases) {
        this.horarioClases = horarioClases;
    }

    @Column(name = "TEMOR_TOMEN_MX", length = 1)
    public String getTemorTomenMx() {
        return temorTomenMx;
    }

    public void setTemorTomenMx(String temorTomenMx) {
        this.temorTomenMx = temorTomenMx;
    }

    @Column(name = "TEMOR_INFO_PERSONAL", length = 1)
    public String getTemorInfoPersonal() {
        return temorInfoPersonal;
    }

    public void setTemorInfoPersonal(String temorInfoPersonal) {
        this.temorInfoPersonal = temorInfoPersonal;
    }

    @Column(name = "OTRA_P_3", length = 255)
    public String getOtraP3() {
        return otraP3;
    }

    public void setOtraP3(String otraP3) {
        this.otraP3 = otraP3;
    }

    @Column(name = "RECOMENDARIA_AMIGO_FAMILIAR", length = 1)
    public String getRecomendariaAmigoFamiliar() {
        return recomendariaAmigoFamiliar;
    }

    public void setRecomendariaAmigoFamiliar(String recomendariaAmigoFamiliar) {
        this.recomendariaAmigoFamiliar = recomendariaAmigoFamiliar;
    }

    @Column(name = "ATENCION_CALIDAD", length = 1)
    public String getAtencionCalidad() {
        return atencionCalidad;
    }

    public void setAtencionCalidad(String atencionCalidad) {
        this.atencionCalidad = atencionCalidad;
    }

    @Column(name = "RESP_NECESIDADES_SALUD_OPORTUNA", length = 1)
    public String getRespNecesidadesSaludOportuna() {
        return respNecesidadesSaludOportuna;
    }

    public void setRespNecesidadesSaludOportuna(String respNecesidadesSaludOportuna) {
        this.respNecesidadesSaludOportuna = respNecesidadesSaludOportuna;
    }

    @Column(name = "TIEMPO_ESPERA_CORTO", length = 1)
    public String getTiempoEsperaCorto() {
        return tiempoEsperaCorto;
    }

    public void setTiempoEsperaCorto(String tiempoEsperaCorto) {
        this.tiempoEsperaCorto = tiempoEsperaCorto;
    }

    @Column(name = "MEJOR_ATENCION_QUE_SEGURO", length = 1)
    public String getMejorAtencionQueSeguro() {
        return mejorAtencionQueSeguro;
    }

    public void setMejorAtencionQueSeguro(String mejorAtencionQueSeguro) {
        this.mejorAtencionQueSeguro = mejorAtencionQueSeguro;
    }

    @Column(name = "EXAMEN_LAB_NECESARIOS", length = 1)
    public String getExamenLabNecesarios() {
        return examenLabNecesarios;
    }

    public void setExamenLabNecesarios(String examenLabNecesarios) {
        this.examenLabNecesarios = examenLabNecesarios;
    }

    @Column(name = "CONOCIMIENTOS_IMPORTANTES", length = 1)
    public String getConocimientosImportantes() {
        return conocimientosImportantes;
    }

    public void setConocimientosImportantes(String conocimientosImportantes) {
        this.conocimientosImportantes = conocimientosImportantes;
    }

    @Column(name = "POCOS_REQUISITOS", length = 1)
    public String getPocosRequisitos() {
        return pocosRequisitos;
    }

    public void setPocosRequisitos(String pocosRequisitos) {
        this.pocosRequisitos = pocosRequisitos;
    }

    @Column(name = "OTRA_P_4_1", length = 255)
    public String getOtraP_4_1() {
        return otraP_4_1;
    }

    public void setOtraP_4_1(String otraP_4_1) {
        this.otraP_4_1 = otraP_4_1;
    }

    @Column(name = "ATENCION_PERSONAL_MALA", length = 1)
    public String getAtencionPersonalMala() {
        return atencionPersonalMala;
    }

    public void setAtencionPersonalMala(String atencionPersonalMala) {
        this.atencionPersonalMala = atencionPersonalMala;
    }

    @Column(name = "NO_DAN_RESP_NECESIDADES", length = 1)
    public String getNoDanRespNecesidades() {
        return noDanRespNecesidades;
    }

    public void setNoDanRespNecesidades(String noDanRespNecesidades) {
        this.noDanRespNecesidades = noDanRespNecesidades;
    }

    @Column(name = "TIEMPO_ESPERA_LARGO", length = 1)
    public String getTiempoEsperaLargo() {
        return tiempoEsperaLargo;
    }

    public void setTiempoEsperaLargo(String tiempoEsperaLargo) {
        this.tiempoEsperaLargo = tiempoEsperaLargo;
    }

    @Column(name = "MEJOR_ATENCION_OTRA_UNIDAD_SALUD", length = 1)
    public String getMejorAtencionOtraUnidadSalud() {
        return mejorAtencionOtraUnidadSalud;
    }

    public void setMejorAtencionOtraUnidadSalud(String mejorAtencionOtraUnidadSalud) {
        this.mejorAtencionOtraUnidadSalud = mejorAtencionOtraUnidadSalud;
    }

    @Column(name = "SOLICITA_DEMASIADA_MX", length = 1)
    public String getSolicitaDemasiadaMx() {
        return solicitaDemasiadaMx;
    }

    public void setSolicitaDemasiadaMx(String solicitaDemasiadaMx) {
        this.solicitaDemasiadaMx = solicitaDemasiadaMx;
    }

    @Column(name = "MUCHOS_REQUISITOS", length = 1)
    public String getMuchosRequisitos() {
        return muchosRequisitos;
    }

    public void setMuchosRequisitos(String muchosRequisitos) {
        this.muchosRequisitos = muchosRequisitos;
    }

    @Column(name = "NO_EXPLICAN_HACEN_MX", length = 1)
    public String getNoExplicanHacenMx() {
        return noExplicanHacenMx;
    }

    public void setNoExplicanHacenMx(String noExplicanHacenMx) {
        this.noExplicanHacenMx = noExplicanHacenMx;
    }

    @Column(name = "NO_CONFIANZA", length = 1)
    public String getNoConfianza() {
        return noConfianza;
    }

    public void setNoConfianza(String noConfianza) {
        this.noConfianza = noConfianza;
    }

    @Column(name = "OTRA_P_4_2", length = 255)
    public String getOtraP_4_2() {
        return otraP_4_2;
    }

    public void setOtraP_4_2(String otraP_4_2) {
        this.otraP_4_2 = otraP_4_2;
    }

    @Column(name = "COMPRENDE_PROCEDIMIENTOS", length = 1)
    public String getComprendeProcedimientos() {
        return comprendeProcedimientos;
    }

    public void setComprendeProcedimientos(String comprendeProcedimientos) {
        this.comprendeProcedimientos = comprendeProcedimientos;
    }

    @Column(name = "NO_COMODO_REALIZAR_PREG", length = 1)
    public String getNoComodoRealizarPreg() {
        return noComodoRealizarPreg;
    }

    public void setNoComodoRealizarPreg(String noComodoRealizarPreg) {
        this.noComodoRealizarPreg = noComodoRealizarPreg;
    }

    @Column(name = "NO_RESPONDIERON_PREG", length = 1)
    public String getNoRespondieronPreg() {
        return noRespondieronPreg;
    }

    public void setNoRespondieronPreg(String noRespondieronPreg) {
        this.noRespondieronPreg = noRespondieronPreg;
    }

    @Column(name = "EXPLICACION_RAPIDA", length = 1)
    public String getExplicacionRapida() {
        return explicacionRapida;
    }

    public void setExplicacionRapida(String explicacionRapida) {
        this.explicacionRapida = explicacionRapida;
    }

    @Column(name = "NO_DEJARON_HACER_PREG", length = 1)
    public String getNoDejaronHacerPreg() {
        return noDejaronHacerPreg;
    }

    public void setNoDejaronHacerPreg(String noDejaronHacerPreg) {
        this.noDejaronHacerPreg = noDejaronHacerPreg;
    }

    @Column(name = "OTRA_P_5_1", length = 255)
    public String getOtraP_5_1() {
        return otraP_5_1;
    }

    public void setOtraP_5_1(String otraP_5_1) {
        this.otraP_5_1 = otraP_5_1;
    }

    @Column(name = "BRINDARON_CONSEJOS_PREVENCION_ENFER", length = 1)
    public String getBrindaronConsejosPrevencionEnfer() {
        return brindaronConsejosPrevencionEnfer;
    }

    public void setBrindaronConsejosPrevencionEnfer(String brindaronConsejosPrevencionEnfer) {
        this.brindaronConsejosPrevencionEnfer = brindaronConsejosPrevencionEnfer;
    }

    @Column(name = "ENTIENDO_PROCEDIMIENTOS_ESTUDIOS", length = 1)
    public String getEntiendoProcedimientosEstudios() {
        return entiendoProcedimientosEstudios;
    }

    public void setEntiendoProcedimientosEstudios(String entiendoProcedimientosEstudios) {
        this.entiendoProcedimientosEstudios = entiendoProcedimientosEstudios;
    }

    @Column(name = "SATISFECHO", length = 1)
    public String getSatisfecho() {
        return satisfecho;
    }

    public void setSatisfecho(String satisfecho) {
        this.satisfecho = satisfecho;
    }

    @Column(name = "COMODO_INFO_RECOLECTADA", length = 1)
    public String getComodoInfoRecolectada() {
        return comodoInfoRecolectada;
    }

    public void setComodoInfoRecolectada(String comodoInfoRecolectada) {
        this.comodoInfoRecolectada = comodoInfoRecolectada;
    }

    @Column(name = "NO_COMODO_PREGUNTAS", length = 1)
    public String getNoComodoPreguntas() {
        return noComodoPreguntas;
    }

    public void setNoComodoPreguntas(String noComodoPreguntas) {
        this.noComodoPreguntas = noComodoPreguntas;
    }

    @Column(name = "RECOMENDACION_MEJORAR_ATENCION", length = 1)
    public String getRecomendacionMejorarAtencion() {
        return recomendacionMejorarAtencion;
    }

    public void setRecomendacionMejorarAtencion(String recomendacionMejorarAtencion) {
        this.recomendacionMejorarAtencion = recomendacionMejorarAtencion;
    }

    @Column(name = "IMPORTANCIA_RECIBIR_INFORMACION", length = 1)
    public String getImportanciaRecibirInformacion() {
        return importanciaRecibirInformacion;
    }

    public void setImportanciaRecibirInformacion(String importanciaRecibirInformacion) {
        this.importanciaRecibirInformacion = importanciaRecibirInformacion;
    }

    @Column(name = "IDENTIFICADOR_EQUIPO", nullable = false, length = 50)
    public String getIdentificadoEquipo() {
        return identificadoEquipo;
    }

    public void setIdentificadoEquipo(String identificadoEquipo) {
        this.identificadoEquipo = identificadoEquipo;
    }

    @Column(name = "estado", nullable = false, length = 15)
    public char getEstado() {
        return estado;
    }

    public void setEstado(char estado) {
        this.estado = estado;
    }

    @Column(name = "PASIVO", nullable = false, length = 15)
    public char getPasivo() {
        return pasivo;
    }

    public void setPasivo(char pasivo) {
        this.pasivo = pasivo;
    }

    @Column(name = "FECHA_REGISTRO", nullable = false)
    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    @Column(name = "CREADO", length = 255)
    public String getCreado() {
        return creado;
    }

    public void setCreado(String creado) {
        this.creado = creado;
    }

    @Column(name = "USUARIO_REGISTRO", nullable = false)
    public String getUsuarioRegistro() {
        return usuarioRegistro;
    }

    public void setUsuarioRegistro(String usuarioRegistro) {
        this.usuarioRegistro = usuarioRegistro;
    }
}
