package ni.org.ics.estudios.web.controller.Cartas;

import com.google.gson.Gson;
import ni.org.ics.estudios.domain.catalogs.*;
import ni.org.ics.estudios.domain.muestreoanual.ParticipanteProcesos;
import ni.org.ics.estudios.domain.scancarta.*;
import ni.org.ics.estudios.dto.*;
import ni.org.ics.estudios.language.MessageResource;
import ni.org.ics.estudios.service.MessageResourceService;
import ni.org.ics.estudios.service.hemodinanicaService.DatoshemodinamicaService;
import ni.org.ics.estudios.service.muestreoanual.ParticipanteProcesosService;
import ni.org.ics.estudios.service.scancarta.ScanCartaService;
import ni.org.ics.estudios.web.utils.DateUtil;
import ni.org.ics.estudios.web.utils.JsonUtil;
import org.apache.commons.lang3.text.translate.UnicodeEscaper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import ni.org.ics.estudios.domain.Participante;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.Resource;
import java.net.InetAddress;
import java.security.cert.Extension;
import java.text.ParseException;
import java.util.*;

/**
 * Created by ICS on 09/01/2020.
 */
@Controller
@RequestMapping("/cartas")
public class CartasController {
    private static final Logger logger = LoggerFactory.getLogger(CartasController.class);
    @Resource(name = "scanCartaService")
    private ScanCartaService scanCartaService;
    /* Instancia de mi Servicio Hemodinamico */
    @Resource(name = "datoshemodinamicaService")
    private DatoshemodinamicaService datoshemodinamicaService;
    @Resource(name = "participanteProcesosService")
    private ParticipanteProcesosService participanteProcesosService;
    /* Instancia de mi Servicio Hemodinamico */
    @Resource(name = "DomicilioService")
    private ni.org.ics.estudios.service.Domicilios.DomicilioService DomicilioService;
    /* Instancia de mi Servicio Hemodinamico */
    @Resource(name = "messageResourceService")
    private MessageResourceService messageResourceService;


    @RequestMapping(value = "/Crear", method = RequestMethod.GET)
    public String Crear(ModelMap model) throws Exception {
        try {
            List<Estudio> carta = scanCartaService.getEstudios();
            model.addAttribute("carta", carta);
            List<MessageResource> relFam = messageResourceService.getCatalogo("CP_CAT_RFTUTOR");
            model.addAttribute("relFam", relFam);
            List<Personal_Cargo> person = scanCartaService.getPersonal();
            model.addAttribute("person", person);
            List<MessageResource> proyecto = messageResourceService.getCatalogo("CAT_SCAN_PROYECTO");
            model.addAttribute("proyecto", proyecto);
            List<MessageResource> SiNoNA = messageResourceService.getCatalogo("SCANCARTA");
            model.addAttribute("SiNoNA", SiNoNA);
            List<MessageResource> tpoasent = messageResourceService.getCatalogo("CAT_TIPO_ASENT");
            model.addAttribute("tpoasent", tpoasent);
            List<Parte> partlist = scanCartaService.getListParte();
            model.addAttribute("partlist", partlist);
            return "/Cartas/CrearCarta";
        } catch (Exception e) {
            return "404";
        }
    }

