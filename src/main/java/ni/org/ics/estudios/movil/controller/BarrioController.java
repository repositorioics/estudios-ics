package ni.org.ics.estudios.movil.controller;

import ni.org.ics.estudios.domain.catalogs.Barrio;
import ni.org.ics.estudios.service.BarrioService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Miguel Salinas on 5/5/2017.
 * V1.0
 */
@Controller
@RequestMapping("/movil/*")
public class BarrioController {

    private static final Logger logger = LoggerFactory.getLogger(BarrioController.class);

    @Resource(name = "barrioService")
    private BarrioService barrioService;

    @RequestMapping(value = "barrios", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    List<Barrio> getBarrios(){
        logger.info("Descargando toda la informacion de los datos de los Barrios");
        List<Barrio> barrios = barrioService.getBarrios();
        if (barrios == null){
            logger.debug("Nulo");
        }
        return  barrios;
    }

}
