package ni.org.ics.estudios.movil.controller.cohortefamilia.casos;

import ni.org.ics.estudios.domain.cohortefamilia.casos.SensorCaso;
import ni.org.ics.estudios.service.cohortefamilia.casos.SensoresCasoService;
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
 * Created by Miguel Salinas on 11/02/2020.
 * V1.0
 */
@Controller
@RequestMapping("/movil/*")
public class SensoresCasosController {

    private static final Logger logger = LoggerFactory.getLogger(SensoresCasosController.class);

    @Resource(name = "sensoresCasoService")
    private SensoresCasoService sensoresCasoService;

    @RequestMapping(value = "sensorescasos", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    List<SensorCaso> getVisitaFinalCasoCasos(){
        logger.info("Descargando toda la informacion de los sensorescasos de las casas de la cohorte familia con caso positivo");
        List<SensorCaso> sensoresCasos = sensoresCasoService.getSensoresCasos();
        if (sensoresCasos == null){
            logger.debug("Nulo");
        }
        return  sensoresCasos;
    }

    @RequestMapping(value = "sensorescasos", method = RequestMethod.POST, consumes = "application/json")
    public @ResponseBody String saveVisitaFinalCasos(@RequestBody SensorCaso[] objetos){
        logger.debug("Insertando/Actualizando los datos sensorescasos de las casas cohorte familia con casos positivos");
        if (objetos == null){
            logger.debug("Nulo");
            return "No recibi nada!";
        }else{
            List<SensorCaso> visitas = Arrays.asList(objetos);
            for(SensorCaso visita : visitas) {
                sensoresCasoService.saveOrUpdateSensorCaso(visita);
            }
        }
        return "Datos recibidos!";
    }
}
