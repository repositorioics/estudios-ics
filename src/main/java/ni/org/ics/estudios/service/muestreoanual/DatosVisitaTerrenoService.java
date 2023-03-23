package ni.org.ics.estudios.service.muestreoanual;

import ni.org.ics.estudios.domain.muestreoanual.DatosVisitaTerreno;
import ni.org.ics.estudios.domain.muestreoanual.DatosVisitaTerrenoId;
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

@Service("datosVisitaTerrenoService")
@Transactional
public class DatosVisitaTerrenoService {
	
	@Resource(name="sessionFactory")
	private SessionFactory sessionFactory;
	
	
	/**
	 * Regresa todos las DatosVisitaTerreno
	 * 
	 * @return una lista de <code>DatosVisitaTerreno</code>(s)
	 */

	@SuppressWarnings("unchecked")
	public List<DatosVisitaTerreno> getDatosVisitaTerrenos() {
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		// Create a Hibernate query (HQL)
		Query query = session.createQuery("FROM DatosVisitaTerreno");
		// Retrieve all
		return  query.list();
	}
	
	

	public DatosVisitaTerreno getDatosVisitaTerreno(DatosVisitaTerrenoId vtId) {
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		Timestamp timeStamp = new Timestamp(vtId.getFechaVisita().getTime());
		Query query = session.createQuery("FROM DatosVisitaTerreno vt where " +
				"vt.visitaId.codigo = " + vtId.getCodigo() + " AND vt.visitaId.fechaVisita = :fechaV");
		query.setTimestamp("fechaV", timeStamp);
		
		DatosVisitaTerreno visita = (DatosVisitaTerreno) query.uniqueResult();
		return visita;
	}
	
	/**
	 * Verifica una DatosVisitaTerreno
	 * 
	 * @return true or false
	 */

	public Boolean checkDatosVisitaTerreno(DatosVisitaTerrenoId vtId) {
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		Timestamp timeStamp = new Timestamp(vtId.getFechaVisita().getTime());
		Query query = session.createQuery("FROM DatosVisitaTerreno vt where " +
				"vt.visitaId.codigo = " + vtId.getCodigo() + " AND vt.visitaId.fechaVisita = :fechaV");
		query.setTimestamp("fechaV", timeStamp);
		
		DatosVisitaTerreno visita = (DatosVisitaTerreno) query.uniqueResult();
		if(visita!=null){
			return true;
		}
		else{
			return false;
		}
			
	}
	
	/**
	 * Agrega un DatosVisitaTerreno
	 * 
	 * 
	 */
	public void addDatosVisitaTerreno(DatosVisitaTerreno visita) {
		Session session = sessionFactory.getCurrentSession();
		session.save(visita);
	}
	
	/**
	 * Actualiza un DatosVisitaTerreno
	 * 
	 * 
	 */
	public void updateDatosVisitaTerreno(DatosVisitaTerreno visita) {
		Session session = sessionFactory.getCurrentSession();
		session.update(visita);
	}
	
}
