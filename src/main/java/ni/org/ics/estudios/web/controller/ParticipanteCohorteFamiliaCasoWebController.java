package ni.org.ics.estudios.web.controller;

import com.google.gson.Gson;
import ni.org.ics.estudios.domain.Participante;
import ni.org.ics.estudios.domain.cohortefamilia.ParticipanteCohorteFamilia;
import ni.org.ics.estudios.domain.cohortefamilia.casos.CasaCohorteFamiliaCaso;
import ni.org.ics.estudios.domain.cohortefamilia.casos.ParticipanteCohorteFamiliaCaso;
import ni.org.ics.estudios.domain.covid19.CandidatoTransmisionCovid19;
import ni.org.ics.estudios.domain.covid19.CasoCovid19;
import ni.org.ics.estudios.domain.covid19.ParticipanteCasoCovid19;
import ni.org.ics.estudios.domain.influenzauo1.ParticipanteCasoUO1;
import ni.org.ics.estudios.domain.muestreoanual.ParticipanteProcesos;
import ni.org.ics.estudios.dto.ParticipanteBusquedaDto;
import ni.org.ics.estudios.dto.muestras.MxDto;
import ni.org.ics.estudios.language.MessageResource;
import ni.org.ics.estudios.service.MessageResourceService;
import ni.org.ics.estudios.service.ParticipanteService;
import ni.org.ics.estudios.service.cohortefamilia.ParticipanteCohorteFamiliaService;
import ni.org.ics.estudios.service.cohortefamilia.casos.CasaCohorteFamiliaCasoService;
import ni.org.ics.estudios.service.cohortefamilia.casos.ParticipanteCohorteFamiliaCasoService;
import ni.org.ics.estudios.service.covid.CovidService;
import ni.org.ics.estudios.service.influenzauo1.ParticipanteCasoUO1Service;
import ni.org.ics.estudios.service.muestreoanual.ParticipanteProcesosService;
import ni.org.ics.estudios.web.utils.DateUtil;
import org.apache.commons.lang3.text.translate.UnicodeEscaper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.text.ParseException;
import java.util.*;

/**
 * Created by Miguel Salinas on 6/28/2017.
 * V1.0
 */
@Controller
@RequestMapping("/super/particaso/*")
public class ParticipanteCohorteFamiliaCasoWebController {

    private static final Logger logger = LoggerFactory.getLogger(ParticipanteCohorteFamiliaCasoWebController.class);

    @Resource(name = "participanteCohorteFamiliaCasoService")
    private ParticipanteCohorteFamiliaCasoService participanteCohorteFamiliaCasoService;

    @Resource(name = "participanteCohorteFamiliaService")
    private ParticipanteCohorteFamiliaService participanteCohorteFamiliaService;

    @Resource(name = "participanteService")
    private ParticipanteService participanteService;

    @Resource(name = "casaCohorteFamiliaCasoService")
    private CasaCohorteFamiliaCasoService casaCohorteFamiliaCasoService;

    @Resource(name = "participanteProcesosService")
    private ParticipanteProcesosService participanteProcesosService;

    @Resource(name = "CovidService")
    private CovidService covidService;

    @Resource(name = "messageResourceService")
    private MessageResourceService messageResourceService;

    @Resource(name = "participanteCasoUO1Service")
    private ParticipanteCasoUO1Service participanteCasoUO1Service;


