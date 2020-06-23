package ni.org.ics.estudios.web.controller.Covid;

import com.google.gson.Gson;
import ni.org.ics.estudios.domain.CartaConsentimiento;
import ni.org.ics.estudios.domain.Participante;
import ni.org.ics.estudios.domain.cohortefamilia.CasaCohorteFamilia;
import ni.org.ics.estudios.domain.covid19.CandidatoTransmisionCovid19;
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

    @RequestMapping( value="saveCandidateTCovid", method=RequestMethod.POST)
    public ResponseEntity<String> saveCandidateTCovid( @RequestParam(value="codigo", required=false, defaultValue="" ) String codigo
            , @RequestParam( value="casaCHF", required=true ) String casaCHF
            , @RequestParam( value="codigoParticipante", required=false, defaultValue="" ) Integer codigoParticipante
            , @RequestParam( value="positivoPor", required=true, defaultValue="" ) String positivoPor
            , @RequestParam( value="fif", required=false, defaultValue="" ) String fif
            , @RequestParam( value="fis", required=true, defaultValue="" ) String fis
    )throws Exception{
        try{
            CandidatoTransmisionCovid19 candidatoTransmisionCovid19 = this.covidService.getCandidatoTransmisionCovid19(codigo);
            if (candidatoTransmisionCovid19==null) {
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
            if (procesos!=null)
                candidatoTransmisionCovid19.setEstActuales(procesos.getEstudio());

            candidatoTransmisionCovid19.setFis(DateUtil.StringToDate(fis, "dd/MM/yyyy"));
            candidatoTransmisionCovid19.setFif(DateUtil.StringToDate(fif, "dd/MM/yyyy"));
            candidatoTransmisionCovid19.setPositivoPor(positivoPor);
            Participante participante = participanteService.getParticipanteByCodigo(codigoParticipante);
            candidatoTransmisionCovid19.setParticipante(participante);
            this.covidService.saveOrUpdateCandidatoTransmisionCovid19(candidatoTransmisionCovid19);

            return JsonUtil.createJsonResponse(candidatoTransmisionCovid19);
        }catch (Exception e){
            Gson gson = new Gson();
            String json = gson.toJson(e.toString());
            return new ResponseEntity<String>( json, HttpStatus.CREATED);
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

    @RequestMapping(value = "/editCandidate/{codigo}", method = RequestMethod.GET)
    public String editCase(Model model, @PathVariable("codigo") String codigo) throws Exception
    {
        try{
            CandidatoTransmisionCovid19 candidatoTransmisionCovid19 = this.covidService.getCandidatoTransmisionCovid19(codigo);
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
            throw e;
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

}
