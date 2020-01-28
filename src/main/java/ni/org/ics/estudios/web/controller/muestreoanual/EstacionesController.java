package ni.org.ics.estudios.web.controller.muestreoanual;

import ni.org.ics.estudios.domain.muestreoanual.MuestraMA;
import ni.org.ics.estudios.domain.muestreoanual.Obsequio;
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
@RequestMapping("/estaciones/*")
public class EstacionesController {
	@Resource(name="muestraMAService")
	private MuestraService muestraService;
	@Resource(name="reporteService")
	private ReporteService reporteService;
	@Resource(name="obsequiosMAService")
	private ObsequioService obsequioService;
	//@Resource(name="reconsentimientoService")
	//private ReConsentimientoDenService reconsentimientoService;
	@Resource(name="newVacunaService")
	private NewVacunaService newVacunaService;
	private static final Logger logger = LoggerFactory.getLogger(EstacionesController.class);
	
	
	/**
	 * Maneja y obtiene todos los BHCs  y los presenta en una pagina JSP
	 * 
	 * @return El nombre de la pagina JSP  
	 */
	@RequestMapping(value = "/bhcs", method = RequestMethod.GET)
	public String fetchTodasBHCJSP(Model model) throws ParseException {
		logger.debug("Mostrando Muestras en JSP");
		// Obtiene todos los bhcs de las estaciones llamando al DomainService
		List<MuestraMA> estBhcs = muestraService.getMuestrasBHC();
		List<Object> conteoBHCEst = reporteService.getCountBHCsEst();
		// Incluye todas las bhcs al modelo
		model.addAttribute("estBhcs", estBhcs);
		model.addAttribute("estBhcsTotales", conteoBHCEst);
		List<Object> muestrasTotal = reporteService.getMuestrasTotal();
        model.addAttribute("muestrasTotal", muestrasTotal);
        List<Object> muestrasxEstudio = reporteService.getMuestrasxEstudio();
        model.addAttribute("muestrasEstudio", muestrasxEstudio);
        List<Object> muestrasxTubo = reporteService.getMuestrasxTubo();
        model.addAttribute("muestrasTubo", muestrasxTubo);
		// Resuelve a  /WEB-INF/muestras/muestras_bhc_estaciones.jsp
		return "muestras/muestras_bhc_estaciones";
	}


	/**
	 * Maneja y obtiene todos los Rojos y los presenta en una pagina JSP
	 * 
	 * @return El nombre de la pagina JSP  
	 */
	@RequestMapping(value = "/rojos", method = RequestMethod.GET)
	public String fetchTodosRojosJSP(Model model) throws ParseException {
		logger.debug("Mostrando Muestras en JSP");
		List<MuestraMA> estRojos = muestraService.getMuestrasRojo();
		List<Object> conteoRojoEst = reporteService.getCountRojosEst();
		model.addAttribute("estRojos", estRojos);
		model.addAttribute("estRojosTotales", conteoRojoEst);
		List<Object> muestrasTotal = reporteService.getMuestrasTotal();
        model.addAttribute("muestrasTotal", muestrasTotal);
        List<Object> muestrasxEstudio = reporteService.getMuestrasxEstudio();
        model.addAttribute("muestrasEstudio", muestrasxEstudio);
        List<Object> muestrasxTubo = reporteService.getMuestrasxTubo();
        model.addAttribute("muestrasTubo", muestrasxTubo);
		return "muestras/muestras_rojo_estaciones";
	}



