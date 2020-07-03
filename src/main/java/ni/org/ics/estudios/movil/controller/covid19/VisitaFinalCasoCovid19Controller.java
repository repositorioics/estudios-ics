package ni.org.ics.estudios.movil.controller.covid19;

import ni.org.ics.estudios.domain.covid19.VisitaFinalCasoCovid19;
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
 * Created by Miguel Salinas on 01/07/2020.
 */
@Controller
@RequestMapping("/movil/*")
public class VisitaFinalCasoCovid19Controller {

    private static final Logger logger = LoggerFactory.getLogger(VisitaFinalCasoCovid19Controller.class);

    @Resource(name = "CovidService")
    private CovidService covidService;

    @RequestMapping(value = "visitasFinalesCovid19", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    List<VisitaFinalCasoCovid19> getVisitasFinalesCasosCovid19(){
        logger.info("Descargando toda la informacion de visitas finales covid19");
        List<VisitaFinalCasoCovid19> visitaFinalCasoCovid19List = covidService.getVisitasFinalesCasosCovid19();
        if (visitaFinalCasoCovid19List == null){
            logger.debug("Nulo");
        }
        return  visitaFinalCasoCovid19List;
    }

    @RequestMapping(value = "visitasFinalesCovid19", method = RequestMethod.POST, consumes = "application/json")
    public @ResponseBody String saveVisitaFinalCasoCovid19(@RequestBody VisitaFinalCasoCovid19[] objetos){
        logger.debug("Insertando/Actualizando visitas finales covid19");
        if (objetos == null){
            logger.debug("Nulo");
            return "No recibi nada!";
        }else{
            List<VisitaFinalCasoCovid19> visitaFinalCasoCovid19List = Arrays.asList(objetos);
            for(VisitaFinalCasoCovid19 visitaFinalCasoCovid19 : visitaFinalCasoCovid19List) {
                covidService.saveOrUpdateVisitaFinalCasoCovid19(visitaFinalCasoCovid19);
            }
        }
        return "Datos recibidos!";
    }
}
