package ni.org.ics.estudios.service.cohortefamilia.casos;

import ni.org.ics.estudios.domain.cohortefamilia.casos.VisitaSeguimientoCaso;

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
@Service("visitaSeguimientoCasoService")
public class VisitaSeguimientoCasoService {

    @Resource(name = "sessionFactory")
    private SessionFactory sessionFactory;
    
    public VisitaSeguimientoCaso getVisitaSeguimientoCaso(String codigoCasoVisita){
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from VisitaSeguimientoCaso v where v.codigoCasoVisita = :codigoCasoVisita");
        query.setParameter("codigoCasoVisita", codigoCasoVisita);
        return (VisitaSeguimientoCaso) query.uniqueResult();
    }

    @SuppressWarnings("unchecked")
	public List<VisitaSeguimientoCaso> getVisitaSeguimientoCasos(){
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from VisitaSeguimientoCaso v where v.pasive = '0' and v.codigoParticipanteCaso.codigoCaso.inactiva = '0'");
        return query.list();
    }
    
	public boolean checkVisitaSeguimientoCasos(String codigoCaso, String visita, String fechaVisita){
        Session session = sessionFactory.getCurrentSession();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");      
	    Date dateVisit = null;
		try {
			dateVisit = sdf.parse(fechaVisita);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Timestamp timeStamp = new Timestamp(dateVisit.getTime());
		Timestamp timeStamp2 = new Timestamp(dateVisit.getTime()+86340000);
        Query query = session.createQuery("from VisitaSeguimientoCaso v where v.pasive = '0' and " +
        		"((v.codigoParticipanteCaso.codigoCasoParticipante = :codigoCaso and v.fechaVisita between :fechaVisitaI and :fechaVisitaF) or (v.codigoParticipanteCaso.codigoCasoParticipante = :codigoCaso and v.visita = :visita))");
        query.setParameter("codigoCaso", codigoCaso);
        query.setParameter("visita", visita);
        query.setTimestamp("fechaVisitaI", timeStamp);
        query.setTimestamp("fechaVisitaF", timeStamp2);
        return query.list().size()>0;
    }
    
    @SuppressWarnings("unchecked")
	public List<VisitaSeguimientoCaso> getVisitaSeguimientoCasos(String codigoCaso){
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from VisitaSeguimientoCaso v where v.pasive = '0' and v.codigoParticipanteCaso.codigoCasoParticipante = :codigoCaso order by v.fechaVisita");
        query.setParameter("codigoCaso", codigoCaso);
        return query.list();
    }

    public void saveOrUpdateVisitaSeguimientoCaso(VisitaSeguimientoCaso visita){
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(visita);
    }
}
