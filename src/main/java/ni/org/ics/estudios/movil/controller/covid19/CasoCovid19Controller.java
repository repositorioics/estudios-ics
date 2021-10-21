package ni.org.ics.estudios.movil.controller.covid19;

import ni.org.ics.estudios.domain.covid19.CasoCovid19;
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
 * Created by miguel on 4/6/2020.
 */
@Controller
@RequestMapping("/movil/*")
public class CasoCovid19Controller {
    private static final Logger logger = LoggerFactory.getLogger(CasoCovid19Controller.class);

    @Resource(name = "CovidService")
    private CovidService covidService;

    @RequestMapping(value = "casosCovid19", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    List<CasoCovid19> getCasasCasosCovid19(){
        logger.info("Descargando toda la informacion de casos en seguimiento covid19");
        List<CasoCovid19> casoCovid19List = covidService.getCasosCovid19();
        if (casoCovid19List == null){
            logger.debug("Nulo");
        }
        return  casoCovid19List;
    }

    @RequestMapping(value = "casosCovid19", method = RequestMethod.POST, consumes = "application/json")
    public @ResponseBody String saveCasasCasosCovid19(@RequestBody CasoCovid19[] objetos){
        logger.debug("Insertando/Actualizando casos en seguimiento covid19");
        if (objetos == null){
            logger.debug("Nulo");
            return "No recibi nada!";
        }else{
            List<CasoCovid19> covid19List = Arrays.asList(objetos);
            for(CasoCovid19 casoCovid19 : covid19List) {
                casoCovid19.setFechaRecibido(new Date());
                covidService.saveOrUpdateCasoCovid19(casoCovid19);
            }
        }
        return "Datos recibidos!";
    }
}
