package ni.org.ics.estudios.movil.controller;

import ni.org.ics.estudios.domain.EnfermedadCronica;
import ni.org.ics.estudios.domain.Tamizaje;
import ni.org.ics.estudios.service.TamizajeService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Miguel Salinas on 5/5/2017.
 * V1.0
 */
@Controller
@RequestMapping("/movil/*")
public class TamizajeController {

    private static final Logger logger = LoggerFactory.getLogger(TamizajeController.class);

    @Resource(name = "tamizajeService")
    private TamizajeService tamizajeService;

    /**
     * Acepta una solicitud GET para JSON
     * @return JSON
     */
    @RequestMapping(value = "tamizajes", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    List<Tamizaje> getTamizajes() {
        logger.info("Descargando toda la informacion de formularios Tamizaje" );
        List<Tamizaje> respuestaList = tamizajeService.getTamizajes();
        if (respuestaList == null){
            logger.debug("Nulo");
        }
        return respuestaList;
    }


    /**
     * Acepta una solicitud POST con un par�metro JSON
     * @param envio Objeto serializado de Tamizaje
     * @return String con el resultado
     */
    @RequestMapping(value = "tamizajes", method = RequestMethod.POST, consumes = "application/json")
    public @ResponseBody String saveTamizajes(@RequestBody Tamizaje[] envio) {
        logger.debug("Insertando/Actualizando formularios Tamizaje");
        if (envio == null){
            logger.debug("Nulo");
            return "No recibi nada!";
        }
        else{
            List<Tamizaje> tamizaje = Arrays.asList(envio);
            for (Tamizaje zp00Screening : tamizaje){
                if (zp00Screening.getAsentimientoVerbal()!=null && zp00Screening.getAsentimientoVerbal().equalsIgnoreCase("null"))
                    zp00Screening.setAsentimientoVerbal(null);
                if (zp00Screening.getRazonNoAceptaParticipar()!=null && zp00Screening.getRazonNoAceptaParticipar().equalsIgnoreCase("null"))
                    zp00Screening.setRazonNoAceptaParticipar(null);
                if (zp00Screening.getAceptaTamizajePersona()!=null && zp00Screening.getAceptaTamizajePersona().equalsIgnoreCase("null"))
                    zp00Screening.setAceptaTamizajePersona("0");
            	tamizajeService.saveOrUpdateTamizaje(zp00Screening);
            }
        }
        return "Datos recibidos!";
    }

    /**
     * Acepta una solicitud GET para JSON
     * @return JSON
     */
    @RequestMapping(value = "enfermedadescro", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    List<EnfermedadCronica> getEnfermedadesCronicas() {
        logger.info("Descargando toda la informacion de formularios EnfermedadCronica" );
        List<EnfermedadCronica> respuestaList = tamizajeService.getEnfermedadesCronicas();
        if (respuestaList == null){
            logger.debug("Nulo");
        }
        return respuestaList;
    }


    /**
     * Acepta una solicitud POST con un par�metro JSON
     * @param envio Objeto serializado de Tamizaje
     * @return String con el resultado
     */
    @RequestMapping(value = "enfermedadescro", method = RequestMethod.POST, consumes = "application/json")
    public @ResponseBody String saveEnfermedadCronica(@RequestBody EnfermedadCronica[] envio) {
        logger.debug("Insertando/Actualizando formularios EnfermedadCronica");
        if (envio == null){
            logger.debug("Nulo");
            return "No recibi nada!";
        }
        else{
            List<EnfermedadCronica> enfermedades = Arrays.asList(envio);
            for (EnfermedadCronica enfermedadCronica : enfermedades){
                tamizajeService.saveOrUpdateEnfermedadCronica(enfermedadCronica);
            }
        }
        return "Datos recibidos!";
    }

}
