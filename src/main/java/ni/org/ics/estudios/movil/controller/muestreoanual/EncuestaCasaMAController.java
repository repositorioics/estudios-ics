package ni.org.ics.estudios.movil.controller.muestreoanual;

import ni.org.ics.estudios.domain.muestreoanual.EncuestaCasaMA;
import ni.org.ics.estudios.service.muestreoanual.EncuestaCasaService;
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
public class EncuestaCasaMAController {
	@Resource(name="encuestaCasaMAService")
	private EncuestaCasaService encuestaCasaService;
	private static final Logger logger = LoggerFactory.getLogger(EncuestaCasaMAController.class);

	/**
     * Retorna Encuesta de casas. Acepta una solicitud GET para JSON
     * @return casas JSON
     */
    @RequestMapping(value = "encuestascasas", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    List<EncuestaCasaMA> descargarEncuestasCasas() {
        logger.info("Descargando toda la informacion de las encuestas de casas");
        List<EncuestaCasaMA> encuestasCasas = encuestaCasaService.getEncuestasCasas();
        if (encuestasCasas == null){
        	logger.debug("Nulo");
        }
        return encuestasCasas ;	
    }
    
    
    
    /**
     * Acepta una solicitud POST con un parametro JSON
     * @param envio de casa Objeto serializado de encuesta de Casa
     * @return una cadena con el resultado*/
    @RequestMapping(value = "encuestascasas", method = RequestMethod.POST, consumes = "application/json")
    public @ResponseBody
    String sendMessageJson(@RequestBody EncuestaCasaMA[] envio) {
        logger.debug("Insertando/Actualizando las encuestas de casas");
        if (envio == null){
        	logger.debug("Nulo");
        	return "No recibi nada!";
        }
        else{
        	List<EncuestaCasaMA> casas = Arrays.asList(envio);
        	for (EncuestaCasaMA encuestaCasa: casas){
                Integer codigo = encuestaCasa.getCodCasa();
                if (codigo==null && (encuestaCasa.getCodCasaChf()!=null && !encuestaCasa.getCodCasaChf().isEmpty())) codigo = Integer.valueOf(encuestaCasa.getCodCasaChf());
        		Boolean existe = encuestaCasaService.checkEncuestaCasa(codigo, encuestaCasa.getFechaEncCasa());
        		if (!existe){
        			encuestaCasaService.addEncuestaCasa(encuestaCasa);
        		}
        		else{
        			encuestaCasaService.updateEncuestaCasa(encuestaCasa);
        		}
        	}
        }
        return "Datos recibidos!";
    }  
    
    
}
