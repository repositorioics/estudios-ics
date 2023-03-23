package ni.org.ics.estudios.api;

import com.google.common.base.Predicate;
import com.google.gson.Gson;
import ni.org.ics.estudios.domain.muestreoanual.ParticipanteProcesos;
import ni.org.ics.estudios.dto.HojaConsulta.*;
import ni.org.ics.estudios.dto.RangosFrecuenciasCardiacas;
import ni.org.ics.estudios.dto.RangosPresion;
import ni.org.ics.estudios.language.MessageResource;
import ni.org.ics.estudios.service.hc.ActualizacionHojaConsultaService;
import ni.org.ics.estudios.service.hemodinanicaService.DatoshemodinamicaService;
import ni.org.ics.estudios.service.muestreoanual.ParticipanteProcesosService;
import ni.org.ics.estudios.web.utils.DateUtil;
import ni.org.ics.estudios.web.utils.FilterLists;
import org.apache.commons.lang3.text.translate.UnicodeEscaper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.math.BigInteger;
import java.util.*;

/**
 * Created by miguel on 14/12/2020.
 */
@Controller
@RequestMapping(value = "/api/v1/")
public class expose {

    private static final Logger logger = LoggerFactory.getLogger(expose.class);

    @Resource(name = "datoshemodinamicaService")
    private DatoshemodinamicaService datoshemodinamicaService;

    @Resource(name = "actualizacionHojaConsultaService")
    private ActualizacionHojaConsultaService actualizacionHojaConsultaService;

    @Resource(name = "participanteProcesosService")
    private ParticipanteProcesosService participanteProcesosService;

