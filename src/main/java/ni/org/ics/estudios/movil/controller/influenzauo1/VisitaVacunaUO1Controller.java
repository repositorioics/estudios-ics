package ni.org.ics.estudios.movil.controller.influenzauo1;

import ni.org.ics.estudios.domain.influenzauo1.VisitaVacunaUO1;
import ni.org.ics.estudios.service.influenzauo1.VisitaVacunaUO1Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/movil/*")
public class VisitaVacunaUO1Controller {

    private static final Logger logger = LoggerFactory.getLogger(VisitaVacunaUO1Controller.class);

    @Resource(name = "visitaVacunaUO1Service")
    private VisitaVacunaUO1Service visitaVacunaUO1Service;

    @RequestMapping(value = "visitasVacunasUO1", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    List<VisitaVacunaUO1> getVisitasVacunasUO1(){
        logger.info("Descargando toda la informacion de visitas vacunas influenza UO1");
        List<VisitaVacunaUO1> visitasVacunas = this.visitaVacunaUO1Service.getVisitasVacunas();
        if (visitasVacunas == null){
            logger.debug("Nulo");
        }
        return  visitasVacunas;
    }

    @RequestMapping(value = "visitasVacunasUO1", method = RequestMethod.POST, consumes = "application/json")
    public @ResponseBody String saveVisitasVacunasUO1(@RequestBody VisitaVacunaUO1[] objetos){
        logger.debug("Insertando/Actualizando visitas vacunas influenza UO1");
        try {
            if (objetos == null){
                logger.debug("Nulo");
                return "No recibi nada!";
            }else{
                List<VisitaVacunaUO1> vacunaUO1List = Arrays.asList(objetos);
                for(VisitaVacunaUO1 vacunaUO1 : vacunaUO1List) {
                    this.visitaVacunaUO1Service.saveOrUpdate(vacunaUO1);
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
