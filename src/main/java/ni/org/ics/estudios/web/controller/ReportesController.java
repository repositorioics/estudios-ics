package ni.org.ics.estudios.web.controller;

import ni.org.ics.estudios.domain.Bhc.Bhc_Detalle_envio;
import ni.org.ics.estudios.domain.Pbmc.Pbmc_Detalle_Envio;
import ni.org.ics.estudios.domain.Retiros.Retiros;
import ni.org.ics.estudios.domain.SerologiaOct2020.SerologiaEnvio;
import ni.org.ics.estudios.domain.SerologiaOct2020.Serologia_Detalle_Envio;
import ni.org.ics.estudios.domain.catalogs.Estudio;
import ni.org.ics.estudios.domain.catalogs.Personal;
import ni.org.ics.estudios.domain.catalogs.Razones_Retiro;
import ni.org.ics.estudios.domain.cohortefamilia.Muestra;
import ni.org.ics.estudios.domain.entomologia.CuestionarioHogar;
import ni.org.ics.estudios.domain.entomologia.CuestionarioHogarPoblacion;
import ni.org.ics.estudios.domain.hemodinamica.DatosHemodinamica;
import ni.org.ics.estudios.domain.hemodinamica.HemoDetalle;
import ni.org.ics.estudios.domain.muestreoanual.*;
import ni.org.ics.estudios.domain.scancarta.DetalleParte;
import ni.org.ics.estudios.domain.scancarta.ParticipanteCarta;
import ni.org.ics.estudios.domain.scancarta.ParticipanteExtension;
import ni.org.ics.estudios.dto.BhcEnvioDto;
import ni.org.ics.estudios.dto.ComparacionMuestrasDto;
import ni.org.ics.estudios.dto.Hemodinamica.HemodinamicaDto;
import ni.org.ics.estudios.dto.cartas.*;
import ni.org.ics.estudios.dto.muestras.MuestraDto;
import ni.org.ics.estudios.dto.muestras.RecepcionBHCDto;
import ni.org.ics.estudios.language.MessageResource;
import ni.org.ics.estudios.service.Bhc.BhcService;
import ni.org.ics.estudios.service.EstudioService;
import ni.org.ics.estudios.service.MessageResourceService;
import ni.org.ics.estudios.service.Pbmc.PbmcService;
import ni.org.ics.estudios.service.SerologiaOct2020.SerologiaOct2020Service;
import ni.org.ics.estudios.service.cohortefamilia.ReportesService;
import ni.org.ics.estudios.service.comparacion.ComparasionService;
import ni.org.ics.estudios.service.entomologia.CuestionarioHogarService;
import ni.org.ics.estudios.service.hemodinanicaService.DatoshemodinamicaService;
import ni.org.ics.estudios.service.muestreoanual.*;
import ni.org.ics.estudios.service.reportes.ReportesPdfService;
import ni.org.ics.estudios.service.retiro.RetiroService;
import ni.org.ics.estudios.service.scancarta.ScanCartaService;
import ni.org.ics.estudios.web.utils.DateUtil;
import ni.org.ics.estudios.web.utils.pdf.Constants;
import ni.org.ics.estudios.web.utils.pdf.DatosGeneralesParticipante;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Miguel Salinas on 9/8/2017.
 * V1.0
 */
@Controller
@RequestMapping("/reportes/*")
public class ReportesController {

    private static final Logger logger = LoggerFactory.getLogger(ChfCasosController.class);
    @Resource(name = "reportesService")
    private ReportesService reportesService;
    @Resource(name = "messageResourceService")
    private MessageResourceService messageResourceService;
    @Resource(name = "reportesPdfService")
    private ReportesPdfService reportesPdfService;
    @Resource(name = "estudioService")
    private EstudioService estudioService;

    @Resource(name = "participanteProcesosService")
    private ParticipanteProcesosService participanteProcesosService;

    /* Instancia de mi Servicio Hemodinamico */
    @Resource(name = "datoshemodinamicaService")
    private DatoshemodinamicaService datoshemodinamicaService;

    /* Instancia de mi Servicio ScanCarta */
    @Resource(name = "scanCartaService")
    private ScanCartaService scanCartaService;

    @Resource(name = "SerologiaService")
    private SerologiaOct2020Service serologiaservice;

    @Resource(name = "PbmcService")
    private PbmcService pbmcService;

    @Resource(name = "BhcService")
    private BhcService bhcService;

    @Resource(name="bhcService")
    private RecepcionBHCService recepcionBHCService;
    @Resource(name="seroService")
    private RecepcionSeroService seroService;
    @Resource(name="muestraMAService")
    private MuestraService muestraService;
    @Resource(name="labBhcService")
    private LabBHCService labBhcService;
    @Resource(name="labSeroService")
    private LabSeroService labSeroService;
    @Resource(name="labPbmcService")
    private LabPbmcService labPbmcService;

    /* Instancia de mi Servicio Retiro */
    @Resource(name = "RetiroService")
    private RetiroService retiroService;
    @Resource(name = "cuestionarioHogarService")
    private CuestionarioHogarService cuestionarioHogarService;

    @RequestMapping(value = "/super/visitas", method = RequestMethod.GET)
    public String obtenerVisitas(Model model) throws ParseException {
        logger.debug("Mostrando casos monitoreo intensivo en JSP");
        List<Object[]> visitas = null; //reportesService.getHistorialVisitas(null, null, null, null);
        model.addAttribute("visitas", visitas);
        return "/supervisor/visitas/list";
    }

