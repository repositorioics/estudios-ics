package ni.org.ics.estudios.movil.controller.cohortefamilia;

import ni.org.ics.estudios.domain.cohortefamilia.Cama;
import ni.org.ics.estudios.domain.cohortefamilia.Cuarto;
import ni.org.ics.estudios.domain.cohortefamilia.PersonaCama;
import ni.org.ics.estudios.service.cohortefamilia.CuartoService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Miguel Salinas on 5/5/2017.
 * V1.0
 */
@Controller
@RequestMapping("/movil/*")
public class CuartoController {

    private static final Logger logger = LoggerFactory.getLogger(CuartoController.class);

    @Resource(name = "cuartoService")
    private CuartoService cuartoService;

    @RequestMapping(value = "cuartos", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody List<Cuarto> getCuartos(){
        logger.info("Descargando toda la informacion de los datos de los cuartos");
        List<Cuarto> cuartos = cuartoService.getCuartos();
        if (cuartos == null){
            logger.debug("Nulo");
        }
        return  cuartos;
    }

    @RequestMapping(value = "cuartos", method = RequestMethod.POST, consumes = "application/json")
    public @ResponseBody String saveCuartos(@RequestBody Cuarto[] objetos){
        logger.debug("Insertando/Actualizando Cuartos");
        if (objetos == null){
            logger.debug("Nulo");
            return "No recibi nada!";
        }else{
            List<Cuarto> cuartos = Arrays.asList(objetos);
            for(Cuarto cuarto : cuartos) {
            	cuartoService.saveOrUdateCuarto(cuarto);
            }
        }
        return "Datos recibidos!";
    }  
    
    @RequestMapping(value = "camas", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody List<Cama> getCamas(){
        logger.info("Descargando toda la informacion de los datos de las Camas");
        List<Cama> camas = cuartoService.getCamas();
        if (camas == null){
            logger.debug("Nulo");
        }
        return  camas;
    }

    @RequestMapping(value = "camas", method = RequestMethod.POST, consumes = "application/json")
    public @ResponseBody String saveCamas(@RequestBody Cama[] objetos){
        logger.debug("Insertando/Actualizando Camas");
        if (objetos == null){
            logger.debug("Nulo");
            return "No recibi nada!";
        }else{
            List<Cama> camas = Arrays.asList(objetos);
            for(Cama cama : camas) {
            	cuartoService.saveOrUdateCama(cama);
            }
        }
        return "Datos recibidos!";
    }
    
    
    @RequestMapping(value = "personasCamas", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody List<PersonaCama> getPersonasCama(){
        logger.info("Descargando toda la informacion de los datos de las PersonaCama");
        List<PersonaCama> personasCama = cuartoService.getPersonasCama();
        if (personasCama == null){
            logger.debug("Nulo");
        }
        return  personasCama;
    }

    @RequestMapping(value = "personasCamas", method = RequestMethod.POST, consumes = "application/json")
    public @ResponseBody String savePersonasCamas(@RequestBody PersonaCama[] objetos){
        logger.debug("Insertando/Actualizando PersonaCama");
        if (objetos == null){
            logger.debug("Nulo");
            return "No recibi nada!";
        }else{
            List<PersonaCama> personasCama = Arrays.asList(objetos);
            for(PersonaCama personaCama : personasCama) {
            	cuartoService.saveOrUdatePersonaCama(personaCama);
            }
        }
        return "Datos recibidos!";
    }

}
