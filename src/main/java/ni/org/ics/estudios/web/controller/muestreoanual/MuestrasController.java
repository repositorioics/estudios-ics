package ni.org.ics.estudios.web.controller.muestreoanual;

import ni.org.ics.estudios.domain.muestreoanual.*;
import ni.org.ics.estudios.dto.muestras.MuestraDto;
import ni.org.ics.estudios.dto.muestras.RecepcionBHCDto;
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
@RequestMapping("/muestras/*")
public class MuestrasController {
	@Resource(name="bhcService")
	private RecepcionBHCService bhcService;
	@Resource(name="seroService")
	private RecepcionSeroService seroService;
	@Resource(name="muestraMAService")
	private MuestraService muestraService;
	@Resource(name="labBhcService")
	private LabBHCService labBhcService;
	@Resource(name="labSeroService")
	private LabSeroService labSeroService;
	@Resource(name="labPbmcService")
	private LabPbmcService labPbmcService;
	@Resource(name="reporteService")
	private ReporteService reporteService;
	private static final Logger logger = LoggerFactory.getLogger(MuestrasController.class);
	
	
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
		List<RecepcionBHC> rBhcs = bhcService.getRecepcionBHCs();
		List<LabBHC> lab = labBhcService.getLabBHCs();
		List<Object> conteoBHCLab = reporteService.getCountBHCsLab();
		// Incluye todas las bhcs al modelo
		model.addAttribute("estBhcs", estBhcs);
		model.addAttribute("supBhcs", rBhcs);
		model.addAttribute("labBhcs", lab);
		model.addAttribute("labBhcsTotales", conteoBHCLab);
		List<Object> muestrasTotal = reporteService.getMuestrasTotal();
        model.addAttribute("muestrasTotal", muestrasTotal);
        List<Object> muestrasxEstudio = reporteService.getMuestrasxEstudio();
        model.addAttribute("muestrasEstudio", muestrasxEstudio);
        List<Object> muestrasxTubo = reporteService.getMuestrasxTubo();
        model.addAttribute("muestrasTubo", muestrasxTubo);
		// Resuelve a  /WEB-INF/muestras/muestras_bhc.jsp
		return "muestras/muestras_bhc";
	}
	
	/**
	 * Maneja y obtiene todos los BHCs  y los presenta en una pagina JSP
	 * 
	 * @return El nombre de la pagina JSP  
	 */
	@RequestMapping(value = "/equipos/bhcs", method = RequestMethod.GET)
	public String fetchTodasBHCEquiposJSP(Model model) throws ParseException {
		logger.debug("Mostrando Muestras en JSP");
		// Obtiene todos los bhcs de las estaciones llamando al DomainService
		List<MuestraMA> estBhcs = muestraService.getMuestrasBHC();
		List<RecepcionBHC> rBhcs = bhcService.getRecepcionBHCs();
		List<LabBHC> lab = labBhcService.getLabBHCs();
		List<Object> conteoBHCLab = reporteService.getCountBHCsLab();
		// Incluye todas las bhcs al modelo
		model.addAttribute("estBhcs", estBhcs);
		model.addAttribute("supBhcs", rBhcs);
		model.addAttribute("labBhcs", lab);
		model.addAttribute("labBhcsTotales", conteoBHCLab);
		List<Object> muestrasTotal = reporteService.getMuestrasTotal();
        model.addAttribute("muestrasTotal", muestrasTotal);
        List<Object> muestrasxEstudio = reporteService.getMuestrasxEstudio();
        model.addAttribute("muestrasEstudio", muestrasxEstudio);
        List<Object> muestrasxTubo = reporteService.getMuestrasxTubo();
        model.addAttribute("muestrasTubo", muestrasxTubo);
		// Resuelve a  /WEB-INF/muestras/muestras_bhc.jsp
		return "muestras/muestras_bhc";
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
		List<RecepcionSero> supRojos = seroService.getRecepcionSeros();
		List<LabSero> labRojos = labSeroService.getLabSeros();
		List<Object> conteoRojoLab = reporteService.getCountRojosLab();
		model.addAttribute("supRojos", supRojos);
		model.addAttribute("labRojos", labRojos);
		model.addAttribute("estRojos", estRojos);
		model.addAttribute("labRojosTotales", conteoRojoLab);
		List<Object> muestrasTotal = reporteService.getMuestrasTotal();
        model.addAttribute("muestrasTotal", muestrasTotal);
        List<Object> muestrasxEstudio = reporteService.getMuestrasxEstudio();
        model.addAttribute("muestrasEstudio", muestrasxEstudio);
        List<Object> muestrasxTubo = reporteService.getMuestrasxTubo();
        model.addAttribute("muestrasTubo", muestrasxTubo);
		return "muestras/muestras_rojo";
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
		List<LabPbmc> labPbmcs = labPbmcService.getLabPbmcs();
		List<Object> conteoPbmcLab = reporteService.getCountPBMCsLab();
		model.addAttribute("estPbmcs", estPbmcs);
		model.addAttribute("labPbmcs", labPbmcs);
		model.addAttribute("labPbmcsTotales", conteoPbmcLab);
		List<Object> muestrasTotal = reporteService.getMuestrasTotal();
        model.addAttribute("muestrasTotal", muestrasTotal);
        List<Object> muestrasxEstudio = reporteService.getMuestrasxEstudio();
        model.addAttribute("muestrasEstudio", muestrasxEstudio);
        List<Object> muestrasxTubo = reporteService.getMuestrasxTubo();
        model.addAttribute("muestrasTubo", muestrasxTubo);
		return "muestras/muestras_pbmc";
	}


	/**
	 *
	 * 
	 * @return El nombre de la pagina JSP  
	 */
	@RequestMapping(value = "/compbhc", method = RequestMethod.GET)
	public String fetchComp1JSP(Model model) throws ParseException {
		logger.debug("Mostrando Muestras en JSP");
		// Obtiene todos los bhcs llamando al DomainService    	
		List<RecepcionBHCDto> bhcSupNoEst = bhcService.getCompBHCSupEstHoy();
		List<RecepcionBHCDto> bhcSupNoLab = bhcService.getCompBHCSupLabHoy();
		List<MuestraDto> bhcEstnoSup = muestraService.getCompBHCEstSupHoy();
		List<MuestraDto> bhcEstnoLab = muestraService.getCompBHCEstLabHoy();
		List<LabBHC> bhcLabNoSup = labBhcService.getCompBHCLabSupHoy();
		List<LabBHC> bhcLabNoEst = labBhcService.getCompBHCLabEstHoy();
		// Incluye bhcs de hoy al modelo
		model.addAttribute("bhcsupnoesthoy", bhcSupNoEst);
		model.addAttribute("bhcsupnolabhoy", bhcSupNoLab);
		model.addAttribute("bhcestnosuphoy", bhcEstnoSup);
		model.addAttribute("bhcestnolabhoy", bhcEstnoLab);
		model.addAttribute("bhclabnosuphoy", bhcLabNoSup);
		model.addAttribute("bhclabnoesthoy", bhcLabNoEst);
		List<Object> muestrasTotal = reporteService.getMuestrasTotal();
        model.addAttribute("muestrasTotal", muestrasTotal);
        List<Object> muestrasxEstudio = reporteService.getMuestrasxEstudio();
        model.addAttribute("muestrasEstudio", muestrasxEstudio);
        List<Object> muestrasxTubo = reporteService.getMuestrasxTubo();
        model.addAttribute("muestrasTubo", muestrasxTubo);
		// Resuelve a  /WEB-INF/jsp/compbhc.jsp
		return "comparacion/comp_bhc";
	}


	/**
	 * 
	 * 
	 * @return El nombre de la pagina JSP  
	 */
	@RequestMapping(value = "/comprojo", method = RequestMethod.GET)
	public String fetchComp2JSP(Model model) throws ParseException {
		logger.debug("Mostrando Muestras en JSP");
		List<RecepcionSero> rojoSupNoEst = seroService.getCompSeroSupEstHoy();
		List<RecepcionSero> rojoSupNoLab = seroService.getCompSeroSupLabHoy();
		List<MuestraDto> rojoEstnoSup = muestraService.getCompSeroEstSupHoy();
		List<MuestraDto> rojoEstnoLab = muestraService.getCompSeroEstLabHoy();
		List<LabSero> rojoLabNoSup = labSeroService.getCompSeroLabSupHoy();
		List<LabSero> rojoLabNoEst = labSeroService.getCompSeroLabEstHoy();
		model.addAttribute("rojosupnoesthoy", rojoSupNoEst);
		model.addAttribute("rojosupnolabhoy", rojoSupNoLab);
		model.addAttribute("rojoestnosuphoy", rojoEstnoSup);
		model.addAttribute("rojoestnolabhoy", rojoEstnoLab);
		model.addAttribute("rojolabnosuphoy", rojoLabNoSup);
		model.addAttribute("rojolabnoesthoy", rojoLabNoEst);
		List<Object> muestrasTotal = reporteService.getMuestrasTotal();
        model.addAttribute("muestrasTotal", muestrasTotal);
        List<Object> muestrasxEstudio = reporteService.getMuestrasxEstudio();
        model.addAttribute("muestrasEstudio", muestrasxEstudio);
        List<Object> muestrasxTubo = reporteService.getMuestrasxTubo();
        model.addAttribute("muestrasTubo", muestrasxTubo);
		return "comparacion/comp_rojo";
	}

	/**
	 * 
	 * 
	 * @return El nombre de la pagina JSP  
	 */
	@RequestMapping(value = "/comppbmc", method = RequestMethod.GET)
	public String fetchComp3JSP(Model model) throws ParseException {
		logger.debug("Mostrando Muestras en JSP");
		List<MuestraDto> pbmcEstnoLab = muestraService.getCompPbmcEstLabHoy();
		List<LabPbmc> pbmcLabNoEst = labPbmcService.getCompPbmcLabEstHoy();
		model.addAttribute("pbmcestnolabhoy", pbmcEstnoLab);
		model.addAttribute("pbmclabnoesthoy", pbmcLabNoEst);
		List<Object> muestrasTotal = reporteService.getMuestrasTotal();
        model.addAttribute("muestrasTotal", muestrasTotal);
        List<Object> muestrasxEstudio = reporteService.getMuestrasxEstudio();
        model.addAttribute("muestrasEstudio", muestrasxEstudio);
        List<Object> muestrasxTubo = reporteService.getMuestrasxTubo();
        model.addAttribute("muestrasTubo", muestrasxTubo);
		return "comparacion/comp_pbmc";
	}
}
