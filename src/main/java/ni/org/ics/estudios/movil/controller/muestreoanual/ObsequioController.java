package ni.org.ics.estudios.movil.controller.muestreoanual;

import ni.org.ics.estudios.domain.muestreoanual.Obsequio;
import ni.org.ics.estudios.domain.muestreoanual.ObsequioId;
import ni.org.ics.estudios.service.muestreoanual.ObsequioService;
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
public class ObsequioController {
	@Resource(name="obsequiosMAService")
	private ObsequioService obsequioService;
	private static final Logger logger = LoggerFactory.getLogger(ObsequioController.class);

	/**
     * Retorna obsequios. Acepta una solicitud GET para JSON
     * @return obsequios JSON
     */
    @RequestMapping(value = "obsequios", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    List<Obsequio> descargarObsequios() {
        logger.info("Descargando toda la informacion de los datos de los obsequios");
        List<Obsequio> obsequios = obsequioService.getObsequios();
        if (obsequios == null){
        	logger.debug("Nulo");
        }
        return obsequios;	
    }
    
    
    
    /**
     * Acepta una solicitud POST con un parametro JSON
     * @param obsequio Objeto serializado de Obsequio
     * @return una cadena con el resultado*/
     
    @RequestMapping(value = "obsequios", method = RequestMethod.POST, consumes = "application/json")
    public @ResponseBody
    String sendMessageJson(@RequestBody Obsequio[] envio) {
        logger.debug("Insertando/Actualizando Obsequios");
        if (envio == null){
        	logger.debug("Nulo");
        	return "No recibi nada!";
        }
        else{
        	List<Obsequio> obsequios = Arrays.asList(envio);
        	for (Obsequio obsequio: obsequios){
        		ObsequioId obId = new ObsequioId();
        		obId.setCodigo(obsequio.getObId().getCodigo());
        		obId.setFechaEntrega(new Date(obsequio.getObId().getFechaEntrega().getTime()));
        		Boolean existe = obsequioService.checkObsequio(obId);
        		if (!existe){
        			obsequioService.addObsequio(obsequio);
        		}
        		else{
        			obsequioService.updateObsequio(obsequio);
        		}
        	}
        }
        return "Datos recibidos!";
    }  
    
    
}
