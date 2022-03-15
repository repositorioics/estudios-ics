package ni.org.ics.estudios.web.controller.Covid;

import com.google.gson.Gson;
import ni.org.ics.estudios.domain.CartaConsentimiento;
import ni.org.ics.estudios.domain.Participante;
import ni.org.ics.estudios.domain.covid19.CandidatoTransmisionCovid19;
import ni.org.ics.estudios.domain.covid19.OtrosPositivosCovid;
import ni.org.ics.estudios.domain.covid19.ParticipanteCasoCovid19;
import ni.org.ics.estudios.domain.muestreoanual.ParticipanteProcesos;
import ni.org.ics.estudios.dto.ParticipanteBusquedaDto;
import ni.org.ics.estudios.language.MessageResource;
import ni.org.ics.estudios.service.CartaConsentimientoService;
import ni.org.ics.estudios.service.MessageResourceService;
import ni.org.ics.estudios.service.ParticipanteService;
import ni.org.ics.estudios.service.covid.CovidService;
import ni.org.ics.estudios.service.muestreoanual.ParticipanteProcesosService;
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
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by ICS on 01/06/2020.
 */

@Controller
@RequestMapping("/super/covid/")
public class CovidCandidatoTransController {
    private static final Logger logger = LoggerFactory.getLogger(CovidCandidatoTransController.class);
    @Resource(name = "CovidService")
    private CovidService covidService;

    @Resource(name = "participanteService")
    private ParticipanteService participanteService;

    @Resource(name = "participanteProcesosService")
    private ParticipanteProcesosService participanteProcesosService;

    @Resource(name="cartaConsentimientoService")
    private CartaConsentimientoService cartaConsentimientoService;

    @Resource(name = "messageResourceService")
    private MessageResourceService messageResourceService;

    /* Mostrar Listado de candidatos */
    @RequestMapping(value = "/listCandidates", method = RequestMethod.GET)
    public String FormCovid(Model model)throws Exception{
        List<CandidatoTransmisionCovid19> candidatos = covidService.getCandidatosTransmisionCovid19();
        List<MessageResource> positivoPor = messageResourceService.getCatalogo("COVID_CAT_POSITIVO_POR");
        model.addAttribute("candidatos",candidatos);
        model.addAttribute("positivoPor",positivoPor);
        return "/supervisor/candidatosTCovid/list";
    }


