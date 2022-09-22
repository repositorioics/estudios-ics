package ni.org.ics.estudios.movil.controller.entomologia;

import ni.org.ics.estudios.domain.entomologia.CuestionarioPuntoClave;
import ni.org.ics.estudios.service.entomologia.CuestionarioPuntoClaveService;
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
 * Created by miguel on 2/9/2022.
 */
@Controller
@RequestMapping("/movil/*")
public class CuestionarioPuntoClaveController {
    private static final Logger logger = LoggerFactory.getLogger(CuestionarioPuntoClaveController.class);

    @Resource(name = "cuestionarioPuntoClaveService")
    private CuestionarioPuntoClaveService cuestionarioPuntoClaveService;

    @RequestMapping(value = "cuestionariosPuntosClaves", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    List<CuestionarioPuntoClave> getCuestionariosPuntoClave(){
        logger.info("Descargando toda la informacion de los cuestionarios de puntos claves mundo post zika");
        List<CuestionarioPuntoClave> puntoClaveList = cuestionarioPuntoClaveService.getCuestionariosPuntoClave();
        if (puntoClaveList == null){
            logger.debug("Nulo");
        }
        return  puntoClaveList;
    }

    @RequestMapping(value = "cuestionariosPuntosClaves", method = RequestMethod.POST, consumes = "application/json")
    public @ResponseBody String saveCuestionarioPuntoClave(@RequestBody CuestionarioPuntoClave[] objetos){
        logger.debug("Insertando/Actualizando CuestionarioPuntoClave");
        if (objetos == null){
            logger.debug("Nulo");
            return "No recibi nada!";
        }else{
            List<CuestionarioPuntoClave> puntoClaveList = Arrays.asList(objetos);
            for(CuestionarioPuntoClave cuestionarioPuntoClave : puntoClaveList) {
                cuestionarioPuntoClaveService.saveCuestionarioPuntoClave(cuestionarioPuntoClave);
            }
        }
        return "Datos recibidos!";
    }
}
