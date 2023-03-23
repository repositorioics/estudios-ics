package ni.org.ics.estudios.movil.controller.muestreoanual;

import ni.org.ics.estudios.domain.muestreoanual.EncuestaParticipanteMA;
import ni.org.ics.estudios.domain.muestreoanual.EncuestaParticipanteId;
import ni.org.ics.estudios.service.muestreoanual.EncuestaParticipanteService;
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
public class EncuestaParticipanteMAController {
	@Resource(name="encuestaParticipanteMAService")
	private EncuestaParticipanteService encuestaParticipanteService;
	private static final Logger logger = LoggerFactory.getLogger(EncuestaParticipanteMAController.class);

	/**
     * Retorna Encuesta de Participantes. Acepta una solicitud GET para JSON
     * @return Participantes JSON
     */
    @RequestMapping(value = "encuestasparticipantes", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    List<EncuestaParticipanteMA> descargarEncuestasParticipantes() {
        logger.info("Descargando toda la informacion de las encuestas de participantes");
        List<EncuestaParticipanteMA> encuestasParticipantes = encuestaParticipanteService.getEncuestasParticipantes();
        if (encuestasParticipantes == null){
        	logger.debug("Nulo");
        }
        return encuestasParticipantes ;	
    }
    
    
    
    /**
     * Acepta una solicitud POST con un parametro JSON
     * @param envio de Participante Objeto serializado de encuesta de Participante
     * @return una cadena con el resultado*/
    @RequestMapping(value = "encuestasparticipantes", method = RequestMethod.POST, consumes = "application/json")
    public @ResponseBody
    String sendMessageJson(@RequestBody EncuestaParticipanteMA[] envio) {
        logger.debug("Insertando/Actualizando las encuestas de Participantes");
        if (envio == null){
        	logger.debug("Nulo");
        	return "No recibi nada!";
        }
        else{
        	List<EncuestaParticipanteMA> encuestasParticipantes= Arrays.asList(envio);
        	for (EncuestaParticipanteMA encuestaParticipante: encuestasParticipantes){
        		EncuestaParticipanteId epId = new EncuestaParticipanteId();
        		epId.setCodigo(encuestaParticipante.getEpId().getCodigo());
        		epId.setFechaEncPar(new Date(encuestaParticipante.getEpId().getFechaEncPar().getTime()));
        		Boolean existe = encuestaParticipanteService.checkEncuestaParticipante(epId);
        		if (!existe){
        			encuestaParticipanteService.addEncuestaParticipante(encuestaParticipante);
        		}
        		else{
        			encuestaParticipanteService.updateEncuestaParticipante(encuestaParticipante);
        		}
        	}
        }
        return "Datos recibidos!";
    }  
    
    
}
