package ni.org.ics.estudios.web.controller;


import ni.org.ics.estudios.domain.cohortefamilia.Muestra;
import ni.org.ics.estudios.domain.cohortefamilia.ParticipanteCohorteFamilia;
import ni.org.ics.estudios.domain.cohortefamilia.casos.CasaCohorteFamiliaCaso;
import ni.org.ics.estudios.domain.cohortefamilia.casos.FormularioContactoCaso;
import ni.org.ics.estudios.domain.cohortefamilia.casos.ParticipanteCohorteFamiliaCaso;
import ni.org.ics.estudios.domain.cohortefamilia.casos.VisitaFallidaCaso;
import ni.org.ics.estudios.domain.cohortefamilia.casos.VisitaFinalCaso;
import ni.org.ics.estudios.domain.cohortefamilia.casos.VisitaSeguimientoCaso;
import ni.org.ics.estudios.domain.cohortefamilia.casos.VisitaSeguimientoCasoSintomas;
import ni.org.ics.estudios.language.MessageResource;
import ni.org.ics.estudios.service.MessageResourceService;
import ni.org.ics.estudios.service.UsuarioService;
import ni.org.ics.estudios.service.cohortefamilia.MuestraService;
import ni.org.ics.estudios.service.cohortefamilia.ParticipanteCohorteFamiliaService;
import ni.org.ics.estudios.service.cohortefamilia.casos.CasaCohorteFamiliaCasoService;
import ni.org.ics.estudios.service.cohortefamilia.casos.FormularioContactoCasoService;
import ni.org.ics.estudios.service.cohortefamilia.casos.ParticipanteCohorteFamiliaCasoService;
import ni.org.ics.estudios.service.cohortefamilia.casos.VisitaFallidaCasoService;
import ni.org.ics.estudios.service.cohortefamilia.casos.VisitaFinalCasoService;
import ni.org.ics.estudios.service.cohortefamilia.casos.VisitaSeguimientoCasoService;
import ni.org.ics.estudios.service.cohortefamilia.casos.VisitaSeguimientoCasoSintomasService;
import ni.org.ics.estudios.users.model.UserSistema;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.google.gson.Gson;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;

/**
 * Created by William Aviles on 10/07/2017.
 * V1.0
 */
@Controller
@RequestMapping("/chf/editarcaso/*")
public class ChfCasosController {

    private static final Logger logger = LoggerFactory.getLogger(ChfCasosController.class);
    