    /* Obtener los Rangos de Presión y Frecuencias Cardiacas */
    @RequestMapping(value = "/rangos-presion-frecuencias", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    String GetRange(@RequestParam(value ="sexo", required = true) String sexo,
                    @RequestParam(value ="fecha_nac", required = true)String fecha) throws Exception{
        try{
            Map<String, String> map = new HashMap<String, String>();
            Date myfecha = DateUtil.StringToDate(fecha, "yyyyMMdd");
            String result = DateUtil.obtenerEdad(myfecha);
            String[] part = result.split(" ");
            String part1 = part[0];
            String part2 =part[1];
            Integer num = Integer.valueOf(part1);
            if (num <= 18) {
                RangosPresion objPresion = datoshemodinamicaService.ObtenerRangosPresion(sexo, num, part2);
                RangosFrecuenciasCardiacas objFrec = datoshemodinamicaService.ObtenerFCardiaca(part2, num);
                if (objPresion != null && objFrec != null) {
                    map.put("presion_sis_dias_min", objPresion.getPsdmin());
                    map.put("presion_sis_dias_med", objPresion.getPsdmed());
                    map.put("presion_sis_dias_max", objPresion.getPsdmax());
                    map.put("presion_arterial_min", objPresion.getPammin());
                    map.put("presion_arterial_med", objPresion.getPammed());
                    map.put("presion_arterial_max", objPresion.getPammax());
                    map.put("frec_cardiaca_min", objFrec.getFcMinima());
                    map.put("frec_cardiaca_med", objFrec.getFcMedia());
                    map.put("frec_cardiaca_prom", objFrec.getFcPromedio());
                    map.put("frec_respiratoria_min",objFrec.getFrMinima());
                    map.put("frec_respiratoria_max", objFrec.getFrMaxima());
                }
            }else{
                RangosFrecuenciasCardiacas objFrec = datoshemodinamicaService.ObtenerFCardiaca(part2, 18);
                if (objFrec != null) {
                    map.put("frec_cardiaca_min", objFrec.getFcMinima());
                    map.put("frec_cardiaca_med", objFrec.getFcMedia());
                    map.put("frec_cardiaca_prom", objFrec.getFcPromedio());
                    map.put("frec_respiratoria_min",objFrec.getFrMinima());
                    map.put("frec_respiratoria_max", objFrec.getFrMaxima());
                }
            }
            String jsonResponse;
            jsonResponse = new Gson().toJson(map);
            UnicodeEscaper escaper = UnicodeEscaper.above(127);
            return escaper.translate(jsonResponse);
        }catch (Exception e){
            e.printStackTrace();
            throw e;
        }
    }

    /**
     * Acepta una solicitud GET para JSON
     * @return JSON
     */
    @RequestMapping(value = "hc/participantes", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    List<HCParticipanteDto> getParticipantes() {
        try {
            logger.info("Descargando toda la informacion de participantes para el sistema hoja de consulta digital");
            List<HCParticipanteDto> respuestaList = actualizacionHojaConsultaService.getParticipantes();
            if (respuestaList == null) {
                logger.debug("Nulo");
            }
            return respuestaList;
        }catch (Exception ex){
            ex.printStackTrace();
            return null;
        }
    }

    /**
     * Acepta una solicitud GET para JSON
     * @return JSON
     */
    @RequestMapping(value = "hc/consentimientos", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    List<HCConsentimientoDto> getConsentimientos() {
        try {
            List<HCConsentimientoDto> respuestaList = new ArrayList<HCConsentimientoDto>();
            List<HCTipoConsentimientoDto> tiposConsentimientos = actualizacionHojaConsultaService.getTiposConsentimientos();
            List<ParticipanteProcesos> participanteProcesos = this.participanteProcesosService.getParticipantesProcesos();
            for(ParticipanteProcesos pp : participanteProcesos ){
                if (pp.getEstudio()!= null && pp.getEstudio()!=null) {
                    String[] arrayString = pp.getEstudio().split("  ");
                    for (String estudio : arrayString) {
                        HCTipoConsentimientoDto tipoCon = getTipoConsentimiento(tiposConsentimientos, estudio.trim());
                        if (tipoCon != null) {
                            HCConsentimientoDto consentimientoDto = new HCConsentimientoDto();
                            consentimientoDto.setCodigo(pp.getCodigo());
                            consentimientoDto.setRetirado(false);
                            consentimientoDto.setFecha(new Date());
                            consentimientoDto.setCons(BigInteger.valueOf(tipoCon.getCons()));
                            respuestaList.add(consentimientoDto);
                        }
                    }
                }
            }
            return respuestaList;
        }catch (Exception ex){
            ex.printStackTrace();
            return null;
        }
    }

    private HCTipoConsentimientoDto getTipoConsentimiento(List<HCTipoConsentimientoDto> tiposConsentimientos, final String descripcion){
        Predicate<HCTipoConsentimientoDto> predicate = new Predicate<HCTipoConsentimientoDto>() {
            @Override
            public boolean apply(HCTipoConsentimientoDto tpconsentimientos) {
                return tpconsentimientos.getDescCons()!=null && tpconsentimientos.getDescCons().equals(descripcion);
            }
        };
        Collection<HCTipoConsentimientoDto> resultado = FilterLists.filter(tiposConsentimientos, predicate);
        return resultado.size()>0?resultado.iterator().next():null;
    }

    /**
     * Acepta una solicitud GET para JSON
     * @return JSON
     */
    @RequestMapping(value = "hc/tipos-consentimientos", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    List<HCTipoConsentimientoDto> getTiposConsentimientos() {
        try {
            logger.info("Descargando toda la informacion de tipos de consentimientos para el sistema hoja de consulta digital");
            List<HCTipoConsentimientoDto> respuestaList = actualizacionHojaConsultaService.getTiposConsentimientos();
            if (respuestaList == null) {
                logger.debug("Nulo");
            }
            return respuestaList;
        }catch (Exception ex){
            ex.printStackTrace();
            return null;
        }
    }

    /**
     * Acepta una solicitud GET para JSON
     * @return JSON
     */
    @RequestMapping(value = "hc/escuelas", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    List<HCEscuelasDto> getEscuelas() {
        try {
            logger.info("Descargando toda la informacion de las escuelas para el sistema hoja de consulta digital");
            List<HCEscuelasDto> respuestaList = actualizacionHojaConsultaService.getEscuelas();
            if (respuestaList == null) {
                logger.debug("Nulo");
            }
            return respuestaList;
        }catch (Exception ex){
            ex.printStackTrace();
            return null;
        }
    }
}
