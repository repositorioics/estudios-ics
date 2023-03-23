package ni.org.ics.estudios.movil.controller.muestreoanual;

import ni.org.ics.estudios.domain.muestreoanual.RecepcionBHC;
import ni.org.ics.estudios.domain.muestreoanual.RecepcionBHCId;
import ni.org.ics.estudios.service.muestreoanual.RecepcionBHCService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.text.ParseException;
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
public class RecepcionBHCController {
	@Resource(name="bhcService")
	private RecepcionBHCService bhcService;
	private static final Logger logger = LoggerFactory.getLogger(RecepcionBHCController.class);

	/**
     * Retorna muestras. Acepta una solicitud GET para JSON
     * @return muestras JSON
     */
    @RequestMapping(value = "bhcs", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    List<RecepcionBHC> descargarRecepcionBHC() {
        logger.info("Descargando toda la informacion de los datos de las RecepcionBHC");
        List<RecepcionBHC> rbhcs = bhcService.getRecepcionBHCs();
        if (rbhcs == null){
        	logger.debug("Nulo");
        }
        return rbhcs;	
    }
    
    
    
    /**
     * Acepta una solicitud POST con un parametro JSON
     * @param muestra Objeto serializado de Muestras
     * @return una cadena con el resultado*/
     
    @RequestMapping(value = "bhcs", method = RequestMethod.POST, consumes = "application/json")
    public @ResponseBody
    String sendMessageJson(@RequestBody RecepcionBHC[] envio) {
        logger.debug("Insertando/Actualizando RecepcionBHC");
        if (envio == null){
        	logger.debug("Nulo");
        	return "No recibi nada!";
        }
        else{
        	List<RecepcionBHC> bhcs = Arrays.asList(envio);
        	for (RecepcionBHC tubo : bhcs){
        		RecepcionBHCId tuboId = new RecepcionBHCId();
        		tuboId.setCodigo(tubo.getRecBhcId().getCodigo());
        		tuboId.setFechaRecBHC(new Date(tubo.getRecBhcId().getFechaRecBHC().getTime()));
        		Boolean existe = bhcService.checkRecepcionBHC(tuboId);
        		if (!existe){
        			bhcService.addRecepcionBHC(tubo);
        		}
        		else{
        			bhcService.updateRecepcionBHC(tubo);
        		}
        	}
        }
        return "Datos recibidos!";
    }  
    
    /**
	 * Maneja y obtiene todos los BHCs y los presenta en una pagina JSP
	 * 
	 * @return El nombre de la pagina JSP  
	 */
    @RequestMapping(value = "/samples", method = RequestMethod.GET)
    public String fetchSilaisJSP(Model model) throws ParseException {
    	logger.debug("Mostrando Muestras en JSP");
    	// Obtiene todos los bhcs llamando al DomainService
    	List<RecepcionBHC> rbhcs = bhcService.getRecepcionBHCsHoy();
    	// Incluye bhcs al modelo
    	model.addAttribute("bhcs", rbhcs);
    	// Resuelve a  /WEB-INF/jsp/samples.jsp
    	return "samples";
	}
    
    
}
