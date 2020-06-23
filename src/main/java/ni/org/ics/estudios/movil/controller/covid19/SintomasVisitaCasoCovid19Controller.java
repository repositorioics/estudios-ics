package ni.org.ics.estudios.movil.controller.covid19;

import ni.org.ics.estudios.domain.covid19.SintomasVisitaCasoCovid19;
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
 * Created by miguel on 22/6/2020.
 */
@Controller
@RequestMapping("/movil/*")
public class SintomasVisitaCasoCovid19Controller {
    private static final Logger logger = LoggerFactory.getLogger(SintomasVisitaCasoCovid19Controller.class);

    @Resource(name = "CovidService")
    private CovidService covidService;

    @RequestMapping(value = "sintomasCasosCovid19", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    List<SintomasVisitaCasoCovid19> getSintomasVisitasCasosCovid19(){
        logger.info("Descargando toda la informacion de sintoms visitas de seguimientos covid19");
        List<SintomasVisitaCasoCovid19> sintomasVisitaCasoCovid19List = covidService.getSintomasVisitasCasosCovid19();
        if (sintomasVisitaCasoCovid19List == null){
            logger.debug("Nulo");
        }
        return  sintomasVisitaCasoCovid19List;
    }

    @RequestMapping(value = "sintomasCasosCovid19", method = RequestMethod.POST, consumes = "application/json")
    public @ResponseBody String saveSintomasVisitasCasosCovid19(@RequestBody SintomasVisitaCasoCovid19[] objetos){
        logger.debug("Insertando/Actualizando sintomas visitas de seguimientos covid19");
        if (objetos == null){
            logger.debug("Nulo");
            return "No recibi nada!";
        }else{
            List<SintomasVisitaCasoCovid19> sintomasVisitaCasoCovid19List = Arrays.asList(objetos);
            for(SintomasVisitaCasoCovid19 sintomasVisitaCasoCovid19 : sintomasVisitaCasoCovid19List) {
                covidService.saveOrUpdateSintomasVisitaCasoCovid19(sintomasVisitaCasoCovid19);
            }
        }
        return "Datos recibidos!";
    }
}
