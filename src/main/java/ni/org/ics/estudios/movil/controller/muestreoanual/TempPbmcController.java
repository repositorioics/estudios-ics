package ni.org.ics.estudios.movil.controller.muestreoanual;

import ni.org.ics.estudios.domain.muestreoanual.TempPbmc;
import ni.org.ics.estudios.domain.muestreoanual.TempPbmcId;
import ni.org.ics.estudios.service.muestreoanual.TempPbmcService;
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
public class TempPbmcController {
	@Resource(name="tPbmcService")
	private TempPbmcService tPbmcService;
	private static final Logger logger = LoggerFactory.getLogger(TempPbmcController.class);

	/**
     * Retorna. Acepta una solicitud GET para JSON
     * @return muestras JSON
     */
    @RequestMapping(value = "tpbmcs", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    List<TempPbmc> descargarTempPbmc() {
        logger.info("Descargando toda la informacion de los datos de las TempPbmc");
        List<TempPbmc> temps = tPbmcService.getTempPbmcs();
        if (temps == null){
        	logger.debug("Nulo");
        }
        return temps;	
    }
    
    
    
    /**
     * Acepta una solicitud POST con un parametro JSON
     * @param muestra Objeto serializado de Muestras
     * @return una cadena con el resultado*/
     
    @RequestMapping(value = "tpbmcs", method = RequestMethod.POST, consumes = "application/json")
    public @ResponseBody
    String sendMessageJson(@RequestBody TempPbmc[] envio) {
        logger.debug("Insertando/Actualizando TempPbmc");
        if (envio == null){
        	logger.debug("Nulo");
        	return "No recibi nada!";
        }
        else{
        	List<TempPbmc> temps = Arrays.asList(envio);
        	for (TempPbmc temperatura : temps){
        		TempPbmcId tempId = new TempPbmcId();
        		tempId.setRecurso(temperatura.getTempPbmcId().getRecurso());
        		tempId.setFechaTempPbmc(new Date(temperatura.getTempPbmcId().getFechaTempPbmc().getTime()));
        		Boolean existe = tPbmcService.checkTempPbmc(tempId);
        		if (!existe){
        			tPbmcService.addTempPbmc(temperatura);
        		}
        		else{
        			tPbmcService.updateTempPbmc(temperatura);
        		}
        	}
        }
        return "Datos recibidos!";
    }  
    
    
}
