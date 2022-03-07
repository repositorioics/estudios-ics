package ni.org.ics.estudios.web.controller.Bhc;

import com.google.gson.Gson;
import ni.org.ics.estudios.domain.Bhc.Bhc;
import ni.org.ics.estudios.domain.Bhc.Bhc_Detalle_envio;
import ni.org.ics.estudios.domain.Participante;
import ni.org.ics.estudios.domain.SerologiaOct2020.SerologiaEnvio;
import ni.org.ics.estudios.domain.catalogs.Rango_Edad_Volumen;
import ni.org.ics.estudios.domain.muestreoanual.ParticipanteProcesos;
import ni.org.ics.estudios.dto.BhcDto;
import ni.org.ics.estudios.language.MessageResource;
import ni.org.ics.estudios.service.Bhc.BhcService;
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
import java.net.InetAddress;
import java.util.*;

/**
 * Created by ICS on 16/02/2022.
 */

@Controller
@RequestMapping("/Bhc")
public class BhcController {

    private static final Logger logger = LoggerFactory.getLogger(BhcController.class);

    @Resource(name = "participanteProcesosService")
    private ParticipanteProcesosService participanteProcesosService;

    /* Instancia de mi Servicio Hemodinamico */
    @Resource(name = "messageResourceService")
    private MessageResourceService messageResourceService;


    @Resource(name = "SerologiaService")
    private SerologiaOct2020Service serologiaService;

    @Resource(name = "BhcService")
    private BhcService bhcService;

    // Bhc/list
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String Crear(ModelMap model) throws Exception {
        try {
            List<Bhc> bhcs = this.bhcService.getAllBhc();
            model.addAttribute("bhcs", bhcs);
            List<MessageResource> numero_envio = messageResourceService.getCatalogo("CAT_NUMERO_ENVIO_MUESTRA");
            model.addAttribute("numero_envio", numero_envio);
            return "/Bhc/BhcList";
        } catch (Exception e) {
            return "404";
        }
    }


    @RequestMapping(value="/recepcion", method = RequestMethod.GET)
    public String recepcion(ModelMap model) throws Exception{
        try {
            model.addAttribute("agregando",true);
            model.addAttribute("editando",false);
            model.addAttribute("caso", new BhcDto());
            return "/Bhc/BhcForm";
        } catch (Exception e) {
            logger.error(e.getMessage());
            return "404";
        }
    }


