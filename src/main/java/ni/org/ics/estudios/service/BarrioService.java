package ni.org.ics.estudios.service;

import ni.org.ics.estudios.domain.catalogs.Barrio;

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
@Service("barrioService")
@Transactional
public class BarrioService {

    @Resource(name = "sessionFactory")
    private SessionFactory sessionFactory;

    
	@SuppressWarnings("unchecked")
	public List<Barrio> getBarrios(){
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from Barrio ");
        return query.list();
    }

    @SuppressWarnings("unchecked")
    public Barrio getBarrioById(Integer id){
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from Barrio b where b.codigo=:id");
        query.setParameter("id",id);
        return (Barrio) query.uniqueResult();
    }
    
}
