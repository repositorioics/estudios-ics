package ni.org.ics.estudios.web.controller.Cartas;

import com.google.gson.Gson;
import ni.org.ics.estudios.domain.catalogs.*;
import ni.org.ics.estudios.domain.scancarta.Extensiones;
import ni.org.ics.estudios.dto.ParteDto;
import ni.org.ics.estudios.dto.VersionExtensionDto;
import ni.org.ics.estudios.dto.VersionParteDto;
import ni.org.ics.estudios.language.MessageResource;
import ni.org.ics.estudios.service.MessageResourceService;
import ni.org.ics.estudios.service.scancarta.ScanCartaService;
import ni.org.ics.estudios.users.model.UserSistema;
import ni.org.ics.estudios.web.utils.DateUtil;
import ni.org.ics.estudios.web.utils.JsonUtil;
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
import org.springframework.web.servlet.support.RequestContextUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;
import java.text.ParseException;
import java.util.*;

/**
 * Created by ICS on 29/04/2020.
 */

@Controller
@RequestMapping("/CatalogoVersion")
public class CatalogoVersionController {

    private static final Logger logger = LoggerFactory.getLogger(CartasController.class);
    @Resource(name = "scanCartaService")
    private ScanCartaService scanCartaService;
    @Resource(name = "messageResourceService")
    private MessageResourceService messageResourceService;

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

    /* Crear una nueva version CatalogoVersion/CrearNuevaVersion */
    @RequestMapping(value = "/CrearNuevaVersion", method = RequestMethod.GET)
    public String CrearNuevaCarta(Model model)throws Exception{
        List<Estudio> cartas = scanCartaService.getAllEstudios();
        model.addAttribute("cartas", cartas);
        List<Version> version = scanCartaService.getScanVersion();
        model.addAttribute("version",version);
        model.addAttribute("caso",new Version());
        model.addAttribute("agregando",true);
        model.addAttribute("editando",false);
        return "/CatalogoScanCarta/Version";
    }

    //Obtener por Id Version para Editar
    @RequestMapping(value = "/editVersion/{idversion}", method = RequestMethod.GET)
    public String editSero(Model model, @PathVariable("idversion") String idversion)throws Exception {
        try{
            int cod = Integer.parseInt(idversion);
            Version caso = this.scanCartaService.getVersionById(cod);
            model.addAttribute("caso", caso);
            List<Estudio> cartas = scanCartaService.getAllEstudios();
            model.addAttribute("cartas", cartas);
            List<Version> version = scanCartaService.getScanVersion();
            model.addAttribute("version",version);
            model.addAttribute("agregando",false);
            model.addAttribute("editando",true);
            return "/CatalogoScanCarta/Version";
        }
        catch (Exception e){
            System.err.println(e.getMessage());
            throw e;
        }
    }



    @RequestMapping(value = "/GetVersion", method = RequestMethod.GET, produces="application/json" )
    public @ResponseBody
    String GetCarta(@RequestParam(value = "idversion") Integer idversion)  throws ParseException {
        Map<String, String> map = new HashMap<String, String>();
        if (idversion != null){
            Version objVersion = scanCartaService.getVersionById(idversion);
            map.put("idversion", objVersion.getIdversion().toString());
            map.put("idcarta", objVersion.getEstudio().getCodigo().toString());
            map.put("nombreVersion",objVersion.getVersion());
            //map.put("activo",objVersion.isActivo());
            String jsonResponse;
            jsonResponse = new Gson().toJson(map);
            UnicodeEscaper escaper = UnicodeEscaper.above(127);
            return escaper.translate(jsonResponse);
        }
        else {
            return "404";
        }
    }
    @RequestMapping(value="/carta", method = RequestMethod.GET)
    public String getSuccess() {
        return "CatalogoScanCarta/Version";
    }

    public static String theMonth(int month){
        String[] monthNames = {"Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"};
        return monthNames[month];
    }

