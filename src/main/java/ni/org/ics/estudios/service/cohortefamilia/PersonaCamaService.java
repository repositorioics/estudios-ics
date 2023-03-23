package ni.org.ics.estudios.service.cohortefamilia;

import ni.org.ics.estudios.domain.cohortefamilia.PersonaCama;
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
@Service("personaCamaService")
public class PersonaCamaService {

    @Resource(name = "sessionFactory")
    private SessionFactory sessionFactory;

    public List<PersonaCama> getPersonasCamaByCama(String cama)
    {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from PersonaCama where cama.codigoCama = :codigo");
        query.setParameter("codigo", cama);
        return query.list();
    }

    public void saveOrUdatePersonaCama(PersonaCama personaCama)
    {
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(personaCama);
    }

    public List<PersonaCama> getPersonasCama()
    {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from PersonaCama where pasive = '0'");
        return query.list();
    }
}
