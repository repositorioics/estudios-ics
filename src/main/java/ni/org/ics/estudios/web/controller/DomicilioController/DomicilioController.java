package ni.org.ics.estudios.web.controller.DomicilioController;

import com.google.gson.Gson;
import ni.org.ics.estudios.domain.DatosCoordenadas;
import ni.org.ics.estudios.domain.Participante;
import ni.org.ics.estudios.domain.catalogs.Personal;
import ni.org.ics.estudios.domain.cohortefamilia.casos.CasaCohorteFamiliaCaso;
import ni.org.ics.estudios.domain.muestreoanual.MovilInfo;
import ni.org.ics.estudios.dto.CoordenadasParticipanteDto;
import ni.org.ics.estudios.dto.DomicilioPdviDto;
import ni.org.ics.estudios.dto.ParticipantesCodigo;
import ni.org.ics.estudios.domain.catalogs.Barrio;
import ni.org.ics.estudios.domain.muestreoanual.ParticipanteProcesos;
import ni.org.ics.estudios.language.MessageResource;
import ni.org.ics.estudios.service.Domicilios.DomicilioService;
import ni.org.ics.estudios.service.MessageResourceService;
import ni.org.ics.estudios.service.hemodinanicaService.DatoshemodinamicaService;
import ni.org.ics.estudios.service.muestreoanual.ParticipanteProcesosService;
import ni.org.ics.estudios.web.utils.DateUtil;
import ni.org.ics.estudios.web.utils.JsonUtil;
import org.apache.commons.lang3.text.translate.UnicodeEscaper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.validation.constraints.Null;
import java.text.ParseException;
import java.util.*;


/**
 * Created by ICS_Inspiron3 on 16/07/2019.
 */
@Controller
@RequestMapping("/Domicilio")
public class DomicilioController {
    private static final Logger logger = LoggerFactory.getLogger(DomicilioController.class);

    /* Instancia de mi Servicio Hemodinamico */
    @Resource(name = "datoshemodinamicaService")
    private DatoshemodinamicaService datoshemodinamicaService;

    /* Instancia de mi Servicio Hemodinamico */
    @Resource(name = "messageResourceService")
    private MessageResourceService messageResourceService;

    /* Instancia de mi Servicio Hemodinamico */
    @Resource(name = "DomicilioService")
    private DomicilioService DomicilioService;
    @Resource(name = "participanteProcesosService")
    private ParticipanteProcesosService participanteProcesosService;


