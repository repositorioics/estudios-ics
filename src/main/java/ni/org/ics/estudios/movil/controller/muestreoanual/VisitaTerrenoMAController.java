package ni.org.ics.estudios.movil.controller.muestreoanual;

import ni.org.ics.estudios.domain.muestreoanual.VisitaTerrenoMA;
import ni.org.ics.estudios.domain.muestreoanual.VisitaTerrenoId;
import ni.org.ics.estudios.service.muestreoanual.VisitaTerrenoService;
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
public class VisitaTerrenoMAController {
	@Resource(name="visitaTerrenoMAService")
	private VisitaTerrenoService visitaTerrenoService;
	private static final Logger logger = LoggerFactory.getLogger(VisitaTerrenoMAController.class);

	/**
     * Retorna muestras. Acepta una solicitud GET para JSON
     * @return muestras JSON
     */
    @RequestMapping(value = "visitasMA", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    List<VisitaTerrenoMA> descargarVisitaTerreno() {
        logger.info("Descargando toda la informacion de los datos de las VisitaTerreno");
        List<VisitaTerrenoMA> visitas = visitaTerrenoService.getVisitaTerrenos();
        if (visitas == null){
        	logger.debug("Nulo");
        }
        return visitas;	
    }
    
    
    
    /**
     * Acepta una solicitud POST con un parametro JSON
     * @param envio Objeto serializado de Muestras
     * @return una cadena con el resultado*/
     
    @RequestMapping(value = "visitasMA", method = RequestMethod.POST, consumes = "application/json")
    public @ResponseBody
    String sendMessageJson(@RequestBody VisitaTerrenoMA[] envio) {
        logger.debug("Insertando/Actualizando VisitaTerreno");
        if (envio == null){
        	logger.debug("Nulo");
        	return "No recibi nada!";
        }
        else{
        	List<VisitaTerrenoMA> visitas = Arrays.asList(envio);
        	for (VisitaTerrenoMA visita : visitas){
        		VisitaTerrenoId vtId = new VisitaTerrenoId();
        		vtId.setCodigo(visita.getVisitaId().getCodigo());
        		vtId.setFechaVisita(new Date(visita.getVisitaId().getFechaVisita().getTime()));
        		Boolean existe = visitaTerrenoService.checkMuestra(vtId);
        		if (!existe){
                    visita.setEncuestaValida(true);
        			visitaTerrenoService.addVisitaTerreno(visita);
        		} else {
                    VisitaTerrenoMA visitaTerrenoMA = visitaTerrenoService.getVisitaTerreno(vtId);
                    if (visitaTerrenoMA != null) {
                        if (visitaTerrenoMA.getEncuestaValida() != null) {
                            visita.setEncuestaValida(visitaTerrenoMA.getEncuestaValida());
                        } else {
                            visita.setEncuestaValida(null);
                        }
                    }
        			visitaTerrenoService.updateVisitaTerreno(visita);
        		}
        	}
        }
        return "Datos recibidos!";
    }  
    
    
}
