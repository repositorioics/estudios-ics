package ni.org.ics.estudios.web.controller.Cartas;

import com.google.gson.Gson;
import ni.org.ics.estudios.domain.catalogs.Carta;
import ni.org.ics.estudios.domain.catalogs.Version;
import ni.org.ics.estudios.service.scancarta.ScanCartaService;
import org.apache.commons.lang3.text.translate.UnicodeEscaper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.Resource;
import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by ICS on 29/04/2020.
 */

@Controller
@RequestMapping("/CatalogoVersion")
public class CatalogoVersionController {

    private static final Logger logger = LoggerFactory.getLogger(CartasController.class);
    @Resource(name = "scanCartaService")
    private ScanCartaService scanCartaService;

    /*  Listado Versiones */
    @RequestMapping(value = "/ListadoVersion", method = RequestMethod.GET)
    public String ListadoVersion(Model model)throws Exception{
        List<Version> version = scanCartaService.getScanVersion();
        if (version != null){
            model.addAttribute("version", version);
            return "/CatalogoScanCarta/ListaScanVersion";
        }else {
            return "404";
        }
    }

    /* Crear una nueva version */
    @RequestMapping(value = "/CrearNuevaVersion", method = RequestMethod.GET)
    public String CrearNuevaCarta(Model model)throws Exception{
        List<Carta> cartas = scanCartaService.getCartaActiva();
        model.addAttribute("cartas", cartas);
        return"/CatalogoScanCarta/Version";
    }


    @RequestMapping(value = "/GetVersion", method = RequestMethod.GET, produces="application/json" )
    public @ResponseBody
    String GetCarta(@RequestParam(value = "idversion") Integer idversion)  throws ParseException {
        Map<String, String> map = new HashMap<String, String>();
        if (idversion != null){
            Version objVersion = scanCartaService.getVersionById(idversion);
            map.put("idversion", objVersion.getIdversion().toString());
            map.put("idcarta", objVersion.getCarta().getIdcarta().toString());
            map.put("nombreVersion",objVersion.getVersion());
            map.put("activo",objVersion.getActivo());
            String jsonResponse;
            jsonResponse = new Gson().toJson(map);
            UnicodeEscaper escaper = UnicodeEscaper.above(127);
            return escaper.translate(jsonResponse);
        }
        else {
            return "404";
        }
    }


    @RequestMapping( value="saveVersion", method=RequestMethod.POST)
    public ResponseEntity<String> saveVersion(@RequestParam( value="idcarta", required=true ) Integer idcarta
            , @RequestParam( value="version", required=true ) String version
            , @RequestParam( value="activo", required=true ) String activo
    )throws Exception{
        try {
            Version v = new Version();
            Carta c = new Carta();
            if (!scanCartaService.checkExistVersion(version,idcarta)){
                c.setIdcarta(idcarta);
                v.setCarta(c);
                v.setVersion(version);
                String ac="";
                if (activo.equals("on")){
                    ac ="true";
                }else {
                    ac ="false";
                }
                v.setActivo(ac);
                v.setCod("");
                v.setDeviceid("Server");
                v.setEstado('0');
                v.setPasive('1');
                v.setRecordDate(new Date());
                v.setRecordUser(SecurityContextHolder.getContext().getAuthentication().getName());
                scanCartaService.saveScanVersion(v);
                return createJsonResponse(v);
            }else{
                Map<String, String> map = new HashMap<String, String>();
                map.put("msj", "Version ya existe!" );
                return createJsonResponse(map);
            }
        }
        catch (Exception ex){
            Gson gson = new Gson();
            String json = gson.toJson(ex.toString());
            return new ResponseEntity<String>( json, HttpStatus.CREATED);
        }
    }

    @RequestMapping(value = "/editVersion/{idversion}", method = RequestMethod.GET)
    public ModelAndView editDetalleHemo(@PathVariable(value = "idversion") Integer idversion, Model model)throws ParseException{
        ModelAndView modelView = new ModelAndView();
        try{
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            Version vers = this.scanCartaService.getVersionById(idversion);
            modelView.addObject("vers",vers);
            List<Carta> cartas = scanCartaService.getScanCartas();
            model.addAttribute("cartas",cartas);
            modelView.setViewName("/CatalogoScanCarta/EditVersion");
            return modelView;
        }
        catch (Exception e){
            Gson gson = new Gson();
            String json = gson.toJson(e.toString());
            return (modelView);
        }
    }

    @RequestMapping(value = "UpdateVersion", method = RequestMethod.POST)
    public ResponseEntity<String> UpdateVersion(@RequestParam( value="idversion", required=true ) String idversion
            ,@RequestParam( value="idcarta", required=true ) Integer idcarta
    ,@RequestParam( value="version", required=true ) String version
    ,@RequestParam( value="activo", required=false ) String activo){
        try{
            Version v = new Version();
            Integer id = Integer.parseInt(idversion);
            v.setIdversion(id);
            Carta c = new Carta();
            c.setIdcarta(idcarta);
            v.setCarta(c);
            v.setVersion(version);
            String ac="";
            if (activo == null || activo.equals(null) || activo.equals("off")){
                ac="false";
            }
            else if (activo.equals("on") || activo.equals("true")){
                ac ="true";
            }
            else {
                ac ="false";
            }
            v.setActivo(ac);
            v.setCod("");
            v.setDeviceid("Server");
            v.setEstado('0');
            v.setPasive('1');
            v.setRecordDate(new Date());
            v.setRecordUser(SecurityContextHolder.getContext().getAuthentication().getName());
            scanCartaService.saveScanVersion(v);
            return createJsonResponse(v);
        }
        catch (Exception e){
            Gson gson = new Gson();
            String json = gson.toJson(e.toString());
            return  new ResponseEntity<String>( json, HttpStatus.CREATED);
        }

    }

    @RequestMapping(value = "/DeleteVersion/{accion}/{idversion}", method= RequestMethod.GET)
    public String UpdateActual(@PathVariable("idversion") String idversion,
                                 @PathVariable("accion") String accion, RedirectAttributes redirectAttributes) throws Exception{
        String redirecTo = "404";
        try{
            Integer id = Integer.parseInt(idversion);
            if (accion.matches("bloq")){
                //redirecTo = "redirect:/CatalogoVersion/ListadoVersion";
                redirecTo = "redirect:/CatalogoVersion/ListadoVersion";
                scanCartaService.DeshabilitarVersion(id);
                redirectAttributes.addAttribute("usuarioDeshabilitado", true);
                redirectAttributes.addFlashAttribute("nombreUsuario", idversion);
            }
            else if (accion.matches("Unbloq")){
                redirecTo = "redirect:/CatalogoVersion/ListadoVersion";
                scanCartaService.HabilitarVersion(id);
                redirectAttributes.addAttribute("usuarioHabilitado", true);
                redirectAttributes.addFlashAttribute("nombreUsuario", idversion);
            }else {
                redirecTo = "redirect:/CatalogoVersion/ListadoVersion";
            }
        }catch (Exception ex){
            return redirecTo;
        }
        return redirecTo;
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
