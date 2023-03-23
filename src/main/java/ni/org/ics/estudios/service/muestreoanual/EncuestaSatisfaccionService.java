package ni.org.ics.estudios.service.muestreoanual;


import ni.org.ics.estudios.domain.muestreoanual.EncuestaSatisfaccion;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.List;

/**
 * Servicio para el objeto EncuestaSatisfaccion
 * 
 * @author Brenda Lopez
 * 
 **/

@Service("encuestaSatisfaccionService")
@Transactional
public class EncuestaSatisfaccionService {
	
	@Resource(name="sessionFactory")
	private SessionFactory sessionFactory;
	
	
	/**
	 * Regresa todos las encuestas de los participantes
	 * 
	 * @return una lista de <code>datos de las encuestas de participantes</code>(s)
	 */

	@SuppressWarnings("unchecked")
	public List<EncuestaSatisfaccion> getEncuestasSatisfaccion() {
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		// Create a Hibernate query (HQL)
		Query query = session.createQuery("FROM EncuestaSatisfaccion");
		// Retrieve all
		return  query.list();
	}
	
	/**
	 * Regresa una Encuesta de Satisfaccion
	 * 
	 * @return una <code>Encuesta de Satisfaccion</code>
	 */

	public EncuestaSatisfaccion getEncuestaSatisfaccion (Long fecha){
		// Retrieve session from Hibernate
	Session session = sessionFactory.getCurrentSession();
	
	Timestamp timeStamp = new Timestamp(fecha);
	Query query = session.createQuery("FROM EncuestaSatisfaccion encsat where encsat.fechaEncuesta = :fechaE");
	query.setTimestamp("fechaE",timeStamp);
	
	EncuestaSatisfaccion encuestaSatisfaccion = (EncuestaSatisfaccion) query.uniqueResult();
	return encuestaSatisfaccion;
	}
	
	/**
	 * Verifica una Encuesta de Satisfaccion
	 * 
	 * @return true or false
	 */

public Boolean checkEncuestaSatisfaccion (Long fecha){
		// Retrieve session from Hibernate
	Session session = sessionFactory.getCurrentSession();
	Timestamp timeStamp = new Timestamp(fecha);
	Query query = session.createQuery("FROM EncuestaSatisfaccion encsat where encsat.fechaEncuesta = :fechaE");
	query.setTimestamp("fechaE",timeStamp);
	
	EncuestaSatisfaccion encuestaSatisfaccion = (EncuestaSatisfaccion) query.uniqueResult();
	if (encuestaSatisfaccion!=null){
		return true;
	}
	else{
		return false;
	}			
	}
	
	/**
	 * Agrega una Encuesta de Satisfaccion
	 * 
	 * 
	 */
public void addEncuestaSatisfaccion(EncuestaSatisfaccion encuestaSatisfaccion){
	Session session = sessionFactory.getCurrentSession();
	session.save(encuestaSatisfaccion);
	}
	
	/**
	 * Actualiza una Encuesta de Satisfaccion
	 * 
	 * 
	 */

public void updateEncuestaSatisfaccion(EncuestaSatisfaccion encuestaSatisfaccion){
	Session session = sessionFactory.getCurrentSession();
	session.update(encuestaSatisfaccion);
	}

	
}
