package ni.org.ics.estudios.movil.controller.muestreoanual;

import ni.org.ics.estudios.domain.muestreoanual.LactanciaMaterna;
import ni.org.ics.estudios.domain.muestreoanual.LactanciaMaternaId;
import ni.org.ics.estudios.service.muestreoanual.LactanciaMaternaService;
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
public class LactanciaMaternaController {
	@Resource(name="lactanciaMaternaService")
	private LactanciaMaternaService lactanciaMaternaService;
	private static final Logger logger = LoggerFactory.getLogger(LactanciaMaternaController.class);

	/**
     * Retorna obsequios. Acepta una solicitud GET para JSON
     * @return obsequios JSON
     */
    @RequestMapping(value = "lactmaterna", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    List<LactanciaMaterna> descargarObsequios() {
        logger.info("Descargando toda la informacion de los datos");
        List<LactanciaMaterna> lactmaterna = lactanciaMaternaService.getLactanciaMaterna();
        if (lactmaterna == null){
        	logger.debug("Nulo");
        }
        return lactmaterna;	
    }
    
    
    
    /**
     * Acepta una solicitud POST con un parametro JSON
     * @param obsequio Objeto serializado de Obsequio
     * @return una cadena con el resultado*/
     
    @RequestMapping(value = "lactmaterna", method = RequestMethod.POST, consumes = "application/json")
    public @ResponseBody
    String sendMessageJson(@RequestBody LactanciaMaterna[] envio) {
        logger.debug("Insertando/Actualizando LM");
        if (envio == null){
        	logger.debug("Nulo");
        	return "No recibi nada!";
        }
        else{
        	List<LactanciaMaterna> lactmats = Arrays.asList(envio);
        	for (LactanciaMaterna lacmat: lactmats){
        		LactanciaMaternaId lmId = new LactanciaMaternaId();
        		lmId.setCodigo(lacmat.getLmId().getCodigo());
        		lmId.setFechaEncLM(new Date(lacmat.getLmId().getFechaEncLM().getTime()));
        		Boolean existe = lactanciaMaternaService.checkLactanciaMaterna(lmId);
        		if (!existe){
                    lacmat.setEncuestaValida(true);
        			lactanciaMaternaService.addLactanciaMaterna(lacmat);
        		}
        		else{
                    LactanciaMaterna lactanciaMaterna = lactanciaMaternaService.getLactanciaMaterna(lmId);
                    if (lactanciaMaterna != null) {
                        if (lactanciaMaterna.getEncuestaValida() != null) {
                            lacmat.setEncuestaValida(lactanciaMaterna.getEncuestaValida());
                        } else {
                            lacmat.setEncuestaValida(null);
                        }
                    }
        			lactanciaMaternaService.updateLactanciaMaterna(lacmat);
        		}
        	}
        }
        return "Datos recibidos!";
    }  
    
    
}
