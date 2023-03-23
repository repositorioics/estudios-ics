package ni.org.ics.estudios.service.muestreoanual;

import ni.org.ics.estudios.domain.muestreoanual.NewVacuna;
import ni.org.ics.estudios.domain.muestreoanual.NewVacunaId;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.List;

/**
 * Servicio para el objeto NewVacuna
 * 
 * @author Brenda Lopez
 * 
 **/

@Service("newVacunaService")
@Transactional
public class NewVacunaService {
	
	@Resource(name="sessionFactory")
	private SessionFactory sessionFactory;
	
	
	/**
	 * Regresa todos las NewVacuna
	 * 
	 * @return una lista de <code>NewVacuna</code>(s)
	 */

	@SuppressWarnings("unchecked")
	public List<NewVacuna> getNewVacunas() {
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		// Create a Hibernate query (HQL)
		Query query = session.createQuery("FROM NewVacuna");
		// Retrieve all
		return  query.list();
	}
	
	/**
	 * Regresa una NewVacuna
	 * 
	 * @return un <code>NewVacuna</code>
	 */

	public NewVacuna getNewVacuna(NewVacunaId vacId) {
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		Timestamp timeStamp = new Timestamp(vacId.getFechaRegistroVacuna().getTime());
		Query query = session.createQuery("FROM NewVacuna vac where " +
				"vac.vacunaId.codigo = " + vacId.getCodigo() + " AND vac.vacunaId.fechaRegistroVacuna = :fechaV");
		query.setTimestamp("fechaV", timeStamp);
		
		NewVacuna vacuna = (NewVacuna) query.uniqueResult();
		return vacuna;
	}
	
	/**
	 * Verifica una NewVacuna
	 * 
	 * @return true or false
	 */

	public Boolean checkNewVacuna(NewVacunaId vacId) {
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		
		Timestamp timeStamp = new Timestamp(vacId.getFechaRegistroVacuna().getTime());
		Query query = session.createQuery("FROM NewVacuna vac where " +
				"vac.vacunaId.codigo = " + vacId.getCodigo() + " AND vac.vacunaId.fechaRegistroVacuna = :fechaV");
		query.setTimestamp("fechaV", timeStamp);
		
		NewVacuna vacuna = (NewVacuna) query.uniqueResult();
		if(vacuna!=null){
			return true;
		}
		else{
			return false;
		}
			
	}
	
	/**
	 * Agrega un NewVacuna
	 * 
	 * 
	 */
	public void addNewVacuna(NewVacuna vacuna) {
		Session session = sessionFactory.getCurrentSession();
		session.save(vacuna);
	}
	
	/**
	 * Actualiza un NewVacuna
	 * 
	 * 
	 */
	public void updateNewVacuna(NewVacuna vacuna) {
		Session session = sessionFactory.getCurrentSession();
		session.update(vacuna);
	}
	
}
