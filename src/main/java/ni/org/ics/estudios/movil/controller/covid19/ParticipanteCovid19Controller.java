package ni.org.ics.estudios.movil.controller.covid19;

import ni.org.ics.estudios.domain.covid19.ParticipanteCasoCovid19;
import ni.org.ics.estudios.domain.covid19.ParticipanteCovid19;
import ni.org.ics.estudios.service.covid.CovidService;
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
 * Created by miguel on 4/6/2020.
 */
@Controller
@RequestMapping("/movil/*")
public class ParticipanteCovid19Controller {
    private static final Logger logger = LoggerFactory.getLogger(ParticipanteCovid19Controller.class);

    @Resource(name = "CovidService")
    private CovidService covidService;

    @RequestMapping(value = "participantesCovid19", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    List<ParticipanteCovid19> getParticipantesCasoCovid19(){
        logger.info("Descargando toda la informacion de participantes en seguimiento covid19");
        List<ParticipanteCovid19> participanteCasoCovid19List = covidService.getParticipantesCovid19();
        if (participanteCasoCovid19List == null){
            logger.debug("Nulo");
        }
        return  participanteCasoCovid19List;
    }

    @RequestMapping(value = "participantesCovid19", method = RequestMethod.POST, consumes = "application/json")
    public @ResponseBody String saveParticipantesCasoCovid19(@RequestBody ParticipanteCovid19[] objetos){
        logger.debug("Insertando/Actualizando participantes en seguimiento covid19");
        if (objetos == null){
            logger.debug("Nulo");
            return "No recibi nada!";
        }else{
            List<ParticipanteCovid19> participanteCasoCovid19List = Arrays.asList(objetos);
            for(ParticipanteCovid19 participanteCasoCovid19 : participanteCasoCovid19List) {
                covidService.saveOrUpdateParticipanteCovid19(participanteCasoCovid19);
            }
        }
        return "Datos recibidos!";
    }

}
