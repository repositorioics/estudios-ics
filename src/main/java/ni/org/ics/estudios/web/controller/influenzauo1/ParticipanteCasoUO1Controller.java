package ni.org.ics.estudios.web.controller.influenzauo1;

import com.google.gson.Gson;
import ni.org.ics.estudios.domain.Participante;
import ni.org.ics.estudios.domain.influenzauo1.ParticipanteCasoUO1;
import ni.org.ics.estudios.domain.muestreoanual.ParticipanteProcesos;
import ni.org.ics.estudios.dto.ParticipanteBusquedaDto;
import ni.org.ics.estudios.dto.influenzauo1.casosPositivosDiffDto;
import ni.org.ics.estudios.dto.muestras.MxDto;
import ni.org.ics.estudios.language.MessageResource;
import ni.org.ics.estudios.service.MessageResourceService;
import ni.org.ics.estudios.service.ParticipanteService;
import ni.org.ics.estudios.service.covid.CovidService;
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

    @Resource(name = "CovidService")
    private CovidService covidService;

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
        String alerta = "";
        Integer dias;
        List<MxDto>muestraMenorA30Dia     = null;
        List<MxDto>Chf_muestraMenorA30Dia = null;
        Participante participante = this.participanteService.getParticipanteByCodigo(codigo);
            if (participante!=null) {

                MessageResource intervalDias = messageResourceService.getMensaje("CAT_INTERVAL_DIAS_MX_30");
                dias = Integer.parseInt(intervalDias.getSpanish());

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


                //Si contiene uo1 o influenza
                if (procesos.getEstudio().contains("UO1") || procesos.getEstudio().contains("Influenza")){
                    ParticipanteCasoUO1 participanteCasoUO1 = participanteCasoUO1Service.getCasoActivoParticipante(procesos.getCodigo());
                    if (participanteCasoUO1==null){
                        List<MxDto>casoIn30Day = this.participanteCasoUO1Service.getCasoUO1Ultimo30Day(codigo,dias);
                        if (casoIn30Day.size()>0){
                            alerta += "► <strong>Positivo de Influenza</strong>- en los ultimos <strong>" + dias + "</strong> días. ";
                            alerta += "\t Inicio:  <strong>"+ DateUtil.DateToString(casoIn30Day.get(0).getFechaInicio(),"dd/MM/yyyy") +"</strong> finalizo: <strong>"+ DateUtil.DateToString(casoIn30Day.get(0).getFechaInactiva(),"dd/MM/yyyy")+"</strong><br>";
                            alerta += "\t Tiempo transcurrido despúes de haber finalizado el caso: <strong>" + casoIn30Day.get(0).getDiasInactiva() + "</strong> días.";
                        }
                    }
                    if (participanteCasoUO1 != null) {
                        MessageResource positivoPor = messageResourceService.getMensajeByCatalogAndCatKey("UO1_CAT_POSITIVO_POR", participanteCasoUO1.getPositivoPor());
                        alerta += "* Positivo de Influenza - Positivo por: "+(positivoPor != null ? positivoPor.getSpanish(): "-") +
                                " - Activación: "+DateUtil.CalcularDiferenciaDiasFechas(participanteCasoUO1.getFechaIngreso(), new Date()) + " Dias <br>";
                    }
                    muestraMenorA30Dia     = this.covidService.getMuestrasTomaMinor30Days(codigo, dias);
                    Chf_muestraMenorA30Dia = this.covidService.getChfMuestraToma2Minor30Days(codigo,dias);

                    if (procesos != null){
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
                    }
                }

            }
            else
                return JsonUtil.createJsonResponse("No se encontró participante según el código ingresado");


        ParticipanteBusquedaDto participanteDto = new ParticipanteBusquedaDto();
        participanteDto.setCodigo(participante.getCodigo());
        participanteDto.setCasaPediatrica(participante.getCasa().getCodigo());
        participanteDto.setAlertas(alerta);
        participanteDto.setMuestras(muestraMenorA30Dia);
        participanteDto.setChf_muestras(Chf_muestraMenorA30Dia);
        return JsonUtil.createJsonResponse(participanteDto);
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
                //casaCasoExistente.setRecordUser(SecurityContextHolder.getContext().getAuthentication().getName());
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
                String nombreUsuario = SecurityContextHolder.getContext().getAuthentication().getName();
                casaCasoExistente.setObservacion(observacion + " - " + nombreUsuario);
                //casaCasoExistente.setRecordUser(SecurityContextHolder.getContext().getAuthentication().getName());
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


    @RequestMapping( value="desactiveCase", method=RequestMethod.POST)
    public ResponseEntity<String> desactiveCase( @RequestParam(value="codigo", required=true ) String codigo,
                                                 @RequestParam(value="motivo", required=true) String motivo){
        try{
            ParticipanteCasoUO1 casaCasoExistente = this.participanteCasoUO1Service.getCasoByCodigo(codigo);
            if(casaCasoExistente!=null){
                casaCasoExistente.setRecordDate(new Date());
                //casaCasoExistente.setRecordUser(SecurityContextHolder.getContext().getAuthentication().getName());
                casaCasoExistente.setPasive('1');
                casaCasoExistente.setActivo("0");// preguntar si debo cambiar este campo
                String nombreUsuario = SecurityContextHolder.getContext().getAuthentication().getName();
                String mot = (motivo.equals(""))?"":motivo.toUpperCase() + " - " + nombreUsuario;
                casaCasoExistente.setDeshabilitado_por(mot);
                this.participanteCasoUO1Service.saveOrUpdate(casaCasoExistente);
                return JsonUtil.createJsonResponse(casaCasoExistente);
            }
            else{
                return JsonUtil.createJsonResponse("Registro no encontrado.");
            }
        }
        catch(Exception e){
            Gson gson = new Gson();
            String json = gson.toJson(e.toString());
            return new ResponseEntity<String>( json, HttpStatus.CREATED);
        }
    }

    @RequestMapping(value = "searchDaysDiff", method = RequestMethod.POST)
    public @ResponseBody
    ResponseEntity<String> searchDaysDiff(@RequestParam( value="codigo", required=true ) String codigo) throws ParseException {
        try{
            Integer dias;
            casosPositivosDiffDto dto = null;
            MessageResource intervalDias = messageResourceService.getMensaje("CAT_INTERVAL_DIAS_CASO_FLU_UO1");
            if (intervalDias == null){
                return JsonUtil.createJsonResponse("Intervalos de dias no encontrado.");
            }else{
                dias = Integer.parseInt(intervalDias.getSpanish());
            }
            dto = this.participanteCasoUO1Service.getdiasTranscurridos(codigo, dias);
            if (dto != null) {
                dto.setDiasDeBusqueda(dias);
                return JsonUtil.createJsonResponse(dto);
            }else{
                dto = this.participanteCasoUO1Service.getInfoDiasTranscurridos(codigo);
                dto.setDiasDeBusqueda(dias);
                return JsonUtil.createJsonResponse(dto);
            }
        }catch (Exception e){
            Gson gson = new Gson();
            String json = gson.toJson(e.toString());
            return new ResponseEntity<String>( json, HttpStatus.CREATED);
        }
    }
}
