package ni.org.ics.estudios.service;

import ni.org.ics.estudios.domain.catalogs.Estudio;

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
@Service("estudioService")
@Transactional
public class EstudioService {

    @Resource(name = "sessionFactory")
    private SessionFactory sessionFactory;

    
	@SuppressWarnings("unchecked")
	public List<Estudio> getEstudios(){
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from Estudio");
        return query.list();
    }

    public Estudio getEstudioByCodigo(int codigo){
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from Estudio where codigo = :codigo");
        query.setParameter("codigo", codigo);
        return (Estudio)query.uniqueResult();
    }
}
