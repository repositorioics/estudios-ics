package ni.org.ics.estudios.service.comparacionScan;

import ni.org.ics.estudios.dto.cartas.ComparacionCartasDto;
import ni.org.ics.estudios.dto.cartas.ComparacionRelFamCartasDto;
import ni.org.ics.estudios.dto.cartas.DiferenciaParteCartaDto;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by miguel on 7/1/2022.
 */
@Service("comparacionCartasService")
@Transactional
public class ComparacionCartasService {

    @Resource(name = "sessionFactory")
    private SessionFactory sessionFactory;


    @SuppressWarnings("unchecked")
    public List<DiferenciaParteCartaDto> getDiferenciasPartesCartas()
    {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createSQLQuery("select a.CODIGO_PARTICIPANTE as codigo, a.FECHA_FIRMA as fechaFirma, a.USUARIO_REGISTRO as usuarioRegistro, " +
                        "a.ACEPTA_PARTE_A as aceptaParteACc, b.ACEPTA_PARTE_A as aceptaParteASc, " +
                        "a.EDAD_ACTUAL_MESES as edadActualMeses, a.EDAD_MESES as edadMeses, " +
                        "a.ACEPTA_PARTE_B as aceptaParteBCc, b.ACEPTA_PARTE_B as aceptaParteBSc, " +
                        "a.ACEPTA_PARTE_C as aceptaParteCCc, b.ACEPTA_PARTE_C as aceptaParteCSc, " +
                        "a.ACEPTA_PARTE_D as aceptaParteDCc, b.ACEPTA_PARTE_D as aceptaParteDSc, " +
                        "a.ACEPTA_PARTE_E as aceptaParteECc, b.ACEPTA_PARTE_E as aceptaParteESc," +
                        "a.ACEPTA_PARTE_F as aceptaParteFCc, b.ACEPTA_PARTE_F as aceptaParteFSc,  " +
                        "a.ACEPTA_CONTACTO_FUTURO as aceptaContactoFuturoCc, b.ACEPTA_CONTACTO_FUTURO as aceptaContactoFuturoSc, " +
                        "a.ASENTIMIENTO_VERBAL as asentimientoVerbalCc, b.ASENTIMIENTO_VERBAL as asentimientoVerbalSc, " +
                        "a.CODIGO_ESTUDIO as estudio, " +
                        "a.VERSION as version " +
                        "from ( " +
                        "select t1.CODIGO_PARTICIPANTE, DATE_FORMAT(t1.FECHA_FIRMA, '%d-%m-%Y') as FECHA_FIRMA, t1.USUARIO_REGISTRO AS USUARIO_REGISTRO, " +
                        "fn_edad_actual_meses(t3.FECHANAC) as EDAD_ACTUAL_MESES, fn_edad_meses(t3.FECHANAC, t1.FECHA_FIRMA) as EDAD_MESES, " +
                        "t1.ACEPTA_PARTE_A, t1.ACEPTA_PARTE_B, t1.ACEPTA_PARTE_C, t1.ACEPTA_PARTE_D, t1.ACEPTA_PARTE_E, t1.ACEPTA_PARTE_F," +
                        "t1.ACEPTA_CONTACTO_FUTURO, COALESCE(t2.ASENTIMIENTO_VERBAL, 'NA') as ASENTIMIENTO_VERBAL, concat(t1.VERSION, '.0') as VERSION, t2.CODIGO_ESTUDIO as CODIGO_ESTUDIO " +
                        "FROM cartas_consentimientos t1 inner join tamizajes t2 on t1.CODIGO_TAMIZAJE = t2.CODIGO " +
                        "inner join participantes t3 on t1.CODIGO_PARTICIPANTE = t3.CODIGO " +
                        "where t1.FECHA_FIRMA >=  str_to_date('10/02/2023', '%d/%m/%Y')" + //FECHA INICIO DE SCAN WEB OFICIAL 15-03-2022. MA2022
                        ") as a, " +
                        "(select t1.CODIGO_PARTICIPANTE, DATE_FORMAT(t1.FECHA_CARTA, '%d-%m-%Y') as FECHA_CARTA," +
                        "( select if(t3.ACEPTA, '1','0') from scan_detalle_parte t3 inner join scan_catalog_parte t2 on t3.IDPARTE = t2.IDPARTE " +
                        "where t3.IDPARTICIPANTECARTA = t1.IDPARTICIPANTECARTA and  (t2.PARTE = 'A' or t2.PARTE = 'Parte A')) as ACEPTA_PARTE_A, " +
                        "( select if(t3.ACEPTA, '1','0') from scan_detalle_parte t3 inner join scan_catalog_parte t2 on t3.IDPARTE = t2.IDPARTE " +
                        "where t3.IDPARTICIPANTECARTA = t1.IDPARTICIPANTECARTA and  (t2.PARTE = 'B' or t2.PARTE = 'Parte B')) as ACEPTA_PARTE_B, " +
                        "( select if(t3.ACEPTA, '1','0') from scan_detalle_parte t3 inner join scan_catalog_parte t2 on t3.IDPARTE = t2.IDPARTE " +
                        "where t3.IDPARTICIPANTECARTA = t1.IDPARTICIPANTECARTA and  (t2.PARTE = 'C' or t2.PARTE = 'Parte C')) as ACEPTA_PARTE_C, " +
                        "( select if(t3.ACEPTA, '1','0') from scan_detalle_parte t3 inner join scan_catalog_parte t2 on t3.IDPARTE = t2.IDPARTE " +
                        "where t3.IDPARTICIPANTECARTA = t1.IDPARTICIPANTECARTA and  (t2.PARTE = 'D' or t2.PARTE = 'Parte D')) as ACEPTA_PARTE_D, " +
                        "( select if(t3.ACEPTA, '1','0') from scan_detalle_parte t3 inner join scan_catalog_parte t2 on t3.IDPARTE = t2.IDPARTE " +
                        "where t3.IDPARTICIPANTECARTA = t1.IDPARTICIPANTECARTA and  (t2.PARTE = 'E' or t2.PARTE = 'Parte E')) as ACEPTA_PARTE_E, " +
                        "( select if(t3.ACEPTA, '1','0') from scan_detalle_parte t3 inner join scan_catalog_parte t2 on t3.IDPARTE = t2.IDPARTE " +
                        "where t3.IDPARTICIPANTECARTA = t1.IDPARTICIPANTECARTA and  (t2.PARTE = 'F' or t2.PARTE = 'Parte F')) as ACEPTA_PARTE_F, " +
                        "if(t1.CONTACTO_FUTURO, '1','0') as ACEPTA_CONTACTO_FUTURO, if (t1.TIPO_ASENTIMIENTO='1', t1.ASENTIMIENTO, 'NA') as ASENTIMIENTO_VERBAL, " +
                        "tv.VERSION as VERSION, tv.CODIGO_ESTUDIO as CODIGO_ESTUDIO " +
                        "from scan_participante_carta t1 inner join scan_catalog_version tv on t1.IDVERSION = tv.IDVERSION WHERE t1.FECHA_CARTA >= str_to_date('10-02-2023','%d-%m-%Y')) as b " +
                        "where a.CODIGO_PARTICIPANTE = b.CODIGO_PARTICIPANTE and a.FECHA_FIRMA = b.FECHA_CARTA and a.VERSION = b.VERSION " +
                        "and ( " +
                        "a.ACEPTA_PARTE_A != b.ACEPTA_PARTE_A or " +
                        "a.ACEPTA_PARTE_B != b.ACEPTA_PARTE_B or " +
                        "a.ACEPTA_PARTE_C != b.ACEPTA_PARTE_C or " +
                        "a.ACEPTA_PARTE_D != b.ACEPTA_PARTE_D or " +
                        "a.ACEPTA_PARTE_E != b.ACEPTA_PARTE_E or " +
                        "a.ACEPTA_PARTE_F != b.ACEPTA_PARTE_F)"
        );
        query.setResultTransformer(Transformers.aliasToBean(DiferenciaParteCartaDto.class));
        return query.list();
    }

