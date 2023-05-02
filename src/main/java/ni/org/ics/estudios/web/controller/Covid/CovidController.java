package ni.org.ics.estudios.web.controller.Covid;

import com.google.gson.Gson;
import ni.org.ics.estudios.domain.CartaConsentimiento;
import ni.org.ics.estudios.domain.Participante;
import ni.org.ics.estudios.domain.Retiros.Retiros;
import ni.org.ics.estudios.domain.cohortefamilia.CasaCohorteFamilia;
import ni.org.ics.estudios.domain.cohortefamilia.Muestra;
import ni.org.ics.estudios.domain.cohortefamilia.ParticipanteCohorteFamilia;
import ni.org.ics.estudios.domain.cohortefamilia.casos.ParticipanteCohorteFamiliaCaso;
import ni.org.ics.estudios.domain.covid19.CandidatoTransmisionCovid19;
import ni.org.ics.estudios.domain.covid19.CasoCovid19;
import ni.org.ics.estudios.domain.covid19.OtrosPositivosCovid;
import ni.org.ics.estudios.domain.covid19.ParticipanteCasoCovid19;
import ni.org.ics.estudios.domain.influenzauo1.ParticipanteCasoUO1;
import ni.org.ics.estudios.domain.muestreoanual.ParticipanteProcesos;
import ni.org.ics.estudios.dto.ParticipanteBusquedaDto;
import ni.org.ics.estudios.dto.ParticipantesEnCasa;
import ni.org.ics.estudios.dto.muestras.MxDto;
import ni.org.ics.estudios.language.MessageResource;
import ni.org.ics.estudios.service.CartaConsentimientoService;
import ni.org.ics.estudios.service.MessageResourceService;
import ni.org.ics.estudios.service.ParticipanteService;
import ni.org.ics.estudios.service.cohortefamilia.ParticipanteCohorteFamiliaService;
import ni.org.ics.estudios.service.cohortefamilia.casos.ParticipanteCohorteFamiliaCasoService;
import ni.org.ics.estudios.service.covid.CovidService;
import ni.org.ics.estudios.service.influenzauo1.ParticipanteCasoUO1Service;
import ni.org.ics.estudios.service.muestreoanual.ParticipanteProcesosService;
import ni.org.ics.estudios.service.retiro.RetiroService;
import ni.org.ics.estudios.service.scancarta.ScanCartaService;
import ni.org.ics.estudios.web.utils.DateUtil;
import ni.org.ics.estudios.web.utils.JsonUtil;
import ni.org.ics.estudios.web.utils.StringUtil;
import org.apache.commons.lang3.text.translate.UnicodeEscaper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.Resource;
import java.net.InetAddress;
import java.text.ParseException;
import java.util.*;
import java.util.regex.Pattern;

/**
 * Created by ICS on 01/06/2020.
 */

@Controller
@RequestMapping("/covid")
public class CovidController {
    private static final Logger logger = LoggerFactory.getLogger(CovidController.class);
    @Resource(name = "CovidService")
    private CovidService covidService;

    @Resource(name = "participanteService")
    private ParticipanteService participanteService;

    @Resource(name = "participanteProcesosService")
    private ParticipanteProcesosService participanteProcesosService;

    @Resource(name="cartaConsentimientoService")
    private CartaConsentimientoService cartaConsentimientoService;

    @Resource(name = "participanteCohorteFamiliaService")
    private ParticipanteCohorteFamiliaService participanteCohorteFamiliaService;

    @Resource(name = "scanCartaService")
    private ScanCartaService scanCartaService;

    @Resource(name = "participanteCohorteFamiliaCasoService")
    private ParticipanteCohorteFamiliaCasoService participanteCohorteFamiliaCasoService;

    @Resource(name = "participanteCasoUO1Service")
    private ParticipanteCasoUO1Service participanteCasoUO1Service;

    @Resource(name = "messageResourceService")
    private MessageResourceService messageResourceService;

    @Resource(name = "RetiroService")
    private RetiroService retiroService;

    /* Buscar Listado Covid Participante  getParticipantesCohorteFamiliaCasoByCodigoCaso */
    @RequestMapping(value = "/listCovid", method = RequestMethod.GET)
    public String FormCovid(Model model)throws Exception{
        List<ParticipanteCasoCovid19> casosCovid = covidService.getParticipanteCasosPositivosCovid();
        List<MessageResource> positivoPor = messageResourceService.getCatalogo("COVID_CAT_POSITIVO_POR");
        model.addAttribute("casosCovid",casosCovid);
        model.addAttribute("positivoPor",positivoPor);
        return "/casosCovid/list";
    }


