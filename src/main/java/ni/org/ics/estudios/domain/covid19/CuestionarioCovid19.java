package ni.org.ics.estudios.domain.covid19;

import ni.org.ics.estudios.domain.BaseMetaData;
import ni.org.ics.estudios.domain.Participante;
import ni.org.ics.estudios.domain.audit.Auditable;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.hibernate.annotations.ForeignKey;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by miguel on 10/9/2020.
 */
@Entity
@Table(name = "covid_cuestionario", catalog = "estudios_ics")
public class CuestionarioCovid19 extends BaseMetaData implements Auditable {

    /*1.¿Desde febrero de 2020 ha tenido usted o su niño/a alguno de los siguientes síntomas?*/
    private String codigo;
    private Participante participante;
    //1. ¿Desde febrero 2021 a usted o su niño/a le han diagnosticado o ha estado enfermo con la COVID19?
    private String enfermoCovid19;
    //¿Cuantas veces ha estado enfermo en este periodo?
    private String cuantasVecesEnfermo;
    private String sabeEvento1;
    private Date evento1;
    private String anioEvento1;
    private String mesEvento1;
    private String sabeEvento2;
    private Date evento2;
    private String anioEvento2;
    private String mesEvento2;
    private String sabeEvento3;
    private Date evento3;
    private String anioEvento3;
    private String mesEvento3;
    //evento1
    private String e1Febricula;
    private String e1Fiebre;
    private String e1Escalofrio;
    private String e1TemblorEscalofrio;
    private String e1DolorMuscular;
    private String e1DolorArticular;
    private String e1SecresionNasal;
    private String e1DolorGarganta;
    private String e1Tos;
    private String e1DificultadResp;
    private String e1DolorPecho;
    private String e1NauseasVomito;
    private String e1DolorCabeza;
    private String e1DolorAbdominal;
    private String e1Diarrea;
    private String e1DificultadDormir;
    private String e1Debilidad;
    private String e1PerdidaOlfatoGusto;
    private String e1Mareo;
    private String e1Sarpullido;
    private String e1Desmayo;
    private String e1QuedoCama;
    /*2.	[Si uno o mas de los síntomas en la sección] Que fecha exacta o aproximada empezaron estos síntomas*/
    private String e1SabeFIS;
    private Date e1Fis;
    private String e1MesInicioSintoma;
    private String e1AnioInicioSintoma;
    /*3.	¿Usted cree que en el año 2020 que usted o su niño/a pudo haber padecido de COVID-19 o su medico le informo que padeció de COVID-19?*/
    //private String padecidoCovid19;
    /*4.	¿Conoce en donde usted o su niño/a estuvieron expuestos a COVID-19?*/
    private String e1ConoceLugarExposicion;
    private String e1LugarExposicion;
    /*5.	¿Busco ayuda medica?*/
    private String e1BuscoAyuda;
    /*6.	[Si P5==”Si”] Donde busco ayuda médica?*/
    private String e1DondeBuscoAyuda;
    private String e1NombreCentroSalud;
    private String e1NombreHospital;
    private String e1RecibioSeguimiento;
    private String e1TipoSeguimiento;
    /*7.	[Si P5==”Si”] Cuanto tiempo después de que iniciaron los síntomas de COVID-19 busco ayuda medica? */
    private String e1TmpDespuesBuscoAyuda;
    /*8.	[Si P5==”Si”] Paso al menos una noche en el hospital por COVID-19?*/
    private String e1UnaNocheHospital;
    /*Si P7==”Si”] A que hospital acudió?  */
    private String e1QueHospital;
    /*10.	[Si P7 == “Si”] Cuantas noches estuvo en el hospital? */
    private String e1SabeCuantasNoches;
    private Integer e1CuantasNochesHosp;
    /*  11.	[Si P7== “Si”] Cual fue su fecha de admisión al hospital? */
    private String e1SabeFechaAdmision;
    private Date e1FechaAdmisionHosp;
    /*12.	 [Si P7 == “Si”] Cual fue su fecha de alta medica? */
    private String e1SabeFechaAlta;
    private Date e1FechaAltaHosp;
    /*13.	[S    i P7==”Si”] Mientras estuvo en el hospital, requirió oxigeno?*/
    private String e1UtilizoOxigeno;
    /*14.	[Si P7==”Si”] Mientras usted o su niño/a estuvo en el hospital, en algún momento requirió de Unidad de Cuidados Intensivos?*/
    private String e1EstuvoUCI;
    private String e1FueIntubado;
    /*  15.	[Si la P3 == “Si”] Se ha recuperado del COVID-19 y posee ahora un estado de salud normal o igual al que tenia antes de enfermedad?*/
    private String e1RecuperadoCovid19;
    /*16.	[Si P15 == “No” o “No Sabe/No esta seguro”/no contesto] ¿Usted o su niño/a que síntomas presenta aun?*/
    private String e1TieneFebricula;
    private String e1TieneCansancio;
    private String e1TieneDolorCabeza;
    private String e1TienePerdidaOlfato;
    private String e1TienePerdidaGusto;
    private String e1TieneTos;
    private String e1TieneDificultadRespirar;
    private String e1TieneDolorPecho;
    private String e1TienePalpitaciones;
    private String e1TieneDolorArticulaciones;
    private String e1TieneParalisis;
    private String e1TieneMareos;
    private String e1TienePensamientoNublado;
    private String e1TieneProblemasDormir;
    private String e1TieneDepresion;
    private String e1TieneOtrosSintomas;
    private String e1cualesSintomas;
    /*  17.	[Si P3 == “Si”] ¿ A usted o su niño/a cuanto tiempo le tomo en recuperarse de los síntomas secundarios a COVID-19 y recobrar su salud normal?*/
    private String e1SabeTiempoRecuperacion;
    private String e1TiempoRecuperacion;
    private String e1TiempoRecuperacionEn;
    /*18.	[Si P3==”Si”] En el momento más severo de su enfermedad, diría que su enfermedad o la de su niño/a fue?*/
    private String e1SeveridadEnfermedad;
    /*  19.	[Si P3==”Si”] Que medicamentos tomo para COVID-19*/
    private String e1TomoMedicamento;
    private String e1QueMedicamento;
    private String e1OtroMedicamento;
    //20. ¿A Ud. o su niño/a le administraron oxígeno en su domicilio?
    private String e1OxigenoDomicilio;
    //20. 1 ¿Por cuanto tiempo le administraron?
    private String e1TiempoOxigenoDom;
    // evento2
    private String e2Febricula;
    private String e2Fiebre;
    private String e2Escalofrio;
    private String e2TemblorEscalofrio;
    private String e2DolorMuscular;
    private String e2DolorArticular;
    private String e2SecresionNasal;
    private String e2DolorGarganta;
    private String e2Tos;
    private String e2DificultadResp;
    private String e2DolorPecho;
    private String e2NauseasVomito;
    private String e2DolorCabeza;
    private String e2DolorAbdominal;
    private String e2Diarrea;
    private String e2DificultadDormir;
    private String e2Debilidad;
    private String e2PerdidaOlfatoGusto;
    private String e2Mareo;
    private String e2Sarpullido;
    private String e2Desmayo;
    private String e2QuedoCama;
    /*2.	[Si uno o mas de los síntomas en la sección] Que fecha exacta o aproximada empezaron estos síntomas*/
    private String e2SabeFIS;
    private Date e2Fis;
    private String e2MesInicioSintoma;
    private String e2AnioInicioSintoma;
    /*3.	¿Usted cree que en el año 2020 que usted o su niño/a pudo haber padecido de COVID-19 o su medico le informo que padeció de COVID-19?*/
    /*4.	¿Conoce en donde usted o su niño/a estuvieron expuestos a COVID-19?*/
    private String e2ConoceLugarExposicion;
    private String e2LugarExposicion;
    /*5.	¿Busco ayuda medica?*/
    private String e2BuscoAyuda;
    /*6.	[Si P5==”Si”] Donde busco ayuda médica?*/
    private String e2DondeBuscoAyuda;
    private String e2NombreCentroSalud;
    private String e2NombreHospital;
    private String e2RecibioSeguimiento;
    private String e2TipoSeguimiento;
    /*7.	[Si P5==”Si”] Cuanto tiempo después de que iniciaron los síntomas de COVID-19 busco ayuda medica? */
    private String e2TmpDespuesBuscoAyuda;
    /*8.	[Si P5==”Si”] Paso al menos una noche en el hospital por COVID-19?*/
    private String e2UnaNocheHospital;
    /*Si P7==”Si”] A que hospital acudió?  */
    private String e2QueHospital;
    /*10.	[Si P7 == “Si”] Cuantas noches estuvo en el hospital? */
    private String e2SabeCuantasNoches;
    private Integer e2CuantasNochesHosp;
    /*  11.	[Si P7== “Si”] Cual fue su fecha de admisión al hospital? */
    private String e2SabeFechaAdmision;
    private Date e2FechaAdmisionHosp;
    /*12.	 [Si P7 == “Si”] Cual fue su fecha de alta medica? */
    private String e2SabeFechaAlta;
    private Date e2FechaAltaHosp;
    /*13.	[S    i P7==”Si”] Mientras estuvo en el hospital, requirió oxigeno?*/
    private String e2UtilizoOxigeno;
    /*14.	[Si P7==”Si”] Mientras usted o su niño/a estuvo en el hospital, en algún momento requirió de Unidad de Cuidados Intensivos?*/
    private String e2EstuvoUCI;
    private String e2FueIntubado;
    /*  15.	[Si la P3 == “Si”] Se ha recuperado del COVID-19 y posee ahora un estado de salud normal o igual al que tenia antes de enfermedad?*/
    private String e2RecuperadoCovid19;
    /*16.	[Si P15 == “No” o “No Sabe/No esta seguro”/no contesto] ¿Usted o su niño/a que síntomas presenta aun?*/
    private String e2TieneFebricula;
    private String e2TieneCansancio;
    private String e2TieneDolorCabeza;
    private String e2TienePerdidaOlfato;
    private String e2TienePerdidaGusto;
    private String e2TieneTos;
    private String e2TieneDificultadRespirar;
    private String e2TieneDolorPecho;
    private String e2TienePalpitaciones;
    private String e2TieneDolorArticulaciones;
    private String e2TieneParalisis;
    private String e2TieneMareos;
    private String e2TienePensamientoNublado;
    private String e2TieneProblemasDormir;
    private String e2TieneDepresion;
    private String e2TieneOtrosSintomas;
    private String e2cualesSintomas;
    /*  17.	[Si P3 == “Si”] ¿ A usted o su niño/a cuanto tiempo le tomo en recuperarse de los síntomas secundarios a COVID-19 y recobrar su salud normal?*/
    private String e2SabeTiempoRecuperacion;
    private String e2TiempoRecuperacion;
    private String e2TiempoRecuperacionEn;
    /*18.	[Si P3==”Si”] En el momento más severo de su enfermedad, diría que su enfermedad o la de su niño/a fue?*/
    private String e2SeveridadEnfermedad;
    /*  19.	[Si P3==”Si”] Que medicamentos tomo para COVID-19*/
    private String e2TomoMedicamento;
    private String e2QueMedicamento;
    private String e2OtroMedicamento;
    //20. ¿A Ud. o su niño/a le administraron oxígeno en su domicilio?
    private String e2OxigenoDomicilio;
    //20. 1 ¿Por cuanto tiempo le administraron?
    private String e2TiempoOxigenoDom;
    //evento3
    private String e3Febricula;
    private String e3Fiebre;
    private String e3Escalofrio;
    private String e3TemblorEscalofrio;
    private String e3DolorMuscular;
    private String e3DolorArticular;
    private String e3SecresionNasal;
    private String e3DolorGarganta;
    private String e3Tos;
    private String e3DificultadResp;
    private String e3DolorPecho;
    private String e3NauseasVomito;
    private String e3DolorCabeza;
    private String e3DolorAbdominal;
    private String e3Diarrea;
    private String e3DificultadDormir;
    private String e3Debilidad;
    private String e3PerdidaOlfatoGusto;
    private String e3Mareo;
    private String e3Sarpullido;
    private String e3Desmayo;
    private String e3QuedoCama;
    /*2.	[Si uno o mas de los síntomas en la sección] Que fecha exacta o aproximada empezaron estos síntomas*/
    private String e3SabeFIS;
    private Date e3Fis;
    private String e3MesInicioSintoma;
    private String e3AnioInicioSintoma;
    /*3.	¿Usted cree que en el año 2020 que usted o su niño/a pudo haber padecido de COVID-19 o su medico le informo que padeció de COVID-19?*/
    /*4.	¿Conoce en donde usted o su niño/a estuvieron expuestos a COVID-19?*/
    private String e3ConoceLugarExposicion;
    private String e3LugarExposicion;
    /*5.	¿Busco ayuda medica?*/
    private String e3BuscoAyuda;
    /*6.	[Si P5==”Si”] Donde busco ayuda médica?*/
    private String e3DondeBuscoAyuda;
    private String e3NombreCentroSalud;
    private String e3NombreHospital;
    private String e3RecibioSeguimiento;
    private String e3TipoSeguimiento;
    /*7.	[Si P5==”Si”] Cuanto tiempo después de que iniciaron los síntomas de COVID-19 busco ayuda medica? */
    private String e3TmpDespuesBuscoAyuda;
    /*8.	[Si P5==”Si”] Paso al menos una noche en el hospital por COVID-19?*/
    private String e3UnaNocheHospital;
    /*Si P7==”Si”] A que hospital acudió?  */
    private String e3QueHospital;
    /*10.	[Si P7 == “Si”] Cuantas noches estuvo en el hospital? */
    private String e3SabeCuantasNoches;
    private Integer e3CuantasNochesHosp;
    /*  11.	[Si P7== “Si”] Cual fue su fecha de admisión al hospital? */
    private String e3SabeFechaAdmision;
    private Date e3FechaAdmisionHosp;
    /*12.	 [Si P7 == “Si”] Cual fue su fecha de alta medica? */
    private String e3SabeFechaAlta;
    private Date e3FechaAltaHosp;
    /*13.	[S    i P7==”Si”] Mientras estuvo en el hospital, requirió oxigeno?*/
    private String e3UtilizoOxigeno;
    /*14.	[Si P7==”Si”] Mientras usted o su niño/a estuvo en el hospital, en algún momento requirió de Unidad de Cuidados Intensivos?*/
    private String e3EstuvoUCI;
    private String e3FueIntubado;
    /*  15.	[Si la P3 == “Si”] Se ha recuperado del COVID-19 y posee ahora un estado de salud normal o igual al que tenia antes de enfermedad?*/
    private String e3RecuperadoCovid19;
    /*16.	[Si P15 == “No” o “No Sabe/No esta seguro”/no contesto] ¿Usted o su niño/a que síntomas presenta aun?*/
    private String e3TieneFebricula;
    private String e3TieneCansancio;
    private String e3TieneDolorCabeza;
    private String e3TienePerdidaOlfato;
    private String e3TienePerdidaGusto;
    private String e3TieneTos;
    private String e3TieneDificultadRespirar;
    private String e3TieneDolorPecho;
    private String e3TienePalpitaciones;
    private String e3TieneDolorArticulaciones;
    private String e3TieneParalisis;
    private String e3TieneMareos;
    private String e3TienePensamientoNublado;
    private String e3TieneProblemasDormir;
    private String e3TieneDepresion;
    private String e3TieneOtrosSintomas;
    private String e3cualesSintomas;
    /*  17.	[Si P3 == “Si”] ¿ A usted o su niño/a cuanto tiempo le tomo en recuperarse de los síntomas secundarios a COVID-19 y recobrar su salud normal?*/
    private String e3SabeTiempoRecuperacion;
    private String e3TiempoRecuperacion;
    private String e3TiempoRecuperacionEn;
    /*18.	[Si P3==”Si”] En el momento más severo de su enfermedad, diría que su enfermedad o la de su niño/a fue?*/
    private String e3SeveridadEnfermedad;
    /*  19.	[Si P3==”Si”] Que medicamentos tomo para COVID-19*/
    private String e3TomoMedicamento;
    private String e3QueMedicamento;
    private String e3OtroMedicamento;
    //20. ¿A Ud. o su niño/a le administraron oxígeno en su domicilio?
    private String e3OxigenoDomicilio;
    //20. 1 ¿Por cuanto tiempo le administraron?
    private String e3TiempoOxigenoDom;

