package ni.org.ics.estudios.movil.controller.cohortefamilia;

import ni.org.ics.estudios.domain.cohortefamilia.ParticipanteCohorteFamilia;
import ni.org.ics.estudios.service.cohortefamilia.ParticipanteCohorteFamiliaService;
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
public class ParticipanteCohorteFamiliaController {

    private static final Logger logger = LoggerFactory.getLogger(ParticipanteCohorteFamiliaController.class);

    @Resource(name = "participanteCohorteFamiliaService")
    private ParticipanteCohorteFamiliaService participanteCohorteFamiliaService;

    /**
     * Acepta una solicitud GET para JSON
     * @return JSON
     */
    @RequestMapping(value = "participantesCHF", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    List<ParticipanteCohorteFamilia> getParticipantes() {
        logger.info("Descargando toda la informacion de Participante cohorte familia" );
        List<ParticipanteCohorteFamilia> respuestaList = participanteCohorteFamiliaService.getParticipantesCHF();
        if (respuestaList == null){
            logger.debug("Nulo");
        }
        return respuestaList;
    }

    /**
     * Acepta una solicitud POST con un par�metro JSON
     * @param participantesArray Objeto serializado de ParticipanteCohorteFamilia
     * @return String con el resultado
     */
    @RequestMapping(value = "participantesCHF", method = RequestMethod.POST, consumes = "application/json")
    public @ResponseBody String saveParticipantes(@RequestBody ParticipanteCohorteFamilia[] participantesArray){
        logger.debug("Insertando/Actualizando formularios Participante cohorte familia");
        if (participantesArray == null){
            logger.debug("Nulo");
            return "No recibi nada!";
        }else{
            List<ParticipanteCohorteFamilia> participantes = Arrays.asList(participantesArray);
            for (ParticipanteCohorteFamilia participante : participantes){
                participanteCohorteFamiliaService.saveOrUpdateParticipanteCHF(participante);
            }
        }
        return "Datos recibidos!";
    }

}
