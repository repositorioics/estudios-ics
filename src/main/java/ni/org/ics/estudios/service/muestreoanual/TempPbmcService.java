package ni.org.ics.estudios.service.muestreoanual;

import ni.org.ics.estudios.domain.muestreoanual.TempPbmc;
import ni.org.ics.estudios.domain.muestreoanual.TempPbmcId;
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
 * Servicio para el objeto TempPbmc
 * 
 * @author Brenda Lopez
 * 
 **/

@Service("tPbmcService")
@Transactional
public class TempPbmcService {
	
	@Resource(name="sessionFactory")
	private SessionFactory sessionFactory;
	
	
	/**
	 * Regresa todos las TempPbmc
	 * 
	 * @return una lista de <code>TempPbmc</code>(s)
	 */

	@SuppressWarnings("unchecked")
	public List<TempPbmc> getTempPbmcs() {
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		// Create a Hibernate query (HQL)
		Query query = session.createQuery("FROM TempPbmc");
		// Retrieve all
		return  query.list();
	}
	
	
	@SuppressWarnings("unchecked")
	public List<Object> getStatTempPbmcs() {
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		// Create a Hibernate query (HQL)
		Query query = session.createSQLQuery("SELECT DATE(fecha_temp_pbmc) as fecha, COUNT(temperatura) AS Cuenta, 12 as LimInf, 30 as LimSup, Min(temperatura) AS Minimo, Avg(temperatura) AS Promedio, Max(temperatura) AS Maximo FROM temp_pbmc GROUP BY DATE(fecha_temp_pbmc);");
		// Retrieve all
		return  query.list();
	}
	
	/**
	 * Regresa todos las TempPbmc de hoy
	 * 
	 * @return una lista de <code>TempPbmc</code>(s)
	 */

	@SuppressWarnings("unchecked")
	public List<TempPbmc> getTempPbmcsHoy() {
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
		Query query = session.createQuery("FROM TempPbmc tp where tp.fecreg = :fechaTemp");
		query.setTimestamp("fechaTemp", timeStamp);
		// Retrieve all
		return  query.list();
	}
	
	
	/**
	 * Regresa todos las TempPbmc de hoy
	 * 
	 * @return una lista de <code>TempPbmc</code>(s)
	 */

	@SuppressWarnings("unchecked")
	public List<TempPbmc> getTempPbmcsFueradeRango() {
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		// Create a Hibernate query (HQL)
		Query query = session.createQuery("FROM TempPbmc tp where (tp.temperatura < 10 or tp.temperatura > 30)");
		// Retrieve all
		return  query.list();
	}
	
	
	/**
	 * Regresa una TempPbmc
	 * 
	 * @return un <code>TempPbmc</code>
	 */

	public TempPbmc getTempPbmc(TempPbmcId tPbmcId) {
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		Timestamp timeStamp = new Timestamp(tPbmcId.getFechaTempPbmc().getTime());
		Query query = session.createQuery("FROM TempPbmc tPbmc where tPbmc.tempPbmcId.recurso = '"
				+ tPbmcId.getRecurso() +  "' AND tPbmc.tempPbmcId.fechaTempPbmc = :fechaTempPbmc");
		query.setTimestamp("fechaTempPbmc", timeStamp);
		
		TempPbmc tPbmc = (TempPbmc) query.uniqueResult();
		return tPbmc;
	}
	
	/**
	 * Verifica una TempPbmc
	 * 
	 * @return true or false
	 */

	public Boolean checkTempPbmc(TempPbmcId tPbmcId) {
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		
		Timestamp timeStamp = new Timestamp(tPbmcId.getFechaTempPbmc().getTime());
		Query query = session.createQuery("FROM TempPbmc tPbmc where tPbmc.tempPbmcId.recurso = '"
				+ tPbmcId.getRecurso() +  "' AND tPbmc.tempPbmcId.fechaTempPbmc = :fechaTempPbmc");
		query.setTimestamp("fechaTempPbmc", timeStamp);
		
		TempPbmc tPbmc = (TempPbmc) query.uniqueResult();
		if(tPbmc!=null){
			return true;
		}
		else{
			return false;
		}
			
	}
	
	/**
	 * Agrega un TempPbmc
	 * 
	 * 
	 */
	public void addTempPbmc(TempPbmc tPbmc) {
		Session session = sessionFactory.getCurrentSession();
		session.save(tPbmc);
	}
	
	/**
	 * Actualiza un TempPbmc
	 * 
	 * 
	 */
	public void updateTempPbmc(TempPbmc tPbmc) {
		Session session = sessionFactory.getCurrentSession();
		session.update(tPbmc);
	}
	
}
