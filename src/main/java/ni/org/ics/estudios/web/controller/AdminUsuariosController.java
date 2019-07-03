package ni.org.ics.estudios.web.controller;

import com.google.gson.Gson;
import ni.org.ics.estudios.domain.audit.AuditTrail;
import ni.org.ics.estudios.domain.catalogs.Estudio;
import ni.org.ics.estudios.domain.relationships.UserStudy;
import ni.org.ics.estudios.service.AuditTrailService;
import ni.org.ics.estudios.service.EstudioService;
import ni.org.ics.estudios.service.UsuarioService;
import ni.org.ics.estudios.users.model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.StandardPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.Resource;
import java.lang.reflect.Type;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Controlador web de peticiones relacionadas a usuarios
 * 
 * @author William Avil�s
 */
@Controller
@RequestMapping("/admin/users/*")
public class AdminUsuariosController {
	private static final Logger logger = LoggerFactory.getLogger(AdminUsuariosController.class);
	@Resource(name="usuarioService")
	private UsuarioService usuarioService;
	@Resource(name="auditTrailService")
	private AuditTrailService auditTrailService;
    @Resource(name="estudioService")
    private EstudioService estudioService;
    
	@RequestMapping(value = "/", method = RequestMethod.GET)
    public String obtenerUsuarios(Model model) throws ParseException { 	
    	logger.debug("Mostrando Usuarios en JSP");
    	List<UserSistema> usuarios = usuarioService.getUsers();
    	model.addAttribute("usuarios", usuarios);
    	return "admin/users/list";
	}
	
	
	/**
     * Custom handler for adding an user.
     *
     * @return a ModelMap with the model attributes for the view
     */
    @RequestMapping(value = "newUser", method = RequestMethod.GET)
	public String initAddUserForm(Model model) {
    	List<Rol> roles = usuarioService.getRoles();
        List<Estudio> estudios = usuarioService.getEstudios();
	    model.addAttribute("roles", roles);
        model.addAttribute("estudios", estudios);
	    model.addAttribute("agregando",true);
        model.addAttribute("editando",false);
		return "admin/users/enterForm";
	}
	
	/**
     * Custom handler for editing an user.
     *
     * @param username the ID of the user to display
     * @return a ModelMap with the model attributes for the view
     */
    @RequestMapping(value = "editUser/{username}", method = RequestMethod.GET)
	public String initUpdateUserForm(@PathVariable("username") String username, Model model) {
		UserSistema usuarioEditar = this.usuarioService.getUser(username);
        UserPermissions permissions = this.usuarioService.getUserPermissions(username);
		if(usuarioEditar!=null){
			model.addAttribute("user",usuarioEditar);
			List<Rol> roles = usuarioService.getRoles();
			List<Estudio> estudios = usuarioService.getEstudios();
	    	model.addAttribute("roles", roles);
	    	model.addAttribute("estudios", estudios);
	    	List<Authority> rolesusuario = this.usuarioService.getRolesUsuario(username);
	    	List<UserStudy> estudiosusuario = this.usuarioService.getEstudiosUsuario(username);
            model.addAttribute("permisos", permissions);
	    	model.addAttribute("rolesusuario", rolesusuario);
	    	model.addAttribute("estudiosusuario", estudiosusuario);
	    	model.addAttribute("editando",true);
            model.addAttribute("agregando",false);
			return "admin/users/enterForm";
		}
		else{
			return "403";
		}
	}
    
