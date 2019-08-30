package ni.org.ics.estudios.web.controller;

import com.google.gson.Gson;
import ni.org.ics.estudios.domain.CartaConsentimiento;
import ni.org.ics.estudios.domain.cohortefamilia.ParticipanteCohorteFamilia;
import ni.org.ics.estudios.domain.cohortefamilia.casos.CasaCohorteFamiliaCaso;
import ni.org.ics.estudios.domain.cohortefamilia.casos.ParticipanteCohorteFamiliaCaso;
import ni.org.ics.estudios.domain.muestreoanual.ParticipanteProcesos;
import ni.org.ics.estudios.language.MessageResource;
import ni.org.ics.estudios.service.CartaConsentimientoService;
import ni.org.ics.estudios.service.MessageResourceService;
import ni.org.ics.estudios.service.UsuarioService;
import ni.org.ics.estudios.service.cohortefamilia.CasaCohorteFamiliaService;
import ni.org.ics.estudios.service.cohortefamilia.ParticipanteCohorteFamiliaService;
import ni.org.ics.estudios.service.cohortefamilia.casos.CasaCohorteFamiliaCasoService;
import ni.org.ics.estudios.service.cohortefamilia.casos.ParticipanteCohorteFamiliaCasoService;
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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.Resource;
import java.text.ParseException;
import java.util.*;

/**
 * Created by Miguel Salinas on 6/28/2017.
 * V1.0
 */
@Controller
@RequestMapping("/super/casacaso/*")
public class CasaCohorteFamiliaCasoWController {

    private static final Logger logger = LoggerFactory.getLogger(CasaCohorteFamiliaCasoWController.class);

    @Resource(name = "casaCohorteFamiliaCasoService")
    private CasaCohorteFamiliaCasoService casaCohorteFamiliaCasoService;

    @Resource(name = "participanteCohorteFamiliaCasoService")
    private ParticipanteCohorteFamiliaCasoService participanteCohorteFamiliaCasoService;

    @Resource(name = "casaCohorteFamiliaService")
    private CasaCohorteFamiliaService casaCohorteFamiliaService;

    @Resource(name = "participanteCohorteFamiliaService")
    private ParticipanteCohorteFamiliaService participanteCohorteFamiliaService;

    @Resource(name = "messageResourceService")
    private MessageResourceService messageResourceService;

    @Resource(name="cartaConsentimientoService")
    private CartaConsentimientoService cartaConsentimientoService;

    @Resource(name="participanteProcesosService")
    private ParticipanteProcesosService participanteProcesosService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String obtenerCasos(Model model) throws ParseException {
        logger.debug("Mostrando casos monitoreo intensivo en JSP");
        List<ParticipanteCohorteFamiliaCaso> casos = participanteCohorteFamiliaCasoService.getParticipanteCohorteFamiliaCasosPositivos();
        List<MessageResource> visitas = messageResourceService.getCatalogo("CHF_CAT_VIS_MI");
        List<MessageResource> tiposEtiquetas = messageResourceService.getCatalogo("CHF_CAT_TIP_ETIQ_MI");
        model.addAttribute("casos",casos);
        model.addAttribute("visitas", visitas);
        model.addAttribute("etiquetas", tiposEtiquetas);
        return "/supervisor/casos/list";
    }

    @RequestMapping(value = "/participants/{codigo}", method = RequestMethod.GET)
    public String obtenerParticipantesCaso(@PathVariable("codigo") String codigo, Model model) throws ParseException {
        logger.debug("Mostrando participantes de caso monitoreo intensivo en JSP");
        List<ParticipanteCohorteFamiliaCaso> participantes = participanteCohorteFamiliaCasoService.getParticipantesCohorteFamiliaCasoByCodigoCaso(codigo);
        model.addAttribute("participantes",participantes);
        model.addAttribute("caso", participantes.get(0).getCodigoCaso());
        return "/supervisor/casos/participantsList";
    }

    @RequestMapping(value = "newCase", method = RequestMethod.GET)
    public String initAddCaseForm(Model model) {
        model.addAttribute("agregando",true);
        model.addAttribute("editando",false);
        model.addAttribute("caso", new ParticipanteCohorteFamiliaCaso());
        return "/supervisor/casos/enterForm";
    }

