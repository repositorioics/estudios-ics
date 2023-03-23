package ni.org.ics.estudios.service.entomologia;

import ni.org.ics.estudios.domain.entomologia.CuestionarioHogar;
import ni.org.ics.estudios.domain.entomologia.CuestionarioHogarPoblacion;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * Created by miguel on 15/8/2022.
 */
@Transactional
@Service("cuestionarioHogarService")
public class CuestionarioHogarService {

    @Resource(name = "sessionFactory")
    private SessionFactory sessionFactory;

    public void saveCuestionarioHogar(CuestionarioHogar cuestionarioHogar){
        Session session = sessionFactory.getCurrentSession();
        session.save(cuestionarioHogar);
    }

    public void saveCuestionarioHogarPoblacion(CuestionarioHogarPoblacion cuestionarioHogarPoblacion){
        Session session = sessionFactory.getCurrentSession();
        session.save(cuestionarioHogarPoblacion);
    }

    public List<CuestionarioHogar> getCuestionariosHogar(){
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from CuestionarioHogar where pasive = '0' order by recordDate, codigoCasa");
        return query.list();
    }

    public List<CuestionarioHogarPoblacion> getCuestionariosHogarPoblacion(){
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from CuestionarioHogarPoblacion where pasive = '0' order by recordDate, codigoEncuesta, edad, sexo");
        return query.list();
    }

    public List<CuestionarioHogar> getCuestionariosHogarByRangoFechas(Date fechaInicio, Date fechaFin){
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from CuestionarioHogar where pasive = '0' and recordDate between :fechaInicio and :fechaFin order by recordDate, codigoCasa");
        query.setParameter("fechaInicio", fechaInicio);
        query.setParameter("fechaFin", fechaFin);
        return query.list();
    }

    public List<CuestionarioHogarPoblacion> getCuestionariosHogarPobByRangoFechas(Date fechaInicio, Date fechaFin){
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from CuestionarioHogarPoblacion where pasive = '0' and recordDate between :fechaInicio and :fechaFin order by recordDate, codigoEncuesta, edad, sexo");
        query.setParameter("fechaInicio", fechaInicio);
        query.setParameter("fechaFin", fechaFin);
        return query.list();
    }
}
