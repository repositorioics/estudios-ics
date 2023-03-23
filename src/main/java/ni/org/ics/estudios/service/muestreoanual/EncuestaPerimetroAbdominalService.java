package ni.org.ics.estudios.service.muestreoanual;

import ni.org.ics.estudios.domain.muestreoanual.EncuestaPerimetroAbdominal;
import ni.org.ics.estudios.domain.muestreoanual.EncuestaPerimetroAbdominalId;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.List;

/**
 * Created by Ing. Santiago Carballo on 30/01/2023.
 *
 */

@Service("encuestaPerimetroAbdominalService")
@Transactional
public class EncuestaPerimetroAbdominalService {
    @Resource(name = "sessionFactory")
    private SessionFactory sessionFactory;

    @SuppressWarnings("unchecked")
    public List<EncuestaPerimetroAbdominal> getListEncuestaPerimetroAbdominal() {
        // Retrieve session from Hibernate
        Session session = sessionFactory.getCurrentSession();
        // Create a Hibernate query (HQL)
        Query query = session.createQuery("FROM EncuestaPerimetroAbdominal");
        // Retrieve all
        return query.list();
    }

    public EncuestaPerimetroAbdominal getEncuestaPerimetroAbdominal(EncuestaPerimetroAbdominalId pabdominalId) {
        // Retrieve session from Hibernate
        Session session = sessionFactory.getCurrentSession();

        Query query = session.createQuery("FROM EncuestaPerimetroAbdominal po where po.paId.codigo = " + pabdominalId.getCodigo() + " AND po.paId.fecha = :fecha");
        query.setDate("fecha", pabdominalId.getFecha());


        EncuestaPerimetroAbdominal encuestaPerimetroAbdominal = (EncuestaPerimetroAbdominal) query.uniqueResult();
        return encuestaPerimetroAbdominal;
    }

    /**
     * Verifica los datos
     *
     * @return true or false
     */

    public Boolean checkEncuestaPerimetroAbdominal(EncuestaPerimetroAbdominalId pabdominalId) {
        // Retrieve session from Hibernate
        Session session = sessionFactory.getCurrentSession();
        Timestamp timeStamp = new Timestamp(pabdominalId.getFecha().getTime());
        Query query = session.createQuery("FROM EncuestaPerimetroAbdominal po where po.paId.codigo = " + pabdominalId.getCodigo() + " AND po.paId.fecha = :fechaE");
        query.setTimestamp("fechaE", timeStamp);

        EncuestaPerimetroAbdominal encuestaPerimetroAbdominal = (EncuestaPerimetroAbdominal) query.uniqueResult();
        if (encuestaPerimetroAbdominal != null) {
            return true;
        } else {
            return false;
        }

    }

    /**
     * Agrega los datos
     *
     *
     */
    public void addEncuestaPerimetroAbdominal(EncuestaPerimetroAbdominal encuestaPerimetroAbdominal) {
        Session session = sessionFactory.getCurrentSession();
        session.save(encuestaPerimetroAbdominal);
    }

    /**
     * Actualiza datos
     *
     *
     */
    public void updateEncuestaPerimetroAbdominal(EncuestaPerimetroAbdominal encuestaPerimetroAbdominal) {
        Session session = sessionFactory.getCurrentSession();
        session.update(encuestaPerimetroAbdominal);
    }
}
