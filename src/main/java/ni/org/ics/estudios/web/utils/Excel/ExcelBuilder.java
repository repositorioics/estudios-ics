package ni.org.ics.estudios.web.utils.Excel;

import ni.org.ics.estudios.domain.Bhc.Bhc_Detalle_envio;
import ni.org.ics.estudios.domain.Pbmc.Pbmc_Detalle_Envio;
import ni.org.ics.estudios.domain.SerologiaOct2020.SerologiaEnvio;
import ni.org.ics.estudios.domain.SerologiaOct2020.Serologia_Detalle_Envio;
import ni.org.ics.estudios.domain.cohortefamilia.Muestra;
import ni.org.ics.estudios.domain.muestreoanual.*;
import ni.org.ics.estudios.dto.BhcEnvioDto;
import ni.org.ics.estudios.dto.ComparacionMuestrasDto;
import ni.org.ics.estudios.dto.cartas.*;
import ni.org.ics.estudios.dto.muestras.MuestraDto;
import ni.org.ics.estudios.dto.muestras.RecepcionBHCDto;
import ni.org.ics.estudios.web.utils.pdf.Constants;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.CellReference;
import org.springframework.web.servlet.view.document.AbstractExcelView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigInteger;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * This class builds an Excel spreadsheet document using Apache POI library.
 * @author www.codejava.net
 *
 */
public class ExcelBuilder extends AbstractExcelView {

    private static final Logger logger = Logger.getLogger("ni.org.ics.webapp.web.utils.Excel.ExcelBuilder");

	@Override
	protected void buildExcelDocument(Map<String, Object> model,
			HSSFWorkbook workbook, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
        String reporte = model.get("TipoReporte").toString();
        if (reporte.equalsIgnoreCase(Constants.TPR_ENVIOREPORTE)) {
            buildExcelDocumentVigDx(model, workbook, response);
        } else
        if (reporte.equalsIgnoreCase(Constants.TPR_ENVIOREPORTEPBCM)){
            buildExcelSerologiaByPbmc(model, workbook, response);
        } else
        if (reporte.equalsIgnoreCase(Constants.TPR_ENVIOREPORTEPBCMTOEXCEL)){
            buildExcelOnlyPbmc(model, workbook, response);
        } else
        if (reporte.equalsIgnoreCase(Constants.TPR_ENVIOREPORTEBHC)){
            buildExcelBhc(model, workbook, response);
        } else
        if (reporte.equalsIgnoreCase(Constants.TPR_INFOCARTAS)){
            buildExcelInfoCartas(model, workbook, response);
        } else if (reporte.equalsIgnoreCase(Constants.TPR_COMPARACION_MX_MA)){
            buildExcelComparacionMuestrasMA(model, workbook, response);
        }
	}
    private static final DecimalFormat df = new DecimalFormat("0.00");

    public void buildExcelDocumentVigDx(Map<String, Object> model, HSSFWorkbook workbook,HttpServletResponse response) throws IOException {
        List<Serologia_Detalle_Envio> listaSerologia = (List<Serologia_Detalle_Envio>) model.get("allSerologia");
        logger.log(Level.INFO, "construyendo libro de excel...");
        response.setContentType("application/octec-stream");
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy_HH:mm:ss");
        String fechaActual = dateFormat.format(new Date());
        String fileName = "envio_muestras_CNDR_"+ fechaActual +".xls";
        response.setHeader("Content-Disposition", "attachment; filename="+ fileName);
        HSSFSheet sheet = workbook.createSheet("Hoja1");
        Font font = workbook.createFont();
        String[] headers = new String[]{
                "CODIGO",
                "fecha",
                "volumen",
                "observacion",
                "PRecepciona",
                "estudio",
                "edadA",
                "edadM",
                "viaje"
        };
        CellStyle headerStyle = workbook.createCellStyle();
        font.setBold(true);
        headerStyle.setFont(font);

        HSSFRow headerRow = sheet.createRow(0);
        for (int i = 0; i < headers.length; ++i) {
            String header = headers[i];
            HSSFCell cell = headerRow.createCell(i);
            cell.setCellStyle(headerStyle);
            cell.setCellValue(header);
        }

        if (listaSerologia.size()>0) {
            //Cell style for content cells
            font = workbook.createFont();
            font.setFontName("Calibri");
            font.setFontHeight((short) (11 * 20));
            font.setColor(HSSFColor.BLACK.index);

            CellStyle dateCellStyle = workbook.createCellStyle();
            CreationHelper createHelper = workbook.getCreationHelper();
            dateCellStyle.setDataFormat(createHelper.createDataFormat().getFormat("MM/dd/yyyy"));
            dateCellStyle.setBorderBottom(BorderStyle.THIN);
            dateCellStyle.setBorderTop(BorderStyle.THIN);
            dateCellStyle.setBorderLeft(BorderStyle.THIN);
            dateCellStyle.setBorderRight(BorderStyle.THIN);
            dateCellStyle.setFont(font);

            CellStyle style = workbook.createCellStyle();
            style.setFillForegroundColor(IndexedColors.LIGHT_YELLOW.getIndex());
            style.setFillPattern(FillPatternType.SOLID_FOREGROUND);

            CellStyle contentCellStyle = workbook.createCellStyle();
            contentCellStyle.setBorderBottom(BorderStyle.THIN);
            contentCellStyle.setBorderTop(BorderStyle.THIN);
            contentCellStyle.setBorderLeft(BorderStyle.THIN);
            contentCellStyle.setBorderRight(BorderStyle.THIN);
            contentCellStyle.setFont(font);

            int rowCount = 1;
            for (Serologia_Detalle_Envio registro : listaSerologia) {
                HSSFRow dataRow = sheet.createRow(rowCount++);
                dataRow.createCell(0).setCellValue(registro.getSerologia().getParticipante());
                sheet.autoSizeColumn(0);
                dataRow.createCell(1).setCellValue(ni.org.ics.estudios.web.utils.DateUtil.DateToString(registro.getSerologia().getFecha(), "dd/MM/yyyy"));
                sheet.autoSizeColumn(1);
                dataRow.createCell(2).setCellValue(registro.getSerologia().getVolumen());
                sheet.autoSizeColumn(2);
                dataRow.createCell(3).setCellValue(registro.getSerologia().getObservacion());
                sheet.autoSizeColumn(3);
                dataRow.createCell(4).setCellValue(registro.getSerologia().getRecordUser());
                sheet.autoSizeColumn(4);
                dataRow.createCell(5).setCellValue(registro.getSerologia().getEstudio());
                sheet.autoSizeColumn(5);
                Double ageAnios = Math.floor(registro.getSerologia().getEdadMeses() / 12);
                dataRow.createCell(6).setCellValue(ageAnios.intValue());
                sheet.autoSizeColumn(6);
                //edad Meses
                double d = registro.getSerologia().getEdadMeses();
                Double edadMeses = d % 12;
                dataRow.createCell(7).setCellValue(edadMeses.intValue());
                sheet.autoSizeColumn(7);
                dataRow.createCell(8).setCellValue(registro.getSerologiaEnvio().getIdenvio());
                sheet.autoSizeColumn(8);
            }
        }else{
            CellStyle noDataCellStyle = workbook.createCellStyle();
            noDataCellStyle.setAlignment(HorizontalAlignment.CENTER);
            noDataCellStyle.setFont(font);
            HSSFRow aRow = sheet.createRow(1);
            sheet.addMergedRegion(new CellRangeAddress(aRow.getRowNum(), aRow.getRowNum(), 0, headers.length - 1));
            aRow.createCell(0).setCellValue("NO SE ENCONTRARON DATOS!");
            aRow.getCell(0).setCellStyle(noDataCellStyle);
        }
    }

