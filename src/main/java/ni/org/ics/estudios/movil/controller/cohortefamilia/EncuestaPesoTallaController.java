package ni.org.ics.estudios.movil.controller.cohortefamilia;

import ni.org.ics.estudios.domain.cohortefamilia.encuestas.EncuestaPesoTalla;
import ni.org.ics.estudios.service.cohortefamilia.EncuestaPesoTallaService;
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
public class EncuestaPesoTallaController {

    private static final Logger logger = LoggerFactory.getLogger(EncuestaPesoTallaController.class);

    @Resource(name = "encuestaPesoTallaService")
    private EncuestaPesoTallaService encuestaPesoTallaService;

    /**
     * Acepta una solicitud GET para JSON
     * @return JSON
     */
    @RequestMapping(value = "encuestasPesoTalla/{username}", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    List<EncuestaPesoTalla> getEncuestasPesoTalla(@PathVariable String username) {
        logger.info("Descargando toda la informacion de formularios encuesta Peso y Talla " +username);
        List<EncuestaPesoTalla> respuestaList = encuestaPesoTallaService.getEncuestasPesoTallaByUser(username);
        if (respuestaList == null){
            logger.debug("Nulo");
        }
        return respuestaList;
    }

    /**
     * Acepta una solicitud GET para JSON
     * @return JSON
     */
    @RequestMapping(value = "encuestasPesoTalla", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    List<EncuestaPesoTalla> getEncuestasPesoTalla() {
        logger.info("Descargando toda la informacion de formularios encuesta Peso y Talla ");
        List<EncuestaPesoTalla> respuestaList = encuestaPesoTallaService.getEncuestasPesoTalla();
        if (respuestaList == null){
            logger.debug("Nulo");
        }
        return respuestaList;
    }

    /**
     * Acepta una solicitud POST con un par�metro JSON
     * @param encuestas Objeto serializado de EncuestaPesoTalla
     * @return String con el resultado
     */
    @RequestMapping(value = "encuestasPesoTalla", method = RequestMethod.POST, consumes = "application/json")
    public @ResponseBody String saveEncuestasPesoTalla(@RequestBody EncuestaPesoTalla[] encuestas){
        logger.debug("Insertando/Actualizando formularios Encuesta Peso y Talla");
        if (encuestas == null){
            logger.debug("Nulo");
            return "No recibi nada!";
        }else{
            List<EncuestaPesoTalla> encuestasList = Arrays.asList(encuestas);
            for (EncuestaPesoTalla encuesta : encuestasList){
                encuestaPesoTallaService.saveOrUpdateEncuestaPesoTalla(encuesta);
            }
        }
        return "Datos recibidos!";
    }

}
