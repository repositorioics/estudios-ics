package ni.org.ics.estudios.movil.controller.muestreoanual;

import ni.org.ics.estudios.domain.muestreoanual.RecepcionSero;
import ni.org.ics.estudios.domain.muestreoanual.RecepcionSeroId;
import ni.org.ics.estudios.service.muestreoanual.RecepcionSeroService;
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
public class RecepcionSeroController {
	@Resource(name="seroService")
	private RecepcionSeroService seroService;
	private static final Logger logger = LoggerFactory.getLogger(RecepcionSeroController.class);

	/**
     * Retorna muestras. Acepta una solicitud GET para JSON
     * @return muestras JSON
     */
    @RequestMapping(value = "seros", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    List<RecepcionSero> descargarRecepcionSero() {
        logger.info("Descargando toda la informacion de los datos de las RecepcionSero");
        List<RecepcionSero> rseros = seroService.getRecepcionSeros();
        if (rseros == null){
        	logger.debug("Nulo");
        }
        return rseros;	
    }
    
    
    
    /**
     * Acepta una solicitud POST con un parametro JSON
     * @param envio Objeto serializado de Muestras
     * @return una cadena con el resultado*/
     
    @RequestMapping(value = "seros", method = RequestMethod.POST, consumes = "application/json")
    public @ResponseBody
    String sendMessageJson(@RequestBody RecepcionSero[] envio) {
        logger.debug("Insertando/Actualizando RecepcionSero");
        if (envio == null){
        	logger.debug("Nulo");
        	return "No recibi nada!";
        }
        else{
        	List<RecepcionSero> seros = Arrays.asList(envio);
        	for (RecepcionSero tubo : seros){
        		//RecepcionSeroId tuboId = new RecepcionSeroId();
        		//tuboId.setCodigo(tubo.getRecSeroId().getCodigo());
        		//tuboId.setFechaRecSero(new Date(tubo.getRecSeroId().getFechaRecSero().getTime()));
        		Boolean existe = seroService.checkRecepcionSero(tubo.getId());
        		if (!existe){
        			seroService.addRecepcionSero(tubo);
        		}
        		else{
        			seroService.updateRecepcionSero(tubo);
        		}
        	}
        }
        return "Datos recibidos!";
    }  
    
    
}