    /*21.	Algún medico u otro profesional de la salud le ha dicho alguna vez que usted o su niño/a padece alguna de estas condiciones?*/
    private String padeceEnfisema;
    private String padeceAsma;
    private String padeceDiabetes;
    private String padeceEnfCoronaria;
    private String padecePresionAlta;
    private String padeceEnfHigado;
    private String padeceEnfRenal;
    private String padeceInfartoDerrameCer;
    private String padeceCancer;
    private String padeceCondicionInmuno;
    private String padeceEnfAutoinmune;
    private String padeceDiscapacidadFis;
    private String padeceCondPsicPsiq;
    private String padeceOtraCondicion;
    private String queOtraCondicion;
    /*  22.	¿Ha fumado alguna vez en su vida? . para mayores 14 anios*/
    private String fumado;
    /*  23.	¿Ha fumado al menos 100 cigarrillos en su vida?*/
    private String fumadoCienCigarrillos;

    //evento1
    /*  24.	[Si P3==”Si”] ¿En los días previos a que se enfermara, usted fumaba cigarrillos todos los días o algunos días previo a que se enfermara? */
    private String e1FumadoPrevioEnfermedad;
    /*25.	¿Usted fumaba cigarrillos todos los días o algunos días ahora? */
    private String e1FumaActualmente;
    /*26	[Si P3==Si] Y si es mujer ¿Usted estaba embarazada al momento de ser diagnosticada o sospechosa de COVID19?*/
    private String e1Embarazada;
    /*Si es Si, Recuerda las semanas de embarazo que tenia*/
    private String e1RecuerdaSemanasEmb;
    private Integer e1SemanasEmbarazo;
    /*Si es Si, como finalizo el embarzo*/
    private String e1FinalEmbarazo;
    private String e1OtroFinalEmbarazo;
    /*27.  [Si P3==Si] Si estaba embarazada recientemente al momento de ser diagnosticada con COVID19, ¿Usted estaba dando pecho materno?*/
    private String e1DabaPecho;
    //evento2
        /*  24.	[Si P3==”Si”] ¿En los días previos a que se enfermara, usted fumaba cigarrillos todos los días o algunos días previo a que se enfermara? */
    private String e2FumadoPrevioEnfermedad;
    /*25.	¿Usted fumaba cigarrillos todos los días o algunos días ahora? */
    private String e2FumaActualmente;
    /*26	[Si P3==Si] Y si es mujer ¿Usted estaba embarazada al momento de ser diagnosticada o sospechosa de COVID19?*/
    private String e2Embarazada;
    /*Si es Si, Recuerda las semanas de embarazo que tenia*/
    private String e2RecuerdaSemanasEmb;
    private Integer e2SemanasEmbarazo;
    /*Si es Si, como finalizo el embarzo*/
    private String e2FinalEmbarazo;
    private String e2OtroFinalEmbarazo;
    /*27.  [Si P3==Si] Si estaba embarazada recientemente al momento de ser diagnosticada con COVID19, ¿Usted estaba dando pecho materno?*/
    private String e2DabaPecho;
    //evento3
        /*  24.	[Si P3==”Si”] ¿En los días previos a que se enfermara, usted fumaba cigarrillos todos los días o algunos días previo a que se enfermara? */
    private String e3FumadoPrevioEnfermedad;
    /*25.	¿Usted fumaba cigarrillos todos los días o algunos días ahora? */
    private String e3FumaActualmente;
    /*26	[Si P3==Si] Y si es mujer ¿Usted estaba embarazada al momento de ser diagnosticada o sospechosa de COVID19?*/
    private String e3Embarazada;
    /*Si es Si, Recuerda las semanas de embarazo que tenia*/
    private String e3RecuerdaSemanasEmb;
    private Integer e3SemanasEmbarazo;
    /*Si es Si, como finalizo el embarzo*/
    private String e3FinalEmbarazo;
    private String e3OtroFinalEmbarazo;
    /*27.  [Si P3==Si] Si estaba embarazada recientemente al momento de ser diagnosticada con COVID19, ¿Usted estaba dando pecho materno?*/
    private String e3DabaPecho;

    /*28. ¿Usted estuvo empleado como trabajador de la salud desde el 1 de febrero de 2020?*/
    private String trabajadorSalud;

    //29. ¿Usted o su niño/a han sido vacunado en la jornada de vacunación voluntaria contra la COVID-19
    private String vacunadoCovid19;
    //30. ¿Puede mostrarnos su tarjeta de vacunación contra la COVID 19?
    private String muestraTarjetaVac;
    //Si no es No, Sabe cuál vacuna le fue aplicada
    private String sabeNombreVacuna;
    private String nombreVacuna;
    private String anioVacuna;
    private String mesVacuna;
    //Si es SI que vacuna está registrada (Permita el ingreso de la primera y segunda dosis)
    private String cuantasDosis;
    //Dosis1
    private String nombreDosis1;
    private String otraVacunaDosis1;
    private String loteDosis1;
    private Date fechaDosis1;
    //31. ¿Posterior a ser vacunado contra la COVID19 Ud. o su niño/a presento alguna molestia o síntoma posterior a un máximo de 72 horas a la vacunación?
    private String presentoSintomasDosis1;
    //31.1 ¿Cual o Cuales de estos síntomas presento?
    private String dolorSitioDosis1;
    private String fiebreDosis1;
    private String cansancioDosis1;
    private String dolorCabezaDosis1;
    private String diarreaDosis1;
    private String vomitoDosis1;
    private String dolorMuscularDosis1;
    private String escalofriosDosis1;
    private String reaccionAlergicaDosis1;
    private String infeccionSitioDosis1;
    private String nauseasDosis1;
    private String bultosDosis1;
    private String otrosDosis1;
    private String desOtrosDosis1;
    //Dosis2
    private String nombreDosis2;
    private String otraVacunaDosis2;
    private String loteDosis2;
    private Date fechaDosis2;
    //31. ¿Posterior a ser vacunado contra la COVID19 Ud. o su niño/a presento alguna molestia o síntoma posterior a un máximo de 72 horas a la vacunación?
    private String presentoSintomasDosis2;
    //31.1 ¿Cual o Cuales de estos síntomas presento?
    private String dolorSitioDosis2;
    private String fiebreDosis2;
    private String cansancioDosis2;
    private String dolorCabezaDosis2;
    private String diarreaDosis2;
    private String vomitoDosis2;
    private String dolorMuscularDosis2;
    private String escalofriosDosis2;
    private String reaccionAlergicaDosis2;
    private String infeccionSitioDosis2;
    private String nauseasDosis2;
    private String bultosDosis2;
    private String otrosDosis2;
    private String desOtrosDosis2;
    //Dosis3
    private String nombreDosis3;
    private String otraVacunaDosis3;
    private String loteDosis3;
    private Date fechaDosis3;
    //31. ¿Posterior a ser vacunado contra la COVID19 Ud. o su niño/a presento alguna molestia o síntoma posterior a un máximo de 72 horas a la vacunación?
    private String presentoSintomasDosis3;
    //31.1 ¿Cual o Cuales de estos síntomas presento?
    private String dolorSitioDosis3;
    private String fiebreDosis3;
    private String cansancioDosis3;
    private String dolorCabezaDosis3;
    private String diarreaDosis3;
    private String vomitoDosis3;
    private String dolorMuscularDosis3;
    private String escalofriosDosis3;
    private String reaccionAlergicaDosis3;
    private String infeccionSitioDosis3;
    private String nauseasDosis3;
    private String bultosDosis3;
    private String otrosDosis3;
    private String desOtrosDosis3;

    //32. ¿Ud. o su niño presentaron síntomas que sospecharon enfermedad por Covid-19 Posterior a haber recibido la vacuna de Covid-19?
    private String covid19PosteriorVacuna;
    private String fechaEventoEnfermoPostVac;
    private String sabeFechaEnfPostVac;
    private Date fechaEnfPostVac;
    private String anioEnfPostVac;
    private String mesEnfPostVac;

    private String periodoSintomas;//Almacena desde que perido se estan preguntando los sintomas de la pregunta 1
    private Date fechaRecibido; //poner fecha en que se recibe el registro en el server

    @Id
    @Column(name = "CODIGO", length = 36, nullable = false)
    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    @ManyToOne
    @JoinColumn(name = "CODIGO_PARTICIPANTE", nullable = false)
    @ForeignKey(name = "FK_CUESTIONARIO_COVID_PARTICIPANTE")
    public Participante getParticipante() {
        return participante;
    }

    public void setParticipante(Participante participante) {
        this.participante = participante;
    }

    @Column(name = "ENFERMO_COVID19", length = 4, nullable = true)
    public String getEnfermoCovid19() {
        return enfermoCovid19;
    }

    public void setEnfermoCovid19(String enfermoCovid19) {
        this.enfermoCovid19 = enfermoCovid19;
    }

    @Column(name = "ENFERMO_COVID19_VECES", length = 4, nullable = true)
    public String getCuantasVecesEnfermo() {
        return cuantasVecesEnfermo;
    }

    public void setCuantasVecesEnfermo(String cuantasVecesEnfermo) {
        this.cuantasVecesEnfermo = cuantasVecesEnfermo;
    }

    @Column(name = "E1_SABE_FECHA", length = 4, nullable = true)
    public String getSabeEvento1() {
        return sabeEvento1;
    }

    public void setSabeEvento1(String sabeEvento1) {
        this.sabeEvento1 = sabeEvento1;
    }

    @Column(name = "E1_FECHA", length = 4, nullable = true)
    public Date getEvento1() {
        return evento1;
    }

    public void setEvento1(Date evento1) {
        this.evento1 = evento1;
    }

    @Column(name = "E1_ANIO", length = 4, nullable = true)
    public String getAnioEvento1() {
        return anioEvento1;
    }

    public void setAnioEvento1(String anioEvento1) {
        this.anioEvento1 = anioEvento1;
    }

    @Column(name = "E1_MES", length = 4, nullable = true)
    public String getMesEvento1() {
        return mesEvento1;
    }

    public void setMesEvento1(String mesEvento1) {
        this.mesEvento1 = mesEvento1;
    }

    @Column(name = "E2_SABE_FECHA", length = 4, nullable = true)
    public String getSabeEvento2() {
        return sabeEvento2;
    }

    public void setSabeEvento2(String sabeEvento2) {
        this.sabeEvento2 = sabeEvento2;
    }

    @Column(name = "E2_FECHA", length = 4, nullable = true)
    public Date getEvento2() {
        return evento2;
    }

    public void setEvento2(Date evento2) {
        this.evento2 = evento2;
    }

    @Column(name = "E2_ANIO", length = 4, nullable = true)
    public String getAnioEvento2() {
        return anioEvento2;
    }

    public void setAnioEvento2(String anioEvento2) {
        this.anioEvento2 = anioEvento2;
    }

    @Column(name = "E2_MES", length = 4, nullable = true)
    public String getMesEvento2() {
        return mesEvento2;
    }

    public void setMesEvento2(String mesEvento2) {
        this.mesEvento2 = mesEvento2;
    }

    @Column(name = "E3_SABE_FECHA", length = 4, nullable = true)
    public String getSabeEvento3() {
        return sabeEvento3;
    }

    public void setSabeEvento3(String sabeEvento3) {
        this.sabeEvento3 = sabeEvento3;
    }

    @Column(name = "E3_FECHA", length = 4, nullable = true)
    public Date getEvento3() {
        return evento3;
    }

    public void setEvento3(Date evento3) {
        this.evento3 = evento3;
    }

    @Column(name = "E3_ANIO", length = 4, nullable = true)
    public String getAnioEvento3() {
        return anioEvento3;
    }

    public void setAnioEvento3(String anioEvento3) {
        this.anioEvento3 = anioEvento3;
    }

    @Column(name = "E3_MES", length = 4, nullable = true)
    public String getMesEvento3() {
        return mesEvento3;
    }

    public void setMesEvento3(String mesEvento3) {
        this.mesEvento3 = mesEvento3;
    }

    @Column(name = "E1_FEBRICULA", length = 4, nullable = true)
    public String getE1Febricula() {
        return e1Febricula;
    }

    public void setE1Febricula(String feb20Febricula) {
        this.e1Febricula = feb20Febricula;
    }

    @Column(name = "E1_FIEBRE", length = 4, nullable = true)
    public String getE1Fiebre() {
        return e1Fiebre;
    }

    public void setE1Fiebre(String feb20Fiebre) {
        this.e1Fiebre = feb20Fiebre;
    }

    @Column(name = "E1_ESCALOFRIO", length = 4, nullable = true)
    public String getE1Escalofrio() {
        return e1Escalofrio;
    }

    public void setE1Escalofrio(String feb20Escalofrio) {
        this.e1Escalofrio = feb20Escalofrio;
    }

    @Column(name = "E1_TEMBLOR_ESCALOFRIO", length = 4, nullable = true)
    public String getE1TemblorEscalofrio() {
        return e1TemblorEscalofrio;
    }

    public void setE1TemblorEscalofrio(String feb20TemblorEscalofrio) {
        this.e1TemblorEscalofrio = feb20TemblorEscalofrio;
    }

    @Column(name = "E1_DOLOR_MUSCULAR", length = 4, nullable = true)
    public String getE1DolorMuscular() {
        return e1DolorMuscular;
    }

    public void setE1DolorMuscular(String feb20DolorMuscular) {
        this.e1DolorMuscular = feb20DolorMuscular;
    }

    @Column(name = "E1_DOLOR_ARTICULAR", length = 4, nullable = true)
    public String getE1DolorArticular() {
        return e1DolorArticular;
    }

    public void setE1DolorArticular(String feb20DolorArticular) {
        this.e1DolorArticular = feb20DolorArticular;
    }

    @Column(name = "E1_SECRECION_NASAL", length = 4, nullable = true)
    public String getE1SecresionNasal() {
        return e1SecresionNasal;
    }

    public void setE1SecresionNasal(String feb20SecresionNasal) {
        this.e1SecresionNasal = feb20SecresionNasal;
    }

    @Column(name = "E1_DOLOR_GARGANTA", length = 4, nullable = true)
    public String getE1DolorGarganta() {
        return e1DolorGarganta;
    }

    public void setE1DolorGarganta(String feb20DolorGarganta) {
        this.e1DolorGarganta = feb20DolorGarganta;
    }

    @Column(name = "E1_TOS", length = 4, nullable = true)
    public String getE1Tos() {
        return e1Tos;
    }

    public void setE1Tos(String feb20Tos) {
        this.e1Tos = feb20Tos;
    }

    @Column(name = "E1_DIFICULTAD_RESPIRATORIA", length = 4, nullable = true)
    public String getE1DificultadResp() {
        return e1DificultadResp;
    }

    public void setE1DificultadResp(String feb20DificultadResp) {
        this.e1DificultadResp = feb20DificultadResp;
    }

    @Column(name = "E1_DOLOR_PECHO", length = 4, nullable = true)
    public String getE1DolorPecho() {
        return e1DolorPecho;
    }

    public void setE1DolorPecho(String feb20DolorPecho) {
        this.e1DolorPecho = feb20DolorPecho;
    }

    @Column(name = "E1_NAUSEAS_VOMITO", length = 4, nullable = true)
    public String getE1NauseasVomito() {
        return e1NauseasVomito;
    }

    public void setE1NauseasVomito(String feb20NauseasVomito) {
        this.e1NauseasVomito = feb20NauseasVomito;
    }

    @Column(name = "E1_DOLOR_CABEZA", length = 4, nullable = true)
    public String getE1DolorCabeza() {
        return e1DolorCabeza;
    }

    public void setE1DolorCabeza(String feb20DolorCabeza) {
        this.e1DolorCabeza = feb20DolorCabeza;
    }

    @Column(name = "E1_DOLOR_ADDOMINAL", length = 4, nullable = true)
    public String getE1DolorAbdominal() {
        return e1DolorAbdominal;
    }

    public void setE1DolorAbdominal(String feb20DolorAbdominal) {
        this.e1DolorAbdominal = feb20DolorAbdominal;
    }

    @Column(name = "E1_DIARREA", length = 4, nullable = true)
    public String getE1Diarrea() {
        return e1Diarrea;
    }

    public void setE1Diarrea(String feb20Diarrea) {
        this.e1Diarrea = feb20Diarrea;
    }

    @Column(name = "E1_DIFICULTAD_DORMIR", length = 4, nullable = true)
    public String getE1DificultadDormir() {
        return e1DificultadDormir;
    }

