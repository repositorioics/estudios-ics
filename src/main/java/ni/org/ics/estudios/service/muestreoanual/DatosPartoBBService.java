package ni.org.ics.estudios.service.muestreoanual;

import ni.org.ics.estudios.domain.muestreoanual.DatosPartoBB;
import ni.org.ics.estudios.domain.muestreoanual.DatosPartoBBId;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.List;

/**
 * Servicio para el objeto parto BB
 * 
 * @author Brenda Lopez
 * 
 **/

@Service("datosPartoBBService")
@Transactional
public class DatosPartoBBService {

	@Resource(name = "sessionFactory")
	private SessionFactory sessionFactory;

	/**
	 * Regresa todos los Datos de DatosPartoBB de los participantes
	 * 
	 * @return una lista de <code>DatosPartoBB</code>(s)
	 */

	@SuppressWarnings("unchecked")
	public List<DatosPartoBB> getDatosPartoBB() {
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		// Create a Hibernate query (HQL)
		Query query = session.createQuery("FROM DatosPartoBB");
		// Retrieve all
		return query.list();
	}

	/**
	 * Regresa los datos de los DatosPartoBB de los participantes
	 * 
	 * 
	 * @return los <code>DatosPartoBB</code>
	 */

	public DatosPartoBB getDatosPartoBB(DatosPartoBBId datosPartoBBId) {
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		
		Query query = session.createQuery("FROM DatosPartoBB dp where dp.datosPartoId.codigo = " + datosPartoBBId.getCodigo() + " AND dp.datosPartoId.fechaDatosParto = :fechaP");
		query.setDate("fechaP", datosPartoBBId.getFechaDatosParto());
		
		
		DatosPartoBB datosPartoBB = (DatosPartoBB) query.uniqueResult();
		return datosPartoBB;
	}

	/**
	 * Verifica los datos
	 * 
	 * @return true or false
	 */

	public Boolean checkDatosPartoBB(DatosPartoBBId datosPartoBBId) {
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		Timestamp timeStamp = new Timestamp(datosPartoBBId.getFechaDatosParto().getTime());
		Query query = session.createQuery("FROM DatosPartoBB dp where dp.datosPartoId.codigo = " + datosPartoBBId.getCodigo() + " AND dp.datosPartoId.fechaDatosParto = :fechaE");
		query.setTimestamp("fechaE",timeStamp);
		
		DatosPartoBB datosPartoBB = (DatosPartoBB) query.uniqueResult();
		if (datosPartoBB != null) {
			return true;
		} else {
			return false;
		}

	}

	/**
	 * Agrega los datos de DatosPartoBB
	 * 
	 * 
	 */
	public void addDatosPartoBB(DatosPartoBB datosPartoBB) {
		Session session = sessionFactory.getCurrentSession();
		session.save(datosPartoBB);
	}

	/**
	 * Actualiza datos de los DatosPartoBB
	 * 
	 * 
	 */
	public void updateDatosPartoBB(DatosPartoBB datosPartoBB) {
		Session session = sessionFactory.getCurrentSession();
		session.update(datosPartoBB);
	}

}
