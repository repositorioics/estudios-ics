package ni.org.ics.estudios.web.utils.pdf;

import java.util.Calendar;

/**
 * Created by ICS_Inspiron3 on 26/08/2019.
 */
public class Constants {
    public static final String TPR_DATOSGENERALES = "DATOSGENERALES";
    public static final String TPR_HEMOREPORTE = "HEMOREPORTE";
    public static final String TPR_REPORTECARTA ="REPORTECARTA";
    public static final String TPR_ENVIOREPORTE = "ENVIOREPORTE";
    public static final String TPR_ENVIOREPORTEPBCM = "ENVIOREPORTEPBMC";
    public static final String TPR_ENVIOREPORTEPBCMTOEXCEL = "ENVIOREPORTEPBCMTOEXCEL";
    public static final String TPR_ENVIOREPORTEBHC = "ENVIOREPORTEBHC";
    public static final String TPR_INFOCARTAS = "INFOCARTAS";
    public static final int ANIOMUESTREO = Calendar.getInstance().get(Calendar.YEAR);
    public static final String TPR_COMPARACION_MX_MA = "COMPARACION_MX_MA";

    public static final String[] COLUMNAS_TBL_DIF_MX_SUP = new String[]{"Código", "Fecha y hora de toma", "Lugar", "Volumen", "Observación", "Supervisor"};
    public static final String[] COLUMNAS_TBL_DIF_MX_EST = new String[]{"Código", "Fecha y hora de toma", "Pinchazos", "Encuestador", "Laboratorista"};
    public static final String[] COLUMNAS_TBL_DIF_MX_LAB = new String[]{"Código", "Fecha y hora de toma", "Volumen", "Observación", "Laboratorista"};
}
