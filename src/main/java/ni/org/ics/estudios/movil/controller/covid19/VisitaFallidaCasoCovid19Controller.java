package ni.org.ics.estudios.movil.controller.covid19;

import ni.org.ics.estudios.domain.covid19.VisitaFallidaCasoCovid19;
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
public class VisitaFallidaCasoCovid19Controller {

    private static final Logger logger = LoggerFactory.getLogger(VisitaFallidaCasoCovid19Controller.class);

    @Resource(name = "CovidService")
    private CovidService covidService;

    @RequestMapping(value = "visitasFallidasCasoCovid19", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    List<VisitaFallidaCasoCovid19> getVisitasFallidasCasoCovid19(){
        logger.info("Descargando toda la informacion de visitas fallidas casos covid19");
        List<VisitaFallidaCasoCovid19> visitaFallidaCasoCovid19List = covidService.getVisitasFallidasCasosActivosCovid19();
        if (visitaFallidaCasoCovid19List == null){
            logger.debug("Nulo");
        }
        return  visitaFallidaCasoCovid19List;
    }

    @RequestMapping(value = "visitasFallidasCasoCovid19", method = RequestMethod.POST, consumes = "application/json")
    public @ResponseBody String saveVisitasFallidasCasoCovid19(@RequestBody VisitaFallidaCasoCovid19[] objetos){
        logger.debug("Insertando/Actualizando visitas fallidas casos covid19");
        if (objetos == null){
            logger.debug("Nulo");
            return "No recibi nada!";
        }else{
            List<VisitaFallidaCasoCovid19> visitaFallidaCasoCovid19List = Arrays.asList(objetos);
            for(VisitaFallidaCasoCovid19 visitaFallidaCasoCovid19 : visitaFallidaCasoCovid19List) {
                covidService.saveOrUpdateVisitaFallidaCasoCovid19(visitaFallidaCasoCovid19);
            }
        }
        return "Datos recibidos!";
    }
}
