package ni.org.ics.estudios.service.influenzauo1;

import ni.org.ics.estudios.domain.influenzauo1.VisitaCasoUO1;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Transactional
@Service("visitaCasoUO1Service")
public class VisitaCasoUO1Service {

    @Resource(name = "sessionFactory")
    private SessionFactory sessionFactory;

    public void saveOrUpdate(VisitaCasoUO1 visitaCasoUO1){
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(visitaCasoUO1);
    }

    public List<VisitaCasoUO1> getVisitasCasosActivos(){
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select vc from VisitaCasoUO1 vc inner join vc.participanteCasoUO1 pc " +
                "where vc.pasive = '0' and pc.activo = '1' and pc.pasive = '0'");
        return query.list();
    }
}