    /**
     * Retorna una lista de casas. Acepta una solicitud GET para JSON
     * @return Un arreglo JSON de racks
     * @throws ParseException
     */
    @RequestMapping(value = "/super/getVisitas", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    List<Object[]> fetchCasaCohorteFamiliaCasosJson(@RequestParam(value = "codigoCasa", required = false) String casa,
                                                    @RequestParam(value = "codigoParticipante", required = false) Integer codigoParticipante,
                                                    @RequestParam(value = "fechaInicio", required = false) String fechaInicio,
                                                    @RequestParam(value = "fechaFin", required = false) String fechaFin) throws ParseException {
        logger.info("Obteniendo las casas en JSON");
        List<Object[]> casas = null;
        casas = reportesService.getHistorialVisitas(casa, codigoParticipante, DateUtil.StringToDate(fechaInicio, "dd/MM/yyyy"), DateUtil.StringToDate(fechaFin + " 23:59:59", "dd/MM/yyyy HH:mm:ss"));
        return casas;
    }

    @RequestMapping(value = "/pdf/fileData", method = RequestMethod.GET)
    public String fileDataReportForm(Model model) throws ParseException {
        logger.debug("Mostrando formulario para generar datos generales para agregar al expediente");
        List<Estudio> estudios = estudioService.getEstudios();
        model.addAttribute("estudios", estudios);
        return "/reportes/fileData";
    }


    @RequestMapping(value = "/downloadFileDataReport", method = RequestMethod.GET)
    public ModelAndView downloadFilaDataReport(@RequestParam(value="estudio", required=false) int estudio,
                                               @RequestParam(value="fechaInicio", required=false ) String fechaInicio,
                                               @RequestParam(value="fechaFin", required=false ) String fechaFin,
                                               @RequestParam(value="codigoParticipante", required=false) Integer codigoParticipante
                                               ) throws Exception{
        ModelAndView excelView = new ModelAndView("pdfView");
        Date dFechaInicio = null;
        if (fechaInicio!=null && !fechaInicio.isEmpty())
            dFechaInicio = DateUtil.StringToDate(fechaInicio, "dd/MM/yyyy");
        Date dFechaFin = null;
        if (fechaFin!=null && !fechaFin.isEmpty())
            dFechaFin = DateUtil.StringToDate(fechaFin+ " 23:59:59", "dd/MM/yyyy HH:mm:ss");

        List<DatosGeneralesParticipante> datosParticipante = reportesPdfService.getDatosGeneralesParticipante(estudio, codigoParticipante, dFechaInicio, dFechaFin);
        List<MessageResource> messageReports = messageResourceService.loadAllMessagesNoCatalogs();
        excelView.addObject("labels", messageReports);
        excelView.addObject("datos", datosParticipante);
        excelView.addObject("TipoReporte", Constants.TPR_DATOSGENERALES);
        return excelView;
    }

//region Reporte Serologia
    @RequestMapping(value = "/downloadFileEnviosSerologia", method = RequestMethod.GET)
    public ModelAndView downloadFileEnviosSerologia(@RequestParam(value="nEnvios", required=false ) Integer nEnvios,
            @RequestParam(value="fechaInicio", required=false ) String fechaInicio,
            @RequestParam(value="fechaFin", required=false ) String fechaFin,
            @RequestParam(value="lugarEnvio", required=false ) Integer lugarEnvio)
        throws Exception{
        ModelAndView ReporteEnvio = new ModelAndView("pdfView");
        Date dFechaInicio = null;
        if (fechaInicio!=null && !fechaInicio.isEmpty())
            dFechaInicio = DateUtil.StringToDate(fechaInicio, "dd/MM/yyyy");
        Date dFechaFin = null;
        if (fechaFin!=null && !fechaFin.isEmpty())
            dFechaFin = DateUtil.StringToDate(fechaFin+ " 23:59:59", "dd/MM/yyyy HH:mm:ss");

        List<SerologiaEnvio> SerologiasEnviadas =  this.serologiaservice.getSerologiaEnvioByDates(nEnvios,dFechaInicio,dFechaFin, lugarEnvio);
        ReporteEnvio.addObject("nEnvios",nEnvios);
        ReporteEnvio.addObject("fechaInicio",fechaInicio);
        ReporteEnvio.addObject("fechaFin",fechaFin);
        ReporteEnvio.addObject("SerologiasEnviadas",SerologiasEnviadas);
        ReporteEnvio.addObject("lugarEnvio",lugarEnvio);
        List<MessageResource> lugares = messageResourceService.getCatalogo("CAT_ENVIAR_MUETRAS");
        ReporteEnvio.addObject("lugares",lugares);
        List<Serologia_Detalle_Envio> allSerologia = this.serologiaservice.getAllSerologia(nEnvios,dFechaInicio,dFechaFin, lugarEnvio);
        ReporteEnvio.addObject("allSerologia",allSerologia);
        ReporteEnvio.addObject("TipoReporte", Constants.TPR_ENVIOREPORTE);
        return ReporteEnvio;
    }


    @RequestMapping(value = "downloadFileSerologiaExcel", method = RequestMethod.GET)
    public ModelAndView SerologiaExcel(@RequestParam(value="nEnvios", required=false ) Integer nEnvios,
                                       @RequestParam(value="fechaInicio", required=false ) String fechaInicio,
                                       @RequestParam(value="fechaFin", required=false ) String fechaFin,
                                       @RequestParam(value="lugarEnvio", required=false ) Integer lugarEnvio
                                       )
            throws Exception {
        ModelAndView ReporteEnvio = new ModelAndView("excelView");
        Date dFechaInicio = null;
        if (fechaInicio != null && !fechaInicio.isEmpty())
            dFechaInicio = DateUtil.StringToDate(fechaInicio, "dd/MM/yyyy");
        Date dFechaFin = null;
        if (fechaFin != null && !fechaFin.isEmpty())
            dFechaFin = DateUtil.StringToDate(fechaFin + " 23:59:59", "dd/MM/yyyy HH:mm:ss");
        List<SerologiaEnvio> SerologiasEnviadas = this.serologiaservice.getSerologiaEnvioByDates(nEnvios, dFechaInicio, dFechaFin, lugarEnvio);
        ReporteEnvio.addObject("nEnvios", nEnvios);
        List<MessageResource> sitios = messageResourceService.getCatalogo("CAT_SITIOS_ENVIO_SEROLOGIA");
        ReporteEnvio.addObject("sitios", sitios);
        ReporteEnvio.addObject("fechaInicio", fechaInicio);
        ReporteEnvio.addObject("fechaFin", fechaFin);
        ReporteEnvio.addObject("SerologiasEnviadas", SerologiasEnviadas);
        List<Serologia_Detalle_Envio> allSerologia = this.serologiaservice.getAllSerologia(nEnvios, dFechaInicio, dFechaFin, lugarEnvio);
        ReporteEnvio.addObject("allSerologia", allSerologia);
        ReporteEnvio.addObject("TipoReporte", Constants.TPR_ENVIOREPORTE);
        return ReporteEnvio;
    }

//endregion

    //todo generar reporte PBMC PDF
    @RequestMapping(value = "/EnvioPbmcPdf", method = RequestMethod.GET)
    public ModelAndView EnvioPbmcPdf(@RequestParam(value="nEnvios", required=false ) Integer nEnvios,
                                                    @RequestParam(value="fechaInicio", required=false ) String fechaInicio,
                                                    @RequestParam(value="fechaFin", required=false ) String fechaFin,
                                                    @RequestParam(value="numero_envio", required=false ) Integer numero_envio,
                                                    @RequestParam(value="lugarEnvio", required=false ) Integer lugarEnvio

    )
            throws Exception{
        ModelAndView ReporteEnvioPbmcPdf = new ModelAndView("pdfView");
        Date dFechaInicio = null;
        if (fechaInicio!=null && !fechaInicio.isEmpty())
            dFechaInicio = DateUtil.StringToDate(fechaInicio, "dd/MM/yyyy");
        Date dFechaFin = null;
        if (fechaFin!=null && !fechaFin.isEmpty())
            dFechaFin = DateUtil.StringToDate(fechaFin+ " 23:59:59", "dd/MM/yyyy HH:mm:ss");


        List<SerologiaEnvio> EnvioPbmc =  this.serologiaservice.getSerologiaEnvioByDates(nEnvios,dFechaInicio,dFechaFin, numero_envio);
        ReporteEnvioPbmcPdf.addObject("nEnvios",nEnvios);
        ReporteEnvioPbmcPdf.addObject("fechaInicio",fechaInicio);
        ReporteEnvioPbmcPdf.addObject("fechaFin",fechaFin);
        ReporteEnvioPbmcPdf.addObject("EnvioPbmc",EnvioPbmc);
        ReporteEnvioPbmcPdf.addObject("lugarEnvio",lugarEnvio);
        List<Pbmc_Detalle_Envio> allPbmc = this.pbmcService.getAllPbmc(nEnvios,dFechaInicio,dFechaFin,lugarEnvio);
        ReporteEnvioPbmcPdf.addObject("allPbmc",allPbmc);
        List<MessageResource> lugares = messageResourceService.getCatalogo("CAT_ENVIAR_MUETRAS");
        ReporteEnvioPbmcPdf.addObject("lugares",lugares);
        ReporteEnvioPbmcPdf.addObject("TipoReporte", Constants.TPR_ENVIOREPORTEPBCM);
        return ReporteEnvioPbmcPdf;
    }

        //todo envio Excel solo Pbmc
    @RequestMapping(value = "/EnvioPbmcToExcel", method = RequestMethod.GET)
    public ModelAndView EnvioPbmcToExcel(@RequestParam(value="nEnvios", required=false ) Integer nEnvios,
                                         @RequestParam(value="fechaInicio", required=false ) String fechaInicio,
                                         @RequestParam(value="fechaFin", required=false ) String fechaFin,
                                         @RequestParam(value="lugarEnvio", required=false ) Integer lugarEnvio)
            throws Exception{
        ModelAndView ReporteEnvioPbmcPdf = new ModelAndView("excelView");
        Date dFechaInicio = null;
        if (fechaInicio!=null && !fechaInicio.isEmpty())
            dFechaInicio = DateUtil.StringToDate(fechaInicio, "dd/MM/yyyy");
        Date dFechaFin = null;
        if (fechaFin!=null && !fechaFin.isEmpty())
            dFechaFin = DateUtil.StringToDate(fechaFin+ " 23:59:59", "dd/MM/yyyy HH:mm:ss");



        ReporteEnvioPbmcPdf.addObject("fechaInicio",fechaInicio);
        ReporteEnvioPbmcPdf.addObject("fechaFin",fechaFin);
        ReporteEnvioPbmcPdf.addObject("lugarEnvio",lugarEnvio);

        List<Pbmc_Detalle_Envio> allPbmc = this.pbmcService.getAllPbmc(nEnvios,dFechaInicio,dFechaFin,lugarEnvio);
        ReporteEnvioPbmcPdf.addObject("allPbmc",allPbmc);
        /*List<PbmcHorasToma> horasPbmc = this.pbmcService.getHorasPbmc(nEnvios,dFechaInicio,dFechaFin);
        ReporteEnvioPbmcPdf.addObject("horasPbmc",horasPbmc);
        */ReporteEnvioPbmcPdf.addObject("TipoReporte", Constants.TPR_ENVIOREPORTEPBCMTOEXCEL);
        return ReporteEnvioPbmcPdf;
    }

    //todo envio Excel solo Pbmc PDF
    @RequestMapping(value = "/EnvioSerologiaConPbmcPdf", method = RequestMethod.GET)
    public ModelAndView EnvioSerologiaConPbmcPdf(@RequestParam(value="nEnvios", required=false ) Integer nEnvios,
                                         @RequestParam(value="fechaInicio", required=false ) String fechaInicio,
                                         @RequestParam(value="fechaFin", required=false ) String fechaFin,
                                         @RequestParam(value="lugarEnvio", required=false ) Integer lugarEnvio)
            throws Exception{
        ModelAndView ReporteEnvioPbmcPdf = new ModelAndView("pdfView");
        Date dFechaInicio = null;
        if (fechaInicio!=null && !fechaInicio.isEmpty())
            dFechaInicio = DateUtil.StringToDate(fechaInicio, "dd/MM/yyyy");
        Date dFechaFin = null;
        if (fechaFin!=null && !fechaFin.isEmpty())
            dFechaFin = DateUtil.StringToDate(fechaFin+ " 23:59:59", "dd/MM/yyyy HH:mm:ss");

        ReporteEnvioPbmcPdf.addObject("fechaInicio",fechaInicio);
        ReporteEnvioPbmcPdf.addObject("fechaFin",fechaFin);
        ReporteEnvioPbmcPdf.addObject("lugarEnvio",lugarEnvio);

        List<Pbmc_Detalle_Envio> allPbmc = this.pbmcService.getAllPbmc(nEnvios,dFechaInicio,dFechaFin,lugarEnvio);
        ReporteEnvioPbmcPdf.addObject("allPbmc",allPbmc);

        ReporteEnvioPbmcPdf.addObject("TipoReporte", Constants.TPR_ENVIOREPORTEPBCMTOEXCEL);
        return ReporteEnvioPbmcPdf;
    }





    //todo: Excel PBMC con serologia
    @RequestMapping(value = "/EnvioSeroPbmcExcel", method = RequestMethod.GET)
    public ModelAndView EnvioSeroPbmcExcel(@RequestParam(value="nEnvios", required=false ) Integer nEnvios,
                                     @RequestParam(value="fechaInicio", required=false ) String fechaInicio,
                                     @RequestParam(value="fechaFin", required=false ) String fechaFin,
                                     @RequestParam(value="lugarEnvio", required=false ) Integer lugarEnvio)
            throws Exception{
        ModelAndView ReporteEnvioPbmcPdf = new ModelAndView("excelView");
        Date dFechaInicio = null;
        if (fechaInicio!=null && !fechaInicio.isEmpty())
            dFechaInicio = DateUtil.StringToDate(fechaInicio, "dd/MM/yyyy");
        Date dFechaFin = null;
        if (fechaFin!=null && !fechaFin.isEmpty())
            dFechaFin = DateUtil.StringToDate(fechaFin+ " 23:59:59", "dd/MM/yyyy HH:mm:ss");


        ReporteEnvioPbmcPdf.addObject("fechaInicio",fechaInicio);
        ReporteEnvioPbmcPdf.addObject("fechaFin",fechaFin);
        ReporteEnvioPbmcPdf.addObject("lugarEnvio",lugarEnvio);
        List<Serologia_Detalle_Envio> SerologiaWithPbmc = this.serologiaservice.getSerologiaByPbmc(nEnvios,dFechaInicio,dFechaFin,lugarEnvio);
        ReporteEnvioPbmcPdf.addObject("SerologiaWithPbmc",SerologiaWithPbmc);
        ReporteEnvioPbmcPdf.addObject("TipoReporte", Constants.TPR_ENVIOREPORTEPBCM);
        return ReporteEnvioPbmcPdf;
    }

    //todo: PDF PBMC con serologia ******
    @RequestMapping(value = "/EnvioSeroPbmcPdf", method = RequestMethod.GET)
    public ModelAndView EnvioSeroPbmcPdf(@RequestParam(value="nEnvios", required=false ) Integer nEnvios,
                                           @RequestParam(value="fechaInicio", required=false ) String fechaInicio,
                                           @RequestParam(value="fechaFin", required=false ) String fechaFin,
                                           @RequestParam(value="lugarEnvio", required=false ) Integer lugarEnvio)
            throws Exception{
        ModelAndView ReporteEnvioSeroPbmcPdf = new ModelAndView("pdfView");
        Date dFechaInicio = null;
        if (fechaInicio!=null && !fechaInicio.isEmpty())
            dFechaInicio = DateUtil.StringToDate(fechaInicio, "dd/MM/yyyy");
        Date dFechaFin = null;
        if (fechaFin!=null && !fechaFin.isEmpty())
            dFechaFin = DateUtil.StringToDate(fechaFin+ " 23:59:59", "dd/MM/yyyy HH:mm:ss");

        ReporteEnvioSeroPbmcPdf.addObject("fechaInicio",fechaInicio);
        ReporteEnvioSeroPbmcPdf.addObject("fechaFin",fechaFin);
        ReporteEnvioSeroPbmcPdf.addObject("lugarEnvio",lugarEnvio);
        List<Serologia_Detalle_Envio> SerologiaWithPbmc = this.serologiaservice.getSerologiaByPbmc(nEnvios,dFechaInicio,dFechaFin, lugarEnvio);
        ReporteEnvioSeroPbmcPdf.addObject("SerologiaWithPbmc",SerologiaWithPbmc);
        List<MessageResource> lugares = messageResourceService.getCatalogo("CAT_ENVIAR_MUETRAS");
        ReporteEnvioSeroPbmcPdf.addObject("lugares",lugares);
        ReporteEnvioSeroPbmcPdf.addObject("TipoReporte", Constants.TPR_ENVIOREPORTEPBCMTOEXCEL);
        return ReporteEnvioSeroPbmcPdf;
    }
    //fin reporte PBMC

    //region todo Reporte BHC
    @RequestMapping(value = "/EnvioBhcPdf", method = RequestMethod.GET)
    public ModelAndView EnvioBhc(@RequestParam(value="nEnvios", required=false ) Integer nEnvios,
                                                    @RequestParam(value="fechaInicio", required=false ) String fechaInicio,
                                                    @RequestParam(value="fechaFin", required=false ) String fechaFin,
                                                    @RequestParam(value="lugarEnvio", required=false ) Integer lugarEnvio)
            throws Exception{
        ModelAndView ReporteEnvio = new ModelAndView("pdfView");
        Date dFechaInicio = null;
        if (fechaInicio!=null && !fechaInicio.isEmpty())
            dFechaInicio = DateUtil.StringToDate(fechaInicio, "dd/MM/yyyy");
        Date dFechaFin = null;
        if (fechaFin!=null && !fechaFin.isEmpty())
            dFechaFin = DateUtil.StringToDate(fechaFin+ " 23:59:59", "dd/MM/yyyy HH:mm:ss");


        ReporteEnvio.addObject("fechaInicio",fechaInicio);
        ReporteEnvio.addObject("fechaFin",fechaFin);
        ReporteEnvio.addObject("lugarEnvio",lugarEnvio);
        List<MessageResource> lugares = messageResourceService.getCatalogo("CAT_ENVIAR_MUETRAS");
        ReporteEnvio.addObject("lugares",lugares);
        List<Bhc_Detalle_envio> allBhc = this.bhcService.getBhcDetailsEnvio(nEnvios,dFechaInicio,dFechaFin, lugarEnvio);
        ReporteEnvio.addObject("allBhc",allBhc);
        ReporteEnvio.addObject("TipoReporte", Constants.TPR_ENVIOREPORTEBHC);
        return ReporteEnvio;
    }

    //todo: Reporte Bhc Excel
    @RequestMapping(value = "/EnvioBhcExcel", method = RequestMethod.GET)
    public ModelAndView EnvioBhcExcel(@RequestParam(value="nEnvios", required=false ) Integer nEnvios,
                                 @RequestParam(value="fechaInicio", required=false ) String fechaInicio,
                                 @RequestParam(value="fechaFin", required=false ) String fechaFin,
                                 @RequestParam(value="lugarEnvio", required=false ) Integer lugarEnvio)
            throws Exception{
        ModelAndView ReporteEnvio = new ModelAndView("excelView");
        Date dFechaInicio = null;
        if (fechaInicio!=null && !fechaInicio.isEmpty())
            dFechaInicio = DateUtil.StringToDate(fechaInicio, "dd/MM/yyyy");
        Date dFechaFin = null;
        if (fechaFin!=null && !fechaFin.isEmpty())
            dFechaFin = DateUtil.StringToDate(fechaFin+ " 23:59:59", "dd/MM/yyyy HH:mm:ss");


        ReporteEnvio.addObject("fechaInicio",fechaInicio);
        ReporteEnvio.addObject("fechaFin",fechaFin);
        //List<Bhc_Detalle_envio> allBhc = this.bhcService.getBhcDetailsEnvio(nEnvios,dFechaInicio,dFechaFin);
        List<BhcEnvioDto> allBhc = this.bhcService.getReporteEnvioBhc(nEnvios, dFechaInicio, dFechaFin, lugarEnvio);
        ReporteEnvio.addObject("allBhc",allBhc);
        ReporteEnvio.addObject("TipoReporte", Constants.TPR_ENVIOREPORTEBHC);
        return ReporteEnvio;
    }
    //endregion

    //region todo HEMODINAMICA

    @RequestMapping(value = "/hemoExcel", method = RequestMethod.GET)
    public ModelAndView hemoToExcel(@RequestParam(value = "participantCode") int participantCode,
                                    @RequestParam(value = "currentYear") int currentYear
                                    )throws Exception{
        ModelAndView hemoToExcel = new ModelAndView("excelView");
        // Obtengo el listado por codigo Participante y anio
        List<DatosHemodinamica> listaHemodinamica = this.datoshemodinamicaService.getDatosByParticipantCodeAndCurYears(participantCode, currentYear);
        List<HemodinamicaDto>hemodinamicaDtoList= new ArrayList<HemodinamicaDto>();

        for (DatosHemodinamica loop: listaHemodinamica){
            HemodinamicaDto hemodinamicaDto = new HemodinamicaDto();
            hemodinamicaDto.setIdDatoHemo(loop.getIdDatoHemo());
            hemodinamicaDto.setCodigo_participante(loop.getParticipante().getCodigo());
            hemodinamicaDto.setFechaRegistro(loop.getRecordDate());
            hemodinamicaDto.setFecha(loop.getFecha());
            hemodinamicaDto.setEdad(loop.getEdad());
            hemodinamicaDto.setUsuario(loop.getRecordUser());
            hemodinamicaDto.setAsuperficiecorporal(loop.getAsc());
            hemodinamicaDto.setDias_enfermo(loop.getDiasenf());
            hemodinamicaDto.setImc(loop.getImc());
            hemodinamicaDto.setPeso(loop.getPeso());
            hemodinamicaDto.setTalla(loop.getTalla());
            hemodinamicaDto.setnExpediente(loop.getnExpediente());
            hemodinamicaDto.setFechaInicioEnfermedad(loop.getFie());
            List<HemoDetalle> hemoDetalles= this.datoshemodinamicaService.NumeroHemoDet(loop.getIdDatoHemo());
            hemodinamicaDto.setListaDetalles(hemoDetalles);
            hemodinamicaDto.setPositivo(loop.getPositivo());
            hemodinamicaDtoList.add(hemodinamicaDto);
        }
        hemoToExcel.addObject("hemodinamicaDtoList",hemodinamicaDtoList);
        List<MessageResource> extremidades = messageResourceService.getCatalogo("EXTREMIDADES");
        extremidades.addAll(messageResourceService.getCatalogo("NIVELCONCIENCIA"));
        extremidades.addAll(messageResourceService.getCatalogo("LLENADOCAPILAR"));
        extremidades.addAll(messageResourceService.getCatalogo("PULSOCALIDAD"));
        extremidades.addAll(messageResourceService.getCatalogo("DIURESIS"));
        hemoToExcel.addObject("extremidades", extremidades);
        hemoToExcel.addObject("TipoReporte", Constants.TPR_HEMOREPORTEEXCEL);
        return  hemoToExcel;
    }


    /*Este controlador devuelve archivo Hemodinámica  /ReporteHemodinamica/?idDatoHemo=e868722a-a855-4929-ba00-076df1b7ea5f    */
    @RequestMapping(value = "/ReporteHemodinamica", method = RequestMethod.GET)
    public ModelAndView ReporteHemodinamica(@RequestParam(value = "idDatoHemo", required = true) String idDatoHemo)
        throws Exception{
        ModelAndView pdfHemodinamic = new ModelAndView("pdfView");
        DatosHemodinamica obj = datoshemodinamicaService.getbyId(idDatoHemo);
        List<HemoDetalle> detalle = datoshemodinamicaService.getListHemoDetalle(idDatoHemo);
        List<MessageResource> messageReports = messageResourceService.loadAllMessagesNoCatalogs();
        List<MessageResource> extremidades = messageResourceService.getCatalogo("EXTREMIDADES");
        extremidades.addAll(messageResourceService.getCatalogo("NIVELCONCIENCIA"));
        extremidades.addAll(messageResourceService.getCatalogo("LLENADOCAPILAR"));
        extremidades.addAll(messageResourceService.getCatalogo("PULSOCALIDAD"));
        extremidades.addAll(messageResourceService.getCatalogo("DIURESIS"));
        pdfHemodinamic.addObject("extremidades", extremidades);
        pdfHemodinamic.addObject("labels", messageReports);
        pdfHemodinamic.addObject("obj", obj);
        pdfHemodinamic.addObject("detalle", detalle);
        pdfHemodinamic.addObject("TipoReporte", Constants.TPR_HEMOREPORTE);
        return pdfHemodinamic;
    }

    /* Este controlador devuelve archivo ScanCarta */
    @RequestMapping(value = "/ReporteCarta", method = RequestMethod.GET)
    public ModelAndView ReporteCarta(@RequestParam(value = "idparticipantecarta", required = true)Integer idparticipantecarta)
        throws  Exception{
        ModelAndView ReporteCarta = new ModelAndView("pdfView");
        ParticipanteCarta obj = scanCartaService.getCartasParticipante(idparticipantecarta);
        ReporteCarta.addObject("obj",obj);
        if (obj != null){
            ParticipanteProcesos procesos = participanteProcesosService.getParticipante(obj.getParticipante().getCodigo());
            ReporteCarta.addObject("procesos",procesos);
        }
        List<DetalleParte> dp = scanCartaService.getDetalleParteList(idparticipantecarta);
        ReporteCarta.addObject("dp",dp);

        List<ParticipanteExtension> getListParticipanteExtension = this.scanCartaService.getAllPartExt(idparticipantecarta);
        ReporteCarta.addObject("getListParticipanteExtension", getListParticipanteExtension);
        List<MessageResource> relFam = messageResourceService.getCatalogo("CP_CAT_RFTUTOR");
        relFam.addAll(messageResourceService.getCatalogo("CAT_SCAN_PROYECTO"));
        relFam.addAll(messageResourceService.getCatalogo("SCANCARTA"));
        relFam.addAll(messageResourceService.getCatalogo("CAT_TIPO_ASENT"));
        relFam.addAll(messageResourceService.getCatalogo("TIPOASENTIMIENTO"));
        relFam.addAll(messageResourceService.getCatalogo("CAT_CASOS_INDICE_MIEMBRO"));
        ReporteCarta.addObject("relFam",relFam);
        ReporteCarta.addObject("TipoReporte", Constants.TPR_REPORTECARTA);
         return ReporteCarta;
    }

    @RequestMapping(value = "downloadLettesInfo", method = RequestMethod.GET)
    public ModelAndView downloadLettesInfo(@RequestParam(value="fechaInicio", required=false ) String fechaInicio,
                                       @RequestParam(value="fechaFin", required=false ) String fechaFin)
            throws Exception {
        ModelAndView ReporteEnvio = new ModelAndView("excelView");
        Date dFechaInicio = null;
        if (fechaInicio != null && !fechaInicio.isEmpty())
            dFechaInicio = DateUtil.StringToDate(fechaInicio, "dd/MM/yyyy");
        Date dFechaFin = null;
        if (fechaFin != null && !fechaFin.isEmpty())
            dFechaFin = DateUtil.StringToDate(fechaFin + " 23:59:59", "dd/MM/yyyy HH:mm:ss");
        List<InformacionPorEstudioDto> porEstudioDtoList = this.scanCartaService.getInformacionPorEstudioDto(dFechaInicio, dFechaFin);
        List<InformacionPorDiaDto> porDiaDtoList = this.scanCartaService.getInformacionPorDiaDto(dFechaInicio, dFechaFin);
        List<InformacionPorBarrioDto> porBarrioDtoList = this.scanCartaService.getInformacionPorBarrioDto(dFechaInicio, dFechaFin);
        List<InformacionRangoEdadDto> porRangoEdadDtoList = this.scanCartaService.getInformacionRangoEdadDto(dFechaInicio, dFechaFin);
        List<InformacionRecursoDto> porRecursoDtoList = this.scanCartaService.getInformacionRecursoDto(dFechaInicio, dFechaFin);
        List<InformacionUsuarioDto> porUsuarioDtoList = this.scanCartaService.getInformacionUsuarioDto(dFechaInicio, dFechaFin);

        InformacionCartasDto informacionCartasDto = new InformacionCartasDto();
        informacionCartasDto.setPorEstudio(porEstudioDtoList);
        informacionCartasDto.setPorDia(porDiaDtoList);
        informacionCartasDto.setPorBarrio(porBarrioDtoList);
        informacionCartasDto.setPorRangoEdad(porRangoEdadDtoList);
        informacionCartasDto.setPorRecurso(porRecursoDtoList);
        informacionCartasDto.setPorUsuario(porUsuarioDtoList);

        ReporteEnvio.addObject("fechaInicio", fechaInicio);
        ReporteEnvio.addObject("fechaFin", fechaFin);
        ReporteEnvio.addObject("datos", informacionCartasDto);
        ReporteEnvio.addObject("TipoReporte", Constants.TPR_INFOCARTAS);
        return ReporteEnvio;
    }

    @RequestMapping(value = "/diferencias-mx-excel", method = RequestMethod.GET)
    public ModelAndView excelComparacionMxHoy()
            throws Exception{
        ModelAndView modelAndView = new ModelAndView("excelView");
        ComparacionMuestrasDto comparacionMuestrasDto = new ComparacionMuestrasDto();

        //comparacion de rojos
        comparacionMuestrasDto.setRojoSupervisorNoEst(seroService.getCompSeroSupEstHoy());
        comparacionMuestrasDto.setRojoSupervisorNoLab(seroService.getCompSeroSupLabHoy());
        comparacionMuestrasDto.setRojoEstacionesNoSup(muestraService.getCompSeroEstSupHoy());
        comparacionMuestrasDto.setRojoEstacionesNoLab(muestraService.getCompSeroEstLabHoy());
        comparacionMuestrasDto.setRojoLaboratorioNoSup(labSeroService.getCompSeroLabSupHoy());
        comparacionMuestrasDto.setRojoLaboratorioNoEst(labSeroService.getCompSeroLabEstHoy());

        //comparacion bhc
        comparacionMuestrasDto.setBhcSupNoEst(recepcionBHCService.getCompBHCSupEstHoy());
        comparacionMuestrasDto.setBhcSupNoLab(recepcionBHCService.getCompBHCSupLabHoy());
        comparacionMuestrasDto.setBhcEstnoSup(muestraService.getCompBHCEstSupHoy());
        comparacionMuestrasDto.setBhcEstnoLab(muestraService.getCompBHCEstLabHoy());
        comparacionMuestrasDto.setBhcLabNoSup(labBhcService.getCompBHCLabSupHoy());
        comparacionMuestrasDto.setBhcLabNoEst(labBhcService.getCompBHCLabEstHoy());

        //comparacion pbmc
        comparacionMuestrasDto.setPbmcLabNoEst(labPbmcService.getCompPbmcLabEstHoy());
        comparacionMuestrasDto.setPbmcEstnoLab(muestraService.getCompPbmcEstLabHoy());

        modelAndView.addObject("datos", comparacionMuestrasDto);
        modelAndView.addObject("TipoReporte", Constants.TPR_COMPARACION_MX_MA);
        return modelAndView;
    }

    @RequestMapping(value = "downloadEntoInfo", method = RequestMethod.GET)
    public ModelAndView downloadEntoInfo(@RequestParam(value="fechaInicio", required=false ) String fechaInicio,
                                           @RequestParam(value="fechaFin", required=false ) String fechaFin)
            throws Exception {
        ModelAndView reporteDatosEntomologia = new ModelAndView("excelView");
        Date dFechaInicio = null;
        Date dFechaFin = null;
        List<CuestionarioHogar> cuestionarios = new ArrayList<CuestionarioHogar>();
        List<CuestionarioHogarPoblacion> poblacion = new ArrayList<CuestionarioHogarPoblacion>();

        if ((fechaInicio != null && !fechaInicio.isEmpty()) && (fechaFin != null && !fechaFin.isEmpty())) {
            dFechaInicio = DateUtil.StringToDate(fechaInicio, "dd/MM/yyyy");
            dFechaFin = DateUtil.StringToDate(fechaFin + " 23:59:59", "dd/MM/yyyy HH:mm:ss");
            cuestionarios = this.cuestionarioHogarService.getCuestionariosHogarByRangoFechas(dFechaInicio, dFechaFin);
            poblacion = this.cuestionarioHogarService.getCuestionariosHogarPobByRangoFechas(dFechaInicio, dFechaFin);
        } else {
            cuestionarios = this.cuestionarioHogarService.getCuestionariosHogar();
            poblacion = this.cuestionarioHogarService.getCuestionariosHogarPoblacion();
        }

        reporteDatosEntomologia.addObject("fechaInicio", fechaInicio);
        reporteDatosEntomologia.addObject("fechaFin", fechaFin);
        reporteDatosEntomologia.addObject("cuestionarios", cuestionarios);
        reporteDatosEntomologia.addObject("poblacion", poblacion);
        reporteDatosEntomologia.addObject("TipoReporte", Constants.TPR_ENTO);

        return reporteDatosEntomologia;
    }

    //region todo Reporte Retiro estudios_ics /reportes/reporteRetiro
    @RequestMapping(value = "/reporteRetiro", method = RequestMethod.GET)
    public ModelAndView retiro(@RequestParam(value="parametro", required=false ) Integer parametro)
            throws Exception{
        ModelAndView ReporteRetiro = new ModelAndView("pdfView");
        List<MessageResource> causas_retiros = messageResourceService.getCatalogo("CAT_CAUSAS_RETIROS");
        ReporteRetiro.addObject("causas_retiros", causas_retiros);
        List<MessageResource> coordinador_estudio = messageResourceService.getCatalogo("CAT_COORDINADOR_ESTUDIO");
        ReporteRetiro.addObject("coordinador_estudio", coordinador_estudio);
        List<MessageResource> relFam = messageResourceService.getCatalogo("CAT_RF_TUTOR");
        ReporteRetiro.addObject("relFam", relFam);
        Retiros retiros = this.retiroService.getRetiroByID(parametro);
        ReporteRetiro.addObject("retiros", retiros);
        Personal personal = this.retiroService.getSupervisorById(retiros.getPersonadocumenta());
        ReporteRetiro.addObject("personal", personal);
        Personal supervisor = this.retiroService.getSupervisorById(retiros.getMedicosupervisor());
        ReporteRetiro.addObject("supervisor", supervisor);
        List<Razones_Retiro> listaDerazones = this.retiroService.getlistaDeRazonRetiro();
        ReporteRetiro.addObject("listaDerazones", listaDerazones);
        List<Razones_Retiro> listaDeRazonesGrupo_1 = this.retiroService.getlistaDeRazonRetiroPorIdGrupo(1);
        ReporteRetiro.addObject("listaDeRazonesGrupo_1", listaDeRazonesGrupo_1);
        List<Razones_Retiro> listaDeRazonesGrupo_2 = this.retiroService.getlistaDeRazonRetiroPorIdGrupo(2);
        ReporteRetiro.addObject("listaDeRazonesGrupo_2", listaDeRazonesGrupo_2);
        List<Razones_Retiro> listaDeRazonesGrupo_3 = this.retiroService.getlistaDeRazonRetiroPorIdGrupo(3);
        ReporteRetiro.addObject("listaDeRazonesGrupo_3", listaDeRazonesGrupo_3);
        List<Razones_Retiro> listaDeRazonesGrupo_4 = this.retiroService.getlistaDeRazonRetiroPorIdGrupo(4);
        ReporteRetiro.addObject("listaDeRazonesGrupo_4", listaDeRazonesGrupo_4);
        ReporteRetiro.addObject("TipoReporte", Constants.TPR_REPORTERETIRO);
        return ReporteRetiro;
    }
    //endregion


    //region todo: filtro para encontrar Muestras
    @RequestMapping(value = "/buscarMuestras", method = RequestMethod.GET)
    public ModelAndView buscarMuestras(@RequestParam(value="fechaInicio", required=false ) String fechaInicio,
                                     @RequestParam(value="fechaFin", required=false ) String fechaFin,
                                     @RequestParam(value="tipoMuestra", required=false ) Integer tipoMuestra ) throws Exception{

        ModelAndView filtrarMuestra = new ModelAndView("pdfView");
        Date dFechaInicio = null;
        if (fechaInicio!=null && !fechaInicio.isEmpty())
            dFechaInicio = DateUtil.StringToDate(fechaInicio, "dd/MM/yyyy");
        Date dFechaFin = null;
        if (fechaFin!=null && !fechaFin.isEmpty())
            dFechaFin = DateUtil.StringToDate(fechaFin+ " 23:59:59", "dd/MM/yyyy HH:mm:ss");

        filtrarMuestra.addObject("fechaInicio",fechaInicio);
        filtrarMuestra.addObject("fechaFin",fechaFin);
        filtrarMuestra.addObject("tipoMuestra", ""+tipoMuestra);

        if (tipoMuestra == 0){
            List<Pbmc_Detalle_Envio> filtro_Pbmc =  this.serologiaservice.filtroListPbmc(dFechaInicio,dFechaFin);
            filtrarMuestra.addObject("filtro_Pbmc",filtro_Pbmc);
        }

        if (tipoMuestra == 1){
            List<Serologia_Detalle_Envio> filtro_Serologia = this.serologiaservice.filtroListSerologia(dFechaInicio,dFechaFin);
            filtrarMuestra.addObject("filtro_Serologia",filtro_Serologia);
        }

        if (tipoMuestra == 2){
            List<Bhc_Detalle_envio> filtro_Bhc = this.serologiaservice.filtroListBhc(dFechaInicio,dFechaFin);
            filtrarMuestra.addObject("filtro_Bhc",filtro_Bhc);
        }

        if (tipoMuestra == 3){
            List<Serologia_Detalle_Envio> filtro_RojoAdicional = this.serologiaservice.filtroListRojoAdicionalPbmc(dFechaInicio,dFechaFin);
            filtrarMuestra.addObject("filtro_RojoAdicional",filtro_RojoAdicional);
        }

        List<MessageResource> catalogosLugarTipoMuestra = messageResourceService.getCatalogo("CAT_ENVIAR_MUETRAS");
        catalogosLugarTipoMuestra.addAll(messageResourceService.getCatalogo("CAT_TIPO_TUBO_MUESTRA"));
        filtrarMuestra.addObject("catalogosLugarTipoMuestra",catalogosLugarTipoMuestra);

        filtrarMuestra.addObject("TipoReporte", Constants.TPR_FILTROMUESTRA);
        return filtrarMuestra;
    }
    //endregion
}