    public void setE1DificultadDormir(String feb20DificultadDormir) {
        this.e1DificultadDormir = feb20DificultadDormir;
    }

    @Column(name = "E1_DEBILIDAD", length = 4, nullable = true)
    public String getE1Debilidad() {
        return e1Debilidad;
    }

    public void setE1Debilidad(String feb20Debilidad) {
        this.e1Debilidad = feb20Debilidad;
    }

    @Column(name = "E1_PERDIDA_OLFATO_GUSTO", length = 4, nullable = true)
    public String getE1PerdidaOlfatoGusto() {
        return e1PerdidaOlfatoGusto;
    }

    public void setE1PerdidaOlfatoGusto(String feb20PerdidaOlfatoGusto) {
        this.e1PerdidaOlfatoGusto = feb20PerdidaOlfatoGusto;
    }

    @Column(name = "E1_MAREO", length = 4, nullable = true)
    public String getE1Mareo() {
        return e1Mareo;
    }

    public void setE1Mareo(String feb20Mareo) {
        this.e1Mareo = feb20Mareo;
    }

    @Column(name = "E1_SARPULLIDO", length = 4, nullable = true)
    public String getE1Sarpullido() {
        return e1Sarpullido;
    }

    public void setE1Sarpullido(String feb20Sarpullido) {
        this.e1Sarpullido = feb20Sarpullido;
    }

    @Column(name = "E1_DESMAYOS", length = 4, nullable = true)
    public String getE1Desmayo() {
        return e1Desmayo;
    }

    public void setE1Desmayo(String feb20Desmayo) {
        this.e1Desmayo = feb20Desmayo;
    }

    @Column(name = "E1_QUEDO_CAMA", length = 4, nullable = true)
    public String getE1QuedoCama() {
        return e1QuedoCama;
    }

    public void setE1QuedoCama(String feb20QuedoCama) {
        this.e1QuedoCama = feb20QuedoCama;
    }

    @Column(name = "E1_SABE_FIS", length = 4, nullable = true)
    public String getE1SabeFIS() {
        return e1SabeFIS;
    }

    public void setE1SabeFIS(String sabeFIS) {
        this.e1SabeFIS = sabeFIS;
    }

    @Column(name = "E1_FIS", nullable = true)
    public Date getE1Fis() {
        return e1Fis;
    }

    public void setE1Fis(Date fis) {
        this.e1Fis = fis;
    }

    @Column(name = "E1_MES_FIS", length = 4, nullable = true)
    public String getE1MesInicioSintoma() {
        return e1MesInicioSintoma;
    }

    public void setE1MesInicioSintoma(String mesInicioSintoma) {
        this.e1MesInicioSintoma = mesInicioSintoma;
    }

    @Column(name = "E1_ANIO_FIS", length = 4, nullable = true)
    public String getE1AnioInicioSintoma() {
        return e1AnioInicioSintoma;
    }

    public void setE1AnioInicioSintoma(String anioInicioSintoma) {
        this.e1AnioInicioSintoma = anioInicioSintoma;
    }

    @Column(name = "E1_CONOCE_LUGAR_EXPOSICION", length = 4, nullable = true)
    public String getE1ConoceLugarExposicion() {
        return e1ConoceLugarExposicion;
    }

    public void setE1ConoceLugarExposicion(String conoceLugarExposicion) {
        this.e1ConoceLugarExposicion = conoceLugarExposicion;
    }

    @Column(name = "E1_LUGAR_EXPOSICION", length = 255, nullable = true)
    public String getE1LugarExposicion() {
        return e1LugarExposicion;
    }

    public void setE1LugarExposicion(String lugarExposicion) {
        this.e1LugarExposicion = lugarExposicion;
    }

    @Column(name = "E1_BUSCO_AYUDA", length = 4, nullable = true)
    public String getE1BuscoAyuda() {
        return e1BuscoAyuda;
    }

    public void setE1BuscoAyuda(String buscoAyuda) {
        this.e1BuscoAyuda = buscoAyuda;
    }

    @Column(name = "E1_DONDE_BUSCO_AYUDA", length = 4, nullable = true)
    public String getE1DondeBuscoAyuda() {
        return e1DondeBuscoAyuda;
    }

    public void setE1DondeBuscoAyuda(String dondeBuscoAyuda) {
        this.e1DondeBuscoAyuda = dondeBuscoAyuda;
    }

    @Column(name = "E1_NOMBRE_CS", length = 255, nullable = true)
    public String getE1NombreCentroSalud() {
        return e1NombreCentroSalud;
    }

    public void setE1NombreCentroSalud(String nombreCentroSalud) {
        this.e1NombreCentroSalud = nombreCentroSalud;
    }

    @Column(name = "E1_NOMBRE_HOSPITAL", length = 255, nullable = true)
    public String getE1NombreHospital() {
        return e1NombreHospital;
    }

    public void setE1NombreHospital(String nombreHospital) {
        this.e1NombreHospital = nombreHospital;
    }

    @Column(name = "E1_RECIBIO_SEGUIMIENTO", length = 4, nullable = true)
    public String getE1RecibioSeguimiento() {
        return e1RecibioSeguimiento;
    }

    public void setE1RecibioSeguimiento(String recibioSeguimiento) {
        this.e1RecibioSeguimiento = recibioSeguimiento;
    }

    @Column(name = "E1_TIPO_SEGUIMIENTO", length = 4, nullable = true)
    public String getE1TipoSeguimiento() {
        return e1TipoSeguimiento;
    }

    public void setE1TipoSeguimiento(String tipoSeguimiento) {
        this.e1TipoSeguimiento = tipoSeguimiento;
    }

    @Column(name = "E1_CUANTO_TMP_DESP_BUSCO_AYUDA", length = 4, nullable = true)
    public String getE1TmpDespuesBuscoAyuda() {
        return e1TmpDespuesBuscoAyuda;
    }

    public void setE1TmpDespuesBuscoAyuda(String tmpDespuesBuscoAyuda) {
        this.e1TmpDespuesBuscoAyuda = tmpDespuesBuscoAyuda;
    }

    @Column(name = "E1_NOCHE_HOSPITALIZADO", length = 4, nullable = true)
    public String getE1UnaNocheHospital() {
        return e1UnaNocheHospital;
    }

    public void setE1UnaNocheHospital(String unaNocheHospital) {
        this.e1UnaNocheHospital = unaNocheHospital;
    }

    @Column(name = "E1_CUAL_HOSPITAL", length = 255, nullable = true)
    public String getE1QueHospital() {
        return e1QueHospital;
    }

    public void setE1QueHospital(String queHospital) {
        this.e1QueHospital = queHospital;
    }

    @Column(name = "E1_SABE_CUANTAS_NOCHES_HOSP", length = 4, nullable = true)
    public String getE1SabeCuantasNoches() {
        return e1SabeCuantasNoches;
    }

    public void setE1SabeCuantasNoches(String sabeCuantasNoches) {
        this.e1SabeCuantasNoches = sabeCuantasNoches;
    }

    @Column(name = "E1_CUANTAS_NOCHES_HOSP", length = 3, nullable = true)
    public Integer getE1CuantasNochesHosp() {
        return e1CuantasNochesHosp;
    }

    public void setE1CuantasNochesHosp(Integer cuantasNochesHosp) {
        this.e1CuantasNochesHosp = cuantasNochesHosp;
    }

    @Column(name = "E1_SABE_FECHA_ADMISION_HOSP", length = 4, nullable = true)
    public String getE1SabeFechaAdmision() {
        return e1SabeFechaAdmision;
    }

    public void setE1SabeFechaAdmision(String sabeFechaAdmision) {
        this.e1SabeFechaAdmision = sabeFechaAdmision;
    }

    @Column(name = "E1_FECHA_ADMISION_HOSP", nullable = true)
    public Date getE1FechaAdmisionHosp() {
        return e1FechaAdmisionHosp;
    }

    public void setE1FechaAdmisionHosp(Date fechaAdmisionHosp) {
        this.e1FechaAdmisionHosp = fechaAdmisionHosp;
    }

    @Column(name = "E1_SABE_FECHA_ALTA_HOSP", length = 4, nullable = true)
    public String getE1SabeFechaAlta() {
        return e1SabeFechaAlta;
    }

    public void setE1SabeFechaAlta(String sabeFechaAlta) {
        this.e1SabeFechaAlta = sabeFechaAlta;
    }

    @Column(name = "E1_FECHA_ALTA_HOSP", nullable = true)
    public Date getE1FechaAltaHosp() {
        return e1FechaAltaHosp;
    }

    public void setE1FechaAltaHosp(Date fechaAltaHosp) {
        this.e1FechaAltaHosp = fechaAltaHosp;
    }

    @Column(name = "E1_UTILIZO_OXIGENO", length = 4, nullable = true)
    public String getE1UtilizoOxigeno() {
        return e1UtilizoOxigeno;
    }

    public void setE1UtilizoOxigeno(String utilizoOxigeno) {
        this.e1UtilizoOxigeno = utilizoOxigeno;
    }

    @Column(name = "E1_ESTUVO_UCI", length = 4, nullable = true)
    public String getE1EstuvoUCI() {
        return e1EstuvoUCI;
    }

    public void setE1EstuvoUCI(String estuvoUCI) {
        this.e1EstuvoUCI = estuvoUCI;
    }

    @Column(name = "E1_FUE_INTUBADO", length = 4, nullable = true)
    public String getE1FueIntubado() {
        return e1FueIntubado;
    }

    public void setE1FueIntubado(String fueIntubado) {
        this.e1FueIntubado = fueIntubado;
    }

    @Column(name = "E1_ESTA_RECUPERADO", length = 4, nullable = true)
    public String getE1RecuperadoCovid19() {
        return e1RecuperadoCovid19;
    }

    public void setE1RecuperadoCovid19(String recuperadoCovid19) {
        this.e1RecuperadoCovid19 = recuperadoCovid19;
    }

    @Column(name = "E1_TIENE_FEBRICULA", length = 4, nullable = true)
    public String getE1TieneFebricula() {
        return e1TieneFebricula;
    }

    public void setE1TieneFebricula(String febricula) {
        this.e1TieneFebricula = febricula;
    }

    @Column(name = "E1_TIENE_CANSANCIO", length = 4, nullable = true)
    public String getE1TieneCansancio() {
        return e1TieneCansancio;
    }

    public void setE1TieneCansancio(String cansancio) {
        this.e1TieneCansancio = cansancio;
    }

    @Column(name = "E1_TIENE_DOLOR_CABEZA", length = 4, nullable = true)
    public String getE1TieneDolorCabeza() {
        return e1TieneDolorCabeza;
    }

    public void setE1TieneDolorCabeza(String dolorCabeza) {
        this.e1TieneDolorCabeza = dolorCabeza;
    }

    @Column(name = "E1_TIENE_PERDIDAD_OLFATO", length = 4, nullable = true)
    public String getE1TienePerdidaOlfato() {
        return e1TienePerdidaOlfato;
    }

    public void setE1TienePerdidaOlfato(String perdidaOlfato) {
        this.e1TienePerdidaOlfato = perdidaOlfato;
    }

    @Column(name = "E1_TIENE_PERDIDA_GUSTO", length = 4, nullable = true)
    public String getE1TienePerdidaGusto() {
        return e1TienePerdidaGusto;
    }

    public void setE1TienePerdidaGusto(String perdidaGusto) {
        this.e1TienePerdidaGusto = perdidaGusto;
    }

    @Column(name = "E1_TIENE_TOS", length = 4, nullable = true)
    public String getE1TieneTos() {
        return e1TieneTos;
    }

    public void setE1TieneTos(String tos) {
        this.e1TieneTos = tos;
    }

    @Column(name = "E1_TIENE_DIFICULTAD_RESPIRAR", length = 4, nullable = true)
    public String getE1TieneDificultadRespirar() {
        return e1TieneDificultadRespirar;
    }

    public void setE1TieneDificultadRespirar(String dificultadRespirar) {
        this.e1TieneDificultadRespirar = dificultadRespirar;
    }

    @Column(name = "E1_TIENE_DOLOR_PECHO", length = 4, nullable = true)
    public String getE1TieneDolorPecho() {
        return e1TieneDolorPecho;
    }

    public void setE1TieneDolorPecho(String dolorPecho) {
        this.e1TieneDolorPecho = dolorPecho;
    }

    @Column(name = "E1_TIENE_PALPITACIONES", length = 4, nullable = true)
    public String getE1TienePalpitaciones() {
        return e1TienePalpitaciones;
    }

    public void setE1TienePalpitaciones(String palpitaciones) {
        this.e1TienePalpitaciones = palpitaciones;
    }

    @Column(name = "E1_TIENE_DOLOR_ARTICULACIONES", length = 4, nullable = true)
    public String getE1TieneDolorArticulaciones() {
        return e1TieneDolorArticulaciones;
    }

    public void setE1TieneDolorArticulaciones(String dolorArticulaciones) {
        this.e1TieneDolorArticulaciones = dolorArticulaciones;
    }

    @Column(name = "E1_TIENE_PARALISIS", length = 4, nullable = true)
    public String getE1TieneParalisis() {
        return e1TieneParalisis;
    }

    public void setE1TieneParalisis(String paralisis) {
        this.e1TieneParalisis = paralisis;
    }

    @Column(name = "E1_TIENE_MAREOS", length = 4, nullable = true)
    public String getE1TieneMareos() {
        return e1TieneMareos;
    }

    public void setE1TieneMareos(String mareos) {
        this.e1TieneMareos = mareos;
    }

    @Column(name = "E1_TIENE_PENSAMIENTO_NUBLADO", length = 4, nullable = true)
    public String getE1TienePensamientoNublado() {
        return e1TienePensamientoNublado;
    }

    public void setE1TienePensamientoNublado(String pensamientoNublado) {
        this.e1TienePensamientoNublado = pensamientoNublado;
    }

    @Column(name = "E1_TIENE_PROBLEMAS_DORMIR", length = 4, nullable = true)
    public String getE1TieneProblemasDormir() {
        return e1TieneProblemasDormir;
    }

    public void setE1TieneProblemasDormir(String problemasDormir) {
        this.e1TieneProblemasDormir = problemasDormir;
    }

    @Column(name = "E1_TIENE_DEPRESION", length = 4, nullable = true)
    public String getE1TieneDepresion() {
        return e1TieneDepresion;
    }

    public void setE1TieneDepresion(String depresion) {
        this.e1TieneDepresion = depresion;
    }

    @Column(name = "E1_TIENE_OTROS_SINTOMAS", length = 4, nullable = true)
    public String getE1TieneOtrosSintomas() {
        return e1TieneOtrosSintomas;
    }

    public void setE1TieneOtrosSintomas(String otrosSintomas) {
        this.e1TieneOtrosSintomas = otrosSintomas;
    }

    @Column(name = "E1_CUALES_OTROS_SINTOMAS", length = 255, nullable = true)
    public String getE1cualesSintomas() {
        return e1cualesSintomas;
    }

    public void setE1cualesSintomas(String cualesSintomas) {
        this.e1cualesSintomas = cualesSintomas;
    }

    @Column(name = "E1_SABE_TIEMPO_RECUPERACION", length = 4, nullable = true)
    public String getE1SabeTiempoRecuperacion() {
        return e1SabeTiempoRecuperacion;
    }

    public void setE1SabeTiempoRecuperacion(String sabeTiempoRecuperacion) {
        this.e1SabeTiempoRecuperacion = sabeTiempoRecuperacion;
    }

    @Column(name = "E1_TIEMPO_RECUPERACION", length = 2, nullable = true)
    public String getE1TiempoRecuperacion() {
        return e1TiempoRecuperacion;
    }

    public void setE1TiempoRecuperacion(String tiempoRecuperacion) {
        this.e1TiempoRecuperacion = tiempoRecuperacion;
    }

    @Column(name = "E1_TIEMPO_RECUPERACION_EN", length = 2, nullable = true)
    public String getE1TiempoRecuperacionEn() {
        return e1TiempoRecuperacionEn;
    }

    public void setE1TiempoRecuperacionEn(String tiempoRecuperacionEn) {
        this.e1TiempoRecuperacionEn = tiempoRecuperacionEn;
    }

    @Column(name = "E1_SEVERIDAD_ENFERMEDAD", length = 4, nullable = true)
    public String getE1SeveridadEnfermedad() {
        return e1SeveridadEnfermedad;
    }

    public void setE1SeveridadEnfermedad(String severidadEnfermedad) {
        this.e1SeveridadEnfermedad = severidadEnfermedad;
    }

