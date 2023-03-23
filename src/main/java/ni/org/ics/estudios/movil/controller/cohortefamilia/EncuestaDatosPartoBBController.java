package ni.org.ics.estudios.movil.controller.cohortefamilia;

import ni.org.ics.estudios.domain.cohortefamilia.encuestas.EncuestaDatosPartoBB;
import ni.org.ics.estudios.service.cohortefamilia.EncuestaDatosPartoBBService;
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
public class EncuestaDatosPartoBBController {

    private static final Logger logger = LoggerFactory.getLogger(EncuestaDatosPartoBBController.class);

    @Resource(name = "encuestaDatosPartoBBService")
    private EncuestaDatosPartoBBService encuestaDatosPartoBBService;

    /**
     * Acepta una solicitud GET para JSON
     * @return JSON
     */
    @RequestMapping(value = "encuestasDatosPartoBB/{username}", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    List<EncuestaDatosPartoBB> getEncuestasDatosPartoBBByUser(@PathVariable String username) {
        logger.info("Descargando toda la informacion de formularios encuesta datos parto BB para el usuario " +username);
        List<EncuestaDatosPartoBB> respuestaList = encuestaDatosPartoBBService.getEncuestasDatosPartoBBByUser(username);
        if (respuestaList == null){
            logger.debug("Nulo");
        }
        return respuestaList;
    }

    /**
     * Acepta una solicitud GET para JSON
     * @return JSON
     */
    @RequestMapping(value = "encuestasDatosPartoBB", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    List<EncuestaDatosPartoBB> getEncuestasDatosPartoBB() {
        logger.info("Descargando toda la informacion de formularios encuesta datos parto BB");
        List<EncuestaDatosPartoBB> respuestaList = encuestaDatosPartoBBService.getEncuestasDatosPartoBB();
        if (respuestaList == null){
            logger.debug("Nulo");
        }
        return respuestaList;
    }

    /**
     * Acepta una solicitud POST con un par�metro JSON
     * @param encuestas Objeto serializado de EncuestaDatosPartoBB
     * @return String con el resultado
     */
    @RequestMapping(value = "encuestasDatosPartoBB", method = RequestMethod.POST, consumes = "application/json")
    public @ResponseBody String saveEncuestasDatosPartoBB(@RequestBody EncuestaDatosPartoBB[] encuestas){
        logger.debug("Insertando/Actualizando formularios Encuesta Datos Partos BB");
        if (encuestas == null){
            logger.debug("Nulo");
            return "No recibi nada!";
        }else{
            List<EncuestaDatosPartoBB> encuestasList = Arrays.asList(encuestas);
            for (EncuestaDatosPartoBB encuesta : encuestasList){
                encuestaDatosPartoBBService.saveOrUpdateEncuestaDatosPartoBB(encuesta);
            }
        }
        return "Datos recibidos!";
    }
}
