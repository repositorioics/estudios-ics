package ni.org.ics.estudios.movil.controller.cohortefamilia;

import ni.org.ics.estudios.domain.cohortefamilia.Banio;
import ni.org.ics.estudios.domain.cohortefamilia.Cocina;
import ni.org.ics.estudios.domain.cohortefamilia.Comedor;
import ni.org.ics.estudios.domain.cohortefamilia.Habitacion;
import ni.org.ics.estudios.domain.cohortefamilia.Sala;
import ni.org.ics.estudios.domain.cohortefamilia.Ventana;
import ni.org.ics.estudios.service.cohortefamilia.AreaAmbienteService;
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
public class AreaAmbienteController {
    private static final Logger logger = LoggerFactory.getLogger(AreaAmbienteController.class);

    @Resource(name = "areaAmbienteService")
    private AreaAmbienteService areaAmbienteService;

    /**
     * Acepta una solicitud GET para JSON
     * @return JSON
     */
    @RequestMapping(value = "banios", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    List<Banio> getBanios() {
        logger.info("Descargando toda la informacion de los datos de los Banio");
        List<Banio> baniosByUser = areaAmbienteService.getBanios();
        if (baniosByUser == null){
            logger.debug("Nulo");
        }
        return baniosByUser;
    }

    /**
     * Acepta una solicitud GET para JSON
     * @return JSON
     */
    @RequestMapping(value = "cocinas", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    List<Cocina> getCocinas() {
        logger.info("Descargando toda la informacion de los datos de las cocinas ");
        List<Cocina> listaByUser = areaAmbienteService.getCocinas();
        if (listaByUser == null){
            logger.debug("Nulo");
        }
        return listaByUser;
    }

    /**
     * Acepta una solicitud GET para JSON
     * @return JSON
     */
    @RequestMapping(value = "comedores", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    List<Comedor> getComedores() {
        logger.info("Descargando toda la informacion de los datos de las comedores");
        List<Comedor> listaByUser = areaAmbienteService.getComedores();
        if (listaByUser == null){
            logger.debug("Nulo");
        }
        return listaByUser;
    }

    /**
     * Acepta una solicitud GET para JSON
     * @return JSON
     */
    @RequestMapping(value = "habitaciones", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    List<Habitacion> getHabitaciones() {
        logger.info("Descargando toda la informacion de los datos de las habitaciones");
        List<Habitacion> listaByUser = areaAmbienteService.getHabitaciones();
        if (listaByUser == null){
            logger.debug("Nulo");
        }
        return listaByUser;
    }

    /**
     * Acepta una solicitud GET para JSON
     * @return JSON
     */
    @RequestMapping(value = "salas", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    List<Sala> getSalas() {
        logger.info("Descargando toda la informacion de los datos de las salas");
        List<Sala> listaByUser = areaAmbienteService.getSalas();
        if (listaByUser == null){
            logger.debug("Nulo");
        }
        return listaByUser;
    }

    /**
     * Acepta una solicitud GET para JSON
     * @return JSON
     */
    @RequestMapping(value = "ventanas", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    List<Ventana> getVentanas() {
        logger.info("Descargando toda la informacion de los datos de las ventanas ");
        List<Ventana> listaByUser = areaAmbienteService.getVentanas();
        if (listaByUser == null){
            logger.debug("Nulo");
        }
        return listaByUser;
    }

    /**
     * Acepta una solicitud POST con un par�metro JSON
     * @param envio Objeto serializado de Baños
     * @return String con el resultado
     */
    @RequestMapping(value = "banios", method = RequestMethod.POST, consumes = "application/json")
    public @ResponseBody String saveBanios(@RequestBody Banio[] envio) {
        logger.debug("Insertando/Actualizando datos Banios");
        if (envio == null){
            logger.debug("Nulo");
            return "No recibi nada!";
        }
        else{
            List<Banio> areasAmbienteList = Arrays.asList(envio);
            for (Banio areaAmb : areasAmbienteList){
                areaAmbienteService.saveOrUpdateBanio(areaAmb);
            }
        }
        return "Datos recibidos!";
    }


    /**
     * Acepta una solicitud POST con un par�metro JSON
     * @param envio Objeto serializado de Cocinas
     * @return String con el resultado
     */
    @RequestMapping(value = "cocinas", method = RequestMethod.POST, consumes = "application/json")
    public @ResponseBody String saveCocinas(@RequestBody Cocina[] envio) {
        logger.debug("Insertando/Actualizando datos Cocinas");
        if (envio == null){
            logger.debug("Nulo");
            return "No recibi nada!";
        }
        else{
            List<Cocina> areasAmbienteList = Arrays.asList(envio);
            for (Cocina areaAmb : areasAmbienteList){
                areaAmbienteService.saveOrUpdateCocina(areaAmb);
            }
        }
        return "Datos recibidos!";
    }

    /**
     * Acepta una solicitud POST con un par�metro JSON
     * @param envio Objeto serializado de Comedores
     * @return String con el resultado
     */
    @RequestMapping(value = "comedores", method = RequestMethod.POST, consumes = "application/json")
    public @ResponseBody String saveComedores(@RequestBody Comedor[] envio) {
        logger.debug("Insertando/Actualizando datos Comedores");
        if (envio == null){
            logger.debug("Nulo");
            return "No recibi nada!";
        }
        else{
            List<Comedor> areasAmbienteList = Arrays.asList(envio);
            for (Comedor areaAmb : areasAmbienteList){
                areaAmbienteService.saveOrUpdateComedor(areaAmb);
            }
        }
        return "Datos recibidos!";
    }

    /**
     * Acepta una solicitud POST con un par�metro JSON
     * @param envio Objeto serializado de Habitaciones
     * @return String con el resultado
     */
    @RequestMapping(value = "habitaciones", method = RequestMethod.POST, consumes = "application/json")
    public @ResponseBody String saveHabitaciones(@RequestBody Habitacion[] envio) {
        logger.debug("Insertando/Actualizando datos Habitaciones");
        if (envio == null){
            logger.debug("Nulo");
            return "No recibi nada!";
        }
        else{
            List<Habitacion> areasAmbienteList = Arrays.asList(envio);
            for (Habitacion areaAmb : areasAmbienteList){
                areaAmbienteService.saveOrUpdateHabitacion(areaAmb);
            }
        }
        return "Datos recibidos!";
    }

    /**
     * Acepta una solicitud POST con un par�metro JSON
     * @param envio Objeto serializado de Salas
     * @return String con el resultado
     */
    @RequestMapping(value = "salas", method = RequestMethod.POST, consumes = "application/json")
    public @ResponseBody String saveSalas(@RequestBody Sala[] envio) {
        logger.debug("Insertando/Actualizando datos Salas");
        if (envio == null){
            logger.debug("Nulo");
            return "No recibi nada!";
        }
        else{
            List<Sala> areasAmbienteList = Arrays.asList(envio);
            for (Sala areaAmb : areasAmbienteList){
                areaAmbienteService.saveOrUpdateSala(areaAmb);
            }
        }
        return "Datos recibidos!";
    }

    /**
     * Acepta una solicitud POST con un par�metro JSON
     * @param envio Objeto serializado de Ventanas
     * @return String con el resultado
     */
    @RequestMapping(value = "ventanas", method = RequestMethod.POST, consumes = "application/json")
    public @ResponseBody String saveVentanas(@RequestBody Ventana[] envio) {
        logger.debug("Insertando/Actualizando datos Ventanas");
        if (envio == null) {
            logger.debug("Nulo");
            return "No recibi nada!";
        }
        return "Datos recibidos!";
    }
}