    /* Mapeo de Campo por campo value debe coincidir con mi name y id de mi control */
    @RequestMapping( value="saveUser", method=RequestMethod.POST)
	public ResponseEntity<String> processUpdateUserForm(
              @RequestParam(value="username", required=true ) String userName
	        , @RequestParam( value="completeName", required=true ) String completeName
	        , @RequestParam( value="confirm_password", required=false ) String confirmPassword
	        , @RequestParam( value="password", required=false, defaultValue="" ) String password
	        , @RequestParam( value="email", required=true, defaultValue="" ) String email
	        , @RequestParam( value="authorities", required=false, defaultValue="") List<String> authorities
	        , @RequestParam( value="studies", required=false, defaultValue="") List<String> studies
            , @RequestParam( value="chk_muestra", required=false, defaultValue="" ) String chkMuestra
            , @RequestParam( value="chk_vacunas", required=false, defaultValue="" ) String chkVacunas
            , @RequestParam( value="chk_consent", required=false, defaultValue="" ) String chkConsent
            , @RequestParam( value="chk_recepcion", required=false, defaultValue="" ) String chkRecepcion
            , @RequestParam( value="chk_visitas", required=false, defaultValue="" ) String chkVisitas
            , @RequestParam( value="chk_obsequio", required=false, defaultValue="" ) String chkObsequio
            , @RequestParam( value="chk_pesotalla", required=false, defaultValue="" ) String chkPesotalla
            , @RequestParam( value="chk_datosparto", required=false, defaultValue="" ) String chkDatosparto
            , @RequestParam( value="chk_ecasa", required=false, defaultValue="" ) String chkEcasa
            , @RequestParam( value="chk_eparticipante", required=false, defaultValue="" ) String chkEparticipante
            , @RequestParam( value="chk_elactancia", required=false, defaultValue="" ) String chkElactancia
            , @RequestParam( value="chk_esatisfaccion", required=false, defaultValue="" ) String chkEsatisfaccion
	        )
	{
    	try{
	    	UserSistema usuarioActual = this.usuarioService.getUser(SecurityContextHolder.getContext().getAuthentication().getName());
			UserSistema user = this.usuarioService.getUser(userName);
	    	if (user==null){
	    		user = new UserSistema();
	    		user.setUsername(userName);
	    		user.setCompleteName(completeName);
	    		user.setEmail(email);
	    		user.setCreated(new Date());
	    		user.setCreatedBy(usuarioActual.getUsername());
	    		user.setModified(new Date());
	    		user.setModifiedBy(usuarioActual.getUsername());
	    		user.setLastCredentialChange(new Date());
	    		StandardPasswordEncoder encoder = new StandardPasswordEncoder();
	    		String encodedPass = encoder.encode(password);
	    		user.setPassword(encodedPass);
	    		this.usuarioService.saveUser(user);

                //Permisos del usuario
                UserPermissions permissions = new UserPermissions();
                permissions.setUsername(user.getUsername());
                permissions.setMuestra(!chkMuestra.isEmpty());
                permissions.setVacunas(!chkVacunas.isEmpty());
                permissions.setConsentimiento(!chkConsent.isEmpty());
                permissions.setRecepcion(!chkRecepcion.isEmpty());
                permissions.setVisitas(!chkVisitas.isEmpty());
                permissions.setObsequio(!chkObsequio.isEmpty());
                permissions.setPesoTalla(!chkPesotalla.isEmpty());
                permissions.setDatosparto(!chkDatosparto.isEmpty());
                permissions.setEncuestaCasa(!chkEcasa.isEmpty());
                permissions.setEncuestaParticipante(!chkEparticipante.isEmpty());
                permissions.setEncuestaLactancia(!chkElactancia.isEmpty());
                permissions.setEncuestaSatisfaccion(!chkEsatisfaccion.isEmpty());
                this.usuarioService.saveUserPermissions(permissions);

	    		for(String a:authorities){
	    			Authority auth = new Authority();
	    			auth.setAuthId(new AuthorityId(userName,a));
					auth.setRecordUser(usuarioActual.getUsername());
					auth.setRecordDate(new Date());
					this.usuarioService.saveRoleUser(auth);
				}
	    		for(String codEstudio :studies){
	    			UserStudy uc = new UserStudy();
                    uc.setUsuario(user);
	    			uc.setEstudio(estudioService.getEstudioByCodigo(Integer.valueOf(codEstudio)));
					uc.setRecordUser(usuarioActual.getUsername());
					uc.setRecordDate(new Date());
					this.usuarioService.saveUserStudies(uc);
				}
	    	}
	    	else{
				user.setModifiedBy(usuarioActual.getUsername());
				user.setCompleteName(completeName);
				user.setEmail(email);
				user.setModified(new Date());
				this.usuarioService.saveUser(user);

                //Permisos del usuario
                UserPermissions permissions = usuarioService.getUserPermissions(userName);
                if (permissions==null) permissions = new UserPermissions();
                permissions.setUsername(user.getUsername());
                permissions.setMuestra(!chkMuestra.isEmpty());
                permissions.setVacunas(!chkVacunas.isEmpty());
                permissions.setConsentimiento(!chkConsent.isEmpty());
                permissions.setRecepcion(!chkRecepcion.isEmpty());
                permissions.setVisitas(!chkVisitas.isEmpty());
                permissions.setObsequio(!chkObsequio.isEmpty());
                permissions.setPesoTalla(!chkPesotalla.isEmpty());
                permissions.setDatosparto(!chkDatosparto.isEmpty());
                permissions.setEncuestaCasa(!chkEcasa.isEmpty());
                permissions.setEncuestaParticipante(!chkEparticipante.isEmpty());
                permissions.setEncuestaLactancia(!chkElactancia.isEmpty());
                permissions.setEncuestaSatisfaccion(!chkEsatisfaccion.isEmpty());
                this.usuarioService.saveUserPermissions(permissions);

				//Recupera los roles activos de este usuario de la base de datos y pone el username en una lista
				List<String> rolesUsuario = new ArrayList<String>();
				List<Authority> rolesusuario = this.usuarioService.getRolesUsuario(userName);
				for(Authority rolActual:rolesusuario){
					rolesUsuario.add(rolActual.getAuthId().getAuthority());
				}
				//Recorre los roles seleccionados en el formulario
				for(String a:authorities){
					boolean encontreRolBD = false;
					//Recorre los roles actuales del usuario
					for(String rActual:rolesUsuario){
						if(rActual.equals(a)){
							encontreRolBD=true;
							break;
						}
					}
					//Si no encuentra el rol seleccionado en los roles actuales ingresa un nuevo rol o lo actualiza
					if (!encontreRolBD){
						Authority nuevoRol = new Authority(new AuthorityId(userName,a), new Date(), usuarioActual.getUsername());
						this.usuarioService.saveRoleUser(nuevoRol);
					}
				}
				//Recorre los roles actuales
				for(String rActual:rolesUsuario){
					boolean encontreRolForm = false;
					//Recorre los roles seleccionados en el formulario
					for(String r:authorities){
						if(rActual.equals(r)){
							encontreRolForm=true;
							break;
						}
					}
					//Si no encuentra el rol actual en los roles seleccionados lo pone en pasivo
					if (!encontreRolForm){
						Authority rol = this.usuarioService.getRolUsuario(userName,rActual);
						rol.setPasive('1');
						this.usuarioService.saveRoleUser(rol);
					}
				}
				//Recupera los estudios activos de este usuario de la base de datos y pone el estudio en una lista
				List<String> centrosUsuario = new ArrayList<String>();
				List<UserStudy> estudiosUsuario = this.usuarioService.getEstudiosUsuario(userName);
				for(UserStudy centroActual:estudiosUsuario){
					centrosUsuario.add(centroActual.getEstudio().getCodigo().toString());
				}
				//Recorre los estudios seleccionados en el formulario
				for(String est :studies){
					boolean encontreCentroBD = false;
					//Recorre los estudios actuales del usuario
					for(String estActual :centrosUsuario){
						if(estActual.equals(est)){
							encontreCentroBD=true;
							break;
						}
					}
					//Si no encuentra el estudio seleccionado en los estudios actuales ingresa un nuevo estudio o lo actualiza
					if (!encontreCentroBD){
                        UserStudy uc = new UserStudy();
                        uc.setUsuario(user);
                        uc.setEstudio(estudioService.getEstudioByCodigo(Integer.valueOf(est)));
                        uc.setRecordUser(usuarioActual.getUsername());
                        uc.setRecordDate(new Date());
                        this.usuarioService.saveUserStudies(uc);
					}
				}
				//Recorre los estudios actuales
				for(String cActual:centrosUsuario){
					boolean encontreCentroForm = false;
					//Recorre los estudios seleccionados en el formulario
					for(String c:studies){
						if(cActual.equals(c)){
							encontreCentroForm=true;
							break;
						}
					}
					//Si no encuentra el centro actual en los centros seleccionados lo pone en pasivo
					if (!encontreCentroForm){
						UserStudy centro = this.usuarioService.getEstudioUsuario(userName, Integer.valueOf(cActual));
						centro.setPasive('1');
						this.usuarioService.saveUserStudies(centro);
					}
				}
	    	}
			
			return createJsonResponse(user);
    	}
    	catch(Exception e){
    		Gson gson = new Gson();
    	    String json = gson.toJson(e.toString());
    		return new ResponseEntity<String>( json, HttpStatus.CREATED);
    	}
	}
    
