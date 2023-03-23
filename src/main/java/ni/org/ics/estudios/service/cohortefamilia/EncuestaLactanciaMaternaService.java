package ni.org.ics.estudios.service.cohortefamilia;

import ni.org.ics.estudios.domain.cohortefamilia.encuestas.EncuestaLactanciaMaterna;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Miguel Salinas on 5/5/2017.
 * V1.0
 */
@Transactional
@Service("encuestaLactanciaMaternaService")
public class EncuestaLactanciaMaternaService {

    @Resource(name = "sessionFactory")
    private SessionFactory sessionFactory;

    public List<EncuestaLactanciaMaterna> getEncuestasLactanciaMaterna(){
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from EncuestaLactanciaMaterna where pasive = '0'");
        return query.list();
    }

    public List<EncuestaLactanciaMaterna> getEncuestasLactanciaMaternaByUser(String username){
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select ec from EncuestaLactanciaMaterna ec where ec.pasive = '0' and ec.participante.casaCHF.casa.codigo in (" +
                "select cc.participante.casa.codigo from CartaConsentimiento cc where cc.tamizaje.estudio.codigo in (" +
                " select us.estudio.codigo from UserStudy us where us.usuario.username = :username))");
        query.setParameter("username",username);
        return query.list();
    }

    public EncuestaLactanciaMaterna getEncuestaLactanciaMaternaByCodigo(String codigo){
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from EncuestaLactanciaMaterna where participante.participanteCHF = :codigo");
        query.setParameter("codigo", codigo);
        return (EncuestaLactanciaMaterna)query.uniqueResult();
    }

    public void saveOrUpdateEncuestaLactanciaMaterna(EncuestaLactanciaMaterna encuestaLactanciaMaterna){
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(encuestaLactanciaMaterna);
    }
}