    @Column(name = "E1_TOMO_MEDICAMENTO", length = 4, nullable = true)
    public String getE1TomoMedicamento() {
        return e1TomoMedicamento;
    }

    public void setE1TomoMedicamento(String tomoMedicamento) {
        this.e1TomoMedicamento = tomoMedicamento;
    }

    @Column(name = "E1_QUE_MEDICAMENTO", length = 64, nullable = true)
    public String getE1QueMedicamento() {
        return e1QueMedicamento;
    }

    public void setE1QueMedicamento(String queMedicamento) {
        this.e1QueMedicamento = queMedicamento;
    }

    @Column(name = "E1_OTRO_MEDICAMENTO", length = 255, nullable = true)
    public String getE1OtroMedicamento() {
        return e1OtroMedicamento;
    }

    public void setE1OtroMedicamento(String otroMedicamento) {
        this.e1OtroMedicamento = otroMedicamento;
    }

    @Column(name = "E1_OXIGENO_DOMICILIO", length = 4, nullable = true)
    public String getE1OxigenoDomicilio() {
        return e1OxigenoDomicilio;
    }

    public void setE1OxigenoDomicilio(String e1OxigenoDomicilio) {
        this.e1OxigenoDomicilio = e1OxigenoDomicilio;
    }

    @Column(name = "E1_TMP_OXIGENO_DOMICILIO", length = 4, nullable = true)
    public String getE1TiempoOxigenoDom() {
        return e1TiempoOxigenoDom;
    }

    public void setE1TiempoOxigenoDom(String e1TiempoOxigenoDom) {
        this.e1TiempoOxigenoDom = e1TiempoOxigenoDom;
    }

    //evento2
    @Column(name = "E2_FEBRICULA", length = 4, nullable = true)
    public String getE2Febricula() {
        return e2Febricula;
    }

    public void setE2Febricula(String feb20Febricula) {
        this.e2Febricula = feb20Febricula;
    }

    @Column(name = "E2_FIEBRE", length = 4, nullable = true)
    public String getE2Fiebre() {
        return e2Fiebre;
    }

    public void setE2Fiebre(String feb20Fiebre) {
        this.e2Fiebre = feb20Fiebre;
    }

    @Column(name = "E2_ESCALOFRIO", length = 4, nullable = true)
    public String getE2Escalofrio() {
        return e2Escalofrio;
    }

    public void setE2Escalofrio(String feb20Escalofrio) {
        this.e2Escalofrio = feb20Escalofrio;
    }

    @Column(name = "E2_TEMBLOR_ESCALOFRIO", length = 4, nullable = true)
    public String getE2TemblorEscalofrio() {
        return e2TemblorEscalofrio;
    }

    public void setE2TemblorEscalofrio(String feb20TemblorEscalofrio) {
        this.e2TemblorEscalofrio = feb20TemblorEscalofrio;
    }

    @Column(name = "E2_DOLOR_MUSCULAR", length = 4, nullable = true)
    public String getE2DolorMuscular() {
        return e2DolorMuscular;
    }

    public void setE2DolorMuscular(String feb20DolorMuscular) {
        this.e2DolorMuscular = feb20DolorMuscular;
    }

    @Column(name = "E2_DOLOR_ARTICULAR", length = 4, nullable = true)
    public String getE2DolorArticular() {
        return e2DolorArticular;
    }

    public void setE2DolorArticular(String feb20DolorArticular) {
        this.e2DolorArticular = feb20DolorArticular;
    }

    @Column(name = "E2_SECRECION_NASAL", length = 4, nullable = true)
    public String getE2SecresionNasal() {
        return e2SecresionNasal;
    }

    public void setE2SecresionNasal(String feb20SecresionNasal) {
        this.e2SecresionNasal = feb20SecresionNasal;
    }

    @Column(name = "E2_DOLOR_GARGANTA", length = 4, nullable = true)
    public String getE2DolorGarganta() {
        return e2DolorGarganta;
    }

    public void setE2DolorGarganta(String feb20DolorGarganta) {
        this.e2DolorGarganta = feb20DolorGarganta;
    }

    @Column(name = "E2_TOS", length = 4, nullable = true)
    public String getE2Tos() {
        return e2Tos;
    }

    public void setE2Tos(String feb20Tos) {
        this.e2Tos = feb20Tos;
    }

    @Column(name = "E2_DIFICULTAD_RESPIRATORIA", length = 4, nullable = true)
    public String getE2DificultadResp() {
        return e2DificultadResp;
    }

    public void setE2DificultadResp(String feb20DificultadResp) {
        this.e2DificultadResp = feb20DificultadResp;
    }

    @Column(name = "E2_DOLOR_PECHO", length = 4, nullable = true)
    public String getE2DolorPecho() {
        return e2DolorPecho;
    }

    public void setE2DolorPecho(String feb20DolorPecho) {
        this.e2DolorPecho = feb20DolorPecho;
    }

    @Column(name = "E2_NAUSEAS_VOMITO", length = 4, nullable = true)
    public String getE2NauseasVomito() {
        return e2NauseasVomito;
    }

    public void setE2NauseasVomito(String feb20NauseasVomito) {
        this.e2NauseasVomito = feb20NauseasVomito;
    }

    @Column(name = "E2_DOLOR_CABEZA", length = 4, nullable = true)
    public String getE2DolorCabeza() {
        return e2DolorCabeza;
    }

    public void setE2DolorCabeza(String feb20DolorCabeza) {
        this.e2DolorCabeza = feb20DolorCabeza;
    }

    @Column(name = "E2_DOLOR_ADDOMINAL", length = 4, nullable = true)
    public String getE2DolorAbdominal() {
        return e2DolorAbdominal;
    }

    public void setE2DolorAbdominal(String feb20DolorAbdominal) {
        this.e2DolorAbdominal = feb20DolorAbdominal;
    }

    @Column(name = "E2_DIARREA", length = 4, nullable = true)
    public String getE2Diarrea() {
        return e2Diarrea;
    }

    public void setE2Diarrea(String feb20Diarrea) {
        this.e2Diarrea = feb20Diarrea;
    }

    @Column(name = "E2_DIFICULTAD_DORMIR", length = 4, nullable = true)
    public String getE2DificultadDormir() {
        return e2DificultadDormir;
    }

    public void setE2DificultadDormir(String feb20DificultadDormir) {
        this.e2DificultadDormir = feb20DificultadDormir;
    }

    @Column(name = "E2_DEBILIDAD", length = 4, nullable = true)
    public String getE2Debilidad() {
        return e2Debilidad;
    }

    public void setE2Debilidad(String feb20Debilidad) {
        this.e2Debilidad = feb20Debilidad;
    }

    @Column(name = "E2_PERDIDA_OLFATO_GUSTO", length = 4, nullable = true)
    public String getE2PerdidaOlfatoGusto() {
        return e2PerdidaOlfatoGusto;
    }

    public void setE2PerdidaOlfatoGusto(String feb20PerdidaOlfatoGusto) {
        this.e2PerdidaOlfatoGusto = feb20PerdidaOlfatoGusto;
    }

    @Column(name = "E2_MAREO", length = 4, nullable = true)
    public String getE2Mareo() {
        return e2Mareo;
    }

    public void setE2Mareo(String feb20Mareo) {
        this.e2Mareo = feb20Mareo;
    }

    @Column(name = "E2_SARPULLIDO", length = 4, nullable = true)
    public String getE2Sarpullido() {
        return e2Sarpullido;
    }

    public void setE2Sarpullido(String feb20Sarpullido) {
        this.e2Sarpullido = feb20Sarpullido;
    }

    @Column(name = "E2_DESMAYOS", length = 4, nullable = true)
    public String getE2Desmayo() {
        return e2Desmayo;
    }

    public void setE2Desmayo(String feb20Desmayo) {
        this.e2Desmayo = feb20Desmayo;
    }

    @Column(name = "E2_QUEDO_CAMA", length = 4, nullable = true)
    public String getE2QuedoCama() {
        return e2QuedoCama;
    }

    public void setE2QuedoCama(String feb20QuedoCama) {
        this.e2QuedoCama = feb20QuedoCama;
    }

    @Column(name = "E2_SABE_FIS", length = 4, nullable = true)
    public String getE2SabeFIS() {
        return e2SabeFIS;
    }

    public void setE2SabeFIS(String sabeFIS) {
        this.e2SabeFIS = sabeFIS;
    }

    @Column(name = "E2_FIS", nullable = true)
    public Date getE2Fis() {
        return e2Fis;
    }

    public void setE2Fis(Date fis) {
        this.e2Fis = fis;
    }

    @Column(name = "E2_MES_FIS", length = 4, nullable = true)
    public String getE2MesInicioSintoma() {
        return e2MesInicioSintoma;
    }

    public void setE2MesInicioSintoma(String mesInicioSintoma) {
        this.e2MesInicioSintoma = mesInicioSintoma;
    }

    @Column(name = "E2_ANIO_FIS", length = 4, nullable = true)
    public String getE2AnioInicioSintoma() {
        return e2AnioInicioSintoma;
    }

    public void setE2AnioInicioSintoma(String anioInicioSintoma) {
        this.e2AnioInicioSintoma = anioInicioSintoma;
    }

    @Column(name = "E2_CONOCE_LUGAR_EXPOSICION", length = 4, nullable = true)
    public String getE2ConoceLugarExposicion() {
        return e2ConoceLugarExposicion;
    }

    public void setE2ConoceLugarExposicion(String conoceLugarExposicion) {
        this.e2ConoceLugarExposicion = conoceLugarExposicion;
    }

    @Column(name = "E2_LUGAR_EXPOSICION", length = 255, nullable = true)
    public String getE2LugarExposicion() {
        return e2LugarExposicion;
    }

    public void setE2LugarExposicion(String lugarExposicion) {
        this.e2LugarExposicion = lugarExposicion;
    }

    @Column(name = "E2_BUSCO_AYUDA", length = 4, nullable = true)
    public String getE2BuscoAyuda() {
        return e2BuscoAyuda;
    }

    public void setE2BuscoAyuda(String buscoAyuda) {
        this.e2BuscoAyuda = buscoAyuda;
    }

    @Column(name = "E2_DONDE_BUSCO_AYUDA", length = 4, nullable = true)
    public String getE2DondeBuscoAyuda() {
        return e2DondeBuscoAyuda;
    }

    public void setE2DondeBuscoAyuda(String dondeBuscoAyuda) {
        this.e2DondeBuscoAyuda = dondeBuscoAyuda;
    }

    @Column(name = "E2_NOMBRE_CS", length = 255, nullable = true)
    public String getE2NombreCentroSalud() {
        return e2NombreCentroSalud;
    }

    public void setE2NombreCentroSalud(String nombreCentroSalud) {
        this.e2NombreCentroSalud = nombreCentroSalud;
    }

    @Column(name = "E2_NOMBRE_HOSPITAL", length = 255, nullable = true)
    public String getE2NombreHospital() {
        return e2NombreHospital;
    }

    public void setE2NombreHospital(String nombreHospital) {
        this.e2NombreHospital = nombreHospital;
    }

    @Column(name = "E2_RECIBIO_SEGUIMIENTO", length = 4, nullable = true)
    public String getE2RecibioSeguimiento() {
        return e2RecibioSeguimiento;
    }

    public void setE2RecibioSeguimiento(String recibioSeguimiento) {
        this.e2RecibioSeguimiento = recibioSeguimiento;
    }

    @Column(name = "E2_TIPO_SEGUIMIENTO", length = 4, nullable = true)
    public String getE2TipoSeguimiento() {
        return e2TipoSeguimiento;
    }

    public void setE2TipoSeguimiento(String tipoSeguimiento) {
        this.e2TipoSeguimiento = tipoSeguimiento;
    }

    @Column(name = "E2_CUANTO_TMP_DESP_BUSCO_AYUDA", length = 4, nullable = true)
    public String getE2TmpDespuesBuscoAyuda() {
        return e2TmpDespuesBuscoAyuda;
    }

    public void setE2TmpDespuesBuscoAyuda(String tmpDespuesBuscoAyuda) {
        this.e2TmpDespuesBuscoAyuda = tmpDespuesBuscoAyuda;
    }

    @Column(name = "E2_NOCHE_HOSPITALIZADO", length = 4, nullable = true)
    public String getE2UnaNocheHospital() {
        return e2UnaNocheHospital;
    }

    public void setE2UnaNocheHospital(String unaNocheHospital) {
        this.e2UnaNocheHospital = unaNocheHospital;
    }

    @Column(name = "E2_CUAL_HOSPITAL", length = 255, nullable = true)
    public String getE2QueHospital() {
        return e2QueHospital;
    }

    public void setE2QueHospital(String queHospital) {
        this.e2QueHospital = queHospital;
    }

    @Column(name = "E2_SABE_CUANTAS_NOCHES_HOSP", length = 4, nullable = true)
    public String getE2SabeCuantasNoches() {
        return e2SabeCuantasNoches;
    }

    public void setE2SabeCuantasNoches(String sabeCuantasNoches) {
        this.e2SabeCuantasNoches = sabeCuantasNoches;
    }

    @Column(name = "E2_CUANTAS_NOCHES_HOSP", length = 3, nullable = true)
    public Integer getE2CuantasNochesHosp() {
        return e2CuantasNochesHosp;
    }

    public void setE2CuantasNochesHosp(Integer cuantasNochesHosp) {
        this.e2CuantasNochesHosp = cuantasNochesHosp;
    }

    @Column(name = "E2_SABE_FECHA_ADMISION_HOSP", length = 4, nullable = true)
    public String getE2SabeFechaAdmision() {
        return e2SabeFechaAdmision;
    }

    public void setE2SabeFechaAdmision(String sabeFechaAdmision) {
        this.e2SabeFechaAdmision = sabeFechaAdmision;
    }

    @Column(name = "E2_FECHA_ADMISION_HOSP", nullable = true)
    public Date getE2FechaAdmisionHosp() {
        return e2FechaAdmisionHosp;
    }

    public void setE2FechaAdmisionHosp(Date fechaAdmisionHosp) {
        this.e2FechaAdmisionHosp = fechaAdmisionHosp;
    }

    @Column(name = "E2_SABE_FECHA_ALTA_HOSP", length = 4, nullable = true)
    public String getE2SabeFechaAlta() {
        return e2SabeFechaAlta;
    }

    public void setE2SabeFechaAlta(String sabeFechaAlta) {
        this.e2SabeFechaAlta = sabeFechaAlta;
    }

    @Column(name = "E2_FECHA_ALTA_HOSP", nullable = true)
    public Date getE2FechaAltaHosp() {
        return e2FechaAltaHosp;
    }

    public void setE2FechaAltaHosp(Date fechaAltaHosp) {
        this.e2FechaAltaHosp = fechaAltaHosp;
    }

    @Column(name = "E2_UTILIZO_OXIGENO", length = 4, nullable = true)
    public String getE2UtilizoOxigeno() {
        return e2UtilizoOxigeno;
    }

    public void setE2UtilizoOxigeno(String utilizoOxigeno) {
        this.e2UtilizoOxigeno = utilizoOxigeno;
    }

    @Column(name = "E2_ESTUVO_UCI", length = 4, nullable = true)
    public String getE2EstuvoUCI() {
        return e2EstuvoUCI;
    }

    public void setE2EstuvoUCI(String estuvoUCI) {
        this.e2EstuvoUCI = estuvoUCI;
    }

    @Column(name = "E2_FUE_INTUBADO", length = 4, nullable = true)
    public String getE2FueIntubado() {
        return e2FueIntubado;
    }

    public void setE2FueIntubado(String fueIntubado) {
        this.e2FueIntubado = fueIntubado;
    }

    @Column(name = "E2_ESTA_RECUPERADO", length = 4, nullable = true)
    public String getE2RecuperadoCovid19() {
        return e2RecuperadoCovid19;
    }

    public void setE2RecuperadoCovid19(String recuperadoCovid19) {
        this.e2RecuperadoCovid19 = recuperadoCovid19;
    }

    @Column(name = "E2_TIENE_FEBRICULA", length = 4, nullable = true)
    public String getE2TieneFebricula() {
        return e2TieneFebricula;
    }

    public void setE2TieneFebricula(String febricula) {
        this.e2TieneFebricula = febricula;
    }

    @Column(name = "E2_TIENE_CANSANCIO", length = 4, nullable = true)
    public String getE2TieneCansancio() {
        return e2TieneCansancio;
    }

