package ni.org.ics.estudios.movil.controller;

import ni.org.ics.estudios.domain.Casa;
import ni.org.ics.estudios.service.CasaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * Created by Miguel Salinas on 5/5/2017.
 * V1.0
 */
@Controller
@RequestMapping("/movil/*")
public class CasaController {

    private static final Logger logger = LoggerFactory.getLogger(CasaController.class);

    @Resource(name = "casaService")
    private CasaService casaService;

    @RequestMapping(value = "casas", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    List<Casa> getCasas(){
        logger.info("Descargando toda la informacion de los datos de las casas");
        List<Casa> casas = casaService.getCasas();
        if (casas == null){
            logger.debug("Nulo");
        }
        return  casas;
    }

    @RequestMapping(value = "casas", method = RequestMethod.POST, consumes = "application/json")
    public @ResponseBody String saveCasas(@RequestBody Casa[] objetos){
        logger.debug("Insertando/Actualizando casas");
        if (objetos == null){
            logger.debug("Nulo");
            return "No recibi nada!";
        }else{
            List<Casa> casas = Arrays.asList(objetos);
            for(Casa casa : casas) {
                casaService.saveOrUpdateCasa(casa);
            }
        }
        return "Datos recibidos!";
    }

    /**
     * Retorna casa. Acepta una solicitud GET para JSON
     * @return participante JSON
     */
    @RequestMapping(value = "casa/{casa}", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody List<Casa> descargarCasa(@PathVariable Integer casa) {
        logger.info(new Date() + " - Descargando toda la informacion de la casa" + casa);
        List<Casa> casaPar = casaService.getCasaParticipante(casa);
        if (casaPar == null){
            logger.debug(new Date() + " - Nulo");
        }
        return casaPar;
    }

}
