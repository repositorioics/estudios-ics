package ni.org.ics.estudios.movil.controller.covid19;

import ni.org.ics.estudios.domain.covid19.ParticipanteCasoCovid19;
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
import java.util.Date;
import java.util.List;

/**
 * Created by miguel on 4/6/2020.
 */
@Controller
@RequestMapping("/movil/*")
public class ParticipanteCasoCovid19Controller {
    private static final Logger logger = LoggerFactory.getLogger(ParticipanteCasoCovid19Controller.class);

    @Resource(name = "CovidService")
    private CovidService covidService;

    @RequestMapping(value = "participantesCasoCovid19", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    List<ParticipanteCasoCovid19> getParticipantesCasoCovid19(){
        logger.info("Descargando toda la informacion de participantes en seguimiento covid19");
        List<ParticipanteCasoCovid19> participanteCasoCovid19List = covidService.getParticipantesCasosCovid19();
        if (participanteCasoCovid19List == null){
            logger.debug("Nulo");
        }
        return  participanteCasoCovid19List;
    }

    @RequestMapping(value = "participantesCasoCovid19", method = RequestMethod.POST, consumes = "application/json")
    public @ResponseBody String saveParticipantesCasoCovid19(@RequestBody ParticipanteCasoCovid19[] objetos){
        logger.debug("Insertando/Actualizando participantes en seguimiento covid19");
        if (objetos == null){
            logger.debug("Nulo");
            return "No recibi nada!";
        }else{
            List<ParticipanteCasoCovid19> participanteCasoCovid19List = Arrays.asList(objetos);
            for(ParticipanteCasoCovid19 participanteCasoCovid19 : participanteCasoCovid19List) {
                ParticipanteCasoCovid19 tmp = covidService.getParticipanteCasoCovid19ByCodCasoPart(participanteCasoCovid19.getCodigoCasoParticipante());
                if (tmp==null || !tmp.getConsentimiento().equals("1")) {
                    participanteCasoCovid19.setFechaRecibido(new Date());
                    covidService.saveOrUpdateParticipanteCasoCovid19(participanteCasoCovid19);
                }
            }
        }
        return "Datos recibidos!";
    }

}
