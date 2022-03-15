package ni.org.ics.estudios.web.controller.Comparacion;

import com.google.gson.Gson;
import ni.org.ics.estudios.domain.CartaConsentimiento;
import ni.org.ics.estudios.domain.Participante;
import ni.org.ics.estudios.domain.catalogs.Barrio;
import ni.org.ics.estudios.domain.catalogs.Personal_Cargo;
import ni.org.ics.estudios.domain.cohortefamilia.RecepcionMuestra;
import ni.org.ics.estudios.domain.covid19.ParticipanteCasoCovid19;
import ni.org.ics.estudios.domain.muestreoanual.*;
import ni.org.ics.estudios.dto.ParticipanteBusquedaDto;
import ni.org.ics.estudios.language.MessageResource;
import ni.org.ics.estudios.service.MessageResourceService;
import ni.org.ics.estudios.service.UsuarioService;
import ni.org.ics.estudios.service.comparacion.ComparasionService;
import ni.org.ics.estudios.users.model.UserSistema;
import ni.org.ics.estudios.web.utils.DateUtil;
import ni.org.ics.estudios.web.utils.JsonUtil;
import org.apache.commons.lang3.text.translate.UnicodeEscaper;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.Resource;
import java.net.InetAddress;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by ICS on 07/01/2021.
 */

@Controller
@RequestMapping("/comparacion")
public class ComparacionController {

    @Resource(name="ComparacionService")
    private ComparasionService comparasionService;

    @Resource(name = "messageResourceService")
    private MessageResourceService messageResourceService;

    //region RECEPCION BHC CONTROLER***
    @RequestMapping(value="/bhc", method = RequestMethod.GET)
    public String bhc(ModelMap model) throws Exception {
       //List<UserSistema> usuarios =  this.comparasionService.getUsuarios();
       //model.addAttribute("usuarios", usuarios);
       List<MessageResource> lugar = messageResourceService.getCatalogo("CAT_LUGAR_RECEP");
       model.addAttribute("lugar", lugar);
       model.addAttribute("agregando",true);
       model.addAttribute("editando",false);
        List<RecepcionBHC> listaBhc = this.comparasionService.getAllBhc();
        model.addAttribute("listaBhc", listaBhc);
       model.addAttribute("RecepcionBHC", new RecepcionBHC());
       return "/comparacion/RecepcionBHC";
    }

    @RequestMapping(value = "/editBhc/{id}/{fechaRecBHC}", method = RequestMethod.GET)
    public String editBhc(Model model, @PathVariable("id") Integer id,
                                       @PathVariable("fechaRecBHC") String fechaRecBHC)throws Exception {
        try{
            Date dfechabhc = DateUtil.StringToDate(fechaRecBHC,"yyyy-MM-dd HH:mm:ss");
            Date enDate = DateUtil.StringToDate(fechaRecBHC,"yyyy-MM-dd HH:mm:ss");
            RecepcionBHC caso = this.comparasionService.getRecepcionBhcById(id,dfechabhc,enDate);
            model.addAttribute("caso", caso);
            model.addAttribute("agregando",false);
            model.addAttribute("editando",true);
            //List<UserSistema> usuarios =  this.comparasionService.getUsuarios();
            //model.addAttribute("usuarios", usuarios);
            List<MessageResource> lugar = messageResourceService.getCatalogo("CAT_LUGAR_RECEP");
            model.addAttribute("lugar", lugar);
            List<RecepcionBHC> listaBhc = this.comparasionService.getAllBhc();
            model.addAttribute("listaBhc", listaBhc);
            return"/comparacion/RecepcionBHC";
        }
        catch (Exception e){
            System.err.println(e.getMessage());
            throw e;
        }
    }

    @RequestMapping(value = "/deletebhc", method = RequestMethod.POST)
    public String deletebhc(@RequestParam(value = "idbhc", required = false) int idbhc,
                            @RequestParam(value = "datebhc", required = false, defaultValue = "") String datebhc)throws Exception{
        try {
            Date dfechabhc = DateUtil.StringToDate(datebhc,"dd/MM/yyyy");
            Date enDate = DateUtil.StringToDate(datebhc,"dd/MM/yyyy");
            this.comparasionService.deletebhc(idbhc, dfechabhc,enDate);
            return"/comparacion/RecepcionBHC";
        }catch (Exception e){
            System.err.println(e.getMessage());
            throw e;
        }
    }

