package ni.org.ics.estudios.movil.controller.cohortefamilia;

import ni.org.ics.estudios.domain.cohortefamilia.Muestra;
import ni.org.ics.estudios.domain.cohortefamilia.MuestraSuperficie;
import ni.org.ics.estudios.service.cohortefamilia.MuestraService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * Created by Miguel Salinas on 5/17/2017.
 * V1.0
 */
@Controller
@RequestMapping("/movil/*")
public class MuestraController {

    private static final Logger logger = LoggerFactory.getLogger(MuestraController.class);

    @Resource(name = "muestraService")
    private MuestraService muestraService;

    /**
     * Acepta una solicitud GET para JSON
     * @return JSON
     */
    @RequestMapping(value = "muestras/{username}", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    List<Muestra> getMuestras(@PathVariable String username) {
        logger.info("Descargando toda la informacion de formularios muestras para el usuario " +username);
        List<Muestra> respuestaList = muestraService.getMuestrasByUser(username);
        if (respuestaList == null){
            logger.debug("Nulo");
        }
        return respuestaList;
    }

    /**
     * Acepta una solicitud GET para JSON
     * @return JSON
     */
    @RequestMapping(value = "muestras", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    List<Muestra> getMuestras() throws Exception{
        logger.info("Descargando toda la informacion de formularios muestras para el usuario ");
        List<Muestra> respuestaList = muestraService.getMuestras();
        if (respuestaList == null){
            logger.debug("Nulo");
        }
        return respuestaList;
    }
    
    /**
     * Acepta una solicitud GET para JSON
     * @return JSON
     */
    @RequestMapping(value = "mxstx", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    List<Muestra> getMuestrasTX() throws Exception{
        logger.info("Descargando toda la informacion de formularios muestras para el usuario ");
        List<Muestra> respuestaList = muestraService.getMuestrasTx();
        if (respuestaList == null){
            logger.debug("Nulo");
        }
        return respuestaList;
    }

    /**
     * Acepta una solicitud POST con un par�metro JSON
     * @param muestras Objeto serializado de Muestras
     * @return String con el resultado
     */
    @RequestMapping(value = "muestras", method = RequestMethod.POST, consumes = "application/json")
    public @ResponseBody String saveMuestras(@RequestBody Muestra[] muestras){
        logger.debug("Insertando/Actualizando muestras");
        if (muestras == null){
            logger.debug("Nulo");
            return "No recibi nada!";
        }else{
            List<Muestra> muestraList = Arrays.asList(muestras);
            for (Muestra muestra : muestraList){
                muestra.setFechaRecibido(new Date());
                muestraService.saveOrUpdate(muestra);
            }
        }
        return "Datos recibidos!";
    }

    /**
     * Acepta una solicitud GET para JSON
     * @return JSON
     */
    @RequestMapping(value = "muestrasSuperficie", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    List<MuestraSuperficie> getMuestrasSuperficie() throws Exception{
        logger.info("Descargando toda la informacion de formularios muestras de superficie para el usuario ");
        List<MuestraSuperficie> respuestaList = muestraService.getMuestrasSuperficie();
        if (respuestaList == null){
            logger.debug("Nulo");
        }
        return respuestaList;
    }

    /**
     * Acepta una solicitud POST con un par�metro JSON
     * @param muestras Objeto serializado de Muestras superficie
     * @return String con el resultado
     */
    @RequestMapping(value = "muestrasSuperficie", method = RequestMethod.POST, consumes = "application/json")
    public @ResponseBody String saveMuestras(@RequestBody MuestraSuperficie[] muestras){
        logger.debug("Insertando/Actualizando muestras superficie");
        if (muestras == null){
            logger.debug("Nulo");
            return "No recibi nada!";
        }else{
            List<MuestraSuperficie> muestraList = Arrays.asList(muestras);
            for (MuestraSuperficie muestra : muestraList){
                muestraService.saveOrUpdateMxSup(muestra);
            }
        }
        return "Datos recibidos!";
    }

    /**
     * Acepta una solicitud GET para JSON
     * @return JSON
     */
    @RequestMapping(value = "muestrasCasosUO1", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    List<Muestra> getMuestrasUO1() throws Exception{
        logger.info("Descargando toda la informacion de formularios muestras UO1 para el usuario ");
        List<Muestra> respuestaList = muestraService.getMuestrasUO1();
        if (respuestaList == null){
            logger.debug("Nulo");
        }
        return respuestaList;
    }

    /**
     * Acepta una solicitud GET para JSON
     * @return JSON
     */
    @RequestMapping(value = "muestrasCasosCovid19", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    List<Muestra> getMuestrasCasosCovid19() throws Exception{
        logger.info("Descargando toda la informacion de formularios muestras COVID19 para el usuario ");
        List<Muestra> respuestaList = muestraService.getMuestrasCovid19();
        if (respuestaList == null){
            logger.debug("Nulo");
        }
        return respuestaList;
    }
}
