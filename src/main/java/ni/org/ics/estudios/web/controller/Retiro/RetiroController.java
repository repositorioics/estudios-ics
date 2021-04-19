package ni.org.ics.estudios.web.controller.Retiro;

import com.google.gson.Gson;
import com.sun.org.apache.xpath.internal.operations.*;
import ni.org.ics.estudios.domain.Participante;
import ni.org.ics.estudios.domain.Retiros.Retiros;
import ni.org.ics.estudios.domain.catalogs.Personal;
import ni.org.ics.estudios.domain.catalogs.Personal_Cargo;
import ni.org.ics.estudios.domain.catalogs.Razones_Retiro;
import ni.org.ics.estudios.domain.muestreoanual.ParticipanteProcesos;
import ni.org.ics.estudios.dto.ParticipanteBusquedaDto;
import ni.org.ics.estudios.dto.ParticipanteSeroDto;
import ni.org.ics.estudios.language.MessageResource;
import ni.org.ics.estudios.service.MessageResourceService;
import ni.org.ics.estudios.service.muestreoanual.ParticipanteProcesosService;
import ni.org.ics.estudios.service.retiro.RetiroService;
import ni.org.ics.estudios.web.utils.DateUtil;
import ni.org.ics.estudios.web.utils.JsonUtil;
import org.apache.commons.lang3.text.translate.UnicodeEscaper;
import org.hibernate.mapping.Array;
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
import java.lang.String;
import java.net.InetAddress;
import java.text.ParseException;
import java.util.*;

/**
 * Created by ICS on 29/10/2020.
 */

@Controller
@RequestMapping("/retiro")
public class RetiroController {

    private static final Logger logger = LoggerFactory.getLogger(RetiroController.class);

    /* Instancia de mi Servicio Hemodinamico */
    @Resource(name = "messageResourceService")
    private MessageResourceService messageResourceService;

    @Resource(name = "participanteProcesosService")
    private ParticipanteProcesosService participanteProcesosService;

    @Resource(name = "RetiroService")
    private RetiroService retiroservice;


    /* Buscar Listado por Codigo Participante */
    @RequestMapping(value = "/ListRetiro", method = RequestMethod.GET)
    public ModelAndView ListRetiro()throws Exception{
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/retiro/ListaRetiro");
        return modelAndView;
    }

    //Obtener la lista de retiro por codigo participante
    @RequestMapping(value = "/ListaHojaRetiro", method = RequestMethod.GET, produces ="application/json")
    public @ResponseBody
    List<Retiros> ListaHojaRetiro(@RequestParam(value = "parametro", required=true ) Integer parametro)
            throws Exception{
        List<Retiros> lista = null;
        try {
            lista = this.retiroservice.getListadoRetiroByID(parametro);
            return lista;
        } catch (Exception e) {
            return  lista = null;
        }
    }

