package ni.org.ics.estudios.service.cohortefamilia;

import ni.org.ics.estudios.domain.cohortefamilia.encuestas.EncuestaCasa;
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
@Service("encuestaCasaService")
public class EncuestaCasaService {

    @Resource(name = "sessionFactory")
    private SessionFactory sessionFactory;

    public List<EncuestaCasa> getEncuestasCasa()
    {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from EncuestaCasa where pasive = '0' ");
        return  query.list();
    }

    public List<EncuestaCasa> getEncuestasCasaByUser(String username)
    {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from EncuestaCasa ec where ec.pasive = false and ec.casaChf.casa.codigo in (" +
                "select cc.participante.casa.codigo from CartaConsentimiento cc where cc.tamizaje.estudio.codigo in (" +
                " select us.estudio.codigo from UserStudy us where us.usuario.username = :username))");
        query.setParameter("username",username);
        return  query.list();
    }

    public void saveOrUpdate(EncuestaCasa encuestaCasa){
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(encuestaCasa);
    }
 }
