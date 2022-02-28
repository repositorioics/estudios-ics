package ni.org.ics.estudios.web.controller.Pbmc;

import com.google.gson.Gson;
import ni.org.ics.estudios.domain.Participante;
import ni.org.ics.estudios.domain.Pbmc.Pbmc;
import ni.org.ics.estudios.domain.Pbmc.Pbmc_Detalle_Envio;
import ni.org.ics.estudios.domain.SerologiaOct2020.Serologia;
import ni.org.ics.estudios.domain.SerologiaOct2020.SerologiaEnvio;
import ni.org.ics.estudios.domain.SerologiaOct2020.Serologia_Detalle_Envio;
import ni.org.ics.estudios.domain.catalogs.Rango_Edad_Volumen;
import ni.org.ics.estudios.domain.muestreoanual.ParticipanteProcesos;
import ni.org.ics.estudios.dto.PbmcDto;
import ni.org.ics.estudios.language.MessageResource;
import ni.org.ics.estudios.service.MessageResourceService;
import ni.org.ics.estudios.service.ParticipanteService;
import ni.org.ics.estudios.service.Pbmc.PbmcService;
import ni.org.ics.estudios.service.SerologiaOct2020.SerologiaOct2020Service;
import ni.org.ics.estudios.service.muestreoanual.ParticipanteProcesosService;
import ni.org.ics.estudios.web.utils.DateUtil;
import ni.org.ics.estudios.web.utils.JsonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.net.InetAddress;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by ICS on 03/02/2022.
 */

@Controller
@RequestMapping("/Pbmc")
public class PbmcController {
    private static final Logger logger = LoggerFactory.getLogger(PbmcController.class);

    @Resource(name = "participanteService")
    private ParticipanteService participanteService;

    @Resource(name = "participanteProcesosService")
    private ParticipanteProcesosService participanteProcesosService;

    @Resource(name = "SerologiaService")
    private SerologiaOct2020Service serologiaService;

    @Resource(name = "PbmcService")
    private PbmcService pbmcService;

    @Resource(name = "messageResourceService")
    private MessageResourceService messageResourceService;

    @RequestMapping(value="/recepcion", method = RequestMethod.GET)
    public String recepcion(ModelMap model) throws Exception{
        try {
            model.addAttribute("agregando",true);
            model.addAttribute("editando",false);
            model.addAttribute("caso", new PbmcDto());
            String ahora = DateUtil.DateToString(new Date(),"dd/MM/yyyy");
            Date finicio = DateUtil.StringToDate(ahora, "dd/MM/yyyy");
            Date ffinal = DateUtil.StringToDate(ahora + " 23:59:59", "dd/MM/yyyy HH:mm:ss");
            Integer CuentaPbmc = this.pbmcService.ContarPbmc(finicio,ffinal);
            model.addAttribute("CuentaPbmc",CuentaPbmc);
            return "/Pbmc/PbmcForm";
        } catch (Exception e) {
            logger.error(e.getMessage());
            return "404";
        }
    }

