package ni.org.ics.estudios.service.influenzauo1;

import ni.org.ics.estudios.domain.influenzauo1.SintomasVisitaCasoUO1;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Transactional
@Service("sintomasVisitaCasoUO1Service")
public class SintomasVisitaCasoUO1Service {

    @Resource(name = "sessionFactory")
    private SessionFactory sessionFactory;

    public void saveOrUpdate(SintomasVisitaCasoUO1 visitaCasoUO1){
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(visitaCasoUO1);
    }

    public List<SintomasVisitaCasoUO1> getSintomasVisitasCasosUO1(){
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select sv from SintomasVisitaCasoUO1 sv inner join sv.codigoCasoVisita vc inner join vc.participanteCasoUO1 pc " +
                "where sv.pasive = '0' and vc.pasive = '0' and pc.activo = '1' and pc.pasive = '0'");
        return query.list();
    }
}
