package ni.org.ics.estudios.web.utils.Excel;

import ni.org.ics.estudios.domain.SerologiaOct2020.Serologia_Detalle_Envio;
import ni.org.ics.estudios.web.utils.pdf.Constants;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.springframework.web.servlet.view.document.AbstractExcelView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
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



}