package ni.org.ics.estudios.movil.controller.covid19;

import ni.org.ics.estudios.domain.covid19.CandidatoTransmisionCovid19;
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
 * Created by miguel on 17/6/2020.
 */
@Controller
@RequestMapping("/movil/*")
public class CandidatoTransmisionCovid19Controller {
    private static final Logger logger = LoggerFactory.getLogger(CasoCovid19Controller.class);

    @Resource(name = "CovidService")
    private CovidService covidService;

    @RequestMapping(value = "candidatosTCovid19", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    List<CandidatoTransmisionCovid19> getCasasCasosCovid19(){
        logger.info("Descargando toda la informacion de  Candidatos estudio Transmision Covid19");
        List<CandidatoTransmisionCovid19> pendientesTransmisionCovid19 = covidService.getCandidatosPendientesTransmisionCovid19();
        if (pendientesTransmisionCovid19 == null){
            logger.debug("Nulo");
        }
        return  pendientesTransmisionCovid19;
    }

    @RequestMapping(value = "candidatosTCovid19", method = RequestMethod.POST, consumes = "application/json")
    public @ResponseBody String saveCasasCasosCovid19(@RequestBody CandidatoTransmisionCovid19[] objetos){
        logger.debug("Insertando/Actualizando Candidatos estudio Transmision Covid19");
        if (objetos == null){
            logger.debug("Nulo");
            return "No recibi nada!";
        }else{
            List<CandidatoTransmisionCovid19> candidatoTransmisionCovid19List = Arrays.asList(objetos);
            for(CandidatoTransmisionCovid19 candidatoTransmisionCovid19 : candidatoTransmisionCovid19List) {
                covidService.saveOrUpdateCandidatoTransmisionCovid19(candidatoTransmisionCovid19);
            }
        }
        return "Datos recibidos!";
    }
}
