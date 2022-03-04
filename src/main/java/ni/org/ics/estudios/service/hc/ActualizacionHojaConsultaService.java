package ni.org.ics.estudios.service.hc;

import ni.org.ics.estudios.domain.Participante;
import ni.org.ics.estudios.dto.HojaConsulta.*;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.transform.Transformers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by miguel on 15/2/2022.
 */

@Service("actualizacionHojaConsultaService")
@Transactional
public class ActualizacionHojaConsultaService {

    private static final Logger logger = LoggerFactory.getLogger(ActualizacionHojaConsultaService.class);

    @Resource(name="sessionFactory")
    private SessionFactory sessionFactory;

    /**
     *  Obtiene un lista de todos los Participantes  para el sistema hoja de consulta digital
     * @return List<HCParticipanteDto>
     */
    @SuppressWarnings("unchecked")
    public List<HCParticipanteDto> getParticipantes(){
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select a.codigo as codigo, a.nombre1 as nombre1, a.nombre2 as nombre2, a.apellido1 as apellido1, a.apellido2 as apellido2, a.sexo as sexo, a.fechaNac as fechaNac, " +
                        " a.nombre1Tutor as nombre1Tutor, a.nombre2Tutor as nombre2Tutor, a.apellido1Tutor as apellido1Tutor, a.apellido2Tutor as apellido2Tutor, b.estPart as estPart, c.direccion as direccion " +
                        "from Participante a, ParticipanteProcesos b, Casa c " +
                        "where a.codigo = b.codigo  " +
                        "and a.casa.codigo = c.codigo");
        query.setResultTransformer(Transformers.aliasToBean(HCParticipanteDto.class));
        return query.list();
    }

    /****
     * Obtiene la lista del catálogo de escuelas para el sistema hoja de consulta digital
      * @return List<HCEscuelasDto>
     */
    public List<HCEscuelasDto> getEscuelas() {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createSQLQuery("select escuela as codigo, escuela_descripcion as nombre from escuelas");
        query.setResultTransformer(Transformers.aliasToBean(HCEscuelasDto.class));
        return query.list();
    }

    /****
     * Obtiene la lista de tipos de consentimientos para el sistema hoja de consulta digital
     * @return List<HCTipoConsentimientoDto>
     */
    public List<HCTipoConsentimientoDto> getTiposConsentimientos() {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createSQLQuery("select cons as cons, desc_cons as descCons from tipo_consentimiento");
        query.setResultTransformer(Transformers.aliasToBean(HCTipoConsentimientoDto.class));
        return query.list();
    }

    /****
     * Obtiene la lista con los consentimientos de cada participante para el sistema hoja de consulta digital
     * @return List<HCConsentimientoDto>
     */
    public List<HCConsentimientoDto> getConsentimientosFromOldScan() {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createSQLQuery("select Fecha as fecha, codigo as codigo, CAST(cons as UNSIGNED) as cons, CAST(asentChik as UNSIGNED) as asentChik, " +
                "CAST(ParteB as UNSIGNED) as parteB, CAST(ParteC as UNSIGNED) as parteC, CAST(ParteD as UNSIGNED) as parteD, CAST(asentimientoesc as UNSIGNED) as asentimientoEsc, " +
                "CAST(ParteE as UNSIGNED) as parteE, CAST(ParteF as UNSIGNED) as parteF, TipoPartTrans as tipoPartTrans, Reactivacion as reactivacion, ahora as ahora, retirado as retirado from scan");
        query.setResultTransformer(Transformers.aliasToBean(HCConsentimientoDto.class));
        return query.list();
    }

    public List<HCConsentimientoDto> getConsentimientosFromNewScan() {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createSQLQuery("select t1.FECHA_CARTA as fecha , t1.CODIGO_PARTICIPANTE as codigo, CAST(tc.cons AS UNSIGNED INTEGER) as cons, " + //, null as asentChik, " +
                "( select if(t3.ACEPTA, 1,0)  from scan_detalle_parte t3 inner join scan_catalog_parte t2 on t3.IDPARTE = t2.IDPARTE " +
                "where t3.IDPARTICIPANTECARTA = t1.IDPARTICIPANTECARTA and  (t2.PARTE = 'B' or t2.PARTE = 'Parte B')) as parteB, " +
                "( select if(t3.ACEPTA, 1,0) from scan_detalle_parte t3 inner join scan_catalog_parte t2 on t3.IDPARTE = t2.IDPARTE " +
                "where t3.IDPARTICIPANTECARTA = t1.IDPARTICIPANTECARTA and  (t2.PARTE = 'C' or t2.PARTE = 'Parte C')) as parteC, " +
                "( select if(t3.ACEPTA, 1,0) from scan_detalle_parte t3 inner join scan_catalog_parte t2 on t3.IDPARTE = t2.IDPARTE " +
                "where t3.IDPARTICIPANTECARTA = t1.IDPARTICIPANTECARTA and  (t2.PARTE = 'D' or t2.PARTE = 'Parte D')) as parteD, " +
                "if (t1.TIPO_ASENTIMIENTO='1', 1, null) as asentimientoEsc, " +
                "( select if(t3.ACEPTA, 1,0) from scan_detalle_parte t3 inner join scan_catalog_parte t2 on t3.IDPARTE = t2.IDPARTE " +
                "where t3.IDPARTICIPANTECARTA = t1.IDPARTICIPANTECARTA and  (t2.PARTE = 'E' or t2.PARTE = 'Parte E')) as parteE, " +
                "( select if(t3.ACEPTA, 1,0) from scan_detalle_parte t3 inner join scan_catalog_parte t2 on t3.IDPARTE = t2.IDPARTE " +
                "where t3.IDPARTICIPANTECARTA = t1.IDPARTICIPANTECARTA and  (t2.PARTE = 'F' or t2.PARTE = 'Parte F')) as parteF, " +
                "(select m.es from mensajes m where m.catRoot = 'CAT_CASOS_INDICE_MIEMBRO' and m.catKey = t1.ES_CASO_INDICE_O_MIEMBRO) as tipoPartTrans, " +
                "'' as reactivacion, t1.FECHA_REGISTRO as ahora " +
                "from scan_participante_carta t1 inner join scan_catalog_version tv on t1.IDVERSION = tv.IDVERSION, tipo_consentimiento tc " +
                "where tc.estudio = tv.CODIGO_ESTUDIO and t1.PASIVO = '0'");
        query.setResultTransformer(Transformers.aliasToBean(HCConsentimientoDto.class));
        return query.list();
    }

}
