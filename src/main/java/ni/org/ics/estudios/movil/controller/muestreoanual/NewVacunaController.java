package ni.org.ics.estudios.movil.controller.muestreoanual;

import ni.org.ics.estudios.domain.muestreoanual.NewVacuna;
import ni.org.ics.estudios.domain.muestreoanual.NewVacunaId;
import ni.org.ics.estudios.service.muestreoanual.NewVacunaService;
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
public class NewVacunaController {
	@Resource(name="newVacunaService")
	private NewVacunaService newVacunaService;
	private static final Logger logger = LoggerFactory.getLogger(NewVacunaController.class);

	/**
     * Retorna NewVacuna. Acepta una solicitud GET para JSON
     * @return NewVacuna JSON
     */
    @RequestMapping(value = "newvacunas", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    List<NewVacuna> descargarNewVacunas() {
        logger.info("Descargando toda la informacion de los datos de las NewVacuna");
        List<NewVacuna> vacunas = newVacunaService.getNewVacunas();
        if (vacunas == null){
        	logger.debug("Nulo");
        }
        return vacunas;	
    }
    
    
    
    /**
     * Acepta una solicitud POST con un parametro JSON
     * @param muestra Objeto serializado de NewVacuna
     * @return una cadena con el resultado*/
     
    @RequestMapping(value = "newvacunas", method = RequestMethod.POST, consumes = "application/json")
    public @ResponseBody
    String sendMessageJson(@RequestBody NewVacuna[] envio) {
        logger.debug("Insertando/Actualizando NewVacuna");
        if (envio == null){
        	logger.debug("Nulo");
        	return "No recibi nada!";
        }
        else{
        	List<NewVacuna> vacunas = Arrays.asList(envio);
        	for (NewVacuna vacuna : vacunas){
        		NewVacunaId vacId = new NewVacunaId();
        		vacId.setCodigo(vacuna.getVacunaId().getCodigo());
        		vacId.setFechaRegistroVacuna(new Date(vacuna.getVacunaId().getFechaRegistroVacuna().getTime()));
        		Boolean existe = newVacunaService.checkNewVacuna(vacId);
        		if (!existe){
                    vacuna.setEncuestaValida(true);
        			newVacunaService.addNewVacuna(vacuna);
        		}
        		else{
                    NewVacuna newVacuna = newVacunaService.getNewVacuna(vacId);
                    if (newVacuna != null) {
                        if (newVacuna.getEncuestaValida() != null) {
                            vacuna.setEncuestaValida(newVacuna.getEncuestaValida());
                        } else {
                            vacuna.setEncuestaValida(null);
                        }
                    }
        			newVacunaService.updateNewVacuna(vacuna);
        		}
        	}
        }
        return "Datos recibidos!";
    }  
    
    
}
