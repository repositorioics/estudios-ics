package ni.org.ics.estudios.service.muestreoanual;

import ni.org.ics.estudios.domain.muestreoanual.PesoyTalla;
import ni.org.ics.estudios.domain.muestreoanual.PesoyTallaId;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.List;

/**
 * Servicio para el objeto Peso y talla
 * 
 * @author Brenda Lopez
 * 
 **/

@Service("pesoyTallaService")
@Transactional
public class PesoyTallaService {

	@Resource(name = "sessionFactory")
	private SessionFactory sessionFactory;

	/**
	 * Regresa todos los Datos de los pesos y tallas de los participantes
	 * 
	 * @return una lista de <code>datos de casa</code>(s)
	 */

	@SuppressWarnings("unchecked")
	public List<PesoyTalla> getPesoytallaEB() {
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		// Create a Hibernate query (HQL)
		Query query = session.createQuery("FROM PesoyTalla");
		// Retrieve all
		return query.list();
	}

	/**
	 * Regresa los datos de los pesos y las tallas de los participantes
	 * 
	 * 
	 * @return los <code>datos de pesos y tallas</code>
	 */

	public PesoyTalla getPesoytalla(PesoyTallaId ptId) {
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		
		Query query = session.createQuery("FROM PesoyTalla pt where pt.ptId.codigo = " + ptId.getCodigo() + " AND pt.ptId.fechaPT = :fechaP");
		query.setDate("fechaP", ptId.getFechaPT());
		
		
		PesoyTalla pesoytalla = (PesoyTalla) query.uniqueResult();
		return pesoytalla;
	}

	/**
	 * Verifica los datos
	 * 
	 * @return true or false
	 */

	public Boolean checkPesoyTallaEB(PesoyTallaId ptId) {
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		Timestamp timeStamp = new Timestamp(ptId.getFechaPT().getTime());
		Query query = session.createQuery("FROM PesoyTalla pt where pt.ptId.codigo = " + ptId.getCodigo() + " AND pt.ptId.fechaPT = :fechaE");
		query.setTimestamp("fechaE",timeStamp);
		
		PesoyTalla pesoytalla = (PesoyTalla) query.uniqueResult();
		if (pesoytalla != null) {
			return true;
		} else {
			return false;
		}

	}

	/**
	 * Agrega los datos de peso y talla
	 * 
	 * 
	 */
	public void addPesoyTallaEB(PesoyTalla pesoytalla) {
		Session session = sessionFactory.getCurrentSession();
		session.save(pesoytalla);
	}

	/**
	 * Actualiza datos de los pesos y las tallas
	 * 
	 * 
	 */
	public void updatePesoyTallaEB(PesoyTalla pesoytalla) {
		Session session = sessionFactory.getCurrentSession();
		session.update(pesoytalla);
	}

}