	/**
	 * Maneja y obtiene todos los Pbmcs y los presenta en una pagina JSP
	 * 
	 * @return El nombre de la pagina JSP  
	 */
	@RequestMapping(value = "/pbmcs", method = RequestMethod.GET)
	public String fetchTodosPbmcsJSP(Model model) throws ParseException {
		logger.debug("Mostrando Muestras en JSP");
		List<MuestraMA> estPbmcs = muestraService.getMuestrasPbmc();
		List<Object> conteoPbmcEst = reporteService.getCountPBMCsEst();
		model.addAttribute("estPbmcs", estPbmcs);
		model.addAttribute("estPbmcsTotales", conteoPbmcEst);
		List<Object> muestrasTotal = reporteService.getMuestrasTotal();
        model.addAttribute("muestrasTotal", muestrasTotal);
        List<Object> muestrasxEstudio = reporteService.getMuestrasxEstudio();
        model.addAttribute("muestrasEstudio", muestrasxEstudio);
        List<Object> muestrasxTubo = reporteService.getMuestrasxTubo();
        model.addAttribute("muestrasTubo", muestrasxTubo);
		return "muestras/muestras_pbmc_estaciones";
	}

	@RequestMapping(value = "/obsequios", method = RequestMethod.GET)
    public String fetchPBMCsJSP(Model model) throws ParseException {
    	logger.debug("Mostrando Obsequios en JSP");
    	List<Obsequio> obsequios = obsequioService.getObsequiosSi();
    	List<Object> conteoObsequios = obsequioService.getCountObsequios();
    	model.addAttribute("obsequios", obsequios);
    	model.addAttribute("conteoObsequios", conteoObsequios);
    	List<Object> muestrasTotal = reporteService.getMuestrasTotal();
        model.addAttribute("muestrasTotal", muestrasTotal);
        List<Object> muestrasxEstudio = reporteService.getMuestrasxEstudio();
        model.addAttribute("muestrasEstudio", muestrasxEstudio);
        List<Object> muestrasxTubo = reporteService.getMuestrasxTubo();
        model.addAttribute("muestrasTubo", muestrasxTubo);
    	return "obsequios/obsequios_estaciones";
	}
	
	
	@RequestMapping(value = "/consentimientoschik", method = RequestMethod.GET)
    public String fetchConsentimientosJSP(Model model) throws ParseException {
    	logger.debug("Mostrando Consentimientos en JSP");
    	/*List<ConsentimientoChik> consentimientos = reconsentimientoService.getConsentimientosChik();
    	List<Object> conteoReconsentimientos = reconsentimientoService.getCountConsentimientosChik();
    	model.addAttribute("consentimientos", consentimientos);
    	model.addAttribute("conteoReconsentimientos", conteoReconsentimientos);*/
    	List<Object> muestrasTotal = reporteService.getMuestrasTotal();
        model.addAttribute("muestrasTotal", muestrasTotal);  
        List<Object> muestrasxEstudio = reporteService.getMuestrasxEstudio();
        model.addAttribute("muestrasEstudio", muestrasxEstudio);
        List<Object> muestrasxTubo = reporteService.getMuestrasxTubo();
        model.addAttribute("muestrasTubo", muestrasxTubo);
    	return "consentimientos/consentimientos_chik";
	}   
	

	@RequestMapping(value = "/chgest", method = RequestMethod.GET)
    public String fetchCambiosEstudiosJSP(Model model) throws ParseException {
    	logger.debug("Mostrando CambiosEstudios en JSP");
    	/*List<CambioEstudio> cambios = reconsentimientoService.getCambiosEstudio();
    	List<Object> conteoCambios = reconsentimientoService.getCountCambios();
    	model.addAttribute("cambios", cambios);
    	model.addAttribute("conteoCambios", conteoCambios);*/
    	List<Object> muestrasTotal = reporteService.getMuestrasTotal();
        model.addAttribute("muestrasTotal", muestrasTotal);
        List<Object> muestrasxEstudio = reporteService.getMuestrasxEstudio();
        model.addAttribute("muestrasEstudio", muestrasxEstudio);
        List<Object> muestrasxTubo = reporteService.getMuestrasxTubo();
        model.addAttribute("muestrasTubo", muestrasxTubo);
    	return "consentimientos/cambios_estudio";
	}


}
