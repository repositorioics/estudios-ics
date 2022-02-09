package ni.org.ics.estudios.web.controller.SerologiaOct2020;

import com.google.gson.Gson;
import ni.org.ics.estudios.domain.Participante;
import ni.org.ics.estudios.domain.SerologiaOct2020.Serologia;
import ni.org.ics.estudios.domain.SerologiaOct2020.SerologiaEnvio;
import ni.org.ics.estudios.domain.SerologiaOct2020.Serologia_Detalle_Envio;
import ni.org.ics.estudios.domain.muestreoanual.ParticipanteProcesos;
import ni.org.ics.estudios.dto.ParticipanteSeroDto;
import ni.org.ics.estudios.dto.SerologiaDto;
import ni.org.ics.estudios.dto.SerologiaEnviarDto;
import ni.org.ics.estudios.language.MessageResource;
import ni.org.ics.estudios.service.MessageResourceService;
import ni.org.ics.estudios.service.ParticipanteService;
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
import java.net.InetAddress;
import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//import com.sun.deploy.uitoolkit.ui.LoggerConsole;
//import ni.org.ics.estudios.domain.Participante;
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


    @Resource(name = "participanteService")
    private ParticipanteService participanteService;

    @Resource(name = "participanteProcesosService")
    private ParticipanteProcesosService participanteProcesosService;

    @Resource(name = "SerologiaService")
    private SerologiaOct2020Service serologiaService;

    //region Formulario
    @RequestMapping(value="/create", method = RequestMethod.GET)
    public String create(ModelMap model) throws Exception{
        try {
            model.addAttribute("agregando",true);
            model.addAttribute("editando",false);
            model.addAttribute("caso", new ParticipanteSeroDto());
            return "/SerologiaOct2020/SerologiaForm";
        } catch (Exception e) {
            logger.error(e.getMessage());
           return "404";
        }
    }

    @RequestMapping(value = "/editMuestra/{idSerologia}", method = RequestMethod.GET)
    public String editMuestra(Model model, @PathVariable("idSerologia") String idSerologia) throws Exception
    {
        try{
            Serologia caso = this.serologiaService.getSerologiaById(idSerologia);
            ParticipanteSeroDto participanteSeroDto = new ParticipanteSeroDto();
            if (caso.getParticipante() !=null){
                Participante participante = this.participanteService.getParticipanteByCodigo(caso.getParticipante());
                if (participante != null){
                    //ParticipanteProcesos procesos = this.participanteProcesosService.getParticipante(caso.getParticipante().getCodigo());
                    participanteSeroDto.setIdSerologia(caso.getIdSerologia());
                    participanteSeroDto.setEdadMeses(caso.getEdadMeses());
                    participanteSeroDto.setFechaNacimiento(participante.getFechaNac());
                    participanteSeroDto.setIdparticipante(caso.getParticipante());
                    participanteSeroDto.setNombreCompleto(participante.getNombreCompleto());
                    participanteSeroDto.setCodigo_casa_PDCS(caso.getCasaPDCS());
                    String string;
                    string = participante.getEdad();
                    String[] parts = string.split("/");
                    String part1 = parts[0];
                    String part2 = parts[1];
                    String part3 = parts[2];
                    participanteSeroDto.setEdad_year(part1);
                    participanteSeroDto.setEdad_meses(part2);
                    participanteSeroDto.setEdad_dias(part3);
                    participanteSeroDto.setFecha(caso.getFecha());
                    participanteSeroDto.setVolumen(caso.getVolumen());
                    participanteSeroDto.setObservacion(caso.getObservacion());
                    participanteSeroDto.setEstudios(caso.getEstudio());
                }else{
                    participanteSeroDto.setIdSerologia(caso.getIdSerologia());
                    participanteSeroDto.setEdadMeses(caso.getEdadMeses());
                    participanteSeroDto.setFechaNacimiento(new Date());
                    participanteSeroDto.setIdparticipante(caso.getParticipante());
                    participanteSeroDto.setNombreCompleto("-");
                    participanteSeroDto.setCodigo_casa_PDCS(0);
                    participanteSeroDto.setCodigo_casa_Familia("0");
                    participanteSeroDto.setEdad_year("0");
                    participanteSeroDto.setEdad_meses("0");
                    participanteSeroDto.setEdad_dias("0");
                    participanteSeroDto.setFecha(caso.getFecha());
                    participanteSeroDto.setVolumen(caso.getVolumen());
                    participanteSeroDto.setObservacion(caso.getObservacion());
                    participanteSeroDto.setEstudios(caso.getEstudio());
                }
                model.addAttribute("caso", participanteSeroDto);
                model.addAttribute("agregando",false);
                model.addAttribute("editando",true);
                model.addAttribute("estudios", "-");
                return "/SerologiaOct2020/SerologiaForm";
            }else{
                return "404";
            }
        }
        catch (Exception e){
            logger.error(e.getMessage());
            return "404";
        }
    }
    //endregion

    //region Enviar Muestra dando Click al boton enviar /Serologia/enviarMuestra Metodo Modificado **
    @RequestMapping(value = "/enviarMuestra", method = RequestMethod.POST, produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody
    ResponseEntity<String> enviarMuestra(@RequestBody SerologiaEnviarDto muestraForEnvio)throws Exception{
        try{
            SerologiaEnvio objToSave = new SerologiaEnvio();
            String computerName = InetAddress.getLocalHost().getHostName();
            objToSave.setDeviceid(computerName);
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
            this.serologiaService.ModificarEnvio(muestraForEnvio.getIdserologia());//Modifica campo Cerrado
            return JsonUtil.createJsonResponse(objToSave);
        }catch(Exception e){
            Gson gson = new Gson();
            String json = gson.toJson(e.toString());
            return new ResponseEntity<String>( json, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    //endregion


    // Metodo ya modificado **
    @RequestMapping(value = "/sendAllSerologias", method = RequestMethod.POST, produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody ResponseEntity<String> sendAllSerologias(@RequestParam(value="numenvio", required=false ) Integer numenvio
            ,@RequestParam(value="desde", required=false ) String desde
            ,@RequestParam(value="hasta", required=false ) String hasta
            ,@RequestParam(value="fechaEnvio", required=false ) String fechaEnvio
            ,@RequestParam(value="horaEnvio", required=false ) String horaEnvio
            ,@RequestParam(value="temperatura", required=true ) String temperatura
    )throws Exception{
        try{
            Date fdesde = DateUtil.StringToDate(desde +  " 00:00:00","dd/MM/yyyy HH:mm:ss");
            Date fhasta = DateUtil.StringToDate(hasta + " 23:59:59", "dd/MM/yyyy HH:mm:ss");
            List<Serologia> ListaSerologiaYaEnviadas = this.serologiaService.ObtenerSerologiasEnviadas(fdesde,fhasta);
            if (ListaSerologiaYaEnviadas.size()<=0)
                return JsonUtil.createJsonResponse("Registros no encontrados: ".concat(""+ListaSerologiaYaEnviadas.size()));


            String computerName = InetAddress.getLocalHost().getHostName();
            SerologiaEnvio envio = new SerologiaEnvio();
            envio.setDeviceid(computerName);
            envio.setEstado('1');
            envio.setPasive('0');
            envio.setRecordDate(new Date());
            envio.setRecordUser(SecurityContextHolder.getContext().getAuthentication().getName());
            envio.setFecha(DateUtil.StringToDate(fechaEnvio, "dd/MM/yyyy"));
            envio.setHora(horaEnvio);
            envio.setIdenvio(numenvio);
            double temp = Double.parseDouble(temperatura);
            envio.setTemperatura(temp);
            this.serologiaService.save_Envio_Serologia(envio);// aqui guardo los datos del envio

            if (envio.getIdserologiaenvio()!=null){

                if (ListaSerologiaYaEnviadas.size()>0){
                    for (Serologia obj: ListaSerologiaYaEnviadas){
                        Serologia_Detalle_Envio detalles_envio= new Serologia_Detalle_Envio();
                        this.serologiaService.ModificarEnvio(obj.getIdSerologia());
                        Serologia objSerologia = this.serologiaService.getSerologiaById(obj.getIdSerologia().toString());
                        detalles_envio.setSerologia(objSerologia);
                        detalles_envio.setSerologiaEnvio(envio);
                        this.serologiaService.save_Detalles_Serologia_Envio(detalles_envio);// guardo el detalle del envio
                    }

                }else {
                    return JsonUtil.createJsonResponse("Registros enviados: ".concat(""+ListaSerologiaYaEnviadas.size()));
                }
            }
            return JsonUtil.createJsonResponse("Registros Enviados: "+ListaSerologiaYaEnviadas.size());
        }catch (Exception ex){
            Gson gson = new Gson();
            String json = gson.toJson(ex.toString());
            return new ResponseEntity<String>( json, HttpStatus.CREATED);
        }
    }

    //region Buscar Participante ya Modificado **
    @RequestMapping(value = "/searchParticipant", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    ResponseEntity<String> BuscarParticipanteByID(@RequestParam(value = "parametro", required = true) Integer parametro)throws Exception {
        try {
            Map<String, String> map = new HashMap<String, String>();
            ParticipanteSeroDto participanteSeroDto = new ParticipanteSeroDto();
            Participante participante = this.serologiaService.getParticipanteByCodigo(parametro);
            if (participante!=null){
                ParticipanteProcesos procesos = this.participanteProcesosService.getParticipante(participante.getCodigo());

                //if (procesos==null)
                    //return JsonUtil.createJsonResponse("No se encontraron Processos del participante!");
                //if (procesos.getEstPart().equals(0))
                    //return JsonUtil.createJsonResponse("Participante retirado");

                String nombres = participante.getNombre1().toUpperCase();
                nombres += (participante.getNombre2() != null) ? " "+participante.getNombre2().toUpperCase() : "";
                String apellidos = participante.getApellido1().toUpperCase();
                apellidos += (participante.getApellido2() != null) ? " "+participante.getApellido2().toUpperCase() : "";
                participanteSeroDto.setNombreCompleto(nombres + " " + apellidos);
                participanteSeroDto.setEstudios(procesos.getEstudio());
                String string;
                string = participante.getEdad();
                String[] parts = string.split("/");
                String part1 = parts[0];
                String part2 = parts[1];
                String part3 = parts[2];
                participanteSeroDto.setEdad_year(part1);
                participanteSeroDto.setEdad_meses(part2);
                participanteSeroDto.setEdad_dias(part3);
                participanteSeroDto.setCodigo_casa_PDCS(participante.getCasa().getCodigo());
                participanteSeroDto.setCodigo_casa_Familia(procesos.getCasaCHF());
                participanteSeroDto.setFechaNacimiento(participante.getFechaNac());
                participanteSeroDto.setEstado(procesos.getEstPart());
                participanteSeroDto.setIdparticipante(participante.getCodigo());
                return JsonUtil.createJsonResponse(participanteSeroDto);
            }else {
                participanteSeroDto.setNombreCompleto("-");
                participanteSeroDto.setEstudios("-");
                participanteSeroDto.setCodigo_casa_PDCS(0);
                participanteSeroDto.setCodigo_casa_Familia("0");
                participanteSeroDto.setEdad_year("0");
                participanteSeroDto.setEdad_meses("0");
                participanteSeroDto.setEdad_dias("0");
                participanteSeroDto.setFechaNacimiento(new Date());
                participanteSeroDto.setIdparticipante(parametro);
            }
            return JsonUtil.createJsonResponse(participanteSeroDto);
        }catch (Exception e){
            logger.error(e.getMessage());
            Gson gson = new Gson();
            String json = gson.toJson(e.toString());
            return  new ResponseEntity<String>( json, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

//endregion

    //region Serologia/GuardarSerologia
    @RequestMapping(value = "GuardarSerologia", method = RequestMethod.POST)
    public ResponseEntity<String>GuardarSerologia (@RequestParam(value = "idSerologia", required=false, defaultValue="") String idSerologia
           ,@RequestParam( value="idParticipante", defaultValue="" ) Integer idParticipante
           ,@RequestParam( value="fecha", required=false, defaultValue=""  ) String fecha
           ,@RequestParam( value="volumen", required=false, defaultValue=""  ) String volumen
           ,@RequestParam( value="observacion", required=false, defaultValue=""  ) String observacion
           ,@RequestParam( value="estudios", required=false, defaultValue=""  ) String estudios
           ,@RequestParam( value="casaCHF", required=false, defaultValue=""  ) String casaCHF
           ,@RequestParam( value="casaPDCS", required=false, defaultValue=""  ) Integer casaPDCS
           ,@RequestParam( value="tiporequest", required=false, defaultValue=""  ) String tiporequest
           ,@RequestParam(value = "edadMeses",required=false, defaultValue="") Integer edadMeses
    ) throws Exception {
        try{
            if (volumen.equals("0") || volumen.equals("")){
                Map<String, String> map = new HashMap<String, String>();
                map.put("msj", "Volumen no puede ser igual a 0." );
                return JsonUtil.createJsonResponse(map);
            }
            if (fecha.equals("")){
                Map<String, String> map = new HashMap<String, String>();
                map.put("msj", "Fecha no puede ser vacía.");
                return JsonUtil.createJsonResponse(map);
            }
            if (idParticipante==0 || idParticipante==null){
                Map<String, String> map = new HashMap<String, String>();
                map.put("msj", "Código participante no es válido.");
                return JsonUtil.createJsonResponse(map);
            }
            Serologia sero = new Serologia();
            String nameComputer = InetAddress.getLocalHost().getHostName();
            if (tiporequest.equals("false")){// Guardar nuevo registro
                if (!serologiaService.ExisteSerologia(DateUtil.StringToDate(fecha,"dd/MM/yyyy"),idParticipante)){
                    //Estudios y edades
                    //ParticipanteProcesos procesos = this.participanteProcesosService.getParticipante(idParticipante);
                        sero.setDeviceid(nameComputer);
                        sero.setEstado('1');
                        sero.setPasive('0');
                        sero.setRecordDate(new Date());
                        sero.setRecordUser(SecurityContextHolder.getContext().getAuthentication().getName());
                        sero.setParticipante(idParticipante);
                        sero.setCasaCHF(casaCHF);
                        sero.setCasaPDCS(casaPDCS);
                        sero.setEnviado('0');
                        sero.setEstudio(estudios);
                        if (casaPDCS == 0 && edadMeses == 0) {
                            sero.setDescripcion("Nuevo Ingreso");
                        } else {
                            sero.setDescripcion("");
                        }
                        sero.setFecha(DateUtil.StringToDate(fecha, "dd/MM/yyyy"));
                        sero.setEdadMeses(edadMeses);
                        String obs = (observacion.equals("")) ? "" : observacion.toUpperCase();
                        sero.setObservacion(obs);
                        sero.setVolumen(Double.parseDouble(volumen));
                        sero.setCodigoPbmc(0);
                        serologiaService.saveSerologia(sero);

                    return JsonUtil.createJsonResponse(sero);
                }
                else {
                    Map<String, String> map = new HashMap<String, String>();
                    map.put("msj", "Muestra ya existe." );
                    return JsonUtil.createJsonResponse(map);
                }
            }else{// Editando registro
                Integer id = Integer.parseInt(idSerologia);
                sero.setIdSerologia(id);
                sero.setDeviceid(nameComputer);
                sero.setEstado('1');
                sero.setPasive('0');
                sero.setRecordDate(new Date());
                sero.setRecordUser(SecurityContextHolder.getContext().getAuthentication().getName());
                sero.setParticipante(idParticipante);
                sero.setCasaCHF(casaCHF);
                sero.setCasaPDCS(casaPDCS);
                sero.setEnviado('0');
                sero.setEstudio(estudios);
                sero.setFecha(DateUtil.StringToDate(fecha, "dd/MM/yyyy"));
                sero.setEdadMeses(edadMeses);
                String obs = (observacion.equals(""))?"":observacion.toUpperCase();
                sero.setObservacion(obs);
                sero.setVolumen(Double.parseDouble(volumen));
                sero.setCodigoPbmc(0);
                serologiaService.saveSerologia(sero);
                return JsonUtil.createJsonResponse(sero);
            }
        }
        catch (Exception e){
            logger.error(e.getMessage());
            Gson gson = new Gson();
            String json = gson.toJson(e.toString());
            return  new ResponseEntity<String>( json, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

//endregion

    @RequestMapping(value = "/listSerologia", method = RequestMethod.GET)
    public String listSerologia(Model model)throws Exception{
        List<Serologia> serologias = this.serologiaService.SerologiaList();
        model.addAttribute("serologias",serologias);
        List<MessageResource> numero_envio = messageResourceService.getCatalogo("CAT_NUMERO_ENVIO_MUESTRA");
        model.addAttribute("numero_envio", numero_envio);
        return "/SerologiaOct2020/List";
    }

    //Serologia/listMuestrasNoEnviadas
    @RequestMapping(value = "/listMuestrasNoEnviadas", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody List<SerologiaDto> ListaMuestraToJson() throws ParseException {
        List<SerologiaDto> seroDtom = null;
        try{
            seroDtom =  this.serologiaService.SerologiaNoEnviadaDto();
            return  seroDtom;
        }catch (Exception e){
            logger.error(e.getMessage());
            return  seroDtom;
        }
    }


    @RequestMapping(value = "/listEnviosMuestras", method = RequestMethod.GET, produces = "application/json")
    public String listEnviosMuestras(Model model)throws Exception{
        List<SerologiaDto> serologias = this.serologiaService.SerologiaNoEnviadaDto();
        model.addAttribute("serologias",serologias);
        List<MessageResource> numero_envio = messageResourceService.getCatalogo("CAT_NUMERO_ENVIO_MUESTRA");
        model.addAttribute("numero_envio", numero_envio);
        return "/SerologiaOct2020/EnvioForm";
    }


//endregion


    @RequestMapping( value="closeCase", method=RequestMethod.POST)
    public ResponseEntity<String> cerrarCaso( @RequestParam(value="idAccion", required=true ) String idAccion
            , @RequestParam( value="message_razon", required=true, defaultValue="" ) String message_razon ) {
        try{
            Serologia serologia = this.serologiaService.getSerologiaById(idAccion);
            if (serologia!=null) {
                serologia.setPasive('1');
                serologia.setObservacion(message_razon.toUpperCase());
                this.serologiaService.saveSerologia(serologia);
            }
            return JsonUtil.createJsonResponse(serologia);
        }
        catch(Exception e){
            Gson gson = new Gson();
            String json = gson.toJson(e.toString());
            return new ResponseEntity<String>( json, HttpStatus.INTERNAL_SERVER_ERROR);
        }
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

}
