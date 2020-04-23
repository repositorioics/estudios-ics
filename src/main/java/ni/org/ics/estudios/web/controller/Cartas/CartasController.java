package ni.org.ics.estudios.web.controller.Cartas;

import com.google.gson.Gson;
import ni.org.ics.estudios.domain.catalogs.*;
import ni.org.ics.estudios.domain.hemodinamica.DatosHemodinamica;
import ni.org.ics.estudios.domain.muestreoanual.ParticipanteProcesos;
import ni.org.ics.estudios.domain.scancarta.DetalleParte;
import ni.org.ics.estudios.domain.scancarta.ParticipanteCarta;
import ni.org.ics.estudios.dto.CoordenadasParticipanteDto;
import ni.org.ics.estudios.dto.ParteDto;
import ni.org.ics.estudios.dto.ParticipanteCartaDto;
import ni.org.ics.estudios.dto.ParticipantesCodigo;
import ni.org.ics.estudios.language.MessageResource;
import ni.org.ics.estudios.service.Domicilios.DomicilioService;
import ni.org.ics.estudios.service.MessageResourceService;
import ni.org.ics.estudios.service.hemodinanicaService.DatoshemodinamicaService;
import ni.org.ics.estudios.service.muestreoanual.ParticipanteProcesosService;
import ni.org.ics.estudios.service.scancarta.ScanCartaService;
import ni.org.ics.estudios.web.utils.DateUtil;
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
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import ni.org.ics.estudios.domain.Participante;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by ICS on 09/01/2020.
 */
@Controller
@RequestMapping("/cartas")
public class CartasController {
    private static final Logger logger = LoggerFactory.getLogger(CartasController.class);
    @Resource(name = "scanCartaService")
    private ScanCartaService scanCartaService;
    /* Instancia de mi Servicio Hemodinamico */
    @Resource(name = "datoshemodinamicaService")
    private DatoshemodinamicaService datoshemodinamicaService;
    @Resource(name = "participanteProcesosService")
    private ParticipanteProcesosService participanteProcesosService;
    /* Instancia de mi Servicio Hemodinamico */
    @Resource(name = "DomicilioService")
    private ni.org.ics.estudios.service.Domicilios.DomicilioService DomicilioService;
    /* Instancia de mi Servicio Hemodinamico */
    @Resource(name = "messageResourceService")
    private MessageResourceService messageResourceService;


    @RequestMapping(value="/Crear", method = RequestMethod.GET)
     public String Crear(ModelMap model)  throws Exception  {
        try{
            List<Carta> carta = scanCartaService.getScanCartas();
            model.addAttribute("carta",carta);
            List<MessageResource> relFam = messageResourceService.getCatalogo("CP_CAT_RFTUTOR");
            model.addAttribute("relFam", relFam);
            List<Personal> person = scanCartaService.getPersonal();
            model.addAttribute("person",person);
            List<MessageResource> proyecto = messageResourceService.getCatalogo("PROYECTO");
            model.addAttribute("proyecto", proyecto);
            List<MessageResource> SiNoNA = messageResourceService.getCatalogo("SCANCARTA");
            model.addAttribute("SiNoNA", SiNoNA);
            List<MessageResource> tpoasent = messageResourceService.getCatalogo("TIPOASENTIMIENTO");
            model.addAttribute("tpoasent",tpoasent);
            List<Parte> partlist = scanCartaService.getParteList();
            model.addAttribute("partlist",partlist);
            return "/Cartas/CrearCarta";
        }catch (Exception e){
            throw e;
        }
    }

/*
    @RequestMapping(value="/Crear", method = RequestMethod.GET, produces="application/json" )
    public ModelAndView Crear(ModelMap model)  throws Exception  {
        ModelAndView modeliew = new ModelAndView();
        try{
            List<Carta> carta = scanCartaService.getScanCartas();
            model.addAttribute("carta",carta);
            List<Version> version = scanCartaService.getVersioCarta(1);
            model.addAttribute("version", version);
            modeliew.setViewName("/Cartas/CrearCarta");
            return modeliew;
        }catch (Exception e){
            Gson gson = new Gson();
            String json = gson.toJson(e.toString());
            return (modeliew);
        }
    }


            List<Version> version = scanCartaService.getVersioCarta(2);
            model.addAttribute("version", version);
*/
    @RequestMapping(value = "/VersionCarta", method = RequestMethod.GET, produces="application/json" )
    public @ResponseBody
    String GetVersionCarta(@RequestParam(value = "idcarta") Integer idcarta,Model model)  throws Exception {
        List<Version> objV = scanCartaService.getVersioCarta(idcarta);
        model.addAttribute("objV", objV);
        String jsonResponse;
        jsonResponse = new Gson().toJson(model);
        UnicodeEscaper escaper = UnicodeEscaper.above(127);
        return escaper.translate(jsonResponse);
    }

