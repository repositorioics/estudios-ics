package ni.org.ics.estudios.service.influenzauo1;

import ni.org.ics.estudios.domain.Tamizaje;
import ni.org.ics.estudios.domain.influenzauo1.ParticipanteCasoUO1;
import ni.org.ics.estudios.dto.muestras.MxDto;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Transactional
@Service("participanteCasoUO1Service")
public class ParticipanteCasoUO1Service {

    @Resource(name = "sessionFactory")
    private SessionFactory sessionFactory;

    public void saveOrUpdate(ParticipanteCasoUO1 participanteCasoUO1){
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(participanteCasoUO1);
    }

    public List<ParticipanteCasoUO1> getCasosActivos(){
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from ParticipanteCasoUO1 where activo = '1' and pasive = '0' order by  fechaIngreso asc");
        return query.list();
    }

    public ParticipanteCasoUO1 getCasoActivoParticipante(Integer codigoParticipante){
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from ParticipanteCasoUO1 where activo = '1' and pasive = '0' and participante.codigo = :codigo");
        query.setParameter("codigo", codigoParticipante);
        return (ParticipanteCasoUO1)query.uniqueResult();
    }

    public List<ParticipanteCasoUO1> getCasos(){
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from ParticipanteCasoUO1 where pasive = '0' order by  fechaIngreso asc");
        return query.list();
    }

    public ParticipanteCasoUO1 getCasoByCodigo(String codigo){
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from ParticipanteCasoUO1 where pasive = '0' and codigoCasoParticipante = :codigo");
        query.setParameter("codigo", codigo);
        return (ParticipanteCasoUO1)query.uniqueResult();
    }
    //region todo: buscar caso menor de 30
    public List<MxDto>getCasoUO1Ultimo30Day(Integer codigo, Integer intervalo){
        Session session = sessionFactory.getCurrentSession();
        String sql = "SELECT upc.CODIGO_PARTICIPANTE as codigoParticipante, upc.ACTIVO as estado, upc.FECHA_INGRESO as fechaInicio,upc.FECHA_DESACTIVACION as fechaInactiva, " +
                " coalesce(DATEDIFF(date_add(CURDATE(),interval 1 day),cast(upc.FECHA_DESACTIVACION AS DATE)),'0') as diasInactiva " +
                " FROM uo1_participantes_casos upc " +
                " WHERE cast(upc.FECHA_DESACTIVACION as date) BETWEEN DATE_SUB(CURDATE(), INTERVAL :intervalo DAY) AND CURDATE() AND upc.CODIGO_PARTICIPANTE =:codigo and upc.pasivo='0'";
        Query query = session.createSQLQuery(sql);
        query.setParameter("intervalo", intervalo);
        query.setParameter("codigo", codigo);
        query.setResultTransformer(Transformers.aliasToBean(MxDto.class));
        return query.list();
    }
    //endregion
}