    @RequestMapping(value="/GuardarBhc", method=RequestMethod.POST)
    public ResponseEntity<String> GuardarBhc(@RequestParam(value = "codigo", required = false, defaultValue = "") int codigo
                                             ,@RequestParam( value="fechaBhc", required=false, defaultValue = "") String fechaBhc
                                             ,@RequestParam(value = "lugar", required = false, defaultValue = "") String lugar
                                             ,@RequestParam(value= "username", required = false, defaultValue = "") String username
                                             ,@RequestParam(value="observacion", required = false, defaultValue = "") String observacion
                                             ,@RequestParam(value="volumen", required = false, defaultValue = "") String volumen
                                             ,@RequestParam(value="chkPaxGene", required = false, defaultValue = "") String chkPaxGene
                                             ,@RequestParam(value="accion", required = false, defaultValue = "") String accion
                                             ,@RequestParam(value = "fechaToEdit", required = false, defaultValue = "")String fechaToEdit
            ,@RequestParam(value = "fechaRegistBhc", required = false, defaultValue = "")String fechaRegistBhc
            ,@RequestParam(value = "hora", required = false, defaultValue = "")String hora

    )throws Exception{
        try{
            Map<String, String> map = new HashMap<String, String>();
            if (accion.equals("false")) {
                RecepcionBHC objBhc = new RecepcionBHC();
                RecepcionBHCId robhc = new RecepcionBHCId();
                robhc.setCodigo(codigo);
                robhc.setFechaRecBHC(DateUtil.StringToDate(fechaBhc, "dd/MM/yyyy"));
                objBhc.setRecBhcId(robhc);
                objBhc.setFecreg(DateUtil.StringToDate(fechaRegistBhc,"dd/MM/yyyy HH:mm:ss"));
                objBhc.setEstado("1");
                objBhc.setLugar(lugar);
                objBhc.setObservacion(observacion);
                Boolean pax = false;
                if (chkPaxGene.equals("")) {
                    pax = false;
                } else {
                    pax = true;
                }
                objBhc.setPaxgene(pax);
                objBhc.setUsername(username);
                double volbhc = Double.parseDouble(volumen);
                objBhc.setVolumen(volbhc);
                Integer result = this.comparasionService.SaveBHC(objBhc);
                if (result == 1) map.put("msj", "Registro Guardado." );
                return JsonUtil.createJsonResponse(objBhc);
            }else{

                Date dateToEdit = DateUtil.StringToDate(fechaBhc, "dd/MM/yyyy");
                Boolean pax = false;
                if (chkPaxGene.equals("")) {
                    pax = false;
                } else {
                    pax = true;
                }
                double volbhc = Double.parseDouble(volumen);
                Date star = DateUtil.StringToDate(fechaToEdit,"dd/MM/yyyy");
                Date fin =  DateUtil.StringToDate(fechaToEdit, "dd/MM/yyyy");
                String estado = "1";
                int result = this.comparasionService.editarManual(estado, codigo,fechaRegistBhc,lugar,observacion,pax,username,volbhc,dateToEdit,star );
                if (result>=1){
                    map.put("msj", "Registro Actualizado." );
                return JsonUtil.createJsonResponse(map);
                }else{
                    map.put("msjError", "Error al Actualizar." );
                    return JsonUtil.createJsonResponse(map);
                }
            }
        } catch (Exception e){
            Gson gson = new Gson();
            String json = gson.toJson(e.toString());
            System.out.println("error: "+e.getMessage());
            return  new ResponseEntity<String>( json, HttpStatus.CREATED);
        }
    }

//endregion



    //region RECEPCION SEROLOGIA CONTROLLER ***

    //Listado y Formulario Serologia
    @RequestMapping(value="/serologia", method = RequestMethod.GET)
    public String serologia(ModelMap model) throws Exception {
        List<UserSistema> usuarios =  this.comparasionService.getUsuarios();
        model.addAttribute("usuarios", usuarios);
        List<MessageResource> lugar = messageResourceService.getCatalogo("CAT_LUGAR_RECEP");
        model.addAttribute("lugar", lugar);
        model.addAttribute("agregando",true);
        model.addAttribute("editando",false);
        List<RecepcionSero> listaSero = this.comparasionService.getAllSerologias();
        model.addAttribute("listaSero", listaSero);
        model.addAttribute("caso", new RecepcionSero());
        return "/comparacion/RecepcionSero";
    }

