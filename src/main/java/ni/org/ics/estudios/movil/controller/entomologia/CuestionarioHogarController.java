package ni.org.ics.estudios.movil.controller.entomologia;

import ni.org.ics.estudios.domain.entomologia.CuestionarioHogar;
import ni.org.ics.estudios.domain.entomologia.CuestionarioHogarPoblacion;
import ni.org.ics.estudios.service.entomologia.CuestionarioHogarService;
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
 * Created by miguel on 15/8/2022.
 */
@Controller
@RequestMapping("/movil/*")
public class CuestionarioHogarController {
    private static final Logger logger = LoggerFactory.getLogger(CuestionarioHogarController.class);

    @Resource(name = "cuestionarioHogarService")
    private CuestionarioHogarService cuestionarioHogarService;

    @RequestMapping(value = "cuestionariosHogar", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    List<CuestionarioHogar> getCuestionariosHogar(){
        logger.info("Descargando toda la informacion de los cuestionarios de hogar mundo post zika");
        List<CuestionarioHogar> cuestionarioHogarList = cuestionarioHogarService.getCuestionariosHogar();
        if (cuestionarioHogarList == null){
            logger.debug("Nulo");
        }
        return  cuestionarioHogarList;
    }

    @RequestMapping(value = "cuestionariosHogarPob", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    List<CuestionarioHogarPoblacion> getCuestionariosHogarPoblacion(){
        logger.info("Descargando toda la informacion de la poblacion los cuestionarios de hogar mundo post zika");
        List<CuestionarioHogarPoblacion> cuestionarioHogarList = cuestionarioHogarService.getCuestionariosHogarPoblacion();
        if (cuestionarioHogarList == null){
            logger.debug("Nulo");
        }
        return  cuestionarioHogarList;
    }

    @RequestMapping(value = "cuestionariosHogar", method = RequestMethod.POST, consumes = "application/json")
    public @ResponseBody String saveOrUpdateCuestionarioHogar(@RequestBody CuestionarioHogar[] objetos){
        logger.debug("Insertando/Actualizando CuestionarioHogar");
        if (objetos == null){
            logger.debug("Nulo");
            return "No recibi nada!";
        }else{
            List<CuestionarioHogar> cuestionarioHogarList = Arrays.asList(objetos);
            for(CuestionarioHogar cuestionarioHogar : cuestionarioHogarList) {
                cuestionarioHogarService.saveCuestionarioHogar(cuestionarioHogar);
            }
        }
        return "Datos recibidos!";
    }

    @RequestMapping(value = "cuestionariosHogarPob", method = RequestMethod.POST, consumes = "application/json")
    public @ResponseBody String saveOrUpdateCuestionarioHogarPoblacion(@RequestBody CuestionarioHogarPoblacion[] objetos){
        logger.debug("Insertando/Actualizando CuestionarioHogarPoblacion");
        if (objetos == null){
            logger.debug("Nulo");
            return "No recibi nada!";
        }else{
            List<CuestionarioHogarPoblacion> cuestionarioHogarList = Arrays.asList(objetos);
            for(CuestionarioHogarPoblacion cuestionarioHogar : cuestionarioHogarList) {
                cuestionarioHogarService.saveCuestionarioHogarPoblacion(cuestionarioHogar);
            }
        }
        return "Datos recibidos!";
    }
}
