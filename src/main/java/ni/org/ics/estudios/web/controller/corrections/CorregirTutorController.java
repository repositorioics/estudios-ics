package ni.org.ics.estudios.web.controller.corrections;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import ni.org.ics.estudios.domain.CartaConsentimiento;
import ni.org.ics.estudios.domain.Participante;
import ni.org.ics.estudios.domain.audit.CorrectionsTrail;
import ni.org.ics.estudios.domain.muestreoanual.ParticipanteProcesos;
import ni.org.ics.estudios.language.MessageResource;
import ni.org.ics.estudios.service.CartaConsentimientoService;
import ni.org.ics.estudios.service.MessageResourceService;
import ni.org.ics.estudios.service.ParticipanteService;
import ni.org.ics.estudios.service.corrections.CorrectionsTrailService;
import ni.org.ics.estudios.service.muestreoanual.ParticipanteProcesosService;
import ni.org.ics.estudios.web.utils.DateUtil;
import ni.org.ics.estudios.web.utils.JsonUtil;
import ni.org.ics.estudios.web.utils.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.util.*;

/**
 * Created by miguel on 18/1/2022.
 */
@Controller
@RequestMapping("/correcion/tutor")
public class CorregirTutorController {

    private static final Logger logger = LoggerFactory.getLogger(CorregirTutorController.class);

    @Resource(name = "participanteService")
    private ParticipanteService participanteService;

    @Resource(name = "participanteProcesosService")
    private ParticipanteProcesosService participanteProcesosService;

    @Resource(name = "messageResourceService")
    private MessageResourceService messageResourceService;

    @Resource(name = "correctionsTrailService")
    private CorrectionsTrailService correctionsTrailService;

    @Resource(name = "cartaConsentimientoService")
    private CartaConsentimientoService cartaConsentimientoService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String obtenerEntidades(Model model) throws ParseException {
        logger.debug("Cargando inicio correccion tutor en JSP");
        List<MessageResource> relFam = messageResourceService.getCatalogo("CP_CAT_RFTUTOR");
        model.addAttribute("relFam", relFam);
        return "corrections/tutor";
    }

    @RequestMapping(value = "/buscar", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    ResponseEntity<String> buscarParticipante(@RequestParam(value="parametro", required=true ) Integer codigo) throws ParseException {
        logger.debug("buscar participante");
        Map<String, String> map = new HashMap<String, String>();
        Participante participante = this.participanteService.getParticipanteByCodigo(codigo);

        if (participante!=null) {
            ParticipanteProcesos procesos = this.participanteProcesosService.getParticipante(codigo);
            MessageResource relFam = messageResourceService.getMensajeByCatalogAndCatKey("CP_CAT_RFTUTOR", participante.getRelacionFamiliarTutor());
            if (procesos != null && procesos.getEstPart().equals(0))
                return JsonUtil.createJsonResponse("Participante retirado");
            else {
                map.put("codigo", participante.getCodigo().toString());
                map.put("nombre", participante.getNombreCompleto());
                map.put("fechaNac", DateUtil.DateToString(participante.getFechaNac(), "dd/MM/yyyy"));
                map.put("edad", participante.getEdad());
                map.put("sexo", participante.getSexo());
                map.put("nombre1ActTutor", participante.getNombre1Tutor());
                map.put("nombre2ActTutor", participante.getNombre2Tutor());
                map.put("apellido1ActTutor", participante.getApellido1Tutor());
                map.put("apellido2ActTutor", participante.getApellido2Tutor());
                //map.put("relacionFamActKey", relFam.getCatKey()); aqui está reventando!!
                //map.put("relacionFamAct", relFam.getSpanish());
            }
        }
        else
            return JsonUtil.createJsonResponse("No se encontró participante según el código ingresado");

        return JsonUtil.createJsonResponse(map);
    }

    @RequestMapping(value = "/guardar", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = "application/json")
    protected void save(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        logger.debug("Guardando correcciones");
        String resultado = "";
        String error = "";
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream(), "UTF8"));
            String json = br.readLine();

            List<CorrectionsTrail> listaCorrecciones = new ArrayList<CorrectionsTrail>();
            //Recuperando Json enviado desde el cliente
            JsonObject jObjectRespuestas = new Gson().fromJson(json, JsonObject.class);
            Integer codigoParticipante = jObjectRespuestas.get("codigoParticipante").getAsInt();
            String observacion = jObjectRespuestas.get("observacion").getAsString();
            String claseParticipante = Participante.class.getSimpleName();
            String claseCarta = CartaConsentimiento.class.getSimpleName();

            Participante participante = participanteService.getParticipanteByCodigo(codigoParticipante);
            CartaConsentimiento cartaConsentimiento = cartaConsentimientoService.getCartaConsentimientoByParticipante(codigoParticipante);


            String nombre1ActTutor = participante.getNombre1Tutor();
            String nombre2ActTutor = participante.getNombre2Tutor();
            String apellido1ActTutor = participante.getApellido1Tutor();
            String apellido2ActTutor = participante.getApellido2Tutor();
            String relacionFamAct = participante.getRelacionFamiliarTutor();