    public void setE2TieneCansancio(String cansancio) {
        this.e2TieneCansancio = cansancio;
    }

    @Column(name = "E2_TIENE_DOLOR_CABEZA", length = 4, nullable = true)
    public String getE2TieneDolorCabeza() {
        return e2TieneDolorCabeza;
    }

    public void setE2TieneDolorCabeza(String dolorCabeza) {
        this.e2TieneDolorCabeza = dolorCabeza;
    }

    @Column(name = "E2_TIENE_PERDIDAD_OLFATO", length = 4, nullable = true)
    public String getE2TienePerdidaOlfato() {
        return e2TienePerdidaOlfato;
    }

    public void setE2TienePerdidaOlfato(String perdidaOlfato) {
        this.e2TienePerdidaOlfato = perdidaOlfato;
    }

    @Column(name = "E2_TIENE_PERDIDA_GUSTO", length = 4, nullable = true)
    public String getE2TienePerdidaGusto() {
        return e2TienePerdidaGusto;
    }

    public void setE2TienePerdidaGusto(String perdidaGusto) {
        this.e2TienePerdidaGusto = perdidaGusto;
    }

    @Column(name = "E2_TIENE_TOS", length = 4, nullable = true)
    public String getE2TieneTos() {
        return e2TieneTos;
    }

    public void setE2TieneTos(String tos) {
        this.e2TieneTos = tos;
    }

    @Column(name = "E2_TIENE_DIFICULTAD_RESPIRAR", length = 4, nullable = true)
    public String getE2TieneDificultadRespirar() {
        return e2TieneDificultadRespirar;
    }

    public void setE2TieneDificultadRespirar(String dificultadRespirar) {
        this.e2TieneDificultadRespirar = dificultadRespirar;
    }

    @Column(name = "E2_TIENE_DOLOR_PECHO", length = 4, nullable = true)
    public String getE2TieneDolorPecho() {
        return e2TieneDolorPecho;
    }

    public void setE2TieneDolorPecho(String dolorPecho) {
        this.e2TieneDolorPecho = dolorPecho;
    }

    @Column(name = "E2_TIENE_PALPITACIONES", length = 4, nullable = true)
    public String getE2TienePalpitaciones() {
        return e2TienePalpitaciones;
    }

    public void setE2TienePalpitaciones(String palpitaciones) {
        this.e2TienePalpitaciones = palpitaciones;
    }

    @Column(name = "E2_TIENE_DOLOR_ARTICULACIONES", length = 4, nullable = true)
    public String getE2TieneDolorArticulaciones() {
        return e2TieneDolorArticulaciones;
    }

    public void setE2TieneDolorArticulaciones(String dolorArticulaciones) {
        this.e2TieneDolorArticulaciones = dolorArticulaciones;
    }

    @Column(name = "E2_TIENE_PARALISIS", length = 4, nullable = true)
    public String getE2TieneParalisis() {
        return e2TieneParalisis;
    }

    public void setE2TieneParalisis(String paralisis) {
        this.e2TieneParalisis = paralisis;
    }

    @Column(name = "E2_TIENE_MAREOS", length = 4, nullable = true)
    public String getE2TieneMareos() {
        return e2TieneMareos;
    }

    public void setE2TieneMareos(String mareos) {
        this.e2TieneMareos = mareos;
    }

    @Column(name = "E2_TIENE_PENSAMIENTO_NUBLADO", length = 4, nullable = true)
    public String getE2TienePensamientoNublado() {
        return e2TienePensamientoNublado;
    }

    public void setE2TienePensamientoNublado(String pensamientoNublado) {
        this.e2TienePensamientoNublado = pensamientoNublado;
    }

    @Column(name = "E2_TIENE_PROBLEMAS_DORMIR", length = 4, nullable = true)
    public String getE2TieneProblemasDormir() {
        return e2TieneProblemasDormir;
    }

    public void setE2TieneProblemasDormir(String problemasDormir) {
        this.e2TieneProblemasDormir = problemasDormir;
    }

    @Column(name = "E2_TIENE_DEPRESION", length = 4, nullable = true)
    public String getE2TieneDepresion() {
        return e2TieneDepresion;
    }

    public void setE2TieneDepresion(String depresion) {
        this.e2TieneDepresion = depresion;
    }

    @Column(name = "E2_TIENE_OTROS_SINTOMAS", length = 4, nullable = true)
    public String getE2TieneOtrosSintomas() {
        return e2TieneOtrosSintomas;
    }

    public void setE2TieneOtrosSintomas(String otrosSintomas) {
        this.e2TieneOtrosSintomas = otrosSintomas;
    }

    @Column(name = "E2_CUALES_OTROS_SINTOMAS", length = 255, nullable = true)
    public String getE2cualesSintomas() {
        return e2cualesSintomas;
    }

    public void setE2cualesSintomas(String cualesSintomas) {
        this.e2cualesSintomas = cualesSintomas;
    }

    @Column(name = "E2_SABE_TIEMPO_RECUPERACION", length = 4, nullable = true)
    public String getE2SabeTiempoRecuperacion() {
        return e2SabeTiempoRecuperacion;
    }

    public void setE2SabeTiempoRecuperacion(String sabeTiempoRecuperacion) {
        this.e2SabeTiempoRecuperacion = sabeTiempoRecuperacion;
    }

    @Column(name = "E2_TIEMPO_RECUPERACION", length = 2, nullable = true)
    public String getE2TiempoRecuperacion() {
        return e2TiempoRecuperacion;
    }

    public void setE2TiempoRecuperacion(String tiempoRecuperacion) {
        this.e2TiempoRecuperacion = tiempoRecuperacion;
    }

    @Column(name = "E2_TIEMPO_RECUPERACION_EN", length = 2, nullable = true)
    public String getE2TiempoRecuperacionEn() {
        return e2TiempoRecuperacionEn;
    }

    public void setE2TiempoRecuperacionEn(String tiempoRecuperacionEn) {
        this.e2TiempoRecuperacionEn = tiempoRecuperacionEn;
    }

    @Column(name = "E2_SEVERIDAD_ENFERMEDAD", length = 4, nullable = true)
    public String getE2SeveridadEnfermedad() {
        return e2SeveridadEnfermedad;
    }

    public void setE2SeveridadEnfermedad(String severidadEnfermedad) {
        this.e2SeveridadEnfermedad = severidadEnfermedad;
    }

    @Column(name = "E2_TOMO_MEDICAMENTO", length = 4, nullable = true)
    public String getE2TomoMedicamento() {
        return e2TomoMedicamento;
    }

    public void setE2TomoMedicamento(String tomoMedicamento) {
        this.e2TomoMedicamento = tomoMedicamento;
    }

    @Column(name = "E2_QUE_MEDICAMENTO", length = 64, nullable = true)
    public String getE2QueMedicamento() {
        return e2QueMedicamento;
    }

    public void setE2QueMedicamento(String queMedicamento) {
        this.e2QueMedicamento = queMedicamento;
    }

    @Column(name = "E2_OTRO_MEDICAMENTO", length = 255, nullable = true)
    public String getE2OtroMedicamento() {
        return e2OtroMedicamento;
    }

    public void setE2OtroMedicamento(String otroMedicamento) {
        this.e2OtroMedicamento = otroMedicamento;
    }

    @Column(name = "E2_OXIGENO_DOMICILIO", length = 4, nullable = true)
    public String getE2OxigenoDomicilio() {
        return e2OxigenoDomicilio;
    }

    public void setE2OxigenoDomicilio(String e2OxigenoDomicilio) {
        this.e2OxigenoDomicilio = e2OxigenoDomicilio;
    }

    @Column(name = "E2_TMP_OXIGENO_DOMICILIO", length = 4, nullable = true)
    public String getE2TiempoOxigenoDom() {
        return e2TiempoOxigenoDom;
    }

    public void setE2TiempoOxigenoDom(String e2TiempoOxigenoDom) {
        this.e2TiempoOxigenoDom = e2TiempoOxigenoDom;
    }
    //evento3
    @Column(name = "E3_FEBRICULA", length = 4, nullable = true)
    public String getE3Febricula() {
        return e3Febricula;
    }

    public void setE3Febricula(String feb20Febricula) {
        this.e3Febricula = feb20Febricula;
    }

    @Column(name = "E3_FIEBRE", length = 4, nullable = true)
    public String getE3Fiebre() {
        return e3Fiebre;
    }

    public void setE3Fiebre(String feb20Fiebre) {
        this.e3Fiebre = feb20Fiebre;
    }

    @Column(name = "E3_ESCALOFRIO", length = 4, nullable = true)
    public String getE3Escalofrio() {
        return e3Escalofrio;
    }

    public void setE3Escalofrio(String feb20Escalofrio) {
        this.e3Escalofrio = feb20Escalofrio;
    }

    @Column(name = "E3_TEMBLOR_ESCALOFRIO", length = 4, nullable = true)
    public String getE3TemblorEscalofrio() {
        return e3TemblorEscalofrio;
    }

    public void setE3TemblorEscalofrio(String feb20TemblorEscalofrio) {
        this.e3TemblorEscalofrio = feb20TemblorEscalofrio;
    }

    @Column(name = "E3_DOLOR_MUSCULAR", length = 4, nullable = true)
    public String getE3DolorMuscular() {
        return e3DolorMuscular;
    }

    public void setE3DolorMuscular(String feb20DolorMuscular) {
        this.e3DolorMuscular = feb20DolorMuscular;
    }

    @Column(name = "E3_DOLOR_ARTICULAR", length = 4, nullable = true)
    public String getE3DolorArticular() {
        return e3DolorArticular;
    }

    public void setE3DolorArticular(String feb20DolorArticular) {
        this.e3DolorArticular = feb20DolorArticular;
    }

    @Column(name = "E3_SECRECION_NASAL", length = 4, nullable = true)
    public String getE3SecresionNasal() {
        return e3SecresionNasal;
    }

    public void setE3SecresionNasal(String feb20SecresionNasal) {
        this.e3SecresionNasal = feb20SecresionNasal;
    }

    @Column(name = "E3_DOLOR_GARGANTA", length = 4, nullable = true)
    public String getE3DolorGarganta() {
        return e3DolorGarganta;
    }

    public void setE3DolorGarganta(String feb20DolorGarganta) {
        this.e3DolorGarganta = feb20DolorGarganta;
    }

    @Column(name = "E3_TOS", length = 4, nullable = true)
    public String getE3Tos() {
        return e3Tos;
    }

    public void setE3Tos(String feb20Tos) {
        this.e3Tos = feb20Tos;
    }

    @Column(name = "E3_DIFICULTAD_RESPIRATORIA", length = 4, nullable = true)
    public String getE3DificultadResp() {
        return e3DificultadResp;
    }

    public void setE3DificultadResp(String feb20DificultadResp) {
        this.e3DificultadResp = feb20DificultadResp;
    }

    @Column(name = "E3_DOLOR_PECHO", length = 4, nullable = true)
    public String getE3DolorPecho() {
        return e3DolorPecho;
    }

    public void setE3DolorPecho(String feb20DolorPecho) {
        this.e3DolorPecho = feb20DolorPecho;
    }

    @Column(name = "E3_NAUSEAS_VOMITO", length = 4, nullable = true)
    public String getE3NauseasVomito() {
        return e3NauseasVomito;
    }

    public void setE3NauseasVomito(String feb20NauseasVomito) {
        this.e3NauseasVomito = feb20NauseasVomito;
    }

    @Column(name = "E3_DOLOR_CABEZA", length = 4, nullable = true)
    public String getE3DolorCabeza() {
        return e3DolorCabeza;
    }

    public void setE3DolorCabeza(String feb20DolorCabeza) {
        this.e3DolorCabeza = feb20DolorCabeza;
    }

    @Column(name = "E3_DOLOR_ADDOMINAL", length = 4, nullable = true)
    public String getE3DolorAbdominal() {
        return e3DolorAbdominal;
    }

    public void setE3DolorAbdominal(String feb20DolorAbdominal) {
        this.e3DolorAbdominal = feb20DolorAbdominal;
    }

    @Column(name = "E3_DIARREA", length = 4, nullable = true)
    public String getE3Diarrea() {
        return e3Diarrea;
    }

    public void setE3Diarrea(String feb20Diarrea) {
        this.e3Diarrea = feb20Diarrea;
    }

    @Column(name = "E3_DIFICULTAD_DORMIR", length = 4, nullable = true)
    public String getE3DificultadDormir() {
        return e3DificultadDormir;
    }

    public void setE3DificultadDormir(String feb20DificultadDormir) {
        this.e3DificultadDormir = feb20DificultadDormir;
    }

    @Column(name = "E3_DEBILIDAD", length = 4, nullable = true)
    public String getE3Debilidad() {
        return e3Debilidad;
    }

    public void setE3Debilidad(String feb20Debilidad) {
        this.e3Debilidad = feb20Debilidad;
    }

    @Column(name = "E3_PERDIDA_OLFATO_GUSTO", length = 4, nullable = true)
    public String getE3PerdidaOlfatoGusto() {
        return e3PerdidaOlfatoGusto;
    }

    public void setE3PerdidaOlfatoGusto(String feb20PerdidaOlfatoGusto) {
        this.e3PerdidaOlfatoGusto = feb20PerdidaOlfatoGusto;
    }

    @Column(name = "E3_MAREO", length = 4, nullable = true)
    public String getE3Mareo() {
        return e3Mareo;
    }

    public void setE3Mareo(String feb20Mareo) {
        this.e3Mareo = feb20Mareo;
    }

    @Column(name = "E3_SARPULLIDO", length = 4, nullable = true)
    public String getE3Sarpullido() {
        return e3Sarpullido;
    }

    public void setE3Sarpullido(String feb20Sarpullido) {
        this.e3Sarpullido = feb20Sarpullido;
    }

    @Column(name = "E3_DESMAYOS", length = 4, nullable = true)
    public String getE3Desmayo() {
        return e3Desmayo;
    }

    public void setE3Desmayo(String feb20Desmayo) {
        this.e3Desmayo = feb20Desmayo;
    }

    @Column(name = "E3_QUEDO_CAMA", length = 4, nullable = true)
    public String getE3QuedoCama() {
        return e3QuedoCama;
    }

    public void setE3QuedoCama(String feb20QuedoCama) {
        this.e3QuedoCama = feb20QuedoCama;
    }

    @Column(name = "E3_SABE_FIS", length = 4, nullable = true)
    public String getE3SabeFIS() {
        return e3SabeFIS;
    }

    public void setE3SabeFIS(String sabeFIS) {
        this.e3SabeFIS = sabeFIS;
    }

    @Column(name = "E3_FIS", nullable = true)
    public Date getE3Fis() {
        return e3Fis;
    }

    public void setE3Fis(Date fis) {
        this.e3Fis = fis;
    }

    @Column(name = "E3_MES_FIS", length = 4, nullable = true)
    public String getE3MesInicioSintoma() {
        return e3MesInicioSintoma;
    }

    public void setE3MesInicioSintoma(String mesInicioSintoma) {
        this.e3MesInicioSintoma = mesInicioSintoma;
    }

    @Column(name = "E3_ANIO_FIS", length = 4, nullable = true)
    public String getE3AnioInicioSintoma() {
        return e3AnioInicioSintoma;
    }

    public void setE3AnioInicioSintoma(String anioInicioSintoma) {
        this.e3AnioInicioSintoma = anioInicioSintoma;
    }

    @Column(name = "E3_CONOCE_LUGAR_EXPOSICION", length = 4, nullable = true)
    public String getE3ConoceLugarExposicion() {
        return e3ConoceLugarExposicion;
    }

    public void setE3ConoceLugarExposicion(String conoceLugarExposicion) {
        this.e3ConoceLugarExposicion = conoceLugarExposicion;
    }

    @Column(name = "E3_LUGAR_EXPOSICION", length = 255, nullable = true)
    public String getE3LugarExposicion() {
        return e3LugarExposicion;
    }

    public void setE3LugarExposicion(String lugarExposicion) {
        this.e3LugarExposicion = lugarExposicion;
    }

    @Column(name = "E3_BUSCO_AYUDA", length = 4, nullable = true)
    public String getE3BuscoAyuda() {
        return e3BuscoAyuda;
    }

    public void setE3BuscoAyuda(String buscoAyuda) {
        this.e3BuscoAyuda = buscoAyuda;
    }

    @Column(name = "E3_DONDE_BUSCO_AYUDA", length = 4, nullable = true)
    public String getE3DondeBuscoAyuda() {
        return e3DondeBuscoAyuda;
    }