    //retiro/searchParticipant
    @RequestMapping(value = "searchParticipant", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    ResponseEntity<String> buscarParticipante(@RequestParam(value="parametro", required=true ) Integer parametro) throws ParseException {
        try {
            ParticipanteSeroDto participante = retiroservice.getDatosParticipanteByCodigo(parametro);
            Participante participante2 = this.retiroservice.getParticipante(parametro);
            String NombreTutor ="";
            if (participante2 != null){
                ParticipanteProcesos procesos = participanteProcesosService.getParticipante(parametro);
                NombreTutor = procesos.getTutor();
            }
            if (participante != null) {
                String estudio = participante.getEstudios();
                String[] arrayString = estudio.split("  ");
                ArrayList<String> frutas = new ArrayList<String>();
                for (int i = 0; i < arrayString.length; i++) {
                    frutas.add(arrayString[i]);
                }
                participante.setSusEstudios(frutas);
                participante.setNombretutor(NombreTutor.toUpperCase());

                String nombrecompleto = participante2.getNombre1().toUpperCase();
                if (participante2.getNombre2() != null)
                    nombrecompleto = nombrecompleto +" "+ participante2.getNombre2().toUpperCase();
                    nombrecompleto = nombrecompleto +" "+ participante2.getApellido1().toUpperCase();
                if (participante2.getApellido2() != null)
                    nombrecompleto = nombrecompleto +" "+ participante2.getApellido2().toUpperCase();
                participante.setNombreCompleto(nombrecompleto);

                String madre = participante2.getNombre1Madre().toUpperCase();
                if (participante2.getNombre2Madre() != null)
                    madre = madre + " " + participante2.getNombre2Madre().toUpperCase();
                madre = madre + " " + participante2.getApellido1Madre().toUpperCase();
                if (participante2.getApellido2Madre() != null)
                    madre = madre + " " + participante2.getApellido2Madre().toUpperCase();
                participante.setNombremadre(madre);

                participante.setEdadParticipante(participante2.getEdad());

                String padre = participante2.getNombre1Padre().toUpperCase();
                if (participante2.getNombre2Padre() != null)
                    padre = padre + " " + participante2.getNombre2Padre().toUpperCase();
                padre = padre + " " + participante2.getApellido1Padre().toUpperCase();

                if (participante2.getApellido2Padre() != null)
                    padre = padre + " " + participante2.getApellido2Padre().toUpperCase();
                participante.setNombrepadre(padre);

                if (participante.getEstado().equals(0))
                    return JsonUtil.createJsonResponse("Participante retirado");
            } else return JsonUtil.createJsonResponse("No se encontró participante según el código ingresado");
            return JsonUtil.createJsonResponse(participante);
        }catch (Exception e){
            return JsonUtil.createJsonResponse(e.getMessage());
        }
    }

    //region Guardar Retiros
    @RequestMapping(value = "saveRetiroForm", method = RequestMethod.GET)
    public String saveRetiroForm(Model model) throws Exception {
        try{
            List<MessageResource> parentesco = messageResourceService.getCatalogo("CP_CAT_RFTUTOR");
            model.addAttribute("parentesco", parentesco);

            List<Personal> recibidaPor = this.retiroservice.getPersonalRecibeRetiro();
            model.addAttribute("recibidaPor", recibidaPor);

            List<MessageResource> tipoFecha = this.messageResourceService.getCatalogo("RETIROS_CAT_TIPO_FECHA");
            model.addAttribute("tipoFecha",tipoFecha);

            List<MessageResource> estudioAretirar = messageResourceService.getCatalogo("RETIROS_CAT_TIPO_ESTUDIO");
            model.addAttribute("estudioAretirar",estudioAretirar);

            List<MessageResource> causaRetiro = messageResourceService.getCatalogo("CAT_CAUSAS_RETIROS");
            model.addAttribute("causaRetiro",causaRetiro);

            List<Personal_Cargo> supervisor = this.retiroservice.getSupervisor();
            model.addAttribute("supervisor", supervisor);

            List<Personal_Cargo> supervisorYdigitador = this.retiroservice.getSupervisorAndDigitador();
            model.addAttribute("supervisorYdigitador", supervisorYdigitador);

            model.addAttribute("estudios", "");
            return "/retiro/RetiroForm";
        }
        catch (Exception e){
            logger.error(e.getMessage());
            throw e;
        }
    }

    //Obtener motivos
    @RequestMapping(value = "/getMotivo", method = RequestMethod.GET, produces="application/json" )
    public @ResponseBody
    String getMotivo(@RequestParam(value = "razonretiro") Integer razonretiro, Model model)  throws Exception {
        List<Razones_Retiro> razonList = null;
        try{
           razonList = this.retiroservice.getRazonesRetiros(razonretiro);
             model.addAttribute("razonList", razonList);
            String jsonResponse;
            jsonResponse = new Gson().toJson(model);
            UnicodeEscaper escaper = UnicodeEscaper.above(127);
            return escaper.translate(jsonResponse);
        }catch (Exception e){
            logger.error(e.getMessage());
            throw e;
        }
    }

    //Guardar Retiro
    @RequestMapping( value="GuardarRetiro", method=RequestMethod.POST)
    public ResponseEntity<String> saveCaseCovid( @RequestParam(value="codigoParticipante", required=false, defaultValue="" ) String codigoParticipante
            ,@RequestParam(value="casaFamilia", required=false, defaultValue="" ) String casaFamilia
            ,@RequestParam(value="casaPDCS", required=false, defaultValue="" ) String casaPDCS
            ,@RequestParam(value="fehaRetiro", required=false, defaultValue="" ) String fehaRetiro
            ,@RequestParam(value="medicosupervisor", required=false, defaultValue="" ) String medicosupervisor
            ,@RequestParam(value="recibidaPor", required=false, defaultValue="" ) String recibidaPor
            ,@RequestParam(value="aretiro", required=false, defaultValue="" ) String aretiro
            ,@RequestParam(value="observacion", required=false, defaultValue="" ) String observacion
            ,@RequestParam(value="otromotivo", required=false, defaultValue="" ) String otromotivo
            ,@RequestParam(value="parentesco", required=false, defaultValue="" ) String parentesco
            ,@RequestParam(value="quiencomunica", required=false, defaultValue="" ) String quiencomunica
            ,@RequestParam(value="fechaFallecido", required=false, defaultValue="" ) String fechaFallecido
            ,@RequestParam(value="devolcioCarnet", required=false, defaultValue="" ) String devolcioCarnet
            ,@RequestParam(value="razonretiro", required=false, defaultValue="" ) String razonretiro

    )throws Exception{
        try{
            Integer codigo = Integer.parseInt(codigoParticipante);
            ParticipanteProcesos procesos = this.participanteProcesosService.getParticipante(codigo);
            Retiros obj = new Retiros();
            Participante p = new Participante();
            p.setCodigo(codigo);
            obj.setParticipante(p);
            Integer kpediatrica = Integer.parseInt(casaPDCS);
            obj.setCodigocasapdcs(kpediatrica);
            obj.setCodigocasafamilia(casaFamilia);
            obj.setFecharetiro(DateUtil.StringToDate(fehaRetiro, "dd/MM/yyyy"));
            if(!fechaFallecido.equals("")){
                obj.setFechafallecido(DateUtil.StringToDate(fechaFallecido,"dd/MM/yyyy"));
            }
            obj.setTipofecha("1");
            Integer supervisor = Integer.parseInt(medicosupervisor);
            obj.setMedicosupervisor(supervisor);
            String comunicador = quiencomunica.equals("") ? "-" : quiencomunica;
            obj.setQuiencomunica(comunicador.toUpperCase());
            Integer relfamiliar = 0;
            if (parentesco.equals("")){
                obj.setRelfam(relfamiliar);
            }else{
                Integer parent = Integer.parseInt(parentesco);
                obj.setRelfam(parent);
            }
            Integer persona = null;
            if (recibidaPor.equals("")){

            }else {
                persona = Integer.parseInt(recibidaPor);
            }
            obj.setPersonadocumenta(persona);
            obj.setMotivo(razonretiro);
            Character c = devolcioCarnet.equals("on") ? '1' : '0';
            obj.setDevolviocarnet(c);
            obj.setObservaciones(observacion.toUpperCase());
            obj.setOtrosmotivo(otromotivo.toUpperCase());
            obj.setEstudioretirado(aretiro);
            obj.setActual(true);
            obj.setEstado('1');
            obj.setPasive('0');
            String computerName = InetAddress.getLocalHost().getHostName();
            obj.setDeviceid(computerName);
            obj.setRecordDate(new Date());
            obj.setRecordUser(SecurityContextHolder.getContext().getAuthentication().getName());
            String estudios = procesos.getEstudio();
            obj.setEstudiosanteriores(estudios);

            String[] arrayString = estudios.split("  ");
            ArrayList<String> frutas = new ArrayList<String>();

            for (int i = 0; i < arrayString.length; i++) {
                frutas.add(arrayString[i]);
                frutas.remove(aretiro);
            }

            if (arrayString.length == 1 ){

                if (aretiro.equals("CH Familia")) {
                    this.retiroservice.procesosEnNoFamilia(codigo);
                }
                if (aretiro.equals("UO1")){
                    //Aqui actualizar todos los procesos de uo1
                }
                if (aretiro.equals("Tcovid")){
                    //Aqui actualizar procesos de Tcovid
                }
                if (aretiro.equals("Dengue")){
                    //Aqui proces de dengue
                }

                //validacion cuando es solo un estudio y diferente a CH Family
                if (!aretiro.equals("CH Familia")) {
                    this.retiroservice.procesosAllEnNo(codigo);
                    //this.retiroservice.ActualizarCampoEstudioAndEstado(codigo);
                }
            }

            StringBuffer sb = new StringBuffer();
            for (String s : frutas) {
                    sb.append(s);
                    sb.append("  ");
            }
            String str = sb.toString();

            //region Cuando tienen mas de un Estudio
            if (arrayString.length > 1) {
                if ( aretiro.equals("CH Familia")) {
                    this.retiroservice.updateEstudiosYcasaFamilia(codigo, str.trim());
                }

                if ( aretiro.equals("Influenza")) {
                    this.retiroservice.procesosEnNoFlu(codigo);
                }

                if ( aretiro.equals("Dengue")) {
                    this.retiroservice.procesosEnNoDengue(codigo);
                }

                if ( aretiro.equals("UO1")) {
                    this.retiroservice.procesosEnNoUO1(codigo);
                }

                if ( aretiro.equals("Tcovid")) {
                    this.retiroservice.procesosEnNoCovid(codigo);
                }
            }
            //endregion
            this.retiroservice.ActualizarCampoEstudio(codigo, str.trim());
            this.retiroservice.allActualBaja(codigo);
            this.retiroservice.SaveRetiros(obj);
            return JsonUtil.createJsonResponse(obj);

        }catch (Exception e){
            logger.error(e.getMessage());
            Gson gson = new Gson();
            String json = gson.toJson(e.toString());
            return new ResponseEntity<String>( json, HttpStatus.CREATED);
        }
    }

    List<MessageResource> messageRelFam = new ArrayList<MessageResource>();
    @RequestMapping(value = "/DetallesRetiro", method = RequestMethod.GET, produces="application/json" )
    public @ResponseBody
    String DetallesRetiro(@RequestParam(value = "idretiro") Integer idretiro)  throws ParseException {
        Map<String, String> map = new HashMap<String, String>();
        Map<String, Object> model = new HashMap<String, Object>();
        Retiros obj = null;
        Personal test = new Personal();
        try {
            obj = this.retiroservice.getRetiroByID(idretiro);
            Integer idsupervisor = obj.getMedicosupervisor();
            test = this.retiroservice.getSupervisorById(idsupervisor);
            Razones_Retiro motivoDetalle = this.retiroservice.getRazonRetiro(obj.getMotivo());
            map.put("motivoDetalle", motivoDetalle.getDescripcion());
            map.put("medicosupervisor", (test != null) ? test.getNombre() : "-" );
            Personal objPersonalDocumenta = this.retiroservice.getSupervisorById(obj.getPersonadocumenta());
            map.put("personadocumenta", objPersonalDocumenta != null ? objPersonalDocumenta.getNombre() : "-" );
            if (obj.getCodigocasapdcs() == null){
                Integer numerocasa = 0;
                map.put("casapediatrica", numerocasa.toString());
            }else{
                String casapediatrica = ""+obj.getCodigocasapdcs();
                map.put("casapediatrica", casapediatrica );
            }
            if (obj.getCodigocasafamilia() == null || obj.getCodigocasafamilia() == "" ){
                Integer numerocasa = 0;
                map.put("casafamilia", numerocasa.toString());
            }else{
                map.put("casafamilia", (obj.getCodigocasafamilia() != null ? obj.getCodigocasafamilia() : "-"));
            }
            map.put("otrosmotivo", (obj.getOtrosmotivo() != null ? obj.getOtrosmotivo() : "-") );
            map.put("observacion", obj.getObservaciones() != null ? obj.getObservaciones() : "-");
            map.put("relfamId", obj.getRelfam().toString());
            map.put("quiencomunica",obj.getQuiencomunica());
            String resultadoCarnet =  (obj.getDevolviocarnet() == '0') ? "No":"Si";
            map.put("carnet", resultadoCarnet);
            messageRelFam = messageResourceService.getMensajeByCatalogAndCatKeys(""+obj.getRelfam(),"CP_CAT_RFTUTOR");
            map.put("relFam",getDescripcionCatalogo(""+obj.getRelfam(),"CP_CAT_RFTUTOR"));
            map.put("observaciones", obj.getObservaciones());
            map.put("motivo", obj.getMotivo());
            String jsonResponse;
            jsonResponse = new Gson().toJson(map);
            UnicodeEscaper escaper = UnicodeEscaper.above(127);
            return escaper.translate(jsonResponse);
        } catch (Exception e) {
            String mensaje = e.getMessage();
            map.put("mensaje", mensaje);
            String jsonResponse;
            jsonResponse = new Gson().toJson(map);
            UnicodeEscaper escaper = UnicodeEscaper.above(127);
            return escaper.translate(jsonResponse);
        }
    }

    private String getDescripcionCatalogo(String codigo,String catroot){
        for(MessageResource rnv : messageRelFam){
            if (rnv.getCatKey().equals(codigo)) {
                if (catroot != "" && rnv.getCatRoot().equals(catroot))
                    return rnv.getSpanish();
            }
        }
        return "-";
    }

    private ResponseEntity<String> JsonUtilcreateJsonResponse( Object o )
    {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");
        Gson gson = new Gson();
        String json = gson.toJson(o);
        UnicodeEscaper escaper = UnicodeEscaper.above(127);
        json = escaper.translate(json);
        return new ResponseEntity<String>( json, headers, HttpStatus.CREATED );
    }

    private ResponseEntity<String> createJsonResponse( Object o ) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");
        Gson gson = new Gson();
        String json = gson.toJson(o);
        UnicodeEscaper escaper = UnicodeEscaper.above(127);
        json = escaper.translate(json);
        return new ResponseEntity<String>(json, headers, HttpStatus.CREATED);
    }

}
