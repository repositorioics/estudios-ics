package ni.org.ics.estudios.web.controller.Covid;

import com.google.gson.Gson;
import ni.org.ics.estudios.domain.CartaConsentimiento;
import ni.org.ics.estudios.domain.Participante;
import ni.org.ics.estudios.domain.cohortefamilia.CasaCohorteFamilia;
import ni.org.ics.estudios.domain.covid19.CasoCovid19;
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
import java.text.ParseException;
import java.util.Date;
import java.util.List;

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

    @Resource(name = "messageResourceService")
    private MessageResourceService messageResourceService;

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
            if (participanteCaso!=null){
                if (participanteCaso.getCodigoCaso().getCasa()!=null)
                    return JsonUtil.createJsonResponse("Participante se encuentra activo como positivo en la casa: "+participanteCaso.getCodigoCaso().getCasa().getCodigoCHF());
                else
                    return JsonUtil.createJsonResponse("Participante se encuentra activo como positivo");
            }
            if (participante.getEstado().equals(0))
                return JsonUtil.createJsonResponse("Participante retirado");
        }else return JsonUtil.createJsonResponse("No se encontró participante según el código ingresado");
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
            CasoCovid19 covid_casos = this.covidService.getCasoCovid19ByCodigo(codigo);
            ParticipanteCasoCovid19 participanteCovid = this.covidService.getParticipanteCasoCovid19ByCodigoAndCodCaso(codigoParticipante, codigo);
            if (covid_casos == null || participanteCovid==null){
                covid_casos = new CasoCovid19();
                covid_casos.setCodigoCaso(StringUtil.getCadenaAlfanumAleatoria(36,true));
                covid_casos.setDeviceid("server");
                covid_casos.setEstado('1');
                covid_casos.setPasive('0');
                covid_casos.setRecordDate(new Date());
                covid_casos.setRecordUser(SecurityContextHolder.getContext().getAuthentication().getName());
                covid_casos.setFechaInactivo(null);
                covid_casos.setInactivo("0");
            }

            covid_casos.setFechaIngreso(DateUtil.StringToDate(fechaInicio,"dd/MM/yyyy"));
            if ((participanteCovid==null || covid_casos.getCasa()!=null) && codigoCasa!=null && !codigoCasa.isEmpty()) {//caso nuevo o cambio de casa familia del participante
                CasaCohorteFamilia casaChFam = new CasaCohorteFamilia();
                casaChFam.setCodigoCHF(codigoCasa);
                covid_casos.setCasa(casaChFam);
            }
            this.covidService.saveOrUpdateCasoCovid19(covid_casos); // Aqui mando a guardar en la 1er tabla

            if (participanteCovid==null) {
                participanteCovid = new ParticipanteCasoCovid19();
                participanteCovid.setCodigoCasoParticipante(StringUtil.getCadenaAlfanumAleatoria(36, true));
                participanteCovid.setDeviceid("server");
                participanteCovid.setEstado('1');
                participanteCovid.setPasive('0');
                participanteCovid.setRecordDate(new Date());
                participanteCovid.setRecordUser(SecurityContextHolder.getContext().getAuthentication().getName());
                participanteCovid.setEnfermo("S");

            }

            ParticipanteProcesos procesos = this.participanteProcesosService.getParticipante(codigoParticipante);
            if (procesos!=null && procesos.getSubEstudios().contains("2"))
                participanteCovid.setConsentimiento("1");//Consentido
            else
                participanteCovid.setConsentimiento("2");//Pendiente

            participanteCovid.setFis(DateUtil.StringToDate(fis,"dd/MM/yyyy"));
            participanteCovid.setFif(DateUtil.StringToDate(fif,"dd/MM/yyyy"));
            participanteCovid.setPositivoPor(positivoPor);
            participanteCovid.setCodigoCaso(covid_casos);
            Participante participante = participanteService.getParticipanteByCodigo(codigoParticipante);
            participanteCovid.setParticipante(participante);
            this.covidService.saveOrUpdateParticipanteCasoCovid19(participanteCovid);

            return JsonUtil.createJsonResponse(covid_casos);
        }catch (Exception e){
            Gson gson = new Gson();
            String json = gson.toJson(e.toString());
            return new ResponseEntity<String>( json, HttpStatus.CREATED);
        }
    }

    @RequestMapping(value = "/participants/{codigo}", method = RequestMethod.GET)
    public String obtenerParticipantesCaso(@PathVariable("codigo") String codigo, Model model) throws ParseException {
        logger.debug("Mostrando participantes de caso monitoreo intensivo en JSP");
        List<ParticipanteCasoCovid19> participantes = covidService.getParticipantesCasoCovid19ByCodigoCaso(codigo);
        List<MessageResource> positivoPor = messageResourceService.getCatalogo("COVID_CAT_POSITIVO_POR");
        model.addAttribute("participantes",participantes);
        model.addAttribute("caso", participantes.get(0).getCodigoCaso());
        model.addAttribute("positivoPor", positivoPor);
        return "casosCovid/participantesList";
    }

    @RequestMapping( value="closeCase", method=RequestMethod.POST)
    public ResponseEntity<String> cerrarCaso( @RequestParam(value="codigo", required=true ) String codigo
            , @RequestParam( value="fechaInactivo", required=true, defaultValue="" ) String fechaInactivo
    )
    {
        try{
            CasoCovid19 casoExistente = this.covidService.getCasoCovid19ByCodigo(codigo);
            if (casoExistente!=null) {
                casoExistente.setFechaInactivo(DateUtil.StringToDate(fechaInactivo, "dd/MM/yyyy"));
                //casoExistente.setRecordUser(SecurityContextHolder.getContext().getAuthentication().getName());
                //casoExistente.setRecordDate(new Date());
                casoExistente.setInactivo("1");
                this.covidService.saveOrUpdateCasoCovid19(casoExistente);
            }
            return JsonUtil.createJsonResponse(casoExistente);
        }
        catch(Exception e){
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
