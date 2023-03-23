package ni.org.ics.estudios.service.muestreoanual;

import ni.org.ics.estudios.domain.muestreoanual.Obsequio;
import ni.org.ics.estudios.domain.muestreoanual.ObsequioId;
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
 * Servicio para el objeto Obsequio
 * 
 * @author Brenda Lopez
 * 
 **/

@Service("obsequiosMAService")
@Transactional
public class ObsequioService {
	
	@Resource(name="sessionFactory")
	private SessionFactory sessionFactory;
	
	
	/**
	 * Regresa todos las Obsequios
	 * 
	 * @return una lista de <code>Obsequios</code>(s)
	 */

	@SuppressWarnings("unchecked")
	public List<Obsequio> getObsequios() {
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		// Create a Hibernate query (HQL)
		Query query = session.createQuery("FROM Obsequio");
		// Retrieve all
		return  query.list();
	}
	
	/**
	 * Regresa todos las Obsequios
	 * 
	 * @return una lista de <code>Obsequios</code>(s)
	 */

	@SuppressWarnings("unchecked")
	public List<Obsequio> getObsequiosSi() {
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		// Create a Hibernate query (HQL)
		Query query = session.createQuery("FROM Obsequio obs where obs.obseqSN = 1");
		// Retrieve all
		return  query.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<Object> getCountObsequios() {
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		// Create a Hibernate query (HQL)
		Query query = session.createSQLQuery("SELECT DATE(fecha_entrega), COUNT(codigo) AS Total FROM obsequios WHERE obseqsn=1 GROUP BY DATE(fecha_entrega);");
		// Retrieve all
		return  query.list();
	}
	
	/**
	 * Regresa todos las Obsequios de hoy
	 * 
	 * @return una lista de <code>Obsequios</code>(s)
	 */

	@SuppressWarnings("unchecked")
	public List<Obsequio> getObsequiosHoy() {
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");      
	    Date dateWithoutTime = null;
		try {
			dateWithoutTime = sdf.parse(sdf.format(new Date()));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Timestamp timeStamp = new Timestamp(dateWithoutTime.getTime());
		// Create a Hibernate query (HQL)
		Query query = session.createQuery("FROM Obsequio obs where obs.obseqSN = 1 and obs.movilInfo.today = :fechaHoy");
		query.setTimestamp("fechaHoy", timeStamp);
		// Retrieve all
		return  query.list();
	}
	
	/**
	 * Regresa una Obsequio
	 * 
	 * @return un <code>Obsequio</code>
	 */

	public Obsequio getObsequio(ObsequioId obId) {
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		Timestamp timeStamp = new Timestamp(obId.getFechaEntrega().getTime());
		Query query = session.createQuery("FROM Obsequio ob where " +
				"ob.obId.codigo = " + obId.getCodigo() + " AND ob.obId.fechaEntrega = :fechaE");
		query.setTimestamp("fechaE",timeStamp);
		
		Obsequio obsequio = (Obsequio) query.uniqueResult();
		return obsequio;
	}
	
	/**
	 * Verifica una Obsequio
	 * 
	 * @return true or false
	 */

	public Boolean checkObsequio(ObsequioId obId) {
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		
		Timestamp timeStamp = new Timestamp(obId.getFechaEntrega().getTime());
		Query query = session.createQuery("FROM Obsequio ob where " +
				"ob.obId.codigo = " + obId.getCodigo() + " AND ob.obId.fechaEntrega = :fechaE");
		query.setTimestamp("fechaE",timeStamp);
		
		Obsequio obsequio = (Obsequio) query.uniqueResult();
		if(obsequio!=null){
			return true;
		}
		else{
			return false;
		}
			
	}
	
	/**
	 * Agrega un Obsequio
	 * 
	 * 
	 */
	public void addObsequio(Obsequio obsequio) {
		Session session = sessionFactory.getCurrentSession();
		session.save(obsequio);
	}
	
	/**
	 * Actualiza un Obsequio
	 * 
	 * 
	 */
	public void updateObsequio(Obsequio obsequio) {
		Session session = sessionFactory.getCurrentSession();
		session.update(obsequio);
	}
	
}
