package ni.org.ics.estudios.service.cohortefamilia;

import ni.org.ics.estudios.domain.cohortefamilia.encuestas.EncuestaParticipante;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Miguel Salinas on 5/4/2017.
 * V1.0
 */
@Transactional
@Service("encuestaParticipanteService")
public class EncuestaParticipanteService {

    @Resource(name = "sessionFactory")
    private SessionFactory sessionFactory;

    public List<EncuestaParticipante> getEncuestasParticipantes(){
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from EncuestaParticipante where pasive = '0'");
        return query.list();
    }
    public List<EncuestaParticipante> getEncuestasParticipantesByUser(String username){
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from EncuestaParticipante ec where ec.pasive = '0' and ec.participante.casaCHF.casa.codigo in (" +
                "select cc.participante.casa.codigo from CartaConsentimiento cc where cc.tamizaje.estudio.codigo in (" +
                " select us.estudio.codigo from UserStudy us where us.usuario.username = :username))");
        query.setParameter("username",username);
        return query.list();
    }

    public List<EncuestaParticipante> getEncuestaParticipanteByCodigo(Integer codigo){
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from EncuestaParticipante where participante.participante.codigo = :codigo");
        query.setParameter("codigo", codigo);
        return query.list();
    }

    public void saveOrUpdate(EncuestaParticipante encuestaParticipante){
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(encuestaParticipante);
    }
}
