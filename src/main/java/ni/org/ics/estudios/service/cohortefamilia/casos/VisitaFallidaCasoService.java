package ni.org.ics.estudios.service.cohortefamilia.casos;

import ni.org.ics.estudios.domain.cohortefamilia.casos.VisitaFallidaCaso;
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
@Service("visitaFallidaCasoService")
public class VisitaFallidaCasoService {

    @Resource(name = "sessionFactory")
    private SessionFactory sessionFactory;

    @SuppressWarnings("unchecked")
	public List<VisitaFallidaCaso> getVisitaFallidaCasos(){
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from VisitaFallidaCaso v where v.pasive = '0' and codigoParticipanteCaso.codigoCaso.inactiva = '0'");
        return query.list();
    }
    
    
	public VisitaFallidaCaso getVisitaFallidaCaso(String codigoFallaVisita){
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from VisitaFallidaCaso v where v.pasive = '0' and v.codigoFallaVisita =:codigoFallaVisita");
        query.setParameter("codigoFallaVisita", codigoFallaVisita);
        return (VisitaFallidaCaso) query.uniqueResult();
    }
    
    @SuppressWarnings("unchecked")
	public List<VisitaFallidaCaso> getVisitaFallidaCasos(String codigoParticipanteCaso){
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from VisitaFallidaCaso v where v.pasive = '0' and v.codigoParticipanteCaso.codigoCasoParticipante =:codigoParticipanteCaso order by v.fechaVisita");
        query.setParameter("codigoParticipanteCaso", codigoParticipanteCaso);
        return query.list();
    }

    public void saveOrUpdateVisitaFallidaCaso(VisitaFallidaCaso visita){
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(visita);
    }
}