    //todo en reporte
    public void buildExcelSerologiaByPbmc(Map<String, Object>model, HSSFWorkbook workbook, HttpServletResponse response)throws IOException{

        List<Serologia_Detalle_Envio> seroConPbmc = (List<Serologia_Detalle_Envio>) model.get("SerologiaWithPbmc");
        logger.log(Level.INFO, "construyendo libro de excel...");
        response.setContentType("application/octec-stream");
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy_HH:mm:ss");
        String fechaActual = dateFormat.format(new Date());
        String fileName = "envio_Rojo_Adicional_CNDR_"+ fechaActual +".xls";
        response.setHeader("Content-Disposition", "attachment; filename="+ fileName);
        HSSFSheet sheet = workbook.createSheet("Hoja1");
        Font font = workbook.createFont();

        String[] headers = new String[]{
                "CODIGO",
                "fecha",
                "volumen",
                "observacion",
                "PRecepciona",
                "estudio",
                "edadA",
                "edadM",
                "viaje",
                "descripcion"
        };
        CellStyle headerStyle = workbook.createCellStyle();
        font.setBold(true);
        headerStyle.setFont(font);

        HSSFRow headerRow = sheet.createRow(0);
        for (int i = 0; i < headers.length; ++i) {
            String header = headers[i];
            HSSFCell cell = headerRow.createCell(i);
            cell.setCellStyle(headerStyle);
            cell.setCellValue(header);
        }

        if (seroConPbmc.size()>0){
            //Obtengo el Envio de Pbmc
            SerologiaEnvio objEnvio= seroConPbmc.get(0).getSerologiaEnvio();

            //Cell style for content cells
            font = workbook.createFont();
            font.setFontName("Calibri");
            font.setFontHeight((short) (11 * 20));
            font.setColor(HSSFColor.BLACK.index);

            CellStyle dateCellStyle = workbook.createCellStyle();
            CreationHelper createHelper = workbook.getCreationHelper();
            dateCellStyle.setDataFormat(createHelper.createDataFormat().getFormat("MM/dd/yyyy"));
            dateCellStyle.setBorderBottom(BorderStyle.THIN);
            dateCellStyle.setBorderTop(BorderStyle.THIN);
            dateCellStyle.setBorderLeft(BorderStyle.THIN);
            dateCellStyle.setBorderRight(BorderStyle.THIN);
            dateCellStyle.setFont(font);

            CellStyle style = workbook.createCellStyle();
            style.setFillForegroundColor(IndexedColors.LIGHT_YELLOW.getIndex());
            style.setFillPattern(FillPatternType.SOLID_FOREGROUND);

            CellStyle contentCellStyle = workbook.createCellStyle();
            contentCellStyle.setBorderBottom(BorderStyle.THIN);
            contentCellStyle.setBorderTop(BorderStyle.THIN);
            contentCellStyle.setBorderLeft(BorderStyle.THIN);
            contentCellStyle.setBorderRight(BorderStyle.THIN);
            contentCellStyle.setFont(font);

            int rowCount = 1;

            for (Serologia_Detalle_Envio registro : seroConPbmc) {
                HSSFRow dataRow = sheet.createRow(rowCount++);
                dataRow.createCell(0).setCellValue(registro.getSerologia().getParticipante().toString());
                sheet.autoSizeColumn(0);
                dataRow.createCell(1).setCellValue(ni.org.ics.estudios.web.utils.DateUtil.DateToString(registro.getSerologia().getFecha(), "dd/MM/yyyy"));
                sheet.autoSizeColumn(1);
                dataRow.createCell(2).setCellValue(registro.getSerologia().getVolumen());
                sheet.autoSizeColumn(2);
                dataRow.createCell(3).setCellValue(registro.getSerologia().getObservacion());
                sheet.autoSizeColumn(3);
                dataRow.createCell(4).setCellValue(registro.getSerologia().getRecordUser());
                sheet.autoSizeColumn(4);
                dataRow.createCell(5).setCellValue(registro.getSerologia().getEstudio());
                sheet.autoSizeColumn(5);
                Double ageAnios = Math.floor(registro.getSerologia().getEdadMeses() / 12);
                dataRow.createCell(6).setCellValue(ageAnios.intValue());
                sheet.autoSizeColumn(6);
                //edad Meses
                double d = registro.getSerologia().getEdadMeses();
                Double edadMeses = d % 12;
                dataRow.createCell(7).setCellValue(edadMeses.intValue());
                sheet.autoSizeColumn(7);
                dataRow.createCell(8).setCellValue(registro.getSerologiaEnvio().getIdenvio());
                sheet.autoSizeColumn(8);
                //lleva serologia
                dataRow.createCell(9).setCellValue(registro.getSerologia().getDescripcion());
                sheet.autoSizeColumn(9);
            }/* fin del for*/


        }else {
            CellStyle noDataCellStyle = workbook.createCellStyle();
            noDataCellStyle.setAlignment(HorizontalAlignment.CENTER);
            noDataCellStyle.setFont(font);
            HSSFRow aRow = sheet.createRow(1);
            sheet.addMergedRegion(new CellRangeAddress(aRow.getRowNum(), aRow.getRowNum(), 0, headers.length - 1));
            aRow.createCell(0).setCellValue("NO SE ENCONTRARON DATOS!");
            aRow.getCell(0).setCellStyle(noDataCellStyle);
        }





    }

    //region todo: Reporte en Excel unicamente de PBMC
    public void buildExcelOnlyPbmc(Map<String, Object>model, HSSFWorkbook workbook, HttpServletResponse response)throws IOException{
        List<Pbmc_Detalle_Envio> pbmc_detalle_envios = (List<Pbmc_Detalle_Envio>) model.get("allPbmc");
        //List<PbmcHorasToma> horasPbmc = (List<PbmcHorasToma>) model.get("horasPbmc");

        logger.log(Level.INFO, "construyendo libro de excel...");
        response.setContentType("application/octec-stream");
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy_HH:mm:ss");
        String fechaActual = dateFormat.format(new Date());
        String fileName = "envio_mxPBMC_CNDR_"+ fechaActual +".xls";
        response.setHeader("Content-Disposition", "attachment; filename="+ fileName);
        HSSFSheet sheet = workbook.createSheet("Hoja1");
        Font font = workbook.createFont();

        String[] headers = new String[]{
                "CODIGO",
                "fecha",
                //"hora",
                "volumen",
                "observacion",
                "PRecepciona",
                "estudio",
                "edadA",
                "edadM",
                "viaje",
                "tiene_rojo"
        };
        CellStyle headerStyle = workbook.createCellStyle();
        font.setBold(true);
        headerStyle.setFont(font);

        HSSFRow headerRow = sheet.createRow(0);
        for (int i = 0; i < headers.length; ++i) {
            String header = headers[i];
            HSSFCell cell = headerRow.createCell(i);
            cell.setCellStyle(headerStyle);
            cell.setCellValue(header);
        }

        if (pbmc_detalle_envios.size()>0){
            //Obtengo el Envio de Pbmc
            //SerologiaEnvio objEnvio= pbmc_detalle_envios.get(0).getSerologiaEnvio();

            //Cell style for content cells
            font = workbook.createFont();
            font.setFontName("Calibri");
            font.setFontHeight((short) (11 * 20));
            font.setColor(HSSFColor.BLACK.index);

            CellStyle dateCellStyle = workbook.createCellStyle();
            CreationHelper createHelper = workbook.getCreationHelper();
            dateCellStyle.setDataFormat(createHelper.createDataFormat().getFormat("MM/dd/yyyy"));
            dateCellStyle.setBorderBottom(BorderStyle.THIN);
            dateCellStyle.setBorderTop(BorderStyle.THIN);
            dateCellStyle.setBorderLeft(BorderStyle.THIN);
            dateCellStyle.setBorderRight(BorderStyle.THIN);
            dateCellStyle.setFont(font);

            CellStyle style = workbook.createCellStyle();
            style.setFillForegroundColor(IndexedColors.LIGHT_YELLOW.getIndex());
            style.setFillPattern(FillPatternType.SOLID_FOREGROUND);

            CellStyle contentCellStyle = workbook.createCellStyle();
            contentCellStyle.setBorderBottom(BorderStyle.THIN);
            contentCellStyle.setBorderTop(BorderStyle.THIN);
            contentCellStyle.setBorderLeft(BorderStyle.THIN);
            contentCellStyle.setBorderRight(BorderStyle.THIN);
            contentCellStyle.setFont(font);

            int rowCount = 1;
            for (Pbmc_Detalle_Envio registro : pbmc_detalle_envios) {
                HSSFRow dataRow = sheet.createRow(rowCount++);
                dataRow.createCell(0).setCellValue(registro.getPbmc().getCodigo_participante());
                sheet.autoSizeColumn(0);
                dataRow.createCell(1).setCellValue(ni.org.ics.estudios.web.utils.DateUtil.DateToString(registro.getPbmc().getFecha_pbmc(), "dd/MM/yyyy"));
                sheet.autoSizeColumn(1);
                dataRow.createCell(2).setCellValue(registro.getPbmc().getVolumen());
                sheet.autoSizeColumn(2);
                dataRow.createCell(3).setCellValue(registro.getPbmc().getObservacion());
                sheet.autoSizeColumn(3);
                dataRow.createCell(4).setCellValue(registro.getPbmc().getRecordUser());
                sheet.autoSizeColumn(4);
                dataRow.createCell(5).setCellValue(registro.getPbmc().getEstudios());
                sheet.autoSizeColumn(5);
                Double ageAnios = Math.floor(registro.getPbmc().getEdadMeses() / 12);
                dataRow.createCell(6).setCellValue(ageAnios.intValue());
                sheet.autoSizeColumn(6);
                //edad Meses
                double d = registro.getPbmc().getEdadMeses();
                Double edadMeses = d % 12;
                dataRow.createCell(7).setCellValue(edadMeses.intValue());
                sheet.autoSizeColumn(7);
                dataRow.createCell(8).setCellValue(registro.getSerologiaEnvio().getIdenvio());
                sheet.autoSizeColumn(8);
                //lleva serologia
                String lleva_rojo = (registro.getPbmc().getPbmc_tiene_serologia()=='1')?"Si":"No";
                dataRow.createCell(9).setCellValue(lleva_rojo);
                sheet.autoSizeColumn(9);
            }
            /* fin del for*/

        }else{
            CellStyle noDataCellStyle = workbook.createCellStyle();
            noDataCellStyle.setAlignment(HorizontalAlignment.CENTER);
            noDataCellStyle.setFont(font);
            HSSFRow aRow = sheet.createRow(1);
            sheet.addMergedRegion(new CellRangeAddress(aRow.getRowNum(), aRow.getRowNum(), 0, headers.length - 1));
            aRow.createCell(0).setCellValue("NO SE ENCONTRARON DATOS!");
            aRow.getCell(0).setCellStyle(noDataCellStyle);
        }
    }
    //endregion


