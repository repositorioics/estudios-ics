package ni.org.ics.estudios.web.controller.muestreoanual;

import ni.org.ics.estudios.domain.muestreoanual.*;
import ni.org.ics.estudios.service.muestreoanual.*;
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
@RequestMapping("/supervisores/*")
public class SupervisoresController {
	@Resource(name="bhcService")
	private RecepcionBHCService bhcService;
	@Resource(name="seroService")
	private RecepcionSeroService seroService;
	@Resource(name="reporteService")
	private ReporteService reporteService;
	@Resource(name="tPbmcService")
	private TempPbmcService tPbmcService;
	@Resource(name="tRojoBhcService")
	private TempRojoBhcService tRojoBhcService;
	@Resource(name="pinService")
	private PinchazoService pinService;
	private static final Logger logger = LoggerFactory.getLogger(SupervisoresController.class);
	
	
	/**
	 * Maneja y obtiene todos los BHCs  y los presenta en una pagina JSP
	 * 
	 * @return El nombre de la pagina JSP  
	 */
	@RequestMapping(value = "/bhcs", method = RequestMethod.GET)
	public String fetchTodasBHCJSP(Model model) throws ParseException {
		logger.debug("Mostrando Muestras en JSP");
		// Obtiene todos los bhcs
		List<RecepcionBHC> supBhcs = bhcService.getRecepcionBHCs();
		List<Object> conteoBHCSup = reporteService.getCountBHCsSup();
		// Incluye todas las bhcs al modelo
		model.addAttribute("supBhcs", supBhcs);
		model.addAttribute("conteoBHCSup", conteoBHCSup);
		List<Object> muestrasTotal = reporteService.getMuestrasTotal();
        model.addAttribute("muestrasTotal", muestrasTotal);
        List<Object> muestrasxEstudio = reporteService.getMuestrasxEstudio();
        model.addAttribute("muestrasEstudio", muestrasxEstudio);
        List<Object> muestrasxTubo = reporteService.getMuestrasxTubo();
        model.addAttribute("muestrasTubo", muestrasxTubo);
		// Resuelve a  /WEB-INF/muestras/muestras_bhc_estaciones.jsp
		return "muestras/muestras_bhc_supervisores";
	}


	/**
	 * Maneja y obtiene todos los Rojos y los presenta en una pagina JSP
	 * 
	 * @return El nombre de la pagina JSP  
	 */
	@RequestMapping(value = "/rojos", method = RequestMethod.GET)
	public String fetchTodosRojosJSP(Model model) throws ParseException {
		logger.debug("Mostrando Muestras en JSP");
		List<RecepcionSero> supRojos = seroService.getRecepcionSeros();
		List<Object> conteoRojoSup = reporteService.getCountRojosSup();
		model.addAttribute("supRojos", supRojos);
		model.addAttribute("conteoRojoSup", conteoRojoSup);
		List<Object> muestrasTotal = reporteService.getMuestrasTotal();
        model.addAttribute("muestrasTotal", muestrasTotal);
        List<Object> muestrasxEstudio = reporteService.getMuestrasxEstudio();
        model.addAttribute("muestrasEstudio", muestrasxEstudio);
        List<Object> muestrasxTubo = reporteService.getMuestrasxTubo();
        model.addAttribute("muestrasTubo", muestrasxTubo);
		return "muestras/muestras_rojo_supervisores";
	}


	@RequestMapping(value = "/temp1", method = RequestMethod.GET)
    public String fetchPBMCsJSP(Model model) throws ParseException {
    	logger.debug("Mostrando Temps en JSP");
    	List<TempPbmc> temps = tPbmcService.getTempPbmcs();
    	List<Object> conteoTemps = tPbmcService.getStatTempPbmcs();
    	model.addAttribute("temps", temps);
    	model.addAttribute("conteoTemps", conteoTemps);
    	List<Object> muestrasTotal = reporteService.getMuestrasTotal();
        model.addAttribute("muestrasTotal", muestrasTotal);
        List<Object> muestrasxEstudio = reporteService.getMuestrasxEstudio();
        model.addAttribute("muestrasEstudio", muestrasxEstudio);
        List<Object> muestrasxTubo = reporteService.getMuestrasxTubo();
        model.addAttribute("muestrasTubo", muestrasxTubo);
    	return "temperaturas/temp_pbmc_supervisores";
	}
	
	
	@RequestMapping(value = "/temp2", method = RequestMethod.GET)
    public String fetchRojosJSP(Model model) throws ParseException {
    	logger.debug("Mostrando Temps en JSP");
    	List<TempRojoBhc> temps = tRojoBhcService.getTempRojoBhcs();
    	List<Object> conteoTemps = tRojoBhcService.getStatTempRojos();
    	model.addAttribute("temps", temps);
    	model.addAttribute("conteoTemps", conteoTemps);
    	List<Object> muestrasTotal = reporteService.getMuestrasTotal();
        model.addAttribute("muestrasTotal", muestrasTotal);
        List<Object> muestrasxEstudio = reporteService.getMuestrasxEstudio();
        model.addAttribute("muestrasEstudio", muestrasxEstudio);
        List<Object> muestrasxTubo = reporteService.getMuestrasxTubo();
        model.addAttribute("muestrasTubo", muestrasxTubo);
    	return "temperaturas/temp_rojo_supervisores";
	}
	
	
	@RequestMapping(value = "/pinchazos", method = RequestMethod.GET)
    public String fetchPinchazosJSP(Model model) throws ParseException {
    	logger.debug("Mostrando Pinchazos en JSP");
    	List<Pinchazo> pinchazos = pinService.getPinchazo();
    	List<Object> conteoPinchazos = pinService.getCountPinchazos();
    	model.addAttribute("pinchazos", pinchazos);
    	model.addAttribute("conteoPinchazos", conteoPinchazos);
    	List<Object> muestrasTotal = reporteService.getMuestrasTotal();
        model.addAttribute("muestrasTotal", muestrasTotal);
        List<Object> muestrasxEstudio = reporteService.getMuestrasxEstudio();
        model.addAttribute("muestrasEstudio", muestrasxEstudio);
        List<Object> muestrasxTubo = reporteService.getMuestrasxTubo();
        model.addAttribute("muestrasTubo", muestrasxTubo);
    	return "pinchazos/pinchazos_supervisores";
	}
	
}
