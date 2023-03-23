package ni.org.ics.estudios.web.controller.muestreoanual;

import ni.org.ics.estudios.service.UsuarioService;
import ni.org.ics.estudios.users.model.Authority;
import ni.org.ics.estudios.users.model.AuthorityId;
import ni.org.ics.estudios.users.model.UserPermissions;
import ni.org.ics.estudios.service.muestreoanual.*;
import ni.org.ics.estudios.users.model.UserSistema;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.StandardPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

/**
 * Controlador web de peticiones relacionadas a usuarios
 * 
 * @author William Aviles
 */
@Controller
@RequestMapping("/usuarios/*")
public class UsuariosMAController {
	@Resource(name="usuarioService")
	private UsuarioService usuarioService;
	@Resource(name="reporteService")
	private ReporteService reporteService;
	private static final Logger logger = LoggerFactory.getLogger(UsuariosMAController.class);
	
	@RequestMapping(value = "/admin/list", method = RequestMethod.GET)
    public String obtenerUsuarios(Model model) throws ParseException {
    	logger.debug("Mostrando Usuarios en JSP");
    	List<UserSistema> usuarios = usuarioService.getUsers();
    	model.addAttribute("usuarios", usuarios);
    	List<Object> muestrasTotal = reporteService.getMuestrasTotal();
        model.addAttribute("muestrasTotal", muestrasTotal);
        List<Object> muestrasxEstudio = reporteService.getMuestrasxEstudio();
        model.addAttribute("muestrasEstudio", muestrasxEstudio);
        List<Object> muestrasxTubo = reporteService.getMuestrasxTubo();
        model.addAttribute("muestrasTubo", muestrasxTubo);
    	return "usuarios/listado";
	}
	
    /**
     * Custom handler for displaying an user.
     *
     * @param username the ID of the user to display
     * @return a ModelMap with the model attributes for the view
     */
    @RequestMapping("/admin/{username}")
    public ModelAndView showUser(@PathVariable("username") String username) {
        ModelAndView mav = new ModelAndView("usuarios/usuario");
        mav.addObject(this.usuarioService.getUser(username));
        return mav;
    }
    
    
    /**
     * Custom handler for enabling an user.
     *
     * @param username the ID of the user to enable
     * @return a String
     */
    @RequestMapping("/admin/{username}/enable")
    public String enableUser(@PathVariable("username") String username, RedirectAttributes redirectAttributes) {
        UserSistema user = this.usuarioService.getUser(username);
    	user.setCreated(new Date());
    	Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        user.setEnabled(true);
    	this.usuarioService.saveUser(user);
    	redirectAttributes.addFlashAttribute("SUCCESS", "Usuario se encuentra activo!");
        return "redirect:/usuarios/admin/{username}";
    }
    
    /**
     * Custom handler for disabling an user.
     *
     * @param username the ID of the user to disable
     * @return a String
     */
    @RequestMapping("/admin/{username}/disable")
    public String disableUser(@PathVariable("username") String username, RedirectAttributes redirectAttributes) {
        UserSistema user = this.usuarioService.getUser(username);
    	Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    	user.setCreated(new Date());
    	user.setEnabled(false);
    	this.usuarioService.saveUser(user);
    	redirectAttributes.addFlashAttribute("SUCCESS", "Usuario se encuentra inactivo!");
        return "redirect:/usuarios/admin/{username}";
    }
    
    /**
     * Custom handler for admin an user.
     *
     * @param username the ID of the user to convert to admin
     * @return a String
     */
    @RequestMapping("/admin/{username}/mkadmin")
    public String adminUser(@PathVariable("username") String username, RedirectAttributes redirectAttributes) {
        UserSistema user = this.usuarioService.getUser(username);
    	user.setCreated(new Date());
    	Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    	AuthorityId authId = new AuthorityId();
    	authId.setUsername(user.getUsername());
    	authId.setAuthority("ROLE_ADMIN");
    	Authority auth = new Authority();
    	auth.setAuthId(authId);
    	auth.setUser(user);
    	this.usuarioService.saveRoleUser(auth);
        this.usuarioService.saveUser(user);
        redirectAttributes.addFlashAttribute("SUCCESS", "Usuario es administrador!");
        return "redirect:/usuarios/admin/{username}";
    }
    
    /**
     * Custom handler for super an user.
     *
     * @param username the ID of the user to convert to supervisor
     * @return a String
     */
    @RequestMapping("/admin/{username}/mksup")
    public String supUser(@PathVariable("username") String username, RedirectAttributes redirectAttributes) {
        UserSistema user = this.usuarioService.getUser(username);
    	user.setCreated(new Date());
    	Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        user.setModifiedBy(authentication.getName());
    	AuthorityId authId = new AuthorityId();
    	authId.setUsername(user.getUsername());
    	authId.setAuthority("ROLE_SUPER");
    	Authority auth = new Authority();
    	auth.setAuthId(authId);
    	auth.setUser(user);
    	this.usuarioService.saveRoleUser(auth);
        this.usuarioService.saveUser(user);
        redirectAttributes.addFlashAttribute("SUCCESS", "Usuario es supervisor!");
        return "redirect:/usuarios/admin/{username}";
    }
    
