package ni.org.ics.estudios.web.controller.muestreoanual;

import ni.org.ics.estudios.domain.muestreoanual.Documentos;
import ni.org.ics.estudios.domain.muestreoanual.DocumentosId;
import ni.org.ics.estudios.service.muestreoanual.DocumentosService;
import ni.org.ics.estudios.service.muestreoanual.ReporteService;
import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Maneja las solicitudes que van a la aplicacion relacionadas a jsp
 * 
 * @author Brenda Lopez
 */
@Controller
@RequestMapping("/docs/*")
public class DocumentosWebController {
	@Resource(name="reporteService")
	private ReporteService reporteService;
	@Resource(name="documentosService")
	private DocumentosService documentosService;
	private static final Logger logger = LoggerFactory.getLogger(DocumentosWebController.class);
	
	
	/**
	 * Maneja y obtiene todos los Docs  y los presenta en una pagina JSP
	 * 
	 * @return El nombre de la pagina JSP  
	 */
	@RequestMapping(value = "/searchcod", method = RequestMethod.GET)
	public String fetchDocsxCodeJSP(Model model) throws ParseException {
		logger.debug("Mostrando docs en JSP");
		List<Object> conteoBHCEst = reporteService.getCountBHCsEst();
		model.addAttribute("estBhcsTotales", conteoBHCEst);
		List<Object> muestrasTotal = reporteService.getMuestrasTotal();
        model.addAttribute("muestrasTotal", muestrasTotal);
        List<Object> muestrasxEstudio = reporteService.getMuestrasxEstudio();
        model.addAttribute("muestrasEstudio", muestrasxEstudio);
        List<Object> muestrasxTubo = reporteService.getMuestrasxTubo();
        model.addAttribute("muestrasTubo", muestrasxTubo);
		return "docs/docxcode";
	}
	
	/**
	 * Maneja y obtiene todos los Docs  y los presenta en una pagina JSP
	 * 
	 * @return El nombre de la pagina JSP  
	 */
	@RequestMapping(value = "/searchdate", method = RequestMethod.GET)
	public String fetchDocsxDateJSP(Model model) throws ParseException {
		logger.debug("Mostrando docs en JSP");
		List<Object> conteoBHCEst = reporteService.getCountBHCsEst();
		model.addAttribute("estBhcsTotales", conteoBHCEst);
		List<Object> muestrasTotal = reporteService.getMuestrasTotal();
        model.addAttribute("muestrasTotal", muestrasTotal);
        List<Object> muestrasxEstudio = reporteService.getMuestrasxEstudio();
        model.addAttribute("muestrasEstudio", muestrasxEstudio);
        List<Object> muestrasxTubo = reporteService.getMuestrasxTubo();
        model.addAttribute("muestrasTubo", muestrasxTubo);
		return "docs/docxdate";
	}
	
	/**
     * Retorna una lista de docs. Acepta una solicitud GET para JSON
     * @return Un arreglo JSON de docs
	 * @throws java.text.ParseException
     */
    @RequestMapping(value = "docscode", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    List<Documentos> fetchDocumentosxCodeJson(@RequestParam(value = "codigo", required = true) Integer codigo) throws ParseException {
        logger.info("Obteniendo los documentos en JSON");
        List<Documentos> docs = null;
        docs = documentosService.getDocumentosxCodigo(codigo);
        return docs;
    }

	/**
     * Retorna una lista de docs. Acepta una solicitud GET para JSON
     * @return Un arreglo JSON de docs
	 * @throws java.text.ParseException
     */
    @RequestMapping(value = "docsdate", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    List<Documentos> fetchDocumentosxDateJson(@RequestParam(value = "fecha", required = true) String fecha) {
        logger.info("Obteniendo los documentos en JSON");
        List<Documentos> docs = null; 
        Date sdf;
		try {
			sdf = new SimpleDateFormat("dd/MM/yyyy").parse(fecha);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
        docs = documentosService.getDocumentosxDia(sdf);
        return docs;	
    }    
    
	/**
	 * Maneja y obtiene todos los Docs  y los presenta en una pagina JSP
	 * 
	 * @return El nombre de la pagina JSP  
	 */
	@RequestMapping(value = "/doc", method = RequestMethod.GET)
	public String fetchDocJSP(Model model, @RequestParam(value = "codigo", required = true) Integer codigo
			,@RequestParam(value = "fecha", required = true) long fecha) throws ParseException {
		logger.debug("Mostrando docs en JSP");
		List<Object> conteoBHCEst = reporteService.getCountBHCsEst();
		model.addAttribute("estBhcsTotales", conteoBHCEst);
		List<Object> muestrasTotal = reporteService.getMuestrasTotal();
        model.addAttribute("muestrasTotal", muestrasTotal);
        List<Object> muestrasxEstudio = reporteService.getMuestrasxEstudio();
        model.addAttribute("muestrasEstudio", muestrasxEstudio);
        List<Object> muestrasxTubo = reporteService.getMuestrasxTubo();
        model.addAttribute("muestrasTubo", muestrasxTubo);
        DocumentosId docId = new DocumentosId();
        docId.setCodigo(codigo);
        docId.setFechaDocumento(new Date(fecha));
        Documentos doc = documentosService.getDocumentos(docId);
        String encode = Base64.encodeBase64String(doc.getDocumento());
        model.addAttribute("doc", doc);
        model.addAttribute("imagen", encode);
		return "docs/doc";
	}

	
}
