package ni.org.ics.estudios.movil.controller.cohortefamilia.casos;

import ni.org.ics.estudios.domain.cohortefamilia.casos.ParticipanteCohorteFamiliaCaso;
import ni.org.ics.estudios.service.cohortefamilia.casos.ParticipanteCohorteFamiliaCasoService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Miguel Salinas on 5/11/2017.
 * V1.0
 */
@Controller
@RequestMapping("/movil/*")
public class ParticipanteCohorteFamiliaCasoController {

    private static final Logger logger = LoggerFactory.getLogger(ParticipanteCohorteFamiliaCasoController.class);

    @Resource(name = "participanteCohorteFamiliaCasoService")
    private ParticipanteCohorteFamiliaCasoService participanteCohorteFamiliaCasoService;

    @RequestMapping(value = "participantescasos", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    List<ParticipanteCohorteFamiliaCaso> getParticipanteCohorteFamiliaCasos(){
        logger.info("Descargando toda la informacion de los datos de los participantes de las casas de la cohorte familia con caso positivo");
        List<ParticipanteCohorteFamiliaCaso> participantes = participanteCohorteFamiliaCasoService.getParticipanteCohorteFamiliaCasos();
        if (participantes == null){
            logger.debug("Nulo");
        }
        return  participantes;
    }

    @RequestMapping(value = "participantescasos", method = RequestMethod.POST, consumes = "application/json")
    public @ResponseBody String saveParticipanteCohorteFamiliaCasos(@RequestBody ParticipanteCohorteFamiliaCaso[] objetos){
        logger.debug("Insertando/Actualizando participantes de las casas cohorte familia con casos positivos");
        if (objetos == null){
            logger.debug("Nulo");
            return "No recibi nada!";
        }else{
            List<ParticipanteCohorteFamiliaCaso> participantes = Arrays.asList(objetos);
            for(ParticipanteCohorteFamiliaCaso participante : participantes) {
            	participanteCohorteFamiliaCasoService.saveOrUpdateParticipanteCohorteFamiliaCaso(participante);
            }
        }
        return "Datos recibidos!";
    }
}
