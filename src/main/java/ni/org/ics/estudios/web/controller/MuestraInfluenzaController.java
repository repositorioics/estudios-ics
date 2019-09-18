package ni.org.ics.estudios.web.controller;

import com.google.gson.Gson;
import ni.org.ics.estudios.domain.MuestraInfluenza;
import ni.org.ics.estudios.domain.cohortefamilia.Muestra;
import ni.org.ics.estudios.domain.cohortefamilia.ParticipanteCohorteFamilia;
import ni.org.ics.estudios.domain.cohortefamilia.casos.ParticipanteCohorteFamiliaCaso;
import ni.org.ics.estudios.language.MessageResource;
import ni.org.ics.estudios.service.MessageResourceService;
import ni.org.ics.estudios.service.MuestraInfluenzaService;
import ni.org.ics.estudios.service.cohortefamilia.ParticipanteCohorteFamiliaService;
import ni.org.ics.estudios.service.cohortefamilia.casos.ParticipanteCohorteFamiliaCasoService;
import ni.org.ics.estudios.web.utils.DateUtil;
import org.apache.commons.lang3.text.translate.UnicodeEscaper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.text.ParseException;
import java.util.*;

/**
 * Created by Miguel Salinas on 7/4/2017.
 * V1.0
 */
@Controller
@RequestMapping("/lab/muestras/*")
public class MuestraInfluenzaController {
    private static final Logger logger = LoggerFactory.getLogger(MuestraInfluenzaController.class);

    @Resource(name = "muestraInfluenzaService")
    private MuestraInfluenzaService muestraInfluenzaService;

    @Resource(name = "messageResourceService")
    private MessageResourceService messageResourceService;

    @Resource(name = "participanteCohorteFamiliaService")
    private ParticipanteCohorteFamiliaService participanteCohorteFamiliaService;

    @Resource(name = "participanteCohorteFamiliaCasoService")
    private ParticipanteCohorteFamiliaCasoService participanteCohorteFamiliaCasoService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String obtenerUsuarios(Model model) throws ParseException {
        logger.debug("Mostrando versiones de cartas en JSP");
        List<MessageResource> tiposEtiquetas = messageResourceService.getCatalogo("CHF_CAT_TIP_ETIQ_MI");
        model.addAttribute("etiquetas",tiposEtiquetas);
        return "laboratorio/search";
    }

