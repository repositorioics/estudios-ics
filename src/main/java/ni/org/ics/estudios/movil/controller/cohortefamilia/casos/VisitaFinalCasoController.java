package ni.org.ics.estudios.movil.controller.cohortefamilia.casos;

import ni.org.ics.estudios.domain.cohortefamilia.casos.VisitaFinalCaso;
import ni.org.ics.estudios.service.cohortefamilia.casos.VisitaFinalCasoService;
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
public class VisitaFinalCasoController {

    private static final Logger logger = LoggerFactory.getLogger(VisitaFinalCasoController.class);

    @Resource(name = "visitaFinalCasoService")
    private VisitaFinalCasoService visitaFinalCasoService;

    @RequestMapping(value = "visitasfinalescasos", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    List<VisitaFinalCaso> getVisitaFinalCasoCasos(){
        logger.info("Descargando toda la informacion de los datos de visitas de los participantes de las casas de la cohorte familia con caso positivo");
        List<VisitaFinalCaso> visitas = visitaFinalCasoService.getVisitaFinalCasos();
        if (visitas == null){
            logger.debug("Nulo");
        }
        return  visitas;
    }

    @RequestMapping(value = "visitasfinalescasos", method = RequestMethod.POST, consumes = "application/json")
    public @ResponseBody String saveVisitaFinalCasos(@RequestBody VisitaFinalCaso[] objetos){
        logger.debug("Insertando/Actualizando los datos de visitas de participantes de las casas cohorte familia con casos positivos");
        if (objetos == null){
            logger.debug("Nulo");
            return "No recibi nada!";
        }else{
            List<VisitaFinalCaso> visitas = Arrays.asList(objetos);
            for(VisitaFinalCaso visita : visitas) {
            	visitaFinalCasoService.saveOrUpdateVisitaFinalCasoCaso(visita);
            }
        }
        return "Datos recibidos!";
    }
}
