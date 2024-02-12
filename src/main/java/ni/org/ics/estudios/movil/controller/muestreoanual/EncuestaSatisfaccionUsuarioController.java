package ni.org.ics.estudios.movil.controller.muestreoanual;

import ni.org.ics.estudios.domain.muestreoanual.EncuestaSatisfaccionUsuario;
import ni.org.ics.estudios.domain.muestreoanual.EncuestaSatisfaccionUsuarioCC;
import ni.org.ics.estudios.service.covid.CovidService;
import ni.org.ics.estudios.service.muestreoanual.EncuestaSatisfaccionUsuarioService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * Created by Ing. Santiago Carballo on 01/03/2023.
 */

@Controller
@RequestMapping("/movil/*")
public class EncuestaSatisfaccionUsuarioController {

    @Resource(name = "encuestaSatisfaccionUsuarioService")
    private EncuestaSatisfaccionUsuarioService encuestaSatisfaccionUsuarioService;

    private static final Logger logger = LoggerFactory.getLogger(EncuestaSatisfaccionUsuarioController.class);

    @RequestMapping(value = "encuestaSatisfaccionUsuario", method = RequestMethod.POST, consumes = "application/json")
    public @ResponseBody
    String saveEncuestaSatisfaccionUsuario(@RequestBody EncuestaSatisfaccionUsuario[] objetos){
        logger.debug("Insertando/Actualizando las encuestas de satisfaccion de usuario");
        if (objetos == null){
            logger.debug("Nulo");
            return "No recibi nada!";
        }else{
            List<EncuestaSatisfaccionUsuario> encuestaSatisfaccionUsuarioList = Arrays.asList(objetos);
            for(EncuestaSatisfaccionUsuario encuestaSatisfaccionUsuario : encuestaSatisfaccionUsuarioList) {

                Boolean existe = encuestaSatisfaccionUsuarioService.checkEncuestaSatisfaccionUsuario(new Date().getTime(), encuestaSatisfaccionUsuario.getCodigoParticipante());
                if (!existe){
                    encuestaSatisfaccionUsuario.setFechaRegistro(new Date());
                    encuestaSatisfaccionUsuario.setEncuestaValida(true);
                    encuestaSatisfaccionUsuarioService.addEncuestaSatisfaccionUsuario(encuestaSatisfaccionUsuario);
                } else {
                    EncuestaSatisfaccionUsuario encuestaSU = encuestaSatisfaccionUsuarioService.getEncuestaSatisfaccionUsuario(new Date().getTime(), encuestaSatisfaccionUsuario.getCodigoParticipante());
                    encuestaSatisfaccionUsuario.setCodigo(encuestaSU.getCodigo());
                    if (encuestaSU != null) {
                        if (encuestaSU.getEncuestaValida() != null) {
                            encuestaSatisfaccionUsuario.setEncuestaValida(encuestaSU.getEncuestaValida());
                        } else {
                            encuestaSatisfaccionUsuario.setEncuestaValida(null);
                        }
                        if (encuestaSU.getFechaRegistro() != null) {
                            encuestaSatisfaccionUsuario.setFechaRegistro(encuestaSU.getFechaRegistro());
                        } else {
                            encuestaSatisfaccionUsuario.setFechaRegistro(null);
                        }
                    }
                    encuestaSatisfaccionUsuarioService.updateEncuestaSatisfaccionUsuario(encuestaSatisfaccionUsuario);
                }
                //encuestaSatisfaccionUsuario.setFechaRegistro(new Date());
                //encuestaSatisfaccionUsuarioService.saveOrUpdateEncuestaSatisfaccionUsuario(encuestaSatisfaccionUsuario);
            }
        }
        return "Datos recibidos!";
    }

    @RequestMapping(value = "encuestaSatisfaccionUsuarioCc", method = RequestMethod.POST, consumes = "application/json")
    public @ResponseBody
    String saveEncuestaSatisfaccionUsuarioCc(@RequestBody EncuestaSatisfaccionUsuarioCC[] objetos){
        logger.debug("Insertando/Actualizando las encuestas de satisfaccion de usuario - CC");
        if (objetos == null){
            logger.debug("Nulo");
            return "No recibi nada!";
        }else{
            List<EncuestaSatisfaccionUsuarioCC> encuestaSatisfaccionUsuarioCcList = Arrays.asList(objetos);
            for(EncuestaSatisfaccionUsuarioCC encuestaSatisfaccionUsuarioCc : encuestaSatisfaccionUsuarioCcList) {
                encuestaSatisfaccionUsuarioCc.setFechaRegistro(new Date());
                encuestaSatisfaccionUsuarioService.saveOrUpdateEncuestaSatisfaccionUsuarioCc(encuestaSatisfaccionUsuarioCc);
            }
        }
        return "Datos recibidos!";
    }
}
