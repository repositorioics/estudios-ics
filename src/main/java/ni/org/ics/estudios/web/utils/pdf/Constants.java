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
    public static final String TPR_REPORTERETIRO = "RETIROREPORTE";
    public static final String TPR_INFOCARTAS = "INFOCARTAS";
    public static final int ANIOMUESTREO = Calendar.getInstance().get(Calendar.YEAR);
    public static final String TPR_COMPARACION_MX_MA = "COMPARACION_MX_MA";
    public static final String TPR_ENTO = "ENTOMOLOGIA";

    public static final String[] COLUMNAS_TBL_DIF_MX_SUP = new String[]{"Código", "Fecha y hora de toma", "Lugar", "Volumen", "Observación", "Supervisor"};
    public static final String[] COLUMNAS_TBL_DIF_MX_EST = new String[]{"Código", "Fecha y hora de toma", "Pinchazos", "Encuestador", "Laboratorista"};
    public static final String[] COLUMNAS_TBL_DIF_MX_LAB = new String[]{"Código", "Fecha y hora de toma", "Volumen", "Observación", "Laboratorista"};

    public static final String[] ENTO_COLUMNAS_TBL_CUEST_HOGAR = new String[]{"codigo_casa", "quien_contesta", "quien_contesta_otro", "quien_contesta_edad", "quien_contesta_escolaridad", "tiempo_vivir_barrio", "cuantas_personas_viven", "edades_femenino", "edades_masculino", "usaron_mosquitero", "quienes_usaron_mosquitero", "usaron_repelente", "quienes_usaron_repelente", "conoce_larvas", "alguien_vis_elim_larvas", "quien_vis_elim_larvas", "quien_vis_elim_larvas_otro", "alguien_dedica_elim_larvas", "quien_dedica_elim_larvas", "tiempo_eliminan_criaderos", "hay_bastante_zancudos", "falta_evitar_zancudos", "falta_evitar_zancudos_otros", "gastaron_dinero_productos", "que_productos_compraron", "que_productos_compraron_otros", "cuanto_gastaron_productos", "ultima_vez_minsa_bti", "ultima_vez_minsa_fumigo", "riesgo_enfermar_dengue_casa", "hay_problema_agua", "cada_cuanto_va_agua", "cada_cuanto_va_agua_otro", "horas_sin_agua", "que_hacen_basura", "que_hacen_basura_otro", "riesgo_enfermar_dengue_barrio", "acciones_barrio_elim_zancudos", "que_acciones", "que_acciones_otro", "alguien_participo_acciones", "quien_participo_acciones", "mayor_criadero_barrio", "usuario_registro", "fecha_registro","codigo_cuestionario"};
    public static final String[] ENTO_COLUMNAS_TBL_CUEST_HOGAR_POB = new String[]{"codigo_casa", "codificado", "edad", "sexo", "usuario_registro", "fecha_registro", "codigo_cuestionario", "codigo_poblacion"};
    public static final String[] ENTO_COLUMNAS_TBL_CUEST_PUNTO_CLAVE = new String[]{"fecha_cuestionario","codigo_barrio","nombre_punto_clave","direccion_punto_clave","tipo_punto_clave","tipo_punto_clave_prod","tipo_punto_clave_prod_otro","tipo_punto_clave_aglo","tipo_punto_clave_aglo_otro","cuantas_personas_reunen","cuantos_dias_sem_reunen","hora_inicio_reunion","hora_fin_reunion","punto_gps","latitud","longitud","tipo_ingreso_cod_sitio","codigo_sitio","hay_ambiente_peri","hora_captura_peri","porcentaje_humedad_peri","temperatura_peri","tipo_ingreso_cod_peri","codigo_peri","hay_ambiente_intra","hora_captura_intra","porcentaje_humedad_intra","temperatura_intra","tipo_ingreso_cod_intra","codigo_intra","nombre_contesta_cuestionario","usuario_registro", "fecha_registro","codigo_cuestionario"};

    public static final String TPR_ENVIOREPORTEANTICUERPO = "TPR_ENVIOREPORTEANTICUERPO";
}