    public void buildExcelBhc(Map<String, Object>model, HSSFWorkbook workbook, HttpServletResponse response)throws IOException{
        //List<Bhc_Detalle_envio> bhc_detalle_envios = (List<Bhc_Detalle_envio>) model.get("allBhc");
        List<BhcEnvioDto> bhc_detalle_envios = (List<BhcEnvioDto>) model.get("allBhc");

        logger.log(Level.INFO, "construyendo libro de excel para bhc...");
        response.setContentType("application/octec-stream");
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy_HH:mm:ss");
        String fechaActual = dateFormat.format(new Date());
        String fileName = "envio_mxBHC_CNDR_"+ fechaActual +".xls";
        response.setHeader("Content-Disposition", "attachment; filename="+ fileName);
        HSSFSheet sheet = workbook.createSheet("Hoja1");
        Font font = workbook.createFont();

        String[] headers = new String[]{
                "CODIGO",
                "fecha",
                "volumen",
                "observacion",
                "ADN_DEN",
                "ADN_UO1",
                "ADN_FLU",
                "ADN_CHF",
                "PRecepciona",
                "estudio",
                "edadA",
                "edadM",
                "viaje",
        };
        CellStyle headerStyle = workbook.createCellStyle();
        font.setBold(true);
        headerStyle.setFont(font);
        HSSFRow headerRow = sheet.createRow(0);
        for (int i = 0; i < headers.length; ++i) {
            String header = headers[i];
            HSSFCell cell = headerRow.createCell(i);
            cell.setCellStyle(headerStyle);
            cell.setCellValue(header);
        }

        if (bhc_detalle_envios.size()>0){
            //Obtengo el Envio de Pbmc
//            SerologiaEnvio objEnvio= bhc_detalle_envios.get(0).getSerologiaEnvio();

            //Cell style for content cells
            font = workbook.createFont();
            font.setFontName("Calibri");
            font.setFontHeight((short) (11 * 20));
            font.setColor(HSSFColor.BLACK.index);

            CellStyle dateCellStyle = workbook.createCellStyle();
            CreationHelper createHelper = workbook.getCreationHelper();
            dateCellStyle.setDataFormat(createHelper.createDataFormat().getFormat("MM/dd/yyyy"));
            dateCellStyle.setBorderBottom(BorderStyle.THIN);
            dateCellStyle.setBorderTop(BorderStyle.THIN);
            dateCellStyle.setBorderLeft(BorderStyle.THIN);
            dateCellStyle.setBorderRight(BorderStyle.THIN);
            dateCellStyle.setFont(font);

            CellStyle style = workbook.createCellStyle();
            style.setFillForegroundColor(IndexedColors.LIGHT_YELLOW.getIndex());
            style.setFillPattern(FillPatternType.SOLID_FOREGROUND);

            CellStyle contentCellStyle = workbook.createCellStyle();
            contentCellStyle.setBorderBottom(BorderStyle.THIN);
            contentCellStyle.setBorderTop(BorderStyle.THIN);
            contentCellStyle.setBorderLeft(BorderStyle.THIN);
            contentCellStyle.setBorderRight(BorderStyle.THIN);
            contentCellStyle.setFont(font);

            int rowCount = 1;
            for (BhcEnvioDto registro: bhc_detalle_envios){
                HSSFRow dataRow = sheet.createRow(rowCount++);
                dataRow.createCell(0).setCellValue(registro.getCodigo());
                sheet.autoSizeColumn(0);
                dataRow.createCell(1).setCellValue(ni.org.ics.estudios.web.utils.DateUtil.DateToString(registro.getFecha(), "dd/MM/yyyy"));
                sheet.autoSizeColumn(1);
                dataRow.createCell(2).setCellValue(registro.getVolumen());
                sheet.autoSizeColumn(2);
                dataRow.createCell(3).setCellValue(registro.getObservacion());
                sheet.autoSizeColumn(3);
                dataRow.createCell(4).setCellValue(registro.getAdnDengue());
                sheet.autoSizeColumn(4);
                dataRow.createCell(5).setCellValue(registro.getAdnUO1());
                sheet.autoSizeColumn(5);
                dataRow.createCell(6).setCellValue(registro.getAdnFlu());
                sheet.autoSizeColumn(6);
                dataRow.createCell(7).setCellValue(registro.getAdnChf());
                sheet.autoSizeColumn(7);
                dataRow.createCell(8).setCellValue(registro.getpRecepciona());
                sheet.autoSizeColumn(8);
                dataRow.createCell(9).setCellValue(registro.getEstudios());
                sheet.autoSizeColumn(9);
                dataRow.createCell(10).setCellValue(registro.getEdadA().intValue());
                sheet.autoSizeColumn(10);
                dataRow.createCell(11).setCellValue(registro.getEdadM().intValue());
                sheet.autoSizeColumn(11);
                //envio
                dataRow.createCell(12).setCellValue(registro.getViaje());
                sheet.autoSizeColumn(12);
            } /* fin del for*/
        }else{//No se encontraron registro de Bhc
            CellStyle noDataCellStyle = workbook.createCellStyle();
            noDataCellStyle.setAlignment(HorizontalAlignment.CENTER);
            noDataCellStyle.setFont(font);
            HSSFRow aRow = sheet.createRow(1);
            sheet.addMergedRegion(new CellRangeAddress(aRow.getRowNum(), aRow.getRowNum(), 0, headers.length - 1));
            aRow.createCell(0).setCellValue("NO SE ENCONTRARON DATOS!");
            aRow.getCell(0).setCellStyle(noDataCellStyle);
        }
    }

