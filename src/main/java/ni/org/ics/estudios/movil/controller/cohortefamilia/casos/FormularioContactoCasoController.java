package ni.org.ics.estudios.movil.controller.cohortefamilia.casos;

import ni.org.ics.estudios.domain.cohortefamilia.casos.FormularioContactoCaso;
import ni.org.ics.estudios.service.cohortefamilia.casos.FormularioContactoCasoService;
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
public class FormularioContactoCasoController {

    private static final Logger logger = LoggerFactory.getLogger(FormularioContactoCasoController.class);

    @Resource(name = "formularioContactoCasoService")
    private FormularioContactoCasoService formularioContactoCasoService;

    @RequestMapping(value = "contactoscasos", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    List<FormularioContactoCaso> getFormularioContactoCasos(){
        logger.info("Descargando toda la informacion de los datos de contactos de los participantes de las casas de la cohorte familia con caso positivo");
        List<FormularioContactoCaso> contactos = formularioContactoCasoService.getFormularioContactoCasos();
        if (contactos == null){
            logger.debug("Nulo");
        }
        return  contactos;
    }

    @RequestMapping(value = "contactoscasos", method = RequestMethod.POST, consumes = "application/json")
    public @ResponseBody String saveFormularioContactoCasos(@RequestBody FormularioContactoCaso[] objetos){
        logger.debug("Insertando/Actualizando los datos de contactos de participantes de las casas cohorte familia con casos positivos");
        if (objetos == null){
            logger.debug("Nulo");
            return "No recibi nada!";
        }else{
            List<FormularioContactoCaso> contactos = Arrays.asList(objetos);
            for(FormularioContactoCaso contacto : contactos) {
            	formularioContactoCasoService.saveOrUpdateFormularioContactoCaso(contacto);
            }
        }
        return "Datos recibidos!";
    }
}