    @RequestMapping(value = "searchParticipant", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    ResponseEntity<String> buscarParticipante(@RequestParam(value="participantCode", required=true ) Integer codigo) throws ParseException {
        logger.debug("buscar participanteChf para monitoreo intensivo");
        ParticipanteCohorteFamilia participanteChf = participanteCohorteFamiliaService.getParticipanteCHFByCodigo(codigo);
        ParticipanteProcesos procesosAlertas = null;
        if (participanteChf==null) {
            Participante participante = participanteService.getParticipanteByCodigo(codigo);
            if (participante!=null){
                ParticipanteProcesos procesos = participanteProcesosService.getParticipante(codigo);
                if (procesos!=null && procesos.getEstPart().equals(0))
                    return createJsonResponse("Participante retirado");
                else return createJsonResponse("Participante aún no ha sido enrolado");
            }
            return createJsonResponse("No se encontró participante según el código ingresado");
        }else{
            ParticipanteCohorteFamiliaCaso participanteCaso = this.participanteCohorteFamiliaCasoService.getParticipanteCohorteFamiliaCasosByParticipantePos(codigo);
            if (participanteCaso!=null)
                return createJsonResponse("Participante ya fue registrado como positivo en la casa: "+participanteCaso.getCodigoCaso().getCasa().getCodigoCHF());
            else {
                ParticipanteProcesos procesos = participanteProcesosService.getParticipante(codigo);
                if (procesos!=null && procesos.getEstPart().equals(0))
                    return createJsonResponse("Participante retirado");
            }
        }
        /*participanteDto*/
        MessageResource intervalDias = messageResourceService.getMensaje("CAT_INTERVAL_DIAS_MX_30");
        Integer dias = Integer.parseInt(intervalDias.getSpanish());
        List<MxDto> ultimoCasoIn30Day = this.covidService.getInforUltimoCasoIn30Day(codigo,dias);
        String alerta="";
        //validar convaleciente
         procesosAlertas = participanteProcesosService.getParticipante(codigo);
        if (procesosAlertas.getConvalesciente().equalsIgnoreCase("Na")) {
            alerta+= "* Convaleciente con menos de 14 días. No tomar muestra. COMUNICAR AL SUPERVISOR\n ";
        } else if (procesosAlertas.getConvalesciente().equalsIgnoreCase("Si")) {
            alerta+= "* Convaleciente 14 días o más. COMUNICAR AL SUPERVISOR\n ";
        }

        if (procesosAlertas.getPosZika().matches("Si")) {
            alerta += "* Fue positivo a ZIKA <br> ";
        }

        if (procesosAlertas.getPosDengue() != null) {
            alerta += "* "+procesosAlertas.getPosDengue() + "<br> ";
        }

        if (procesosAlertas.getPosCovid() != null) {
            alerta += "* "+procesosAlertas.getPosCovid() + "<br> ";
        }

        if (procesosAlertas.getEstudio().contains("CH Familia")){
            List<ParticipanteCohorteFamiliaCaso> participanteCohorteFamiliaCasos = participanteCohorteFamiliaCasoService.getParticipanteCohorteFamiliaCasosByCasaPos(procesosAlertas.getCasaCHF());
            if (participanteCohorteFamiliaCasos.size()>0) {
                MessageResource positivoPor = messageResourceService.getMensajeByCatalogAndCatKey("CHF_CAT_POSITIVO_POR", participanteCohorteFamiliaCasos.get(0).getPositivoPor());
                alerta += "* Casa en seguimiento de Influenza - positivo por: "+(positivoPor != null ? positivoPor.getSpanish(): "-") +
                        " - Activación: "+DateUtil.CalcularDiferenciaDiasFechas(participanteCohorteFamiliaCasos.get(0).getCodigoCaso().getFechaInicio(), new Date()) + " Dias <br>";
            }
        }
        if (procesosAlertas.getEstudio().contains("UO1") || procesosAlertas.getEstudio().contains("Influenza")){
            ParticipanteCasoUO1 participanteCasoUO1 = participanteCasoUO1Service.getCasoActivoParticipante(procesosAlertas.getCodigo());
            if (participanteCasoUO1==null){
                List<MxDto> ultimoCasoUO1In30Days = this.participanteCasoUO1Service.getCasoUO1Ultimo30Day(codigo,dias);
                if (ultimoCasoUO1In30Days.size()>0){
                    String fecha1 = (ultimoCasoUO1In30Days.get(0).getFechaInactiva() == null) ? " - " : DateUtil.DateToString(ultimoCasoUO1In30Days.get(0).getFechaInactiva(), "dd/MM/yyyy");
                    alerta += " ► <strong>Positivo de Influenza,</strong> - en los ultimos <strong>" + dias + "</strong> días.";
                    alerta += " Inició el: <strong>" + DateUtil.DateToString(ultimoCasoUO1In30Days.get(0).getFechaInicio(), "dd/MM/yyyy") + ".</strong> Finalizó el: <strong>" + fecha1 + ".</strong><br>";
                    alerta += "\t Tiempo transcurrido despúes de haber finalizado el caso: <strong>" + ultimoCasoUO1In30Days.get(0).getDiasInactiva() + "</strong> días.";
                }
            }
            if (participanteCasoUO1 != null) {
                MessageResource positivoPor = messageResourceService.getMensajeByCatalogAndCatKey("UO1_CAT_POSITIVO_POR", participanteCasoUO1.getPositivoPor());
                alerta += "* Positivo de Influenza - Positivo por: "+(positivoPor != null ? positivoPor.getSpanish(): "-") +
                        " - Activación: "+DateUtil.CalcularDiferenciaDiasFechas(participanteCasoUO1.getFechaIngreso(), new Date()) + " Dias <br>";
            }
        }

        ParticipanteCasoCovid19 participanteCasoCovid19 = covidService.getParticipanteCasoCovid19Pos(procesosAlertas.getCodigo());
        if (participanteCasoCovid19 == null){
            if(ultimoCasoIn30Day.size()>0) {
                String fecha1 = (ultimoCasoIn30Day.get(0).getFechaInactiva() == null) ? " - " : DateUtil.DateToString(ultimoCasoIn30Day.get(0).getFechaInactiva(), "dd/MM/yyyy");
                alerta += " ► <strong>Positivo de SARS.</strong> - En los ultimos <strong>" + dias + "</strong> días en Casa Cohorte de Familia: <strong>" + ultimoCasoIn30Day.get(0).getCasaFam() + "</strong>";
                alerta += " Inició el: <strong>" + DateUtil.DateToString(ultimoCasoIn30Day.get(0).getFechaInicio(), "dd/MM/yyyy") + ".</strong> Finalizó el: <strong>" + fecha1 + ".</strong><br>";
                alerta += "\t Tiempo transcurrido despúes de haber finalizado el caso: <strong>" + ultimoCasoIn30Day.get(0).getDiasInactiva() + "</strong> días.";
            }
        }
        if (participanteCasoCovid19 != null) {
            MessageResource positivoPor = messageResourceService.getMensajeByCatalogAndCatKey("COVID_CAT_POSITIVO_POR", participanteCasoCovid19.getPositivoPor());
            alerta += "* Positivo de SARS - Positivo por: "+(positivoPor != null ? positivoPor.getSpanish(): "-") +
                    " - Activación: "+DateUtil.CalcularDiferenciaDiasFechas(participanteCasoCovid19.getCodigoCaso().getFechaIngreso(), new Date()) + " Dias <br>";
        } else {
            if (procesosAlertas.getCasaCHF() != null) {
                CasoCovid19 casoCovid19 = covidService.getCasoCovid19ByCasaChf(procesosAlertas.getCasaCHF());
                if (casoCovid19 != null) {
                    alerta += "* Casa en seguimiento de SARS" +
                            " - Activación: "+DateUtil.CalcularDiferenciaDiasFechas(casoCovid19.getFechaIngreso(), new Date()) + " Dias <br>";
                } else {
                    CandidatoTransmisionCovid19 candidatoTransmisionCovid19 = covidService.getCandidatoTransmisionCovid19ByCasaChf(procesosAlertas.getCasaCHF());
                    if (candidatoTransmisionCovid19 != null) {
                        alerta += "* Casa en seguimiento de SARS" +
                                " - consentimiento: "+ candidatoTransmisionCovid19.getConsentimiento();
                    }
                }
            }
        }

        ParticipanteBusquedaDto participanteBusquedaDto = this.covidService.getDatosParticipanteByCodigo(codigo);
        participanteBusquedaDto.setAlertas(alerta);
        if(procesosAlertas.getEstudio().contains("CH Familia")){
            // chf_muestras
            List<MxDto> getallFamily = this.covidService.getCasaFamiliaEnCh_MuestraMinor30Days(procesosAlertas.getCasaCHF(), dias);
            participanteBusquedaDto.setChf_muestras(getallFamily);
            //muestras
            List<MxDto> getAllMuestra = this.covidService.getMuestraMenor30DaysByCasaFam(procesosAlertas.getCasaCHF(), dias);
            participanteBusquedaDto.setMuestras(getAllMuestra);
        }else{
            List<MxDto> muestras = this.covidService.getMuestrasTomaMinor30Days(codigo,dias);
            participanteBusquedaDto.setMuestras(muestras);

            List<MxDto> chf_muestras = this.covidService.getChfMuestraToma2Minor30Days(codigo,dias);
            participanteBusquedaDto.setChf_muestras(chf_muestras);
        }
        participanteBusquedaDto.setIntervalo(intervalDias.getSpanish());
        /* participanteDto*/
        return createJsonResponse(participanteBusquedaDto);
    }

    @RequestMapping(value = "getParticipantsCasos", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    ResponseEntity<String> buscarParticipantesByCasa(@RequestParam(value="casas", required=true ) String casas,
                                                     @RequestParam(value = "etiquetas", required = false) String etiquetas) throws ParseException {
        logger.debug("buscar participantes de las casas para monitoreo intensivo"+etiquetas);
        //CHF_CAT_TIP_ETIQ_MI_01(BHC),CHF_CAT_TIP_ETIQ_MI_02(PBMC),CHF_CAT_TIP_ETIQ_MI_03(Rojo),CHF_CAT_TIP_ETIQ_MI_04(FLU)
        Map<String, String> map = new HashMap<String, String>();
        String[] codigosCasas = casas.split(",");
        //List<MuestraInfluenza> muestras = new ArrayList<MuestraInfluenza>();
        int indice = 0;
        for(String codigoCasa : codigosCasas){
            List<CasaCohorteFamiliaCaso> casosCasa = casaCohorteFamiliaCasoService.getCasaCohorteFamiliaCasosByCodigoCasa(codigoCasa);
            String evento = (casosCasa.size()<10?"0":"")+String.valueOf(casosCasa.size());
            List<ParticipanteCohorteFamiliaCaso> participantesCasos = participanteCohorteFamiliaCasoService.getParticipantesCohorteFamiliaCasoByCodigoCasa(codigoCasa);
            List<String> codigoLineal = new ArrayList<String>();
            for(ParticipanteCohorteFamiliaCaso participante : participantesCasos){
                //*1*1 al final de cada codigo indica que es una copia del estiquer y es tipo QRCode
                //El formato para los tipo QRCode es: <fechafif><fechaHoy>*<codigolab>*<codigocasa>*<copias>*<tipo>

                //*1*2 al final de cada codigo indica que es una copia del estiquer y es tipo Codabar
                //El formato para los tipo Codabar es: <codigoparticipante>*<copias>*<tipo>
                String[] edad = participante.getParticipante().getParticipante().getEdad().split("/");
                int anios = 0;
                int meses = 0;
                //int dias = 0;
                if (edad.length > 0) {
                    anios = Integer.valueOf(edad[0]);
                    meses = Integer.valueOf(edad[1]);
                    //dias = Integer.valueOf(edad[2]);
                }
                //Para los menores de 6 meses no se toma ningún tipo de muestra
                if (anios > 0 || (anios == 0 && meses >= 6)) {
                    String codigoLab = "";

                    Date fif = (participante.getFechaEnfermedad() != null ? participante.getFechaEnfermedad() : DateUtil.StringToDate("10/10/3000", "dd/MM/yyyy"));
                    if (etiquetas.contains("CHF_CAT_TIP_ETIQ_MI_04")) {
                        codigoLab = DateUtil.DateToString(fif, "dd/MM/yyyy") + DateUtil.DateToString(new Date(), "dd/MM/yyyy");
                        codigoLab += "  *" + participante.getParticipante().getParticipante().getCodigo() + "." + evento + ".TR1";
                        codigoLab += "*" + participante.getCodigoCaso().getCasa().getCodigoCHF() + "*1*1";
                        map.put("CODIGO" + indice, codigoLab);
                        indice++;
                    }
                    if (etiquetas.contains("CHF_CAT_TIP_ETIQ_MI_03")) {
                        codigoLab = DateUtil.DateToString(fif, "dd/MM/yyyy") + DateUtil.DateToString(new Date(), "dd/MM/yyyy");
                        codigoLab += "  *" + participante.getParticipante().getParticipante().getCodigo() + "." + evento + ".TRI";
                        codigoLab += "*" + participante.getCodigoCaso().getCasa().getCodigoCHF() + "*1*1";
                        map.put("CODIGO" + indice, codigoLab);
                        indice++;
                    }
                    if (etiquetas.contains("CHF_CAT_TIP_ETIQ_MI_02")) {
                        codigoLab = DateUtil.DateToString(fif, "dd/MM/yyyy") + DateUtil.DateToString(new Date(), "dd/MM/yyyy");
                        codigoLab += "  *" + participante.getParticipante().getParticipante().getCodigo() + "." + evento + ".TPI";
                        codigoLab += "*" + participante.getCodigoCaso().getCasa().getCodigoCHF() + "*1*1";
                        map.put("CODIGO" + indice, codigoLab);
                        indice++;
                    }
                    if (etiquetas.contains("CHF_CAT_TIP_ETIQ_MI_01")) {
                        codigoLab = participante.getParticipante().getParticipante().getCodigo() + "*1*2";
                        codigoLineal.add(codigoLab);
                    }

                }
            }
            for(String codigo : codigoLineal){
                map.put("CODIGO"+indice, codigo);
                indice++;
            }
        }
        map.put("TOTAL", String.valueOf(indice));
        return createJsonResponse(map);
    }

    private ResponseEntity<String> createJsonResponse( String mensaje )
    {
        Map<String, String> map = new HashMap<String, String>();
        map.put("mensaje", mensaje);
        Gson gson = new Gson();
        String json = gson.toJson(map);
        UnicodeEscaper escaper = UnicodeEscaper.above(127);
        json = escaper.translate(json);
        return new ResponseEntity<String>( json, HttpStatus.CREATED);
    }

    private ResponseEntity<String> createJsonResponse( Object o )
    {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");
        Gson gson = new Gson();
        String json = gson.toJson(o);
        UnicodeEscaper escaper = UnicodeEscaper.above(127);
        json = escaper.translate(json);
        return new ResponseEntity<String>( json, headers, HttpStatus.CREATED );
    }

}
