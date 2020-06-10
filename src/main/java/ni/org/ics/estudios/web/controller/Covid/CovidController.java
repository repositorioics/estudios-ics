package ni.org.ics.estudios.web.controller.Covid;

import com.google.gson.Gson;
import ni.org.ics.estudios.domain.Participante;
import ni.org.ics.estudios.domain.cohortefamilia.CasaCohorteFamilia;
import ni.org.ics.estudios.domain.cohortefamilia.ParticipanteCohorteFamilia;
import ni.org.ics.estudios.domain.cohortefamilia.casos.CasaCohorteFamiliaCaso;
import ni.org.ics.estudios.domain.cohortefamilia.casos.ParticipanteCohorteFamiliaCaso;
import ni.org.ics.estudios.domain.covid19.CasoCovid19;
import ni.org.ics.estudios.domain.covid19.ParticipanteCasoCovid19;
import ni.org.ics.estudios.domain.covid19.ParticipanteCovid19;
import ni.org.ics.estudios.domain.influenzauo1.ParticipanteCasoUO1;
import ni.org.ics.estudios.domain.muestreoanual.ParticipanteProcesos;
import ni.org.ics.estudios.language.MessageResource;
import ni.org.ics.estudios.service.MessageResourceService;
import ni.org.ics.estudios.service.ParticipanteService;
import ni.org.ics.estudios.service.cohortefamilia.CasaCohorteFamiliaService;
import ni.org.ics.estudios.service.cohortefamilia.ParticipanteCohorteFamiliaService;
import ni.org.ics.estudios.service.cohortefamilia.casos.ParticipanteCohorteFamiliaCasoService;
import ni.org.ics.estudios.service.covid.CovidService;
import ni.org.ics.estudios.service.muestreoanual.ParticipanteProcesosService;
import ni.org.ics.estudios.web.utils.DateUtil;
import ni.org.ics.estudios.web.utils.JsonUtil;
import ni.org.ics.estudios.web.utils.StringUtil;
import org.apache.commons.lang3.text.translate.UnicodeEscaper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.Resource;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

/**
 * Created by ICS on 01/06/2020.
 */

@Controller
@RequestMapping("/covid")
public class CovidController {
    private static final Logger logger = LoggerFactory.getLogger(CovidController.class);
    @Resource(name = "CovidService")
    private CovidService covidService;

    @Resource(name = "casaCohorteFamiliaService")
    private CasaCohorteFamiliaService casaCohorteFamiliaService;


    @Resource(name = "participanteService")
    private ParticipanteService participanteService;

    @Resource(name = "participanteProcesosService")
    private ParticipanteProcesosService participanteProcesosService;

    @Resource(name = "participanteCohorteFamiliaCasoService")
    private ParticipanteCohorteFamiliaCasoService participanteCohorteFamiliaCasoService;

    @Resource(name = "messageResourceService")
    private MessageResourceService messageResourceService;

    @Resource(name = "CovidService")
    private  CovidService CovidService;

    @Resource(name = "participanteCohorteFamiliaService")
    private ParticipanteCohorteFamiliaService participanteCohorteFamiliaService;


    /* Buscar Listado Covid Participante  getParticipantesCohorteFamiliaCasoByCodigoCaso */
    @RequestMapping(value = "/listCovid", method = RequestMethod.GET)
    public String FormCovid(Model model)throws Exception{
        List<ParticipanteCasoCovid19> casosCovid = covidService.getParticipanteCasosPositivosCovid();
        model.addAttribute("casosCovid",casosCovid);
        return "/casosCovid/list";
    }
    @RequestMapping(value = "SaveForm", method = RequestMethod.GET)
    public String SaveForm(Model model) throws Exception
    {
        try{
            List<MessageResource> positivoPor = messageResourceService.getCatalogo("UO1_CAT_POSITIVO_POR");
            model.addAttribute("positivoPor", positivoPor);
            return "/casosCovid/saveForm";
        }
        catch (Exception e){
            throw e;
        }
    }

