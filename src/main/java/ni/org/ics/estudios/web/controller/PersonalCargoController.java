package ni.org.ics.estudios.web.controller;

import com.google.gson.Gson;
import ni.org.ics.estudios.domain.catalogs.Cargo;
import ni.org.ics.estudios.domain.catalogs.Personal;
import ni.org.ics.estudios.domain.catalogs.Personal_Cargo;
import ni.org.ics.estudios.dto.CargoDto;
import ni.org.ics.estudios.dto.PersonalCargoDto;
import ni.org.ics.estudios.service.PersonalCargoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.Resource;
import java.io.Serializable;
import java.net.InetAddress;
import java.text.ParseException;
import java.util.*;

/**
 * Created by ICS on 19/11/2021.
 */

@Controller
@RequestMapping("/admin/personal/*")
public class PersonalCargoController implements Serializable {

    @Resource(name = "personalCargoService")
    private PersonalCargoService personalCargoService;
    private static final Logger logger = LoggerFactory.getLogger(PersonalCargoController.class);

    //admin/personal/list
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String obtenerUsuarios(Model model) throws ParseException {
        List<Personal>personals = this.personalCargoService.getAllPersonals();
        List<PersonalCargoDto> personalCargoDtoList = new ArrayList<PersonalCargoDto>();
        for (Personal p: personals){
            PersonalCargoDto objdto = new PersonalCargoDto();
            objdto.setCodigo(p.getIdpersonal());
            objdto.setNombre(p.getNombreApellido());
            objdto.setPasive(p.getPasive());
            if (p.getPasive()=='1'){
                objdto.setEstado(false);
            }else{
                objdto.setEstado(true);
            }
            HashSet<CargoDto> cargoList = new HashSet<CargoDto>();
            List<Personal_Cargo> per = this.personalCargoService.getPersonalCargoListById(p.getIdpersonal());
            for (Personal_Cargo pc: per){
                CargoDto cargoDto = new CargoDto();
                cargoDto.setCodigoCargo(pc.getCargo().getIdcargo());
                cargoDto.setNombreCargo(pc.getCargo().getNombreCargo());
                cargoList.add(cargoDto);
                objdto.setCargos(cargoList);
            }
            personalCargoDtoList.add(objdto);
        }
        List<Cargo> cargos = this.personalCargoService.getAllCargos();
        model.addAttribute("cargos", cargos);
        model.addAttribute("personalCargoDtoList", personalCargoDtoList);
        return "PersonalCargo/ListPersonCargo";
    }

    //admin/personal/person
    @RequestMapping(value = "/person", method = RequestMethod.GET)
    public String FormPersonalCargo(Model model) throws ParseException {
        logger.debug("Mostrando Usuarios en JSP");
        model.addAttribute("agregando", true);
        model.addAttribute("editando",false);
        model.addAttribute("caso", new Personal_Cargo());
        List<Cargo> cargos = personalCargoService.getAllCargos();
        model.addAttribute("cargos", cargos);
        return "PersonalCargo/enterForm";
    }

    @RequestMapping(value = "editPerson/{codigo}", method = RequestMethod.GET)
    public String editPerson(@PathVariable("codigo") Integer codigo, Model model) throws ParseException {
        try {
            model.addAttribute("agregando", false);
            model.addAttribute("editando", true);
            List<Cargo> cargos = personalCargoService.getAllCargos();
            model.addAttribute("cargos", cargos);
            Personal persona = this.personalCargoService.getPersonaById(codigo);
            model.addAttribute("persona", persona);
            //Obtener los cargos de la Persona
            List<Personal_Cargo> cargosPersona = this.personalCargoService.getEstudiosUsuario(codigo);
            model.addAttribute("cargosPersona", cargosPersona);
            Set<Integer> cargocadena = new HashSet<Integer>();
            return "PersonalCargo/enterForm";
        }catch (Exception ex){
            return "404";
        }
    }

