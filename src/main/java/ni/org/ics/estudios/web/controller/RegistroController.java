package ni.org.ics.estudios.web.controller;

import com.google.gson.Gson;
import ni.org.ics.estudios.domain.Participante;
import ni.org.ics.estudios.domain.Retiros.Retiros;
import ni.org.ics.estudios.domain.catalogs.Estudio;
import ni.org.ics.estudios.domain.catalogs.Razones_Retiro;
import ni.org.ics.estudios.domain.muestreoanual.ParticipanteProcesos;
import ni.org.ics.estudios.dto.DatosParticipante;
import ni.org.ics.estudios.dto.HistorialRetiroDto;
import ni.org.ics.estudios.dto.ParticipantesEnCasa;
import ni.org.ics.estudios.dto.RetiroDto;
import ni.org.ics.estudios.service.MessageResourceService;
import ni.org.ics.estudios.service.ParticipanteService;
import ni.org.ics.estudios.service.hemodinanicaService.DatoshemodinamicaService;
import ni.org.ics.estudios.service.muestreoanual.ParticipanteProcesosService;
import ni.org.ics.estudios.service.retiro.RetiroService;
import ni.org.ics.estudios.web.utils.DateUtil;
import ni.org.ics.estudios.web.utils.JsonUtil;
import org.apache.commons.lang3.text.translate.UnicodeEscaper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by ICS on 17/08/2020.
 */
@Controller
@RequestMapping("/Registro")
public class RegistroController {

    private static final Logger logger = LoggerFactory.getLogger(RegistroController.class);

    /* Instancia de mi Servicio Mensajes */
    @Resource(name = "messageResourceService")
    private MessageResourceService messageResourceService;

    @Resource(name = "participanteService")
    private ParticipanteService participanteService;

    @Resource(name = "participanteProcesosService")
    private ParticipanteProcesosService participanteProcesosService;

    /* Instancia de mi Servicio Hemodinamico */
    @Resource(name = "datoshemodinamicaService")
    private DatoshemodinamicaService datoshemodinamicaService;

    @Resource(name = "RetiroService")
    private RetiroService retiroservice;

    @RequestMapping(value = "/BuscarInfor", method = RequestMethod.GET)
    public String BuscarInfor(Model model)throws Exception{

        return "/Registro/VerRegistro";
    }