    /* Buscar Participante desde Form Domicilio */
    @RequestMapping(value = "/searchParticipant", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    String BuscarParticipanteByID(@RequestParam(value="parametro", required=true ) Integer parametro) throws Exception {
        try{
            Map<String, String> map = new HashMap<String, String>();
            Participante participante = datoshemodinamicaService.getParticipante(parametro);
            if (participante != null){
                ParticipanteProcesos procesos = participanteProcesosService.getParticipante(parametro);
                map.put("estado",procesos.getEstPart().toString());
                ParticipantesCodigo participantesCodigo = DomicilioService.getCodigos(parametro);
                if(participantesCodigo.getCasaFamilia() != null){
                    map.put("CFamilia", participantesCodigo.getCasaFamilia());
                }else {
                    map.put("CFamilia", "");
                }
                map.put("estudios", participantesCodigo.getEstudio());
            }
            if(participante.getCodigo().toString() != null){
                map.put("codigo", participante.getCodigo().toString());
            }
            else{
                map.put("codigo", "");
            }
            map.put("nombre", participante.getNombreCompleto().toString() != null ?  participante.getNombreCompleto().toString() : "");
            map.put("casaP", participante.getCasa().getCodigo().toString() != null ? participante.getCasa().getCodigo().toString() : "");
            map.put("barrio", participante.getCasa().getBarrio().getCodigo().toString() != null ? participante.getCasa().getBarrio().getCodigo().toString() : "");
            map.put("direccion", participante.getCasa().getDireccion().toString() != null ?  participante.getCasa().getDireccion().toString() : "");
            map.put("manzana", participante.getCasa().getManzana() != null ? participante.getCasa().getManzana(): "");
            String jsonResponse;
            jsonResponse = new Gson().toJson(map);
            //escapar caracteres especiales, escape de los caracteres con valor num�rico mayor a 127
            UnicodeEscaper escaper = UnicodeEscaper.above(127);
            return escaper.translate(jsonResponse);
        }catch (Exception e){
            throw e;
        }
    }

   @RequestMapping(value="/Listado", method = RequestMethod.GET)
    public String listadoCoordenada()throws Exception  {
        try{
            return "/CambioDomicilio/Coordenadas";
        }catch (Exception e){
            throw e;
        }
    }
    /* ESTE METODO ES PARA RETORNAR UNA LISTA DE LA TABLA PDVI_CAMBIO DE DOMICILIO  */
    @RequestMapping(value = "/Coordenadas", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody List<CoordenadasParticipanteDto> fetchobjCoordenadasToJson(@RequestParam(value = "parametro", required = true)Integer parametro)
            throws ParseException {
        List<CoordenadasParticipanteDto> coordenadas = null;
        try{
            coordenadas =  DomicilioService.CoordenadasParticipante(parametro);
            return coordenadas;
        }catch (Exception e){
            return coordenadas = null;
        }
    }


@RequestMapping(value = "/ListPdvi", method = RequestMethod.GET)
    public String ListPdvi()throws Exception{
        return "/CambioDomicilio/ListaCoordenadas";
    }

    /*ESTE METODO ES PARA RETORNAR UNA LISTA DE LA TABLA PDVI_CAMBIO DE DOMICILIO  */
    @RequestMapping(value = "/ListaPdvi", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody List<DomicilioPdviDto> fetchCoordenadasToJson(@RequestParam(value = "parametro2", required = true)Integer parametro2)
            throws ParseException {
        List<DomicilioPdviDto> coordenadas = null;
        logger.info("Obteniendo las cambios de domicilio en JSON");
        try{
            coordenadas =  DomicilioService.ListOfPDVI(parametro2);
        return coordenadas;
    }catch (Exception e){
           return coordenadas = null;
     }
    }


    @RequestMapping(value = "/Home")
    public ModelAndView Home() throws Exception {
        ModelAndView modelView = new ModelAndView();
        List<Barrio> barrios = datoshemodinamicaService.getBarrios();
        modelView.addObject("barrios", barrios);
        List<MessageResource> NoGeo = messageResourceService.getCatalogo("CP_CAT_NOGEO");
        modelView.addObject("NoGeo", NoGeo);
        List<Personal> person = DomicilioService.ListPersonal();
        modelView.addObject("person", person);
        modelView.setViewName("/CambioDomicilio/FormDomicilio");
        return modelView;
    }
    /* Actualizar datos datos_coordenadas 25/07/2019 */
    @RequestMapping(value="UpdateActual", method= RequestMethod.GET)
    public ResponseEntity<String>UpdateActual(@RequestParam(value = "codigo", required = true) String codigo)throws Exception{
        try{
            DomicilioService.UpdateActual(codigo);
            return createJsonResponse("Datos Actualizados") ;

        }catch (Exception e){
            Gson gson = new Gson();
            String json = gson.toJson(e.toString());
            return  new ResponseEntity<String>( json, HttpStatus.CREATED);
        }
    }


        /* Guardar datos datos_coordenadas 17/07/2019 - first save */
    @RequestMapping(value="SaveInfo", method= RequestMethod.POST)
    public ResponseEntity<String>SaveInfo(@RequestParam(value = "IdParticipante",required=true ) String IdParticipante
                                      ,@RequestParam(value = "codigoCasa", required=true) String codigoCasa
                                      ,@RequestParam(value = "casacohortefamilia", required=false) Integer  casacohortefamilia
                                      ,@RequestParam(value = "estudios", required = false) String estudios
                                      ,@RequestParam(value = "barrio", required = false) Integer barrio
                                      ,@RequestParam(value = "manzana", required = false) String manzana
                                      ,@RequestParam(value = "telefono", required = false) String telefono
                                      ,@RequestParam(value = "otroBarrio", required = false) String otroBarrio
                                      ,@RequestParam(value = "direccion", required = true) String direccion
                                      ,@RequestParam(value = "razonnogeoref", required = true) String razonnogeoref
                                      ,@RequestParam(value = "recurso1", required = true) Integer recurso1
                                      ,@RequestParam(value = "observacion", required = false) String observacion
                                      ,@RequestParam(value = "fecha_reportado", required = false) String fecha_reportado

    ){
        try{
            DomicilioService.allActualBaja(Integer.valueOf(IdParticipante));
            DatosCoordenadas obj = new DatosCoordenadas();
            UUID deviceUuid = new UUID(IdParticipante.hashCode(),new Date().hashCode());
            obj.setCodigo(deviceUuid.toString());
            Participante p = new Participante();
            p.setCodigo(Integer.valueOf(IdParticipante));
            obj.setParticipante(p);
            obj.setCodigoCasa(Integer.valueOf(codigoCasa));
            if(casacohortefamilia == null ){
                obj.setCasacohortefamilia(0);
            }else{

                obj.setCasacohortefamilia(casacohortefamilia);
            }

            Barrio b = new Barrio();
            b.setCodigo(Integer.valueOf(barrio));
            obj.setBarrio(b);
            obj.setManzana(manzana);
            obj.setTelefono(telefono);
            obj.setDireccion(direccion);
            obj.setOtroBarrio(otroBarrio);
            obj.setRazonNoGeoref(razonnogeoref);
            obj.setEstudios(estudios);
            Character imp = '1';
            obj.setActual(Character.valueOf(imp));
            obj.setRecurso1(Integer.valueOf(recurso1));
            obj.setConpunto("0");
            obj.setObservacion(observacion);
            obj.setFechaReportado(fecha_reportado);
            MovilInfo movil = new MovilInfo();
            movil.setIdInstancia(Integer.valueOf(148));
            movil.setInstancePath("Server");
            movil.setEstado("1");
            Boolean t = false;
            movil.setEliminado(t);
            movil.setUltimoCambio(String.valueOf(new Date()));
            movil.setStart(String.valueOf(new Date()));
            movil.setEnd(String.valueOf(new Date()));
            movil.setDeviceid("WEB");
            movil.setUsername(SecurityContextHolder.getContext().getAuthentication().getName());
            movil.setToday(new Date());
            movil.setRecurso1(recurso1);
            movil.setRecurso2(0);
            obj.setMovilInfo(movil);
            DomicilioService.SaveDomicilio(obj);
            return createJsonResponse(obj) ;
        }catch (Exception e){
            Gson gson = new Gson();
            String json = gson.toJson(e.toString());
            return  new ResponseEntity<String>( json, HttpStatus.CREATED);
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
