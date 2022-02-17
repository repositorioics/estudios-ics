package ni.org.ics.estudios.service.hc;

import ni.org.ics.estudios.domain.Participante;
import ni.org.ics.estudios.dto.HojaConsulta.HCConsentimientoDto;
import ni.org.ics.estudios.dto.HojaConsulta.HCEscuelasDto;
import ni.org.ics.estudios.dto.HojaConsulta.HCParticipanteDto;
import ni.org.ics.estudios.dto.HojaConsulta.HCTipoConsentimientoDto;
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
    public List<HCConsentimientoDto> getConsentimientos() {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createSQLQuery("select Fecha as fecha, codigo as codigo, cons as cons, asentChik as asentChik, ParteB as parteB, ParteC as parteC, ParteD as parteD, asentimientoesc as asentimientoEsc, " +
                "ParteE as parteE, ParteF as parteF, TipoPartTrans as tipoPartTrans, Reactivacion as reactivacion, ahora as ahora, retirado as retirado from scan");
        query.setResultTransformer(Transformers.aliasToBean(HCConsentimientoDto.class));
        return query.list();
    }

}