    /**
     * Custom handler for web an user.
     *
     * @param username the ID of the user to convert to supervisor
     * @return a String
     */
    @RequestMapping("/admin/{username}/mkweb")
    public String webUser(@PathVariable("username") String username, RedirectAttributes redirectAttributes) {
        UserSistema user = this.usuarioService.getUser(username);
    	user.setCreated(new Date());
    	Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        user.setModifiedBy(authentication.getName());
    	AuthorityId authId = new AuthorityId();
    	authId.setUsername(user.getUsername());
    	authId.setAuthority("ROLE_WEB");
    	Authority auth = new Authority();
    	auth.setAuthId(authId);
    	auth.setUser(user);
    	this.usuarioService.saveRoleUser(auth);
        this.usuarioService.saveUser(user);
        redirectAttributes.addFlashAttribute("SUCCESS", "Usuario es web!");
        return "redirect:/usuarios/admin/{username}";
    }
    
    /**
     * Custom handler for terr an user.
     *
     * @param username the ID of the user to convert to terreno
     * @return a String
     */
    @RequestMapping("/admin/{username}/mkterr")
    public String terrUser(@PathVariable("username") String username, RedirectAttributes redirectAttributes) {
        UserSistema user = this.usuarioService.getUser(username);
    	user.setCreated(new Date());
    	Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        user.setModifiedBy(authentication.getName());
    	AuthorityId authId = new AuthorityId();
    	authId.setUsername(user.getUsername());
    	authId.setAuthority("ROLE_MOVIL");
    	Authority auth = new Authority();
    	auth.setAuthId(authId);
    	auth.setUser(user);
    	this.usuarioService.saveRoleUser(auth);
        this.usuarioService.saveUser(user);
        redirectAttributes.addFlashAttribute("SUCCESS", "Usuario es movil!");
        return "redirect:/usuarios/admin/{username}";
    }
    
    /**
     * Custom handler for no admin an user.
     *
     * @param username the ID of the user to un-admin
     * @return a String
     */
    @RequestMapping("/admin/{username}/noadmin")
    public String noAdminUser(@PathVariable("username") String username, RedirectAttributes redirectAttributes) {
        UserSistema user = this.usuarioService.getUser(username);
    	user.setCreated(new Date());
    	Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        user.setModifiedBy(authentication.getName());
        Integer borrado = this.usuarioService.deleteRoleAdmin(username);
    	logger.debug("Borrado ROLE_ADMIN "+borrado);
        this.usuarioService.saveUser(user);
        redirectAttributes.addFlashAttribute("SUCCESS", "Usuario ya no es administrador!");
        return "redirect:/usuarios/admin/{username}";
    }
    
    /**
     * Custom handler for no super an user.
     *
     * @param username the ID of the user to un-super
     * @return a String
     */
    @RequestMapping("/admin/{username}/nosup")
    public String noSupUser(@PathVariable("username") String username, RedirectAttributes redirectAttributes) {
        UserSistema user = this.usuarioService.getUser(username);
    	user.setCreated(new Date());
    	Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        user.setModifiedBy(authentication.getName());
        Integer borrado = this.usuarioService.deleteRoleSuper(username);
    	logger.debug("Borrado ROLE_SUPER "+borrado);
        this.usuarioService.saveUser(user);
        redirectAttributes.addFlashAttribute("SUCCESS", "Usuario ya no es supervisor!");
        return "redirect:/usuarios/admin/{username}";
    }
    
    /**
     * Custom handler for no web an user.
     *
     * @param username the ID of the user to un-web
     * @return a String
     */
    @RequestMapping("/admin/{username}/noweb")
    public String noWebUser(@PathVariable("username") String username, RedirectAttributes redirectAttributes) {
        UserSistema user = this.usuarioService.getUser(username);
    	user.setCreated(new Date());
    	Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        user.setModifiedBy(authentication.getName());
        Integer borrado = this.usuarioService.deleteRoleWeb(username);
    	logger.debug("Borrado ROLE_WEB "+borrado);
        this.usuarioService.saveUser(user);
        redirectAttributes.addFlashAttribute("SUCCESS", "Usuario ya no es web!");
        return "redirect:/usuarios/admin/{username}";
    }
    
