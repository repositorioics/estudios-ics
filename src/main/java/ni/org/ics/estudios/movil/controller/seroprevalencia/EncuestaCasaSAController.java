package ni.org.ics.estudios.movil.controller.seroprevalencia;

import ni.org.ics.estudios.domain.seroprevalencia.EncuestaCasaSA;
import ni.org.ics.estudios.service.seroprevalencia.EncuestaCasaSAService;
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
public class EncuestaCasaSAController {

    private static final Logger logger = LoggerFactory.getLogger(EncuestaCasaSAController.class);

    @Resource(name = "encuestaCasaSAService")
    private EncuestaCasaSAService encuestaCasaSAService;

    /**
     * Acepta una solicitud GET para JSON
     * @return JSON
     */
    @RequestMapping(value = "encuestasCasaSA/{username}", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    List<EncuestaCasaSA> getEncuestasCasaSAByUser(@PathVariable String username) {
        logger.info("Descargando toda la informacion de formularios encuesta casa seroprevalencia para el usuario " +username);
        List<EncuestaCasaSA> respuestaList = encuestaCasaSAService.getEncuestasCasaByUser(username);
        if (respuestaList == null){
            logger.debug("Nulo");
        }
        return respuestaList;
    }

    /**
     * Acepta una solicitud GET para JSON
     * @return JSON
     */
    @RequestMapping(value = "encuestasCasaSA", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    List<EncuestaCasaSA> getEncuestasCasaSA() {
        logger.info("Descargando toda la informacion de formularios encuesta casa seroprevalencia");
        List<EncuestaCasaSA> respuestaList = encuestaCasaSAService.getEncuestasCasa();
        if (respuestaList == null){
            logger.debug("Nulo");
        }
        return respuestaList;
    }

    /**
     * Acepta una solicitud POST con un par�metro JSON
     * @param encuestas Objeto serializado de EncuestaCasaSA
     * @return String con el resultado
     */
    @RequestMapping(value = "encuestasCasaSA", method = RequestMethod.POST, consumes = "application/json")
    public @ResponseBody String saveEncuestasCasaSA(@RequestBody EncuestaCasaSA[] encuestas){
        logger.debug("Insertando/Actualizando formularios Encuesta Casa seroprevalencia");
        if (encuestas == null){
            logger.debug("Nulo");
            return "No recibi nada!";
        }else{
            List<EncuestaCasaSA> encuestasList = Arrays.asList(encuestas);
            for (EncuestaCasaSA encuesta : encuestasList){
                encuestaCasaSAService.saveOrUpdate(encuesta);
            }
        }
        return "Datos recibidos!";
    }

}
