package ni.org.ics.estudios.web.controller;

import com.google.gson.Gson;
import ni.org.ics.estudios.domain.Participante;
import ni.org.ics.estudios.domain.Retiros.Retiros;
import ni.org.ics.estudios.domain.catalogs.Estudio;
import ni.org.ics.estudios.domain.catalogs.Razones_Retiro;
import ni.org.ics.estudios.domain.muestreoanual.ParticipanteProcesos;
import ni.org.ics.estudios.dto.HistorialRetiroDto;
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
    org.springframework.http.ResponseEntity<String> BuscarParticipanteByID(@RequestParam(value = "parametro", required = true) Integer parametro)
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
            historialDTO.setTutor(procesos.getTutor());
            historialDTO.setRelacionFamTutor(procesos.getRelacionFam().toString());
            historialDTO.setEstudios(procesos.getEstudio());
            historialDTO.setIsPbmc(procesos.getPbmc());
            historialDTO.setIsPaxgene(procesos.getPaxgene());
            historialDTO.setPesotalla(procesos.getPesoTalla());
            historialDTO.setLactMat(procesos.getEncLacMat());
            historialDTO.setEncPart(procesos.getEncPart());
            historialDTO.setConsFlu(procesos.getConsFlu());
            historialDTO.setConsDeng(procesos.getConsDeng());
            historialDTO.setVacunas(procesos.getInfoVacuna());
            historialDTO.setEncCHF(procesos.getEnCasaChf());
            historialDTO.setEncCasaCoh(procesos.getEnCasaChf());
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
                retirodto.setObservaciones(auxiliar.getObservaciones());
                retirodto.setOtrosmotivo(auxiliar.getOtrosmotivo());
                retirodto.setPersonadocumenta(auxiliar.getPersonadocumenta());
                retirodto.setQuiencomunica(auxiliar.getQuiencomunica());
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

}