    public void buildExcelInfoCartas(Map<String, Object> model, HSSFWorkbook workbook,HttpServletResponse response) throws IOException {

        String fechaInicio = model.get("fechaInicio").toString();
        String fechaFin = model.get("fechaFin").toString();
        InformacionCartasDto informacionCartasDto = (InformacionCartasDto) model.get("datos");
        List<InformacionPorEstudioDto> porEstudioDtoList = informacionCartasDto.getPorEstudio();
        List<InformacionPorDiaDto> porDiaDtoList = informacionCartasDto.getPorDia();
        List<InformacionPorBarrioDto> porBarrioDtoList = informacionCartasDto.getPorBarrio();
        List<InformacionRangoEdadDto> porRangoEdadDtoList = informacionCartasDto.getPorRangoEdad();
        List<InformacionRecursoDto> porRecursoDtoList = informacionCartasDto.getPorRecurso();
        List<InformacionUsuarioDto> porUsuarioDtoList = informacionCartasDto.getPorUsuario();

        logger.log(Level.INFO, "construyendo libro de excel...");

        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy_HH:mm:ss");
        String fechaActual = dateFormat.format(new Date());
        String fileName = "informacion_cartas_"+ fechaActual +".xls";

        response.setContentType("application/octec-stream");
        response.setHeader("Content-Disposition", "attachment; filename="+ fileName);

        HSSFSheet sheet = workbook.createSheet("Hoja1");

        Font font = workbook.createFont();
        font.setFontName("Arial");
        font.setFontHeight((short)(11*20));
        font.setColor(HSSFColor.BLACK.index);
        font.setBold(true);

        Font fontContent = workbook.createFont();
        fontContent.setFontName("Calibri");
        fontContent.setFontHeight((short) (11 * 20));
        fontContent.setColor(HSSFColor.BLACK.index);

        Font fontTotal = workbook.createFont();
        fontTotal.setFontName("Calibri");
        fontTotal.setFontHeight((short) (11 * 20));
        fontTotal.setColor(HSSFColor.BLACK.index);
        fontTotal.setBold(true);

        CellStyle headerStyle = workbook.createCellStyle();
        headerStyle.setFillForegroundColor(HSSFColor.GREY_25_PERCENT.index);
        headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        headerStyle.setAlignment(HorizontalAlignment.CENTER);
        headerStyle.setBorderBottom(BorderStyle.THIN);
        headerStyle.setBorderTop(BorderStyle.THIN);
        headerStyle.setBorderLeft(BorderStyle.THIN);
        headerStyle.setBorderRight(BorderStyle.THIN);
        headerStyle.setFont(font);

        CellStyle titleStyle = workbook.createCellStyle();
        titleStyle.setFont(font);

        CellStyle dateCellStyle = workbook.createCellStyle();
        CreationHelper createHelper = workbook.getCreationHelper();
        dateCellStyle.setDataFormat(createHelper.createDataFormat().getFormat("dd/MM/yyyy"));
        dateCellStyle.setBorderBottom(BorderStyle.THIN);
        dateCellStyle.setBorderTop(BorderStyle.THIN);
        dateCellStyle.setBorderLeft(BorderStyle.THIN);
        dateCellStyle.setBorderRight(BorderStyle.THIN);
        dateCellStyle.setFont(fontContent);

        CellStyle contentCellStyle = workbook.createCellStyle();
        contentCellStyle.setBorderBottom(BorderStyle.THIN);
        contentCellStyle.setBorderTop(BorderStyle.THIN);
        contentCellStyle.setBorderLeft(BorderStyle.THIN);
        contentCellStyle.setBorderRight(BorderStyle.THIN);
        contentCellStyle.setFont(fontContent);

        CellStyle totalCellStyle = workbook.createCellStyle();
        totalCellStyle.setBorderBottom(BorderStyle.THIN);
        totalCellStyle.setBorderTop(BorderStyle.THIN);
        totalCellStyle.setBorderLeft(BorderStyle.THIN);
        totalCellStyle.setBorderRight(BorderStyle.THIN);
        totalCellStyle.setAlignment(HorizontalAlignment.RIGHT);
        totalCellStyle.setFont(fontTotal);

        CellStyle noDataCellStyle = workbook.createCellStyle();
        noDataCellStyle.setBorderBottom(BorderStyle.THIN);
        noDataCellStyle.setBorderTop(BorderStyle.THIN);
        noDataCellStyle.setBorderLeft(BorderStyle.THIN);
        noDataCellStyle.setBorderRight(BorderStyle.THIN);
        noDataCellStyle.setAlignment(HorizontalAlignment.CENTER);
        noDataCellStyle.setFont(fontTotal);

        int rowCount = 0;
        createHorizontalCellRange(sheet, sheet.createRow(rowCount++), String.format("Centro de Salud Sócrates Flores"), 0, 3, false, titleStyle);
        createHorizontalCellRange(sheet, sheet.createRow(rowCount++), String.format("Informe de seguimiento de cartas de consentimiento"), 0, 3, false, titleStyle);
        if (fechaInicio.equalsIgnoreCase(fechaFin))
            createHorizontalCellRange(sheet, sheet.createRow(rowCount++), String.format("Fecha: %s", fechaInicio), 0, 3, false, titleStyle);
        else
            createHorizontalCellRange(sheet, sheet.createRow(rowCount++), String.format("Desde: %s Hasta: %s", fechaInicio, fechaFin), 0, 3, false, titleStyle);

        rowCount+=2;

        createHorizontalCellRange(sheet, sheet.createRow(rowCount++), "1) Cartas registradas por estudio", 0, 1, false, titleStyle);
        String[] headers = new String[]{
                "Estudio",
                "Total_Estudio"
        };
        setTableHeader(sheet.createRow(rowCount++), headerStyle, headers);
        if (porEstudioDtoList.size()>0) {
            for (InformacionPorEstudioDto registro : porEstudioDtoList) {
                HSSFRow dataRow = sheet.createRow(rowCount++);
                if (registro.getEstudio() != null && !registro.getEstudio().isEmpty()) {
                    setCellData(dataRow, registro.getEstudio(), 0, false, contentCellStyle, dateCellStyle);
                    setCellData(dataRow, registro.getTotal(), 1, false, contentCellStyle, dateCellStyle);
                } else {
                    setCellData(dataRow, "Total", 0, false, totalCellStyle, dateCellStyle);
                    setCellData(dataRow, registro.getTotal(), 1, false, totalCellStyle, dateCellStyle);
                }
            }
        }else{
            setNoDataRow(sheet, headers.length, rowCount++, noDataCellStyle);
        }
        rowCount+=2;

        createHorizontalCellRange(sheet, sheet.createRow(rowCount++), "2) Cartas registradas por dia", 0, 1, false, titleStyle);
        String[] headers2 = new String[]{
                "Dia",
                "Total_Dia"
        };
        setTableHeader(sheet.createRow(rowCount++), headerStyle, headers2);
        if (porDiaDtoList.size()>0) {
            for (InformacionPorDiaDto registro : porDiaDtoList) {
                HSSFRow dataRow = sheet.createRow(rowCount++);
                if (registro.getDia() != null) {
                    setCellData(dataRow, registro.getDia(), 0, false, contentCellStyle, dateCellStyle);
                    setCellData(dataRow, registro.getTotal(), 1, false, contentCellStyle, dateCellStyle);
                } else {
                    setCellData(dataRow, "Total", 0, false, totalCellStyle, dateCellStyle);
                    setCellData(dataRow, registro.getTotal(), 1, false, totalCellStyle, dateCellStyle);
                }
            }
        }else{
            setNoDataRow(sheet, headers2.length, rowCount++, noDataCellStyle);
        }

        for(int i =0;i<headers2.length;i++){
            sheet.autoSizeColumn(i);
        }

        rowCount+=2;

        createHorizontalCellRange(sheet, sheet.createRow(rowCount++), "3) Cartas registradas por barrio", 0, 3, false, titleStyle);
        String[] headers3 = new String[]{
                "Barrio",
                "Estudio",
                "Version_Carta",
                "Total_Barrio"
        };
        setTableHeader(sheet.createRow(rowCount++), headerStyle, headers3);
        if (porBarrioDtoList.size()>0) {
            int indicePrimeraFila = rowCount+1;
            for (InformacionPorBarrioDto registro : porBarrioDtoList) {
                HSSFRow dataRow = sheet.createRow(rowCount++);
                setCellData(dataRow, registro.getBarrio(), 0, false, contentCellStyle, dateCellStyle);
                setCellData(dataRow, registro.getEstudio(), 1, false, contentCellStyle, dateCellStyle);
                setCellData(dataRow, registro.getVersion(), 2, false, contentCellStyle, dateCellStyle);
                setCellData(dataRow, registro.getTotal(), 3, false, contentCellStyle, dateCellStyle);
            }
            HSSFRow dataRow = sheet.createRow(rowCount++);
            int indiceUltimafila = rowCount-1;
            String columnLetter = CellReference.convertNumToColString(3);
            String formula = "SUM("+columnLetter+indicePrimeraFila+":"+columnLetter+indiceUltimafila+")";
            createHorizontalCellRange(sheet, dataRow, "Total", 0, 2, false, totalCellStyle);
            setCellData(dataRow, formula, 3, true, totalCellStyle, dateCellStyle);
        }else{
            setNoDataRow(sheet, headers3.length, rowCount++, noDataCellStyle);
        }

        for(int i =0;i<headers3.length;i++){
            sheet.autoSizeColumn(i);
        }

        rowCount+=2;

        createHorizontalCellRange(sheet, sheet.createRow(rowCount++), "4) Cartas registradas por rango de edad", 0, 3, false, titleStyle);
        String[] headers4 = new String[]{
                "Estudio",
                "Version_Carta",
                "Grupo_Edad",
                "Total_Grupo"
        };
        setTableHeader(sheet.createRow(rowCount++), headerStyle, headers4);
        if (porRangoEdadDtoList.size()>0) {
            int indicePrimeraFila = rowCount+1;
            for (InformacionRangoEdadDto registro : porRangoEdadDtoList) {
                HSSFRow dataRow = sheet.createRow(rowCount++);
                setCellData(dataRow, registro.getEstudio(), 0, false, contentCellStyle, dateCellStyle);
                setCellData(dataRow, registro.getVersion(), 1, false, contentCellStyle, dateCellStyle);
                setCellData(dataRow, registro.getRango().replaceAll("\\d\\. ", ""), 2, false, contentCellStyle, dateCellStyle);
                setCellData(dataRow, registro.getTotal(), 3, false, contentCellStyle, dateCellStyle);
            }
            HSSFRow dataRow = sheet.createRow(rowCount++);
            int indiceUltimafila = rowCount-1;
            String columnLetter = CellReference.convertNumToColString(3);
            String formula = "SUM("+columnLetter+indicePrimeraFila+":"+columnLetter+indiceUltimafila+")";
            createHorizontalCellRange(sheet, dataRow, "Total", 0, 2, false, totalCellStyle);
            setCellData(dataRow, formula, 3, true, totalCellStyle, dateCellStyle);
        }else{
            setNoDataRow(sheet, headers4.length, rowCount++, noDataCellStyle);
        }

        for(int i =0;i<headers4.length;i++){
            sheet.autoSizeColumn(i);
        }

        rowCount+=2;

        createHorizontalCellRange(sheet, sheet.createRow(rowCount++), "5) Productividad por recurso", 0, 3, false, titleStyle);
        String[] headers5 = new String[]{
                "Nombre_Apellido",
                "Total_Recurso"
        };
        setTableHeader(sheet.createRow(rowCount++), headerStyle, headers5);
        if (porRecursoDtoList.size()>0) {
            int indicePrimeraFila = rowCount+1;
            for (InformacionRecursoDto registro : porRecursoDtoList) {
                HSSFRow dataRow = sheet.createRow(rowCount++);
                setCellData(dataRow, registro.getNombre(), 0, false, contentCellStyle, dateCellStyle);
                setCellData(dataRow, registro.getCantidad(), 1, false, contentCellStyle, dateCellStyle);
            }
            HSSFRow dataRow = sheet.createRow(rowCount++);
            int indiceUltimafila = rowCount-1;
            String columnLetter = CellReference.convertNumToColString(1);
            String formula = "SUM("+columnLetter+indicePrimeraFila+":"+columnLetter+indiceUltimafila+")";
            setCellData(dataRow, "Total", 0, false, totalCellStyle, dateCellStyle);
            setCellData(dataRow, formula, 1, true, totalCellStyle, dateCellStyle);
        }else{
            setNoDataRow(sheet, headers5.length, rowCount++, noDataCellStyle);
        }

        for(int i =0;i<headers5.length;i++){
            sheet.autoSizeColumn(i);
        }

        rowCount+=2;

        createHorizontalCellRange(sheet, sheet.createRow(rowCount++), "6) Productividad por usuario digita", 0, 3, false, titleStyle);
        String[] headers6 = new String[]{
                "Usuario",
                "Nombre_Apellido",
                "Total_Usuario"
        };
        setTableHeader(sheet.createRow(rowCount++), headerStyle, headers6);
        if (porUsuarioDtoList.size()>0) {
            int indicePrimeraFila = rowCount+1;
            for (InformacionUsuarioDto registro : porUsuarioDtoList) {
                HSSFRow dataRow = sheet.createRow(rowCount++);
                setCellData(dataRow, registro.getUsuario(), 0, false, contentCellStyle, dateCellStyle);
                setCellData(dataRow, registro.getNombre(), 1, false, contentCellStyle, dateCellStyle);
                setCellData(dataRow, registro.getCantidad(), 2, false, contentCellStyle, dateCellStyle);
            }
            HSSFRow dataRow = sheet.createRow(rowCount++);
            int indiceUltimafila = rowCount-1;
            String columnLetter = CellReference.convertNumToColString(2);
            String formula = "SUM("+columnLetter+indicePrimeraFila+":"+columnLetter+indiceUltimafila+")";
            createHorizontalCellRange(sheet, dataRow, "Total", 0, 1, false, totalCellStyle);
            setCellData(dataRow, formula, 2, true, totalCellStyle, dateCellStyle);
        }else{
            setNoDataRow(sheet, headers6.length, rowCount++, noDataCellStyle);
        }

        for(int i =0;i<headers6.length;i++){
            sheet.autoSizeColumn(i);
        }
    }

