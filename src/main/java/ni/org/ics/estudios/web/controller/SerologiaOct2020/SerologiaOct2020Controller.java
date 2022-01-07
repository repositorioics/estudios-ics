package ni.org.ics.estudios.web.controller.SerologiaOct2020;

import com.google.gson.Gson;
//import com.sun.deploy.uitoolkit.ui.LoggerConsole;
//import ni.org.ics.estudios.domain.Participante;
import ni.org.ics.estudios.domain.Participante;
import ni.org.ics.estudios.domain.SerologiaOct2020.Envio;
//import ni.org.ics.estudios.domain.SerologiaOct2020.RangoGradilla;
import ni.org.ics.estudios.domain.SerologiaOct2020.Serologia;
import ni.org.ics.estudios.domain.SerologiaOct2020.SerologiaEnvio;
import ni.org.ics.estudios.domain.SerologiaOct2020.SerologiaEnvio;
import ni.org.ics.estudios.domain.catalogs.Personal;
import ni.org.ics.estudios.domain.muestreoanual.ParticipanteProcesos;
import ni.org.ics.estudios.dto.*;
import ni.org.ics.estudios.language.MessageResource;
import ni.org.ics.estudios.service.MessageResourceService;
import ni.org.ics.estudios.service.SerologiaOct2020.SerologiaOct2020Service;
import ni.org.ics.estudios.service.muestreoanual.ParticipanteProcesosService;
import ni.org.ics.estudios.web.utils.DateUtil;
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
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.*;
import com.google.gson.Gson;
import org.apache.commons.lang3.text.translate.UnicodeEscaper;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
/**
 * Created by ICS on 15/10/2020.
 */

@Controller
@RequestMapping("/Serologia")
public class SerologiaOct2020Controller {
    private static final Logger logger = LoggerFactory.getLogger(SerologiaOct2020Controller.class);

    /* Instancia de mi Servicio Hemodinamico */
    @Resource(name = "messageResourceService")
    private MessageResourceService messageResourceService;

    @Resource(name = "participanteProcesosService")
    private ParticipanteProcesosService participanteProcesosService;

    @Resource(name = "SerologiaService")
    private SerologiaOct2020Service serologiaService;

    //region Formulario
    @RequestMapping(value="/create", method = RequestMethod.GET)
    public String create(ModelMap model) throws Exception{
        try {
            List<Personal> personaValida = serologiaService.getListPersonRececiona();
            model.addAttribute("personaValida", personaValida);
            model.addAttribute("agregando",true);
            model.addAttribute("editando",false);
            model.addAttribute("caso", new Serologia());
            return "/SerologiaOct2020/SerologiaForm";
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw e;
        }
    }
    //endregion

    //region Ir Formulario para Editar
    @RequestMapping(value = "/editMuestra/{idSerologia}", method = RequestMethod.GET)
    public String editMuestra(Model model, @PathVariable("idSerologia") String idSerologia) throws Exception
    {
        try{
            Serologia caso = this.serologiaService.getSerologiaById(idSerologia);

            if (caso.getParticipante()==null){
             return "404";
            }else{
                ParticipanteProcesos procesos = this.participanteProcesosService.getParticipante(caso.getParticipante().getCodigo());
                model.addAttribute("agregando",false);
                model.addAttribute("editando",true);
                model.addAttribute("caso", caso);
                model.addAttribute("estudios", procesos.getEstudio());
                return "/SerologiaOct2020/SerologiaForm";
            }
        }
        catch (Exception e){
            logger.error(e.getMessage());
            throw e;
        }
    }
    //endregion

