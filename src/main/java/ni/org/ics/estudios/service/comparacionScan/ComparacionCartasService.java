package ni.org.ics.estudios.service.comparacionScan;

import ni.org.ics.estudios.dto.ComparacionCartasDto;
import ni.org.ics.estudios.dto.ComparacionRelFamCartasDto;
import ni.org.ics.estudios.dto.DiferenciaParteCartaDto;
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
                "a.EDAD_ACTUAL_MESES as edadActualMeses, a.EDAD_MESES as edadMeses, " +
                "a.ACEPTA_PARTE_A as aceptaParteACc, b.ACEPTA_PARTE_A as aceptaParteASc, " +
                "a.ACEPTA_PARTE_B as aceptaParteBCc, b.ACEPTA_PARTE_B as aceptaParteBSc, " +
                "a.ACEPTA_PARTE_C as aceptaParteCCc, b.ACEPTA_PARTE_C as aceptaParteCSc, " +
                "a.ACEPTA_CONTACTO_FUTURO as aceptaContactoFuturoCc, b.ACEPTA_CONTACTO_FUTURO as aceptaContactoFuturoSc, " +
                "a.ASENTIMIENTO_VERBAL as asentimientoVerbalCc, b.ASENTIMIENTO_VERBAL as asentimientoVerbalSc, " +
                "a.VERSION as versionCc, b.VERSION as versionSc " +
                "from ( " +
                "select t1.CODIGO_PARTICIPANTE, DATE_FORMAT(t1.FECHA_FIRMA, '%d-%m-%Y') as FECHA_FIRMA, t1.USUARIO_REGISTRO AS USUARIO_REGISTRO, " +
                "fn_edad_actual_meses(t3.FECHANAC) as EDAD_ACTUAL_MESES, fn_edad_meses(t3.FECHANAC, t1.FECHA_FIRMA) as EDAD_MESES, " +
                "t1.ACEPTA_PARTE_A, t1.ACEPTA_PARTE_B, t1.ACEPTA_PARTE_C, " +
                "t1.ACEPTA_CONTACTO_FUTURO, COALESCE(t2.ASENTIMIENTO_VERBAL, 'NA') as ASENTIMIENTO_VERBAL, t1.VERSION as VERSION " +
                "FROM cartas_consentimientos t1 inner join tamizajes t2 on t1.CODIGO_TAMIZAJE = t2.CODIGO " +
                "inner join participantes t3 on t1.CODIGO_PARTICIPANTE = t3.CODIGO " +
                ") as a, " +
                "(select t1.CODIGO_PARTICIPANTE, " +
                "( select if(t3.ACEPTA, '1','0') from scan_detalle_parte t3 inner join scan_catalog_parte t2 on t3.IDPARTE = t2.IDPARTE " +
                "where t3.IDPARTICIPANTECARTA = t1.IDPARTICIPANTECARTA and  t2.PARTE = 'A') as ACEPTA_PARTE_A, " +
                "( select if(t3.ACEPTA, '1','0') from scan_detalle_parte t3 inner join scan_catalog_parte t2 on t3.IDPARTE = t2.IDPARTE " +
                "where t3.IDPARTICIPANTECARTA = t1.IDPARTICIPANTECARTA and  t2.PARTE = 'B') as ACEPTA_PARTE_B, " +
                "( select if(t3.ACEPTA, '1','0') from scan_detalle_parte t3 inner join scan_catalog_parte t2 on t3.IDPARTE = t2.IDPARTE " +
                "where t3.IDPARTICIPANTECARTA = t1.IDPARTICIPANTECARTA and  t2.PARTE = 'C') as ACEPTA_PARTE_C, " +
                "if(t1.CONTACTO_FUTURO, '1','0') as ACEPTA_CONTACTO_FUTURO, if (t1.TIPO_ASENTIMIENTO='1', t1.ASENTIMIENTO, 'NA') as ASENTIMIENTO_VERBAL, " +
                "tv.VERSION as VERSION " +
                "from scan_participante_carta t1 inner join scan_catalog_version tv on t1.IDVERSION = tv.IDVERSION) as b " +
                "where a.CODIGO_PARTICIPANTE = b.CODIGO_PARTICIPANTE " +
                "and ( " +
                "a.ACEPTA_PARTE_A != b.ACEPTA_PARTE_A or " +
                "a.ACEPTA_PARTE_B != b.ACEPTA_PARTE_B or " +
                "a.ACEPTA_PARTE_C != b.ACEPTA_PARTE_C)");
        query.setResultTransformer(Transformers.aliasToBean(DiferenciaParteCartaDto.class));
        return query.list();
    }

    public List<ComparacionCartasDto> getConsentimientosSinCarta() {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createSQLQuery("SELECT t1.CODIGO_PARTICIPANTE as codigoParticipante, DATE_FORMAT(t1.FECHA_FIRMA, '%d-%m-%Y') as fechaFirma, FLOOR(fn_edad_actual_meses(t3.FECHANAC)/12) as edadActual, t1.USUARIO_REGISTRO as usuarioRegistro, "+
                "t1.ACEPTA_CONTACTO_FUTURO as contactoFuturo, COALESCE(t4.ASENTIMIENTO_VERBAL, '-') as asentimiento, t1.ACEPTA_PARTE_A as parteA, t1.ACEPTA_PARTE_B as parteB, t1.ACEPTA_PARTE_C as parteC, "+
                "concat(t1.NOMBRE1_TUTOR, IF(t1.NOMBRE2_TUTOR is not null,' ', ''), COALESCE(t1.NOMBRE2_TUTOR,''), ' ', t1.APELLIDO1_TUTOR, IF(t1.APELLIDO2_TUTOR is not null,' ', ''), COALESCE(t1.APELLIDO2_TUTOR, '')) as quienFirma, "+
        "(select m.es from mensajes m where m.catRoot = 'CP_CAT_RFTUTOR' and m.catKey = t1.RELACION_FAMILIAR) as relacionFamiliar, t1.VERSION as versionCarta "+
        "FROM cartas_consentimientos t1 "+
        "left join scan_participante_carta t2 on t1.CODIGO_PARTICIPANTE = t2.CODIGO_PARTICIPANTE "+
        "inner join participantes t3 on t1.CODIGO_PARTICIPANTE = t3.CODIGO "+
        "inner join tamizajes t4 on t1.CODIGO_TAMIZAJE = t4.CODIGO "+
        "WHERE (t2.CODIGO_PARTICIPANTE Is Null) order by t1.CODIGO_PARTICIPANTE");
        query.setResultTransformer(Transformers.aliasToBean(ComparacionCartasDto.class));
        return query.list();
    }

    public List<ComparacionRelFamCartasDto> getDiferenciasRelFam(){
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createSQLQuery("select t1.CODIGO_PARTICIPANTE as codigoParticipante, DATE_FORMAT(t1.FECHA_FIRMA, '%d-%m-%Y') as fechaFirma, "+
                        "FLOOR(fn_edad_meses(t3.FECHANAC, t1.FECHA_FIRMA)/12) as edadFirma, t1.USUARIO_REGISTRO as usuarioRegistro, "+
                        "t1.NOMBRE1_TUTOR as nombre1TutorC, coalesce(t1.NOMBRE2_TUTOR, '') as nombre2TutorC, t1.APELLIDO1_TUTOR as apellido1TutorC, coalesce(t1.APELLIDO2_TUTOR, '') as apellido2TutorC, "+
                        "t2.NOMBRE1TUTOR as nombre1TutorS, t2.NOMBRE2TUTOR as nombre2TutorS, t2.APELLIDO1TUTOR as apellido1TutorS, t2.APELLIDO2TUTOR as apellido2TutorS, "+
                        "(select m.es from mensajes m where m.catRoot = 'CP_CAT_RFTUTOR' and m.catKey = t1.RELACION_FAMILIAR) as relacionFamiliarC, " +
                        "(select m.es from mensajes m where m.catRoot = 'CP_CAT_RFTUTOR' and m.catKey = CAST(t2.RELACION_FAMILIAR AS CHAR)) as relacionFamiliarS "+
                        "from cartas_consentimientos t1 inner join scan_participante_carta t2 on t1.CODIGO_PARTICIPANTE = t2.CODIGO_PARTICIPANTE and date(t1.FECHA_FIRMA) = date(t2.FECHA_CARTA) "+
                        "inner join participantes t3 on t1.CODIGO_PARTICIPANTE = t3.CODIGO "+
                        "where (t1.NOMBRE1_TUTOR is null or t2.NOMBRE1TUTOR is null or t1.NOMBRE1_TUTOR != t2.NOMBRE1TUTOR) "+
                        "or (t1.NOMBRE2_TUTOR is null or t2.NOMBRE2TUTOR is null or t1.NOMBRE2_TUTOR != t2.NOMBRE2TUTOR) "+
                        "or (t1.APELLIDO1_TUTOR is null or t2.APELLIDO1TUTOR is null or t1.APELLIDO1_TUTOR != t2.APELLIDO1TUTOR) "+
                        "or (t1.APELLIDO2_TUTOR is null or t2.APELLIDO2TUTOR is null or t1.APELLIDO2_TUTOR != t2.APELLIDO2TUTOR) "+
                        "or (t1.RELACION_FAMILIAR is null or t2.RELACION_FAMILIAR is null or t1.RELACION_FAMILIAR != t2.RELACION_FAMILIAR) " +
                        "order by t1.CODIGO_PARTICIPANTE"
        );
        query.setResultTransformer(Transformers.aliasToBean(ComparacionRelFamCartasDto.class));
        return query.list();
    }
}
