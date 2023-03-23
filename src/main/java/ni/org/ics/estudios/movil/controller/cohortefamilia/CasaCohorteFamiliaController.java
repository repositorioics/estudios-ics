package ni.org.ics.estudios.movil.controller.cohortefamilia;

import ni.org.ics.estudios.domain.cohortefamilia.CasaCohorteFamilia;
import ni.org.ics.estudios.service.cohortefamilia.CasaCohorteFamiliaService;
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
public class CasaCohorteFamiliaController {

    private static final Logger logger = LoggerFactory.getLogger(CasaCohorteFamiliaController.class);

    @Resource(name = "casaCohorteFamiliaService")
    private CasaCohorteFamiliaService casaCohorteFamiliaService;

    @RequestMapping(value = "casasCHF/{username}", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    List<CasaCohorteFamilia> getCasasCHFByUser(@PathVariable String username){
        logger.info("Descargando toda la informacion de los datos de las casas de la cohorte familia para el usuario " +username);
        List<CasaCohorteFamilia> casas = casaCohorteFamiliaService.getCasasCHFByUser(username);
        if (casas == null){
            logger.debug("Nulo");
        }
        return  casas;
    }

    @RequestMapping(value = "casasCHF", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    List<CasaCohorteFamilia> getCasasCHF(){
        logger.info("Descargando toda la informacion de los datos de las casas de la cohorte familia");
        List<CasaCohorteFamilia> casas = casaCohorteFamiliaService.getCasasCHF();
        if (casas == null){
            logger.debug("Nulo");
        }
        return  casas;
    }

    @RequestMapping(value = "casasCHF", method = RequestMethod.POST, consumes = "application/json")
    public @ResponseBody String saveCasasCHF(@RequestBody CasaCohorteFamilia[] objetos){
        logger.debug("Insertando/Actualizando casas cohorte familia");
        if (objetos == null){
            logger.debug("Nulo");
            return "No recibi nada!";
        }else{
            List<CasaCohorteFamilia> casas = Arrays.asList(objetos);
            for(CasaCohorteFamilia casa : casas) {
                casaCohorteFamiliaService.saveOrUpdateCasaCHF(casa);
            }
        }
        return "Datos recibidos!";
    }
}
