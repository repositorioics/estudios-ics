package ni.org.ics.estudios.web.controller.corrections;

import com.google.gson.Gson;
import ni.org.ics.estudios.domain.Participante;
import ni.org.ics.estudios.domain.muestreoanual.ParticipanteProcesos;
import ni.org.ics.estudios.dto.DatosParticipante;
import ni.org.ics.estudios.language.MessageResource;
import ni.org.ics.estudios.service.MessageResourceService;
import ni.org.ics.estudios.service.ParticipanteService;
import ni.org.ics.estudios.service.muestreoanual.ParticipanteProcesosService;
import ni.org.ics.estudios.web.utils.DateUtil;
import ni.org.ics.estudios.web.utils.JsonUtil;
import org.apache.commons.lang3.text.translate.UnicodeEscaper;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by lserrano on 06/06/2023.
 */
@Controller
@RequestMapping("/CorrectionParticipant")
public class CorregirParticipante {

    @Resource(name = "messageResourceService")
    private MessageResourceService messageResourceService;

    @Resource(name = "participanteService")
    private ParticipanteService participanteService;

    @Resource(name = "participanteProcesosService")
    private ParticipanteProcesosService participanteProcesosService;


    // CorrectionParticipant/participantForm
    @RequestMapping(value = "/participantForm", method = RequestMethod.GET)
    public String Crear(ModelMap model) throws Exception {
        try {
            List<MessageResource> catSiNo = messageResourceService.getCatalogo("CHF_CAT_SINO");
            model.addAttribute("catSiNo", catSiNo);
            List<MessageResource> relFam = messageResourceService.getCatalogo("CP_CAT_RFTUTOR");
            model.addAttribute("relFam", relFam);
            return "/corrections/participante";
        } catch (Exception e) {
            return "404";
        }
    }

    /*(Solicitado por Brenda y Jairo)
    * Edición de datos de participantes
    * (Nombres y Apellidos de participantes, padre, madre, tutor, relación familiar de tutor, fecha de nacimiento y sexo).
    */