    //region Buscar Participante ya Modificado **
    @RequestMapping(value = "/searchParticipant", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    ResponseEntity<String> BuscarParticipanteByID(@RequestParam(value = "parametro", required = true) Integer parametro)throws Exception {
        try {
            BhcDto bhcDto = new BhcDto();
            ParticipanteProcesos procesos;
            Participante participante = this.bhcService.getParticipanteByCodigo(parametro);
            if (participante!=null){

                String nombres = participante.getNombre1().toUpperCase();
                nombres += (participante.getNombre2() != null) ? " "+participante.getNombre2().toUpperCase() : "";
                String apellidos = participante.getApellido1().toUpperCase();
                apellidos += (participante.getApellido2() != null) ? " "+participante.getApellido2().toUpperCase() : "";
                String string;

                procesos = this.participanteProcesosService.getParticipante(participante.getCodigo());
                if (procesos.getEstPart()==1){
                    String estudios = procesos.getEstudio();
                    bhcDto.setNombreCompleto(nombres + " " + apellidos);
                    bhcDto.setEstudios(procesos.getEstudio().trim());
                    string = participante.getEdad();
                    String[] parts = string.split("/");
                    String part1 = parts[0];
                    String part2 = parts[1];
                    String part3 = parts[2];
                    bhcDto.setEdadA(part1);
                    bhcDto.setEdadM(part2);
                    bhcDto.setEdadD(part3);
                    bhcDto.setCodigo_casa_PDCS(participante.getCasa().getCodigo());
                    bhcDto.setCodigo_casa_familia(procesos.getCasaCHF());
                    bhcDto.setEstado("" + procesos.getEstPart());
                    bhcDto.setCodigo_participante(participante.getCodigo());
                    bhcDto.setFechaNacimiento(participante.getFechaNac());
                    //edad Meses
                    double d = Double.parseDouble(part1)*12;
                    Double edadMeses = d % 12;
                    bhcDto.setEdadEnMeses(d);
                    int edad = Integer.parseInt(part1) * 12;
                    String estudiosFinales = "";
                    if (estudios.contains("Tcovid")) {
                        String s = estudios;
                        String[] result = s.split("Tcovid", 2);
                        String first = result[0];
                        estudiosFinales = first.trim();
                    } else {
                        estudiosFinales = procesos.getEstudio().trim();
                    }
                    Rango_Edad_Volumen rango = this.bhcService.getRangoEdadByTipoMuestra(edad, "BHC", estudiosFinales.trim());
                    if (rango!=null){
                        bhcDto.setVolumen_bhc_desde_bd("" + rango.getVolumen());
                    }else{
                        bhcDto.setVolumen_bhc_desde_bd("0");
                    }
                    return JsonUtil.createJsonResponse(bhcDto);
                }else{//todo: Reactivar
                    bhcDto.setNombreCompleto(nombres + " " + apellidos);
                    bhcDto.setEstudios("-");
                    bhcDto.setEstado("" + procesos.getEstPart());
                    int codigopdcs = (participante.getCasa().getCodigo()==null)?0:participante.getCasa().getCodigo();
                    bhcDto.setCodigo_casa_PDCS(codigopdcs);
                    String casaFam = (procesos.getCasaCHF()=="")?"0":procesos.getCasaCHF();
                    bhcDto.setCodigo_casa_familia(casaFam);
                    //edad
                    bhcDto.setFechaNacimiento(new Date());
                    string = participante.getEdad();
                    String[] parts = string.split("/");
                    String part1 = parts[0];
                    String part2 = parts[1];
                    String part3 = parts[2];
                    bhcDto.setEdadA(part1);
                    bhcDto.setEdadM(part2);
                    bhcDto.setEdadD(part3);
                    //edad Meses
                    double d = Double.parseDouble(part1)*12;
                    Double edadMeses = d % 12;
                    bhcDto.setEdadEnMeses(d);
                    bhcDto.setCodigo_participante(participante.getCodigo());
                    int edad = Integer.parseInt(part1) * 12;
                    Rango_Edad_Volumen rango = this.bhcService.getRangoEdadByTipoMuestra(edad,"BHC","NULL");
                    if (rango!=null){
                        bhcDto.setVolumen_bhc_desde_bd("" + rango.getVolumen());
                    }else{
                        bhcDto.setVolumen_bhc_desde_bd("0");
                    }
                    return JsonUtil.createJsonResponse(bhcDto);
                }
            }else {// todo: Nuevo Ingreso
                bhcDto.setNombreCompleto("-");
                bhcDto.setEstudios("-");
                bhcDto.setEstado("2");//nuevo ingreso
                bhcDto.setCodigo_casa_PDCS(0);
                bhcDto.setCodigo_casa_familia("0");
                bhcDto.setEdadA("0");
                bhcDto.setEdadM("0");
                bhcDto.setEdadD("0");
                bhcDto.setCodigo_participante(parametro);
                bhcDto.setFechaNacimiento(new Date());
                bhcDto.setEdadEnMeses(0.0);
                Rango_Edad_Volumen rango = this.bhcService.getRangoEdadByTipoMuestra(0,"BHC","NULL");
                if (rango!=null){
                    bhcDto.setVolumen_bhc_desde_bd("" + rango.getVolumen());
                }else{
                    bhcDto.setVolumen_bhc_desde_bd("0");
                }
            }
            return JsonUtil.createJsonResponse(bhcDto);
        }catch (Exception e){
            logger.error(e.getMessage());
            Gson gson = new Gson();
            String json = gson.toJson(e.toString());
            return  new ResponseEntity<String>( json, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

//endregion

        //region todo: guardar bhc
        @RequestMapping(value = "saveBhc", method = RequestMethod.POST)
        public ResponseEntity<String>saveBhc (@RequestParam(value = "bhc_id", required=false, defaultValue="") String bhc_id
                ,@RequestParam(value = "edadMeses",     required=false, defaultValue="") String edadMeses
                ,@RequestParam( value="tiporequest",    required=false, defaultValue=""  ) String tiporequest
                ,@RequestParam(value = "estado",        required=false, defaultValue="") String estado
                ,@RequestParam( value="idParticipante", required=false, defaultValue="" ) Integer idParticipante
                ,@RequestParam( value="casaCHF",        required=false, defaultValue=""  ) String casaCHF
                ,@RequestParam( value="casaPDCS",       required=false, defaultValue=""  ) Integer casaPDCS
                ,@RequestParam( value="fecha",          required=false, defaultValue=""  ) String fecha
                ,@RequestParam( value="volumen",        required=false, defaultValue=""  ) String volumen
                ,@RequestParam( value="observacion",    required=false, defaultValue=""  ) String observacion
                ,@RequestParam( value="estudios",       required=false, defaultValue=""  ) String estudios
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
                Bhc bhc = new Bhc();
                String nameComputer = InetAddress.getLocalHost().getHostName();
                if (tiporequest.equals("false")){// Guardar nuevo registro

                    Date date = DateUtil.StringToDate(fecha, "dd/MM/yyyy");
                    Calendar calendar = Calendar.getInstance();
                    calendar.setTime(date);
                    int dateYear = calendar.get(Calendar.YEAR);
                    if (!this.bhcService.yaTieneMuestraBhcAnual(dateYear, idParticipante)){
                        //Estudios y edades
                        bhc.setDeviceid(nameComputer);
                        bhc.setEstado('1');
                        bhc.setPasive('0');
                        bhc.setRecordDate(new Date());
                        bhc.setRecordUser(SecurityContextHolder.getContext().getAuthentication().getName());
                        bhc.setCodigo_participante(idParticipante);
                        bhc.setCasa_Familia(casaCHF);
                        bhc.setCasa_PDCS(casaPDCS);
                        bhc.setEnviado('0');
                        bhc.setEstudios(estudios);
                        if (estado.equals("0")) {
                            bhc.setNotas("Reactivar");
                        } else if (estado.equals("3")){
                            bhc.setNotas("Ingreso");
                        }else {
                            bhc.setNotas("");
                        }
                        bhc.setFecha_bhc(DateUtil.StringToDate(fecha, "dd/MM/yyyy"));
                        double EdadEnMeses = Double.parseDouble(edadMeses);
                        bhc.setEdadMeses(EdadEnMeses);
                        String obs = (observacion.equals("")) ? "" : observacion.toUpperCase();
                        bhc.setObservacion(obs);
                        bhc.setVolumen(Double.parseDouble(volumen));
                        this.bhcService.saveOrUpdateBhc(bhc);
                        return JsonUtil.createJsonResponse(bhc);
                    }
                    else {
                        Map<String, String> map = new HashMap<String, String>();
                        map.put("msj", "Participante ya tiene Muestra Anual" );
                        return JsonUtil.createJsonResponse(map);
                    }
                }else{// Editando registro
                    Integer id = Integer.parseInt(bhc_id);
                    bhc.setBhc_id(id);
                    bhc.setDeviceid(nameComputer);
                    bhc.setEstado('1');
                    bhc.setPasive('0');
                    bhc.setRecordDate(new Date());
                    bhc.setRecordUser(SecurityContextHolder.getContext().getAuthentication().getName());
                    bhc.setCodigo_participante(idParticipante);
                    bhc.setCasa_Familia(casaCHF);
                    bhc.setCasa_PDCS(casaPDCS);
                    bhc.setEnviado('0');
                    bhc.setEstudios(estudios);
                    bhc.setFecha_bhc(DateUtil.StringToDate(fecha, "dd/MM/yyyy"));
                    double EdadEnMeses = Double.parseDouble(edadMeses);
                    bhc.setEdadMeses(EdadEnMeses);
                    String obs = (observacion.equals(""))?"":observacion.toUpperCase();
                    bhc.setObservacion(obs);
                    bhc.setVolumen(Double.parseDouble(volumen));
                    this.bhcService.saveOrUpdateBhc(bhc);
                    return JsonUtil.createJsonResponse(bhc);
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


    @RequestMapping(value = "/editBhc/{bhc_id}", method = RequestMethod.GET)
    public String editBhc(ModelMap model,@PathVariable("bhc_id") String bhc_id) throws Exception{
        try {
            Integer id = Integer.parseInt(bhc_id);
            Bhc bhc = this.bhcService.getBhcById(id);
            BhcDto caso = new BhcDto();
            ParticipanteProcesos procesos = null;
            caso.setBhc_id(bhc.getBhc_id());
            caso.setEdadEnMeses(bhc.getEdadMeses());
            caso.setEstado("");
            caso.setCodigo_participante(bhc.getCodigo_participante());
            caso.setEstudios(bhc.getEstudios());
            caso.setCodigo_casa_familia(bhc.getCasa_Familia());
            caso.setCodigo_casa_PDCS(bhc.getCasa_PDCS());
            caso.setFecha(bhc.getFecha_bhc());
            caso.setVolumen_bhc(""+bhc.getVolumen());
            caso.setObservacion(bhc.getObservacion());
            int edadConverted = (int) (bhc.getEdadMeses()/12);
            caso.setEdadA(""+edadConverted);
            String est_part ="Ingreso";
            // buscar estado, nombres
            Participante participante = this.bhcService.getParticipanteByCodigo(bhc.getCodigo_participante());
            if (participante!=null){
                //Nombre participante
                String nombres = participante.getNombre1().toUpperCase();
                nombres += (participante.getNombre2() != null) ? " "+participante.getNombre2().toUpperCase() : "";
                String apellidos = participante.getApellido1().toUpperCase();
                apellidos += (participante.getApellido2() != null) ? " "+participante.getApellido2().toUpperCase() : "";
                caso.setNombreCompleto(nombres +" "+ apellidos);
                String string = participante.getEdad();
                String[] parts = string.split("/");
                String part1 = parts[0];
                String part2 = parts[1];
                String part3 = parts[2];
                caso.setEdadA(part1);
                caso.setEdadM(part2);
                caso.setEdadD(part3);
                caso.setFechaNacimiento(participante.getFechaNac());
                procesos = this.participanteProcesosService.getParticipante(bhc.getCodigo_participante());
                if (procesos.getEstPart()==1)
                    est_part="Activo";
                else
                    est_part="Inactivo";
            }else {
                caso.setNombreCompleto("-");
                caso.setFecha(new Date());
            }
            String estudiosFinales = "";
            if (caso.getEstudios().contains("Tcovid")) {
                String s = caso.getEstudios();
                String[] result = s.split("Tcovid", 2);
                String first = result[0];
                estudiosFinales = first.trim();
            } else if(caso.equals("")){
                estudiosFinales="NULL";
            } else {
                estudiosFinales = procesos.getEstudio().trim();
            }
            int edad_val = (int) edadConverted;
            Rango_Edad_Volumen rango = this.bhcService.getRangoEdadByTipoMuestra(edad_val, "BHC", estudiosFinales.trim());
            if(rango!=null){
                caso.setVolumen_bhc_desde_bd("" + rango.getVolumen());
            }else {
                caso.setVolumen_bhc_desde_bd("0");
            }
            caso.setEstado(est_part);
            model.addAttribute("caso", caso);
            model.addAttribute("agregando",false);
            model.addAttribute("editando",true);
            String ahora = DateUtil.DateToString(new Date(),"dd/MM/yyyy");
            Date finicio = DateUtil.StringToDate(ahora, "dd/MM/yyyy");
            Date ffinal = DateUtil.StringToDate(ahora + " 23:59:59", "dd/MM/yyyy HH:mm:ss");
            Integer CuentaBhc = this.bhcService.ContarBhc(finicio,ffinal);
            model.addAttribute("CuentaBhc",CuentaBhc);
            return "/Bhc/BhcForm";
        } catch (Exception e) {
            logger.error(e.getMessage());
            return "404";
        }
    }


    @RequestMapping(value = "/EnviadasBhc", method = RequestMethod.GET)
    public String listPbmc(Model model)throws Exception{
        List<MessageResource> numero_envio = messageResourceService.getCatalogo("CAT_NUMERO_ENVIO_MUESTRA");
        model.addAttribute("numero_envio", numero_envio);
        return "/Bhc/EnvioBhc";
    }



    // Metodo para realizar envio de BHC **
    @RequestMapping(value = "/sendBhc", method = RequestMethod.POST, produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody ResponseEntity<String> sendAllPbmc(@RequestParam(value="numenvio", required=false ) Integer numenvio
            ,@RequestParam(value="desde", required=false ) String desde
            ,@RequestParam(value="hasta", required=false ) String hasta
            ,@RequestParam(value="fechaEnvio", required=false ) String fechaEnvio
            ,@RequestParam(value="horaEnvio", required=false ) String horaEnvio
            ,@RequestParam(value="temperatura", required=true ) String temperatura
    )throws Exception{
        try{
            Date fdesde = DateUtil.StringToDate(desde +  " 00:00:00","dd/MM/yyyy HH:mm:ss");
            Date fhasta = DateUtil.StringToDate(hasta + " 23:59:59", "dd/MM/yyyy HH:mm:ss");
            List<Bhc> ListaBhcYaEnviadas = this.bhcService.ObtenerBhcEnviadas(fdesde, fhasta);
            if (ListaBhcYaEnviadas.size()<=0)
                return JsonUtil.createJsonResponse("No se encontraron egistros: ".concat(""+ListaBhcYaEnviadas.size()));

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
            int contadorBhc=0;
            if (envio.getIdserologiaenvio()!=null){
                if (ListaBhcYaEnviadas.size()>0){
                    for (Bhc obj: ListaBhcYaEnviadas){
                        Bhc_Detalle_envio bhc_detalle_envio= new Bhc_Detalle_envio();
                        Bhc bhc = this.bhcService.getBhcById(obj.getBhc_id());
                        bhc.setEnviado('1');
                        this.bhcService.saveOrUpdateBhc(bhc);//Actualizo el campo enviado a '1' (Si)
                        bhc_detalle_envio.setBhc(bhc);
                        bhc_detalle_envio.setSerologiaEnvio(envio);
                        this.bhcService.save_Detalles_bhc_Envio(bhc_detalle_envio);// guardo el detalle del envio de PBMC
                        contadorBhc++;
                    }
                }else {
                    return JsonUtil.createJsonResponse("Registros enviados: "+ contadorBhc);
                }
            }
            return JsonUtil.createJsonResponse("Registros Enviados: "+contadorBhc);
        }catch (Exception ex){
            Gson gson = new Gson();
            String json = gson.toJson(ex.toString());
            return new ResponseEntity<String>( json, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }



    @RequestMapping( value="closeCase", method=RequestMethod.POST)
    public ResponseEntity<String> cerrarCaso( @RequestParam(value="bhc_id", required=true ) Integer bhc_id
            , @RequestParam( value="message_razon", required=true, defaultValue="" ) String message_razon ) {
        try{
            Bhc bhc = this.bhcService.getBhcById(bhc_id);
            if (bhc!=null) {
                bhc.setPasive('1');
                bhc.setObservacion(message_razon.toUpperCase());
                this.bhcService.saveOrUpdateBhc(bhc);
            }
            return JsonUtil.createJsonResponse(bhc);
        }
        catch(Exception e){
            Gson gson = new Gson();
            String json = gson.toJson(e.toString());
            return new ResponseEntity<String>( json, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/getObservaciones", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    List<String> getObservaciones(@RequestParam(value = "observacion", required = true) String observacion)
            throws Exception {
        List<MessageResource> obsv =new ArrayList<MessageResource>();
        try {
            ArrayList<String> observacionArrayList = new ArrayList<String>();
            obsv = messageResourceService.getCatalogo("CHF_CAT_RAZON_NO_MX");
            for (MessageResource m: obsv){
                observacionArrayList.add(m.getSpanish());
            }
            return observacionArrayList;
        }catch (Exception e){
            return null;
        }
    }

    public static ResponseEntity<String> createJsonResponse( Object o )
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
