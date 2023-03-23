package ni.org.ics.estudios.movil.controller.muestreoanual;

import ni.org.ics.estudios.domain.muestreoanual.Pinchazo;
import ni.org.ics.estudios.domain.muestreoanual.PinchazoId;
import ni.org.ics.estudios.service.muestreoanual.PinchazoService;
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
public class PinchazoController {
	@Resource(name="pinService")
	private PinchazoService pinService;
	private static final Logger logger = LoggerFactory.getLogger(PinchazoController.class);

	/**
     * Retorna muestras. Acepta una solicitud GET para JSON
     * @return muestras JSON
     */
    @RequestMapping(value = "pins", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    List<Pinchazo> descargarPinchazo() {
        logger.info("Descargando toda la informacion de los datos de las Pinchazo");
        List<Pinchazo> pins = pinService.getPinchazo();
        if (pins == null){
        	logger.debug("Nulo");
        }
        return pins;	
    }
    
    
    
    /**
     * Acepta una solicitud POST con un parametro JSON
     * @param muestra Objeto serializado de Muestras
     * @return una cadena con el resultado*/
     
    @RequestMapping(value = "pins", method = RequestMethod.POST, consumes = "application/json")
    public @ResponseBody
    String sendMessageJson(@RequestBody Pinchazo[] envio) {
        logger.debug("Insertando/Actualizando Pinchazo");
        if (envio == null){
        	logger.debug("Nulo");
        	return "No recibi nada!";
        }
        else{
        	List<Pinchazo> pinchazos = Arrays.asList(envio);
        	for (Pinchazo pin : pinchazos){
        		PinchazoId pinId = new PinchazoId();
        		pinId.setCodigo(pin.getPinId().getCodigo());
        		pinId.setFechaPinchazo(new Date(pin.getPinId().getFechaPinchazo().getTime()));
        		Boolean existe = pinService.checkPinchazo(pinId);
        		if (!existe){
        			pinService.addPinchazo(pin);
        		}
        		else{
        			pinService.updatePinchazo(pin);
        		}
        	}
        }
        return "Datos recibidos!";
    }  
    
    
}
