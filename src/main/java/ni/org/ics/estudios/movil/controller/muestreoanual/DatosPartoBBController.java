package ni.org.ics.estudios.movil.controller.muestreoanual;

import ni.org.ics.estudios.domain.muestreoanual.DatosPartoBB;
import ni.org.ics.estudios.domain.muestreoanual.DatosPartoBBId;
import ni.org.ics.estudios.service.muestreoanual.DatosPartoBBService;
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
public class DatosPartoBBController {
	@Resource(name="datosPartoBBService")
	private DatosPartoBBService datosPartoBBService;
	private static final Logger logger = LoggerFactory.getLogger(DatosPartoBBController.class);

	/**
     * Retorna obsequios. Acepta una solicitud GET para JSON
     * @return obsequios JSON
     */
    @RequestMapping(value = "datospartobb", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    List<DatosPartoBB> descargaDatosPartoBB() {
        logger.info("Descargando toda la informacion de los datos de los DatosPartoBB");
        List<DatosPartoBB> datosPartoBB = datosPartoBBService.getDatosPartoBB();
        if (datosPartoBB == null){
        	logger.debug("Nulo");
        }
        return datosPartoBB;	
    }
    
    
    
    /**
     * Acepta una solicitud POST con un parametro JSON
     * @param obsequio Objeto serializado de DatosPartoBB
     * @return una cadena con el resultado*/
     
    @RequestMapping(value = "datospartobb", method = RequestMethod.POST, consumes = "application/json")
    public @ResponseBody
    String sendMessageJson(@RequestBody DatosPartoBB[] envio) {
        logger.debug("Insertando/Actualizando DatosPartoBB");
        if (envio == null){
        	logger.debug("Nulo");
        	return "No recibi nada!";
        }
        else{
        	List<DatosPartoBB> datospartobbs = Arrays.asList(envio);
        	for (DatosPartoBB datospartobb: datospartobbs){
        		DatosPartoBBId datospartobbId = new DatosPartoBBId();
        		datospartobbId.setCodigo(datospartobb.getDatosPartoId().getCodigo());
        		datospartobbId.setFechaDatosParto(new Date(datospartobb.getDatosPartoId().getFechaDatosParto().getTime()));
        		Boolean existe = datosPartoBBService.checkDatosPartoBB(datospartobbId);
        		if (!existe){
                    datospartobb.setEncuestaValida(true);
        			datosPartoBBService.addDatosPartoBB(datospartobb);
        		}
        		else{
                    DatosPartoBB datos = datosPartoBBService.getDatosPartoBB(datospartobbId);
                    if (datos != null) {
                        if (datos.getEncuestaValida() != null) {
                            datospartobb.setEncuestaValida(datos.getEncuestaValida());
                        } else {
                            datospartobb.setEncuestaValida(null);
                        }
                    }
        			datosPartoBBService.updateDatosPartoBB(datospartobb);
        		}
        	}
        }
        return "Datos recibidos!";
    }  
    
    
}
