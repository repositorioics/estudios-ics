package ni.org.ics.estudios.web.controller.muestreoanual;

import ni.org.ics.estudios.domain.muestreoanual.*;
import ni.org.ics.estudios.service.muestreoanual.LabBHCService;
import ni.org.ics.estudios.service.muestreoanual.LabPbmcService;
import ni.org.ics.estudios.service.muestreoanual.LabSeroService;
import ni.org.ics.estudios.service.muestreoanual.ReporteService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
@RequestMapping("/laboratorio/*")
public class LaboratorioController {
	@Resource(name="labBhcService")
	private LabBHCService labBhcService;
	@Resource(name="labSeroService")
	private LabSeroService labSeroService;
	@Resource(name="labPbmcService")
	private LabPbmcService labPbmcService;
	@Resource(name="reporteService")
	private ReporteService reporteService;
	private static final Logger logger = LoggerFactory.getLogger(LaboratorioController.class);
	
	
	/**
	 * Maneja y obtiene todos los BHCs  y los presenta en una pagina JSP
	 * 
	 * @return El nombre de la pagina JSP  
	 */
	@RequestMapping(value = "/bhcs", method = RequestMethod.GET)
	public String fetchTodasBHCJSP(Model model) throws ParseException {
		logger.debug("Mostrando Muestras en JSP");
		// Obtiene todos los bhcs
		List<LabBHC> labBhcs = labBhcService.getLabBHCs();
		List<Object> conteoBHCLab = reporteService.getCountBHCsLab();
		// Incluye todas las bhcs al modelo
		model.addAttribute("labBhcs", labBhcs);
		model.addAttribute("conteoBHCLab", conteoBHCLab);
		List<Object> muestrasTotal = reporteService.getMuestrasTotal();
        model.addAttribute("muestrasTotal", muestrasTotal);
        List<Object> muestrasxEstudio = reporteService.getMuestrasxEstudio();
        model.addAttribute("muestrasEstudio", muestrasxEstudio);
        List<Object> muestrasxTubo = reporteService.getMuestrasxTubo();
        model.addAttribute("muestrasTubo", muestrasxTubo);
		// Resuelve a  /WEB-INF/muestras/muestras_bhc_estaciones.jsp
		return "muestras/muestras_bhc_laboratorio";
	}


	/**
	 * Maneja y obtiene todos los Rojos y los presenta en una pagina JSP
	 * 
	 * @return El nombre de la pagina JSP  
	 */
	@RequestMapping(value = "/rojos", method = RequestMethod.GET)
	public String fetchTodosRojosJSP(Model model) throws ParseException {
		logger.debug("Mostrando Muestras en JSP");
		List<LabSero> labRojos = labSeroService.getLabSeros();
		List<Object> conteoRojoLab = reporteService.getCountRojosLab();
		model.addAttribute("labRojos", labRojos);
		model.addAttribute("conteoRojoLab", conteoRojoLab);
		List<Object> muestrasTotal = reporteService.getMuestrasTotal();
        model.addAttribute("muestrasTotal", muestrasTotal);
        List<Object> muestrasxEstudio = reporteService.getMuestrasxEstudio();
        model.addAttribute("muestrasEstudio", muestrasxEstudio);
        List<Object> muestrasxTubo = reporteService.getMuestrasxTubo();
        model.addAttribute("muestrasTubo", muestrasxTubo);
		return "muestras/muestras_rojo_laboratorio";
	}
	
	
	/**
	 * Maneja y obtiene todos los Pbmcs y los presenta en una pagina JSP
	 * 
	 * @return El nombre de la pagina JSP  
	 */
	@RequestMapping(value = "/pbmcs", method = RequestMethod.GET)
	public String fetchTodosPbmcsJSP(Model model) throws ParseException {
		logger.debug("Mostrando Muestras en JSP");
		List<LabPbmc> labPbmcs = labPbmcService.getLabPbmcs();
		List<Object> conteoPbmcLab = reporteService.getCountPBMCsLab();
		model.addAttribute("labPbmcs", labPbmcs);
		model.addAttribute("conteoPbmcLab", conteoPbmcLab);
		List<Object> muestrasTotal = reporteService.getMuestrasTotal();
        model.addAttribute("muestrasTotal", muestrasTotal);
        List<Object> muestrasxEstudio = reporteService.getMuestrasxEstudio();
        model.addAttribute("muestrasEstudio", muestrasxEstudio);
        List<Object> muestrasxTubo = reporteService.getMuestrasxTubo();
        model.addAttribute("muestrasTubo", muestrasxTubo);
		return "muestras/muestras_pbmc_laboratorio";
	}
	
	
	/**
	 * Maneja y obtiene todos los paxgenes y los presenta en una pagina JSP
	 * 
	 * @return El nombre de la pagina JSP  
	 */
	@RequestMapping(value = "/paxs", method = RequestMethod.GET)
	public String fetchTodosPaxsJSP(Model model) throws ParseException {
		logger.debug("Mostrando Muestras en JSP");
		List<LabPax> labPaxs = reporteService.getLabPax();
		List<Object> conteoPaxLab = reporteService.getCountPaxsLab();
		model.addAttribute("labPaxs", labPaxs);
		model.addAttribute("conteoPaxLab", conteoPaxLab);
		List<Object> muestrasTotal = reporteService.getMuestrasTotal();
        model.addAttribute("muestrasTotal", muestrasTotal);
        List<Object> muestrasxEstudio = reporteService.getMuestrasxEstudio();
        model.addAttribute("muestrasEstudio", muestrasxEstudio);
        List<Object> muestrasxTubo = reporteService.getMuestrasxTubo();
        model.addAttribute("muestrasTubo", muestrasxTubo);
		return "muestras/muestras_pax_laboratorio";
	}


	
	
	
	@RequestMapping(value = "/temperaturas", method = RequestMethod.GET)
    public String fetchRojosJSP(Model model) throws ParseException {
    	logger.debug("Mostrando Temps en JSP");
    	List<TempLab> temps = reporteService.getTempLab();
    	List<Object> conteoTemps = reporteService.getStatTempLab();
    	model.addAttribute("temps", temps);
    	model.addAttribute("conteoTemps", conteoTemps);
    	List<Object> muestrasTotal = reporteService.getMuestrasTotal();
        model.addAttribute("muestrasTotal", muestrasTotal);
        List<Object> muestrasxEstudio = reporteService.getMuestrasxEstudio();
        model.addAttribute("muestrasEstudio", muestrasxEstudio);
        List<Object> muestrasxTubo = reporteService.getMuestrasxTubo();
        model.addAttribute("muestrasTubo", muestrasxTubo);
    	return "temperaturas/temp_laboratorio";
	}
	
}
