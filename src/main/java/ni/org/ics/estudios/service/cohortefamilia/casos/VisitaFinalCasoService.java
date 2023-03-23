package ni.org.ics.estudios.service.cohortefamilia.casos;

import ni.org.ics.estudios.domain.cohortefamilia.casos.VisitaFinalCaso;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

import java.util.List;

/**
 * Created by Miguel Salinas on 5/11/2017.
 * V1.0
 */
@Transactional
@Service("visitaFinalCasoService")
public class VisitaFinalCasoService {

    @Resource(name = "sessionFactory")
    private SessionFactory sessionFactory;
    
    public VisitaFinalCaso getVisitaFinalCaso(String codigoParticipanteCaso){
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from VisitaFinalCaso v where v.codigoParticipanteCaso.codigoCasoParticipante = :codigoParticipanteCaso");
        query.setParameter("codigoParticipanteCaso", codigoParticipanteCaso);
        return (VisitaFinalCaso) query.uniqueResult();
    }

    @SuppressWarnings("unchecked")
	public List<VisitaFinalCaso> getVisitaFinalCasos(){
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from VisitaFinalCaso v where v.pasive = '0' and v.codigoParticipanteCaso.codigoCaso.inactiva = '0'");
        return query.list();
    }

    public void saveOrUpdateVisitaFinalCasoCaso(VisitaFinalCaso visita){
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(visita);
    }
}
