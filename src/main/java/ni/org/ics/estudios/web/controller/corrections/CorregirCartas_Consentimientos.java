package ni.org.ics.estudios.web.controller.corrections;

import com.google.gson.Gson;
import ni.org.ics.estudios.domain.CartaConsentimiento;
import ni.org.ics.estudios.domain.Participante;
import ni.org.ics.estudios.domain.muestreoanual.ParticipanteProcesos;
import ni.org.ics.estudios.dto.BuscaParticipanteForCarta;
import ni.org.ics.estudios.dto.EstudioDto;
import ni.org.ics.estudios.dto.ParticipantesCodigo;
import ni.org.ics.estudios.language.MessageResource;
import ni.org.ics.estudios.service.MessageResourceService;
import ni.org.ics.estudios.service.corrections.CartaConsentimientoService;
import ni.org.ics.estudios.service.hemodinanicaService.DatoshemodinamicaService;
import ni.org.ics.estudios.service.muestreoanual.ParticipanteProcesosService;
import ni.org.ics.estudios.web.utils.DateUtil;
import ni.org.ics.estudios.web.utils.JsonUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by PC on 15/11/2022.
 */
@Controller
@RequestMapping("/correction")
public class CorregirCartas_Consentimientos {

    @Resource(name = "datoshemodinamicaService")
    private DatoshemodinamicaService datoshemodinamicaService;

    @Resource(name = "participanteProcesosService")
    private ParticipanteProcesosService participanteProcesosService;
    @Resource(name = "cartaConsentimientoCorrectionService")
    private CartaConsentimientoService cartaConsentimientoCorrectionService;
    @Resource(name = "messageResourceService")
    private MessageResourceService messageResourceService;

    @RequestMapping(value = "/CorrectionForm", method = RequestMethod.GET)
    public String Crear(ModelMap model) throws Exception {
        try {
            List<MessageResource> catSiNo = messageResourceService.getCatalogo("CHF_CAT_SINO");
            model.addAttribute("catSiNo", catSiNo);
            List<MessageResource> relFam = messageResourceService.getCatalogo("CP_CAT_RFTUTOR");
            model.addAttribute("relFam", relFam);
            return "/corrections/cartas_consentimiento";
        } catch (Exception e) {
            return "404";
        }
    }

