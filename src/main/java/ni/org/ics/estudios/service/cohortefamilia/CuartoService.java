package ni.org.ics.estudios.service.cohortefamilia;

import ni.org.ics.estudios.domain.cohortefamilia.*;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Miguel Salinas on 5/3/2017.
 * V1.0
 */
@Transactional
@Service("cuartoService")
public class CuartoService {
    @Resource(name = "sessionFactory")
    private SessionFactory sessionFactory;

    @SuppressWarnings("unchecked")
	public List<Cuarto> getCuartos(){
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from Cuarto where pasive = '0'");
        return query.list();
    }
    
    public void saveOrUdateCuarto(Cuarto cuarto){
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(cuarto);
    }
    
    @SuppressWarnings("unchecked")
	public List<Cama> getCamas(){
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from Cama where pasive = '0'");
        return query.list();
    }
    
    public void saveOrUdateCama(Cama cama){
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(cama);
    }
    
    @SuppressWarnings("unchecked")
	public List<PersonaCama> getPersonasCama(){
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from PersonaCama where pasive = '0'");
        return query.list();
    }
    

    public void saveOrUdatePersonaCama(PersonaCama personaCama){
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(personaCama);
    }    
}