    //Obtener por Id Serologia para Editar
    @RequestMapping(value = "/editSero/{id}", method = RequestMethod.GET)
    public String editSero(Model model, @PathVariable("id") String id)throws Exception {
        try{
            RecepcionSero caso = this.comparasionService.getSerologiaById(id);
            model.addAttribute("caso", caso);
            model.addAttribute("agregando",false);
            model.addAttribute("editando",true);
            List<UserSistema> usuarios =  this.comparasionService.getUsuarios();
            model.addAttribute("usuarios", usuarios);
            List<MessageResource> lugar = messageResourceService.getCatalogo("CAT_LUGAR_RECEP");
            model.addAttribute("lugar", lugar);
            List<RecepcionSero> listaSero = this.comparasionService.getAllSerologias();
            model.addAttribute("listaSero", listaSero);
            return"/comparacion/RecepcionSero";
        }
        catch (Exception e){
            System.err.println(e.getMessage());
            throw e;
        }
    }


    //Guardar Serologia
    @RequestMapping(value = "/saveSerologia", method = RequestMethod.POST)
    public ResponseEntity<String> saveSerologia(@RequestParam(value = "id", required = false, defaultValue = "") String id
            ,@RequestParam(value = "codigoparticipante", required = true) int codigoparticipante
            ,@RequestParam(value = "fechaSero", required = true) String fechaSero
            ,@RequestParam(value = "fecreg",required = true) String fecreg
            ,@RequestParam(value = "volumen", required = true) String volumen
            ,@RequestParam(value = "lugar", required = false) String lugar
            ,@RequestParam(value = "observacion", required = true) String observacion
            ,@RequestParam(value = "username", required = true) String username
            ,@RequestParam(value = "accion", required = false, defaultValue = "") String accion
            ,@RequestParam(value = "fechaReg", required = false, defaultValue = "") String fechaReg
    ) throws Exception {
        try {
            RecepcionSero objSerologia = new RecepcionSero();
            String ComputerName = "NicaUmich2";
            final String uuid = UUID.randomUUID().toString().replace("-", "");
            String micodeUUID = ComputerName+"-"+uuid;

            if (accion.equals("true") && id != null){// actualiza el registro
                objSerologia.setId(id);
                objSerologia.setCodigo(codigoparticipante);
                objSerologia.setEstado("1");
                objSerologia.setFechaRecSero(DateUtil.StringToDate(fechaSero, "dd/MM/yyyy"));
                objSerologia.setFecreg(DateUtil.StringToDate(fechaReg,"dd/MM/yyyy HH:mm:ss"));
                objSerologia.setLugar(lugar);
                objSerologia.setObservacion(observacion);
                objSerologia.setUsername(username);
                objSerologia.setVolumen(Double.parseDouble(volumen));
                if (!id.isEmpty())
                    this.comparasionService.ModificarSerologia(objSerologia);
            }else{//Guarda si es nuevo ***

                objSerologia.setId(micodeUUID);
                objSerologia.setCodigo(codigoparticipante);
                objSerologia.setFechaRecSero(DateUtil.StringToDate(fechaSero, "dd/MM/yyyy"));
                Date FechaDelRegistro = DateUtil.StringToDate(fechaReg,"dd/MM/yyyy HH:mm:ss");
                System.out.println("fecha registro: "+FechaDelRegistro);
                objSerologia.setFecreg(DateUtil.StringToDate(fechaReg, "dd/MM/yyyy HH:mm:ss"));
                objSerologia.setVolumen(Double.parseDouble(volumen));
                objSerologia.setLugar(lugar);
                objSerologia.setObservacion(observacion);
                objSerologia.setUsername(username);
                objSerologia.setEstado("1");
                this.comparasionService.GuardarSerologia(objSerologia);
            }
            return createJsonResponse(objSerologia);
        } catch (Exception e) {
            Gson gson = new Gson();
            String json = gson.toJson(e.toString());
            return new ResponseEntity<String>(json, HttpStatus.CREATED);
        }
    }

