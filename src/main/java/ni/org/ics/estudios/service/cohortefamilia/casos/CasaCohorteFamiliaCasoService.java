package ni.org.ics.estudios.service.cohortefamilia.casos;

import ni.org.ics.estudios.domain.cohortefamilia.casos.CasaCohorteFamiliaCaso;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * Created by Miguel Salinas on 5/11/2017.
 * V1.0
 */
@Transactional
@Service("casaCohorteFamiliaCasoService")
public class CasaCohorteFamiliaCasoService {

    @Resource(name = "sessionFactory")
    private SessionFactory sessionFactory;

    @SuppressWarnings("unchecked")

	public List<CasaCohorteFamiliaCaso> getCasaCohorteFamiliaCasos(){
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from CasaCohorteFamiliaCaso c where c.pasive = '0' and c.inactiva = '0'");
        return query.list();
    }
    /* Servicios Devuelve una Casas por parametro */
    @SuppressWarnings("unchecked")
	public List<CasaCohorteFamiliaCaso> getCasaCohorteFamiliaCasos(String parametro){
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from CasaCohorteFamiliaCaso c where c.pasive = '0' and c.inactiva = '0' and c.casa.codigoCHF =:parametro");
        query.setParameter("parametro",parametro);
        return query.list();
    }

    public CasaCohorteFamiliaCaso getCasaCohorteFamiliaCasosByCodigo(String codigo){
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from CasaCohorteFamiliaCaso c where c.pasive = '0' and c.inactiva = '0' and codigoCaso = :codigo");
        query.setParameter("codigo",codigo);
        return (CasaCohorteFamiliaCaso)query.uniqueResult();
    }

    public CasaCohorteFamiliaCaso getCasaCohorteFamiliaCasoByCodigoCasa(String codigo){
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from CasaCohorteFamiliaCaso c where c.pasive = '0' and c.inactiva = '0' and c.casa.codigoCHF = :codigo");
        query.setParameter("codigo",codigo);
        return (CasaCohorteFamiliaCaso)query.uniqueResult();
    }

    public CasaCohorteFamiliaCaso getCasaCohorteFamiliaCasosByCodigoCasaFecha(String codigo, Date fechaInicio){
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from CasaCohorteFamiliaCaso c where c.pasive = '0' and c.inactiva = '0' and c.casa.codigoCHF = :codigo and c.fechaInicio = :fechaInicio");
        query.setParameter("codigo",codigo);
        query.setParameter("fechaInicio", fechaInicio);
        return (CasaCohorteFamiliaCaso)query.uniqueResult();
    }

    @SuppressWarnings("unchecked")
    public List<CasaCohorteFamiliaCaso> getCasaCohorteFamiliaCasosByCodigoCasa(String codigo){
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from CasaCohorteFamiliaCaso c where c.pasive = '0' and c.casa.codigoCHF = :codigo");
        query.setParameter("codigo",codigo);
        return query.list();
    }

    public void saveOrUpdateCasaCohorteFamiliaCaso(CasaCohorteFamiliaCaso casa){
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(casa);
    }
}
