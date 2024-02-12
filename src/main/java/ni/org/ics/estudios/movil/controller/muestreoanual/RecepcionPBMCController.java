package ni.org.ics.estudios.movil.controller.muestreoanual;

import ni.org.ics.estudios.domain.muestreoanual.RecepcionPBMC;
import ni.org.ics.estudios.service.muestreoanual.RecepcionPBMCService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * Created by Ing. Santiago Carballo on 09/02/2024.
 */

@Controller
@RequestMapping("/movil/*")
public class RecepcionPBMCController {

    @Resource(name="pbmcService")
    private RecepcionPBMCService pbmcService;
    private static final Logger logger = LoggerFactory.getLogger(RecepcionPBMCController.class);

    @RequestMapping(value = "pbmcs", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    List<RecepcionPBMC> descargarRecepcionPBMC() {
        logger.info("Descargando toda la informacion de los datos de las RecepcionPBMC");
        List<RecepcionPBMC> pbmcs = pbmcService.getAllRecepcionPBMC();
        if (pbmcs == null){
            logger.debug("Nulo");
        }
        return pbmcs;
    }

    @RequestMapping(value = "pbmcs", method = RequestMethod.POST, consumes = "application/json")
    public @ResponseBody
    String sendMessageJson(@RequestBody RecepcionPBMC[] envio) {
        logger.debug("Insertando/Actualizando RecepcionPBMC");
        if (envio == null){
            logger.debug("Nulo");
            return "No recibi nada!";
        }
        else{
            List<RecepcionPBMC> recepcionPBMCs = Arrays.asList(envio);
            for (RecepcionPBMC tubo : recepcionPBMCs){
                RecepcionPBMC recepcionPBMC = null;

                recepcionPBMC = pbmcService.getRecepcionPBMC(tubo.getCodigo());
                if (recepcionPBMC == null){
                    tubo.setFechaRegistro(new Date());
                    pbmcService.addRecepcionPBMC(tubo);
                } else {
                    if (recepcionPBMC.getFechaRegistro() != null) {
                        tubo.setId(recepcionPBMC.getId());
                        tubo.setFechaRegistro(recepcionPBMC.getFechaRegistro());
                    } else {
                        tubo.setFechaRegistro(null);
                    }
                    pbmcService.updateRecepcionPBMC(tubo);
                }
            }
        }
        return "Datos recibidos!";
    }
}
