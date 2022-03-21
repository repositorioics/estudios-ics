package ni.org.ics.estudios.web.controller.Cartas;

import com.google.gson.Gson;
import ni.org.ics.estudios.domain.catalogs.Estudio;
import ni.org.ics.estudios.domain.catalogs.Parte;
import ni.org.ics.estudios.domain.catalogs.Version;
import ni.org.ics.estudios.service.scancarta.ScanCartaService;
import ni.org.ics.estudios.web.utils.JsonUtil;
import org.apache.commons.lang3.text.translate.UnicodeEscaper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.Resource;
import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by ICS on 10/05/2020.
 */

@Controller
@RequestMapping("/CatalogoParte")
public class ParteController {

    private static final Logger logger = LoggerFactory.getLogger(CartasController.class);
    @Resource(name = "scanCartaService")
    private ScanCartaService scanCartaService;

    @RequestMapping(value = "/ListadoParte", method = RequestMethod.GET)
    public String ListadoVersion(Model model)throws Exception{
        List<Parte> parte = scanCartaService.getListParte();
        if (parte != null){
            model.addAttribute("parte", parte);
            return "CatalogoScanCarta/ParticipanteCartaTmp";
        }else {
            return "404";
        }
    }

    /* Crear una nueva Parte /CatalogoParte/CrearNuevaParte */
    @RequestMapping(value = "/CrearNuevaParte", method = RequestMethod.GET)
    public String CrearNuevaParte(Model model)throws Exception{
        try {
            List<Estudio> estudios = scanCartaService.getAllEstudios();
            model.addAttribute("estudios", estudios);
            List<Parte> parte = scanCartaService.getListParte();
            model.addAttribute("parte", parte);
            List<Version> version = scanCartaService.getVersionActiva();
            model.addAttribute("version", version);
            model.addAttribute("caso", new Parte());
            model.addAttribute("agregando", true);
            model.addAttribute("editando", false);
            return "/CatalogoScanCarta/Parte";
        }catch (Exception e){
                System.err.println(e.getMessage());
                return "404";
        }
    }

    //Obtener por Id Parte para Editar CatalogoParte/CrearNuevaParte
    @RequestMapping(value = "/editParte/{idparte}", method = RequestMethod.GET)
    public String editParte(Model model, @PathVariable("idparte") String idparte)throws Exception {
        try{
            int cod = Integer.parseInt(idparte);
            Parte caso = this.scanCartaService.getParteById(cod);
            model.addAttribute("caso", caso);
            List<Estudio> estudios = scanCartaService.getAllEstudios();
            model.addAttribute("estudios", estudios);
            List<Version> version = scanCartaService.getVersionByIdestudio(caso.getVersion().getEstudio().getCodigo());
            model.addAttribute("version",version);
            List<Parte> parte = scanCartaService.getListParte();
            model.addAttribute("parte", parte);
            model.addAttribute("agregando",false);
            model.addAttribute("editando",true);
            return"/CatalogoScanCarta/Parte";
        }
        catch (Exception e){
            System.err.println(e.getMessage());
          return "404";
        }
    }

    public boolean siTienePartePrincipal(List<Parte>list){
        boolean encontrado = false;
        for (Parte x :list)
            if (x.isPrincipal() == true) {
                encontrado = true;
                break;
            }
        return encontrado;
    }