    /**
     * Custom handler for enabling/disabling an user.
     *
     * @param username the ID of the user to enable
     * @return a String
     */
    @RequestMapping("/habdes/{accion}/{username}")
    public String enableUser(@PathVariable("username") String username, 
    		@PathVariable("accion") String accion, RedirectAttributes redirectAttributes) {
    	String redirecTo="404";
    	boolean hab;
    	if (accion.matches("enable1")){
    		redirecTo = "redirect:/admin/users/";
    		hab = true;
    		redirectAttributes.addFlashAttribute("usuarioHabilitado", true);
        }
        else if (accion.matches("enable2")){
        	redirecTo = "redirect:/admin/users/{username}";
    		hab = true;
    		redirectAttributes.addFlashAttribute("usuarioHabilitado", true);
        }
        else if(accion.matches("disable1")){
        	redirecTo = "redirect:/admin/users/";
    		hab = false;
    		redirectAttributes.addFlashAttribute("usuarioDeshabilitado", true);
        }
        else if(accion.matches("disable2")){
        	redirecTo = "redirect:/admin/users/{username}";
    		hab = false;
    		redirectAttributes.addFlashAttribute("usuarioDeshabilitado", true);
        }
        else{
        	return redirecTo;
        }
    	UserSistema usuarioActual = this.usuarioService.getUser(SecurityContextHolder.getContext().getAuthentication().getName());
		UserSistema user = this.usuarioService.getUser(username);
    	if(user!=null){
    		user.setModified(new Date());
    		user.setModifiedBy(usuarioActual.getUsername());
    		user.setEnabled(hab);
    		this.usuarioService.saveUser(user);
    		redirectAttributes.addFlashAttribute("nombreUsuario", username);
    	}
    	else{
    		redirecTo = "403";
    	}
    	return redirecTo;	
    }
    