    // ** Vista para Editar un Carta **
    @RequestMapping(value = "EditCarta/{idCartaParticipante}", method = RequestMethod.GET)
    public String EditCartaParticipante(@PathVariable(value = "idCartaParticipante") Integer idCartaParticipante, Model model) {
        List<Estudio> carta = scanCartaService.getEstudios();
        model.addAttribute("carta", carta);
        List<MessageResource> relFam = messageResourceService.getCatalogo("CP_CAT_RFTUTOR");
        model.addAttribute("relFam", relFam);
        List<Personal_Cargo> person = scanCartaService.getPersonal();
        model.addAttribute("person", person);
        List<MessageResource> scanca = messageResourceService.getCatalogo("SCANCARTA");
        model.addAttribute("scanca", scanca);
        List<MessageResource> proyecto = messageResourceService.getCatalogo("CAT_SCAN_PROYECTO");
        model.addAttribute("proyecto", proyecto);
        List<MessageResource> tpoasent = messageResourceService.getCatalogo("CAT_TIPO_ASENT");
        model.addAttribute("tpoasent", tpoasent);
        ParticipanteCarta obj = null;
        try {
            obj = scanCartaService.getCartasParticipante(idCartaParticipante);
            List<Version> version = scanCartaService.getVersioCarta(obj.getVersion().getEstudio().getCodigo());
            model.addAttribute("version", version);
            Participante participante = datoshemodinamicaService.getParticipante(obj.getParticipante().getCodigo());
            model.addAttribute("participante", participante);
            if (participante != null) {
                ParticipanteProcesos procesos = participanteProcesosService.getParticipante(participante.getCodigo());
                model.addAttribute("procesos", procesos);
                String edad = participante.getEdad();
                String[] strs = edad.split("/");
                int edadyear = Integer.parseInt(strs[0]);
                obj.setEdadyears(edadyear);
                int edadmeses = Integer.parseInt(strs[1]);
                obj.setEdadmeses(edadmeses);
                int edaddias = Integer.parseInt(strs[2]);
                obj.setEdadyears(edadyear);
                boolean menor_edad = true;
                Integer yearOld = Integer.parseInt(String.valueOf(edadyear));
                if (yearOld <= 6 || yearOld >= 18) {
                    menor_edad = false;
                }
                model.addAttribute("menor_edad", menor_edad);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        List<MessageResource> SiNoNA = messageResourceService.getCatalogo("SCANCARTA");
        model.addAttribute("SiNoNA", SiNoNA);
        List<DetalleParte> dp = scanCartaService.getDetalleParteList(idCartaParticipante);
        model.addAttribute("dp", dp);
        model.addAttribute("obj", obj);
        return "/Cartas/EditCarta";
    }

    @RequestMapping(value = "/VersionCarta", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody String GetVersionCarta(@RequestParam(value = "idcarta") Integer idcarta, Model model) throws Exception {
        List<Version> objV = scanCartaService.getVersioCarta(idcarta);
        model.addAttribute("objV", objV);
        String jsonResponse;
        jsonResponse = new Gson().toJson(model);
        UnicodeEscaper escaper = UnicodeEscaper.above(127);
        return escaper.translate(jsonResponse);
    }

    @RequestMapping(value = "/ParteVersion", method = RequestMethod.GET, produces = "application/json")
    public   @ResponseBody
    String GetParteCarta(@RequestParam(value = "idversion") Integer idversion, Model model) throws Exception {
        String result = "No Found";
        List<Parte> parte = scanCartaService.getParte(idversion);
        model.addAttribute("parte", parte);
        String jsonResponse;
        jsonResponse = new Gson().toJson(model);
        UnicodeEscaper escaper = UnicodeEscaper.above(127);
        return escaper.translate(jsonResponse);
    }


    @RequestMapping(value = "/searchParticipant", method = RequestMethod.GET, produces = "application/json")
    public
    @ResponseBody
    ResponseEntity<String> BuscarParticipanteByID(@RequestParam(value = "parametro", required = true) Integer parametro) throws Exception {
        try {
            Map<String, String> map = new HashMap<String, String>();
            BuscaParticipanteForCarta objEncontrado = new BuscaParticipanteForCarta();
            Participante participante = datoshemodinamicaService.getParticipante(parametro);
            if (participante != null) {
                ParticipanteProcesos procesos = participanteProcesosService.getParticipante(parametro);
                objEncontrado.setEstado(procesos.getEstPart().toString());
                ParticipantesCodigo participantesCodigo = DomicilioService.getCodigos(parametro);
                if (participantesCodigo.getCasaFamilia() != null) {
                    map.put("CFamilia", participantesCodigo.getCasaFamilia());
                } else {
                    map.put("CFamilia", "");
                }
                String edad = participante.getEdad();
                String[] strs = edad.split("/");
                int edadyear = Integer.parseInt(strs[0]);
                objEncontrado.setEdadAnios(edadyear);
                int edadmeses = Integer.parseInt(strs[1]);
                objEncontrado.setEdadMes(edadmeses);
                int edaddias = Integer.parseInt(strs[2]);
                objEncontrado.setEdadDia(edaddias);
                boolean menor_edad = true;
                Integer yearOld = Integer.parseInt(String.valueOf(edadyear));
                if (yearOld <= 6 || yearOld >= 18) {
                    menor_edad = false;
                }
                objEncontrado.setMenorEdad(menor_edad);
                objEncontrado.setEstudios(participantesCodigo.getEstudio());
                objEncontrado.setNombreTutor(participante.getNombre1Tutor() + " " + participante.getApellido1());
                objEncontrado.setRealFam(participante.getRelacionFamiliarTutor());
                String SusEstudios = participantesCodigo.getEstudio();
                String[] arrayString = SusEstudios.split("  ");
                ArrayList<String> estudios_procesos = new ArrayList<String>();
                for (int i = 0; i < arrayString.length; i++) {
                    estudios_procesos.add(arrayString[i]);
                }
                ArrayList<EstudioDto> lista_estudios = new ArrayList<EstudioDto>();
                lista_estudios.add(new EstudioDto(1, "CH Familia"));
                lista_estudios.add(new EstudioDto(2, "Arbovirus"));
                lista_estudios.add(new EstudioDto(3, "Dengue"));
                lista_estudios.add(new EstudioDto(4, "Influenza"));
                lista_estudios.add(new EstudioDto(5, "UO1"));
                lista_estudios.add(new EstudioDto(6, "Tcovid"));
                List<EstudioDto> studyFinal = new ArrayList<EstudioDto>();
                for (String cadena : estudios_procesos) {
                    for (EstudioDto e : lista_estudios) {
                        if (cadena.equals(e.getNombreEstudio())) {
                            studyFinal.add(new EstudioDto(e.getPrioridad(), e.getNombreEstudio()));
                            objEncontrado.setListEstudios(studyFinal);
                        }
                    }
                }
            }
            objEncontrado.setCodigoParticipante(participante.getCodigo());
            objEncontrado.setNombreCompleto(participante.getNombreCompleto());
            objEncontrado.setFechaNac(participante.getFechaNac());
            objEncontrado.setDireccion(participante.getCasa().getDireccion());
            String madre = participante.getNombre1Madre().toUpperCase();
            if (participante.getNombre2Madre() != null)
                madre = madre + " " + participante.getNombre2Madre().toUpperCase();
            madre = madre + " " + participante.getApellido1Madre().toUpperCase();
            if (participante.getApellido2Madre() != null)
                madre = madre + " " + participante.getApellido2Madre().toUpperCase();
            objEncontrado.setNombreMadre(madre);
            String padre = participante.getNombre1Padre().toUpperCase();
            if (participante.getNombre2Padre() != null)
                padre = padre + " " + participante.getNombre2Padre().toUpperCase();
            padre = padre + " " + participante.getApellido1Padre().toUpperCase();
            if (participante.getApellido2Padre() != null)
                padre = padre + " " + participante.getApellido2Padre().toUpperCase();
            objEncontrado.setNombrePadre(padre);
            objEncontrado.setName1Tutor(participante.getNombre1Tutor());
            String name2tutor = (participante.getNombre2Tutor() != null) ? participante.getNombre2Tutor().toUpperCase() : "";
            objEncontrado.setName2Tutor(name2tutor);
            objEncontrado.setSurname1Tutor(participante.getApellido1Tutor());
            String ape2tutor = (participante.getApellido2() != null) ? participante.getApellido2Tutor().toUpperCase() : "";
            objEncontrado.setSurname2Tutor(ape2tutor);

            return JsonUtil.createJsonResponse(objEncontrado);
        } catch (Exception e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @RequestMapping(value = "/VerParteCarta", method = RequestMethod.GET, produces = "application/json")
    public
    @ResponseBody
    List<DetalleParte> VerPartes(@RequestParam(value = "idparticipantecarta") Integer idparticipantecarta) throws ParseException {
        List<DetalleParte> p = null;
        try {
            p = scanCartaService.getDetalleParteList(idparticipantecarta);
            return p;
        } catch (Exception ex) {
            return p;
        }
    }

    // cartas/VerExtension
    @RequestMapping(value = "/VerExtension", method = RequestMethod.GET, produces = "application/json")
    public
    @ResponseBody
    List<Extensiones> VerExtension(@RequestParam(value = "idversion") Integer idversion) throws Exception {
        List<Extensiones> lista = null;
        try {
            lista = this.scanCartaService.getExtension(idversion);
            return lista;
        } catch (Exception e) {
            return null;
        }
    }

    //Metodo para Guardar la Version/Participante
    @RequestMapping(value = "/saveScanCarta", method = RequestMethod.POST, produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody ResponseEntity<String> saveScanCarta(@RequestBody ParticipanteCartaDto obj) {
        try {
            Version v = new Version();
            if (!scanCartaService.SiExisteParticipanteCarta(obj.getVersion(), obj.getCodigo(), obj.getFechacarta())) {
                if (obj.getAsentimiento().equals("1") && obj.getTipoasentimiento() == null){
                    Map<String, String> map = new HashMap<String, String>();
                    map.put("msj", "Especifica el Tipo de Asentimiento");
                    return createJsonResponse(map);
                }else if(obj.getAsentimiento().equals("1") && obj.getTipoasentimiento() == 3){
                    Map<String, String> map = new HashMap<String, String>();
                    map.put("msj", "Revisa el Tipo de Asentimiento");
                    return createJsonResponse(map);
                }else{}
                ParticipanteCarta pc = new ParticipanteCarta();
                String computerName = InetAddress.getLocalHost().getHostName();
                if (obj != null) {
                    Participante p = new Participante();
                    p.setCodigo(obj.getCodigo());
                    pc.setParticipante(p);
                    v.setIdversion(obj.getVersion());
                    pc.setVersion(v);
                    pc.setRelfam(obj.getRelfam());
                    pc.setAsentimiento(obj.getAsentimiento());
                    int tipoAsentimiento = (obj.getTipoasentimiento() == null) ? 0 : obj.getTipoasentimiento();
                    pc.setTipoasentimiento(tipoAsentimiento);
                    pc.setQuienfirma(obj.getNombfirma().toUpperCase());
                    String name2Tutor = (obj.getNombre2Firma() != null) ? obj.getNombre2Firma().toUpperCase() : "";
                    pc.setNombre2Firma(name2Tutor);
                    pc.setApellido1Firma(obj.getApellido1Firma().toUpperCase());
                    String ape2Tutor = (obj.getApellido2Firma() != null) ? obj.getApellido2Firma().toUpperCase() : "";
                    pc.setApellido2Firma(ape2Tutor);
                    Personal person = new Personal();
                    person.setCodigo(obj.getPerson());
                    pc.setPersonal(person);
                    pc.setProyecto(obj.getProyecto());
                    boolean cf = (obj.getContactoFuturo().equals("1")) ? true : false;
                    pc.setContactoFuturo(cf);
                    boolean testigo = obj.getTestigopresente().equals('1') ? true : false;
                    pc.setTestigopresent(testigo);
                    String name1Testigo = (obj.getNombre1testigo() != null) ? obj.getNombre1testigo().toUpperCase() : "";
                    pc.setNombre1testigo(name1Testigo);
                    String name2Testigo = (obj.getNombre2testigo() != null) ? obj.getNombre2testigo().toUpperCase() : "";
                    pc.setNombre2testigo(name2Testigo);
                    String ape1Testigo = (obj.getApellido1testigo() != null) ? obj.getApellido1testigo().toUpperCase() : "";
                    pc.setApellido1testigo(ape1Testigo);
                    String ape2Testigo = (obj.getApellido2testigo() != null) ? obj.getApellido2testigo().toUpperCase() : "";
                    pc.setApellido2testigo(ape2Testigo);
                    String obs = (obj.getObservacion() != null) ? obj.getObservacion().toUpperCase() : "";
                    pc.setObservacion(obs);
                    pc.setEdadyears(obj.getEdadyears());
                    pc.setEdadmeses(obj.getEdadmeses());
                    pc.setEdaddias(obj.getEdaddias());
                    pc.setFechacarta(DateUtil.StringToDate(obj.getFechacarta(), "dd/MM/yyyy"));
                    pc.setRecordDate(new Date());
                    pc.setRecordUser(SecurityContextHolder.getContext().getAuthentication().getName());
                    pc.setDeviceid(computerName);
                    pc.setEstado('1');
                    pc.setPasive('0');
                    pc.setEstudios(obj.getEstudios_actuales());
                    scanCartaService.saveOrUpdateScanCarta(pc); // Guarda/Participante-Version
                }
                if (obj.getParte() != null) {// Guarda las Version-Partes
                    Parte pr = new Parte();
                    for (ParteDto parte : obj.getParte()) {
                        DetalleParte dp = new DetalleParte();
                        dp.setParticipantecarta(pc);//
                        dp.setAcepta(parte.isAcepta());
                        pr.setIdparte(parte.getIdparte());
                        dp.setParte(pr);
                        dp.setDeviceid(computerName);
                        dp.setRecordDate(new Date());
                        dp.setRecordUser(SecurityContextHolder.getContext().getAuthentication().getName());
                        dp.setEstado('1');
                        dp.setPasive('0');
                        scanCartaService.saveParteCarta(dp);
                    }
                }
                return createJsonResponse(pc);
            } else {
                Map<String, String> map = new HashMap<String, String>();
                map.put("msj", "Registro ya existe.");
                return createJsonResponse(map);
            }
        } catch (Exception e) {
            Gson gson = new Gson();
            String json = gson.toJson(e.toString());
            return new ResponseEntity<String>(json, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /* Buscar Listado por Codigo Participante */
    @RequestMapping(value = "/ListadoCartaParticipant", method = RequestMethod.GET)
    public ModelAndView ListadoCartaParticipant() throws Exception {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/Cartas/ListadoCartaParticipante");
        return modelAndView;
    }

    // Metodo de Búsqueda por código del Participante
    @RequestMapping(value = "/GetCartasParticipante", method = RequestMethod.GET, produces = "application/json")
    public
    @ResponseBody
    List<VersionExtensionCartaDto> fetchCartaParticipanteToJson(@RequestParam(value = "parametro", required = true) Integer parametro)
            throws ParseException {
        List<VersionExtensionCartaDto> ext = new ArrayList<VersionExtensionCartaDto>();
        try {
            List<ParticipanteCarta> cartaparticipante = scanCartaService.getScanCartasByParticipante(parametro);
            for (ParticipanteCarta pcarta : cartaparticipante) {
                VersionExtensionCartaDto obj = new VersionExtensionCartaDto();
                ParticipanteProcesos procesos = this.participanteProcesosService.getParticipante(pcarta.getParticipante().getCodigo());
                Integer partExt = this.scanCartaService.cantExtensionByCarta(pcarta.getIdparticipantecarta());
                obj.setCantidadExtension(partExt);
                obj.setIdParticipanteCarta(pcarta.getIdparticipantecarta());
                obj.setCodigoParticipante(pcarta.getParticipante().getCodigo());
                obj.setNombreCompleto(pcarta.getParticipante().getNombreCompleto());
                obj.setIdEstudio(pcarta.getVersion().getEstudio().getCodigo());
                obj.setNmobreEstudio(pcarta.getVersion().getEstudio().getNombre());
                obj.setIdVersion(pcarta.getVersion().getIdversion());
                obj.setNombreVersion(pcarta.getVersion().getVersion());
                obj.setFechaCarta(DateUtil.DateToString(pcarta.getFechacarta(), "dd/MM/yyyy"));
                obj.setEstado("" + procesos.getEstPart());
                obj.setAnulada(pcarta.isAnulada());
                obj.setPqAnulada(pcarta.getPq_anulada());
                obj.setTieneExtesion(this.scanCartaService.tieneExtensionByVersion(pcarta.getVersion().getIdversion()));
                ext.add(obj);
            }
            return ext;
        } catch (Exception e) {
            return ext = null;
        }
    }


    /* Anular  la carta principal, partes y extension */
    @RequestMapping(value = "UpdateRetiro", method = RequestMethod.GET)
    public ResponseEntity<String> UpdateRetiro(@RequestParam(value = "IdParticipanteCartaModalAnular", required = true) Integer IdParticipanteCartaModalAnular,
                                               @RequestParam(value = "observacion", required = true) String observacion)
            throws Exception {
        try {
            if (this.scanCartaService.updateAnular(IdParticipanteCartaModalAnular, observacion.toUpperCase())) {
                if (this.scanCartaService.UpdateParteAnulada(IdParticipanteCartaModalAnular)) {
                   boolean result =  this.scanCartaService.UpdateExtensionAnulada(IdParticipanteCartaModalAnular);
                    System.out.print("result: "+result);
                }
            }
            return createJsonResponse("Anulado éxitoso!");
        } catch (Exception e) {
            Gson gson = new Gson();
            String json = gson.toJson(e.toString());
            return new ResponseEntity<String>(json, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //cartas/Delete -- no use
    @RequestMapping(value = "Delete", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<String> Delete(@RequestParam(value = "idParticpanteCarta", required = true) Integer idParticipanteCarta) throws Exception {
        try {
           /* if (this.scanCartaService.deleteDetalleParte(idParticipanteCarta)){
                if (this.scanCartaService.deleteExtension(idParticipanteCarta)) {
                    this.scanCartaService.deleteParticipanteCarta(idParticipanteCarta);
                }
            }*/
            return JsonUtil.createJsonResponse("Registros Eliminados!");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    // cartas/updateDetalleParte
    @RequestMapping(value = "updateDetalleParte", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<String> updateDetalleParte(@RequestBody ArrayList<ParteDto> postData) throws Exception {
        try {
            for (ParteDto dto : postData) {
                this.scanCartaService.ActualizarAcepta(dto.getIddetalle(), dto.isAcepta());
            }
            return JsonUtil.createJsonResponse("Registros Actualizados");
        } catch (Exception e) {
            Gson gson = new Gson();
            String json = gson.toJson(e.toString());
            return new ResponseEntity<String>(json, HttpStatus.OK);
        }
    }


    // ** Metodo para Actulizar Carta
    @RequestMapping(value = "UpdateAll", method = RequestMethod.POST, produces = "application/json")
    ResponseEntity<String> UpdateAll(@RequestBody ParticipanteCartaDto obj) throws Exception {
        try {
            ParticipanteCarta pc = new ParticipanteCarta();
            String computerName = InetAddress.getLocalHost().getHostName();
            if (obj != null) {
                pc.setIdparticipantecarta(obj.getCodigo());
                Participante p = new Participante();
                p.setCodigo(obj.getIdparticipante());
                pc.setParticipante(p);
                Version v = new Version();
                v.setIdversion(obj.getVersion());
                pc.setVersion(v);
                boolean asentimiento = (obj.getAsentimiento().equals("")) ? false : true;
                pc.setAsentimiento(obj.getAsentimiento());
                int tipo_asentimiento = (obj.getTipoasentimiento() == null) ? 0 : obj.getTipoasentimiento();
                pc.setTipoasentimiento(tipo_asentimiento);
                pc.setRelfam(obj.getRelfam());
                pc.setQuienfirma(obj.getNombfirma().toUpperCase());
                String nombre2tutor = (obj.getNombre2Firma() != null) ? obj.getNombre2Firma().toUpperCase() : "";
                pc.setNombre2Firma(nombre2tutor);
                pc.setApellido1Firma(obj.getApellido1Firma().toUpperCase());
                String apellido2tutor = (obj.getApellido2Firma() != null) ? obj.getApellido2Firma().toUpperCase() : "";
                pc.setApellido2Firma(apellido2tutor);
                Personal person = new Personal();
                person.setCodigo(obj.getPerson());
                pc.setPersonal(person);
                pc.setProyecto(obj.getProyecto());
                boolean cf = (obj.getContactoFuturo().equals("1")) ? true : false;
                pc.setContactoFuturo(cf);
                boolean testigo = obj.getTestigopresente().equals("1") ? true : false;
                pc.setTestigopresent(testigo);
                pc.setNombre1testigo(obj.getNombre1testigo().toUpperCase());
                String name2Testigo = (obj.getNombre2testigo() != null) ? obj.getNombre2testigo().toUpperCase() : "";
                pc.setNombre2testigo(name2Testigo);
                pc.setApellido1testigo(obj.getApellido1testigo().toUpperCase());
                String ape2Testigo = (obj.getApellido2testigo() != null) ? obj.getApellido2testigo().toUpperCase() : "";
                pc.setApellido2testigo(ape2Testigo);
                String obs = (obj.getObservacion() != null) ? obj.getObservacion().toUpperCase() : "";
                pc.setObservacion(obs);
                pc.setEdadyears(obj.getEdadyears());
                pc.setEdadmeses(obj.getEdadmeses());
                pc.setEdaddias(obj.getEdaddias());
                pc.setFechacarta(DateUtil.StringToDate(obj.getFechacarta(), "dd/MM/yyyy"));
                pc.setRecordDate(new Date());
                pc.setRecordUser(SecurityContextHolder.getContext().getAuthentication().getName());
                pc.setDeviceid(computerName);
                pc.setEstado('1');
                pc.setPasive('0');
                pc.setEstudios(obj.getEstudios_actuales());
                scanCartaService.saveOrUpdateScanCarta(pc);
            }
            if (obj.getParte() != null) {
                for (ParteDto dto : obj.getParte()) {
                    this.scanCartaService.ActualizarAcepta(dto.getIddetalle(), dto.isAcepta());
                }
            }
            return createJsonResponse(pc);
        } catch (Exception e) {
            Gson gson = new Gson();
            String json = gson.toJson(e.toString());
            return new ResponseEntity<String>(json, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    // ** una vez guardada la carta
    @RequestMapping(value = "/cartaSaveEdit/{idparticipantecarta}", method = RequestMethod.GET)
    public String cartaSaveEdit(@PathVariable("idparticipantecarta") String idparticipantecarta, Model model) throws ParseException {
        try {
            logger.debug("Mostrando participantes de caso monitoreo intensivo en JSP: " + idparticipantecarta);
            Integer parametro = Integer.parseInt(idparticipantecarta);
            ParticipanteCarta cartaparticipante = scanCartaService.getScanCartasByIdParticipanteCarta(parametro);
            if (cartaparticipante != null) {
                ParticipanteProcesos procesos = this.participanteProcesosService.getParticipante(cartaparticipante.getParticipante().getCodigo());
                model.addAttribute("pc", cartaparticipante);
                boolean tieneExt = this.scanCartaService.tieneExtensionByVersion(cartaparticipante.getVersion().getIdversion());
                model.addAttribute("tieneExt", tieneExt);
                model.addAttribute("estado", procesos.getEstPart());
                Integer partExt = this.scanCartaService.cantExtensionByCarta(cartaparticipante.getIdparticipantecarta());
                model.addAttribute("partExt", partExt);
                return "Cartas/CartaUnique";
            } else {
                return "404";
            }
        } catch (Exception e) {
            return "404";
        }
    }

    @RequestMapping(value = "/HabYDesCarta/{accion}/{idcarta}", method = RequestMethod.GET)
    public String HabYDesParte(@PathVariable("idcarta") String idcarta,
                               @PathVariable("accion") String accion, RedirectAttributes redirectAttributes) throws Exception {
        String redirecTo = "404";
        try {
            Integer id = Integer.parseInt(idcarta);
            if (accion.matches("bloq")) {
                redirecTo = "redirect:/CatalogoCarta/CrearNuevaCarta";
                scanCartaService.DesHabilitarCarta(id);
                redirectAttributes.addAttribute("usuarioDeshabilitado", true);
                redirectAttributes.addFlashAttribute("nombreUsuario", idcarta);
            } else if (accion.matches("Unbloq")) {
                redirecTo = "redirect:/CatalogoCarta/CrearNuevaCarta";
                scanCartaService.HabilitaCarta(id);
                redirectAttributes.addAttribute("usuarioHabilitado", true);
                redirectAttributes.addFlashAttribute("nombreUsuario", idcarta);
            } else {
                redirecTo = "redirect:/CatalogoCarta/CrearNuevaCarta";
            }
        } catch (Exception ex) {
            return redirecTo;
        }
        return redirecTo;
    }

    // Form/Listado para asignar una extension
    @RequestMapping(value = "/extension/{idParticipanteCarta}", method = RequestMethod.GET)
    public String extension(@PathVariable(value = "idParticipanteCarta") Integer idParticipanteCarta, Model model) throws Exception {
        try {
            ParticipanteCarta objParticipanteCarta = this.scanCartaService.getCartasParticipante(idParticipanteCarta);
            model.addAttribute("objParticipanteCarta", objParticipanteCarta);
            List<Extension> exts = scanCartaService.getExtensionVersion(objParticipanteCarta.getVersion().getIdversion());
            model.addAttribute("exts", exts);
            String nombreCompleto = objParticipanteCarta.getParticipante().getNombre1() + " " + objParticipanteCarta.getParticipante().getNombre2() + " " + objParticipanteCarta.getParticipante().getApellido1() + " " + objParticipanteCarta.getParticipante().getApellido2();
            model.addAttribute("nombreCompleto", nombreCompleto);
            model.addAttribute("version", objParticipanteCarta.getVersion().getVersion());
            model.addAttribute("estudio", objParticipanteCarta.getVersion().getEstudio().getNombre());
            model.addAttribute("idParticipanteCarta", objParticipanteCarta.getIdparticipantecarta());
            model.addAttribute("idVersion", objParticipanteCarta.getVersion().getIdversion());
            model.addAttribute("idParticipante", objParticipanteCarta.getParticipante().getCodigo());
            List<ParticipanteExtension> getParticipantExt = this.scanCartaService.getAllPartExt(objParticipanteCarta.getIdparticipantecarta());
            model.addAttribute("getPartExt", getParticipantExt);
            List<ParticipanteExtension>participanteExtensionObj = this.scanCartaService.getAllExtensiones();
            model.addAttribute("participanteExtensionObj",participanteExtensionObj);
            List<DetalleParte> listDetailParte = this.scanCartaService.getDetalleParteList(objParticipanteCarta.getIdparticipantecarta());
            model.addAttribute("listDetailParte", listDetailParte);
            model.addAttribute("agregando", true);
            model.addAttribute("editando", false);
            model.addAttribute("caso", new ParticipanteExtension());
        } catch (Exception e) {
            e.printStackTrace();
            return "404";
        }
        return "/Cartas/Extensiones";
    }
    // Metodo para editar Extension
    @RequestMapping(value = "/edit/{idParticipantExtension}", method = RequestMethod.GET)
    public String editextension(Model model, @PathVariable(value = "idParticipantExtension") Integer idParticipantExtension) throws Exception {
        try {
            ParticipanteExtension caso = this.scanCartaService.getByIDDetalleParte(idParticipantExtension);
            model.addAttribute("agregando", false);
            model.addAttribute("editando", true);
            ParticipanteCarta objParticipanteCarta = this.scanCartaService.getCartasParticipante(caso.getParticipantecarta().getIdparticipantecarta());
            String nombreCompleto = objParticipanteCarta.getParticipante().getNombre1() + " " + objParticipanteCarta.getParticipante().getNombre2() + " " + objParticipanteCarta.getParticipante().getApellido1() + " " + objParticipanteCarta.getParticipante().getApellido2();
            model.addAttribute("nombreCompleto", nombreCompleto);
            model.addAttribute("version", objParticipanteCarta.getVersion().getVersion());
            model.addAttribute("estudio", objParticipanteCarta.getVersion().getEstudio().getNombre());
            model.addAttribute("idParticipanteCarta", objParticipanteCarta.getIdparticipantecarta());
            List<Extension> exts = scanCartaService.getExtensionVersion(caso.getParticipantecarta().getVersion().getIdversion());
            model.addAttribute("exts", exts);
            List<ParticipanteExtension> getParticipantExt = this.scanCartaService.getAllPartExt(objParticipanteCarta.getIdparticipantecarta());
            model.addAttribute("getPartExt", getParticipantExt);
            List<ParticipanteExtension>participanteExtensionObj = this.scanCartaService.getAllExtensiones();
            model.addAttribute("participanteExtensionObj",participanteExtensionObj);
            model.addAttribute("caso", caso);
        } catch (Exception e) {
            return "404";
        }
        return "/Cartas/Extensiones";
    }


    //cartas/saveExtensCarta
    @RequestMapping(value = "/saveExtensCarta", method = RequestMethod.POST, produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public
    @ResponseBody
    ResponseEntity<String> saveExtensCarta(@RequestParam(value = "fechaExtension", defaultValue = "") String fechaExtension
            , @RequestParam(value = "nombre1tutor", required = true) String nombre1tutor
            , @RequestParam(value = "nombre2tutor", required = false, defaultValue = "") String nombre2tutor
            , @RequestParam(value = "apellido1tutor", required = true, defaultValue = "") String apellido1tutor
            , @RequestParam(value = "apellido2tutor", required = false) String apellido2tutor
            , @RequestParam(value = "chktestigo", required = false, defaultValue = "") String chktestigo
            , @RequestParam(value = "nombre1Testigo", required = true, defaultValue = "") String nombre1Testigo
            , @RequestParam(value = "nombre2Testigo", required = false, defaultValue = "") String nombre2Testigo
            , @RequestParam(value = "apellido1Testigo", required = true, defaultValue = "") String apellido1Testigo
            , @RequestParam(value = "apellido2Testigo", required = true, defaultValue = "") String apellido2Testigo
            , @RequestParam(value = "observacion", required = false, defaultValue = "") String observacion
            , @RequestParam(value = "idExtension", required = true) Integer idExtension
            , @RequestParam(value = "accion", required = true) String accion
            , @RequestParam(value = "idParticipante", required = true) Integer idParticipante
            , @RequestParam(value = "idParticipanteCarta", required = true) Integer idParticipanteCarta
            , @RequestParam(value = "idVersion", required = true) Integer idVersion
            , @RequestParam(value = "idParticipantExtension", required = true) Integer idParticipantExtension
    ) throws Exception {
        try {

            if (accion.equals("true")) { //aqui actualizar
                ParticipanteExtension editObj = new ParticipanteExtension();
                editObj.setIdParticipantExtension(idParticipantExtension);
                editObj.setFechaExtension(DateUtil.StringToDate(fechaExtension, "dd/MM/yyyy"));
                editObj.setNombre1Tutor(nombre1tutor.toUpperCase());
                String name2tutor = ""; // = (nombre2tutor != null) ? nombre2tutor.toUpperCase():"";
                if (nombre2tutor.equals("")) {
                    editObj.setNombre2Tutor(name2tutor);
                } else {
                    editObj.setNombre2Tutor(nombre2tutor.toUpperCase());
                }
                String ape2Tutor = "";
                editObj.setApellido1Tutor(apellido1tutor.toUpperCase());
                if (apellido2tutor.equals("")) {
                    editObj.setApellido2Tutor(ape2Tutor);
                } else {
                    editObj.setApellido2Tutor(apellido2tutor.toUpperCase());
                }
                boolean band = (chktestigo.equals("on")) ? true : false;
                editObj.setTestigoPresente(band);
                String name1Testigo = (nombre1Testigo != null) ? nombre1Testigo.toUpperCase() : "";
                editObj.setNombre1Testigo(name1Testigo);
                String name2testigo = (nombre2Testigo != null) ? nombre2Testigo.toUpperCase() : "";
                editObj.setNombre2Testigo(name2testigo);
                String ape1Testigo = (apellido1Testigo != null) ? apellido1Testigo.toUpperCase() : "";
                editObj.setApellido1Testigo(ape1Testigo);
                String ape2Testigo = (apellido2Testigo != null) ? apellido2Testigo.toUpperCase() : "";
                editObj.setApellido2Testigo(ape2Testigo);
                String obs = (observacion != null) ? observacion.toUpperCase() : "";
                editObj.setObservacion(obs);
                ParticipanteCarta pc = new ParticipanteCarta();
                pc.setIdparticipantecarta(idParticipanteCarta);
                editObj.setParticipantecarta(pc);
                Extensiones extensiones = new Extensiones();
                extensiones.setId(idExtension);
                editObj.setExtensiones(extensiones);
                String ComputerName = InetAddress.getLocalHost().getHostName();
                editObj.setDeviceid(ComputerName);
                editObj.setEstado('1');
                editObj.setPasive('0');
                editObj.setRecordDate(new Date());
                editObj.setRecordUser(SecurityContextHolder.getContext().getAuthentication().getName());
                this.scanCartaService.saveParticpanteExtension(editObj);
                return JsonUtil.createJsonResponse(editObj);
            } else {
                ParticipanteExtension ext = new ParticipanteExtension();
                if (!this.scanCartaService.VerificaExtension(fechaExtension, fechaExtension, idExtension)) {
                    ext.setFechaExtension(DateUtil.StringToDate(fechaExtension, "dd/MM/yyyy"));
                    ext.setNombre1Tutor(nombre1tutor.toUpperCase());
                    if (nombre2tutor.equals("")) {
                        ext.setNombre2Tutor("");
                    } else {
                        ext.setNombre2Tutor(nombre2tutor.toUpperCase());
                    }
                    String ape2Tutor = "";
                    ext.setApellido1Tutor(apellido1tutor.toUpperCase());
                    if (apellido2tutor.equals("")) {
                        ext.setApellido2Tutor(ape2Tutor);
                    } else {
                        ext.setApellido2Tutor(apellido2tutor.toUpperCase());
                    }
                    boolean band = (chktestigo.equals("on")) ? true : false;
                    ext.setTestigoPresente(band);
                    String name1Testigo = (nombre1Testigo != null) ? nombre1Testigo.toUpperCase() : "";
                    ext.setNombre1Testigo(name1Testigo);
                    String name2testigo = (nombre2Testigo != null) ? nombre2Testigo.toUpperCase() : "";
                    ext.setNombre2Testigo(name2testigo);
                    String ape1Testigo = (apellido1Testigo != null) ? apellido1Testigo.toUpperCase() : "";
                    ext.setApellido1Testigo(ape1Testigo);
                    String ape2Testigo = (apellido2Testigo != null) ? apellido2Testigo.toUpperCase() : "";
                    ext.setApellido2Testigo(ape2Testigo);
                    String obs = (observacion != null) ? observacion.toUpperCase() : "";
                    ext.setObservacion(obs);
                    ParticipanteCarta pc = new ParticipanteCarta();
                    pc.setIdparticipantecarta(idParticipanteCarta);
                    ext.setParticipantecarta(pc);
                    Extensiones extensiones = new Extensiones();
                    extensiones.setId(idExtension);
                    ext.setExtensiones(extensiones);
                    String ComputerName = InetAddress.getLocalHost().getHostName();
                    ext.setDeviceid(ComputerName);
                    ext.setEstado('1');
                    ext.setPasive('0');
                    ext.setRecordDate(new Date());
                    ext.setRecordUser(SecurityContextHolder.getContext().getAuthentication().getName());
                    this.scanCartaService.saveParticpanteExtension(ext);
                    return createJsonResponse(ext);
                } else {
                    String cadenaCodificada = ("Participante tiene registrada Extension!");
                    return createJsonResponse(cadenaCodificada);
                }
            }
            /*
            ParticipanteExtension ext = new ParticipanteExtension();
            if (!this.scanCartaService.VerificaExtension(fechaExtension,fechaExtension,idExtension,idVersion, idParticipante)){
                ext.setFechaExtension(DateUtil.StringToDate(fechaExtension, "dd/MM/yyyy"));
                ext.setNombre1Tutor(nombre1tutor.toUpperCase());
                String name2tutor = (nombre2tutor != null) ? nombre2tutor.toUpperCase():"";
                ext.setNombre2Tutor(name2tutor);
                ext.setApellido1Tutor(apellido1tutor.toUpperCase());
                String ape2Tutor = (apellido2tutor != null) ? apellido2tutor.toUpperCase(): "";
                ext.setApellido2Tutor(ape2Tutor);
                boolean band = (chktestigo.equals("on")) ? true : false;
                ext.setTestigoPresente(band);
                String name1Testigo = (nombre1Testigo != null) ? nombre1Testigo.toUpperCase():"";
                ext.setNombre1Testigo(name1Testigo);
                String name2testigo = (nombre2Testigo != null) ? nombre2Testigo.toUpperCase():"";
                ext.setNombre2Testigo(name2testigo);
                String ape1Testigo = (apellido1Testigo != null) ? apellido1Testigo.toUpperCase():"";
                ext.setApellido1Testigo(ape1Testigo);
                String ape2Testigo = (apellido2Testigo != null) ? apellido2Testigo.toUpperCase():"";
                ext.setApellido2Testigo(ape2Testigo);
                String obs = (observacion != null) ? observacion.toUpperCase():"";
                ext.setObservacion(obs);
                ParticipanteCarta pc = new ParticipanteCarta();
                pc.setIdparticipantecarta(idParticipanteCarta);
                ext.setParticipantecarta(pc);
                Extensiones extensiones= new Extensiones();
                extensiones.setId(idExtension);
                ext.setExtensiones(extensiones);
                String ComputerName = InetAddress.getLocalHost().getHostName();
                ext.setDeviceid(ComputerName);
                ext.setEstado('1');
                ext.setPasive('0');
                ext.setRecordDate(new Date());
                ext.setRecordUser(SecurityContextHolder.getContext().getAuthentication().getName());
                this.scanCartaService.saveParticpanteExtension(ext);
                return createJsonResponse(ext);
            }else{
                String cadenaCodificada = ("Participante tiene registrada Extension!");
                return createJsonResponse(cadenaCodificada);
            }*/
        } catch (Exception e) {
            Gson gson = new Gson();
            String json = gson.toJson(e.toString());
            return new ResponseEntity<String>(json, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }




    //region CARTAS TEMPORALES

    //Metodo para crear una Nueva Carta Temporal /CatalogoCarta/CartaParticipantTmp
    @RequestMapping(value = "/CartaParticipantTmp", method = RequestMethod.GET)
    public ModelAndView CartaParticipantTmp(Model model) throws Exception {
        ModelAndView modelAndView = new ModelAndView();
        List<Estudio> cartas = scanCartaService.getAllEstudios();
        model.addAttribute("cartas", cartas);
        List<MessageResource> relFam = messageResourceService.getCatalogo("CP_CAT_RFTUTOR");
        model.addAttribute("relFam", relFam);
        List<Personal_Cargo> person = scanCartaService.getPersonal();
        model.addAttribute("person", person);
        List<MessageResource> proyecto = messageResourceService.getCatalogo("CAT_SCAN_PROYECTO");
        model.addAttribute("proyecto", proyecto);
        List<MessageResource> SiNoNA = messageResourceService.getCatalogo("SCANCARTA");
        model.addAttribute("SiNoNA", SiNoNA);
        List<MessageResource> tpoasent = messageResourceService.getCatalogo("CAT_TIPO_ASENT");
        model.addAttribute("tpoasent", tpoasent);
        model.addAttribute("caso", new ParticipanteCartaTmp());
        List<Parte> parte = scanCartaService.getListParte();
        model.addAttribute("parte", parte);
        List<Version> version = this.scanCartaService.getVersionActiva();
        model.addAttribute("version", version);
        List<ParticipanteCartaTmp> cartaTmp = this.scanCartaService.getAllParticipanteCartaTmp();

        boolean tieneExtension = false;
        List<ParticipanteCartaDto> listaDto = new ArrayList<ParticipanteCartaDto>();
        for (int i = 0; i < cartaTmp.size(); i++) {
            ParticipanteCartaDto objtbl = new ParticipanteCartaDto();
            objtbl.setIdparticipante(cartaTmp.get(i).getIdparticipante());
            objtbl.setFechacarta(DateUtil.DateToString(cartaTmp.get(i).getFechacarta(), "dd/MM/yyyy"));
            objtbl.setVersion(cartaTmp.get(i).getVersion().getIdversion());
            objtbl.setEstudio(cartaTmp.get(i).getVersion().getEstudio().getCodigo());
            objtbl.setNombfirma(cartaTmp.get(i).getName1tutor());
            objtbl.setApellido1Firma(cartaTmp.get(i).getSurname1tutor());
            objtbl.setCodigo(cartaTmp.get(i).getId());
            objtbl.setNombreUsuario(cartaTmp.get(i).getRecordUser());
            tieneExtension = (this.scanCartaService.tieneExtensionByVersion(objtbl.getVersion()));
            List<ParteDto> listPartesDto = new ArrayList<ParteDto>();
            List<DetalleParteTmp> detalletmp = this.scanCartaService.getDetalleParteTmpById(cartaTmp.get(i).getId());
            for (DetalleParteTmp tmp1 : detalletmp) {
                ParteDto objpartes = new ParteDto();
                objpartes.setNombreparte(tmp1.getParte().getParte());
                objpartes.setAcepta(tmp1.isAcepta());
                listPartesDto.add(objpartes);
            }
            objtbl.setParte(listPartesDto);
            objtbl.setTineneExtension(tieneExtension);
            listaDto.add(objtbl);
        }
        model.addAttribute("listaDto", listaDto);
        model.addAttribute("agregando",true);
        model.addAttribute("editando",false);
        modelAndView.setViewName("/CatalogoScanCarta/ParticipanteCartaTmp");
        return modelAndView;
    }

    // ** Editar Carta Temporal
    @RequestMapping(value = "/editTmp/{codigo}", method = RequestMethod.GET)
    public String editTmp(Model model, @PathVariable("codigo") String codigo) throws Exception
    {
        try{
            int cod = Integer.parseInt(codigo);
            ParticipanteCartaTmp caso = this.scanCartaService.getAllParticipanteCartaTmpById(cod);
            model.addAttribute("caso", caso);

            List<Estudio> cartas = scanCartaService.getAllEstudios();
            model.addAttribute("cartas", cartas);

            List<Version> version = this.scanCartaService.getVersioCarta(caso.getVersion().getEstudio().getCodigo());
            model.addAttribute("version", version);

            List<DetalleParteTmp> dp = scanCartaService.getList_Detalle_Parte_Tmp(caso.getId());
            Parte oP = null;
            for (DetalleParteTmp prici : dp){
               oP = this.scanCartaService.getPartePrincipal(prici.getParticipantecartatmp().getVersion().getIdversion());
            }
            model.addAttribute("partePrincipal",oP.getParte());
            model.addAttribute("dp", dp);

            List<MessageResource> relFam = messageResourceService.getCatalogo("CP_CAT_RFTUTOR");
            model.addAttribute("relFam", relFam);
            List<Personal_Cargo> person = scanCartaService.getPersonal();
            model.addAttribute("person", person);
            List<MessageResource> proyecto = messageResourceService.getCatalogo("CAT_SCAN_PROYECTO");
            model.addAttribute("proyecto", proyecto);


            List<ParticipanteCartaTmp> cartaTmp = this.scanCartaService.getAllParticipanteCartaTmp();
            //model.addAttribute("cartaTmp",cartaTmp);
            List<MessageResource> SiNoNA = messageResourceService.getCatalogo("SCANCARTA");
            model.addAttribute("SiNoNA", SiNoNA);

            List<MessageResource> tpoasent = messageResourceService.getCatalogo("CAT_TIPO_ASENT");
            model.addAttribute("tpoasent", tpoasent);
            model.addAttribute("obj", new ParticipanteCartaDto());
            List<ParticipanteCartaTmp> listCartaTmp = this.scanCartaService.getAllParticipanteCartaTmp();

            boolean tieneExtension = false;
            List<ParticipanteCartaDto> listaDto = new ArrayList<ParticipanteCartaDto>();
            for (int i = 0; i < listCartaTmp.size(); i++) {
                ParticipanteCartaDto objtbl = new ParticipanteCartaDto();
                objtbl.setIdparticipante(cartaTmp.get(i).getIdparticipante());
                objtbl.setFechacarta(DateUtil.DateToString(cartaTmp.get(i).getFechacarta(), "dd/MM/yyyy"));
                objtbl.setVersion(cartaTmp.get(i).getVersion().getIdversion());
                objtbl.setEstudio(cartaTmp.get(i).getVersion().getEstudio().getCodigo());
                objtbl.setNombfirma(cartaTmp.get(i).getName1tutor());
                objtbl.setApellido1Firma(cartaTmp.get(i).getSurname1tutor());
                objtbl.setCodigo(cartaTmp.get(i).getId());
                objtbl.setNombreUsuario(cartaTmp.get(i).getRecordUser());
                tieneExtension = (this.scanCartaService.tieneExtensionByVersion(objtbl.getVersion()));
                List<ParteDto> listPartesDto = new ArrayList<ParteDto>();
                List<DetalleParteTmp> detalletmp = this.scanCartaService.getDetalleParteTmpById(cartaTmp.get(i).getId());
                for (DetalleParteTmp tmp1 : detalletmp) {
                    ParteDto objpartes = new ParteDto();
                    objpartes.setNombreparte(tmp1.getParte().getParte());
                    objpartes.setAcepta(tmp1.isAcepta());
                    listPartesDto.add(objpartes);
                }
                objtbl.setParte(listPartesDto);
                objtbl.setTineneExtension(tieneExtension);
                listaDto.add(objtbl);
            }
            model.addAttribute("listaDto", listaDto);
            model.addAttribute("agregando",false);
            model.addAttribute("accion",true);
            return "/CatalogoScanCarta/ParticipanteCartaTmp";
        }
        catch (Exception e){
            logger.error(e.getMessage());
            throw e;
        }
    }

    // Guardar carta temporal
    @RequestMapping(value = "/saveCartaTmp", method = RequestMethod.POST, produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody ResponseEntity<String> saveCartaTmp(@RequestBody ParticipanteCartaDto obj) throws Exception {
        try {
            if (obj.getIdparticipante() == null ) {
                Map<String, String> map = new HashMap<String, String>();
                map.put("msj", "Ingresa codigo del participante.");
                return createJsonResponse(map);
            }
            if (obj.getIdparticipante() <= 0){
                Map<String, String> map = new HashMap<String, String>();
                map.put("msj", "Codigo del participante debe ser Mayor que cero.");
                return createJsonResponse(map);
            }
            if (obj.getVersion() == null) {
                Map<String, String> map = new HashMap<String, String>();
                map.put("msj", "Seleccione la Version.");
                return createJsonResponse(map);
            }
            if (obj.getTestigopresente().equals("1") && obj.getNombre1testigo().equals("") && obj.getApellido1testigo().equals("")){
                Map<String, String> map = new HashMap<String, String>();
                map.put("msj", "Ingresa el nombre y  apellido del testigo.");
                return createJsonResponse(map);
            }
            if (obj.getParte().size() == 0) {
                Map<String, String> map = new HashMap<String, String>();
                map.put("msj", "Seleccione las partes.");
                return createJsonResponse(map);
            }
            Participante participante = datoshemodinamicaService.getParticipante(obj.getIdparticipante());
            if (participante != null) {
                Map<String, String> map = new HashMap<String, String>();
                map.put("msjCodigo", "Codigo: ".concat(obj.getIdparticipante().toString()) + " Participante ya existe!.");
                return createJsonResponse(map);
            }

            if (obj.getAsentimiento().equals("0") && obj.getTipoasentimiento() == 0) {
                Map<String, String> map = new HashMap<String, String>();
                map.put("msj", "Revisa el Asentiemiento y Tipo de Asentimiento: ");
                return createJsonResponse(map);
            }
            String computerName = InetAddress.getLocalHost().getHostName();
            int rec = Integer.parseInt(obj.getRecurso());
            String n1, n2, a1, a2;
            boolean asent, contact, test;
            if (obj.getAccion().equals("true")) {
                ParticipanteCartaTmp temporalForEdit = new ParticipanteCartaTmp();
                temporalForEdit.setId(obj.getCodigo());
                Version versForEdit = new Version();
                versForEdit.setIdversion(obj.getVersion());
                temporalForEdit.setVersion(versForEdit);
                temporalForEdit.setIdparticipante(obj.getIdparticipante());
                temporalForEdit.setRecurso(rec);
                temporalForEdit.setTipoasentimiento(obj.getTipoasentimiento());
                temporalForEdit.setAsentimiento(obj.getAsentimiento());
                temporalForEdit.setRelfam(obj.getRelfam());
                temporalForEdit.setName1tutor(obj.getNombfirma().toUpperCase());
                String nombre2t = (obj.getNombre2Firma() == "") ? "" : obj.getNombre2Firma().toUpperCase();
                temporalForEdit.setName2tutor(nombre2t);
                temporalForEdit.setSurname1tutor(obj.getApellido1Firma().toUpperCase());
                String ap2 = (obj.getApellido2Firma() == "") ? "" : obj.getApellido2Firma().toUpperCase();
                temporalForEdit.setSurname2tutor(ap2);
                contact = (obj.getContactoFuturo().equals("")) ? false : true;
                temporalForEdit.setContactoFuturo(contact);
                temporalForEdit.setProyecto(obj.getProyecto());
                temporalForEdit.setFechacarta(DateUtil.StringToDate(obj.getFechacarta(), "dd/MM/yyyy"));
                temporalForEdit.setObservacion(obj.getObservacion());
                test = (obj.getTestigopresente().equals("0")) ? false : true;
                temporalForEdit.setTestigopresent(test);
                n1 = (obj.getNombre1testigo() == "") ? "" : obj.getNombre1testigo().toUpperCase();
                temporalForEdit.setNombre1testigo(n1);
                n2 = (obj.getNombre2testigo() == "") ? "" : obj.getNombre2testigo().toUpperCase();
                temporalForEdit.setNombre2testigo(n2);
                a1 = (obj.getApellido1testigo() == "") ? "" : obj.getApellido1testigo().toUpperCase();
                temporalForEdit.setApellido1testigo(a1);
                a2 = (obj.getApellido2testigo() == "") ? "" : obj.getApellido2testigo().toUpperCase();
                temporalForEdit.setApellido2testigo(a2);
                temporalForEdit.setEstado('1');
                temporalForEdit.setPasive('0');
                temporalForEdit.setDeviceid(computerName);
                temporalForEdit.setRecordDate(new Date());
                temporalForEdit.setRecordUser(SecurityContextHolder.getContext().getAuthentication().getName());
                ParticipanteProcesos procesos = participanteProcesosService.getParticipante(obj.getIdparticipante());
                temporalForEdit.setEstudios(procesos.getEstudio());
                this.scanCartaService.guardarCartaTMP(temporalForEdit);
                if (obj.getParte() != null){
                   int regEliminados = this.scanCartaService.deleteDetalleParteTmp(temporalForEdit.getId());
                    System.out.println("Registros Eliminados: " + regEliminados);
                    Parte pr = new Parte();
                       for (ParteDto parte : obj.getParte()) {
                           DetalleParteTmp dp = new DetalleParteTmp();
                           dp.setParticipantecartatmp(temporalForEdit);//
                           dp.setAcepta(parte.isAcepta());
                           pr.setIdparte(parte.getIdparte());
                           dp.setParte(pr);
                           dp.setDeviceid(computerName);
                           dp.setRecordDate(new Date());
                           dp.setRecordUser(SecurityContextHolder.getContext().getAuthentication().getName());
                           dp.setEstado('1');
                           dp.setPasive('0');
                           scanCartaService.saveParteCartaTMP(dp);
                       }
                }
                return JsonUtil.createJsonResponse(temporalForEdit);
            } else { // GUARDAR NUEVO
            if (!scanCartaService.SiExisteParticipanteCartaTMP(obj.getVersion(), obj.getIdparticipante(), obj.getFechacarta())) {
                ParticipanteCartaTmp temporal = new ParticipanteCartaTmp();
                temporal.setIdparticipante(obj.getIdparticipante());
                Version vers = new Version();
                vers.setIdversion(obj.getVersion());
                temporal.setVersion(vers);
                temporal.setRecurso(rec);
                temporal.setAsentimiento(obj.getAsentimiento());
                temporal.setTipoasentimiento(obj.getTipoasentimiento());
                temporal.setRelfam(obj.getRelfam());
                temporal.setName1tutor(obj.getNombfirma().toUpperCase());
                String nombre2t = (obj.getNombre2Firma() == "") ? "" : obj.getNombre2Firma().toUpperCase();
                temporal.setName2tutor(nombre2t);
                temporal.setSurname1tutor(obj.getApellido1Firma().toUpperCase());
                String ap2 = (obj.getApellido2Firma() == "") ? "" : obj.getApellido2Firma().toUpperCase();
                temporal.setSurname2tutor(ap2);
                contact = (obj.getContactoFuturo().equals("0")) ? false : true;
                temporal.setContactoFuturo(contact);
                temporal.setProyecto(obj.getProyecto());
                temporal.setFechacarta(DateUtil.StringToDate(obj.getFechacarta(), "dd/MM/yyyy"));
                temporal.setObservacion(obj.getObservacion());
                test = (obj.getTestigopresente().equals("0")) ? false : true;
                temporal.setTestigopresent(test);
                n1 = (obj.getNombre1testigo() == "") ? "" : obj.getNombre1testigo().toUpperCase();
                temporal.setNombre1testigo(n1);
                n2 = (obj.getNombre2testigo() == "") ? "" : obj.getNombre2testigo().toUpperCase();
                temporal.setNombre2testigo(n2);
                a1 = (obj.getApellido1testigo() == "") ? "" : obj.getApellido1testigo().toUpperCase();
                temporal.setApellido1testigo(a1);
                a2 = (obj.getApellido2testigo() == "") ? "" : obj.getApellido2testigo().toUpperCase();
                temporal.setApellido2testigo(a2);
                temporal.setRecordDate(new Date());
                temporal.setRecordUser(SecurityContextHolder.getContext().getAuthentication().getName());
                temporal.setDeviceid(computerName);
                temporal.setEstado('1');
                temporal.setPasive('0');
                ParticipanteProcesos procesos = this.participanteProcesosService.getParticipante(obj.getIdparticipante());
                temporal.setEstudios(procesos.getEstudio());
                this.scanCartaService.guardarCartaTMP(temporal);
                if (obj.getParte() != null) {
                    Parte pr = new Parte();
                    int count = 0;
                    for (ParteDto parte : obj.getParte()) {
                        ++count;
                        DetalleParteTmp dp = new DetalleParteTmp();
                        dp.setParticipantecartatmp(temporal);//
                        dp.setAcepta(parte.isAcepta());
                        pr.setIdparte(parte.getIdparte());
                        dp.setParte(pr);
                        dp.setDeviceid(computerName);
                        dp.setRecordDate(new Date());
                        dp.setRecordUser(SecurityContextHolder.getContext().getAuthentication().getName());
                        dp.setEstado('1');
                        dp.setPasive('0');
                        scanCartaService.saveParteCartaTMP(dp);
                    }
                    System.out.println("total " + count + " registros.");
                }
                return JsonUtil.createJsonResponse(temporal);
            } else {
                Map<String, String> map = new HashMap<String, String>();
                map.put("msj", "Registro ya existe.");
                return createJsonResponse(map);
            }
        }
        } catch (Exception ex) {
            Gson gson = new Gson();
            String json = gson.toJson(ex.toString());
            return new ResponseEntity<String>(json, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    //cartas/codigoParticipante
    @RequestMapping(value = "/codigoParticipante", method = RequestMethod.GET, produces = "application/json")
    public
    @ResponseBody
    ResponseEntity<String> buscarCodigoParticipante(@RequestParam(value = "idparticipante", required = true) Integer codigo) throws ParseException {
        Participante participante = null;
        try {
            participante = scanCartaService.getCodigoParticipante(codigo);
            if (participante != null)
                return JsonUtil.createJsonResponse("Codigo Participante: ".concat(codigo.toString()).concat(" Existe!"));
            else
                return JsonUtil.createJsonResponse("");
        } catch (Exception e) {
            Gson gson = new Gson();
            String json = gson.toJson(e.toString());
            return new ResponseEntity<String>(json, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //cartas/listaParteTmpById
    @RequestMapping(value = "/listaParteTmpById", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody ResponseEntity<String> listaParteTmpById(@RequestParam(value = "codigo", required = true) Integer codigo) throws ParseException {
        List<DetalleParteTmp> detalle = null;
        try {
            detalle = scanCartaService.getDetalleParteTmpById(codigo);
            if (detalle != null)
                return JsonUtil.createJsonResponse(detalle);
            else
                return JsonUtil.createJsonResponse("No se encontraron datos!");
        } catch (Exception e) {
            Gson gson = new Gson();
            String json = gson.toJson(e.toString());
            return new ResponseEntity<String>(json, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    //endregion
    //cartas/saveCartaExample
    @RequestMapping(value = "/saveCartaExample", method = RequestMethod.POST, produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public
    @ResponseBody
    ResponseEntity<String> saveExtensCarta(@RequestParam(value = "idparticipante", defaultValue = "", required = false) String idparticipante
            , @RequestParam(value = "idcarta", defaultValue = "", required = false) String idcarta
            , @RequestParam(value = "version", defaultValue = "", required = false) String version
            , @RequestParam(value = "partes", defaultValue = "", required = false) Integer partes[]
            , @RequestParam(value = "name1tutor", defaultValue = "", required = false) String name1tutor
            , @RequestParam(value = "name2tutor", defaultValue = "", required = false) String name2tutor
            , @RequestParam(value = "apellido1tutor", defaultValue = "", required = false) String apellido1tutor
            , @RequestParam(value = "apellido2Firma", defaultValue = "", required = false) String apellido2Firma
            , @RequestParam(value = "relfam", defaultValue = "", required = false) String relfam
            , @RequestParam(value = "fechacarta", defaultValue = "", required = false) String fechacarta
            , @RequestParam(value = "proyecto", defaultValue = "", required = false) String proyecto
            , @RequestParam(value = "person", defaultValue = "", required = false) String person
            , @RequestParam(value = "contactoFuturo", defaultValue = "", required = false) String contactoFuturo
            , @RequestParam(value = "asentimiento", defaultValue = "", required = false) String asentimiento
            , @RequestParam(value = "tipoasentimiento", defaultValue = "", required = false) String tipoasentimiento
            , @RequestParam(value = "nombre1Testigo", defaultValue = "", required = false) String nombre1Testigo
            , @RequestParam(value = "nombre2Testigo", defaultValue = "", required = false) String nombre2Testigo
            , @RequestParam(value = "apellido1Testigo", defaultValue = "", required = false) String apellido1Testigo
            , @RequestParam(value = "apellido2Testigo", defaultValue = "", required = false) String apellido2Testigo
            , @RequestParam(value = "observacion", defaultValue = "", required = false) String observacion
    ) throws Exception {
        try {
            ParticipanteCartaDto dto = new ParticipanteCartaDto();
            int i = Integer.parseInt(idparticipante);
            dto.setCodigo(i);
            return JsonUtil.createJsonResponse("yeah");
        } catch (Exception e) {
            Gson gson = new Gson();
            String json = gson.toJson(e.toString());
            return new ResponseEntity<String>(json, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Agregar extensionTmp temporal por idcartaparticipanteTmp
    @RequestMapping(value = "/extensionTmp/{codigo}", method = RequestMethod.GET)
    public String extensionTmp(@PathVariable(value = "codigo") Integer codigo, Model model) throws Exception {
        try {
            // obtener por ID al participantecartaTmp
            ParticipanteCartaTmp participantecartatmp = this.scanCartaService.getAllParticipanteCartaTmpById(codigo);
            model.addAttribute("participantecartatmp",participantecartatmp);

            List<DetalleParteTmp> partestmp = scanCartaService.getDetalleParteTmpById(participantecartatmp.getId());
            model.addAttribute("listPartestmp",partestmp);

            model.addAttribute("version",participantecartatmp.getVersion().getVersion());
            model.addAttribute("idversion",participantecartatmp.getVersion().getIdversion());
            model.addAttribute("estudio", participantecartatmp.getVersion().getEstudio().getNombre());
            model.addAttribute("codigo_participante",participantecartatmp.getIdparticipante());
            model.addAttribute("nombre1Tutor", participantecartatmp.getName1tutor());
            String name2 = (participantecartatmp.getName2tutor()=="") ? "" : participantecartatmp.getName2tutor();
            model.addAttribute("nombre2Tutor", name2 );
            model.addAttribute("Surname1tutor",  participantecartatmp.getSurname1tutor());
            String ape2 = (participantecartatmp.getSurname2tutor()== "") ? "" : participantecartatmp.getSurname2tutor();
            model.addAttribute("Surname2tutor", ape2 );

            List<Extensiones> exts =  this.scanCartaService.getExtension(participantecartatmp.getVersion().getIdversion());
            model.addAttribute("exts",exts);

            model.addAttribute("caso", new ExtensionesTmp());

            List<ExtensionesTmp> listaExtension = this.scanCartaService.getListExtensionTmp();
            model.addAttribute("listaExtension",listaExtension);

            model.addAttribute("editando", false);
            return "CatalogoScanCarta/ExtensionTmp";
        } catch (Exception e) {
            return "404";
        }
    }

    // Metodo para editar Extension Temporal
    @RequestMapping(value = "/editextensionTmp/{idParticipantExtensiontmp}", method = RequestMethod.GET)
    public String editextensionTmp(Model model, @PathVariable(value = "idParticipantExtensiontmp") Integer idParticipantExtensiontmp) throws Exception {
        try {

            // obtener por ID la Extension
            ExtensionesTmp caso = this.scanCartaService.getExtensionTmpById(idParticipantExtensiontmp);
            model.addAttribute("caso", caso);


            // obtener por ID al participantecartaTmp
            ParticipanteCartaTmp participantecartatmp = this.scanCartaService.getAllParticipanteCartaTmpById(caso.getParticipantecartatmp().getId());
            model.addAttribute("participantecartatmp",participantecartatmp);


            List<Extensiones> exts =  this.scanCartaService.getExtension(caso.getParticipantecartatmp().getVersion().getIdversion());
            model.addAttribute("exts",exts);

            List<ExtensionesTmp> listaExtension = this.scanCartaService.getListExtensionTmp();
            model.addAttribute("listaExtension",listaExtension);

            model.addAttribute("agregando", false);
            model.addAttribute("editando", true);
        } catch (Exception e) {
            return "404";
        }
        return "CatalogoScanCarta/ExtensionTmp";
    }



    //GUARDAR/EDIT EXTENSION TEMPORAL //cartas/saveExtensionTmp
    @RequestMapping(value = "/saveExtensionTmp", method = RequestMethod.POST, produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody
    ResponseEntity<String> saveExtensionTmp(
           @RequestParam(value = "idExtensiontmp",    defaultValue = "", required = false) String idExtensiontmp
          ,@RequestParam(value = "participantecartatmp",         defaultValue = "", required = false) String participantecartatmp
          ,@RequestParam(value = "fechaExtension",               defaultValue = "", required = false) String fechaExtension
          ,@RequestParam(value = "idExtension",                  defaultValue = "", required = false) String idExtension
          ,@RequestParam(value = "nombre1Tutor",                 defaultValue = "", required = false) String nombre1Tutor
          ,@RequestParam(value = "nombre2Tutor",                 defaultValue = "", required = false) String nombre2Tutor
          ,@RequestParam(value = "apellido1Tutor",               defaultValue = "", required = false) String apellido1Tutor
          ,@RequestParam(value = "apellido2Tutor",               defaultValue = "", required = false) String apellido2Tutor
          ,@RequestParam(value = "chktestigo",                   defaultValue = "", required = false) String chktestigo
          ,@RequestParam(value = "nombre1Testigo",               defaultValue = "", required = false) String nombre1Testigo
          ,@RequestParam(value = "nombre2Testigo",               defaultValue = "", required = false) String nombre2Testigo
          ,@RequestParam(value = "apellido1Testigo",             defaultValue = "", required = false) String apellido1Testigo
          ,@RequestParam(value = "apellido2Testigo",             defaultValue = "", required = false) String apellido2Testigo
          ,@RequestParam(value = "observacion",                  defaultValue = "", required = false) String observacion
          ,@RequestParam(value = "idversion",                    defaultValue = "", required = false) String idversion
          ,@RequestParam(value = "accion",                       defaultValue = "", required = false) String accion
    ) throws Exception {
        String computerName = InetAddress.getLocalHost().getHostName();
        int id_participante_carta_tmp = Integer.parseInt(participantecartatmp);
        int codigo_extension = Integer.parseInt(idExtension);
        Extensiones ex = this.scanCartaService.getExtensionById(codigo_extension);
        ParticipanteCartaTmp pct = this.scanCartaService.getAllParticipanteCartaTmpById(id_participante_carta_tmp);
        if (accion.equals("true")){
            ExtensionesTmp objToEdit = new ExtensionesTmp();
            int cod = Integer.parseInt(idExtensiontmp);
            objToEdit.setIdParticipantExtensiontmp(cod);
            objToEdit.setDeviceid(computerName);
            objToEdit.setRecordDate(new Date());
            objToEdit.setRecordUser(SecurityContextHolder.getContext().getAuthentication().getName());
            objToEdit.setEstado('1');
            objToEdit.setPasive('0');
            objToEdit.setParticipantecartatmp(pct);
            objToEdit.setFechaExtension(DateUtil.StringToDate(fechaExtension, "dd/MM/yyyy"));

            objToEdit.setNombre1Tutor(nombre1Tutor.toUpperCase());
            String name2Tutor = (nombre2Tutor != "") ? nombre2Tutor.toUpperCase():"";
            objToEdit.setNombre2Tutor(name2Tutor);
            objToEdit.setApellido1Tutor(apellido1Tutor.toUpperCase());
            String ape2tutor = (apellido2Tutor != "")? apellido2Tutor.toUpperCase():"";
            objToEdit.setApellido2Tutor(ape2tutor);

            boolean isTestigoPresent = (chktestigo.equals("1")) ? true : false;
            objToEdit.setTestigoPresente(isTestigoPresent);
            objToEdit.setNombre1Testigo(nombre1Testigo.toUpperCase());
            String name2testigo = (nombre2Testigo != "")? nombre2Testigo.toUpperCase():"";
            objToEdit.setNombre2Testigo(name2testigo);
            objToEdit.setApellido1Testigo(apellido1Testigo.toUpperCase());
            String surmane2Testigo = (apellido2Testigo!="")?apellido2Testigo.toUpperCase():"";
            objToEdit.setApellido2Testigo(surmane2Testigo);

            String ob = (observacion != "") ? observacion.toUpperCase() : "";
            objToEdit.setObservacion(ob);
            objToEdit.setExtensiones(ex);
            this.scanCartaService.guardarExtensionTmp(objToEdit);
            return JsonUtil.createJsonResponse(objToEdit);
        }else { // Guardar Nuevo
            int cod = Integer.parseInt(participantecartatmp);
            int codExtension = Integer.parseInt(idExtension);
            if (!scanCartaService.verificaSiyaTieneExtension(id_participante_carta_tmp, codExtension, fechaExtension)) {
                try {
                    ExtensionesTmp tmp = new ExtensionesTmp();
                    tmp.setDeviceid(computerName);
                    tmp.setRecordDate(new Date());
                    tmp.setRecordUser(SecurityContextHolder.getContext().getAuthentication().getName());
                    tmp.setEstado('1');
                    tmp.setPasive('0');
                    tmp.setFechaExtension(DateUtil.StringToDate(fechaExtension, "dd/MM/yyyy"));

                    tmp.setNombre1Tutor(nombre1Tutor.toUpperCase());
                    String name2Tutor = (nombre2Tutor != "") ? nombre2Tutor.toUpperCase() : "";
                    tmp.setNombre2Tutor(name2Tutor);
                    tmp.setApellido1Tutor(apellido1Tutor.toUpperCase());
                    String surname2Tutor = (apellido2Tutor != "")?apellido2Tutor.toUpperCase():"";
                    tmp.setApellido2Tutor(surname2Tutor);

                    boolean isTestigoPresent = (chktestigo.equals("1")) ? true : false;
                    tmp.setTestigoPresente(isTestigoPresent);
                    tmp.setNombre1Testigo(nombre1Testigo.toUpperCase());
                    String name2testigo = (nombre2Testigo != "")? nombre2Testigo.toUpperCase():"";
                    tmp.setNombre2Testigo(name2testigo);
                    tmp.setApellido1Testigo(apellido1Testigo.toUpperCase());
                    String surmane2Testigo = (apellido2Testigo!="")?apellido2Testigo.toUpperCase():"";
                    tmp.setApellido2Testigo(surmane2Testigo);

                    String ob = (observacion != "") ? observacion.toUpperCase() : "";
                    tmp.setObservacion(ob);
                    tmp.setExtensiones(ex);
                    tmp.setParticipantecartatmp(pct);
                    this.scanCartaService.guardarExtensionTmp(tmp);
                    return JsonUtil.createJsonResponse(tmp);
                } catch (Exception e) {
                    Gson gson = new Gson();
                    String json = gson.toJson(e.toString());
                    return new ResponseEntity<String>(json, HttpStatus.INTERNAL_SERVER_ERROR);
                }
            } else {
                Map<String, String> map = new HashMap<String, String>();
                map.put("msj", "Registro ya existe.");
                return createJsonResponse(map);
            }
        }
    }
    //region Eliminar TODO Temporales


    // Metodo para poner pasivo extension temporal.
    @RequestMapping(value = "ExtensionTmpPasive", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<String> ExtensionTmpPasive(@RequestParam(value = "codigo", required = true) Integer codigo) throws Exception {
        try {
            ExtensionesTmp extTemporal = this.scanCartaService.getExtensionTmpById(codigo);
            if(extTemporal!=null){
                extTemporal.setPasive('1');
                this.scanCartaService.guardarExtensionTmp(extTemporal);
            }
            return JsonUtil.createJsonResponse("Registros Anulados!");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/deleteAllTmp", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<String> disable(@RequestParam(value = "idparticipantecartatmp", required = true) Integer idparticipantecartatmp
    ) throws Exception {
        try {
            ParticipanteCartaTmp p = this.scanCartaService.getAllParticipanteCartaTmpById(idparticipantecartatmp);
            if (p != null) {
                if(this.scanCartaService.Borrar_Detalle_Partes_tmp(p.getId())){
                    this.scanCartaService.Borrar_Participante_Carta_Extension(p.getId());
                }
                return JsonUtil.createJsonResponse(p);
            }else{
                Map<String, String> map = new HashMap<String, String>();
                map.put("msj", "No se encontraron registros, con ID: ".concat(""+p.getId()));
                return createJsonResponse(map);
            }
        } catch (Exception e) {
            Gson gson = new Gson();
            String json = gson.toJson(e.toString());
            return new ResponseEntity<String>(json, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //endregion

    //region Buscar Por Nombres y Apellidos
    @RequestMapping(value = "/getNombre1", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    List<String> getNombre1(@RequestParam(value = "nombre1", required = true) String nombre1)
            throws Exception {
        ArrayList<String> nombreArrayList = null;
        try {
            List<String> listaPart = this.scanCartaService.getNombre1(nombre1);
            return listaPart;
        }catch (Exception e){
            return nombreArrayList = null;
        }
    }
    @RequestMapping(value = "/getNombre2", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    List<String> getNombre2(@RequestParam(value = "nombre2", required = true) String nombre2)
            throws Exception {
        ArrayList<String> nombreArrayList = null;
        try {
            List<String> listaPart = this.scanCartaService.getNombre2(nombre2);
            return listaPart;
        }catch (Exception e){
            return nombreArrayList = null;
        }
    }
    @RequestMapping(value = "/getApellido1", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    List<String> getApellido1(@RequestParam(value = "apellido1", required = true) String apellido1)
            throws Exception {
        ArrayList<String> nombreArrayList = null;
        try {
            List<String> listaPart = this.scanCartaService.getApellido1(apellido1);
            return listaPart;
        }catch (Exception e){
            return nombreArrayList = null;
        }
    }
    @RequestMapping(value = "/getApellido2", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    List<String> getApellido2(@RequestParam(value = "apellido2", required = true) String apellido2)
            throws Exception {
        ArrayList<String> nombreArrayList = null;
        try {
            List<String> listaPart = this.scanCartaService.getApellido2(apellido2);
            return listaPart;
        }catch (Exception e){
            return nombreArrayList = null;
        }
    }

//endregion


    //region  Pasar datos de una tabla Temporal a la tabla oficial **
    @RequestMapping(value = "/saveTmpsToOficial", method = RequestMethod.POST, produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody
    ResponseEntity<String> saveTmpsToOficial(@RequestBody List<Integer> dataArrayToSend)throws Exception{
        try{
            String computerName = InetAddress.getLocalHost().getHostName();
                int contador = 0;
            for (Integer num : dataArrayToSend){// Verificar q todas las q suban sean extensiones q no esten pasive 1
                ParticipanteCartaTmp cartaTemporal = this.scanCartaService.getAllParticipanteCartaTmpById(num);
                Participante participanteNuevo = this.scanCartaService.getParticipante(cartaTemporal.getIdparticipante());
                if (participanteNuevo == null){
                    continue;
                }
                String string  = participanteNuevo.getEdad();
                String[] parts = string.split("/");
                int anios = Integer.parseInt( parts[0] ); // 0
                int meses = Integer.parseInt( parts[1] ); // 11
                int dias  =  Integer.parseInt( parts[2] ); // 4
                ParticipanteCarta pc = new ParticipanteCarta();
                pc.setEdadyears(anios);
                pc.setEdadmeses(meses);
                pc.setEdaddias(dias);
                pc.setFechacarta(cartaTemporal.getFechacarta());
                pc.setRecordDate(new Date());
                pc.setRecordUser(SecurityContextHolder.getContext().getAuthentication().getName());
                pc.setDeviceid(computerName);
                pc.setEstado('1');
                pc.setPasive('0');
                pc.setQuienfirma(cartaTemporal.getName1tutor());
                pc.setNombre2Firma(cartaTemporal.getName2tutor());
                pc.setApellido1Firma(cartaTemporal.getSurname1tutor());
                pc.setApellido2Firma(cartaTemporal.getSurname2tutor());
                pc.setNombre1testigo(cartaTemporal.getNombre1testigo().toUpperCase());
                String nom1Testigo = (cartaTemporal.getNombre1testigo() !=  "") ? cartaTemporal.getNombre2testigo().toUpperCase() : "";
                pc.setNombre2testigo(nom1Testigo);
                pc.setApellido1testigo(cartaTemporal.getApellido1testigo());
                String ape2Testigo = (cartaTemporal.getApellido2testigo() != "") ? cartaTemporal.getApellido2testigo().toUpperCase() : "";
                pc.setApellido2testigo(ape2Testigo);
                String o = ( cartaTemporal.getObservacion() != "" ) ? cartaTemporal.getObservacion().toUpperCase() : "";
                pc.setObservacion(o);
                pc.setPq_anulada("");
                pc.setProyecto(cartaTemporal.getProyecto());
                Personal person = new Personal();
                person.setCodigo(cartaTemporal.getRecurso());
                Integer relfam = cartaTemporal.getRelfam();
                pc.setRelfam(relfam);
                boolean testgPresent = (cartaTemporal.isTestigopresent() == false) ? false : true;
                pc.setTestigopresent(testgPresent);
                pc.setTipoasentimiento(cartaTemporal.getTipoasentimiento());
                pc.setParticipante(participanteNuevo);
                pc.setPersonal(person);
                Version version = new Version();
                version.setIdversion(cartaTemporal.getVersion().getIdversion());
                pc.setVersion(version);
                pc.setContactoFuturo(cartaTemporal.isContactoFuturo());
                //boolean asent = (cartaTemporal.isAsentimiento() == true) ? true : false;
                pc.setAsentimiento(pc.getAsentimiento());
                contador++;
                scanCartaService.saveOrUpdateScanCarta(pc);
                List<DetalleParteTmp> detalleParteTmps = this.scanCartaService.getDetalleParteTmpById(cartaTemporal.getId());
                if (detalleParteTmps != null) {
                    for (DetalleParteTmp dparte : detalleParteTmps) {
                        Parte pr = new Parte();
                        DetalleParte dp = new DetalleParte();
                        dp.setParticipantecarta(pc);
                        dp.setAcepta(dparte.isAcepta());
                        pr.setIdparte(dparte.getParte().getIdparte());
                        dp.setParte(pr);
                        dp.setDeviceid(computerName);
                        dp.setRecordDate(new Date());
                        dp.setRecordUser(SecurityContextHolder.getContext().getAuthentication().getName());
                        dp.setEstado('1');
                        dp.setPasive('0');
                        scanCartaService.saveParteCarta(dp);
                    }
                }
                boolean existExtension = scanCartaService.isExistExtensionesById(cartaTemporal.getId());
                if (existExtension) {
                    System.out.println("Extension exist para eliminar con id: ".concat(""+cartaTemporal.getId()));
                    this.scanCartaService.Borrar_Participante_Carta_Extension(cartaTemporal.getId());
                }
                boolean SiEliminoPartes = this.scanCartaService.Borrar_Detalle_Partes_tmp(cartaTemporal.getId());
                if(SiEliminoPartes){
                    System.out.println("Participante_Carta_Tmp exist para eliminar con id: ".concat(""+cartaTemporal.getId()));
                    this.scanCartaService.Borrar_Participante_Carta_Tmp(cartaTemporal.getId());
                }
            }
            return JsonUtil.createJsonResponse("Registro transferidos: ".concat(""+contador));
        }catch (DataAccessException ex){
            Gson gson = new Gson();
            String json = gson.toJson(ex.getMostSpecificCause().getMessage());
            return new ResponseEntity<String>(json, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    //endregion


    /*  Esta Funcion retorna un Json  */
    private ResponseEntity<String> createJsonResponse( Object o )
    {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");
        Gson gson = new Gson();
        String json = gson.toJson(o);
        UnicodeEscaper escaper = UnicodeEscaper.above(127);
        json = escaper.translate(json);
        return new ResponseEntity<String>( json, headers, HttpStatus.CREATED );
    }
}
