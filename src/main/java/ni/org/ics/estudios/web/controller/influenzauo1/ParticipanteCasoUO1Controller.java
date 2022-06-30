package ni.org.ics.estudios.web.controller.influenzauo1;

import com.google.gson.Gson;
import ni.org.ics.estudios.domain.Participante;
import ni.org.ics.estudios.domain.influenzauo1.ParticipanteCasoUO1;
import ni.org.ics.estudios.domain.muestreoanual.ParticipanteProcesos;
import ni.org.ics.estudios.language.MessageResource;
import ni.org.ics.estudios.service.MessageResourceService;
import ni.org.ics.estudios.service.ParticipanteService;
import ni.org.ics.estudios.service.influenzauo1.ParticipanteCasoUO1Service;
import ni.org.ics.estudios.service.muestreoanual.ParticipanteProcesosService;
import ni.org.ics.estudios.web.utils.DateUtil;
import ni.org.ics.estudios.web.utils.JsonUtil;
import ni.org.ics.estudios.web.utils.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

@Controller
@RequestMapping("/super/UO1/*")
public class ParticipanteCasoUO1Controller {
    private static final Logger logger = LoggerFactory.getLogger(ParticipanteCasoUO1Controller.class);

    @Resource(name = "participanteCasoUO1Service")
    private ParticipanteCasoUO1Service participanteCasoUO1Service;

    @Resource(name = "participanteService")
    private ParticipanteService participanteService;

    @Resource(name = "participanteProcesosService")
    private ParticipanteProcesosService participanteProcesosService;

    @Resource(name = "messageResourceService")
    private MessageResourceService messageResourceService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String obtenerCasos(Model model) throws ParseException {
        logger.debug("Mostrando casos monitoreo intensivo en JSP");
        List<MessageResource> positivoPor = messageResourceService.getCatalogo("UO1_CAT_POSITIVO_POR");
        List<ParticipanteCasoUO1> casos = this.participanteCasoUO1Service.getCasos();
        model.addAttribute("positivoPor",positivoPor);
        model.addAttribute("casos",casos);
        return "/supervisor/influenzauo1/list";
    }

    @RequestMapping(value = "newCase", method = RequestMethod.GET)
    public String initAddCaseForm(Model model) {
        List<MessageResource> positivoPor = messageResourceService.getCatalogo("UO1_CAT_POSITIVO_POR");
        model.addAttribute("agregando",true);
        model.addAttribute("editando",false);
        model.addAttribute("positivoPor", positivoPor);
        model.addAttribute("caso", new ParticipanteCasoUO1());
        return "/supervisor/influenzauo1/enterForm";
    }

    @RequestMapping(value = "editCase/{codigo}", method = RequestMethod.GET)
    public String initEditCaseForm(Model model, @PathVariable("codigo") String codigo) {
        List<MessageResource> positivoPor = messageResourceService.getCatalogo("UO1_CAT_POSITIVO_POR");
        ParticipanteCasoUO1 participanteCasoUO1 = this.participanteCasoUO1Service.getCasoByCodigo(codigo);
        model.addAttribute("agregando",false);
        model.addAttribute("editando",true);
        model.addAttribute("positivoPor", positivoPor);
        model.addAttribute("caso", participanteCasoUO1);
        return "/supervisor/influenzauo1/enterForm";
    }

