package ni.org.ics.estudios.movil.controller.muestreoanual;

import ni.org.ics.estudios.domain.muestreoanual.DatosVisitaTerreno;
import ni.org.ics.estudios.domain.muestreoanual.DatosVisitaTerrenoId;
import ni.org.ics.estudios.service.muestreoanual.DatosVisitaTerrenoService;
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
public class DatosVisitaTerrenoController {
	@Resource(name="datosVisitaTerrenoService")
	private DatosVisitaTerrenoService datosVisitaTerrenoService;
	private static final Logger logger = LoggerFactory.getLogger(DatosVisitaTerrenoController.class);

	/**
     * Retorna muestras. Acepta una solicitud GET para JSON
     * @return muestras JSON
     */
    @RequestMapping(value = "visitasn", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    List<DatosVisitaTerreno> descargarDatosVisitaTerreno() {
        logger.info("Descargando toda la informacion de los datos de las VisitaTerreno");
        List<DatosVisitaTerreno> visitas = datosVisitaTerrenoService.getDatosVisitaTerrenos();
        if (visitas == null){
        	logger.debug("Nulo");
        }
        return visitas;	
    }
    
    
    
    /**
     * Acepta una solicitud POST con un parametro JSON
     * @param muestra Objeto serializado de Muestras
     * @return una cadena con el resultado*/
     
    @RequestMapping(value = "visitasn", method = RequestMethod.POST, consumes = "application/json")
    public @ResponseBody
    String sendMessageJson(@RequestBody DatosVisitaTerreno[] envio) {
        logger.debug("Insertando/Actualizando DatosVisitaTerreno");
        if (envio == null){
        	logger.debug("Nulo");
        	return "No recibi nada!";
        }
        else{
        	List<DatosVisitaTerreno> visitas = Arrays.asList(envio);
        	for (DatosVisitaTerreno visita : visitas){
        		DatosVisitaTerrenoId vtId = new DatosVisitaTerrenoId();
        		vtId.setCodigo(visita.getVisitaId().getCodigo());
        		vtId.setFechaVisita(new Date(visita.getVisitaId().getFechaVisita().getTime()));
        		Boolean existe = datosVisitaTerrenoService.checkDatosVisitaTerreno(vtId);
        		if (!existe){
                    visita.setEncuestaValida(true);
        			datosVisitaTerrenoService.addDatosVisitaTerreno(visita);
        		} else{
                    DatosVisitaTerreno datosVisitaTerreno = datosVisitaTerrenoService.getDatosVisitaTerreno(vtId);
                    if (datosVisitaTerreno != null) {
                        if (datosVisitaTerreno.getEncuestaValida() != null) {
                            visita.setEncuestaValida(datosVisitaTerreno.getEncuestaValida());
                        } else {
                            visita.setEncuestaValida(null);
                        }
                    }
        			datosVisitaTerrenoService.updateDatosVisitaTerreno(visita);
        		}
        	}
        }
        return "Datos recibidos!";
    }  
    
    
}