    //Realiza búsqueda de participante para editar una Cartas en el FORMULARIO
    @RequestMapping(value = "/searchParticipant", method = RequestMethod.GET, produces = "application/json")
    public
    @ResponseBody
    ResponseEntity<String> BuscarParticipanteByID(@RequestParam(value = "parametro", required = true) Integer parametro) throws Exception {
        try {
            Map<String, String> map = new HashMap<String, String>();
            List<CartaConsentimiento> cartasCartaConsentimientoList = new ArrayList<CartaConsentimiento>();
            Participante participante = datoshemodinamicaService.getParticipante(parametro);
            if (participante != null) {
                cartasCartaConsentimientoList = this.cartaConsentimientoCorrectionService.getConsentimientoByCodeParticipante(parametro);
                return JsonUtil.createJsonResponse(cartasCartaConsentimientoList);
            }
            return JsonUtil.createJsonResponse("No se encontro informacion del Participante!!");
        } catch (Exception e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //correction/VerConsentimiento
    @RequestMapping(value = "/VerConsentimiento", method = RequestMethod.GET, produces = "application/json")
    public
    @ResponseBody
    CartaConsentimiento VerConsentimiento(@RequestParam(value = "codigo") String codigo) throws Exception {
        CartaConsentimiento obj = null;
        try {
            obj = this.cartaConsentimientoCorrectionService.getByCartaConsentimientoId(codigo);
            return obj;
        } catch (Exception e) {
            return null;
        }
    }


    //region todo: Metodo para Guardar saveCarta
    @RequestMapping(value = "/saveCarta", method = RequestMethod.POST, produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody ResponseEntity<String> saveCarta(@RequestParam(value = "codigo", defaultValue = "") String codigo,
                                                          @RequestParam(value = "participanteCode", defaultValue = "") String participanteCode,
                                                          @RequestParam(value = "testigoPresente", defaultValue = "") String testigoPresente,
                                                          @RequestParam(value = "nombre1Testigo", defaultValue = "") String nombre1Testigo,
                                                          @RequestParam(value = "nombre2Testigo", defaultValue = "") String nombre2Testigo,
                                                          @RequestParam(value = "apellido1Testigo", defaultValue = "") String apellido1Testigo,
                                                          @RequestParam(value = "apellido2Testigo", defaultValue = "") String apellido2Testigo,
                                                          @RequestParam(value = "mismoTutor", defaultValue = "") String mismoTutor,
                                                          @RequestParam(value = "NombreTutor1", defaultValue = "") String NombreTutor1,
                                                          @RequestParam(value = "NombreTutor2", defaultValue = "") String NombreTutor2,
                                                          @RequestParam(value = "ApellidoTutor1", defaultValue = "") String ApellidoTutor1,
                                                          @RequestParam(value = "ApellidoTutor2", defaultValue = "") String ApellidoTutor2,
                                                          @RequestParam(value = "relacionFamiliarTutor", defaultValue = "") String relacionFamiliarTutor,
                                                          @RequestParam(value = "fechaCarta", defaultValue = "") String fechaCarta,
                                                          @RequestParam(value = "contactoFuturo", defaultValue = "") String contactoFuturo,
                                                          @RequestParam(value = "parteA", defaultValue = "") String parteA,
                                                          @RequestParam(value = "parteB", defaultValue = "") String parteB,
                                                          @RequestParam(value = "parteC", defaultValue = "") String parteC,
                                                          @RequestParam(value = "parteD", defaultValue = "") String parteD,
                                                          @RequestParam(value = "parteE", defaultValue = "") String parteE,
                                                          @RequestParam(value = "parteF", defaultValue = "") String parteF,
                                                          @RequestParam(value = "parteG", defaultValue = "") String parteG) {
        try{
            CartaConsentimiento cartaConsentimiento = this.cartaConsentimientoCorrectionService.getByCartaConsentimientoId(codigo);
            if (cartaConsentimiento!=null){

                cartaConsentimiento.setCodigo(codigo);

                String testigoPresent = (testigoPresente.equals("on"))?"1":null;
                cartaConsentimiento.setTestigoPresente(testigoPresent);

                String N1T = (nombre1Testigo!="")?nombre1Testigo.toUpperCase().trim():null;
                cartaConsentimiento.setNombre1Testigo(N1T);

                String N2T = (nombre2Testigo!="")?nombre2Testigo.toUpperCase().trim():null;
                cartaConsentimiento.setNombre2Testigo(N2T);

                String A1T = (apellido1Testigo!="")?apellido1Testigo.toUpperCase().trim():null;
                cartaConsentimiento.setApellido1Testigo(A1T);

                String A2T = (apellido2Testigo!="")?apellido2Testigo.toUpperCase().trim():null;
                cartaConsentimiento.setApellido2Testigo(A2T);

                String misTutor = (mismoTutor.equals("on"))?"1":null;
                cartaConsentimiento.setMismoTutor(misTutor);

                String nt1 = (NombreTutor1!="")?NombreTutor1.toUpperCase().trim():null;
                cartaConsentimiento.setNombre1Tutor(nt1);

                String nt2 = (NombreTutor2!="")?NombreTutor2.toUpperCase().trim():null;
                cartaConsentimiento.setNombre2Tutor(nt2);

                String at1 = (ApellidoTutor1!="")?ApellidoTutor1.toUpperCase().trim():null;
                cartaConsentimiento.setApellido1Tutor(at1);

                String at2 = (ApellidoTutor2!="")?ApellidoTutor2.toUpperCase().trim():null;
                cartaConsentimiento.setApellido2Tutor(at2);

                cartaConsentimiento.setRelacionFamiliarTutor(relacionFamiliarTutor);
                cartaConsentimiento.setFechaFirma(DateUtil.StringToDate(fechaCarta, "dd/MM/yyyy"));

                String contact = (contactoFuturo!="")?contactoFuturo:null;
                cartaConsentimiento.setAceptaContactoFuturo(contact);

                if (parteA !=""){
                    cartaConsentimiento.setAceptaParteA(parteA);
                }
                if (parteB != ""){
                    cartaConsentimiento.setAceptaParteB(parteB);
                }
                if(parteC != ""){
                    cartaConsentimiento.setAceptaParteC(parteC);
                }
                if (parteD != ""){
                    cartaConsentimiento.setAceptaParteD(parteD);
                }
                if (parteE!=""){
                    cartaConsentimiento.setAceptaParteE(parteE);
                }
                if(parteF!=""){
                    cartaConsentimiento.setAceptaParteF(parteF);
                }
                if(parteG!=""){
                    cartaConsentimiento.setAceptaParteG(parteG);
                }
                this.cartaConsentimientoCorrectionService.updateConsentimiento(cartaConsentimiento);
                return JsonUtil.createJsonResponse(cartaConsentimiento);
            }else{
                return JsonUtil.createJsonResponse("Registro no encontrado!");
            }
        }catch (Exception e){
            Gson gson = new Gson();
            String json = gson.toJson(e.toString());
            return new ResponseEntity<String>(json, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    //region todo Buscar Por Nombres y Apellidos
    @RequestMapping(value = "/getNombre1", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    List<String> getNombre1(@RequestParam(value = "nombre1", required = true) String nombre1)
            throws Exception {
        ArrayList<String> nombreArrayList = null;
        try {
            List<String> listaPart = this.cartaConsentimientoCorrectionService.getNombre1(nombre1);
            return listaPart;
        }catch (Exception e){
            return nombreArrayList = null;
        }
    }
    @RequestMapping(value = "/getNombre2", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    List<String> getNombre2(@RequestParam(value = "nombre2", required = true) String nombre2)
            throws Exception {
        ArrayList<String> nombreArrayList = null;
        try {
            List<String> listaPart = this.cartaConsentimientoCorrectionService.getNombre2(nombre2);
            return listaPart;
        }catch (Exception e){
            return nombreArrayList = null;
        }
    }


    @RequestMapping(value = "/getApellido1", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    List<String> getApellido1(@RequestParam(value = "apellido1", required = true) String apellido1)
            throws Exception {
        ArrayList<String> nombreArrayList = null;
        try {
            List<String> listaPart = this.cartaConsentimientoCorrectionService.getApellido1(apellido1);
            return listaPart;
        }catch (Exception e){
            return nombreArrayList = null;
        }
    }


    @RequestMapping(value = "/getApellido2", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    List<String> getApellido2(@RequestParam(value = "apellido2", required = true) String apellido2)
            throws Exception {
        ArrayList<String> nombreArrayList = null;
        try {
            List<String> listaPart = this.cartaConsentimientoCorrectionService.getApellido2(apellido2);
            return listaPart;
        }catch (Exception e){
            return nombreArrayList = null;
        }
    }

//endregion
}