    @RequestMapping(value = "editCase/{codigo}", method = RequestMethod.GET)
    public String initEditCaseForm(@PathVariable("codigo") String codigo, Model model) {
        ParticipanteCohorteFamiliaCaso participanteCaso = this.participanteCohorteFamiliaCasoService.getParticipanteCohorteFamiliaCasosByCodigo(codigo);
        model.addAttribute("agregando",false);
        model.addAttribute("editando",true);
        model.addAttribute("caso", participanteCaso);
        return "/supervisor/casos/enterForm";
    }

    @RequestMapping("/actions/{accion}/{codigo}")
    public String enableUser(@PathVariable("codigo") String codigo,
                             @PathVariable("accion") String accion, RedirectAttributes redirectAttributes) {
        String redirecTo="404";
        if (accion.matches("disable")){
            redirecTo = "redirect:/super/casacaso/";
            redirectAttributes.addFlashAttribute("deshabilitado", true);

            //UserSistema usuarioActual = this.usuarioService.getUser(SecurityContextHolder.getContext().getAuthentication().getName());
            CasaCohorteFamiliaCaso casaCasoExistente = this.casaCohorteFamiliaCasoService.getCasaCohorteFamiliaCasosByCodigo(codigo);
            if(casaCasoExistente!=null){
                casaCasoExistente.setRecordDate(new Date());
                casaCasoExistente.setRecordUser(SecurityContextHolder.getContext().getAuthentication().getName());
                casaCasoExistente.setPasive('1');
                List<ParticipanteCohorteFamiliaCaso> participanteCohorteFamiliaCasos = this.participanteCohorteFamiliaCasoService.getParticipantesCohorteFamiliaCasoByCodigoCaso(codigo);
                this.casaCohorteFamiliaCasoService.saveOrUpdateCasaCohorteFamiliaCaso(casaCasoExistente);
                for(ParticipanteCohorteFamiliaCaso participante : participanteCohorteFamiliaCasos){
                    participante.setRecordDate(new Date());
                    participante.setRecordUser(SecurityContextHolder.getContext().getAuthentication().getName());
                    participante.setPasive('1');
                    this.participanteCohorteFamiliaCasoService.saveOrUpdateParticipanteCohorteFamiliaCaso(participante);
                }
                redirectAttributes.addFlashAttribute("casa", casaCasoExistente.getCasa().getCodigoCHF());
            }
            else{
                redirecTo = "403";
            }
        } else if (accion.matches("disablepart")){
            ParticipanteCohorteFamiliaCaso participanteCohorteFamiliaCaso = participanteCohorteFamiliaCasoService.getParticipanteCohorteFamiliaCasosByCodigo(codigo);
            participanteCohorteFamiliaCaso.setRecordDate(new Date());
            participanteCohorteFamiliaCaso.setRecordUser(SecurityContextHolder.getContext().getAuthentication().getName());
            participanteCohorteFamiliaCaso.setPasive('1');
            this.participanteCohorteFamiliaCasoService.saveOrUpdateParticipanteCohorteFamiliaCaso(participanteCohorteFamiliaCaso);

            redirecTo = "redirect:/super/casacaso/participants/"+participanteCohorteFamiliaCaso.getCodigoCaso().getCodigoCaso();
        }
        else{
            return redirecTo;
        }

        return redirecTo;
    }

    @RequestMapping( value="closeCase", method=RequestMethod.POST)
    public ResponseEntity<String> cerrarCaso( @RequestParam(value="codigo", required=true ) String codigo
            , @RequestParam( value="fechaInactiva", required=true, defaultValue="" ) String fechaInactiva
    )
    {
        try{
            CasaCohorteFamiliaCaso casaCasoExistente = this.casaCohorteFamiliaCasoService.getCasaCohorteFamiliaCasosByCodigo(codigo);
            if (casaCasoExistente!=null) {
                casaCasoExistente.setFechaInactiva(DateUtil.StringToDate(fechaInactiva, "dd/MM/yyyy"));
                casaCasoExistente.setRecordUser(SecurityContextHolder.getContext().getAuthentication().getName());
                casaCasoExistente.setRecordDate(new Date());
                casaCasoExistente.setInactiva("1");
                this.casaCohorteFamiliaCasoService.saveOrUpdateCasaCohorteFamiliaCaso(casaCasoExistente);
            }
            return JsonUtil.createJsonResponse(casaCasoExistente);
        }
        catch(Exception e){
            Gson gson = new Gson();
            String json = gson.toJson(e.toString());
            return new ResponseEntity<String>( json, HttpStatus.CREATED);
        }
    }

