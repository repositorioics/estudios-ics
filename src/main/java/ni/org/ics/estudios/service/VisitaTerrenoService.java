package ni.org.ics.estudios.service;

import ni.org.ics.estudios.domain.VisitaTerreno;

import ni.org.ics.estudios.domain.VisitaTerrenoParticipante;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Miguel Salinas on 5/2/2017.
 * V1.0
 */
@Service("visitaTerrenoService")
@Transactional
public class VisitaTerrenoService {

    @Resource(name = "sessionFactory")
    private SessionFactory sessionFactory;

    
	@SuppressWarnings("unchecked")
	public List<VisitaTerreno> getVisitasTerreno()
    {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from VisitaTerreno ");
        return query.list();
    }


    public void saveOrUpdateVisitaTerreno(VisitaTerreno visita)
    {
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(visita);
    }

    /**
     * Crear o actualizar un VisitaTerrenoParticipante
     * @param visitaTerrenoParticipante
     */
    public void saveOrUpdateVisitaTerrenoParticipante(VisitaTerrenoParticipante visitaTerrenoParticipante){
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(visitaTerrenoParticipante);
    }

    @SuppressWarnings("unchecked")
    public List<VisitaTerrenoParticipante> getVisitasTerrenoParticipante(){
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from VisitaTerrenoParticipante ");
        return query.list();
    }

}