    public void setE3DondeBuscoAyuda(String dondeBuscoAyuda) {
        this.e3DondeBuscoAyuda = dondeBuscoAyuda;
    }

    @Column(name = "E3_NOMBRE_CS", length = 255, nullable = true)
    public String getE3NombreCentroSalud() {
        return e3NombreCentroSalud;
    }

    public void setE3NombreCentroSalud(String nombreCentroSalud) {
        this.e3NombreCentroSalud = nombreCentroSalud;
    }

    @Column(name = "E3_NOMBRE_HOSPITAL", length = 255, nullable = true)
    public String getE3NombreHospital() {
        return e3NombreHospital;
    }

    public void setE3NombreHospital(String nombreHospital) {
        this.e3NombreHospital = nombreHospital;
    }

    @Column(name = "E3_RECIBIO_SEGUIMIENTO", length = 4, nullable = true)
    public String getE3RecibioSeguimiento() {
        return e3RecibioSeguimiento;
    }

    public void setE3RecibioSeguimiento(String recibioSeguimiento) {
        this.e3RecibioSeguimiento = recibioSeguimiento;
    }

    @Column(name = "E3_TIPO_SEGUIMIENTO", length = 4, nullable = true)
    public String getE3TipoSeguimiento() {
        return e3TipoSeguimiento;
    }

    public void setE3TipoSeguimiento(String tipoSeguimiento) {
        this.e3TipoSeguimiento = tipoSeguimiento;
    }

    @Column(name = "E3_CUANTO_TMP_DESP_BUSCO_AYUDA", length = 4, nullable = true)
    public String getE3TmpDespuesBuscoAyuda() {
        return e3TmpDespuesBuscoAyuda;
    }

    public void setE3TmpDespuesBuscoAyuda(String tmpDespuesBuscoAyuda) {
        this.e3TmpDespuesBuscoAyuda = tmpDespuesBuscoAyuda;
    }

    @Column(name = "E3_NOCHE_HOSPITALIZADO", length = 4, nullable = true)
    public String getE3UnaNocheHospital() {
        return e3UnaNocheHospital;
    }

    public void setE3UnaNocheHospital(String unaNocheHospital) {
        this.e3UnaNocheHospital = unaNocheHospital;
    }

    @Column(name = "E3_CUAL_HOSPITAL", length = 255, nullable = true)
    public String getE3QueHospital() {
        return e3QueHospital;
    }

    public void setE3QueHospital(String queHospital) {
        this.e3QueHospital = queHospital;
    }

    @Column(name = "E3_SABE_CUANTAS_NOCHES_HOSP", length = 4, nullable = true)
    public String getE3SabeCuantasNoches() {
        return e3SabeCuantasNoches;
    }

    public void setE3SabeCuantasNoches(String sabeCuantasNoches) {
        this.e3SabeCuantasNoches = sabeCuantasNoches;
    }

    @Column(name = "E3_CUANTAS_NOCHES_HOSP", length = 3, nullable = true)
    public Integer getE3CuantasNochesHosp() {
        return e3CuantasNochesHosp;
    }

    public void setE3CuantasNochesHosp(Integer cuantasNochesHosp) {
        this.e3CuantasNochesHosp = cuantasNochesHosp;
    }

    @Column(name = "E3_SABE_FECHA_ADMISION_HOSP", length = 4, nullable = true)
    public String getE3SabeFechaAdmision() {
        return e3SabeFechaAdmision;
    }

    public void setE3SabeFechaAdmision(String sabeFechaAdmision) {
        this.e3SabeFechaAdmision = sabeFechaAdmision;
    }

    @Column(name = "E3_FECHA_ADMISION_HOSP", nullable = true)
    public Date getE3FechaAdmisionHosp() {
        return e3FechaAdmisionHosp;
    }

    public void setE3FechaAdmisionHosp(Date fechaAdmisionHosp) {
        this.e3FechaAdmisionHosp = fechaAdmisionHosp;
    }

    @Column(name = "E3_SABE_FECHA_ALTA_HOSP", length = 4, nullable = true)
    public String getE3SabeFechaAlta() {
        return e3SabeFechaAlta;
    }

    public void setE3SabeFechaAlta(String sabeFechaAlta) {
        this.e3SabeFechaAlta = sabeFechaAlta;
    }

    @Column(name = "E3_FECHA_ALTA_HOSP", nullable = true)
    public Date getE3FechaAltaHosp() {
        return e3FechaAltaHosp;
    }

    public void setE3FechaAltaHosp(Date fechaAltaHosp) {
        this.e3FechaAltaHosp = fechaAltaHosp;
    }

    @Column(name = "E3_UTILIZO_OXIGENO", length = 4, nullable = true)
    public String getE3UtilizoOxigeno() {
        return e3UtilizoOxigeno;
    }

    public void setE3UtilizoOxigeno(String utilizoOxigeno) {
        this.e3UtilizoOxigeno = utilizoOxigeno;
    }

    @Column(name = "E3_ESTUVO_UCI", length = 4, nullable = true)
    public String getE3EstuvoUCI() {
        return e3EstuvoUCI;
    }

    public void setE3EstuvoUCI(String estuvoUCI) {
        this.e3EstuvoUCI = estuvoUCI;
    }

    @Column(name = "E3_FUE_INTUBADO", length = 4, nullable = true)
    public String getE3FueIntubado() {
        return e3FueIntubado;
    }

    public void setE3FueIntubado(String fueIntubado) {
        this.e3FueIntubado = fueIntubado;
    }

    @Column(name = "E3_ESTA_RECUPERADO", length = 4, nullable = true)
    public String getE3RecuperadoCovid19() {
        return e3RecuperadoCovid19;
    }

    public void setE3RecuperadoCovid19(String recuperadoCovid19) {
        this.e3RecuperadoCovid19 = recuperadoCovid19;
    }

    @Column(name = "E3_TIENE_FEBRICULA", length = 4, nullable = true)
    public String getE3TieneFebricula() {
        return e3TieneFebricula;
    }

    public void setE3TieneFebricula(String febricula) {
        this.e3TieneFebricula = febricula;
    }

    @Column(name = "E3_TIENE_CANSANCIO", length = 4, nullable = true)
    public String getE3TieneCansancio() {
        return e3TieneCansancio;
    }

    public void setE3TieneCansancio(String cansancio) {
        this.e3TieneCansancio = cansancio;
    }

    @Column(name = "E3_TIENE_DOLOR_CABEZA", length = 4, nullable = true)
    public String getE3TieneDolorCabeza() {
        return e3TieneDolorCabeza;
    }

    public void setE3TieneDolorCabeza(String dolorCabeza) {
        this.e3TieneDolorCabeza = dolorCabeza;
    }

    @Column(name = "E3_TIENE_PERDIDAD_OLFATO", length = 4, nullable = true)
    public String getE3TienePerdidaOlfato() {
        return e3TienePerdidaOlfato;
    }

    public void setE3TienePerdidaOlfato(String perdidaOlfato) {
        this.e3TienePerdidaOlfato = perdidaOlfato;
    }

    @Column(name = "E3_TIENE_PERDIDA_GUSTO", length = 4, nullable = true)
    public String getE3TienePerdidaGusto() {
        return e3TienePerdidaGusto;
    }

    public void setE3TienePerdidaGusto(String perdidaGusto) {
        this.e3TienePerdidaGusto = perdidaGusto;
    }

    @Column(name = "E3_TIENE_TOS", length = 4, nullable = true)
    public String getE3TieneTos() {
        return e3TieneTos;
    }

    public void setE3TieneTos(String tos) {
        this.e3TieneTos = tos;
    }

    @Column(name = "E3_TIENE_DIFICULTAD_RESPIRAR", length = 4, nullable = true)
    public String getE3TieneDificultadRespirar() {
        return e3TieneDificultadRespirar;
    }

    public void setE3TieneDificultadRespirar(String dificultadRespirar) {
        this.e3TieneDificultadRespirar = dificultadRespirar;
    }

    @Column(name = "E3_TIENE_DOLOR_PECHO", length = 4, nullable = true)
    public String getE3TieneDolorPecho() {
        return e3TieneDolorPecho;
    }

    public void setE3TieneDolorPecho(String dolorPecho) {
        this.e3TieneDolorPecho = dolorPecho;
    }

    @Column(name = "E3_TIENE_PALPITACIONES", length = 4, nullable = true)
    public String getE3TienePalpitaciones() {
        return e3TienePalpitaciones;
    }

    public void setE3TienePalpitaciones(String palpitaciones) {
        this.e3TienePalpitaciones = palpitaciones;
    }

    @Column(name = "E3_TIENE_DOLOR_ARTICULACIONES", length = 4, nullable = true)
    public String getE3TieneDolorArticulaciones() {
        return e3TieneDolorArticulaciones;
    }

    public void setE3TieneDolorArticulaciones(String dolorArticulaciones) {
        this.e3TieneDolorArticulaciones = dolorArticulaciones;
    }

    @Column(name = "E3_TIENE_PARALISIS", length = 4, nullable = true)
    public String getE3TieneParalisis() {
        return e3TieneParalisis;
    }

    public void setE3TieneParalisis(String paralisis) {
        this.e3TieneParalisis = paralisis;
    }

    @Column(name = "E3_TIENE_MAREOS", length = 4, nullable = true)
    public String getE3TieneMareos() {
        return e3TieneMareos;
    }

    public void setE3TieneMareos(String mareos) {
        this.e3TieneMareos = mareos;
    }

    @Column(name = "E3_TIENE_PENSAMIENTO_NUBLADO", length = 4, nullable = true)
    public String getE3TienePensamientoNublado() {
        return e3TienePensamientoNublado;
    }

    public void setE3TienePensamientoNublado(String pensamientoNublado) {
        this.e3TienePensamientoNublado = pensamientoNublado;
    }

    @Column(name = "E3_TIENE_PROBLEMAS_DORMIR", length = 4, nullable = true)
    public String getE3TieneProblemasDormir() {
        return e3TieneProblemasDormir;
    }

    public void setE3TieneProblemasDormir(String problemasDormir) {
        this.e3TieneProblemasDormir = problemasDormir;
    }

    @Column(name = "E3_TIENE_DEPRESION", length = 4, nullable = true)
    public String getE3TieneDepresion() {
        return e3TieneDepresion;
    }

    public void setE3TieneDepresion(String depresion) {
        this.e3TieneDepresion = depresion;
    }

    @Column(name = "E3_TIENE_OTROS_SINTOMAS", length = 4, nullable = true)
    public String getE3TieneOtrosSintomas() {
        return e3TieneOtrosSintomas;
    }

    public void setE3TieneOtrosSintomas(String otrosSintomas) {
        this.e3TieneOtrosSintomas = otrosSintomas;
    }

    @Column(name = "E3_CUALES_OTROS_SINTOMAS", length = 255, nullable = true)
    public String getE3cualesSintomas() {
        return e3cualesSintomas;
    }

    public void setE3cualesSintomas(String cualesSintomas) {
        this.e3cualesSintomas = cualesSintomas;
    }

    @Column(name = "E3_SABE_TIEMPO_RECUPERACION", length = 4, nullable = true)
    public String getE3SabeTiempoRecuperacion() {
        return e3SabeTiempoRecuperacion;
    }

    public void setE3SabeTiempoRecuperacion(String sabeTiempoRecuperacion) {
        this.e3SabeTiempoRecuperacion = sabeTiempoRecuperacion;
    }

    @Column(name = "E3_TIEMPO_RECUPERACION", length = 2, nullable = true)
    public String getE3TiempoRecuperacion() {
        return e3TiempoRecuperacion;
    }

    public void setE3TiempoRecuperacion(String tiempoRecuperacion) {
        this.e3TiempoRecuperacion = tiempoRecuperacion;
    }

    @Column(name = "E3_TIEMPO_RECUPERACION_EN", length = 2, nullable = true)
    public String getE3TiempoRecuperacionEn() {
        return e3TiempoRecuperacionEn;
    }

    public void setE3TiempoRecuperacionEn(String tiempoRecuperacionEn) {
        this.e3TiempoRecuperacionEn = tiempoRecuperacionEn;
    }

    @Column(name = "E3_SEVERIDAD_ENFERMEDAD", length = 4, nullable = true)
    public String getE3SeveridadEnfermedad() {
        return e3SeveridadEnfermedad;
    }

    public void setE3SeveridadEnfermedad(String severidadEnfermedad) {
        this.e3SeveridadEnfermedad = severidadEnfermedad;
    }

    @Column(name = "E3_TOMO_MEDICAMENTO", length = 4, nullable = true)
    public String getE3TomoMedicamento() {
        return e3TomoMedicamento;
    }

    public void setE3TomoMedicamento(String tomoMedicamento) {
        this.e3TomoMedicamento = tomoMedicamento;
    }

    @Column(name = "E3_QUE_MEDICAMENTO", length = 64, nullable = true)
    public String getE3QueMedicamento() {
        return e3QueMedicamento;
    }

    public void setE3QueMedicamento(String queMedicamento) {
        this.e3QueMedicamento = queMedicamento;
    }

    @Column(name = "E3_OTRO_MEDICAMENTO", length = 255, nullable = true)
    public String getE3OtroMedicamento() {
        return e3OtroMedicamento;
    }

    public void setE3OtroMedicamento(String otroMedicamento) {
        this.e3OtroMedicamento = otroMedicamento;
    }

    @Column(name = "E3_OXIGENO_DOMICILIO", length = 4, nullable = true)
    public String getE3OxigenoDomicilio() {
        return e3OxigenoDomicilio;
    }

    public void setE3OxigenoDomicilio(String e3OxigenoDomicilio) {
        this.e3OxigenoDomicilio = e3OxigenoDomicilio;
    }

    @Column(name = "E3_TMP_OXIGENO_DOMICILIO", length = 4, nullable = true)
    public String getE3TiempoOxigenoDom() {
        return e3TiempoOxigenoDom;
    }

    public void setE3TiempoOxigenoDom(String e3TiempoOxigenoDom) {
        this.e3TiempoOxigenoDom = e3TiempoOxigenoDom;
    }
    //una vez
    @Column(name = "PADECE_ENFISEMA", length = 4, nullable = true)
    public String getPadeceEnfisema() {
        return padeceEnfisema;
    }

    public void setPadeceEnfisema(String padeceEnfisema) {
        this.padeceEnfisema = padeceEnfisema;
    }

    @Column(name = "PADECE_ASMA", length = 4, nullable = true)
    public String getPadeceAsma() {
        return padeceAsma;
    }

    public void setPadeceAsma(String padeceAsma) {
        this.padeceAsma = padeceAsma;
    }

    @Column(name = "PADECE_DIABETES", length = 4, nullable = true)
    public String getPadeceDiabetes() {
        return padeceDiabetes;
    }

    public void setPadeceDiabetes(String padeceDiabetes) {
        this.padeceDiabetes = padeceDiabetes;
    }

    @Column(name = "PADECE_ENF_CORONARIA", length = 4, nullable = true)
    public String getPadeceEnfCoronaria() {
        return padeceEnfCoronaria;
    }

    public void setPadeceEnfCoronaria(String padeceEnfCoronaria) {
        this.padeceEnfCoronaria = padeceEnfCoronaria;
    }

    @Column(name = "PADECE_PRESION_ALTA", length = 4, nullable = true)
    public String getPadecePresionAlta() {
        return padecePresionAlta;
    }

    public void setPadecePresionAlta(String padecePresionAlta) {
        this.padecePresionAlta = padecePresionAlta;
    }

    @Column(name = "PADECE_ENF_HIGADO", length = 4, nullable = true)
    public String getPadeceEnfHigado() {
        return padeceEnfHigado;
    }

    public void setPadeceEnfHigado(String padeceEnfHigado) {
        this.padeceEnfHigado = padeceEnfHigado;
    }

    @Column(name = "PADECE_ENF_RENAL", length = 4, nullable = true)
    public String getPadeceEnfRenal() {
        return padeceEnfRenal;
    }

    public void setPadeceEnfRenal(String padeceEnfRenal) {
        this.padeceEnfRenal = padeceEnfRenal;
    }

    @Column(name = "PADECE_INFARTO_DERRAME_CEREB", length = 4, nullable = true)
    public String getPadeceInfartoDerrameCer() {
        return padeceInfartoDerrameCer;
    }

    public void setPadeceInfartoDerrameCer(String padeceInfartoDerrameCer) {
        this.padeceInfartoDerrameCer = padeceInfartoDerrameCer;
    }

