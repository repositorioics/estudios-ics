package ni.org.ics.estudios.movil.controller.cohortefamilia.casos;

import ni.org.ics.estudios.domain.cohortefamilia.casos.VisitaFallidaCaso;
import ni.org.ics.estudios.service.cohortefamilia.casos.VisitaFallidaCasoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Miguel Salinas on 5/11/2017.
 * V1.0
 */
@Controller
@RequestMapping("/movil/*")
public class VisitaFallidaCasoController {

    private static final Logger logger = LoggerFactory.getLogger(VisitaFallidaCasoController.class);

    @Resource(name = "visitaFallidaCasoService")
    private VisitaFallidaCasoService visitaFallidaCasoService;

    @RequestMapping(value = "visitasfallidascasos", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    List<VisitaFallidaCaso> getVisitaFallidaCasos(){
        logger.info("Descargando toda la informacion de los datos de visitas fallidas de los participantes de las casas de la cohorte familia con caso positivo");
        List<VisitaFallidaCaso> visitas = visitaFallidaCasoService.getVisitaFallidaCasos();
        if (visitas == null){
            logger.debug("Nulo");
        }
        return  visitas;
    }

    @RequestMapping(value = "visitasfallidascasos", method = RequestMethod.POST, consumes = "application/json")
    public @ResponseBody String saveVisitaSeguimientoCasos(@RequestBody VisitaFallidaCaso[] objetos){
        logger.debug("Insertando/Actualizando los datos de visitas fallidas de participantes de las casas cohorte familia con casos positivos");
        if (objetos == null){
            logger.debug("Nulo");
            return "No recibi nada!";
        }else{
            List<VisitaFallidaCaso> visitas = Arrays.asList(objetos);
            for(VisitaFallidaCaso visita : visitas) {
            	visitaFallidaCasoService.saveOrUpdateVisitaFallidaCaso(visita);
            }
        }
        return "Datos recibidos!";
    }
}
