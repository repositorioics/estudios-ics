package ni.org.ics.estudios.web.controller.muestreoanual;

import ni.org.ics.estudios.service.muestreoanual.ReporteService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import java.text.ParseException;
import java.util.List;

/**
 * Maneja las solicitudes que van a la aplicacion relacionadas a jsp
 * 
 * @author Brenda Lopez
 */
@Controller
@RequestMapping("/reporte/*")
public class InformeController {
	@Resource(name="reporteService")
	private ReporteService reporteService;
	
	
	/**
	 * @return El nombre de la pagina JSP  
	 */
	@RequestMapping(value = "/sxday", method = RequestMethod.GET)
	public String fetchMuestrasxDiaJSP(Model model) throws ParseException {
		List<Object> muestrasDia = reporteService.getMuestrasDia();
		model.addAttribute("muestrasDia", muestrasDia);
		List<Object> muestrasTotal = reporteService.getMuestrasTotal();
        model.addAttribute("muestrasTotal", muestrasTotal);
        List<Object> muestrasxEstudio = reporteService.getMuestrasxEstudio();
        model.addAttribute("muestrasEstudio", muestrasxEstudio);
        List<Object> muestrasxTubo = reporteService.getMuestrasxTubo();
        model.addAttribute("muestrasTubo", muestrasxTubo);
		return "reporte/muestras_dia";
	}
	
	/**
	 * @return El nombre de la pagina JSP  
	 */
	@RequestMapping(value = "/sxnei", method = RequestMethod.GET)
	public String fetchMuestrasxBarrioJSP(Model model) throws ParseException {
		List<Object> muestrasBarrio = reporteService.getMuestrasxBarrio();
		model.addAttribute("muestrasBarrio", muestrasBarrio);
		List<Object> muestrasTotal = reporteService.getMuestrasTotal();
        model.addAttribute("muestrasTotal", muestrasTotal);
        List<Object> muestrasxEstudio = reporteService.getMuestrasxEstudio();
        model.addAttribute("muestrasEstudio", muestrasxEstudio);
        List<Object> muestrasxTubo = reporteService.getMuestrasxTubo();
        model.addAttribute("muestrasTubo", muestrasxTubo);
		return "reporte/muestras_barrio";
	}
	
	
	/**
	 * @return El nombre de la pagina JSP  
	 */
	@RequestMapping(value = "/sxage", method = RequestMethod.GET)
	public String fetchMuestrasxEdadJSP(Model model) throws ParseException {
		List<Object> muestrasEdad = reporteService.getMuestrasxEdad();
		model.addAttribute("muestrasEdad", muestrasEdad);
		List<Object> muestrasTotal = reporteService.getMuestrasTotal();
        model.addAttribute("muestrasTotal", muestrasTotal);
        List<Object> muestrasxEstudio = reporteService.getMuestrasxEstudio();
        model.addAttribute("muestrasEstudio", muestrasxEstudio);
        List<Object> muestrasxTubo = reporteService.getMuestrasxTubo();
        model.addAttribute("muestrasTubo", muestrasxTubo);
		return "reporte/muestras_edad";
	}
	
	
	/**
	 * @return El nombre de la pagina JSP  
	 */
	@RequestMapping(value = "/sxest", method = RequestMethod.GET)
	public String fetchMuestrasxEstudioJSP(Model model) throws ParseException {
		List<Object> muestrasEstudio = reporteService.getMuestrasxEstudio();
		model.addAttribute("muestrasEstudio", muestrasEstudio);
		List<Object> muestrasTotal = reporteService.getMuestrasTotal();
        model.addAttribute("muestrasTotal", muestrasTotal);
        List<Object> muestrasxEstudio = reporteService.getMuestrasxEstudio();
        model.addAttribute("muestrasEstudio", muestrasxEstudio);
        List<Object> muestrasxTubo = reporteService.getMuestrasxTubo();
        model.addAttribute("muestrasTubo", muestrasxTubo);
		return "reporte/muestras_estudio";
	}
	
}