    //CatalogoParte/saveParte
    @RequestMapping( value="saveParte", method=RequestMethod.POST)
    public ResponseEntity<String> saveVersion(@RequestParam( value="idversion", required=true ) Integer idversion
            , @RequestParam( value = "parte"      ,  required = true ) String parte
            , @RequestParam( value = "activo"     ,  required = false, defaultValue = "" ) String activo
            , @RequestParam( value = "editando"   ,  required = true ) String editando
            , @RequestParam( value = "idparte"    ,  required = true ) Integer idparte
            , @RequestParam( value = "principal"  ,  required = false, defaultValue = "") String principal
    )throws Exception{
        try {
            List<Parte> partesByIdVersion = scanCartaService.listadoPartesPrincipales(idversion);
            Version v = new Version();
            Parte p = new Parte();
            if (editando.equals("true")){//actualizar
                p.setIdparte(idparte);
                p.setParte(parte);
                boolean ac = false;
                if (activo == null || activo.equals(null) || activo.equals("off") || activo.equals("")) {
                    ac = false;
                } else {
                    ac = true;
                }
                p.setActivo(ac);
                boolean princi = false;
                if( principal.equals("") || principal.equals("off") || principal.equals(null) ){
                    princi = false;
                } else{
                    princi = true;
                }
                p.setPrincipal(princi);
                v.setIdversion(idversion);
                p.setVersion(v);
                p.setAcepta("false");
                p.setDeviceid("NicaUmich2");
                p.setEstado('0');
                p.setPasive('1');
                p.setRecordDate(new Date());
                p.setRecordUser(SecurityContextHolder.getContext().getAuthentication().getName());
                scanCartaService.SaveParte(p);
                return createJsonResponse(p);
            }else { // guarda nuevo
                if (!scanCartaService.CheckequalsParte(parte, idversion)) {
                    Version vers = this.scanCartaService.getVersionById(idversion);
                    if(vers.getEstudio().getCodigo()!=6) {
                        if (siTienePartePrincipal(partesByIdVersion) && principal.equals("on")) {
                            Map<String, String> map = new HashMap<String, String>();
                            map.put("msj", "Ya existe una parte principal!");
                            return createJsonResponse(map);
                        }
                    }
                    p.setParte(parte);
                    boolean ac = false;
                    if (activo == null || activo.equals(null) || activo.equals("off") || activo.equals("")) {
                        ac = false;
                    } else {
                        ac = true;
                    }
                    p.setActivo(ac);
                    boolean princi = false;
                    if( principal.equals("") || principal.equals("off") || principal.equals(null) ){
                        princi = false;
                    } else{
                        princi = true;
                    }
                    p.setPrincipal(princi);
                    v.setIdversion(idversion);
                    p.setVersion(v);
                    p.setAcepta("false");
                    p.setDeviceid("NicaUmich2");
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
        }
        catch (Exception ex){
            Gson gson = new Gson();
            String json = gson.toJson(ex.toString());
            return new ResponseEntity<String>( json, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }



    @RequestMapping(value = "UpdateParte", method = RequestMethod.POST)
    public ResponseEntity<String> UpdateParte(@RequestParam( value="idparte", required=true ) Integer idparte
            ,@RequestParam( value="idversion", required=true ) Integer idversion
            ,@RequestParam( value="parte", required=true ) String parte
            ,@RequestParam( value="fecha_parte", required=false ) String fecha_parte
            ,@RequestParam( value="principal", required=false ) String principal
            ,@RequestParam( value="activo", required=false ) String activo){
        try{
            Parte p = new Parte();
            p.setIdparte(idparte);
            Version v = new Version();
            v.setIdversion(idversion);
            p.setVersion(v);
            p.setParte(parte);
            boolean ac = false;
            if (activo == null || activo.equals(null) || activo.equals("off") || activo.equals("")){
                ac = false;
            } else {
                ac = true;
            }
            p.setActivo(ac);
            boolean princi = false;
            if( principal.equals("") || principal.equals("off") || principal.equals(null) ){
                princi = false;
            } else{
                princi = true;
            }
            p.setPrincipal(princi);
            p.setAcepta("false");
            p.setDeviceid("NicaUmich2");
            p.setEstado('0');
            p.setPasive('1');
            p.setRecordDate(new Date());
            p.setRecordUser(SecurityContextHolder.getContext().getAuthentication().getName());
            scanCartaService.SaveParte(p);
            return createJsonResponse(v);
        }
        catch (Exception e){
            Gson gson = new Gson();
            String json = gson.toJson(e.toString());
            return  new ResponseEntity<String>( json, HttpStatus.CREATED);
        }
    }

    // CatalogoParte/GetVersion
    @RequestMapping(value = "/GetVersion", method = RequestMethod.GET, produces = "application/json")
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
    @RequestMapping(value = "/HabYDesParte/{accion}/{idparte}", method= RequestMethod.GET)
    public String HabYDesParte(@PathVariable("idparte") String idparte,
                               @PathVariable("accion") String accion, RedirectAttributes redirectAttributes) throws Exception{
        String redirecTo = "404";
        try{
            Integer id = Integer.parseInt(idparte);
            if (accion.matches("bloq")){
                redirecTo = "redirect:/CatalogoParte/ListadoParte";
                scanCartaService.DesHabilitarParte(id);
                redirectAttributes.addAttribute("usuarioDeshabilitado", true);
                redirectAttributes.addFlashAttribute("nombreUsuario", idparte);
            }
            else if (accion.matches("Unbloq")){
                redirecTo = "redirect:/CatalogoParte/ListadoParte";
                scanCartaService.HabilitarParte(id);
                redirectAttributes.addAttribute("usuarioHabilitado", true);
                redirectAttributes.addFlashAttribute("nombreUsuario", idparte);
            }else {
                redirecTo = "redirect:/CatalogoParte/ListadoParte";
            }
        }catch (Exception ex){
            return redirecTo;
        }
        return redirecTo;
    }


    // CatalogoParte/delete/1
    @RequestMapping(value = "delete", method= RequestMethod.POST)
    public ResponseEntity<String> delete(@RequestParam("idparte") Integer idparte)throws Exception{
        try{
            Parte parte = this.scanCartaService.getParteById(idparte);
            if (parte!=null) {
                this.scanCartaService.DesHabilitarParte(idparte);
            }
            return JsonUtil.createJsonResponse(parte);
        }catch (Exception e){
            System.out.println(e.getMessage());
            Gson gson = new Gson();
            String json = gson.toJson(e.toString());
            return new ResponseEntity<String>( json, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // CatalogoParte/activar
    @RequestMapping(value = "activar", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<String> activar(@RequestParam("idparte") Integer idparte)throws Exception{
        try{
            Parte parte = this.scanCartaService.getParteById(idparte);
            if (parte!=null){
                this.scanCartaService.HabilitarParte(idparte);
            }
            return JsonUtil.createJsonResponse(parte);
        }catch (Exception e){
            System.out.println(e.getMessage());
            Gson gson = new Gson();
            String json = gson.toJson(e.toString());
            return new ResponseEntity<String>( json, HttpStatus.INTERNAL_SERVER_ERROR);
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