    @RequestMapping(value = "searchParticipant", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    ResponseEntity<String> buscarParticipante(@RequestParam(value="participantCode", required=true ) Integer codigo) throws ParseException {
        ParticipanteCovid19 participantecovid = covidService.getParticipanteCovidById(codigo);
        if (participantecovid == null){
            Participante participante = participanteService.getParticipanteByCodigo(codigo);
            if (participante!=null){
                ParticipanteProcesos procesos = participanteProcesosService.getParticipante(codigo);
                if (procesos!=null && procesos.getEstPart().equals(0))
                    return JsonUtil.createJsonResponse("Participante retirado");
                else return JsonUtil.createJsonResponse("Participante aún no ha sido enrolado");
            }
            return JsonUtil.createJsonResponse("No se encontró participante según el código ingresado");
        }else{
            ParticipanteCasoCovid19 participanteCaso = covidService.getParticipanteCasoCovid19Pos(codigo);
            if (participanteCaso!=null)
                return JsonUtil.createJsonResponse("Participante ya fue registrado como positivo en la casa: "+participanteCaso.getCodigoCaso().getCasa().getCodigoCHF());
            else {
                ParticipanteProcesos procesos = participanteProcesosService.getParticipante(codigo);
                if (procesos!=null && procesos.getEstPart().equals(0))
                    return JsonUtil.createJsonResponse("Participante retirado");
            }
        }
        return JsonUtil.createJsonResponse(participantecovid);
    }

    @RequestMapping( value="saveCaseCovid", method=RequestMethod.POST)
    public ResponseEntity<String> saveCaseCovid( @RequestParam(value="codigo", required=false, defaultValue="" ) String codigo
            , @RequestParam( value="codigoCasa", required=true ) String codigoCasa
            , @RequestParam( value="fechaInicio", required=false ) String fechaInicio
            , @RequestParam( value="codigoParticipante", required=false, defaultValue="" ) Integer codigoParticipante
            , @RequestParam( value="positivoPor", required=true, defaultValue="" ) String positivoPor
            , @RequestParam( value="fif", required=false, defaultValue="" ) String fif
            , @RequestParam( value="fis", required=true, defaultValue="" ) String fis
    )throws Exception{
        try{
            CasoCovid19 covid_casos = new CasoCovid19();
            covid_casos.setCodigoCaso(StringUtil.getCadenaAlfanumAleatoria(36,true));
            covid_casos.setDeviceid("server");
            covid_casos.setEstado('1');
            covid_casos.setPasive('0');
            covid_casos.setRecordDate(new Date());
            covid_casos.setRecordUser(SecurityContextHolder.getContext().getAuthentication().getName());
            covid_casos.setFechaInactivo(null);
            covid_casos.setFechaIngreso(DateUtil.StringToDate(fechaInicio,"dd/MM/yyyy"));
            covid_casos.setInactivo("0");
            CasaCohorteFamilia casaChFam = new CasaCohorteFamilia();
            casaChFam.setCodigoCHF(codigoCasa);
            covid_casos.setCasa(casaChFam);
            this.covidService.saveOrUpdateCasoCovid19(covid_casos); // Aqui mando a guardar en la 1er tabla

            ParticipanteCasoCovid19 participanteCovid = new ParticipanteCasoCovid19();
            participanteCovid.setCodigoCasoParticipante(StringUtil.getCadenaAlfanumAleatoria(36,true));
            participanteCovid.setDeviceid("server");
            participanteCovid.setEstado('1');
            participanteCovid.setPasive('0');
            participanteCovid.setRecordDate(new Date());
            participanteCovid.setRecordUser(SecurityContextHolder.getContext().getAuthentication().getName());
            participanteCovid.setConsentimiento("S");
            participanteCovid.setEnfermo("S");
            participanteCovid.setFis(DateUtil.StringToDate(fis,"dd/MM/yyyy"));
            participanteCovid.setFif(DateUtil.StringToDate(fif,"dd/MM/yyyy"));
            participanteCovid.setPositivoPor(positivoPor);
            participanteCovid.setCodigoCaso(covid_casos);
            Participante participante = covidService.getParticipanteByCodigo(codigoParticipante);
            participanteCovid.setParticipante(participante);
            this.covidService.saveOrUpdateParticipanteCasoCovid19(participanteCovid);

            return JsonUtil.createJsonResponse(covid_casos);
        }catch (Exception e){
            Gson gson = new Gson();
            String json = gson.toJson(e.toString());
            return new ResponseEntity<String>( json, HttpStatus.CREATED);
        }
    }

    private ResponseEntity<String> JsonUtilcreateJsonResponse( Object o )
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