    @RequestMapping(value = "searchSamples", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    ResponseEntity<String> buscarMuestras(@RequestParam(value="codigoParticipante", required=false ) Integer codigoParticipante,
                                          @RequestParam(value="codigoCasa", required=false ) String codigoCasa,
                                          @RequestParam(value="codigoMx", required=false ) String codigoMx,
                                          @RequestParam(value="fechaInicio", required=false ) String fechaInicio,
                                          @RequestParam(value="fechaFin", required=false ) String fechaFin,
                                          @RequestParam(value="registrado", required=false ) String registrado,
                                          @RequestParam(value="tipoEtiquetas", required=false ) String tipoEtiquetas) throws Exception {
        logger.debug("buscar participanteChf para monitoreo intensivo"+tipoEtiquetas);
        List<MuestraInfluenza> muestras = new ArrayList<MuestraInfluenza>();
        Map<Integer, Object> map = new HashMap<Integer, Object>();
        if (registrado!=null) {
            muestras = muestraInfluenzaService.getMuestrasInfluenza(codigoParticipante, codigoMx, DateUtil.StringToDate(fechaInicio, "dd/MM/yyyy"), DateUtil.StringToDate(fechaFin + " 23:59:59", "dd/MM/yyyy HH:mm:ss"), codigoCasa);
            int indice = 0;
            for (MuestraInfluenza muestraInfluenza : muestras) {
                ParticipanteCohorteFamilia participanteCohorteFamilia = participanteCohorteFamiliaService.getParticipanteCHFByCodigo(muestraInfluenza.getIdMx());
                map.put(indice, parseSampleMap(muestraInfluenza.getIdMx(), muestraInfluenza.getCodLab(), muestraInfluenza.getFechaTomaMx(), muestraInfluenza.getFif(), participanteCohorteFamilia));
                indice++;
            }
        }else{
            //CHF_CAT_TIP_ETIQ_MI_01,CHF_CAT_TIP_ETIQ_MI_02,CHF_CAT_TIP_ETIQ_MI_03,CHF_CAT_TIP_ETIQ_MI_04
            //String[] tiposEtiquetas = tipoEtiquetas.split(",");
            int indice = 0;
            //for(String etiqueta : tiposEtiquetas) {
                if (codigoCasa!=null && !codigoCasa.isEmpty() && codigoParticipante==null){
                    List<ParticipanteCohorteFamiliaCaso> participantesCaso = participanteCohorteFamiliaCasoService.getParticipantesCohorteFamiliaCasoByCodigoCasa(codigoCasa);
                    for(ParticipanteCohorteFamiliaCaso participanteCaso : participantesCaso) {
                        muestras = muestraInfluenzaService.getMuestrasInfluenza(participanteCaso.getParticipante().getParticipante().getCodigo(), codigoCasa, null);
                        String codigoLab = "";
                        if (muestras.size() > 0) {
                            Integer numMuestra = Integer.valueOf(muestras.get(0).getCodLab().substring(muestras.get(0).getCodLab().lastIndexOf(".") + 3, muestras.get(0).getCodLab().length()));
                            codigoLab = muestras.get(0).getIdMx() + ".01.TR" + String.valueOf(numMuestra + 1);
                            if (numMuestra<5) {
                                ParticipanteCohorteFamilia participanteCohorteFamilia = participanteCohorteFamiliaService.getParticipanteCHFByCodigo(muestras.get(0).getIdMx());
                                map.put(indice, parseSampleMap(muestras.get(0).getIdMx(), codigoLab, muestras.get(0).getFechaTomaMx(), muestras.get(0).getFif(), participanteCohorteFamilia));
                                indice++;
                            }
                        } else {
                            ParticipanteCohorteFamilia participanteCohorteFamilia = participanteCohorteFamiliaService.getParticipanteCHFByCodigo(participanteCaso.getParticipante().getParticipante().getCodigo());
                            Date fif = (participanteCaso.getFechaEnfermedad() != null ? participanteCaso.getFechaEnfermedad() : DateUtil.StringToDate("10/10/3000", "dd/MM/yyyy"));

                            codigoParticipante = participanteCaso.getParticipante().getParticipante().getCodigo();
                            codigoLab = participanteCaso.getParticipante().getParticipante().getCodigo() + ".01.TR1";
                            map.put(indice, parseSampleMap(participanteCaso.getParticipante().getParticipante().getCodigo(), codigoLab, null, fif, participanteCohorteFamilia));

                            indice++;
                            codigoLab = codigoParticipante + ".01.TRI";
                            map.put(indice, parseSampleMap(codigoParticipante, codigoLab, null, fif, participanteCohorteFamilia));

                            indice++;
                            codigoLab = codigoParticipante + ".01.TPI";
                            map.put(indice, parseSampleMap(codigoParticipante, codigoLab, null, fif, participanteCohorteFamilia));

                            indice++;
                            codigoLab = String.valueOf(codigoParticipante);
                            map.put(indice, parseSampleMap(codigoParticipante, codigoLab, null, fif, participanteCohorteFamilia));
                        }
                    }
                } else{
                    muestras = muestraInfluenzaService.getMuestrasInfluenza(codigoParticipante, codigoCasa, null);
                    String codigoLab = "";
                    if (muestras.size() > 0) {
                        ParticipanteCohorteFamiliaCaso participanteCaso = participanteCohorteFamiliaCasoService.getParticipanteCohorteFamiliaCasosByParticipante(codigoParticipante, null);
                        Integer numMuestra = Integer.valueOf(muestras.get(0).getCodLab().substring(muestras.get(0).getCodLab().lastIndexOf(".") + 3, muestras.get(0).getCodLab().length()));
                        if (numMuestra<5) {
                            ParticipanteCohorteFamilia participanteCohorteFamilia = participanteCohorteFamiliaService.getParticipanteCHFByCodigo(muestras.get(0).getIdMx());
                            Date fif = (participanteCaso != null && participanteCaso.getFechaEnfermedad() != null ? participanteCaso.getFechaEnfermedad() : DateUtil.StringToDate("10/10/3000", "dd/MM/yyyy"));

                            codigoLab = muestras.get(0).getIdMx() + ".01.TR" + String.valueOf(numMuestra + 1);
                            map.put(indice, parseSampleMap(codigoParticipante, codigoLab, null, fif, participanteCohorteFamilia));
                            indice++;

                            codigoLab = codigoParticipante + ".01.TRF";
                            map.put(indice, parseSampleMap(codigoParticipante, codigoLab, null, fif, participanteCohorteFamilia));
                            indice++;

                            codigoLab = codigoParticipante + ".01.TPF";
                            map.put(indice, parseSampleMap(codigoParticipante, codigoLab, null, fif, participanteCohorteFamilia));
                        }
                    } else {
                        ParticipanteCohorteFamiliaCaso participanteCaso = participanteCohorteFamiliaCasoService.getParticipanteCohorteFamiliaCasosByParticipante(codigoParticipante, null);
                        Date fif = (participanteCaso != null && participanteCaso.getFechaEnfermedad() != null ? participanteCaso.getFechaEnfermedad() : DateUtil.StringToDate("10/10/3000", "dd/MM/yyyy"));
                        codigoLab = codigoParticipante + ".01.TR1";
                        ParticipanteCohorteFamilia participanteCohorteFamilia = participanteCohorteFamiliaService.getParticipanteCHFByCodigo(codigoParticipante);
                        map.put(indice, parseSampleMap(codigoParticipante, codigoLab, null, fif, participanteCohorteFamilia));

                        indice++;
                        codigoLab = codigoParticipante + ".01.TRI";
                        map.put(indice, parseSampleMap(codigoParticipante, codigoLab, null, fif, participanteCohorteFamilia));

                        indice++;
                        codigoLab = codigoParticipante + ".01.TPI";
                        map.put(indice, parseSampleMap(codigoParticipante, codigoLab, null, fif, participanteCohorteFamilia));

                        indice++;
                        codigoLab = String.valueOf(codigoParticipante);
                        map.put(indice, parseSampleMap(codigoParticipante, codigoLab, null, fif, participanteCohorteFamilia));
                    }
                }
            //}
        }
        if (map.size()>0)
            return createJsonResponse(map);
        else
            return createJsonResponse("No se encontraron resultados");
    }

    /* Mi Busqueda*/
    @RequestMapping(value = "searchLabel", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    ResponseEntity<String> searchLabel(@RequestParam(value="codigoParticipante", required=false ) Integer codigoParticipante,
                                          @RequestParam(value="codigoCasa", required=false ) String codigoCasa,
                                          @RequestParam(value="codigoMx", required=false ) String codigoMx,
                                          @RequestParam(value="fechaInicio", required=false ) String fechaInicio,
                                          @RequestParam(value="fechaFin", required=false ) String fechaFin,
                                          @RequestParam(value="registrado", required=false ) String registrado,
                                          @RequestParam(value="tipoEtiquetas", required=false ) String tipoEtiquetas) throws Exception {



        return  createJsonResponse("Test Realizado correctamente!");

    }
    //Fin de Mi Busqueda


    private  Map<String, Object> parseSampleMap(Integer codigoParticipante, String codigoLab, Date fechaToma, Date fif, ParticipanteCohorteFamilia participanteCohorteFamilia) throws Exception{
        Map<String, Object> mapMuestra = new HashMap<String, Object>();
        mapMuestra.put("idMx", codigoParticipante);
        mapMuestra.put("codLab", codigoLab);
        mapMuestra.put("fechaTomaMx", (fechaToma!=null?fechaToma:new Date()));
        mapMuestra.put("fif", fif);
        if (participanteCohorteFamilia != null){
            mapMuestra.put("casachf", participanteCohorteFamilia.getCasaCHF().getCodigoCHF());
            mapMuestra.put("casa", participanteCohorteFamilia.getParticipante().getCasa().getCodigo());
        }
        else{
            mapMuestra.put("casachf", "-");
            mapMuestra.put("casa", "-");
        }
        if (codigoLab.contains(".")){
            mapMuestra.put("tipo", "1");
        }else {
            mapMuestra.put("tipo", "2");
        }
        return mapMuestra;
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