    //region Enviar Muestra dando Click al boton enviar /Serologia/enviarMuestra
    @RequestMapping(value = "/enviarMuestra", method = RequestMethod.POST, produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody
    ResponseEntity<String> enviarMuestra(@RequestBody SerologiaEnviarDto muestraForEnvio)throws Exception{
        try{
            SerologiaEnvio objToSave = new SerologiaEnvio();
            objToSave.setDeviceid("server");
            objToSave.setEstado('1');
            objToSave.setPasive('0');
            objToSave.setRecordDate(new Date());
            objToSave.setRecordUser(SecurityContextHolder.getContext().getAuthentication().getName());
            objToSave.setFecha(DateUtil.StringToDate(muestraForEnvio.getFechaenvio(), "dd/MM/yyyy"));
            objToSave.setHora(muestraForEnvio.getHora());
            objToSave.setIdenvio(muestraForEnvio.getNenvios());

            String idMxSerologica = muestraForEnvio.getIdserologia().toString();
            Serologia objSerologia = this.serologiaService.getSerologiaById(idMxSerologica);
            objSerologia.setIdSerologia(objSerologia.getIdSerologia());
            objToSave.setSerologia(objSerologia);


            //service guardar en tabla envios
            this.serologiaService.saveSerologiaEnviadas(objToSave);
            this.serologiaService.ModificarEnvio(muestraForEnvio.getIdserologia());//Modifica campo Cerrado
            return JsonUtil.createJsonResponse(objToSave);
        }catch(Exception e){
            Gson gson = new Gson();
            String json = gson.toJson(e.toString());
            return new ResponseEntity<String>( json, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    //endregion


    @RequestMapping(value = "/sendAllSerologias", method = RequestMethod.POST, produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody ResponseEntity<String> sendAllSerologias(@RequestParam(value="numenvio", required=false ) Integer numenvio,
            @RequestParam(value="desde", required=false ) String desde,
            @RequestParam(value="hasta", required=false ) String hasta
            ,@RequestParam(value="fechaEnvio", required=false ) String fechaEnvio
            ,@RequestParam(value="horaEnvio", required=false ) String horaEnvio
    )throws Exception{
        try{
            Date fdesde = DateUtil.StringToDate(desde +  " 00:00:00","dd/MM/yyyy HH:mm:ss");
            Date fhasta = DateUtil.StringToDate(hasta + " 23:59:59", "dd/MM/yyyy HH:mm:ss");
            //this.serologiaService.ModificarAllEnvios(fdesde,fhasta);
            List<Serologia> ListaSerologiaYaEnviadas = this.serologiaService.ObtenerSerologiasEnviadas(fdesde,fhasta);
            if (ListaSerologiaYaEnviadas.size()>0){
                for (Serologia obj: ListaSerologiaYaEnviadas){
                    SerologiaEnvio objToSave = new SerologiaEnvio();
                    objToSave.setDeviceid("server");
                    objToSave.setEstado('1');
                    objToSave.setPasive('0');
                    objToSave.setRecordDate(new Date());
                    objToSave.setRecordUser(SecurityContextHolder.getContext().getAuthentication().getName());
                    objToSave.setFecha(DateUtil.StringToDate(fechaEnvio, "dd/MM/yyyy"));
                    objToSave.setHora(horaEnvio);
                    objToSave.setIdenvio(numenvio);
                    this.serologiaService.ModificarEnvio(obj.getIdSerologia());
                    Serologia objSerologia = this.serologiaService.getSerologiaById(obj.getIdSerologia().toString());
                    objToSave.setSerologia(objSerologia);
                    this.serologiaService.saveSerologiaEnviadas(objToSave);
                }
            }else {
                return JsonUtil.createJsonResponse("Registros enviados: ".concat(""+ListaSerologiaYaEnviadas.size()));
            }
            return JsonUtil.createJsonResponse("Registros Enviados: "+ListaSerologiaYaEnviadas.size());
        }catch (Exception ex){
            Gson gson = new Gson();
            String json = gson.toJson(ex.toString());
            return new ResponseEntity<String>( json, HttpStatus.CREATED);
        }
    }




    //region Buscar Participante
    @RequestMapping(value = "/searchParticipant", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    ResponseEntity<String> BuscarParticipanteByID(@RequestParam(value = "parametro", required = true) Integer parametro)throws Exception {
        try {
            Map<String, String> map = new HashMap<String, String>();
            ParticipanteSeroDto participante = this.serologiaService.getDatosParticipanteById(parametro);
            if (participante!=null){

                if (participante.getEstudios().equalsIgnoreCase("Dengue"))
                    return JsonUtil.createJsonResponse("Participante pertenece cohorte Dengue");

                if (participante.getEstudios().equalsIgnoreCase("UO1"))
                    return JsonUtil.createJsonResponse("Participante pertenece estudio UO1");

                if (participante.getEstado().equals(0))
                    return JsonUtil.createJsonResponse("Participante retirado");

            }else return JsonUtil.createJsonResponse("No se encontró participante según el código ingresado");
            return JsonUtil.createJsonResponse(participante);
        }catch (Exception e){
            logger.error(e.getMessage());
            throw e;
        }
    }

//endregion

    //region Serologia/GuardarSerologia
    @RequestMapping(value = "GuardarSerologia", method = RequestMethod.POST)
    public ResponseEntity<String>GuardarSerologia (@RequestParam(value = "idSerologia", required=false, defaultValue="") String idSerologia
           ,@RequestParam( value="idParticipante", defaultValue="" ) Integer idParticipante
           ,@RequestParam( value="fecha", required=false, defaultValue=""  ) String fecha
           ,@RequestParam( value="volumen", required=false, defaultValue=""  ) String volumen
           ,@RequestParam( value="precepciona", required=false, defaultValue=""  ) Integer precepciona
           ,@RequestParam( value="observacion", required=false, defaultValue=""  ) String observacion
           ,@RequestParam( value="estudios", required=false, defaultValue=""  ) String estudios
           ,@RequestParam( value="casaCHF", required=false, defaultValue=""  ) String casaCHF
           ,@RequestParam( value="tiporequest", required=false, defaultValue=""  ) String tiporequest
           ,@RequestParam(value = "edadMeses",required=false, defaultValue="") Integer edadMeses

    ) throws Exception {
        try{
            Serologia sero = new Serologia();
            if (tiporequest.equals("false")){
                if (!serologiaService.ExisteSerologia(DateUtil.StringToDate(fecha,"dd/MM/yyyy"),idParticipante)){
                    sero.setDeviceid("server");
                    sero.setEstado('1');
                    sero.setPasive('0');
                    sero.setRecordDate(new Date());
                    sero.setRecordUser(SecurityContextHolder.getContext().getAuthentication().getName());
                    Participante p = new Participante();
                    p.setCodigo(idParticipante);
                    sero.setParticipante(p);
                    sero.setCasaCHF(casaCHF);
                    sero.setCerrado('0');
                    String estudiosFinales="";
                    if (estudios.contains("Tcovid")){
                        String s = estudios;
                        String[] result = s.split("Tcovid", 2);
                        String first = result[0];
                        estudiosFinales = first;
                     sero.setEstudio(estudiosFinales);
                    }else{
                        sero.setEstudio(estudios);
                    }
                    sero.setFecha(DateUtil.StringToDate(fecha, "dd/MM/yyyy"));
                    sero.setEdadMeses(edadMeses);
                    sero.setObservacion(observacion);
                    sero.setPrecepciona(precepciona);
                    sero.setVolumen(Double.parseDouble(volumen));
                    serologiaService.saveSerologia(sero);
                    return JsonUtil.createJsonResponse(sero);
                }
                else {
                    Map<String, String> map = new HashMap<String, String>();
                    map.put("msj", "Muestra ya existe." );
                    return JsonUtil.createJsonResponse(map);
                }
            }else{
                Integer id = Integer.parseInt(idSerologia);
                sero.setIdSerologia(id);
                sero.setDeviceid("server");
                sero.setEstado('1');
                sero.setPasive('0');
                sero.setRecordDate(new Date());
                sero.setRecordUser(SecurityContextHolder.getContext().getAuthentication().getName());
                Participante p = new Participante();
                p.setCodigo(idParticipante);
                sero.setParticipante(p);
                sero.setCasaCHF(casaCHF);
                sero.setCerrado('0');
                sero.setEstudio(estudios);
                sero.setFecha(DateUtil.StringToDate(fecha, "dd/MM/yyyy"));
                sero.setEdadMeses(edadMeses);
                sero.setObservacion(observacion);
                sero.setPrecepciona(precepciona);
                sero.setVolumen(Double.parseDouble(volumen));
                serologiaService.saveSerologia(sero);
                return JsonUtil.createJsonResponse(sero);
            }
        }
        catch (Exception e){
            logger.error(e.getMessage());
            Gson gson = new Gson();
            String json = gson.toJson(e.toString());
            return  new ResponseEntity<String>( json, HttpStatus.CREATED);
        }

    }

//endregion

    //region Guardar Nuevo Ingreso

    @RequestMapping(value = "GuardarNuevaSerologia", method = RequestMethod.POST)
    public ResponseEntity<String>GuardarNuevaSerologia(@RequestParam( value="txtNewParticipante", defaultValue="" ) Integer txtNewParticipante
            ,@RequestParam( value="txtvolumen", required=false, defaultValue=""  ) double txtvolumen
            ,@RequestParam( value="txtObservacion", required=false, defaultValue=""  ) String txtObservacion
            ,@RequestParam(value = "fechaNuevoIng") String fechaNuevoIng
    )throws Exception{
        Serologia sero = new Serologia();
        sero.setDeviceid("server");
        sero.setEstado('1');
        sero.setPasive('0');
        sero.setRecordDate(new Date());
        sero.setRecordUser(SecurityContextHolder.getContext().getAuthentication().getName());
        sero.setCodigonuevoparticipante(txtNewParticipante);
        sero.setCasaCHF("-");
        sero.setCerrado('0');
        sero.setEstudio("-");
        sero.setFecha(DateUtil.StringToDate(fechaNuevoIng, "yyyy-MM-dd"));
        sero.setEdadMeses(Integer.parseInt("0"));
        sero.setObservacion(txtObservacion);
        sero.setPrecepciona(Integer.parseInt("0"));
        sero.setVolumen(txtvolumen);
        serologiaService.saveSerologia(sero);
        return JsonUtil.createJsonResponse(sero);
    }
    //endregion


    @RequestMapping(value = "/listSerologia", method = RequestMethod.GET)
    public String listSerologia(Model model)throws Exception{
        List<Serologia> serologias = this.serologiaService.SerologiaList();
        List<Personal> person = this.serologiaService.getListPersonRececiona();
        model.addAttribute("person", person);
        model.addAttribute("serologias",serologias);
        List<Envio> nenvios = this.serologiaService.getAllEnvios();
        model.addAttribute("nenvios",nenvios);
        return "/SerologiaOct2020/List";
    }

    //Serologia/listMuestrasNoEnviadas
    @RequestMapping(value = "/listMuestrasNoEnviadas", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody List<Serologia> ListaMuestraToJson() throws ParseException {
        List<Serologia> seroDtom = null;
        try{
            seroDtom =  this.serologiaService.SerologiaNoEnviada();
            return  seroDtom;
        }catch (Exception e){
            logger.error(e.getMessage());
            return  seroDtom;
        }
    }


    @RequestMapping(value = "/listEnviosMuestras", method = RequestMethod.GET, produces = "application/json")
    public String listEnviosMuestras(Model model)throws Exception{
        List<Serologia> serologias = this.serologiaService.SerologiaNoEnviada();
        model.addAttribute("serologias",serologias);
        List<Envio> nenvios = this.serologiaService.getAllEnvios();
        model.addAttribute("nenvios",nenvios);
        return "/SerologiaOct2020/EnvioForm";
    }


    //region BuscarGradilla
    @RequestMapping(value = "/BuscarUltGradilla", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody ResponseEntity<String>BuscarUltGradilla(@RequestParam(value="fechaInicio", required=false ) String fechaInicio,
                                                                 @RequestParam(value="fechaFin", required=false ) String fechaFin)
            throws Exception{
        Date dFechaInicio = null;
        if (fechaInicio!=null && !fechaInicio.isEmpty())
            dFechaInicio = DateUtil.StringToDate(fechaInicio+ " 00:00:00", "dd-MM-yyyy HH:mm:ss");
        Date dFechaFin =  DateUtil.StringToDate(fechaInicio, "dd-MM-yyyy");
        if (dFechaFin!=null)
            dFechaFin = DateUtil.StringToDate(fechaInicio+ " 23:59:59", "dd-MM-yyyy HH:mm:ss");
        //GradillaDto ultGradilla = this.serologiaService.getUtlGradillaByDates(dFechaInicio,dFechaFin);
        return null;// JsonUtil.createJsonResponse(ultGradilla);
    }
//endregion



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

}
