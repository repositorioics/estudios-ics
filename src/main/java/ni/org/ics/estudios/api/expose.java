package ni.org.ics.estudios.api;

import com.google.gson.Gson;
import ni.org.ics.estudios.dto.RangosFrecuenciasCardiacas;
import ni.org.ics.estudios.dto.RangosPresion;
import ni.org.ics.estudios.service.hemodinanicaService.DatoshemodinamicaService;
import ni.org.ics.estudios.web.utils.DateUtil;
import org.apache.commons.lang3.text.translate.UnicodeEscaper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by miguel on 14/12/2020.
 */
@Controller
@RequestMapping(value = "/api/v1/")
public class expose {

    @Resource(name = "datoshemodinamicaService")
    private DatoshemodinamicaService datoshemodinamicaService;

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
                map.put("result","mayor de 18 años");
            }
            String jsonResponse;
            jsonResponse = new Gson().toJson(map);
            UnicodeEscaper escaper = UnicodeEscaper.above(127);
            return escaper.translate(jsonResponse);
        }catch (Exception e){
            throw e;
        }
    }
}