    public void buildExcelComparacionMuestrasMA(Map<String, Object> model, HSSFWorkbook workbook,HttpServletResponse response) throws IOException {

        ComparacionMuestrasDto comparacionMuestrasDto = (ComparacionMuestrasDto) model.get("datos");
        List<RecepcionBHCDto> bhcSupNoEst = comparacionMuestrasDto.getBhcSupNoEst();
        List<RecepcionBHCDto> bhcSupNoLab = comparacionMuestrasDto.getBhcSupNoLab();
        List<MuestraDto> bhcEstnoSup = comparacionMuestrasDto.getBhcEstnoSup();
        List<MuestraDto> bhcEstnoLab = comparacionMuestrasDto.getBhcEstnoLab();
        List<LabBHC> bhcLabNoSup = comparacionMuestrasDto.getBhcLabNoSup();
        List<LabBHC> bhcLabNoEst = comparacionMuestrasDto.getBhcLabNoEst();

        List<RecepcionSero> rojoSupNoEst = comparacionMuestrasDto.getRojoSupervisorNoEst();
        List<RecepcionSero> rojoSupNoLab = comparacionMuestrasDto.getRojoSupervisorNoLab();
        List<MuestraDto> rojoEstnoSup = comparacionMuestrasDto.getRojoEstacionesNoSup();
        List<MuestraDto> rojoEstnoLab = comparacionMuestrasDto.getRojoEstacionesNoLab();
        List<LabSero> rojoLabNoSup = comparacionMuestrasDto.getRojoLaboratorioNoSup();
        List<LabSero> rojoLabNoEst = comparacionMuestrasDto.getRojoLaboratorioNoEst();

        List<MuestraDto> pbmcEstnoLab = comparacionMuestrasDto.getPbmcEstnoLab();
        List<LabPbmc> pbmcLabNoEst = comparacionMuestrasDto.getPbmcLabNoEst();

        logger.log(Level.INFO, "construyendo libro de excel...");

        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        String fechaActual = dateFormat.format(new Date());
        String fileName = "diferencias_muestras_" + fechaActual + ".xls";

        response.setContentType("application/octec-stream");
        response.setHeader("Content-Disposition", "attachment; filename=" + fileName);

        Font font = workbook.createFont();
        font.setFontName("Arial");
        font.setFontHeight((short) (11 * 20));
        font.setColor(HSSFColor.BLACK.index);
        font.setBold(true);

        Font fontContent = workbook.createFont();
        fontContent.setFontName("Calibri");
        fontContent.setFontHeight((short) (11 * 20));
        fontContent.setColor(HSSFColor.BLACK.index);

        Font fontNoData = workbook.createFont();
        fontNoData.setFontName("Calibri");
        fontNoData.setFontHeight((short) (11 * 20));
        fontNoData.setColor(HSSFColor.BLACK.index);
        fontNoData.setBold(true);

        CellStyle headerStyle = workbook.createCellStyle();
        headerStyle.setFillForegroundColor(HSSFColor.GREY_25_PERCENT.index);
        headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        headerStyle.setAlignment(HorizontalAlignment.CENTER);
        headerStyle.setBorderBottom(BorderStyle.THIN);
        headerStyle.setBorderTop(BorderStyle.THIN);
        headerStyle.setBorderLeft(BorderStyle.THIN);
        headerStyle.setBorderRight(BorderStyle.THIN);
        headerStyle.setFont(font);

        CellStyle titleStyle = workbook.createCellStyle();
        titleStyle.setFont(font);

        CellStyle dateCellStyle = workbook.createCellStyle();
        CreationHelper createHelper = workbook.getCreationHelper();
        dateCellStyle.setDataFormat(createHelper.createDataFormat().getFormat("dd/MM/yyyy HH:mm:ss"));
        dateCellStyle.setBorderBottom(BorderStyle.THIN);
        dateCellStyle.setBorderTop(BorderStyle.THIN);
        dateCellStyle.setBorderLeft(BorderStyle.THIN);
        dateCellStyle.setBorderRight(BorderStyle.THIN);
        dateCellStyle.setFont(fontContent);

        CellStyle contentCellStyle = workbook.createCellStyle();
        contentCellStyle.setBorderBottom(BorderStyle.THIN);
        contentCellStyle.setBorderTop(BorderStyle.THIN);
        contentCellStyle.setBorderLeft(BorderStyle.THIN);
        contentCellStyle.setBorderRight(BorderStyle.THIN);
        contentCellStyle.setFont(fontContent);

        CellStyle noDataCellStyle = workbook.createCellStyle();
        noDataCellStyle.setBorderBottom(BorderStyle.THIN);
        noDataCellStyle.setBorderTop(BorderStyle.THIN);
        noDataCellStyle.setBorderLeft(BorderStyle.THIN);
        noDataCellStyle.setBorderRight(BorderStyle.THIN);
        noDataCellStyle.setAlignment(HorizontalAlignment.CENTER);
        noDataCellStyle.setFont(fontNoData);

        int rowCount = 0;
        HSSFSheet sheet = workbook.createSheet("Tubo BHC");

        createHorizontalCellRange(sheet, sheet.createRow(rowCount++), String.format("Centro de Salud Sócrates Flores"), 0, 5, false, titleStyle);
        createHorizontalCellRange(sheet, sheet.createRow(rowCount++), String.format("Muestreo Anual " + Constants.ANIOMUESTREO + " - Informe de diferencias de muestras (Tubos BHC)"), 0, 5, false, titleStyle);
        createHorizontalCellRange(sheet, sheet.createRow(rowCount++), String.format("Fecha: %s", fechaActual), 0, 5, false, titleStyle);

        rowCount += 2;

        createHorizontalCellRange(sheet, sheet.createRow(rowCount++), "1) Tubos BHC del supervisor que no tienen las estaciones", 0, 5, false, titleStyle);
        setTableHeader(sheet.createRow(rowCount++), headerStyle, Constants.COLUMNAS_TBL_DIF_MX_SUP);
        if (bhcSupNoEst.size() > 0) {
            for (RecepcionBHCDto registro : bhcSupNoEst) {
                HSSFRow dataRow = sheet.createRow(rowCount++);
                setCellData(dataRow, registro.getCodigo(), 0, false, contentCellStyle, dateCellStyle);
                setCellData(dataRow, registro.getFecreg(), 1, false, contentCellStyle, dateCellStyle);
                setCellData(dataRow, registro.getLugar(), 2, false, contentCellStyle, dateCellStyle);
                setCellData(dataRow, registro.getVolumen(), 3, false, contentCellStyle, dateCellStyle);
                setCellData(dataRow, registro.getObservacion(), 4, false, contentCellStyle, dateCellStyle);
                setCellData(dataRow, registro.getUsername(), 5, false, contentCellStyle, dateCellStyle);
            }
        } else {
            setNoDataRow(sheet, Constants.COLUMNAS_TBL_DIF_MX_SUP.length, rowCount++, noDataCellStyle);
        }
        for (int i = 0; i < Constants.COLUMNAS_TBL_DIF_MX_SUP.length; i++) {
            sheet.autoSizeColumn(i);
        }
        rowCount += 2;

        createHorizontalCellRange(sheet, sheet.createRow(rowCount++), "2) Tubos BHC del supervisor que no tiene el laboratorio", 0, 5, false, titleStyle);
        setTableHeader(sheet.createRow(rowCount++), headerStyle, Constants.COLUMNAS_TBL_DIF_MX_SUP);
        if (bhcSupNoLab.size() > 0) {
            for (RecepcionBHCDto registro : bhcSupNoLab) {
                HSSFRow dataRow = sheet.createRow(rowCount++);
                setCellData(dataRow, registro.getCodigo(), 0, false, contentCellStyle, dateCellStyle);
                setCellData(dataRow, registro.getFecreg(), 1, false, contentCellStyle, dateCellStyle);
                setCellData(dataRow, registro.getLugar(), 2, false, contentCellStyle, dateCellStyle);
                setCellData(dataRow, registro.getVolumen(), 3, false, contentCellStyle, dateCellStyle);
                setCellData(dataRow, registro.getObservacion(), 4, false, contentCellStyle, dateCellStyle);
                setCellData(dataRow, registro.getUsername(), 5, false, contentCellStyle, dateCellStyle);
            }
        } else {
            setNoDataRow(sheet, Constants.COLUMNAS_TBL_DIF_MX_SUP.length, rowCount++, noDataCellStyle);
        }
        for (int i = 0; i < Constants.COLUMNAS_TBL_DIF_MX_SUP.length; i++) {
            sheet.autoSizeColumn(i);
        }
        rowCount += 2;

        createHorizontalCellRange(sheet, sheet.createRow(rowCount++), "3) Tubos BHC de las estaciones que no tiene el supervisor", 0, 4, false, titleStyle);
        setTableHeader(sheet.createRow(rowCount++), headerStyle, Constants.COLUMNAS_TBL_DIF_MX_EST);
        if (bhcEstnoSup.size() > 0) {
            for (MuestraDto registro : bhcEstnoSup) {
                HSSFRow dataRow = sheet.createRow(rowCount++);
                setCellData(dataRow, registro.getCodigo(), 0, false, contentCellStyle, dateCellStyle);
                setCellData(dataRow, registro.getFechaMuestra(), 1, false, contentCellStyle, dateCellStyle);
                setCellData(dataRow, registro.getPinchazos(), 2, false, contentCellStyle, dateCellStyle);
                setCellData(dataRow, registro.getRecurso1(), 3, false, contentCellStyle, dateCellStyle);
                setCellData(dataRow, registro.getRecurso2(), 4, false, contentCellStyle, dateCellStyle);
            }
        } else {
            setNoDataRow(sheet, Constants.COLUMNAS_TBL_DIF_MX_EST.length, rowCount++, noDataCellStyle);
        }
        for (int i = 0; i < Constants.COLUMNAS_TBL_DIF_MX_EST.length; i++) {
            sheet.autoSizeColumn(i);
        }
        rowCount += 2;

        createHorizontalCellRange(sheet, sheet.createRow(rowCount++), "4) Tubos BHC de las estaciones que no tiene el laboratorio", 0, 4, false, titleStyle);
        setTableHeader(sheet.createRow(rowCount++), headerStyle, Constants.COLUMNAS_TBL_DIF_MX_EST);
        if (bhcEstnoLab.size() > 0) {
            for (MuestraDto registro : bhcEstnoLab) {
                HSSFRow dataRow = sheet.createRow(rowCount++);
                setCellData(dataRow, registro.getCodigo(), 0, false, contentCellStyle, dateCellStyle);
                setCellData(dataRow, registro.getFechaMuestra(), 1, false, contentCellStyle, dateCellStyle);
                setCellData(dataRow, registro.getPinchazos(), 2, false, contentCellStyle, dateCellStyle);
                setCellData(dataRow, registro.getRecurso1(), 3, false, contentCellStyle, dateCellStyle);
                setCellData(dataRow, registro.getRecurso2(), 4, false, contentCellStyle, dateCellStyle);
            }
        } else {
            setNoDataRow(sheet, Constants.COLUMNAS_TBL_DIF_MX_EST.length, rowCount++, noDataCellStyle);
        }
        for (int i = 0; i < Constants.COLUMNAS_TBL_DIF_MX_EST.length; i++) {
            sheet.autoSizeColumn(i);
        }
        rowCount += 2;

        createHorizontalCellRange(sheet, sheet.createRow(rowCount++), "5) Tubos BHC de laboratorio que no tiene el supervisor", 0, 4, false, titleStyle);
        setTableHeader(sheet.createRow(rowCount++), headerStyle, Constants.COLUMNAS_TBL_DIF_MX_LAB);
        if (bhcLabNoSup.size() > 0) {
            for (LabBHC registro : bhcLabNoSup) {
                HSSFRow dataRow = sheet.createRow(rowCount++);
                setCellData(dataRow, registro.getCodigo(), 0, false, contentCellStyle, dateCellStyle);
                setCellData(dataRow, registro.getFecreg(), 1, false, contentCellStyle, dateCellStyle);
                setCellData(dataRow, registro.getVolumen(), 2, false, contentCellStyle, dateCellStyle);
                setCellData(dataRow, registro.getObservacion(), 3, false, contentCellStyle, dateCellStyle);
                setCellData(dataRow, registro.getUsername(), 4, false, contentCellStyle, dateCellStyle);
            }
        } else {
            setNoDataRow(sheet, Constants.COLUMNAS_TBL_DIF_MX_LAB.length, rowCount++, noDataCellStyle);
        }
        for (int i = 0; i < Constants.COLUMNAS_TBL_DIF_MX_LAB.length; i++) {
            sheet.autoSizeColumn(i);
        }
        rowCount += 2;

        createHorizontalCellRange(sheet, sheet.createRow(rowCount++), "6) Tubos BHC de laboratorio que no tienen las estaciones", 0, 4, false, titleStyle);
        setTableHeader(sheet.createRow(rowCount++), headerStyle, Constants.COLUMNAS_TBL_DIF_MX_LAB);
        if (bhcLabNoEst.size() > 0) {
            for (LabBHC registro : bhcLabNoEst) {
                HSSFRow dataRow = sheet.createRow(rowCount++);
                setCellData(dataRow, registro.getCodigo(), 0, false, contentCellStyle, dateCellStyle);
                setCellData(dataRow, registro.getFecreg(), 1, false, contentCellStyle, dateCellStyle);
                setCellData(dataRow, registro.getVolumen(), 2, false, contentCellStyle, dateCellStyle);
                setCellData(dataRow, registro.getObservacion(), 3, false, contentCellStyle, dateCellStyle);
                setCellData(dataRow, registro.getUsername(), 4, false, contentCellStyle, dateCellStyle);
            }
        } else {
            setNoDataRow(sheet, Constants.COLUMNAS_TBL_DIF_MX_LAB.length, rowCount++, noDataCellStyle);
        }
        for (int i = 0; i < Constants.COLUMNAS_TBL_DIF_MX_LAB.length; i++) {
            sheet.autoSizeColumn(i);
        }

        sheet = workbook.createSheet("Tubo Rojo");
        rowCount = 0;
        createHorizontalCellRange(sheet, sheet.createRow(rowCount++), String.format("Centro de Salud Sócrates Flores"), 0, 5, false, titleStyle);
        createHorizontalCellRange(sheet, sheet.createRow(rowCount++), String.format("Muestreo Anual " + Constants.ANIOMUESTREO + " - Informe de diferencias de muestras (Tubos Rojos)"), 0, 5, false, titleStyle);
        createHorizontalCellRange(sheet, sheet.createRow(rowCount++), String.format("Fecha: %s", fechaActual), 0, 5, false, titleStyle);

        rowCount += 2;

        createHorizontalCellRange(sheet, sheet.createRow(rowCount++), "1) Tubos Rojos del supervisor que no tienen las estaciones", 0, 5, false, titleStyle);
        setTableHeader(sheet.createRow(rowCount++), headerStyle, Constants.COLUMNAS_TBL_DIF_MX_SUP);
        if (rojoSupNoEst.size() > 0) {
            for (RecepcionSero registro : rojoSupNoEst) {
                HSSFRow dataRow = sheet.createRow(rowCount++);
                setCellData(dataRow, registro.getCodigo(), 0, false, contentCellStyle, dateCellStyle);
                setCellData(dataRow, registro.getFecreg(), 1, false, contentCellStyle, dateCellStyle);
                setCellData(dataRow, registro.getLugar(), 2, false, contentCellStyle, dateCellStyle);
                setCellData(dataRow, registro.getVolumen(), 3, false, contentCellStyle, dateCellStyle);
                setCellData(dataRow, registro.getObservacion(), 4, false, contentCellStyle, dateCellStyle);
                setCellData(dataRow, registro.getUsername(), 5, false, contentCellStyle, dateCellStyle);
            }
        } else {
            setNoDataRow(sheet, Constants.COLUMNAS_TBL_DIF_MX_SUP.length, rowCount++, noDataCellStyle);
        }
        for (int i = 0; i < Constants.COLUMNAS_TBL_DIF_MX_SUP.length; i++) {
            sheet.autoSizeColumn(i);
        }
        rowCount += 2;

        createHorizontalCellRange(sheet, sheet.createRow(rowCount++), "2) Tubos Rojos del supervisor que no tiene el laboratorio", 0, 5, false, titleStyle);
        setTableHeader(sheet.createRow(rowCount++), headerStyle, Constants.COLUMNAS_TBL_DIF_MX_SUP);
        if (rojoSupNoLab.size() > 0) {
            for (RecepcionSero registro : rojoSupNoLab) {
                HSSFRow dataRow = sheet.createRow(rowCount++);
                setCellData(dataRow, registro.getCodigo(), 0, false, contentCellStyle, dateCellStyle);
                setCellData(dataRow, registro.getFecreg(), 1, false, contentCellStyle, dateCellStyle);
                setCellData(dataRow, registro.getLugar(), 2, false, contentCellStyle, dateCellStyle);
                setCellData(dataRow, registro.getVolumen(), 3, false, contentCellStyle, dateCellStyle);
                setCellData(dataRow, registro.getObservacion(), 4, false, contentCellStyle, dateCellStyle);
                setCellData(dataRow, registro.getUsername(), 5, false, contentCellStyle, dateCellStyle);
            }
        } else {
            setNoDataRow(sheet, Constants.COLUMNAS_TBL_DIF_MX_SUP.length, rowCount++, noDataCellStyle);
        }
        for (int i = 0; i < Constants.COLUMNAS_TBL_DIF_MX_SUP.length; i++) {
            sheet.autoSizeColumn(i);
        }
        rowCount += 2;

        createHorizontalCellRange(sheet, sheet.createRow(rowCount++), "3) Tubos Rojos de las estaciones que no tiene el supervisor", 0, 4, false, titleStyle);
        setTableHeader(sheet.createRow(rowCount++), headerStyle, Constants.COLUMNAS_TBL_DIF_MX_EST);
        if (rojoEstnoSup.size() > 0) {
            for (MuestraDto registro : rojoEstnoSup) {
                HSSFRow dataRow = sheet.createRow(rowCount++);
                setCellData(dataRow, registro.getCodigo(), 0, false, contentCellStyle, dateCellStyle);
                setCellData(dataRow, registro.getFechaMuestra(), 1, false, contentCellStyle, dateCellStyle);
                setCellData(dataRow, registro.getPinchazos(), 2, false, contentCellStyle, dateCellStyle);
                setCellData(dataRow, registro.getRecurso1(), 3, false, contentCellStyle, dateCellStyle);
                setCellData(dataRow, registro.getRecurso2(), 4, false, contentCellStyle, dateCellStyle);
            }
        } else {
            setNoDataRow(sheet, Constants.COLUMNAS_TBL_DIF_MX_EST.length, rowCount++, noDataCellStyle);
        }
        for (int i = 0; i < Constants.COLUMNAS_TBL_DIF_MX_EST.length; i++) {
            sheet.autoSizeColumn(i);
        }
        rowCount += 2;

        createHorizontalCellRange(sheet, sheet.createRow(rowCount++), "4) Tubos Rojos de las estaciones que no tiene el laboratorio", 0, 4, false, titleStyle);
        setTableHeader(sheet.createRow(rowCount++), headerStyle, Constants.COLUMNAS_TBL_DIF_MX_EST);
        if (rojoEstnoLab.size() > 0) {
            for (MuestraDto registro : rojoEstnoLab) {
                HSSFRow dataRow = sheet.createRow(rowCount++);
                setCellData(dataRow, registro.getCodigo(), 0, false, contentCellStyle, dateCellStyle);
                setCellData(dataRow, registro.getFechaMuestra(), 1, false, contentCellStyle, dateCellStyle);
                setCellData(dataRow, registro.getPinchazos(), 2, false, contentCellStyle, dateCellStyle);
                setCellData(dataRow, registro.getRecurso1(), 3, false, contentCellStyle, dateCellStyle);
                setCellData(dataRow, registro.getRecurso2(), 4, false, contentCellStyle, dateCellStyle);
            }
        } else {
            setNoDataRow(sheet, Constants.COLUMNAS_TBL_DIF_MX_EST.length, rowCount++, noDataCellStyle);
        }
        for (int i = 0; i < Constants.COLUMNAS_TBL_DIF_MX_EST.length; i++) {
            sheet.autoSizeColumn(i);
        }
        rowCount += 2;

        createHorizontalCellRange(sheet, sheet.createRow(rowCount++), "5) Tubos Rojos de laboratorio que no tiene el supervisor", 0, 4, false, titleStyle);
        setTableHeader(sheet.createRow(rowCount++), headerStyle, Constants.COLUMNAS_TBL_DIF_MX_LAB);
        if (rojoLabNoSup.size() > 0) {
            for (LabSero registro : rojoLabNoSup) {
                HSSFRow dataRow = sheet.createRow(rowCount++);
                setCellData(dataRow, registro.getCodigo(), 0, false, contentCellStyle, dateCellStyle);
                setCellData(dataRow, registro.getFecreg(), 1, false, contentCellStyle, dateCellStyle);
                setCellData(dataRow, registro.getVolumen(), 2, false, contentCellStyle, dateCellStyle);
                setCellData(dataRow, registro.getObservacion(), 3, false, contentCellStyle, dateCellStyle);
                setCellData(dataRow, registro.getUsername(), 4, false, contentCellStyle, dateCellStyle);
            }
        } else {
            setNoDataRow(sheet, Constants.COLUMNAS_TBL_DIF_MX_LAB.length, rowCount++, noDataCellStyle);
        }
        for (int i = 0; i < Constants.COLUMNAS_TBL_DIF_MX_LAB.length; i++) {
            sheet.autoSizeColumn(i);
        }
        rowCount += 2;

        createHorizontalCellRange(sheet, sheet.createRow(rowCount++), "6) Tubos Rojos de laboratorio que no tienen las estaciones", 0, 4, false, titleStyle);
        setTableHeader(sheet.createRow(rowCount++), headerStyle, Constants.COLUMNAS_TBL_DIF_MX_LAB);
        if (rojoLabNoEst.size() > 0) {
            for (LabSero registro : rojoLabNoEst) {
                HSSFRow dataRow = sheet.createRow(rowCount++);
                setCellData(dataRow, registro.getCodigo(), 0, false, contentCellStyle, dateCellStyle);
                setCellData(dataRow, registro.getFecreg(), 1, false, contentCellStyle, dateCellStyle);
                setCellData(dataRow, registro.getVolumen(), 2, false, contentCellStyle, dateCellStyle);
                setCellData(dataRow, registro.getObservacion(), 3, false, contentCellStyle, dateCellStyle);
                setCellData(dataRow, registro.getUsername(), 4, false, contentCellStyle, dateCellStyle);
            }
        } else {
            setNoDataRow(sheet, Constants.COLUMNAS_TBL_DIF_MX_LAB.length, rowCount++, noDataCellStyle);
        }
        for (int i = 0; i < Constants.COLUMNAS_TBL_DIF_MX_LAB.length; i++) {
            sheet.autoSizeColumn(i);
        }

        sheet = workbook.createSheet("Tubo PBMC");
        rowCount = 0;
        createHorizontalCellRange(sheet, sheet.createRow(rowCount++), String.format("Centro de Salud Sócrates Flores"), 0, 5, false, titleStyle);
        createHorizontalCellRange(sheet, sheet.createRow(rowCount++), String.format("Muestreo Anual " + Constants.ANIOMUESTREO + " - Informe de diferencias de muestras (Tubos PBMC)"), 0, 5, false, titleStyle);
        createHorizontalCellRange(sheet, sheet.createRow(rowCount++), String.format("Fecha: %s", fechaActual), 0, 5, false, titleStyle);

        rowCount += 2;

        createHorizontalCellRange(sheet, sheet.createRow(rowCount++), "1) Tubos PBMC de laboratorio que no tienen las estaciones", 0, 5, false, titleStyle);
        setTableHeader(sheet.createRow(rowCount++), headerStyle, Constants.COLUMNAS_TBL_DIF_MX_LAB);
        if (pbmcLabNoEst.size() > 0) {
            for (LabPbmc registro : pbmcLabNoEst) {
                HSSFRow dataRow = sheet.createRow(rowCount++);
                setCellData(dataRow, registro.getCodigo(), 0, false, contentCellStyle, dateCellStyle);
                setCellData(dataRow, registro.getFecreg(), 1, false, contentCellStyle, dateCellStyle);
                setCellData(dataRow, registro.getVolumen(), 2, false, contentCellStyle, dateCellStyle);
                setCellData(dataRow, registro.getObservacion(), 3, false, contentCellStyle, dateCellStyle);
                setCellData(dataRow, registro.getUsername(), 4, false, contentCellStyle, dateCellStyle);
            }
        } else {
            setNoDataRow(sheet, Constants.COLUMNAS_TBL_DIF_MX_LAB.length, rowCount++, noDataCellStyle);
        }
        for (int i = 0; i < Constants.COLUMNAS_TBL_DIF_MX_LAB.length; i++) {
            sheet.autoSizeColumn(i);
        }
        rowCount += 2;

        createHorizontalCellRange(sheet, sheet.createRow(rowCount++), "2) Tubos PBMC de las estaciones que no tiene el laboratorio", 0, 5, false, titleStyle);
        setTableHeader(sheet.createRow(rowCount++), headerStyle, Constants.COLUMNAS_TBL_DIF_MX_EST);
        if (pbmcEstnoLab.size() > 0) {
            for (MuestraDto registro : pbmcEstnoLab) {
                HSSFRow dataRow = sheet.createRow(rowCount++);
                setCellData(dataRow, registro.getCodigo(), 0, false, contentCellStyle, dateCellStyle);
                setCellData(dataRow, registro.getFechaMuestra(), 1, false, contentCellStyle, dateCellStyle);
                setCellData(dataRow, registro.getPinchazos(), 2, false, contentCellStyle, dateCellStyle);
                setCellData(dataRow, registro.getRecurso1(), 3, false, contentCellStyle, dateCellStyle);
                setCellData(dataRow, registro.getRecurso2(), 4, false, contentCellStyle, dateCellStyle);
            }
        } else {
            setNoDataRow(sheet, Constants.COLUMNAS_TBL_DIF_MX_LAB.length, rowCount++, noDataCellStyle);
        }
        for (int i = 0; i < Constants.COLUMNAS_TBL_DIF_MX_LAB.length; i++) {
            sheet.autoSizeColumn(i);
        }
    }

