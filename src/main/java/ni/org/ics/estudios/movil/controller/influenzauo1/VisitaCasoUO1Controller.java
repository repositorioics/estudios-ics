package ni.org.ics.estudios.movil.controller.influenzauo1;

import ni.org.ics.estudios.domain.influenzauo1.VisitaCasoUO1;
import ni.org.ics.estudios.service.influenzauo1.VisitaCasoUO1Service;
import org.hibernate.HibernateException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.sql.BatchUpdateException;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/movil/*")
public class VisitaCasoUO1Controller {

    private static final Logger logger = LoggerFactory.getLogger(ParticipanteCasoUO1mController.class);

    @Resource(name = "visitaCasoUO1Service")
    private VisitaCasoUO1Service visitaCasoUO1Service;

    @RequestMapping(value = "visitasCasoUO1", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    List<VisitaCasoUO1> getParticipantesCasoUO1(){
        logger.info("Descargando toda la informacion de visitas casos positivos activos influenza UO1");
        List<VisitaCasoUO1> casosActivos = this.visitaCasoUO1Service.getVisitasCasosActivos();
        if (casosActivos == null){
            logger.debug("Nulo");
        }
        return  casosActivos;
    }

    @RequestMapping(value = "visitasCasoUO1", method = RequestMethod.POST, consumes = "application/json")
    public @ResponseBody String saveParticipantesCasoUO1(@RequestBody VisitaCasoUO1[] objetos){
        logger.debug("Insertando/Actualizando visitas casos positivos activos influenza UO1");
        try {
            if (objetos == null){
                logger.debug("Nulo");
                return "No recibi nada!";
            }else{
                List<VisitaCasoUO1> casoUO1List = Arrays.asList(objetos);
                for(VisitaCasoUO1 casoUO1 : casoUO1List) {
                    this.visitaCasoUO1Service.saveOrUpdate(casoUO1);
                }
            }
            return "Datos recibidos!";
        }catch (Exception ex){
            if (ex.getCause() != null && ex.getCause().getCause()!=null)
                return ex.getCause().getCause().toString();
            else return ex.getMessage();
        }
    }
}