    @RequestMapping( value="saveCase", method=RequestMethod.POST)
    public ResponseEntity<String> guardarCaso( @RequestParam(value="codigo", required=true ) String codigo
            , @RequestParam( value="codigoCasa", required=true ) String codigoCasa
            , @RequestParam( value="fechaInicio", required=false ) String fechaInicio
            , @RequestParam( value="codigoParticipante", required=false, defaultValue="" ) Integer codigoParticipante
            , @RequestParam( value="fif", required=true, defaultValue="" ) String fif
    )
    {
        try{
            Date dFechaInicio = DateUtil.StringToDate(fechaInicio, "dd/MM/yyyy");
            Date dFIF = DateUtil.StringToDate(fif, "dd/MM/yyyy");
            if (dFechaInicio.after(new Date())){
                return JsonUtil.createJsonResponse("Fecha de inicio es posterior a la fecha actual: "+DateUtil.DateToString(new Date(), "dd/MM/yyyy"));
            }
            if (dFIF.after(new Date())){
                return JsonUtil.createJsonResponse("FIF es posterior a la fecha actual: "+DateUtil.DateToString(new Date(), "dd/MM/yyyy"));
            }
            CasaCohorteFamiliaCaso casaCasoExistente = this.casaCohorteFamiliaCasoService.getCasaCohorteFamiliaCasoByCodigoCasa(codigoCasa);
            //CasaCohorteFamiliaCaso casaCasoExistente = this.casaCohorteFamiliaCasoService.getCasaCohorteFamiliaCasosByCodigoCasaFecha(codigoCasa,dFechaInicio);
            CasaCohorteFamiliaCaso casaCaso = this.casaCohorteFamiliaCasoService.getCasaCohorteFamiliaCasosByCodigo(codigo);
            /*if (casaCasoExistente!=null && codigo.isEmpty())
                return createJsonResponse("Ya existe un caso positivo para esta casa");
*/
            if (casaCasoExistente==null) {
                if (casaCaso == null) {
                    casaCaso = new CasaCohorteFamiliaCaso();
                    casaCaso.setCodigoCaso(StringUtil.getCadenaAlfanumAleatoria(36, true));

                }
                casaCaso.setCasa(casaCohorteFamiliaService.getCasasCHFByCodigo(codigoCasa));
                casaCaso.setFechaInicio(dFechaInicio);
                casaCaso.setEstado('1');
                casaCaso.setPasive('0');
                casaCaso.setInactiva("0");
                casaCaso.setRecordDate(new Date());
                casaCaso.setRecordUser(SecurityContextHolder.getContext().getAuthentication().getName());

                this.casaCohorteFamiliaCasoService.saveOrUpdateCasaCohorteFamiliaCaso(casaCaso);
            }else {
                if (casaCasoExistente.getFechaInicio().compareTo(dFechaInicio)!=0){
                    return JsonUtil.createJsonResponse("Ya existe un caso activo para esta casa con fecha de inicio: "+DateUtil.DateToString(casaCasoExistente.getFechaInicio(),"dd/MM/yyyy"));
                }else
                    casaCaso = casaCasoExistente;
            }
            //agregar paticipante positivo
            ParticipanteCohorteFamiliaCaso participanteCaso = this.participanteCohorteFamiliaCasoService.getParticipanteCohorteFamiliaCasosByParticipante(codigoParticipante, casaCaso.getCodigoCaso());
            if(participanteCaso==null){
                participanteCaso = new ParticipanteCohorteFamiliaCaso();
                participanteCaso.setCodigoCasoParticipante(StringUtil.getCadenaAlfanumAleatoria(36, true));
                participanteCaso.setRecordDate(new Date());
                participanteCaso.setRecordUser(SecurityContextHolder.getContext().getAuthentication().getName());
                participanteCaso.setPasive('0');
            }
            participanteCaso.setParticipante(this.participanteCohorteFamiliaService.getParticipanteCHFByCodigo(codigoParticipante));
            participanteCaso.setCodigoCaso(casaCaso);
            participanteCaso.setEstado('1');
            participanteCaso.setEnfermo("S");
            participanteCaso.setFechaEnfermedad(dFIF);


            this.participanteCohorteFamiliaCasoService.saveOrUpdateParticipanteCohorteFamiliaCaso(participanteCaso);
            //verificar si no tiene consentimiento muestra de manos
            boolean pedirConsentimiento = true;
            boolean pedirAsentimientoCasa = true;
            List<CartaConsentimiento> cartaConPart = this.cartaConsentimientoService.getCartaConsentimientoByCodParticipanteEstudio(codigoParticipante, 1); //1 es estudio familia
            List<CartaConsentimiento> cartaConCasa = this.cartaConsentimientoService.getCartaConsentimientoByCodCasaEstudio(casaCaso.getCasa().getCodigoCHF(), 1); //1 es estudio familia
            for(CartaConsentimiento cc : cartaConPart){
                //es carta para cohorte familia y tiene registro en parteD que se refiere a muestra de manos
                if ((cc.getAceptaParteD()!=null && !cc.getAceptaParteD().isEmpty())){
                    pedirConsentimiento = false;
                    break;
                }
            }
            if (cartaConCasa.size()>0) pedirAsentimientoCasa = false;

            if (pedirConsentimiento || pedirAsentimientoCasa){
                ParticipanteProcesos procesos = this.participanteProcesosService.getParticipante(codigoParticipante);
                procesos.setMxSuperficie((pedirConsentimiento && pedirAsentimientoCasa)?"3":(pedirConsentimiento?"2":"1"));
                this.participanteProcesosService.saveOrUpdateParticipanteProc(procesos);
            }else{
                ParticipanteProcesos procesos = this.participanteProcesosService.getParticipante(codigoParticipante);
                procesos.setMxSuperficie("0");//ninguno
                this.participanteProcesosService.saveOrUpdateParticipanteProc(procesos);
            }


            //Agregar resto de participantes de la casa
            List<ParticipanteCohorteFamilia> participantesCasa = participanteCohorteFamiliaService.getParticipantesCHFByCodigoCasa(codigoCasa);
            for(ParticipanteCohorteFamilia participante : participantesCasa){
                //como son todos los de la casa, se omite el positivo
                if (!participante.getParticipante().getCodigo().equals(codigoParticipante)) {
                    participanteCaso = this.participanteCohorteFamiliaCasoService.getParticipanteCohorteFamiliaCasosByParticipante(participante.getParticipante().getCodigo(), casaCaso.getCodigoCaso());
                    if (participanteCaso == null) {
                        participanteCaso = new ParticipanteCohorteFamiliaCaso();
                        participanteCaso.setCodigoCasoParticipante(StringUtil.getCadenaAlfanumAleatoria(36, true));
                        participanteCaso.setEnfermo("N");
                        participanteCaso.setFechaEnfermedad(null);
                        participanteCaso.setRecordDate(new Date());
                        participanteCaso.setRecordUser(SecurityContextHolder.getContext().getAuthentication().getName());
                        participanteCaso.setPasive('0');
                    }
                    participanteCaso.setParticipante(participante);
                    participanteCaso.setCodigoCaso(casaCaso);
                    participanteCaso.setEstado('1');

                    this.participanteCohorteFamiliaCasoService.saveOrUpdateParticipanteCohorteFamiliaCaso(participanteCaso);
                        ParticipanteProcesos procesos = this.participanteProcesosService.getParticipante(participante.getParticipante().getCodigo());
                        procesos.setMxSuperficie(pedirAsentimientoCasa?"1":"0");
                        this.participanteProcesosService.saveOrUpdateParticipanteProc(procesos);

                }
            }

            return JsonUtil.createJsonResponse(casaCaso);
        }
        catch(Exception e){
            Gson gson = new Gson();
            String json = gson.toJson(e.toString());
            return new ResponseEntity<String>( json, HttpStatus.CREATED);
        }
    }
}