    @Resource(name = "casaCohorteFamiliaCasoService")
    private CasaCohorteFamiliaCasoService casaCohorteFamiliaCasoService;
    @Resource(name = "participanteCohorteFamiliaCasoService")
    private ParticipanteCohorteFamiliaCasoService participanteCohorteFamiliaCasoService;
    @Resource(name = "participanteCohorteFamiliaService")
    private ParticipanteCohorteFamiliaService participanteCohorteFamiliaService;
    @Resource(name = "visitaSeguimientoCasoService")
    private VisitaSeguimientoCasoService visitaSeguimientoCasoService;
    @Resource(name = "visitaFallidaCasoService")
    private VisitaFallidaCasoService visitaFallidaCasoService;
    @Resource(name = "visitaFinalCasoService")
    private VisitaFinalCasoService visitaFinalCasoService;
    @Resource(name = "visitaSeguimientoCasoSintomasService")
    private VisitaSeguimientoCasoSintomasService visitaSeguimientoCasoSintomasService;
    @Resource(name = "formularioContactoCasoService")
    private FormularioContactoCasoService formularioContactoCasoService;
    @Resource(name = "muestraService")
    private MuestraService muestraService;
    @Resource(name = "messageResourceService")
    private MessageResourceService messageResourceService;
    @Resource(name = "usuarioService")
    private UsuarioService usuarioService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String obtenerCasos(Model model) throws ParseException {
        logger.debug("Mostrando casos monitoreo intensivo en JSP");
        return "/chfcasos/search";
    }
    
    
    /**
     * Retorna una lista de casas. Acepta una solicitud GET para JSON
     * @return Un arreglo JSON de racks
	 * @throws ParseException 
     */
    @RequestMapping(value = "casas", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody List<CasaCohorteFamiliaCaso> fetchCasaCohorteFamiliaCasosJson(@RequestParam(value = "parametro", required = true) String parametro) throws ParseException {
        logger.info("Obteniendo las casas en JSON");
        List<CasaCohorteFamiliaCaso> casas = null; 
        casas = casaCohorteFamiliaCasoService.getCasaCohorteFamiliaCasos(parametro);
        return casas;	
    }
    
    @RequestMapping(value = "/participants/{codigoCaso}/", method = RequestMethod.GET)
    public String obtenerParticipantesCaso(@PathVariable("codigoCaso") String codigoCaso, Model model) {
    	CasaCohorteFamiliaCaso caso = casaCohorteFamiliaCasoService.getCasaCohorteFamiliaCasosByCodigo(codigoCaso);
    	List<ParticipanteCohorteFamiliaCaso> participantes = participanteCohorteFamiliaCasoService.getParticipantesCohorteFamiliaCasoByCodigoCaso(codigoCaso);
        model.addAttribute("caso",caso);
        model.addAttribute("participantes",participantes);
        return "/chfcasos/participantsList";
    }
    
    
    @RequestMapping(value = "/participantdata/{codigo}/", method = RequestMethod.GET)
    public String obtenerDatosParticipanteCaso(@PathVariable("codigo") String codigo, Model model) {
        logger.debug("Mostrando datos de un participante de caso monitoreo intensivo en JSP");
        ParticipanteCohorteFamiliaCaso participante = participanteCohorteFamiliaCasoService.getParticipanteCohorteFamiliaCasosByCodigo(codigo);
        List<VisitaSeguimientoCaso> vseg = visitaSeguimientoCasoService.getVisitaSeguimientoCasos(codigo);
        List<VisitaFallidaCaso> vfall = this.visitaFallidaCasoService.getVisitaFallidaCasos(codigo);
        VisitaFinalCaso visFinal = this.visitaFinalCasoService.getVisitaFinalCaso(codigo);
        List<Muestra> muestras = null;
        if(visFinal!=null) muestras = muestraService.getMuestrasTx(visFinal.getCodigoParticipanteCaso().getParticipante().getParticipante().getCodigo(),visFinal.getFechaVisita());
        model.addAttribute("participante",participante);
        model.addAttribute("visitas",vseg);
        model.addAttribute("fallidas",vfall);
        model.addAttribute("visFinal",visFinal);
        model.addAttribute("muestras",muestras);
        return "/chfcasos/participantData";
    }
    
    @RequestMapping(value = "/newvisit/{codigo}/", method = RequestMethod.GET)
	public String initCreationVisit(@PathVariable("codigo") String codigo, Model model) {
    	ParticipanteCohorteFamiliaCaso participante = participanteCohorteFamiliaCasoService.getParticipanteCohorteFamiliaCasosByCodigo(codigo);
    	List<MessageResource> visitas = messageResourceService.getCatalogo("CHF_CAT_VIS_MI");
    	List<MessageResource> sino = messageResourceService.getCatalogo("CHF_CAT_SINO");
    	model.addAttribute("participante",participante);
    	model.addAttribute("visitas",visitas);
    	model.addAttribute("sino",sino);
		return "/chfcasos/enterVisit";
	}
    
    @RequestMapping(value = "/editVisit/{codigoCasoVisita}/", method = RequestMethod.GET)
    public String initEditionVisit(Model model, @PathVariable(value = "codigoCasoVisita") String codigoCasoVisita){
    	VisitaSeguimientoCaso vsc = this.visitaSeguimientoCasoService.getVisitaSeguimientoCaso(codigoCasoVisita);
    	if (vsc!=null){
    		model.addAttribute("visita", vsc);
    		ParticipanteCohorteFamiliaCaso participante = participanteCohorteFamiliaCasoService.getParticipanteCohorteFamiliaCasosByCodigo(vsc.getCodigoParticipanteCaso().getCodigoCasoParticipante());
        	List<MessageResource> visitas = messageResourceService.getCatalogo("CHF_CAT_VIS_MI");
        	List<MessageResource> sino = messageResourceService.getCatalogo("CHF_CAT_SINO");
        	model.addAttribute("participante",participante);
        	model.addAttribute("visitas",visitas);
        	model.addAttribute("sino",sino);
    		return "/chfcasos/enterVisit";
    	}
    	else{
    		return "404";
        }
    }  
    
    /**
     * Custom handler for enabling/disabling a visit.
     *
     * @param username the ID of the visit to enable
     * @return a String
     */
    @RequestMapping("/voidVisit/{accion}/{codigoCasoVisita}")
    public String initDeletionVisit(@PathVariable("codigoCasoVisita") String codigoCasoVisita, 
    		@PathVariable("accion") String accion, RedirectAttributes redirectAttributes) {
    	String redirecTo="404";
    	UserSistema usuarioActual = this.usuarioService.getUser(SecurityContextHolder.getContext().getAuthentication().getName());
    	VisitaSeguimientoCaso vsc = this.visitaSeguimientoCasoService.getVisitaSeguimientoCaso(codigoCasoVisita);
    	
    	char hab;
    	if (accion.matches("enable")){
        	redirecTo = "redirect:/chf/editarcaso/participantdata/"+vsc.getCodigoParticipanteCaso().getCodigoCasoParticipante()+"/";
    		hab = '0';
    		redirectAttributes.addFlashAttribute("visitaHabilitado", true);
        }
        else if(accion.matches("disable")){
        	redirecTo = "redirect:/chf/editarcaso/participantdata/"+vsc.getCodigoParticipanteCaso().getCodigoCasoParticipante()+"/";
    		hab = '1';
    		redirectAttributes.addFlashAttribute("visitaDeshabilitado", true);
        }
        else{
        	return redirecTo;
        }
    	if(vsc!=null){
    		vsc.setRecordDate(new Date());
    		vsc.setRecordUser(usuarioActual.getUsername());
    		vsc.setPasive(hab);
    		this.visitaSeguimientoCasoService.saveOrUpdateVisitaSeguimientoCaso(vsc);
    	}
    	return redirecTo;	
    }
    
    
    /**
     * Custom handler for enabling/disabling a fail visit.
     *
     * @param username the ID of the visit to disable
     * @return a String
     */
    @RequestMapping("/voidFailVisit/{accion}/{codigoFallaVisita}")
    public String initDeletionFailVisit(@PathVariable("codigoFallaVisita") String codigoFallaVisita, 
    		@PathVariable("accion") String accion, RedirectAttributes redirectAttributes) {
    	String redirecTo="404";
    	UserSistema usuarioActual = this.usuarioService.getUser(SecurityContextHolder.getContext().getAuthentication().getName());
    	VisitaFallidaCaso vfc = this.visitaFallidaCasoService.getVisitaFallidaCaso(codigoFallaVisita);
    	
    	char hab;
    	if (accion.matches("enable")){
        	redirecTo = "redirect:/chf/editarcaso/participantdata/"+vfc.getCodigoParticipanteCaso().getCodigoCasoParticipante()+"/";
    		hab = '0';
    		redirectAttributes.addFlashAttribute("visitaHabilitado", true);
        }
        else if(accion.matches("disable")){
        	redirecTo = "redirect:/chf/editarcaso/participantdata/"+vfc.getCodigoParticipanteCaso().getCodigoCasoParticipante()+"/";
    		hab = '1';
    		redirectAttributes.addFlashAttribute("visitaDeshabilitado", true);
        }
        else{
        	return redirecTo;
        }
    	if(vfc!=null){
    		vfc.setRecordDate(new Date());
    		vfc.setRecordUser(usuarioActual.getUsername());
    		vfc.setPasive(hab);
    		this.visitaFallidaCasoService.saveOrUpdateVisitaFallidaCaso(vfc);
    	}
    	return redirecTo;	
    }
    
    @RequestMapping( value="saveVisit", method=RequestMethod.POST)
	public ResponseEntity<String> processVisitForm( @RequestParam(value="codigoCasoVisita", required=false, defaultValue="" ) String codigoCasoVisita
			, @RequestParam( value="codigoParticipanteCaso", required=true ) String codigoParticipanteCaso
			, @RequestParam( value="visita", required=true ) String visita
			, @RequestParam( value="fechaVisita", required=true ) String fechaVisita
			, @RequestParam( value="horaVisita", required=true ) String horaVisita
			, @RequestParam( value="horaProbableVisita", required=true ) String horaProbableVisita
			, @RequestParam( value="temp", required=true ) float temp
			, @RequestParam( value="expCS", required=true ) String expCS
	        ){
    	try{
    		UserSistema usuario = usuarioService.getUser(SecurityContextHolder.getContext().getAuthentication().getName());
    		ParticipanteCohorteFamiliaCaso participante = participanteCohorteFamiliaCasoService.getParticipanteCohorteFamiliaCasosByCodigo(codigoParticipanteCaso);
			VisitaSeguimientoCaso vsc = new VisitaSeguimientoCaso();
			List<Muestra> muestras = null;
			//Si el codigoCasoVisita viene en blanco es una nueva visita
			if (codigoCasoVisita.equals("")){
				if (this.visitaSeguimientoCasoService.checkVisitaSeguimientoCasos(codigoParticipanteCaso, visita, fechaVisita)){
					MessageResource repetido = messageResourceService.getMensaje("visitDuplicated");
					Gson gson = new Gson();
		    	    String json = gson.toJson(repetido.getSpanish());
					return new ResponseEntity<String>( json, HttpStatus.CREATED);
				}
				//Crear un nuevo VisitaSeguimientoCaso
				codigoCasoVisita = new UUID(usuario.getUsername().hashCode(),new Date().hashCode()).toString();
				vsc.setCodigoCasoVisita(codigoCasoVisita);
				vsc.setCodigoParticipanteCaso(participante);
			}
			//Si el codigoCasoVisita no viene en blanco hay que editar la visita
			else{
				//Recupera el medicion de la base de datos
				vsc = this.visitaSeguimientoCasoService.getVisitaSeguimientoCaso(codigoCasoVisita);
				muestras = muestraService.getMuestrasTx(vsc.getCodigoParticipanteCaso().getParticipante().getParticipante().getCodigo(),vsc.getFechaVisita());
			}
			SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        	Date date1 = formatter.parse(fechaVisita+" "+horaVisita);
        	vsc.setVisita(visita);
			vsc.setFechaVisita(date1);
			vsc.setHoraProbableVisita(horaProbableVisita);
			vsc.setTemp(temp);
			vsc.setExpCS(expCS);
			vsc.setRecordUser(usuario.getUsername());
			vsc.setRecordDate(new Date());
			WebAuthenticationDetails wad  = (WebAuthenticationDetails) SecurityContextHolder.getContext().getAuthentication().getDetails();
        	String idSesion = wad.getSessionId();
        	String direccionIp = wad.getRemoteAddress();
			vsc.setDeviceid(idSesion + "-"+ direccionIp);
			vsc.setEstado('1');
			//Actualiza la visita
			this.visitaSeguimientoCasoService.saveOrUpdateVisitaSeguimientoCaso(vsc);
			if(muestras!=null){
				for (Muestra muestra:muestras){
					muestra.setRecordDate(date1);
					muestra.setRecordUser(usuario.getUsername());
					muestra.setDeviceid(idSesion + "-"+ direccionIp);
					this.muestraService.saveOrUpdate(muestra);
				}
			}
			return createJsonResponse(vsc);
    	}
    	catch (DataIntegrityViolationException e){
    		String message = e.getMostSpecificCause().getMessage();
    		Gson gson = new Gson();
    	    String json = gson.toJson(message);
    		return new ResponseEntity<String>( json, HttpStatus.CREATED);
    	}
    	catch(Exception e){
    		Gson gson = new Gson();
    	    String json = gson.toJson(e.toString());
    		return new ResponseEntity<String>( json, HttpStatus.CREATED);
    	}
		
	}
    
    @RequestMapping(value = "/visitdata/{codigoCasoVisita}", method = RequestMethod.GET)
    public String obtenerDatosVisitaCaso(@PathVariable("codigoCasoVisita") String codigoCasoVisita, Model model) {
        logger.debug("Mostrando datos de un visita de caso monitoreo intensivo en JSP");
        VisitaSeguimientoCaso vsc = this.visitaSeguimientoCasoService.getVisitaSeguimientoCaso(codigoCasoVisita);
        ParticipanteCohorteFamiliaCaso participante = participanteCohorteFamiliaCasoService.getParticipanteCohorteFamiliaCasosByCodigo(vsc.getCodigoParticipanteCaso().getCodigoCasoParticipante());
        List<VisitaSeguimientoCasoSintomas> vsegsint = visitaSeguimientoCasoSintomasService.getVisitaSeguimientoCasoSintomas(codigoCasoVisita);
        List<FormularioContactoCaso> contactos = formularioContactoCasoService.getFormularioContactoCasos(codigoCasoVisita);
        List<Muestra> muestras = muestraService.getMuestrasTx(vsc.getCodigoParticipanteCaso().getParticipante().getParticipante().getCodigo(),vsc.getFechaVisita());
        model.addAttribute("participante",participante);
        model.addAttribute("visita",vsc);
        model.addAttribute("sintomas",vsegsint);
        model.addAttribute("contactos",contactos);
        model.addAttribute("muestras",muestras);
        return "/chfcasos/visitData";
    }
    
    @RequestMapping(value = "/newsint/{codigoCasoVisita}", method = RequestMethod.GET)
	public String initCreationSint(@PathVariable("codigoCasoVisita") String codigoCasoVisita, Model model) {
    	VisitaSeguimientoCaso vsc = this.visitaSeguimientoCasoService.getVisitaSeguimientoCaso(codigoCasoVisita);
    	List<MessageResource> sino = messageResourceService.getCatalogo("CHF_CAT_SND");
    	model.addAttribute("visita",vsc);
    	model.addAttribute("sino",sino);
		return "/chfcasos/enterSint";
	}
    
    @RequestMapping(value = "/editSint/{codigoCasoSintoma}/", method = RequestMethod.GET)
    public String initEditionSint(Model model, @PathVariable(value = "codigoCasoSintoma") String codigoCasoSintoma){
    	VisitaSeguimientoCasoSintomas vscs = this.visitaSeguimientoCasoSintomasService.getVisitaSeguimientoCasoSintoma(codigoCasoSintoma);
    	if (vscs!=null){
    		model.addAttribute("sintoma", vscs);
    		model.addAttribute("visita",vscs.getCodigoVisitaCaso());
    		List<MessageResource> sino = messageResourceService.getCatalogo("CHF_CAT_SND");
        	model.addAttribute("sino",sino);
    		return "/chfcasos/enterSint";
    	}
    	else{
    		return "404";
        }
    }  
    
    @RequestMapping("/voidSint/{codigoCasoSintoma}")
    public String initDeletionSint(@PathVariable("codigoCasoSintoma") String codigoCasoSintoma, RedirectAttributes redirectAttributes) {
    	String redirecTo="404";
    	UserSistema usuarioActual = this.usuarioService.getUser(SecurityContextHolder.getContext().getAuthentication().getName());
    	VisitaSeguimientoCasoSintomas vscs = this.visitaSeguimientoCasoSintomasService.getVisitaSeguimientoCasoSintoma(codigoCasoSintoma);
    	redirecTo = "redirect:/chf/editarcaso/visitdata/"+vscs.getCodigoVisitaCaso().getCodigoCasoVisita()+"/";
    	redirectAttributes.addFlashAttribute("sintNoHabilitado", true);
    	if(vscs!=null){
    		vscs.setRecordDate(new Date());
    		vscs.setRecordUser(usuarioActual.getUsername());
    		vscs.setPasive('1');
    		this.visitaSeguimientoCasoSintomasService.saveOrUpdateVisitaSeguimientoCasoSintomas(vscs);
    	}
    	return redirecTo;	
    }
    
    @RequestMapping( value="saveSint", method=RequestMethod.POST)
	public ResponseEntity<String> processSintForm( @RequestParam(value="codigoCasoSintoma", required=false, defaultValue="" ) String codigoCasoSintoma
			, @RequestParam( value="codigoCasoVisita", required=true ) String codigoCasoVisita
			, @RequestParam( value="fechaSintomas", required=true ) String fechaSintomas
			, @RequestParam( value="fiebre", required=true ) String fiebre
			, @RequestParam( value="fif", required=false, defaultValue="" ) String fif
			, @RequestParam( value="fiebreCuantificada", required=true ) String fiebreCuantificada
			, @RequestParam( value="valorFiebreCuantificada", required=false, defaultValue="" ) Float valorFiebreCuantificada
			, @RequestParam( value="dolorCabeza", required=true ) String dolorCabeza
			, @RequestParam( value="dolorArticular", required=true ) String dolorArticular
			, @RequestParam( value="dolorMuscular", required=true ) String dolorMuscular
			, @RequestParam( value="dificultadRespiratoria", required=true ) String dificultadRespiratoria
			, @RequestParam( value="secrecionNasal", required=true ) String secrecionNasal
			, @RequestParam( value="fsn", required=false, defaultValue="" ) String fsn
			, @RequestParam( value="tos", required=true ) String tos
			, @RequestParam( value="ftos", required=false, defaultValue="" ) String ftos
			, @RequestParam( value="pocoApetito", required=true ) String pocoApetito
			, @RequestParam( value="dolorGarganta", required=true ) String dolorGarganta
			, @RequestParam( value="frr", required=false, defaultValue="" ) String frr
			, @RequestParam( value="diarrea", required=true ) String diarrea
			, @RequestParam( value="quedoCama", required=true ) String quedoCama
			, @RequestParam( value="respiracionRuidosa", required=true ) String respiracionRuidosa
			, @RequestParam( value="oseltamivir", required=true ) String oseltamivir
			, @RequestParam( value="antibiotico", required=true ) String antibiotico
			, @RequestParam( value="cualAntibiotico", required=false, defaultValue="" ) String cualAntibiotico
			, @RequestParam( value="prescritoMedico", required=false, defaultValue="" ) String prescritoMedico
	        ){
    	try{
    		UserSistema usuario = usuarioService.getUser(SecurityContextHolder.getContext().getAuthentication().getName());
    		VisitaSeguimientoCaso vsc = this.visitaSeguimientoCasoService.getVisitaSeguimientoCaso(codigoCasoVisita);
    		VisitaSeguimientoCasoSintomas vscs = new VisitaSeguimientoCasoSintomas();
			//Si el codigoCasoSintoma viene en blanco es una nueva sintoma
			if (codigoCasoSintoma.equals("")){
				if (this.visitaSeguimientoCasoSintomasService.checkVisitaSeguimientoCasoSintomas(vsc.getCodigoParticipanteCaso().getCodigoCasoParticipante(), fechaSintomas)){
					MessageResource repetido = messageResourceService.getMensaje("sintDuplicated");
					Gson gson = new Gson();
		    	    String json = gson.toJson(repetido.getSpanish());
					return new ResponseEntity<String>( json, HttpStatus.CREATED);
				}
				//Crear un nuevo VisitaSeguimientoCaso
				codigoCasoSintoma = new UUID(usuario.getUsername().hashCode(),new Date().hashCode()).toString();
				vscs.setCodigoCasoSintoma(codigoCasoSintoma);
				vscs.setCodigoVisitaCaso(vsc);
			}
			//Si el codigoCasoSintoma no viene en blanco hay que editar la sintoma
			else{
				//Recupera el sintoma de la base de datos
				vscs = this.visitaSeguimientoCasoSintomasService.getVisitaSeguimientoCasoSintoma(codigoCasoSintoma);
			}
			SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        	Date date1 = formatter.parse(fechaSintomas);
        	vscs.setFechaSintomas(date1);
			vscs.setFiebre(fiebre);
			if(!fif.equals("")){
				date1 = formatter.parse(fif);
				vscs.setFif(date1);
			}
			vscs.setFiebreCuantificada(fiebreCuantificada);
			vscs.setValorFiebreCuantificada(valorFiebreCuantificada);
			vscs.setDolorCabeza(dolorCabeza);
			vscs.setDolorArticular(dolorArticular);
			vscs.setDolorMuscular(dolorMuscular);
			vscs.setDificultadRespiratoria(dificultadRespiratoria);
			vscs.setSecrecionNasal(secrecionNasal);
			if(!fsn.equals("")){
				date1 = formatter.parse(fsn);
				vscs.setFsn(date1);
			}
			vscs.setTos(tos);
			if(!ftos.equals("")){
				date1 = formatter.parse(ftos);
				vscs.setFtos(date1);
			}
			vscs.setPocoApetito(pocoApetito);
			vscs.setDolorGarganta(dolorGarganta);
			if(!frr.equals("")){
				date1 = formatter.parse(frr);
				vscs.setFrr(date1);
			}
			vscs.setDiarrea(diarrea);
			vscs.setQuedoCama(quedoCama);
			vscs.setRespiracionRuidosa(respiracionRuidosa);
			vscs.setOseltamivir(oseltamivir);
			vscs.setAntibiotico(antibiotico);
			vscs.setCualAntibiotico(cualAntibiotico);
			vscs.setPrescritoMedico(prescritoMedico);
			vscs.setRecordUser(usuario.getUsername());
			vscs.setRecordDate(new Date());
			WebAuthenticationDetails wad  = (WebAuthenticationDetails) SecurityContextHolder.getContext().getAuthentication().getDetails();
        	String idSesion = wad.getSessionId();
        	String direccionIp = wad.getRemoteAddress();
			vscs.setDeviceid(idSesion + "-"+ direccionIp);
			vscs.setEstado('1');
			//Actualiza la sintoma
			this.visitaSeguimientoCasoSintomasService.saveOrUpdateVisitaSeguimientoCasoSintomas(vscs);
			return createJsonResponse(vscs);
    	}
    	catch (DataIntegrityViolationException e){
    		String message = e.getMostSpecificCause().getMessage();
    		Gson gson = new Gson();
    	    String json = gson.toJson(message);
    		return new ResponseEntity<String>( json, HttpStatus.CREATED);
    	}
    	catch(Exception e){
    		Gson gson = new Gson();
    	    String json = gson.toJson(e.toString());
    		return new ResponseEntity<String>( json, HttpStatus.CREATED);
    	}
		
	}
    
    
    @RequestMapping(value = "/newcont/{codigoCasoVisita}", method = RequestMethod.GET)
	public String initCreationCont(@PathVariable("codigoCasoVisita") String codigoCasoVisita, Model model) {
    	VisitaSeguimientoCaso vsc = this.visitaSeguimientoCasoService.getVisitaSeguimientoCaso(codigoCasoVisita);
    	List<MessageResource> tiempoCat = messageResourceService.getCatalogo("CHF_CAT_TIEMPO_INTERACCION");
    	List<MessageResource> tipoCat = messageResourceService.getCatalogo("CHF_CAT_TIPO_INTERACCION");
    	List<ParticipanteCohorteFamiliaCaso> participantes = participanteCohorteFamiliaCasoService.getParticipantesCohorteFamiliaCasoByCodigoCaso(vsc.getCodigoParticipanteCaso().getCodigoCaso().getCodigoCaso());
    	participantes.remove(vsc.getCodigoParticipanteCaso());
        model.addAttribute("participantes",participantes);
    	model.addAttribute("visita",vsc);
    	model.addAttribute("tiempoCat",tiempoCat);
    	model.addAttribute("tipoCat",tipoCat);
		return "/chfcasos/enterCont";
	}
    
    @RequestMapping(value = "editCont/{codigoCasoContacto}/", method = RequestMethod.GET)
    public String initEditionCont(Model model, @PathVariable(value = "codigoCasoContacto") String codigoCasoContacto){
    	FormularioContactoCaso fcc = this.formularioContactoCasoService.getFormularioContactoCaso(codigoCasoContacto);
    	if (fcc!=null){
    		model.addAttribute("contacto", fcc);
    		model.addAttribute("visita",fcc.getCodigoVisitaCaso());
    		List<MessageResource> tiempoCat = messageResourceService.getCatalogo("CHF_CAT_TIEMPO_INTERACCION");
        	List<MessageResource> tipoCat = messageResourceService.getCatalogo("CHF_CAT_TIPO_INTERACCION");
        	List<ParticipanteCohorteFamiliaCaso> participantes = participanteCohorteFamiliaCasoService.getParticipantesCohorteFamiliaCasoByCodigoCaso(fcc.getCodigoVisitaCaso().getCodigoParticipanteCaso().getCodigoCaso().getCodigoCaso());
        	participantes.remove(fcc.getCodigoVisitaCaso().getCodigoParticipanteCaso());
            model.addAttribute("participantes",participantes);
        	model.addAttribute("tiempoCat",tiempoCat);
        	model.addAttribute("tipoCat",tipoCat);
    		return "/chfcasos/enterCont";
    	}
    	else{
    		return "404";
        }
    }  
    
    @RequestMapping("/voidCont/{codigoCasoContacto}")
    public String initDeletionCont(@PathVariable("codigoCasoContacto") String codigoCasoContacto, RedirectAttributes redirectAttributes) {
    	String redirecTo="404";
    	UserSistema usuarioActual = this.usuarioService.getUser(SecurityContextHolder.getContext().getAuthentication().getName());
    	FormularioContactoCaso fcc = this.formularioContactoCasoService.getFormularioContactoCaso(codigoCasoContacto);
    	redirecTo = "redirect:/chf/editarcaso/visitdata/"+fcc.getCodigoVisitaCaso().getCodigoCasoVisita()+"/";
    	redirectAttributes.addFlashAttribute("contNoHabilitado", true);
    	if(fcc!=null){
    		fcc.setRecordDate(new Date());
    		fcc.setRecordUser(usuarioActual.getUsername());
    		fcc.setPasive('1');
    		this.formularioContactoCasoService.saveOrUpdateFormularioContactoCaso(fcc);
    	}
    	return redirecTo;	
    }
    
    @RequestMapping( value="saveCont", method=RequestMethod.POST)
	public ResponseEntity<String> processContForm( @RequestParam(value="codigoCasoContacto", required=false, defaultValue="" ) String codigoCasoContacto
			, @RequestParam( value="codigoCasoVisita", required=true ) String codigoCasoVisita
			, @RequestParam( value="fechaContacto", required=true ) String fechaContacto
			, @RequestParam( value="partContacto", required=true ) Integer partContacto
			, @RequestParam( value="tiempoInteraccion", required=true ) String tiempoInteraccion
			, @RequestParam( value="tipoInteraccion", required=false, defaultValue="" ) String tipoInteraccion
	        ){
    	try{
    		UserSistema usuario = usuarioService.getUser(SecurityContextHolder.getContext().getAuthentication().getName());
    		VisitaSeguimientoCaso vsc = this.visitaSeguimientoCasoService.getVisitaSeguimientoCaso(codigoCasoVisita);
    		FormularioContactoCaso fc = new FormularioContactoCaso();
			//Si el codigoCasoContacto viene en blanco es un nuevo contacto
			if (codigoCasoContacto.equals("")){
				if (this.formularioContactoCasoService.checkContactoCasoSintomas(vsc.getCodigoParticipanteCaso().getParticipante().getParticipante().getCodigo(), fechaContacto, partContacto)){
					MessageResource repetido = messageResourceService.getMensaje("contDuplicated");
					Gson gson = new Gson();
		    	    String json = gson.toJson(repetido.getSpanish());
					return new ResponseEntity<String>( json, HttpStatus.CREATED);
				}
				//Crear un nuevo FormularioContactoCaso
				codigoCasoContacto = new UUID(usuario.getUsername().hashCode(),new Date().hashCode()).toString();
				fc.setCodigoCasoContacto(codigoCasoContacto);
				fc.setCodigoVisitaCaso(vsc);
			}
			//Si el codigoCasoContacto no viene en blanco hay que editar el contacto
			else{
				//Recupera el contacto de la base de datos
				fc = this.formularioContactoCasoService.getFormularioContactoCaso(codigoCasoContacto);
			}
			SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        	Date date1 = formatter.parse(fechaContacto);
        	fc.setFechaContacto(date1);
        	ParticipanteCohorteFamilia participante = participanteCohorteFamiliaService.getParticipanteCHFByCodigo(partContacto);
        	fc.setPartContacto(participante);
			fc.setTiempoInteraccion(tiempoInteraccion);
			fc.setTipoInteraccion(tipoInteraccion);
			fc.setRecordUser(usuario.getUsername());
			fc.setRecordDate(new Date());
			WebAuthenticationDetails wad  = (WebAuthenticationDetails) SecurityContextHolder.getContext().getAuthentication().getDetails();
        	String idSesion = wad.getSessionId();
        	String direccionIp = wad.getRemoteAddress();
			fc.setDeviceid(idSesion + "-"+ direccionIp);
			fc.setEstado('1');
			//Actualiza el contacto
			this.formularioContactoCasoService.saveOrUpdateFormularioContactoCaso(fc);
			return createJsonResponse(fc);
    	}
    	catch (DataIntegrityViolationException e){
    		String message = e.getMostSpecificCause().getMessage();
    		Gson gson = new Gson();
    	    String json = gson.toJson(message);
    		return new ResponseEntity<String>( json, HttpStatus.CREATED);
    	}
    	catch(Exception e){
    		Gson gson = new Gson();
    	    String json = gson.toJson(e.toString());
    		return new ResponseEntity<String>( json, HttpStatus.CREATED);
    	}
		
	}
    
    
    @RequestMapping(value = "/newfailvisit/{codigoCaso}", method = RequestMethod.GET)
	public String initCreationFailVisit(@PathVariable("codigoCaso") String codigoCaso, Model model) {
    	CasaCohorteFamiliaCaso caso = casaCohorteFamiliaCasoService.getCasaCohorteFamiliaCasosByCodigo(codigoCaso);
    	List<MessageResource> razones = messageResourceService.getCatalogo("CHF_CAT_VISITA_NO_C");
        List<MessageResource> visitas = messageResourceService.getCatalogo("CHF_CAT_VIS_MI");
    	model.addAttribute("caso",caso);
    	model.addAttribute("razones",razones);
        model.addAttribute("visitas",visitas);
		return "/chfcasos/enterFailVisit";
	}
    
    @RequestMapping(value = "/newfailvisitpart/{codigoCasoParticipante}", method = RequestMethod.GET)
	public String initCreationFailVisitPart(@PathVariable("codigoCasoParticipante") String codigoCasoParticipante, Model model) {
    	ParticipanteCohorteFamiliaCaso participante = participanteCohorteFamiliaCasoService.getParticipanteCohorteFamiliaCasosByCodigo(codigoCasoParticipante);
    	List<MessageResource> razones = messageResourceService.getCatalogo("CHF_CAT_VISITA_NO_P");
        List<MessageResource> visitas = messageResourceService.getCatalogo("CHF_CAT_VIS_MI");
    	model.addAttribute("participante",participante);
    	model.addAttribute("razones",razones);
        model.addAttribute("visitas",visitas);
		return "/chfcasos/enterFailVisitPart";
	}
    
    
    @RequestMapping(value = "editfailvisit/{codigoFallaVisita}/", method = RequestMethod.GET)
    public String initEditionFailVisit(Model model, @PathVariable(value = "codigoFallaVisita") String codigoFallaVisita){
    	VisitaFallidaCaso vfc = this.visitaFallidaCasoService.getVisitaFallidaCaso(codigoFallaVisita);
    	if (vfc!=null){
    		model.addAttribute("visitafallida", vfc);
    		ParticipanteCohorteFamiliaCaso participante = participanteCohorteFamiliaCasoService.getParticipanteCohorteFamiliaCasosByCodigo(vfc.getCodigoParticipanteCaso().getCodigoCasoParticipante());
    		model.addAttribute("participante",participante);
            List<MessageResource> razones = messageResourceService.getCatalogo("CHF_CAT_VISITA_NO_P");
            List<MessageResource> visitas = messageResourceService.getCatalogo("CHF_CAT_VIS_MI");
    		model.addAttribute("razones",razones);
            model.addAttribute("visitas",visitas);
    		return "/chfcasos/enterFailVisitPart";
    	}
    	else{
    		return "404";
        }
    }
    
    
    @RequestMapping( value="saveCasoFailVisit", method=RequestMethod.POST)
	public ResponseEntity<String> processFailVisitForm( @RequestParam(value="codigoCaso", required=false, defaultValue="" ) String codigo
			, @RequestParam( value="fechaVisita", required=true ) String fechaVisita
			, @RequestParam( value="horaVisita", required=true ) String horaVisita
			, @RequestParam( value="razonVisitaFallida", required=true ) String razonVisitaFallida
			, @RequestParam( value="otraRazon", required=false, defaultValue="" ) String otraRazon
            , @RequestParam( value="visita", required=false, defaultValue="" ) String visita
			){
    	try{
    		UserSistema usuario = usuarioService.getUser(SecurityContextHolder.getContext().getAuthentication().getName());
    		CasaCohorteFamiliaCaso caso = casaCohorteFamiliaCasoService.getCasaCohorteFamiliaCasosByCodigo(codigo);
    		List<ParticipanteCohorteFamiliaCaso> participantes = participanteCohorteFamiliaCasoService.getParticipantesCohorteFamiliaCasoByCodigoCaso(codigo);
    		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        	Date date1 = formatter.parse(fechaVisita+" "+horaVisita);
        	WebAuthenticationDetails wad  = (WebAuthenticationDetails) SecurityContextHolder.getContext().getAuthentication().getDetails();
        	String idSesion = wad.getSessionId();
        	String direccionIp = wad.getRemoteAddress();
    		for (ParticipanteCohorteFamiliaCaso part:participantes){
    			VisitaFallidaCaso vfc = new VisitaFallidaCaso();
    			vfc.setCodigoFallaVisita(new UUID(usuario.getUsername().hashCode(),new Date().hashCode()).toString());
    			vfc.setCodigoParticipanteCaso(part);
    			vfc.setFechaVisita(date1);
    			vfc.setRecordUser(usuario.getUsername());
    			vfc.setRecordDate(new Date());
    			vfc.setDeviceid(idSesion + "-"+ direccionIp);
    			vfc.setEstado('1');
    			vfc.setRazonVisitaFallida(razonVisitaFallida);
    			vfc.setOtraRazon(otraRazon);
                vfc.setVisita(visita);
    			//Actualiza la visita
    			this.visitaFallidaCasoService.saveOrUpdateVisitaFallidaCaso(vfc);
    		}
    		return createJsonResponse(caso);
    	}
    	catch (DataIntegrityViolationException e){
    		String message = e.getMostSpecificCause().getMessage();
    		Gson gson = new Gson();
    	    String json = gson.toJson(message);
    		return new ResponseEntity<String>( json, HttpStatus.CREATED);
    	}
    	catch(Exception e){
    		Gson gson = new Gson();
    	    String json = gson.toJson(e.toString());
    		return new ResponseEntity<String>( json, HttpStatus.CREATED);
    	}
	}
    
    
    @RequestMapping( value="saveCasoFailVisitPart", method=RequestMethod.POST)
	public ResponseEntity<String> processFailVisitPartForm( @RequestParam(value="codigoFallaVisita", required=false, defaultValue="" ) String codigoFallaVisita
			, @RequestParam( value="codigoParticipanteCaso", required=true ) String codigoParticipanteCaso
			, @RequestParam( value="fechaVisita", required=true ) String fechaVisita
			, @RequestParam( value="horaVisita", required=true ) String horaVisita
			, @RequestParam( value="razonVisitaFallida", required=true ) String razonVisitaFallida
			, @RequestParam( value="otraRazon", required=false, defaultValue="" ) String otraRazon
            , @RequestParam( value="visita", required=false, defaultValue="" ) String visita
	        ){
    	try{
    		UserSistema usuario = usuarioService.getUser(SecurityContextHolder.getContext().getAuthentication().getName());
    		ParticipanteCohorteFamiliaCaso participante = participanteCohorteFamiliaCasoService.getParticipanteCohorteFamiliaCasosByCodigo(codigoParticipanteCaso);
    		VisitaFallidaCaso vfc = new VisitaFallidaCaso();
			//Si el codigoFallaVisita viene en blanco es un nuevo visita
			if (codigoFallaVisita.equals("")){
				//Crear un nuevo VisitaFallidaCaso
				codigoFallaVisita = new UUID(usuario.getUsername().hashCode(),new Date().hashCode()).toString();
				vfc.setCodigoFallaVisita(codigoFallaVisita);
				vfc.setCodigoParticipanteCaso(participante);
			}
			//Si el codigoFallaVisita no viene en blanco hay que editar el visita
			else{
				//Recupera el visita de la base de datos
				vfc = this.visitaFallidaCasoService.getVisitaFallidaCaso(codigoFallaVisita);
			}
			SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        	Date date1 = formatter.parse(fechaVisita+" "+horaVisita);
        	vfc.setFechaVisita(date1);
			vfc.setRecordUser(usuario.getUsername());
			vfc.setRecordDate(new Date());
			WebAuthenticationDetails wad  = (WebAuthenticationDetails) SecurityContextHolder.getContext().getAuthentication().getDetails();
			String idSesion = wad.getSessionId();
        	String direccionIp = wad.getRemoteAddress();
			vfc.setDeviceid(idSesion + "-"+ direccionIp);
			vfc.setEstado('1');
			vfc.setRazonVisitaFallida(razonVisitaFallida);
			vfc.setOtraRazon(otraRazon);
            vfc.setVisita(visita);
			//Actualiza el contacto
			this.visitaFallidaCasoService.saveOrUpdateVisitaFallidaCaso(vfc);
			return createJsonResponse(vfc);
    	}
    	catch (DataIntegrityViolationException e){
    		String message = e.getMostSpecificCause().getMessage();
    		Gson gson = new Gson();
    	    String json = gson.toJson(message);
    		return new ResponseEntity<String>( json, HttpStatus.CREATED);
    	}
    	catch(Exception e){
    		Gson gson = new Gson();
    	    String json = gson.toJson(e.toString());
    		return new ResponseEntity<String>( json, HttpStatus.CREATED);
    	}
		
	}
    
    
    @RequestMapping(value = "/newsamp/{codigoCasoVisita}/{tipo}/", method = RequestMethod.GET)
	public String initCreationSamp(@PathVariable("codigoCasoVisita") String codigoCasoVisita, @PathVariable("tipo") String tipo, Model model) {
    	String formName="";
    	if (tipo.equals("1")){
    		VisitaSeguimientoCaso vsc = this.visitaSeguimientoCasoService.getVisitaSeguimientoCaso(codigoCasoVisita);
        	model.addAttribute("visita", vsc);
        	formName = "/chfcasos/enterSamp";
    	}
    	else if(tipo.equals("2")){
    		VisitaFinalCaso visFinal = this.visitaFinalCasoService.getVisitaFinalCaso(codigoCasoVisita);
    		model.addAttribute("visita", visFinal);
    		formName = "/chfcasos/enterSampFinal";
    	}
    	List<MessageResource> catTipoMx = messageResourceService.getCatalogo("CHF_CAT_TIP0_MX");
    	catTipoMx.remove(messageResourceService.getMensaje("CHF_CAT_TIP0_MX_09"));
    	model.addAttribute("catTipoMx", catTipoMx);
    	List<MessageResource> catTipoTubo = messageResourceService.getCatalogo("CHF_CAT_TIP_TUBO_MX");
    	catTipoTubo.remove(messageResourceService.getMensaje("CHF_CAT_TIP_TUBO_MX_04"));
    	catTipoTubo.remove(messageResourceService.getMensaje("CHF_CAT_TIP_TUBO_MX_05"));
    	catTipoTubo.remove(messageResourceService.getMensaje("CHF_CAT_TIP_TUBO_MX_09"));
    	model.addAttribute("catTipoTubo", catTipoTubo);
    	List<MessageResource> catSiNo = messageResourceService.getCatalogo("CHF_CAT_SINO");
    	model.addAttribute("catSiNo", catSiNo);
    	List<MessageResource> catNoMxResp = messageResourceService.getCatalogo("CHF_CAT_RAZON_NO_MX_RESP");
    	List<MessageResource> catNoMxSangre = messageResourceService.getCatalogo("CHF_CAT_RAZON_NO_MX");
    	model.addAttribute("catNoMxResp", catNoMxResp);
    	model.addAttribute("catNoMxSangre", catNoMxSangre);
    	List<MessageResource> catObservacion = messageResourceService.getCatalogo("CHF_CAT_OBSERV_MX");
    	model.addAttribute("catObservacion", catObservacion);
    	List<MessageResource> catPinchazos = messageResourceService.getCatalogo("CHF_CAT_PINCH_MX");
    	model.addAttribute("catPinchazos", catPinchazos);
		return formName;
	}
    
    @RequestMapping(value = "/editSamp/{codigoCasoVisita}/{codigo}/{tipo}/", method = RequestMethod.GET)
    public String initEditionSamp(Model model, @PathVariable(value = "codigo") String codigo, @PathVariable(value = "codigoCasoVisita") String codigoCasoVisita, @PathVariable("tipo") String tipo){
    	Muestra fcc = this.muestraService.getMuestra(codigo);
    	if (fcc!=null){
    		model.addAttribute("muestra", fcc);
    		String formName="";
        	if (tipo.equals("1")){
        		VisitaSeguimientoCaso vsc = this.visitaSeguimientoCasoService.getVisitaSeguimientoCaso(codigoCasoVisita);
            	model.addAttribute("visita", vsc);
            	formName = "/chfcasos/enterSamp";
        	}
        	else if(tipo.equals("2")){
        		VisitaFinalCaso visFinal = this.visitaFinalCasoService.getVisitaFinalCaso(codigoCasoVisita);
        		model.addAttribute("visita", visFinal);
        		formName = "/chfcasos/enterSampFinal";
        	}
    		List<MessageResource> catTipoMx = messageResourceService.getCatalogo("CHF_CAT_TIP0_MX");
        	catTipoMx.remove(messageResourceService.getMensaje("CHF_CAT_TIP0_MX_09"));
        	model.addAttribute("catTipoMx", catTipoMx);
        	List<MessageResource> catTipoTubo = messageResourceService.getCatalogo("CHF_CAT_TIP_TUBO_MX");
        	catTipoTubo.remove(messageResourceService.getMensaje("CHF_CAT_TIP_TUBO_MX_04"));
        	catTipoTubo.remove(messageResourceService.getMensaje("CHF_CAT_TIP_TUBO_MX_05"));
        	catTipoTubo.remove(messageResourceService.getMensaje("CHF_CAT_TIP_TUBO_MX_09"));
        	model.addAttribute("catTipoTubo", catTipoTubo);
        	List<MessageResource> catSiNo = messageResourceService.getCatalogo("CHF_CAT_SINO");
        	model.addAttribute("catSiNo", catSiNo);
        	List<MessageResource> catNoMxResp = messageResourceService.getCatalogo("CHF_CAT_RAZON_NO_MX_RESP");
        	List<MessageResource> catNoMxSangre = messageResourceService.getCatalogo("CHF_CAT_RAZON_NO_MX");
        	model.addAttribute("catNoMxResp", catNoMxResp);
        	model.addAttribute("catNoMxSangre", catNoMxSangre);
        	List<MessageResource> catObservacion = messageResourceService.getCatalogo("CHF_CAT_OBSERV_MX");
        	model.addAttribute("catObservacion", catObservacion);
        	List<MessageResource> catPinchazos = messageResourceService.getCatalogo("CHF_CAT_PINCH_MX");
        	model.addAttribute("catPinchazos", catPinchazos);
    		return formName;
    	}
    	else{
    		return "404";
        }
    }  
    
    @RequestMapping("/voidSamp/{codigoCasoVisita}/{codigo}/{tipo}/")
    public String initDeletionSamp(@PathVariable("codigo") String codigo, @PathVariable("codigoCasoVisita") String codigoCasoVisita, @PathVariable("tipo") String tipo,
    		RedirectAttributes redirectAttributes) {
    	String redirecTo="404";
    	UserSistema usuarioActual = this.usuarioService.getUser(SecurityContextHolder.getContext().getAuthentication().getName());
    	Muestra sample = this.muestraService.getMuestra(codigo);
    	if (tipo.equals("1")){
    		redirecTo = "redirect:/chf/editarcaso/visitdata/{codigoCasoVisita}/";
    	}
    	else if(tipo.equals("2")){
    		redirecTo = "redirect:/chf/editarcaso/participantdata/{codigoCasoVisita}/";
    	}
    	redirectAttributes.addFlashAttribute("sampNoHabilitado", true);
    	if(sample!=null){
    		sample.setRecordDate(new Date());
    		sample.setRecordUser(usuarioActual.getUsername());
    		sample.setPasive('1');
    		this.muestraService.saveOrUpdate(sample);
    	}
    	return redirecTo;	
    }
    
    @RequestMapping( value="saveSamp", method=RequestMethod.POST)
	public ResponseEntity<String> processSampForm( @RequestParam(value="codigo", required=false, defaultValue="" ) String codigo
			, @RequestParam( value="participante", required=true ) Integer participante
			, @RequestParam( value="fechaVisita", required=true ) String fechaVisita
			, @RequestParam( value="tomaMxSn", required=true ) String tomaMxSn
			, @RequestParam( value="codigoMx", required=false, defaultValue="" ) String codigoMx
			, @RequestParam( value="hora", required=false, defaultValue="" ) String hora
			, @RequestParam( value="horaFin", required=false, defaultValue="" ) String horaFin
			, @RequestParam( value="volumen", required=false, defaultValue="" ) Double volumen
			, @RequestParam( value="observacion", required=false, defaultValue="" ) String observacion
			, @RequestParam( value="descOtraObservacion", required=false, defaultValue="" ) String descOtraObservacion
			, @RequestParam( value="numPinchazos", required=false, defaultValue="" ) String numPinchazos
			, @RequestParam( value="razonNoTomaSang", required=false, defaultValue="" ) String razonNoTomaSang
			, @RequestParam( value="razonNoTomaResp", required=false, defaultValue="" ) String razonNoTomaResp
			, @RequestParam( value="descOtraRazonNoToma", required=false, defaultValue="" ) String descOtraRazonNoToma
			, @RequestParam( value="tubo", required=false, defaultValue="" ) String tubo
			, @RequestParam( value="tipoMuestra", required=false, defaultValue="" ) String tipoMuestra
			, @RequestParam( value="realizaPaxgene", required=false, defaultValue="" ) String realizaPaxgene
			, @RequestParam( value="horaInicioPax", required=false, defaultValue="" ) String horaInicioPax
			, @RequestParam( value="horaFinPax", required=false, defaultValue="" ) String horaFinPax
			
	        ){
    	try{
    		UserSistema usuario = usuarioService.getUser(SecurityContextHolder.getContext().getAuthentication().getName());
    		ParticipanteCohorteFamilia partCHF = participanteCohorteFamiliaService.getParticipanteCHFByCodigo(participante);
    		Muestra muestra = new Muestra();
			//Si el codigo viene en blanco es un nuevo muestra
			if (codigo.equals("")){
				//Crear un nuevo muestra
				codigo = new UUID(usuario.getUsername().hashCode(),new Date().hashCode()).toString();
				muestra.setCodigo(codigo);
				muestra.setParticipante(partCHF.getParticipante());
			}
			//Si el codigo no viene en blanco hay que editar el contacto
			else{
				//Recupera el contacto de la base de datos
				muestra = this.muestraService.getMuestra(codigo);
			}
			SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        	Date date1 = formatter.parse(fechaVisita);
        	muestra.setRecordDate(date1);
        	muestra.setTipoMuestra(tipoMuestra);
        	if(tipoMuestra.equals("5")){
        		muestra.setTubo("5");
        	}
        	else if(tipoMuestra.equals("1")){
        		muestra.setTubo(tubo);
        	}
        	else if(tipoMuestra.equals("2")||tipoMuestra.equals("3")||tipoMuestra.equals("4")){
        		muestra.setTubo("4");
        	}
        	else{
        		muestra.setTubo("9");
        	}
        	muestra.setTomaMxSn(tomaMxSn);
        	muestra.setRazonNoToma(razonNoTomaResp);
        	if(tipoMuestra.equals("1")) muestra.setRazonNoToma(razonNoTomaSang);
        	muestra.setDescOtraRazonNoToma(descOtraRazonNoToma);
        	muestra.setHora(hora);
        	muestra.setCodigoMx(codigoMx);
        	muestra.setVolumen(volumen);
        	muestra.setObservacion(observacion);
        	muestra.setDescOtraObservacion(descOtraObservacion);
        	muestra.setProposito("3");
        	muestra.setNumPinchazos(numPinchazos);
        	muestra.setRealizaPaxgene(realizaPaxgene);
        	muestra.setHoraInicioPax(horaInicioPax);
        	muestra.setHoraFin(horaFinPax);
        	muestra.setRecordUser(usuario.getUsername());
			WebAuthenticationDetails wad  = (WebAuthenticationDetails) SecurityContextHolder.getContext().getAuthentication().getDetails();
        	String idSesion = wad.getSessionId();
        	String direccionIp = wad.getRemoteAddress();
			muestra.setDeviceid(idSesion + "-"+ direccionIp);
			muestra.setEstado('1');
			//Actualiza el muestra
			this.muestraService.saveOrUpdate(muestra);
			return createJsonResponse(muestra);
    	}
    	catch (DataIntegrityViolationException e){
    		String message = e.getMostSpecificCause().getMessage();
    		Gson gson = new Gson();
    	    String json = gson.toJson(message);
    		return new ResponseEntity<String>( json, HttpStatus.CREATED);
    	}
    	catch(Exception e){
    		Gson gson = new Gson();
    	    String json = gson.toJson(e.toString());
    		return new ResponseEntity<String>( json, HttpStatus.CREATED);
    	}
		
	}
    
    @RequestMapping(value = "/visfinal/{codigoCasoParticipante}", method = RequestMethod.GET)
	public String initCreationVisFinal(@PathVariable("codigoCasoParticipante") String codigoCasoParticipante, Model model) {
    	ParticipanteCohorteFamiliaCaso part = this.participanteCohorteFamiliaCasoService.getParticipanteCohorteFamiliaCasosByCodigo(codigoCasoParticipante);
    	model.addAttribute("participante",part);
    	VisitaFinalCaso visFinal = this.visitaFinalCasoService.getVisitaFinalCaso(codigoCasoParticipante);
    	model.addAttribute("visFinal",visFinal);
    	List<MessageResource> sinod = messageResourceService.getCatalogo("CHF_CAT_SND");
    	model.addAttribute("sinod",sinod);
    	List<MessageResource> sino = messageResourceService.getCatalogo("CHF_CAT_SINO");
    	model.addAttribute("sino",sino);
    	List<MessageResource> tratamientos = messageResourceService.getCatalogo("CHF_CAT_TRATAMIENTO");
    	model.addAttribute("tratamientos",tratamientos);
		return "/chfcasos/enterVisFinal";
	}
    
    @RequestMapping( value="saveVisitFinal", method=RequestMethod.POST)
	public ResponseEntity<String> processVisitFinalForm( @RequestParam(value="codigoParticipanteCaso", required=true) String codigoParticipanteCaso
			, @RequestParam( value="fechaVisita", required=true ) String fechaVisita
			, @RequestParam( value="horaVisita", required=true ) String horaVisita
			, @RequestParam( value="enfermo", required=true ) String enfermo
			, @RequestParam( value="consTerreno", required=false, defaultValue="" ) String consTerreno
			, @RequestParam( value="referidoCs", required=false, defaultValue="" ) String referidoCs
			, @RequestParam( value="tratamiento", required=false, defaultValue="" ) String tratamiento
			, @RequestParam( value="cualAntibiotico", required=false, defaultValue="" ) String cualAntibiotico
			, @RequestParam( value="sintResp", required=true ) String sintResp
			, @RequestParam( value="fiebre", required=false, defaultValue="" ) String fiebre
			, @RequestParam( value="tos", required=false, defaultValue="" ) String tos
			, @RequestParam( value="dolorGarganta", required=false, defaultValue="" ) String dolorGarganta
			, @RequestParam( value="secrecionNasal", required=false, defaultValue="" ) String secrecionNasal
			, @RequestParam( value="fif", required=false, defaultValue="" ) String fif
			, @RequestParam( value="fff", required=false, defaultValue="" ) String fff
			, @RequestParam( value="fitos", required=false, defaultValue="" ) String fitos
			, @RequestParam( value="fftos", required=false, defaultValue="" ) String fftos
			, @RequestParam( value="figg", required=false, defaultValue="" ) String figg
			, @RequestParam( value="ffgg", required=false, defaultValue="" ) String ffgg
			, @RequestParam( value="fisn", required=false, defaultValue="" ) String fisn
			, @RequestParam( value="ffsn", required=false, defaultValue="" ) String ffsn
	        ){
    	try{
    		UserSistema usuario = usuarioService.getUser(SecurityContextHolder.getContext().getAuthentication().getName());
    		ParticipanteCohorteFamiliaCaso participante = participanteCohorteFamiliaCasoService.getParticipanteCohorteFamiliaCasosByCodigo(codigoParticipanteCaso);
    		VisitaFinalCaso visFinal = this.visitaFinalCasoService.getVisitaFinalCaso(codigoParticipanteCaso);
    		List<Muestra> muestras = null;
			//Si el visFinal es nulo
			if (visFinal==null){
				//Crear un nuevo visFinal
				visFinal = new VisitaFinalCaso();
				visFinal.setCodigoParticipanteCaso(participante);
			}
			else{
				muestras = muestraService.getMuestrasTx(visFinal.getCodigoParticipanteCaso().getParticipante().getParticipante().getCodigo(),visFinal.getFechaVisita());
			}
			SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        	Date date1 = formatter.parse(fechaVisita+" "+horaVisita);
        	visFinal.setFechaVisita(date1);
        	visFinal.setEnfermo(enfermo);
        	visFinal.setConsTerreno(consTerreno);
        	visFinal.setReferidoCs(referidoCs);
        	visFinal.setTratamiento(tratamiento);
        	visFinal.setCualAntibiotico(cualAntibiotico);
        	visFinal.setSintResp(sintResp);
        	visFinal.setFiebre(fiebre);
        	visFinal.setTos(tos);
        	visFinal.setDolorGarganta(dolorGarganta);
        	visFinal.setSecrecionNasal(secrecionNasal);
        	SimpleDateFormat formatterSintDate = new SimpleDateFormat("dd/MM/yyyy");
        	Date sintDate = null;
        	if (!fif.equals("")){
        		sintDate = formatterSintDate.parse(fif);
        		visFinal.setFif(sintDate);
        	}else{
                visFinal.setFif(null);
            }
        	if (!fff.equals("")){
        		sintDate = formatterSintDate.parse(fff);
        		visFinal.setFff(sintDate);
        	}else{
                visFinal.setFff(null);
            }
        	if (!fitos.equals("")){
        		sintDate = formatterSintDate.parse(fitos);
        		visFinal.setFitos(sintDate);
        	}else{
                visFinal.setFitos(null);
            }
        	if (!fftos.equals("")){
        		sintDate = formatterSintDate.parse(fftos);
        		visFinal.setFftos(sintDate);
        	}else{
                visFinal.setFftos(null);
            }
        	if (!figg.equals("")){
        		sintDate = formatterSintDate.parse(figg);
        		visFinal.setFigg(sintDate);
        	}else{
                visFinal.setFigg(null);
            }
        	if (!ffgg.equals("")){
        		sintDate = formatterSintDate.parse(ffgg);
        		visFinal.setFfgg(sintDate);
        	}else{
                visFinal.setFfgg(null);
            }
        	if (!fisn.equals("")){
        		sintDate = formatterSintDate.parse(fisn);
        		visFinal.setFisn(sintDate);
        	}else{
                visFinal.setFisn(null);
            }
        	if (!ffsn.equals("")){
        		sintDate = formatterSintDate.parse(ffsn);
        		visFinal.setFfsn(sintDate);
        	}else{
                visFinal.setFfsn(null);
            }
        	visFinal.setRecordUser(usuario.getUsername());
        	visFinal.setRecordDate(new Date());
			WebAuthenticationDetails wad  = (WebAuthenticationDetails) SecurityContextHolder.getContext().getAuthentication().getDetails();
        	String idSesion = wad.getSessionId();
        	String direccionIp = wad.getRemoteAddress();
        	visFinal.setDeviceid(idSesion + "-"+ direccionIp);
			visFinal.setEstado('1');
			//Actualiza la visita
			this.visitaFinalCasoService.saveOrUpdateVisitaFinalCasoCaso(visFinal);
			if(muestras!=null){
				for (Muestra muestra:muestras){
					muestra.setRecordDate(date1);
					muestra.setRecordUser(usuario.getUsername());
					muestra.setDeviceid(idSesion + "-"+ direccionIp);
					this.muestraService.saveOrUpdate(muestra);
				}
			}
			return createJsonResponse(visFinal);
    	}
    	catch (DataIntegrityViolationException e){
    		String message = e.getMostSpecificCause().getMessage();
    		Gson gson = new Gson();
    	    String json = gson.toJson(message);
    		return new ResponseEntity<String>( json, HttpStatus.CREATED);
    	}
    	catch(Exception e){
    		Gson gson = new Gson();
    	    String json = gson.toJson(e.toString());
    		return new ResponseEntity<String>( json, HttpStatus.CREATED);
    	}
		
	}
    
    private ResponseEntity<String> createJsonResponse( Object o ){
	    HttpHeaders headers = new HttpHeaders();
	    headers.set("Content-Type", "application/json");
	    Gson gson = new Gson();
	    String json = gson.toJson(o);
	    return new ResponseEntity<String>( json, headers, HttpStatus.CREATED );
	}

       
}
