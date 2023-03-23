package ni.org.ics.estudios.movil.controller.cohortefamilia;

import ni.org.ics.estudios.domain.cohortefamilia.PreTamizaje;
import ni.org.ics.estudios.service.cohortefamilia.PreTamizajeService;
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
public class PreTamizajeController {

    private static final Logger logger = LoggerFactory.getLogger(PreTamizajeController.class);

    @Resource(name = "preTamizajeService")
    private PreTamizajeService preTamizajeService;

    /**
     * Acepta una solicitud GET para JSON
     * @return JSON
     */
    @RequestMapping(value = "preTamizajes/{username}", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    List<PreTamizaje> getPreTamizajesByUser(@PathVariable String username) {
        logger.info("Descargando toda la informacion de formularios PreTamizaje" +username);
        List<PreTamizaje> respuestaList = preTamizajeService.getPreTamizajesByUser(username);
        if (respuestaList == null){
            logger.debug("Nulo");
        }
        return respuestaList;
    }

    /**
     * Acepta una solicitud GET para JSON
     * @return JSON
     */
    @RequestMapping(value = "preTamizajes", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    List<PreTamizaje> getPreTamizajes() {
        logger.info("Descargando toda la informacion de formularios PreTamizaje");
        List<PreTamizaje> respuestaList = preTamizajeService.getPreTamizajes();
        if (respuestaList == null){
            logger.debug("Nulo");
        }
        return respuestaList;
    }

    /**
     * Acepta una solicitud POST con un par�metro JSON
     * @param preTamizajesArr Objeto serializado de PreTamizaje
     * @return String con el resultado
     */
    @RequestMapping(value = "preTamizajes", method = RequestMethod.POST, consumes = "application/json")
    public @ResponseBody String savePreTamizajes(@RequestBody PreTamizaje[] preTamizajesArr){
        logger.debug("Insertando/Actualizando formularios PreTamizaje");
        if (preTamizajesArr == null){
            logger.debug("Nulo");
            return "No recibi nada!";
        }else{
            List<PreTamizaje> preTamizajeList = Arrays.asList(preTamizajesArr);
            for (PreTamizaje preTamizaje : preTamizajeList){
                preTamizajeService.saveOrUpdate(preTamizaje);
            }
        }
        return "Datos recibidos!";
    }


}
