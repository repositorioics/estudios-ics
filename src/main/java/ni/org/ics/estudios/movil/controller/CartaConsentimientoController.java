package ni.org.ics.estudios.movil.controller;

import ni.org.ics.estudios.domain.CartaConsentimiento;
import ni.org.ics.estudios.service.CartaConsentimientoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * Created by Miguel Salinas on 5/5/2017.
 * V1.0
 */
@Controller
@RequestMapping("/movil/*")
public class CartaConsentimientoController {

    private static final Logger logger = LoggerFactory.getLogger(CartaConsentimientoController.class);

    @Resource(name = "cartaConsentimientoService")
    private CartaConsentimientoService cartaConsentimientoService;

    @RequestMapping(value = "cartasConsen/{username}", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    List <CartaConsentimiento> getCartasConsentimientoByUser(@PathVariable String username) {
        logger.info("Descargando toda la informacion de los datos de las cartas de consentimiento para el usuario " +username);
        List<CartaConsentimiento> cartaConsentimientos = cartaConsentimientoService.getCartasConsentimientoByUser(username);
        if (cartaConsentimientos == null) {
            logger.debug("Nulo");
        }

        return cartaConsentimientos;
    }

    @RequestMapping(value = "cartasConsen", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    List <CartaConsentimiento> getCartasConsentimiento() {
        logger.info("Descargando toda la informacion de los datos de las cartas de consentimiento");
        List<CartaConsentimiento> cartaConsentimientos = cartaConsentimientoService.getCartasConsentimiento();
        if (cartaConsentimientos == null) {
            logger.debug("Nulo");
        }

        return cartaConsentimientos;
    }

    @RequestMapping(value = "cartasConsen", method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody String saveCartasConsentimiento(@RequestBody CartaConsentimiento[] objetos){
        logger.debug("Insertando/Actualizando cartas de consentimiento");
        if (objetos == null){
            logger.debug("Nulo");
            return "No recibi nada!";
        }else{
            List<CartaConsentimiento> cartaConsentimientos = Arrays.asList(objetos);
            for(CartaConsentimiento objeto : cartaConsentimientos){
                objeto.setFechaRecibido(new Date());
                cartaConsentimientoService.saveOrUpdateCartaConsentimiento(objeto);
            }
        }
        return "Datos recibidos!";
    }

}
