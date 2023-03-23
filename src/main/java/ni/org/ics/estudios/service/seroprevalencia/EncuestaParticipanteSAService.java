package ni.org.ics.estudios.service.seroprevalencia;

import ni.org.ics.estudios.domain.seroprevalencia.EncuestaParticipanteSA;
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
@Service("encuestaParticipanteSAService")
public class EncuestaParticipanteSAService {
    @Resource(name = "sessionFactory")
    private SessionFactory sessionFactory;

    public List<EncuestaParticipanteSA> getEncuestasParticipantes(){
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from EncuestaParticipanteSA where pasive = '0'");
        return query.list();
    }
    public List<EncuestaParticipanteSA> getEncuestasParticipantesByUser(String username){
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from EncuestaParticipanteSA ec where ec.pasive = false and ec.participanteSA.casaCHF.casa.codigo in (" +
                "select cc.participante.casa.codigo from CartaConsentimiento cc where cc.tamizaje.estudio.codigo in (" +
                " select us.estudio.codigo from UserStudy us where us.usuario.username = :username))");
        query.setParameter("username",username);
        return query.list();
    }

    public EncuestaParticipanteSA getEncuestaParticipanteByCodigo(String codigo){
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from EncuestaParticipanteSA where participanteSA.participanteSA = :codigo");
        query.setParameter("codigo", codigo);
        return (EncuestaParticipanteSA)query.uniqueResult();
    }

    public void saveOrUpdate(EncuestaParticipanteSA encuestaParticipante){
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(encuestaParticipante);
    }
}