    @RequestMapping(value = "/editPbmc/{pbcm_id}", method = RequestMethod.GET)
    public String editPbmc(ModelMap model,@PathVariable("pbcm_id") String pbcm_id) throws Exception{
        try {
            Integer id = Integer.parseInt(pbcm_id);
            Pbmc pbmc = this.pbmcService.getPbmcByID(id);
            PbmcDto caso = new PbmcDto();
            ParticipanteProcesos procesos = null;
            String est_part ="Ingreso";
            caso.setCodigo_pbmc(pbmc.getPbcm_id());
            caso.setEdadEnMeses(pbmc.getEdadMeses());
            caso.setCodigo_casa_familia(pbmc.getCasaCHF());
            caso.setCodigo_casa_PDCS(pbmc.getCasaPDCS());
            caso.setCodigo_participante(pbmc.getCodigo_participante());
            caso.setEstudios(pbmc.getEstudios());
            int edadConverter = pbmc.getEdadMeses()/12;
            caso.setEdadA(""+edadConverter);
            caso.setEdadM("0");
            caso.setFecha(DateUtil.DateToString(pbmc.getFecha_pbmc(),"dd/MM/yyyy"));
            caso.setVolumen_pbmc(""+pbmc.getVolumen());
            if (pbmc.getPbmc_tiene_serologia()=='1') {
             Serologia serologia = this.pbmcService.getSerologiaByPbmc_Id(pbmc.getPbcm_id());
                caso.setVolumen_rojo_adic("" + serologia.getVolumen());
                caso.setId_serologia(serologia.getIdSerologia());
            }else {
                caso.setVolumen_rojo_adic("0");
            }
            caso.setObservacion(pbmc.getObservacion());
            Participante participante = this.participanteService.getParticipanteByCodigo(pbmc.getCodigo_participante());
            if (participante!=null){
                procesos = this.participanteProcesosService.getParticipante(pbmc.getCodigo_participante());
                if (procesos.getEstPart()==1)
                    est_part = "Activo";
                else
                    est_part = "Inactivo";
            }
            String estudiosFinales = "";
            if (pbmc.getEstudios().contains("Tcovid")){
                String s = pbmc.getEstudios();
                String[] result = s.split("Tcovid", 2);
                String first = result[0];
                estudiosFinales = first.trim();
            }else if(caso.equals("")){
                estudiosFinales="NULL";
            } else {
                estudiosFinales = pbmc.getEstudios().trim();
            }
            Rango_Edad_Volumen rango = this.serologiaService.getRangoEdadByTipoMuestra(edadConverter, "PBMC", estudiosFinales.trim());
            if (rango!=null){
                caso.setVolumen_pbmc_desde_bd("" + rango.getVolumen());
                caso.setVolumen_adicional_desde_bd("" + rango.getVolumen_adicional());
            }else{
                caso.setVolumen_pbmc_desde_bd("0");
                caso.setVolumen_adicional_desde_bd("0");
            }
            caso.setEstado(est_part);
            model.addAttribute("agregando",false);
            model.addAttribute("editando",true);
            model.addAttribute("caso", caso);
            String ahora = DateUtil.DateToString(new Date(),"dd/MM/yyyy");
            Date finicio = DateUtil.StringToDate(ahora, "dd/MM/yyyy");
            Date ffinal = DateUtil.StringToDate(ahora + " 23:59:59", "dd/MM/yyyy HH:mm:ss");
            Integer CuentaPbmc = this.pbmcService.ContarPbmc(finicio,ffinal);
            model.addAttribute("CuentaPbmc",CuentaPbmc);
            return "/Pbmc/PbmcForm";
        } catch (Exception e) {
            logger.error(e.getMessage());
            return "404";
        }
    }


    @RequestMapping(value = "/listPbmc", method = RequestMethod.GET)
    public String listPbmc(Model model)throws Exception{
        List<Pbmc> pbmc = this.pbmcService.getAllPbmc();
        model.addAttribute("pbmc",pbmc);
        List<MessageResource> numero_envio = messageResourceService.getCatalogo("CAT_NUMERO_ENVIO_MUESTRA");
        model.addAttribute("numero_envio", numero_envio);
        return "/Pbmc/ListPbmc";
    }


