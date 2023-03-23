package ni.org.ics.estudios.movil.controller.cohortefamilia;

import ni.org.ics.estudios.domain.cohortefamilia.encuestas.EncuestaParticipante;
import ni.org.ics.estudios.service.cohortefamilia.EncuestaParticipanteService;
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
public class EncuestaParticipanteController {

    private static final Logger logger = LoggerFactory.getLogger(EncuestaParticipanteController.class);

    @Resource(name = "encuestaParticipanteService")
    private EncuestaParticipanteService encuestaParticipanteService;

    /**
     * Acepta una solicitud GET para JSON
     * @return JSON
     */
    @RequestMapping(value = "encuestasParticipante/{username}", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    List<EncuestaParticipante> getEncuestasParticipanteByUser(@PathVariable String username) {
        logger.info("Descargando toda la informacion de formularios encuesta participante " +username);
        List<EncuestaParticipante> respuestaList = encuestaParticipanteService.getEncuestasParticipantesByUser(username);
        if (respuestaList == null){
            logger.debug("Nulo");
        }
        return respuestaList;
    }

    /**
     * Acepta una solicitud GET para JSON
     * @return JSON
     */
    @RequestMapping(value = "encuestasParticipante", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    List<EncuestaParticipante> getEncuestasParticipante() {
        logger.info("Descargando toda la informacion de formularios encuesta participante ");
        List<EncuestaParticipante> respuestaList = encuestaParticipanteService.getEncuestasParticipantes();
        if (respuestaList == null){
            logger.debug("Nulo");
        }
        return respuestaList;
    }

    /**
     * Acepta una solicitud POST con un par�metro JSON
     * @param encuestas Objeto serializado de EncuestaParticipante
     * @return String con el resultado
     */
    @RequestMapping(value = "encuestasParticipante", method = RequestMethod.POST, consumes = "application/json")
    public @ResponseBody String saveEncuestasParticipante(@RequestBody EncuestaParticipante[] encuestas){
        logger.debug("Insertando/Actualizando formularios Encuesta participante");
        if (encuestas == null){
            logger.debug("Nulo");
            return "No recibi nada!";
        }else{
            List<EncuestaParticipante> encuestasList = Arrays.asList(encuestas);
            for (EncuestaParticipante encuesta : encuestasList){
                encuestaParticipanteService.saveOrUpdate(encuesta);
            }
        }
        return "Datos recibidos!";
    }

}
