package ni.org.ics.estudios.movil.controller.muestreoanual;

import ni.org.ics.estudios.domain.muestreoanual.PesoyTalla;
import ni.org.ics.estudios.domain.muestreoanual.PesoyTallaId;
import ni.org.ics.estudios.service.muestreoanual.PesoyTallaService;
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
public class PesoyTallaController {
	@Resource(name="pesoyTallaService")
	private PesoyTallaService pesoyTallaService;
	private static final Logger logger = LoggerFactory.getLogger(PesoyTallaController.class);

	/**
     * Retorna obsequios. Acepta una solicitud GET para JSON
     * @return obsequios JSON
     */
    @RequestMapping(value = "pts", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    List<PesoyTalla> descargaPesoyTalla() {
        logger.info("Descargando toda la informacion de los datos de los obsequios");
        List<PesoyTalla> pts = pesoyTallaService.getPesoytallaEB();
        if (pts == null){
        	logger.debug("Nulo");
        }
        return pts;	
    }
    
    
    
    /**
     * Acepta una solicitud POST con un parametro JSON
     * @param obsequio Objeto serializado de Obsequio
     * @return una cadena con el resultado*/
     
    @RequestMapping(value = "pts", method = RequestMethod.POST, consumes = "application/json")
    public @ResponseBody
    String sendMessageJson(@RequestBody PesoyTalla[] envio) {
        logger.debug("Insertando/Actualizando PesoyTalla");
        if (envio == null){
        	logger.debug("Nulo");
        	return "No recibi nada!";
        }
        else{
        	List<PesoyTalla> pts = Arrays.asList(envio);
        	for (PesoyTalla pt: pts){
        		PesoyTallaId ptId = new PesoyTallaId();
        		ptId.setCodigo(pt.getPtId().getCodigo());
        		ptId.setFechaPT(new Date(pt.getPtId().getFechaPT().getTime()));
        		Boolean existe = pesoyTallaService.checkPesoyTallaEB(ptId);
        		if (!existe){
        			pesoyTallaService.addPesoyTallaEB(pt);
        		}
        		else{
        			pesoyTallaService.updatePesoyTallaEB(pt);
        		}
        	}
        }
        return "Datos recibidos!";
    }  
    
    
}