    /***
     * Método para crear el encabezado de las tablas
     * @param header Fila donde se colocaran los encabezado de las columnas
     * @param style Estilo que se le aplicará al encabezado
     * @param columnas Arreglo con el texto de cada encabezado
     */
    public static void setTableHeader(HSSFRow header, CellStyle style, String[] columnas){
        int indice = 0;
        for(String columna : columnas){
            header.createCell(indice).setCellValue(columna);
            header.getCell(indice).setCellStyle(style);
            header.getSheet().autoSizeColumn(indice);
            indice++;
        }

    }

    /***
     * Método para crear el encabezado de las tablas
     * @param header Fila donde se colocaran los encabezado de las columnas
     * @param style Estilo que se le aplicará al encabezado
     * @param columnas Lista con el texto de cada encabezado
     */
    public static void setTableHeader(HSSFRow header, CellStyle style,List<String> columnas){
        int indice = 0;
        for(String columna : columnas){
            header.createCell(indice).setCellValue(columna);
            header.getCell(indice).setCellStyle(style);
            indice++;
        }
    }

    /***
     * Metodo para agregar una celda con la etiqueta "No se encontraron datos!"
     * @param sheet Hoja en la que se creará la etiqueta
     * @param totalColumns Total de columnas que cubrirá la etiqueta
     * @param rowCount Posición de la fila en que se agregará la etiqueta
     */
    public static void setNoDataRow(HSSFSheet sheet, int totalColumns, int rowCount, CellStyle noDataCellStyle) {

        createHorizontalCellRange(sheet, sheet.createRow(rowCount), "No se encontraron datos!", 0, totalColumns - 1, false, noDataCellStyle);
    }

