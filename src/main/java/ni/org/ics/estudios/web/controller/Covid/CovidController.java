package ni.org.ics.estudios.web.controller.Covid;

import com.google.gson.Gson;
import ni.org.ics.estudios.domain.Participante;
import ni.org.ics.estudios.domain.cohortefamilia.ParticipanteCohorteFamilia;
import ni.org.ics.estudios.domain.cohortefamilia.casos.ParticipanteCohorteFamiliaCaso;
import ni.org.ics.estudios.domain.muestreoanual.ParticipanteProcesos;
import ni.org.ics.estudios.service.MessageResourceService;
import ni.org.ics.estudios.service.ParticipanteService;
import ni.org.ics.estudios.service.cohortefamilia.ParticipanteCohorteFamiliaService;
import ni.org.ics.estudios.service.cohortefamilia.casos.ParticipanteCohorteFamiliaCasoService;
import ni.org.ics.estudios.service.covid.CovidService;
import ni.org.ics.estudios.service.muestreoanual.ParticipanteProcesosService;
import org.apache.commons.lang3.text.translate.UnicodeEscaper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.text.ParseException;

/**
 * Created by ICS on 01/06/2020.
 */

@Controller
@RequestMapping("/covid")
public class CovidController {
    private static final Logger logger = LoggerFactory.getLogger(CovidController.class);
    @Resource(name = "CovidService")
    private CovidService CovidService;

    @Resource(name = "participanteService")
    private ParticipanteService participanteService;

    @Resource(name = "participanteProcesosService")
    private ParticipanteProcesosService participanteProcesosService;

    @Resource(name = "participanteCohorteFamiliaCasoService")
    private ParticipanteCohorteFamiliaCasoService participanteCohorteFamiliaCasoService;

    @Resource(name = "messageResourceService")
    private MessageResourceService messageResourceService;

    @Resource(name = "participanteCohorteFamiliaService")
    private ParticipanteCohorteFamiliaService participanteCohorteFamiliaService;


    @RequestMapping(value = "searchParticipant", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    ResponseEntity<String> buscarParticipante(@RequestParam(value="participantCode", required=true ) Integer codigo) throws ParseException {
        logger.debug("buscar participanteChf para monitoreo intensivo");
        ParticipanteCohorteFamilia participanteChf = participanteCohorteFamiliaService.getParticipanteCHFByCodigo(codigo);
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
        return createJsonResponse(participanteChf);
    }



    @RequestMapping(value = "SaveForm", method = RequestMethod.GET)
    public String SaveForm() throws Exception
    {
        try{

            return "/casosCovid/saveForm";
        }
        catch (Exception e){
            throw e;
        }
    }


    /* Buscar Listado Covid Participante */
    @RequestMapping(value = "/listCovid", method = RequestMethod.GET)
    public ModelAndView FormCovid()throws Exception{
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/casosCovid/list");
        return modelAndView;
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
