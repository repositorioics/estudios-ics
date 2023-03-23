package ni.org.ics.estudios.movil.controller.cohortefamilia.casos;

import ni.org.ics.estudios.domain.cohortefamilia.casos.VisitaSeguimientoCasoSintomas;
import ni.org.ics.estudios.service.cohortefamilia.casos.VisitaSeguimientoCasoSintomasService;

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
public class VisitaSeguimientoCasoSintomasController {

    private static final Logger logger = LoggerFactory.getLogger(VisitaSeguimientoCasoSintomasController.class);

    @Resource(name = "visitaSeguimientoCasoSintomasService")
    private VisitaSeguimientoCasoSintomasService visitaSeguimientoCasoSintomasService;

    @RequestMapping(value = "sintomascasos", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    List<VisitaSeguimientoCasoSintomas> getVisitaSeguimientoCasoSintomas(){
        logger.info("Descargando toda la informacion de los datos de sintomas de los participantes de las casas de la cohorte familia con caso positivo");
        List<VisitaSeguimientoCasoSintomas> sintomas = visitaSeguimientoCasoSintomasService.getVisitaSeguimientoCasoSintomas();
        if (sintomas == null){
            logger.debug("Nulo");
        }
        return  sintomas;
    }

    @RequestMapping(value = "sintomascasos", method = RequestMethod.POST, consumes = "application/json")
    public @ResponseBody String saveVisitaSeguimientoCasoSintomas(@RequestBody VisitaSeguimientoCasoSintomas[] objetos){
        logger.debug("Insertando/Actualizando los datos de sintomas de participantes de las casas cohorte familia con casos positivos");
        if (objetos == null){
            logger.debug("Nulo");
            return "No recibi nada!";
        }else{
            List<VisitaSeguimientoCasoSintomas> sintomas = Arrays.asList(objetos);
            for(VisitaSeguimientoCasoSintomas sintoma : sintomas) {
            	visitaSeguimientoCasoSintomasService.saveOrUpdateVisitaSeguimientoCasoSintomas(sintoma);
            }
        }
        return "Datos recibidos!";
    }
}
