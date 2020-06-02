package ni.org.ics.estudios.web.controller.Cartas;

import com.google.gson.Gson;
import ni.org.ics.estudios.domain.catalogs.Carta;
import ni.org.ics.estudios.service.scancarta.ScanCartaService;
import org.apache.commons.lang3.text.translate.UnicodeEscaper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by ICS on 28/04/2020.
 */

@Controller
@RequestMapping("/CatalogoCarta")
public class CatalogoCartaController {
    private static final Logger logger = LoggerFactory.getLogger(CartasController.class);
    @Resource(name = "scanCartaService")
    private ScanCartaService scanCartaService;


    @RequestMapping(value = "/CrearNuevaCarta", method = RequestMethod.GET)
    public ModelAndView CrearNuevaCarta(Model model)throws Exception{
        ModelAndView modelAndView = new ModelAndView();
        List<Carta> cartas = scanCartaService.getScanCartas();
        model.addAttribute("cartas", cartas);
        modelAndView.setViewName("/CatalogoScanCarta/Carta");
        return modelAndView;
    }


    @RequestMapping(value = "/GetCarta", method = RequestMethod.GET, produces="application/json" )
    public @ResponseBody
    String GetCarta(@RequestParam(value = "idcarta") Integer idcarta)  throws ParseException {
        Map<String, String> map = new HashMap<String, String>();
        Map<String, Object> model = new HashMap<String, Object>();
        Carta objCarta = scanCartaService.getCartaById(idcarta);
        map.put("idcarta", objCarta.getIdcarta().toString());
        map.put("carta", objCarta.getCarta());
        String jsonResponse;
        jsonResponse = new Gson().toJson(map);
        UnicodeEscaper escaper = UnicodeEscaper.above(127);
        return escaper.translate(jsonResponse);
    }

    @RequestMapping(value="DelCarta", method=RequestMethod.POST)
    public ResponseEntity<String>DelCarta(@RequestParam( value="idcarta", required=true ) Integer idcarta){
        try{
            scanCartaService.DeleteCarta(idcarta);
            Map<String, String> map = new HashMap<String, String>();
            map.put("msj", "Eliminado correctamente!");
            return createJsonResponse(map);
        }catch (Exception ex){
            Gson gson = new Gson();
            String json = gson.toJson(ex.toString());
            return  new ResponseEntity<String>( json, HttpStatus.OK);
        }
    }
    @RequestMapping(value="SaveCarta", method=RequestMethod.POST)
    public ResponseEntity<String>SaveCarta(@RequestParam( value="idcarta", required=false ) Integer idcarta
            ,@RequestParam( value="NameCarta", required=true ) String NameCarta) {
        try{
            Carta obj = new Carta();
            if (idcarta == null){
                if (!scanCartaService.CheckequalsCarta(NameCarta)){
                    obj.setDeviceid("server");
                    obj.setEstado('0');
                    obj.setPasive('1');
                    obj.setRecordDate(new Date());
                    obj.setRecordUser(SecurityContextHolder.getContext().getAuthentication().getName());
                    obj.setCarta(NameCarta);
                    obj.setActivo("true");
                    scanCartaService.saveCarta(obj);
                }else {
                    Map<String, String> map = new HashMap<String, String>();
                    map.put("msj", "Nombre ya existe!" );
                    return createJsonResponse(map);
                }
            }else{
                obj.setDeviceid("server");
                obj.setEstado('0');
                obj.setPasive('1');
                obj.setRecordDate(new Date());
                obj.setRecordUser(SecurityContextHolder.getContext().getAuthentication().getName());
                obj.setIdcarta(idcarta);
                obj.setCarta(NameCarta);
                obj.setActivo("true");
                scanCartaService.saveCarta(obj);
            }
            return createJsonResponse(obj) ;
        }catch (Exception e){
            Gson gson = new Gson();
            String json = gson.toJson(e.toString());
            return  new ResponseEntity<String>( json, HttpStatus.CREATED);
        }
    }



    /*  Esta Funcion retorna un Json  */
    private ResponseEntity<String> createJsonResponse( Object o )
    {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");
        Gson gson = new Gson();
        String json = gson.toJson(o);
        UnicodeEscaper escaper = UnicodeEscaper.above(127);
        json = escaper.translate(json);
        return new ResponseEntity<String>( json, headers, HttpStatus.CREATED );
    }
}
