package ni.org.ics.estudios.service.muestreoanual;

import ni.org.ics.estudios.domain.muestreoanual.VisitaTerrenoId;
import ni.org.ics.estudios.domain.muestreoanual.VisitaTerrenoMA;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.List;

/**
 * Servicio para el objeto Muestra
 * 
 * @author Brenda Lopez
 * 
 **/

@Service("visitaTerrenoMAService")
@Transactional
public class VisitaTerrenoService {
	
	@Resource(name="sessionFactory")
	private SessionFactory sessionFactory;
	
	
	/**
	 * Regresa todos las VisitaTerreno
	 * 
	 * @return una lista de <code>VisitaTerreno</code>(s)
	 */

	@SuppressWarnings("unchecked")
	public List<VisitaTerrenoMA> getVisitaTerrenos() {
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		// Create a Hibernate query (HQL)
		Query query = session.createQuery("FROM VisitaTerrenoMA ");
		// Retrieve all
		return  query.list();
	}
	
	

	public VisitaTerrenoMA getVisitaTerreno(VisitaTerrenoId vtId) {
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		Timestamp timeStamp = new Timestamp(vtId.getFechaVisita().getTime());
		Query query = session.createQuery("FROM VisitaTerrenoMA vt where " +
				"vt.visitaId.codigo = " + vtId.getCodigo() + " AND vt.visitaId.fechaVisita = :fechaV");
		query.setTimestamp("fechaV", timeStamp);

        VisitaTerrenoMA visita = (VisitaTerrenoMA) query.uniqueResult();
		return visita;
	}
	
	/**
	 * Verifica una VisitaTerreno
	 * 
	 * @return true or false
	 */

	public Boolean checkMuestra(VisitaTerrenoId vtId) {
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		Timestamp timeStamp = new Timestamp(vtId.getFechaVisita().getTime());
		Query query = session.createQuery("FROM VisitaTerrenoMA vt where " +
				"vt.visitaId.codigo = " + vtId.getCodigo() + " AND vt.visitaId.fechaVisita = :fechaV");
		query.setTimestamp("fechaV", timeStamp);

        VisitaTerrenoMA visita = (VisitaTerrenoMA) query.uniqueResult();
		if(visita!=null){
			return true;
		}
		else{
			return false;
		}
			
	}
	
	/**
	 * Agrega un VisitaTerreno
	 * 
	 * 
	 */
	public void addVisitaTerreno(VisitaTerrenoMA visita) {
		Session session = sessionFactory.getCurrentSession();
		session.save(visita);
	}
	
	/**
	 * Actualiza un VisitaTerreno
	 * 
	 * 
	 */
	public void updateVisitaTerreno(VisitaTerrenoMA visita) {
		Session session = sessionFactory.getCurrentSession();
		session.update(visita);
	}
	
}
