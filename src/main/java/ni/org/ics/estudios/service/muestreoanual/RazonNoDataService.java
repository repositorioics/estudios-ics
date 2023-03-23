package ni.org.ics.estudios.service.muestreoanual;

import ni.org.ics.estudios.domain.muestreoanual.RazonNoData;
import ni.org.ics.estudios.domain.muestreoanual.RazonNoDataId;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.List;

/**
 * Servicio para el objeto Pinchazo
 * 
 * @author Brenda Lopez
 * 
 **/

@Service("razonNoDataService")
@Transactional
public class RazonNoDataService {
	
	@Resource(name="sessionFactory")
	private SessionFactory sessionFactory;
	
	
	/**
	 * Regresa todos las RazonNoData
	 * 
	 * @return una lista de <code>RazonNoData</code>(s)
	 */

	@SuppressWarnings("unchecked")
	public List<RazonNoData> getRazonNoData() {
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		// Create a Hibernate query (HQL)
		Query query = session.createQuery("FROM RazonNoData");
		// Retrieve all
		return  query.list();
	}
	
	/**
	 * Regresa una RazonNoData
	 * 
	 * @return un <code>RazonNoData</code>
	 */

	public RazonNoData getRazonNoData(RazonNoDataId rndId) {
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		Timestamp timeStamp = new Timestamp(rndId.getFechaRegistro().getTime());
		Query query = session.createQuery("FROM RazonNoData rnd where rnd.rndId.codigo = "
				+ rndId.getCodigo() +  " AND rnd.rndId.fechaRegistro = :fechaRegistro");
		query.setTimestamp("fechaRegistro", timeStamp);
		
		RazonNoData rnd = (RazonNoData) query.uniqueResult();
		return rnd;
	}
	
	/**
	 * Verifica una RazonNoData
	 * 
	 * @return true or false
	 */

	public Boolean checkRazonNoData(RazonNoDataId rndId) {
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		Timestamp timeStamp = new Timestamp(rndId.getFechaRegistro().getTime());
		Query query = session.createQuery("FROM RazonNoData rnd where rnd.rndId.codigo = "
				+ rndId.getCodigo() +  " AND rnd.rndId.fechaRegistro = :fechaRegistro");
		query.setTimestamp("fechaRegistro", timeStamp);
		
		RazonNoData rnd = (RazonNoData) query.uniqueResult();
		
		if(rnd!=null){
			return true;
		}
		else{
			return false;
		}
			
	}
	
	/**
	 * Agrega un RazonNoData
	 * 
	 * 
	 */
	public void addRazonNoData(RazonNoData rnd) {
		Session session = sessionFactory.getCurrentSession();
		session.save(rnd);
	}
	
	/**
	 * Actualiza un RazonNoData
	 * 
	 * 
	 */
	public void updateRazonNoData(RazonNoData rnd) {
		Session session = sessionFactory.getCurrentSession();
		session.update(rnd);
	}
	
}