    @RequestMapping(value = "SaveForm", method = RequestMethod.GET)
    public String SaveForm(Model model) throws Exception
    {
        try{
            List<MessageResource> positivoPor = messageResourceService.getCatalogo("COVID_CAT_POSITIVO_POR");
            model.addAttribute("positivoPor", positivoPor);
            model.addAttribute("agregando",true);
            model.addAttribute("editando",false);
            model.addAttribute("caso", new ParticipanteCasoCovid19());
            model.addAttribute("estudios", "");
            return "/casosCovid/saveForm";
        }
        catch (Exception e){
            logger.error(e.getMessage());
            throw e;
        }
    }

    @RequestMapping(value = "/editCase/{codigoCasoParticipante}", method = RequestMethod.GET)
    public String editCase(Model model, @PathVariable("codigoCasoParticipante") String codigoCasoParticipante) throws Exception
    {
        try{
            ParticipanteCasoCovid19 caso = this.covidService.getParticipanteCasoCovid19ByCodCasoPart(codigoCasoParticipante);
            ParticipanteProcesos procesos = this.participanteProcesosService.getParticipante(caso.getParticipante().getCodigo());
            List<MessageResource> positivoPor = messageResourceService.getCatalogo("COVID_CAT_POSITIVO_POR");
            model.addAttribute("positivoPor", positivoPor);
            model.addAttribute("agregando",false);
            model.addAttribute("editando",true);
            model.addAttribute("caso", caso);
            model.addAttribute("estudios", procesos.getEstudio());
            return "/casosCovid/saveForm";
        }
        catch (Exception e){
            logger.error(e.getMessage());
            throw e;
        }
    }

