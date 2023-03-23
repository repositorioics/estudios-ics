package ni.org.ics.estudios.service.seroprevalencia;

import ni.org.ics.estudios.domain.seroprevalencia.ParticipanteSeroprevalencia;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Miguel Salinas on 5/25/2017.
 * V1.0
 */
@Transactional
@Service("participanteSeroprevalenciaService")
public class ParticipanteSeroprevalenciaService {
    @Resource(name="sessionFactory")
    private SessionFactory sessionFactory;

    @SuppressWarnings("unchecked")
    public List<ParticipanteSeroprevalencia> getParticipantesSA(){
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from ParticipanteSeroprevalencia where pasive = '0'");
        return query.list();
    }

    public void saveOrUpdateParticipanteSA(ParticipanteSeroprevalencia participante){
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(participante);
    }
}
