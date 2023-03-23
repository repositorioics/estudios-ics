package ni.org.ics.estudios.movil.controller.cohortefamilia.casos;

import ni.org.ics.estudios.domain.cohortefamilia.casos.VisitaSeguimientoCaso;
import ni.org.ics.estudios.service.cohortefamilia.casos.VisitaSeguimientoCasoService;

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
public class VisitaSeguimientoCasoController {

    private static final Logger logger = LoggerFactory.getLogger(VisitaSeguimientoCasoController.class);

    @Resource(name = "visitaSeguimientoCasoService")
    private VisitaSeguimientoCasoService visitaSeguimientoCasoService;

    @RequestMapping(value = "visitascasos", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    List<VisitaSeguimientoCaso> getVisitaSeguimientoCasos(){
        logger.info("Descargando toda la informacion de los datos de visitas de los participantes de las casas de la cohorte familia con caso positivo");
        List<VisitaSeguimientoCaso> visitas = visitaSeguimientoCasoService.getVisitaSeguimientoCasos();
        if (visitas == null){
            logger.debug("Nulo");
        }
        return  visitas;
    }

    @RequestMapping(value = "visitascasos", method = RequestMethod.POST, consumes = "application/json")
    public @ResponseBody String saveVisitaSeguimientoCasos(@RequestBody VisitaSeguimientoCaso[] objetos){
        logger.debug("Insertando/Actualizando los datos de visitas de participantes de las casas cohorte familia con casos positivos");
        if (objetos == null){
            logger.debug("Nulo");
            return "No recibi nada!";
        }else{
            List<VisitaSeguimientoCaso> visitas = Arrays.asList(objetos);
            for(VisitaSeguimientoCaso visita : visitas) {
            	visitaSeguimientoCasoService.saveOrUpdateVisitaSeguimientoCaso(visita);
            }
        }
        return "Datos recibidos!";
    }
}
