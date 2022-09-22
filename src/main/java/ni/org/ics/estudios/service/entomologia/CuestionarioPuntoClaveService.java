package ni.org.ics.estudios.service.entomologia;

import ni.org.ics.estudios.domain.entomologia.CuestionarioPuntoClave;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * Created by miguel on 2/9/2022.
 */

@Transactional
@Service("cuestionarioPuntoClaveService")
public class CuestionarioPuntoClaveService {
    @Resource(name = "sessionFactory")
    private SessionFactory sessionFactory;

    public void saveCuestionarioPuntoClave(CuestionarioPuntoClave cuestionarioPuntoClave){
        Session session = sessionFactory.getCurrentSession();
        session.save(cuestionarioPuntoClave);
    }

    public List<CuestionarioPuntoClave> getCuestionariosPuntoClave(){
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from CuestionarioPuntoClave where movilInfo.eliminado  = false order by movilInfo.today");
        return query.list();
    }

    public List<CuestionarioPuntoClave> getCuestionariosPuntoClaveByRangoFechas(Date fechaInicio, Date fechaFin){
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from CuestionarioPuntoClave where  movilInfo.eliminado  = false and movilInfo.today between :fechaInicio and :fechaFin order by movilInfo.today");
        query.setParameter("fechaInicio", fechaInicio);
        query.setParameter("fechaFin", fechaFin);
        return query.list();
    }

}
