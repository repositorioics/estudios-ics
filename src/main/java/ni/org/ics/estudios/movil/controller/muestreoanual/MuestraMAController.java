package ni.org.ics.estudios.movil.controller.muestreoanual;

import ni.org.ics.estudios.domain.muestreoanual.MuestraMA;
import ni.org.ics.estudios.domain.muestreoanual.MuestraId;
import ni.org.ics.estudios.service.muestreoanual.MuestraService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
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
public class MuestraMAController {
	@Resource(name="muestraMAService")
	private MuestraService muestraService;
	private static final Logger logger = LoggerFactory.getLogger(MuestraMAController.class);

	/**
     * Retorna muestras. Acepta una solicitud GET para JSON
     * @return muestras JSON
     */
    @RequestMapping(value = "muestrasMA", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    List<MuestraMA> descargarmuestras() throws ParseException {
        logger.info("Descargando toda la informacion de los datos de las muestras");
        List<MuestraMA> muestras = muestraService.getMuestrasActual();
        if (muestras == null){
        	logger.debug("Nulo");
        }
        return muestras;	
    }
    
    
    
    /**
     * Acepta una solicitud POST con un parametro JSON
     * @param envio Objeto serializado de Muestras
     * @return una cadena con el resultado*/
     
    @RequestMapping(value = "muestrasMA", method = RequestMethod.POST, consumes = "application/json")
    public @ResponseBody
    String sendMessageJson(@RequestBody MuestraMA[] envio) {
        logger.debug("Insertando/Actualizando muestras");
        if (envio == null){
        	logger.debug("Nulo");
        	return "No recibi nada!";
        }
        else{
        	List<MuestraMA> muestras = Arrays.asList(envio);
        	for (MuestraMA muestra : muestras){
        		MuestraId mId = new MuestraId();
        		mId.setCodigo(muestra.getmId().getCodigo());
        		mId.setFechaMuestra(new Date(muestra.getmId().getFechaMuestra().getTime()));
        		Boolean existe = muestraService.checkMuestra(mId);
        		if (!existe){
        			muestraService.addMuestra(muestra);
        		}
        		else{
        			muestraService.updateMuestra(muestra);
        		}
        	}
        }
        return "Datos recibidos!";
    }  
    
    
}
