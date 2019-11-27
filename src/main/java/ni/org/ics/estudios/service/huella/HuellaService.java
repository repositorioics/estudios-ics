package ni.org.ics.estudios.service.huella;

import ni.org.ics.estudios.domain.huella.Huella;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Miguel Salinas on 13/11/2019.
 * V1.0
 */

@Service("huellaService")
@Transactional
public class HuellaService {

    //Configuration conf2 = new Configuration().configure("hibernate.huella.cfg.xml");
    //SessionFactory huellaSessionFactory = conf2.buildSessionFactory();

    @Autowired
    @Qualifier(value="huellaSessionFactory")
    private SessionFactory huellaSessionFactory;

    public List<Huella> getHuellas(){
        Session session = huellaSessionFactory.getCurrentSession();
        Query query = session.createQuery("from Huella ");
        return  query.list();
    }

    public void saveOrUpdateHuella(Huella huella)
    {
        Session session = huellaSessionFactory.getCurrentSession();
        session.saveOrUpdate(huella);
    }
}