    public List<ComparacionCartasDto> getConsentimientosSinCarta() {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createSQLQuery("SELECT t1.CODIGO_PARTICIPANTE as codigoParticipante, DATE_FORMAT(t1.FECHA_FIRMA, '%d-%m-%Y') as fechaFirma, FLOOR(fn_edad_actual_meses(t3.FECHANAC)/12) as edadActual, t1.USUARIO_REGISTRO as usuarioRegistro, "+
                "t1.ACEPTA_CONTACTO_FUTURO as contactoFuturo, COALESCE(t4.ASENTIMIENTO_VERBAL, '-') as asentimiento, t1.ACEPTA_PARTE_A as parteA, t1.ACEPTA_PARTE_B as parteB, t1.ACEPTA_PARTE_C as parteC, "+
                "concat(t1.NOMBRE1_TUTOR, IF(t1.NOMBRE2_TUTOR is not null,' ', ''), COALESCE(t1.NOMBRE2_TUTOR,''), ' ', t1.APELLIDO1_TUTOR, IF(t1.APELLIDO2_TUTOR is not null,' ', ''), COALESCE(t1.APELLIDO2_TUTOR, '')) as quienFirma, "+
        "(select m.es from mensajes m where m.catRoot = 'CP_CAT_RFTUTOR' and m.catKey = t1.RELACION_FAMILIAR) as relacionFamiliar, t1.VERSION as versionCarta, es.NOMBRE as estudio "+
        "FROM cartas_consentimientos t1 "+
        "left join scan_participante_carta t2 on t1.CODIGO_PARTICIPANTE = t2.CODIGO_PARTICIPANTE "+
        "inner join participantes t3 on t1.CODIGO_PARTICIPANTE = t3.CODIGO " +
        "inner join tamizajes t4 on t1.CODIGO_TAMIZAJE = t4.CODIGO "+
        "inner join estudios es on t4.CODIGO_ESTUDIO = es.CODIGO "+
        "WHERE (t2.CODIGO_PARTICIPANTE Is Null) " +
        "and t1.FECHA_FIRMA >=  str_to_date('15/03/2022', '%d/%m/%Y') " + //FECHA INICIO DE SCAN WEB OFICIAL 15-03-2022. MA2022
        "order by t1.CODIGO_PARTICIPANTE");
        query.setResultTransformer(Transformers.aliasToBean(ComparacionCartasDto.class));
        return query.list();
    }


