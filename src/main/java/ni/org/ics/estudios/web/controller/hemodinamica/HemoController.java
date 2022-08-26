package ni.org.ics.estudios.web.controller.hemodinamica;

import com.google.gson.Gson;
import ni.org.ics.estudios.domain.Participante;
import ni.org.ics.estudios.domain.catalogs.Barrio;
import ni.org.ics.estudios.domain.catalogs.Personal;
import ni.org.ics.estudios.domain.catalogs.Personal_Cargo;
import ni.org.ics.estudios.domain.hemodinamica.DatosHemodinamica;
import ni.org.ics.estudios.domain.hemodinamica.HemoDetalle;
import ni.org.ics.estudios.domain.muestreoanual.ParticipanteProcesos;
import ni.org.ics.estudios.dto.RangosFrecuenciasCardiacas;
import ni.org.ics.estudios.dto.RangosPresion;
import ni.org.ics.estudios.language.MessageResource;
import ni.org.ics.estudios.service.MessageResourceService;
import ni.org.ics.estudios.service.muestreoanual.ParticipanteProcesosService;
import ni.org.ics.estudios.service.hemodinanicaService.DatoshemodinamicaService;
import ni.org.ics.estudios.web.utils.DateUtil;
import ni.org.ics.estudios.web.utils.JsonUtil;
import org.apache.commons.lang3.text.translate.UnicodeEscaper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.*;

/**
 * Created by ICS_Inspiron3 on 22/05/2019.
 */
@Controller
@RequestMapping("/hemo")
public class HemoController {
    private static final Logger logger = LoggerFactory.getLogger(HemoController.class);

