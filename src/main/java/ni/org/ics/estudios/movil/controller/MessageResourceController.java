package ni.org.ics.estudios.movil.controller;

import ni.org.ics.estudios.language.MessageResource;
import ni.org.ics.estudios.service.MessageResourceService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Miguel Salinas on 5/5/2017.
 * V1.0
 */
@Controller
@RequestMapping("/movil/*")
public class MessageResourceController {

    private static final Logger logger = LoggerFactory.getLogger(MessageResourceController.class);

    @Resource(name = "messageResourceService")
    private MessageResourceService messageResourceService;

    @RequestMapping(value = "catalogos", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    List<MessageResource> getMessageResources(){
        logger.info("Descargando toda la informacion de los datos de los catalogos");
        List<MessageResource> catalogos = messageResourceService.getCatalogos();
        if (catalogos == null){
            logger.debug("Nulo");
        }
        return  catalogos;
    }

}
