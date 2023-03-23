package ni.org.ics.estudios.service.cohortefamilia.casos;

import ni.org.ics.estudios.domain.cohortefamilia.casos.VisitaSeguimientoCasoSintomas;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by Miguel Salinas on 5/11/2017.
 * V1.0
 */
@Transactional
@Service("visitaSeguimientoCasoSintomasService")
public class VisitaSeguimientoCasoSintomasService {

    @Resource(name = "sessionFactory")
    private SessionFactory sessionFactory;

    @SuppressWarnings("unchecked")
	public List<VisitaSeguimientoCasoSintomas> getVisitaSeguimientoCasoSintomas(){
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from VisitaSeguimientoCasoSintomas v where v.pasive = '0' and v.codigoVisitaCaso.codigoParticipanteCaso.codigoCaso.inactiva = '0'");
        return query.list();
    }
    
	public VisitaSeguimientoCasoSintomas getVisitaSeguimientoCasoSintoma(String codigoCasoSintoma){
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from VisitaSeguimientoCasoSintomas v where v.pasive = '0' and v.codigoCasoSintoma = :codigoCasoSintoma");
        query.setParameter("codigoCasoSintoma", codigoCasoSintoma);
        return (VisitaSeguimientoCasoSintomas) query.uniqueResult();
    }
    
    @SuppressWarnings("unchecked")
	public List<VisitaSeguimientoCasoSintomas> getVisitaSeguimientoCasoSintomas(String codigoCasoVisita){
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from VisitaSeguimientoCasoSintomas v where v.pasive = '0' and v.codigoVisitaCaso.codigoCasoVisita = :codigoCasoVisita order by v.fechaSintomas");
        query.setParameter("codigoCasoVisita", codigoCasoVisita);
        return query.list();
    }
    
    public boolean checkVisitaSeguimientoCasoSintomas(String codigoCaso,String fechaSintomas){
        Session session = sessionFactory.getCurrentSession();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");      
	    Date dateSint = null;
		try {
			dateSint = sdf.parse(fechaSintomas);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Timestamp timeStamp = new Timestamp(dateSint.getTime());
		Timestamp timeStamp2 = new Timestamp(dateSint.getTime()+86340000);
        Query query = session.createQuery("from VisitaSeguimientoCasoSintomas v where v.pasive = '0' and " +
        		"v.codigoVisitaCaso.codigoParticipanteCaso.codigoCasoParticipante = :codigoCaso and v.fechaSintomas between :fechaSintomaI and :fechaSintomaF");
        query.setParameter("codigoCaso", codigoCaso);
        query.setTimestamp("fechaSintomaI", timeStamp);
        query.setTimestamp("fechaSintomaF", timeStamp2);
        return query.list().size()>0;
    }

    public void saveOrUpdateVisitaSeguimientoCasoSintomas(VisitaSeguimientoCasoSintomas sintoma){
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(sintoma);
    }
}