    /**
     * Custom handler for locking/unlocking an user.
     *
     * @param username the ID of the user to lock/unlock
     * @return a String
     */
    @RequestMapping("/lockunl/{accion}/{username}")
    public String lockUnlockUser(@PathVariable("username") String username, 
    		@PathVariable("accion") String accion, RedirectAttributes redirectAttributes) {
    	String redirecTo="404";
    	boolean lock;
    	if (accion.matches("lock1")){
    		redirecTo = "redirect:/admin/users/";
    		lock = true;
    		redirectAttributes.addFlashAttribute("usuarioBloqueado", true);
        }
        else if (accion.matches("lock2")){
        	redirecTo = "redirect:/admin/users/{username}";
    		lock = true;
    		redirectAttributes.addFlashAttribute("usuarioBloqueado", true);
        }
        else if(accion.matches("unlock1")){
        	redirecTo = "redirect:/admin/users/";
    		lock = false;
    		redirectAttributes.addFlashAttribute("usuarioNoBloqueado", true);
        }
        else if(accion.matches("unlock2")){
        	redirecTo = "redirect:/admin/users/{username}";
    		lock = false;
    		redirectAttributes.addFlashAttribute("usuarioNoBloqueado", true);
        }
        else{
        	return redirecTo;
        }
    	UserSistema usuarioActual = this.usuarioService.getUser(SecurityContextHolder.getContext().getAuthentication().getName());
		UserSistema user = this.usuarioService.getUser(username);
    	if(user!=null){
    		user.setModified(new Date());
    		user.setModifiedBy(usuarioActual.getUsername());
    		user.setAccountNonLocked(!lock);
    		this.usuarioService.saveUser(user);
    		redirectAttributes.addFlashAttribute("nombreUsuario", username);
    	}
    	else{
    		redirecTo = "403";
    	}
    	return redirecTo;	
    }
    
