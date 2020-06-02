package ni.org.ics.estudios.service.covid;

import ni.org.ics.estudios.domain.cohortefamilia.ParticipanteCohorteFamilia;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * Created by ICS on 01/06/2020.
 */

@Service("CovidService")
@Transactional
public class CovidService {
    @Resource(name="sessionFactory")
    private SessionFactory sessionFactory;


    public ParticipanteCohorteFamilia getParticipanteCHFByCodigo(Integer codigo){
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from ParticipanteCohorteFamilia where participante.codigo = :codigo");
        query.setParameter("codigo",codigo);
        return (ParticipanteCohorteFamilia)query.uniqueResult();
    }

}
