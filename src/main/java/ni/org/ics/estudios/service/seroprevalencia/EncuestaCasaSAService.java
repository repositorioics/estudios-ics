package ni.org.ics.estudios.service.seroprevalencia;

import ni.org.ics.estudios.domain.seroprevalencia.EncuestaCasaSA;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Miguel Salinas on 5/25/2017.
 * V1.0
 */
@Transactional
@Service("encuestaCasaSAService")
public class EncuestaCasaSAService {
    @Resource(name = "sessionFactory")
    private SessionFactory sessionFactory;

    public List<EncuestaCasaSA> getEncuestasCasa()
    {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from EncuestaCasaSA where pasive = '0' ");
        return  query.list();
    }

    public List<EncuestaCasaSA> getEncuestasCasaByUser(String username)
    {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from EncuestaCasaSA ec where ec.pasive = '0' and ec.casaCHF.casa.codigo in (" +
                "select cc.participante.casa.codigo from CartaConsentimiento cc where cc.tamizaje.estudio.codigo in (" +
                " select us.estudio.codigo from UserStudy us where us.usuario.username = :username))");
        query.setParameter("username",username);
        return  query.list();
    }

    public void saveOrUpdate(EncuestaCasaSA encuestaCasa){
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(encuestaCasa);
    }
}