    @RequestMapping( value="saveVersion", method=RequestMethod.POST)
    public ResponseEntity<String> saveVersion(@RequestParam(value = "idversion", required = false) String idversion
            , @RequestParam(value = "idcarta", required = true) Integer idcarta
            , @RequestParam(value = "version", required = true) String version
            , @RequestParam(value = "activo", required = false) String activo
            , @RequestParam(value = "editando", required = true) String editando
            , @RequestParam(value = "fecha_version", required = true) String fecha_version
            , @RequestParam(value = "fecha_format", required = true) String fecha_format
    )throws Exception{
        try {

            Version v = new Version();
            Estudio c = new Estudio();
            String[] words = fecha_version.split("-");
            int mes =  Integer.parseInt(words[0]) -1;
            String monthName = theMonth(mes);
            int yy = Integer.parseInt( words[1]);

            if (editando.equals("true")){
                System.out.println("editando: "+editando);
                Integer id = Integer.parseInt(idversion);
                v.setIdversion(id);
                c.setCodigo(idcarta);
                v.setEstudio(c);
                v.setVersion(version);
                boolean ac = false;
                if (activo == null || activo.equals(null) || activo.equals("off")) {
                    ac = false;
                } else {
                    ac = true;
                }
                v.setActivo(ac);
                v.setFecha_version(fecha_version);
                v.setFecha_format(monthName + ", " + yy);
                String nameComputer = InetAddress.getLocalHost().getHostName();
                v.setDeviceid(nameComputer);
                v.setEstado('0');
                v.setPasive('1');
                v.setRecordDate(new Date());
                v.setRecordUser(SecurityContextHolder.getContext().getAuthentication().getName());
                scanCartaService.saveScanVersion(v);
                return createJsonResponse(v);
            }else {
                if (!scanCartaService.checkExistVersion(version, idcarta)) {
                    c.setCodigo(idcarta);
                    v.setEstudio(c);
                    v.setVersion(version);
                    boolean ac = false;
                    if (activo == null || activo.equals(null) || activo.equals("off")) {
                        ac = false;
                    } else {
                        ac = true;
                    }
                    v.setActivo(ac);
                    v.setFecha_version(fecha_version);
                    v.setFecha_format(monthName + ", " + yy);
                    String nameComputer = InetAddress.getLocalHost().getHostName();
                    v.setDeviceid(nameComputer);
                    v.setEstado('0');
                    v.setPasive('1');
                    v.setRecordDate(new Date());
                    v.setRecordUser(SecurityContextHolder.getContext().getAuthentication().getName());
                    scanCartaService.saveScanVersion(v);
                    return createJsonResponse(v);
                } else {
                    Map<String, String> map = new HashMap<String, String>();
                    map.put("msj", "Version ya existe!");
                    return createJsonResponse(map);
                }
            }// fin Else
        }
        catch (Exception ex){
            Gson gson = new Gson();
            String json = gson.toJson(ex.toString());
            return new ResponseEntity<String>( json, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
/*
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
    }*/

    @RequestMapping(value = "UpdateVersion", method = RequestMethod.POST)
    public ResponseEntity<String> UpdateVersion(@RequestParam( value="idversion", required=true ) String idversion
            ,@RequestParam( value="idcarta", required=true ) Integer idcarta
    ,@RequestParam( value="version", required=true ) String version
    ,@RequestParam( value="fecha_version", required=true ) String fecha_version
    ,@RequestParam( value="activo", required=false ) String activo){
        try{
            Version v = new Version();
            String[] words = fecha_version.split("-");
            int mes =  Integer.parseInt(words[0]) -1;
            String monthName = theMonth(mes);
            int yy = Integer.parseInt( words[1]);
            Integer id = Integer.parseInt(idversion);
            v.setIdversion(id);
            Estudio e = new Estudio();
            e.setCodigo(idcarta);
            v.setEstudio(e);
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
            //v.setActivo(ac);
            //v.setFecha_version(DateUtil.StringToDate(fecha_version,"dd//MM/yyyy"));
            v.setFecha_format(monthName + ", " + yy);
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
            return  new ResponseEntity<String>( json, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }



    @RequestMapping(value = "/DelVersion/{accion}/{idversion}", method= RequestMethod.GET)
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

    // CatalogoVersion/delete/1
    @RequestMapping(value = "delete", method= RequestMethod.POST)
    public ResponseEntity<String> delete(@RequestParam("idversion") Integer idversion)throws Exception{
        try{
            Version version = this.scanCartaService.getVersionById(idversion);
            if (version!=null) {
                this.scanCartaService.DeshabilitarVersion(idversion);
            }
            return JsonUtil.createJsonResponse(version);
        }catch (Exception e){
            System.out.println(e.getMessage());
            Gson gson = new Gson();
            String json = gson.toJson(e.toString());
            return new ResponseEntity<String>( json, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "activar", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<String> activar(@RequestParam("idversion") Integer idversion)throws Exception{
        try{
            Version version = this.scanCartaService.getVersionById(idversion);
            if (version!=null){
                this.scanCartaService.HabilitarVersion(idversion);
            }
            return JsonUtil.createJsonResponse(version);
        }catch (Exception e){
            System.out.println(e.getMessage());
            Gson gson = new Gson();
            String json = gson.toJson(e.toString());
            return new ResponseEntity<String>( json, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    //CatalogoVersion/saveParte
    @RequestMapping( value="saveParte", method=RequestMethod.POST)
    public ResponseEntity<String> saveVersion(@RequestParam( value="version2", required=true ) Integer version2
            , @RequestParam( value = "parte",     required = true ) String parte
            , @RequestParam( value = "fecha",    required = false, defaultValue = "" ) String fecha
    )throws Exception{
        try {
            Version v = new Version();
            Parte p = new Parte();
                if (!scanCartaService.CheckequalsParte(parte, version2)) {
                    p.setParte(parte);
                    p.setActivo(true);
                    v.setIdversion(version2);
                    p.setVersion(v);
                    p.setAcepta("false");
                    String nameComputer = InetAddress.getLocalHost().getHostName().toUpperCase();
                    p.setDeviceid(nameComputer);
                    p.setEstado('0');
                    p.setPasive('1');
                    p.setRecordDate(new Date());
                    p.setRecordUser(SecurityContextHolder.getContext().getAuthentication().getName());
                    scanCartaService.SaveParte(p);
                    return createJsonResponse(p);
                } else {
                    Map<String, String> map = new HashMap<String, String>();
                    map.put("msj", "Parte ya existe!");
                    return createJsonResponse(map);
                }
        }
        catch (Exception ex){
            Gson gson = new Gson();
            String json = gson.toJson(ex.toString());
            return new ResponseEntity<String>( json, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    //CatalogoVersion/saveExt
    @RequestMapping( value="saveExt", method=RequestMethod.POST)
    public ResponseEntity<String> saveExt(@RequestParam( value = "version3",  required=false, defaultValue = "") Integer version3
                                         ,@RequestParam( value = "extension", required = true ) String extension
                                         ,@RequestParam( value = "fechaExt",  required = true ) String fechaExt
    )throws Exception{
        try {
            Extensiones ext = new Extensiones();
            Version v = new Version();
                if (!scanCartaService.CheckequalsExtension(extension,version3)) {
                    ext.setExtension(extension);
                    v.setIdversion(version3);
                    ext.setVersion(v);
                    ext.setActive(true);
                    ext.setFecha_extension(fechaExt);
                    this.scanCartaService.saveORupdateExtension(ext);
                    return JsonUtil.createJsonResponse(ext);
                } else {
                    Map<String, String> map = new HashMap<String, String>();
                    map.put("msj", "Extensión ya existe!");
                    return createJsonResponse(map);
                }
        }
        catch (Exception ex){
            Gson gson = new Gson();
            String json = gson.toJson(ex.toString());
            return new ResponseEntity<String>( json, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // ** EXTENSION DE LA CARTA **

    @RequestMapping(value = "/extension", method = RequestMethod.GET)
    public String getAllExtensiones(Model model)throws Exception{
        try{
            List<Extensiones> extension = this.scanCartaService.getAllExtension();
            model.addAttribute("extension",extension);
            model.addAttribute("caso",new Extensiones());
            List<Estudio> cartas = scanCartaService.getAllEstudios();
            model.addAttribute("cartas", cartas);
            List<Version> version = scanCartaService.getScanVersion();
            model.addAttribute("version",version);
            model.addAttribute("editando", false);
            return "CatalogoScanCarta/Extension";
        }catch (Exception e){
            return "404";
        }
    }

    //Obtener por Id Extension para Editar bbb
    @RequestMapping(value = "/editExtension/{idextension}", method = RequestMethod.GET)
    public String editExtension(Model model, @PathVariable("idextension") String idextension)throws Exception {
        try{
            int cod = Integer.parseInt(idextension);
            Extensiones caso = this.scanCartaService.getExtensionById(cod);
            model.addAttribute("caso", caso);
            List<Estudio> cartas = scanCartaService.getAllEstudios();
            model.addAttribute("cartas", cartas);
            List<Version> version = scanCartaService.getVersionByIdestudio(caso.getVersion().getEstudio().getCodigo());
            model.addAttribute("version",version);
            List<Extensiones> extension = this.scanCartaService.getAllExtension();
            model.addAttribute("extension",extension);
            model.addAttribute("editando",true);
            return "CatalogoScanCarta/Extension";
        }
        catch (Exception e){
            System.err.println(e.getMessage());
            throw e;
        }
    }

    // CatalogoVersion/saveExtension
    @RequestMapping(value = "saveExtension", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<String>saveOrupdate(@RequestParam("idextension")Integer idextension
                                             ,@RequestParam("extension")String extension
                                             ,@RequestParam("idversion")Integer idversion
                                             ,@RequestParam("fecha")String fecha
                                             ,@RequestParam("editando")String editando)throws Exception{
        try{
            Version v = new Version();
            String nameComputer = InetAddress.getLocalHost().getHostName();
            Extensiones ext = new Extensiones();
            if (editando.equals("true")){
                ext.setDeviceid(nameComputer);
                ext.setEstado('1');
                ext.setPasive('0');
                ext.setRecordDate(new Date());
                ext.setRecordUser(SecurityContextHolder.getContext().getAuthentication().getName());
                ext.setId(idextension);
                ext.setExtension(extension);
                v.setIdversion(idversion);
                ext.setVersion(v);
                ext.setActive(true);
                ext.setFecha_extension(fecha);
                this.scanCartaService.saveORupdateExtension(ext);
            }else {
                if (!this.scanCartaService.CheckequalsExtension(extension,idextension)) {
                    ext.setDeviceid(nameComputer);
                    ext.setEstado('1');
                    ext.setPasive('0');
                    ext.setRecordDate(new Date());
                    ext.setRecordUser(SecurityContextHolder.getContext().getAuthentication().getName());
                    ext.setExtension(extension);
                    v.setIdversion(idversion);
                    ext.setVersion(v);
                    ext.setActive(true);
                    ext.setFecha_extension(fecha);
                    this.scanCartaService.saveORupdateExtension(ext);
                }else{
                    Map<String, String> map = new HashMap<String, String>();
                    map.put("msj", "Parte ya existe!");
                    return createJsonResponse(map);
                }
            }
            return JsonUtil.createJsonResponse(ext);
        }catch (Exception e){
            System.out.println(e.getMessage());
            Gson gson = new Gson();
            String json = gson.toJson(e.toString());
            return new ResponseEntity<String>( json, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    // CatalogoParte/GetVersion
    @RequestMapping(value = "/obtenerVersion", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    String GetVersion(@RequestParam(value = "idcarta", required = true) Integer idcarta, Model model)
            throws ParseException {
        List<Version> version = null;
        try{
            version =  scanCartaService.getVersioCarta(idcarta);
            model.addAttribute("version",version);
            String jsonResponse;
            jsonResponse = new Gson().toJson(model);
            UnicodeEscaper escaper = UnicodeEscaper.above(127);
            return escaper.translate(jsonResponse);
        }catch (Exception e){
            return "Not Found!";
        }
    }



    // ** FIN EXTENSION DE LA CARTA **





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
