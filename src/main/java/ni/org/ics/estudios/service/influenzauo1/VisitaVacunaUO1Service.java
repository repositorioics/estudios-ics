package ni.org.ics.estudios.service.influenzauo1;

import ni.org.ics.estudios.domain.influenzauo1.VisitaVacunaUO1;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Transactional
@Service("visitaVacunaUO1Service")
public class VisitaVacunaUO1Service {

    @Resource(name = "sessionFactory")
    private SessionFactory sessionFactory;

    public void saveOrUpdate(VisitaVacunaUO1 visitaCasoUO1){
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(visitaCasoUO1);
    }

    public List<VisitaVacunaUO1> getVisitasVacunas(){
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select vc from VisitaVacunaUO1 vc where vc.pasive = '0'");
        return query.list();
    }
}
