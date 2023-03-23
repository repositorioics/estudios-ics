package ni.org.ics.estudios.movil.controller.cohortefamilia.casos;

import ni.org.ics.estudios.domain.cohortefamilia.casos.CasaCohorteFamiliaCaso;
import ni.org.ics.estudios.service.cohortefamilia.casos.CasaCohorteFamiliaCasoService;

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
public class CasaCohorteFamiliaCasoController {

    private static final Logger logger = LoggerFactory.getLogger(CasaCohorteFamiliaCasoController.class);

    @Resource(name = "casaCohorteFamiliaCasoService")
    private CasaCohorteFamiliaCasoService casaCohorteFamiliaCasoService;

    @RequestMapping(value = "casascasos", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    List<CasaCohorteFamiliaCaso> getCasaCohorteFamiliaCasos(){
        logger.info("Descargando toda la informacion de los datos de las casas de la cohorte familia con caso positivo");
        List<CasaCohorteFamiliaCaso> casas = casaCohorteFamiliaCasoService.getCasaCohorteFamiliaCasos();
        if (casas == null){
            logger.debug("Nulo");
        }
        return  casas;
    }

    @RequestMapping(value = "casascasos", method = RequestMethod.POST, consumes = "application/json")
    public @ResponseBody String saveCasaCohorteFamiliaCasos(@RequestBody CasaCohorteFamiliaCaso[] objetos){
        logger.debug("Insertando/Actualizando casas cohorte familia con casos positivos");
        if (objetos == null){
            logger.debug("Nulo");
            return "No recibi nada!";
        }else{
            List<CasaCohorteFamiliaCaso> casas = Arrays.asList(objetos);
            for(CasaCohorteFamiliaCaso casa : casas) {
            	casaCohorteFamiliaCasoService.saveOrUpdateCasaCohorteFamiliaCaso(casa);
            }
        }
        return "Datos recibidos!";
    }
}
