package ni.org.ics.estudios.movil.controller.muestreoanual;

import ni.org.ics.estudios.domain.muestreoanual.RazonNoData;
import ni.org.ics.estudios.domain.muestreoanual.RazonNoDataId;
import ni.org.ics.estudios.service.muestreoanual.RazonNoDataService;
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
public class RazonNoDataController {
	@Resource(name="razonNoDataService")
	private RazonNoDataService razonNoDataService;
	private static final Logger logger = LoggerFactory.getLogger(RazonNoDataController.class);

	/**
     * Retorna RazonNoData. Acepta una solicitud GET para JSON
     * @return muestras JSON
     */
    @RequestMapping(value = "rnds", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    List<RazonNoData> descargarRazonNoData() {
        logger.info("Descargando toda la informacion de los datos de las RazonNoData");
        List<RazonNoData> rnds = razonNoDataService.getRazonNoData();
        if (rnds == null){
        	logger.debug("Nulo");
        }
        return rnds;	
    }
    
    
    
    /**
     * Acepta una solicitud POST con un parametro JSON
     * @param muestra Objeto serializado de Muestras
     * @return una cadena con el resultado*/
     
    @RequestMapping(value = "rnds", method = RequestMethod.POST, consumes = "application/json")
    public @ResponseBody
    String sendMessageJson(@RequestBody RazonNoData[] envio) {
        logger.debug("Insertando/Actualizando RazonNoData");
        if (envio == null){
        	logger.debug("Nulo");
        	return "No recibi nada!";
        }
        else{
        	List<RazonNoData> rnds = Arrays.asList(envio);
        	for (RazonNoData rnd : rnds){
        		RazonNoDataId rndId = new RazonNoDataId();
        		rndId.setCodigo(rnd.getRndId().getCodigo());
        		rndId.setFechaRegistro(new Date(rnd.getRndId().getFechaRegistro().getTime()));
        		Boolean existe = razonNoDataService.checkRazonNoData(rndId);
        		if (!existe){
        			razonNoDataService.addRazonNoData(rnd);
        		}
        		else{
        			razonNoDataService.updateRazonNoData(rnd);
        		}
        	}
        }
        return "Datos recibidos!";
    }  
    
    
}
