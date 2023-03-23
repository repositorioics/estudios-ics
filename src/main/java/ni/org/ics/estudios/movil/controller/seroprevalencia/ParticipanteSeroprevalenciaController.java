package ni.org.ics.estudios.movil.controller.seroprevalencia;

import ni.org.ics.estudios.domain.seroprevalencia.ParticipanteSeroprevalencia;
import ni.org.ics.estudios.service.seroprevalencia.ParticipanteSeroprevalenciaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Miguel Salinas on 5/5/2017.
 * V1.0
 */
@Controller
@RequestMapping("/movil/*")
public class ParticipanteSeroprevalenciaController {

    private static final Logger logger = LoggerFactory.getLogger(ParticipanteSeroprevalenciaController.class);

    @Resource(name = "participanteSeroprevalenciaService")
    private ParticipanteSeroprevalenciaService participanteSeroprevalenciaService;

    /**
     * Acepta una solicitud GET para JSON
     * @return JSON
     */
    @RequestMapping(value = "participantesSA", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    List<ParticipanteSeroprevalencia> getParticipantesSA() {
        logger.info("Descargando toda la informacion de Participante seroprevalencia arbovirus" );
        List<ParticipanteSeroprevalencia> respuestaList = participanteSeroprevalenciaService.getParticipantesSA();
        if (respuestaList == null){
            logger.debug("Nulo");
        }
        return respuestaList;
    }

    /**
     * Acepta una solicitud POST con un par�metro JSON
     * @param participantesArray Objeto serializado de ParticipanteSeroprevalencia
     * @return String con el resultado
     */
    @RequestMapping(value = "participantesSA", method = RequestMethod.POST, consumes = "application/json")
    public @ResponseBody String saveParticipantesSA(@RequestBody ParticipanteSeroprevalencia[] participantesArray){
        logger.debug("Insertando/Actualizando formularios Participante seroprevalencia arbovirus");
        if (participantesArray == null){
            logger.debug("Nulo");
            return "No recibi nada!";
        }else{
            List<ParticipanteSeroprevalencia> participantes = Arrays.asList(participantesArray);
            for (ParticipanteSeroprevalencia participante : participantes){
                participanteSeroprevalenciaService.saveOrUpdateParticipanteSA(participante);
            }
        }
        return "Datos recibidos!";
    }

}