    /***
     * Método para crear una celda y ponerle el valor que va a contener deacuerdo al tipo de dato
     * @param aRow Fila en la que se creará la celda
     * @param dato Valor que se le asignará
     * @param indice número de la columna en la fila (recordar que la primera celda tiene posición 0)
     * @param esFormula TRUE para indicar si la celda contendrá una fórmula
     * @param contentCellStyle Estilo que se le aplicará a la celda cuándo no es fecha
     * @param dateCellStyle Estilo que se le aplicará a la celda cuándo es fecha
     */
    public static void setCellData(HSSFRow aRow, Object dato, int indice, boolean esFormula, CellStyle contentCellStyle, CellStyle dateCellStyle){
            aRow.createCell(indice);
            boolean isDate= false;
            if (dato !=null){
                if (esFormula){
                    aRow.getCell(indice).setCellFormula(dato.toString());
                    aRow.getCell(indice).setCellType(CellType.FORMULA);
                }else {
                    if (dato instanceof Date) {
                        aRow.getCell(indice).setCellValue((Date) dato);
                        isDate = true;
                    } else if (dato instanceof Integer) {
                        aRow.getCell(indice).setCellValue((Integer) dato);
                    } else if (dato instanceof Long) {
                        aRow.getCell(indice).setCellValue((Long) dato);
                    } else if (dato instanceof Float) {
                        aRow.getCell(indice).setCellValue((Float) dato);
                    } else if (dato instanceof Double) {
                        aRow.getCell(indice).setCellValue((Double) dato);
                    } else if (dato instanceof BigInteger) {
                        aRow.getCell(indice).setCellValue(((BigInteger) dato).intValue());
                    } else {
                        aRow.createCell(indice).setCellValue(dato.toString());
                    }
                }
            }
            if (!isDate)
                aRow.getCell(indice).setCellStyle(contentCellStyle);
            else
                aRow.getCell(indice).setCellStyle(dateCellStyle);

    }

    /**
     * Método para crear en orientación horizonta un rango de celdas en una hoja y ponerle el valor que va a contener deacuerdo al tipo de dato. Sobre una misma fila
     * @param sheet Hoja en la que se creará el rango de celdas combinadas
     * @param row Fila en la que se creará la celda
     * @param value Valor que se le asignará
     * @param posicionInicio número de la columna en que iniciará la combinación de celdas (recordar que la primera celda tiene posición 0)
     * @param posicionFin número de la columna en que terminará la combinación de celdas
     * @param esFormula TRUE para indicar si la celda contendrá una fórmula
     * @param style Estilo que se le aplicará a cada celda dentro del rango
     */
    public static void createHorizontalCellRange(HSSFSheet sheet, HSSFRow row, Object value, int posicionInicio, int posicionFin, boolean esFormula, CellStyle style){
        sheet.addMergedRegion(new CellRangeAddress(row.getRowNum(), row.getRowNum(), posicionInicio, posicionFin));
        setCellData(row, value, posicionInicio, esFormula, style, null);
        //inicializando resto de celdas contenidas en el merge
        for (int i = posicionInicio+1; i <= posicionFin; i++){
            row.createCell(i);
            row.getCell(i).setCellStyle(style);
        }
    }
}//fin