    /**
     * Custom handler for displaying an user.
     *
     * @param username the ID of the user to display
     * @return a ModelMap with the model attributes for the view
     */
    @RequestMapping("/{username}")
    public ModelAndView showUser(@PathVariable("username") String username) {
    	ModelAndView mav;
    	UserSistema user = this.usuarioService.getUser(username);
        if(user==null){
        	mav = new ModelAndView("403");
        }
        else{
        	mav = new ModelAndView("admin/users/user");
            List<UserAccess> accesosUsuario = usuarioService.getUserAccess(username);
            List<AuditTrail> bitacoraUsuario = auditTrailService.getBitacora(username);
            UserPermissions permissions = usuarioService.getUserPermissions(username);
            mav.addObject("user",user);
            mav.addObject("permisos",permissions);
            mav.addObject("accesses",accesosUsuario);
            mav.addObject("bitacora",bitacoraUsuario);
            List<Authority> rolesusuario = this.usuarioService.getRolesUsuarioTodos(username);
            mav.addObject("rolesusuario", rolesusuario);
            List<UserStudy> estudiosusuario = this.usuarioService.getAllEstudiosUsuario(username);
            mav.addObject("estudiosUsuario", estudiosusuario);
        }
        return mav;
    }
    
    /**
     * Custom handler for changing an user password.
     *
     * @param username the ID of the user to display
     * @return a ModelMap with the model attributes for the view
     */
    @RequestMapping(value = "chgpass/{username}", method = RequestMethod.GET)
	public String initChangePassForm(@PathVariable("username") String username, Model model) {
    	UserSistema usertoChange = this.usuarioService.getUser(username);
		if(usertoChange!=null){
			model.addAttribute("user",usertoChange);
			return "admin/users/chgpass";
		}
		else{
			return "403";
		}
	}
    
    @RequestMapping( value="chgPass", method=RequestMethod.POST)
	public ResponseEntity<String> processChangePassForm( @RequestParam(value="username", required=true ) String userName
			, @RequestParam( value="password", required=true ) String password
	        )
	{
    	UserSistema usuario = usuarioService.getUser(SecurityContextHolder.getContext().getAuthentication().getName());
    	UserSistema user = usuarioService.getUser(userName);
    	try{
			user.setModifiedBy(usuario.getUsername());
			user.setModified(new Date());
			StandardPasswordEncoder encoder = new StandardPasswordEncoder();
			String encodedPass = encoder.encode(password);
			user.setPassword(encodedPass);
			user.setLastCredentialChange(new Date());
			this.usuarioService.saveUser(user);
			return createJsonResponse(user);
    	}
    	catch(Exception e){
    		Gson gson = new Gson();
    	    String json = gson.toJson(e.toString());
    		return new ResponseEntity<String>( json, HttpStatus.CREATED);
    	}
	}
    
    private ResponseEntity<String> createJsonResponse( Object o )
	{
	    HttpHeaders headers = new HttpHeaders();
	    headers.set("Content-Type", "application/json");
	    Gson gson = new Gson();
	    String json = gson.toJson(o);
	    return new ResponseEntity<String>( json, headers, HttpStatus.CREATED );
	}
	
}