    @Column(name = "PADECE_CANCER", length = 4, nullable = true)
    public String getPadeceCancer() {
        return padeceCancer;
    }

    public void setPadeceCancer(String padeceCancer) {
        this.padeceCancer = padeceCancer;
    }

    @Column(name = "PADECE_CONDICION_INMUNO", length = 4, nullable = true)
    public String getPadeceCondicionInmuno() {
        return padeceCondicionInmuno;
    }

    public void setPadeceCondicionInmuno(String padeceCondicionInmuno) {
        this.padeceCondicionInmuno = padeceCondicionInmuno;
    }

    @Column(name = "PADECE_ENF_AUTOINMUNE", length = 4, nullable = true)
    public String getPadeceEnfAutoinmune() {
        return padeceEnfAutoinmune;
    }

    public void setPadeceEnfAutoinmune(String padeceEnfAutoinmune) {
        this.padeceEnfAutoinmune = padeceEnfAutoinmune;
    }

    @Column(name = "PADECE_DISCAPACIDAD", length = 4, nullable = true)
    public String getPadeceDiscapacidadFis() {
        return padeceDiscapacidadFis;
    }

    public void setPadeceDiscapacidadFis(String padeceDiscapacidadFis) {
        this.padeceDiscapacidadFis = padeceDiscapacidadFis;
    }

    @Column(name = "PADECE_CONDICION_PSIC_PSIQ", length = 4, nullable = true)
    public String getPadeceCondPsicPsiq() {
        return padeceCondPsicPsiq;
    }

    public void setPadeceCondPsicPsiq(String padeceCondPsicPsiq) {
        this.padeceCondPsicPsiq = padeceCondPsicPsiq;
    }

    @Column(name = "PADECE_OTRA_CONDICION", length = 4, nullable = true)
    public String getPadeceOtraCondicion() {
        return padeceOtraCondicion;
    }

    public void setPadeceOtraCondicion(String padeceOtraCondicion) {
        this.padeceOtraCondicion = padeceOtraCondicion;
    }

    @Column(name = "QUE_OTRA_CONDICION", length = 255, nullable = true)
    public String getQueOtraCondicion() {
        return queOtraCondicion;
    }

    public void setQueOtraCondicion(String queOtraCondicion) {
        this.queOtraCondicion = queOtraCondicion;
    }

    @Column(name = "FUMADO", length = 4, nullable = true)
    public String getFumado() {
        return fumado;
    }

    public void setFumado(String fumado) {
        this.fumado = fumado;
    }

    @Column(name = "FUMADO_CIEN_CIGARRILLOS", length = 4, nullable = true)
    public String getFumadoCienCigarrillos() {
        return fumadoCienCigarrillos;
    }

    public void setFumadoCienCigarrillos(String fumadoCienCigarrillos) {
        this.fumadoCienCigarrillos = fumadoCienCigarrillos;
    }

    @Column(name = "E1_FUMADO_PREVIO_ENFERMEDAD", length = 4, nullable = true)
    public String getE1FumadoPrevioEnfermedad() {
        return e1FumadoPrevioEnfermedad;
    }

    public void setE1FumadoPrevioEnfermedad(String fumadoPrevioEnfermedad) {
        this.e1FumadoPrevioEnfermedad = fumadoPrevioEnfermedad;
    }

    @Column(name = "E1_FUMA_ACTUALMENTE", length = 4, nullable = true)
    public String getE1FumaActualmente() {
        return e1FumaActualmente;
    }

    public void setE1FumaActualmente(String fumaActualmente) {
        this.e1FumaActualmente = fumaActualmente;
    }

    @Column(name = "E1_EMBARAZADA", length = 4, nullable = true)
    public String getE1Embarazada() {
        return e1Embarazada;
    }

    public void setE1Embarazada(String embarazada) {
        this.e1Embarazada = embarazada;
    }

    @Column(name = "E1_RECUERDA_SEMANAS_EMB", length = 4, nullable = true)
    public String getE1RecuerdaSemanasEmb() {
        return e1RecuerdaSemanasEmb;
    }

    public void setE1RecuerdaSemanasEmb(String recuerdaSemanasEmb) {
        this.e1RecuerdaSemanasEmb = recuerdaSemanasEmb;
    }

    @Column(name = "E1_SEMANAS_EMBARAZO", nullable = true)
    public Integer getE1SemanasEmbarazo() {
        return e1SemanasEmbarazo;
    }

    public void setE1SemanasEmbarazo(Integer semanasEmbarazo) {
        this.e1SemanasEmbarazo = semanasEmbarazo;
    }

    @Column(name = "E1_FINAL_EMBARAZO", length = 4, nullable = true)
    public String getE1FinalEmbarazo() {
        return e1FinalEmbarazo;
    }

    public void setE1FinalEmbarazo(String finalEmbarazo) {
        this.e1FinalEmbarazo = finalEmbarazo;
    }

    @Column(name = "E1_OTRO_FINAL_EMBARAZO", nullable = true)
    public String getE1OtroFinalEmbarazo() {
        return e1OtroFinalEmbarazo;
    }

    public void setE1OtroFinalEmbarazo(String otroFinalEmbarazo) {
        this.e1OtroFinalEmbarazo = otroFinalEmbarazo;
    }

    @Column(name = "E1_DABA_PECHO", length = 4, nullable = true)
    public String getE1DabaPecho() {
        return e1DabaPecho;
    }

    public void setE1DabaPecho(String dabaPecho) {
        this.e1DabaPecho = dabaPecho;
    }
    //evento2
    @Column(name = "E2_FUMADO_PREVIO_ENFERMEDAD", length = 4, nullable = true)
    public String getE2FumadoPrevioEnfermedad() {
        return e2FumadoPrevioEnfermedad;
    }

    public void setE2FumadoPrevioEnfermedad(String fumadoPrevioEnfermedad) {
        this.e2FumadoPrevioEnfermedad = fumadoPrevioEnfermedad;
    }

    @Column(name = "E2_FUMA_ACTUALMENTE", length = 4, nullable = true)
    public String getE2FumaActualmente() {
        return e2FumaActualmente;
    }

    public void setE2FumaActualmente(String fumaActualmente) {
        this.e2FumaActualmente = fumaActualmente;
    }

    @Column(name = "E2_EMBARAZADA", length = 4, nullable = true)
    public String getE2Embarazada() {
        return e2Embarazada;
    }

    public void setE2Embarazada(String embarazada) {
        this.e2Embarazada = embarazada;
    }

    @Column(name = "E2_RECUERDA_SEMANAS_EMB", length = 4, nullable = true)
    public String getE2RecuerdaSemanasEmb() {
        return e2RecuerdaSemanasEmb;
    }

    public void setE2RecuerdaSemanasEmb(String recuerdaSemanasEmb) {
        this.e2RecuerdaSemanasEmb = recuerdaSemanasEmb;
    }

    @Column(name = "E2_SEMANAS_EMBARAZO", nullable = true)
    public Integer getE2SemanasEmbarazo() {
        return e2SemanasEmbarazo;
    }

    public void setE2SemanasEmbarazo(Integer semanasEmbarazo) {
        this.e2SemanasEmbarazo = semanasEmbarazo;
    }

    @Column(name = "E2_FINAL_EMBARAZO", length = 4, nullable = true)
    public String getE2FinalEmbarazo() {
        return e2FinalEmbarazo;
    }

    public void setE2FinalEmbarazo(String finalEmbarazo) {
        this.e2FinalEmbarazo = finalEmbarazo;
    }

    @Column(name = "E2_OTRO_FINAL_EMBARAZO", nullable = true)
    public String getE2OtroFinalEmbarazo() {
        return e2OtroFinalEmbarazo;
    }

    public void setE2OtroFinalEmbarazo(String otroFinalEmbarazo) {
        this.e2OtroFinalEmbarazo = otroFinalEmbarazo;
    }

    @Column(name = "E2_DABA_PECHO", length = 4, nullable = true)
    public String getE2DabaPecho() {
        return e2DabaPecho;
    }

    public void setE2DabaPecho(String dabaPecho) {
        this.e2DabaPecho = dabaPecho;
    }
    //evento3
    @Column(name = "E3_FUMADO_PREVIO_ENFERMEDAD", length = 4, nullable = true)
    public String getE3FumadoPrevioEnfermedad() {
        return e3FumadoPrevioEnfermedad;
    }

    public void setE3FumadoPrevioEnfermedad(String fumadoPrevioEnfermedad) {
        this.e3FumadoPrevioEnfermedad = fumadoPrevioEnfermedad;
    }

    @Column(name = "E3_FUMA_ACTUALMENTE", length = 4, nullable = true)
    public String getE3FumaActualmente() {
        return e3FumaActualmente;
    }

    public void setE3FumaActualmente(String fumaActualmente) {
        this.e3FumaActualmente = fumaActualmente;
    }

    @Column(name = "E3_EMBARAZADA", length = 4, nullable = true)
    public String getE3Embarazada() {
        return e3Embarazada;
    }

    public void setE3Embarazada(String embarazada) {
        this.e3Embarazada = embarazada;
    }

    @Column(name = "E3_RECUERDA_SEMANAS_EMB", length = 4, nullable = true)
    public String getE3RecuerdaSemanasEmb() {
        return e3RecuerdaSemanasEmb;
    }

    public void setE3RecuerdaSemanasEmb(String recuerdaSemanasEmb) {
        this.e3RecuerdaSemanasEmb = recuerdaSemanasEmb;
    }

    @Column(name = "E3_SEMANAS_EMBARAZO", nullable = true)
    public Integer getE3SemanasEmbarazo() {
        return e3SemanasEmbarazo;
    }

    public void setE3SemanasEmbarazo(Integer semanasEmbarazo) {
        this.e3SemanasEmbarazo = semanasEmbarazo;
    }

    @Column(name = "E3_FINAL_EMBARAZO", length = 4, nullable = true)
    public String getE3FinalEmbarazo() {
        return e3FinalEmbarazo;
    }

    public void setE3FinalEmbarazo(String finalEmbarazo) {
        this.e3FinalEmbarazo = finalEmbarazo;
    }

    @Column(name = "E3_OTRO_FINAL_EMBARAZO", nullable = true)
    public String getE3OtroFinalEmbarazo() {
        return e3OtroFinalEmbarazo;
    }

    public void setE3OtroFinalEmbarazo(String otroFinalEmbarazo) {
        this.e3OtroFinalEmbarazo = otroFinalEmbarazo;
    }

    @Column(name = "E3_DABA_PECHO", length = 4, nullable = true)
    public String getE3DabaPecho() {
        return e3DabaPecho;
    }

    public void setE3DabaPecho(String dabaPecho) {
        this.e3DabaPecho = dabaPecho;
    }
    //una vez
    @Column(name = "TRABAJADOR_SALUD", length = 4, nullable = true)
    public String getTrabajadorSalud() {
        return trabajadorSalud;
    }

    public void setTrabajadorSalud(String trabajadorSalud) {
        this.trabajadorSalud = trabajadorSalud;
    }

    @Column(name = "VACUNADO_COVID19", length = 4, nullable = true)
    public String getVacunadoCovid19() {
        return vacunadoCovid19;
    }

    public void setVacunadoCovid19(String vacunadoCovid19) {
        this.vacunadoCovid19 = vacunadoCovid19;
    }

    @Column(name = "MUESTRA_TARJETA_VACUNA", length = 4, nullable = true)
    public String getMuestraTarjetaVac() {
        return muestraTarjetaVac;
    }

    public void setMuestraTarjetaVac(String muestraTarjetaVac) {
        this.muestraTarjetaVac = muestraTarjetaVac;
    }

    @Column(name = "SABE_NOMBRE_VACUNA", length = 4, nullable = true)
    public String getSabeNombreVacuna() {
        return sabeNombreVacuna;
    }

    public void setSabeNombreVacuna(String sabeNombreVacuna) {
        this.sabeNombreVacuna = sabeNombreVacuna;
    }

    @Column(name = "NOMBRE_VACUNA", length = 128, nullable = true)
    public String getNombreVacuna() {
        return nombreVacuna;
    }

    public void setNombreVacuna(String nombreVacuna) {
        this.nombreVacuna = nombreVacuna;
    }

    @Column(name = "ANIO_VACUNA", length = 4, nullable = true)
    public String getAnioVacuna() {
        return anioVacuna;
    }

    public void setAnioVacuna(String anioVacuna) {
        this.anioVacuna = anioVacuna;
    }

    @Column(name = "MES_VACUNA", length = 4, nullable = true)
    public String getMesVacuna() {
        return mesVacuna;
    }

    public void setMesVacuna(String mesVacuna) {
        this.mesVacuna = mesVacuna;
    }

    @Column(name = "CUANTAS_DOSIS_TARJETA", length = 4, nullable = true)
    public String getCuantasDosis() {
        return cuantasDosis;
    }

    public void setCuantasDosis(String cuantasDosis) {
        this.cuantasDosis = cuantasDosis;
    }

    @Column(name = "D1_NOMBRE_VACUNA", length = 4, nullable = true)
    public String getNombreDosis1() {
        return nombreDosis1;
    }

    public void setNombreDosis1(String nombreDosis1) {
        this.nombreDosis1 = nombreDosis1;
    }

    @Column(name = "D1_OTRA_VACUNA", length = 128, nullable = true)
    public String getOtraVacunaDosis1() {
        return otraVacunaDosis1;
    }

    public void setOtraVacunaDosis1(String otraVacunaDosis1) {
        this.otraVacunaDosis1 = otraVacunaDosis1;
    }

    @Column(name = "D1_LOTE_VACUNA", length = 32, nullable = true)
    public String getLoteDosis1() {
        return loteDosis1;
    }

    public void setLoteDosis1(String loteDosis1) {
        this.loteDosis1 = loteDosis1;
    }

    @Column(name = "D1_FECHA_VACUNA", length = 4, nullable = true)
    public Date getFechaDosis1() {
        return fechaDosis1;
    }

    public void setFechaDosis1(Date fechaDosis1) {
        this.fechaDosis1 = fechaDosis1;
    }

    @Column(name = "D1_PRESENTO_SINTOMAS", length = 4, nullable = true)
    public String getPresentoSintomasDosis1() {
        return presentoSintomasDosis1;
    }

    public void setPresentoSintomasDosis1(String presentoSintomasDosis1) {
        this.presentoSintomasDosis1 = presentoSintomasDosis1;
    }

    @Column(name = "D1_DOLOR_SITIO", length = 4, nullable = true)
    public String getDolorSitioDosis1() {
        return dolorSitioDosis1;
    }

    public void setDolorSitioDosis1(String dolorSitioDosis1) {
        this.dolorSitioDosis1 = dolorSitioDosis1;
    }

    @Column(name = "D1_FIEBRE", length = 4, nullable = true)
    public String getFiebreDosis1() {
        return fiebreDosis1;
    }

    public void setFiebreDosis1(String fiebreDosis1) {
        this.fiebreDosis1 = fiebreDosis1;
    }

    @Column(name = "D1_CANSANCIO", length = 4, nullable = true)
    public String getCansancioDosis1() {
        return cansancioDosis1;
    }

    public void setCansancioDosis1(String cansancioDosis1) {
        this.cansancioDosis1 = cansancioDosis1;
    }

    @Column(name = "D1_DOLOR_CABEZA", length = 4, nullable = true)
    public String getDolorCabezaDosis1() {
        return dolorCabezaDosis1;
    }

    public void setDolorCabezaDosis1(String dolorCabezaDosis1) {
        this.dolorCabezaDosis1 = dolorCabezaDosis1;
    }

    @Column(name = "D1_DIARREA", length = 4, nullable = true)
    public String getDiarreaDosis1() {
        return diarreaDosis1;
    }

    public void setDiarreaDosis1(String diarreaDosis1) {
        this.diarreaDosis1 = diarreaDosis1;
    }

    @Column(name = "D1_VOMITO", length = 4, nullable = true)
    public String getVomitoDosis1() {
        return vomitoDosis1;
    }

    public void setVomitoDosis1(String vomitoDosis1) {
        this.vomitoDosis1 = vomitoDosis1;
    }

    @Column(name = "D1_DOLOR_MUSCULAR", length = 4, nullable = true)
    public String getDolorMuscularDosis1() {
        return dolorMuscularDosis1;
    }

    public void setDolorMuscularDosis1(String dolorMuscularDosis1) {
        this.dolorMuscularDosis1 = dolorMuscularDosis1;
    }