            String nombre1NuevoTutor = jObjectRespuestas.get("nombre1NuevoTutor").getAsString();
            String nombre2NuevoTutor = jObjectRespuestas.get("nombre2NuevoTutor").getAsString();
            String apellido1NuevoTutor = jObjectRespuestas.get("apellido1NuevoTutor").getAsString();
            String apellido2NuevoTutor = jObjectRespuestas.get("apellido2NuevoTutor").getAsString();
            String relacionFamNuevo = jObjectRespuestas.get("relacionFamNuevo").getAsString();

            if (StringUtil.sonValoresDiferentes(nombre1ActTutor, nombre1NuevoTutor)) {

                listaCorrecciones.add(fillCorrection(claseParticipante, participante.getCodigo().toString(), "NOMBRE1_TUTOR", nombre1ActTutor, nombre1NuevoTutor, observacion));
                listaCorrecciones.add(fillCorrection(claseCarta, cartaConsentimiento.getCodigo().toString(), "NOMBRE1_TUTOR", nombre1ActTutor, nombre1NuevoTutor, observacion));

                participante.setNombre1Tutor(nombre1NuevoTutor);
                cartaConsentimiento.setNombre1Tutor(nombre1NuevoTutor);

            }
            if (StringUtil.sonValoresDiferentes(nombre2ActTutor, nombre2NuevoTutor)) {

                listaCorrecciones.add(fillCorrection(claseParticipante, participante.getCodigo().toString(), "NOMBRE2_TUTOR", nombre2ActTutor, nombre2NuevoTutor, observacion));
                listaCorrecciones.add(fillCorrection(claseCarta, cartaConsentimiento.getCodigo(), "NOMBRE2_TUTOR", nombre2ActTutor, nombre2NuevoTutor, observacion));

                participante.setNombre2Tutor(nombre2NuevoTutor);
                cartaConsentimiento.setNombre2Tutor(nombre2NuevoTutor);

            }
            if (StringUtil.sonValoresDiferentes(apellido1ActTutor, apellido1NuevoTutor)) {

                listaCorrecciones.add(fillCorrection(claseParticipante, participante.getCodigo().toString(), "APELLIDO1_TUTOR", apellido1ActTutor, apellido1NuevoTutor, observacion));
                listaCorrecciones.add(fillCorrection(claseCarta, cartaConsentimiento.getCodigo(), "APELLIDO1_TUTOR", apellido1ActTutor, apellido1NuevoTutor, observacion));

                participante.setApellido1Tutor(apellido1NuevoTutor);
                cartaConsentimiento.setApellido1Tutor(apellido1NuevoTutor);

            }
            if (StringUtil.sonValoresDiferentes(apellido2ActTutor, apellido2NuevoTutor)) {

                listaCorrecciones.add(fillCorrection(claseParticipante ,participante.getCodigo().toString(), "APELLIDO2_TUTOR", apellido2ActTutor, apellido2NuevoTutor, observacion));
                listaCorrecciones.add(fillCorrection(claseCarta ,cartaConsentimiento.getCodigo(), "APELLIDO2_TUTOR", apellido2ActTutor, apellido2NuevoTutor, observacion));

                participante.setApellido2Tutor(apellido2NuevoTutor);
                cartaConsentimiento.setApellido2Tutor(apellido2NuevoTutor);

            }
            if (StringUtil.sonValoresDiferentes(relacionFamAct, relacionFamNuevo)) {

                listaCorrecciones.add(fillCorrection(claseParticipante, participante.getCodigo().toString(), "RELACION_FAMILIAR", relacionFamAct, relacionFamNuevo, observacion));
                listaCorrecciones.add(fillCorrection(claseCarta, cartaConsentimiento.getCodigo(), "RELACION_FAMILIAR", relacionFamAct, relacionFamNuevo, observacion));

                participante.setRelacionFamiliarTutor(relacionFamNuevo);
                cartaConsentimiento.setRelacionFamiliarTutor(relacionFamNuevo);

            }

            this.participanteService.saveOrUpdateParticipante(participante);
            this.cartaConsentimientoService.saveOrUpdateCartaConsentimiento(cartaConsentimiento);
            this.correctionsTrailService.saveCorrectionsTrailList(listaCorrecciones);

            resultado = String.format(messageResourceService.getMensaje("msg.correction.tutor.success").getSpanish(), codigoParticipante);

        }catch (Exception ex) {
            logger.error(ex.getMessage(),ex);
            ex.printStackTrace();
            error = messageResourceService.getMensaje("msg.correction.tutor.error").getSpanish();
            error=error+". \n "+ex.getMessage();
        } finally {
            Map<String, String> map = new HashMap<String, String>();
            map.put("mensaje",resultado);
            map.put("error",error);
            String jsonResponse = new Gson().toJson(map);
            response.getOutputStream().write(jsonResponse.getBytes());
            response.getOutputStream().close();
        }

    }

    private CorrectionsTrail fillCorrection(String entidad, String id, String propiedad, String valorAnterior, String valorNuevo, String observacion){
        CorrectionsTrail correctionsTrail = new CorrectionsTrail();
        correctionsTrail.setEntityName(entidad);
        correctionsTrail.setEntityId(id);
        correctionsTrail.setOperationDate(new Date());
        correctionsTrail.setUsername(SecurityContextHolder.getContext().getAuthentication().getName());
        correctionsTrail.setEntityProperty(propiedad);
        correctionsTrail.setEntityPropertyOldValue(valorAnterior);
        correctionsTrail.setEntityPropertyNewValue(valorNuevo);
        correctionsTrail.setObservacion(observacion);
        return correctionsTrail;
    }

}
