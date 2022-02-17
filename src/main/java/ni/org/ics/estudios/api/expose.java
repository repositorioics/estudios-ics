package ni.org.ics.estudios.api;

import com.google.gson.Gson;
import ni.org.ics.estudios.dto.HojaConsulta.HCConsentimientoDto;
import ni.org.ics.estudios.dto.HojaConsulta.HCEscuelasDto;
import ni.org.ics.estudios.dto.HojaConsulta.HCParticipanteDto;
import ni.org.ics.estudios.dto.HojaConsulta.HCTipoConsentimientoDto;
import ni.org.ics.estudios.dto.RangosFrecuenciasCardiacas;
import ni.org.ics.estudios.dto.RangosPresion;
import ni.org.ics.estudios.service.hc.ActualizacionHojaConsultaService;
import ni.org.ics.estudios.service.hemodinanicaService.DatoshemodinamicaService;
import ni.org.ics.estudios.web.utils.DateUtil;
import org.apache.commons.lang3.text.translate.UnicodeEscaper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
            logger.info("Descargando toda la informacion de los consentimientos de cada participante para el sistema hoja de consulta digital");
            List<HCConsentimientoDto> respuestaList = actualizacionHojaConsultaService.getConsentimientos();
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
