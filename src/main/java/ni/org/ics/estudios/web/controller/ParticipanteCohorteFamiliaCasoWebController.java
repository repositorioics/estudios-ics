package ni.org.ics.estudios.web.controller;

import com.google.gson.Gson;
import ni.org.ics.estudios.domain.Participante;
import ni.org.ics.estudios.domain.cohortefamilia.ParticipanteCohorteFamilia;
import ni.org.ics.estudios.domain.cohortefamilia.casos.CasaCohorteFamiliaCaso;
import ni.org.ics.estudios.domain.cohortefamilia.casos.ParticipanteCohorteFamiliaCaso;
import ni.org.ics.estudios.domain.muestreoanual.ParticipanteProcesos;
import ni.org.ics.estudios.service.ParticipanteService;
import ni.org.ics.estudios.service.cohortefamilia.ParticipanteCohorteFamiliaService;
import ni.org.ics.estudios.service.cohortefamilia.casos.CasaCohorteFamiliaCasoService;
import ni.org.ics.estudios.service.cohortefamilia.casos.ParticipanteCohorteFamiliaCasoService;
import ni.org.ics.estudios.service.muestreoanual.ParticipanteProcesosService;
import ni.org.ics.estudios.web.utils.DateUtil;
import org.apache.commons.lang3.text.translate.UnicodeEscaper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.text.ParseException;
import java.util.*;

/**
 * Created by Miguel Salinas on 6/28/2017.
 * V1.0
 */
@Controller
@RequestMapping("/super/particaso/*")
public class ParticipanteCohorteFamiliaCasoWebController {

    private static final Logger logger = LoggerFactory.getLogger(ParticipanteCohorteFamiliaCasoWebController.class);

    @Resource(name = "participanteCohorteFamiliaCasoService")
    private ParticipanteCohorteFamiliaCasoService participanteCohorteFamiliaCasoService;

    @Resource(name = "participanteCohorteFamiliaService")
    private ParticipanteCohorteFamiliaService participanteCohorteFamiliaService;

    @Resource(name = "participanteService")
    private ParticipanteService participanteService;

    @Resource(name = "casaCohorteFamiliaCasoService")
    private CasaCohorteFamiliaCasoService casaCohorteFamiliaCasoService;

    @Resource(name = "participanteProcesosService")
    private ParticipanteProcesosService participanteProcesosService;