    @RequestMapping(value = "saveCandidateForm", method = RequestMethod.GET)
    public String saveCandidateForm(Model model) throws Exception
    {
        try{
            List<MessageResource> positivoPor = messageResourceService.getCatalogo("COVID_CAT_POSITIVO_POR");
            model.addAttribute("positivoPor", positivoPor);
            model.addAttribute("agregando",true);
            model.addAttribute("editando",false);
            model.addAttribute("candidato", new CandidatoTransmisionCovid19());
            model.addAttribute("estudios", "");
            return "/supervisor/candidatosTCovid/saveForm";
        }
        catch (Exception e){
            logger.error(e.getMessage());
            throw e;
        }
    }
    //candidatos transmision covid como Indice
    @RequestMapping( value="saveCandidateTCovid", method=RequestMethod.POST)
    public ResponseEntity<String> saveCandidateTCovid( @RequestParam(value="codigo", required=false, defaultValue="" ) String codigo
            , @RequestParam( value = "casaCHF"              , required = true ) String casaCHF
            , @RequestParam( value = "codigoParticipante"   , required = false , defaultValue = "" ) Integer codigoParticipante
            , @RequestParam( value = "positivoPor"          , required = true  , defaultValue = "" ) String positivoPor
            , @RequestParam( value = "chkCasoIndice"        , required = false , defaultValue = "" ) String chkCasoIndice
            , @RequestParam( value = "fechaIngreso"         , required = true  , defaultValue = "" ) String fechaIngreso
            , @RequestParam( value = "chkMasPositvo"        , required = false , defaultValue = "") String chkMasPositvo
            , @RequestParam( value = "fif"                  , required = false , defaultValue = "" ) String fif
            , @RequestParam( value = "fis"                  , required = true  , defaultValue = "" ) String fis
    )throws Exception{
        try {
            Character indice = (chkCasoIndice.equals("on") ? '1' : '0');
            Date finicio = DateUtil.StringToDate(fechaIngreso, "dd/MM/yyyy");
            Date ffinal = DateUtil.StringToDate(fechaIngreso + " 23:59:59", "dd/MM/yyyy HH:mm:ss");
            CandidatoTransmisionCovid19 candidatoTransmisionCovid19 = this.covidService.getCandidatoTransmisionCovid19(codigo);
            if (candidatoTransmisionCovid19 == null) {
                int result = this.covidService.existeIndice(casaCHF, finicio, ffinal);
                if (result > 0){
                    return JsonUtil.createJsonResponse("YA EXISTE UN CASO INDICE PARA ESTA CASA: ");
                }else if (result == 0 && chkCasoIndice.equals("")) {
                    System.out.printf("INGRESA EL CASO INDICE PARA ESTA CASA ");
                    return JsonUtil.createJsonResponse("INGRESA EL CASO INDICE PARA ESTA CASA ");
                } else if (result > 0 && chkCasoIndice.equals("on")){
                    return JsonUtil.createJsonResponse("YA EXISTE UN CASO INDICE PARA ESTA CASA: ".concat(casaCHF));
                }else {}

                candidatoTransmisionCovid19 = new CandidatoTransmisionCovid19();
                candidatoTransmisionCovid19.setCodigo(StringUtil.getCadenaAlfanumAleatoria(36, true));
                candidatoTransmisionCovid19.setDeviceid("server");
                candidatoTransmisionCovid19.setEstado('1');
                candidatoTransmisionCovid19.setPasive('0');
                candidatoTransmisionCovid19.setRecordDate(new Date());
                candidatoTransmisionCovid19.setRecordUser(SecurityContextHolder.getContext().getAuthentication().getName());
                candidatoTransmisionCovid19.setCasaCHF(casaCHF);
                candidatoTransmisionCovid19.setConsentimiento("PENDIENTE");
            }

            ParticipanteProcesos procesos = this.participanteProcesosService.getParticipante(codigoParticipante);
            if (procesos != null)
                candidatoTransmisionCovid19.setEstActuales(procesos.getEstudio());

            candidatoTransmisionCovid19.setFis(DateUtil.StringToDate(fis, "dd/MM/yyyy"));
            candidatoTransmisionCovid19.setFif(DateUtil.StringToDate(fif, "dd/MM/yyyy"));
            candidatoTransmisionCovid19.setFechaIngreso(DateUtil.StringToDate(fechaIngreso, "dd/MM/yyyy"));
            candidatoTransmisionCovid19.setPositivoPor(positivoPor);
            Participante participante = participanteService.getParticipanteByCodigo(codigoParticipante);
            candidatoTransmisionCovid19.setParticipante(participante);
            boolean mas = chkMasPositvo.equals("on")? true : false;
            candidatoTransmisionCovid19.setTienemaspositivos(mas);
            candidatoTransmisionCovid19.setIndice(indice);
            this.covidService.saveOrUpdateCandidatoTransmisionCovid19(candidatoTransmisionCovid19);
            return JsonUtil.createJsonResponse(candidatoTransmisionCovid19);
        }catch (Exception e){
            Gson gson = new Gson();
            String json = gson.toJson(e.toString());
            return new ResponseEntity<String>( json, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "searchParticipant", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    ResponseEntity<String> buscarParticipante(@RequestParam(value="participantCode", required=true ) Integer codigo) throws ParseException {
        ParticipanteBusquedaDto participante = covidService.getDatosParticipanteByCodigo(codigo);
        if (participante!=null){
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
            if (!participante.getEstudios().contains("CH Familia"))
                return JsonUtil.createJsonResponse("Participante no pertenece Cohorte Familia");
            if (participanteCaso!=null){
                if (participanteCaso.getCodigoCaso().getCasa()!=null)
                    return JsonUtil.createJsonResponse("Participante se encuentra activo como positivo en la casa: "+participanteCaso.getCodigoCaso().getCasa().getCodigoCHF());
                else
                    return JsonUtil.createJsonResponse("Participante se encuentra activo como positivo");
            }
            boolean casoCovid19 = this.covidService.getCasoEsActivo(participante.getCasaFamilia());
            if (casoCovid19)
                return JsonUtil.createJsonResponse("Participante activo en Casa de Familia: ".concat(participante.getCasaFamilia()));
            if (participante.getEstado().equals(0))
                return JsonUtil.createJsonResponse("Participante retirado");
        }else return JsonUtil.createJsonResponse("No se encontró participante según el código ingresado");
        return JsonUtil.createJsonResponse(participante);
    }

    @RequestMapping(value = "/editCandidate/{codigo}", method = RequestMethod.GET)
    public String editCase(Model model, @PathVariable("codigo") String codigo) throws Exception
    {
        try{
            CandidatoTransmisionCovid19 candidatoTransmisionCovid19 = this.covidService.getCandidatoTransmisionCovid19(codigo);
            boolean ind;
            if(candidatoTransmisionCovid19.getIndice() == null){
                ind = false;
            }else if (candidatoTransmisionCovid19.getIndice() == '0') {
                ind = false;
            } else{ ind =  true;
            }
            model.addAttribute("ind", ind);
            List<MessageResource> positivoPor = messageResourceService.getCatalogo("COVID_CAT_POSITIVO_POR");
            model.addAttribute("positivoPor", positivoPor);
            model.addAttribute("agregando",false);
            model.addAttribute("editando",true);
            model.addAttribute("candidato", candidatoTransmisionCovid19);
            model.addAttribute("estudios", candidatoTransmisionCovid19.getEstActuales());
            return "/supervisor/candidatosTCovid/saveForm";
        }
        catch (Exception e){
            logger.error(e.getMessage());
           return "404";
        }
    }

    @RequestMapping("/candidate/actions/{accion}/{codigo}")
    public String enableUser(@PathVariable("codigo") String codigo,
                             @PathVariable("accion") String accion, RedirectAttributes redirectAttributes) {
        String redirecTo="404";
        if (accion.matches("disable")){
            redirecTo = "redirect:/super/covid/listCandidates/";
            redirectAttributes.addFlashAttribute("deshabilitado", true);
            CandidatoTransmisionCovid19 casaCasoExistente = this.covidService.getCandidatoTransmisionCovid19(codigo);
            if(casaCasoExistente!=null){
                casaCasoExistente.setPasive('1');
                this.covidService.saveOrUpdateCandidatoTransmisionCovid19(casaCasoExistente);
            }
            else{
                redirecTo = "403";
            }
        }
        else{
            return redirecTo;
        }

        return redirecTo;
    }
//region MÉTODOS PARA OTROS POSITIVOS COVID

    // Editar Caso editOtherPositivo
    @RequestMapping(value = "/editOtroPositivo/{codigo}", method = RequestMethod.GET)
    public String editOtroPositivo(Model model, @PathVariable("codigo") Long codigo) throws Exception
    {
        try{
            OtrosPositivosCovid otroTransmisionCovid19 = this.covidService.getOtrosPositivoTransmisionCovid19(codigo);
            List<MessageResource> positivoPor = messageResourceService.getCatalogo("COVID_CAT_POSITIVO_POR");
            model.addAttribute("positivoPor", positivoPor);
            model.addAttribute("agregando",false);
            model.addAttribute("editando",true);
            CandidatoTransmisionCovid19 candidatoTransmisionCovid19 = this.covidService.getCandidatoTransmisionCovid19(otroTransmisionCovid19.getCandidatoTransmisionCovid19().getCodigo());
            model.addAttribute("candidato", candidatoTransmisionCovid19);
            model.addAttribute("caso", otroTransmisionCovid19);
            model.addAttribute("estudiosedit", otroTransmisionCovid19.getEstActuales());
            model.addAttribute("casachf", otroTransmisionCovid19.getCasaCHF());
            List<OtrosPositivosCovid> listadoOtrosPositivos = this.covidService.ObtenerOtrosPositivos();
            model.addAttribute("listadoOtrosPositivos", listadoOtrosPositivos);
            return "casosCovid/OtrosPositivosPorIndice";
        }
        catch (Exception e){
            logger.error(e.getMessage());
           return "404";
        }
    }

    // super/covid/saveOtrosCandidateTCovid /super/covid/otherPositive
    @RequestMapping( value="saveOtrosCandidateTCovid", method=RequestMethod.POST)
    public ResponseEntity<String> saveOtrosCandidateTCovid(@RequestParam( value = "casoIndice",required = true, defaultValue = "" ) String casoIndice
            , @RequestParam( value = "idparticipante"       ,required = true  , defaultValue = "" ) Integer idparticipante
            , @RequestParam( value = "estudio"              ,required = true  , defaultValue = "") String estudio
            , @RequestParam( value = "positivoPor"          ,required = true  , defaultValue = "" ) String positivoPor
            , @RequestParam( value = "fis"                  ,required = true  , defaultValue = "" ) String fis
            , @RequestParam( value = "fif"                  ,required = false , defaultValue = "" ) String fif
            , @RequestParam( value = "covid_participantes_casos" ,required = false , defaultValue = "" ) Long covid_participantes_casos
            , @RequestParam( value = "editando"             ,required = false , defaultValue = "" ) String editando
            , @RequestParam( value = "casaChf"              ,required = true ) String casaChf
            , @RequestParam( value = "fecha_ingreso"        ,required = true ) String fecha_ingreso
    )throws Exception{
        try{
            if (editando.equals("true")){
                OtrosPositivosCovid Edit_otro = new OtrosPositivosCovid();
                Edit_otro.setCodigo(covid_participantes_casos);
                CandidatoTransmisionCovid19 candidatoTransmisionCovid19 = this.covidService.getCandidatoTransmisionCovid19(casoIndice);
                Edit_otro.setCandidatoTransmisionCovid19(candidatoTransmisionCovid19);
                Edit_otro.setCodigo_participante(idparticipante);
                Edit_otro.setEstActuales(estudio);
                Edit_otro.setCasaCHF(casaChf);
                Edit_otro.setPositivoPor(positivoPor);
                Edit_otro.setFis(DateUtil.StringToDate(fis, "dd/MM/yyyy"));
                Edit_otro.setFif(DateUtil.StringToDate(fif, "dd/MM/yyyy"));
                String nameComputer = "NicaUmich2";
                Edit_otro.setDeviceid(nameComputer);
                Edit_otro.setEstado('1');
                Edit_otro.setPasive('0');
                Edit_otro.setRecordDate(new Date());
                Edit_otro.setRecordUser(SecurityContextHolder.getContext().getAuthentication().getName());
                this.covidService.saveOrUpdateOtrosPositivos(Edit_otro);
                return JsonUtil.createJsonResponse(Edit_otro);
            }else {
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
                        return JsonUtil.createJsonResponse(otros);
                    }else{
                        Map<String, String> map = new HashMap<String, String>();
                        map.put("msj", "Registro ya existe!\n".concat("codigo del Caso Indice: ").concat(""+casitoIndice.getParticipante().getCodigo()));
                        return createJsonResponse(map);
                    }
                }else{
                    Map<String, String> map = new HashMap<String, String>();
                    map.put("msj", "Registro ya existe con Fecha de Ingreso del: ".concat(fecha_ingreso));
                    return createJsonResponse(map);
                }
            }
        }catch (Exception ex){
            Gson gson = new Gson();
            String json = gson.toJson(ex.toString());
            return new ResponseEntity<String>( json, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //God Job super/covid/otherPositive/
    @RequestMapping(value = "/setPasive", method = RequestMethod.POST)
    public ResponseEntity<String> setPasive(@RequestParam("codigo") int codigo) {
        if (codigo >0){
            Long id = (long) codigo;
            OtrosPositivosCovid casaCasoExistente = this.covidService.getOtrosPositivoTransmisionCovid19(id);
            if(casaCasoExistente!=null){
                casaCasoExistente.setPasive('1');
                this.covidService.saveOrUpdateOtrosPositivos(casaCasoExistente);
                return createJsonResponse(casaCasoExistente);
            }else{
                Map<String, String> map = new HashMap<String, String>();
                map.put("msj", "No se pudo Desactivar!");
                return createJsonResponse(map);
            }
        }else{
            Map<String, String> map = new HashMap<String, String>();
            map.put("msj", "Registro no Encontrado!");
            return createJsonResponse(map);
        }
    }

    /* Formulario Otros Posivos cuando se guarda el Caso Indice */
    @RequestMapping(value = "/detailsOPositivos/{codigo}", method = RequestMethod.GET)
    public String detailsOPositivos(Model model, @PathVariable("codigo") String codigo)throws Exception {
        CandidatoTransmisionCovid19 indice = this.covidService.getByIdCasoIndice(codigo);
        model.addAttribute("indice",indice);
        if (indice != null){
            List<ParticipanteProcesos> procesos = this.covidService.obtenerParticipanteByCasaCHF(indice.getCasaCHF());
            if (procesos != null)
                model.addAttribute("procesos",procesos);
        }
        List<MessageResource> positivoPor = messageResourceService.getCatalogo("COVID_CAT_POSITIVO_POR");
        model.addAttribute("positivoPor",positivoPor);
        model.addAttribute("editando",false);
        model.addAttribute("caso", new OtrosPositivosCovid());
        return "/supervisor/candidatosTCovid/detallesOtrosPositivos";
    }

     /* Page CRUD Otros Positivos */
    @RequestMapping(value = "/otherPositive", method = RequestMethod.GET)
    public String otherPsotive(Model model)throws Exception{
        List<OtrosPositivosCovid> ListOtrosPositivos = covidService.ObtenerOtrosPositivos();
        model.addAttribute("ListOtrosPositivos",ListOtrosPositivos);
        List<MessageResource> positivoPor = messageResourceService.getCatalogo("COVID_CAT_POSITIVO_POR");
        model.addAttribute("positivoPor",positivoPor);
        model.addAttribute("editando",false);
        model.addAttribute("caso", new OtrosPositivosCovid());
        return "/supervisor/candidatosTCovid/otrosPositivos";
    }

    // Editar Caso
    @RequestMapping(value = "/editOtherPositivo/{codigo}", method = RequestMethod.GET)
    public String editOtherPositivo(Model model, @PathVariable("codigo") Long codigo) throws Exception
    {
        try{
            List<MessageResource> positivoPor = messageResourceService.getCatalogo("COVID_CAT_POSITIVO_POR");
            model.addAttribute("positivoPor", positivoPor);
            model.addAttribute("editando",true);
            OtrosPositivosCovid otroTransmisionCovid19 = this.covidService.getOtrosPositivoTransmisionCovid19(codigo);
            model.addAttribute("caso", otroTransmisionCovid19);
            model.addAttribute("casachf", otroTransmisionCovid19.getCasaCHF());
            List<OtrosPositivosCovid> ListOtrosPositivos = covidService.ObtenerOtrosPositivos();
            model.addAttribute("ListOtrosPositivos",ListOtrosPositivos);
            return "/supervisor/candidatosTCovid/otrosPositivos";
        }
        catch (Exception e){
            logger.error(e.getMessage());
            return "404";
        }
    }
//endregion

    /*  Esta Funcion retorna un Json  */
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