    @RequestMapping(value = "searchParticipant", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    ResponseEntity<String> buscarParticipante(@RequestParam(value="participantCode", required=true ) Integer codigo) throws ParseException {
        MessageResource intervalDias = messageResourceService.getMensaje("CAT_INTERVAL_DIAS_MX_30");
        Integer dias = Integer.parseInt(intervalDias.getSpanish());
        ParticipanteProcesos procesos = null;
        ParticipanteBusquedaDto participante = covidService.getDatosParticipanteByCodigo(codigo);
        if (participante!=null){
            procesos = this.participanteProcesosService.getParticipante(codigo);
            if (!participante.getSubEstudios().contains("2")) {
                List<CartaConsentimiento> cartaConsentimientos = this.cartaConsentimientoService.getCartaConsCovidCPByCodParticipante(codigo);
                for(CartaConsentimiento carta : cartaConsentimientos){
                    if (carta.getAceptaParteD().equalsIgnoreCase("0")) return JsonUtil.createJsonResponse("Participante no acepto particiar en el estudio, el día "+DateUtil.DateToString(carta.getFechaFirma(), "dd/MM/yyyy"));
                }
                participante.setValidacion("Participante aún no ha sido enrolado, recuerde llenar consentimiento");
            }

            ParticipanteCasoCovid19 participanteCaso = covidService.getParticipanteCasoCovid19Pos(codigo);
            if (participante.getEstudios().equalsIgnoreCase("Dengue"))
                return JsonUtil.createJsonResponse("Participante pertenece cohorte Dengue");
            if (participanteCaso!=null){
                if (participanteCaso.getCodigoCaso().getCasa()!=null)
                    return JsonUtil.createJsonResponse("Participante se encuentra activo como positivo en la casa: "+participanteCaso.getCodigoCaso().getCasa().getCodigoCHF());
                else
                    return JsonUtil.createJsonResponse("Participante se encuentra activo como positivo");
            }
            if (participante.getEstado().equals(0))
                return JsonUtil.createJsonResponse("Participante retirado");
        }else return JsonUtil.createJsonResponse("No se encontró participante según el código ingresado");

        String alerta="";
        if(procesos!=null){
            //validar convaleciente
            if (procesos.getConvalesciente().equalsIgnoreCase("Na")) {
                alerta+= "* Convaleciente con menos de 14 días. No tomar muestra. COMUNICAR AL SUPERVISOR\n ";
            } else if (procesos.getConvalesciente().equalsIgnoreCase("Si")) {
                alerta+= "* Convaleciente 14 días o más. COMUNICAR AL SUPERVISOR\n ";
            }

            if (procesos.getPosZika().matches("Si")) {
                alerta += "* Fue positivo a ZIKA <br> ";
            }

            if (procesos.getPosDengue() != null) {
                alerta += "* "+procesos.getPosDengue() + "<br> ";
            }

            if (procesos.getPosCovid() != null) {
                alerta += "* "+procesos.getPosCovid() + "<br> ";
            }
            List<MxDto> ultimoCasoIn30Day = this.covidService.getInforUltimoCasoIn30Day(codigo,dias);
            /*se recuperan datos se seguimientos activos. Familia, Influensa, covid*/
            if (procesos.getEstudio().contains("CH Familia")){
                List<ParticipanteCohorteFamiliaCaso> participanteCohorteFamiliaCasos = participanteCohorteFamiliaCasoService.getParticipanteCohorteFamiliaCasosByCasaPos(procesos.getCasaCHF());
                if (participanteCohorteFamiliaCasos.size()>0) {
                    MessageResource positivoPor = messageResourceService.getMensajeByCatalogAndCatKey("CHF_CAT_POSITIVO_POR", participanteCohorteFamiliaCasos.get(0).getPositivoPor());
                    alerta += "* Casa en seguimiento de Influenza - positivo por: "+(positivoPor != null ? positivoPor.getSpanish(): "-") +
                            " - Activación: "+DateUtil.CalcularDiferenciaDiasFechas(participanteCohorteFamiliaCasos.get(0).getCodigoCaso().getFechaInicio(), new Date()) + " Dias <br>";
                }
            }
            if (procesos.getEstudio().contains("UO1") || procesos.getEstudio().contains("Influenza")){
                ParticipanteCasoUO1 participanteCasoUO1 = participanteCasoUO1Service.getCasoActivoParticipante(procesos.getCodigo());
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

            ParticipanteCasoCovid19 participanteCasoCovid19 = covidService.getParticipanteCasoCovid19Pos(procesos.getCodigo());
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
                if (procesos.getCasaCHF() != null) {
                    CasoCovid19 casoCovid19 = covidService.getCasoCovid19ByCasaChf(procesos.getCasaCHF());
                    if (casoCovid19 != null) {
                        alerta += "* Casa en seguimiento de SARS" +
                                " - Activación: "+DateUtil.CalcularDiferenciaDiasFechas(casoCovid19.getFechaIngreso(), new Date()) + " Dias <br>";
                    } else {
                        CandidatoTransmisionCovid19 candidatoTransmisionCovid19 = covidService.getCandidatoTransmisionCovid19ByCasaChf(procesos.getCasaCHF());
                        if (candidatoTransmisionCovid19 != null) {
                            alerta += "* Casa en seguimiento de SARS" +
                                    " - consentimiento: "+ candidatoTransmisionCovid19.getConsentimiento();
                        }
                    }
                }
            }
        }
        participante.setAlertas(alerta);
        if(procesos.getEstudio().contains("CH Familia")){
            // chf_muestras
            List<MxDto> getallFamily = this.covidService.getCasaFamiliaEnCh_MuestraMinor30Days(procesos.getCasaCHF(), dias);
            participante.setChf_muestras(getallFamily);
            //muestras
            List<MxDto> getAllMuestra = this.covidService.getMuestraMenor30DaysByCasaFam(procesos.getCasaCHF(), dias);
            participante.setMuestras(getAllMuestra);
        }else{
            List<MxDto> muestras = this.covidService.getMuestrasTomaMinor30Days(codigo,dias);
            participante.setMuestras(muestras);

            List<MxDto> chf_muestras = this.covidService.getChfMuestraToma2Minor30Days(codigo,dias);
            participante.setChf_muestras(chf_muestras);
        }
        participante.setIntervalo(intervalDias.getSpanish());
        return JsonUtil.createJsonResponse(participante);
    }

    @RequestMapping( value="saveCaseCovid", method=RequestMethod.POST)
    public ResponseEntity<String> saveCaseCovid( @RequestParam(value="codigo", required=false, defaultValue="" ) String codigo
            , @RequestParam( value="codigoCasa", required=true ) String codigoCasa
            , @RequestParam( value="fechaInicio", required=false ) String fechaInicio
            , @RequestParam( value="codigoParticipante", required=false, defaultValue="" ) Integer codigoParticipante
            , @RequestParam( value="positivoPor", required=true, defaultValue="" ) String positivoPor
            , @RequestParam( value="fif", required=false, defaultValue="" ) String fif
            , @RequestParam( value="fis", required=true, defaultValue="" ) String fis
    )throws Exception{
        try{
            Date dFechaInicio = DateUtil.StringToDate(fechaInicio, "dd/MM/yyyy");
            CasoCovid19 casoCovid19 = null;
            CasoCovid19 casoByCasa = null;
            ParticipanteCasoCovid19 partCasoIndice = null;
            boolean fueIndice = false, fueMiembro = false;
             if (codigoCasa!=null) casoByCasa = this.covidService.getCasoCovid19ByCasaChf(codigoCasa);
            if (casoByCasa!=null){
                if (casoByCasa.getFechaIngreso().compareTo(dFechaInicio)!=0){
                    return JsonUtil.createJsonResponse("Ya existe un caso activo para esta casa con fecha de inicio: "+DateUtil.DateToString(casoByCasa.getFechaIngreso(),"dd/MM/yyyy"));
                }else {
                    casoCovid19 = casoByCasa;//es el mismo caso
                    codigo = casoCovid19.getCodigoCaso();
                }
            }else
                casoCovid19 = this.covidService.getCasoCovid19ByCodigo(codigo);//es caso pediatrica

            ParticipanteCasoCovid19 participanteCasoCovid = this.covidService.getParticipanteCasoCovid19ByCodigoAndCodCaso(codigoParticipante, codigo);
            ParticipanteProcesos procesos = this.participanteProcesosService.getParticipante(codigoParticipante);

            if (casoCovid19 == null && procesos.getEstudio().toLowerCase().contains("tcovid")){ //si es caso nuevo validar si el positivo tiene consentimiento de caso indice
                fueIndice = this.covidService.esParticipanteIndiceCasoCovidByCodigo(codigoParticipante);
                if (!fueIndice) return JsonUtil.createJsonResponse("Participante no tiene consentimiento de caso indice, debe registrarlo como candidato");
            }

            if (casoCovid19 == null){//if (casoCovid19 == null || participanteCasoCovid==null){
                casoCovid19 = new CasoCovid19();
                casoCovid19.setCodigoCaso(StringUtil.getCadenaAlfanumAleatoria(36,true));
                casoCovid19.setDeviceid("server");
                casoCovid19.setEstado('1');
                casoCovid19.setPasive('0');
                casoCovid19.setRecordDate(new Date());
                casoCovid19.setRecordUser(SecurityContextHolder.getContext().getAuthentication().getName());
                casoCovid19.setFechaInactivo(null);
                casoCovid19.setInactivo("0");
            }else{
                partCasoIndice = this.covidService.getParticipanteIndiceCasoCovid19ByCodigoCaso(casoCovid19.getCodigoCaso());

            }

            casoCovid19.setFechaIngreso(DateUtil.StringToDate(fechaInicio, "dd/MM/yyyy"));
            if ((participanteCasoCovid==null || casoCovid19.getCasa()!=null) && codigoCasa!=null && !codigoCasa.isEmpty()) {//caso nuevo o cambio de casa familia del participante
                CasaCohorteFamilia casaChFam = new CasaCohorteFamilia();
                casaChFam.setCodigoCHF(codigoCasa);
                casoCovid19.setCasa(casaChFam);
            }
            this.covidService.saveOrUpdateCasoCovid19(casoCovid19); // Aqui mando a guardar en la 1er tabla

            if (participanteCasoCovid==null) {
                participanteCasoCovid = new ParticipanteCasoCovid19();
                participanteCasoCovid.setCodigoCasoParticipante(StringUtil.getCadenaAlfanumAleatoria(36, true));
                participanteCasoCovid.setDeviceid("server");
                participanteCasoCovid.setEstado('1');
                participanteCasoCovid.setPasive('0');
                participanteCasoCovid.setRecordDate(new Date());
                participanteCasoCovid.setRecordUser(SecurityContextHolder.getContext().getAuthentication().getName());
            }
            if (procesos.getEstudio().toLowerCase().contains("tcovid")) {
                if (!partCasoIndice.getParticipante().getCodigo().equals(codigoParticipante))
                    participanteCasoCovid.setEnfermo("S");//solo el primero se registra como Indice
                else
                    participanteCasoCovid.setEnfermo("I");
            }
            else participanteCasoCovid.setEnfermo("S");

            if (procesos.getSubEstudios().contains("2"))
                participanteCasoCovid.setConsentimiento("1");//Consentido
            else
                participanteCasoCovid.setConsentimiento("2");//Pendiente

            participanteCasoCovid.setFis(DateUtil.StringToDate(fis,"dd/MM/yyyy"));
            participanteCasoCovid.setFif(DateUtil.StringToDate(fif,"dd/MM/yyyy"));
            participanteCasoCovid.setPositivoPor(positivoPor);
            participanteCasoCovid.setCodigoCaso(casoCovid19);
            Participante participante = participanteService.getParticipanteByCodigo(codigoParticipante);
            participanteCasoCovid.setParticipante(participante);
            this.covidService.saveOrUpdateParticipanteCasoCovid19(participanteCasoCovid);

            //Agregar resto de participantes de la casa
            List<ParticipanteCohorteFamilia> participantesCasa = participanteCohorteFamiliaService.getParticipantesCHFByCodigoCasa(codigoCasa);
            for(ParticipanteCohorteFamilia pchf : participantesCasa){
                if (!pchf.getParticipante().getCodigo().equals(codigoParticipante) && !pchf.getParticipante().getCodigo().equals(partCasoIndice.getParticipante().getCodigo())) { //omitir el positivo
                    fueMiembro = this.covidService.esParticipanteMiembroCasoCovidByCodigo(pchf.getParticipante().getCodigo());
                    participanteCasoCovid = this.covidService.getParticipanteCasoCovid19ByCodigoAndCodCaso(pchf.getParticipante().getCodigo(), codigo);
                    if (participanteCasoCovid == null) {
                        participanteCasoCovid = new ParticipanteCasoCovid19();
                        participanteCasoCovid.setCodigoCasoParticipante(StringUtil.getCadenaAlfanumAleatoria(36, true));
                        participanteCasoCovid.setDeviceid("server");
                        participanteCasoCovid.setEstado('1');
                        participanteCasoCovid.setPasive('0');
                        participanteCasoCovid.setRecordDate(new Date());
                        participanteCasoCovid.setRecordUser(SecurityContextHolder.getContext().getAuthentication().getName());
                        participanteCasoCovid.setEnfermo("N");
                        participanteCasoCovid.setParticipante(pchf.getParticipante());
                        participanteCasoCovid.setCodigoCaso(casoCovid19);
                        procesos = this.participanteProcesosService.getParticipante(codigoParticipante);
                        if (procesos != null && procesos.getSubEstudios().contains("2") && fueMiembro)  //solo si ya fue miembro ponerlo como consentido
                            participanteCasoCovid.setConsentimiento("1");//Consentido
                        else
                            participanteCasoCovid.setConsentimiento("2");//Pendiente
                        this.covidService.saveOrUpdateParticipanteCasoCovid19(participanteCasoCovid);
                    }
                }
            }

            return JsonUtil.createJsonResponse(casoCovid19);
        }catch (Exception e){
            logger.error(e.getMessage());
            Gson gson = new Gson();
            String json = gson.toJson(e.toString());
            return new ResponseEntity<String>( json, HttpStatus.CREATED);
        }
    }

    @RequestMapping(value = "/participants/{codigo}", method = RequestMethod.GET)
    public String obtenerParticipantesCaso(@PathVariable("codigo") String codigo, Model model) throws ParseException {
        logger.debug("Mostrando participantes de caso monitoreo intensivo en JSP");
        List<ParticipanteCasoCovid19> participantes = covidService.getParticipantesCasoCovid19ByCodigoCaso(codigo);
        model.addAttribute("casoCovid19",participantes.get(0).getCodigoCaso().getInactivo());
        List<MessageResource> positivoPor = messageResourceService.getCatalogo("COVID_CAT_POSITIVO_POR");
        model.addAttribute("participantes",participantes);
        model.addAttribute("caso", participantes.get(0).getCodigoCaso());
        model.addAttribute("positivoPor", positivoPor);
        return "casosCovid/participantesList";
    }

    private static String identificador;
    private static Integer medico_supervisor;
    private static Integer persona_documenta;
    private static Integer relacionFamiliar;

    @RequestMapping( value="closeCase", method=RequestMethod.POST)
    public ResponseEntity<String> cerrarCaso( @RequestParam(value="codigo", required=true ) String codigo
            , @RequestParam( value="fechaInactivo", required=true, defaultValue="" ) String fechaInactivo
            , @RequestParam( value="observacion", required=false, defaultValue="" ) String observacion) {
        try{
            CasoCovid19 casoExistente = this.covidService.getCasoCovid19ByCodigo(codigo);
            if (casoExistente!=null) {
                Date dFechaInactivo = DateUtil.StringToDate(fechaInactivo, "dd/MM/yyyy");
                //quitar vigencia a las extensiones de la carta o cartas de Tcovid de familia que puedan tener activas los participantes
                this.scanCartaService.quitarVigenciaCartaTCovid(casoExistente.getCodigoCaso(), dFechaInactivo);
                this.scanCartaService.quitarVigenciaExtensionTCovid(casoExistente.getCodigoCaso(), dFechaInactivo);
                casoExistente.setFechaInactivo(dFechaInactivo);
                casoExistente.setObservacion(observacion);
                //casoExistente.setRecordUser(SecurityContextHolder.getContext().getAuthentication().getName());
                //casoExistente.setRecordDate(new Date());
                casoExistente.setInactivo("1");
                this.covidService.saveOrUpdateCasoCovid19(casoExistente);

                /* Iniciar retiro del estudio de covid */
                List<MessageResource> meta = this.messageResourceService.getCatalogo("CAT_METADATOS_RETIRO_AUTOMATIC");
                identificador = meta.get(1).getSpanish();
                medico_supervisor = Integer.parseInt(meta.get(2).getSpanish());
                persona_documenta = Integer.parseInt(meta.get(3).getSpanish());
                relacionFamiliar = Integer.parseInt(meta.get(4).getSpanish());

                List<ParticipantesEnCasa> participantesEnCasas = this.covidService.getParticipantesByCasaFamiliaId(casoExistente.getCasa().getCodigoCHF());
                for (int i = 0; i < participantesEnCasas.size() ; i++) {
                ArrayList<String> arrayEstudios = new ArrayList<String>();
                    ParticipanteProcesos procesos = this.participanteProcesosService.getParticipante(participantesEnCasas.get(i).getIdParticipante());
                    if (procesos != null & procesos.getEstudio().contains("Tcovid")){

                        String[] arrayString = procesos.getEstudio().split("  ");
                        for (int j = 0; j < arrayString.length ; j++) {
                            arrayEstudios.add(arrayString[j]);
                            arrayEstudios.remove("Tcovid");
                        }
                        StringBuffer stringBuffer = new StringBuffer();
                        for (String s:arrayEstudios){
                            stringBuffer.append(s);
                            stringBuffer.append("  ");
                        }

                        //Actualiza el campo estudio
                        boolean result = this.covidService.ActualizarCampoEstudio(procesos.getCodigo(), stringBuffer.toString());
                        if(result){ // iniciar Guardar en documentacion_retiro_data
                            Retiros retiro = new Retiros();
                            retiro.setDeviceid(identificador);
                            retiro.setEstado('1');
                            retiro.setPasive('0');
                            retiro.setRecordDate(new Date());
                            retiro.setRecordUser(SecurityContextHolder.getContext().getAuthentication().getName());
                            retiro.setActual(true);
                            retiro.setCodigocasafamilia(procesos.getCasaCHF());
                            retiro.setCodigocasapdcs(casoExistente.getCasa().getCasa().getCodigo());
                            retiro.setDevolviocarnet('0');
                            retiro.setEstudioretirado("Tcovid");
                            retiro.setEstudiosanteriores(procesos.getEstudio());
                            retiro.setFecharetiro(dFechaInactivo);
                            retiro.setMedicosupervisor(medico_supervisor);
                            retiro.setMotivo("C2");
                            retiro.setObservaciones("RETIRADO POR TERMINAR PERIODO DEL ESTUDIO");
                            retiro.setOtrosmotivo("");
                            retiro.setPersonadocumenta(persona_documenta);
                            retiro.setQuiencomunica("Ninguno");
                            retiro.setRelfam(relacionFamiliar);
                            retiro.setTipofecha("1");
                            Participante participante = this.participanteService.getParticipanteByCodigo(procesos.getCodigo());
                            retiro.setParticipante(participante);
                            this.retiroService.SaveRetiros(retiro);
                        }
                    }
                }
            }
            return JsonUtil.createJsonResponse(casoExistente);
        } catch(Exception e){
            Gson gson = new Gson();
            String json = gson.toJson(e.toString());
            return new ResponseEntity<String>( json, HttpStatus.CREATED);
        }
    }

    @RequestMapping("/actions/{accion}/{codigo}")
    public String enableUser(@PathVariable("codigo") String codigo,
                             @PathVariable("accion") String accion, RedirectAttributes redirectAttributes) {
        String redirecTo="404";
        if (accion.matches("disable")){
            redirecTo = "redirect:/covid/listCovid/";
            redirectAttributes.addFlashAttribute("deshabilitado", true);

            //UserSistema usuarioActual = this.usuarioService.getUser(SecurityContextHolder.getContext().getAuthentication().getName());
            CasoCovid19 casaCasoExistente = this.covidService.getCasoCovid19ByCodigo(codigo);
            if(casaCasoExistente!=null){
                //casaCasoExistente.setRecordDate(new Date());
                //casaCasoExistente.setRecordUser(SecurityContextHolder.getContext().getAuthentication().getName());
                casaCasoExistente.setPasive('1');
                List<ParticipanteCasoCovid19> participanteCasoCovid19List = this.covidService.getParticipantesCasoCovid19ByCodigoCaso(codigo);
                this.covidService.saveOrUpdateCasoCovid19(casaCasoExistente);
                for(ParticipanteCasoCovid19 participante : participanteCasoCovid19List){
                    //participante.setRecordDate(new Date());
                    //participante.setRecordUser(SecurityContextHolder.getContext().getAuthentication().getName());
                    participante.setPasive('1');
                    this.covidService.saveOrUpdateParticipanteCasoCovid19(participante);
                }
            }
            else{
                redirecTo = "403";
            }
        } else if (accion.matches("disablepart")){
            ParticipanteCasoCovid19 participanteCasoCovid19 = this.covidService.getParticipanteCasoCovid19ByCodCasoPart(codigo);
            //participanteCasoCovid19.setRecordDate(new Date());
            //participanteCasoCovid19.setRecordUser(SecurityContextHolder.getContext().getAuthentication().getName());
            participanteCasoCovid19.setPasive('1');
            this.covidService.saveOrUpdateParticipanteCasoCovid19(participanteCasoCovid19);
            redirectAttributes.addFlashAttribute("participante", participanteCasoCovid19.getParticipante().getCodigo());
            redirectAttributes.addFlashAttribute("deshabilitado", true);
            redirecTo = "redirect:/covid/participants/"+participanteCasoCovid19.getCodigoCaso().getCodigoCaso();
        }
        else{
            return redirecTo;
        }
        return redirecTo;
    }




    ///covid/otrosPositivosCovid/00000000-75b3-19f8-ffff-ffff9d4bc42b*8152
    @RequestMapping(value = "/otrosPositivosCovid/{codigoCaso}/{idparticpante}", method = RequestMethod.GET)
    public String otrosPositivosCovid(Model model, @PathVariable("codigoCaso") String codigoCaso,
                                      @PathVariable("idparticpante") String idparticpante) throws Exception
    {
        try{
           /* String string = codigoCaso;
            String separador = Pattern.quote("*");
            String[] parts = string.split(separador);
            String part1 = parts[0]; // codigoCaso
            String part2 = parts[1]; // codigo_participante
            List<ParticipanteCasoCovid19> participantes = covidService.getParticipantesCasoCovid19ByCodigoCaso(codigoCaso);            */
            Integer codigo_participante = Integer.parseInt( idparticpante );


            CasoCovid19 casoCovid19 = this.covidService.getCasoCovid19ByCodigo(codigoCaso); // aqui obtengo la fecha de ingreso de la tabla caso covid
            model.addAttribute("casoCovid19",casoCovid19);

            String fechaIngreso = DateUtil.DateToString(casoCovid19.getFechaIngreso(), "dd/MM/yyyy");
                Date finicio = DateUtil.StringToDate(fechaIngreso, "dd/MM/yyyy");
                Date ffinal = DateUtil.StringToDate(fechaIngreso + " 23:59:59", "dd/MM/yyyy HH:mm:ss");
                model.addAttribute("casoCovid19", casoCovid19);
            ParticipanteCasoCovid19 participanteCasoCovid19 = this.covidService.getParticipanteIndiceCasoCovid19ByCodigoCaso(codigoCaso); // si es caso Indice de la tabla covid_participante_transmision

            if (participanteCasoCovid19 != null) {

                CandidatoTransmisionCovid19 indice = this.covidService.getIdCandidatoTransmisionCovid19(finicio,ffinal,participanteCasoCovid19.getParticipante().getCodigo(),casoCovid19.getCasa().getCodigoCHF());
                model.addAttribute("indice", indice);


                ParticipanteProcesos procesos = this.participanteProcesosService.getParticipante(codigo_participante);
                model.addAttribute("procesos", procesos);


                List<MessageResource> positivoPor = messageResourceService.getCatalogo("COVID_CAT_POSITIVO_POR");
                model.addAttribute("positivoPor", positivoPor);

                model.addAttribute("editando", false);
                return "casosCovid/OtrosPositivosPorIndice";
            }else{
                return "404";
            }
        }
        catch (Exception e){
            logger.error(e.getMessage());
            throw e;
        }
    }

    // Guardar Otro Positivos /covid/saveOtrosPositivosTCovid
    @RequestMapping( value="saveOtrosPositivosTCovid", method=RequestMethod.POST)
    public ResponseEntity<String> saveOtrosPositivosTCovid(@RequestParam( value = "casoIndice",required = true ) String casoIndice
            , @RequestParam( value = "idparticipante"       ,required = true  ) Integer idparticipante
            , @RequestParam( value = "estudio"              ,required = true  ) String estudio
            , @RequestParam( value = "positivoPor"          ,required = true  , defaultValue = "" ) String positivoPor
            , @RequestParam( value = "fis"                  ,required = true  , defaultValue = "" ) String fis
            , @RequestParam( value = "fif"                  ,required = false , defaultValue = "" ) String fif
            , @RequestParam( value = "editando"             ,required = false , defaultValue = "" ) String editando
            , @RequestParam( value = "casaChf"              ,required = true ) String casaChf
            , @RequestParam( value = "fecha_ingreso"        ,required = true ) String fecha_ingreso
            , @RequestParam( value = "CodigoCaso"           ,required = true ) String CodigoCaso
    )throws Exception{
        try{
                Date fecha_ingreso_inicio = DateUtil.StringToDate(fecha_ingreso, "dd/MM/yyyy");
                Date fecha_ingreso_final = DateUtil.StringToDate(fecha_ingreso + " 23:59:59", "dd/MM/yyyy HH:mm:ss");
                // ** obtengo el caso indice **
                CandidatoTransmisionCovid19 casitoIndice = this.covidService.getByIdCasoIndice(casoIndice);
                if (!this.covidService.verificaSiExiste(idparticipante, fecha_ingreso_inicio, fecha_ingreso_final)){
                    CandidatoTransmisionCovid19 candidatoTransmisionCovid19 = this.covidService.getCandidatoTransmisionCovid19(casoIndice);
                    if (!this.covidService.verificaCasoYaExiste(casoIndice,idparticipante)) {
                        OtrosPositivosCovid otros = new OtrosPositivosCovid();
                        otros.setCandidatoTransmisionCovid19(candidatoTransmisionCovid19);
                        otros.setCodigo_participante(idparticipante);
                        otros.setEstActuales(estudio);
                        otros.setCasaCHF(casaChf);
                        otros.setPositivoPor(positivoPor);
                        otros.setFis(DateUtil.StringToDate(fis, "dd/MM/yyyy"));
                        otros.setFif(DateUtil.StringToDate(fif, "dd/MM/yyyy"));
                        String nameComputer = "NicaUmich2";
                        otros.setDeviceid(nameComputer);
                        otros.setEstado('1');
                        otros.setPasive('0');
                        otros.setRecordDate(new Date());
                        otros.setRecordUser(SecurityContextHolder.getContext().getAuthentication().getName());
                        this.covidService.saveOrUpdateOtrosPositivos(otros);

                        ParticipanteCasoCovid19 covid_participante_Caso = this.covidService.getByIdAndParticipanteId(CodigoCaso, idparticipante);//obtengo el participante no enfermo
                        if (covid_participante_Caso != null){
                            covid_participante_Caso.setEnfermo("S");
                            covid_participante_Caso.setPositivoPor(positivoPor);
                            covid_participante_Caso.setFis(DateUtil.StringToDate(fis, "dd/MM/yyyy"));
                            covid_participante_Caso.setFif(DateUtil.StringToDate(fif, "dd/MM/yyyy"));
                            this.covidService.saveOrUpdateParticipanteCasoCovid19(covid_participante_Caso);
                        }
                        return JsonUtilcreateJsonResponse(otros);
                    }else{
                        Map<String, String> map = new HashMap<String, String>();
                        map.put("msj", "Registro ya existe!\n".concat("codigo del Caso Indice: ").concat(""+casitoIndice.getParticipante().getCodigo()));
                        return JsonUtilcreateJsonResponse(map);
                    }
                }else{
                    Map<String, String> map = new HashMap<String, String>();
                    map.put("msj", "Registro ya existe con Fecha de Ingreso del: ".concat(fecha_ingreso));
                    return JsonUtilcreateJsonResponse(map);
                }

        }catch (Exception ex){
            Gson gson = new Gson();
            String json = gson.toJson(ex.toString());
            return new ResponseEntity<String>( json, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }



    private ResponseEntity<String> JsonUtilcreateJsonResponse( Object o )
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