    @SuppressWarnings("unchecked")
    public List<DiferenciaParteCartaDto> getDiferenciasPartesCartasG()
    {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createSQLQuery("select a.CODIGO_PARTICIPANTE as codigo, a.FECHA_FIRMA as fechaFirma, a.USUARIO_REGISTRO as usuarioRegistro, " +
                "a.EDAD_ACTUAL_MESES as edadActualMeses, a.EDAD_MESES as edadMeses,  " +
                "a.ACEPTA_PARTE_G AS aceptaParteGCc, b.ACEPTA_PARTE_G AS aceptaParteGSc, " +
                "a.ACEPTA_CONTACTO_FUTURO as aceptaContactoFuturoCc, b.ACEPTA_CONTACTO_FUTURO as aceptaContactoFuturoSc," +
                "a.ASENTIMIENTO_VERBAL as asentimientoVerbalCc, b.ASENTIMIENTO_VERBAL as asentimientoVerbalSc, " +
                "a.CODIGO_ESTUDIO as estudio, " +
                "a.VERSION as version " +
                "from ( SELECT t1.CODIGO_PARTICIPANTE, DATE_FORMAT(t1.FECHA_FIRMA, '%d-%m-%Y') as FECHA_FIRMA, t1.USUARIO_REGISTRO AS USUARIO_REGISTRO, " +
                "               fn_edad_actual_meses(t3.FECHANAC) as EDAD_ACTUAL_MESES, fn_edad_meses(t3.FECHANAC, t1.FECHA_FIRMA) as EDAD_MESES, t1.ACEPTA_PARTE_G," +
                "               t1.ACEPTA_CONTACTO_FUTURO, COALESCE(t2.ASENTIMIENTO_VERBAL, 'NA') as ASENTIMIENTO_VERBAL, concat(t1.VERSION, '.0') as VERSION, t2.CODIGO_ESTUDIO as CODIGO_ESTUDIO " +
                "        FROM cartas_consentimientos t1 inner join tamizajes t2 on t1.CODIGO_TAMIZAJE = t2.CODIGO " +
                "               inner join participantes t3 on t1.CODIGO_PARTICIPANTE = t3.CODIGO " +
                "        WHERE date(t1.FECHA_FIRMA) >= str_to_date('10-02-2023', '%d-%m-%Y') AND t2.CODIGO_ESTUDIO=4 " +
                ") AS a,(select t1.CODIGO_PARTICIPANTE, DATE_FORMAT(t1.FECHA_CARTA, '%d-%m-%Y') as FECHA_CARTA, " +
                "        (SELECT if(t3.ACEPTA, '1','0') FROM scan_detalle_parte t3 INNER JOIN scan_catalog_parte t2 ON t3.IDPARTE = t2.IDPARTE " +
                "         WHERE t3.IDPARTICIPANTECARTA = t1.IDPARTICIPANTECARTA AND (t2.PARTE = 'G' OR t2.PARTE = 'Parte G')) AS ACEPTA_PARTE_G, " +
                "         if(t1.CONTACTO_FUTURO, '1','0') as ACEPTA_CONTACTO_FUTURO, if (t1.TIPO_ASENTIMIENTO='1', t1.ASENTIMIENTO, 'NA') as ASENTIMIENTO_VERBAL, " +
                "            tv.VERSION as VERSION, tv.CODIGO_ESTUDIO AS CODIGO_ESTUDIO " +
                "         from scan_participante_carta t1 inner join scan_catalog_version tv on t1.IDVERSION = tv.IDVERSION AND t1.FECHA_CARTA >= STR_TO_DATE('10-02-2023','%d-%m-%Y') AND tv.CODIGO_ESTUDIO = 4) as b " +
                "where a.CODIGO_PARTICIPANTE = b.CODIGO_PARTICIPANTE and a.FECHA_FIRMA = b.FECHA_CARTA and a.VERSION = b.VERSION and (a.ACEPTA_PARTE_G != b.ACEPTA_PARTE_G)");
        query.setResultTransformer(Transformers.aliasToBean(DiferenciaParteCartaDto.class));
        return query.list();
    }