    @RequestMapping( value="deletesero", method=RequestMethod.POST)
    public String deleteSero(@RequestParam(value = "id", required = true) String id)throws Exception{
        try{
            RecepcionSero casoDel = this.comparasionService.getSerologiaById(id);
            if (casoDel!=null) {
                this.comparasionService.DeleteSerologiaById(id);
            }
            return "/comparacion/RecepcionSero";
        }
        catch(Exception e){
            Gson gson = new Gson();
            String json = gson.toJson(e.toString());
            return  "/comparacion/RecepcionSero";
        }
    }
    //endregion


    //region RECEPCION MUESTRAS
    @RequestMapping(value="/muestra", method = RequestMethod.GET)
    public String muestra(ModelMap model) throws Exception {
        //List<UserSistema> usuarios =  this.comparasionService.getUsuarios();
        //model.addAttribute("usuarios", usuarios);
        List<Personal_Cargo> recurso =  this.comparasionService.getRecursos();
        model.addAttribute("recurso", recurso);
        List<MessageResource> SiNo = messageResourceService.getCatalogo("CHF_CAT_SINO");
        model.addAttribute("SiNo", SiNo);
        model.addAttribute("agregando",true);
        model.addAttribute("editando",false);
        List<MuestraMA> listaMx = this.comparasionService.getAllMuestras();
        model.addAttribute("listaMx", listaMx);
        model.addAttribute("caso", new MuestraMA());
        return "/comparacion/RecepcionMuestra";
    }


    @RequestMapping(value = "/editMuestra/{id}/{fechaMx}", method = RequestMethod.GET)
    public String editMuestra(Model model, @PathVariable("id") Integer id,
                          @PathVariable("fechaMx") String fechaMx)throws Exception {
        try{
            Date dfechabhc = DateUtil.StringToDate(fechaMx,"yyyy-MM-dd HH:mm:ss");
            Date enDate = DateUtil.StringToDate(fechaMx,"yyyy-MM-dd HH:mm:ss");
            MuestraMA caso = this.comparasionService.getMuestraByID(id, dfechabhc, enDate);
            model.addAttribute("caso", caso);

            List<MessageResource> SiNo = messageResourceService.getCatalogo("CHF_CAT_SINO");
            model.addAttribute("SiNo", SiNo);

            List<Personal_Cargo> recurso =  this.comparasionService.getRecursos();
            model.addAttribute("recurso", recurso);

            model.addAttribute("agregando",false);
            model.addAttribute("editando",true);
            List<UserSistema> usuarios =  this.comparasionService.getUsuarios();
            model.addAttribute("usuarios", usuarios);

            List<MuestraMA> listaMx = this.comparasionService.getAllMuestras();
            model.addAttribute("listaMx", listaMx);

            return"/comparacion/RecepcionMuestra";
        }
        catch (Exception e){
            System.err.println(e.getMessage());
            throw e;
        }
    }



    @RequestMapping(value = "/deletemuestra", method = RequestMethod.POST)
    public String deletemuestra(@RequestParam(value = "codigoMuestra", required = false) int codigoMuestra,
                                @RequestParam(value = "fechamuestra", required = false, defaultValue = "") String fechamuestra)throws Exception{
        try {
            Date dfechabhc = DateUtil.StringToDate(fechamuestra,"dd/MM/yyyy HH:mm:ss");
            System.out.println(dfechabhc);
            Date enDate = DateUtil.StringToDate(fechamuestra,"dd/MM/yyyy HH:mm:ss");
           Integer cod = this.comparasionService.deleteMuestra(codigoMuestra,dfechabhc,enDate);
           return "/comparacion/RecepcionMuestra";
        }catch (Exception e){
            System.err.println(e.getMessage());
            throw e;
        }
    }


