package ni.org.ics.estudios.service.muestreoanual;

import ni.org.ics.estudios.domain.muestreoanual.LactanciaMaterna;
import ni.org.ics.estudios.domain.muestreoanual.LactanciaMaternaId;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.List;

/**
 * Servicio para el objeto Lactancia Materna
 * 
 * @author Brenda Lopez
 * 
 **/

@Service("lactanciaMaternaService")
@Transactional
public class LactanciaMaternaService {
	
	@Resource(name="sessionFactory")
	private SessionFactory sessionFactory;
	
	
	/**
	 * Regresa todos los Datos de las encuestas de Lactancia Materna
	 * 
	 * @return una lista de <code>datos de Lactancia Materna</code>(s)
	 */

	@SuppressWarnings("unchecked")
	public List<LactanciaMaterna> getLactanciaMaterna() {
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		// Create a Hibernate query (HQL)
		Query query = session.createQuery("FROM LactanciaMaterna");
		// Retrieve all
		return  query.list();
	}
	
	/**
	 * Regresa los datos de las encuestas de lactancia materna
	 * 
	 * 
	 * @return una <code>Encuesta de lactancia materna</code>
	 */

	public LactanciaMaterna getLactanciaMaterna (LactanciaMaternaId lmId) {
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		Timestamp timeStamp = new Timestamp(lmId.getFechaEncLM().getTime());
		Query query = session.createQuery("FROM LactanciaMaterna lacmat where lacmat.lmId.codigo = " + lmId.getCodigo() + " AND lacmat.lmId.fechaEncLM = :fechaE");
		query.setTimestamp("fechaE", timeStamp);
		
		LactanciaMaterna lactanciamaterna = (LactanciaMaterna) query.uniqueResult();
		return lactanciamaterna;
	}
	
	/**
	 * Verifica los datos de las encuestas de lactancia materna
	 * 
	 * @return true or false
	 */

	public Boolean checkLactanciaMaterna(LactanciaMaternaId lmId) {
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		
		Timestamp timeStamp = new Timestamp(lmId.getFechaEncLM().getTime());
		Query query = session.createQuery("FROM LactanciaMaterna lacmat where lacmat.lmId.codigo = " + lmId.getCodigo() + " AND lacmat.lmId.fechaEncLM = :fechaE");
		query.setTimestamp("fechaE", timeStamp);
		
		LactanciaMaterna lactanciamaterna = (LactanciaMaterna) query.uniqueResult();
		if(lactanciamaterna!=null){
			return true;
		}
		else{
			return false;
		}
			
	}
	
	/**
	 * Agrega los datos de las encuestas de lactancia materna
	 * 
	 * 
	 */
	public void addLactanciaMaterna(LactanciaMaterna lactanciaMaterna) {
		Session session = sessionFactory.getCurrentSession();
		session.save(lactanciaMaterna);
	}
	
	/**
	 * Actualiza una encuesta de lactancia materna
	 * 
	 * 
	 */
	
	public void updateLactanciaMaterna(LactanciaMaterna lactanciaMaterna) {
		Session session = sessionFactory.getCurrentSession();
		session.update(lactanciaMaterna);
	}
		
}