    //region Buscar Participante ya Modificado **
    @RequestMapping(value = "/searchParticipant", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    ResponseEntity<String> BuscarParticipanteByID(@RequestParam(value = "parametro", required = true) Integer parametro)throws Exception {
        try {
            String fechaNacimiento="";
            String est_part ="";
            ParticipanteProcesos procesos = null;
            PbmcDto participantePbmcDto = new PbmcDto();
            Participante participante = this.participanteService.getParticipanteByCodigo(parametro);
            if (participante!=null) {
                procesos = this.participanteProcesosService.getParticipante(participante.getCodigo());
                if (procesos.getEstPart()==1 & procesos.getEstudio()!="" ){//Participante activo
                String estudios = (procesos.getEstudio().equals("")) ? "-" : procesos.getEstudio();
                participantePbmcDto.setEstudios(estudios);
                fechaNacimiento = DateUtil.DateToString(participante.getFechaNac(), "yyyy-MM-dd");
                participantePbmcDto.setFechaNacimiento(fechaNacimiento);
                String string;
                string = participante.getEdad();
                String[] parts = string.split("/");
                String part1 = parts[0];
                String part2 = parts[1];
                participantePbmcDto.setEdadA(part1);
                participantePbmcDto.setEdadM(part2);
                double edad_meses = Double.parseDouble(part1) * 12;
                participantePbmcDto.setEdadEnMeses(edad_meses);
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
                Rango_Edad_Volumen rango = this.pbmcService.getRangoEdadByTipoMuestra(edad, "PBMC", estudiosFinales.trim());
                   if (rango!=null){
                       participantePbmcDto.setVolumen_pbmc_desde_bd("" + rango.getVolumen());
                       participantePbmcDto.setVolumen_adicional_desde_bd("" + rango.getVolumen_adicional());
                   }else {
                       participantePbmcDto.setVolumen_pbmc_desde_bd("0");
                       participantePbmcDto.setVolumen_adicional_desde_bd("0");
                   }
                est_part = (procesos.getEstPart() == 1) ? "Activo" : "Inactivo";
                participantePbmcDto.setEstado(est_part);
                participantePbmcDto.setCodigo_participante(participante.getCodigo());
                participantePbmcDto.setCodigo_casa_PDCS(participante.getCasa().getCodigo());
                String casaFam = (procesos.getCasaCHF() == null) ? "0" : procesos.getCasaCHF();
                participantePbmcDto.setCodigo_casa_familia(casaFam);
            }else{//Reactivación
                    participantePbmcDto.setFechaNacimiento(DateUtil.DateToString(participante.getFechaNac(),"dd/MM/yyyy"));
                    String string = participante.getEdad();
                    String[] parts = string.split("/");
                    String part1 = parts[0];
                    String part2 = parts[1];
                    participantePbmcDto.setEdadA(part1);
                    participantePbmcDto.setEdadM(part2);
                    double edad_meses = Double.parseDouble(part1)*12;
                    participantePbmcDto.setEdadEnMeses(edad_meses);
                    participantePbmcDto.setCodigo_casa_PDCS(participante.getCasa().getCodigo());
                    participantePbmcDto.setCodigo_casa_familia("0");
                    participantePbmcDto.setCodigo_participante(participante.getCodigo());
                    participantePbmcDto.setEstudios("-");
                    participantePbmcDto.setEstado("Reactivar");
                    Rango_Edad_Volumen rango = this.pbmcService.getRangoEdadByTipoMuestra(0,"PBMC","NULL");
                    if (rango!=null){
                        participantePbmcDto.setVolumen_pbmc_desde_bd(""+rango.getVolumen());
                        participantePbmcDto.setVolumen_adicional_desde_bd(""+rango.getVolumen_adicional());
                    }else{
                        participantePbmcDto.setVolumen_pbmc_desde_bd("0");
                        participantePbmcDto.setVolumen_adicional_desde_bd("0");
                    }
                    return JsonUtil.createJsonResponse(participantePbmcDto);
             }
            }else { //Nuevo Ingreso
                participantePbmcDto.setCodigo_participante(parametro);
                participantePbmcDto.setEstudios("-");
                participantePbmcDto.setEdadA("0");
                participantePbmcDto.setEdadM("0");
                fechaNacimiento = DateUtil.DateToString(new Date(),"yyyy-MM-dd");
                participantePbmcDto.setEdadEnMeses(0);
                participantePbmcDto.setFechaNacimiento(fechaNacimiento);
                participantePbmcDto.setEstado("Ingreso");
                participantePbmcDto.setCodigo_casa_PDCS(0);
                participantePbmcDto.setCodigo_casa_familia("0");
                Rango_Edad_Volumen rango = this.pbmcService.getRangoEdadByTipoMuestra(0,"PBMC","NULL");
                if (rango!=null){
                    participantePbmcDto.setVolumen_pbmc_desde_bd(""+rango.getVolumen());
                    participantePbmcDto.setVolumen_adicional_desde_bd(""+rango.getVolumen_adicional());
                }else{
                    participantePbmcDto.setVolumen_pbmc_desde_bd("0");
                    participantePbmcDto.setVolumen_adicional_desde_bd("0");
                }
            }
            return JsonUtil.createJsonResponse(participantePbmcDto);
        }catch (Exception e){
            logger.error(e.getMessage());
            Gson gson = new Gson();
            String json = gson.toJson(e.toString());
            return  new ResponseEntity<String>( json, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "GuardarPbmc", method = RequestMethod.POST)
    public ResponseEntity<String>GuardarSerologia(@RequestParam(value = "codigo_pbmc", required=false, defaultValue="" ) Integer codigo_pbmc
            ,@RequestParam( value = "codigo_participante" ,required=false, defaultValue=""  ) Integer codigo_participante
            ,@RequestParam( value = "volumen_pbmc"        ,required=false, defaultValue=""  ) String  volumen_pbmc
            ,@RequestParam( value = "fecha"               ,required=false, defaultValue=""  ) String  fecha
            ,@RequestParam( value = "volumen_rojo_adic"   ,required=false, defaultValue=""  ) String  volumen_rojo_adic
            ,@RequestParam( value = "observacion"         ,required=false, defaultValue=""  ) String  observacion
            ,@RequestParam( value = "estudios"            ,required=false, defaultValue=""  ) String  estudios
            ,@RequestParam( value=  "editando"            ,required=false, defaultValue=""  ) String  editando
            ,@RequestParam( value=  "edadEnMeses"         ,required=false, defaultValue=""  ) String  edadEnMeses
            ,@RequestParam( value=  "codigo_casa_PDCS"    ,required=false, defaultValue=""  ) String  codigo_casa_PDCS
            ,@RequestParam( value=  "codigo_casa_familia" ,required=false, defaultValue=""  ) String  codigo_casa_familia
            ,@RequestParam( value=  "idSerologia"         ,required=false, defaultValue=""  ) Integer  idSerologia
            ,@RequestParam( value=  "estado"              ,required=false, defaultValue=""  ) String  estado
    )throws Exception{
        try{
            Pbmc pbmc = new Pbmc();
            PbmcDto Responsedto = new PbmcDto();
            Integer codecasaP = (codigo_casa_PDCS.equals(""))?0:Integer.parseInt(codigo_casa_PDCS);
            //Integer casaPDCS  = codecasaP;
            Integer id_serologia = null;


            if (fecha.equals("")){
                Map<String, String> map = new HashMap<String, String>();
                map.put("msj", "Fecha no puede ser vacía.");
                return JsonUtil.createJsonResponse(map);
            }

            if (codigo_participante==0 || codigo_participante==null){
                Map<String, String> map = new HashMap<String, String>();
                map.put("msj", "Código participante no es válido.");
                return JsonUtil.createJsonResponse(map);
            }

            if (editando.equals("false")) {// Guardar nuevo Registro PBMC

                if (!this.pbmcService.ExistePbmc(DateUtil.StringToDate(fecha, "dd/MM/yyyy"), codigo_participante)) {
                    // METADATA PBMC
                    String nameComputer = InetAddress.getLocalHost().getHostName();
                    String nombreUsuario = SecurityContextHolder.getContext().getAuthentication().getName();
                    pbmc.setRecordUser(nombreUsuario);
                    pbmc.setDeviceid(nameComputer);
                    pbmc.setRecordDate(new Date());
                    pbmc.setEstado('1');
                    pbmc.setPasive('0');
                    pbmc.setCodigo_participante(codigo_participante);
                    pbmc.setEdadMeses(Integer.valueOf(edadEnMeses));
                    pbmc.setEnviado('0');
                    pbmc.setEstudios(estudios);
                    pbmc.setFecha_pbmc(DateUtil.StringToDate(fecha, "dd/MM/yyyy"));
                    String obs = (observacion != "")?observacion.toUpperCase().trim():"";
                    pbmc.setObservacion(obs);
                    double volumenpbmc = Double.parseDouble(volumen_pbmc);
                    pbmc.setVolumen(volumenpbmc);
                    pbmc.setNotas(estado);
                    pbmc.setCasaCHF(codigo_casa_familia);
                    pbmc.setCasaPDCS(codecasaP);
                    boolean lleva_volumen_Rojo;
                    if (volumen_rojo_adic !="0" || volumen_rojo_adic !=""){
                        pbmc.setPbmc_tiene_serologia('1');
                        lleva_volumen_Rojo =true;
                    }else {
                        pbmc.setPbmc_tiene_serologia('0');
                        lleva_volumen_Rojo = false;
                    }

                    boolean pbmcIsSaved = this.pbmcService.saveOrUpdatePbmc(pbmc);
                    if (pbmcIsSaved & lleva_volumen_Rojo) {
                            Double volumenRojoAdicional = Double.parseDouble(volumen_rojo_adic);
                            Serologia serologia = new Serologia();
                            //METADATA SEROLOGIA_ADICIONAL
                            serologia.setDeviceid(nameComputer);
                            serologia.setRecordDate(new Date());
                            serologia.setEstado('1');
                            serologia.setPasive('0');
                            serologia.setRecordUser(nombreUsuario);
                            serologia.setCasaPDCS(codecasaP);
                            serologia.setCasaCHF(codigo_casa_familia);
                            serologia.setCodigoPbmc(pbmc.getPbcm_id());
                            serologia.setEdadMeses(Integer.valueOf(edadEnMeses));
                            serologia.setEnviado('0');
                            serologia.setEstudio(estudios);
                            serologia.setFecha(DateUtil.StringToDate(fecha, "dd/MM/yyyy"));
                            serologia.setParticipante(codigo_participante);
                            serologia.setVolumen(volumenRojoAdicional);
                            serologia.setObservacion(obs);
                            serologia.setDescripcion("Pbmc");
                            this.serologiaService.saveSerologia(serologia);
                            id_serologia = serologia.getIdSerologia();
                    }else {
                        Map<String, String> map = new HashMap<String, String>();
                        map.put("msj", "Error al guardar registro de Pbmc.");
                        return JsonUtil.createJsonResponse(map);
                    }
                }else { //is  not  Saved
                    Map<String, String> map = new HashMap<String, String>();
                    map.put("msj", "Registro Pbmc ya existe.");
                    return JsonUtil.createJsonResponse(map);
                }
            }else {//Editando Registro de PBMC

                Pbmc pbmcToEdit = this.pbmcService.getPbmcByID(codigo_pbmc);
                if (pbmcToEdit != null) {
                    String obs = (observacion != "") ? observacion.toUpperCase().trim() : "";
                    pbmcToEdit.setObservacion(obs);
                    double volumenpbmc = Double.parseDouble(volumen_pbmc);
                    pbmcToEdit.setVolumen(volumenpbmc);
                    if (pbmcToEdit.getPbmc_tiene_serologia() == '1') {
                        Serologia serologiaToEdit = this.pbmcService.getSerologiaByPbmc_Id_And_Serologia_Id(pbmcToEdit.getPbcm_id(), idSerologia);
                        Double volumenRojoAdicional = Double.parseDouble(volumen_rojo_adic);
                        char tiene_sero_adic = (volumenRojoAdicional > 0) ? '1' : '0';
                        pbmcToEdit.setPbmc_tiene_serologia(tiene_sero_adic);
                        boolean guardoPbmc = this.pbmcService.saveOrUpdatePbmc(pbmcToEdit);
                        serologiaToEdit.setVolumen(volumenRojoAdicional);
                        serologiaToEdit.setFecha(DateUtil.StringToDate(fecha, "dd/MM/yyyy"));
                        serologiaToEdit.setObservacion(obs);
                        if (guardoPbmc)
                        this.serologiaService.saveSerologia(serologiaToEdit);
                        else
                            return JsonUtil.createJsonResponse("Error al Guardar Pbmc.");
                    }
                } else {
                    Map<String, String> map = new HashMap<String, String>();
                    map.put("msj", "Registro no encontrado!");
                    return JsonUtil.createJsonResponse(map);
                }
            }
            //Llenando DTO
            Responsedto.setCodigo_pbmc(pbmc.getPbcm_id());
            Responsedto.setId_serologia(id_serologia);
            Responsedto.setFecha(DateUtil.DateToString(pbmc.getFecha_pbmc(), "dd/MM/yyyy"));
            Responsedto.setVolumen_pbmc(String.valueOf(pbmc.getVolumen()));
            boolean haveRojo = (pbmc.getPbmc_tiene_serologia()=='1')?true:false;
            Responsedto.setTiene_rojo_adic(haveRojo);
            Responsedto.setVolumen_rojo_adic(volumen_rojo_adic);
            Responsedto.setEdadEnMeses(Double.parseDouble(edadEnMeses));
            return JsonUtil.createJsonResponse(Responsedto);
        }catch (Exception e){
            logger.error(e.getMessage());
            Gson gson = new Gson();
            String json = gson.toJson(e.toString());
            return  new ResponseEntity<String>( json, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }



    @RequestMapping( value="closeCase", method=RequestMethod.POST)
    public ResponseEntity<String> cerrarCaso( @RequestParam(value="pbmc_id", required=true ) Integer pbmc_id
                                             ,@RequestParam( value="message_razon", required=true, defaultValue="" ) String message_razon )
    {
        try{
            Pbmc casoExistente = this.pbmcService.getPbmcByID(pbmc_id);
            if (casoExistente!=null) {
                casoExistente.setObservacion(message_razon.toUpperCase());
                casoExistente.setPasive('1');
                this.pbmcService.saveOrUpdatePbmc(casoExistente);

                if (casoExistente.getPbmc_tiene_serologia()=='1') {
                    Serologia serologiaToPasivo = this.pbmcService.getSerologiaByPbmc_Id(casoExistente.getPbcm_id());
                    serologiaToPasivo.setPasive('1');
                    serologiaToPasivo.setObservacion(message_razon);
                    this.serologiaService.saveSerologia(serologiaToPasivo);
                }
            }
            return JsonUtil.createJsonResponse(casoExistente);
        }
        catch(Exception e){
            Gson gson = new Gson();
            String json = gson.toJson(e.toString());
            return new ResponseEntity<String>( json, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //listEnviosPbmc
    @RequestMapping(value = "/listEnviosPbmc", method = RequestMethod.GET, produces = "application/json")
    public String listEnviosPbmc(Model model)throws Exception{
        List<Pbmc> pbmc = this.pbmcService.ListaPbmcNoEnviadas();
        model.addAttribute("pbmc",pbmc);
        List<MessageResource> numero_envio = messageResourceService.getCatalogo("CAT_NUMERO_ENVIO_MUESTRA");
        model.addAttribute("numero_envio", numero_envio);
        return "/Pbmc/EnvioPbmc";
    }


    // Metodo para realizar envio de PBMC y SEROLOGIA **
    @RequestMapping(value = "/sendAllPbmc", method = RequestMethod.POST, produces = "application/json")
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
            List<Pbmc> ListaPbmcYaEnviadas = this.pbmcService.ObtenerPbmcEnviadas(fdesde, fhasta);
            if (ListaPbmcYaEnviadas.size()<=0)
                return JsonUtil.createJsonResponse("No se encontraron egistros: ".concat(""+ListaPbmcYaEnviadas.size()));


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
                if (ListaPbmcYaEnviadas.size()>0){
                    for (Pbmc obj: ListaPbmcYaEnviadas){
                        Pbmc_Detalle_Envio pbmc_detalle_envio= new Pbmc_Detalle_Envio();
                        Pbmc pbmc = this.pbmcService.getPbmcByID(obj.getPbcm_id());
                        pbmc.setEnviado('1');
                        this.pbmcService.saveOrUpdatePbmc(pbmc);//Actualizo el campo enviado a '1' (Si)
                        /*if (pbmc.getPbmc_tiene_serologia()=='1') {
                            Serologia serologiaDePbmc = this.pbmcService.getSerologiaByPbmc_Id(pbmc.getPbcm_id());
                            serologiaDePbmc.setEnviado('1');
                            this.serologiaService.saveSerologia(serologiaDePbmc);// Actualizo el campo enviado a '1' (Si)  en tabla serologia
                            Serologia_Detalle_Envio serologia_detalle_envio = new Serologia_Detalle_Envio();// creo obj de la tabla Serologia_detalle_envio
                            serologia_detalle_envio.setSerologia(serologiaDePbmc);
                            serologia_detalle_envio.setSerologiaEnvio(envio);// mismo envio de
                            this.serologiaService.save_Detalles_Serologia_Envio(serologia_detalle_envio);
                        }*/
                        pbmc_detalle_envio.setPbmc(pbmc);
                        pbmc_detalle_envio.setSerologiaEnvio(envio);
                        this.pbmcService.save_Detalles_Pbmc_Envio(pbmc_detalle_envio);// guardo el detalle del envio de PBMC
                    }
                }else {
                    return JsonUtil.createJsonResponse("Registros enviados: ".concat(""+ListaPbmcYaEnviadas.size()));
                }
            }
            return JsonUtil.createJsonResponse("Registros Enviados: "+ListaPbmcYaEnviadas.size());
        }catch (Exception ex){
            Gson gson = new Gson();
            String json = gson.toJson(ex.toString());
            return new ResponseEntity<String>( json, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //todo: esto es para cargar la tabla de PBMC con SEROLOGIA
    @RequestMapping(value = "/listSeroConPbmc", method = RequestMethod.GET)
    public String listSeroConPbmc(Model model)throws Exception{

        List<Serologia> pbmcConSerologia = this.pbmcService.getPbmcConSerologia();
        model.addAttribute("pbmcConSerologia",pbmcConSerologia);

        List<MessageResource> numero_envio = messageResourceService.getCatalogo("CAT_NUMERO_ENVIO_MUESTRA");
        model.addAttribute("numero_envio", numero_envio);
        return "/Pbmc/EnvioPbmcConSerologia";
    }


    @RequestMapping(value="/sendSerologiaConPbmc", method = RequestMethod.POST, produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody ResponseEntity<String>sendSerologiaConPbmc(@RequestParam(value = "", required = false)Integer numenvio
    ,@RequestParam(value = "desde", required = false) String desde
    ,@RequestParam(value = "hasta", required = false) String hasta
    ,@RequestParam(value = "fechaEnvio", required = false) String fechaEnvio
    ,@RequestParam(value = "horaEnvio", required = false) String horaEnvio
    ,@RequestParam(value = "temperatura", required = false) String temperatura
    )throws Exception{
        try {
            Date fdesde = DateUtil.StringToDate(desde + " 00:00:00", "dd/MM/yyyy HH:mm:ss");
            Date fhasta = DateUtil.StringToDate(hasta + " 23:59:59", "dd/MM/yyyy HH:mm:ss");
            List<Serologia> ListPbmcConSerologia = this.pbmcService.getSerologiaDePbmc(fdesde, fhasta);
            if (ListPbmcConSerologia.size()<=0)
                return JsonUtil.createJsonResponse("No se encontraron egistros: ".concat(""+ListPbmcConSerologia.size()));

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
            int contador = 0;
            if (envio.getIdserologiaenvio()!=null & ListPbmcConSerologia.size()>0){
                for (Serologia objSero: ListPbmcConSerologia){
                    Serologia serologiaDePbmc = this.pbmcService.getSerologiaByPbmc_Id(objSero.getCodigoPbmc());//obtener la serologia de pbmc para actualiza campo enviado a =1(Si)
                    serologiaDePbmc.setEnviado('1');
                    this.serologiaService.saveSerologia(serologiaDePbmc);// Actualizo el campo enviado a '1' (Si)  en tabla serologia
                    Serologia_Detalle_Envio serologia_detalle_envio = new Serologia_Detalle_Envio();// creo obj de la tabla Serologia_detalle_envio
                    serologia_detalle_envio.setSerologia(serologiaDePbmc);
                    serologia_detalle_envio.setSerologiaEnvio(envio);// mismo envio de
                    this.serologiaService.save_Detalles_Serologia_Envio(serologia_detalle_envio);
                    contador++;
                }
                return JsonUtil.createJsonResponse("Registros enviados: "+contador);
            }
            return JsonUtil.createJsonResponse("Registros enviados: "+contador);
        }catch (Exception e){
            Gson gson = new Gson();
            String json = gson.toJson(e.toString());
            return new ResponseEntity<String>( json, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
