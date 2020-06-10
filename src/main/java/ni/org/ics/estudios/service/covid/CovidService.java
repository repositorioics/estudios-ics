package ni.org.ics.estudios.service.covid;

import ni.org.ics.estudios.domain.cohortefamilia.ParticipanteCohorteFamilia;
import ni.org.ics.estudios.domain.covid19.CasoCovid19;
import ni.org.ics.estudios.domain.covid19.ParticipanteCasoCovid19;
import ni.org.ics.estudios.domain.covid19.ParticipanteCovid19;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

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

    public List<ParticipanteCovid19> getParticipantesCovid19(){
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from ParticipanteCovid19 p where p.pasive = '0'");
        return query.list();
    }

    public void saveOrUpdateParticipanteCovid19(ParticipanteCovid19 participanteCovid19){
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(participanteCovid19);
    }

    public List<CasoCovid19> getCasosCovid19(){
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from CasoCovid19 c where c.pasive = '0' and c.inactivo = '0'");
        return query.list();
    }

    public void saveOrUpdateCasoCovid19(CasoCovid19 casa){
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(casa);
    }

    /***
     * Obtiene una lista de todos los participantes en monitoreo
     */
    public List<ParticipanteCasoCovid19> getParticipantesCasosCovid19(){
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from ParticipanteCasoCovid19 p where p.pasive = '0' and p.codigoCaso.inactivo = '0'");
        return query.list();
    }

    public void saveOrUpdateParticipanteCasoCovid19(ParticipanteCasoCovid19 participanteCasoCovid19){
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(participanteCasoCovid19);
    }

}