    /* Lista de Detalle Hemodinamico del Participante */
    @RequestMapping(value="/listDetailsHemo/{idDatoHemo}", method = RequestMethod.GET)
    public ModelAndView addDetalleHemo(@PathVariable(value="idDatoHemo" ) String idDatoHemo)throws ParseException {
        ModelAndView mv = new ModelAndView();
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            List<HemoDetalle>  hemo = datoshemodinamicaService.getListHemoDetalle(idDatoHemo);
            DatosHemodinamica obj = datoshemodinamicaService.getbyId(idDatoHemo);
            /* Esto es para obtener la lista de nivel de conciencia compararla recorrerla en la vista con un foreach i un if*/
            List<MessageResource> nivel = messageResourceService.getCatalogo("NIVELCONCIENCIA");
            mv.addObject("nivel",nivel);
            List<MessageResource> extremidades = messageResourceService.getCatalogo("EXTREMIDADES");
            mv.addObject("extremidades", extremidades);
            List<MessageResource> pulsoCalidad = messageResourceService.getCatalogo("PULSOCALIDAD");
            mv.addObject("pulsoCalidad", pulsoCalidad);
            List<MessageResource> llenadoCapilar = messageResourceService.getCatalogo("LLENADOCAPILAR");
            mv.addObject("llenadoCapilar", llenadoCapilar);
            mv.addObject("hemo", hemo);
            mv.addObject("codigo", obj.getParticipante().getCodigo());
            mv.addObject("nombre", obj.getParticipante().getNombreCompleto());
            mv.addObject("edad",obj.getParticipante().getEdad());
            mv.addObject("fechanac", obj.getParticipante().getFechaNac());
            mv.addObject("expediente", obj.getnExpediente());
            mv.addObject("idDatoHemo", obj.getIdDatoHemo());
            mv.setViewName("/hemodinamica/listadoDetalle");
            return mv;
        }
        catch (Exception e){
            return mv;
        }
    }
    //Formulario para ingresar datos Hemodinamica
    @RequestMapping(value="/create", method = RequestMethod.GET)
    public String login(ModelMap model) {
        List<Barrio> barrios = datoshemodinamicaService.getBarrios();
        model.addAttribute("barrios", barrios);
        return "/hemodinamica/datos";
    }

    /* Instancia de mi Servicio Hemodinamico */
    @Resource(name = "messageResourceService")
    private MessageResourceService messageResourceService;

    @Resource(name = "participanteProcesosService")
    private ParticipanteProcesosService participanteProcesosService;

    // Formulario para Capturar el detalle Hemodinamico
    @RequestMapping(value="adddetails/{idDatoHemo}", method = RequestMethod.GET)
    public String adddetails(@PathVariable(value="idDatoHemo" ) String idDatoHemo, ModelMap model) {
        try {
            /*Select poblar el select de nivel de conciencia*/
            List<MessageResource> nivelConciencia = messageResourceService.getCatalogo("NIVELCONCIENCIA");
            model.addAttribute("nivelConciencia", nivelConciencia);
            List<MessageResource> pulsoCalidad = messageResourceService.getCatalogo("PULSOCALIDAD");
            model.addAttribute("pulsoCalidad", pulsoCalidad);
            List<MessageResource> extremidades = messageResourceService.getCatalogo("EXTREMIDADES");
            model.addAttribute("extremidades", extremidades);
            List<MessageResource> clasificacion = messageResourceService.getCatalogo("CLASIFICACIONDENGUE");
            model.addAttribute("clasificacion", clasificacion);
            List<MessageResource> llenadoCapilar = messageResourceService.getCatalogo("LLENADOCAPILAR");
            model.addAttribute("llenadoCapilar", llenadoCapilar);
            model.addAttribute("idDatoHemo", idDatoHemo);

            List<MessageResource>obtenerMedicos = this.messageResourceService.getCatalogo("CAT_SELECCIONAR_PERSONAL_HEMODINAMICA");
            String[] personId = obtenerMedicos.get(0).getSpanish().split(",");
            HashSet<Integer> hset = new HashSet<Integer>();
            List<String> cargosId = Arrays.asList(personId);
            for (int i = 0; i < cargosId.size(); i++) {
                int value = Integer.parseInt( cargosId.get(i) );
                hset.add(value);
            }
            List<Personal_Cargo> person = this.datoshemodinamicaService.getPersonal(hset);
            model.addAttribute("person", person);

            List<MessageResource> personaValida = messageResourceService.getCatalogo("PERSONAVALIDA");
            model.addAttribute("personaValida", personaValida);

            List<MessageResource> diuresis = messageResourceService.getCatalogo("DIURESIS");
            model.addAttribute("diuresis", diuresis);
            DatosHemodinamica h = datoshemodinamicaService.getbyId(idDatoHemo);
            List<HemoDetalle> contParams = datoshemodinamicaService.NumeroHemoDet(idDatoHemo);
            model.addAttribute("h",h);
            model.addAttribute("numParameter", (h.getNumParametros()== null) ? "0" : h.getNumParametros() );
            model.addAttribute("contParams",contParams.size());
        } catch (Exception e) {
           System.err.print(e.getMessage());
        }
        return "/hemodinamica/formhemodetalle";
    }

    /* Instancia de mi Servicio Hemodinamico */
    @Resource(name = "datoshemodinamicaService")
    private DatoshemodinamicaService datoshemodinamicaService;


    /*LISTAR DE TODOS LOS PACIENTE CON HOJA HEMODINAMICA -- No está en uso*/
    @RequestMapping(value = "/listado", method = RequestMethod.GET)
    public ModelAndView ListadoHemo() throws Exception {
        try {
        List<DatosHemodinamica> lista = datoshemodinamicaService.getListadoHemo();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("lista", lista);
        modelAndView.setViewName("/hemodinamica/listado");
        return modelAndView;
        }catch (Exception e){
            throw e;
        }
    }
    /* FIN DEL METODO LISTADO*/

    /* Buscar Listado por Codigo Participante */
    @RequestMapping(value = "/listado2", method = RequestMethod.GET)
    public ModelAndView listado2()throws Exception{
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/hemodinamica/listado2");
        return modelAndView;
    }

    //Obtener la lista de hojas por codigo participante
    @RequestMapping(value = "/ListaHoja", method = RequestMethod.GET, produces ="application/json")
    public @ResponseBody
    List<DatosHemodinamica> ListaHoja(@RequestParam(value = "parametro", required=true ) Integer parametro)
            throws Exception{
        List<DatosHemodinamica> lista = null;
        try {
            lista = datoshemodinamicaService.getListadoHemoByID(parametro);
            return lista;
        } catch (Exception e) {
            return  lista = null;
        }
    }
    List<MessageResource> messagediuresis = new ArrayList<MessageResource>();
    List<MessageResource> messagePersonaValida = new ArrayList<MessageResource>();
    /*Buscar lo faltante para modals*/
    @RequestMapping(value = "/ViewResutl", method = RequestMethod.GET, produces="application/json" )
    public @ResponseBody
    String BuscarResultado(@RequestParam(value = "idHemoDetalle") String idHemoDetalle)  throws ParseException {
        Map<String, String> map = new HashMap<String, String>();
        //Map<String, Object> model = new HashMap<String, Object>();
        HemoDetalle obj = datoshemodinamicaService.getByHemoDetalleId(idHemoDetalle);
        map.put("pa", obj.getPa());
        map.put("pd", obj.getPd());
        map.put("pp", obj.getPp());
        map.put("pam", obj.getPam());
        map.put("fcardi", obj.getFc());
        map.put("fr", obj.getFr());
        map.put("tc", obj.getTc());
        map.put("sa", obj.getSa());
        messagediuresis = messageResourceService.getMensajeByCatalogAndCatKeys(obj.getDiuresis(),"DIURESIS");
        map.put("diuresis",getDescripcionCatalogo(obj.getDiuresis(),"DIURESIS"));
        Integer personalID = Integer.parseInt(obj.getPersonaValida());
        Personal personal = this.datoshemodinamicaService.getPersonalById(personalID);
        map.put("personaValida", personal.getIdpersonal().toString() +" - "+ personal.getNombreApellido());
        map.put("densidadU", (obj.getDensidadUrinaria() != null ? obj.getDensidadUrinaria():"-"));
        String jsonResponse;
        jsonResponse = new Gson().toJson(map);
        UnicodeEscaper escaper = UnicodeEscaper.above(127);
        return escaper.translate(jsonResponse);
    }
    private String getDescripcionCatalogo(String codigo,String catroot){
        for(MessageResource rnv : messagediuresis){
            if (rnv.getCatKey().equals(codigo)) {
                if (catroot != "" && rnv.getCatRoot().equals(catroot))
                    return rnv.getSpanish();
            }
        }
        return "-";
    }
    private String getDescripcionCatalogo2(String codigo,String catroot){
        for(MessageResource rnv : messagePersonaValida){
            if (rnv.getCatKey().equals(codigo)) {
                if (catroot != "" && rnv.getCatRoot().equals(catroot))
                    return rnv.getSpanish();
            }
        }
        return "-";
    }
    /* Controlador para realizar por una búsqueda de participante
    * http://localhost:8080/estudios_ics/hemo/searchParticipant?participantCode=100
    * */
    @RequestMapping(value = "/searchParticipant", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
     String BuscarParticipanteByID(@RequestParam(value="parametro", required=true ) Integer parametro)
            throws Exception {
        try {
        Map<String, String> map = new HashMap<String, String>();
        Participante participante = datoshemodinamicaService.getParticipante(parametro);
        if (participante != null){
            ParticipanteProcesos procesos = participanteProcesosService.getParticipante(parametro);
            map.put("estado",procesos.getEstPart().toString());
        }
        map.put("codigo", participante.getCodigo().toString());
        map.put("nombre", participante.getNombreCompleto());
        map.put("fecha", DateUtil.DateToString(participante.getFechaNac(),"dd/MM/yyyy"));
        map.put("edad", participante.getEdad());
        map.put("direccion", participante.getCasa().getDireccion());
        map.put("barrio", participante.getCasa().getBarrio().getCodigo().toString());
        map.put("sexo", participante.getSexo().toString());
        String jsonResponse;
        jsonResponse = new Gson().toJson(map);
        UnicodeEscaper escaper = UnicodeEscaper.above(127);
        return escaper.translate(jsonResponse);
    }catch (Exception e){
        throw e;
    }
    }
    /* Obtener los Rangos de Presión y Frecuencias Cardiacas */
    @RequestMapping(value = "/GetRange", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    String GetRange(@RequestParam(value ="sexo", required = true) String sexo,
                    @RequestParam(value ="fecha", required = true)String fecha) throws Exception{
        try{
            Map<String, String> map = new HashMap<String, String>();
            Date myfecha = DateUtil.StringToDate(fecha,"dd/MM/yyyy");
            String result = DateUtil.obtenerEdad(myfecha);
            String[] part = result.split(" ");
            String part1 = part[0];
            String part2 =part[1];
            Integer num = Integer.valueOf(part1);
            if (num <= 18) {
                RangosPresion objPresion = datoshemodinamicaService.ObtenerRangosPresion(sexo, num, part2);
                RangosFrecuenciasCardiacas objFrec = datoshemodinamicaService.ObtenerFCardiaca(part2, num);
                if (objPresion != null && objFrec != null) {
                    map.put("objPsdmin", objPresion.getPsdmin());
                    map.put("objPsdmed", objPresion.getPsdmed());
                    map.put("objPsdmax", objPresion.getPsdmax());
                    map.put("objPammin", objPresion.getPammin());
                    map.put("objPammed", objPresion.getPammed());
                    map.put("objPammax", objPresion.getPammax());
                    map.put("objfcMin", objFrec.getFcMinima());
                    map.put("objfcMax", objFrec.getFcMedia());
                    map.put("objfcProm", objFrec.getFcPromedio());
                    map.put("objfrMin",objFrec.getFrMinima());
                    map.put("objfrMax", objFrec.getFrMaxima());
                }
            }else{
                map.put("result","mayor de 18 años");
            }
            String jsonResponse;
            jsonResponse = new Gson().toJson(map);
            UnicodeEscaper escaper = UnicodeEscaper.above(127);
            return escaper.translate(jsonResponse);
        }catch (Exception e){
            throw e;
        }
    }

        /* Guardar datos Hemodinamica 29/05/2019 - first save */
        @RequestMapping(value="addHemodinamica", method=RequestMethod.POST)
        public ResponseEntity<String>addHemodinamica(
                  @RequestParam( value="direccion", required=true ) String direccion
                , @RequestParam( value="edad", required=true ) String edad
                , @RequestParam( value="fecha", required=true ) String fecha
                , @RequestParam( value="fconsulta", required=true ) String fconsulta
                , @RequestParam( value="expediente", required=true ) String nExpediente
                , @RequestParam( value="peso", required=true ) String peso
                , @RequestParam( value="sector", required=true ) String sector
                , @RequestParam(value = "barrioF") String barrioF
                , @RequestParam( value="talla", required=true ) double talla
                , @RequestParam( value="telefono", required=true ) String telefono
                , @RequestParam( value="idParticipante" ) int idParticipante
                , @RequestParam( value="fie", required=true ) String fie
                , @RequestParam( value="diasenf", required=true ) Integer diasenf
                , @RequestParam( value="sdMin", required=false ) String sdMin
                , @RequestParam( value="sdMed", required=false ) String sdMed
                , @RequestParam( value="sdMax", required=false ) String sdMax
                , @RequestParam( value="pamMin", required=false ) String pamMin
                , @RequestParam( value="pamMed", required=false ) String pamMed
                , @RequestParam( value="pamMax", required=false ) String pamMax
                , @RequestParam( value="fcMin", required=false ) Integer fcMin
                , @RequestParam( value="fcMed", required=false ) Integer fcMed
                , @RequestParam( value="fcProm", required=false ) Integer fcProm
                , @RequestParam( value="frMin", required=false ) Integer frMin
                , @RequestParam( value="frMax", required=false ) Integer frMax
                , @RequestParam( value="chkpositivo", required=false ) char chkpositivo
                ,@RequestParam(value = "numParametro", required = true) Integer numParametro
        ){
        try{
            DatosHemodinamica obj = new DatosHemodinamica();
            double areasc = getAreaSupCorp(peso,talla);
            obj.setAsc(Double.valueOf(round(areasc,2)));
            obj.setDireccion(direccion);
            obj.setEdad(edad);
            obj.setFecha(DateUtil.StringToDate(fconsulta,"dd/MM/yyyy"));
            double valorImc = getIMC(peso,talla);
            obj.setImc(Double.valueOf(round(valorImc,2)));
            obj.setMunicipio("Managua");
            obj.setnExpediente(nExpediente);
            obj.setPeso(peso);
            obj.setSector(sector);
            obj.setBarrioF(barrioF);
            obj.setSilais("Managua");
            obj.setTalla(talla);
            obj.setTelefono(telefono);
            obj.setuSalud("Sócrates Flores Vivas");
            Participante p = new Participante();
            p.setCodigo(idParticipante);
            obj.setParticipante(p);
            obj.setIMCdetallado("-");
            obj.setRecordUser(SecurityContextHolder.getContext().getAuthentication().getName());
            obj.setRecordDate(new Date());
            obj.setFie(DateUtil.StringToDate(fie, "dd/MM/yyyy"));
            if (validarFecha(fie) && validarFecha(fconsulta)) {
                int dias_enfermo = (int) calculateDays(DateUtil.StringToDate(fie, "dd/MM/yyyy"), DateUtil.StringToDate(fconsulta, "dd/MM/yyyy"));
                obj.setDiasenf(dias_enfermo);
            }
            obj.setSdMin(sdMin);
            obj.setSdMed(sdMed);
            obj.setSdMax(sdMax);
            obj.setPamMin(pamMin);
            obj.setPamMax(pamMax);
            obj.setPamMed(pamMed);
            obj.setFcMin(fcMin);
            obj.setFcMed(fcMed);
            obj.setFcProm(fcProm);
            obj.setFrMax(frMax);
            obj.setFrMin(frMin);
            obj.setPositivo(chkpositivo);
            obj.setNumParametros(numParametro);
            obj.setEstado('1');
            obj.setPasive('0');
            obj.setDeviceid("server");
            datoshemodinamicaService.SaveDatosHemo(obj);
            return createJsonResponse(obj) ;
        }
        catch (Exception e){
            Gson gson = new Gson();
            String json = gson.toJson(e.toString());
            return  new ResponseEntity<String>( json, HttpStatus.CREATED);
        }
    }

    public static boolean validarFecha(String fecha) {
        try {
            SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
            formatoFecha.setLenient(false);
            formatoFecha.parse(fecha);
        } catch (ParseException e) {
            return false;
        }
        return true;
    }

    public static long calculateDays(Date dateBeforeString1, Date dateAfterString2) {
        String f1 = DateUtil.DateToString(dateBeforeString1, "yyyy-MM-dd");
        String f2 = DateUtil.DateToString(dateAfterString2, "yyyy-MM-dd");
        LocalDate dateBefore = LocalDate.parse(f1);
        LocalDate dateAfter = LocalDate.parse(f2);
        long noOfDaysBetween = ChronoUnit.DAYS.between(dateBefore, dateAfter);
        return noOfDaysBetween + 1;
    }

    /* Modificar datos Hemodinamica */
    /*@RequestMapping(value = "UpdateHemodinamica/{idDatoHemo}", method = RequestMethod.PUT)
    @PathVariable(value="idDatoHemo") String idDatoHemo,
    * */
    @RequestMapping(value = "UpdateHemodinamica", method = RequestMethod.POST)
    public ResponseEntity<String>UpdateHemodinamica(@RequestParam (value="idDatoHemo") String idDatoHemo
            , @RequestParam( value="direccion", required=true ) String direccion
            , @RequestParam( value="edad", required=true ) String edad
            , @RequestParam( value="fecha", required=true ) String fecha
            , @RequestParam( value="fconsulta", required=true ) String fconsulta
            , @RequestParam( value="expediente", required=true ) String nExpediente
            , @RequestParam( value="peso", required=true ) String peso
            , @RequestParam( value="sector", required=true ) String sector
            , @RequestParam(value = "barrioF") String barrioF
            , @RequestParam( value="talla", required=true ) double talla
            , @RequestParam( value="telefono", required=true ) String telefono
            , @RequestParam( value="idParticipante" ) int idParticipante
            , @RequestParam( value="fie", required=true ) String fie
            , @RequestParam( value="diasenf", required=true ) Integer diasenf
            , @RequestParam( value="sdMin", required=false ) String sdMin
            , @RequestParam( value="sdMed", required=false ) String sdMed
            , @RequestParam( value="sdMax", required=false ) String sdMax
            , @RequestParam( value="pamMin", required=false ) String pamMin
            , @RequestParam( value="pamMed", required=false ) String pamMed
            , @RequestParam( value="pamMax", required=false ) String pamMax
            , @RequestParam( value="fcMin", required=false ) Integer fcMin
            , @RequestParam( value="fcMed", required=false ) Integer fcMed
            , @RequestParam( value="fcProm", required=false ) Integer fcProm
            , @RequestParam( value="frMin", required=false ) Integer frMin
            , @RequestParam( value="frMax", required=false ) Integer frMax
            , @RequestParam( value="chkpositivo", required=false ) char chkpositivo
            ,@RequestParam(value = "numParametro", required = true) Integer numParametro
    ){
        try{
            DatosHemodinamica obj = new DatosHemodinamica();
            obj.setIdDatoHemo(idDatoHemo);
            double areasc = getAreaSupCorp(peso,talla);
            obj.setAsc(Double.valueOf(round(areasc,2)));
            obj.setDireccion(direccion);
            obj.setEdad(edad);
            obj.setFecha(DateUtil.StringToDate(fconsulta, "dd/MM/yyyy"));
            double valorImc = getIMC(peso,talla);
            obj.setImc(Double.valueOf(round(valorImc,2)));
            obj.setMunicipio("Managua");
            obj.setnExpediente(nExpediente);
            obj.setPeso(peso);
            obj.setSector(sector);
            obj.setBarrioF(barrioF.toUpperCase().trim());
            obj.setSilais("Managua");
            obj.setTalla(talla);
            obj.setTelefono(telefono);
            obj.setuSalud("Sócrates Flores Vivas");
            Participante p = new Participante();
            p.setCodigo(idParticipante);
            obj.setParticipante(p);
            obj.setIMCdetallado("-");
            obj.setRecordUser(SecurityContextHolder.getContext().getAuthentication().getName());
            obj.setRecordDate(new Date());
            obj.setFie(DateUtil.StringToDate(fie,"dd/MM/yyyy"));
            if (validarFecha(fie) && validarFecha(fconsulta)) {
                int dias_enfermo = (int) calculateDays(DateUtil.StringToDate(fie, "dd/MM/yyyy"), DateUtil.StringToDate(fconsulta, "dd/MM/yyyy"));
                obj.setDiasenf(dias_enfermo);
            }
            obj.setSdMin(sdMin);
            obj.setSdMed(sdMed);
            obj.setSdMax(sdMax);
            obj.setPamMin(pamMin);
            obj.setPamMed(pamMed);
            obj.setPamMax(pamMax);
            obj.setFcMin(fcMin);
            obj.setFcMed(fcMed);
            obj.setFcProm(fcProm);
            obj.setFrMax(frMax);
            obj.setFrMin(frMin);
            obj.setPositivo(chkpositivo);
            obj.setNumParametros(numParametro);
            obj.setEstado('1');
            obj.setPasive('0');
            obj.setDeviceid("server");
            datoshemodinamicaService.SaveDatosHemo(obj);
            return createJsonResponse(obj) ;
        }catch (Exception e){
            Gson gson = new Gson();
            String json = gson.toJson(e.toString());
            return  new ResponseEntity<String>( json, HttpStatus.OK);
        }
    }

    public double getIMC(String peso, double altura){
        double alturaMetro = (altura)/100;
        double p = Double.parseDouble(peso);
        double cuadrado = (alturaMetro*alturaMetro);
        double imc = p / cuadrado;
        return imc;
    }
    public double getAreaSupCorp(String peso, double talla){
        double p = Double.parseDouble(peso);
        return  Math.sqrt((p * talla) / 3600);
    }

    public static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();
        BigDecimal bd = new BigDecimal(value);
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }

    // ** Vista para Editar un Carta **
    @RequestMapping(value = "/edithemo/{idDatoHemo}",method = RequestMethod.GET)
    public String edithemo(@PathVariable(value = "idDatoHemo") String idDatoHemo, Model model) {
        try {
            DatosHemodinamica obj = datoshemodinamicaService.getbyId(idDatoHemo);
            model.addAttribute("obj", obj);
            List<Barrio> barrios = datoshemodinamicaService.getBarrios();
            model.addAttribute("barrios",barrios);
            return "/hemodinamica/formedit";
        }catch (Exception e){
            return  "404";
        }
    }

    /* -* OBTIENE UN REGISTRO PARA EDITARLO EN FORMULARIO -* */
    @RequestMapping(value = "/editdetails/{idHemoDetalle}", method = RequestMethod.GET)
    public ModelAndView editDetalleHemo(@PathVariable(value = "idHemoDetalle") String idHemoDetalle)throws ParseException{
        ModelAndView modelView = new ModelAndView();
        try{
            HemoDetalle objDet = datoshemodinamicaService.getByHemoDetalleId(idHemoDetalle);
            modelView.addObject("objDet",objDet);
            List<MessageResource> nivelConciencia = messageResourceService.getCatalogo("NIVELCONCIENCIA");
            modelView.addObject("nivelConciencia", nivelConciencia);
            List<MessageResource> pulsoCalidad = messageResourceService.getCatalogo("PULSOCALIDAD");
            modelView.addObject("pulsoCalidad", pulsoCalidad);
            List<MessageResource> extremidades = messageResourceService.getCatalogo("EXTREMIDADES");
            modelView.addObject("extremidades", extremidades);
            List<MessageResource> clasificacion = messageResourceService.getCatalogo("CLASIFICACIONDENGUE");
            modelView.addObject("clasificacion", clasificacion);
            List<MessageResource> llenadoCapilar = messageResourceService.getCatalogo("LLENADOCAPILAR");
            modelView.addObject("llenadoCapilar", llenadoCapilar);
            List<MessageResource>obtenerMedicos = this.messageResourceService.getCatalogo("CAT_SELECCIONAR_PERSONAL_HEMODINAMICA");
            String[] personId = obtenerMedicos.get(0).getSpanish().split(",");
            HashSet<Integer> hset = new HashSet<Integer>();
            List<String> cargosId = Arrays.asList(personId);
            for (int i = 0; i < cargosId.size(); i++) {
                int value = Integer.parseInt( cargosId.get(i));
                hset.add(value);
            }
            List<Personal_Cargo> person = this.datoshemodinamicaService.getPersonal(hset);
            modelView.addObject("person", person);
            List<MessageResource> personaValida = messageResourceService.getCatalogo("PERSONAVALIDA");
            modelView.addObject("personaValida", personaValida);
            List<MessageResource> diuresis = messageResourceService.getCatalogo("DIURESIS");
            modelView.addObject("diuresis", diuresis);
            modelView.setViewName("/hemodinamica/formEditDetalle");
            return modelView;
        }
        catch (Exception e){
            Gson gson = new Gson();
            String json = gson.toJson(e.toString());
            return (modelView);
        }
    }
        /* ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++*/
    /* -- Guardar el Detalle de la hoja hemodinamica--  */
    @RequestMapping(value = "addHemoDetalle", method = RequestMethod.POST)
    public ResponseEntity<String>addHemoDetalle(@RequestParam( value="signo", required=true ) String signo
            ,@RequestParam( value="fecha", required=true ) String fecha
            ,@RequestParam( value="hora", required=true ) String hora
            ,@RequestParam( value="nivelConciencia", required=true ) String nivelConciencia
            ,@RequestParam( value="pa", required=true ) String pa
            ,@RequestParam( value="pd", required=true ) String pd
            ,@RequestParam( value="fc", required=true ) String fc
            ,@RequestParam( value="fr", required=true ) String fr
            ,@RequestParam( value="tc" ) String tc
            ,@RequestParam( value="sa" ) String sa
            ,@RequestParam( value="extremidades", required=true ) String extremidades
            ,@RequestParam( value="llenadoCapilar", required=true ) String llenadoCapilar
            ,@RequestParam( value="pulsoCalidad", required=true ) String pulsoCalidad
            ,@RequestParam( value="diuresis", required=true ) String diuresis
            ,@RequestParam( value="densidadUrinaria", required=true ) String densidadUrinaria
            ,@RequestParam( value="personaValida", required=true ) String personaValida
            ,@RequestParam( value="idDatoHemo", required=true) String idDatoHemo
            ,@RequestParam( value="impreso", required=false ) Boolean impreso
            ,@RequestParam( value="dx" ) String dx
    ){
        try{
            if (!datoshemodinamicaService.SiExisteHemo(idDatoHemo, DateUtil.StringToDate(fecha, "dd/MM/yyyy"), hora)) {
                HemoDetalle obj = new HemoDetalle();
                obj.setSigno(signo);
                obj.setDx(dx);
                obj.setFecha(DateUtil.StringToDate(fecha, "dd/MM/yyyy"));
                obj.setHora(hora);
                obj.setNivelConciencia(nivelConciencia);
                obj.setPa(pa);
                Integer psistolica = Integer.parseInt(pa);
                Integer pdiastolica = Integer.parseInt(pd);
                Integer diferencia = PAM(psistolica,pdiastolica);
                obj.setPp(""+diferencia);
                obj.setPd(pd);
                double aProm = ((pdiastolica * 2) + psistolica) / 3;
                obj.setPam(""+ Math.round(aProm));
                obj.setFc(fc);
                obj.setFr(fr);
                obj.setTc(tc);
                obj.setSa(sa);
                obj.setExtremidades(extremidades);
                obj.setLlenadoCapilar(llenadoCapilar);
                obj.setPulsoCalidad(pulsoCalidad);
                obj.setDiuresis(diuresis);
                obj.setDensidadUrinaria(densidadUrinaria);
                obj.setPersonaValida(personaValida);
                Character imp = '0';
                obj.setImpreso(imp);
                DatosHemodinamica o = datoshemodinamicaService.getbyId(idDatoHemo);
                obj.setDatoshemodinamica(o);
                obj.setEstado('1');
                obj.setPasive('0');
                obj.setDeviceid("server");
                obj.setRecordDate(new Date());
                obj.setRecordUser(SecurityContextHolder.getContext().getAuthentication().getName());
                datoshemodinamicaService.SaveDetalleHemo(obj);
                return createJsonResponse(obj);
            }else{
                Map<String, String> map = new HashMap<String, String>();
                map.put("msj", "El Detalle ya existe." );
                return createJsonResponse(map);
            }
        }
        catch (Exception e){
            Gson gson = new Gson();
            Map<String, String> map = new HashMap<String, String>();
            map.put("msj", e.toString() );
            return   createJsonResponse(map);
        }
    }
    /* --  fin -- */
    /* MODIFICAR EL DETALLE DE LA HEMODINAMICA*/
    @RequestMapping(value = "UpdateDetalleHemo", method = RequestMethod.POST)
    public ResponseEntity<String> UpdateDetalleHemo(@RequestParam( value="idHemoDetalle", required=true ) String idHemoDetalle
            ,@RequestParam( value="signo", required=true ) String signo
            ,@RequestParam( value="dx", required=false ) String dx
            ,@RequestParam( value="fecha", required=true ) String fecha
            ,@RequestParam( value="hora", required=true ) String hora
            ,@RequestParam( value="nivelConciencia", required=true ) String nivelConciencia
            ,@RequestParam( value="pa", required=true ) String pa
            ,@RequestParam( value="pd", required=true ) String pd
            ,@RequestParam( value="fc", required=true ) String fc
            ,@RequestParam( value="fr", required=true ) String fr
            ,@RequestParam( value="tc" ) String tc
            ,@RequestParam( value="sa" ) String sa
            ,@RequestParam( value="extremidades", required=true ) String extremidades
            ,@RequestParam( value="llenadoCapilar", required=true ) String llenadoCapilar
            ,@RequestParam( value="pulsoCalidad", required=true ) String pulsoCalidad
            ,@RequestParam( value="diuresis", required=true ) String diuresis
            ,@RequestParam( value="densidadUrinaria", required=true ) String densidadUrinaria
            ,@RequestParam( value="personaValida", required=true ) String personaValida
            ,@RequestParam( value="idDatoHemo", required=true) String idDatoHemo
            ,@RequestParam( value="impreso", required=false ) Boolean impreso

    ){
        try{
            HemoDetalle objDetalle = new HemoDetalle();
            objDetalle.setIdHemoDetalle(idHemoDetalle);
            objDetalle.setSigno(signo);
            objDetalle.setDx(dx);
            objDetalle.setFecha(DateUtil.StringToDate(fecha, "dd/MM/yyyy"));
            objDetalle.setHora(hora);
            objDetalle.setNivelConciencia(nivelConciencia);
            objDetalle.setPa(pa);
            Integer psistolica = Integer.parseInt(pa);
            Integer pdiastolica = Integer.parseInt(pd);
            Integer dif = PAM(psistolica,pdiastolica);
            objDetalle.setPp(""+dif);
            objDetalle.setPd(pd);
            double aProm = ((pdiastolica * 2) + psistolica) / 3;
            objDetalle.setPam(""+ Math.round(aProm));
            objDetalle.setFc(fc);
            objDetalle.setFr(fr);
            objDetalle.setTc(tc);
            objDetalle.setSa(sa);
            objDetalle.setExtremidades(extremidades);
            objDetalle.setLlenadoCapilar(llenadoCapilar);
            objDetalle.setPulsoCalidad(pulsoCalidad);
            objDetalle.setDiuresis(diuresis);
            objDetalle.setDensidadUrinaria(densidadUrinaria);
            objDetalle.setPersonaValida(personaValida);
            Character imp='0';
            objDetalle.setImpreso(imp);
            DatosHemodinamica o = datoshemodinamicaService.getbyId(idDatoHemo);
            objDetalle.setDatoshemodinamica(o);
            objDetalle.setEstado('1');
            objDetalle.setPasive('0');
            objDetalle.setDeviceid("server");
            objDetalle.setRecordDate(new Date());
            objDetalle.setRecordUser(SecurityContextHolder.getContext().getAuthentication().getName());
            datoshemodinamicaService.SaveDetalleHemo(objDetalle);
            return createJsonResponse(objDetalle) ;
        }
        catch (Exception e){
            Gson gson = new Gson();
            String json = gson.toJson(e.toString());
            return  new ResponseEntity<String>( json, HttpStatus.CREATED);
        }
    }

    public Integer PAM(Integer ps, Integer pd){
        Integer diferencia=0;
        if(ps > pd)
            diferencia = (ps - pd);
        else{
            diferencia = (pd - ps);
        }
        return diferencia;
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