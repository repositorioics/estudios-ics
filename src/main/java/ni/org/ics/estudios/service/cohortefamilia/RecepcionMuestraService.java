package ni.org.ics.estudios.service.cohortefamilia;

import ni.org.ics.estudios.domain.cohortefamilia.RecepcionMuestra;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Miguel Salinas on 5/17/2017.
 * V1.0
 */
@Transactional
@Service("recepcionMuestraService")
public class RecepcionMuestraService {

    @Resource(name = "sessionFactory")
    private SessionFactory sessionFactory;

    public List<RecepcionMuestra> getRecepcionesMuestras()
    {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from RecepcionMuestra where pasive = '0' ");
        return  query.list();
    }

    public void saveOrUpdate(RecepcionMuestra recepcionMuestra){
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(recepcionMuestra);
    }

}