    @RequestMapping(value = "searchParticipant", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    ResponseEntity<String> buscarParticipante(@RequestParam(value="participantCode", required=true ) Integer codigo) throws ParseException {
        logger.debug("buscar participante para positivo UO1");
            Participante participante = this.participanteService.getParticipanteByCodigo(codigo);
            if (participante!=null) {
                ParticipanteProcesos procesos = this.participanteProcesosService.getParticipante(codigo);
                if (procesos != null && procesos.getEstPart().equals(0))
                    return JsonUtil.createJsonResponse("Participante retirado");
                else if (procesos != null && (!procesos.getEstudio().contains("UO1") && !procesos.getEstudio().contains("Influenza")))
                    return JsonUtil.createJsonResponse("Participante aún no ha sido enrolado FLU (UO1, Influenza)");

                ParticipanteCasoUO1 participanteUO1 = this.participanteCasoUO1Service.getCasoActivoParticipante(codigo);
                if (participanteUO1 != null) {
                    return JsonUtil.createJsonResponse("Participante ya fue registrado como positivo y se encuentra activo FLU");
                }

                if (procesos != null && procesos.getEstudio().contains("CH Familia"))
                    return JsonUtil.createJsonResponse("Participante pertenece a Cohorte de Familia, no se puede registrar como positivo");
            }
            else
                return JsonUtil.createJsonResponse("No se encontró participante según el código ingresado");

        return JsonUtil.createJsonResponse(participante);
    }

    @RequestMapping( value="saveCase", method=RequestMethod.POST)
    public ResponseEntity<String> guardarCaso(@RequestParam(value="codigo", required=true ) String codigo
            , @RequestParam( value="fechaInicio", required=true ) String fechaInicio
            , @RequestParam( value="codigoParticipante", required=false, defaultValue="" ) Integer codigoParticipante
            , @RequestParam( value="fif", required=true, defaultValue="" ) String fif
            , @RequestParam( value="positivoPor", required=true, defaultValue="" ) String positivoPor
            , @RequestParam( value="fis", required=true, defaultValue="" ) String fis
    )
    {
        try{
            Date dFechaIngreso = DateUtil.StringToDate(fechaInicio, "dd/MM/yyyy");
            Date dFIF = DateUtil.StringToDate(fif, "dd/MM/yyyy");
            Date dFIS = DateUtil.StringToDate(fis, "dd/MM/yyyy");

            if (dFechaIngreso.after(new Date())){
                return JsonUtil.createJsonResponse("Fecha de inicio es posterior a la fecha actual: "+DateUtil.DateToString(new Date(), "dd/MM/yyyy"));
            }
            if (dFIF != null && dFIF.after(new Date())){
                return JsonUtil.createJsonResponse("FIF es posterior a la fecha actual: "+DateUtil.DateToString(new Date(), "dd/MM/yyyy"));
            }
            if (dFIS != null && dFIS.after(new Date())){
                return JsonUtil.createJsonResponse("FIS es posterior a la fecha actual: "+DateUtil.DateToString(new Date(), "dd/MM/yyyy"));
            }
            if (dFIS != null && dFIF != null && dFIS.after(dFIF)){
                return JsonUtil.createJsonResponse("FIS es posterior a la FIF: "+fif);
            }
            ParticipanteCasoUO1 casaCasoExistente = this.participanteCasoUO1Service.getCasoActivoParticipante(codigoParticipante);
            ParticipanteCasoUO1 casaCaso = this.participanteCasoUO1Service.getCasoByCodigo(codigo);
            if (casaCasoExistente==null) {
                if (casaCaso == null) {
                    casaCaso = new ParticipanteCasoUO1();
                    casaCaso.setCodigoCasoParticipante(StringUtil.getCadenaAlfanumAleatoria(36, true));

                }
                casaCaso.setParticipante(this.participanteService.getParticipanteByCodigo(codigoParticipante));
                casaCaso.setFechaIngreso(dFechaIngreso);
                casaCaso.setFif(dFIF);
                casaCaso.setFis(dFIS);
                casaCaso.setPositivoPor(positivoPor);
                casaCaso.setEstado('1');
                casaCaso.setPasive('0');
                casaCaso.setActivo("1");
                casaCaso.setRecordDate(new Date());
                casaCaso.setRecordUser(SecurityContextHolder.getContext().getAuthentication().getName());

                this.participanteCasoUO1Service.saveOrUpdate(casaCaso);
            }else {
                if (casaCasoExistente.getFechaIngreso().compareTo(dFechaIngreso)!=0){
                    return JsonUtil.createJsonResponse("Ya existe un caso activo para esta casa con fecha de inicio: "+DateUtil.DateToString(casaCasoExistente.getFechaIngreso(),"dd/MM/yyyy"));
                }else {
                    casaCasoExistente.setFechaIngreso(dFechaIngreso);
                    casaCasoExistente.setFis(dFIS);
                    casaCasoExistente.setFif(dFIF);
                    casaCasoExistente.setPositivoPor(positivoPor);
                    this.participanteCasoUO1Service.saveOrUpdate(casaCasoExistente);
                    casaCaso = casaCasoExistente;
                }
            }

            return JsonUtil.createJsonResponse(casaCaso);
        }
        catch(Exception e){
            return JsonUtil.createJsonResponse(e.toString());
        }
    }

    @RequestMapping("/actions/{accion}/{codigo}")
    public String enableUser(@PathVariable("codigo") String codigo,
                             @PathVariable("accion") String accion, RedirectAttributes redirectAttributes) {
        String redirecTo="404";
        if (accion.matches("disable")){
            redirecTo = "redirect:/super/UO1/";
            redirectAttributes.addFlashAttribute("deshabilitado", true);

            //UserSistema usuarioActual = this.usuarioService.getUser(SecurityContextHolder.getContext().getAuthentication().getName());
            ParticipanteCasoUO1 casaCasoExistente = this.participanteCasoUO1Service.getCasoByCodigo(codigo);
            if(casaCasoExistente!=null){
                casaCasoExistente.setRecordDate(new Date());
                casaCasoExistente.setRecordUser(SecurityContextHolder.getContext().getAuthentication().getName());
                casaCasoExistente.setPasive('1');
                this.participanteCasoUO1Service.saveOrUpdate(casaCasoExistente);
                redirectAttributes.addFlashAttribute("participante", casaCasoExistente.getParticipante().getCodigo().toString());
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
    @RequestMapping( value="closeCase", method=RequestMethod.POST)
    public ResponseEntity<String> cerrarCaso( @RequestParam(value="codigo", required=true ) String codigo
            , @RequestParam( value="fechaDesactivacion", required=true, defaultValue="" ) String fechaDesactivacion
            , @RequestParam( value="observacion", required=false, defaultValue="" ) String observacion
    )
    {
        try{
            ParticipanteCasoUO1 casaCasoExistente = this.participanteCasoUO1Service.getCasoByCodigo(codigo);
            if (casaCasoExistente!=null) {
                casaCasoExistente.setFechaDesactivacion(DateUtil.StringToDate(fechaDesactivacion, "dd/MM/yyyy"));
                casaCasoExistente.setObservacion(observacion);
                casaCasoExistente.setRecordUser(SecurityContextHolder.getContext().getAuthentication().getName());
                casaCasoExistente.setRecordDate(new Date());
                casaCasoExistente.setActivo("0");
                this.participanteCasoUO1Service.saveOrUpdate(casaCasoExistente);
            }
            return JsonUtil.createJsonResponse(casaCasoExistente);
        }
        catch(Exception e){
            Gson gson = new Gson();
            String json = gson.toJson(e.toString());
            return new ResponseEntity<String>( json, HttpStatus.CREATED);
        }
    }
}
