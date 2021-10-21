package ni.org.ics.estudios.movil.controller.covid19;

import ni.org.ics.estudios.domain.covid19.CuestionarioCovid19;
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
 * Created by miguel on 17/9/2020.
 */
@Controller
@RequestMapping("/movil/*")
public class CuestionarioCovid19Controller {

    private static final Logger logger = LoggerFactory.getLogger(CuestionarioCovid19Controller.class);

    @Resource(name = "CovidService")
    private CovidService covidService;

    @RequestMapping(value = "cuestionariosCovid19", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    List<CuestionarioCovid19> getCuestionariosCovid19(){
        logger.info("Descargando toda la informacion de CuestionarioCovid19");
        List<CuestionarioCovid19> cuestionariosCovid19 = covidService.getCuestionariosCovid19();
        if (cuestionariosCovid19 == null){
            logger.debug("Nulo");
        }
        return  cuestionariosCovid19;
    }

    @RequestMapping(value = "cuestionariosCovid19", method = RequestMethod.POST, consumes = "application/json")
    public @ResponseBody String saveCuestionarioCovid19(@RequestBody CuestionarioCovid19[] objetos){
        logger.debug("Insertando/Actualizando CuestionarioCovid19");
        if (objetos == null){
            logger.debug("Nulo");
            return "No recibi nada!";
        }else{
            List<CuestionarioCovid19> cuestionarioCovid19List = Arrays.asList(objetos);
            for(CuestionarioCovid19 cuestionarioCovid19 : cuestionarioCovid19List) {
                cuestionarioCovid19.setFechaRecibido(new Date());
                covidService.saveOrUpdateCuestionarioCovid19(cuestionarioCovid19);
            }
        }
        return "Datos recibidos!";
    }
}
