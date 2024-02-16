package ni.org.ics.estudios.movil.controller;

import ni.org.ics.estudios.domain.ObsequioGeneral;
import ni.org.ics.estudios.service.ObsequioService;
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
 * Created by Miguel Salinas on 06/11/2018.
 * V1.0
 */
@Controller
@RequestMapping("/movil/*")
public class ObsequiosGenController {

    private static final Logger logger = LoggerFactory.getLogger(ObsequiosGenController.class);

    @Resource(name = "obsequioService")
    private ObsequioService obsequioService;

    @RequestMapping(value = "obsequiosgen", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody List<ObsequioGeneral> getObsequios(){
        logger.info("Descargando toda la informacion de los datos de las ObsequioGeneral");
        List<ObsequioGeneral> obsequios = obsequioService.getObsequios();
        if (obsequios == null){
            logger.debug("Nulo");
        }
        return  obsequios;
    }

    @RequestMapping(value = "obsequiosgen", method = RequestMethod.POST, consumes = "application/json")
    public @ResponseBody String saveObsequios(@RequestBody ObsequioGeneral[] objetos){
        logger.debug("Insertando/Actualizando ObsequioGeneral");
        if (objetos == null){
            logger.debug("Nulo");
            return "No recibi nada!";
        }else{
            List<ObsequioGeneral> obsequioGeneralList = Arrays.asList(objetos);
            for(ObsequioGeneral obsequioGeneral : obsequioGeneralList) {
                obsequioGeneral.setEncuestaValida(true);
            	obsequioService.saveorUpdateObsequio(obsequioGeneral);
            }
        }
        return "Datos recibidos!";
    }

}