    @Column(name = "D1_ESCALOFRIOS", length = 4, nullable = true)
    public String getEscalofriosDosis1() {
        return escalofriosDosis1;
    }

    public void setEscalofriosDosis1(String escalofriosDosis1) {
        this.escalofriosDosis1 = escalofriosDosis1;
    }

    @Column(name = "D1_REACCION_ALERGICA", length = 4, nullable = true)
    public String getReaccionAlergicaDosis1() {
        return reaccionAlergicaDosis1;
    }

    public void setReaccionAlergicaDosis1(String reaccionAlergicaDosis1) {
        this.reaccionAlergicaDosis1 = reaccionAlergicaDosis1;
    }

    @Column(name = "D1_INFECCION_SITIO", length = 4, nullable = true)
    public String getInfeccionSitioDosis1() {
        return infeccionSitioDosis1;
    }

    public void setInfeccionSitioDosis1(String infeccionSitioDosis1) {
        this.infeccionSitioDosis1 = infeccionSitioDosis1;
    }

    @Column(name = "D1_NAUSEAS", length = 4, nullable = true)
    public String getNauseasDosis1() {
        return nauseasDosis1;
    }

    public void setNauseasDosis1(String nauseasDosis1) {
        this.nauseasDosis1 = nauseasDosis1;
    }

    @Column(name = "D1_BULTOS", length = 4, nullable = true)
    public String getBultosDosis1() {
        return bultosDosis1;
    }

    public void setBultosDosis1(String bultosDosis1) {
        this.bultosDosis1 = bultosDosis1;
    }

    @Column(name = "D1_OTROS_SINTOMAS", length = 4, nullable = true)
    public String getOtrosDosis1() {
        return otrosDosis1;
    }

    public void setOtrosDosis1(String otrosDosis1) {
        this.otrosDosis1 = otrosDosis1;
    }

    @Column(name = "D1_DESC_OTROS_SINTOMAS", length = 255, nullable = true)
    public String getDesOtrosDosis1() {
        return desOtrosDosis1;
    }

    public void setDesOtrosDosis1(String desOtrosDosis1) {
        this.desOtrosDosis1 = desOtrosDosis1;
    }

    //Dosis2
    @Column(name = "D2_NOMBRE_VACUNA", length = 4, nullable = true)
    public String getNombreDosis2() {
        return nombreDosis2;
    }

    public void setNombreDosis2(String nombreDosis2) {
        this.nombreDosis2 = nombreDosis2;
    }

    @Column(name = "D2_OTRA_VACUNA", length = 128, nullable = true)
    public String getOtraVacunaDosis2() {
        return otraVacunaDosis2;
    }

    public void setOtraVacunaDosis2(String otraVacunaDosis2) {
        this.otraVacunaDosis2 = otraVacunaDosis2;
    }

    @Column(name = "D2_LOTE_VACUNA", length = 32, nullable = true)
    public String getLoteDosis2() {
        return loteDosis2;
    }

    public void setLoteDosis2(String loteDosis2) {
        this.loteDosis2 = loteDosis2;
    }

    @Column(name = "D2_FECHA_VACUNA", length = 4, nullable = true)
    public Date getFechaDosis2() {
        return fechaDosis2;
    }

    public void setFechaDosis2(Date fechaDosis2) {
        this.fechaDosis2 = fechaDosis2;
    }

    @Column(name = "D2_PRESENTO_SINTOMAS", length = 4, nullable = true)
    public String getPresentoSintomasDosis2() {
        return presentoSintomasDosis2;
    }

    public void setPresentoSintomasDosis2(String presentoSintomasDosis2) {
        this.presentoSintomasDosis2 = presentoSintomasDosis2;
    }

    @Column(name = "D2_DOLOR_SITIO", length = 4, nullable = true)
    public String getDolorSitioDosis2() {
        return dolorSitioDosis2;
    }

    public void setDolorSitioDosis2(String dolorSitioDosis2) {
        this.dolorSitioDosis2 = dolorSitioDosis2;
    }

    @Column(name = "D2_FIEBRE", length = 4, nullable = true)
    public String getFiebreDosis2() {
        return fiebreDosis2;
    }

    public void setFiebreDosis2(String fiebreDosis2) {
        this.fiebreDosis2 = fiebreDosis2;
    }

    @Column(name = "D2_CANSANCIO", length = 4, nullable = true)
    public String getCansancioDosis2() {
        return cansancioDosis2;
    }

    public void setCansancioDosis2(String cansancioDosis2) {
        this.cansancioDosis2 = cansancioDosis2;
    }

    @Column(name = "D2_DOLOR_CABEZA", length = 4, nullable = true)
    public String getDolorCabezaDosis2() {
        return dolorCabezaDosis2;
    }

    public void setDolorCabezaDosis2(String dolorCabezaDosis2) {
        this.dolorCabezaDosis2 = dolorCabezaDosis2;
    }

    @Column(name = "D2_DIARREA", length = 4, nullable = true)
    public String getDiarreaDosis2() {
        return diarreaDosis2;
    }

    public void setDiarreaDosis2(String diarreaDosis2) {
        this.diarreaDosis2 = diarreaDosis2;
    }

    @Column(name = "D2_VOMITO", length = 4, nullable = true)
    public String getVomitoDosis2() {
        return vomitoDosis2;
    }

    public void setVomitoDosis2(String vomitoDosis2) {
        this.vomitoDosis2 = vomitoDosis2;
    }

    @Column(name = "D2_DOLOR_MUSCULAR", length = 4, nullable = true)
    public String getDolorMuscularDosis2() {
        return dolorMuscularDosis2;
    }

    public void setDolorMuscularDosis2(String dolorMuscularDosis2) {
        this.dolorMuscularDosis2 = dolorMuscularDosis2;
    }

    @Column(name = "D2_ESCALOFRIOS", length = 4, nullable = true)
    public String getEscalofriosDosis2() {
        return escalofriosDosis2;
    }

    public void setEscalofriosDosis2(String escalofriosDosis2) {
        this.escalofriosDosis2 = escalofriosDosis2;
    }

    @Column(name = "D2_REACCION_ALERGICA", length = 4, nullable = true)
    public String getReaccionAlergicaDosis2() {
        return reaccionAlergicaDosis2;
    }

    public void setReaccionAlergicaDosis2(String reaccionAlergicaDosis2) {
        this.reaccionAlergicaDosis2 = reaccionAlergicaDosis2;
    }

    @Column(name = "D2_INFECCION_SITIO", length = 4, nullable = true)
    public String getInfeccionSitioDosis2() {
        return infeccionSitioDosis2;
    }

    public void setInfeccionSitioDosis2(String infeccionSitioDosis2) {
        this.infeccionSitioDosis2 = infeccionSitioDosis2;
    }

    @Column(name = "D2_NAUSEAS", length = 4, nullable = true)
    public String getNauseasDosis2() {
        return nauseasDosis2;
    }

    public void setNauseasDosis2(String nauseasDosis2) {
        this.nauseasDosis2 = nauseasDosis2;
    }

    @Column(name = "D2_BULTOS", length = 4, nullable = true)
    public String getBultosDosis2() {
        return bultosDosis2;
    }

    public void setBultosDosis2(String bultosDosis2) {
        this.bultosDosis2 = bultosDosis2;
    }

    @Column(name = "D2_OTROS_SINTOMAS", length = 4, nullable = true)
    public String getOtrosDosis2() {
        return otrosDosis2;
    }

    public void setOtrosDosis2(String otrosDosis2) {
        this.otrosDosis2 = otrosDosis2;
    }

    @Column(name = "D2_DESC_OTROS_SINTOMAS", length = 255, nullable = true)
    public String getDesOtrosDosis2() {
        return desOtrosDosis2;
    }

    public void setDesOtrosDosis2(String desOtrosDosis2) {
        this.desOtrosDosis2 = desOtrosDosis2;
    }
//Dosis3
@Column(name = "D3_NOMBRE_VACUNA", length = 4, nullable = true)
public String getNombreDosis3() {
    return nombreDosis3;
}

    public void setNombreDosis3(String nombreDosis3) {
        this.nombreDosis3 = nombreDosis3;
    }

    @Column(name = "D3_OTRA_VACUNA", length = 128, nullable = true)
    public String getOtraVacunaDosis3() {
        return otraVacunaDosis3;
    }

    public void setOtraVacunaDosis3(String otraVacunaDosis3) {
        this.otraVacunaDosis3 = otraVacunaDosis3;
    }

    @Column(name = "D3_LOTE_VACUNA", length = 32, nullable = true)
    public String getLoteDosis3() {
        return loteDosis3;
    }

    public void setLoteDosis3(String loteDosis3) {
        this.loteDosis3 = loteDosis3;
    }

    @Column(name = "D3_FECHA_VACUNA", length = 4, nullable = true)
    public Date getFechaDosis3() {
        return fechaDosis3;
    }

    public void setFechaDosis3(Date fechaDosis3) {
        this.fechaDosis3 = fechaDosis3;
    }

    @Column(name = "D3_PRESENTO_SINTOMAS", length = 4, nullable = true)
    public String getPresentoSintomasDosis3() {
        return presentoSintomasDosis3;
    }

    public void setPresentoSintomasDosis3(String presentoSintomasDosis3) {
        this.presentoSintomasDosis3 = presentoSintomasDosis3;
    }

    @Column(name = "D3_DOLOR_SITIO", length = 4, nullable = true)
    public String getDolorSitioDosis3() {
        return dolorSitioDosis3;
    }

    public void setDolorSitioDosis3(String dolorSitioDosis3) {
        this.dolorSitioDosis3 = dolorSitioDosis3;
    }

    @Column(name = "D3_FIEBRE", length = 4, nullable = true)
    public String getFiebreDosis3() {
        return fiebreDosis3;
    }

    public void setFiebreDosis3(String fiebreDosis3) {
        this.fiebreDosis3 = fiebreDosis3;
    }

    @Column(name = "D3_CANSANCIO", length = 4, nullable = true)
    public String getCansancioDosis3() {
        return cansancioDosis3;
    }

    public void setCansancioDosis3(String cansancioDosis3) {
        this.cansancioDosis3 = cansancioDosis3;
    }

    @Column(name = "D3_DOLOR_CABEZA", length = 4, nullable = true)
    public String getDolorCabezaDosis3() {
        return dolorCabezaDosis3;
    }

    public void setDolorCabezaDosis3(String dolorCabezaDosis3) {
        this.dolorCabezaDosis3 = dolorCabezaDosis3;
    }

    @Column(name = "D3_DIARREA", length = 4, nullable = true)
    public String getDiarreaDosis3() {
        return diarreaDosis3;
    }

    public void setDiarreaDosis3(String diarreaDosis3) {
        this.diarreaDosis3 = diarreaDosis3;
    }

    @Column(name = "D3_VOMITO", length = 4, nullable = true)
    public String getVomitoDosis3() {
        return vomitoDosis3;
    }

    public void setVomitoDosis3(String vomitoDosis3) {
        this.vomitoDosis3 = vomitoDosis3;
    }

    @Column(name = "D3_DOLOR_MUSCULAR", length = 4, nullable = true)
    public String getDolorMuscularDosis3() {
        return dolorMuscularDosis3;
    }

    public void setDolorMuscularDosis3(String dolorMuscularDosis3) {
        this.dolorMuscularDosis3 = dolorMuscularDosis3;
    }

    @Column(name = "D3_ESCALOFRIOS", length = 4, nullable = true)
    public String getEscalofriosDosis3() {
        return escalofriosDosis3;
    }

    public void setEscalofriosDosis3(String escalofriosDosis3) {
        this.escalofriosDosis3 = escalofriosDosis3;
    }

    @Column(name = "D3_REACCION_ALERGICA", length = 4, nullable = true)
    public String getReaccionAlergicaDosis3() {
        return reaccionAlergicaDosis3;
    }

    public void setReaccionAlergicaDosis3(String reaccionAlergicaDosis3) {
        this.reaccionAlergicaDosis3 = reaccionAlergicaDosis3;
    }

    @Column(name = "D3_INFECCION_SITIO", length = 4, nullable = true)
    public String getInfeccionSitioDosis3() {
        return infeccionSitioDosis3;
    }

    public void setInfeccionSitioDosis3(String infeccionSitioDosis3) {
        this.infeccionSitioDosis3 = infeccionSitioDosis3;
    }

    @Column(name = "D3_NAUSEAS", length = 4, nullable = true)
    public String getNauseasDosis3() {
        return nauseasDosis3;
    }

    public void setNauseasDosis3(String nauseasDosis3) {
        this.nauseasDosis3 = nauseasDosis3;
    }

    @Column(name = "D3_BULTOS", length = 4, nullable = true)
    public String getBultosDosis3() {
        return bultosDosis3;
    }

    public void setBultosDosis3(String bultosDosis3) {
        this.bultosDosis3 = bultosDosis3;
    }

    @Column(name = "D3_OTROS_SINTOMAS", length = 4, nullable = true)
    public String getOtrosDosis3() {
        return otrosDosis3;
    }

    public void setOtrosDosis3(String otrosDosis3) {
        this.otrosDosis3 = otrosDosis3;
    }

    @Column(name = "D3_DESC_OTROS_SINTOMAS", length = 255, nullable = true)
    public String getDesOtrosDosis3() {
        return desOtrosDosis3;
    }

    public void setDesOtrosDosis3(String desOtrosDosis3) {
        this.desOtrosDosis3 = desOtrosDosis3;
    }
    //una vez
    @Column(name = "COVID_POSTERIOR_VACUNAS", length = 4, nullable = true)
    public String getCovid19PosteriorVacuna() {
        return covid19PosteriorVacuna;
    }

    public void setCovid19PosteriorVacuna(String covid19PosteriorVacuna) {
        this.covid19PosteriorVacuna = covid19PosteriorVacuna;
    }

    @Column(name = "FECHA_EVENTO_POST_VAC", length = 16, nullable = true)
    public String getFechaEventoEnfermoPostVac() {
        return fechaEventoEnfermoPostVac;
    }

    public void setFechaEventoEnfermoPostVac(String fechaEventoEnfermoPostVac) {
        this.fechaEventoEnfermoPostVac = fechaEventoEnfermoPostVac;
    }

    @Column(name = "SABE_FECHA_COV_POST_VAC", length = 4, nullable = true)
    public String getSabeFechaEnfPostVac() {
        return sabeFechaEnfPostVac;
    }

    public void setSabeFechaEnfPostVac(String sabeFechaEnfPostVac) {
        this.sabeFechaEnfPostVac = sabeFechaEnfPostVac;
    }

    @Column(name = "FECHA_COV_POST_VAC", length = 4, nullable = true)
    public Date getFechaEnfPostVac() {
        return fechaEnfPostVac;
    }

    public void setFechaEnfPostVac(Date fechaEnfPostVac) {
        this.fechaEnfPostVac = fechaEnfPostVac;
    }

    @Column(name = "ANIO_COV_POST_VAC", length = 4, nullable = true)
    public String getAnioEnfPostVac() {
        return anioEnfPostVac;
    }

    public void setAnioEnfPostVac(String anioEnfPostVac) {
        this.anioEnfPostVac = anioEnfPostVac;
    }

    @Column(name = "MES_COV_POST_VAC", length = 4, nullable = true)
    public String getMesEnfPostVac() {
        return mesEnfPostVac;
    }

    public void setMesEnfPostVac(String mesEnfPostVac) {
        this.mesEnfPostVac = mesEnfPostVac;
    }

    @Column(name = "PERIODO_SINTOMAS", length = 16, nullable = true)
    public String getPeriodoSintomas() {
        return periodoSintomas;
    }

    public void setPeriodoSintomas(String periodoSintomas) {
        this.periodoSintomas = periodoSintomas;
    }

    @JsonIgnore
    @Column(name = "FECHA_RECIBIDO")
    public Date getFechaRecibido() {
        return fechaRecibido;
    }

    public void setFechaRecibido(Date fechaRecibido) {
        this.fechaRecibido = fechaRecibido;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CuestionarioCovid19 that = (CuestionarioCovid19) o;

        if (!codigo.equals(that.codigo)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return codigo.hashCode();
    }

    @Override
    public String toString() {
        return "CuestionarioCovid19{" +
                "codigo='" + codigo + '\'' +
                ", participante=" + participante +
                '}';
    }

    @Override
    public boolean isFieldAuditable(String fieldname) {
        return true;
    }
}
