package ni.org.ics.estudios.movil.controller.seroprevalencia;

import ni.org.ics.estudios.domain.seroprevalencia.EncuestaParticipanteSA;
import ni.org.ics.estudios.service.seroprevalencia.EncuestaParticipanteSAService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Miguel Salinas on 5/5/2017.
 * V1.0
 */
@Controller
@RequestMapping("/movil/*")
public class EncuestaParticipanteSAController {

    private static final Logger logger = LoggerFactory.getLogger(EncuestaParticipanteSAController.class);

    @Resource(name = "encuestaParticipanteSAService")
    private EncuestaParticipanteSAService encuestaParticipanteSAService;

    /**
     * Acepta una solicitud GET para JSON
     * @return JSON
     */
    @RequestMapping(value = "encuestasParticipanteSA/{username}", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    List<EncuestaParticipanteSA> getEncuestasParticipanteSAByUser(@PathVariable String username) {
        logger.info("Descargando toda la informacion de formularios encuesta participante seroprevalencia" +username);
        List<EncuestaParticipanteSA> respuestaList = encuestaParticipanteSAService.getEncuestasParticipantesByUser(username);
        if (respuestaList == null){
            logger.debug("Nulo");
        }
        return respuestaList;
    }

    /**
     * Acepta una solicitud GET para JSON
     * @return JSON
     */
    @RequestMapping(value = "encuestasParticipanteSA", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    List<EncuestaParticipanteSA> getEncuestasParticipanteSA() {
        logger.info("Descargando toda la informacion de formularios encuesta participante seroprevalencia");
        List<EncuestaParticipanteSA> respuestaList = encuestaParticipanteSAService.getEncuestasParticipantes();
        if (respuestaList == null){
            logger.debug("Nulo");
        }
        return respuestaList;
    }

    /**
     * Acepta una solicitud POST con un par�metro JSON
     * @param encuestas Objeto serializado de EncuestaParticipanteSA
     * @return String con el resultado
     */
    @RequestMapping(value = "encuestasParticipanteSA", method = RequestMethod.POST, consumes = "application/json")
    public @ResponseBody String saveEncuestasParticipanteSA(@RequestBody EncuestaParticipanteSA[] encuestas){
        logger.debug("Insertando/Actualizando formularios Encuesta participante seroprevalencia");
        if (encuestas == null){
            logger.debug("Nulo");
            return "No recibi nada!";
        }else{
            List<EncuestaParticipanteSA> encuestasList = Arrays.asList(encuestas);
            for (EncuestaParticipanteSA encuesta : encuestasList){
                encuestaParticipanteSAService.saveOrUpdate(encuesta);
            }
        }
        return "Datos recibidos!";
    }

}
