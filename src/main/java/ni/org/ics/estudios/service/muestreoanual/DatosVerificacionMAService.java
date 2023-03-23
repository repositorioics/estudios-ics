package ni.org.ics.estudios.service.muestreoanual;

import ni.org.ics.estudios.domain.muestreoanual.DatosVerificacionMA;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by miguel on 13/3/2022.
 */
@Service("datosVerificacionMAService")
@Transactional
public class DatosVerificacionMAService {

    @Resource(name = "sessionFactory")
    private SessionFactory sessionFactory;

    public List<DatosVerificacionMA> getDatosVerificacionByCodigoParticipante(Integer codigoParticipante) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from DatosVerificacionMA where codigoParticipante = :codigoParticipante ");
        query.setParameter("codigoParticipante", codigoParticipante);
        return query.list();
    }

    public void saveDatosVerificacionMA(DatosVerificacionMA objeto) {
        Session session = sessionFactory.getCurrentSession();
        session.save(objeto);
    }


}