    @RequestMapping(value = "/searchParticipant", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    ResponseEntity<String> BuscarParticipanteByID(@RequestParam(value = "parametro", required = true) Integer parametro)
            throws Exception {
        try {
            Map<String, String> map = new HashMap<String, String>();
            Participante participante = datoshemodinamicaService.getParticipante(parametro);
            ParticipanteProcesos procesos = participanteProcesosService.getParticipante(parametro);
            HistorialRetiroDto historialDTO = new HistorialRetiroDto();
            historialDTO.setCodigo_participante(participante.getCodigo());
            historialDTO.setCodigoCasa(participante.getCasa().getCodigo());
            historialDTO.setCodigoCasaFamilia(procesos.getCasaCHF());
            String parti = participante.getNombre1().toUpperCase();
            if (participante.getNombre2() != null)
                parti = parti + " " + participante.getNombre2().toUpperCase();
            parti = parti + " " + participante.getApellido1().toUpperCase();
            if (participante.getApellido2() != null)
                parti = parti + " " + participante.getApellido2().toUpperCase();
            historialDTO.setNombreCompletoParticipante(parti);
            historialDTO.setSexo(participante.getSexo());
            String fecha = DateUtil.DateToString(participante.getFechaNac(), "dd/MM/yyyy");
            historialDTO.setFechaNac(fecha);
            historialDTO.setEdad(participante.getEdad());
            historialDTO.setDireccion(participante.getCasa().getDireccion());
            historialDTO.setNombreBarrio(participante.getCasa().getBarrio().getNombre());
            historialDTO.setJefe(participante.getCasa().getNombre1JefeFamilia()+" "+participante.getCasa().getNombre2JefeFamilia()+" "+participante.getCasa().getApellido1JefeFamilia()+" " +participante.getCasa().getApellido2JefeFamilia());
            String padre = participante.getNombre1Padre().toUpperCase();
            if (participante.getNombre2Padre() != null)
                padre = padre + " " + participante.getNombre2Padre().toUpperCase();
            padre = padre + " " + participante.getApellido1Padre().toUpperCase();
            if (participante.getApellido2Padre() != null)
                padre = padre + " " + participante.getApellido2Padre().toUpperCase();
            historialDTO.setNombreCompletoPadre(padre);
            String madre = participante.getNombre1Madre().toUpperCase();
            if (participante.getNombre2Madre() != null)
                madre = madre + " " + participante.getNombre2Madre().toUpperCase();
            madre = madre + " " + participante.getApellido1Madre().toUpperCase();
            if (participante.getApellido2Madre() != null)
                madre = madre + " " + participante.getApellido2Madre().toUpperCase();
            historialDTO.setNombreCompletoMadre(madre);
            historialDTO.setTutor(participante.getTutor());
            historialDTO.setRelacionFamTutor(participante.getRelacionFamiliarTutor());
            historialDTO.setEstudios(procesos.getEstudio());
            historialDTO.setIsPbmc(procesos.getPbmc());
            historialDTO.setIsPaxgene(procesos.getPaxgene());
            historialDTO.setPesotalla(procesos.getPesoTalla());
            historialDTO.setLactMat(procesos.getEncLacMat());
            historialDTO.setEncPart(procesos.getEncPart());
            historialDTO.setConsFlu(procesos.getConsFlu());
            historialDTO.setConsDeng(procesos.getConsDeng());
            historialDTO.setVacunas(procesos.getInfoVacuna());
            historialDTO.setEncCasaCoh(procesos.getEnCasa());// aqui encuesta casa cohorte pediátrica
            historialDTO.setEncCHF(procesos.getEnCasaChf());
            historialDTO.setEncCasaSa(procesos.getEnCasaSa());
            historialDTO.setEstPart(procesos.getEstPart());
            historialDTO.setManzana(participante.getCasa().getManzana());
            historialDTO.setCodigoCasaFamilia(procesos.getCasaCHF());
            List<RetiroDto>addobj = new ArrayList<RetiroDto>();
            // Trabajar la Lista del DTO.
            List<Retiros> listRetiroByParticipantId = retiroservice.getListadoRetiroByID(parametro);
            for(Retiros auxiliar : listRetiroByParticipantId){
                RetiroDto retirodto = new RetiroDto();
                retirodto.setIdretiro(auxiliar.getIdretiro());
                retirodto.setCodigocasapdcs(auxiliar.getCodigocasapdcs());
                retirodto.setCodigocasafamilia(auxiliar.getCodigocasafamilia());
                retirodto.setDevolviocarnet(auxiliar.getDevolviocarnet());
                retirodto.setEstudioretirado(auxiliar.getEstudioretirado());
                retirodto.setEstudios_anteriores(auxiliar.getEstudiosanteriores());
                retirodto.setFechafallecido(auxiliar.getFechafallecido());
                retirodto.setFecharetiro(auxiliar.getFecharetiro());
                retirodto.setMedicosupervisor(auxiliar.getMedicosupervisor());
                retirodto.setMotivo(auxiliar.getMotivo());
                String obs = auxiliar.getObservaciones() == null ? "Ninguna" : auxiliar.getObservaciones();
                retirodto.setObservaciones(obs);
                retirodto.setOtrosmotivo(auxiliar.getOtrosmotivo());
                retirodto.setPersonadocumenta(auxiliar.getPersonadocumenta());
                String comunicador = auxiliar.getQuiencomunica() == null ? "Ninguno": auxiliar.getQuiencomunica();
                retirodto.setQuiencomunica(comunicador);
                retirodto.setRelfam(auxiliar.getRelfam());
                retirodto.setCodigo_participante(auxiliar.getParticipante().getCodigo());
                Razones_Retiro objRazon = this.retiroservice.getRazonRetiro(auxiliar.getMotivo());
                retirodto.setDescripcionretiro(objRazon.getDescripcion());
                addobj.add(retirodto);
            }
            historialDTO.setRetiroList(addobj);
            return JsonUtil.createJsonResponse(historialDTO);
        }catch (Exception e){
            logger.error(e.getMessage());
            Gson gson = new Gson();
            String json = gson.toJson(e.toString());
            return new ResponseEntity<String>( json, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/HistorialRetiro", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody List<RetiroDto> HistorialRetiro(@RequestParam(value = "parametro2", required = true)Integer parametro2)
            throws ParseException {
        List<RetiroDto> listRetiro = null;
        try{
            listRetiro =  retiroservice.ListOfRetiro(parametro2);
            return listRetiro;
        }catch (Exception e){
            return listRetiro = null;
        }
    }
    //Registro/participantsByCasa
    @RequestMapping(value = "/participantsByCasa", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    List<ParticipantesEnCasa> participantsByCasa(@RequestParam(value = "casaCode", required = false, defaultValue = "")Integer casaCode,
                                                 @RequestParam(value = "codParticipante", required = false, defaultValue = "") Integer codParticipante)
            throws Exception {
        List<ParticipantesEnCasa> listEnCasa = new ArrayList<ParticipantesEnCasa>();
        try{
            List<Participante>  listParticipantes =  participanteService.getParticipanteByCodeCasa(casaCode);
            //ParticipanteProcesos procesos = participanteProcesosService.getParticipante(codParticipante);
            for (Participante participantes : listParticipantes){

                ParticipanteProcesos procesos = participanteProcesosService.getParticipante(participantes.getCodigo());

                ParticipantesEnCasa ParticipantEnCasa = new ParticipantesEnCasa();
                ParticipantEnCasa.setCodCasaPediatrica(participantes.getCasa().getCodigo());
                ParticipantEnCasa.setCodCasaFamilia(procesos.getCasaCHF());
                ParticipantEnCasa.setIdParticipante(participantes.getCodigo());
                String nombrecompleto = participantes.getNombre1().toUpperCase();
                if (participantes.getNombre2() != null)
                    nombrecompleto = nombrecompleto +" "+ participantes.getNombre2().toUpperCase();
                nombrecompleto = nombrecompleto +" "+ participantes.getApellido1().toUpperCase();
                if (participantes.getApellido2() != null)
                    nombrecompleto = nombrecompleto +" "+ participantes.getApellido2().toUpperCase();
                ParticipantEnCasa.setNombreParticipante(nombrecompleto);
                String cadena = participantes.getEdad();
                String[] parts = cadena.split("/");
                String part1 = parts[0]; // años
                String part2 = parts[1]; // meses
                String part3 = parts[2]; // dias
                ParticipantEnCasa.setAnios(part1);
                ParticipantEnCasa.setMeses(part2);
                ParticipantEnCasa.setDias(part3);
                String est = (procesos.getEstPart()== 1 ? "Activo" : "Retirado");
                ParticipantEnCasa.setEstado(est.toUpperCase());
                listEnCasa.add(ParticipantEnCasa);
           }
            return listEnCasa;
        }catch (Exception e){
            return listEnCasa = null;
        }
    }

    //Registro/allParticipants
    @RequestMapping(value = "/allParticipants", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    List<DatosParticipante> allParticipants()
            throws Exception{
        try {
            List<DatosParticipante> lista = participanteService.getAllParticipantes();
            return lista;
        }catch (Exception e){
            return null;
        }
    }

    /* obtener los nombres y apellidos de participante
     *
      * /Registro/getNombre1
     */
    @RequestMapping(value = "/getNombre1", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    List<String> getNombre1(@RequestParam(value = "nombre1", required = true) String nombre1)
            throws Exception {
        ArrayList<String> nombreArrayList = null;
        try {
            List<String> listaPart = participanteService.getNombre11(nombre1);

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
            List<String> listaPart = participanteService.getApellido1(apellido1);
            if (listaPart.size() == 0){

            }
            return listaPart;
        }catch (Exception e){
            return nombreArrayList = null;
        }
    }


    @RequestMapping(value = "/getPartNombreApellido", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    List<Participante> getPartNombreApellido(@RequestParam(value = "nombre1", required = true) String nombre1
            ,@RequestParam(value = "apellido1", required = true) String apellido1)
            throws Exception {
        try{
            List<Participante> getNombreApellido = participanteService.getParticipanteByNomApellido(nombre1,apellido1);
            return getNombreApellido;
        }catch (Exception e){
            return null;
        }
    }


}