    /* Mapeo de Campo por campo value debe coincidir con mi name y id de mi control /admin/personal/savePerson */
    @RequestMapping( value="savePerson", method=RequestMethod.POST)
    public ResponseEntity<String> processUpdateUserForm(@RequestParam( value="completeName", required=true ) String completeName
            , @RequestParam( value="codigoPersonal", required=false ) String codigoPersonal
            , @RequestParam( value="cargos", required=false, defaultValue="") List<Integer> cargos
    ){
        try{
            PersonalCargoDto objDto = new PersonalCargoDto();
            Integer idPersonal = Integer.parseInt(codigoPersonal);
            Personal personalActual = this.personalCargoService.getPersonaById(idPersonal);
            String nameComputer = "NicaUmich2";
            if (personalActual == null){//GUARDA NUEVO
                personalActual = new Personal();
                personalActual.setIdpersonal(idPersonal);
                personalActual.setNombreApellido(completeName.toUpperCase());
                personalActual.setRecordDate(new Date());
                personalActual.setEstado('1');
                personalActual.setPasive('0');
                personalActual.setRecordUser(SecurityContextHolder.getContext().getAuthentication().getName());
                personalActual.setDeviceid(nameComputer);
                this.personalCargoService.saveOrUpdatePersona(personalActual);
                //Cargos del Personal
                Personal_Cargo pc = null;
                HashSet<CargoDto> cargoDtos = new HashSet<CargoDto>();
                for(Integer a:cargos){
                    pc = new Personal_Cargo();
                    CargoDto cargosToDto = new CargoDto();
                    Personal p = this.personalCargoService.getPersonaById(idPersonal);
                    pc.setPersonal(p);
                    Cargo cargo = this.personalCargoService.getCargoById(a);
                    pc.setCargo(cargo);
                    cargosToDto.setCodigoCargo(cargo.getIdcargo());
                    cargosToDto.setNombreCargo(cargo.getNombreCargo());
                    pc.setRecordDate(new Date());
                    pc.setEstado('1');
                    pc.setPasive('0');
                    pc.setRecordUser(SecurityContextHolder.getContext().getAuthentication().getName());
                    pc.setDeviceid(nameComputer);
                    this.personalCargoService.saveOrUpdate(pc);
                    cargoDtos.add(cargosToDto);
                }
                objDto.setCodigo(personalActual.getIdpersonal());
                objDto.setNombre(personalActual.getNombreApellido());
                objDto.setCargos(cargoDtos);
                return createJsonResponse(objDto);
            }
            else{// ACTUALIZA
                Personal editPerson = this.personalCargoService.getPersonaById(idPersonal);
                editPerson.setIdpersonal(idPersonal);
                editPerson.setNombreApellido(completeName.toUpperCase());
                this.personalCargoService.saveOrUpdatePersona(editPerson);
                boolean result = this.personalCargoService.deleteAllCargos(editPerson.getIdpersonal());
                if(result) {
                    objDto = new PersonalCargoDto();
                    objDto.setCodigo(editPerson.getIdpersonal());
                    objDto.setNombre(editPerson.getNombreApellido().toUpperCase());

                    //Cargos del Personal
                    Personal_Cargo pc = null;
                    for (Integer a : cargos) {
                        pc = new Personal_Cargo();
                        Personal p = this.personalCargoService.getPersonaById(idPersonal);
                        pc.setPersonal(p);
                        Cargo cargo = this.personalCargoService.getCargoById(a);
                        pc.setCargo(cargo);
                        pc.setRecordDate(new Date());
                        pc.setEstado('1');
                        pc.setPasive('0');
                        pc.setRecordUser(SecurityContextHolder.getContext().getAuthentication().getName());
                        pc.setDeviceid(nameComputer);
                        this.personalCargoService.saveOrUpdate(pc);
                    }
                }else{
                    Map<String, String> map = new HashMap<String, String>();
                    map.put("msj", "Error al  modificar los Cargos!");
                    return createJsonResponse(map);
                }
                return createJsonResponse(objDto);
            }
        }
        catch(Exception e){
            Gson gson = new Gson();
            String json = gson.toJson(e.toString());
            return new ResponseEntity<String>( json, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    ///admin/personal/actions/
    @RequestMapping("actions/{accion}/{codigo}")
    public String enableUser(@PathVariable("codigo") int codigo,
                             @PathVariable("accion") String accion, RedirectAttributes redirectAttributes) {
        String redirecTo="404";
        Personal person = this.personalCargoService.getPersonaById(codigo);
        if (person !=null) {
            if (accion.matches("enable")) { //Habilitar al personal
                redirecTo = "redirect:/admin/personal/list";
                this.personalCargoService.ActivarCargo(person.getIdpersonal());
                person.setPasive('0');
                this.personalCargoService.saveOrUpdatePersona(person);
                redirectAttributes.addFlashAttribute("usuarioHabilitado", true);
            } else if (accion.matches("disable")) { //Deshabilitar al personal
                redirecTo = "redirect:/admin/personal/list";
                this.personalCargoService.Desactivarcargo(person.getIdpersonal());
                person.setPasive('1');
                this.personalCargoService.saveOrUpdatePersona(person);
                redirectAttributes.addFlashAttribute("usuarioDeshabilitado", true);
            }else {
                redirecTo = "404";
            }
        }
        return redirecTo;
    }


    ///admin/personal/searchPersonCargo
    @RequestMapping(value = "/searchPersonCargo", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    List<PersonalCargoDto> searchPersonCargo(@RequestParam(value = "parametro", required = true) Integer parametro)
            throws ParseException {
        try {
            List<PersonalCargoDto>lista= new ArrayList<PersonalCargoDto>();
            Personal p = this.personalCargoService.getPersonaById(parametro);
            PersonalCargoDto obj = new PersonalCargoDto();
            Set<CargoDto>cargo = new HashSet<CargoDto>();
            obj.setCargos((HashSet<CargoDto>) cargo);
            lista.add(obj);
            return lista;
        }catch (Exception e){
            logger.error(e.getMessage());
            Gson gson = new Gson();
            String json = gson.toJson(e.toString());
            return null;
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