    @RequestMapping(value = "searchParticipant", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    ResponseEntity<String> buscarParticipante(@RequestParam(value="participantCode", required=true ) Integer participantCode) throws ParseException {
        try {
            ParticipanteBusquedaDto participante = this.comparasionService.getDatosParticipanteByCodigo(participantCode);
            if (participante!=null){
                if (participante.getEstado().equals(0))
                    return JsonUtil.createJsonResponse("Participante retirado");
            }else return JsonUtil.createJsonResponse("No se encontró participante según el código ingresado");
            return JsonUtil.createJsonResponse(participante);
        } catch (Exception e) {
            Gson gson = new Gson();
            String json = gson.toJson(e.toString());
            return  new ResponseEntity<String>( json, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/saveMuestra", method = RequestMethod.POST)
    public ResponseEntity<String> saveMuestra(@RequestParam(value = "codigoMx", required = true) int codigoMx
            ,@RequestParam(value = "fechaMx", required = true) String fechaMx
            ,@RequestParam(value = "recurso1", required = true) String recurso1
            ,@RequestParam(value = "recurso2", required = false) String recurso2
            ,@RequestParam(value = "username", required = true) String username
            ,@RequestParam(value = "terrenoMx", required = true) String terrenoMx
            ,@RequestParam(value = "txtpinchazo", required = true) String txtpinchazo
            ,@RequestParam(value = "tubobhc", required = false) String tubobhc
            ,@RequestParam(value = "tuboleuco", required = false) String tuboleuco
            ,@RequestParam(value = "tuborojo", required = false) String tuborojo
            ,@RequestParam(value = "estudiosAct", required = true) String estudiosAct
            ,@RequestParam(value = "chkEstado", required = false) String chkEstado
            ,@RequestParam(value = "fechaReg", required = false) String fechaReg
            /*,@RequestParam(value = "chkEliminadoMx", required = false) String chkEliminadoMx
            ,@RequestParam(value = "chkEstadoMx", required = false) String chkEstadoMx*/
    )throws Exception{
        try {
            MuestraMA o = new MuestraMA();
            MovilInfo movil = new MovilInfo();
            String ComputerName = "NicaUmich2";
            movil.setIdInstancia(Integer.valueOf(148));
            movil.setInstancePath(ComputerName);
            String estado;
            if(chkEstado.equals("on")){
                estado ="1";
            }else{
                estado = "0";
            }
            movil.setEstado(estado);

            movil.setEliminado(false);
            SimpleDateFormat form = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
            movil.setStart(form.format(new Date()));
            movil.setEnd(form.format(new Date()));
            movil.setDeviceid(ComputerName);
            movil.setSimserial("111");
            movil.getPhonenumber();
            movil.setToday(DateUtil.StringToDate(fechaReg, "dd/MM/yyyy HH:mm:ss"));
            movil.setUsername(username);
            movil.setRecurso1(Integer.parseInt(recurso1));
            movil.setRecurso2(Integer.parseInt(recurso2));
            movil.setUltimoCambio(form.format(new Date()));
            o.setMovilInfo(movil);

            MuestraId objMuestraId =new MuestraId();
            objMuestraId.setCodigo(codigoMx);
            o.setmId(objMuestraId);
            objMuestraId.setFechaMuestra(DateUtil.StringToDate(fechaMx, "dd/MM/yyyy"));
            o.setTerreno(terrenoMx);

            if (txtpinchazo.equals("")){
             //txtpinchazo = null;
            }else{
                o.setPinchazos(Integer.parseInt(txtpinchazo));
            }

            if (tubobhc.equals("")){
               // tubobhc = null;
            }else {
                o.setTuboBHC(Integer.parseInt(tubobhc));
            }

            if (tuboleuco.equals("")){
                //tuboleuco = null;
            }else {
                o.setTuboLeu(Integer.parseInt(tuboleuco));
            }

            if (tuborojo.equals("")){
                //tuborojo = null;
            }else {
                o.setTuboRojo(Integer.parseInt(tuborojo));
            }
            o.setEstudiosAct(estudiosAct);
            //campos rellenos
            o.setFiebreM(0);

            this.comparasionService.SaveMuestra(o);
            return createJsonResponse(o);

        }catch (Exception e) {
            Gson gson = new Gson();
            String json = gson.toJson(e.toString());
            return new ResponseEntity<String>(json, HttpStatus.CREATED);
        }
    }
    //endregion

    @RequestMapping(value = "/getUserName", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    List<String> getUserName(@RequestParam(value = "username", required = true) String username)
            throws Exception {
        ArrayList<String> nombreArrayList = null;
        try {
            List<String> listaPart = this.comparasionService.getNombreUsuario(username);
            return listaPart;
        }catch (Exception e){
            return nombreArrayList = null;
        }
    }

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
