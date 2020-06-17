package ni.org.ics.estudios.movil.controller.covid19;

import ni.org.ics.estudios.domain.covid19.VisitaSeguimientoCasoCovid19;
import ni.org.ics.estudios.service.covid.CovidService;
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
 * Created by miguel on 14/6/2020.
 */
@Controller
@RequestMapping("/movil/*")
public class VisitaSeguimientoCasoCovid19Controller {

    private static final Logger logger = LoggerFactory.getLogger(VisitaSeguimientoCasoCovid19Controller.class);

    @Resource(name = "CovidService")
    private CovidService covidService;

    @RequestMapping(value = "visitasSeguimientosCasoCovid19", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    List<VisitaSeguimientoCasoCovid19> getVisitasSeguimientosCasosCovid19(){
        logger.info("Descargando toda la informacion de visitas seguimiento covid19");
        List<VisitaSeguimientoCasoCovid19> visitaSeguimientoCasoCovid19List = covidService.getVisitaSeguimientoCasos();
        if (visitaSeguimientoCasoCovid19List == null){
            logger.debug("Nulo");
        }
        return  visitaSeguimientoCasoCovid19List;
    }

    @RequestMapping(value = "visitasSeguimientosCasoCovid19", method = RequestMethod.POST, consumes = "application/json")
    public @ResponseBody String saveVisitaSeguimientoCasoCovid19(@RequestBody VisitaSeguimientoCasoCovid19[] objetos){
        logger.debug("Insertando/Actualizando visitas seguimiento covid19");
        if (objetos == null){
            logger.debug("Nulo");
            return "No recibi nada!";
        }else{
            List<VisitaSeguimientoCasoCovid19> visitaSeguimientoCasoCovid19List = Arrays.asList(objetos);
            for(VisitaSeguimientoCasoCovid19 visitaSeguimientoCasoCovid19 : visitaSeguimientoCasoCovid19List) {
                covidService.saveOrUpdateVisitaSeguimientoCasoCovid19(visitaSeguimientoCasoCovid19);
            }
        }
        return "Datos recibidos!";
    }
}