    @RequestMapping(value = "/ParteVersion", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    String GetParteCarta(@RequestParam(value = "idversion")Integer idversion, Model model) throws Exception{
        String result ="No Found";
        List<Parte> parte = scanCartaService.getParte(idversion);
       /* if (parte.size() <= 0 ){
            model.addAttribute("result",result);
        }*/
        model.addAttribute("parte",parte);
        String jsonResponse;
        jsonResponse = new Gson().toJson(model);
        UnicodeEscaper escaper = UnicodeEscaper.above(127);
        return escaper.translate(jsonResponse);
    }


    @RequestMapping(value = "/searchParticipant", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    String BuscarParticipanteByID(@RequestParam(value="parametro", required=true ) Integer parametro)
            throws Exception {
        try {
            Map<String, String> map = new HashMap<String, String>();
            Participante participante = datoshemodinamicaService.getParticipante(parametro);
            if (participante != null){
                ParticipanteProcesos procesos = participanteProcesosService.getParticipante(parametro);
                map.put("estado",procesos.getEstPart().toString());
                ParticipantesCodigo participantesCodigo = DomicilioService.getCodigos(parametro);
                if(participantesCodigo.getCasaFamilia() != null){
                    map.put("CFamilia", participantesCodigo.getCasaFamilia());
                }else {
                    map.put("CFamilia", "");
                }
                map.put("estudios", participantesCodigo.getEstudio());
                map.put("tutor", procesos.getTutor());
                map.put("relacionFam",procesos.getRelacionFam().toString());
            }
            map.put("codigo", participante.getCodigo().toString());
            map.put("nombre", participante.getNombreCompleto());
            map.put("fecha", DateUtil.DateToString(participante.getFechaNac(), "dd/MM/yyyy"));
            map.put("edad", participante.getEdad());
            map.put("direccion", participante.getCasa().getDireccion());
            map.put("madre", participante.getNombre1Madre()  +" "+ participante.getNombre2Madre() +" "+ participante.getApellido1Madre() +" "+ participante.getApellido2Madre());
            map.put("padre", participante.getNombre1Padre()  +" "+ participante.getNombre2Padre() +" "+ participante.getApellido1Padre() +" "+ participante.getApellido2Padre());
            String jsonResponse;
            jsonResponse = new Gson().toJson(map);
            UnicodeEscaper escaper = UnicodeEscaper.above(127);
            return escaper.translate(jsonResponse);
        }catch (Exception e){
            throw e;
        }
    }

    @RequestMapping(value = "/VerParteCarta", method = RequestMethod.GET, produces="application/json" )
    public @ResponseBody
    List<Parte> VerPartes (@RequestParam(value = "idparticipantecarta") Integer idparticipantecarta)throws ParseException{
        List<Parte> p = null;
        try {
            p = scanCartaService.getParteParticipante(idparticipantecarta);
            return p;
        }
        catch (Exception ex){
            return p;
        }
    }
    //Metodo para guardar la Version/Participante
    @RequestMapping( value="/saveScanCarta", method=RequestMethod.POST, produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody
    ResponseEntity<String> saveScanCarta(@RequestBody ParticipanteCartaDto obj){
        try{
            if(!scanCartaService.SiExisteParticipanteCarta(obj.getVersion(), obj.getCodigo())){
                // ini
                ParticipanteCarta pc = new ParticipanteCarta();
                if(obj != null) {
                    Participante p = new Participante();
                    p.setCodigo(obj.getCodigo());
                    pc.setParticipante(p);
                    Version v = new Version();
                    v.setIdversion(obj.getVersion());
                    pc.setVersion(v);
                    pc.setAsentimiento(obj.getAsentimiento());
                    pc.setRelfam(obj.getRelfam());
                    pc.setTipoasentimiento(obj.getTipoasentimiento());
                    pc.setQuienfirma(obj.getNombfirma());
                    pc.setNombre2Firma(obj.getNombre2Firma());
                    pc.setApellido1Firma(obj.getApellido1Firma());
                    pc.setApellido2Firma(obj.getApellido2Firma());
                    Personal person = new Personal();
                    person.setCodigo(obj.getPerson());
                    pc.setPersonal(person);
                    pc.setRecurso(obj.getRecurso());
                    pc.setProyecto(obj.getProyecto());
                    Character cf = '0';
                    cf = (obj.getContactoFuturo().equals("1"))?'1':'0';
                    pc.setContactoFuturo(cf);
                    char ret = '0';
                    ret =  (obj.getRetiro().equals("1"))?'1':'0';
                    pc.setRetirado(ret);
                    pc.setObservacion(obj.getObservacion());
                    pc.setFechacarta(DateUtil.StringToDate(obj.getFechacarta(), "dd/MM/yyyy"));
                    pc.setRecordDate(new Date());
                    pc.setRecordUser(SecurityContextHolder.getContext().getAuthentication().getName());
                    pc.setDeviceid("server");
                    pc.setEstado('1');
                    pc.setPasive('0');
                    scanCartaService.saveOrUpdateScanCarta(pc);
                }
                if (obj.getParte() != null){
                    Parte pr = new Parte();
                    for (ParteDto parte : obj.getParte()){
                        DetalleParte dp = new DetalleParte();
                        dp.setParticipantecarta(pc);//
                        dp.setAcepta(parte.isAcepta());
                        pr.setIdparte(parte.getIdparte());
                        dp.setParte(pr);
                        dp.setDeviceid("server");
                        dp.setRecordDate(new Date());
                        dp.setRecordUser(SecurityContextHolder.getContext().getAuthentication().getName());
                        dp.setEstado('1');
                        dp.setPasive('0');
                        scanCartaService.saveParteCarta(dp);
                    }
                }
                return createJsonResponse(pc);
                //fin
            }else{
                Map<String, String> map = new HashMap<String, String>();
                map.put("msj", "Registro ya existe." );
                return createJsonResponse(map);
            }

        }catch(Exception e){
            Gson gson = new Gson();
            String json = gson.toJson(e.toString());
            return new ResponseEntity<String>( json, HttpStatus.CREATED);
        }
    }




    /* Buscar Listado por Codigo Participante */
    @RequestMapping(value = "/ListadoCartaParticipant", method = RequestMethod.GET)
    public ModelAndView ListadoCartaParticipant()throws Exception{
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/Cartas/ListadoCartaParticipante");
        return modelAndView;
    }

    @RequestMapping(value = "/GetCartasParticipante", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody List<ParticipanteCarta> fetchCartaParticipanteToJson(@RequestParam(value = "parametro", required = true)Integer parametro)
            throws ParseException {
        List<ParticipanteCarta> cartaparticipante = null;
        try{
            cartaparticipante =  scanCartaService.getScanCartasByParticipante(parametro);
            return cartaparticipante;
        }catch (Exception e){
            return cartaparticipante = null;
        }
    }

    @RequestMapping(value = "EditCarta/{idCartaParticipante}", method = RequestMethod.GET)
    public String EditCartaParticipante(@PathVariable(value = "idCartaParticipante")Integer idCartaParticipante, Model model){
        List<Carta> carta = scanCartaService.getScanCartas();
        model.addAttribute("carta",carta);
        List<MessageResource> relFam = messageResourceService.getCatalogo("CP_CAT_RFTUTOR");
        model.addAttribute("relFam", relFam);
        List<Personal> person = scanCartaService.getPersonal();
        model.addAttribute("person",person);
        List<MessageResource> scanca = messageResourceService.getCatalogo("SCANCARTA");
        model.addAttribute("scanca", scanca);
        List<MessageResource> proyecto = messageResourceService.getCatalogo("PROYECTO");
        model.addAttribute("proyecto", proyecto);
        List<MessageResource> tpoasent = messageResourceService.getCatalogo("TIPOASENTIMIENTO");
        model.addAttribute("tpoasent",tpoasent);
        ParticipanteCarta obj = null;
        try {
            obj = scanCartaService.getCartasParticipante(idCartaParticipante);
            List<Version> version = scanCartaService.getVersioCarta(obj.getVersion().getCarta().getIdcarta());
            model.addAttribute("version", version);
            Participante participante = datoshemodinamicaService.getParticipante(obj.getParticipante().getCodigo());
            model.addAttribute("participante", participante);
            if (participante != null){
                ParticipanteProcesos procesos = participanteProcesosService.getParticipante(participante.getCodigo());
                model.addAttribute("procesos", procesos);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        List<DetalleParte> dp = scanCartaService.getDetalleParteList(idCartaParticipante);
        model.addAttribute("obj",obj);
        model.addAttribute("dp",dp);
        return "/Cartas/EditCarta";
    }


    /* Actualizar datos datos_coordenadas 25/07/2019 */
    @RequestMapping(value="UpdateRetiro", method= RequestMethod.GET)
    public ResponseEntity<String>UpdateRetiro(@RequestParam(value = "codigo", required = true) Integer codigo)throws Exception{
        try{
            scanCartaService.updateRetiro(codigo);
            return createJsonResponse("Retiro éxitoso!");
        }catch (Exception e){
            Gson gson = new Gson();
            String json = gson.toJson(e.toString());
            return  new ResponseEntity<String>( json, HttpStatus.CREATED);
        }
    }


    //metodo para actulizar partes
    @RequestMapping(value = "UpdateAll", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<String>UpdateAll(@RequestBody ParticipanteCartaDto obj)throws Exception{
        try{
            ParticipanteCarta pc = new ParticipanteCarta();
            if(obj != null) {
                pc.setIdparticipantecarta(obj.getCodigo());
                Participante p = new Participante();
                p.setCodigo(obj.getIdparticipante());
                pc.setParticipante(p);
                Version v = new Version();
                v.setIdversion(obj.getVersion());
                pc.setVersion(v);
                pc.setAsentimiento(obj.getAsentimiento());
                pc.setRelfam(obj.getRelfam());
                pc.setTipoasentimiento(obj.getTipoasentimiento());
                pc.setQuienfirma(obj.getNombfirma());
                pc.setNombre2Firma(obj.getNombre2Firma());
                pc.setApellido1Firma(obj.getApellido1Firma());
                pc.setApellido2Firma(obj.getApellido2Firma());
                Personal person = new Personal();
                person.setCodigo(obj.getPerson());
                pc.setPersonal(person);
                pc.setRecurso(obj.getRecurso());
                pc.setProyecto(obj.getProyecto());
                Character cf = '0';
                cf = (obj.getContactoFuturo().equals("1"))?'1':'0';
                pc.setContactoFuturo(cf);
                char ret = '0';
                ret =  (obj.getRetiro().equals("1"))?'1':'0';
                pc.setRetirado(ret);
                pc.setObservacion(obj.getObservacion());
                pc.setFechacarta(DateUtil.StringToDate(obj.getFechacarta(), "dd/MM/yyyy"));
                pc.setRecordDate(new Date());
                pc.setRecordUser(SecurityContextHolder.getContext().getAuthentication().getName());
                pc.setDeviceid("server");
                pc.setEstado('1');
                pc.setPasive('0');
                scanCartaService.saveOrUpdateScanCarta(pc);
            }

            if (obj.getParte() != null){
                scanCartaService.DeleteParteParticipante(obj.getCodigo()); //Metodo para eliminar el detalle de las partes
                Parte pr = new Parte();
                for (ParteDto parte : obj.getParte()) {
                    DetalleParte dp = new DetalleParte();
                    dp.setParticipantecarta(pc);//
                    dp.setAcepta(parte.isAcepta());
                    pr.setIdparte(parte.getIdparte());
                    dp.setParte(pr);
                    dp.setDeviceid("server");
                    dp.setRecordDate(new Date());
                    dp.setRecordUser(SecurityContextHolder.getContext().getAuthentication().getName());
                    dp.setEstado('1');
                    dp.setPasive('0');
                    scanCartaService.saveParteCarta(dp);
                }
            }
            return createJsonResponse(pc);
        }catch (Exception e){
            Gson gson = new Gson();
            String json = gson.toJson(e.toString());
            return new ResponseEntity<String>(json, HttpStatus.OK);
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
