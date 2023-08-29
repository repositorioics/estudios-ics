package ni.org.ics.estudios.service.cohortefamilia.casos;

import ni.org.ics.estudios.domain.cohortefamilia.casos.SensorCaso;
import ni.org.ics.estudios.dto.SensorCasoDto;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.transform.Transformers;
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

    public List<SensorCasoDto> getSensoresCasosDto(){
        Session session = sessionFactory.getCurrentSession();

        Query query = session.createQuery("SELECT s.codigoSensor as codigoSensor, s.codigoCaso.codigoCaso as codigoCaso, s.numeroSensor as numeroSensor, " +
                "s.area.codigo as area, s.cuarto.codigo as cuarto, s.fechaColocacion as fechaColocacion, s.fechaRetiro as fechaRetiro, " +
                "s.horaRetiro as horaRetiro, s.observacionRetiro as observacionRetiro, s.sensorSN as sensorSN, s.razonNoColocaSensor as razonNoColocaSensor, " +
                "s.recordUser as recordUser, s.recordDate as recordDate, s.pasive as pasive, s.estado as estado, s.deviceid as deviceid " +
                "FROM SensorCaso s ");
                //"FROM SensorCaso s where s.fechaRetiro is null ");
        query.setResultTransformer(Transformers.aliasToBean(SensorCasoDto.class));
        return query.list();
    }
}
