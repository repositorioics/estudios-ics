package ni.org.ics.estudios.movil.controller.covid19;

import ni.org.ics.estudios.domain.covid19.SintomasVisitaFinalCovid19;
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
import java.util.Date;
import java.util.List;

/**
 * Created by miguel on 22/6/2020.
 */
@Controller
@RequestMapping("/movil/*")
public class SintomasVisitaFinalCovid19Controller {
    private static final Logger logger = LoggerFactory.getLogger(SintomasVisitaFinalCovid19Controller.class);

    @Resource(name = "CovidService")
    private CovidService covidService;

    @RequestMapping(value = "sintVisitasFinalesCovid19", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    List<SintomasVisitaFinalCovid19> getSintomasVisitasFinalesCovid19(){
        logger.info("Descargando toda la informacion de sintoms visitas finales covid19");
        List<SintomasVisitaFinalCovid19> sintomasVisitaFinalCovid19List = covidService.getSintomasVisitasFinalesCovid19();
        if (sintomasVisitaFinalCovid19List == null){
            logger.debug("Nulo");
        }
        return  sintomasVisitaFinalCovid19List;
    }

    @RequestMapping(value = "sintVisitasFinalesCovid19", method = RequestMethod.POST, consumes = "application/json")
    public @ResponseBody String saveSintomasVisitasFinalesCovid19(@RequestBody SintomasVisitaFinalCovid19[] objetos){
        logger.debug("Insertando/Actualizando sintomas visitas finales covid19");
        if (objetos == null){
            logger.debug("Nulo");
            return "No recibi nada!";
        }else{
            List<SintomasVisitaFinalCovid19> sintomasVisitaFinalCovid19List = Arrays.asList(objetos);
            for(SintomasVisitaFinalCovid19 sintomasVisitaFinalCovid19 : sintomasVisitaFinalCovid19List) {
                sintomasVisitaFinalCovid19.setFechaRecibido(new Date());
                covidService.saveOrUpdateSintomasVisitaFinalCovid19(sintomasVisitaFinalCovid19);
            }
        }
        return "Datos recibidos!";
    }
}