    @RequestMapping(value = "searchParticipant", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    ResponseEntity<String> buscarParticipante(@RequestParam(value="participantCode", required=true ) Integer codigo) throws ParseException {
        logger.debug("buscar participanteChf para monitoreo intensivo");
        ParticipanteCohorteFamilia participanteChf = participanteCohorteFamiliaService.getParticipanteCHFByCodigo(codigo);
        if (participanteChf==null) {
            Participante participante = participanteService.getParticipanteByCodigo(codigo);
            if (participante!=null){
                ParticipanteProcesos procesos = participanteProcesosService.getParticipante(codigo);
                if (procesos!=null && procesos.getEstPart().equals(0))
                    return createJsonResponse("Participante retirado");
                else return createJsonResponse("Participante aún no ha sido enrolado");
            }
            return createJsonResponse("No se encontró participante según el código ingresado");
        }else{
            ParticipanteCohorteFamiliaCaso participanteCaso = this.participanteCohorteFamiliaCasoService.getParticipanteCohorteFamiliaCasosByParticipantePos(codigo);
            if (participanteCaso!=null)
                return createJsonResponse("Participante ya fue registrado como positivo en la casa: "+participanteCaso.getCodigoCaso().getCasa().getCodigoCHF());
            else {
                ParticipanteProcesos procesos = participanteProcesosService.getParticipante(codigo);
                if (procesos!=null && procesos.getEstPart().equals(0))
                    return createJsonResponse("Participante retirado");
            }
        }
            return createJsonResponse(participanteChf);
    }

    @RequestMapping(value = "getParticipantsCasos", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    ResponseEntity<String> buscarParticipantesByCasa(@RequestParam(value="casas", required=true ) String casas,
                                                     @RequestParam(value = "etiquetas", required = false) String etiquetas) throws ParseException {
        logger.debug("buscar participantes de las casas para monitoreo intensivo"+etiquetas);
        //CHF_CAT_TIP_ETIQ_MI_01(BHC),CHF_CAT_TIP_ETIQ_MI_02(PBMC),CHF_CAT_TIP_ETIQ_MI_03(Rojo),CHF_CAT_TIP_ETIQ_MI_04(FLU)
        Map<String, String> map = new HashMap<String, String>();
        String[] codigosCasas = casas.split(",");
        //List<MuestraInfluenza> muestras = new ArrayList<MuestraInfluenza>();
        int indice = 0;
        for(String codigoCasa : codigosCasas){
            List<CasaCohorteFamiliaCaso> casosCasa = casaCohorteFamiliaCasoService.getCasaCohorteFamiliaCasosByCodigoCasa(codigoCasa);
            String evento = (casosCasa.size()<10?"0":"")+String.valueOf(casosCasa.size());
            List<ParticipanteCohorteFamiliaCaso> participantesCasos = participanteCohorteFamiliaCasoService.getParticipantesCohorteFamiliaCasoByCodigoCasa(codigoCasa);
            List<String> codigoLineal = new ArrayList<String>();
            for(ParticipanteCohorteFamiliaCaso participante : participantesCasos){
                //*1*1 al final de cada codigo indica que es una copia del estiquer y es tipo QRCode
                //El formato para los tipo QRCode es: <fechafif><fechaHoy>*<codigolab>*<codigocasa>*<copias>*<tipo>

                //*1*2 al final de cada codigo indica que es una copia del estiquer y es tipo Codabar
                //El formato para los tipo Codabar es: <codigoparticipante>*<copias>*<tipo>
                String[] edad = participante.getParticipante().getParticipante().getEdad().split("/");
                int anios = 0;
                int meses = 0;
                //int dias = 0;
                if (edad.length > 0) {
                    anios = Integer.valueOf(edad[0]);
                    meses = Integer.valueOf(edad[1]);
                    //dias = Integer.valueOf(edad[2]);
                }
                //Para los menores de 6 meses no se toma ningún tipo de muestra
                if (anios > 0 || (anios == 0 && meses >= 6)) {
                    String codigoLab = "";

                    Date fif = (participante.getFechaEnfermedad() != null ? participante.getFechaEnfermedad() : DateUtil.StringToDate("10/10/3000", "dd/MM/yyyy"));
                    if (etiquetas.contains("CHF_CAT_TIP_ETIQ_MI_04")) {
                        codigoLab = DateUtil.DateToString(fif, "dd/MM/yyyy") + DateUtil.DateToString(new Date(), "dd/MM/yyyy");
                        codigoLab += "  *" + participante.getParticipante().getParticipante().getCodigo() + "." + evento + ".TR1";
                        codigoLab += "*" + participante.getCodigoCaso().getCasa().getCodigoCHF() + "*1*1";
                        map.put("CODIGO" + indice, codigoLab);
                        indice++;
                    }
                    if (etiquetas.contains("CHF_CAT_TIP_ETIQ_MI_03")) {
                        codigoLab = DateUtil.DateToString(fif, "dd/MM/yyyy") + DateUtil.DateToString(new Date(), "dd/MM/yyyy");
                        codigoLab += "  *" + participante.getParticipante().getParticipante().getCodigo() + "." + evento + ".TRI";
                        codigoLab += "*" + participante.getCodigoCaso().getCasa().getCodigoCHF() + "*1*1";
                        map.put("CODIGO" + indice, codigoLab);
                        indice++;
                    }
                    if (etiquetas.contains("CHF_CAT_TIP_ETIQ_MI_02")) {
                        codigoLab = DateUtil.DateToString(fif, "dd/MM/yyyy") + DateUtil.DateToString(new Date(), "dd/MM/yyyy");
                        codigoLab += "  *" + participante.getParticipante().getParticipante().getCodigo() + "." + evento + ".TPI";
                        codigoLab += "*" + participante.getCodigoCaso().getCasa().getCodigoCHF() + "*1*1";
                        map.put("CODIGO" + indice, codigoLab);
                        indice++;
                    }
                    if (etiquetas.contains("CHF_CAT_TIP_ETIQ_MI_01")) {
                        codigoLab = participante.getParticipante().getParticipante().getCodigo() + "*1*2";
                        codigoLineal.add(codigoLab);
                    }

                }
            }
            for(String codigo : codigoLineal){
                map.put("CODIGO"+indice, codigo);
                indice++;
            }
        }
        map.put("TOTAL", String.valueOf(indice));
        return createJsonResponse(map);
    }

    private ResponseEntity<String> createJsonResponse( String mensaje )
    {
        Map<String, String> map = new HashMap<String, String>();
        map.put("mensaje", mensaje);
        Gson gson = new Gson();
        String json = gson.toJson(map);
        UnicodeEscaper escaper = UnicodeEscaper.above(127);
        json = escaper.translate(json);
        return new ResponseEntity<String>( json, HttpStatus.CREATED);
    }

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
