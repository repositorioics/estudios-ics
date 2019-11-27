package ni.org.ics.estudios.movil.controller.huella;

import ni.org.ics.estudios.domain.huella.Huella;
import ni.org.ics.estudios.service.huella.HuellaService;
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
 * Created by Miguel Salinas on 13/11/2019.
 * V1.0
 */
@Controller
@RequestMapping("/movil/*")
public class HuellasController {

    private static final Logger logger = LoggerFactory.getLogger(HuellasController.class);

    @Resource(name = "huellaService")
    private HuellaService huellaService;

    @RequestMapping(value = "huellas", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    List<Huella> getBarrios(){
        logger.info("Descargando toda la informacion de los datos de los huellas");
        List<Huella> barrios = huellaService.getHuellas();
        if (barrios == null){
            logger.debug("Nulo");
        }
        return  barrios;
    }

    @RequestMapping(value = "huellas", method = RequestMethod.POST, consumes = "application/json")
    public @ResponseBody String saveCasas(@RequestBody Huella[] objetos){
        logger.debug("Insertando/Actualizando huellas");
        if (objetos == null){
            logger.debug("Nulo");
            return "No recibi nada!";
        }else{
            List<Huella> huellas = Arrays.asList(objetos);
            for(Huella huella : huellas) {
                huellaService.saveOrUpdateHuella(huella);
            }
        }
        return "Datos recibidos!";
    }
}
