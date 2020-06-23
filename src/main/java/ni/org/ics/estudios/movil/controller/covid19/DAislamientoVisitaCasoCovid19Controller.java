package ni.org.ics.estudios.movil.controller.covid19;

import ni.org.ics.estudios.domain.covid19.DatosAislamientoVisitaCasoCovid19;
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
public class DAislamientoVisitaCasoCovid19Controller {
    private static final Logger logger = LoggerFactory.getLogger(DAislamientoVisitaCasoCovid19Controller.class);

    @Resource(name = "CovidService")
    private CovidService covidService;

    @RequestMapping(value = "datosAislamientoCovid19", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    List<DatosAislamientoVisitaCasoCovid19> getDatosAislamientoVisitasCasosCovid19(){
        logger.info("Descargando toda la informacion de datos aislamiento de seguimientos covid19");
        List<DatosAislamientoVisitaCasoCovid19> datosList = covidService.getDatosAislamientoVisitasCasosCovid19();
        if (datosList == null){
            logger.debug("Nulo");
        }
        return  datosList;
    }

    @RequestMapping(value = "datosAislamientoCovid19", method = RequestMethod.POST, consumes = "application/json")
    public @ResponseBody String saveDatosAislamientoVisitaCasoCovid19(@RequestBody DatosAislamientoVisitaCasoCovid19[] objetos){
        logger.debug("Insertando/Actualizando de datos aislamiento de seguimientos covid19");
        if (objetos == null){
            logger.debug("Nulo");
            return "No recibi nada!";
        }else{
            List<DatosAislamientoVisitaCasoCovid19> datosList = Arrays.asList(objetos);
            for(DatosAislamientoVisitaCasoCovid19 dato : datosList) {
                covidService.saveOrUpdateDatosAislamientoVisitaCasoCovid19(dato);
            }
        }
        return "Datos recibidos!";
    }

}