    // Metodo para Buscar por código de Participante /CorrectionParticipant/GetCartasParticipante
    @RequestMapping(value = "/GetCartasParticipante", method = RequestMethod.GET, produces = "application/json")
    public
    @ResponseBody
    ResponseEntity<String> getParticipante(@RequestParam(value = "parametro", required = true) Integer parametro){
        try {
            // realizar la busqueda del participante
            Participante participante = this.participanteService.getParticipanteByCodigo(parametro);
            if (participante != null){
                ParticipanteProcesos procesos = this.participanteProcesosService.getParticipante(participante.getCodigo());
                DatosParticipante datosParticipante = new DatosParticipante();
                datosParticipante.setCodigo(participante.getCodigo());
                datosParticipante.setNombre1(participante.getNombre1());
                datosParticipante.setNombre2(participante.getNombre2());
                datosParticipante.setApellido1(participante.getApellido1());
                datosParticipante.setApellido2(participante.getApellido2());
                //padre
                datosParticipante.setNombre1Padre(participante.getNombre1Padre());
                datosParticipante.setNombre2Padre(participante.getNombre2Padre());
                datosParticipante.setApellido1Padre(participante.getApellido1Padre());
                datosParticipante.setApellido2Padre(participante.getApellido2Padre());
                //Madre
                datosParticipante.setNombre1Madre(participante.getNombre1Madre());
                datosParticipante.setNombre2Madre(participante.getNombre2Madre());
                datosParticipante.setApellido1Madre(participante.getApellido1Madre());
                datosParticipante.setApellido2Madre(participante.getApellido2Madre());
                //tutor
                datosParticipante.setNombre1Tutor(participante.getNombre1Tutor());
                datosParticipante.setNombre2Tutor(participante.getNombre2Tutor());
                datosParticipante.setApellido1Tutor(participante.getApellido1Tutor());
                datosParticipante.setApellido2Tutor(participante.getApellido2Tutor());
                datosParticipante.setRelacionFamTutor(participante.getRelacionFamiliarTutor());
                datosParticipante.setFechaNac(participante.getFechaNac());
                datosParticipante.setSexo(participante.getSexo());
                datosParticipante.setEstPart(procesos.getEstPart());
                return JsonUtil.createJsonResponse(datosParticipante);
            }else{
                return JsonUtil.createJsonResponse("Participante no encontrado");
            }
        } catch (Exception e) {
            Gson gson = new Gson();
            String json = gson.toJson(e.toString());
            return new ResponseEntity<String>(json, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @RequestMapping(value="updateParticipante", method=RequestMethod.POST)
    public ResponseEntity<String>updateParticipante(
              @RequestParam( value="codigo", required=true ) int codigo
            , @RequestParam( value="nombre1", required=true ) String nombre1
            , @RequestParam( value="nombre2", required=false ) String nombre2
            , @RequestParam( value="apellido1", required=true ) String apellido1
            , @RequestParam( value="apellido2", required=false ) String apellido2

            , @RequestParam( value="nombre1Madre", required=true ) String nombre1Madre
            , @RequestParam( value="nombre2Madre", required=false ) String nombre2Madre
            , @RequestParam(value = "apellido1Madre", required=true) String apellido1Madre
            , @RequestParam( value="apellido2Madre", required=false ) String apellido2Madre

            , @RequestParam( value="nombre1Padre", required=true ) String nombre1Padre
            , @RequestParam( value="nombre2Padre", required=false ) String nombre2Padre
            , @RequestParam( value="apellido1Padre", required=true ) String apellido1Padre
            , @RequestParam( value="apellido2Padre", required=false ) String apellido2Padre

            , @RequestParam( value="nombre1Tutor", required=true ) String nombre1Tutor
            , @RequestParam( value="nombre2Tutor", required=false ) String nombre2Tutor
            , @RequestParam( value="apellido1Tutor", required=true ) String apellido1Tutor
            , @RequestParam( value="apellido2Tutor", required=false ) String apellido2Tutor

            , @RequestParam( value="fechaNac", required=true ) String fechaNac
            , @RequestParam( value="relfam", required=true ) String relfam
            , @RequestParam( value="sexo", required=true ) String sexo
    ){
        try{

            if (relfam==null || relfam ==""){
                return JsonUtil.createJsonResponse("Seleciona relacion familiar.");
            }

            if(sexo=="" || sexo==null){
                return JsonUtil.createJsonResponse("Seleciona el sexo del participante.");
            }

            Participante participante = this.participanteService.getParticipanteByCodigo(codigo);
            if(participante!=null){
                participante.setCodigo(participante.getCodigo());
                participante.setNombre1(nombre1.toUpperCase());
                String np2 = (nombre2=="") ? "":nombre2.toUpperCase();
                participante.setNombre2(np2);
                participante.setApellido1(apellido1.toUpperCase());
                String ap2 = (apellido2=="")?"":apellido2.toUpperCase();
                participante.setApellido2(ap2);

                participante.setNombre1Madre(nombre1Madre.toUpperCase());
                String n1m = (nombre2Madre=="")?"":nombre2Madre.toUpperCase();
                participante.setNombre2Madre(n1m);
                participante.setApellido1Madre(apellido1Madre.toUpperCase());
                String a2m = (apellido2Madre=="")?"":apellido2Madre.toUpperCase();
                participante.setApellido2Madre(a2m);

                participante.setNombre1Padre(nombre1Padre.toUpperCase());
                String n2p = (nombre2Padre=="")?"":nombre2Padre.toUpperCase();
                participante.setNombre2Padre(n2p);
                participante.setApellido1Padre(apellido1Padre.toUpperCase());
                String a2p = (apellido2Padre=="")?"":apellido2Padre.toUpperCase();
                participante.setApellido2Padre(a2p);

                participante.setNombre1Tutor(nombre1Tutor.toUpperCase());
                String n2t = (nombre2Tutor =="")?"":nombre2Tutor.toUpperCase();
                participante.setNombre2Tutor(n2t);
                participante.setApellido1Tutor(apellido1Tutor.toUpperCase());
                String a2t = (apellido2Tutor=="")?"":apellido2Tutor.toUpperCase();
                participante.setApellido2Tutor(a2t);
                participante.setRelacionFamiliarTutor(relfam);

                participante.setFechaNac(DateUtil.StringToDate(fechaNac, "dd/MM/yyyy"));
                participante.setSexo(sexo);
                this.participanteService.saveOrUpdateParticipante(participante);
            }
            return createJsonResponse(participante);
        } catch (Exception e) {
            Gson gson = new Gson();
            String json = gson.toJson(e.toString());
            return new ResponseEntity<String>(json, HttpStatus.INTERNAL_SERVER_ERROR);
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