    public List<ComparacionRelFamCartasDto> getDiferenciasRelFam(){
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createSQLQuery("select distinct t1.CODIGO_PARTICIPANTE as codigoParticipante, DATE_FORMAT(t1.FECHA_FIRMA, '%d-%m-%Y') as fechaFirma, "+
                "FLOOR(fn_edad_meses(t3.FECHANAC, t1.FECHA_FIRMA)/12) as edadFirma, t1.USUARIO_REGISTRO as usuarioRegistro, "+
                "t1.NOMBRE1_TUTOR as nombre1TutorC, coalesce(t1.NOMBRE2_TUTOR, '') as nombre2TutorC, t1.APELLIDO1_TUTOR as apellido1TutorC, coalesce(t1.APELLIDO2_TUTOR, '') as apellido2TutorC, "+
                "t2.NOMBRE1TUTOR as nombre1TutorS, t2.NOMBRE2TUTOR as nombre2TutorS, t2.APELLIDO1TUTOR as apellido1TutorS, t2.APELLIDO2TUTOR as apellido2TutorS, "+
                "(select m.es from mensajes m where m.catRoot = 'CP_CAT_RFTUTOR' and m.catKey = t1.RELACION_FAMILIAR) as relacionFamiliarC, "+
                "(select m.es from mensajes m where m.catRoot = 'CP_CAT_RFTUTOR' and m.catKey = CAST(t2.RELACION_FAMILIAR AS CHAR)) as relacionFamiliarS, t1.VERSION as versionCarta, es.NOMBRE as estudio "+
                "from cartas_consentimientos t1 inner join tamizajes tt on t1.codigo_tamizaje = tt.codigo "+
                "inner join estudios es on tt.CODIGO_ESTUDIO = es.CODIGO "+
                "inner join scan_participante_carta t2 "+
                "on t1.CODIGO_PARTICIPANTE = t2.CODIGO_PARTICIPANTE "+
                "and date(t1.FECHA_FIRMA) = date(t2.FECHA_CARTA) "+
                "inner join scan_catalog_version cv on cv.IDVERSION = t2.IDVERSION "+
                "inner join participantes t3 on t2.CODIGO_PARTICIPANTE = t3.CODIGO "+
                "where cv.CODIGO_ESTUDIO = tt.CODIGO_ESTUDIO and "+
                "((t1.NOMBRE1_TUTOR != t2.NOMBRE1TUTOR) "+
                "or (t1.NOMBRE2_TUTOR != t2.NOMBRE2TUTOR) "+
                "or (t1.APELLIDO1_TUTOR != t2.APELLIDO1TUTOR) "+
                "or (t1.APELLIDO2_TUTOR != t2.APELLIDO2TUTOR) "+
                "or (t1.RELACION_FAMILIAR != t2.RELACION_FAMILIAR)) " +
                "order by t1.CODIGO_PARTICIPANTE "
        );
        query.setResultTransformer(Transformers.aliasToBean(ComparacionRelFamCartasDto.class));
        return query.list();
    }
}
