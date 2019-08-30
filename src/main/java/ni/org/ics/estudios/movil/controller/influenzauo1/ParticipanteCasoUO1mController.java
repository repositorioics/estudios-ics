package ni.org.ics.estudios.movil.controller.influenzauo1;

import ni.org.ics.estudios.domain.influenzauo1.ParticipanteCasoUO1;
import ni.org.ics.estudios.service.influenzauo1.ParticipanteCasoUO1Service;
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

@Controller
@RequestMapping("/movil/*")
public class ParticipanteCasoUO1mController {
    private static final Logger logger = LoggerFactory.getLogger(ParticipanteCasoUO1mController.class);
    @Resource(name = "participanteCasoUO1Service")
    private ParticipanteCasoUO1Service participanteCasoUO1Service;

    @RequestMapping(value = "participantesCasoUO1", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    List<ParticipanteCasoUO1> getParticipantesCasoUO1(){
        logger.info("Descargando toda la informacion de casos positivos activos influenza UO1");
        List<ParticipanteCasoUO1> casosActivos = this.participanteCasoUO1Service.getCasosActivos();
        if (casosActivos == null){
            logger.debug("Nulo");
        }
        return  casosActivos;
    }

    @RequestMapping(value = "participantesCasoUO1", method = RequestMethod.POST, consumes = "application/json")
    public @ResponseBody String saveParticipantesCasoUO1(@RequestBody ParticipanteCasoUO1[] objetos){
        logger.debug("Insertando/Actualizando casos positivos activos influenza UO1");
        if (objetos == null){
            logger.debug("Nulo");
            return "No recibi nada!";
        }else{
            List<ParticipanteCasoUO1> casoUO1List = Arrays.asList(objetos);
            for(ParticipanteCasoUO1 casoUO1 : casoUO1List) {
                this.participanteCasoUO1Service.saveOrUpdate(casoUO1);
            }
        }
        return "Datos recibidos!";
    }
}
