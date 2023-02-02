package ni.org.ics.estudios.movil.controller.muestreoanual;

import ni.org.ics.estudios.domain.muestreoanual.EncuestaPerimetroAbdominal;
import ni.org.ics.estudios.domain.muestreoanual.EncuestaPerimetroAbdominalId;
import ni.org.ics.estudios.service.muestreoanual.EncuestaPerimetroAbdominalService;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
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
 * Created by Ing. Santiago Carballo on 31/01/2023.
 */

@Controller
@RequestMapping("/movil/*")
public class EncuestaPerimetroAbdominalController {
    @Resource(name="encuestaPerimetroAbdominalService")
    private EncuestaPerimetroAbdominalService encuestaPerimetroAbdominalService;
    private static final Logger logger = LoggerFactory.getLogger(EncuestaPerimetroAbdominalController.class);

    @RequestMapping(value = "perimetroabdominal", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    List<EncuestaPerimetroAbdominal> descargaEncuestaPerimetroAbdominal() {
        logger.info("Descargando toda la informacion de los Permietros Abdominales");
        List<EncuestaPerimetroAbdominal> pabdominal = encuestaPerimetroAbdominalService.getListEncuestaPerimetroAbdominal();
        if (pabdominal == null){
            logger.debug("Nulo");
        }
        return pabdominal;
    }

    @RequestMapping(value = "perimetroabdominal", method = RequestMethod.POST, consumes = "application/json")
    public @ResponseBody
    String sendMessageJson(@RequestBody EncuestaPerimetroAbdominal[] envio) {
        logger.debug("Insertando/Actualizando Permietros Abdominales");
        if (envio == null){
            logger.debug("Nulo");
            return "No recibi nada!";
        }
        else{
            List<EncuestaPerimetroAbdominal> pAbdominal = Arrays.asList(envio);
            for (EncuestaPerimetroAbdominal pa: pAbdominal){
                EncuestaPerimetroAbdominalId pabdominalId = new EncuestaPerimetroAbdominalId();
                pabdominalId.setCodigo(pa.getPaId().getCodigo());
                pabdominalId.setFecha(new Date(pa.getPaId().getFecha().getTime()));
                Boolean existe = encuestaPerimetroAbdominalService.checkEncuestaPerimetroAbdominal(pabdominalId);
                if (!existe){
                    encuestaPerimetroAbdominalService.addEncuestaPerimetroAbdominal(pa);
                }
                else{
                    encuestaPerimetroAbdominalService.updateEncuestaPerimetroAbdominal(pa);
                }
            }
        }
        return "Datos recibidos!";
    }
}
