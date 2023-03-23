package ni.org.ics.estudios.movil.controller.cohortefamilia;

import ni.org.ics.estudios.domain.cohortefamilia.encuestas.EncuestaLactanciaMaterna;
import ni.org.ics.estudios.service.cohortefamilia.EncuestaLactanciaMaternaService;
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
public class EncuestaLactanciaMaternaController {

    private static final Logger logger = LoggerFactory.getLogger(EncuestaLactanciaMaternaController.class);

    @Resource(name = "encuestaLactanciaMaternaService")
    private EncuestaLactanciaMaternaService encuestaLactanciaMaternaService;

    /**
     * Acepta una solicitud GET para JSON
     * @return JSON
     */
    @RequestMapping(value = "encuestasLactanciaMaterna/{username}", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    List<EncuestaLactanciaMaterna> getEncuestasLactanciaMaternaByUser(@PathVariable String username) {
        logger.info("Descargando toda la informacion de formularios encuesta lactancia materna " +username);
        List<EncuestaLactanciaMaterna> respuestaList = encuestaLactanciaMaternaService.getEncuestasLactanciaMaternaByUser(username);
        if (respuestaList == null){
            logger.debug("Nulo");
        }
        return respuestaList;
    }

    /**
     * Acepta una solicitud GET para JSON
     * @return JSON
     */
    @RequestMapping(value = "encuestasLactanciaMaterna", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    List<EncuestaLactanciaMaterna> getEncuestasLactanciaMaterna() {
        logger.info("Descargando toda la informacion de formularios encuesta lactancia materna ");
        List<EncuestaLactanciaMaterna> respuestaList = encuestaLactanciaMaternaService.getEncuestasLactanciaMaterna();
        if (respuestaList == null){
            logger.debug("Nulo");
        }
        return respuestaList;
    }

    /**
     * Acepta una solicitud POST con un par�metro JSON
     * @param encuestas Objeto serializado de EncuestaLactanciaMaterna
     * @return String con el resultado
     */
    @RequestMapping(value = "encuestasLactanciaMaterna", method = RequestMethod.POST, consumes = "application/json")
    public @ResponseBody String saveEncuestasLactanciaMaterna(@RequestBody EncuestaLactanciaMaterna[] encuestas){
        logger.debug("Insertando/Actualizando formularios Encuesta lactancia materna");
        if (encuestas == null){
            logger.debug("Nulo");
            return "No recibi nada!";
        }else{
            List<EncuestaLactanciaMaterna> encuestasList = Arrays.asList(encuestas);
            for (EncuestaLactanciaMaterna encuesta : encuestasList){
                encuestaLactanciaMaternaService.saveOrUpdateEncuestaLactanciaMaterna(encuesta);
            }
        }
        return "Datos recibidos!";
    }

}
