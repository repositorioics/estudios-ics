package ni.org.ics.estudios.web.controller.entomologia;

import ni.org.ics.estudios.domain.entomologia.CuestionarioHogar;
import ni.org.ics.estudios.domain.entomologia.CuestionarioHogarPoblacion;
import ni.org.ics.estudios.service.entomologia.CuestionarioHogarService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.text.ParseException;
import java.util.Arrays;
import java.util.List;

/**
 * Created by miguel on 15/8/2022.
 */
@Controller
@RequestMapping("/ento/*")
public class CuestionarioHogarWebController {
    private static final Logger logger = LoggerFactory.getLogger(CuestionarioHogarWebController.class);

    @RequestMapping(value = "informacion", method = RequestMethod.GET)
    public String informacion(Model model) throws ParseException {
        logger.debug("Solicitar informacion de cuestionarios entomologia para generar excel");

        return "entomologia/informacion";
    }
}
