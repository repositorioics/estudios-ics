package ni.org.ics.estudios.movil.controller.muestreoanual;

import ni.org.ics.estudios.domain.muestreoanual.TempRojoBhc;
import ni.org.ics.estudios.domain.muestreoanual.TempRojoBhcId;
import ni.org.ics.estudios.service.muestreoanual.TempRojoBhcService;
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
 * Maneja las solicitudes que van a la aplicacion relacionadas a json
 * 
 * @author Brenda Lopez
 */
@Controller
@RequestMapping("/movil/*")
public class TempRojoBhcController {
	@Resource(name="tRojoBhcService")
	private TempRojoBhcService tRojoBhcService;
	private static final Logger logger = LoggerFactory.getLogger(TempRojoBhcController.class);

	/**
     * Retorna. Acepta una solicitud GET para JSON
     * @return muestras JSON
     */
    @RequestMapping(value = "trojos", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    List<TempRojoBhc> descargarTempRojoBhc() {
        logger.info("Descargando toda la informacion de los datos de las TempRojoBhc");
        List<TempRojoBhc> temps = tRojoBhcService.getTempRojoBhcs();
        if (temps == null){
        	logger.debug("Nulo");
        }
        return temps;	
    }
    
    
    
    /**
     * Acepta una solicitud POST con un parametro JSON
     * @param muestra Objeto serializado de Muestras
     * @return una cadena con el resultado*/
     
    @RequestMapping(value = "trojos", method = RequestMethod.POST, consumes = "application/json")
    public @ResponseBody
    String sendMessageJson(@RequestBody TempRojoBhc[] envio) {
        logger.debug("Insertando/Actualizando TempRojoBhc");
        if (envio == null){
        	logger.debug("Nulo");
        	return "No recibi nada!";
        }
        else{
        	List<TempRojoBhc> temps = Arrays.asList(envio);
        	for (TempRojoBhc temperatura : temps){
        		TempRojoBhcId tempId = new TempRojoBhcId();
        		tempId.setRecurso(temperatura.getTempRojoBhcId().getRecurso());
        		tempId.setFechaTempRojoBhc(new Date(temperatura.getTempRojoBhcId().getFechaTempRojoBhc().getTime()));
        		Boolean existe = tRojoBhcService.checkTempRojoBhc(tempId);
        		if (!existe){
        			tRojoBhcService.addTempRojoBhc(temperatura);
        		}
        		else{
        			tRojoBhcService.updateTempRojoBhc(temperatura);
        		}
        	}
        }
        return "Datos recibidos!";
    }  
    
    
}
