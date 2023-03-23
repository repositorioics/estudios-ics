package ni.org.ics.estudios.movil.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.Resource;

import ni.org.ics.estudios.language.MessageResource;
import ni.org.ics.estudios.service.MessageResourceService;
import ni.org.ics.estudios.service.UsuarioService;
import ni.org.ics.estudios.users.model.Authority;
import ni.org.ics.estudios.users.model.UserPermissions;
import ni.org.ics.estudios.users.model.UserSistema;

import ni.org.ics.estudios.service.RoleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Maneja las solicitudes que van a la aplicacion relacionadas a json
 * 
 * @author Brenda Lopez
 */
@Controller
@RequestMapping("/movil/*")
public class UsuariosMovilController {
	@Resource(name="usuarioService")
	private UsuarioService usuarioService;

    @Resource(name="roleService")
    private RoleService roleService;

    @Resource(name = "messageResourceService")
    private MessageResourceService messageResourceService;

	private static final Logger logger = LoggerFactory.getLogger(UsuariosMovilController.class);
	
	
	@RequestMapping(value = "ingreso/{username}", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody UserSistema getMessage(@PathVariable String username) {
    	logger.info("Accessando a la aplicacion " + username);
    	return usuarioService.getUser(username);
    }

    /**
     * Retorna usuarios. Acepta una solicitud GET para JSON
     * @return barrios JSON
     */
    @RequestMapping(value = "usuarios", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    List<UserSistema> descargarUsuarios() {
        logger.info("Descargando toda la informacion de los datos de los usuarios");
        return usuarioService.getUsers();
    }

    /**
     * Retorna roles. Acepta una solicitud GET para JSON
     * @return barrios JSON
     */
    @RequestMapping(value = "roles", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody List<Authority> descargarRoles() {
        logger.info("Descargando toda la informacion de los datos de los roles");
        List<Authority> roles = roleService.getRoles();
        if (roles == null){
            logger.debug(new Date() + " - Nulo");
        }
        else{
            for (Authority auth : roles) {
                auth.setUser(null);
                auth.setRol(null);
            }
        }
        return roles;
    }

    /**
     * Retorna roles. Acepta una solicitud GET para JSON
     * @return barrios JSON
     */
    @RequestMapping(value = "roles/{username}", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody List<Authority> descargarRolesUsuario(@PathVariable String username) {
        logger.info("Descargando toda la informacion de los datos de los roles para el usuario "+username);
        List<Authority> roles = usuarioService.getRolesUsuario(username);
        if (roles == null){
        	logger.debug(new Date() + " - Nulo");
        }
        else{
        	for (Authority auth : roles) {
        		auth.setUser(null);
        		auth.setRol(null);
        	}
        }
        return roles;	
    }

    /**
     * Retorna usuarios. Acepta una solicitud GET para JSON
     * @return String JSON
     */
    @RequestMapping(value = "role/{username}", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    Boolean checkRole(@PathVariable String username) {
        logger.info("Verificando Rol de Admin");
        return roleService.checRoleAdmin(username);
    }

    /**
     * Retorna roles. Acepta una solicitud GET para JSON
     * @return barrios JSON
     */
    @RequestMapping(value = "permisos", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody List<UserPermissions> descargarPermisos() {
        logger.info("Descargando toda la informacion de los datos de los permisos");
        List<UserPermissions> permisos = usuarioService.getUserPermissions();
        if (permisos == null){
            logger.debug(new Date() + " - Nulo");
        }
        return permisos;
    }

    /**
     * Retorna roles. Acepta una solicitud GET para JSON
     * @return barrios JSON
     */
    @RequestMapping(value = "permisos/{username}", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody UserPermissions descargarPermisosUsuario(@PathVariable String username) {
        logger.info("Descargando toda la informacion de los permisos para el usuario "+username);
        return  usuarioService.getUserPermissions(username);
    }

    @RequestMapping(value = "pin/{pinUser}", method = RequestMethod.GET, produces = "text/plain")
    public @ResponseBody String verificarPin(@PathVariable String pinUser) {
        logger.info("Verificando pin de descarga " + pinUser);
        MessageResource pinDownload = messageResourceService.getMensaje("PIN_DOWNLOAD");
        if (pinDownload!=null && pinDownload.getSpanish().equalsIgnoreCase(pinUser))
            return "OK";
        else
            return "NO";
    }
}