    /**
     * Custom handler for no terreno an user.
     *
     * @param username the ID of the user to un-terreno
     * @return a String
     */
    @RequestMapping("/admin/{username}/noterr")
    public String noTerrUser(@PathVariable("username") String username, RedirectAttributes redirectAttributes) {
        UserSistema user = this.usuarioService.getUser(username);
    	user.setCreated(new Date());
    	Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        user.setModifiedBy(authentication.getName());
        Integer borrado = this.usuarioService.deleteRoleTerreno(username);
    	logger.debug("Borrado ROLE_MOVIL "+borrado);
        this.usuarioService.saveUser(user);
        redirectAttributes.addFlashAttribute("SUCCESS", "Usuario ya no es de terreno!");
        return "redirect:/usuarios/admin/{username}";
    }
    
    
    @RequestMapping(value = "/admin/{username}/edit", method = RequestMethod.GET)
    public String initUpdateUserForm(@PathVariable("username") String username, Model model) {
        UserSistema user = this.usuarioService.getUser(username);
        model.addAttribute(user);
        return "usuarios/UpdateUserForm";
    }
    
    @RequestMapping(value = "/admin/{username}/edit", method = RequestMethod.PUT)
    public String processUpdateUserForm(@Valid UserSistema userSistema, BindingResult result, SessionStatus status, RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            return "usuarios/UpdateUserForm";
        } else {
        	userSistema.setCreated(new Date());
        	Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            userSistema.setModifiedBy(authentication.getName());
            this.usuarioService.saveUser(userSistema);
            status.setComplete();
            redirectAttributes.addFlashAttribute("SUCCESS", "Cambios guardados correctamente!");
            return "redirect:/usuarios/admin/{username}";
        }
    }
    
    
    @RequestMapping(value = "/admin/{username}/chgpass", method = RequestMethod.GET)
    public String initChgPassUserForm(@PathVariable("username") String username, Model model) {
        UserSistema user = this.usuarioService.getUser(username);
        model.addAttribute(user);
        return "usuarios/ChgPassForm";
    }
    
    
    @RequestMapping(value = "/admin/{username}/chgpass", method = RequestMethod.PUT)
    public String processChgPassUserForm(@Valid UserSistema userSistema, BindingResult result, SessionStatus status, RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            return "usuarios/ChgPassForm";
        } else {
        	userSistema.setCreated(new Date());
        	Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            userSistema.setModifiedBy(authentication.getName());
            StandardPasswordEncoder encoder = new StandardPasswordEncoder();
            String encodedPass = encoder.encode(userSistema.getPassword());
            userSistema.setPassword(encodedPass);
        	this.usuarioService.saveUser(userSistema);
            status.setComplete();
            redirectAttributes.addFlashAttribute("SUCCESS", "Contraseña cambiada correctamente!");
            return "redirect:/usuarios/admin/{username}";
        }
    }
    
    @RequestMapping(value = "/mod/password", method = RequestMethod.GET)
    public String initChgPassUser2Form(Model model) {
    	Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserSistema user = this.usuarioService.getUser(authentication.getName());
        model.addAttribute(user);
        return "usuarios/ChgPassUser";
    }
    
    @RequestMapping(value = "/mod/password", method = RequestMethod.PUT)
    public String processChgPassUser2Form(@Valid UserSistema userSistema, BindingResult result, SessionStatus status, RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            return "usuarios/ChgPassUser";
        } else {
        	userSistema.setCreated(new Date());
            userSistema.setModifiedBy(userSistema.getUsername());
            StandardPasswordEncoder encoder = new StandardPasswordEncoder();
            String encodedPass = encoder.encode(userSistema.getPassword());
            userSistema.setPassword(encodedPass);
        	this.usuarioService.saveUser(userSistema);
            status.setComplete();
            redirectAttributes.addFlashAttribute("SUCCESS", "Contraseña cambiada correctamente!");
            return "redirect:/";
        }
    }
    
    @RequestMapping(value = "/admin/new", method = RequestMethod.GET)
    public String initCreationForm(Model model) {
        UserPermissions userPermissions = new UserPermissions();
        model.addAttribute(userPermissions);
        return "usuarios/CreateUserForm";
    }
    
    @RequestMapping(value = "/admin/new", method = RequestMethod.POST)
    public String processCreationForm(@Valid UserSistema userSistema, BindingResult result, SessionStatus status) {
        if (result.hasErrors()) {
            return "usuarios/CreateUserForm";
        }
        else if (this.usuarioService.checkUser(userSistema.getUsername())){
        	result.rejectValue("username", "error.user", "Ya existe este nombre de usuario en la base de datos");
        	return "usuarios/CreateUserForm";
        } else {
        	userSistema.setCreated(new Date());
        	Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            userSistema.setModifiedBy(authentication.getName());
            StandardPasswordEncoder encoder = new StandardPasswordEncoder();
            String encodedPass = encoder.encode(userSistema.getPassword());
            userSistema.setPassword(encodedPass);
            this.usuarioService.saveUser(userSistema);
            status.setComplete();
            return "redirect:/usuarios/admin/"+ userSistema.getUsername();
        }
    }
	
}
