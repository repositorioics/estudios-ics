package ni.org.ics.estudios.service.cohortefamilia.casos;

import ni.org.ics.estudios.domain.cohortefamilia.casos.SensorCaso;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Miguel Salinas on 11/02/2020.
 * V1.0
 */
@Transactional
@Service("sensoresCasoService")
public class SensoresCasoService {

    @Resource(name = "sessionFactory")
    private SessionFactory sessionFactory;
    
    @SuppressWarnings("unchecked")
	public List<SensorCaso> getSensoresCasos(){
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from SensorCaso v where v.pasive = '0' and v.codigoCaso.inactiva = '0'");
        return query.list();
    }

    public void saveOrUpdateSensorCaso(SensorCaso sensorCaso){
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(sensorCaso);
    }
}
